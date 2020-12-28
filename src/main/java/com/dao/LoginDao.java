package com.dao;

import com.entity.LoginEntity;
import org.hibernate.HibernateError;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/03/13
 */
public class LoginDao extends Dao<LoginEntity>{

    public boolean login(String userName,String password){
        boolean result = false;
        StringBuilder hql = new StringBuilder();
        hql.append("from ").append(LoginEntity.class.getName());
        hql.append(" where uId like ?0 AND uPass like ?1");
        try {
            List list = getQuery(hql.toString(),userName,password);
            if(list.size() == 1){
                result = true;
            }
            System.out.println(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将传入的参数转换成hql delete删除语句并执行
     * @param id 删除条件
     * @return 删除成功返回true，否则返回false
     */
    @Override
    public boolean delete(String id){
        boolean result = false;
        String hql = "delete " + LoginEntity.class.getName() +
                " where uId like ?0";
        int number = executeUpdate(hql,id);
        if(number > 0){
            result = true;
        }
        return result;
    }

    /**
     * 读取数据库中所有的数据
     *
     * @return 返回用户账号信息
     */
    @Override
    public List<LoginEntity> getList() {
        List<LoginEntity> list = null;
        StringBuilder hql = new StringBuilder("from ");
        hql.append(LoginEntity.class.getName());
        try{
            list = getQuery(hql.toString());
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
    public boolean update(String id, LoginEntity data) {
        return false;
    }

    /**
     * 将一条数据添加到数据库中
     *
     * @param data 要添加的数据
     * @return 成功返回true，否则返回false
     */
    @Override
    public boolean insert(LoginEntity data) {
        save(data);
        return false;
    }

    /**
     * 获取数据库表中所有字段注释
     *
     * @return 返回一个字符串链表
     */
    @Override
    public List<String> getComments() {
        return null;
    }

    /**
     * 修改用户登录密码
     * @param id 用户名，用于识别要修改的用户
     * @param oldPass 旧密码，用于识别密码修改操作是否是本人操作
     * @param newPass 新密码
     * @return 成功返回true，失败返回false
     */
    public boolean updatePass(String id,String oldPass,String newPass){
        boolean result = false;
        StringBuilder hql = new StringBuilder();
        hql.append("update ").append(LoginEntity.class.getName()).append(
                " set pass=?0").append(" where id=?1").append(" AND pass=?2");
        try{
            if(executeUpdate(hql.toString(),newPass,id,oldPass) == 1){
                result = true;
            }
        }catch (HibernateError h){
            h.printStackTrace();
        }
        return result;
    }



    public static void main(String[] argv){
        LoginDao dao = new LoginDao();
    }
}
