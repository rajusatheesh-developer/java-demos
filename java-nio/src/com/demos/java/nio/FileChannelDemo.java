package com.demos.java.nio;

import com.sun.nio.file.ExtendedOpenOption;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelDemo {
    public static void main(String[] args) {

        writeInts();
        readInts();

    }

    public static void writeInts() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.putInt(100);
        try (FileChannel fileChannel = FileChannel.open(
                Paths.get("ints.bin"), StandardOpenOption.CREATE,
                StandardOpenOption.WRITE
        )) {
            fileChannel.write(byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void readInts() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try (FileChannel fileChannel = FileChannel.open(
                Paths.get("ints.bin"),
                StandardOpenOption.READ
        )) {
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            IntBuffer intBuffer = byteBuffer.asIntBuffer();

            int value = 0;
            do {
                value = intBuffer.get();
                System.out.println("Value :" + value);
            }
            while (value >0);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
