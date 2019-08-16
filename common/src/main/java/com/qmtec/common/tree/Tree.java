package com.qmtec.common.tree;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Tree {

    private String id;     // 节点唯一索引
    private String nodeData;
    private String title;  //节点标题
    private String href;   //点击节点弹出新窗口对应的 url
    private List<Tree> children = new ArrayList<Tree>(); //子节点
    private boolean spread = false;  //节点是否初始展开
    private boolean checked; //节点是否初始为选中状态
    private boolean disabled;//节点是否为禁用状态
    private boolean Parent;   //是否父级

}
