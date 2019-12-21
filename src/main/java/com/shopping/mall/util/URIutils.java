package com.shopping.mall.util;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @program: shopping-mall
 * @classname: URIutils
 * @description:
 * @author: 朱林
 * @create: 2019-11-28 17:36
 **/
public class URIutils {

    private URIutils(){}

    /**
     * 获得主机地址端口路径
     * @param uri
     * @return java.net.URI
     * @date 2019/11/28 17:39
     */
    public static URI getHost(URI uri) {
        URI effectiveURI = null;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return effectiveURI;
    }
}
