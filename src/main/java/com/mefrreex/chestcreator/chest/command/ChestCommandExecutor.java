package com.mefrreex.chestcreator.chest.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.mefrreex.chestcreator.chest.Chest;

public class ChestCommandExecutor extends Command {

    private final Chest chest;

    public ChestCommandExecutor(ChestCommand command, Chest chest) {
        super(command.getName(), command.getDescription());
        this.setAliases(command.getAliases());
        this.setPermission(command.getPermission());
        this.chest = chest;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }
        if (sender instanceof Player player) {
            chest.send(player);
        }
        return true;
    }
}
