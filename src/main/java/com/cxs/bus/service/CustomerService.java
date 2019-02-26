package com.cxs.bus.service;

import com.cxs.bus.vo.CustomerVo;
import com.cxs.sys.utils.DataGridView;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/21 15:24
 */
public interface CustomerService {


    DataGridView loadAllCustomer(CustomerVo customerVo);

    Integer addCustomer(CustomerVo customerVo);

    Integer updateCustomer(CustomerVo customerVo);

    Integer deleteCustomer(Integer id);

}
