package com.demos.java.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ReadingWritingBuffers
{

    public static void main(String[] args) {

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024*1024);//1Mega byte
        byteBuffer.putInt(10);
        byteBuffer.putInt(20);
        byteBuffer.putInt(30);

        System.out.println("Byte Position : "+byteBuffer.position());
        System.out.println("Byte Limit : "+byteBuffer.limit());
        byteBuffer.flip(); // sets limit to current position and current position to 0
        IntBuffer intBuffer=byteBuffer.asIntBuffer();
        System.out.println("Int Position : "+intBuffer.position());
        System.out.println("Int Limit : "+intBuffer.limit());

    }
}
