package com.l08gr01.legendsOfZeldaDungeons.model.game.monster;

import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.Attack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.Move;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy.AttackStrategy;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy.NullAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy.MoveStrategy;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy.RandomMovement;
import com.l08gr01.legendsOfZeldaDungeons.sprites.RatSprites;
import java.util.ArrayList;

public class Rat extends Monster{
    public Rat(int x, int y, int level) {
        super(x, y, level, 10);
        velocity=1;
        RatSprites sprites= Database.getDatabase().getRatSprites();
        hitbox = new Hitbox(12, 12, position,new Position(0,0));
        move= new Move(this,sprites,2);
        move.setCurrentMovingState(move.getCharacterMovingUp());
        currentAction=move;

        attack = new Attack(this, buildDummySpriteArrayList(), 0, 0, buildDummyHitboxes());

        setHealth(10*monstersLevel);
        maxHealth=10*monstersLevel;


    }

    @Override
    protected MoveStrategy buildMoveStrategy() {
        return new RandomMovement(this);
    }

    @Override
    protected AttackStrategy buildAttackStrategy() {
        return new NullAttack();
    }

    private ArrayList<ArrayList<Image>> buildDummySpriteArrayList() {
        ArrayList<ArrayList<Image>> dummyMatrix = new ArrayList<>();
        dummyMatrix.add(new ArrayList<>());
        dummyMatrix.add(new ArrayList<>());
        dummyMatrix.add(new ArrayList<>());
        dummyMatrix.add(new ArrayList<>());
        return dummyMatrix;
    }

    private ArrayList<Hitbox> buildDummyHitboxes() {
        ArrayList<Hitbox> dummyHitboxes = new ArrayList<>();
        dummyHitboxes.add(new Hitbox(0, 0, new Position(0, 0), new Position(0, 0)));
        dummyHitboxes.add(new Hitbox(0, 0, new Position(0, 0), new Position(0, 0)));
        dummyHitboxes.add(new Hitbox(0, 0, new Position(0, 0), new Position(0, 0)));
        dummyHitboxes.add(new Hitbox(0, 0, new Position(0, 0), new Position(0, 0)));

        return dummyHitboxes;
    }

}
