package com.mefrreex.chestcreator.chest.element;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;
import com.mefrreex.chestcreator.chest.action.Action;
import com.mefrreex.chestcreator.utils.Format;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString
public class ItemElement {
    
    @SerializedName("id") private String id;
    @SerializedName("name") private String name;
    @SerializedName("canTake") private boolean canTake;
    @SerializedName("close") private boolean close;

    @SerializedName("lore") 
    private List<String> lore = new ArrayList<>();

    @SerializedName("actions") 
    private List<Action> actions = new ArrayList<>();

    private static final String RESET = TextFormat.RESET.toString() + TextFormat.WHITE.toString(); 

    public ItemElement(int id, int damage, String name) {
        this.id = id + ":" + damage;
        this.name = name;
    }

    /**
     * Add lore line
     */
    public ItemElement addLore(String line) {
        this.lore.add(line);
        return this;
    }

    /**
     * Add item action
     */
    public ItemElement addAction(Action action) {
        actions.add(action);
        return this;
    }

    /**
     * Get item for player
     * @param player Player
     * @return Item
     */
    public Item getItem(Player player) {
        String[] data = id.split(":");
        int itemId = Integer.parseInt(data[0]);
        int itemDamage = Integer.parseInt(data[1]);
    
        Item item = Item.get(itemId, itemDamage);
        item.setCustomName(RESET + Format.format(name, player));
    
        if (lore != null) {
            item.setLore(lore.stream()
                .map(line -> RESET + Format.format(line, player))
                .toArray(String[]::new));
        }
    
        return item;
    }
}
