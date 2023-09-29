package com.l08gr01.legendsOfZeldaDungeons.model.game;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.Action;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.Move;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.Attack;

public class Character extends Element {
        protected Move move;

        protected Attack attack;

        protected Action currentAction;

        protected Position position;

        protected Hitbox hitbox;

        protected int velocity;
        protected int health;
        protected int maxHealth;

        public Character(int x, int y){
                position= new Position(x,y);
        }



        public Position getCenterPoint(){
                return hitbox.getHitboxCenter();
        }

        @Override
        public Position getPosition() {
                return position;
        }

        @Override
        public Position getHitboxPosition() {
                return hitbox.getHitboxPosition();
        }

        public void setPosition(Position position_) {
                position.setPosition(position_);
        }

        public int getVelocity(){
                return velocity;
        }

        @Override
        public Image getSprite(){
                return currentAction.getSprite();
        }

        public  void setCharacterAction(Action action){
                currentAction=action;
        }

        public int getWidth(){
                return this.hitbox.getWidth();
        }

        public int getHeight(){
                return this.hitbox.getHeight();
        }

        public Attack getAttack(){ return attack;}

        public int getHealth() {
                return health;
        }

        public Action getCharacterAction() {
                return currentAction;
        }

        public void setHealth(int health) {
                this.health = health;
        }

        public Hitbox getHitbox() {
                return hitbox;
        }

        public Move getMover() {
                return move;
        }

        public void decreaseHealth(int damage) {
                this.health -= damage;
        }

        public void increaseHealth(int recovery) {
                this.health+=recovery;
        }

        public int getMaxHealth() {
                return maxHealth;
        }


}
