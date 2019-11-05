package com.qmtec.dataSource.table;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Schema implements Serializable {
	private String name;
	private List<Element> elements = new ArrayList<>();

	public Schema(String name) {
		this.name = name;
	}
}
