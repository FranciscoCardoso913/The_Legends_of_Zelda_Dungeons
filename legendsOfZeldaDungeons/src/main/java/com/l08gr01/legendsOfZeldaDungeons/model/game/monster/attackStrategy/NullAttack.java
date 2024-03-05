package com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy;

import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;

public class NullAttack implements AttackStrategy {
    @Override
    public boolean canAttack(long currTime, Link link) {
        return false;
    }

    @Override
    public boolean isReadyToAttack(long currTime) {
        return false;
    }

    @Override
    public boolean isInRange(Link link) {
        return false;
    }
}
