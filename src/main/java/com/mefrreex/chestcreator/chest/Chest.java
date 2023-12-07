package com.mefrreex.chestcreator.chest;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandMap;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.inventory.InventoryType;
import com.mefrreex.chestcreator.chest.ChestManager.PlayerChest;
import com.mefrreex.chestcreator.chest.action.Action;
import com.mefrreex.chestcreator.chest.command.ChestCommand;
import com.mefrreex.chestcreator.chest.command.ChestCommandExecutor;
import com.mefrreex.chestcreator.chest.element.ItemElement;
import com.mefrreex.chestcreator.event.ChestSendEvent;
import com.mefrreex.chestcreator.utils.Format;
import me.iwareq.fakeinventories.FakeInventory;
import me.iwareq.fakeinventories.util.ItemHandler;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
@ToString
public class Chest {
    
    @SerializedName("command") private ChestCommand command;
    private transient ChestCommandExecutor executableCommand;

    @SerializedName("type") private InventoryType type;
    @SerializedName("switchMode") private ChestSwitchMode switchMode;
    @SerializedName("title") private String title;
    @SerializedName("items") private Map<Integer, ItemElement> items = new HashMap<>();

    @SerializedName("openActions") private List<Action> openActions = new ArrayList<>();
    @SerializedName("closeActions") private List<Action> closeActions = new ArrayList<>();

    public Chest() {
        this(InventoryType.CHEST);
    }

    public Chest(InventoryType type) {
        this(type, "");
    }

    public Chest(InventoryType type, String title) {
        this.title = title; 
    }

    /**
     * Initialize the chest
     */
    public void init() {
        if (command != null && command.isEnable()) {
            this.registerCommand();
        }
    }

    private void registerCommand() {
        CommandMap map = Server.getInstance().getCommandMap();
        this.executableCommand = new ChestCommandExecutor(command, this);
        map.register("ChestCreator", executableCommand);
    }

    /**
     * Set chest item
     * @param item ItemElement
     */
    public Chest setItem(int slot, ItemElement item) {
        items.put(slot, item);
        return this;
    }

    /**
     * Add an action to open the chest
     */
    public Chest addOpenAction(Action action) {
        openActions.add(action);
        return this;
    }

    /**
     * Add an action to close the chest
     */
    public Chest addCloseAction(Action action) {
        closeActions.add(action);
        return this;
    }

    public ChestSwitchMode getSwitchMode() {
        return switchMode != null ? switchMode : ChestSwitchMode.DEFAULT;
    }

    /**
     * Create a chest in FakeInventories
     * @param player Player
     * @return Inventory
     */
    public Inventory build(Player player) {
        FakeInventory inventory = new FakeInventory(type, Format.format(title, player));
        inventory.setDefaultItemHandler((item, event) -> event.setCancelled());

        items.forEach((slot, item) -> {
            ItemHandler handler  = (i, event) -> {
                item.getActions().forEach(action -> {
                    if (!item.isCanTake()) event.setCancelled();
                    if (item.isClose()) inventory.close(player);
                    action.execute(player);
                });
            };
    
            inventory.setItem(slot, item.getItem(player), handler);
        });

        return inventory;
    }

    /**
     * Send chest to player
     * @param player Player 
     */
    public void send(Player player) {
        ChestSendEvent event = new ChestSendEvent(this, player);
        Server.getInstance().getPluginManager().callEvent(event);
            
        if (event.isCancelled()) {
            return;
        }

        openActions.forEach(action -> {
            action.execute(player);
        });

        Inventory inventory = this.build(player);
        ChestManager.getPlayerChest().put(player, new PlayerChest(this, inventory));
        player.addWindow(inventory);
    }
}
