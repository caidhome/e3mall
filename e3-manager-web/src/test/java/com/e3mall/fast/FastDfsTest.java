package com.e3mall.fast;

import com.e3mall.common.utils.FastDFSClient;
import org.junit.Test;

public class FastDfsTest {

    @Test
    public void testDfsClient() throws Exception{
        FastDFSClient fastDFSClient = new FastDFSClient("E:\\Profession\\JavaProject\\IntelliJ_IDEA\\e3mall\\e3-manager-web\\src\\main\\resources\\conf\\client.conf");
        String path = fastDFSClient.uploadFile("C:\\Users\\Chenzhiwen\\Pictures\\Saved Pictures\\pic\\Camera Roll\\DSC03131.JPG");
        System.out.print(path);
    }
}
