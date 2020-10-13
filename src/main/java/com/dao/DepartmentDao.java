package com.dao;

import com.entity.DepartmentEntity;

import javax.management.StringValueExp;
import java.util.List;

public class DepartmentDao extends Dao<DepartmentEntity> {
    /**
     * 将传入的参数转换成hql delete删除语句并执行
     *
     * @param id 删除条件
     * @return 删除成功返回true，否则返回false
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        StringBuilder hql = new StringBuilder();
        hql.append("delete ").append(DepartmentEntity.class.getName()).append(" where id=?0");
        if(executeUpdate(hql.toString(),id) == 1){
            result = true;
        }
        return result;
    }

    /**
     * 读取数据库中所有的数据
     *
     * @return 返回数据库中对应Entity的表中所有数据
     */
    @Override
    public List<DepartmentEntity> getList() {
        StringBuilder hql = new StringBuilder();
        hql.append("from ").append(DepartmentEntity.class.getName());
        List<DepartmentEntity> list = getQuery(hql.toString());
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
    public boolean update(String id, DepartmentEntity data) {
        boolean result = false;
        StringBuilder hql = new StringBuilder();
        hql.append("update ").append(DepartmentEntity.class.getName()).append(" set name=?0,collegeId=?1,id=?2 where id=?3");
        if(executeUpdate(hql.toString(),data.getName(),data.getCollegeId(),data.getId(),id) == 1){
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
    public boolean insert(DepartmentEntity data) {
        boolean result = false;
        try {
            save(data);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public List<Object[]> getIdAndName(){
        List<Object[]> list = null;
        StringBuilder hql = new StringBuilder();
        hql.append("select id,name from ").append(DepartmentEntity.class.getName());
        list = getQuery(hql.toString());
        return list;
    }
}
