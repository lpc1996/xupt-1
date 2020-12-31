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
        initData();
        majorOperation.InitPane();
        setOperationPane("专业信息",majorOperation);

        createInsertAction();
        createUpdateAction();
        createDeleteAction();
    }

    @Override
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

                setNull();
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
                if(majorDao.insert(major)){
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
        List<String> comments = majorDao.getComments();
        majorOperation.setList(majorList,comments);
        String[] title = {comments.get(0)};
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
        repaint();
    }
}
