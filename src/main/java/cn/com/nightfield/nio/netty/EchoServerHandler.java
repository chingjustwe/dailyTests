package cn.com.nightfield.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
/**
 * 
 * @author Rocky Chi
 * a netty echo server handler.
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * connect event, server protocol connected will trigger channelActive event.
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server EchoServerHandler: channelActive");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server EchoServerHandler: channelActive");
    }

    /**
     * read data from client
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server EchoServerHandler: channelRead");
        //client request message
        ByteBuf requestBuf = (ByteBuf) msg;
        byte[] bytes = new byte[requestBuf.readableBytes()];
        //read buffer to byte
        requestBuf.readBytes(bytes);
        String message = new String(bytes, "UTF-8");
        System.out.println("client message is '" + message + "'");

        //server response
        /*String response = "Hello World/n";
        //write byte to buffer
        ByteBuf responseBuf = Unpooled.copiedBuffer(response.getBytes());
        ctx.write(responseBuf);*/
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server EchoServerHandler: channelReadComplete");
        /**
         * flush ChannelOutboundBuffer to buffer. NIO can not read/write form channel, all action is done by buffer
         */
        ctx.flush();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server EchoServerHandler: channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server EchoServerHandler: channelUnregistered");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server EchoServerHandler: channelWritabilityChanged");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("server EchoServerHandler: exceptionCaught");
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("server EchoServerHandler: userEventTriggered");
    }
    
    
}
