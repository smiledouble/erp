package com.cxs.bus.service.impl;

import com.cxs.bus.domain.Customer;
import com.cxs.bus.mapper.CustomerMapper;
import com.cxs.bus.service.CustomerService;
import com.cxs.bus.vo.CustomerVo;
import com.cxs.sys.utils.DataGridView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/21 15:26
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public DataGridView loadAllCustomer(CustomerVo customerVo) {
        Page<Object> page = null;
        List<Customer> customers = null;
        page = PageHelper.startPage(customerVo.getPage(), customerVo.getLimit());
        customers = this.customerMapper.queryAllCustomer(customerVo);

        if (page.size() == 0) {
            if (customerVo.getPage() == 1) {
                return new DataGridView(page.getTotal(), customers);
            } else {
                //说明当前页的最后一条数据被删掉了 需要查询他的前一页
                page = PageHelper.startPage(customerVo.getPage() - 1, customerVo.getLimit());
                customers = this.customerMapper.queryAllCustomer(customerVo);
            }
        }
        return new DataGridView(page.getTotal(), customers);
    }

    @Override
    public Integer addCustomer(CustomerVo customerVo) {
        return this.customerMapper.insertSelective(customerVo);
    }

    @Override
    public Integer updateCustomer(CustomerVo customerVo) {
        return this.customerMapper.updateByPrimaryKeySelective(customerVo);
    }

    @Override
    public Integer deleteCustomer(Integer id) {
        return this.customerMapper.deleteByPrimaryKey(id);
    }
}
