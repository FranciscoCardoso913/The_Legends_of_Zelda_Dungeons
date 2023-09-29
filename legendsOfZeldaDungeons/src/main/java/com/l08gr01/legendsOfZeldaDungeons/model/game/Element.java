package com.l08gr01.legendsOfZeldaDungeons.model.game;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Rat;

public abstract class Element implements Comparable<Element> {

    public abstract Image getSprite();
    public abstract Position getPosition();
    public abstract Position getHitboxPosition();

    @Override public int compareTo(Element o) {
        if(this instanceof Rat) return -1;
        if (this.getPosition().getY() > o.getPosition().getY()) return 1;
        else if (this.getPosition().getY() < o.getPosition().getY()) return -1;
        else {
            if (this == o) return 0;
        }
        return 1;
    }


}
