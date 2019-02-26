package com.cxs.sys.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 11:13
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode {

    FAIL(0,"失败"),
    SUCCESS(1,"成功");


    private int code;
    private String message;

    public static ResultCode valueOf(int code){
        for (ResultCode result: values()) {
            if(result.code == code){
                return result;
            }
        }
        return null;
    }
}
