package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.movingStates;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.Move;

import java.util.ArrayList;

public class MovingLeft extends Moving {

    public MovingLeft(Move move, ArrayList<Image>sprites, int framesPerSprite) {
        super(move,sprites,framesPerSprite);
    }

    @Override
    public void MovingRight() {
        currSprite = 0;
        move.setCurrentMovingState(move.getCharacterMovingRight());
        move.getCharacter().getAttack().setAttackRight();
    }

    @Override
    public void MovingLeft() {
        currSprite= (currSprite+1)%(quantity*framesPerSprite);
        move.getCharacter().getAttack().setAttackLeft();
    }

    @Override
    public void MovingUp() {
        currSprite = 0;
        move.setCurrentMovingState(move.getCharacterMovingUp());
        move.getCharacter().getAttack().setAttackUp();
    }

    @Override
    public void MovingDown() {
        currSprite = 0;
        move.setCurrentMovingState(move.getCharacterMovingDown());
        move.getCharacter().getAttack().setAttackDown();
    }

    @Override
    public void attack() {
        currSprite = 0;
        move.getCharacter().setCharacterAction(move.getCharacter().getAttack());
        move.getCharacter().getAttack().setAttackLeft();
    }

    @Override
    public Image getSprite() {
        return sprites.get(currSprite/framesPerSprite);
    }
}
