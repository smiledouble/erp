package com.cxs.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/21 15:31
 */
@Controller
public class BusinessController {

    private static final String MODEL = "bus";

    /**
     * 跳转到客户管理
     *
     * @return
     */
    @GetMapping(MODEL + "/toCustomerManager")
    public ModelAndView toCustomerManager() {
        ModelAndView view = new ModelAndView("business/customer/customerManager");
        return view;
    }

    /**
     * 跳转到供应商管理
     *
     * @return
     */
    @GetMapping(MODEL + "/toProviderManager")
    public ModelAndView toProviderManager() {
        ModelAndView view = new ModelAndView("business/provider/ProviderManager");
        return view;
    }


    /**
     * 跳转到商品管理页面
     *
     * @return
     */
    @GetMapping(MODEL + "/toGoodsManager")
    public ModelAndView toGoodsManager() {
        ModelAndView view = new ModelAndView("business/goods/goodsManager");
        return view;
    }

    /**
     * 跳转到商品左边的树
     *
     * @return
     */
    @GetMapping(MODEL + "/toGoodsLeftManager")
    public ModelAndView toGoodsLeftManager() {
        ModelAndView view = new ModelAndView("business/goods/goodsLeftManager");
        return view;
    }

    /**
     * 跳转到商品右边的表格
     *
     * @return
     */
    @GetMapping(MODEL + "/toGoodsRightManager")
    public ModelAndView toGoodsRightManager() {
        ModelAndView view = new ModelAndView("business/goods/goodsRightManager");
        return view;
    }

    /**
     * 跳转到进货管理
     *
     * @return
     */
    @GetMapping(MODEL + "/toInportManager")
    public ModelAndView toInportManager() {
        ModelAndView view = new ModelAndView("business/inport/inportManager");
        return view;
    }

}
