package com.dao;

import com.entity.MajorEntity;
import com.entity.SemesterEntity;

import java.util.List;

public class SemesterDao extends Dao<SemesterEntity> {
    /**
     * 将传入的参数转换成hql delete删除语句并执行
     *
     * @param id 删除条件
     * @return 删除成功返回true，否则返回false
     */
    @Override
    public boolean delete(String id) {
        return false;
    }

    /**
     * 读取数据库中所有的数据
     *
     * @return 返回数据库中对应Entity的表中所有数据
     */
    @Override
    public List<SemesterEntity> getList() {
        return null;
    }

    /**
     * 将传入的参数重组成hql update语句并执行
     *
     * @param id   筛选条件
     * @param data 新数据
     * @return 更新成功返回true，反之，返回false
     */
    @Override
    public boolean update(String id, SemesterEntity data) {
        return false;
    }

    /**
     * 将一条数据添加到数据库中
     *
     * @param data 要添加的数据
     * @return 成功返回true，否则返回false
     */
    @Override
    public boolean insert(SemesterEntity data) {
        return false;
    }

    public List<Object[]> getIdAndName(){
        List<Object[]> list = null;
        StringBuilder hql = new StringBuilder();
        hql.append("select id,name from ").append(SemesterEntity.class.getName());
        list = getQuery(hql.toString());
        return list;
    }
}
