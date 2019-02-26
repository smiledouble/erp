package com.cxs.bus.service.impl;

import com.cxs.bus.domain.Goods;
import com.cxs.bus.domain.Inport;
import com.cxs.bus.mapper.GoodsMapper;
import com.cxs.bus.mapper.InportMapper;
import com.cxs.bus.service.InportService;
import com.cxs.bus.vo.InportVo;
import com.cxs.sys.utils.DataGridView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/26 17:06
 */
@Service
public class InportServiceImpl implements InportService {

    @Autowired
    private InportMapper inportMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public DataGridView loadAllInport(InportVo inportVo) {
        Page<Object> page = null;
        List<Inport> inports = null;
        page = PageHelper.startPage(inportVo.getPage(), inportVo.getLimit());

        inports = this.inportMapper.queryAllInport(inportVo);
        if (page.size() == 0) {
            if (inportVo.getPage() == 1) {
                return new DataGridView(page.getTotal(), inports);
            } else {
                //说明当前页的最后一条数据被删掉了 需要查询他的前一页
                page = PageHelper.startPage(inportVo.getPage() - 1, inportVo.getLimit());
                inports = this.inportMapper.queryAllInport(inportVo);
            }
        }
        return new DataGridView(page.getTotal(), inports);
    }

    @Override
    public Integer addInport(InportVo inportVo) {
        //添加进货的时候记得修改商品的库存
        Goods goods = this.goodsMapper.selectByPrimaryKey(inportVo.getGoodsid());
        goods.setNumber(goods.getNumber() + inportVo.getNumber());
        this.goodsMapper.updateByPrimaryKeySelective(goods);
        return this.inportMapper.insertSelective(inportVo);
    }

    @Override
    public Integer deleteInport(Integer id) {
        //根据订单id 查到这个订单对象
        Inport inport = this.inportMapper.selectByPrimaryKey(id);
        //再根据这个订单对象查到他对应的商品对象
        Goods goods = this.goodsMapper.selectByPrimaryKey(inport.getGoodsid());
        goods.setNumber(goods.getNumber() - inport.getNumber());
        return this.inportMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Inport queryOneByInportId(Integer id) {
        return null;
    }
}
