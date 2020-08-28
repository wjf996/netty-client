package com.netty.client.netty

import groovy.util.logging.Slf4j
import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder



@Slf4j
class NettyClient {
    void start(InetSocketAddress socketAddress) {
        def loopGroup = new NioEventLoopGroup()
        def b = new Bootstrap()
        b.group(loopGroup)
                .channel(NioSocketChannel)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        def p = channel.pipeline()
                        p.addLast("decoder", new StringDecoder())
                        p.addLast("encoder", new StringEncoder())
                        p.addLast(new NettyClientHandler())
                    }
                })
        b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
        b.option(ChannelOption.SO_KEEPALIVE, true)
        b.option(ChannelOption.TCP_NODELAY, true)
        try {
            ChannelFuture future = b.connect(socketAddress).sync()
            log.info("客户端连接服务端成功。。。。。")
            future.channel().writeAndFlush("你好，我是王俊峰")
            future.channel().closeFuture().sync()
        } catch (Exception e) {
            e.printStackTrace()
        } finally {
            loopGroup.shutdownGracefully()
        }
    }
}
