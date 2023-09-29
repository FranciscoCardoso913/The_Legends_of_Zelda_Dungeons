package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Character;

public interface Action {

    public Image getSprite();
    public void setCharacterAction( Action action);

    public Character getCharacter();

}
