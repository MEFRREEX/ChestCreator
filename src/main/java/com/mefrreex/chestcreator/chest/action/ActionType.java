package com.mefrreex.chestcreator.chest.action;

public interface ActionType {

    String PLAYER_COMMAND = "PLAYER_COMMAND";

    String CONSOLE_COMMAND = "CONSOLE_COMMAND";

    String MESSAGE = "MESSAGE";

    String PLAYER_MESSAGE = "PLAYER_MESSAGE";

    @Deprecated
    String OPEN = "OPEN";

    String OPEN_CHEST = "OPEN_CHEST";

    String OPEN_FORM = "OPEN_FORM";
}
