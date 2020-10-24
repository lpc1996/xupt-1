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
        initData();
        courseOperation.InitPane();
        setOperationPane("课程信息",courseOperation);

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
                createJLabel(comments.size());
                idField = new JTextField();
                idField.setPreferredSize(fieldSize1);
                add(labList.get(0));
                add(idField);
                nameField = new JTextField();
                nameField.setPreferredSize(fieldSize1);
                add(labList.get(1));
                add(nameField);
                collegeBox = new JComboBox<String>();
                collegeBox.setPreferredSize(fieldSize1);
                add(labList.get(2));
                add(collegeBox);
                departmentBox = new JComboBox<String>();
                departmentBox.setPreferredSize(fieldSize1);
                add(labList.get(3));
                add(departmentBox);
                typeBox = new JComboBox<String>();
                typeBox.setPreferredSize(fieldSize1);
                add(labList.get(4));
                add(typeBox);
                creditField = new JTextField();
                creditField.setPreferredSize(fieldSize1);
                add(labList.get(5));
                add(creditField);

                initBox();
            }

            @Override
            public void initBox() {
                for(int i=0; i<labList.size(); i++){
                    labList.get(i).setText(comments.get(i)+"：");
                }

                List<Object[]> list = new CollegeDao().getIdAndName();
                collegeBox.removeAllItems();
                for(int i=0; i<list.size(); i++){
                    collegeBox.addItem(list.get(i)[0]+" "+list.get(i)[1]);
                }
                list = new DepartmentDao().getIdAndName();
                departmentBox.removeAllItems();
                for(int i=0; i<list.size(); i++){
                    departmentBox.addItem(list.get(i)[0]+" "+list.get(i)[1]);
                }

                typeBox.removeAllItems();
                typeBox.addItem("必修课");
                typeBox.addItem("选修课");

                setNull();
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
        CourseDao courseDao = new CourseDao();
        List<CourseEntity> list = courseDao.getList();
        List<String> comments = courseDao.getComments();
        courseOperation.setList(list,comments);
        String[] title = {comments.get(0)};
        DefaultTableModel model = new DefaultTableModel(title,list.size());
        for(int i=0; i<list.size(); i++){
            model.setValueAt(list.get(i).getId(),i,0);
        }
        setTableModel(model);
    }

    @Override
    protected void reload() {
        initData();
        courseOperation.initBox();
        repaint();
    }
}
