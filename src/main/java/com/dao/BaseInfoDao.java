package com.dao;

import com.entity.BaseInfoEntity;
import org.hibernate.HibernateError;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 濃霧-遠方
 * @date 2020/07/16
 */
public class BaseInfoDao extends Dao<BaseInfoEntity>{
    public BaseInfoDao() {
    }

    public BaseInfoEntity getUser(String id){
        BaseInfoEntity user = null;
        StringBuilder hql = new StringBuilder();
        hql.append("from ").append(BaseInfoEntity.class.getName()).append(" where uId like ?0");
        try {
            List list = getQuery(hql.toString(),id);
            if(list.size() == 1){
                user = (BaseInfoEntity)list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

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
     * 读取数据库中BaseInfoEntity表中所有的数据
     * @return
     */
    @Override
    public List<BaseInfoEntity> getList() {
        List<BaseInfoEntity> list = null;
        hql = new StringBuilder();
        hql.append(" from ").append(BaseInfoEntity.class.getName());
        try{
            list = getQuery(hql.toString());
        }catch (HibernateError h){
            h.printStackTrace();
        }
        return list;
    }

    public List<BaseInfoEntity> getList(String type){
        List<BaseInfoEntity> list = null;
        hql = new StringBuilder();
        hql.append("from ").append(BaseInfoEntity.class.getName()).append(" where uType=?0");
        try{
            list = getQuery(hql.toString(),type);
        }catch (HibernateError h){
            h.printStackTrace();
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
    public boolean update(String id, BaseInfoEntity data) {
        return false;
    }

    /**
     * 将一条数据添加到数据库中
     *
     * @param data 要添加的数据
     * @return 成功返回true，否则返回false
     */
    @Override
    public boolean insert(BaseInfoEntity data) {
        return false;
    }

    public static void main(String[] argv){
        BaseInfoDao baseInfoDao = new BaseInfoDao();
        System.out.println(baseInfoDao.getList("student").size());
    }
}
