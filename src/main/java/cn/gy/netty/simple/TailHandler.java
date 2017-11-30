package cn.gy.netty.simple;

/**
 * 跟channel 交互的第一个handler
 * Created by gaoyang on 2017/11/1.
 */
public class TailHandler implements SocketHandler{

    @Override
    public void read(SocketHandlerContext context, Object msg) {
        context.fireRead(msg);
    }

    @Override
    public void write(SocketHandlerContext context, Object msg) {
//        context
    }

    @Override
    public void flush() {

    }
}
