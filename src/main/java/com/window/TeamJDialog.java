package com.window;

import com.dao.*;
import com.entity.TeamEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @author 濃霧-遠方
 * @date 2020/12/28
 */
public class TeamJDialog extends Window<TeamEntity> {

    private final Dimension operationSize;
    private final OperationPane<TeamEntity> teamOption;

    TeamJDialog(){
        super(new Dimension(1000,500),"班级信息管理",false);
        operationSize = new Dimension(getWidth()-150-30,getHeight()-140);
        teamOption = createOperation();
        initData();
        teamOption.InitPane();
        setOperationPane("班级信息",teamOption);
    }
    /**
     * @return
     */
    @Override
    protected OperationPane<TeamEntity> createOperation() {
        OperationPane<TeamEntity> option = new OperationPane<TeamEntity>(operationSize) {
            private JComboBox<String> semesterBox;
            private JComboBox<String> majorBox;
            private JComboBox<String> departmentBox;
            private JComboBox<String> collegeBox;
            private JTextField numberField;
            private JTextField nameField;
            private JTextField idField;

            @Override
            public void InitData(String id) {
                for(TeamEntity teamEntity:list) {
                    if(teamEntity.getId().equals(id)){
                        idField.setText(teamEntity.getId());
                        nameField.setText(teamEntity.getName());
                        numberField.setText(teamEntity.getName()+"");
                        collegeBox.setSelectedItem(equals(teamEntity.getCollegeId(),collegeBox));
                        departmentBox.setSelectedItem(equals(teamEntity.getDepartmentId(),departmentBox));
                        majorBox.setSelectedItem(equals(teamEntity.getMajorId(),majorBox));
                        semesterBox.setSelectedItem(equals(teamEntity.getSemester(),semesterBox));
                    }
                }
            }

            @Override
            public TeamEntity getData() {
                TeamEntity teamEntity = new TeamEntity();
                if(idField.getText().length() != 0){
                    teamEntity.setId(idField.getText());
                }else{
                    return null;
                }
                if(nameField.getText().length() != 0){
                    teamEntity.setName(nameField.getText());
                }else{
                    return null;
                }
                if (numberField.getText().length() != 0){
                    try{
                        teamEntity.setNumber(Integer.parseInt(numberField.getText()));
                    }catch (Exception e){
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null,"请输入正确的班级人数！");
                        return null;
                    }
                }else{
                    return null;
                }
                if(collegeBox.getSelectedIndex() >= 0){
                    teamEntity.setCollegeId(split(collegeBox.getSelectedItem()+""));
                }else{
                    return null;
                }
                if(departmentBox.getSelectedIndex() >= 0){
                    teamEntity.setDepartmentId(split(departmentBox.getSelectedItem()+""));
                }else{
                    return null;
                }
                if(majorBox.getSelectedIndex() >= 0){
                    teamEntity.setMajorId(split(majorBox.getSelectedItem()+""));
                }else{
                    return null;
                }
                if(semesterBox.getSelectedIndex() >= -1){
                    teamEntity.setSemester(semesterBox.getSelectedItem()+"");
                }else{
                    return null;
                }
                return teamEntity;
            }

            @Override
            public void setNull() {
                idField.setText(null);
                nameField.setText(null);
                numberField.setText(0+"");
                collegeBox.setSelectedIndex(-1);
                departmentBox.setSelectedIndex(-1);
                majorBox.setSelectedIndex(-1);
                semesterBox.setSelectedIndex(-1);
            }

            @Override
            protected void InitPane() {
                java.util.List<String> comments = new TeamDao().getComments();
                java.util.List<JLabel> labList = createJLabel(comments);
                idField = new JTextField();
                idField.setPreferredSize(fieldSize);
                add(labList.get(0));
                add(idField);
                nameField = new JTextField();
                nameField.setPreferredSize(fieldSize);
                add(labList.get(1));
                add(nameField);
                numberField = new JTextField();
                numberField.setPreferredSize(fieldSize);
                add(labList.get(2));
                add(numberField);
                collegeBox = new JComboBox<>();
                collegeBox.setPreferredSize(fieldSize);
                add(labList.get(3));
                add(collegeBox);
                departmentBox = new JComboBox<>();
                departmentBox.setPreferredSize(fieldSize);
                add(labList.get(4));
                add(departmentBox);
                majorBox = new JComboBox<>();
                majorBox.setPreferredSize(fieldSize);
                add(labList.get(5));
                add(majorBox);
                semesterBox = new JComboBox<>();
                semesterBox.setPreferredSize(fieldSize);
                add(labList.get(6));
                add(semesterBox);

                initBox();
            }

            @Override
            public void initBox() {
                java.util.List<Object[]> list = new CollegeDao().getIdAndName();
                collegeBox.removeAllItems();
                for(Object[] o:list){
                    collegeBox.addItem(o[0]+" "+o[1]);
                }
                list = new DepartmentDao().getIdAndName();
                departmentBox.removeAllItems();
                for(Object[] o:list){
                    departmentBox.addItem(o[0]+" "+o[1]);
                }
                list = new MajorDao().getIdAndName();
                majorBox.removeAllItems();
                for(Object[] o:list){
                    majorBox.addItem(o[0]+" "+o[1]);
                }
                list = new SemesterDao().getIdAndName();
                semesterBox.removeAllItems();
                for(Object[] o:list){
                    semesterBox.addItem(o[0]+" "+o[1]);
                }
            }
        };
        return option;
    }

    /**
     *
     */
    @Override
    protected void createInsertAction() {

    }

    /**
     *
     */
    @Override
    protected void createUpdateAction() {

    }

    /**
     *
     */
    @Override
    protected void createDeleteAction() {

    }

    /**
     *
     */
    @Override
    protected void initData() {
        TeamDao teamDao = new TeamDao();
        java.util.List<TeamEntity> teamEntities = teamDao.getList();
        java.util.List<String> comments = teamDao.getComments();
        teamOption.setList(teamEntities);
        String[] title = {comments.get(0)};
        DefaultTableModel model = new DefaultTableModel(title,teamEntities.size());
        for(int i=0; i<teamEntities.size(); i++) {
            TeamEntity team = teamEntities.get(i);
            model.setValueAt(team.getId(),i,0);
        }
        setTableModel(model);
    }

    /**
     *
     */
    @Override
    protected void reload() {

    }
}
