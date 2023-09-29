package com.l08gr01.legendsOfZeldaDungeons.controller.menu;
import com.l08gr01.legendsOfZeldaDungeons.Game;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.model.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuControllerTest {
    MenuController menuController;
    Menu menu;
    Game game;
    long time;

    @BeforeEach
    void setUp(){
        game = Mockito.mock(Game.class);
        time = System.currentTimeMillis();
        menu = new Menu();
        menuController = new MenuController(menu);
    }

    @Test
    void testingStepNextEntryWithNextButton() throws IOException {
        int initialEntry = menu.getCurrEntry();
        menuController.step(game, GUI.ACTION.DOWN, time);

        assertEquals(initialEntry + 1, menu.getCurrEntry());
    }

    @Test
    void testingStepPrevEntryWithNextButton() throws IOException {
        menuController.step(game, GUI.ACTION.DOWN, time);

        int initialEntry = menu.getCurrEntry();
        menuController.step(game, GUI.ACTION.UP, time);

        assertEquals(initialEntry - 1, menu.getCurrEntry());
    }

    @Test
    void testingStepPrevEntryWithoutPrevButton() throws IOException {
        while(menu.getCurrEntry() > 0){
            menuController.step(game, GUI.ACTION.UP, time);
        }
        menuController.step(game, GUI.ACTION.UP, time);
        assertEquals(menu.getEntries().size() - 1, menu.getCurrEntry());
    }


    @Test
    void testingStepSelect() throws IOException {

        MenuController spyMenuController1 = Mockito.spy(menuController);
        spyMenuController1.step(game, GUI.ACTION.SELECT, time);
        Mockito.verify(spyMenuController1, Mockito.times(1)).getModel();

        menuController.step(game, GUI.ACTION.DOWN,time);

        MenuController spyMenuController2 = Mockito.spy(menuController);
        spyMenuController2.step(game, GUI.ACTION.SELECT,time);
        Mockito.verify(spyMenuController2, Mockito.times(3)).getModel();

        Mockito.verify(game).setState(null);

    }
}