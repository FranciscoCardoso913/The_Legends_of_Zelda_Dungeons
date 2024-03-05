package com.l08gr01.legendsOfZeldaDungeons.model.game.Arena;

import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Archer;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Fighter;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Rat;

import java.io.IOException;
import java.util.ArrayList;

public class Map3 extends Map{
    public Map3(Link link, int monstersLevel) throws IOException {
        super(link, monstersLevel);
        link.setPosition(new Position(195,34));
        loadMap(3);
        cameraIntialPosition= new Position(95,0);
    }

    @Override
    public void resetMap() {
        monsterlevel++;
        link.setPosition(new Position(195,34));
        cameraIntialPosition= new Position(95,0);
        monsters= new ArrayList<>();
        monsters.add(new Rat(150, 140,monstersLevel));
        monsters.add(new Rat(250, 140,monstersLevel));
        monsters.add(new Rat(126, 238,monstersLevel));
        monsters.add(new Fighter(350, 250,monstersLevel));
        monsters.add(new Fighter(150, 250,monstersLevel));
        monsters.add(new Archer(50,50,monstersLevel));

    }
}
