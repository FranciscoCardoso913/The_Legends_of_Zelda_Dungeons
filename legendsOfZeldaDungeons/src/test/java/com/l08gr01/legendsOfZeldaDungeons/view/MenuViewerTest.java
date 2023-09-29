package com.l08gr01.legendsOfZeldaDungeons.view;

import com.googlecode.lanterna.TerminalPosition;
import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.Game;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

public class MenuViewerTest {

    MenuViewer menuViewer;
    Menu menu;
    Game game;
    GUI gui;

    @BeforeEach
    void setup(){
        menu = Mockito.mock(Menu.class);
        menuViewer = new MenuViewer(menu);
        game = Mockito.mock(Game.class);
        gui = Mockito.spy(GUI.class);
        Mockito.when(menu.getCurrEntry()).thenReturn(0);
        Mockito.when(menu.getEntries()).thenReturn(Arrays.asList("/images/menu/start_selected.png", "/images/menu/exit_selected.png"));
    }

    @Test
    void drawElements() throws IOException {

        menuViewer.drawElements(game,gui);

        Mockito.verify(gui).draw(TerminalPosition.TOP_LEFT_CORNER,new Image(menu.getEntries().get(0)));
        Mockito.verify(gui).draw(new TerminalPosition(184,153), Database.getDatabase().getNumberSpritesWhite().getNumbers().get(Database.getDatabase().getHighscore()%10));

    }
}
