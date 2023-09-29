package com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy;

import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;

public class RandomMovement implements MoveStrategy {

    private long lastMovement;

    private int currentDirection;

    private Monster monster;

    public RandomMovement(Monster monster) {
        this.monster = monster;
        lastMovement = 0;
        currentDirection = 0;
    }

    @Override
    public Position getNextPosition(long currTime, Position currPosition, Arena arena) {
        if (currTime - lastMovement > 1000) {
            currentDirection = (int) (Math.random() * 4);
            this.lastMovement = currTime;
        }
        switch (currentDirection) {
            case 0:
                monster.getMover().MovingLeft();
                return new Position(currPosition.getX() - monster.getVelocity(), currPosition.getY());
            case 1:
                monster.getMover().MovingRight();
                return new Position(currPosition.getX() + monster.getVelocity(), currPosition.getY());
            case 2:
                monster.getMover().MovingUp();
                return new Position(currPosition.getX(), currPosition.getY() - monster.getVelocity());
            default:
                monster.getMover().MovingDown();
                return new Position(currPosition.getX(), currPosition.getY() + monster.getVelocity());
        }
    }

    public long getLastMovement() {
        return lastMovement;
    }

}

