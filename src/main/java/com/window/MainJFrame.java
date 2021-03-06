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
    private StudentJDialog studentJDialog;
    private TeacherJDialog teacherJDialog;
    private CollegeJDialog collegeJdialog;
    private DepartmentJDialog departmentJDialog;
    private MajorJDialog majorJDialog;
    private SyJDialog syJDialog;
    private StJDialog stJDialog;
    private CourseJDialog courseJDialog;
    private OCJDialog ocJDialog;
    private TeamJDialog teamJDialog;

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
        bar.add(createEAMJMenu());
        bar.add(createCollegesJMenu());

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
                if(personalInfoJFrame == null || !personalInfoJFrame.isDisplayable()) {
                    personalInfoJFrame = new PersonalInfoJFrame();
                }
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
                if(updatePass == null || !updatePass.isDisplayable()) {
                    updatePass = new UpdatePass();
                }
                updatePass.repaint();
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

    /**
     * 创建教务信息管理菜单
     */
    private JMenu createEAMJMenu(){
        JMenu EAMJMenu = new JMenu("教务信息管理");
        EAMJMenu.add(createStudentManageJMenu());
        EAMJMenu.add(createTeacherManageItem());

        return EAMJMenu;
    }

    private JMenuItem createStudentManageJMenu(){
        JMenuItem studentManageJMenu = new JMenuItem("学生信息管理");
        studentManageJMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(studentJDialog == null || !studentJDialog.isDisplayable() ){
                    studentJDialog = new StudentJDialog();
                }
                studentJDialog.repaint();
                studentJDialog.setVisible(true);
            }
        });
        return studentManageJMenu;
    }

    private JMenuItem createTeacherManageItem(){
        JMenuItem teacherItem = new JMenuItem("教职工信息管理");
        teacherItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(teacherJDialog == null || !teacherJDialog.isDisplayable()) {
                    teacherJDialog = new TeacherJDialog();
                }
                teacherJDialog.repaint();
                teacherJDialog.setVisible(true);
            }
        });
        return teacherItem;
    }

    private JMenu createCollegesJMenu(){
        JMenu collegesJMenu = new JMenu("院系设置");
        collegesJMenu.add(createCollegeItem());
        collegesJMenu.add(createDepartmentItem());
        collegesJMenu.add(createMajorItem());
        collegesJMenu.add(createSYItem());
        collegesJMenu.add(createSTItem());
        collegesJMenu.add(createCourseItem());
        collegesJMenu.add(createOCItem());
        collegesJMenu.add(createTeamItem());
        return collegesJMenu;
    }

    private JMenuItem createCollegeItem(){
        JMenuItem collegeItem = new JMenuItem("学院信息管理");
        collegeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(collegeJdialog == null || !collegeJdialog.isDisplayable()) {
                    collegeJdialog = new CollegeJDialog();
                }
                collegeJdialog.repaint();
                collegeJdialog.setVisible(true);
            }
        });
        return collegeItem;
    }

    private JMenuItem createDepartmentItem(){
        JMenuItem departmentItem = new JMenuItem("系/部信息管理");
        departmentItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(departmentJDialog == null || !departmentJDialog.isDisplayable()){
                    departmentJDialog = new DepartmentJDialog();
                }
                departmentJDialog.repaint();
                departmentJDialog.setVisible(true);

            }
        });
        return departmentItem;
    }

    private JMenuItem createMajorItem(){
        JMenuItem majorItem = new JMenuItem("专业信息管理");
        majorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(majorJDialog == null || !majorJDialog.isDisplayable()) {
                    majorJDialog = new MajorJDialog();
                }
                majorJDialog.repaint();
                majorJDialog.setVisible(true);
            }
        });
        return majorItem;
    }

    private JMenuItem createSYItem(){
        JMenuItem item = new JMenuItem("学年信息管理");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(syJDialog == null || syJDialog.isDisplayable()) {
                    syJDialog = new SyJDialog();
                }
                syJDialog.repaint();
                syJDialog.setVisible(true);
            }
        });
        return item;
    }

    private JMenuItem createSTItem(){
        JMenuItem item = new JMenuItem("学期信息管理");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(stJDialog == null || !stJDialog.isDisplayable()){
                    stJDialog = new StJDialog();
                }
                stJDialog.repaint();
                stJDialog.setVisible(true);
            }
        });
        return item;
    }

    private JMenuItem createCourseItem(){
        JMenuItem item = new JMenuItem("课程信息管理");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(courseJDialog == null || !courseJDialog.isDisplayable()) {
                    courseJDialog = new CourseJDialog();
                }
                courseJDialog.repaint();
                courseJDialog.setVisible(true);
            }
        });
        return item;
    }

    private JMenuItem createOCItem(){
        JMenuItem item = new JMenuItem("课程班管理");
        item.addActionListener(e -> {
            if(ocJDialog == null || !ocJDialog.isDisplayable()) {
                ocJDialog = new OCJDialog();
            }
            repaint();
            ocJDialog.setVisible(true);
        });
        return item;
    }

    private JMenuItem createTeamItem(){
        JMenuItem item = new JMenuItem("班级信息管理");
        item.addActionListener(e->{
            if (teamJDialog == null || !teamJDialog.isDisplayable()) {
                teamJDialog = new TeamJDialog();
            }
            repaint();
            teamJDialog.setVisible(true);
        });
        return item;
    }

    public static void main(String... argv){
        MainJFrame mainJFrame = new MainJFrame();
        mainJFrame.setVisible(true);
    }
}
