package com.window;

import com.dao.CollegeDao;
import com.dao.DepartmentDao;
import com.dao.MajorDao;
import com.entity.MajorEntity;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MajorJDialog extends Window<MajorEntity> {

    private OperationPane<MajorEntity> majorOperation;
    private List<MajorEntity> majorList;
    private Dimension operationSize;

    public MajorJDialog(){
        super(new Dimension(600,500),"专业信息管理",false);
        operationSize = new Dimension(getWidth()-150-30,getHeight()-140);
        majorOperation = createOperation();
        setOperationPane("专业信息",majorOperation);

        initData();
        createInsertAction();
        createUpdateAction();
        createDeleteAction();
    }

    protected OperationPane<MajorEntity> createOperation() {
        OperationPane<MajorEntity> majorOperation = new OperationPane<MajorEntity>(operationSize) {
            private JComboBox<String> departmentBox;
            private JComboBox<String> collegeBox;
            private JTextField nameField;
            private JTextField idField;

            @Override
            public void InitData(String id) {
                for(int i=0; i<list.size(); i++){
                    if(list.get(i).getId().equals(id)){
                        MajorEntity major = list.get(i);
                        idField.setText(major.getId());
                        nameField.setText(major.getName());
                        collegeBox.setSelectedItem(equals(major.getCollegeId(),collegeBox));
                        departmentBox.setSelectedItem(equals(major.getDepartmentId(),departmentBox));
                    }
                }
            }

            @Override
            public MajorEntity getData() {
                MajorEntity major = new MajorEntity();
                major.setId(idField.getText());
                major.setName(nameField.getText());
                major.setCollegeId(split(collegeBox.getSelectedItem()+""));
                major.setDepartmentId(split(departmentBox.getSelectedItem()+""));
                return major;
            }

            @Override
            public void setNull() {
                idField.setText(null);
                nameField.setText(null);
                collegeBox.setSelectedIndex(-1);
                departmentBox.setSelectedIndex(-1);
            }

            @Override
            protected void InitPane() {
                JLabel idLab = new JLabel("专业编号:");
                idLab.setPreferredSize(labSize);
                idField = new JTextField();
                idField.setPreferredSize(fieldSize1);
                add(idLab);
                add(idField);
                JLabel nameLab = new JLabel("专业名称：");
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
                JLabel departmentLab = new JLabel("所属系/部：");
                departmentLab.setPreferredSize(labSize);
                departmentBox = new JComboBox<String>();
                departmentBox.setPreferredSize(fieldSize1);
                add(departmentLab);
                add(departmentBox);

                initBox();
            }

            @Override
            public void initBox() {
                List<Object[]> list = new CollegeDao().getIdAndName();
                collegeBox.removeAllItems();
                for(int i=0; i<list.size(); i++){
                    collegeBox.addItem(list.get(i)[0]+" "+list.get(i)[1]);
                }
                list = new DepartmentDao().getIdAndName();
                for(int i=0; i<list.size(); i++){
                    departmentBox.addItem(list.get(i)[0]+" "+list.get(i)[1]);
                }
            }
        };
        return majorOperation;
    }

    @Override
    protected void createInsertAction() {
        setInsertAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MajorEntity major = majorOperation.getData();
                if(major.getId().length() == 0){
                    JOptionPane.showMessageDialog(null,"请输入要添加的信息！");
                    return;
                }
                MajorDao majorDao = new MajorDao();
                if(majorDao.save(major)){
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
                MajorEntity major = majorOperation.getData();
                MajorDao majorDao = new MajorDao();
                if(majorDao.update(id,major)){
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
                MajorDao majorDao = new MajorDao();
                if(majorDao.delete(id)){
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
        MajorDao majorDao = new MajorDao();
        majorList = majorDao.getList();
        majorOperation.setList(majorList);
        String[] title = {"专业编号"};
        DefaultTableModel model = new DefaultTableModel(title,majorList.size());
        for(int i=0; i<majorList.size(); i++){
            model.setValueAt(majorList.get(i).getId(),i,0);
        }
        setTableModel(model);
    }

    @Override
    protected void reload() {
        initData();
        majorOperation.initBox();
        majorOperation.setNull();
        repaint();
    }
}
