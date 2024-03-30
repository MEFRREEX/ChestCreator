package com.mefrreex.chestcreator.command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import com.mefrreex.chestcreator.command.subcommand.HelpSubCommand;
import com.mefrreex.chestcreator.command.subcommand.InfoSubCommand;
import com.mefrreex.chestcreator.command.subcommand.OpenSubCommand;
import com.mefrreex.chestcreator.command.subcommand.ReloadSubCommand;

public class ChestCreatorCommand extends BaseCommand {

    public ChestCreatorCommand() {
        super("chestcreator", "ChestCreator");
        this.setPermission("chestcreator");
        this.registerSubCommand(new HelpSubCommand(this));
        this.registerSubCommand(new InfoSubCommand(this));
        this.registerSubCommand(new OpenSubCommand(this));
        this.registerSubCommand(new ReloadSubCommand(this));
    }

    @Override
    public boolean executeDefault(CommandSender sender, String label) {
        if (!this.testPermission(sender)) {
            return false;
        }
        if (sender instanceof Player) {
            this.sendUsage(sender, "/%s help".formatted(label));
        }
        return true;
    }
    
    public static void register() {
        ChestCreatorCommand command = new ChestCreatorCommand();
        Server.getInstance().getCommandMap().register("ChestCreator", command);
    }
}
