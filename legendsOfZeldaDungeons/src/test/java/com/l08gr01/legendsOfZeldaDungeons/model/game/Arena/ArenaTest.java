package com.l08gr01.legendsOfZeldaDungeons.model.game.Arena;
import com.l08gr01.legendsOfZeldaDungeons.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;

public class ArenaTest {

    Game game;

    @BeforeEach
    void creatingGame(){
        game = Mockito.mock(Game.class);
    }

    @Test
    void loadMaps() throws IOException {
        Arena arena = new Arena(game);
        arena.loadMaps();
        Assertions.assertEquals(4, arena.getMaps().size());
    }

    @Test
    void loadNextMap1() throws IOException {
        Arena arena = new Arena(game);
        int expected = arena.getCurrLevel() + 1;
        arena.loadNextMap();
        Assertions.assertEquals(expected, arena.getCurrLevel());
        Assertions.assertEquals(Map2.class, arena.getCurrMap().getClass());
    }

    @Test
    void loadNextMap2() throws IOException {
        Arena arena = new Arena(game);
        int expected = 0;
        arena.loadNextMap();
        arena.loadNextMap();
        arena.loadNextMap();
        arena.loadNextMap();
        Assertions.assertEquals(expected, arena.getCurrLevel());
        Assertions.assertEquals(Map1.class, arena.getCurrMap().getClass());
    }
}
