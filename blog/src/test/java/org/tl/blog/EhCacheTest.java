package org.tl.blog;

import org.junit.Test;
import org.springframework.cache.ehcache.EhCacheManagerUtils;
import org.tl.blog.common.utils.EhcacheUtil;

public class EhCacheTest extends BlogApplicationTests {

    @Test
    public void testCache(){
        EhcacheUtil.put("clientCache","fsfjlsfjls","t1");
        System.out.println(EhcacheUtil.get("clientCache","fsfjlsfjls"));
    }
}
