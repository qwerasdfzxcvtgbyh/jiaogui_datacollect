package com.qmtec.servicecore.comm;

public enum FlumeTaskType {
    MysqlToKafka(new Integer("1"), "mysql到kafka"),
    KafkaToKafka(new Integer("2"), "kafka到kafka");

    private final Integer value;

    private final String name;

    FlumeTaskType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return this.value;
    }

    public Integer value() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }
}
