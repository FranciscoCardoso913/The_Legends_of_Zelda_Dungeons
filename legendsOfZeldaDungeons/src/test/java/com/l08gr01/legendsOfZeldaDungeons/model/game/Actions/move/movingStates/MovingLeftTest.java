package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.movingStates;

import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.Move;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MovingLeftTest {
    MovingLeft movingLeft;
    Move move;

    @BeforeEach
    void setup() {
        move = Mockito.mock(Move.class);
        Mockito.when(move.getCharacter()).thenReturn(new Link(0, 0));
        movingLeft = new MovingLeft(move, Database.getDatabase().getLinkSprites().getMovingLeft(), 1);
    }

    @Test
    void movingRight() {
        movingLeft.MovingRight();
        Assertions.assertEquals(0, movingLeft.currSprite);
        Mockito.verify(move).setCurrentMovingState(move.getCharacterMovingRight());
        Mockito.verify(move).getCharacter();

    }

    @Test
    void MovingDown() {
        movingLeft.MovingDown();
        Assertions.assertEquals(0, movingLeft.currSprite);
        Mockito.verify(move).setCurrentMovingState(move.getCharacterMovingDown());
        Mockito.verify(move).getCharacter();

    }

    @Test
    void movingUp() {
        movingLeft.MovingUp();
        Assertions.assertEquals(0, movingLeft.currSprite);
        Mockito.verify(move).setCurrentMovingState(move.getCharacterMovingUp());
        Mockito.verify(move).getCharacter();

    }

    @Test
    void movingLeft() {
        movingLeft.MovingLeft();
        Assertions.assertEquals(1, movingLeft.currSprite);
        Mockito.verify(move).getCharacter();

        while (movingLeft.currSprite < movingLeft.sprites.size() - 1) movingLeft.MovingLeft();
        movingLeft.MovingLeft();

        Assertions.assertEquals(0, movingLeft.currSprite);
    }

    @Test
    void attack() {
        movingLeft.attack();
        Assertions.assertEquals(0, movingLeft.currSprite);
        Mockito.verify(move, Mockito.times(3)).getCharacter();
    }

    @Test
    void getSprite() {
        Image image = movingLeft.getSprite();
        Assertions.assertEquals(movingLeft.sprites.get(movingLeft.currSprite), image);
    }
}