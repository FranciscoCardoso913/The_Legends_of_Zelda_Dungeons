package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class AttackStateTest {
    private AttackState attackState;

    @BeforeEach
    void setUp(){
        ArrayList<Image> temp= new ArrayList<>();
        temp.add(new Image("/images/enemies/rat/up/1.png"));
        temp.add(new Image("/images/enemies/archer/move/down/1.png"));
        Attack attack=Mockito.mock(Attack.class);

        attackState= new AttackState(attack,temp,1,new Hitbox(10,10,new Position(10,10),new Position(0,0)));

    }

    @Test
    public void gettersTester(){
        Assertions.assertEquals(new Hitbox(10,10,new Position(10,10),new Position(0,0)),attackState.getWeaponHitbox());
        Assertions.assertEquals(1,attackState.getFramesPerSprite());
        Assertions.assertEquals(2,attackState.getQuantity());
    }
    @Test
    public void updateTest(){
        Assertions.assertEquals(0,attackState.getCurrSprite());
        Assertions.assertEquals(new Image("/images/enemies/rat/up/1.png"),attackState.getSprite());
        attackState.attack();
        Assertions.assertEquals(1,attackState.getCurrSprite());
    }
}
