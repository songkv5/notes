package com.nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class NettyNioServer {
    public void server(int port) throws Exception{
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello, this is nio netty implement".getBytes(Charset.forName("utf-8")));
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(9000))
                    /**
                     * 当channel被创建时，他会被自动分配到它专属的pipeline
                     * initChannel被调用时，ChannelInitializer将在ChannelPipeline中安装一组自定义的ChannelHandler
                     */
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.writeAndFlush(Unpooled.copiedBuffer("test".getBytes(Charset.forName("utf-8"))));
                            /**
                             * channelpipline 为channelHandler链提供了容器，并定义了用于在该链上传播入站和出站的事件流的api
                             */
                            socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.writeAndFlush(byteBuf.duplicate());

                                    ByteBuf byteBuf2 = Unpooled.copiedBuffer("hello, this is nio netty implement2".getBytes(Charset.forName("utf-8")));
                                    ctx.writeAndFlush(byteBuf2.duplicate());
                                            //监听写动作，写完就执行close操作（关闭连接）;

                                    ByteBuf byteBuf3 = Unpooled.copiedBuffer("hello, this is nio netty implement3".getBytes(Charset.forName("utf-8")));
                                    ctx.writeAndFlush(byteBuf3.duplicate());
                                }
                            }).addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    ByteBuf msgb = (ByteBuf) msg;
                                    String msgContent = msgb.toString(Charset.forName("utf-8"));
                                    System.out.println("receive msg:" + msgContent);

                                    ctx.writeAndFlush(Unpooled.copiedBuffer("我已经收到了你的消息，再见~".getBytes(Charset.forName("utf-8"))))
                                            .addListener(ChannelFutureListener.CLOSE);
                                }

                            });
                        }
                    });
            ChannelFuture f = server.bind().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) {
        try {
            new NettyNioServer().server(9000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
