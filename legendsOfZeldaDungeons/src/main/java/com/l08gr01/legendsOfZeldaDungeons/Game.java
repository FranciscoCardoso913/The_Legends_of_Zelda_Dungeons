package com.l08gr01.legendsOfZeldaDungeons;

import com.l08gr01.legendsOfZeldaDungeons.gui.LanternaGUI;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import com.l08gr01.legendsOfZeldaDungeons.states.MenuState;
import com.l08gr01.legendsOfZeldaDungeons.states.State;
import com.l08gr01.legendsOfZeldaDungeons.model.Menu;

public class Game {


        private final LanternaGUI gui;
        private State state;
        private int width;
        private int height;


        public Game() throws FontFormatException, IOException, URISyntaxException {
            this.gui = new LanternaGUI(288, 224);
            this.state= new MenuState(new Menu());
            width=288;
            height=224;
        }

        public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
            new Game().start();
        }

        public void setState(State state) {
        this.state = state;
    }

        private void start() throws IOException {
            int FPS = 20;
            int frameTime = 1000 / FPS;
            Database.getDatabase();
            while (this.state != null) {
                long startTime = System.currentTimeMillis();
                gui.refresh();

                state.step(this, gui, startTime);

                long elapsedTime = System.currentTimeMillis() - startTime;
                long sleepTime = frameTime - elapsedTime;

                try {
                    if (sleepTime > 0) Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                }
            }

            gui.close();
        }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}

