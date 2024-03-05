package com.l08gr01.legendsOfZeldaDungeons.view.game.HUD;

import com.googlecode.lanterna.TerminalPosition;
import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;

import java.io.IOException;
import java.util.ArrayList;


public class ScoreHud {
    private int score;

    public ScoreHud(){
        this.score = 0;
    }
    public void draw(GUI gui) throws IOException {

        ArrayList<Image> numberSprites = Database.getDatabase().getNumberSpritesBlack().getNumbers();
        Image background = Database.getDatabase().getNumberSpritesBlack().getBackground();
        gui.draw(new TerminalPosition(288-56,10), background);

        int aux = score;
        TerminalPosition position = new TerminalPosition(288-20,14);

        if(score == 0){
            gui.draw(position, numberSprites.get(0));
        }

        while(score > 0){
            Integer res = score%10;
            score = score/10;
            gui.draw(position, numberSprites.get(res));
            position = new TerminalPosition(position.getColumn()-6, position.getRow());
        }
        score = aux;
        //System.out.println(System.currentTimeMillis()-s);
    }

    public void update(int score,GUI gui) throws IOException {
        this.score = score;
        this.draw(gui);
    }

    public int getScore() {
        return score;
    }
}
