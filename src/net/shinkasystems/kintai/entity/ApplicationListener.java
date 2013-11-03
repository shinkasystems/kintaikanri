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
public class ApplicationListener implements EntityListener<Application> {

    @Override
    public void preInsert(Application entity, PreInsertContext context) {
    }

    @Override
    public void preUpdate(Application entity, PreUpdateContext context) {
    }

    @Override
    public void preDelete(Application entity, PreDeleteContext context) {
    }

    @Override
    public void postInsert(Application entity, PostInsertContext context) {
    }

    @Override
    public void postUpdate(Application entity, PostUpdateContext context) {
    }

    @Override
    public void postDelete(Application entity, PostDeleteContext context) {
    }
}