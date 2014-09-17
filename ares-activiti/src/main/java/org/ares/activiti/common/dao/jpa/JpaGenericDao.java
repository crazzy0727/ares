package org.ares.activiti.common.dao.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.ares.activiti.common.util.Assert;

public class JpaGenericDao<T> implements JpaBaseDao<T> {
	
	private static final long serialVersionUID = 7433224241393375192L;
	
	@PersistenceContext
	public EntityManager entityManager;
	
	public Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public JpaGenericDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	public T findById(Class<T> clazz, Serializable id) {
		Assert.notNull(id, "Id can not be null.");
		return entityManager.find(clazz, id);
	}
	
/*	public T findById(Serializable id) {
		return entityManager.find(entityClass, id);
	}*/
	
	public T save(T t) throws RuntimeException {
		try {
			t = entityManager.contains(t) ? t : entityManager.merge(t);
		} catch (Exception e) {
			throw new RuntimeException("保存失败，请联系管理员！");
		}
		return t;
	}

	public Collection<T> save(Collection<T> ts) {
		Collection<T> collection = new HashSet<T>();
		for (T t : ts) {
			collection.add(save(t));
		}
		return collection;
	}

	public T persist(T t) {
		try {
			entityManager.persist(t);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("错误：保存新建实例时发生异常：" + e.getMessage());
		}
		return t;
	}

	public boolean persist(Collection<T> ts) {
		try {
			for (T t : ts) {
				entityManager.persist(t);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public T merge(T t) {
		try {
			t = entityManager.contains(t) ? t : entityManager.merge(t);
		} catch (Exception e) {
			throw new RuntimeException("更新失败，请联系管理员！");
		}
		return t;
	}

	public Collection<T> merge(Collection<T> ts) {
		Collection<T> collection = new HashSet<T>();
		for (T t : ts) {
			collection.add(merge(t));
		}
		return collection;
	}

	public void remove(T t) {
		if (t== null) {
			throw new RuntimeException("请求删除对象为空！");
		}
		try {
			if (entityManager.contains(t)) {
				entityManager.remove(t);
			} else {
				Object id = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(t);
				entityManager.remove(entityManager.find(t.getClass(), id));
			}
		} catch (Exception e) {
			throw new RuntimeException("删除对象时出错，请联系管理员！");
		}
		
	}

	public void remove(Collection<T> ts) {
		for (T t : ts) {
			remove(t);
		}
		
	}

	public void remove(Class<T> clazz, Serializable id) {
		Assert.notNull(id, "Id can not be null.");
		try {
			entityManager.remove(entityManager.find(clazz, id));
		} catch (Exception e) {
		}
	}

	public void remove(Class<T> clazz, Collection<Serializable> ids) {
		for (Serializable id : ids) {
			remove(clazz, id);
		}
		
	}
	
	public T refresh(Class<T> clazz, T t) throws RuntimeException {
		String id = (String) entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(t);
		if (t== null) {
			throw new RuntimeException("请求刷新的实体为空！");
		}
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		if (entityManager.contains(t)) {
			return t;
		} else {
			return entityManager.find(clazz, id);
		}
	}

	public void clear() {
		entityManager.clear();
	}

	public void detach(T t) {
		entityManager.detach(t);
	}

	public void detach(Collection<T> ts) {
		for (T t : ts) {
			detach(t);
		}
	}

	public boolean isManaged(T t) {
		return entityManager.contains(t);
	}

	public void flush() {
		entityManager.flush();
	}
	
	public String getEntityName() {
		return entityClass.getName();
	}

    /**
     * 数据查询
     *
     * @param start
     *            数据起始游标值
     * @param pageSize
     *            分页大小
     * @param pageResult
     *            结果集封装对象
     * @param cq
     *            数据查询
     * @param cqCount
     *            符合条件的总条数
     * @return
     */
//    protected PageResult<T> getResult(CriteriaQuery<T> cq,
//            List<Predicate> predicates, CriteriaQuery<Long> cqCount,
//            List<Predicate> predicatesCount, int start, int pageSize) {
// 
//        PageResult<T> pageResult = new PageResult<T>();
//        // 设置查询条件
//        cq.where(predicates.toArray(new Predicate[0]));
//        cqCount.where(predicatesCount.toArray(new Predicate[0]));
//        try {
//            // 查询符合条件的数据总数
//            long total = getEntityManager().createQuery(cqCount)
//                    .getSingleResult();
//            // 如果结果总数超过了Integer的最大值 则Integer的返回最大值
//            pageResult.setTotalCount(total <= Integer.MAX_VALUE ? (int) total
//                    : Integer.MAX_VALUE);
//        } catch (Exception e) {
//            // e.printStackTrace();
//            pageResult.setTotalCount(0);
//        }
//        // 判断分页
//        if (start > pageResult.getTotalCount() && pageSize > 0) {
//            int newIndex = pageResult.getTotalCount() % pageSize == 0 ? pageResult
//                    .getTotalCount() / pageSize
//                    : pageResult.getTotalCount() / pageSize + 1;
//            start = (newIndex - 1) * pageSize;
//            pageResult.setChangeIndex(newIndex);
//            pageResult.setFirst(start);
//            pageResult.setPageSize(pageSize);
//        }
//        // 分页查询
//        TypedQuery<T> tq = entityManager.createQuery(cq);
//        if (start >= 0 && pageSize > 0) {
//            tq.setFirstResult(start).setMaxResults(pageSize);
//        }
//        pageResult.setPageResultList(tq.getResultList());
//        return pageResult;
//    }
}
