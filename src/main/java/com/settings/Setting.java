package com.settings;

import com.Main;

import javax.swing.*;
import java.awt.*;

/**
 * @author 濃霧-遠方
 * @date 2020/06/18
 */
public class Setting {

    private String fontName;
    private int fontType;
    private int fontSize;
    private String style;

    public Setting() {
    }

    public void setValues(String name,String value){
        if("style".equals(name)){
            this.style = value;
        }else if("name".equals(name)){
            this.fontName=value;
        }else if("type".equals(name)){
            this.fontType=Integer.parseInt(value);
        }else if("size".equals(name)){
            this.fontSize = Integer.parseInt(value);
        }else{
//            JOptionPane.showConfirmDialog(null,"读取配置文件出错，请检查配置文件！");
//            Main.exit(0);
        }
    }

    public String getFontName() {
        return fontName;
    }

    public int getFontType() {
        return fontType;
    }

    public int getFontSize() {
        return fontSize;
    }

    public String getStyle() {
        return style;
    }
}
