package com.qmtec.dataSource.table;

import lombok.*;

import java.io.Serializable;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Element implements Serializable {
	private String name;
	private String type;
	//private String dictCatalogId; // 引用字典ID
	private String cname; // 字段中文名
	private boolean isPrimarykey; // 是否为主键（true/false）
	private boolean isNull; // 是否为空（true为空/false不为空）
	private boolean isIncrement; // 是否为增量（true/false）
	private boolean isIndex; // 是否为索引（true/false）
	private boolean isDelete; // 是否为索引（true/false）
	private String length; // 值长度
	private String defaultValue;//默认值
	private String precise;//精度
	private String filedsource;//字段来源  0：原表字段 ，1 ：从数据元中载入的字段 ，2 ：自定义的字段

}
