
package cn.gy.filter.def;

import java.util.List;

/**
 * @author yang.gao created on 2016/11/30 14:38
 * @version $Id$
 */
public class Filters {

    public static Invoker buildInvokerChain(List<Filter> filters, Invoker invoker){
        Invoker last = invoker;
        for(int i = filters.size() - 1; i >= 0; i --){
            Filter filter = filters.get(i);
            Invoker next = last;
            last = new Invoker() {
                @Override
                public Object invoke(Invocation invocation) {
                    return filter.filter(next,invocation);
                }
            };
        }
        return last;
    }

}
