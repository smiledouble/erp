package com.cxs.sys.vo;

import com.cxs.sys.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 20:51
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeVo extends Notice {

    //起止时间
    private Date startDate;
    private Date endDate;

    //分页
    private Integer page;
    private Integer limit;

}
