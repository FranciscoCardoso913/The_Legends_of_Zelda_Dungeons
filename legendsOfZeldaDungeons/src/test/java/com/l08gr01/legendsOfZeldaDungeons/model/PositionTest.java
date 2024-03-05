package com.l08gr01.legendsOfZeldaDungeons.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {
    private Position position;

    @BeforeEach
    public void createPosition() {
        position = new Position(1, 2);
    }

    @Test
    public void getX() {
        Assertions.assertEquals(1, position.getX());
    }

    @Test
    public void getY() {
        Assertions.assertEquals(2, position.getY());
    }

    @Test
    public void setX() {
        position.setX(3);
        Assertions.assertEquals(3, position.getX());
    }

    @Test
    public void setY() {
        position.setY(3);
        Assertions.assertEquals(3, position.getY());
    }

    @Test
    public void equals() {
        Position anotherPosition = new Position(1, 2);
        Assertions.assertEquals(position, anotherPosition);
        Assertions.assertEquals(position, position);
    }

    @Test
    public void notEquals() {
        Integer anInteger = 1;
        Assertions.assertNotEquals(position, anInteger);
    }

    @Test
    public void differentPositions(){
        Position position1 = new Position(1,1);
        Assertions.assertNotEquals(position1, position);
    }

    @Test
    public void getDistance() {
        Position position1 = new Position(1, 1);
        Position position2 = new Position(4, 5);
        double expected = 5.0;

        Assertions.assertEquals(expected, position1.getDistance(position2));
    }

    @Test
    public void setPosition() {
        Position position1 = new Position(0, 0);
        position1.setPosition(position);

        Assertions.assertEquals(position1, position);
    }

    @Test
    public void translate(){
        Position position1 = new Position(1,1);
        Position position2 = position.translate(position1);

        Assertions.assertEquals(position2, new Position(2,3));
    }

    @Test
    public void getLeft(){
        Position position1 = position.getLeft();

        Assertions.assertEquals(position1, new Position(0,2));
    }

    @Test
    public void getRight(){
        Position position1 = position.getRight();

        Assertions.assertEquals(position1, new Position(2,2));
    }

    @Test
    public void getUp(){
        Position position1 = position.getUp();

        Assertions.assertEquals(position1, new Position(1,1));
    }

    @Test
    public void getDown(){
        Position position1 = position.getDown();

        Assertions.assertEquals(position1, new Position(1,3));
    }
}
