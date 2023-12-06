package com.mefrreex.chestcreator.command.subcommand;

import com.mefrreex.chestcreator.chest.Chest;
import com.mefrreex.chestcreator.chest.ChestManager;
import com.mefrreex.chestcreator.command.BaseCommand;
import com.mefrreex.chestcreator.command.BaseSubCommand;
import com.mefrreex.chestcreator.utils.Language;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;

public class OpenSubCommand extends BaseSubCommand {

    public OpenSubCommand(BaseCommand command) {
        super("open", "Open form", command);
        this.setPermission("chestcreator.open");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String label, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }

        if (args.length < 2) {
            this.sendUsage(sender, "/%s %s <chest> <player>".formatted(commandLabel, label));
            return false;
        }

        Chest chest = ChestManager.get(args[0]);
        if (chest == null) {
            sender.sendMessage(Language.get("command-chest-not-found", args[0]));
            return false;
        }

        Player player = Server.getInstance().getPlayer(args[1]);
        if (player == null) {
            sender.sendMessage(Language.get("command-player-not-found", args[1]));
            return false; 
        }

        chest.send(player);
        sender.sendMessage(Language.get("command-open-opened", args[0], player.getName()));
        return true;
    } 
}