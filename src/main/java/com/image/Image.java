package com.image;

import java.awt.*;

public class Image {

//    private static final String preURL= "com/lpc/image/";
    public java.awt.Image getSearch_4(){
        return Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("search-4.png").getPath());
    }

    public static void main(String[] argv){
        System.out.println(new Image().getSearch_4());
    }
}
