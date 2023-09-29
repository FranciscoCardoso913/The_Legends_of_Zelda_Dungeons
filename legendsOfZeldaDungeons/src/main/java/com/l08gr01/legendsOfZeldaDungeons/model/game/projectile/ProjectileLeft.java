package com.l08gr01.legendsOfZeldaDungeons.model.game.projectile;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;

public class ProjectileLeft extends Projectile {
    public ProjectileLeft(Image sprite,Position position,int speed,int damage, int widht,int height) {
        super( sprite, position, speed, damage,widht,height);
    }

    @Override
    public void updatePosition() {
        Position topLeftCorner = hitbox.getPosition();
        topLeftCorner.setPosition(new Position(topLeftCorner.getX() - speed, topLeftCorner.getY()));
    }

    @Override
    public Hitbox getNextPosition() {
        return new Hitbox(getHitbox().getHeight(),getHitbox().getWidth(),new Position(hitbox.getHitboxPosition().getX()-speed,getHitbox().getHitboxPosition().getY()),new Position(0,0));
    }
}
