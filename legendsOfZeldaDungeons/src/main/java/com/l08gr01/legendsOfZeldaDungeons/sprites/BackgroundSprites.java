package com.l08gr01.legendsOfZeldaDungeons.sprites;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;

import java.awt.*;
import java.util.HashMap;

public class BackgroundSprites {
    private HashMap<String, Image> backgroundSprites = new HashMap<>();

    public BackgroundSprites() {
        getSprites("/images/background/", backgroundSprites);
    }

    public HashMap<String, Image> getBackgroundSprites() {
        return backgroundSprites;
    }

    private void getSprites(String path, HashMap<String, Image> backgroundSprites) {
        backgroundSprites.put("cid", new Image(path + "cid.png", new Color(97, 25, 25)));
        backgroundSprites.put("cie", new Image(path + "cie.png", new Color(97, 25, 25)));
        backgroundSprites.put("csd", new Image(path + "csd.png", new Color(97, 25, 25)));
        backgroundSprites.put("cse", new Image(path + "cse.png", new Color(97, 25, 25)));
        backgroundSprites.put("floor", new Image(path + "floor.png", new Color(97, 25, 25)));
        backgroundSprites.put("ld", new Image(path + "ld.png", new Color(97, 25, 25)));
        backgroundSprites.put("le", new Image(path + "le.png", new Color(97, 25, 25)));
        backgroundSprites.put("li", new Image(path + "li.png", new Color(97, 25, 25)));
        backgroundSprites.put("ls", new Image(path + "ls.png", new Color(97, 25, 25)));
        backgroundSprites.put("pd", new Image(path + "pd.png", new Color(97, 25, 25)));
        backgroundSprites.put("pl", new Image(path + "pl.png", new Color(97, 25, 25)));
        backgroundSprites.put("pr", new Image(path + "pr.png", new Color(97, 25, 25)));
        backgroundSprites.put("pu", new Image(path + "pu.png", new Color(97, 25, 25)));
    }
}
