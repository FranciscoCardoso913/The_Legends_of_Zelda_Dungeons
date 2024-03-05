package com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy;

import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;

public class AlignedAttack implements AttackStrategy {
    private Monster monster;
    private long lastAttacked;
    private int range;

    public AlignedAttack(Monster monster, int range) {
        this.monster = monster;
        this.lastAttacked = 0;
        this.range = range;
    }

    @Override
    public boolean canAttack(long currTime, Link link) {
        if (isReadyToAttack(currTime) && isInRange(link) && isAligned(link)) {
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
        return monster.getCenterPoint().getDistance(link.getCenterPoint()) <= range;
    }

    public boolean isAligned(Link link) {
        boolean alignedVertically = monster.getCenterPoint().getX() == link.getCenterPoint().getX();
        boolean alignedHorizontally = monster.getCenterPoint().getY() == link.getCenterPoint().getY();

        return alignedVertically || alignedHorizontally;
    }

    public long getLastAttacked() {
        return lastAttacked;
    }

}
