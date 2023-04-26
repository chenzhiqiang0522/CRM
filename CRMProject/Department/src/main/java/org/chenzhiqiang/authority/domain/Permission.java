package org.chenzhiqiang.authority.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    private Long id;
    private String name;
    private String url;
    private String descs;
    private String sn;
    private Long parent_id;

}
