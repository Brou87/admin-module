/*
 * This file is generated by jOOQ.
 */
package io.surati.gap.admin.module.jooq.generated.tables;


import io.surati.gap.admin.module.jooq.generated.Keys;
import io.surati.gap.admin.module.jooq.generated.Public;
import io.surati.gap.admin.module.jooq.generated.tables.records.AppUserRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 * @since 0.1
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AppUser extends TableImpl<AppUserRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.app_user</code>
     */
    public static final AppUser APP_USER = new AppUser();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AppUserRecord> getRecordType() {
        return AppUserRecord.class;
    }

    /**
     * The column <code>public.app_user.id</code>.
     */
    public final TableField<AppUserRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.app_user.login</code>.
     */
    public final TableField<AppUserRecord, String> LOGIN = createField(DSL.name("login"), SQLDataType.VARCHAR(25).nullable(false), this, "");

    /**
     * The column <code>public.app_user.password</code>.
     */
    public final TableField<AppUserRecord, String> PASSWORD = createField(DSL.name("password"), SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>public.app_user.salt</code>.
     */
    public final TableField<AppUserRecord, String> SALT = createField(DSL.name("salt"), SQLDataType.VARCHAR(30).nullable(false), this, "");

    /**
     * The column <code>public.app_user.blocked</code>.
     */
    public final TableField<AppUserRecord, Boolean> BLOCKED = createField(DSL.name("blocked"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("FALSE", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>public.app_user.profile_id</code>.
     */
    public final TableField<AppUserRecord, Long> PROFILE_ID = createField(DSL.name("profile_id"), SQLDataType.BIGINT, this, "");

    private AppUser(Name alias, Table<AppUserRecord> aliased) {
        this(alias, aliased, null);
    }

    private AppUser(Name alias, Table<AppUserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.app_user</code> table reference
     */
    public AppUser(String alias) {
        this(DSL.name(alias), APP_USER);
    }

    /**
     * Create an aliased <code>public.app_user</code> table reference
     */
    public AppUser(Name alias) {
        this(alias, APP_USER);
    }

    /**
     * Create a <code>public.app_user</code> table reference
     */
    public AppUser() {
        this(DSL.name("app_user"), null);
    }

    public <O extends Record> AppUser(Table<O> child, ForeignKey<O, AppUserRecord> key) {
        super(child, key, APP_USER);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<AppUserRecord> getPrimaryKey() {
        return Keys.APP_USER_PKEY;
    }

    @Override
    public List<UniqueKey<AppUserRecord>> getKeys() {
        return Arrays.<UniqueKey<AppUserRecord>>asList(Keys.APP_USER_PKEY);
    }

    @Override
    public List<ForeignKey<AppUserRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<AppUserRecord, ?>>asList(Keys.APP_USER_ID_FKEY, Keys.PROFILE_APP_USER_PROFILE_ID_FKEY);
    }

    private transient Person _person;
    private transient Profile _profile;

    public Person person() {
        if (_person == null)
            _person = new Person(this, Keys.APP_USER_ID_FKEY);

        return _person;
    }

    public Profile profile() {
        if (_profile == null)
            _profile = new Profile(this, Keys.PROFILE_APP_USER_PROFILE_ID_FKEY);

        return _profile;
    }

    @Override
    public AppUser as(String alias) {
        return new AppUser(DSL.name(alias), this);
    }

    @Override
    public AppUser as(Name alias) {
        return new AppUser(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AppUser rename(String name) {
        return new AppUser(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AppUser rename(Name name) {
        return new AppUser(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, String, String, String, Boolean, Long> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
