
package cn.gy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yang.gao created on 2017/2/9 20:17
 * @version $Id$
 */
public class JdkProxy {

    interface TestInter{

        public String hello(String sub);

    }

    public static void main(String[] args) {
        TestInter testInter = (TestInter)Proxy.newProxyInstance(JdkProxy.class.getClassLoader(), new Class[]{TestInter.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println(proxy.getClass().getName());
                        return "hello " + args[0];
                    }
                });
        System.out.println(testInter.hello("world"));
    }

}
