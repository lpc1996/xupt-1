package com.window;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * 用表格显示数据
 * @author 濃霧-遠方
 * @date 2020/07/16
 */
public class DataPane extends JPanel {

    private JTable idTable;
    private JTabbedPane showPane;

    public DataPane(Dimension size) {
        setSize(size);
        setPreferredSize(size);
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        showPane=createShowPane();
        add(createIdTable());
        add(showPane);

    }

    private JPanel createIdTable(){
        JPanel idPane = new JPanel();
        idPane.setPreferredSize(new Dimension(150,getHeight()));
        idPane.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        idPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane js = new JScrollPane();
        js.setPreferredSize(new Dimension(148,getHeight()-2));
        idTable = new JTable();
        js.setViewportView(idTable);
        idPane.add(js);
        return idPane;
    }

    private JTabbedPane createShowPane(){
        JTabbedPane showPane = new JTabbedPane(JTabbedPane.TOP);
        showPane.setPreferredSize(new Dimension(getWidth()-150-15,getHeight()-2));

        return showPane;
    }

    /**
     *向选项卡面板添加一个标题为title，组件为tab的选项卡
     * @param tab 要添加的选项卡
     * @param title 要添加的选项卡的标题
     */
    public void addTab(Component tab,String title){
        showPane.addTab(title,tab);
        repaint();
    }

    /**
     * 向表中填入数据
     * @param model 要填入表格的数据集合
     */
    public void setData(DefaultTableModel model){
        idTable.setModel(model);
    }

    /**
     * 获取喜欢中行的数据
     * @return 如果有选中行则返回该行的数据，否则返回null
     */
    public String getSelectedId(){
        return (String)idTable.getValueAt(idTable.getSelectedRow(),0);
    }

    /**
     * 设置选中行
     * @param row 行号
     */
    public void setSelectedRow(int row){
        idTable.setRowSelectionInterval(row,row);
    }

    /**
     * 获取表格的行数
     * @return 返回表格行数
     */
    public int getRowCount(){
        return idTable.getRowCount();
    }

    public Object getValueAt(int row){
        return idTable.getValueAt(row,0);
    }

    public static void main(String[] argv){
        JFrame jFrame = new JFrame();
        jFrame.setSize(900,700);
        DataPane dataPane = new DataPane(new Dimension(900,700));
        jFrame.add(dataPane);

        JPanel j = new JPanel();
        dataPane.addTab(j,"测试");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
