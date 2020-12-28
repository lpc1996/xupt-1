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
        boolean result;
        result = delete("delete " + BaseInfoEntity.class.getName() + " where uId=?0",id);
        return result;
    }

    /**
     * 读取数据库中BaseInfoEntity表中所有的数据
     * @return 返回一个List或者null
     */
    @Override
    public List<BaseInfoEntity> getList() {
        List<BaseInfoEntity> list = null;
        StringBuilder hql = new StringBuilder();
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
        StringBuilder hql = new StringBuilder();
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
        boolean result ;
        String hql;
        hql = "update " + BaseInfoEntity.class.getName() + " base " + " set base.uName = ?0," +
                "base.formarName = ?1," + "base.sex = ?2," + "base.age = ?3," + "base.nativePlace = ?4," +
                "base.idcardType = ?5," + "base.idcardNum = ?6," + "base.uType = ?7," +
                "base.tel = ?8 ," + "base.uId=?9" + "where uId = ?10";
        result = executeUpdate(hql, data.getuName(), data.getFormarName(), data.getSex(), data.getAge(),
                data.getNativePlace(), data.getIdcardType(), data.getIdcardNum(), data.getuType(), data.getTel(), data.getuId(), id) == 1;
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
        boolean result;
        try {
            save(data);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public List<Object[]> getIdAndName(String type){
        List<Object[]> list;
        String hql;
        hql = "select uId,uName from " + BaseInfoEntity.class.getName() +
                " where uType=?0";
        list = getQuery(hql,type);
        return list;
    }

    @Override
    public List<String> getComments(){
        return getComments("base_info");
    }

    public static void main(String[] argv){
        BaseInfoDao baseInfoDao = new BaseInfoDao();
        List<String> list = baseInfoDao.getComments();
        for (String s : list) {
            System.out.println(s);
            System.out.println(s != null);
        }
    }
}
