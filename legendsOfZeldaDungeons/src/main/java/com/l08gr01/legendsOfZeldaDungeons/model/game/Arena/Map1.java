package com.l08gr01.legendsOfZeldaDungeons.model.game.Arena;

import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Rat;
import java.io.IOException;
import java.util.ArrayList;


public class Map1 extends Map{

    public Map1(Link link,int monstersLevel) throws IOException {
        super(link,monstersLevel);
        link.setPosition(new Position(185,288));
        loadMap(1);
        cameraIntialPosition= new Position(64,128);

    }

    @Override
    public void resetMap() {
        monsterlevel++;
        monsters= new ArrayList<>();
        link.setPosition(new Position(185,288));
        cameraIntialPosition= new Position(64,128);
        monsters.add(new Rat(250, 140,monstersLevel));
    }
}
