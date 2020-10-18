package com.window;

import com.DateChooser;
import com.dao.*;
import com.entity.BaseInfoEntity;
import com.entity.CollegeEntity;
import com.entity.StudentEntity;
import com.entity.TeacherEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TeacherJDialog extends Window<TeacherEntity> {

    private List<TeacherEntity> teacherList;
    private List<BaseInfoEntity> baseInfoList;
    private Dimension operationSize;
    private OperationPane<BaseInfoEntity> baseInfoOperation;
    private OperationPane<TeacherEntity> teacherOperation;

    public TeacherJDialog() {
        super(new Dimension(1000,500),"教职工信息管理",true);
        operationSize = new Dimension(getWidth()-150-30,getHeight()-140);
        baseInfoOperation = createBaseInfoOpeartion(operationSize);
        teacherOperation = createOperation();
        initData();
        setOperationPane("基础信息",baseInfoOperation);
        setOperationPane("职务信息",teacherOperation);

        createInsertAction();
        createUpdateAction();
        createDeleteAction();
    }

    @Override
    protected void createInsertAction() {
        setInsertAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BaseInfoEntity baseInfo = baseInfoOperation.getData();
                TeacherEntity teacher = teacherOperation.getData();
                teacher.setId(baseInfo.getuId());
                if(baseInfo.getuId().length() == 0){
                    JOptionPane.showMessageDialog(null,"请输入要添加的信息！");
                    return;
                }
                BaseInfoDao baseInfoDao = new BaseInfoDao();
                TeacherDao teacherDao = new TeacherDao();
                if(baseInfoDao.insert(baseInfo) && teacherDao.insert(teacher)){
                    JOptionPane.showMessageDialog(null,"添加成功");
                }else{
                    JOptionPane.showMessageDialog(null,"添加失败");
                }
                reload();
            }
        });
    }

    @Override
    protected void createUpdateAction() {
        setUpdateAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = getSelectRow();
                if(index == -1){
                    JOptionPane.showMessageDialog(null,"请选中一行数据并进行修改");
                    return;
                }
                String id = getValueAt(index)+"";
                BaseInfoEntity baseInfo = baseInfoOperation.getData();
                TeacherEntity teacher = teacherOperation.getData();
                teacher.setId(baseInfo.getuId());
                BaseInfoDao baseInfoDao = new BaseInfoDao();
                TeacherDao teacherDao = new TeacherDao();
                if(baseInfoDao.update(id,baseInfo) && teacherDao.update(id,teacher)){
                    JOptionPane.showMessageDialog(null,"修改成功");
                }else{
                    JOptionPane.showMessageDialog(null,"修改失败");
                }
                reload();
            }
        });
    }

    @Override
    protected void createDeleteAction() {
        setDeleteAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = getSelectRow();
                if(index == -1){
                    JOptionPane.showMessageDialog(null,"请选中一行数据");
                    return;
                }
                String id = getValueAt(index)+"";
                BaseInfoDao baseInfoDao = new BaseInfoDao();
                TeacherDao teacherDao = new TeacherDao();
                if(teacherDao.delete(id) && baseInfoDao.delete(id)){
                    JOptionPane.showMessageDialog(null,"删除成功");
                }else{
                    JOptionPane.showMessageDialog(null,"删除失败");
                }
                reload();
            }
        });
    }

    @Override
    protected void initData() {
        BaseInfoDao baseInfoDao = new BaseInfoDao();
        TeacherDao teacherDao = new TeacherDao();
        baseInfoList = baseInfoDao.getList("teacher");
        teacherList = teacherDao.getList();
        baseInfoOperation.setList(baseInfoList);
        teacherOperation.setList(teacherList);
        String[] title = new String[1];
        title[0] = "工号";
        DefaultTableModel model = new DefaultTableModel(title,teacherList.size());
        for(int i=0; i<teacherList.size(); i++){
            model.setValueAt(teacherList.get(i).getId()+"",i,0);
        }
        setTableModel(model);
    }

    protected void reload(){
        initData();
        baseInfoOperation.initBox();
        teacherOperation.initBox();
        baseInfoOperation.setNull();
        teacherOperation.setNull();

        repaint();
    }

    protected OperationPane<TeacherEntity> createOperation(){
        OperationPane<TeacherEntity> teacherOperation = new OperationPane<TeacherEntity>(operationSize) {
            private JComboBox<String> departmentBox;
            private JTextField yearField;
            private JComboBox<String> educationBox;
            private JComboBox<String> levelBox;
            private JComboBox<String> collegeBox;

            @Override
            public void InitData(String id) {
                for(int i=0; i<list.size(); i++){
                    TeacherEntity teacher = list.get(i);
                    if(teacher.getId().equals(id)){
                        collegeBox.setSelectedItem(equals(teacher.getCollege(),collegeBox));
                        departmentBox.setSelectedItem(equals(teacher.getDepartment(),departmentBox));
                        levelBox.setSelectedItem(teacher.getLevel());
                        educationBox.setSelectedItem(teacher.getEducation());
                        yearField.setText(teacher.getYear().toString());
                        break;
                    }
                }
            }

            @Override
            public TeacherEntity getData() {
                TeacherEntity teacher = new TeacherEntity();
                teacher.setCollege(split(collegeBox.getSelectedItem()+""));
                teacher.setDepartment(split(departmentBox.getSelectedItem()+""));
                teacher.setLevel(levelBox.getSelectedItem()+"");
                teacher.setEducation(educationBox.getSelectedItem()+"");
                try {
                    teacher.setYear(new Date( (new SimpleDateFormat("yyyy-MM-d").parse(yearField.getText()) ).getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return teacher;
            }

            @Override
            public void setNull() {
                collegeBox.setSelectedIndex(-1);
                departmentBox.setSelectedIndex(-1);
                levelBox.setSelectedIndex(-1);
                educationBox.setSelectedIndex(-1);
                yearField.setText("");
            }

            @Override
            protected void InitPane() {
                JLabel collegeLab = new JLabel("所属学院*：");
                collegeLab.setPreferredSize(labSize);
                collegeBox = new JComboBox<String>();
                collegeBox.setPreferredSize(fieldSize);
                add(collegeLab);
                add(collegeBox);
                JLabel departmentLab = new JLabel("所属系/部*：");
                departmentLab.setPreferredSize(labSize);
                departmentBox = new JComboBox<String>();
                departmentBox.setPreferredSize(fieldSize);
                add(departmentLab);
                add(departmentBox);
                JLabel levelLab = new JLabel("职称*：");
                levelLab.setPreferredSize(labSize);
                levelBox = new JComboBox<String>();
                levelBox.setPreferredSize(fieldSize);
                add(levelLab);
                add(levelBox);
                JLabel educationLab = new JLabel("学历*：");
                educationLab.setPreferredSize(labSize);
                educationBox = new JComboBox<String>();
                educationBox.setPreferredSize(fieldSize);
                add(educationLab);
                add(educationBox);
                JLabel yearLab = new JLabel("入职时间");
                yearLab.setPreferredSize(labSize);
                yearField = new JTextField();
                DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
                dateChooser.register(yearField);
                yearField.setPreferredSize(fieldSize);
                add(yearLab);
                add(yearField);

                initBox();
            }
            public void initBox(){
                List list = null;
                collegeBox.removeAllItems();
                list = new CollegeDao().getList();
                for(int i=0; i<list.size(); i++){
                    CollegeEntity collegeEntity = (CollegeEntity) list.get(i);
                    collegeBox.addItem(collegeEntity.getId()+" "+collegeEntity.getName());
                }
                departmentBox.removeAllItems();
                list = new DepartmentDao().getIdAndName();
                for(int i=0; i<list.size(); i++){
                    Object[] departmentEntity = (Object[]) list.get(i);
                    departmentBox.addItem(departmentEntity[0]+" "+departmentEntity[1]);
                }

                levelBox.addItem("助教");
                levelBox.addItem("讲师");
                levelBox.addItem("副教授");
                levelBox.addItem("教授");

                educationBox.addItem("专科");
                educationBox.addItem("本科");
                educationBox.addItem("硕士");
                educationBox.addItem("博士");
            }
        };
        return teacherOperation;
    }
}
