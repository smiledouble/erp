package com.cxs.bus.service.impl;

import com.cxs.bus.domain.Provider;
import com.cxs.bus.mapper.ProviderMapper;
import com.cxs.bus.service.ProviderService;
import com.cxs.bus.vo.ProviderVo;
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
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public DataGridView loadAllProvider(ProviderVo providerVo) {
        Page<Object> page = null;
        List<Provider> providers = null;
        page = PageHelper.startPage(providerVo.getPage(), providerVo.getLimit());

        providers = this.providerMapper.queryAllProvider(providerVo);
        if (page.size() == 0) {
            if (providerVo.getPage() == 1) {
                return new DataGridView(page.getTotal(), providers);
            } else {
                //说明当前页的最后一条数据被删掉了 需要查询他的前一页
                page = PageHelper.startPage(providerVo.getPage() - 1, providerVo.getLimit());
                providers = this.providerMapper.queryAllProvider(providerVo);
            }
        }
        return new DataGridView(page.getTotal(), providers);
    }

    /**
     * 查询所有的供应商用于生成树
     *
     * @param providerVo
     * @return
     */
    @Override
    public List<Provider> queryAllProvider(ProviderVo providerVo) {
        return this.providerMapper.queryAllProvider(providerVo);
    }

    @Override
    public Integer addProvider(ProviderVo providerVo) {
        return this.providerMapper.insertSelective(providerVo);
    }

    @Override
    public Integer updateProvider(ProviderVo providerVo) {
        return this.providerMapper.updateByPrimaryKeySelective(providerVo);
    }

    @Override
    public Integer deleteProvider(Integer id) {
        return this.providerMapper.deleteByPrimaryKey(id);
    }
}
