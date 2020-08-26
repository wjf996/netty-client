package com.netty.client.netty

import com.netty.client.netty.client.NettyClient
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class NettyClientApplication {

    static void main(String[] args) {
        SpringApplication.run(NettyClientApplication, args)
        new NettyClient().start(new InetSocketAddress("127.0.0.1", 8090))
    }

}
