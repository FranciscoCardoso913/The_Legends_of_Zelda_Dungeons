package com.l08gr01.legendsOfZeldaDungeons.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;

public class LanternaGUITest {
    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics tg;

    @BeforeEach
    void creatingTerminal() {
        screen = Mockito.mock(Screen.class);
        tg = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(tg);

        gui = new LanternaGUI(screen);
    }
    @Test
    public void testingDraw() throws IOException {
        creatingTerminal();
        Image image= new Image("/images/link.png");
        gui.clear();
        gui.draw(new TerminalPosition(10,10),image);
        gui.refresh();

        gui.close();
        Mockito.verify(tg, Mockito.times(1)).putString(261,10," ");
    }
    @Test
    public void drawRectangle() throws IOException {
        creatingTerminal();
        gui.drawRetangle(new Position(261,10), 1,1, new Color(10,10,10));
        gui.refresh();
        gui.close();
        Mockito.verify(tg, Mockito.times(1)).fillRectangle(new TerminalPosition(261,10),new TerminalSize(1,1), ' ');
    }
}
