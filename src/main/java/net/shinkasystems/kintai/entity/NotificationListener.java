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
public class NotificationListener implements EntityListener<Notification> {

	@Override
	public void preInsert(Notification entity, PreInsertContext context) {
	}

	@Override
	public void preUpdate(Notification entity, PreUpdateContext context) {
	}

	@Override
	public void preDelete(Notification entity, PreDeleteContext context) {
	}

	@Override
	public void postInsert(Notification entity, PostInsertContext context) {
	}

	@Override
	public void postUpdate(Notification entity, PostUpdateContext context) {
	}

	@Override
	public void postDelete(Notification entity, PostDeleteContext context) {
	}
}