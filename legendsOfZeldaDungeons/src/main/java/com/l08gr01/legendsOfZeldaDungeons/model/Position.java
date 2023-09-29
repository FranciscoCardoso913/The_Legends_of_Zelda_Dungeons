package com.l08gr01.legendsOfZeldaDungeons.model;

import java.lang.Math;
import java.util.Objects;

public class Position {
    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position getLeft() {
        return new Position(x -1, y);
    }

    public Position getRight() {
        return new Position(x + 1, y);
    }

    public Position getUp() {
        return new Position(x, y - 1);
    }

    public Position getDown() {
        return new Position(x, y + 1);
    }

    public double getDistance(Position position) {
        return Math.hypot(position.getX() - this.getX(), position.getY() - this.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(Position position){
        this.x= position.getX();
        this.y= position.getY();
    }
    public Position translate(Position pos){
        return new Position(this.x+pos.getX(),this.y+pos.getY());
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
