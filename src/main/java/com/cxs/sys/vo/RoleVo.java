package com.cxs.sys.vo;

import com.cxs.sys.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 15:22
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo extends Role {

    private Integer[] ids;

    private Integer page;
    private Integer limit;
}
