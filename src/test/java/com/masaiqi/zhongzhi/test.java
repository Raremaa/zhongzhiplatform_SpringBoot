package com.masaiqi.zhongzhi;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws Exception {
    }

    @Test
    public void doWrite()throws Exception{
        int sum = 1000000000;//总量
        StringBuffer sb = new StringBuffer(0);
        for(int i=0;i<sum;i++){
            sb.append((char)(Math.random()*26+'A'));
        }
        try(
                FileOutputStream fos = new FileOutputStream(new File("myFile"))
        ) {
            fos.write(sb.toString().getBytes());
        }
    }
}
