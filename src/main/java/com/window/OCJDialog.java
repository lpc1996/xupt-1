package com.window;

import com.DateChooser;
import com.dao.*;
import com.entity.OfferingCoursesEntity;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class OCJDialog extends Window<OfferingCoursesEntity> {

    private final Dimension operationSize;
    private final OperationPane<OfferingCoursesEntity> ocOperation;

    public OCJDialog(){
        super(new Dimension(800,500),"教学班信息管理",false);
        operationSize = new Dimension(getWidth()-150-30,getHeight()-140);
        ocOperation = createOperation();
        setOperationPane("教学班信息",ocOperation);

        initData();
        createInsertAction();
        createUpdateAction();
        createDeleteAction();
    }
    @Override
    protected OperationPane<OfferingCoursesEntity> createOperation() {
        OperationPane<OfferingCoursesEntity> operation = new OperationPane<OfferingCoursesEntity>(operationSize) {
            private JTextField numField;
            private JComboBox<String> semesterBox;
            private JComboBox<String> stBox;
            private JComboBox<String> syBox;
            private JTextField beginField;
            private JComboBox<String> teacherIdBox;
            private JComboBox<String> courseIdBox;
            private JTextField idField;

            @Override
            public void InitData(String id) {
                for(int i=0; i<list.size();i ++){
                    if(list.get(i).getId().equals(id)){
                        OfferingCoursesEntity of = list.get(i);
                        idField.setText(of.getId());
                        courseIdBox.setSelectedItem(equals(of.getCourseId(),courseIdBox));
                        teacherIdBox.setSelectedItem(equals(of.getTeacherId(),teacherIdBox));
                        beginField.setText(of.getBegin().toString());
                        syBox.setSelectedItem(equals(of.getSchoolYearId(),syBox));
                        stBox.setSelectedItem(equals(of.getSchoolTremId(),stBox));
                        semesterBox.setSelectedItem(equals(of.getSemesterId(),semesterBox));
                        numField.setText(of.getMaxNum()+"");
                    }
                }
            }

            @Override
            public OfferingCoursesEntity getData() {
                OfferingCoursesEntity of = new OfferingCoursesEntity();
                of.setId(idField.getText());
                of.setCourseId(split(courseIdBox.getSelectedItem().toString()));
                of.setTeacherId(split(teacherIdBox.getSelectedItem().toString()));
                try {
                    of.setBegin(new Date( new SimpleDateFormat("yyyy-MM-dd").parse(beginField.getText()).getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"请选择一个日期！");
                    return null;
                }
                of.setSchoolYearId(split(syBox.getSelectedItem().toString()));
                of.setSchoolTremId(split(stBox.getSelectedItem().toString()));
                of.setSemesterId(split(semesterBox.getSelectedItem().toString()));
                try{
                    of.setMaxNum(Integer.parseInt(numField.getText()));
                }catch (NumberFormatException n){
                    n.printStackTrace();
                    JOptionPane.showMessageDialog(null,"请输入班级人数！");
                    return null;
                }
                return of;
            }

            @Override
            public void setNull() {
                idField.setText(null);
                courseIdBox.setSelectedIndex(-1);
                teacherIdBox.setSelectedIndex(-1);
                beginField.setText(null);
                syBox.setSelectedIndex(-1);
                stBox.setSelectedIndex(-1);
                semesterBox.setSelectedIndex(-1);
                numField.setText(null);
            }

            @Override
            protected void InitPane() {
                JLabel idLab = new JLabel("教学班代码：");
                idLab.setPreferredSize(labSize);
                idField = new JTextField();
                idField.setPreferredSize(fieldSize1);
                add(idLab);
                add(idField);
                JLabel courseIdLab = new JLabel("课程代码：");
                courseIdLab.setPreferredSize(labSize);
                courseIdBox = new JComboBox<String>();
                courseIdBox.setPreferredSize(fieldSize1);
                add(courseIdLab);
                add(courseIdBox);
                JLabel teacherIdLab = new JLabel("任课教师工号：");
                teacherIdLab.setPreferredSize(labSize);
                teacherIdBox = new JComboBox<String>();
                teacherIdBox.setPreferredSize(fieldSize1);
                add(teacherIdLab);
                add(teacherIdBox);
                JLabel beginLab = new JLabel("开课时间：");
                beginLab.setPreferredSize(labSize);
                beginField = new JTextField();
                DateChooser.getInstance("yyyy-MM-dd").register(beginField);
                beginField.setPreferredSize(fieldSize1);
                add(beginLab);
                add(beginField);
                JLabel syLab = new JLabel("开课学年：");
                syLab.setPreferredSize(labSize);
                syBox = new JComboBox<String>();
                syBox.setPreferredSize(fieldSize1);
                add(syLab);
                add(syBox);
                JLabel stLab = new JLabel("开课学期：");
                stLab.setPreferredSize(labSize);
                stBox = new JComboBox<String>();
                stBox.setPreferredSize(fieldSize1);
                add(stLab);
                add(stBox);
                JLabel semesterLab = new JLabel("开课年级：");
                semesterLab.setPreferredSize(labSize);
                semesterBox = new JComboBox<String>();
                semesterBox.setPreferredSize(fieldSize1);
                add(semesterLab);
                add(semesterBox);
                JLabel numLab = new JLabel("班级人数：");
                numLab.setPreferredSize(labSize);
                numField = new JTextField();
                numField.setPreferredSize(fieldSize1);
                add(numLab);
                add(numField);

                initBox();
            }

            @Override
            public void initBox() {
                List<Object[]> list = new CourseDao().getIdAndName();
                courseIdBox.removeAllItems();
                for(int i=0; i<list.size(); i++){
                    courseIdBox.addItem(list.get(i)[0]+" "+list.get(i)[1]);
                }
                list = new BaseInfoDao().getIdAndName("teacher");
                teacherIdBox.removeAllItems();
                for(int i=0; i<list.size(); i++){
                    teacherIdBox.addItem(list.get(i)[0]+" "+list.get(i)[1]);
                }
                list = new SYDao().getIdAndName();
                syBox.removeAllItems();
                for(int i=0; i<list.size();i++){
                    syBox.addItem(list.get(i)[0]+" "+list.get(i)[1]);
                }
                list = new STDao().getIdAndName();
                stBox.removeAllItems();
                for(int i=0; i<list.size(); i++){
                    stBox.addItem(list.get(i)[0]+" "+list.get(i)[1]);
                }
                list = new SemesterDao().getIdAndName();
                semesterBox.removeAllItems();
                for(int i=0; i<list.size(); i++){
                    semesterBox.addItem(list.get(i)[0]+" "+list.get(i)[1]);
                }
            }
        };
        return operation;
    }

    @Override
    protected void createInsertAction() {
        setInsertAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OfferingCoursesEntity oc = ocOperation.getData();
                if(oc.getId().length() == 0){
                    JOptionPane.showMessageDialog(null,"请输入要添加的信息！");
                    return;
                }
                OCDao ocDao = new OCDao();
                if(ocDao.save(oc)){
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
                OfferingCoursesEntity oc = ocOperation.getData();
                if (oc != null) {
                    OCDao ocDao = new OCDao();
                    if(ocDao.update(id,oc)){
                        JOptionPane.showMessageDialog(null,"修改成功");
                    }else{
                        JOptionPane.showMessageDialog(null,"修改失败");
                    }
                    reload();
                }
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
                OCDao ocDao = new OCDao();
                if(ocDao.delete(id)){
                    JOptionPane.showMessageDialog(null,"修改成功");

                }else{
                    JOptionPane.showMessageDialog(null,"修改失败");
                }
                reload();
            }
        });
    }

    @Override
    protected void initData() {
        OCDao ocDao = new OCDao();
        List<OfferingCoursesEntity> list = ocDao.getList();
        ocOperation.setList(list);
        String[] title = {"课程班编号"};
        DefaultTableModel model = new DefaultTableModel(title,list.size());
        for(int i=0; i<list.size(); i++){
            model.setValueAt(list.get(i).getId(),i,0);
        }
        setTableModel(model);
    }

    @Override
    protected void reload() {
        initData();
        ocOperation.initBox();
        ocOperation.setNull();
        repaint();
    }

    public static void main(String[] argv){
        OCJDialog ocjDialog = new OCJDialog();
        ocjDialog.setVisible(true);
    }
}