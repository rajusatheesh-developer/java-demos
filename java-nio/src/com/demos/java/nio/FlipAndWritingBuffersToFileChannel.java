package com.demos.java.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

public class FlipAndWritingBuffersToFileChannel
{

    public static void main(String[] args) throws IOException {

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024*1024);//1Mega byte
        byteBuffer.putInt(10);
        byteBuffer.putInt(20);
        byteBuffer.putInt(30);

        System.out.println("Byte Position : "+byteBuffer.position());
        System.out.println("Byte Limit : "+byteBuffer.limit());

        Path path= Paths.get(UUID.randomUUID().toString().concat(".bin"));
        try(FileChannel channel=FileChannel.open(path,
                StandardOpenOption.CREATE,StandardOpenOption.WRITE);)
        {
            byteBuffer.flip();
            channel.write(byteBuffer);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("File size in bytes : "+ Files.size(path));
    }
}
