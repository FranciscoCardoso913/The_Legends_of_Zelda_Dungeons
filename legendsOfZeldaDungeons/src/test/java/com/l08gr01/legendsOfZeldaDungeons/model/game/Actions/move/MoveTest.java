package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move;

import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.Attack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.movingStates.Moving;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.movingStates.MovingDown;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveTest {
    Move move;

    @BeforeEach
    void setup() {
        move = new Move(new Link(0, 0), Database.getDatabase().getLinkSprites(), 1);
    }

    @Test
    void setCurrentMovingState() {
        Moving moving = new MovingDown(move, Database.getDatabase().getLinkSprites().getMovingDown(), 1);

        move.setCurrentMovingState(moving);

        Assertions.assertSame(moving, move.getCurrentMovingState());
    }

    @Test
    void MovingUp() {
        move.MovingUp();
        Assertions.assertSame(move.characterMovingUp, move.currentMovingState);
    }

    @Test
    void MovingDown() {
        move.MovingDown();
        Assertions.assertSame(move.characterMovingDown, move.currentMovingState);
    }

    @Test
    void MovingLeft() {
        move.MovingLeft();
        Assertions.assertSame(move.characterMovingLeft, move.currentMovingState);
    }

    @Test
    void MovingRight() {
        move.MovingRight();
        Assertions.assertSame(move.characterMovingRight, move.currentMovingState);
    }

    @Test
    void attack() {
        move.attack();
        Assertions.assertSame(move.character.getCharacterAction(), move.character.getAttack());
    }

    @Test
    void setCharacterAction() {
        Attack action = move.character.getAttack();
        move.setCharacterAction(action);
        Assertions.assertSame(action, move.getCharacter().getCharacterAction());
    }
}