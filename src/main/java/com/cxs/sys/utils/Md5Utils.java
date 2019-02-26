package com.cxs.sys.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/25 11:01
 */
public class Md5Utils {

    /**
     * 生成密码等
     */

    public static String createRandomStr() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }


    public static String md5(String source, Object salt, Integer hashIterations) {
        return new Md5Hash(source, salt, hashIterations).toString();
    }


}
