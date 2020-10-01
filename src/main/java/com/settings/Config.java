package com.settings;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * @author 濃霧-遠方
 * @date 2020/06/17
 */
public class Config {

    private Element rootElement;
    private Setting setting;

    public Config() {
        try {
            rootElement = loadXML();
            setting = new Setting();
            readXML(rootElement);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Element loadXML() throws Exception {
        SAXReader reader = new SAXReader();
        File file =
                    new File(this.getClass().getClassLoader().getResource("setting.xml").getPath());
        InputStream inputStream =
                new FileInputStream(file);
        Document document = reader.read(inputStream);
        Element element= document.getRootElement();
        return element;
    }

    private void readXML(Element element){
        Iterator it = element.elementIterator();
        while(it.hasNext()){
            Element node_1 = (Element) it.next();
            Iterator it_1 = node_1.elementIterator();
            while(it_1.hasNext()){
                Element node_2 = (Element)it_1.next();
                Iterator it_2 = node_2.elementIterator();
                while(it_2.hasNext()){
                    Element node_3 = (Element)it_2.next();
                    Iterator it_3 = node_3.elementIterator();
                    while(it_3.hasNext()){}
                    setting.setValues(node_3.getName(),node_3.getStringValue());
                }
                setting.setValues(node_2.getName(),node_2.getStringValue());
            }
        }
    }

    public Setting getSetting(){
        return setting;
    }

    public static void main( String[] args ) {
        Config config = new Config();
    }

}
