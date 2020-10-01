package com.window;

import com.Main;
import javax.swing.*;
import java.awt.*;

/**
 * @author 濃霧-遠方
 * @date 2020/07/03
 */
public class PersonalInfoJFrame extends JFrame{
    private JLabel idLab;
    private JLabel nameLab;
    private JLabel nickNameLab;
    private JLabel formarNameLab;
    private JLabel ageLab;
    private JLabel sexLab;
    private JLabel nativeLab;
    private JLabel idcardTypeLab;
    private JLabel idcardNumLab;
    private JLabel telLab;

    public PersonalInfoJFrame() {
        setSize(new Dimension(650,300));
        setTitle("个人信息");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        setContentPane(createContentPane());
        InitData();
    }

    private JPanel createContentPane(){
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));

        Dimension labSize = new Dimension(300,40);
        idLab = new JLabel();
        idLab.setPreferredSize(labSize);
        contentPane.add(idLab);
        nameLab = new JLabel();
        nameLab.setPreferredSize(labSize);
        contentPane.add(nameLab);
        nickNameLab = new JLabel();
        nickNameLab.setPreferredSize(labSize);
        contentPane.add(nickNameLab);
        formarNameLab = new JLabel();
        formarNameLab.setPreferredSize(labSize);
        contentPane.add(formarNameLab);
        ageLab = new JLabel();
        ageLab.setPreferredSize(labSize);
        contentPane.add(ageLab);
        sexLab = new JLabel();
        sexLab.setPreferredSize(labSize);
        contentPane.add(sexLab);
        nativeLab = new JLabel();
        nativeLab.setPreferredSize(labSize);;
        contentPane.add(nativeLab);
        idcardTypeLab = new JLabel();
        idcardTypeLab.setPreferredSize(labSize);
        contentPane.add(idcardTypeLab);
        idcardNumLab = new JLabel();
        idcardNumLab.setPreferredSize(labSize);
        contentPane.add(idcardNumLab);
        telLab = new JLabel();
        telLab.setPreferredSize(labSize);
        contentPane.add(telLab);

        return contentPane;
    }

    public void InitData() {
        // TODO Auto-generated method stub
        idLab.setText("学工号："+ Main.getUser().getuId());
        nameLab.setText("姓名："+Main.getUser().getuName());
        nickNameLab.setText("昵称："+Main.getUser().getuName());
        formarNameLab.setText("曾用名："+Main.getUser().getFormarName());
        ageLab.setText("年龄："+Main.getUser().getAge());
        sexLab.setText("性别："+Main.getUser().getSex());
        nativeLab.setText("籍贯："+Main.getUser().getNativePlace());
        idcardTypeLab.setText("证件类型："+Main.getUser().getIdcardType());
        idcardNumLab.setText("证件号码："+Main.getUser().getIdcardNum());
        telLab.setText("电话号码："+Main.getUser().getTel());
    }
}
