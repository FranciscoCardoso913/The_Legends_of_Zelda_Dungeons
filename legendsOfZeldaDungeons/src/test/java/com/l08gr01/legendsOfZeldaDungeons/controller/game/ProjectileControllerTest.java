package com.l08gr01.legendsOfZeldaDungeons.controller.game;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Camera;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Fighter;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;
import com.l08gr01.legendsOfZeldaDungeons.model.game.projectile.Projectile;
import com.l08gr01.legendsOfZeldaDungeons.model.game.projectile.ProjectileUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectileControllerTest {
    Arena arena;
    ProjectileController projectileController;
    ArrayList<Monster> monsters;

    ArrayList<Projectile>projectiles;
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
    public Projectile getProjectile(){
        return arena.getProjectiles().get(0);
    }
    @BeforeEach
    void setUp(){
        arena= Mockito.mock(Arena.class);
        Fighter fighter= new Fighter(70,70,1);
        link= new Link(70,70);
        projectileController= new ProjectileController(arena);
        Mockito.when(arena.getLink()).thenReturn(link);
        monsters= new ArrayList<>();
        monsters.add(fighter);
        projectiles=new ArrayList<>();
        projectiles.add(new ProjectileUp(new Image("/images/arrow/arrow_1.png"),new Position(10,10),5,10,5,5));
        Mockito.when(arena.getMonsters()).thenReturn(monsters);
        Mockito.when(arena.getCollision()).thenReturn(createMap());
        Mockito.when(arena.getSmallerSpritesSize()).thenReturn(32);
        Mockito.when(arena.getCamera()).thenReturn(camera);
        Mockito.when(arena.getProjectiles()).thenReturn(projectiles);

    }
    @Test
    void InitizialPosition(){
        assertEquals(getProjectile().getPosition(),new Position(10,10));
    }

    @Test
    public void checkCollisionTest() throws IOException {
        Assertions.assertEquals(0,arena.getCollision().get(0).get(0));
        Assertions.assertTrue(projectileController.checkCollision(new Position(0,0)));
        Assertions.assertFalse(projectileController.checkCollision(new Position(40,45)));
        Assertions.assertTrue(projectileController.checkCollision(new Position(60,60)));
    }
    @Test
    public void hitMonster(){
        Assertions.assertTrue(projectileController.checkHits(getProjectile()));
        getProjectile().getPosition().setPosition(new Position(35,35));
        Assertions.assertFalse(projectileController.checkHits(getProjectile()));
        getFighter().getPosition().setPosition(new Position(27,19));
        Assertions.assertTrue(projectileController.checkHits(getProjectile()));
        getFighter().getPosition().setPosition(new Position(77,77));
        Assertions.assertFalse(projectileController.checkHits(getProjectile()));
        link.setPosition(new Position(25,25));
        Assertions.assertTrue(projectileController.checkHits(getProjectile()));
        Assertions.assertEquals(getFighter().getMaxHealth()-10,getFighter().getHealth());
        Assertions.assertEquals(link.getMaxHealth()-10,link.getHealth());
    }
}
