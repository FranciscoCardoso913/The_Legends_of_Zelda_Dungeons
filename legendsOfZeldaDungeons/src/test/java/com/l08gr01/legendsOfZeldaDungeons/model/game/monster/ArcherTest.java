package com.l08gr01.legendsOfZeldaDungeons.model.game.monster;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.BowAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.ShieldAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy.AlignedAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy.MeleeAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy.AlignStrategy;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy.RandomMovement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArcherTest {
    private Archer archer;

    @BeforeEach
    public void setUp(){
        this.archer= new Archer(150,150,1);
    }

    @Test
    public void archerStatus(){
        archer= new Archer(150,150,2);
        Assertions.assertEquals(archer.getVelocity(),1);
        Assertions.assertEquals(archer.getHealth(),60);
        Assertions.assertEquals(archer.getMaxHealth(),60);
        Assertions.assertEquals(archer.getPoints(),50);
        archer= new Archer(150,150,3);
        Assertions.assertEquals(archer.getHealth(),90);
        Assertions.assertEquals(archer.getMaxHealth(),90);
    }
    @Test
    public void archerTopLeftCornerTestTrue(){
        Assertions.assertEquals(archer.getHitboxPosition(), new Position(163,159));
    }
    @Test
    public void archerTopLeftCornerTestFalse(){
        Assertions.assertNotEquals(archer.getHitboxPosition(), new Position(162,165));
    }
    @Test
    public void archerCenterPointTestTrue(){
        Assertions.assertEquals(archer.getCenterPoint(), new Position(172,171));
    }
    @Test
    public void archerCenterPointTestFalse(){
        Assertions.assertNotEquals(archer.getCenterPoint(), new Position(170,172));
    }
    @Test
    public void InitialArcherCurrentSpriteTrue(){
        Assertions.assertEquals(archer.getSprite(), new Image("/images/enemies/archer/move/up/1.png"));
    }
    @Test
    public void InitialArcherCurrentSpriteFalse(){
        Assertions.assertNotEquals(archer.getSprite(), new Image("/images/enemies/archer/move/up/2.png"));
    }
    @Test
    public void archerSpritesTest(){
        archer.getMover().MovingDown();
        Assertions.assertNotEquals(archer.getSprite(), new Image("/images/enemies/archer/move/up/1.png"));
        Assertions.assertEquals(archer.getSprite(), new Image("/images/enemies/archer/move/down/1.png"));
        archer.getMover().MovingDown();
        archer.getMover().MovingDown();
        Assertions.assertEquals(archer.getSprite(), new Image("/images/enemies/archer/move/down/2.png"));
        archer.getMover().MovingRight();
        Assertions.assertEquals(archer.getSprite(), new Image("/images/enemies/archer/move/right/1.png"));
        archer.setCharacterAction(archer.getAttack());
        Assertions.assertEquals(archer.getSprite(), new Image("/images/enemies/archer/attack/right/1.png"));

    }

    @Test
    public void getArcherSize(){
        Assertions.assertEquals(archer.getWidth(),19);
        Assertions.assertEquals(archer.getHeight(),24);
    }

    @Test
    public void archerAttacks(){
        Assertions.assertEquals(archer.getAttack().getClass(), BowAttack.class);
        Assertions.assertNotEquals(archer.getAttack().getClass(), ShieldAttack.class);
        Assertions.assertEquals(archer.getAttack().getDamage(),10);
    }
    @Test
    public void archerStrategies(){
        Assertions.assertEquals(archer.getAttackStrategy().getClass(), AlignedAttack.class);
        Assertions.assertNotEquals(archer.getAttackStrategy().getClass(), MeleeAttack.class);
    }
    @Test
    public void MoveStrategies(){
        Assertions.assertEquals(archer.getMoveStrategy().getClass(), AlignStrategy.class);
        Assertions.assertNotEquals(archer.getMoveStrategy().getClass(), RandomMovement.class);
    }

    @Test
    public void archerHitbox(){
        Assertions.assertEquals(archer.getHitbox(),new Hitbox(24, 19, new Position(150,150), new Position(13, 9)));
        archer.setPosition(new Position(10,10));
        Assertions.assertEquals(archer.getHitbox(),new Hitbox(24, 19, new Position(10,10), new Position(13, 9)));
    }
    @Test
    public void archerWeaponsHitboxes(){
        Assertions.assertEquals(archer.getAttack().getAttackHitbox(), new Hitbox(15,5,new Position(150,150),new Position(19,-9)));
        archer.setPosition(new Position(10,10));
        Assertions.assertEquals(archer.getAttack().getAttackHitbox(), new Hitbox(15,5,new Position(10,10),new Position(19,-9)));
        Assertions.assertNotEquals(archer.getAttack().getAttackHitbox(), new Hitbox(11,9,new Position(10,10),new Position(3,-4)));

    }
    @Test
    public  void takingDamage(){
        archer.decreaseHealth(5);
        Assertions.assertEquals(25,archer.getHealth());
    }
}


