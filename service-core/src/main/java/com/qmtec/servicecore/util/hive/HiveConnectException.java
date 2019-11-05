package com.qmtec.servicecore.util.hive;

import java.sql.SQLException;

public class HiveConnectException extends SQLException {
    public HiveConnectException() {
        super("Hive server连接异常，请检查相关hive server服务是否正常运行或配置是否正确");
    }
}
