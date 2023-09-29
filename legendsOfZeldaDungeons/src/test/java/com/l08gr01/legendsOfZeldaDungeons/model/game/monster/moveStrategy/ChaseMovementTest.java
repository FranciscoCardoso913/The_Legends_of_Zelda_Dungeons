package com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy;

import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.Attack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.Move;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;

public class ChaseMovementTest {
    private Arena arena;
    private Link link;
    private Monster monster;
    private Attack attack;
    private Move mover;
    private Hitbox linkHitbox;
    private ChaseMovement chaseMovement;

    @BeforeEach
    public void setup() {
        arena = Mockito.mock(Arena.class);
        link = Mockito.mock(Link.class);
        monster = Mockito.mock(Monster.class);
        attack = Mockito.mock(Attack.class);
        mover = Mockito.mock(Move.class);
        linkHitbox = Mockito.mock(Hitbox.class);
        chaseMovement = new ChaseMovement(monster);

        Mockito.when(monster.getAttack()).thenReturn(attack);
        Mockito.when(arena.getLink()).thenReturn(link);
        Mockito.when(link.getHitbox()).thenReturn(linkHitbox);
        Mockito.doAnswer((i) -> {
            return mover;
        }).when(monster).getMover();
        Mockito.when(attack.getAttackHitbox()).thenReturn(new Hitbox(20, 20, new Position(100, 100), new Position(0, 0)));
        Mockito.when(linkHitbox.getShortenedHitbox(Mockito.anyDouble())).thenReturn(new Hitbox(30, 30, new Position(0, 0), new Position(0, 0)));
    }

    public void setMonsterPosition(Position pos) {
        Mockito.when(monster.getCenterPoint()).thenReturn(pos);
    }

    public void setLinkPosition(Position pos) {
        Mockito.when(link.getCenterPoint()).thenReturn(pos);
    }

    public ArrayList<Position> createTooClosePositions() {
        ArrayList<Position> farAway = new ArrayList<>();
        farAway.add(new Position(300, 316));
        farAway.add(new Position(316, 300));
        farAway.add(new Position(300, 284));
        farAway.add(new Position(284, 300));
        return farAway;
    }

    @Test
    public void testTooClose() {
        Position expected = new Position(1, 1);
        ArrayList<Position> tooClosePositions = createTooClosePositions();
        setLinkPosition(new Position(300, 300));

        setMonsterPosition(tooClosePositions.get(0));
        Assertions.assertTrue(chaseMovement.tooClose(link));
        Assertions.assertEquals(expected, chaseMovement.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingUp();
        Mockito.verify(mover, Mockito.times(1)).setDefault();

        setMonsterPosition(tooClosePositions.get(1));
        Assertions.assertTrue(chaseMovement.tooClose(link));
        Assertions.assertEquals(expected, chaseMovement.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingLeft();
        Mockito.verify(mover, Mockito.times(2)).setDefault();

        setMonsterPosition(tooClosePositions.get(2));
        Assertions.assertTrue(chaseMovement.tooClose(link));
        Assertions.assertEquals(expected, chaseMovement.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingDown();
        Mockito.verify(mover, Mockito.times(3)).setDefault();

        setMonsterPosition(tooClosePositions.get(3));
        Assertions.assertTrue(chaseMovement.tooClose(link));
        Assertions.assertEquals(expected, chaseMovement.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingRight();
        Mockito.verify(mover, Mockito.times(4)).setDefault();

        setMonsterPosition(new Position(500, 300));
        Assertions.assertFalse(chaseMovement.tooClose(link));
    }

    @Test
    public void farAway() {
        setLinkPosition(new Position(600, 300));
        setMonsterPosition(new Position(300, 300));

        Assertions.assertTrue(chaseMovement.tooFarAway(link));
        Assertions.assertEquals(new Position(1, 1), chaseMovement.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).setDefault();

        setMonsterPosition(new Position(500, 300));
        Assertions.assertFalse(chaseMovement.tooFarAway(link));
    }

    @Test
    public void inRangeToAttack() {
        Mockito.when(linkHitbox.getShortenedHitbox(Mockito.anyDouble())).thenReturn(new Hitbox(30, 30, new Position(110, 110), new Position(0, 0)));
        Mockito.when(attack.getAttackHitbox()).thenReturn(new Hitbox(20, 20, new Position(100, 100), new Position(0, 0)));
        setMonsterPosition(new Position(0, 0));
        setLinkPosition(new Position(0, 0));

        Assertions.assertEquals(new Position(1, 1), chaseMovement.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).setDefault();
    }

    @Test
    public void approachHorizontally() {
        Hitbox monsterHitbox = Mockito.mock(Hitbox.class);

        setLinkPosition(new Position(300, 300));
        setMonsterPosition(new Position(200, 300));

        Mockito.when(monster.getPosition()).thenReturn(new Position(1, 1));
        Mockito.when(monster.getHitbox()).thenReturn(monsterHitbox);

        Mockito.when(monsterHitbox.getHitboxCenter()).thenReturn(new Position(200, 300));
        Mockito.when(linkHitbox.getHitboxCenter()).thenReturn(new Position(300, 300));

        Assertions.assertEquals(new Position(2, 1), chaseMovement.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingRight();

        setLinkPosition(new Position(300, 300));
        setMonsterPosition(new Position(400, 300));
        Mockito.when(monsterHitbox.getHitboxCenter()).thenReturn(new Position(400, 300));
        Mockito.when(linkHitbox.getHitboxCenter()).thenReturn(new Position(300, 300));

        Assertions.assertEquals(new Position(0, 1), chaseMovement.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingLeft();
    }

    @Test
    public void approachVertically() {
        Hitbox monsterHitbox = Mockito.mock(Hitbox.class);

        setLinkPosition(new Position(300, 300));
        setMonsterPosition(new Position(200, 301));
        Mockito.when(monster.getPosition()).thenReturn(new Position(1, 1));
        Mockito.when(monster.getHitbox()).thenReturn(monsterHitbox);

        Mockito.when(monsterHitbox.getHitboxCenter()).thenReturn(new Position(200, 301));
        Mockito.when(linkHitbox.getHitboxCenter()).thenReturn(new Position(300, 300));

        Assertions.assertEquals(new Position(1, 0), chaseMovement.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingUp();

        setLinkPosition(new Position(300, 300));
        setMonsterPosition(new Position(400, 299));
        Mockito.when(monsterHitbox.getHitboxCenter()).thenReturn(new Position(400, 299));
        Mockito.when(linkHitbox.getHitboxCenter()).thenReturn(new Position(300, 300));

        Assertions.assertEquals(new Position(1, 2), chaseMovement.getNextPosition(0, new Position(1, 1), arena));
        Mockito.verify(mover, Mockito.times(1)).MovingDown();
    }
}