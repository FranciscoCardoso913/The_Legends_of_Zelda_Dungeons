package com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy;

import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;

import java.lang.Math;

public class ChaseMovement implements MoveStrategy {
    private Monster monster;

    public ChaseMovement(Monster monster) {
        this.monster = monster;
    }

    @Override
    public Position getNextPosition(long currTime, Position currPosition, Arena arena) {
        Hitbox weaponHitbox = monster.getAttack().getAttackHitbox();
        boolean stayInPlace = tooFarAway(arena.getLink()) || weaponHitbox.collidesWith(arena.getLink().getHitbox().getShortenedHitbox(0.5));

        if (tooClose(arena.getLink())) {
            turnToLink(this.monster, arena.getLink());
            this.monster.getMover().setDefault();
            return currPosition;
        }
        else if (stayInPlace) {
            this.monster.getMover().setDefault();
            return currPosition;
        }
        else  {
            int distY = Math.abs(monster.getCenterPoint().getY() - arena.getLink().getCenterPoint().getY());

            if (distY == 0) {
                return moveHorizontally(arena.getLink());
            }
            else
                return moveVertically(arena.getLink());
        }
    }

    protected Position moveHorizontally(Link link) {
        if (monster.getHitbox().getHitboxCenter().getX() < link.getCenterPoint().getX()) {
            monster.getMover().MovingRight();
            return monster.getPosition().getRight();
        }
        else {
            monster.getMover().MovingLeft();
            return monster.getPosition().getLeft();
        }
    }

    protected Position moveVertically(Link link) {
        if (monster.getHitbox().getHitboxCenter().getY() < link.getCenterPoint().getY()) {
            monster.getMover().MovingDown();
            return monster.getPosition().getDown();
        }
        else {
            monster.getMover().MovingUp();
            return monster.getPosition().getUp();
        }
    }

    protected boolean tooFarAway(Link link) {
        return this.monster.getCenterPoint().getDistance(link.getCenterPoint()) >= 300;
    }

    protected boolean tooClose(Link link) {
        return this.monster.getCenterPoint().getDistance(link.getCenterPoint()) <= 16;
    }

    protected void turnToLink(Monster monster, Link link) {
        if (Math.abs(monster.getCenterPoint().getX() - link.getCenterPoint().getX()) > Math.abs(monster.getCenterPoint().getY() - link.getCenterPoint().getY())) {
            if (monster.getCenterPoint().getX() < link.getCenterPoint().getX())
                monster.getMover().MovingRight();
            else
                monster.getMover().MovingLeft();
        }
        else {
            if (monster.getCenterPoint().getY() < link.getCenterPoint().getY())
                monster.getMover().MovingDown();
            else
                monster.getMover().MovingUp();
        }
    }

}