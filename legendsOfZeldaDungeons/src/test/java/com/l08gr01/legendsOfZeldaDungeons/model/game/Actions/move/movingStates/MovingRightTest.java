package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.movingStates;

import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.Move;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MovingRightTest {
    MovingRight movingRight;
    Move move;

    @BeforeEach
    void setup() {
        move = Mockito.mock(Move.class);
        Mockito.when(move.getCharacter()).thenReturn(new Link(0, 0));
        movingRight = new MovingRight(move, Database.getDatabase().getLinkSprites().getMovingRight(), 1);
    }

    @Test
    void movingLeft() {
        movingRight.MovingLeft();
        Assertions.assertEquals(0, movingRight.currSprite);
        Mockito.verify(move).setCurrentMovingState(move.getCharacterMovingLeft());
        Mockito.verify(move).getCharacter();

    }

    @Test
    void MovingDown() {
        movingRight.MovingDown();
        Assertions.assertEquals(0, movingRight.currSprite);
        Mockito.verify(move).setCurrentMovingState(move.getCharacterMovingDown());
        Mockito.verify(move).getCharacter();

    }

    @Test
    void movingUp() {
        movingRight.MovingUp();
        Assertions.assertEquals(0, movingRight.currSprite);
        Mockito.verify(move).setCurrentMovingState(move.getCharacterMovingUp());
        Mockito.verify(move).getCharacter();

    }

    @Test
    void MovingRight() {
        movingRight.MovingRight();
        Assertions.assertEquals(1, movingRight.currSprite);
        Mockito.verify(move).getCharacter();

        while (movingRight.currSprite < movingRight.sprites.size() - 1) movingRight.MovingRight();
        movingRight.MovingRight();

        Assertions.assertEquals(0, movingRight.currSprite);
    }

    @Test
    void attack() {
        movingRight.attack();
        Assertions.assertEquals(0, movingRight.currSprite);
        Mockito.verify(move, Mockito.times(3)).getCharacter();
    }

    @Test
    void getSprite() {
        Image image = movingRight.getSprite();
        Assertions.assertEquals(movingRight.sprites.get(movingRight.currSprite), image);
    }
}