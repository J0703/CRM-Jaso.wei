package com.lanou.hrd.page;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
public class PageHibernateCallback<T> implements HibernateCallback<List<T>> {

    private String hql;
    private Object[] params;//参数
    private int startIndex;//开始索引
    private int pageSize;//每页显示条数


    public PageHibernateCallback(String hql, Object[] params, int startIndex, int pageSize) {
        this.hql = hql;
        this.params = params;
        this.startIndex = startIndex;
        this.pageSize = pageSize;
    }

    @Override
    public List<T> doInHibernate(Session session) throws HibernateException {

        //通过hql语句获得query对象
        Query query = session.createQuery(hql);
        //t条件设置
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i,params[i]);

        }
        //分页
        query.setFirstResult(startIndex);
        query.setMaxResults(pageSize);
        //查询所有
        return query.list();

    }
}
