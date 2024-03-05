package com.l08gr01.legendsOfZeldaDungeons.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class LanternaGUI implements GUI {
        private final Screen screen;
        TextGraphics tg ;

        public LanternaGUI(Screen screen) {
            this.screen = screen;
            tg = screen.newTextGraphics();
        }

        public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
            AWTTerminalFontConfiguration fontConfig = loadSquareFont();
            Terminal terminal = createTerminal(width, height, fontConfig);
            this.screen = createScreen(terminal);
            tg = screen.newTextGraphics();
        }

        private Screen createScreen(Terminal terminal) throws IOException {
            final Screen screen;
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            return screen;
        }

        private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
            TerminalSize terminalSize = new TerminalSize(width, height + 1);

            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            terminalFactory.setForceAWTOverSwing(true);
            terminalFactory.setTerminalEmulatorTitle("The Legends Of Zelda Dungeons");
            terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
            Terminal terminal = terminalFactory.createTerminal();
            return terminal;
        }

        private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
            URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
            File fontFile = new File(resource.toURI());
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            Font loadedFont = font.deriveFont(Font.PLAIN, 3);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
            return fontConfig;
        }

        @Override
        public ACTION getNextAction() throws IOException {
            KeyStroke keyStroke = screen.pollInput();
            if (keyStroke == null) return ACTION.NONE;
            KeyStroke prev=null;
            while( keyStroke!= null){
                prev= keyStroke;
                keyStroke=screen.pollInput();
            }
            keyStroke=prev;
            if (keyStroke.getKeyType() == KeyType.EOF) return ACTION.QUIT;
            if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q') return ACTION.QUIT;
            if (keyStroke.getKeyType() == KeyType.ArrowUp || (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'w')) return ACTION.UP;
            if (keyStroke.getKeyType() == KeyType.ArrowRight || (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'd')) return ACTION.RIGHT;
            if (keyStroke.getKeyType() == KeyType.ArrowDown || (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 's')) return ACTION.DOWN;
            if (keyStroke.getKeyType() == KeyType.ArrowLeft || (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'a')) return ACTION.LEFT;
            if((keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == ' ')) return ACTION.ATTACK;
            if((keyStroke.getKeyType()==KeyType.Character && keyStroke.getCharacter()=='e')) return ACTION.DEFEND;
            if((keyStroke.getKeyType()==KeyType.Character && keyStroke.getCharacter()=='r')) return ACTION.ARROW;
            if (keyStroke.getKeyType() == KeyType.Enter) return ACTION.SELECT;

            return ACTION.NONE;
        }

    @Override
    public void draw(TerminalPosition position,Image image) throws IOException {
        ArrayList<ArrayList<Color>> object;
        object=image.getImage();
        int terminalY=(int)position.getRow();
        int terminalX=(int) position.getColumn();
        for(ArrayList<Color> line: object){
            terminalX=(int) position.getColumn();
            for(Color color:line){
                Color nullColor= image.getNullColor();
                if(!nullColor.equals(color)){
                    tg.setBackgroundColor( new TextColor.RGB(color.getRed(),color.getGreen(),color.getBlue()));
                    tg.putString(terminalX,terminalY," ");
                }
                terminalX+=1;
            }
            terminalY+=1;
        }
        }

        @Override
        public void drawRetangle(Position position, int width,int height, Color color){
            TerminalPosition terminalPosition= new TerminalPosition(position.getX(), position.getY());
            tg.setBackgroundColor(new TextColor.RGB(color.getRed(),color.getGreen(),color.getBlue()));
            tg.fillRectangle(terminalPosition,new TerminalSize(width,height), ' ');
        }

        @Override
        public void clear() {
            screen.clear();
        }

        @Override
        public void refresh() throws IOException {
            screen.refresh();
        }

        @Override
        public void close() throws IOException {
            screen.close();
        }
}



