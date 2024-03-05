package com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy;

import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;

public interface AttackStrategy {
    boolean canAttack(long currTime, Link link);
    boolean isReadyToAttack(long currTime);

    boolean isInRange(Link link);
}
