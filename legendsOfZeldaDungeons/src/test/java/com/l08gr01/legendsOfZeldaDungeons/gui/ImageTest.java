package com.l08gr01.legendsOfZeldaDungeons.gui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class ImageTest {

    @Test
    public void nullColor(){
        Image i= new Image("/images/link_sprites/movement/down/1.png");
        Assertions.assertEquals(new Color(0,0,0),i.getNullColor());
    }
}
