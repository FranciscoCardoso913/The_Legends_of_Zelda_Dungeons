package com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy;

import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;

public interface MoveStrategy {
    public Position getNextPosition(long currTime, Position currPosition, Arena arena);
}
