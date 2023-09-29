package com.l08gr01.legendsOfZeldaDungeons.model;

import java.util.Objects;


public class Hitbox {
    private final int height;
    private final int width;
    private Position topLeftCorner;

    private Position translation;

    public Hitbox(int height, int width, Position topLeftCorner,Position translation) {
        this.height = height;
        this.width = width;
        this.topLeftCorner = topLeftCorner;
        this.translation= translation;
    }

    public Hitbox getShortenedHitbox(double percentage) {
        Position newTranslation = new Position(translation.getX() + (int) ((1-percentage)*width)/2, translation.getY() + (int) ((1-percentage)*height)/2);
        return new Hitbox((int) (percentage*height), (int) (percentage*width), topLeftCorner, newTranslation);
    }

    public Position getHitboxPosition() {
        return topLeftCorner.translate(translation);
    }

    public Position getBottomRightCorner() {
        Position hitboxPosition = topLeftCorner.translate(translation);
        return new Position(hitboxPosition.getX() + this.width, hitboxPosition.getY() + this.height);
    }

    public boolean collidesWith(Hitbox otherHitbox) {
        int thisTopWall = this.getHitboxPosition().getY();
        int thisBotWall = this.getBottomRightCorner().getY();
        int thisLeftWall = this.getHitboxPosition().getX();
        int thisRightWall = this.getBottomRightCorner().getX();
        int otherTopWall = otherHitbox.getHitboxPosition().getY();
        int otherBotWall = otherHitbox.getBottomRightCorner().getY();
        int otherLeftWall = otherHitbox.getHitboxPosition().getX();
        int otherRightWall = otherHitbox.getBottomRightCorner().getX();

        boolean hasVerticalWallIn = isBetween(thisLeftWall, thisRightWall, otherLeftWall) || isBetween(otherLeftWall, otherRightWall, thisLeftWall);
        boolean hasHorizontalWallIn = isBetween(thisTopWall, thisBotWall, otherTopWall) || isBetween(otherTopWall, otherBotWall, thisTopWall);

        return hasVerticalWallIn && hasHorizontalWallIn;
    }

    protected boolean isBetween(int leftBound, int rightBound, int target) {
        return leftBound <= target && target <= rightBound;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Position getHitboxCenter(){
        Position hitboxTopLeftCorner = topLeftCorner.translate(translation);
        return new Position(hitboxTopLeftCorner.getX()+width/2,hitboxTopLeftCorner.getY()+height/2);
    }

    public Position getTranslation() {
        return translation;
    }
    public Position getPosition(){return this.topLeftCorner;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hitbox)) return false;
        Hitbox hitbox = (Hitbox) o;
        return height == hitbox.height && width == hitbox.width && Objects.equals(topLeftCorner, hitbox.topLeftCorner) && Objects.equals(translation, hitbox.translation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width, topLeftCorner, translation);
    }
}
