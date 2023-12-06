package com.mefrreex.chestcreator.chest.action.executor;

import com.mefrreex.chestcreator.chest.action.ActionType;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ExecutorManager {
    
    @Getter
    private static final Map<String, Executor> executors = new HashMap<>();

    public static void init() {
        register(ActionType.PLAYER_COMMAND, new CommandExecutor(true));
        register(ActionType.CONSOLE_COMMAND, new CommandExecutor(false));
        register(ActionType.MESSAGE, new MessageExecutor(true));
        register(ActionType.PLAYER_MESSAGE, new MessageExecutor(false));
        register(ActionType.OPEN, new OpenExecutor());
        register(ActionType.OPEN_FORM, new OpenFormExecutor());
    }

    public static void register(String type, Executor executor) {
        executors.put(type, executor);
    }

    public static Executor getExecutor(String type) {
        return executors.get(type.toUpperCase());
    }
}
