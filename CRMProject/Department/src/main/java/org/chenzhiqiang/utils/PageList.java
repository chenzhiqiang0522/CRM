package org.chenzhiqiang.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageList<T> {
    private List<T> rows = new ArrayList<>();
    private Integer total;
}
