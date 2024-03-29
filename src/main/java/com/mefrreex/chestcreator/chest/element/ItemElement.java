package com.mefrreex.chestcreator.chest.element;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;
import com.mefrreex.chestcreator.chest.action.Action;
import com.mefrreex.chestcreator.utils.Format;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter 
@Setter
@ToString
public class ItemElement {
    
    private String id;
    private int meta;
    private int count;
    private String name;

    private boolean canTake;
    private boolean close;

    private List<String> lore = new ArrayList<>();

    private List<Action> actions = new ArrayList<>();

    private static final String RESET = TextFormat.RESET.toString() + TextFormat.WHITE.toString(); 

    public ItemElement(String namespaceId, String name) {
        this.id = namespaceId;
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
        Item item = Item.fromString(id);
        item.setCount(count);
        item.setDamage(meta);
        item.setCustomName(RESET + Format.format(name, player));
    
        if (lore != null) {
            item.setLore(lore.stream()
                .map(line -> RESET + Format.format(line, player))
                .toArray(String[]::new));
        }
    
        return item;
    }
}
