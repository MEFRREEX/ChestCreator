package com.mefrreex.chestcreator.chest.action.executor.impl;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.lang.CommandOutputContainer;
import cn.nukkit.lang.TextContainer;
import com.mefrreex.chestcreator.chest.action.executor.Executor;
import org.jetbrains.annotations.NotNull;

public class CommandExecutor implements Executor {

    private final CommandExecuteType executeType;

    private static final ConsoleCommandSender CONSOLE_SENDER = Server.getInstance().getConsoleSender();
    private static final OperatorSender OPERATOR_SENDER = new OperatorSender();

    public CommandExecutor(CommandExecuteType executeType) {
        this.executeType = executeType;
    }

    @Override
    @SuppressWarnings("all")
    public void execute(Player player, String command) {
        Server.getInstance().dispatchCommand(switch(executeType) {
            case PLAYER -> player;
            case CONSOLE -> CONSOLE_SENDER;
            case OPERATOR -> OPERATOR_SENDER;
        }, command);
    }

    public static class OperatorSender extends ConsoleCommandSender {

        @Override
        public void sendMessage(String message) {
            
        }

        @Override
        public void sendMessage(TextContainer message) {
            
        }

        @Override
        public void sendCommandOutput(CommandOutputContainer arg0) {
            
        }

        @Override
        public @NotNull String getName() {
            return "Operator";
        }
    }

    public static enum CommandExecuteType {
        PLAYER,
        CONSOLE,
        OPERATOR
    }
}