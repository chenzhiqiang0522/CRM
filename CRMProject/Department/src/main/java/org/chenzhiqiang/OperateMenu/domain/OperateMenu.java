package org.chenzhiqiang.OperateMenu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateMenu {
    private Long id;
    private String name;
    private String url;
    private String icon;
    private Long parentId;

    public OperateMenu(String name, String url, String icon, Long parentId) {
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.parentId = parentId;
    }
}
