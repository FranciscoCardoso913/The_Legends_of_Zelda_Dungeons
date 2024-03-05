package com.l08gr01.legendsOfZeldaDungeons.view;

import com.googlecode.lanterna.TerminalPosition;
import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.Game;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Menu;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class MenuViewer extends Viewer<Menu> {
    public MenuViewer(Menu menu) {
        super(menu);
    }

    @Override
    protected void drawElements(Game game,GUI gui) throws IOException {
        gui.clear();
        int currEntry = getModel().getCurrEntry();
        List<String> entries = getModel().getEntries();

        gui.draw(TerminalPosition.TOP_LEFT_CORNER,new Image(entries.get(currEntry)));

        ArrayList<Image> numberSprites = Database.getDatabase().getNumberSpritesWhite().getNumbers();
        int highscore = Database.getDatabase().getHighscore();
        TerminalPosition pos = new TerminalPosition(184, 153);
        for (int i = 0; i < 6; i++) {
            Integer res = highscore%10;
            highscore = highscore/10;
            gui.draw(pos, numberSprites.get(res));
            pos = new TerminalPosition(pos.getColumn()-6, pos.getRow());
        }
    }
}