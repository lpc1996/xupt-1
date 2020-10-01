package com;

import com.entity.BaseInfoEntity;
import com.settings.Config;
import com.settings.Setting;
import com.window.LoginJDialog;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

/**
 * @author 濃霧-遠方
 * @date 2020/06/16
 */
public class Main {

    private static Dimension screenSize;
    private static Dimension windowSize;
    private static Font font;
    private static Setting setting;
    private static BaseInfoEntity user;
    private static LoginJDialog loginJDialog;

    static{
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Config config = new Config();
        setting = config.getSetting();
        font = new Font(setting.getFontName(),setting.getFontType(),setting.getFontSize());
        windowSize = new Dimension((int)(screenSize.width*0.6),(int)(screenSize.height*0.6));
    }

    public static void main( String[] args ) {
        loginJDialog = new LoginJDialog();
        InitGlobalFont(font);
        loginJDialog.setVisible(true);

    }

    public static void reLogin(){
        LoginJDialog.disposeMainJFrame();
        loginJDialog = new LoginJDialog();
        loginJDialog.setVisible(true);
    }

    /**
     * 设置全局字体样式
     * @param font 字体
     */
    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

    public static Dimension getScreenSize(){
        return screenSize;
    }

    public static Font getFont(){
        return font;
    }

    public static Dimension getWindowSize() {
        return windowSize;
    }

    public static void exit(int status){
        System.exit(status);
    }

    public static Setting getSetting(){
        return Main.setting;
    }

    public static void setUser(BaseInfoEntity user){
        Main.user=user;
    }

    public static BaseInfoEntity getUser(){
        return Main.user;
    }
}