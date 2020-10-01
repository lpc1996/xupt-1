package com.dao;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import java.util.List;

/**
 * @author 濃霧-遠方
 */

public abstract class Dao<Entity> {

    protected boolean result;
    protected StringBuilder hql;

    private Session getSession(){
        Session session;
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
        }catch (HibernateError hibernateError){
            hibernateError.printStackTrace();
            session = null;
        }
        return session;
    }

    protected List<Entity> getQuery(String hql,String... para){
        List<Entity> list = null;
        Session session = getSession();
        try {
            Query<Entity> query = session.createQuery(hql);
            if(para.length > 0) {
                int i = 0;
                for (String str : para) {
                    query.setParameter(i, str);
                    i++;
                }
            }
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    public void save(Entity data){
        Session session = getSession();
        Transaction t = session.getTransaction();
        session.save(data);
        t.commit();
        session.close();
    }

    /**
     * 更新数据中的数据（修改）
     * @param hql hql语句
     * @param para 匹配条件
     * @return 返回操作的行数
     */
    protected int executeUpdate(String hql,String... para){
        Session session = getSession();
        Transaction t = session.getTransaction();
        int result = -1;
        try{
            Query<Entity> query = session.createQuery(hql);
            if(para != null && para.length > 0) {
                int i = 0;
                for (String p : para) {
                    query.setParameter(i, p);
                    i++;
                }
            }
            result = query.executeUpdate();
            t.commit();
            System.out.println(result);
        }catch (SessionException s){
            s.printStackTrace();
        }finally {
            session.close();
        }
        return result;
    }

    public void update(Entity data){
        Session session = getSession();
        Transaction t = session.getTransaction();
        session.update(data);
        t.commit();
        session.close();
    }

    /**
     * 将传入的参数转换成hql delete删除语句并执行
     * @param id 删除条件
     * @return 删除成功返回true，否则返回false
     */
    public abstract boolean delete(String id);

    /**
     * 读取数据库中所有的数据
     * @return 返回数据库中对应Entity的表中所有数据
     */
    public abstract List<Entity> getList();

    /**
     * 将传入的参数重组成hql update语句并执行
     * @param id 筛选条件
     * @param data 新数据
     * @return 更新成功返回true，反之，返回false
     */
    public abstract boolean update(String id,Entity data);

    /**
     * 将一条数据添加到数据库中
     * @param data 要添加的数据
     * @return  成功返回true，否则返回false
     */
    public abstract boolean insert(Entity data);

}
