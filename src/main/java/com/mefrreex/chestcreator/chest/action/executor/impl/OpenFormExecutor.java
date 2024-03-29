package com.mefrreex.chestcreator.chest.action.executor.impl;

import cn.nukkit.Player;
import com.mefrreex.chestcreator.chest.action.executor.Executor;
import com.mefrreex.formcreator.form.FormManager;

public class OpenFormExecutor implements Executor {

    @Override
    public void execute(Player player, String name) {
        try {
            if (!FormManager.exists(name)) {
                throw new RuntimeException("Form with name " + name + " not found");
            }
            FormManager.get(name).send(player);
        } catch (NoClassDefFoundError e) {
            throw new RuntimeException("FormCreator plugin not found", e);
        }
    }
}