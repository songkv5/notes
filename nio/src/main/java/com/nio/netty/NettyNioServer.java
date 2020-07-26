package com.nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

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
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            /**
                             * channelpipline 为channelHandler链提供了容器，并定义了用于在该链上传播入站和出站的事件流的api
                             */
                            socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.writeAndFlush(byteBuf.duplicate())
                                            .addListener(ChannelFutureListener.CLOSE);
                                    super.channelActive(ctx);
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
