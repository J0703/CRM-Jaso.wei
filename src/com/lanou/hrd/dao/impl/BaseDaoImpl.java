package com.lanou.hrd.dao.impl;

import com.lanou.hrd.dao.BaseDao;
import com.lanou.hrd.page.PageHibernateCallback;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/24.
 */

@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {


    @Override
    public int getTotalRecord(String condition, Object[] params) {
        List<Long> lists = (List<Long>) this.getHibernateTemplate().find(condition,params);
        if (lists != null){
            return lists.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<T> findAll(String hql, Object[] params, int startIndex, int pageSize) {
        return this.getHibernateTemplate().execute(
                new PageHibernateCallback<T>(hql,params,startIndex,pageSize));
    }

    ////////////////////////////////////////////////////
    @Override
    public List<T> findAll(String hql) {

        List<T> list = (List<T>) getHibernateTemplate().find(hql);

        return list;
    }

    @Override
    public List<T> find(String hql, Map<String, Object> params) {
        Session session = currentSession();

        //执行查询语句
        Query query = session.createQuery(hql);
        //设置参数
        if (params!=null && !params.isEmpty()){
            //遍历参数
            for (String key : params.keySet()) {
                //设置查询语句中的key和value
                query.setParameter(key,params.get(key));
            }
        }
        List<T> list = query.list();

        return list;//返回查询结果
    }

    @Override
    public List<T> find(String hql, Object[] params) {
        List<T> list = (List<T>) getHibernateTemplate().find(hql, params);
        return list;
    }

    @Override
    public T findSingle(String hql, Map<String, Object> params) {
        List<T> tList = find(hql,params);
        if (tList.size()>0) {
            return tList.get(0);//返回查询到的第一个对象
        }
        return null;
    }

    @Override
    public T findById(Serializable id, Class<T> tClass) {
        Session session = currentSession();
        //根据主键id查询某个对象
        T t = (T) session.get(tClass,id);
        return t;
    }

    @Override
    public void add(T t) {
        getHibernateTemplate().save(t);
    }

    @Override
    public void delete(Serializable id, Class<T> tClass) {
        getHibernateTemplate().delete(findById(id,tClass));
    }

    @Override
    public void update(T t) {
        Session session = currentSession();
        session.merge(t);
    }

}
