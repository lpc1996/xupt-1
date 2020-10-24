package com.dao;

import com.entity.OfferingCoursesEntity;
import java.util.List;

public class OCDao extends Dao<OfferingCoursesEntity> {
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
        hql.append("delete ").append(OfferingCoursesEntity.class.getName()).append(" where id=?0");
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
    public List<OfferingCoursesEntity> getList() {
        StringBuilder hql = new StringBuilder();
        hql.append("from ").append(OfferingCoursesEntity.class.getName());
        List<OfferingCoursesEntity> list = getQuery(hql.toString());
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
    public boolean update(String id, OfferingCoursesEntity data) {
        boolean result = false;
        StringBuilder hql = new StringBuilder();
        hql.append("update ").append(OfferingCoursesEntity.class.getName()).
                append(" set id=?0,courseId=?1,teacherId=?2,begin=?3,schoolYearId=?4,schoolTremId=?5,")
                .append("semesterId=?6,maxNum=?7 where id=?8");
        if(executeUpdate(hql.toString(),data.getId(),data.getCourseId(),data.getTeacherId(),data.getBegin()
        ,data.getSchoolYearId(),data.getSchoolTremId(),data.getSemesterId(),data.getMaxNum(),id) == 1){
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
    public boolean insert(OfferingCoursesEntity data) {
        return false;
    }

    /**
     * 获取数据库表中所有字段注释
     *
     * @return 返回一个字符串链表
     */
    @Override
    public List<String> getComments() {
        List<String> list = getComments("offering_courses");
        return list;
    }

    public List<Object[]> getIdAndName(){
        List<Object[]> list = null;
        StringBuilder hql = new StringBuilder();
        hql.append("select id,name from ").append(OfferingCoursesEntity.class.getName());
        list = getQuery(hql.toString());
        return list;
    }
}
