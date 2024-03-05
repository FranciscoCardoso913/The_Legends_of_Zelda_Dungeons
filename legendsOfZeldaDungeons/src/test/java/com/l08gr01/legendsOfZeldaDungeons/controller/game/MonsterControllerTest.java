package com.l08gr01.legendsOfZeldaDungeons.controller.game;

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

public class MonsterControllerTest {
    Arena arena;
    MonsterController fighterController;
    ArrayList<Monster> monsters;
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
    public Monster getFighter(){
        return arena.getMonsters().get(0);
    }
    @BeforeEach
    void setUp(){
        arena= Mockito.mock(Arena.class);
        Fighter fighter= new Fighter(20,50,1);
        link= new Link(10,50);
        fighterController= new MonsterController(arena);
        Mockito.when(arena.getLink()).thenReturn(link);
         monsters= new ArrayList<>();
        monsters.add(fighter);
        Mockito.when(arena.getMonsters()).thenReturn(monsters);
        Mockito.when(arena.getCollision()).thenReturn(createMap());
        Mockito.when(arena.getSmallerSpritesSize()).thenReturn(32);
        Mockito.when(arena.getCamera()).thenReturn(camera);

    }
    @Test
    void InitialPosition(){
        assertEquals(getFighter().getPosition(),new Position(20,50));

    }

    @Test
    public void checkCollisionTest() throws IOException {
        Assertions.assertEquals(0,arena.getCollision().get(0).get(0));
        Assertions.assertTrue(fighterController.checkCollision(new Position(0,0),getFighter()));
        Assertions.assertFalse(fighterController.checkCollision(new Position(20,15),getFighter()));
        Assertions.assertTrue(fighterController.checkCollision(new Position(60,60),getFighter()));
    }
    @Test
    public void TurnToLinkTest(){
        Assertions.assertEquals(getFighter().getMover().getCharacterMovingUp(),getFighter().getMover().getCurrentMovingState());
        fighterController.turnToLink(getFighter(), arena.getLink());
        Assertions.assertNotEquals(getFighter().getMover().getCharacterMovingUp(),getFighter().getMover().getCurrentMovingState());
        Assertions.assertEquals(getFighter().getMover().getCharacterMovingLeft(),getFighter().getMover().getCurrentMovingState());
        link.setPosition(new Position(30,50));
        fighterController.turnToLink(getFighter(), arena.getLink());
        Assertions.assertEquals(getFighter().getMover().getCharacterMovingRight(),getFighter().getMover().getCurrentMovingState());
        link.setPosition(new Position(20,40));
        fighterController.turnToLink(getFighter(), arena.getLink());
        Assertions.assertEquals(getFighter().getMover().getCharacterMovingUp(),getFighter().getMover().getCurrentMovingState());
        link.setPosition(new Position(20,60));
        fighterController.turnToLink(getFighter(), arena.getLink());
        Assertions.assertEquals(getFighter().getMover().getCharacterMovingDown(),getFighter().getMover().getCurrentMovingState());

    }
}
