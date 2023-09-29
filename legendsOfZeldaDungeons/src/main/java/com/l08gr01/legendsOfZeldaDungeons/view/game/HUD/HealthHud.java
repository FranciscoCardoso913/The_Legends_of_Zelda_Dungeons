package com.l08gr01.legendsOfZeldaDungeons.view.game.HUD;

import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Camera;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Character;

import java.io.IOException;

public interface HealthHud {
    public void update(Character character, GUI gui, Camera camera) throws IOException;
    public void draw(GUI gui) throws IOException;
}
