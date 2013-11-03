package net.shinkasystems.kintai.entity;

/** */
@javax.annotation.Generated(value = { "Doma", "1.31.0" }, date = "2013-11-03T20:55:09.367+0900")
public final class _Application extends org.seasar.doma.jdbc.entity.AbstractEntityType<net.shinkasystems.kintai.entity.Application> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("1.31.0");
    }

    private static final _Application __singleton = new _Application();

    /** the applicantId */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.lang.Integer, java.lang.Object> $applicantId = new org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.lang.Integer, java.lang.Object>(net.shinkasystems.kintai.entity.Application.class, java.lang.Integer.class, org.seasar.doma.wrapper.IntegerWrapper.class, null, null, "applicantId", "APPLICANT_ID", true, true);

    /** the commentApplycant */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.lang.String, java.lang.Object> $commentApplycant = new org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.lang.String, java.lang.Object>(net.shinkasystems.kintai.entity.Application.class, java.lang.String.class, org.seasar.doma.wrapper.StringWrapper.class, null, null, "commentApplycant", "COMMENT_APPLYCANT", true, true);

    /** the commentAuthority */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.lang.String, java.lang.Object> $commentAuthority = new org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.lang.String, java.lang.Object>(net.shinkasystems.kintai.entity.Application.class, java.lang.String.class, org.seasar.doma.wrapper.StringWrapper.class, null, null, "commentAuthority", "COMMENT_AUTHORITY", true, true);

    /** the createDate */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.sql.Date, java.lang.Object> $createDate = new org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.sql.Date, java.lang.Object>(net.shinkasystems.kintai.entity.Application.class, java.sql.Date.class, org.seasar.doma.wrapper.DateWrapper.class, null, null, "createDate", "CREATE_DATE", true, true);

    /** the form */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.lang.String, java.lang.Object> $form = new org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.lang.String, java.lang.Object>(net.shinkasystems.kintai.entity.Application.class, java.lang.String.class, org.seasar.doma.wrapper.StringWrapper.class, null, null, "form", "FORM", true, true);

    /** the id */
    public final org.seasar.doma.jdbc.entity.AssignedIdPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.lang.Integer, java.lang.Object> $id = new org.seasar.doma.jdbc.entity.AssignedIdPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.lang.Integer, java.lang.Object>(net.shinkasystems.kintai.entity.Application.class, java.lang.Integer.class, org.seasar.doma.wrapper.IntegerWrapper.class, null, null, "id", "ID");

    /** the proxyId */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.lang.Integer, java.lang.Object> $proxyId = new org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.lang.Integer, java.lang.Object>(net.shinkasystems.kintai.entity.Application.class, java.lang.Integer.class, org.seasar.doma.wrapper.IntegerWrapper.class, null, null, "proxyId", "PROXY_ID", true, true);

    /** the status */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.lang.String, java.lang.Object> $status = new org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.lang.String, java.lang.Object>(net.shinkasystems.kintai.entity.Application.class, java.lang.String.class, org.seasar.doma.wrapper.StringWrapper.class, null, null, "status", "STATUS", true, true);

    /** the term */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.sql.Date, java.lang.Object> $term = new org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.sql.Date, java.lang.Object>(net.shinkasystems.kintai.entity.Application.class, java.sql.Date.class, org.seasar.doma.wrapper.DateWrapper.class, null, null, "term", "TERM", true, true);

    /** the updateDate */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.sql.Date, java.lang.Object> $updateDate = new org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, java.sql.Date, java.lang.Object>(net.shinkasystems.kintai.entity.Application.class, java.sql.Date.class, org.seasar.doma.wrapper.DateWrapper.class, null, null, "updateDate", "UPDATE_DATE", true, true);

    private final net.shinkasystems.kintai.entity.ApplicationListener __listener;

    private final org.seasar.doma.jdbc.entity.NamingType __namingType;

    private final String __catalogName;

    private final String __schemaName;

    private final String __tableName;

    private final String __qualifiedTableName;

    private final String __name;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.Application, ?>> __idPropertyTypes;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.Application, ?>> __entityPropertyTypes;

    private final java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.Application, ?>> __entityPropertyTypeMap;

    private _Application() {
        __listener = new net.shinkasystems.kintai.entity.ApplicationListener();
        __namingType = org.seasar.doma.jdbc.entity.NamingType.NONE;
        __name = "Application";
        __catalogName = "";
        __schemaName = "";
        __tableName = "APPLICATION";
        __qualifiedTableName = "APPLICATION";
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.Application, ?>> __idList = new java.util.ArrayList<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.Application, ?>>();
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.Application, ?>> __list = new java.util.ArrayList<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.Application, ?>>(10);
        java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.Application, ?>> __map = new java.util.HashMap<String, org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.Application, ?>>(10);
        __list.add($applicantId);
        __map.put("applicantId", $applicantId);
        __list.add($commentApplycant);
        __map.put("commentApplycant", $commentApplycant);
        __list.add($commentAuthority);
        __map.put("commentAuthority", $commentAuthority);
        __list.add($createDate);
        __map.put("createDate", $createDate);
        __list.add($form);
        __map.put("form", $form);
        __idList.add($id);
        __list.add($id);
        __map.put("id", $id);
        __list.add($proxyId);
        __map.put("proxyId", $proxyId);
        __list.add($status);
        __map.put("status", $status);
        __list.add($term);
        __map.put("term", $term);
        __list.add($updateDate);
        __map.put("updateDate", $updateDate);
        __idPropertyTypes = java.util.Collections.unmodifiableList(__idList);
        __entityPropertyTypes = java.util.Collections.unmodifiableList(__list);
        __entityPropertyTypeMap = java.util.Collections.unmodifiableMap(__map);
    }

    @Override
    public org.seasar.doma.jdbc.entity.NamingType getNamingType() {
        return __namingType;
    }

    @Override
    public String getName() {
        return __name;
    }

    @Override
    public String getCatalogName() {
        return __catalogName;
    }

    @Override
    public String getSchemaName() {
        return __schemaName;
    }

    @Override
    public String getTableName() {
        return __tableName;
    }

    @Override
    public String getQualifiedTableName() {
        return __qualifiedTableName;
    }

    @Override
    public void preInsert(net.shinkasystems.kintai.entity.Application entity, org.seasar.doma.jdbc.entity.PreInsertContext context) {
        __listener.preInsert(entity, context);
    }

    @Override
    public void preUpdate(net.shinkasystems.kintai.entity.Application entity, org.seasar.doma.jdbc.entity.PreUpdateContext context) {
        __listener.preUpdate(entity, context);
    }

    @Override
    public void preDelete(net.shinkasystems.kintai.entity.Application entity, org.seasar.doma.jdbc.entity.PreDeleteContext context) {
        __listener.preDelete(entity, context);
    }

    @Override
    public void postInsert(net.shinkasystems.kintai.entity.Application entity, org.seasar.doma.jdbc.entity.PostInsertContext context) {
        __listener.postInsert(entity, context);
    }

    @Override
    public void postUpdate(net.shinkasystems.kintai.entity.Application entity, org.seasar.doma.jdbc.entity.PostUpdateContext context) {
        __listener.postUpdate(entity, context);
    }

    @Override
    public void postDelete(net.shinkasystems.kintai.entity.Application entity, org.seasar.doma.jdbc.entity.PostDeleteContext context) {
        __listener.postDelete(entity, context);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.Application, ?>> getEntityPropertyTypes() {
        return __entityPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.Application, ?> getEntityPropertyType(String __name) {
        return __entityPropertyTypeMap.get(__name);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.Application, ?>> getIdPropertyTypes() {
        return __idPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, ?, ?> getGeneratedIdPropertyType() {
        return null;
    }

    @Override
    public org.seasar.doma.jdbc.entity.VersionPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.Application, ?, ?> getVersionPropertyType() {
        return null;
    }

    @Override
    public net.shinkasystems.kintai.entity.Application newEntity() {
        return new net.shinkasystems.kintai.entity.Application();
    }

    @Override
    public Class<net.shinkasystems.kintai.entity.Application> getEntityClass() {
        return net.shinkasystems.kintai.entity.Application.class;
    }

    @Override
    public net.shinkasystems.kintai.entity.Application getOriginalStates(net.shinkasystems.kintai.entity.Application __entity) {
        return null;
    }

    @Override
    public void saveCurrentStates(net.shinkasystems.kintai.entity.Application __entity) {
    }

    /**
     * @return the singleton
     */
    public static _Application getSingletonInternal() {
        return __singleton;
    }

    /**
     * @return the new instance
     */
    public static _Application newInstance() {
        return new _Application();
    }

}
