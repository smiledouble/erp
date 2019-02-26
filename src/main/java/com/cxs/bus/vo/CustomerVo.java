package com.cxs.bus.vo;

import com.cxs.bus.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/21 15:24
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVo extends Customer {

    private Integer page;
    private Integer limit;

}
