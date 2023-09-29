package com.l08gr01.legendsOfZeldaDungeons.model.game;

import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.movingStates.*;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;

public class Camera {
    private Position position;
    private int terminalWidth,terminalHeight;
    private Arena arena;

    public Camera(Position position, int width, int height, Arena arena){
        this.terminalHeight=height;
        this.terminalWidth=width;
        this.arena=arena;
        this.position=position;

    }
    public void update() {
        Moving moving = arena.getLink().getMover().getCurrentMovingState();
        if (moving.getClass() == MovingDown.class) moveDown();
        else if (moving.getClass() == MovingUp.class) moveUp();
        else if(moving.getClass()== MovingLeft.class)moveLeft();
        else if(moving.getClass()== MovingRight.class)moveRight();
    }


    void moveDown(){
        if(arena.getLink().getCenterPoint().getY()>position.getY()+terminalHeight/2 && position.getY()<arena.getMap().size()* arena.getSmallerSpritesSize()){
            if(position.getY()+arena.getLink().getVelocity()+terminalHeight<arena.getMap().size()* arena.getSmallerSpritesSize())
                position.setY(position.getY()+arena.getLink().getVelocity());
        }
    }
    void moveUp(){
        if(arena.getLink().getCenterPoint().getY()<position.getY()+terminalHeight/2 && position.getY()>0){
            position.setY(position.getY()-arena.getLink().getVelocity());
        }
    }
    void moveRight(){
        if(arena.getLink().getCenterPoint().getX()>position.getX()+terminalWidth/2 && position.getX()<=arena.getMap().get(0).size()* arena.getSmallerSpritesSize()){
            if(position.getX()+arena.getLink().getVelocity()+terminalWidth<arena.getMap().get(0).size()* arena.getSmallerSpritesSize())
                position.setX(position.getX()+arena.getLink().getVelocity());
        }
    }
    void moveLeft(){
        if(arena.getLink().getCenterPoint().getX()<position.getX()+terminalWidth/2 && position.getX()>0){
            position.setX(position.getX()-arena.getLink().getVelocity());
        }
    }
    public Position getCameraPositon(){
        return position;
    }

    public Arena getArena() { return arena; }

    public int getTerminalHeight() {
        return terminalHeight;
    }

    public int getTerminalWidth() {
        return terminalWidth;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
