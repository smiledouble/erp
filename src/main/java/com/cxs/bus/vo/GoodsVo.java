package com.cxs.bus.vo;

import com.cxs.bus.domain.Goods;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/24 14:11
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo extends Goods {
    private Integer page;
    private Integer limit;
}
