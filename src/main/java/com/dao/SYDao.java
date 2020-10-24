package com.dao;

import com.entity.SchoolYearEntity;
import java.util.List;

public class SYDao extends Dao<SchoolYearEntity> {
    /**
     * 将传入的参数转换成hql delete删除语句并执行
     *
     * @param id 删除条件
     * @return 删除成功返回true，否则返回false
     */
    @Override
    public boolean delete(String id) {
        boolean result;
        StringBuilder hql = new StringBuilder();
        hql.append("delete ").append(SchoolYearEntity.class.getName()).append(" where id=?0");
        result = delete(hql.toString(),id);
        return result;
    }

    /**
     * 读取数据库中所有的数据
     *
     * @return 返回数据库中对应Entity的表中所有数据
     */
    @Override
    public List<SchoolYearEntity> getList() {
        List<SchoolYearEntity> list = null;
        StringBuilder hql = new StringBuilder();
        hql.append(" from ").append(SchoolYearEntity.class.getName());
        try {
            list = getQuery(hql.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将传入的参数重组成hql update语句并执行
     *
     * @param id   筛选条件
     * @param data 新数据
     * @return 更新成功返回true，反之，返回false
     */
    @Override
    public boolean update(String id, SchoolYearEntity data) {
        boolean result = false;
        StringBuilder hql = new StringBuilder();
        hql.append("update ").append(SchoolYearEntity.class.getName()).append(" sy set sy.id=?0,sy.name=?1,")
               .append("sy.begin=?2,sy.end=?3 where sy.id=?4");
        System.out.println(hql.toString());
        if(executeUpdate(hql.toString(),data.getId(),data.getName(),data.getBegin(),data.getEnd(),id) == 1){
           result = true;
        }
        return result;
    }

    /**
     * 将一条数据添加到数据库中
     *
     * @param data 要添加的数据
     * @return 成功返回true，否则返回false
     */
    @Override
    public boolean insert(SchoolYearEntity data) {
        boolean result;
        try{
            save(data);
            result = true;
        }catch (Exception e){
            result = false;
        }
        return result;
    }

    /**
     * 获取数据库表中所有字段注释
     *
     * @return 返回一个字符串链表
     */
    @Override
    public List<String> getComments() {
        List<String> list = getComments("school_year");
        return list;
    }

    public List<Object[]> getIdAndName(){
        List<Object[]> list = null;
        StringBuilder hql = new StringBuilder();
        hql.append("select id,name from ").append(SchoolYearEntity.class.getName());
        list = getQuery(hql.toString());
        return list;
    }
}
