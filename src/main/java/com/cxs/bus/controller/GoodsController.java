package com.cxs.bus.controller;

import com.cxs.bus.domain.Goods;
import com.cxs.bus.service.GoodsService;
import com.cxs.bus.vo.GoodsVo;
import com.cxs.sys.constant.BaseException;
import com.cxs.sys.constant.BaseResult;
import com.cxs.sys.constant.ResultCode;
import com.cxs.sys.constant.SYSConstant;
import com.cxs.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 21:04
 */
@RestController
public class GoodsController {

    private static final String MODEL = "goods";

    @Value("${fileupload.uploadlocation}")
    private String imagePath;

    @Autowired
    private GoodsService goodsService;

    /**
     * 加载商品
     *
     * @param goodsVo
     * @return
     */
    @GetMapping(MODEL + "/loadAllGoods")
    public DataGridView loadAllGoods(GoodsVo goodsVo) {
        if (null == goodsVo) {
            throw new BaseException(ResultCode.FAIL);
        }
        return this.goodsService.loadAllGoods(goodsVo);
    }

    /**
     * 添加商品
     *
     * @param goodsVo
     * @return
     */

    @PostMapping(MODEL + "/addGoods")
    public BaseResult<?> addGoods(GoodsVo goodsVo) {
        String msg = "";
        if (goodsVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.goodsService.addGoods(goodsVo);
            msg = i > 0 ? SYSConstant.OPERATE_ADD_SUCCESS : SYSConstant.OPERATE_ADD_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_ADD_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }

    /**
     * 修改商品
     *
     * @param goodsVo
     * @return
     */

    @PostMapping(MODEL + "/updateGoods")
    public BaseResult<?> updateGoods(GoodsVo goodsVo) {
        String msg = "";
        if (goodsVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            //先根据id从数据库里面查询出当前要修改的商品信息goods
            Goods goods = this.goodsService.queryOneByGoodsId(goodsVo.getId());
            //从里面取出goods.getGoodsimg() 原来的图片地址
            String goodsimg = goods.getGoodsimg();
            //如果goodsVo.getGoodsImg()==goods.getGoodsimg()不相同，就说明用户修改了图片
            if(!goodsimg.equals(goodsVo.getGoodsimg()) ){
            //先根据goods.getGoodsimg()把原来的图片删除
                File file = new File(imagePath,goodsimg);
                if(file.exists()){
                    file.delete();
                }
            }
            //再把goodsVo.getGoodsImg()保存到数据库里面
            //做修改
            Integer i = this.goodsService.updateGoods(goodsVo);
            msg = i > 0 ? SYSConstant.OPERATE_UPDATE_SUCCESS : SYSConstant.OPERATE_UPDATE_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_UPDATE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }

    /**
     * 删除商品
     *
     * @param goodsVo
     * @return
     */
    @PostMapping(MODEL + "/deleteGoods")
    public BaseResult<?> deleteGoods(GoodsVo goodsVo) {
        String msg = "";
        if (goodsVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            //删除要做判断
            String goodsimg = goodsVo.getGoodsimg();
            if (!goodsimg.equals("defaultimage.jpg")) {
                    //找到图片在硬盘上的位置 然后删掉
                File file = new File(imagePath,goodsimg);
                if(file.exists()){
                    file.delete();
                }
            }
            Integer i = this.goodsService.deleteGoods(goodsVo.getId());
            msg = i > 0 ? SYSConstant.OPERATE_DELETE_SUCCESS : SYSConstant.OPERATE_DELETE_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);

        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_DELETE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }
}
