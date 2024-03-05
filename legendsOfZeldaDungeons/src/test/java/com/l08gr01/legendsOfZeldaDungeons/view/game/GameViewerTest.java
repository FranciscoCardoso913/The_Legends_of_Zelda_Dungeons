package com.l08gr01.legendsOfZeldaDungeons.view.game;

import com.l08gr01.legendsOfZeldaDungeons.Game;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Camera;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Archer;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Fighter;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Rat;
import com.l08gr01.legendsOfZeldaDungeons.model.game.projectile.Projectile;
import com.l08gr01.legendsOfZeldaDungeons.model.game.projectile.ProjectileUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

public class GameViewerTest {
    GameViewer gameViewer;
    Arena arena;

    public ArrayList<Monster> monsters(){
        ArrayList<Monster> monsters= new ArrayList<>();
        monsters.add(new Fighter(10,20,1));
        monsters.add(new Archer(10,30,1));
        monsters.add(new Rat(15,15,1));
        return monsters;
    }
    public ArrayList<Projectile> projectiles(){
        ArrayList<Projectile> projectiles= new ArrayList<>();
        projectiles.add(new ProjectileUp(new Image("/images/arrow/arrow_1.png"),new Position(10,17),1,10,10,10));
        projectiles.add(new ProjectileUp(new Image("/images/arrow/arrow_1.png"),new Position(10,31),1,10,10,10));
        return projectiles;
    }

    @BeforeEach
    public void setup(){
        arena= Mockito.mock(Arena.class);
        gameViewer= new GameViewer(arena);
        Mockito.when(arena.getLink()).thenReturn(new Link(10,10));
        Mockito.when(arena.getMonsters()).thenReturn(monsters());
        Mockito.when(arena.getProjectiles()).thenReturn(projectiles());
        Mockito.when(arena.getCamera()).thenReturn(new Camera(new Position(0,0),10,10,arena));
        Mockito.when(arena.getSmallerSpritesSize()).thenReturn(32);
        ArrayList<ArrayList<Image>> map= new ArrayList<>();
        ArrayList<Image> aux= new ArrayList<>();
        aux.add(new Image("/images/arrow/arrow_1.png"));
        map.add(aux);
        Mockito.when(arena.getMap()).thenReturn(map);
    }
    @Test
    public void getDrawOrderTest(){
        Assertions.assertEquals(arena.getMonsters().get(2),gameViewer.getDrawOrder(arena).first());
        Assertions.assertNotEquals(arena.getLink(),gameViewer.getDrawOrder(arena).first());
        Assertions.assertEquals(arena.getProjectiles().get(1),gameViewer.getDrawOrder(arena).last());
    }
    @Test
    public void drawTest() throws IOException {

        Game game= Mockito.mock(Game.class);
        GUI gui= Mockito.mock(GUI.class);
        GameViewer spy= Mockito.spy(gameViewer);
        spy.drawElements(game,gui);
        Mockito.verify(spy).drawArena(game,gui);

    }
}
