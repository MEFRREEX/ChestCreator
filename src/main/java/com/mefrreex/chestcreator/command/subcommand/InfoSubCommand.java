package com.mefrreex.chestcreator.command.subcommand;

import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginDescription;
import com.mefrreex.chestcreator.ChestCreator;
import com.mefrreex.chestcreator.command.BaseCommand;
import com.mefrreex.chestcreator.command.BaseSubCommand;

public class InfoSubCommand extends BaseSubCommand {

    public InfoSubCommand(BaseCommand command) {
        super("info", "Info", command);
        this.setPermission("chestcreator.info");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String label, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }

        PluginDescription description = ChestCreator.get().getDescription();

        StringBuilder builder = new StringBuilder();
        builder.append("§l§a" + description.getName() + "§r");
        builder.append("\nPlugin version: " + description.getVersion());
        builder.append("\nAuthor: MEFRREEX §7[Discord: mefrreex]§r");
        builder.append("\nLink: §ahttps://github.com/MEFRREEX/FormCreator");

        sender.sendMessage(builder.toString());
        return true;
    } 
}