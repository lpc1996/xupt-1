package com.dao;

import com.entity.TeacherEntity;

import java.util.List;

/**
 * @author 濃霧-遠方
 * @date 2020/07/16
 */
public class TeacherDao extends Dao<TeacherEntity> {
    /**
     * 将传入的参数转换成hql delete删除语句并执行
     *
     * @param id 删除条件
     * @return 删除成功返回true，否则返回false
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        StringBuilder hql = new StringBuilder("delete ").append(TeacherEntity.class.getName()).append(" where id=?0");
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
    public List<TeacherEntity> getList() {
        List<TeacherEntity> list = null;
        StringBuilder hql = new StringBuilder();
        hql.append("from ").append(TeacherEntity.class.getName());
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
    public boolean update(String id, TeacherEntity data) {
        boolean result = false;
        StringBuilder hql = new StringBuilder();
        hql.append("update ").append(TeacherEntity.class.getName()).append(" set college=?0,").append("department=?1,")
                .append("level=?2,").append("education=?3,").append("year=?4 ").append("where id=?5");
        if(executeUpdate(hql.toString(),data.getCollege(),data.getDepartment(),data.getLevel(),data.getEducation(),data.getYear(),id) == 1){
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
    public boolean insert(TeacherEntity data) {
        boolean result = false;
        try {
            save(data);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<String> getComments(){
        return getComments("teacher");
    }
}
