package net.shinkasystems.kintai.entity.sub;

/** */
@javax.annotation.Generated(value = { "Doma", "1.31.0" }, date = "2013-11-10T21:47:57.584+0900")
public final class _UserData extends org.seasar.doma.jdbc.entity.AbstractEntityType<net.shinkasystems.kintai.entity.sub.UserData> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("1.31.0");
    }

    private static final _UserData __singleton = new _UserData();

    private final org.seasar.doma.jdbc.id.BuiltinIdentityIdGenerator __idGenerator = new org.seasar.doma.jdbc.id.BuiltinIdentityIdGenerator();

    /** the activated */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<net.shinkasystems.kintai.entity.User, net.shinkasystems.kintai.entity.sub.UserData, java.lang.Boolean, java.lang.Object> $activated = new org.seasar.doma.jdbc.entity.BasicPropertyType<net.shinkasystems.kintai.entity.User, net.shinkasystems.kintai.entity.sub.UserData, java.lang.Boolean, java.lang.Object>(net.shinkasystems.kintai.entity.sub.UserData.class, java.lang.Boolean.class, org.seasar.doma.wrapper.BooleanWrapper.class, net.shinkasystems.kintai.entity._User.getSingletonInternal().$activated, null, "activated", "ACTIVATED", true, true);

    /** the authorityId */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<net.shinkasystems.kintai.entity.User, net.shinkasystems.kintai.entity.sub.UserData, java.lang.Integer, java.lang.Object> $authorityId = new org.seasar.doma.jdbc.entity.BasicPropertyType<net.shinkasystems.kintai.entity.User, net.shinkasystems.kintai.entity.sub.UserData, java.lang.Integer, java.lang.Object>(net.shinkasystems.kintai.entity.sub.UserData.class, java.lang.Integer.class, org.seasar.doma.wrapper.IntegerWrapper.class, net.shinkasystems.kintai.entity._User.getSingletonInternal().$authorityId, null, "authorityId", "AUTHORITY_ID", true, true);

    /** the displayName */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<net.shinkasystems.kintai.entity.User, net.shinkasystems.kintai.entity.sub.UserData, java.lang.String, java.lang.Object> $displayName = new org.seasar.doma.jdbc.entity.BasicPropertyType<net.shinkasystems.kintai.entity.User, net.shinkasystems.kintai.entity.sub.UserData, java.lang.String, java.lang.Object>(net.shinkasystems.kintai.entity.sub.UserData.class, java.lang.String.class, org.seasar.doma.wrapper.StringWrapper.class, net.shinkasystems.kintai.entity._User.getSingletonInternal().$displayName, null, "displayName", "DISPLAY_NAME", true, true);

    /** the expireDate */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<net.shinkasystems.kintai.entity.User, net.shinkasystems.kintai.entity.sub.UserData, java.sql.Date, java.lang.Object> $expireDate = new org.seasar.doma.jdbc.entity.BasicPropertyType<net.shinkasystems.kintai.entity.User, net.shinkasystems.kintai.entity.sub.UserData, java.sql.Date, java.lang.Object>(net.shinkasystems.kintai.entity.sub.UserData.class, java.sql.Date.class, org.seasar.doma.wrapper.DateWrapper.class, net.shinkasystems.kintai.entity._User.getSingletonInternal().$expireDate, null, "expireDate", "EXPIRE_DATE", true, true);

    /** the id */
    public final org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<net.shinkasystems.kintai.entity.User, net.shinkasystems.kintai.entity.sub.UserData, java.lang.Integer, java.lang.Object> $id = new org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<net.shinkasystems.kintai.entity.User, net.shinkasystems.kintai.entity.sub.UserData, java.lang.Integer, java.lang.Object>(net.shinkasystems.kintai.entity.sub.UserData.class, java.lang.Integer.class, org.seasar.doma.wrapper.IntegerWrapper.class, net.shinkasystems.kintai.entity._User.getSingletonInternal().$id, null, "id", "ID", __idGenerator);

    /** the password */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<net.shinkasystems.kintai.entity.User, net.shinkasystems.kintai.entity.sub.UserData, java.lang.String, java.lang.Object> $password = new org.seasar.doma.jdbc.entity.BasicPropertyType<net.shinkasystems.kintai.entity.User, net.shinkasystems.kintai.entity.sub.UserData, java.lang.String, java.lang.Object>(net.shinkasystems.kintai.entity.sub.UserData.class, java.lang.String.class, org.seasar.doma.wrapper.StringWrapper.class, net.shinkasystems.kintai.entity._User.getSingletonInternal().$password, null, "password", "PASSWORD", true, true);

    /** the role */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<net.shinkasystems.kintai.entity.User, net.shinkasystems.kintai.entity.sub.UserData, java.lang.String, java.lang.Object> $role = new org.seasar.doma.jdbc.entity.BasicPropertyType<net.shinkasystems.kintai.entity.User, net.shinkasystems.kintai.entity.sub.UserData, java.lang.String, java.lang.Object>(net.shinkasystems.kintai.entity.sub.UserData.class, java.lang.String.class, org.seasar.doma.wrapper.StringWrapper.class, net.shinkasystems.kintai.entity._User.getSingletonInternal().$role, null, "role", "ROLE", true, true);

    /** the userName */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<net.shinkasystems.kintai.entity.User, net.shinkasystems.kintai.entity.sub.UserData, java.lang.String, java.lang.Object> $userName = new org.seasar.doma.jdbc.entity.BasicPropertyType<net.shinkasystems.kintai.entity.User, net.shinkasystems.kintai.entity.sub.UserData, java.lang.String, java.lang.Object>(net.shinkasystems.kintai.entity.sub.UserData.class, java.lang.String.class, org.seasar.doma.wrapper.StringWrapper.class, net.shinkasystems.kintai.entity._User.getSingletonInternal().$userName, null, "userName", "USER_NAME", true, true);

    /** the authorityDisplayName */
    public final org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.sub.UserData, java.lang.String, java.lang.Object> $authorityDisplayName = new org.seasar.doma.jdbc.entity.BasicPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.sub.UserData, java.lang.String, java.lang.Object>(net.shinkasystems.kintai.entity.sub.UserData.class, java.lang.String.class, org.seasar.doma.wrapper.StringWrapper.class, null, null, "authorityDisplayName", "AUTHORITY_DISPLAY_NAME", true, true);

    private final org.seasar.doma.jdbc.entity.NullEntityListener __listener;

    private final org.seasar.doma.jdbc.entity.NamingType __namingType;

    private final String __catalogName;

    private final String __schemaName;

    private final String __tableName;

    private final String __qualifiedTableName;

    private final String __name;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.sub.UserData, ?>> __idPropertyTypes;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.sub.UserData, ?>> __entityPropertyTypes;

    private final java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.sub.UserData, ?>> __entityPropertyTypeMap;

    private _UserData() {
        __listener = new org.seasar.doma.jdbc.entity.NullEntityListener();
        __namingType = org.seasar.doma.jdbc.entity.NamingType.NONE;
        __name = "UserData";
        __catalogName = "";
        __schemaName = "";
        __tableName = "UserData";
        __qualifiedTableName = "UserData";
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.sub.UserData, ?>> __idList = new java.util.ArrayList<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.sub.UserData, ?>>();
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.sub.UserData, ?>> __list = new java.util.ArrayList<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.sub.UserData, ?>>(9);
        java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.sub.UserData, ?>> __map = new java.util.HashMap<String, org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.sub.UserData, ?>>(9);
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
        __list.add($authorityDisplayName);
        __map.put("authorityDisplayName", $authorityDisplayName);
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
    public void preInsert(net.shinkasystems.kintai.entity.sub.UserData entity, org.seasar.doma.jdbc.entity.PreInsertContext context) {
        __listener.preInsert(entity, context);
    }

    @Override
    public void preUpdate(net.shinkasystems.kintai.entity.sub.UserData entity, org.seasar.doma.jdbc.entity.PreUpdateContext context) {
        __listener.preUpdate(entity, context);
    }

    @Override
    public void preDelete(net.shinkasystems.kintai.entity.sub.UserData entity, org.seasar.doma.jdbc.entity.PreDeleteContext context) {
        __listener.preDelete(entity, context);
    }

    @Override
    public void postInsert(net.shinkasystems.kintai.entity.sub.UserData entity, org.seasar.doma.jdbc.entity.PostInsertContext context) {
        __listener.postInsert(entity, context);
    }

    @Override
    public void postUpdate(net.shinkasystems.kintai.entity.sub.UserData entity, org.seasar.doma.jdbc.entity.PostUpdateContext context) {
        __listener.postUpdate(entity, context);
    }

    @Override
    public void postDelete(net.shinkasystems.kintai.entity.sub.UserData entity, org.seasar.doma.jdbc.entity.PostDeleteContext context) {
        __listener.postDelete(entity, context);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.sub.UserData, ?>> getEntityPropertyTypes() {
        return __entityPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.sub.UserData, ?> getEntityPropertyType(String __name) {
        return __entityPropertyTypeMap.get(__name);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<net.shinkasystems.kintai.entity.sub.UserData, ?>> getIdPropertyTypes() {
        return __idPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<net.shinkasystems.kintai.entity.User, net.shinkasystems.kintai.entity.sub.UserData, ?, ?> getGeneratedIdPropertyType() {
        return $id;
    }

    @Override
    public org.seasar.doma.jdbc.entity.VersionPropertyType<java.lang.Object, net.shinkasystems.kintai.entity.sub.UserData, ?, ?> getVersionPropertyType() {
        return null;
    }

    @Override
    public net.shinkasystems.kintai.entity.sub.UserData newEntity() {
        return new net.shinkasystems.kintai.entity.sub.UserData();
    }

    @Override
    public Class<net.shinkasystems.kintai.entity.sub.UserData> getEntityClass() {
        return net.shinkasystems.kintai.entity.sub.UserData.class;
    }

    @Override
    public net.shinkasystems.kintai.entity.sub.UserData getOriginalStates(net.shinkasystems.kintai.entity.sub.UserData __entity) {
        return null;
    }

    @Override
    public void saveCurrentStates(net.shinkasystems.kintai.entity.sub.UserData __entity) {
    }

    /**
     * @return the singleton
     */
    public static _UserData getSingletonInternal() {
        return __singleton;
    }

    /**
     * @return the new instance
     */
    public static _UserData newInstance() {
        return new _UserData();
    }

}
