package com.mefrreex.chestcreator.chest.action.executor;

import com.mefrreex.chestcreator.chest.action.ActionType;
import com.mefrreex.chestcreator.chest.action.executor.impl.CommandExecutor;
import com.mefrreex.chestcreator.chest.action.executor.impl.MessageExecutor;
import com.mefrreex.chestcreator.chest.action.executor.impl.OpenChestExecutor;
import com.mefrreex.chestcreator.chest.action.executor.impl.OpenFormExecutor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ExecutorManager {
    
    @Getter
    private static final Map<String, Executor> executors = new HashMap<>();

    @SuppressWarnings("deprecation")
    public static void init() {
        register(ActionType.PLAYER_COMMAND, new CommandExecutor(true));
        register(ActionType.CONSOLE_COMMAND, new CommandExecutor(false));
        register(ActionType.MESSAGE, new MessageExecutor(true));
        register(ActionType.PLAYER_MESSAGE, new MessageExecutor(false));
        register(ActionType.OPEN, new OpenChestExecutor(true)); // register executor for backward compatibility
        register(ActionType.OPEN_CHEST, new OpenChestExecutor(false));
        register(ActionType.OPEN_FORM, new OpenFormExecutor());
    }

    public static void register(String type, Executor executor) {
        executors.put(type, executor);
    }

    public static Executor getExecutor(String type) {
        return executors.get(type.toUpperCase());
    }
}
