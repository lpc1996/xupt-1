package com.window;

import com.DateChooser;
import com.dao.STDao;
import com.dao.SYDao;
import com.entity.SchoolTremEntity;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class StJDialog extends Window<SchoolTremEntity> {

    private final OperationPane<SchoolTremEntity> stOperation;
    private final Dimension operationSize;

    public StJDialog(){
        super(new Dimension(600,500),"学期信息管理",false);
        operationSize = new Dimension(getWidth()-150-30,getHeight()-140);
        stOperation = createOperation();
        initData();
        stOperation.InitPane();
        setOperationPane("学期信息",stOperation);

        createInsertAction();
        createUpdateAction();
        createDeleteAction();
    }

    @Override
    protected OperationPane<SchoolTremEntity> createOperation() {
        OperationPane<SchoolTremEntity> operation = new OperationPane<SchoolTremEntity>(operationSize) {
            private JComboBox<String> syBox;
            private JTextField endField;
            private JTextField beginField;
            private JTextField nameField;
            private JTextField idField;

            @Override
            public void InitData(String id) {
                for(int i=0; i<list.size(); i++){
                    if(list.get(i).getId().equals(id)){
                        SchoolTremEntity st = list.get(i);
                        idField.setText(st.getId());
                        nameField.setText(st.getName());
                        syBox.setSelectedItem(equals(st.getSchoolYear(),syBox));
                        beginField.setText(st.getBegin().toString());
                        endField.setText(st.getEnd().toString());
                    }
                }
            }

            @Override
            public SchoolTremEntity getData() {
                SchoolTremEntity st = new SchoolTremEntity();
                st.setId(idField.getText());
                st.setName(nameField.getText());
                st.setSchoolYear(split(syBox.getSelectedItem().toString() ));
                try {
                    st.setBegin(new Date( new SimpleDateFormat("yyyy-MM-dd").parse(beginField.getText()).getTime()));
                    st.setEnd(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(endField.getText()).getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    return null;
                }
                return st;
            }

            @Override
            public void setNull() {
                idField.setText(null);
                nameField.setText(null);
                syBox.setSelectedIndex(-1);
                beginField.setText(null);
                endField.setText(null);
            }

            @Override
            protected void InitPane() {
                createJLabel(comments.size());
                JLabel idLab = new JLabel("学年编号：");
                idLab.setPreferredSize(labSize);
                idField = new JTextField();
                idField.setPreferredSize(fieldSize1);
                add(labList.get(0));
                add(idField);
                JLabel nameLab = new JLabel("学年名称：");
                nameLab.setPreferredSize(labSize);
                nameField = new JTextField();
                nameField.setPreferredSize(fieldSize1);
                add(labList.get(1));
                add(nameField);
                JLabel syLab = new JLabel("所属学年：");
                syLab.setPreferredSize(labSize);
                syBox = new JComboBox<String>();
                syBox.setPreferredSize(fieldSize1);
                add(labList.get(2));
                add(syBox);
                JLabel beginLab = new JLabel("起始时间：");
                beginLab.setPreferredSize(labSize);
                beginField = new JTextField();
                beginField.setPreferredSize(fieldSize1);
                DateChooser.getInstance("yyyy-MM-dd").register(beginField);
                add(labList.get(3));
                add(beginField);
                JLabel endLab = new JLabel("结束时间：");
                endLab.setPreferredSize(labSize);
                endField = new JTextField();
                endField.setPreferredSize(fieldSize1);
                DateChooser.getInstance("yyyy-MM-dd").register(endField);
                add(labList.get(4));
                add(endField);

                initBox();
            }

            @Override
            public void initBox() {
                for(int i=0; i<labList.size(); i++){
                    labList.get(i).setText(comments.get(i)+"：");
                }
                List<Object[]> list = new SYDao().getIdAndName();
                syBox.removeAllItems();
                for(int i=0; i<list.size(); i++){
                    syBox.addItem(list.get(i)[0]+" "+list.get(i)[1]);
                }

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
                SchoolTremEntity st = stOperation.getData();
                if(st.getId().length() == 0){
                    JOptionPane.showMessageDialog(null,"请输入要添加的信息！");
                    return;
                }
                STDao stDao = new STDao();
                if(stDao.save(st)){
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
                SchoolTremEntity st = stOperation.getData();
                STDao stDao = new STDao();
                if(stDao.update(id,st)){
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
                STDao stDao = new STDao();
                if(stDao.delete(id)){
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
        STDao stDao = new STDao();
        List<SchoolTremEntity> list = stDao.getList();
        List<String> comments = stDao.getComments();
        stOperation.setList(list,comments);
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
        stOperation.initBox();
        repaint();
    }
}
