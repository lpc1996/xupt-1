package com.window;

import com.DateChooser;
import com.dao.SYDao;
import com.entity.SchoolYearEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class SyJDialog extends Window<SchoolYearEntity> {

    private OperationPane<SchoolYearEntity> syOperation;
    private Dimension operationSize;

    public SyJDialog(){
        super(new Dimension(600,500),"学年信息管理",false);
        operationSize = new Dimension(getWidth()-150-30,getHeight()-140);
        syOperation = createOperation();
        initData();
        syOperation.InitPane();
        setOperationPane("学年信息",syOperation);


        createInsertAction();
        createUpdateAction();
        createDeleteAction();
    }

    @Override
    protected OperationPane<SchoolYearEntity> createOperation() {
        OperationPane<SchoolYearEntity> operation = new OperationPane<SchoolYearEntity>(operationSize) {
            private JTextField endField;
            private JTextField beginField;
            private JTextField idField;
            private JTextField nameField;

            @Override
            public void InitData(String id) {
                for(int i=0; i<list.size(); i++){
                    if(list.get(i).getId().equals(id)){
                        SchoolYearEntity sy = list.get(i);
                        idField.setText(sy.getId());
                        nameField.setText(sy.getName());
                        beginField.setText(sy.getBegin().toString());
                        endField.setText(sy.getEnd().toString());
                    }
                }
            }

            @Override
            public SchoolYearEntity getData() {
                SchoolYearEntity sy = new SchoolYearEntity();
                sy.setId(idField.getText());
                sy.setName(nameField.getText());
                try {
                    sy.setBegin(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(beginField.getText()).getTime() ));
                    sy.setEnd(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(endField.getText()).getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"请输入正确的日期！");
                    return null;
                }
                return sy;
            }

            @Override
            public void setNull() {
                idField.setText(null);
                nameField.setText(null);
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
                DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
                JLabel beginLab = new JLabel("起始时间：");
                beginLab.setPreferredSize(labSize);
                beginField = new JTextField();
                beginField.setPreferredSize(fieldSize1);
                DateChooser.getInstance("yyyy-MM-dd").register(beginField);
                add(labList.get(2));
                add(beginField);
                JLabel endLab = new JLabel("结束时间：");
                endLab.setPreferredSize(labSize);
                endField = new JTextField();
                endField.setPreferredSize(fieldSize1);
                DateChooser.getInstance("yyyy-MM-dd").register(endField);
                add(labList.get(3));
                add(endField);

                initBox();
            }

            @Override
            public void initBox() {
                for(int i=0; i<labList.size(); i++){
                    labList.get(i).setText(comments.get(i)+"：");
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
                SchoolYearEntity sy = syOperation.getData();
                if(sy.getId().length() == 0){
                    JOptionPane.showMessageDialog(null,"请输入要添加的信息！");
                    return;
                }
                SYDao syDao = new SYDao();
                if(syDao.insert(sy)){
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
                SchoolYearEntity sy = syOperation.getData();
                SYDao syDao = new SYDao();
                if(syDao.update(id,sy)){
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
                SYDao syDao = new SYDao();
                if(syDao.delete(id)){
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
        SYDao syDao = new SYDao();
        List<SchoolYearEntity> list = syDao.getList();
        List<String> comments = syDao.getComments();
        syOperation.setList(list,comments);
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
        syOperation.initBox();
        repaint();
    }
}
