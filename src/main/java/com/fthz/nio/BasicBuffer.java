package com.fthz.nio;

import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {
        //举例说明Buffer

        //可存放5个int的Buffer
        IntBuffer intBuffer = IntBuffer.allocate(5);

        //写操作
        for(int i = 0 ; i < intBuffer.capacity(); i++){
            intBuffer.put(i * 2);
        }

        //读写切换
        intBuffer.flip();
        intBuffer.position(1); //2,4,6,8
        intBuffer.limit(3); //2,4

        while(intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }

}
