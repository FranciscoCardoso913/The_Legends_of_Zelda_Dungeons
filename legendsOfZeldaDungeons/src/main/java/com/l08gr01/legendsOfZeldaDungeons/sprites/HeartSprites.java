package com.l08gr01.legendsOfZeldaDungeons.sprites;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;

import java.util.ArrayList;

public class HeartSprites {
    ArrayList<Image> hearts = new ArrayList<>();

    public HeartSprites() {
        getSprites("", 3, hearts);
    }

    private void getSprites(String path, int quantity, ArrayList<Image> sprites) {
        for (int i = 1; i <= quantity; i++) {
            sprites.add(new Image("/images/hearts/" + path + i + ".png"));
        }
    }

    public ArrayList<Image> getHearts() {
        return hearts;
    }
}
