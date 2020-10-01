package com.window;

import com.Main;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 濃霧-遠方
 * @date 2020/06/17
 */
public class MainJFrame extends JFrame {

    private PersonalInfoJFrame personalInfoJFrame;
    private UpdatePass updatePass;

    public MainJFrame() {
        setTitle("教务管理系统");
        setSize(Main.getWindowSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        setJMenuBar(createJMenuBar());
    }

    private JMenuBar createJMenuBar(){
        JMenuBar bar = new JMenuBar();
        bar.add(createPersonalCenter());

        return bar;
    }

    private JMenu createPersonalCenter(){
        JMenu personalCenter = new JMenu("个人中心");
        personalCenter.add(createpersonalInfoItem());
        personalCenter.add(createUpdatePassItem());
        personalCenter.add(createSwitchItem());
        personalCenter.addSeparator();
        personalCenter.add(createExitItem());
        return personalCenter;
    }

    private JMenuItem createpersonalInfoItem(){
        JMenuItem personalInfo = new JMenuItem("个人信息");
        personalInfo.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
            personalInfoJFrame = new PersonalInfoJFrame();
            personalInfoJFrame.setVisible(true);
            }
        });
        return personalInfo;
    }

    private JMenuItem createUpdatePassItem(){
        JMenuItem updatePassItem = new JMenuItem("修改密码");
        updatePassItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePass = new UpdatePass();
                updatePass.setVisible(true);
            }
        });
        return updatePassItem;
    }

    private JMenuItem createSwitchItem(){
        JMenuItem switchItem = new JMenuItem("切换账户");
        switchItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Main.reLogin();
            }
        });
        return switchItem;
    }

    private JMenuItem createExitItem(){
        JMenuItem exitItem = new JMenuItem("退出");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.exit(1000);
            }
        });
        return exitItem;
    }
}
