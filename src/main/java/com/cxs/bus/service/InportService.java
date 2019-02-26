package com.cxs.bus.service;

import com.cxs.bus.domain.Inport;
import com.cxs.bus.vo.InportVo;
import com.cxs.sys.utils.DataGridView;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/21 15:24
 */
public interface InportService {


    DataGridView loadAllInport(InportVo inportVo);

    Integer addInport(InportVo inportVo);

    Integer deleteInport(Integer id);

    Inport queryOneByInportId(Integer id);
}
