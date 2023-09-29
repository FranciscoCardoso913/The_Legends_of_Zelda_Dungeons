package com.l08gr01.legendsOfZeldaDungeons.view.game.HUD;

import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class ScoreHudTest {
    ScoreHud scoreHud;
    GUI gui;

    @BeforeEach
    void setup(){
        scoreHud = new ScoreHud();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void update() throws IOException {
        ScoreHud spyScoreHud = Mockito.spy(scoreHud);
        spyScoreHud.update(200,gui);
        scoreHud.update(200, gui);

        Mockito.verify(spyScoreHud).draw(gui);
        Assertions.assertEquals(200, scoreHud.getScore());

        scoreHud.update(0,gui);
        Assertions.assertEquals(0,scoreHud.getScore());
    }
}
