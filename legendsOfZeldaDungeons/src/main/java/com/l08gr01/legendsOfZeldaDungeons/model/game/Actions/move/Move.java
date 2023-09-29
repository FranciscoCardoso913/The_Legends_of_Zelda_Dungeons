package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.Action;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.movingStates.*;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Character;

import com.l08gr01.legendsOfZeldaDungeons.sprites.CharacterSprites;

public class Move implements Action {

    Moving characterMovingDown;
    Moving characterMovingUp;
    Moving characterMovingLeft;
    Moving characterMovingRight;
    Moving currentMovingState;
    Character character;

    public Move(Character character, CharacterSprites characterSprites, int framesPerSprite){
        this.character=character;
        characterMovingDown= new MovingDown(this, characterSprites.getMovingDown(),framesPerSprite);
        characterMovingLeft= new MovingLeft(this, characterSprites.getMovingLeft(),framesPerSprite);
        characterMovingRight= new MovingRight(this, characterSprites.getMovingRight(),framesPerSprite);
        characterMovingUp= new MovingUp(this, characterSprites.getMovingUp(),framesPerSprite);
        setCurrentMovingState(characterMovingUp);
    }

    public void setCurrentMovingState(Moving movingState){
        currentMovingState=movingState;
        setDefault();
    }

    public Moving getCharacterMovingDown(){
        return characterMovingDown;
    }

    public Moving getCharacterMovingLeft() {
        return characterMovingLeft;
    }

    public Moving getCharacterMovingRight() {
        return characterMovingRight;
    }

    public Moving getCharacterMovingUp() {
        return characterMovingUp;
    }

    public void MovingDown(){
        currentMovingState.MovingDown();
    }
    public void MovingUp(){
        currentMovingState.MovingUp();
    }

    public Moving getCurrentMovingState() {
        return currentMovingState;
    }

    public void MovingLeft(){
        currentMovingState.MovingLeft();
    }
    public void MovingRight(){
        currentMovingState.MovingRight();
    }
    public void attack() {
        currentMovingState.attack();
    }
    public void setDefault(){
        currentMovingState.setCurrSprite(0);
    }

    @Override
    public  Character getCharacter(){
        return character;
    }

    @Override
    public Image getSprite() {
        return currentMovingState.getSprite();
    }
    @Override
    public void setCharacterAction( Action action){
        character.setCharacterAction(action);
    }
}
