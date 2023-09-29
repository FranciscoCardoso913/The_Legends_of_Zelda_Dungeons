package com.l08gr01.legendsOfZeldaDungeons.view.game;

import com.googlecode.lanterna.TerminalPosition;
import com.l08gr01.legendsOfZeldaDungeons.Game;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Map1;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Camera;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class ArenaViewerTest {

    ArenaViewer arenaViewer;
    Game game;
    Arena arena;


    @BeforeEach
    void setup() throws IOException {
        game = Mockito.mock(Game.class);
        arenaViewer = new ArenaViewer();
        arena = Mockito.mock(Arena.class);
        Mockito.when(arena.getCamera()).thenReturn(new Camera(new Position(0,0),100,100,arena));
        Mockito.when(arena.getMap()).thenReturn(new Map1(new Link(0,0),1).getMap());
        Mockito.when(arena.getSmallerSpritesSize()).thenReturn(32);
    }

    @Test
    void draw() throws IOException {
        GUI gui = Mockito.spy(GUI.class);
        Image image = arena.getMap().get(0).get(0);
        arenaViewer.draw(game, gui, arena);

        Mockito.verify(gui).draw(new TerminalPosition(0, 0), image);

    }

    @Test
    void initialPositionTest(){
        Mockito.when(arena.getCamera()).thenReturn(new Camera(new Position(33,63),10,10,arena));
        Mockito.when(arena.getSmallerSpritesSize()).thenReturn(32);
        Assertions.assertEquals(arenaViewer.initialPosition(arena), new Position(32,32));
    }
}
