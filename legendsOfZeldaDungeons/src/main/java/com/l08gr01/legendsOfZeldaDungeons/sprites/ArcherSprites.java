package com.l08gr01.legendsOfZeldaDungeons.sprites;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;

import java.util.ArrayList;

public class ArcherSprites implements CharacterSprites {
    private ArrayList<Image> leftMove = new ArrayList<>();
    private ArrayList<Image> rightMove = new ArrayList<>();
    private ArrayList<Image> upMove = new ArrayList<>();
    private ArrayList<Image> downMove = new ArrayList<>();
    private ArrayList<Image> leftAttack = new ArrayList<>();
    private ArrayList<Image> rightAttack = new ArrayList<>();
    private ArrayList<Image> upAttack = new ArrayList<>();
    private ArrayList<Image> downAttack = new ArrayList<>();

    public ArcherSprites() {
        createSprites("move/down/", 2, downMove);
        createSprites("move/up/", 2, upMove);
        createSprites("move/right/", 2, rightMove);
        createSprites("move/left/", 2, leftMove);
        createSprites("attack/down/", 2, downAttack);
        createSprites("attack/up/", 2, upAttack);
        createSprites("attack/right/", 2, rightAttack);
        createSprites("attack/left/", 2, leftAttack);
    }

    @Override
    public ArrayList<Image> getMovingDown() {
        return downMove;
    }

    @Override
    public ArrayList<Image> getMovingLeft() {
        return leftMove;
    }

    @Override
    public ArrayList<Image> getMovingRight() {
        return rightMove;
    }

    @Override
    public ArrayList<Image> getMovingUp() {
        return upMove;
    }

    public ArrayList<ArrayList<Image>> getAttackSprites() {
        ArrayList<ArrayList<Image>> sprites= new ArrayList<>();
        sprites.add(upAttack);
        sprites.add(downAttack);
        sprites.add(leftAttack);
        sprites.add(rightAttack);
        return sprites;
    }

    private void createSprites(String path, int quantity, ArrayList<Image> sprites) {
        for (int i = 1; i <= quantity; i++) {
            sprites.add(new Image("/images/enemies/archer/" + path + i + ".png"));
        }
    }
}