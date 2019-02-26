package com.cxs.bus.service;

import com.cxs.bus.domain.Provider;
import com.cxs.bus.vo.ProviderVo;
import com.cxs.sys.utils.DataGridView;

import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/21 15:24
 */
public interface ProviderService {


    DataGridView loadAllProvider(ProviderVo providerVo);

    //查询所有的供应商 用于生成树
    List<Provider> queryAllProvider(ProviderVo providerVo);


    Integer addProvider(ProviderVo providerVo);

    Integer updateProvider(ProviderVo providerVo);

    Integer deleteProvider(Integer id);

}
