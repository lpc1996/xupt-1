package com.dao;

import com.entity.CourseEntity;

import java.util.List;

import static java.util.Collections.*;

/**
 * @author 濃霧-遠方
 * @date 2020/07/16
 */
public class CourseDao extends Dao<CourseEntity> {
    /**
     * 将传入的参数转换成hql delete删除语句并执行
     *
     * @param id 删除条件
     * @return 删除成功返回true，否则返回false
     */
    @Override
    public boolean delete(String id) {
        return delete("delete " + CourseEntity.class.getName() + " where id=?0", id);
    }

    /**
     * 读取数据库中所有的数据
     *
     * @return 返回数据库中对应Entity的表中所有数据
     */
    @Override
    public List<CourseEntity> getList() {
        List list = null;
        StringBuilder hql = new StringBuilder();
        hql.append(" from ").append(CourseEntity.class.getName());
        try {
            list = unmodifiableList(getQuery(hql.toString()));
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
    public boolean update(String id, CourseEntity data) {
        boolean result = false;
        StringBuilder hql = new StringBuilder();
        hql.append("update ").append(CourseEntity.class.getName()).append(" c set c.id=?0,c.name=?1,")
                .append("c.collegeId=?2,c.departmentId=?3,c.type=?4,c.credit=?5 where c.id=?6");
        if(executeUpdate(hql.toString(),data.getId(),data.getName(),data.getCollegeId(),data.getDepartmentId()
        ,data.getType(),data.getCredit(),id) == 1){
            result = true;
        }
        System.out.println(hql.toString());
        return result;
    }

    /**
     * 将一条数据添加到数据库中
     *
     * @param data 要添加的数据
     * @return 成功返回true，否则返回false
     */
    @Override
    public boolean insert(CourseEntity data) {
        return false;
    }

    public List<Object[]> getIdAndName(){
        List<Object[]> list;
        list = getQuery("select id,name from " + CourseEntity.class.getName());
        return list;
    }

    @Override
    public List<String> getComments(){
        return getComments("course");
    }
}
