package com.window;

import com.dao.CollegeDao;
import com.entity.CollegeEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CollegeJDialog extends Window<CollegeEntity> {

    private final Dimension operationSize;
    private List<CollegeEntity> collegeList;
    private OperationPane<CollegeEntity> collegeOperation;

    public CollegeJDialog(){
        super(new Dimension(600,500),"学院信息管理",false);
        operationSize = new Dimension(getWidth()-150-30,getHeight()-140);
        collegeOperation = createOperation();
        setOperationPane("学院信息",collegeOperation);

        initData();
        createInsertAction();
        createUpdateAction();
        createDeleteAction();
    }

    protected OperationPane<CollegeEntity> createOperation(){
        OperationPane<CollegeEntity> college = new OperationPane<CollegeEntity>(operationSize) {
            private JTextField nameField;
            private JTextField idField;

            @Override
            public void InitData(String id) {
                for(int i=0; i<list.size(); i++){
                    CollegeEntity college = list.get(i);
                    if(college.getId().equals(id)){
                        idField.setText(id);
                        nameField.setText(college.getName());
                    }
                }
            }

            @Override
            public CollegeEntity getData() {
                CollegeEntity college = new CollegeEntity();
                college.setId(idField.getText());
                college.setName(nameField.getText());
                return college;
            }

            @Override
            public void setNull() {
                idField.setText(null);
                nameField.setText(null);
            }

            @Override
            protected void InitPane() {
                JLabel idLab = new JLabel("学院编号：");
                idLab.setPreferredSize(labSize);
                idField = new JTextField();
                idField.setPreferredSize(fieldSize1);
                add(idLab);
                add(idField);
                JLabel nameLab = new JLabel("学院名称：");
                nameLab.setPreferredSize(labSize);
                nameField = new JTextField();
                nameField.setPreferredSize(fieldSize1);
                add(nameLab);
                add(nameField);
            }

            @Override
            public void initBox() {

            }
        };
        return college;
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
                CollegeEntity college = collegeOperation.getData();
                if(college.getId().length() == 0){
                    JOptionPane.showMessageDialog(null,"请输入要添加的信息");
                    return;
                }
                CollegeDao collegeDao = new CollegeDao();
                if(collegeDao.insert(college)){
                    JOptionPane.showMessageDialog(null,"添加成功");
                    reload();
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
                CollegeEntity college = collegeOperation.getData();
                CollegeDao collegeDao = new CollegeDao();
                if(collegeDao.update(id,college)){
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
                CollegeDao collegeDao = new CollegeDao();
                if(collegeDao.delete(id)){
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
        CollegeDao collegeDao = new CollegeDao();
        collegeList = collegeDao.getList();
        collegeOperation.setList(collegeList);
        String[] title = {"学院编号"};
        DefaultTableModel model = new DefaultTableModel(title,collegeList.size());
        for(int i=0; i<collegeList.size(); i++){
            model.setValueAt(collegeList.get(i).getId(),i,0);
        }
        setTableModel(model);
    }

    @Override
    protected void reload() {
        initData();
        collegeOperation.setNull();
        repaint();
    }
}
