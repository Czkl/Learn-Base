package io.AIO20180723;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;



//作为handler接收客户端连接
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncServerHandler> {

    @Override
    public void completed(AsynchronousSocketChannel result, AsyncServerHandler attachment) {
    /*    //继续接受其他客户端的请求
        AIOServer.clientCount++;
        System.out.println("连接的客户端数：" + AIOServer.clientCount);
        serverHandler.channel.accept(serverHandler, this);
        //创建新的Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //异步读  第三个参数为接收消息回调的业务Handler
        channel.read(buffer, buffer, new ReadHandler(channel));*/
    }

    @Override
    public void failed(Throwable exc, AsyncServerHandler attachment) {

    }
}
