package com.qmtec.servicecore.comm;

public enum ColumType {
    TINYINT("tinyint"),
    SMALLINT("smallint"),
    INTEGER("integer"),
    INT("int"),
    bigint("bigint"),
    FLOAT("float"),
    DOUBLE("double"),
    DECIMAL("decimal"),
    NUMERIC("numeric"),

    TIMESTAMP("timestamp"),
    DATE("date"),
    INTERVAL("interval"),

    STRING("string"),
    VARCHAR("varchar"),
    CHAR("char"),

    BOOLEAN("boolean"),
    BINARY("binary"),

    ARRAYS("arrays"),
    MAPS("maps"),
    STRUCTS("structs"),
    UNION("union");

    private final String name;

    ColumType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
