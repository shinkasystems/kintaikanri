package net.shinkasystems.kintai.entity;

/** */
@javax.annotation.Generated(value = { "Doma", "1.31.0" }, date = "2013-11-03T20:55:09.373+0900")
public final class _User extends org.seasar.doma.jdbc.entity.AbstractEntityType<net.shinkasystems.kintai.entity.User> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("1.31.0");
    }

    private static final _User __singleton = new _User();

    /** the activated */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, java.lang.Boolean, java.lang.Object> $activated = new org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, java.lang.Boolean, java.lang.Object>(net.shinkasystems.kintai.entity.User.class, java.lang.Boolean.class, org.seasar.doma.wrapper.BooleanWrapper.class, null, null, "activated", "ACTIVATED", true, true);

    /** the authorityId */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, java.lang.Integer, java.lang.Object> $authorityId = new org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, java.lang.Integer, java.lang.Object>(net.shinkasystems.kintai.entity.User.class, java.lang.Integer.class, org.seasar.doma.wrapper.IntegerWrapper.class, null, null, "authorityId", "AUTHORITY_ID", true, true);

    /** the displayName */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, java.lang.String, java.lang.Object> $displayName = new org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, java.lang.String, java.lang.Object>(net.shinkasystems.kintai.entity.User.class, java.lang.String.class, org.seasar.doma.wrapper.StringWrapper.class, null, null, "displayName", "DISPLAY_NAME", true, true);

    /** the expireDate */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, java.sql.Date, java.lang.Object> $expireDate = new org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, java.sql.Date, java.lang.Object>(net.shinkasystems.kintai.entity.User.class, java.sql.Date.class, org.seasar.doma.wrapper.DateWrapper.class, null, null, "expireDate", "EXPIRE_DATE", true, true);

    /** the id */
    public final org.seasar.doma.jdbc.entity.AssignedIdPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, java.lang.Integer, java.lang.Object> $id = new org.seasar.doma.jdbc.entity.AssignedIdPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, java.lang.Integer, java.lang.Object>(net.shinkasystems.kintai.entity.User.class, java.lang.Integer.class, org.seasar.doma.wrapper.IntegerWrapper.class, null, null, "id", "ID");

    /** the password */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, java.lang.String, java.lang.Object> $password = new org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, java.lang.String, java.lang.Object>(net.shinkasystems.kintai.entity.User.class, java.lang.String.class, org.seasar.doma.wrapper.StringWrapper.class, null, null, "password", "PASSWORD", true, true);

    /** the role */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, java.lang.String, java.lang.Object> $role = new org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, java.lang.String, java.lang.Object>(net.shinkasystems.kintai.entity.User.class, java.lang.String.class, org.seasar.doma.wrapper.StringWrapper.class, null, null, "role", "ROLE", true, true);

    /** the userName */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, java.lang.String, java.lang.Object> $userName = new org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, java.lang.String, java.lang.Object>(net.shinkasystems.kintai.entity.User.class, java.lang.String.class, org.seasar.doma.wrapper.StringWrapper.class, null, null, "userName", "USER_NAME", true, true);

    private final net.shinkasystems.kintai.entity.UserListener __listener;

    private final org.seasar.doma.jdbc.entity.NamingType __namingType;

    private final String __catalogName;

    private final String __schemaName;

    private final String __tableName;

    private final String __qualifiedTableName;

    private final String __name;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.User, ?>> __idPropertyTypes;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.User, ?>> __entityPropertyTypes;

    private final java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.User, ?>> __entityPropertyTypeMap;

    private _User() {
        __listener = new net.shinkasystems.kintai.entity.UserListener();
        __namingType = org.seasar.doma.jdbc.entity.NamingType.NONE;
        __name = "User";
        __catalogName = "";
        __schemaName = "";
        __tableName = "USER";
        __qualifiedTableName = "USER";
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.User, ?>> __idList = new java.util.ArrayList<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.User, ?>>();
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.User, ?>> __list = new java.util.ArrayList<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.User, ?>>(8);
        java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.User, ?>> __map = new java.util.HashMap<String, org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.User, ?>>(8);
        __list.add($activated);
        __map.put("activated", $activated);
        __list.add($authorityId);
        __map.put("authorityId", $authorityId);
        __list.add($displayName);
        __map.put("displayName", $displayName);
        __list.add($expireDate);
        __map.put("expireDate", $expireDate);
        __idList.add($id);
        __list.add($id);
        __map.put("id", $id);
        __list.add($password);
        __map.put("password", $password);
        __list.add($role);
        __map.put("role", $role);
        __list.add($userName);
        __map.put("userName", $userName);
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
    public void preInsert(net.shinkasystems.kintai.entity.User entity, org.seasar.doma.jdbc.entity.PreInsertContext context) {
        __listener.preInsert(entity, context);
    }

    @Override
    public void preUpdate(net.shinkasystems.kintai.entity.User entity, org.seasar.doma.jdbc.entity.PreUpdateContext context) {
        __listener.preUpdate(entity, context);
    }

    @Override
    public void preDelete(net.shinkasystems.kintai.entity.User entity, org.seasar.doma.jdbc.entity.PreDeleteContext context) {
        __listener.preDelete(entity, context);
    }

    @Override
    public void postInsert(net.shinkasystems.kintai.entity.User entity, org.seasar.doma.jdbc.entity.PostInsertContext context) {
        __listener.postInsert(entity, context);
    }

    @Override
    public void postUpdate(net.shinkasystems.kintai.entity.User entity, org.seasar.doma.jdbc.entity.PostUpdateContext context) {
        __listener.postUpdate(entity, context);
    }

    @Override
    public void postDelete(net.shinkasystems.kintai.entity.User entity, org.seasar.doma.jdbc.entity.PostDeleteContext context) {
        __listener.postDelete(entity, context);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.User, ?>> getEntityPropertyTypes() {
        return __entityPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.User, ?> getEntityPropertyType(String __name) {
        return __entityPropertyTypeMap.get(__name);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.User, ?>> getIdPropertyTypes() {
        return __idPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, ?, ?> getGeneratedIdPropertyType() {
        return null;
    }

    @Override
    public org.seasar.doma.jdbc.entity.VersionPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.User, ?, ?> getVersionPropertyType() {
        return null;
    }

    @Override
    public net.shinkasystems.kintai.entity.User newEntity() {
        return new net.shinkasystems.kintai.entity.User();
    }

    @Override
    public Class<net.shinkasystems.kintai.entity.User> getEntityClass() {
        return net.shinkasystems.kintai.entity.User.class;
    }

    @Override
    public net.shinkasystems.kintai.entity.User getOriginalStates(net.shinkasystems.kintai.entity.User __entity) {
        return null;
    }

    @Override
    public void saveCurrentStates(net.shinkasystems.kintai.entity.User __entity) {
    }

    /**
     * @return the singleton
     */
    public static _User getSingletonInternal() {
        return __singleton;
    }

    /**
     * @return the new instance
     */
    public static _User newInstance() {
        return new _User();
    }

}
