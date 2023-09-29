package com.l08gr01.legendsOfZeldaDungeons.model.game.projectile;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectileUpTest {
    ProjectileLeft projectile;
    @BeforeEach
    public void setup(){
        projectile = new ProjectileLeft(new Image("/images/arrow/arrow_1.png"),new Position(10,10),5,10,10,10);
    }
    @Test
    public void gettersTest(){
        Assertions.assertEquals(new Hitbox(10,10,new Position(10,10),new Position(0,0)),projectile.getHitbox());
        Assertions.assertNotEquals(new Hitbox(15,10,new Position(10,10),new Position(0,0)),projectile.getHitbox());
        Assertions.assertEquals(new Image("/images/arrow/arrow_1.png"),projectile.getSprite());
        Assertions.assertEquals(10,projectile.getDamage());
        Assertions.assertEquals(new Position(10,10),projectile.getHitboxPosition());
        Assertions.assertEquals(new Position(10,10),projectile.getPosition());

    }
    @Test
    public void updatePositionTest(){
        Assertions.assertEquals(new Position(10,10),projectile.getPosition());
        projectile.updatePosition();
        Assertions.assertNotEquals(new Position(10,10),projectile.getPosition());
        Assertions.assertEquals(new Position(5,10),projectile.getPosition());
        Assertions.assertEquals(new Position(0,10),projectile.getNextPosition().getPosition());
        Assertions.assertEquals(new Position(5,10),projectile.getPosition());
    }
    @Test
    public void foundTargetTest(){
        Assertions.assertFalse(projectile.isFoundTarget());
        projectile.destroyed();
        Assertions.assertTrue(projectile.isFoundTarget());
    }
}
