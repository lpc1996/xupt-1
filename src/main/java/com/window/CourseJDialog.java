package com.window;

import com.dao.CollegeDao;
import com.dao.CourseDao;
import com.dao.DepartmentDao;
import com.entity.CourseEntity;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CourseJDialog extends Window<CourseEntity> {

    private final Dimension operationSize;
    private final OperationPane<CourseEntity> courseOperation;

    public CourseJDialog(){
        super(new Dimension(600,500),"课程信息管理",false);
        operationSize = new Dimension(getWidth()-150-30,getHeight()-140);
        courseOperation = createOperation();
        setOperationPane("课程信息",courseOperation);

        initData();
        createInsertAction();
        createUpdateAction();
        createDeleteAction();
    }
    @Override
    protected OperationPane<CourseEntity> createOperation() {
        OperationPane<CourseEntity> operation = new OperationPane<CourseEntity>(operationSize) {
            private JComboBox<String> departmentBox;
            private JComboBox<String> collegeBox;
            private JTextField creditField;
            private JComboBox<String> typeBox;
            private JTextField nameField;
            private JTextField idField;

            @Override
            public void InitData(String id) {
                for(int i=0; i<list.size(); i++){
                    if(list.get(i).getId().equals(id)){
                        CourseEntity course = list.get(i);
                        idField.setText(course.getId());
                        nameField.setText(course.getName());
                        collegeBox.setSelectedItem(equals(course.getCollegeId(),collegeBox));
                        departmentBox.setSelectedItem(equals(course.getDepartmentId(),departmentBox));
                        typeBox.setSelectedItem(course.getType());
                        creditField.setText(course.getCredit()+"");
                    }
                }
            }

            @Override
            public CourseEntity getData() {
                CourseEntity course = new CourseEntity();
                course.setId(idField.getText());
                course.setName(nameField.getText());
                course.setCollegeId(split(collegeBox.getSelectedItem().toString()));
                course.setDepartmentId(split(departmentBox.getSelectedItem().toString()));
                course.setType(typeBox.getSelectedItem().toString());
                try {
                    course.setCredit(Double.parseDouble(creditField.getText()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"请输入正确的学分！");
                    course = null;
                }
                return course;
            }

            @Override
            public void setNull() {
                idField.setText(null);
                nameField.setText(null);
                collegeBox.setSelectedIndex(-1);
                departmentBox.setSelectedIndex(-1);
                typeBox.setSelectedIndex(-1);
                creditField.setText(null);
            }

            @Override
            protected void InitPane() {
                JLabel idlab = new JLabel("课程代码：");
                idlab.setPreferredSize(labSize);
                idField = new JTextField();
                idField.setPreferredSize(fieldSize1);
                add(idlab);
                add(idField);
                JLabel nameLab = new JLabel("课程名称：");
                nameLab.setPreferredSize(labSize);
                nameField = new JTextField();
                nameField.setPreferredSize(fieldSize1);
                add(nameLab);
                add(nameField);
                JLabel collegeLab = new JLabel("开课学院：");
                collegeLab.setPreferredSize(labSize);
                collegeBox = new JComboBox<String>();
                collegeBox.setPreferredSize(fieldSize1);
                add(collegeLab);
                add(collegeBox);
                JLabel departmentLab = new JLabel("所属系/部:");
                departmentLab.setPreferredSize(labSize);
                departmentBox = new JComboBox<String>();
                departmentBox.setPreferredSize(fieldSize1);
                add(departmentLab);
                add(departmentBox);
                JLabel typeLab = new JLabel("课程性质：");
                typeLab.setPreferredSize(labSize);
                typeBox = new JComboBox<String>();
                typeBox.setPreferredSize(fieldSize1);
                add(typeLab);
                add(typeBox);
                JLabel creditLab = new JLabel("学分：");
                creditLab.setPreferredSize(labSize);
                creditField = new JTextField();
                creditField.setPreferredSize(fieldSize1);
                add(creditLab);
                add(creditField);

                initBox();
            }

            @Override
            public void initBox() {
                List<Object[]> list = new CollegeDao().getIdAndName();
                for(int i=0; i<list.size(); i++){
                    collegeBox.addItem(list.get(i)[0]+" "+list.get(i)[1]);
                }
                list = new DepartmentDao().getIdAndName();
                for(int i=0; i<list.size(); i++){
                    departmentBox.addItem(list.get(i)[0]+" "+list.get(i)[1]);
                }

                typeBox.addItem("必修课");
                typeBox.addItem("选修课");
            }
        };
        return operation;
    }

    @Override
    protected void createInsertAction() {
        setInsertAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseEntity course = courseOperation.getData();
                if(course.getId().length() == 0){
                    JOptionPane.showMessageDialog(null,"请输入要添加的信息！");
                    return;
                }
                CourseDao courseDao = new CourseDao();
                if(courseDao.save(course)){
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
                CourseEntity course = courseOperation.getData();
                CourseDao courseDao = new CourseDao();
                if(courseDao.update(id,course)){
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
                CourseDao courseDao = new CourseDao();
                if(courseDao.delete(id)){
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
        List<CourseEntity> list = new CourseDao().getList();
        courseOperation.setList(list);
        String[] title = {"课程代码："};
        DefaultTableModel model = new DefaultTableModel(title,list.size());
        for(int i=0; i<list.size(); i++){
            model.setValueAt(list.get(i).getId(),i,0);
        }
        setTableModel(model);
    }

    @Override
    protected void reload() {
        initData();
        courseOperation.setNull();
        repaint();
    }
}
