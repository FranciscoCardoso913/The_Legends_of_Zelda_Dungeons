package com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy;

import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.Move;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;

public class AlignedStrategyTest {
    private Arena arena;
    private Link link;
    private Monster monster;
    private AlignStrategy alignStrategy;
    private Move mover;

    @BeforeEach
    public void setup() {
        arena = Mockito.mock(Arena.class);
        link = Mockito.mock(Link.class);
        monster = Mockito.mock(Monster.class);
        alignStrategy = new AlignStrategy(monster);
    }

    public void mockMover() {
        mover = Mockito.mock(Move.class);
        Mockito.doAnswer((i) -> {
            return mover;
        }).when(monster).getMover();
    }

    public ArrayList<Position> createFarAway() {
        ArrayList<Position> farAway = new ArrayList<>();
        farAway.add(new Position(-1, 300));
        farAway.add(new Position(601, 300));
        farAway.add(new Position(300, -1));
        farAway.add(new Position(300, 601));
        return farAway;
    }

    public ArrayList<Position> createNotFarAway() {
        ArrayList<Position> NotFarAway = new ArrayList<>();
        NotFarAway.add(new Position(0, 300));
        NotFarAway.add(new Position(600, 300));
        NotFarAway.add(new Position(300, 0));
        NotFarAway.add(new Position(300, 600));
        return NotFarAway;
    }

    public ArrayList<Position> createNotAlignedPoints() {
        ArrayList<Position> notAligned = new ArrayList<>();
        notAligned.add(new Position(600, 600));
        notAligned.add(new Position(0, 0));
        notAligned.add(new Position(0, 600));
        notAligned.add(new Position(600, 0));
        return notAligned;
    }

    public ArrayList<Position> createAlignedPoints() {
        ArrayList<Position> aligned = new ArrayList<>();
        aligned.add(new Position(0, 300));
        aligned.add(new Position(600, 300));
        aligned.add(new Position(300, 0));
        aligned.add(new Position(300, 600));
        return aligned;
    }

    public ArrayList<Position> createMoveAwayPositions() {
        ArrayList<Position> moveAway = new ArrayList<>();
        moveAway.add(new Position(302, 301));
        moveAway.add(new Position(298, 301));
        moveAway.add(new Position(299, 302));
        moveAway.add(new Position(299, 298));
        moveAway.add(new Position(364, 300));
        return moveAway;
    }

    public ArrayList<Position> createToAlignPosition() {
        ArrayList<Position> toAlign = new ArrayList<>();
        toAlign.add(new Position(221, 220));
        toAlign.add(new Position(379, 220));
        toAlign.add(new Position(220, 221));
        toAlign.add(new Position(220, 379));
        return toAlign;
    }

    @Test
    public void isTooFarAway() {
        Position expected;
        int times = 1;
        mockMover();
        Mockito.when(monster.getCenterPoint()).thenReturn(new Position(300, 300));
        Mockito.when(arena.getLink()).thenReturn(link);
        ArrayList<Position> farAway = createFarAway();
        ArrayList<Position> notFarAway = createNotFarAway();

        for (Position position : farAway) {
            Mockito.when(link.getCenterPoint()).thenReturn(position);
            expected = alignStrategy.getNextPosition(0, new Position(1, 1), arena);
            Assertions.assertEquals(expected, new Position(1, 1));
            Mockito.verify(mover, Mockito.times(times++)).setDefault();
            Assertions.assertTrue(alignStrategy.tooFarAway(link));
        }

        for (Position position : notFarAway) {
            Mockito.when(link.getCenterPoint()).thenReturn(position);
            Assertions.assertFalse(alignStrategy.tooFarAway(link));
        }
    }

    @Test
    public void isAlignedWith() {
        Mockito.when(monster.getCenterPoint()).thenReturn(new Position(300, 300));
        ArrayList<Position> alignedPoints = createAlignedPoints();
        ArrayList<Position> notAlignedPoints = createNotAlignedPoints();

        for (Position position : alignedPoints) {
            Mockito.when(link.getCenterPoint()).thenReturn(position);
            Assertions.assertTrue(alignStrategy.isAlignedWith(link));
        }

        for (Position position : notAlignedPoints) {
            Mockito.when(link.getCenterPoint()).thenReturn(position);
            Assertions.assertFalse(alignStrategy.isAlignedWith(link));
        }
    }

    @Test
    public void moveTowards() {
        Position expected;
        Mockito.when(arena.getLink()).thenReturn(link);
        Mockito.when(monster.getPosition()).thenReturn(new Position(1, 1));
        Mockito.when(link.getCenterPoint()).thenReturn(new Position(300, 300));
        ArrayList<Position> alignedPositions = createAlignedPoints();
        mockMover();

        Mockito.when(monster.getCenterPoint()).thenReturn(alignedPositions.get(0));
        expected = new Position(2, 1);
        Assertions.assertEquals(expected, alignStrategy.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingRight();

        Mockito.when(monster.getCenterPoint()).thenReturn(alignedPositions.get(1));
        expected = new Position(0, 1);
        Assertions.assertEquals(expected, alignStrategy.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingLeft();

        Mockito.when(monster.getCenterPoint()).thenReturn(alignedPositions.get(2));
        expected = new Position(1, 2);
        Assertions.assertEquals(expected, alignStrategy.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingDown();

        Mockito.when(monster.getCenterPoint()).thenReturn(alignedPositions.get(3));
        expected = new Position(1, 0);
        Assertions.assertEquals(expected, alignStrategy.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingUp();
    }

    @Test
    public void moveAway() {
        Position expected;
        Mockito.when(arena.getLink()).thenReturn(link);
        Mockito.when(monster.getPosition()).thenReturn(new Position(1, 1));
        Mockito.when(link.getCenterPoint()).thenReturn(new Position(300, 300));
        ArrayList<Position> moveAwayPositions = createMoveAwayPositions();
        mockMover();

        Mockito.when(monster.getCenterPoint()).thenReturn(moveAwayPositions.get(0));
        expected = new Position(2, 1);
        Assertions.assertEquals(expected, alignStrategy.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingRight();

        Mockito.when(monster.getCenterPoint()).thenReturn(moveAwayPositions.get(1));
        expected = new Position(0, 1);
        Assertions.assertEquals(expected, alignStrategy.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingLeft();

        Mockito.when(monster.getCenterPoint()).thenReturn(moveAwayPositions.get(2));
        expected = new Position(1, 2);
        Assertions.assertEquals(expected, alignStrategy.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingDown();

        Mockito.when(monster.getCenterPoint()).thenReturn(moveAwayPositions.get(3));
        expected = new Position(1, 0);
        Assertions.assertEquals(expected, alignStrategy.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingUp();

        Mockito.when(monster.getCenterPoint()).thenReturn(moveAwayPositions.get(4));
        expected = new Position(2, 1);
        Assertions.assertEquals(expected, alignStrategy.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(2)).MovingRight();
    }

    @Test
    public void alignWithLink() {
        Position expected;
        Mockito.when(arena.getLink()).thenReturn(link);
        Mockito.when(monster.getPosition()).thenReturn(new Position(1, 1));
        Mockito.when(link.getCenterPoint()).thenReturn(new Position(300, 300));
        ArrayList<Position> toAlignPositions = createToAlignPosition();
        mockMover();

        Mockito.when(monster.getCenterPoint()).thenReturn(toAlignPositions.get(0));
        expected = new Position(2, 1);
        Assertions.assertEquals(expected, alignStrategy.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingRight();

        Mockito.when(monster.getCenterPoint()).thenReturn(toAlignPositions.get(1));
        expected = new Position(0, 1);
        Assertions.assertEquals(expected, alignStrategy.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingLeft();

        Mockito.when(monster.getCenterPoint()).thenReturn(toAlignPositions.get(2));
        expected = new Position(1, 2);
        Assertions.assertEquals(expected, alignStrategy.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingDown();

        Mockito.when(monster.getCenterPoint()).thenReturn(toAlignPositions.get(3));
        expected = new Position(1, 0);
        Assertions.assertEquals(expected, alignStrategy.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingUp();
    }
}