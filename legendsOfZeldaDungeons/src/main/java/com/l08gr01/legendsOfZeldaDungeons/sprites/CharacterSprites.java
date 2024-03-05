package com.l08gr01.legendsOfZeldaDungeons.sprites;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;

import java.util.ArrayList;

public interface CharacterSprites {
    ArrayList<Image> movingLeft=new ArrayList<>();
    ArrayList<Image> movingRight=new ArrayList<>();
    ArrayList<Image> movingUp=new ArrayList<>();
    ArrayList<Image> movingDown=new ArrayList<>();

    public ArrayList<Image> getMovingLeft();
    public ArrayList<Image> getMovingRight();

    public ArrayList<Image> getMovingUp();

    public ArrayList<Image> getMovingDown();
}
