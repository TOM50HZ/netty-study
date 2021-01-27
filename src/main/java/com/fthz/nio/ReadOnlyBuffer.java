package com.fthz.nio;

import java.nio.ByteBuffer;

public class ReadOnlyBuffer {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        for(int i = 0 ; i < 64; i++){
            buffer.put((byte)i);
        }

        buffer.flip();

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        while(readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }
    }
}
