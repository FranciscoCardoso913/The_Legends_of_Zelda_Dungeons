package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.movingStates;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.Move;

import java.util.ArrayList;

public abstract class Moving {

    Move move;
    ArrayList<Image> sprites;
    int currSprite;

    int framesPerSprite;

    int quantity;

    public Moving(Move move, ArrayList<Image> sprites, int framesPerSprite){
        this.move = move;
        this.sprites= sprites;
        quantity=sprites.size();
        this.framesPerSprite=framesPerSprite;
    }
    public void setCurrSprite(int x){
        currSprite=x;
    }
    public abstract void MovingRight() ;

    public abstract void MovingLeft();

    public abstract void MovingUp() ;

    public abstract void MovingDown() ;

    public abstract void attack();
    public abstract Image getSprite();
}
