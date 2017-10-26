
package cn.gy.proxy;

import com.google.common.collect.Maps;
import net.sf.cglib.core.ReflectUtils;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author yang.gao created on 2017/2/9 20:52
 * @version $Id$
 */
public class CglibProxy {

    static class ProxySub{

        public ProxySub(Map<String, String> map) {
            this.map = map;
        }

        private Map<String,String> map = Maps.newHashMap();

        public void hello(String sub){
            System.out.println(sub);
        }
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ProxySub.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                objects[0] = "hello " + objects[0];
                System.out.println(o.getClass().getName());
                return methodProxy.invokeSuper(o,objects);
            }
        });
        ProxySub proxySub = (ProxySub)enhancer.create();
        proxySub.hello("world");
    }

}
