package com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy;

import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.Attack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MeleeAttackTest {
    private Monster monster;
    private Link link;
    private MeleeAttack meleeAttack;

    @BeforeEach
    public void setup() {
        link = Mockito.mock(Link.class);
        monster = Mockito.mock(Monster.class);
        meleeAttack = new MeleeAttack(monster);
    }

    public void setMonsterPosition(int x, int y) {
        Mockito.when(monster.getCenterPoint()).thenReturn(new Position(x, y));
    }

    public void setLinkPosition(int x, int y) {
        Mockito.when(link.getCenterPoint()).thenReturn(new Position(x, y));
    }

    @Test
    public void isReadyToAttack() {
        Assertions.assertFalse(meleeAttack.isReadyToAttack(0));
        Assertions.assertFalse(meleeAttack.isReadyToAttack(2000));
        Assertions.assertTrue(meleeAttack.isReadyToAttack(2001));
    }

    @Test
    public void isInRange() {
        Attack attack = Mockito.mock(Attack.class);
        Mockito.when(monster.getAttack()).thenReturn(attack);
        Mockito.when(link.getHitbox()).thenReturn(new Hitbox(20, 20, new Position(100, 100), new Position(0, 0)));

        Mockito.when(attack.getAttackHitbox()).thenReturn(new Hitbox(20, 20, new Position(90, 90), new Position(0, 0)));
        Assertions.assertTrue(meleeAttack.isInRange(link));

        Mockito.when(attack.getAttackHitbox()).thenReturn(new Hitbox(20, 20, new Position(70, 70), new Position(0, 0)));
        Assertions.assertFalse(meleeAttack.isInRange(link));
    }

    @Test
    public void canAttack() {
        Attack attack = Mockito.mock(Attack.class);
        Mockito.when(monster.getAttack()).thenReturn(attack);
        Mockito.when(link.getHitbox()).thenReturn(new Hitbox(20, 20, new Position(100, 100), new Position(0, 0)));

        Mockito.when(attack.getAttackHitbox()).thenReturn(new Hitbox(20, 20, new Position(90, 90), new Position(0, 0)));
        Assertions.assertFalse(meleeAttack.canAttack(1999, link));
        Assertions.assertEquals(0, meleeAttack.getLastAttacked());

        setMonsterPosition(200, 300);
        Assertions.assertTrue(meleeAttack.canAttack(2001, link));
        Assertions.assertEquals(2001, meleeAttack.getLastAttacked());
    }

    @Test
    public void readyToAttackAfterAttacking() {
        Attack attack = Mockito.mock(Attack.class);
        Mockito.when(monster.getAttack()).thenReturn(attack);
        Mockito.when(link.getHitbox()).thenReturn(new Hitbox(20, 20, new Position(100, 100), new Position(0, 0)));
        Mockito.when(attack.getAttackHitbox()).thenReturn(new Hitbox(20, 20, new Position(90, 90), new Position(0, 0)));

        setLinkPosition(300, 300);
        setMonsterPosition(200, 300);
        meleeAttack.canAttack(2001, link);

        Assertions.assertFalse(meleeAttack.isReadyToAttack(4000));
    }
}