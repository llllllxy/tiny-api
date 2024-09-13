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

    MySQL("MySQL", "com.mysql.cj.jdbc.Driver", 10),
    POSTGRESQL("POSTGRESQL", "org.postgresql.Driver", 11),
    DB2("DB2", "com.ibm.db2.jcc.DB2Driver", 12),
    ORACLE("ORACLE", "oracle.jdbc.driver.OracleDriver", 13),

    ;

    private String name;
    private String driver;
    private Integer datasourceType;

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

    private DatasourceDriverEnum(String name, String driver, Integer datasourceType) {
        this.name = name;
        this.driver = driver;
        this.datasourceType = datasourceType;
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
