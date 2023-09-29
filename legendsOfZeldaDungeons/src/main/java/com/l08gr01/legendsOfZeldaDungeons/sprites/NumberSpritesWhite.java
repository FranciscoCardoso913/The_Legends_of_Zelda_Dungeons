package com.l08gr01.legendsOfZeldaDungeons.sprites;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;

import java.awt.*;
import java.util.ArrayList;

public class NumberSpritesWhite {
    private ArrayList<Image> numbers = new ArrayList<>();

    public NumberSpritesWhite() {
        createSprites(10, numbers);
    }

    public ArrayList<Image> getNumbers() {
        return numbers;
    }

    private void createSprites(int quantity, ArrayList<Image> sprites) {
        for (int i = 0; i < quantity; i++) {
            sprites.add(new Image("/images/numbers/white/" + i + ".png", new Color(0, 0, 0)));
        }
    }
}
