package com.l08gr01.legendsOfZeldaDungeons.view.game.HUD;

import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Camera;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Fighter;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class MonsterHealthHudTest {
    MonsterHud monsterHud;
    GUI gui;
    Camera camera;
    Monster monster;

    @BeforeEach
    void setup(){
        monsterHud = new MonsterHud(20);
        gui = Mockito.mock(GUI.class);
        camera = Mockito.mock(Camera.class);
        monster = new Fighter(0,0,1);

        Mockito.when(camera.getCameraPositon()).thenReturn(new Position(0,0));
    }

    @Test
    void update() throws IOException {
        monsterHud.update(monster, gui, camera);

        Assertions.assertEquals(20,monsterHud.healthBar);

    }
}
