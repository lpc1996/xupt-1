package com.window;

import com.DateChooser;
import com.dao.SYDao;
import com.entity.SchoolYearEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author 濃霧-遠方
 * @date 2020/07/17
 */
public class SyJDialog extends Window<SchoolYearEntity> {

    private final OperationPane<SchoolYearEntity> syOperation;
    private final Dimension operationSize;

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
        return new OperationPane<>(operationSize) {
            private JTextField endField;
            private JTextField beginField;
            private JTextField idField;
            private JTextField nameField;

            @Override
            public void InitData(String id) {
                for (SchoolYearEntity schoolYearEntity : list) {
                    if (schoolYearEntity.getId().equals(id)) {
                        idField.setText(schoolYearEntity.getId());
                        nameField.setText(schoolYearEntity.getName());
                        beginField.setText(schoolYearEntity.getBegin().toString());
                        endField.setText(schoolYearEntity.getEnd().toString());
                    }
                }
            }

            @Override
            public SchoolYearEntity getData() {
                SchoolYearEntity sy = new SchoolYearEntity();
                sy.setId(idField.getText());
                sy.setName(nameField.getText());
                try {
                    sy.setBegin(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(beginField.getText()).getTime()));
                    sy.setEnd(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(endField.getText()).getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "请输入正确的日期！");
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
                idField = new JTextField();
                idField.setPreferredSize(fieldSize1);
                add(labList.get(0));
                add(idField);
                nameField = new JTextField();
                nameField.setPreferredSize(fieldSize1);
                add(labList.get(1));
                add(nameField);
                beginField = new JTextField();
                beginField.setPreferredSize(fieldSize1);
                DateChooser.getInstance("yyyy-MM-dd").register(beginField);
                add(labList.get(2));
                add(beginField);
                endField = new JTextField();
                endField.setPreferredSize(fieldSize1);
                DateChooser.getInstance("yyyy-MM-dd").register(endField);
                add(labList.get(3));
                add(endField);

                initBox();
            }

            @Override
            public void initBox() {
                for (int i = 0; i < labList.size(); i++) {
                    labList.get(i).setText(comments.get(i) + "：");
                }

                setNull();
            }
        };
    }

    @Override
    protected void createInsertAction() {
        setInsertAction(e -> {
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
        });
    }

    @Override
    protected void createUpdateAction() {
        setUpdateAction(e -> {
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
        });
    }

    @Override
    protected void createDeleteAction() {
        setDeleteAction(e -> {
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
