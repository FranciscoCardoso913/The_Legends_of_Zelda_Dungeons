package com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy;

import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;

import java.lang.Math;

public class AlignStrategy implements MoveStrategy {
    Monster monster;

    public AlignStrategy(Monster monster) {
        this.monster = monster;
    }

    @Override
    public Position getNextPosition(long currTime, Position currPosition, Arena arena) {
        if (monster.getCenterPoint().getDistance(arena.getLink().getCenterPoint()) <= 64) {
            return moveAway(arena.getLink());
        }
        else if (tooFarAway(arena.getLink())) {
            monster.getMover().setDefault();
            return currPosition;
        }
        else {
            if (isAlignedWith(arena.getLink())) {
                if (monster.getCenterPoint().getDistance(arena.getLink().getCenterPoint()) >= 176) {
                    return moveTowards(arena.getLink());
                }
            }
            else {
                return align(arena.getLink());
            }
            monster.getMover().setDefault();
            return currPosition;
        }
    }

    protected Position align(Link link) {
        Position linkCenter = link.getCenterPoint();
        Position archerCenter = monster.getCenterPoint();
        int distX = Math.abs(linkCenter.getX() - archerCenter.getX());
        int distY = Math.abs(linkCenter.getY() - archerCenter.getY());

        if (distX < distY && linkCenter.getX() < archerCenter.getX()) {
            monster.getMover().MovingLeft();
            return monster.getPosition().getLeft();
        }
        else if (distX < distY && linkCenter.getX() >= archerCenter.getX()) {
            monster.getMover().MovingRight();
            return monster.getPosition().getRight();
        }
        else if (distX >= distY && linkCenter.getY() < archerCenter.getY()) {
            monster.getMover().MovingUp();
            return monster.getPosition().getUp();
        }
        else {
            monster.getMover().MovingDown();
            return monster.getPosition().getDown();
        }
    }

    protected Position moveAway(Link link) {
        Position linkCenter = link.getCenterPoint();
        Position archerCenter = monster.getCenterPoint();

        if (Math.abs(linkCenter.getX() - archerCenter.getX()) > Math.abs(linkCenter.getY() - archerCenter.getY())) {
            if (linkCenter.getX() < archerCenter.getX()) {
                monster.getMover().MovingRight();
                return monster.getPosition().getRight();
            }
            else {
                monster.getMover().MovingLeft();
                return monster.getPosition().getLeft();
            }
        }
        else {
            if (linkCenter.getY() < archerCenter.getY()) {
                monster.getMover().MovingDown();
                return monster.getPosition().getDown();
            }
            else {
                monster.getMover().MovingUp();
                return monster.getPosition().getUp();
            }
        }
    }

    protected Position moveTowards(Link link) {
        Position linkCenter = link.getCenterPoint();
        Position archerCenter = monster.getCenterPoint();

        if (linkCenter.getX() < archerCenter.getX()) {
            monster.getMover().MovingLeft();
            return monster.getPosition().getLeft();
        }
        else if (linkCenter.getX() > archerCenter.getX()) {
            monster.getMover().MovingRight();
            return monster.getPosition().getRight();
        }
        else if (linkCenter.getY() < archerCenter.getY()) {
            monster.getMover().MovingUp();
            return monster.getPosition().getUp();
        }
        else {
            monster.getMover().MovingDown();
            return monster.getPosition().getDown();
        }
    }

    protected boolean isAlignedWith(Link link) {
        Position linkCenter = link.getCenterPoint();
        Position thisCenter = this.monster.getCenterPoint();
        return (linkCenter.getX() == thisCenter.getX()) || (linkCenter.getY() == thisCenter.getY());
    }

    protected boolean tooFarAway(Link link) {
        return this.monster.getCenterPoint().getDistance(link.getCenterPoint()) > 300;
    }
}