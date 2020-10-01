package com.window;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author 濃霧-遠方
 * @date 2020/07/03
 */
public class Window extends JFrame {

    private DataPane dataPane;
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
    }

    protected JPanel createContentPane(){
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));

        dataPane = new DataPane(new Dimension(getWidth()-30,getHeight()-80));
        contentPane.add(dataPane);

        contentPane.add(createBtnPane());

        return contentPane;
    }

    private JPanel createBtnPane(){
        JPanel btnPane = new JPanel();
        btnPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        btnPane.setPreferredSize(new Dimension(getWidth()-30,50));

        insertBtn = new JButton("添加");
        insertBtn.setPreferredSize(new Dimension(80,30));
        updateBtn = new JButton("修改");
        updateBtn.setPreferredSize(new Dimension(80,30));
        deleteBtn = new JButton("删除");
        deleteBtn.setPreferredSize(new Dimension(80,30));
        refreshBtn = new JButton("刷新");
        refreshBtn.setPreferredSize(new Dimension(80,30));

        btnPane.add(insertBtn);
        btnPane.add(updateBtn);
        btnPane.add(deleteBtn);
        btnPane.add(refreshBtn);
        return btnPane;
    }

    public void setInsertAction(ActionListener action){
        insertBtn.addActionListener(action);
    }

    public void setUpdateAction(ActionListener action) {
        updateBtn.addActionListener(action);
    }

    public void setDeleteAction(ActionListener action) {
        deleteBtn.addActionListener(action);
    }

    public void setRefreshAction(ActionListener action) {
        refreshBtn.addActionListener(action);
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
}
