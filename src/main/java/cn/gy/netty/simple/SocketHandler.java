package cn.gy.netty.simple;

/**
 * Created by gaoyang on 2017/11/1.
 */
public interface SocketHandler {

    void read(SocketHandlerContext context, Object msg);

    void write(SocketHandlerContext context, Object msg);

    void flush();

}
