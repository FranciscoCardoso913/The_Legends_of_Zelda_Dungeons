package com.l08gr01.legendsOfZeldaDungeons.view.game;

import com.googlecode.lanterna.TerminalPosition;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Camera;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Archer;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Fighter;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Rat;
import com.l08gr01.legendsOfZeldaDungeons.model.game.projectile.ProjectileUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class ElementViewerTest {
    ElementsViewer elementsViewer;
    Arena arena;

    @BeforeEach
    public void setup(){
        arena= Mockito.mock(Arena.class);
        elementsViewer= new ElementsViewer(arena);
        Mockito.when(arena.getCamera()).thenReturn(new Camera(new Position(5,5),100,100,arena));
    }

    @Test
    public void drawLink() throws IOException {
        Link link= new Link(10,10);
        GUI spy= Mockito.spy(GUI.class);
        elementsViewer.draw(link,spy);
        Mockito.verify(spy).draw(new TerminalPosition(5,5),link.getSprite());
    }
    @Test
    public void drawMonsters() throws IOException {
        GUI spy= Mockito.spy(GUI.class);
        Fighter fighter= new Fighter(10,10,1);
        elementsViewer.draw(fighter,spy);
        Mockito.verify(spy).draw(new TerminalPosition(5,5),fighter.getSprite());
        Archer archer= new Archer(10,10,1);
        elementsViewer.draw(archer,spy);
        Mockito.verify(spy).draw(new TerminalPosition(5,5),archer.getSprite());
        Rat rat= new Rat(10,10,1);
        elementsViewer.draw(rat,spy);
        Mockito.verify(spy).draw(new TerminalPosition(5,5),rat.getSprite());
    }
    @Test
    public void drawProjectile() throws IOException {
        ProjectileUp projectile= new ProjectileUp(new Image("/images/arrow/arrow_1.png"),new Position(10,10),5,5,5,5);
        GUI spy= Mockito.spy(GUI.class);
        elementsViewer.draw(projectile,spy);
        Mockito.verify(spy).draw(new TerminalPosition(5,5),projectile.getSprite());
    }
}
