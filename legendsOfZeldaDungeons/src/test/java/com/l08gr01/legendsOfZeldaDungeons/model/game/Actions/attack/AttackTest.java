package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack;

import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.sprites.LinkSprites;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AttackTest {

    Attack attack;

    @BeforeEach
    public void setUp(){
        LinkSprites linkSprites= new LinkSprites();
        ArrayList<Hitbox> temp= new ArrayList<>();
        Link link= new Link(20,20);
        temp.add(new Hitbox(10,10,link.getPosition(),new Position(0,0)));
        temp.add(new Hitbox(10,10,link.getPosition(),new Position(0,0)));
        temp.add(new Hitbox(10,10,link.getPosition(),new Position(0,0)));
        temp.add(new Hitbox(10,10,link.getPosition(),new Position(0,0)));
        attack= new Attack(link,linkSprites.getAttackSprites(),1,10,temp);
    }

    @Test
    public void switchingBetweenStates(){
        Assertions.assertEquals(attack.getAttackUp(),attack.getCurrentAttackState());
        attack.setAttackDown();
        Assertions.assertNotEquals(attack.getAttackUp(),attack.getCurrentAttackState());
        Assertions.assertEquals(attack.getAttackDown(),attack.getCurrentAttackState());
        attack.setAttackLeft();
        Assertions.assertEquals(attack.getAttackLeft(),attack.getCurrentAttackState());
        attack.setAttackRight();
        Assertions.assertEquals(attack.getAttackRight(),attack.getCurrentAttackState());
        attack.setAttackUp();
        Assertions.assertEquals(attack.getAttackUp(),attack.getCurrentAttackState());
    }

}
