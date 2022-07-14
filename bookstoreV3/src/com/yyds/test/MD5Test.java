package com.yyds.test;

import com.yyds.utils.MD5Util;
import org.junit.Test;


public class MD5Test {

    @Test
    public void testMD5(){
        String encode = MD5Util.encode("123456");
        System.out.println(encode);
    }

}
