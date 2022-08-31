package com.demos.java.nio;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

public class UsingCharsets {

    public static void main(String[] args) throws IOException {

        CharBuffer charBuffer = CharBuffer.allocate(1024 * 1024);
        String hello = "Hello world from satheesh";
        charBuffer.put(hello);

        System.out.println("Char Buffer Position: " + charBuffer.position());
        System.out.println("Char Buffer Limit: " + charBuffer.limit());

        charBuffer.flip();

        var byteBuffer = StandardCharsets.UTF_8.encode(charBuffer);

        String fileName = UUID.randomUUID().toString().concat(".txt");
        var path = Paths.get(fileName);
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE,
                StandardOpenOption.WRITE);) {

            channel.write(byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("File size in bytes after writing : " + Files.size(path));
        byteBuffer.clear();
        try (FileChannel channel = FileChannel.open(path,
                StandardOpenOption.READ);) {
            channel.read(byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("File size in bytes after reading : " + Files.size(path));
        byteBuffer.flip();
        String data = new String(StandardCharsets.UTF_8.decode(byteBuffer).array());
        System.out.println(data);


    }

}
