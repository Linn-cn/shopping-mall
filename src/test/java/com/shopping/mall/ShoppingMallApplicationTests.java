package com.shopping.mall;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;


@SpringBootTest
public class ShoppingMallApplicationTests {

    @Test
    public void demo() {
        System.out.println(DigestUtils.md5DigestAsHex("000000".getBytes()));
    }

}
