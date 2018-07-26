package io.NIO20180723;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class NIOClient {

    public static void main(String[] args) throws IOException {

        SocketAddress address = new InetSocketAddress("127.0.0.1",6666);

        //打开监听通道
        SocketChannel socketChannel = SocketChannel.open();
        //如果为 true，则此通道将被置于阻塞模式；如果为 false，则此通道将被置于非阻塞模式
        socketChannel.configureBlocking(false);//开启非阻塞模式

        //创建选择器
        Selector selector = Selector.open();


        if (socketChannel.connect(address)){

            socketChannel.register(selector, SelectionKey.OP_READ);

            byte[] req ="hi, 来啊".getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(req.length);
            byteBuffer.put(req);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            if(!byteBuffer.hasRemaining()){
                System.out.println("Send 2 client successed");
            }

        }else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }

    }
}
