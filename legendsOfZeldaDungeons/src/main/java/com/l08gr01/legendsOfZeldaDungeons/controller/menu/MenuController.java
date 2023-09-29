package com.l08gr01.legendsOfZeldaDungeons.controller.menu;

import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.Game;
import com.l08gr01.legendsOfZeldaDungeons.Sound;
import com.l08gr01.legendsOfZeldaDungeons.controller.Controller;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.model.Menu;

import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.states.GameState;

import java.io.IOException;

public class MenuController extends Controller<Menu> {
    Sound sound;

    public MenuController(Menu menu) {
        super(menu);
        sound = new Sound("/sounds/menu.wav");
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (!sound.isRunning()) {
            sound.start();
        }
        switch (action) {
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case SELECT:
                sound.stop();
                if (getModel().getCurrEntry() == 0) {
                    game.setState(new GameState(new Arena(game)));
                } else if (getModel().getCurrEntry() == (getModel().getEntries().size() - 1)) {
                    Database.getDatabase().storeHighscore();
                    game.setState(null);
                }
        }
    }
}
