package com.qmtec.dataSource;

import lombok.*;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestParams {
    private int page;
    private int size;

    private String timeFild;//时间字段
    private String fildType;//字段类型
    private String createTimeStart;
    private String createTimeEnd;

}
