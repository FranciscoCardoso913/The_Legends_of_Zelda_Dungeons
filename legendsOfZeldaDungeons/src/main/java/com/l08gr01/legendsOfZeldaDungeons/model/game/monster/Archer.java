package com.l08gr01.legendsOfZeldaDungeons.model.game.monster;

import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.Attack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.BowAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.Move;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy.AlignedAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy.AttackStrategy;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy.AlignStrategy;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy.MoveStrategy;
import com.l08gr01.legendsOfZeldaDungeons.sprites.ArcherSprites;

import java.util.ArrayList;

public class Archer extends Monster {
    private Attack bowAttack;

    public Archer(int x, int y,int level) {
        super(x, y, level, 50);
        velocity = 1;
        ArcherSprites sprites = Database.getDatabase().getArcherSprites();
        hitbox = new Hitbox(24, 19, position, new Position(13, 9));
        move = new Move(this, sprites, 2);
        move.setCurrentMovingState(move.getCharacterMovingUp());
        currentAction = move;
        setHealth(30*monstersLevel);
        maxHealth=30*monstersLevel;
        bowAttack= new BowAttack(this, sprites.getAttackSprites(),2,10,createArcherBow());
        attack = bowAttack;
    }

    @Override
    protected AttackStrategy buildAttackStrategy() {
        return new AlignedAttack(this, 176);
    }

    @Override
    protected MoveStrategy buildMoveStrategy() {
        return new AlignStrategy(this);
    }

    public ArrayList<Hitbox> createArcherBow(){
        ArrayList<Hitbox> hitboxes= new ArrayList<>();
        hitboxes.add(new Hitbox(15,5,position,new Position(19,-9)));
        hitboxes.add(new Hitbox(15,5,position,new Position(19,35)));
        hitboxes.add(new Hitbox(5,15,position,new Position(-6,19)));
        hitboxes.add(new Hitbox(5,15,position,new Position(37,19)));
        return hitboxes;
    }
}
