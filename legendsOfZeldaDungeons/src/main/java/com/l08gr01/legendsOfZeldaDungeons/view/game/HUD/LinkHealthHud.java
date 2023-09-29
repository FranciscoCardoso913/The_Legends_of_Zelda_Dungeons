package com.l08gr01.legendsOfZeldaDungeons.view.game.HUD;

import com.googlecode.lanterna.TerminalPosition;
import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Camera;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Character;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LinkHealthHud implements HealthHud {
    private List<Integer> hearts;
    int nHearts;
    public LinkHealthHud(int nHearts){
        this.nHearts=nHearts;
    }

    @Override
    public void update(Character character, GUI gui, Camera camera) throws IOException {
        float health=character.getHealth();
        float maxHealth= character.getMaxHealth();
        hearts = new ArrayList<>();
        int completeHearts = (int)(health*nHearts/maxHealth);
        int incompleteHearts =(int)((health*nHearts/maxHealth-completeHearts)*3);
        for (int i = 0; i < completeHearts; i++) {
            hearts.add(3);
        }
        if (incompleteHearts > 0) hearts.add(incompleteHearts);
        draw(gui);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        ArrayList<Image> heartSprites = Database.getDatabase().getHeartSprites().getHearts();
        int x = 20;
        for (Integer heart : hearts) {
            gui.draw(new TerminalPosition(x, 10), heartSprites.get(heart - 1));
            x += 16;

        }
    }

    public List<Integer> getHearts() {
        return hearts;
    }
}
