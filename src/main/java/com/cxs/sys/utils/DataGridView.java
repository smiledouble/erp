package com.cxs.sys.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 17:35
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView {

    private Integer code = 0;
    private String msg = "";
    private Long count;
    private List<?> data;

    public DataGridView(Long count, List<?> data) {
        this.count = count;
        this.data = data;
    }
}
