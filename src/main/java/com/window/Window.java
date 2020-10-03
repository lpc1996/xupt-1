package com.window;

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

    private DataPane dataPane;
    private JTextField searchField;
    private JButton searchBtn;
    private JButton insertBtn;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JButton refreshBtn;

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

        dataPane = new DataPane(new Dimension(getWidth()-30,getHeight()-130));
        contentPane.add(dataPane);

        contentPane.add(createBtnPane());

        return contentPane;
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
    public void setInsertAction(ActionListener action){
        insertBtn.addActionListener(action);
    }

    /**
     * 给update按钮注册监听器
     * @param action 监听器对象
     */
    public void setUpdateAction(ActionListener action) {
        updateBtn.addActionListener(action);
    }

    /**
     * 给delete按钮注册监听器
     * @param action 监听器对象
     */
    public void setDeleteAction(ActionListener action) {
        deleteBtn.addActionListener(action);
    }

    /**
     * 给refresh注册监听器
     * @param action 监听器对象
     */
    public void setRefreshAction(ActionListener action) {
        refreshBtn.addActionListener(action);
    }

    /**
     * 给searchBtn注册监听器
     */
    public void setSearchAction(){
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(searchField.getText().length() == 0 || " ".equals(searchField.getText())) {
                    JOptionPane.showMessageDialog(null,"不能匹配空字符串");
                    return;
                }
                String string = searchField.getText();
                int i = 0;
                for(; i<dataPane.getRowCount(); i++){
                    if( ((String)dataPane.getValueAt(i)).equals(string) ){
                        dataPane.setSelectedRow(i);
                        break;
                    }
                }
                if(i == dataPane.getRowCount()){
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
    public void setTableModel(DefaultTableModel model){
//        dataPane.setTableModel(model);
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
    public int getSelectRow(){
        return getSelectRow();
    }

    /**
     * 获取row行column列的单元格的数据
     * @param row
     * @param cloumn
     * @return
     */
    public Object getValueAt(int row,int cloumn){
        return getValueAt(row,cloumn);
    }

    /**
     * 设置列宽
     * @param width 要设置的列宽
     */
    public void setColumnWidth(int width){
        setColumnWidth(width);
    }

    /**
     * 设置选中行
     * @param row 要选中的行的位置
     */
    public void setSelectedRow(int row){
        setSelectedRow(row);
    }

    public static void main(String[] argv){
        Window window = new Window(new Dimension(1000,800));
        window.setVisible(true);
    }
}
