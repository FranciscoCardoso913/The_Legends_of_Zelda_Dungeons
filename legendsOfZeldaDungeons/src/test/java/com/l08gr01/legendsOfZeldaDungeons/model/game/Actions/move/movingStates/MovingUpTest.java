package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.movingStates;

import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.Move;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MovingUpTest {
    MovingUp movingUp;
    Move move;

    @BeforeEach
    void setup() {
        move = Mockito.mock(Move.class);
        Mockito.when(move.getCharacter()).thenReturn(new Link(0, 0));
        movingUp = new MovingUp(move, Database.getDatabase().getLinkSprites().getMovingUp(), 1);
    }

    @Test
    void movingLeft() {
        movingUp.MovingLeft();
        Assertions.assertEquals(0, movingUp.currSprite);
        Mockito.verify(move).setCurrentMovingState(move.getCharacterMovingLeft());
        Mockito.verify(move).getCharacter();

    }

    @Test
    void MovingDown() {
        movingUp.MovingDown();
        Assertions.assertEquals(0, movingUp.currSprite);
        Mockito.verify(move).setCurrentMovingState(move.getCharacterMovingDown());
        Mockito.verify(move).getCharacter();

    }

    @Test
    void MovingRight() {
        movingUp.MovingRight();
        Assertions.assertEquals(0, movingUp.currSprite);
        Mockito.verify(move).setCurrentMovingState(move.getCharacterMovingRight());
        Mockito.verify(move).getCharacter();

    }

    @Test
    void MovingUp() {
        movingUp.MovingUp();
        Assertions.assertEquals(1, movingUp.currSprite);
        Mockito.verify(move).getCharacter();

        while (movingUp.currSprite < movingUp.sprites.size() - 1) movingUp.MovingUp();
        movingUp.MovingUp();

        Assertions.assertEquals(0, movingUp.currSprite);
    }

    @Test
    void attack() {
        movingUp.attack();
        Assertions.assertEquals(0, movingUp.currSprite);
        Mockito.verify(move, Mockito.times(3)).getCharacter();
    }

    @Test
    void getSprite() {
        Image image = movingUp.getSprite();
        Assertions.assertEquals(movingUp.sprites.get(movingUp.currSprite), image);
    }
}