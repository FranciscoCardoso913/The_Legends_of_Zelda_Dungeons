package com.l08gr01.legendsOfZeldaDungeons.gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ArrayList;

public class Image {

    ArrayList<ArrayList<Color>> image;
    private Color nullColor;


    public Image(String path){
        URL url = getClass().getResource(path);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(url);
            bufferedImage.getWidth();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*Defining the background color to be ignored*/
        nullColor = new Color(bufferedImage.getRGB(0, 0));
        image= readingImage(bufferedImage);
    }
    public Image(String path, Color nullColor_){
        URL url = getClass().getResource(path);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(url);
            bufferedImage.getWidth();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*Defining the background color to be ignored*/
        nullColor = nullColor_;
        image= readingImage(bufferedImage);
    }
    public ArrayList<ArrayList<Color>> readingImage(BufferedImage bufferedImage) {
        ArrayList<ArrayList<Color>> image_ = new ArrayList<>();
        for (int yy = 0; yy < (int) bufferedImage.getHeight(); yy++) {
            ArrayList<Color> aux = new ArrayList<>();
            for (int xx = 0; xx < (int) bufferedImage.getWidth(); xx++) {
                aux.add(new Color(bufferedImage.getRGB(xx, yy)));
            }
            image_.add(aux);
        }
        return  image_;
    }

    public ArrayList<ArrayList<Color>> getImage(){
        return image;
    }
    public Color getNullColor(){
        return nullColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;
        Image image1 = (Image) o;
        return Objects.equals(image, image1.image) && Objects.equals(nullColor, image1.nullColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image, nullColor);
    }
}
