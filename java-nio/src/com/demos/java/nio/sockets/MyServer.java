package com.demos.java.nio.sockets;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class MyServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(12345));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            System.out.println("We are waiting for connections....");
            int events = selector.select();
            System.out.println("Events : " + events);
            Set<SelectionKey> keys = selector.keys();
            for (SelectionKey key : keys) {
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {

                    System.out.println("Accepting connection...");
                    ServerSocketChannel sc = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = sc.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    key.cancel();

                } else {
                    System.out.println("Reading content...");
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    sc.read(buffer);
                    buffer.flip();
                    CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer);
                    String data = new String(charBuffer.array());
                    System.out.println(data);
                    buffer.clear();
                   // keys.remove(key);
                    key.channel();
                    sc.close();
                }
            }
        }


    }
}
