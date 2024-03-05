package com.l08gr01.legendsOfZeldaDungeons.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Character;

public class CharacterTest {
    private Character character;

    @BeforeEach
    public void createPosition() {
        character = new Character(1, 2);
    }

    @Test
    public void getPosition() {
        Position position = new Position(1,2);

        Assertions.assertEquals(character.getPosition(), position);
    }

    @Test
    public void setPosition() {
        character.setPosition(new Position(2, 3));
        Position position = new Position(2,3);

        Assertions.assertEquals(character.getPosition(), position);
    }

    @Test
    public void switchActions(){
        Assertions.assertEquals(character.getMover(),character.getCharacterAction());
        character.setCharacterAction(character.getAttack());
        Assertions.assertEquals(character.getAttack(),character.getCharacterAction());
    }

    @Test
    public void increaseHealth(){
        int temp= character.getHealth();
        character.increaseHealth(20);
        Assertions.assertEquals(temp+20,character.getHealth());
    }




}
