package com.window;

import javax.swing.*;
import java.awt.*;
import java.util.List;

abstract class OperationPane<T> extends JPanel{

    protected List<T> list;
    protected Dimension labSize;
    protected Dimension fieldSize;
    protected Dimension fieldSize1;

    public OperationPane(Dimension size){
        this(size,null);
    }

    public OperationPane(Dimension size,List<T> list) {
        setSize(size);
        setPreferredSize(size);
        setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
        setList(list);

        labSize = new Dimension(120,35);
        fieldSize = new Dimension(getWidth()/2-labSize.width-10,35);
        fieldSize1 = new Dimension(getWidth()-labSize.width-10,35);

        InitPane();
        setNull();
    }

    public abstract void InitData(String id);

    public abstract T getData();

    public abstract void setNull();

    public void setList(List<T> list){
        this.list=list;
    }

    protected abstract void InitPane();

    protected String equals(String string,JComboBox<String> box){
        String item = "";
        for(int i=0; i <box.getItemCount(); i++){
            item = box.getItemAt(i);
            if(item.split(" ")[0].equals(string)){
                return box.getItemAt(i);
            }
        }
        return null;
    }

    protected String split(String string){
        return string.split(" ")[0];
    }

    public abstract void initBox();
}
