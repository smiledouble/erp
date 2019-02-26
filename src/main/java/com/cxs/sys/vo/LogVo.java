package com.cxs.sys.vo;

import com.cxs.sys.domain.Log;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 16:32
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogVo extends Log {

    //起止时间
    private Date startDate;
    private Date endDate;

    //分页
    private Integer page;
    private Integer limit;
}
