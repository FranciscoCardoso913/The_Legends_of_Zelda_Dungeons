package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.movingStates;

import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.Move;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class MovingDownTest {

    MovingDown movingDown;
    Move move;

    @BeforeEach
    void setup() {
        move = Mockito.mock(Move.class);
        Mockito.when(move.getCharacter()).thenReturn(new Link(0, 0));
        movingDown = new MovingDown(move, Database.getDatabase().getLinkSprites().getMovingDown(), 1);
    }

    @Test
    void movingRight() {
        movingDown.MovingRight();
        Assertions.assertEquals(0, movingDown.currSprite);
        Mockito.verify(move).setCurrentMovingState(move.getCharacterMovingRight());
        Mockito.verify(move).getCharacter();

    }

    @Test
    void movingLeft() {
        movingDown.MovingLeft();
        Assertions.assertEquals(0, movingDown.currSprite);
        Mockito.verify(move).setCurrentMovingState(move.getCharacterMovingLeft());
        Mockito.verify(move).getCharacter();

    }

    @Test
    void movingUp() {
        movingDown.MovingUp();
        Assertions.assertEquals(0, movingDown.currSprite);
        Mockito.verify(move).setCurrentMovingState(move.getCharacterMovingUp());
        Mockito.verify(move).getCharacter();

    }

    @Test
    void movingDown() {
        movingDown.MovingDown();
        Assertions.assertEquals(1, movingDown.currSprite);
        Mockito.verify(move).getCharacter();

        while (movingDown.currSprite < movingDown.sprites.size() - 1) movingDown.MovingDown();
        movingDown.MovingDown();

        Assertions.assertEquals(0, movingDown.currSprite);
    }

    @Test
    void attack() {
        movingDown.attack();
        Assertions.assertEquals(0, movingDown.currSprite);
        Mockito.verify(move, Mockito.times(3)).getCharacter();
    }

    @Test
    void getSprite() {
        Image image = movingDown.getSprite();
        Assertions.assertEquals(movingDown.sprites.get(movingDown.currSprite), image);
    }
}