package com.l08gr01.legendsOfZeldaDungeons.model.game;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.BowAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.ShieldAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.SwordAttack;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkTest {
    private Link link;

    @BeforeEach
    public void setUp(){
        link= new Link( 150,150);
    }

    @Test
    public void linkTopLeftCornerTestTrue(){
        Assertions.assertEquals(link.getHitboxPosition(), new Position(163,165));
    }
    @Test
    public void linkTopLeftCornerTestFalse(){
        Assertions.assertNotEquals(link.getHitboxPosition(), new Position(162,165));
    }
    @Test
    public void linkCenterPointTestTrue(){
        Assertions.assertEquals(link.getCenterPoint(), new Position(171,172));
    }
    @Test
    public void linkCenterPointTestFalse(){
        Assertions.assertNotEquals(link.getCenterPoint(), new Position(170,172));
    }
    @Test
    public void InitialLinkCurrentSpriteTrue(){
        Assertions.assertEquals(link.getSprite(), new Image("/images/link_sprites/movement/up/1.png"));
    }
    @Test
    public void InitialLinkCurrentSpriteFalse(){
        Assertions.assertNotEquals(link.getSprite(), new Image("/images/link_sprites/movement/up/2.png"));
    }

    @Test
    public void linkSpritesTest(){
        link.getMover().MovingDown();
        Assertions.assertNotEquals(link.getSprite(), new Image("/images/link_sprites/movement/up/1.png"));
        Assertions.assertEquals(link.getSprite(), new Image("/images/link_sprites/movement/down/1.png"));
        link.getMover().MovingDown();
        Assertions.assertEquals(link.getSprite(), new Image("/images/link_sprites/movement/down/2.png"));
        link.getMover().MovingRight();
        Assertions.assertEquals(link.getSprite(), new Image("/images/link_sprites/movement/right/1.png"));
        link.setCharacterAction(link.getAttack());
        Assertions.assertEquals(link.getSprite(), new Image("/images/link_sprites/attack/right/1.png"));

    }

    @Test
    public void getLinkSize(){
        Assertions.assertEquals(link.getWidth(),16);
        Assertions.assertEquals(link.getHeight(),15);
    }
    @Test
    public void linkAttacks(){
        Assertions.assertEquals(link.getAttack().getClass(),SwordAttack.class);
        Assertions.assertEquals(link.getAttack().getDamage(),20);
        link.shield();
        Assertions.assertNotEquals(link.getAttack().getClass(),SwordAttack.class);
        Assertions.assertEquals(link.getAttack().getClass(), ShieldAttack.class);
        Assertions.assertEquals(link.getAttack().getDamage(),0);
        link.bow();
        Assertions.assertEquals(link.getAttack().getClass(), BowAttack.class);
        Assertions.assertEquals(link.getAttack().getDamage(),10);
        link.attack();
        Assertions.assertEquals(link.getAttack().getClass(),SwordAttack.class);
    }
    @Test
    public void linkReceivingDamage(){
        Assertions.assertEquals(link.getHealth(),160);
        Assertions.assertEquals(link.getMaxHealth(),160);
        Assertions.assertFalse(link.isInvincible());
        Assertions.assertEquals(link.getGotInvincible(),0);
        link.decreaseHealth(20);
        Assertions.assertEquals(link.getHealth(),140);
        Assertions.assertEquals(link.getMaxHealth(),160);
        Assertions.assertTrue(link.isInvincible());
        Assertions.assertNotEquals(link.getGotInvincible(),0);
        link.decreaseHealth(20);
        Assertions.assertEquals(link.getHealth(),140);
        Assertions.assertEquals(link.getMaxHealth(),160);
        Assertions.assertTrue(link.isInvincible());
    }

    @Test
    public void linkHitbox(){
        Assertions.assertEquals(link.getHitbox(),new Hitbox(15, 16, new Position(150,150),new Position(13,15)));
        link.setPosition(new Position(10,10));
        Assertions.assertEquals(link.getHitbox(),new Hitbox(15, 16, new Position(10,10),new Position(13,15)));
    }
    @Test
    public void linkIncreaseHealth(){
        Assertions.assertEquals(link.getHealth(),160);
        link.increaseHealth(20);
        Assertions.assertEquals(link.getHealth(),180);
        link.setInvincible(true);
        link.increaseHealth(20);
        Assertions.assertEquals(link.getHealth(),180);
    }

    @Test
    public void linkWeaponsHitboxes(){
        Assertions.assertEquals(link.getAttack().getAttackHitbox(), new Hitbox(10,42,new Position(150,150),new Position(0,0)));
        link.setPosition(new Position(10,10));
        Assertions.assertEquals(link.getAttack().getAttackHitbox(), new Hitbox(10,42,new Position(10,10),new Position(0,0)));
        link.bow();
        Assertions.assertEquals(link.getAttack().getAttackHitbox(),new Hitbox(15,5,new Position(10,10),new Position(15,-1)));
        link.shield();
        Assertions.assertEquals(link.getAttack().getAttackHitbox(),new Hitbox(1,14,new Position(10,10),new Position(14,15)));
    }

    @Test
    public void linkVelocity(){
        Assertions.assertEquals(link.getVelocity(),5);
    }




}
