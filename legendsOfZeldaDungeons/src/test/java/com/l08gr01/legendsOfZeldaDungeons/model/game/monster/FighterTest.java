package com.l08gr01.legendsOfZeldaDungeons.model.game.monster;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.BowAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.SwordAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy.AlignedAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy.MeleeAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy.ChaseMovement;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy.RandomMovement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FighterTest {
    private Fighter fighter;
    @BeforeEach
    public void setUp(){
        this.fighter= new Fighter(150,150,1);
    }
    @Test
    public void fighterStatus(){
        Assertions.assertEquals(fighter.getVelocity(),2);
        fighter= new Fighter(150,150,2);
        Assertions.assertEquals(fighter.getHealth(),80);
        Assertions.assertEquals(fighter.getMaxHealth(),80);
        Assertions.assertEquals(fighter.getPoints(),20);
        fighter= new Fighter(150,150,3);
        Assertions.assertEquals(fighter.getHealth(),120);
        Assertions.assertEquals(fighter.getMaxHealth(),120);
    }
    @Test
    public void fighterTopLeftCornerTestTrue(){
        Assertions.assertEquals(fighter.getHitboxPosition(), new Position(163,158));
    }
    @Test
    public void fighterTopLeftCornerTestFalse(){
        Assertions.assertNotEquals(fighter.getHitboxPosition(), new Position(162,165));
    }
    @Test
    public void fighterCenterPointTestTrue(){
        Assertions.assertEquals(fighter.getCenterPoint(), new Position(172,170));
    }
    @Test
    public void fighterCenterPointTestFalse(){
        Assertions.assertNotEquals(fighter.getCenterPoint(), new Position(170,172));
    }
    @Test
    public void InitialFighterCurrentSpriteTrue(){
        Assertions.assertEquals(fighter.getSprite(), new Image("/images/enemies/swordsman/move/up/1.png"));
    }
    @Test
    public void InitialFighterCurrentSpriteFalse(){
        Assertions.assertNotEquals(fighter.getSprite(), new Image("/images/enemies/swordsman/move/up/2.png"));
    }

    @Test
    public void fighterSpritesTest(){
        fighter.getMover().MovingDown();
        Assertions.assertNotEquals(fighter.getSprite(), new Image("/images/enemies/swordsman/move/up/1.png"));
        Assertions.assertEquals(fighter.getSprite(), new Image("/images/enemies/swordsman/move/down/1.png"));
        fighter.getMover().MovingDown();
        fighter.getMover().MovingDown();
        Assertions.assertEquals(fighter.getSprite(), new Image("/images/enemies/swordsman/move/down/2.png"));
        fighter.getMover().MovingRight();
        Assertions.assertEquals(fighter.getSprite(), new Image("/images/enemies/swordsman/move/right/1.png"));
        fighter.setCharacterAction(fighter.getAttack());
        Assertions.assertEquals(fighter.getSprite(), new Image("/images/enemies/swordsman/attack/right/1.png"));

    }
    @Test
    public void getFighterSize(){
        Assertions.assertEquals(fighter.getWidth(),19);
        Assertions.assertEquals(fighter.getHeight(),25);
    }
    @Test
    public void fighterAttacks(){
        Assertions.assertEquals(fighter.getAttack().getClass(), SwordAttack.class);
        Assertions.assertNotEquals(fighter.getAttack().getClass(), BowAttack.class);
        Assertions.assertEquals(fighter.getAttack().getDamage(),20);
    }
    @Test
    public void fighterStrategies(){
        Assertions.assertEquals(fighter.getAttackStrategy().getClass(), MeleeAttack.class);
        Assertions.assertNotEquals(fighter.getAttackStrategy().getClass(), AlignedAttack.class);
    }
    @Test
    public void MoveStrategies(){
        Assertions.assertEquals(fighter.getMoveStrategy().getClass(), ChaseMovement.class);
        Assertions.assertNotEquals(fighter.getMoveStrategy().getClass(), RandomMovement.class);
    }
    @Test
    public void fighterHitbox(){
        Assertions.assertEquals(fighter.getHitbox(),new Hitbox(25, 19, new Position(150,150), new Position(13, 8)));
        fighter.setPosition(new Position(10,10));
        Assertions.assertEquals(fighter.getHitbox(),new Hitbox(25, 19, new Position(10,10), new Position(13, 8)));
    }
    @Test
    public void fighterWeaponsHitboxes(){
        Assertions.assertEquals(fighter.getAttack().getAttackHitbox(), new Hitbox(11,9,new Position(150,150),new Position(23,-4)));
        fighter.setPosition(new Position(10,10));
        Assertions.assertEquals(fighter.getAttack().getAttackHitbox(), new Hitbox(11,9,new Position(10,10),new Position(23,-4)));
        Assertions.assertNotEquals(fighter.getAttack().getAttackHitbox(), new Hitbox(11,9,new Position(10,10),new Position(3,-4)));
    }
    @Test
    public  void takingDamage(){
        fighter.decreaseHealth(5);
        Assertions.assertEquals(35,fighter.getHealth());
    }


}
