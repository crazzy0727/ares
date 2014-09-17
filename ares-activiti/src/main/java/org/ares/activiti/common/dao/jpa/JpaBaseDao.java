package org.ares.activiti.common.dao.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public interface JpaBaseDao<T> extends Serializable{

	public T findById(Class<T> clazz, Serializable id);
	/**
     * 不需确定新建实体是否已经存在
     */
    public T save(T t);
 
    /**
     * 不需确定新建实体是否已经存在
     */
    public Collection<T> save(Collection<T> ts);
 
    /**
     * 可确定为新建实体；返回处于托管状态的实例
     */
    public T persist(T t);
 
    public boolean persist(Collection<T> ts);
 
    /**
     * 若数据库中已有此记录，置为托管状态
     */
    public T merge(T t);
 
    /**
     * 若数据库中已有此记录，置为托管状态
     */
    public Collection<T> merge(Collection<T> ts);
 
    /**
     * 删除
     */
    public void remove(T t);
 
    /**
     * 批量删除 传入集合
     */
    public void remove(Collection<T> ts);
 
    /**
     * 删除指定id、指定class的实例
     */
    public void remove(Class<T> clazz, Serializable id);
 
    /**
     * 删除指定id、指定class的实例
     */
    public void remove(Class<T> clazz, Collection<Serializable> ids);
    
    /**
     * 若数据库中存在，返回最新状态；否则，返回空
     */
    public T refresh(Class<T> clazz, T t);
 
    /**
     * 清除一级缓存
     */
    public void clear();
 
    /**
     * 将对象置为游离状态
     */
    public void detach(T t);
 
    /**
     * 将对象置为游离状态
     */
    public void detach(Collection<T> ts);
 
    /**
     * 检查实例是否处于托管状态
     */
    public boolean isManaged(T t);
 
    /**
     * 同步jpa容器和数据库
     */
    public void flush();
}
