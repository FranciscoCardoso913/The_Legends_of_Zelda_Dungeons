package com.l08gr01.legendsOfZeldaDungeons.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HitboxTest {
    private Hitbox createDummyHitbox() {
        return new Hitbox(0, 0, new Position(0, 0), new Position(0, 0));
    }

    @Test
    public void getDimensions() {
        Hitbox toTest = new Hitbox(10, 20, new Position(0, 0), new Position(0, 0));

        Assertions.assertEquals(10, toTest.getHeight());
        Assertions.assertEquals(20, toTest.getWidth());
    }

    @Test
    public void getRealTopLeftCorner() {
        Hitbox toTest = new Hitbox(10, 10, new Position(0, 0), new Position(0, 1));

        Assertions.assertEquals(new Position(0, 0), toTest.getPosition());
    }

    @Test
    public void getTranslation() {
        Hitbox toTest = new Hitbox(10, 10, new Position(0, 1), new Position(0, 0));

        Assertions.assertEquals(new Position(0, 0), toTest.getTranslation());
    }

    @Test
    public void getBottomRightCorner() {
        Position pos = Mockito.mock(Position.class);
        Mockito.when(pos.translate(Mockito.any(Position.class))).thenReturn(new Position(4, 6));
        Hitbox toTest = new Hitbox(10, 10, pos, new Position(0, 0));

        Position bottomRightCorner = new Position(14, 16);

        Assertions.assertEquals(bottomRightCorner, toTest.getBottomRightCorner());
    }

    @Test
    public void getHitboxCenter() {
        Position pos = Mockito.mock(Position.class);
        Mockito.when(pos.translate(Mockito.any(Position.class))).thenReturn(new Position(4, 6));
        Hitbox toTest = new Hitbox(10, 10, pos, new Position(0, 0));

        Position center = new Position(9, 11);

        Assertions.assertEquals(center, toTest.getHitboxCenter());
    }

    @Test
    public void isBetween() {
        Hitbox dummy = createDummyHitbox();
        int leftBound = -20;
        int rightBound = 20;

        Assertions.assertFalse(dummy.isBetween(leftBound, rightBound, leftBound - 1));
        for (int target = leftBound; target <= rightBound; target++) {
            Assertions.assertTrue(dummy.isBetween(leftBound, rightBound, target), "Failed on value " + target);
        }
        Assertions.assertFalse(dummy.isBetween(leftBound, rightBound, rightBound + 1));
    }

    @Test
    public void hitboxCollision() {
        Hitbox staticHitbox = new Hitbox(10, 10, new Position(10, 10), new Position(0, 0));

        for (int x = 5; x <= 20; x++) {
            Assertions.assertFalse(staticHitbox.collidesWith(new Hitbox(5, 5, new Position(x, 4), new Position(0, 0))));
        }

        for (int y = 5; y <= 20; y++) {
            Assertions.assertFalse(staticHitbox.collidesWith(new Hitbox(5, 5, new Position(4, y), new Position(0, 0))));
            for (int x = 5; x <= 20; x++) {
                Assertions.assertTrue(staticHitbox.collidesWith(new Hitbox(5, 5, new Position(x, y), new Position(0, 0))), "Failed on " + x + " " +y);
            }
            Assertions.assertFalse(staticHitbox.collidesWith(new Hitbox(5, 5, new Position(21, y), new Position(0, 0))));
        }

        for (int x = 5; x <= 20; x++) {
            Assertions.assertFalse(staticHitbox.collidesWith(new Hitbox(5, 5, new Position(x, 21), new Position(0, 0))));
        }
    }

    @Test
    public void shortenHitbox() {
        Hitbox hitbox = new Hitbox(16, 16, new Position(4, 4), new Position(6, 6));
        Hitbox ansHitbox = new Hitbox(4, 4, new Position(0, 0), new Position(16, 16));
        Hitbox testHitbox = hitbox.getShortenedHitbox(0.25);

        Assertions.assertEquals(ansHitbox.getHitboxPosition(), testHitbox.getHitboxPosition());
        Assertions.assertEquals(ansHitbox.getHeight(), testHitbox.getHeight());
        Assertions.assertEquals(ansHitbox.getWidth(), testHitbox.getWidth());
    }
}
