package com.l08gr01.legendsOfZeldaDungeons.controller.game;

import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.Game;
import com.l08gr01.legendsOfZeldaDungeons.Sound;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.model.Menu;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;
import com.l08gr01.legendsOfZeldaDungeons.model.game.projectile.Projectile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import java.util.ArrayList;

public class ArenaControllerTest {

    Arena arena;
    Game game;

    @BeforeEach
    void createArena() throws IOException {
        game = Mockito.mock(Game.class);
        arena = new Arena(game);
    }
    @Test
    void incrementScore() throws IOException {
        game = Mockito.mock(Game.class);
        arena = new Arena(game);
        ArenaController arenaController = new ArenaController(arena);

        int maxScore = arena.getMaxScore();
        arena.setScore(0);
        int expected = maxScore - 10;
        arenaController.incrementScore(expected);
        Assertions.assertEquals(expected, arena.getScore());

        arenaController.incrementScore(maxScore);
        Assertions.assertEquals(maxScore, arena.getScore());
    }

    @Test
    void step() throws IOException {
        ArenaController arenaController = new ArenaController(arena);

        ArenaController spyArenaController = Mockito.spy(arenaController);
        spyArenaController.step(game, GUI.ACTION.NONE,0);
        Mockito.verify(spyArenaController).upDate();
        arena.getLink().setHealth(0);
        spyArenaController.step(game, GUI.ACTION.NONE,0);
    }

    @Test
    void upDate(){
        ArenaController arenaController = new ArenaController(arena);
        ArrayList<Monster> monsters = new ArrayList<>();
        ArrayList<Projectile> projectiles = new ArrayList<>();

        Monster monster1 = Mockito.mock(Monster.class);
        Monster monster2 = Mockito.mock(Monster.class);
        Monster monster3 = Mockito.mock(Monster.class);

        Mockito.when(monster1.getHealth()).thenReturn(10);
        Mockito.when(monster2.getHealth()).thenReturn(0);
        Mockito.when(monster3.getHealth()).thenReturn(-10);

        monsters.add(monster1);
        monsters.add(monster2);
        monsters.add(monster3);

        arena.setMonsters(monsters);

        Projectile projectile1 = Mockito.mock(Projectile.class);
        Projectile projectile2 = Mockito.mock(Projectile.class);

        Mockito.when(projectile1.isFoundTarget()).thenReturn(true);
        Mockito.when(projectile2.isFoundTarget()).thenReturn(false);

        projectiles.add(projectile1);
        projectiles.add(projectile2);

        arena.setProjectiles(projectiles);

        arenaController.upDate();

        Assertions.assertEquals(1, arena.getMonsters().size());
        Assertions.assertEquals(monster1, arena.getMonsters().get(0));

        Assertions.assertEquals(1,arena.getProjectiles().size());
        Assertions.assertEquals(projectile2,arena.getProjectiles().get(0));
    }



}