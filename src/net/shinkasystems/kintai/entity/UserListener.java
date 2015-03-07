package net.shinkasystems.kintai.entity;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PostDeleteContext;
import org.seasar.doma.jdbc.entity.PostInsertContext;
import org.seasar.doma.jdbc.entity.PostUpdateContext;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;

/**
 * 
 */
public class UserListener implements EntityListener<User> {

	@Override
	public void preInsert(User entity, PreInsertContext context) {
	}

	@Override
	public void preUpdate(User entity, PreUpdateContext context) {
	}

	@Override
	public void preDelete(User entity, PreDeleteContext context) {
	}

	@Override
	public void postInsert(User entity, PostInsertContext context) {
	}

	@Override
	public void postUpdate(User entity, PostUpdateContext context) {
	}

	@Override
	public void postDelete(User entity, PostDeleteContext context) {
	}
}