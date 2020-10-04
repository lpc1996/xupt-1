package com.window;

import com.entity.StudentEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 濃霧-遠方
 * @date 2020/07/03
 */
public class Window extends JFrame {

    private JTextField searchField;
    private JButton searchBtn;
    private JButton insertBtn;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JButton refreshBtn;
    private JTable idTable;
    private JTabbedPane dataPane;

    public Window(Dimension size){
        this(size,null);
    }

    public Window(Dimension size,String title) {
        setSize(size);
        setTitle(title);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setContentPane(createContentPane());
    }

    protected JPanel createContentPane(){
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));

//        showPane = new DataPane(new Dimension(getWidth()-30,getHeight()-130));
        dataPane = createdataPane();
//        contentPane.add(dataPane);

        JPanel showPane = new JPanel();
        showPane.setPreferredSize(new Dimension(getWidth()-30,getHeight()-130));
        showPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        showPane.add(createIdTable());
        showPane.add(dataPane);
//        showPane.setBorder(BorderFactory.createTitledBorder(""));

        contentPane.add(showPane);

        contentPane.add(createBtnPane());

        return contentPane;
    }

    private JScrollPane createIdTable(){
//        JPanel idPane = new JPanel();
//        idPane.setPreferredSize(new Dimension(150,getHeight()-130));
//        idPane.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
//        idPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane js = new JScrollPane();
        js.setPreferredSize(new Dimension(148,getHeight()-140));
        idTable = new JTable();
        js.setViewportView(idTable);
//        idPane.add(js);
        return js;
    }

    private JTabbedPane createdataPane(){
        JTabbedPane dataPane = new JTabbedPane(JTabbedPane.TOP);
        dataPane.setPreferredSize(new Dimension(getWidth()-150-48,getHeight()-140));

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
     * @param action 监听器对象
     */
    protected void setRefreshAction(ActionListener action) {
        refreshBtn.addActionListener(action);
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
     * 获取选中的行数
     * @return 被选中的行数
     */
//    public int getSelectRowCount(){
//        return dataPane.getSelectRowCount();
//    }

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

    public static void main(String[] argv){
        Window window = new Window(new Dimension(1000,600));
        window.setVisible(true);
    }
}
