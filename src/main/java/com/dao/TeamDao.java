package com.dao;

import com.entity.TeamEntity;

import java.util.List;

/**
 * @author 濃霧-遠方
 * @date 2020/07/16
 */
public class TeamDao extends Dao<TeamEntity> {

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
    public List<TeamEntity> getList() {
        String hql = "from "+TeamEntity.class.getName();
        List<TeamEntity> list = null;
        try {
            list=getQuery(hql);
        }catch (Exception e) {
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
    public boolean update(String id, TeamEntity data) {
        return false;
    }

    /**
     * 将一条数据添加到数据库中
     *
     * @param data 要添加的数据
     * @return 成功返回true，否则返回false
     */
    @Override
    public boolean insert(TeamEntity data) {
        return false;
    }

    /**
     * 获取数据库表中所有字段注释
     *
     * @return 返回一个字符串链表
     */
    @Override
    public List<String> getComments() {
        return getComments("team");
    }

    public List<Object[]> getIdAndName(){
        List<Object[]> list ;
        list = getQuery("select id,name from " + TeamEntity.class.getName());
        return list;
    }
}
