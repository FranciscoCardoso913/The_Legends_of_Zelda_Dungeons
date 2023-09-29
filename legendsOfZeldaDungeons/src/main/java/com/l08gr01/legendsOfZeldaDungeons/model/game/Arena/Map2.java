package com.l08gr01.legendsOfZeldaDungeons.model.game.Arena;

import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Fighter;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Rat;

import java.io.IOException;
import java.util.ArrayList;

public class Map2 extends Map{
    public Map2(Link link,int monstersLevel) throws IOException {
        super(link,monstersLevel);
        link.setPosition(new Position(34,152));
        loadMap(2);
        cameraIntialPosition= new Position(0,28);

    }

    @Override
    public void resetMap() {
        monsterlevel++;
        link.setPosition(new Position(34,152));
        cameraIntialPosition= new Position(0,28);
        monsters= new ArrayList<>();
        monsters.add(new Rat(150, 40,monstersLevel));
        monsters.add(new Rat(250, 40,monstersLevel));
        monsters.add(new Fighter(350, 150,monstersLevel));


    }
}
