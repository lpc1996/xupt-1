package com.dao;

import com.entity.BaseInfoEntity;
import org.hibernate.HibernateError;
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
        boolean result = false;
        StringBuilder hql = new StringBuilder();
        hql.append("delete ").append(BaseInfoEntity.class.getName()).append(" where uId=?0");
        result = delete(hql.toString(),id);
        return result;
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
        boolean result = false;
        StringBuilder hql = new StringBuilder();
        hql.append("update ").append(BaseInfoEntity.class.getName()).append(" base ").append(" set base.uName = ?0,")
        .append("base.formarName = ?1,").append("base.sex = ?2,").append("base.age = ?3,").append("base.nativePlace = ?4,")
                .append("base.idcardType = ?5,").append("base.idcardNum = ?6,").append("base.uType = ?7,").append(
                        "base.tel = ?8 ,").append("base.uId=?9").append("where uId = ?10");
        if(executeUpdate(hql.toString(),data.getuName(),data.getFormarName(),data.getSex(),data.getAge(),
                data.getNativePlace(),data.getIdcardType(),data.getIdcardNum(),data.getuType(),data.getTel(),data.getuId(),id) == 1){
            result = true;
        }else{
            result = false;
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
    public boolean insert(BaseInfoEntity data) {
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

    public static void main(String[] argv){
        BaseInfoDao baseInfoDao = new BaseInfoDao();
        System.out.println(baseInfoDao.getList("student").size());
    }
}
