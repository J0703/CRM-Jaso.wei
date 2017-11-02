package com.lanou.base;

import com.lanou.hrd.domain.Staff;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/24.
 */
public interface BaseDao<T> {

    /**
     * 获取总记录数
     */
    public int getTotalRecord(String condition,Object[] params);

    /**
     * 获取到数据,带分页参数
     */
    public List<T> findAll(String condition, Object[] params, int startIndex, int pageSize);





    /**
     * 查询所有
     */
    List<T> findAll(String hql);

    /**
     * 根据条件查询, 返回查询到的结果集
     */
    List<T> find(String hql, Map<String,Object> params);
    List<T> find(String hql,Object[] params);

    /**
     * 根据条件查询, 返回第一个对象
     */
    T findSingle(String hql, Map<String,Object> params);

    /**
     * 根据主键id查询某个对象
     * @param id 要查询的主键id
     * @param tClass 返回对象类型声明
     * @return
     */
    T findById(Serializable id, Class<T> tClass);

    /**
     * 添加
     */
    void add(T t);

    /**
     * 删除
     */
    void delete(Serializable id,Class<T> tClass);
    /**
     * 修改
     */
    void update(T t);


}
