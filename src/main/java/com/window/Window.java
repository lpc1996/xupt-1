package com.window;

import com.entity.BaseInfoEntity;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 濃霧-遠方
 * @date 2020/07/03
 */
public abstract class Window <T> extends JFrame {

    private JTextField searchField;
    private JButton searchBtn;
    private JButton insertBtn;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JButton refreshBtn;
    private JTable idTable;
    private JTabbedPane dataPane;
    private boolean judge;

    public Window(Dimension size){
        this(size,null,false);
    }

    public Window(Dimension size,boolean x){
        this(size,null,x);
    }

    public Window(Dimension size,String title,boolean x) {
        setSize(size);
        setTitle(title);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        judge = x;

        setContentPane(createContentPane());
        setRefreshAction();
    }

    protected JPanel createContentPane(){
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));

        dataPane = createdataPane();

        JPanel showPane = new JPanel();
        showPane.setPreferredSize(new Dimension(getWidth()-30,getHeight()-130));
        showPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        showPane.add(createIdTable());
        showPane.add(dataPane);

        contentPane.add(showPane);
        contentPane.add(createBtnPane());

        return contentPane;
    }

    private JScrollPane createIdTable(){
        JScrollPane js = new JScrollPane();
        js.setPreferredSize(new Dimension(120,getHeight()-140));
        idTable = new JTable();
        idTable.addMouseListener(createJTableAction());
        js.setViewportView(idTable);
        return js;
    }

    private JTabbedPane createdataPane(){
        JTabbedPane dataPane = new JTabbedPane(JTabbedPane.TOP);
        dataPane.setPreferredSize(new Dimension(getWidth()-170,getHeight()-140));

        return dataPane;
    }

    private JPanel createBtnPane(){
        JPanel btnPane = new JPanel();
        btnPane.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
        btnPane.setPreferredSize(new Dimension(getWidth()-30,80));

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(getWidth()-130,35));
        searchBtn = new JButton("搜索");
        searchBtn.setPreferredSize(new Dimension(80,35));
        setSearchAction();

        insertBtn = new JButton("添加");
        insertBtn.setPreferredSize(new Dimension(80,35));
        updateBtn = new JButton("修改");
        updateBtn.setPreferredSize(new Dimension(80,35));
        deleteBtn = new JButton("删除");
        deleteBtn.setPreferredSize(new Dimension(80,35));
        refreshBtn = new JButton("刷新");
        refreshBtn.setPreferredSize(new Dimension(80,35));

        btnPane.add(searchField);
        btnPane.add(searchBtn);
        btnPane.add(insertBtn);
        btnPane.add(updateBtn);
        btnPane.add(deleteBtn);
        btnPane.add(refreshBtn);
        return btnPane;
    }

    /**
     * 给insert按钮注册监听器
     * @param action 监听器对象
     */
    protected void setInsertAction(ActionListener action){
        insertBtn.addActionListener(action);
    }

    /**
     * 给update按钮注册监听器
     * @param action 监听器对象
     */
    protected void setUpdateAction(ActionListener action) {
        updateBtn.addActionListener(action);
    }

    /**
     * 给delete按钮注册监听器
     * @param action 监听器对象
     */
    protected void setDeleteAction(ActionListener action) {
        deleteBtn.addActionListener(action);
    }

    /**
     * 给refresh注册监听器
     */
    protected void setRefreshAction() {
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reload();
            }
        });
    }

    /**
     * 给searchBtn注册监听器
     */
    private void setSearchAction(){
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if(searchField.getText().length() == 0 || " ".equals(searchField.getText())) {
                JOptionPane.showMessageDialog(null,"不能匹配空字符串");
                return;
            }
            String string = searchField.getText();
            int i = 0;
            for(; i<getRowCount(); i++){
                if( ((String)getValueAt(i)).equals(string) ){
                    setSelectedRow(i);
                    if(judge){
                        setOperationId(string,1);
                    }
                    setOperationId(string,0);
                    break;
                }
            }
            if(i == getRowCount()){
                JOptionPane.showMessageDialog(null,"找不到字符串:"+string+"，请重新输入正确的线索！");
                return;
            }

            }
        });
    }

    /**
     * 初始化表格数据
     * 将model中的数据显示在表格中
     * @param model 表格数据
     */
    protected void setTableModel(DefaultTableModel model){
        idTable.setModel(model);
    }

    /**
     * 获取选中行的索引
     * @return 如果有一行被选中返回选中行的索引，如果没有一行被选中，返回-1
     */
    protected int getSelectRow(){
        return idTable.getSelectedRow();
    }

    /**
     * 获取row行column列的单元格的数据
     * @param row 要获取数据所在行的位置
     * @return 返回第row行的第0列的数据
     */
    protected Object getValueAt(int row){
        return idTable.getValueAt(row,0);
    }

    /**
     * 设置选中行
     * @param row 要选中的行的位置
     */
    protected void setSelectedRow(int row){
        idTable.setRowSelectionInterval(row,row);
    }

    /**
     * 获取表格行数
     * @return 返回表格行数
     */
    protected int getRowCount(){
        return idTable.getRowCount();
    }

    /**
     * 给选项卡面板添加标题为title内容为operationPane面板的选项卡
     * @param title 标题
     * @param operationPane 要添加的组件
     */
    protected void setOperationPane(String title,OperationPane operationPane){
        dataPane.add(title,operationPane);
    }

    private void setOperationId(String id,int index){
        OperationPane operationPane = (OperationPane) dataPane.getComponentAt(index);
        operationPane.InitData(id);
    }

    protected OperationPane<BaseInfoEntity> createBaseInfoOpeartion(Dimension size){
        OperationPane<BaseInfoEntity> baseInfoEntity = new OperationPane<BaseInfoEntity>(size) {

            private List<JLabel> labList;
            private JComboBox<String> uTypeBox;
            private JTextField telField;
            private JTextField idCardNumField;
            private JComboBox idCardTypeBox;
            private JTextField nativePlaceField;
            private JTextField ageField;
            private JComboBox sexBox;
            private JTextField formarNameField;
            private JTextField nameField;
            private JTextField idField;

            @Override
            public void InitData(String id) {
                BaseInfoEntity baseInfoEntity = null;
                for(int i=0; i < list.size(); i++){
                    baseInfoEntity = list.get(i);
                    if(baseInfoEntity.getuId().equals(id)) {
                        idField.setText(baseInfoEntity.getuId());
                        nameField.setText(baseInfoEntity.getuName());
                        formarNameField.setText(baseInfoEntity.getFormarName());
                        sexBox.setSelectedItem(baseInfoEntity.getSex());
                        ageField.setText(baseInfoEntity.getAge()+"");
                        nativePlaceField.setText(baseInfoEntity.getNativePlace());
                        idCardTypeBox.setSelectedItem(baseInfoEntity.getIdcardType());
                        idCardNumField.setText(baseInfoEntity.getIdcardNum());
                        uTypeBox.setSelectedItem(baseInfoEntity.getuType());
                        telField.setText(baseInfoEntity.getTel());
                        break;
                    }
                }
            }

            @Override
            public BaseInfoEntity getData() {
                BaseInfoEntity baseInfoEntity = new BaseInfoEntity();
                baseInfoEntity.setuId(idField.getText());
                baseInfoEntity.setuName(nameField.getText());
                baseInfoEntity.setFormarName(formarNameField.getText());
                baseInfoEntity.setSex(sexBox.getSelectedItem()+"");
                try {
                    baseInfoEntity.setAge(Integer.parseInt(ageField.getText()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"请输入正确的年龄");
                }
                baseInfoEntity.setNativePlace(nativePlaceField.getText());
                baseInfoEntity.setIdcardType(idCardTypeBox.getSelectedItem()+"");
                baseInfoEntity.setIdcardNum(idCardNumField.getText());
                baseInfoEntity.setuType(uTypeBox.getSelectedItem()+"");
                baseInfoEntity.setTel(telField.getText());
                return baseInfoEntity;
            }

            @Override
            public void setNull() {
                idField.setText(null);
                nameField.setText(null);
                formarNameField.setText(null);
                sexBox.setSelectedIndex(-1);
                ageField.setText(null);
                nativePlaceField.setText(null);
                idCardTypeBox.setSelectedIndex(-1);
                idCardNumField.setText(null);
                uTypeBox.setSelectedIndex(-1);
                telField.setText(null);
            }

            @Override
            protected void InitPane() {
                labList = new ArrayList<JLabel>();
                for(int i=0; i<comments.size()-1; i++){
                    JLabel jLabel = new JLabel();
                    jLabel.setPreferredSize(labSize);
                    labList.add(jLabel);
                }
                idField = new JTextField();
                idField.setPreferredSize(fieldSize);
                add(labList.get(0));
                add(idField);
                nameField = new JTextField();
                nameField.setPreferredSize(fieldSize);
                add(labList.get(1));
                add(nameField);
                formarNameField = new JTextField();
                formarNameField.setPreferredSize(fieldSize);
                add(labList.get(2));
                add(formarNameField);
                sexBox = new JComboBox();
                sexBox.setPreferredSize(fieldSize);
                add(labList.get(3));
                add(sexBox);
                ageField = new JTextField();
                ageField.setPreferredSize(fieldSize);
                add(labList.get(4));
                add(ageField);
                nativePlaceField = new JTextField();
                nativePlaceField.setPreferredSize(fieldSize);
                add(labList.get(5));
                add(nativePlaceField);
                idCardTypeBox = new JComboBox();
                idCardTypeBox.setPreferredSize(fieldSize);;
                add(labList.get(6));
                add(idCardTypeBox);
                idCardNumField = new JTextField();
                idCardNumField.setPreferredSize(fieldSize);
                add(labList.get(7));
                add(idCardNumField);
                uTypeBox = new JComboBox<String>();
                uTypeBox.setPreferredSize(fieldSize);
                add(labList.get(8));
                add(uTypeBox);
                telField = new JTextField();
                telField.setPreferredSize(fieldSize);
                add(labList.get(9));
                add(telField);

                initBox();
            }

            @Override
            public void initBox(){

                for(int i=0; i<labList.size(); i++){
                    labList.get(i).setText(comments.get(i+1)+"：");
                }
                sexBox.removeAllItems();
                sexBox.addItem("男");
                sexBox.addItem("女");
                sexBox.addItem("保密");

                idCardTypeBox.removeAllItems();
                idCardTypeBox.addItem("居民身份证");
                idCardTypeBox.addItem("中华人民共和国护照");

                uTypeBox.removeAllItems();
                uTypeBox.addItem("student");
                uTypeBox.addItem("teacher");
                uTypeBox.addItem("admin");

                setNull();
            }

        };
        return baseInfoEntity;
    }

    private MouseAdapter createJTableAction(){
        return new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                String id = idTable.getValueAt(idTable.getSelectedRow(),0)+"";
                if(judge){
                    setOperationId(id,1);
                }
                setOperationId(id,0);
            }
        };
    }

    /**
     *
     * @return
     */
    protected abstract OperationPane<T> createOperation();

    /**
     *
     */
    protected abstract void createInsertAction();

    /**
     *
     */
    protected abstract void createUpdateAction();

    /**
     *
     */
    protected abstract void createDeleteAction();

    /**
     *
     */
    protected abstract void initData();

    /**
     *
     */
    protected abstract void reload();

}
