package com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy;

import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AlignedAttackTest {
    private Link link;
    private Monster monster;
    private AlignedAttack alignedAttack;

    public AlignedAttackTest() {
    }

    @BeforeEach
    public void setup() {
        link = Mockito.mock(Link.class);
        monster = Mockito.mock(Monster.class);
        alignedAttack = new AlignedAttack(monster, 100);
    }

    public void setMonsterPosition(int x, int y) {
        Mockito.when(monster.getCenterPoint()).thenReturn(new Position(x, y));
    }

    public void setLinkPosition(int x, int y) {
        Mockito.when(link.getCenterPoint()).thenReturn(new Position(x, y));
    }

    @Test
    public void isReadyToAttack() {
        Assertions.assertFalse(alignedAttack.isReadyToAttack(0));
        Assertions.assertFalse(alignedAttack.isReadyToAttack(2000));
        Assertions.assertTrue(alignedAttack.isReadyToAttack(2001));
    }

    @Test
    public void isInRange() {
        setMonsterPosition(300, 300);
        setLinkPosition(300, 301);
        Assertions.assertTrue(alignedAttack.isInRange(link));

        setMonsterPosition(300, 300);
        setLinkPosition(300, 400);
        Assertions.assertTrue(alignedAttack.isInRange(link));

        setMonsterPosition(300, 300);
        setLinkPosition(300, 401);
        Assertions.assertFalse(alignedAttack.isInRange(link));
    }

    @Test
    public void isAligned() {
        setLinkPosition(300, 300);

        setMonsterPosition(300, 400);
        Assertions.assertTrue(alignedAttack.isAligned(link));

        setMonsterPosition(300, 200);
        Assertions.assertTrue(alignedAttack.isAligned(link));

        setMonsterPosition(200, 300);
        Assertions.assertTrue(alignedAttack.isAligned(link));

        setMonsterPosition(400, 300);
        Assertions.assertTrue(alignedAttack.isAligned(link));

        setMonsterPosition(200, 200);
        Assertions.assertFalse(alignedAttack.isAligned(link));
    }

    @Test
    public void canAttack() {
        setLinkPosition(300, 300);

        setMonsterPosition(199, 299);
        Assertions.assertFalse(alignedAttack.canAttack(1999, link));
        Assertions.assertEquals(0, alignedAttack.getLastAttacked());

        setMonsterPosition(200, 300);
        Assertions.assertTrue(alignedAttack.canAttack(2001, link));
        Assertions.assertEquals(2001, alignedAttack.getLastAttacked());
    }

    @Test
    public void readyToAttackAfterAttacking() {
        setLinkPosition(300, 300);
        setMonsterPosition(200, 300);
        alignedAttack.canAttack(2001, link);

        Assertions.assertFalse(alignedAttack.isReadyToAttack(4000));
    }
}