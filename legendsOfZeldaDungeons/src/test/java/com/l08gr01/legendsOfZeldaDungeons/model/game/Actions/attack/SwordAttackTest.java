package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack;

import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Fighter;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;
import com.l08gr01.legendsOfZeldaDungeons.sprites.LinkSprites;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class SwordAttackTest {
    private SwordAttack swordAttack;
    private Arena arena;
    @BeforeEach
    public void setUp(){

        LinkSprites linkSprites= new LinkSprites();
        ArrayList<Hitbox> temp= new ArrayList<>();
        Link link= new Link(20,20);
        temp.add(new Hitbox(10,10,link.getPosition(),new Position(0,0)));
        temp.add(new Hitbox(10,10,link.getPosition(),new Position(0,0)));
        temp.add(new Hitbox(10,10,link.getPosition(),new Position(0,0)));
        temp.add(new Hitbox(10,10,link.getPosition(),new Position(0,0)));
        swordAttack= new SwordAttack(link,linkSprites.getAttackSprites(),1,10,temp);
        arena= Mockito.mock(Arena.class);
        ArrayList<Monster> m= new ArrayList<>();
        m.add(new Fighter(15,15,1));
        Mockito.when(arena.getMonsters()).thenReturn(m);
        Mockito.when(arena.getLink()).thenReturn(link);
    }

    @Test
    public void stepTestHits(){
        Assertions.assertEquals(40,arena.getMonsters().get(0).getHealth());
        swordAttack.step(arena);
        Assertions.assertEquals(38,arena.getMonsters().get(0).getHealth());
        Assertions.assertEquals(160,arena.getLink().getHealth());
        arena.getMonsters().get(0).getAttack().setAttackDown();
        arena.getMonsters().get(0).getAttack().step(arena);
        Assertions.assertEquals(140,arena.getLink().getHealth());

    }
    @Test
    public void stepTestFails(){
        arena.getLink().setPosition(new Position(150,150));
        Assertions.assertEquals(40,arena.getMonsters().get(0).getHealth());
        swordAttack.step(arena);
        Assertions.assertEquals(40,arena.getMonsters().get(0).getHealth());
        Assertions.assertEquals(160,arena.getLink().getHealth());
        arena.getMonsters().get(0).getAttack().setAttackDown();
        arena.getMonsters().get(0).getAttack().step(arena);
        Assertions.assertEquals(160,arena.getLink().getHealth());

    }
}
