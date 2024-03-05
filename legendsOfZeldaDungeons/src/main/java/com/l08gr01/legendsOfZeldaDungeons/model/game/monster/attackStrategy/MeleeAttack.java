package com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy;

import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;

public class MeleeAttack implements AttackStrategy {
    private Monster monster;
    private long lastAttacked;

    public MeleeAttack(Monster monster) {
        this.monster = monster;
        this.lastAttacked = 0;
    }

    @Override
    public boolean canAttack(long currTime, Link link) {
        if (isReadyToAttack(currTime) && isInRange(link)) {
            lastAttacked = currTime;
            return true;
        }
        return false;
    }

    @Override
    public boolean isReadyToAttack(long currTime) {
        return currTime - lastAttacked > 2000;
    }

    @Override
    public boolean isInRange(Link link) {
        return monster.getAttack().getAttackHitbox().collidesWith(link.getHitbox());
    }

    public long getLastAttacked() {
        return lastAttacked;
    }

}
