package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack;

import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;
import com.l08gr01.legendsOfZeldaDungeons.model.game.projectile.Projectile;
import com.l08gr01.legendsOfZeldaDungeons.sprites.LinkSprites;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class ShieldAttackTest {
    private ShieldAttack shieldAttack;
    private Arena arena;

    private Link link;
    private Hitbox hitbox;

    private ArrayList<Monster> monsters;
    private ArrayList<Projectile>projectiles;
    @BeforeEach
    public void setUp(){

        LinkSprites linkSprites= new LinkSprites();
        ArrayList<Hitbox> temp= new ArrayList<>();
        link= new Link(20,20);
        hitbox=Mockito.mock(Hitbox.class);
        Monster monster= Mockito.mock(Monster.class);
        Attack attack= Mockito.mock(Attack.class);
        Mockito.when(monster.getAttack()).thenReturn(attack);
        Mockito.when(attack.getAttackHitbox()).thenReturn(hitbox);
        Mockito.when(attack.getDamage()).thenReturn(20);
        Mockito.when(monster.getCharacterAction()).thenReturn(attack);
        Projectile projectile= Mockito.mock(Projectile.class);
        Mockito.when(projectile.getNextPosition()).thenReturn(hitbox);
        temp.add(hitbox);
        temp.add(hitbox);
        temp.add(hitbox);
        temp.add(hitbox);
        shieldAttack= new ShieldAttack(link,linkSprites.getShieldSprites(),1,10,temp);
        arena= Mockito.mock(Arena.class);
        monsters= new ArrayList<>();
         projectiles= new ArrayList<>();
        projectiles.add(projectile);
        monsters.add(monster);
        Mockito.when(arena.getProjectiles()).thenReturn(projectiles);
        Mockito.when(arena.getMonsters()).thenReturn(monsters);
        Mockito.when(arena.getLink()).thenReturn(link);
    }

    @Test
    public void monsterDefendTest(){
        projectiles=new ArrayList<>();
        Mockito.when(hitbox.collidesWith(Mockito.any(Hitbox.class))).thenReturn(true);
        Assertions.assertEquals(160,link.getHealth());
        shieldAttack.step(arena);
       link.decreaseHealth(20);
        Assertions.assertEquals(160,link.getHealth());
    }

    @Test
    public void projectileDefendTest(){
        monsters= new ArrayList<>();
        Mockito.when(hitbox.collidesWith(Mockito.any(Hitbox.class))).thenReturn(true);
        Assertions.assertEquals(160,link.getHealth());
        shieldAttack.step(arena);
        link.decreaseHealth(20);
        Assertions.assertEquals(160,link.getHealth());
    }
    @Test
    public void monsterNotDefendTest(){
        projectiles=new ArrayList<>();
        Mockito.when(hitbox.collidesWith(Mockito.any(Hitbox.class))).thenReturn(false);
        Assertions.assertEquals(160,link.getHealth());
        shieldAttack.step(arena);
        link.decreaseHealth(20);
        Assertions.assertEquals(140,link.getHealth());
    }

    @Test
    public void projectileNotDefendTest(){
        monsters= new ArrayList<>();
        Mockito.when(hitbox.collidesWith(Mockito.any(Hitbox.class))).thenReturn(false);
        Assertions.assertEquals(160,link.getHealth());
        shieldAttack.step(arena);
        link.decreaseHealth(20);
        Assertions.assertEquals(140,link.getHealth());
    }
}
