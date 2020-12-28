package com.window;

import com.Main;
import com.dao.LoginDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author 濃霧-遠方
 * @date 2020/07/17
 */
public class UpdatePass extends JFrame {
    private JPasswordField oldPass;
    private JPasswordField newPass1;
    private JPasswordField newPass2;

    public UpdatePass() {
        this.setTitle("修改密码");
        setSize(new Dimension(400,220));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        setContentPane(createContentPane());
    }

    private JPanel createContentPane() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createTitledBorder("操作一体化"));
        JLabel oldPassLab = new JLabel();
        oldPassLab.setPreferredSize(new Dimension(120,30));
        oldPassLab.setText("旧密码：");
        oldPass = new JPasswordField();
        oldPass.setPreferredSize(new Dimension(200,30));
        JLabel newPass1Lab = new JLabel("新密码：");
        newPass1Lab.setPreferredSize(new Dimension(120,30));
        newPass1 = new JPasswordField();
        newPass1.setPreferredSize(new Dimension(200,30));
        JLabel newPassLab2 = new JLabel("确认新密码：");
        newPassLab2.setPreferredSize(new Dimension(120,30));
        newPass2 = new JPasswordField();
        newPass2.setPreferredSize(new Dimension(200,30));
        contentPane.add(oldPassLab);
        contentPane.add(oldPass);
        contentPane.add(newPass1Lab);
        contentPane.add(newPass1);
        contentPane.add(newPassLab2);
        contentPane.add(newPass2);

        JButton submit = new JButton("修改密码");
        submit.addActionListener(createBtnAction());
        JButton exit = new JButton("退出修改");
        exit.addActionListener(createExitAction());
        contentPane.add(submit);
        contentPane.add(exit);
        return contentPane;
    }

    private ActionListener createBtnAction() {

        return arg0 -> {
            // TODO Auto-generated method stub
            if(oldPass.getPassword().length == 0 || newPass1.getPassword().length == 0 || newPass2.getPassword().length ==0) {
                JOptionPane.showMessageDialog(getComponent(0), "请输入密码！");
                return;
            }
            String oldPassword = new String( oldPass.getPassword() );
            String newPass = new String( newPass1.getPassword() );
            if(!newPass.equals(new String(newPass2.getPassword()))) {
                JOptionPane.showMessageDialog(getComponent(0), "两次输入的密码不同，请重新输入");
                return ;
            }
            LoginDao loginDao = new LoginDao();
            if(loginDao.updatePass(Main.getUser().getuId(), oldPassword, newPass)) {
                JOptionPane.showMessageDialog(getComponent(0), "密码修改成功，请重新登陆！");
                dispose();
                Main.reLogin();
            }else {
                JOptionPane.showMessageDialog(getComponent(0), "密码修改失败！");
            }
        };
    }

    private ActionListener createExitAction(){
        return e -> dispose();
    }

    public static void main(String[] argv){
        UpdatePass updatePass = new UpdatePass();
        updatePass.setVisible(true);
    }
}
