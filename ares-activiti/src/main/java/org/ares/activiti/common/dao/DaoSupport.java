package org.ares.activiti.common.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class DaoSupport<T, PK extends Serializable> implements Dao<T, PK> {

	public void delete(Collection<T> collection) {
		for (T t : collection) {
			delete(t);
		}
	}

	public Collection<T> save(Collection<T> collection) {
		List<T> saved = new ArrayList<T>();
		for (T t : collection) {
			saved.add(save(t));
		}
		return saved;
	}

	public void deleteById(Collection<PK> ids) {
		for (PK id : ids) {
			deleteById(id);
		}
	}
}
