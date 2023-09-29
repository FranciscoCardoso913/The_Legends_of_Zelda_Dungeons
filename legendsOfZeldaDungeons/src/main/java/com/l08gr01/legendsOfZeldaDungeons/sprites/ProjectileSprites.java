package com.l08gr01.legendsOfZeldaDungeons.sprites;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;

import java.util.ArrayList;

public class ProjectileSprites {
    ArrayList<Image> linkArrows = new ArrayList<>();
    ArrayList<Image> monsterArrows = new ArrayList<>();

    public ProjectileSprites() {
        createSprites("arrow/arrow_", 4, linkArrows);
        createSprites("enemies/arrow/arrow_", 4, monsterArrows);
    }

    public ArrayList<Image> getLinkArrows() {
        return linkArrows;
    }

    public ArrayList<Image> getMonsterArrows() {
        return monsterArrows;
    }

    private void createSprites(String path, int quantity, ArrayList<Image> sprites) {
        for (int i = 1; i <= quantity; i++) {
            sprites.add(new Image("/images/" + path + i + ".png"));
        }
    }
}
