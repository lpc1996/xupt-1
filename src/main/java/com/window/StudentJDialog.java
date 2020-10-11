package com.window;

import com.DateChooser;
import com.dao.*;
import com.entity.BaseInfoEntity;
import com.entity.CollegeEntity;
import com.entity.StudentEntity;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class StudentJDialog extends Window<StudentEntity>{
    private List<StudentEntity> studentList;
    private List<BaseInfoEntity> baseInfoList;
    private Dimension operationSize;
    private OperationPane<StudentEntity> studenOperationPane;
    private OperationPane<BaseInfoEntity> baseInfoOperationPane;

    public StudentJDialog(){
        super(new Dimension(1000,500),"学生信息管理",true);
        operationSize = new Dimension(getWidth()-150-30,getHeight()-140);

        studenOperationPane = createStudentOperationPane();
        baseInfoOperationPane = createBaseInfoOpeartionPane(operationSize);
        setOperationPane("基础信息",baseInfoOperationPane);
        setOperationPane("学籍信息",studenOperationPane);
        initData();
        createInsertAction();
        createUpdateAction();
        createDeleteAction();
        createRefreshAction();
    }

    private void initData(){
        StudentDao studentDao = new StudentDao();
        studentList = studentDao.getList();
        BaseInfoDao baseInfoDao = new BaseInfoDao();
        baseInfoList = baseInfoDao.getList("student");
        baseInfoOperationPane.setList(baseInfoList);
        studenOperationPane.setList(studentList);
        String[] title = new String[1];
        title[0] = "学/工号";
        DefaultTableModel model = new DefaultTableModel(title,studentList.size());
        for(int i=0; i<studentList.size(); i++){
            model.setValueAt(studentList.get(i).getId()+"",i,0);
        }
        setTableModel(model);
    }

    private OperationPane<StudentEntity> createStudentOperationPane(){
        OperationPane<StudentEntity> studentOperation = new OperationPane<StudentEntity>(operationSize) {
            private JComboBox<String> departmentBox;
            private JComboBox<String> collegeBox;
            private JTextField idField;
            private JComboBox<String> educationBox;
            private JComboBox<String> typeBox;
            private JComboBox<String> cultureBox;
            private JComboBox classBox;
            private DateChooser dateChooser;
            private JTextField yearText;
            private JComboBox gradeBox;
            private JComboBox majorBox;

            @Override
            public void InitData(String id) {
                for(int i=0; i<list.size(); i++){
                    StudentEntity studentEntity = list.get(i);
                    if(studentEntity.getId().equals(id)){
                        idField.setText(id);
                        yearText.setText(studentEntity.getYear().toString());
                        collegeBox.setSelectedItem(equals(studentEntity.getCollege(),collegeBox));
                        departmentBox.setSelectedItem(equals(studentEntity.getDepartment(),departmentBox));
                        majorBox.setSelectedItem(equals(studentEntity.getMajor(),majorBox));
                        gradeBox.setSelectedItem(equals(studentEntity.getGrade(),gradeBox));
                        classBox.setSelectedItem(equals(studentEntity.getClazz(),classBox));
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
                studentEntity.setId(idField.getText());
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
                idField.setText(null);
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
                JLabel idLab = new JLabel("学号：");
                idLab.setPreferredSize(labSize);
                idField = new JTextField();
                idField.setPreferredSize(fieldSize);
                add(idLab);
                add(idField);
                JLabel yearLab = new JLabel("入学时间*：");
                yearLab.setPreferredSize(labSize);;
                yearText = new JTextField();
                dateChooser = DateChooser.getInstance("yyyy-MM-dd");
                dateChooser.register(yearText);
                yearText.setPreferredSize(fieldSize);
                add(yearLab);
                add(yearText);
                JLabel collegeLab = new JLabel("所属学院*：");
                collegeLab.setPreferredSize(labSize);
                collegeBox = new JComboBox<String>();
                collegeBox.setPreferredSize(fieldSize);
                add(collegeLab);
                add(collegeBox);
                JLabel departmentLab = new JLabel("所属系/部*：");
                departmentLab.setPreferredSize(labSize);
                departmentBox = new JComboBox<String>();
                departmentBox.setPreferredSize(fieldSize);
                add(departmentLab);
                add(departmentBox);
                JLabel majorLab = new JLabel("专业*：");
                majorLab.setPreferredSize(labSize);
                majorBox = new JComboBox();
                majorBox.setPreferredSize(fieldSize);
                add(majorLab);
                add(majorBox);
                JLabel gradeLab = new JLabel("年级*：");
                gradeLab.setPreferredSize(labSize);;
                gradeBox = new JComboBox();
                gradeBox.setPreferredSize(fieldSize);
                add(gradeLab);
                add(gradeBox);
                JLabel classLab = new JLabel("班级*：");
                classLab.setPreferredSize(labSize);;
                classBox = new JComboBox();
                classBox.setPreferredSize(fieldSize);
                add(classLab);
                add(classBox);
                JLabel cultureLab = new JLabel("培养层次*：");
                cultureLab.setPreferredSize(labSize);
                cultureBox = new JComboBox<String>();
                cultureBox.setPreferredSize(fieldSize);
                add(cultureLab);
                add(cultureBox);
                JLabel typeLab = new JLabel("学生类别*：");
                typeLab.setPreferredSize(labSize);
                typeBox = new JComboBox<String>();
                typeBox.setPreferredSize(fieldSize);
                add(typeLab);
                add(typeBox);
                JLabel EducationLab = new JLabel("学历：");
                EducationLab.setPreferredSize(labSize);
                educationBox = new JComboBox<String>();
                educationBox.setPreferredSize(fieldSize);
                add(EducationLab);
                add(educationBox);

                initBox();
            }

            private void initBox(){
                List list = null;
                CollegeDao collegeDao = new CollegeDao();
                list = collegeDao.getList();
                for(int i=0; i<list.size(); i++){
                    CollegeEntity collegeEntity = (CollegeEntity) list.get(i);
                    collegeBox.addItem(collegeEntity.getId()+" "+collegeEntity.getName());
                }
                collegeDao = null;
                DepartmentDao departmentDao = new DepartmentDao();
                list = departmentDao.getIdAndName();
                for(int i=0; i<list.size(); i++){
                    Object[] departmentEntity = (Object[]) list.get(i);
                    departmentBox.addItem(departmentEntity[0]+" "+departmentEntity[1]);
                }
                list = new MajorDao().getIdAndName();
                for(int i=0; i<list.size(); i++){
                    Object[] obj = (Object[]) list.get(i);
                    majorBox.addItem(obj[0]+" "+obj[1]);
                }
                list = new SemesterDao().getIdAndName();
                for(int i=0; i<list.size(); i++){
                    Object[] obj = (Object[]) list.get(i);
                    gradeBox.addItem(obj[0]+" "+obj[1]);
                }
                list = new TeamDao().getIdAndName();
                for(int i=0; i<list.size(); i++){
                    Object[] obj = (Object[]) list.get(i);
                    classBox.addItem(obj[0]+" "+obj[1]);
                }

                cultureBox.addItem("专科");
                cultureBox.addItem("本科");
                cultureBox.addItem("硕士");
                cultureBox.addItem("博士");

                typeBox.addItem("普通专科生");
                typeBox.addItem("普通本科生");
                typeBox.addItem("硕士研究生");
                typeBox.addItem("博士");

                educationBox.addItem("小学");
                educationBox.addItem("初中");
                educationBox.addItem("高中");
                educationBox.addItem("本科");
                educationBox.addItem("专科");
                educationBox.addItem("硕士");
                educationBox.addItem("博士");
                educationBox.addItem(" ");
            }
        };
        return studentOperation;
    }

    public static void main(String[] argv){
        StudentJDialog studentJDialog = new StudentJDialog();
        studentJDialog.setVisible(true);
    }

    @Override
    protected void createInsertAction() {
        setInsertAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BaseInfoEntity baseInfoEntity = baseInfoOperationPane.getData();
                StudentEntity studentEntity = studenOperationPane.getData();
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
                BaseInfoEntity baseInfoEntity = baseInfoOperationPane.getData();
                StudentEntity studentEntity = studenOperationPane.getData();
                BaseInfoDao baseInfoDao = new BaseInfoDao();
                StudentDao studentDao = new StudentDao();
                if(baseInfoDao.update(id,baseInfoEntity) && studentDao.update(id,studentEntity)){
                    JOptionPane.showMessageDialog(null,"修改成功");
                    initData();
                    repaint();
                    studenOperationPane.setNull();
                    baseInfoOperationPane.setNull();
                }else{
                    JOptionPane.showMessageDialog(null,"修改失败");
                }
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
                BaseInfoDao baseInfoDao = new BaseInfoDao();
                StudentDao studentDao = new StudentDao();
                if(studentDao.delete(id) && baseInfoDao.delete(id)){
                    JOptionPane.showMessageDialog(null,"删除成功");
                    initData();
                    repaint();
                }else{
                    JOptionPane.showMessageDialog(null,"删除失败");
                }
            }
        });
    }

    @Override
    protected void createRefreshAction() {
        setRefreshAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initData();
                baseInfoOperationPane.setNull();
                studenOperationPane.setNull();
                repaint();
            }
        });
    }
}
