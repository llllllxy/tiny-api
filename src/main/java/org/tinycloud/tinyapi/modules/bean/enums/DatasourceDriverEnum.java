package org.tinycloud.tinyapi.modules.bean.enums;

/**
 * <p>
 *     数据库驱动枚举
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-2024/9/13 21:46
 */
public enum DatasourceDriverEnum {

    MySQL("MySQL", "com.mysql.cj.jdbc.Driver"),
    POSTGRESQL("POSTGRESQL", "org.postgresql.Driver"),
    DB2("DB2", "com.ibm.db2.jcc.DB2Driver"),
    ORACLE("ORACLE", "oracle.jdbc.driver.OracleDriver"),

    ;

    private String name;
    private String driver;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    private DatasourceDriverEnum(String name, String driver) {
        this.name = name;
        this.driver = driver;
    }

    private DatasourceDriverEnum() {
    }
}
