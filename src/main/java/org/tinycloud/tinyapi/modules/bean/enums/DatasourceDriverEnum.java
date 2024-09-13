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

    MySQL("MySQL", "com.mysql.cj.jdbc.Driver", 10, "select 1"),
    POSTGRESQL("POSTGRESQL", "org.postgresql.Driver", 11, "select 1"),
    DB2("DB2", "com.ibm.db2.jcc.DB2Driver", 12, "select 1 from SYSIBM.SYSDUMMY1"),
    ORACLE("ORACLE", "oracle.jdbc.driver.OracleDriver", 13, "SELECT 1 FROM DUAL"),

    ;

    private String name;
    private String driver;
    private Integer datasourceType;
    private String connectionTestQuery;

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

    public Integer getDatasourceType() {
        return datasourceType;
    }

    public void setDatasourceType(Integer datasourceType) {
        this.datasourceType = datasourceType;
    }

    public String getConnectionTestQuery() {
        return connectionTestQuery;
    }

    public void setConnectionTestQuery(String connectionTestQuery) {
        this.connectionTestQuery = connectionTestQuery;
    }

    private DatasourceDriverEnum(String name, String driver, Integer datasourceType, String connectionTestQuery) {
        this.name = name;
        this.driver = driver;
        this.datasourceType = datasourceType;
        this.connectionTestQuery = connectionTestQuery;
    }

    private DatasourceDriverEnum() {
    }

    public static DatasourceDriverEnum get(Integer datasourceType) {
        for (DatasourceDriverEnum DatasourceDriverEnum : DatasourceDriverEnum.values()) {
            if (DatasourceDriverEnum.getDatasourceType().equals(datasourceType)) {
                return DatasourceDriverEnum;
            }
        }
        return null;
    }
}
