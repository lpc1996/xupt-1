package com.window;

import com.image.Image;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 用表格显示数据
 * @author 濃霧-遠方
 * @date 2020/07/16
 */
public class DataPane extends JPanel {

    private JTable idTable;
    private JTabbedPane showPane;
    private JTextField searchText;
    private JButton searchBtn;

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
        idPane.setPreferredSize(new Dimension(150,getHeight()-40));
        idPane.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        idPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        searchText = new JTextField();
        searchText.setPreferredSize(new Dimension(113,35) );
        idPane.add(searchText);
        searchBtn = new JButton();
        searchBtn.setPreferredSize(new Dimension(35,35));
        searchBtn.setIcon(new ImageIcon(new Image().getSearch_4()) );
        idPane.add(searchBtn);
        JScrollPane js = new JScrollPane();
        js.setPreferredSize(new Dimension(148,getHeight()-77));
        idTable = new JTable();
        js.setViewportView(idTable);
        idPane.add(js);
        return idPane;
    }

    private JTabbedPane createShowPane(){
        JTabbedPane showPane = new JTabbedPane(JTabbedPane.TOP);
        showPane.setPreferredSize(new Dimension(getWidth()-150-32,getHeight()-39));

        return showPane;
    }

    public void createTab(Component tab,String title){
        showPane.addTab(title,tab);
        repaint();
    }

    public void setData(DefaultTableModel model){
        idTable.setModel(model);
    }

    public String getSelectedId(){
        return (String)idTable.getValueAt(idTable.getSelectedRow(),0);
    }

    public static void main(String[] argv){
        JFrame jFrame = new JFrame();
        jFrame.setSize(900,700);
        DataPane dataPane = new DataPane(new Dimension(900,700));
        jFrame.add(dataPane);

        JPanel j = new JPanel();
        dataPane.createTab(j,"测试");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
