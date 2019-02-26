package com.cxs.sys.vo;

import com.cxs.sys.domain.Dept;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/22 10:00
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeptVo extends Dept {
    private Integer page;
    private Integer limit;
}
