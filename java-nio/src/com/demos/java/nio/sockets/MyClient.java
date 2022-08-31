package com.demos.java.nio.sockets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class MyClient {
    public static void main(String[] args) throws IOException, InterruptedException {

        InetSocketAddress inetAddress = new InetSocketAddress( 12345);
        SocketChannel socketChannel = SocketChannel.open(inetAddress);
        Socket socket = socketChannel.socket();

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("Hello world");
        charBuffer.flip();
        ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(charBuffer);
        socketChannel.write(byteBuffer);
        socket.close();

        Thread.sleep(5000);
    }
}
