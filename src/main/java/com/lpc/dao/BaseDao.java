package com.lpc.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.type.StringType;

import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

/**
 * @author 濃霧-遠方
 * @date 2020/12/31
 */
abstract class BaseDao {
    public static final Logger logger = LogManager.getLogger();

    /**
     * 创建一个Session实例
     * @return
     */
    private Session getSession(){
        Session session;
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
        }catch (Exception e){
            e.printStackTrace();
            session = null;
            logger.error("创建Session失败！");
        }
        return session;
    }

    /**
     * 返回所给id的实体类持久化实例，如果实例不存在则返回null。 该方法不会返回没有初始化的实例
     * @param clazz
     * @param id
     * @return
     */
    protected <T> T get(Class<T> clazz, Serializable id){
        Session session = getSession();
        try {
            return session.get(clazz,id);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("get by id for "+clazz.getClass().getName()+" is failed!");
        }finally {
            closeSession(session);
        }
        return null;
    }

    /**
     * 使用hql和value的查询
     * 使用createQuery方法查询
     * @param hql
     * @param values
     * @return
     */
    protected List<Object> findByHQLAndValue(String hql,Object... values){
        List list = null;
        Session session = getSession();
        try {
            Query query = session.createQuery(hql);
            for (int i=0; i<values.length; i++) {
                query.setParameter(i, values[i]);
            }
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("execute for '" + hql + "' is failed!");
        } finally {
            closeSession(session);
        }
        return list;
    }

    /**
     * 使用hql和values更新或删除某个实例
     * @param hql
     * @param values
     * @return
     */
    protected int updateOrSave(String hql, Object... values){
        Session session = getSession();
        Transaction t = session.getTransaction();
        int result = -1;
        try{
            Query query = session.createQuery(hql);
            if(values.length > 0) {
                for (int i=0;i <values.length; i++) {
                    query.setParameter(i, values[i]);
                }
            }
            result = query.executeUpdate();
            t.commit();
        }catch (Exception e){
            e.printStackTrace();
            rollback(t);
            result = -1;
            logger.error("executeUpdate fro "+hql +" is failed!");
        }finally {
            closeSession(session);
        }
        return result;
    }

    /**
     * 面向对象的更新或删除某个实例
     * @param entity
     * @return
     */
    public boolean saveOrUpdate(Object entity){
        boolean result = false;
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try{
            session.saveOrUpdate(entity);
            transaction.commit();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
            rollback(transaction);
            logger.error("saveOrUpdate "+entity.getClass().getName()+" is failed!");
        }finally {
            closeSession(session);
        }
        return result;
    }

    /**
     * 保存某个实例
     * @param entity
     * @return
     */
    public boolean saveByEntity(Object entity){
        boolean result = false;
        Session session = getSession();
        Transaction t = session.getTransaction();
        try {
            session.save(entity);
            t.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            rollback(t);
            result = false;
            logger.error("sava for "+entity.getClass().getName()+" is failed!");
        } finally {
            closeSession(session);
        }
        return result;
    }

    /**
     * 面向对象的更新某个实例
     * @param entity
     * @return
     */
    public boolean updateByEntity(Object entity){
        boolean result = false;
        Session session = getSession();
        Transaction t = session.getTransaction();
        try{
            session.update(entity);
            t.commit();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
            rollback(t);
            logger.error("update for "+entity.getClass().getName()+" is failed!");
        }finally {
            closeSession(session);
        }
        return result;
    }

    /**
     * 删除某个实例
     * @param entity
     * @return
     */
    public boolean deleteByEntity(Object entity){
        boolean result = false;
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try{
            session.delete(entity);
            transaction.commit();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
            rollback(transaction);
            logger.error("delete " + entity.getClass().getName() + " is failed!");
        }
        return result;
    }

    /**
     *  根据entityClass查询
     *  使用Criteria查询
     * @param entityClass
     * @return
     */
    protected <Entity> List<Entity> createCriteria(Class<Entity> entityClass){
        Session session = getSession();
        Transaction t = session.getTransaction();
        try{
            CriteriaQuery<Entity> criteriaQuery = session.getCriteriaBuilder().createQuery(entityClass);
            criteriaQuery.from(entityClass);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("createCriteria by Entity for "+entityClass.getClass().getName()+" is failed!");
        }finally {
            closeSession(session);
        }
        return null;
    }

    /**
     * 查询表中所有字段注释
     * @param tableName
     * @return
     */
    public List<String> getComments(String tableName){
        StringBuilder hql = new StringBuilder();
        hql.append("select column_comment from INFORMATION_SCHEMA.COLUMNS WHERE table_name=?0")
                .append(" AND table_schema = 'xupt'");
        Session session = getSession();
        List<String> comments = null;
        try{
            Query query = session.createSQLQuery(hql.toString())
                    .addScalar("column_comment", StringType.INSTANCE);
            query.setParameter(0,tableName);
            comments = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeSession(session);
        }
        return comments;
    }

    /**
     * 事务回滚
     * @param t
     */
    private void rollback(Transaction t){
        if(t.isActive()){
            t.rollback();
        }
    }

    /**
     * 关闭Session
     * @param session
     */
    private void closeSession(Session session){
        if (session.isOpen()){
            session.close();
        }
    }
}
