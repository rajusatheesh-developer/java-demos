package com.demos.java.nio;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReadingFromFile
{

    public static void main(String[] args) throws IOException {

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024*1024);//1Mega byte
        System.out.println("Byte Position : "+byteBuffer.position());
        System.out.println("Byte Limit : "+byteBuffer.limit());

        Path path= Paths.get("ab6dfb5e-3f96-40f3-a029-3f61655c6854.bin");
        try(FileChannel channel=FileChannel.open(path, StandardOpenOption.READ);)
        {
            channel.read(byteBuffer);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("File size in bytes : "+ Files.size(path));
        System.out.println("Byte Buffer Position : "+byteBuffer.position());
        System.out.println("Byte Buffer  Limit : "+byteBuffer.limit());
        byteBuffer.flip();
        IntBuffer intBuffer=byteBuffer.asIntBuffer();

        System.out.println("Int Buffer Position : "+byteBuffer.position());
        System.out.println("Int Buffer  Limit : "+byteBuffer.limit());
        List<Integer> integerList=new ArrayList<Integer>();

        try {
            while (true) {
                integerList.add(intBuffer.get());
            }
        }
        catch (BufferUnderflowException e)
        {
            System.out.println("Read completed");
        }

        integerList.forEach(System.out::println);
    }
}
