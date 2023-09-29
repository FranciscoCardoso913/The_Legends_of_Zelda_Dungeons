package com.l08gr01.legendsOfZeldaDungeons.view.game.HUD;

import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Camera;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class LinkHealthHudTest {

    LinkHealthHud linkHealthHud;
    GUI gui;
    Camera camera;

    @BeforeEach
    void setup(){
        linkHealthHud = new LinkHealthHud(10);
        gui = Mockito.mock(GUI.class);
        camera = Mockito.mock(Camera.class);
    }

    @Test
    void update() throws IOException {
        linkHealthHud.update(new Link(0,0),gui,camera);

        Assertions.assertEquals(10,linkHealthHud.getHearts().size());
    }
}
