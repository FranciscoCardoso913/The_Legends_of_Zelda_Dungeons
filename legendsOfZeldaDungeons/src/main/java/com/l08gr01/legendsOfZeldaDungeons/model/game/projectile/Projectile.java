package com.l08gr01.legendsOfZeldaDungeons.model.game.projectile;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Element;

public abstract class Projectile extends Element {
    private Image sprite;
    protected Hitbox hitbox;
    protected final int speed;
    private final int damage;
    private boolean foundTarget;

    public Projectile(Image sprite, Position position, int speed, int damage, int width, int height) {
        this.sprite = sprite; // Sprite will be fetched from database
        foundTarget=false;
        this.hitbox=new Hitbox(height,width,position,new Position(0,0));
        this.speed = speed;
        this.damage = damage;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    @Override
    public Image getSprite() {
        return sprite;
    }

    public int getDamage() {
        return damage;
    }

    public abstract void updatePosition();

    @Override
    public Position getPosition() {
        return hitbox.getPosition();
    }

    @Override
    public Position getHitboxPosition() {
        return hitbox.getHitboxPosition();
    }

    public void destroyed(){
        foundTarget=true;
    }

    public boolean isFoundTarget() {
        return foundTarget;
    }
    public  abstract Hitbox getNextPosition();
}
