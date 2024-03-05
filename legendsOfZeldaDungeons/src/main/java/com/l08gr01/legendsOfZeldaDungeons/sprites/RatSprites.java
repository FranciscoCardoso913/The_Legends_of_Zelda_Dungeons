package com.l08gr01.legendsOfZeldaDungeons.sprites;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;

import java.util.ArrayList;

public class RatSprites implements CharacterSprites {
    private ArrayList<Image> leftMove = new ArrayList<>();
    private ArrayList<Image> rightMove = new ArrayList<>();
    private ArrayList<Image> upMove = new ArrayList<>();
    private ArrayList<Image> downMove = new ArrayList<>();

    public RatSprites() {

        createSprites("down/", 2, downMove);
        createSprites("up/", 2, upMove);
        createSprites("left/", 2, leftMove);
        createSprites("right/", 2, rightMove);
    }

    @Override
    public ArrayList<Image> getMovingUp() {
        return upMove;
    }

    @Override
    public ArrayList<Image> getMovingRight() {
        return rightMove;
    }

    @Override
    public ArrayList<Image> getMovingLeft() {
        return leftMove;
    }

    @Override
    public ArrayList<Image> getMovingDown() {
        return downMove;
    }

    private void createSprites(String path, int quantity, ArrayList<Image> sprites) {
        for (int i = 1; i <= quantity; i++) {
            sprites.add(new Image("/images/enemies/rat/" + path + i + ".png"));
        }
    }
}
