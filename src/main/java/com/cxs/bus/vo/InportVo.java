package com.cxs.bus.vo;

import com.cxs.bus.domain.Inport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/26 16:58
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InportVo extends Inport {

    private Integer page;
    private Integer limit;
    private Date startTime;
    private Date endTime;
}
