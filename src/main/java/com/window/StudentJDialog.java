package com.window;

import com.DateChooser;
import com.dao.*;
import com.entity.BaseInfoEntity;
import com.entity.CollegeEntity;
import com.entity.StudentEntity;
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
public class StudentJDialog extends Window<StudentEntity>{
    private final Dimension operationSize;
    private final OperationPane<StudentEntity> studentOperation;
    private final OperationPane<BaseInfoEntity> baseInfoOperation;

    public StudentJDialog(){
        super(new Dimension(1000,500),"学生信息管理",true);
        operationSize = new Dimension(getWidth()-150-30,getHeight()-140);

        studentOperation = createOperation();
        baseInfoOperation = createBaseInfoOpeartion(operationSize);
        initData();
        studentOperation.InitPane();
        baseInfoOperation.InitPane();
        setOperationPane("基础信息",baseInfoOperation);
        setOperationPane("学籍信息",studentOperation);

        createInsertAction();
        createUpdateAction();
        createDeleteAction();
    }

    @Override
    protected void initData(){
        StudentDao studentDao = new StudentDao();
        List<StudentEntity> studentList = studentDao.getList();
        BaseInfoDao baseInfoDao = new BaseInfoDao();
        List<BaseInfoEntity> baseInfoList = baseInfoDao.getList("student");
        List<String> baseComments = baseInfoDao.getComments();
        baseInfoOperation.setList(baseInfoList,baseComments);
        studentOperation.setList(studentList,studentDao.getComments());
        String[] title = {baseComments.get(1)};
        DefaultTableModel model = new DefaultTableModel(title, studentList.size());
        for(int i = 0; i< studentList.size(); i++){
            model.setValueAt(studentList.get(i).getId()+"",i,0);
        }
        setTableModel(model);
    }

    @Override
    protected void reload() {
        initData();
        studentOperation.initBox();
        baseInfoOperation.initBox();
        repaint();
    }

    @Override
    protected OperationPane<StudentEntity> createOperation(){
        return new OperationPane<>(operationSize) {
            private JComboBox<String> departmentBox;
            private JComboBox<String> collegeBox;
            private JComboBox<String> educationBox;
            private JComboBox<String> typeBox;
            private JComboBox<String> cultureBox;
            private JComboBox<String> classBox;
            private JTextField yearText;
            private JComboBox<String> gradeBox;
            private JComboBox<String> majorBox;

            @Override
            public void InitData(String id) {
                for (StudentEntity studentEntity : list) {
                    if (studentEntity.getId().equals(id)) {
                        yearText.setText(studentEntity.getYear().toString());
                        collegeBox.setSelectedItem(equals(studentEntity.getCollege(), collegeBox));
                        departmentBox.setSelectedItem(equals(studentEntity.getDepartment(), departmentBox));
                        majorBox.setSelectedItem(equals(studentEntity.getMajor(), majorBox));
                        gradeBox.setSelectedItem(equals(studentEntity.getGrade(), gradeBox));
                        classBox.setSelectedItem(equals(studentEntity.getClazz(), classBox));
                        cultureBox.setSelectedItem(studentEntity.getCultureLevel());
                        typeBox.setSelectedItem(studentEntity.getStudentType());
                        educationBox.setSelectedItem(studentEntity.getEducation());
                        break;
                    }
                }
            }

            @Override
            public StudentEntity getData() {
                StudentEntity studentEntity = new StudentEntity();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d");
                try {
                    studentEntity.setYear(new Date( sdf.parse(yearText.getText()).getTime() ) );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                studentEntity.setCollege(split(collegeBox.getSelectedItem()+""));
                studentEntity.setDepartment(split(departmentBox.getSelectedItem()+""));
                studentEntity.setMajor(split(majorBox.getSelectedItem()+""));
                studentEntity.setGrade(split(gradeBox.getSelectedItem()+""));
                studentEntity.setClazz(split(classBox.getSelectedItem()+""));
                studentEntity.setCultureLevel(cultureBox.getSelectedItem()+"");
                studentEntity.setStudentType(typeBox.getSelectedItem()+"");
                studentEntity.setEducation(educationBox.getSelectedItem()+"");
                return studentEntity;
            }

            @Override
            public void setNull() {
                yearText.setText(null);
                collegeBox.setSelectedIndex(-1);
                departmentBox.setSelectedIndex(-1);
                majorBox.setSelectedIndex(-1);
                gradeBox.setSelectedIndex(-1);
                classBox.setSelectedIndex(-1);
                cultureBox.setSelectedIndex(-1);
                typeBox.setSelectedIndex(-1);
                educationBox.setSelectedIndex(-1);
            }

            @Override
            protected void InitPane() {
                createJLabel(comments.size()-1);
                yearText = new JTextField();
                DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
                dateChooser.register(yearText);
                yearText.setPreferredSize(fieldSize);
                add(labList.get(0));
                add(yearText);
                collegeBox = new JComboBox<>();
                collegeBox.setPreferredSize(fieldSize);
                add(labList.get(1));
                add(collegeBox);
                departmentBox = new JComboBox<>();
                departmentBox.setPreferredSize(fieldSize);
                add(labList.get(2));
                add(departmentBox);
                majorBox = new JComboBox<>();
                majorBox.setPreferredSize(fieldSize);
                add(labList.get(3));
                add(majorBox);
                gradeBox = new JComboBox<>();
                gradeBox.setPreferredSize(fieldSize);
                add(labList.get(4));
                add(gradeBox);
                classBox = new JComboBox<>();
                classBox.setPreferredSize(fieldSize);
                add(labList.get(5));
                add(classBox);
                cultureBox = new JComboBox<>();
                cultureBox.setPreferredSize(fieldSize);
                add(labList.get(6));
                add(cultureBox);
                typeBox = new JComboBox<>();
                typeBox.setPreferredSize(fieldSize);
                add(labList.get(7));
                add(typeBox);
                educationBox = new JComboBox<>();
                educationBox.setPreferredSize(fieldSize);
                add(labList.get(8));
                add(educationBox);

                initBox();
            }

            @Override
            public void initBox(){
                for(int i=0; i<labList.size(); i++){
                    labList.get(i).setText(comments.get(i+1)+"：");
                }
                collegeBox.removeAllItems();
                List list = new CollegeDao().getList();
                for (Object o : list) {
                    CollegeEntity collegeEntity = (CollegeEntity) o;
                    collegeBox.addItem(collegeEntity.getId() + " " + collegeEntity.getName());
                }
                departmentBox.removeAllItems();
                list = new DepartmentDao().getIdAndName();
                for (Object o : list) {
                    Object[] departmentEntity = (Object[]) o;
                    departmentBox.addItem(departmentEntity[0] + " " + departmentEntity[1]);
                }
                majorBox.removeAllItems();
                list = new MajorDao().getIdAndName();
                for (Object o : list) {
                    Object[] obj = (Object[]) o;
                    majorBox.addItem(obj[0] + " " + obj[1]);
                }
                gradeBox.removeAllItems();
                list = new SemesterDao().getIdAndName();
                for (Object o : list) {
                    Object[] obj = (Object[]) o;
                    gradeBox.addItem(obj[0] + " " + obj[1]);
                }
                classBox.removeAllItems();
                list = new TeamDao().getIdAndName();
                for (Object o : list) {
                    Object[] obj = (Object[]) o;
                    classBox.addItem(obj[0] + " " + obj[1]);
                }

                cultureBox.removeAllItems();
                cultureBox.addItem("专科");
                cultureBox.addItem("本科");
                cultureBox.addItem("硕士");
                cultureBox.addItem("博士");

                typeBox.removeAllItems();
                typeBox.addItem("普通专科生");
                typeBox.addItem("普通本科生");
                typeBox.addItem("硕士研究生");
                typeBox.addItem("博士");

                educationBox.removeAllItems();
                educationBox.addItem("小学");
                educationBox.addItem("初中");
                educationBox.addItem("高中");
                educationBox.addItem("本科");
                educationBox.addItem("专科");
                educationBox.addItem("硕士");
                educationBox.addItem("博士");

                setNull();
            }
        };
    }

    public static void main(String[] argv){
        StudentJDialog studentJDialog = new StudentJDialog();
        studentJDialog.setVisible(true);
    }

    @Override
    protected void createInsertAction() {
        setInsertAction(e -> {
            BaseInfoEntity baseInfoEntity = baseInfoOperation.getData();
            StudentEntity studentEntity = studentOperation.getData();
            studentEntity.setId(baseInfoEntity.getuId());
            if(baseInfoEntity.getuId().length() == 0){
                JOptionPane.showMessageDialog(null,"请输入要添加的信息！");
                return;
            }
            BaseInfoDao baseInfoDao = new BaseInfoDao();
            StudentDao studentDao = new StudentDao();
            if(baseInfoDao.insert(baseInfoEntity) && studentDao.insert(studentEntity)){
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
            BaseInfoEntity baseInfoEntity = baseInfoOperation.getData();
            StudentEntity studentEntity = studentOperation.getData();
            studentEntity.setId(baseInfoEntity.getuId());
            BaseInfoDao baseInfoDao = new BaseInfoDao();
            StudentDao studentDao = new StudentDao();
            if(baseInfoDao.update(id,baseInfoEntity) && studentDao.update(id,studentEntity)){
                JOptionPane.showMessageDialog(null,"修改成功");
            }else{
                JOptionPane.showMessageDialog(null,"修改失败");
            }
            reload();
        });
    }

    @Override
    protected void createDeleteAction() {
        setDeleteAction(e-> {
            int index = getSelectRow();
            if(index == -1){
                JOptionPane.showMessageDialog(null,"请选中一行数据");
                return;
            }
            String id = getValueAt(index)+"";
            BaseInfoDao baseInfoDao = new BaseInfoDao();
            StudentDao studentDao = new StudentDao();
            if(studentDao.delete(id) && baseInfoDao.delete(id)){
                JOptionPane.showMessageDialog(null,"删除成功");
                reload();
            }else{
                JOptionPane.showMessageDialog(null,"删除失败");
            }
        });
    }
}
