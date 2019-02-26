package com.cxs.bus.service.impl;

import com.cxs.bus.domain.Goods;
import com.cxs.bus.mapper.GoodsMapper;
import com.cxs.bus.service.GoodsService;
import com.cxs.bus.vo.GoodsVo;
import com.cxs.sys.utils.DataGridView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/24 14:12
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public DataGridView loadAllGoods(GoodsVo goodsVo) {
        Page<Object> page = null;
        List<Goods> goods = null;
        page = PageHelper.startPage(goodsVo.getPage(), goodsVo.getLimit());

        goods = this.goodsMapper.queryAllGoods(goodsVo);
        if (page.size() == 0) {
            if (goodsVo.getPage() == 1) {
                return new DataGridView(page.getTotal(), goods);
            } else {
                //说明当前页的最后一条数据被删掉了 需要查询他的前一页
                page = PageHelper.startPage(goodsVo.getPage() - 1, goodsVo.getLimit());
                goods = this.goodsMapper.queryAllGoods(goodsVo);
            }
        }
        return new DataGridView(page.getTotal(), goods);
    }

    @Override
    public Integer addGoods(GoodsVo goodsVo) {
        return this.goodsMapper.insertSelective(goodsVo);
    }

    /**
     * 商品的修改
     * @param goodsVo
     * @return
     */
    @Override
    public Integer updateGoods(GoodsVo goodsVo) {
        return  this.goodsMapper.updateByPrimaryKeySelective(goodsVo);
    }

    @Override
    public Integer deleteGoods(Integer id) {
        return this.goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Goods queryOneByGoodsId(Integer id) {
        return this.goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Goods> queryGoodsByProviderId(Integer id) {
        return this.goodsMapper.queryGoodsByProviderId(id);
    }
}
