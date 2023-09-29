package com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.Move;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RandomMovementTest {
    private Arena arena;
    private Monster monster;
    private Move mover;
    private RandomMovement randomMovement;

    @BeforeEach
    public void setup() {
        arena = Mockito.mock(Arena.class);
        monster = Mockito.mock(Monster.class);
        mover = Mockito.mock(Move.class);
        randomMovement = new RandomMovement(monster);

        Mockito.doAnswer((i) -> {
            return mover;
        }).when(monster).getMover();

        Mockito.when(monster.getVelocity()).thenReturn(1);
    }

    @Test
    public void verifyReturnedPosition() {
        Position currPosition = new Position(100, 100);
        Position newPosition = randomMovement.getNextPosition(1500, currPosition, arena);

        boolean movedLeft = newPosition.equals(currPosition.getLeft());
        boolean movedRight = newPosition.equals(currPosition.getRight());
        boolean movedUp = newPosition.equals(currPosition.getUp());
        boolean movedDown = newPosition.equals(currPosition.getDown());

        if (movedLeft)
            Mockito.verify(mover, Mockito.times(1)).MovingLeft();
        else if (movedRight)
            Mockito.verify(mover, Mockito.times(1)).MovingRight();
        else if (movedUp)
            Mockito.verify(mover, Mockito.times(1)).MovingUp();
        else
            Mockito.verify(mover, Mockito.times(1)).MovingDown();

        Assertions.assertTrue(movedLeft || movedDown || movedUp || movedRight);
    }

    @Test
    public void verifyLastMovementUpdate() {
        randomMovement.getNextPosition(500, new Position(0, 0), arena);

        Assertions.assertEquals(0, randomMovement.getLastMovement());

        randomMovement.getNextPosition(1500, new Position(0, 0), arena);

        Assertions.assertEquals(1500, randomMovement.getLastMovement());
    }
}