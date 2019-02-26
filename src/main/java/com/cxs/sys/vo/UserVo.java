package com.cxs.sys.vo;

import com.cxs.sys.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 11:09
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserVo extends User {

    private Integer rids[];
    private String oldPwd;

    private Integer page;
    private Integer limit;
}
