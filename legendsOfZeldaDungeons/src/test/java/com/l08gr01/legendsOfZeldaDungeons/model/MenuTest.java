package com.l08gr01.legendsOfZeldaDungeons.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MenuTest {
    Menu menu;
    @BeforeEach
    public void setup(){
        menu=new Menu();
    }
    @Test
    public void gettersTest(){
        Assertions.assertEquals(Arrays.asList("/images/menu/start_selected.png", "/images/menu/exit_selected.png"),menu.getEntries());
        Assertions.assertEquals(0,menu.getCurrEntry());
    }

}
