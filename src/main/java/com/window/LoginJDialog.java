package com.window;

import com.Main;
import com.dao.BaseInfoDao;
import com.dao.LoginDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 濃霧-遠方
 * @date 2020/06/17
 */
public class LoginJDialog extends JFrame {

    private JTextField userName;
    private JPasswordField password;
    private JButton submitBtn;
    private JButton exitBtn;
    private static MainJFrame mainJFrame;

    public LoginJDialog() {

        this.setSize(new Dimension(296, 356));
        this.setResizable(true);
        this.setTitle("欢迎登陆");

        this.setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel(Main.getSetting().getStyle());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setContentPane(createContentPane());
    }

    private JPanel createContentPane(){
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        contentPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel titleLab = new JLabel();
        titleLab.setFont(Main.getFont());
        titleLab.setText("<html><center>用户登录</center></html>");
        titleLab.setHorizontalAlignment(JLabel.CENTER);
        titleLab.setPreferredSize(new Dimension(getWidth()-30,(int)(getHeight()*0.3)));
        contentPane.add(titleLab);

        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
        inputPanel.setPreferredSize(new Dimension(getWidth()-30,100));
        JLabel userNameLab = new JLabel("用户名：");
        userNameLab.setFont(Main.getFont());
        userNameLab.setPreferredSize(new Dimension((int)(inputPanel.getPreferredSize().getWidth()*0.3),
                40));
        userName = new JTextField();
        userName.setFont(Main.getFont());
        userName.setPreferredSize(new Dimension((int)(inputPanel.getPreferredSize().getWidth()*0.7-10),
                40));
        inputPanel.add(userNameLab);
        inputPanel.add(userName);

        JLabel passwordLab = new JLabel("密码：");
        passwordLab.setFont(Main.getFont());
        passwordLab.setPreferredSize(new Dimension((int)(inputPanel.getPreferredSize().getWidth()*0.3),
                40));
        password = new JPasswordField();
        password.setFont(Main.getFont());
        password.setPreferredSize(new Dimension((int)(inputPanel.getPreferredSize().getWidth()*0.7-10),
                40));

        inputPanel.add(passwordLab);
        inputPanel.add(password);
        contentPane.add(inputPanel);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
        btnPanel.setPreferredSize(new Dimension((int)inputPanel.getPreferredSize().getWidth() ,
                50) );
        submitBtn = new JButton("登录");
        submitBtn.setFont(Main.getFont());
        submitBtn.setPreferredSize(new Dimension((int)(btnPanel.getPreferredSize().getWidth()-15)/2,
                40));
        submitBtn.addActionListener(createSubmitAction());
        btnPanel.add(submitBtn);
        exitBtn = new JButton("退出");
        exitBtn.setFont(Main.getFont());
        exitBtn.setPreferredSize(new Dimension((int)(btnPanel.getPreferredSize().getWidth()-15)/2
                ,40));
        exitBtn.addActionListener(createExitAction());
        btnPanel.add(exitBtn);

        contentPane.add(btnPanel);
        return contentPane;
    }

    private ActionListener createSubmitAction(){
        ActionListener submitAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userName.getText();
                String pass = new String(password.getPassword());
                if(user.length() == 0){
                    JOptionPane.showMessageDialog(getContentPane(),"请输入用户名！");
                    return;
                }
                if(pass.length() == 0){
                    JOptionPane.showMessageDialog(getContentPane(),"请输入密码！");
                    return;
                }else if(pass.length() < 8){
                    JOptionPane.showMessageDialog(getContentPane(),"密码长度不够（8-36位）!");
                    return;
                }
                LoginDao loginDao = new LoginDao();
                if(loginDao.login(user,pass)){
                    BaseInfoDao baseInfoDao = new BaseInfoDao();
                    Main.setUser(baseInfoDao.getUser(user));
                    JOptionPane.showMessageDialog(getContentPane(),"登陆成功");
                    dispose();
                    mainJFrame = new MainJFrame();
                    mainJFrame.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(getContentPane(),"登陆失败，用户名或密码错误！");
                }
            }
        };
        return submitAction;
    }

    private ActionListener createExitAction(){
        ActionListener exitAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.exit(0);
            }
        };
        return exitAction;
    }

    public static void disposeMainJFrame(){
        LoginJDialog.mainJFrame.dispose();
    }
}
