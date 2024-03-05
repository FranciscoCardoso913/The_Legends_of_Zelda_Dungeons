package com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy;

import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NullAttackTest {
    private Link link;
    private NullAttack nullAttack;

    @BeforeEach
    public void setup() {
        nullAttack = new NullAttack();
        link = Mockito.mock(Link.class);
    }

    @Test
    public void doenstAttack() {
        Assertions.assertFalse(nullAttack.canAttack(0, link));
    }

    @Test
    public void isntReadyToAttack() {
        Assertions.assertFalse(nullAttack.isReadyToAttack(0));
    }

    @Test
    public void isntInRange() {
        Assertions.assertFalse(nullAttack.isInRange(link));
    }
}
