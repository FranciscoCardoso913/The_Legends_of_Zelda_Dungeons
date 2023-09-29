package com.l08gr01.legendsOfZeldaDungeons.sprites;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;

import java.awt.*;
import java.util.ArrayList;

public class NumberSpritesBlack {
    private ArrayList<Image> numbers = new ArrayList<>();
    private Image background;

    public NumberSpritesBlack() {
        createSprites(10, numbers);
        background = new Image("/images/numbers/black/background.png");
    }

    public ArrayList<Image> getNumbers() {
        return numbers;
    }

    public Image getBackground() {
        return background;
    }

    private void createSprites(int quantity, ArrayList<Image> sprites) {
        for (int i = 0; i < quantity; i++) {
            sprites.add(new Image("/images/numbers/black/" + i + ".png", new Color(255, 255, 255)));
        }
    }
}
