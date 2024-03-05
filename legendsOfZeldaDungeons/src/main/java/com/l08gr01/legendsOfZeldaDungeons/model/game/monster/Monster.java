package com.l08gr01.legendsOfZeldaDungeons.model.game.monster;

import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Character;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy.AttackStrategy;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy.MoveStrategy;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;

public abstract class Monster extends Character {
    protected MoveStrategy moveStrategy;
    private AttackStrategy attackStrategy;
    private int points;

    protected int monstersLevel;

    public Monster(int x, int y, int level, int points) {
        super(x, y);
        this.points = points;
        hitbox = new Hitbox(35, 22, getPosition(),new Position(0,0));
        moveStrategy = buildMoveStrategy();
        attackStrategy = buildAttackStrategy();
        monstersLevel=level;
    }

    public int getPoints() {
        return points;
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }
    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    protected abstract MoveStrategy buildMoveStrategy();
    protected abstract AttackStrategy buildAttackStrategy();
}
