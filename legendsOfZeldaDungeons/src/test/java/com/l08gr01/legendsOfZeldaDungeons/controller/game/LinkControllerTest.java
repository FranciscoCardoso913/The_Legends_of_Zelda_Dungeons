package com.l08gr01.legendsOfZeldaDungeons.controller.game;

import com.l08gr01.legendsOfZeldaDungeons.Game;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI.ACTION;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Camera;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Fighter;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkControllerTest {
    Arena arena;
    LinkController linkController;
    Link link;
    Camera camera;
    public ArrayList<ArrayList<Integer>> createMap(){
        ArrayList<ArrayList<Integer>>map= new ArrayList<>();
        ArrayList<Integer> aux= new ArrayList<>();
        Integer a[]={0,0,0};
        Collections.addAll(aux, a);
        map.add(aux);
        aux= new ArrayList<>();
        Integer b[]={1,1,1};
        Collections.addAll(aux, b);
        map.add(aux);
        aux= new ArrayList<>();
        Integer c[]={1,1,2};
        Collections.addAll(aux, c);
        map.add(aux);
        return map;
    }
    @BeforeEach
    void setUp(){
        arena= Mockito.mock(Arena.class);
        link= new Link(20,50);
        camera= new Camera(link.getPosition(),20,20,arena);
        linkController= new LinkController(arena);
        Mockito.when(arena.getLink()).thenReturn(link);
        ArrayList<Monster> monsters= new ArrayList<>();
        monsters.add(new Fighter(20,50,1));
        Mockito.when(arena.getMonsters()).thenReturn(monsters);
        Mockito.when(arena.getCollision()).thenReturn(createMap());
        Mockito.when(arena.getSmallerSpritesSize()).thenReturn(32);
        Mockito.when(arena.getCamera()).thenReturn(camera);

    }

    @Test
    void InitizialPosition(){
       assertEquals(arena.getLink().getPosition(),new Position(20,50));

    }
    @Test
    void moveUpFree() throws IOException {
        linkController.moveLinkUp();
        assertEquals(arena.getLink().getPosition(),new Position(20,50-arena.getLink().getVelocity()));

    }
    @Test
    public void movingUpToWall() throws IOException {
        link.setPosition(new Position(10,10));
        linkController.moveLinkUp();

        assertEquals(new Position(10,10),arena.getLink().getPosition());
    }

    @Test
    void moveDownFree() throws IOException {
        linkController.moveLinkDown();
        assertEquals(arena.getLink().getPosition(),new Position(20,50+arena.getLink().getVelocity()));
    }
    @Test
    void moveLeftFree() throws IOException {
        linkController.moveLinkLeft();
        assertEquals(arena.getLink().getPosition(),new Position(20-arena.getLink().getVelocity(),50));
    }
    @Test
    void moveRightFree() throws IOException {
        linkController.moveLinkRight();
        assertEquals(arena.getLink().getPosition(),new Position(20+arena.getLink().getVelocity(),50));
    }
    @Test
    public void movingDownToWall() throws IOException {
        arena.getLink().setPosition(new Position(10,10));
        linkController.moveLinkUp();
        assertEquals(new Position(10,10),arena.getLink().getPosition());
    }
    @Test
    public void movingLeftToWall() throws IOException {
        arena.getLink().setPosition(new Position(10,10));
        linkController.moveLinkUp();
        assertEquals(new Position(10,10),arena.getLink().getPosition());
    }
    @Test
    public void movingRightToWall() throws IOException {
        arena.getLink().setPosition(new Position(10,10));
        linkController.moveLinkUp();
        assertEquals(new Position(10,10),arena.getLink().getPosition());
    }
    @Test
    public void checkInvisibilityTest() throws InterruptedException {
        Assertions.assertFalse(arena.getLink().isInvincible());
        arena.getLink().decreaseHealth(10);
        Assertions.assertTrue(arena.getLink().isInvincible());
        linkController.checkInvincibilityTime();
        while(System.currentTimeMillis()-arena.getLink().getGotInvincible()<=1000)Assertions.assertTrue(arena.getLink().isInvincible());
        linkController.checkInvincibilityTime();
        Assertions.assertFalse(arena.getLink().isInvincible());
    }
    @Test
    public void checkCollisionTest() throws IOException {
        Assertions.assertEquals(0,arena.getCollision().get(0).get(0));
        Assertions.assertTrue(linkController.checkCollision(new Position(0,0)));
        Assertions.assertFalse(linkController.checkCollision(new Position(40,45)));
        Assertions.assertTrue(linkController.checkCollision(new Position(80,80)));
    }
    @Test
    public void CheckCollisionsWithMonsters(){
        link.setHealth(160);
        Assertions.assertEquals(160,link.getHealth());
        linkController.checkMonsterCollision();
        Assertions.assertEquals(159,link.getHealth());
    }

   @Test
    public void checkInputs() throws IOException {
        link.setPosition(new Position(10,10));
        LinkController spy=Mockito.spy(linkController);
        Game game= Mockito.mock(Game.class);
        spy.step(game,GUI.ACTION.UP,0);
        Mockito.verify(spy).moveLinkUp();
       spy.step(game, ACTION.DOWN,0);
       Mockito.verify(spy).moveLinkDown();


    }
    @Test
    public void checkUseSword() throws IOException {
        link.setPosition(new Position(10,10));
        LinkController spy=Mockito.spy(linkController);
        Game game= Mockito.mock(Game.class);
        spy.step(game, ACTION.ATTACK,0);
        Mockito.verify(spy).useSword();
    }
    @Test
    public void checkUseShield() throws IOException {
        link.setPosition(new Position(10,10));
        LinkController spy=Mockito.spy(linkController);
        Game game= Mockito.mock(Game.class);
        spy.step(game, ACTION.DEFEND,0);
        Mockito.verify(spy).useShield();
    }
    @Test
    public void checkUseBow() throws IOException {
        link.setPosition(new Position(10,10));
        LinkController spy=Mockito.spy(linkController);
        Game game= Mockito.mock(Game.class);
        spy.step(game, ACTION.ARROW,0);
        Mockito.verify(spy).useBow();
    }
    @Test
    public void twoAttacks() throws IOException {
        link.setPosition(new Position(10,10));
        LinkController spy=Mockito.spy(linkController);
        Game game= Mockito.mock(Game.class);
        spy.step(game, ACTION.ARROW,0);
        Mockito.verify(spy).useBow();
        spy.step(game, ACTION.ARROW,0);
        Mockito.verify(spy).attack();
    }
}
