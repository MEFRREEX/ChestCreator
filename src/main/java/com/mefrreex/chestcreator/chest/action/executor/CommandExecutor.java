package com.mefrreex.chestcreator.chest.action.executor;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandMap;

public class CommandExecutor implements Executor {

    private final boolean isPlayer;
    private final Server server;

    public CommandExecutor(boolean isPlayer) {
        this.isPlayer = isPlayer;
        this.server = Server.getInstance();
    }

    @Override
    public void execute(Player player, String command) {
        if (server.isPrimaryThread()) {
            executeCommand(player, command);
        } else {
            server.getScheduler().scheduleTask(null, () -> executeCommand(player, command));
        }
    }

    private void executeCommand(Player player, String command) {
        CommandMap commandMap = server.getCommandMap();
        if (isPlayer) {
            commandMap.executeCommand(player, command);
        } else {
            commandMap.executeCommand(server.getConsoleSender(), command);
        }
    }
}