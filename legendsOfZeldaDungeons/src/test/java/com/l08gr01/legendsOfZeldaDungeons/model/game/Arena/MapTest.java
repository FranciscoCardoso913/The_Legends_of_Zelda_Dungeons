package com.l08gr01.legendsOfZeldaDungeons.model.game.Arena;

import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public class MapTest {
    Map map;
    @BeforeEach
    public void setup() throws IOException {
        map=new Map1(new Link(10,10),1);
    }
   
}
