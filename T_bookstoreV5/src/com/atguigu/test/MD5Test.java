package com.atguigu.test;

import com.atguigu.utils.MD5Util;
import org.junit.Test;

/**
 * Date:2022/4/8
 * Author:ybc
 * Description:
 */
public class MD5Test {

    @Test
    public void testMD5(){
        String encode = MD5Util.encode("123456");
        System.out.println(encode);
    }

}
