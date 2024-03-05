package com.l08gr01.legendsOfZeldaDungeons;

import com.l08gr01.legendsOfZeldaDungeons.sprites.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Database {
    private static Database theInstance;
    private final LinkSprites linkSprites;
    private final ProjectileSprites projectileSprites;
    private final HeartSprites heartSprites;
    private final FighterSprites fighterSprites;

    private final ArcherSprites archerSprites;
    private final RatSprites ratSprites;
    private final NumberSpritesBlack numberSpritesBlack;
    private final BackgroundSprites backgroundSprites;
    private final NumberSpritesWhite numberSpritesWhite;

    private Integer highscore;

    private Database() {
        this.linkSprites = new LinkSprites();
        this.projectileSprites = new ProjectileSprites();
        this.heartSprites = new HeartSprites();
        this.fighterSprites = new FighterSprites();
        this.archerSprites = new ArcherSprites();
        this.ratSprites = new RatSprites();
        this.numberSpritesBlack = new NumberSpritesBlack();
        this.backgroundSprites = new BackgroundSprites();
        this.highscore = loadHighScore();
        this.numberSpritesWhite = new NumberSpritesWhite();
    }

    public static Database getDatabase() {
        if (theInstance == null)
            theInstance = new Database();
        return theInstance;
    }

    public LinkSprites getLinkSprites() {
        return linkSprites;
    }

    public ProjectileSprites getProjectileSprites() {
        return projectileSprites;
    }

    public HeartSprites getHeartSprites() {
        return heartSprites;
    }

    public FighterSprites getFighterSprites() {
        return fighterSprites;
    }

    public ArcherSprites getArcherSprites() {
        return archerSprites;
    }

    public RatSprites getRatSprites() {
        return ratSprites;
    }

    public NumberSpritesBlack getNumberSpritesBlack() {
        return numberSpritesBlack;
    }

    public NumberSpritesWhite getNumberSpritesWhite() {
        return numberSpritesWhite;
    }

    public BackgroundSprites getBackgroundSprites() {
        return backgroundSprites;
    }

    private int loadHighScore() {
        int data = 0;
        try {
            File Obj = new File("./src/main/resources/highscore");
            Scanner Reader = new Scanner(Obj);
            if(Reader.hasNextInt()){
                data = Reader.nextInt();
            }
            Reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
            e.printStackTrace();
        }
        return data;
    }

    public Integer getHighscore() {
        return highscore;
    }

    public void setHighscore(Integer highscore) {
        this.highscore = highscore;
    }

    public void storeHighscore() {
        try {
            FileWriter Writer = new FileWriter("./src/main/resources/highscore");
            Writer.write(highscore.toString());
            Writer.close();
        }
        catch (IOException e) {
            System.out.println("Couldn't write on file");
            e.printStackTrace();
        }
    }
}
