package cn.gy.netty.simple;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/**
 * handler 运行环境
 * Created by gaoyang on 2017/11/1.
 */
public class SocketHandlerContext {

    private int currentIndex = 0;
    private List<SocketHandler> socketHandlers = Lists.newArrayList();
    private Map<String, Object> attrs = Maps.newHashMap();
    //todo 写缓冲
    private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

    public static SocketHandlerContext build(List<SocketHandler> handlerList, Boolean inStream) {
        SocketHandlerContext handlerContext = new SocketHandlerContext();
        handlerContext.socketHandlers.add(new TailHandler());
        handlerContext.socketHandlers.addAll(handlerList);
        handlerContext.socketHandlers.add(new HeadHandler());
        if (!inStream) {
            handlerContext.currentIndex = handlerContext.socketHandlers.size() - 1;
        }
        return handlerContext;
    }

    //调用下一个handler
    public void fireRead(Object msg) {
        currentIndex++;
        socketHandlers.get(currentIndex).read(this, msg);
    }

    public void fireWrite(Object msg) {
        currentIndex--;
        socketHandlers.get(currentIndex).write(this, msg);
    }

    public void flush() {
        currentIndex--;
//        socketHandlers.get(currentIndex).flush(this);
    }

    public Object getAttr(String key) {
        return attrs.get(key);
    }

    public void setAttr(String key, Object value) {
        attrs.put(key, value);
    }
}
