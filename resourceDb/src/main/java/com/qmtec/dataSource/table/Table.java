package com.qmtec.dataSource.table;

import lombok.*;

import java.io.Serializable;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Table implements Serializable{
	
	private String id;
	/**
	 * TABLE_CAT String => 表类别（可为 null）
	 *
	 * TABLE_SCHEM String => 表模式（可为 null）
	 *
	 * TABLE_NAME String => 表名称
	 *
	 * TABLE_TYPE String => 表类型。
	 *
	 * REMARKS String => 表的解释性注释
	 *
	 * TYPE_CAT String => 类型的类别（可为 null）
	 *
	 * TYPE_SCHEM String => 类型模式（可为 null）
	 *
	 * TYPE_NAME String => 类型名称（可为 null）
	 *
	 * SELF_REFERENCING_COL_NAME String => 有类型表的指定 "identifier" 列的名称（可为 null）
	 *
	 * REF_GENERATION String
	 */

	private String name;

	private String type;

	private String tabCat;

	private String tabSchem;

	private String typeCat;

	private String typeSchem;

	private String typeName;

	private String SELF_REFERENCING_COL_NAME;

	private String refGeneration;


	private int colSize;

	private String descr;

	private String dbId;
	
	private String dbName;

	// 定义 json for com.sunshine.dms.metadata.table.Schema 各子类
	private Schema schema;

	public Table(String name, String type, String descr) {
		this.name = name;
		this.type = type;
		this.descr = descr;
	}

	public Table(String name) {
		this.name = name;
	}
}
