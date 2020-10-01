package com.window;

import javax.swing.table.DefaultTableModel;

public interface TableOperation {
    /**
     * 初始化表格数据
     * 将model中的数据显示在表格中
     * @param model 表格数据
     */
    void setTableModel(DefaultTableModel model);

    /**
     * 获取选中的行数
     * @return 被选中的行数
     */
    int getSelectRowCount();

    /**
     * 获取选中行的索引
     * @return 如果有一行被选中返回选中行的索引，如果没有一行被选中，返回-1
     */
    int getSelectRow();

    /**
     * 获取row行column列的单元格的数据
     * @param row
     * @param cloumn
     * @return
     */
    Object getValueAt(int row, int cloumn);

    /**
     * 设置列宽
     * @param width 要设置的列宽
     */
    void setColumnWidth(int width);

    /**
     * 设置选中行
     * @param row 要选中的行的位置
     */
    void setSelectedRow(int row);
}
