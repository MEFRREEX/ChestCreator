package com.mefrreex.chestcreator.command.subcommand;

import cn.nukkit.command.CommandSender;
import com.mefrreex.chestcreator.chest.ChestManager;
import com.mefrreex.chestcreator.command.BaseCommand;
import com.mefrreex.chestcreator.command.BaseSubCommand;
import com.mefrreex.chestcreator.utils.Language;

public class ReloadSubCommand extends BaseSubCommand {

    public ReloadSubCommand(BaseCommand command) {
        super("reload", "Reload forms", command);
        this.setPermission("chestcreator.reload");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String label, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }

        try {
            ChestManager.getChests().clear();
            ChestManager.loadAll();
        } catch(Exception exception) {
            sender.sendMessage(Language.get("command-reload-error"));
            exception.printStackTrace();
            return false;
        }

        sender.sendMessage(Language.get("command-reload-success"));
        return true;
    } 
}