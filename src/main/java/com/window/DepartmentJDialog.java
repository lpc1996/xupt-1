package com.window;

import com.dao.CollegeDao;
import com.dao.DepartmentDao;
import com.entity.DepartmentEntity;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DepartmentJDialog extends Window<DepartmentEntity> {
    private Dimension operationSize;
    private OperationPane<DepartmentEntity> departmentOperation;
    private List<DepartmentEntity> departmentList;

    public DepartmentJDialog() {
        super(new Dimension(600,500),"系/部信息管理",false);
        operationSize = new Dimension(getWidth()-150-30,getHeight()-140);
        departmentOperation = createDepartmentOperation();
        setOperationPane("系/部信息",departmentOperation);

        initData();
        createInsertAction();
        createUpdateAction();
        createDeleteAction();
    }

    private OperationPane<DepartmentEntity> createDepartmentOperation() {
        OperationPane<DepartmentEntity> departmentOperation = new OperationPane<DepartmentEntity>(operationSize) {
            private JComboBox<String> collegeBox;
            private JTextField nameField;
            private JTextField idField;

            @Override
            public void InitData(String id) {
                for(int i=0; i<list.size(); i++){
                    if(list.get(i).getId().equals(id)){
                        DepartmentEntity department = list.get(i);
                        idField.setText(department.getId());
                        nameField.setText(department.getName());
                        collegeBox.setSelectedItem(equals(department.getCollegeId(),collegeBox));
                    }
                }
            }

            @Override
            public DepartmentEntity getData() {
                DepartmentEntity department = new DepartmentEntity();
                department.setId(idField.getText());
                department.setName(nameField.getText());
                department.setCollegeId(split(collegeBox.getSelectedItem()+""));
                return department;
            }

            @Override
            public void setNull() {
                idField.setText(null);
                nameField.setText(null);
                collegeBox.setSelectedIndex(-1);
            }

            @Override
            protected void InitPane() {
                JLabel idLab = new JLabel("系/部编号:");
                idLab.setPreferredSize(labSize);
                idField = new JTextField();
                idField.setPreferredSize(fieldSize1);
                add(idLab);
                add(idField);
                JLabel nameLab = new JLabel("系/部名称：");
                nameLab.setPreferredSize(labSize);
                nameField = new JTextField();
                nameField.setPreferredSize(fieldSize1);
                add(nameLab);
                add(nameField);
                JLabel collegeLab = new JLabel("所属学院：");
                collegeLab.setPreferredSize(labSize);
                collegeBox = new JComboBox<String>();
                collegeBox.setPreferredSize(fieldSize1);
                add(collegeLab);
                add(collegeBox);

                initBox();
            }

            public void initBox(){
                List<Object[]> list = new CollegeDao().getIdAndName();
                collegeBox.removeAllItems();
                for(int i=0; i<list.size(); i++){
                    collegeBox.addItem(list.get(i)[0]+" "+list.get(i)[1]);
                }
            }
        };
        return departmentOperation;
    }

    @Override
    protected void createInsertAction() {
        setInsertAction(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                DepartmentEntity department = departmentOperation.getData();
                if(department.getId().length() == 0){
                    JOptionPane.showMessageDialog(null,"请输入要添加的信息！");
                    return;
                }
                DepartmentDao departmentDao = new DepartmentDao();
                if(departmentDao.insert(department)){
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
                DepartmentEntity department = departmentOperation.getData();
                DepartmentDao departmentDao = new DepartmentDao();
                if(departmentDao.update(id,department)){
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
                DepartmentDao departmentDao = new DepartmentDao();
                if(departmentDao.delete(id)){
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
        DepartmentDao departmentDao = new DepartmentDao();
        departmentList = departmentDao.getList();
        String[] title = {"系/部编号"};
        departmentOperation.setList(departmentList);
        DefaultTableModel model = new DefaultTableModel(title,departmentList.size());
        for(int i=0; i<departmentList.size(); i++) {
            model.setValueAt(departmentList.get(i).getId(),i,0);
        }
        setTableModel(model);
    }

    @Override
    protected void reload() {
        initData();
        departmentOperation.initBox();
        departmentOperation.setNull();
        repaint();
    }
}