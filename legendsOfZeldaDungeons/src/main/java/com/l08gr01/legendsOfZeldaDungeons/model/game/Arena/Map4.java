package com.l08gr01.legendsOfZeldaDungeons.model.game.Arena;

import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Archer;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Fighter;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Rat;

import java.io.IOException;
import java.util.ArrayList;

public class Map4 extends Map {
    public Map4(Link link, int monstersLevel) throws IOException {
        super(link, monstersLevel);
        link.setPosition(new Position(350,160));
        loadMap(4);
        cameraIntialPosition= new Position(124,65);
    }

    @Override
    public void resetMap() {
        monsterlevel++;
        link.setPosition(new Position(350,160));
        cameraIntialPosition= new Position(124,65);
        monsters= new ArrayList<>();
        monsters.add(new Rat(150, 140,monstersLevel));
        monsters.add(new Rat(250, 140,monstersLevel));
        monsters.add(new Rat(126, 238,monstersLevel));
        monsters.add(new Rat(30, 238,monstersLevel));
        monsters.add(new Fighter(350, 250,monstersLevel));
        monsters.add(new Fighter(350, 250,monstersLevel));
        monsters.add(new Fighter(126, 110,monstersLevel));
        monsters.add(new Archer(50,50,monstersLevel));
        monsters.add(new Archer(250,250,monstersLevel));
    }
}
