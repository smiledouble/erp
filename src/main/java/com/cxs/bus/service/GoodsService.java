package com.cxs.bus.service;

import com.cxs.bus.domain.Goods;
import com.cxs.bus.vo.GoodsVo;
import com.cxs.sys.utils.DataGridView;

import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/21 15:24
 */
public interface GoodsService {


    DataGridView loadAllGoods(GoodsVo goodsVo);

    Integer addGoods(GoodsVo goodsVo);

    Integer updateGoods(GoodsVo goodsVo);

    Integer deleteGoods(Integer id);

    Goods queryOneByGoodsId(Integer id);

    List<Goods> queryGoodsByProviderId(Integer id);
}
