package com.cxs.bus.mapper;

import com.cxs.bus.domain.Goods;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    //全查询商品
    List<Goods> queryAllGoods(Goods goods);
}