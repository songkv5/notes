package com.nio.origin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
    public void server(int port) throws Exception{
        // 打开一个socketchannel
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket serverSocket = serverChannel.socket();
        SocketAddress address = new InetSocketAddress(port);
        serverSocket.bind(address);

        Selector selector = Selector.open();
        // 将注册到选择器
        SelectionKey serverSelectionKey = serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        ByteBuffer msg = ByteBuffer.wrap("Hi".getBytes());

        while (true) {
            try {
                /**
                 * 阻塞方法，将一直阻塞到下一个事件传入
                 */
                int select = selector.select();
            } catch (IOException e) {
                break;
            }
            /**
             * 获取所有接收事件的selectionKey实例。
             * 参考selectionkey的注解：selectionkey相当于一个令牌。这个令牌代表了SelectableChannel到selector的注册关系。
             * 每次都是在channel注册到selector的时候产生。可以产生后一直保持valid状态知道直接调用cancel方法（变成cancel状态）
             *
             */
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    /**
                     * 判断事件是否是一个新的已经就绪可以被接收的连接
                     */
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        // 注册到选择器
                        client.register(selector, SelectionKey.OP_WRITE|SelectionKey.OP_READ, msg.duplicate());
                        System.out.println("accept from client " + client);
                    }

                    /**
                     * 检查socket是否已经准备好写数据
                     */
                    if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        while(buffer.hasRemaining()) {
                            // 将数据写到已连接的客户端
                            if (client.write(buffer) == 0) {
                                break;
                            }
                        }
                        client.close();
                    }
                } catch (IOException e) {
                    key.cancel();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Server().server(9000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
