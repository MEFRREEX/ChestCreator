package com.mefrreex.chestcreator.command.subcommand;

import com.mefrreex.chestcreator.command.BaseCommand;
import com.mefrreex.chestcreator.command.BaseSubCommand;
import com.mefrreex.chestcreator.utils.Language;

import cn.nukkit.command.CommandSender;

public class HelpSubCommand extends BaseSubCommand {

    public HelpSubCommand(BaseCommand command) {
        super("help", "Help", command);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String label, String[] args) {
        StringBuilder builder = new StringBuilder();
        builder.append(Language.get("command-help-available"));
        for (BaseSubCommand sub : this.getCommand().getSubcommands().values()) {
            if (sub.testPermission(sender)) {
                String name = sub.getName();
                String description = sub.getDescription();
                builder.append("\n" + Language.get("command-help-format", commandLabel, name, description));
            }
        }
        sender.sendMessage(builder.toString());
        return true;
    }
}