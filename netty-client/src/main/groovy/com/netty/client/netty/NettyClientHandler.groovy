package com.netty.client.netty

import groovy.util.logging.Slf4j
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

@Slf4j
class NettyClientHandler extends ChannelInboundHandlerAdapter{
    @Override
    void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端Active。。。。")
    }

    @Override
    void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("客户端收到消息：{}",msg.toString())
    }

    @Override
    void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace()
        ctx.close()
    }
}
