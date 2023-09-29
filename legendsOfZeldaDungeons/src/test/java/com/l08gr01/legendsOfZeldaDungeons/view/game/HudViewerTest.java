package com.l08gr01.legendsOfZeldaDungeons.view.game;

import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Camera;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Archer;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Fighter;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Rat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

public class HudViewerTest {
    HudViewer hudViewer;
    Arena arena;
    GUI gui;
    @BeforeEach
    public void setup(){
        hudViewer=new HudViewer();
        arena= Mockito.mock(Arena.class);
        Mockito.when(arena.getCamera()).thenReturn(new Camera(new Position(0,0),10,10,arena));
        gui= Mockito.mock(GUI.class);
    }
    public ArrayList<Monster> monsters(){
        ArrayList<Monster> monsters= new ArrayList<>();
        monsters.add(new Fighter(10,10,1));
        monsters.add(new Archer(10,10,1));
        monsters.add(new Rat(10,10,1));
        return monsters;
    }
    @Test
    public void updateTest() throws IOException {
        Mockito.when(arena.getLink()).thenReturn(new Link(0,0));
        Mockito.when(arena.getMonsters()).thenReturn(monsters());
        Mockito.when(arena.getScore()).thenReturn(10);
        hudViewer.upDateAll(arena,gui);
        Mockito.verify(arena, Mockito.times(1)).getLink();
        Mockito.verify(arena,Mockito.times(arena.getMonsters().size()+1)).getCamera();
        Mockito.verify(arena,Mockito.times(1)).getScore();
    }
}
