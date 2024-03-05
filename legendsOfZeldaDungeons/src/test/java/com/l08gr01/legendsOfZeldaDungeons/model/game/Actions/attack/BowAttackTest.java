package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack;

import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Character;
import com.l08gr01.legendsOfZeldaDungeons.model.game.projectile.*;
import com.l08gr01.legendsOfZeldaDungeons.sprites.LinkSprites;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class BowAttackTest {
    private BowAttack bowAttack;
    private Arena arena;
    @BeforeEach
    public void setUp(){

        LinkSprites linkSprites= new LinkSprites();
        ArrayList<Hitbox> temp= new ArrayList<>();
        Character character= new Character(10,10);
        temp.add(new Hitbox(10,10,character.getPosition(),new Position(0,0)));
        temp.add(new Hitbox(10,10,character.getPosition(),new Position(0,0)));
        temp.add(new Hitbox(10,10,character.getPosition(),new Position(0,0)));
        temp.add(new Hitbox(10,10,character.getPosition(),new Position(0,0)));
        bowAttack= new BowAttack(character,linkSprites.getBowSprites(),1,10,temp);
        arena= Mockito.mock(Arena.class);
        ArrayList<Projectile> projectiles= new ArrayList<>();
        Mockito.when(arena.getProjectiles()).thenReturn(projectiles);

    }

    @Test
    public void upTest(){
       Assertions.assertEquals(0,arena.getProjectiles().size());
       bowAttack.step(arena);
       bowAttack.step(arena);
       bowAttack.step(arena);
       Assertions.assertEquals(1,arena.getProjectiles().size());
       Assertions.assertEquals(ProjectileUp.class,arena.getProjectiles().get(0).getClass());

    }

    @Test
    public void DownTest(){
        bowAttack.setAttackDown();
        Assertions.assertEquals(0,arena.getProjectiles().size());
        bowAttack.step(arena);
        bowAttack.step(arena);
        bowAttack.step(arena);
        Assertions.assertEquals(1,arena.getProjectiles().size());
        Assertions.assertEquals(ProjectileDown.class,arena.getProjectiles().get(0).getClass());

    }

    @Test
    public void leftTest(){
        bowAttack.setAttackLeft();
        Assertions.assertEquals(0,arena.getProjectiles().size());
        bowAttack.step(arena);
        bowAttack.step(arena);
        bowAttack.step(arena);
        Assertions.assertEquals(1,arena.getProjectiles().size());
        Assertions.assertEquals(ProjectileLeft.class,arena.getProjectiles().get(0).getClass());
    }

    @Test
    public void rightTest(){
        bowAttack.setAttackRight();
        Assertions.assertEquals(0,arena.getProjectiles().size());
        bowAttack.step(arena);
        bowAttack.step(arena);
        bowAttack.step(arena);
        Assertions.assertEquals(1,arena.getProjectiles().size());
        Assertions.assertEquals(ProjectileRight.class,arena.getProjectiles().get(0).getClass());
    }

}
