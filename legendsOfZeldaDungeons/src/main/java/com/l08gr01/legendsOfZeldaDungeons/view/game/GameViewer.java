package com.l08gr01.legendsOfZeldaDungeons.view.game;

import com.l08gr01.legendsOfZeldaDungeons.Game;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;

import com.l08gr01.legendsOfZeldaDungeons.model.game.Element;

import com.l08gr01.legendsOfZeldaDungeons.view.Viewer;

import java.io.IOException;
import java.util.TreeSet;

public class GameViewer extends Viewer<Arena> {
    ElementsViewer elementsViewer;
    HudViewer hudViewer;

    public GameViewer(Arena arena) {
        super(arena);
        elementsViewer= new ElementsViewer(arena);
        hudViewer =new HudViewer();
    }

    @Override
    public void drawElements(Game game, GUI gui) throws IOException {

        drawArena(game,gui);
        for(Element element: getDrawOrder(getModel()))drawElement(gui,element,elementsViewer);
        hudViewer.upDateAll(getModel(),gui);
    }

    public  <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) throws IOException {
        viewer.draw(element, gui);
    }
    public void drawArena(Game game,GUI gui) throws IOException {
        ArenaViewer arenaViewer= new ArenaViewer();
        arenaViewer.draw(game,gui,getModel());
    }
    public TreeSet<Element> getDrawOrder(Arena arena){
        TreeSet<Element> v= new TreeSet<Element>();
        v.add(arena.getLink());
        v.addAll(arena.getMonsters());
        v.addAll(arena.getProjectiles());
        return v;
    }

}

