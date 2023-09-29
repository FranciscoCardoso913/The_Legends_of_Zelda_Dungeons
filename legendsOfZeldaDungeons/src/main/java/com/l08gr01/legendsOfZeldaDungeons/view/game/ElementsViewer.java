package com.l08gr01.legendsOfZeldaDungeons.view.game;

import com.googlecode.lanterna.TerminalPosition;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Element;

import java.io.IOException;

public class ElementsViewer implements ElementViewer{
    Arena arena;
    public ElementsViewer(Arena arena){
        this.arena=arena;
    }
    @Override
    public void draw(Element element, GUI gui) throws IOException {
        int x= element.getPosition().getX()-arena.getCamera().getCameraPositon().getX();
        int y= element.getPosition().getY()- arena.getCamera().getCameraPositon().getY();
        gui.draw(new TerminalPosition(x,y),element.getSprite());
    }
}
