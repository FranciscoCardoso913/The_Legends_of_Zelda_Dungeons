package com.l08gr01.legendsOfZeldaDungeons.model.game;

import com.l08gr01.legendsOfZeldaDungeons.Game;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class CameraTest {
    Game game;

    @BeforeEach
    void createGame(){
        game = Mockito.mock(Game.class);
    }

    @Test
    void update() throws IOException {
        Arena arena = new Arena(game);
        Camera camera = new Camera(new Position(200,200), 288, 224, arena);

        arena.getLink().getMover().setCurrentMovingState(arena.getLink().getMover().getCharacterMovingDown());
        Camera spyCamera = Mockito.spy(camera);
        spyCamera.update();
        Mockito.verify(spyCamera).moveDown();

        arena.getLink().getMover().setCurrentMovingState(arena.getLink().getMover().getCharacterMovingUp());
        spyCamera.update();
        Mockito.verify(spyCamera).moveUp();

        arena.getLink().getMover().setCurrentMovingState(arena.getLink().getMover().getCharacterMovingLeft());
        spyCamera.update();
        Mockito.verify(spyCamera).moveLeft();

        arena.getLink().getMover().setCurrentMovingState(arena.getLink().getMover().getCharacterMovingRight());
        spyCamera.update();
        Mockito.verify(spyCamera).moveRight();
    }

    @Test
    void moveUp() throws IOException{
        Arena arena = new Arena(game);
        Camera camera = new Camera(new Position(200,200), 288, 224, arena);

        Position expected = new Position(200,200-arena.getLink().getVelocity());
        arena.getLink().setPosition(new Position(0,0));
        camera.moveUp();
        Assertions.assertEquals(camera.getCameraPositon(), expected);

        expected = camera.getCameraPositon();
        arena.getLink().setPosition(camera.getCameraPositon());
        camera.moveUp();
        Assertions.assertEquals(camera.getCameraPositon(), expected);
    }

    @Test
    void moveDown() throws IOException{
        Arena arena = new Arena(game);
        Camera camera = new Camera(new Position(0,0), 288, 224, arena);

        Position expected = new Position(0,arena.getLink().getVelocity());
        arena.getLink().setPosition(new Position(200,200));
        camera.moveDown();
        Assertions.assertEquals(camera.getCameraPositon(), expected);

        expected = camera.getCameraPositon();
        arena.getLink().setPosition(camera.getCameraPositon());
        camera.moveDown();
        Assertions.assertEquals(camera.getCameraPositon(), expected);
    }

    @Test
    void moveLeft() throws IOException{
        Arena arena = new Arena(game);
        Camera camera = new Camera(new Position(200,200), 288, 224, arena);

        Position expected = new Position(200-arena.getLink().getVelocity(),200);
        arena.getLink().setPosition(new Position(0,0));
        camera.moveLeft();
        Assertions.assertEquals(camera.getCameraPositon(), expected);

        expected = camera.getCameraPositon();
        arena.getLink().setPosition(camera.getCameraPositon());
        camera.moveLeft();
        Assertions.assertEquals(camera.getCameraPositon(), expected);

    }

    @Test
    void moveRight() throws IOException{
        Arena arena = new Arena(game);
        Camera camera = new Camera(new Position(0,0), 288,224, arena);

        Position expected = new Position(arena.getLink().getVelocity(),0);
        arena.getLink().setPosition(new Position(200,200));
        camera.moveRight();
        Assertions.assertEquals(camera.getCameraPositon(), expected);

        expected = camera.getCameraPositon();
        arena.getLink().setPosition(camera.getCameraPositon());
        camera.moveRight();
        Assertions.assertEquals(camera.getCameraPositon(), expected);
    }

    @Test
    void getTerminalHeight() throws IOException {
        Arena arena = new Arena(game);
        Camera camera = new Camera(new Position(0,0), 288,224, arena);

        int expected = 224;

        Assertions.assertEquals(expected,camera.getTerminalHeight());
    }

    @Test
    void getTerminalWidth() throws IOException {
        Arena arena = new Arena(game);
        Camera camera = new Camera(new Position(0,0), 288,224, arena);

        int expected = 288;

        Assertions.assertEquals(expected,camera.getTerminalWidth());
    }

    @Test
    void gettersTest() throws IOException {
        Arena arena = new Arena(game);
        Camera camera = new Camera(new Position(0,0), 288,224, arena);
        Assertions.assertEquals(arena,camera.getArena());
    }




}
