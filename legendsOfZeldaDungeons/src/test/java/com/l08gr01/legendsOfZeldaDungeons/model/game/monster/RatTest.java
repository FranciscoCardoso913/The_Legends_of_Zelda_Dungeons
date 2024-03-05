package com.l08gr01.legendsOfZeldaDungeons.model.game.monster;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.Attack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.ShieldAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy.MeleeAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy.NullAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy.AlignStrategy;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy.RandomMovement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RatTest {
        private Rat rat;

        @BeforeEach
        public void setUp(){
            this.rat= new Rat(150,150,1);
        }

        @Test
        public void ratStatus(){
            rat= new Rat(150,150,2);
            Assertions.assertEquals(1,rat.getVelocity());
            Assertions.assertEquals(20,rat.getHealth());
            Assertions.assertEquals(20,rat.getMaxHealth());
            Assertions.assertEquals(10,rat.getPoints());
            rat= new Rat(150,150,3);
            Assertions.assertEquals(30,rat.getHealth());
            Assertions.assertEquals(30,rat.getMaxHealth());
        }
        @Test
        public void ratTopLeftCornerTestTrue(){
            Assertions.assertEquals(rat.getHitboxPosition(), new Position(150,150));
        }
        @Test
        public void ratTopLeftCornerTestFalse(){
            Assertions.assertNotEquals(rat.getHitboxPosition(), new Position(162,165));
        }
        @Test
        public void ratCenterPointTestTrue(){
            Assertions.assertEquals(rat.getCenterPoint(), new Position(156,156));
        }
        @Test
        public void ratCenterPointTestFalse(){
            Assertions.assertNotEquals(rat.getCenterPoint(), new Position(170,172));
        }
        @Test
        public void InitialRatCurrentSpriteTrue(){
            Assertions.assertEquals(rat.getSprite(), new Image("/images/enemies/rat/up/1.png"));
        }
        @Test
        public void InitialRatCurrentSpriteFalse(){
            Assertions.assertNotEquals(rat.getSprite(), new Image("/images/enemies/rat/up/2.png"));
        }
    @Test
    public void ratSpritesTest(){
        rat.getMover().MovingDown();
        Assertions.assertNotEquals(rat.getSprite(), new Image("/images/enemies/rat/up/1.png"));
        Assertions.assertEquals(rat.getSprite(), new Image("/images/enemies/rat/down/1.png"));
        rat.getMover().MovingDown();
        rat.getMover().MovingDown();
        Assertions.assertEquals(rat.getSprite(), new Image("/images/enemies/rat/down/2.png"));
        rat.getMover().MovingRight();
        Assertions.assertEquals(rat.getSprite(), new Image("/images/enemies/rat/right/1.png"));
    }

        @Test
        public void getRatSize(){
            Assertions.assertEquals(12,rat.getWidth());
            Assertions.assertEquals(12,rat.getHeight());
        }

        @Test
        public void ratAttacks(){
            Assertions.assertEquals(Attack.class,rat.getAttack().getClass());
            Assertions.assertNotEquals( ShieldAttack.class,rat.getAttack().getClass());
            Assertions.assertEquals(0,rat.getAttack().getDamage());
        }
        @Test
        public void ratStrategies(){
            Assertions.assertEquals( NullAttack.class,rat.getAttackStrategy().getClass());
            Assertions.assertNotEquals( MeleeAttack.class,rat.getAttackStrategy().getClass());
        }
        @Test
        public void MoveStrategies(){
            Assertions.assertEquals( RandomMovement.class,rat.getMoveStrategy().getClass());
            Assertions.assertNotEquals( AlignStrategy.class,rat.getMoveStrategy().getClass());
        }

        @Test
        public void ratHitbox(){
            Assertions.assertEquals(new Hitbox(12, 12, new Position(150,150),new Position(0,0)),rat.getHitbox());
            rat.setPosition(new Position(10,10));
            Assertions.assertEquals(new Hitbox(12, 12, new Position(10,10),new Position(0,0)),rat.getHitbox());
        }
        @Test
        public void ratWeaponsHitboxes(){
            Assertions.assertEquals(new Hitbox(0, 0, new Position(0, 0), new Position(0, 0)),rat.getAttack().getAttackHitbox());
            rat.setPosition(new Position(10,10));
            Assertions.assertEquals(new Hitbox(0, 0, new Position(0, 0), new Position(0, 0)),rat.getAttack().getAttackHitbox());
            Assertions.assertNotEquals(new Hitbox(11,9,new Position(10,10),new Position(3,-4)),rat.getAttack().getAttackHitbox());
        }
        @Test
        public  void takingDamage(){
            rat.decreaseHealth(5);
            Assertions.assertEquals(5,rat.getHealth());
        }




}
