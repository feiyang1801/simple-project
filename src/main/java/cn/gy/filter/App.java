
package cn.gy.filter;

import cn.gy.filter.def.Filter;
import cn.gy.filter.def.Filters;
import cn.gy.filter.def.Invocation;
import cn.gy.filter.def.Invoker;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author yang.gao created on 2016/11/30 14:34
 * @version $Id$
 */
public class App {

    public static void main(String[] args) {
        Invoker invoker = new Invoker() {
            @Override
            public Object invoke(Invocation invocation) {
                System.out.println("Invoker.invoke: " + invocation);
                return "invoker " + invocation.getParm();
            }
        };

        Filter helloFilter = new Filter() {
            @Override
            public Object filter(Invoker invoker, Invocation invocation) {
                System.out.println("helloFilter filter start");
                invocation.setParm("hello " + invocation.getParm());
                return invoker.invoke(invocation);
            }
        };

        Filter sFilter = new Filter() {
            @Override
            public Object filter(Invoker invoker, Invocation invocation) {
                System.out.println("sFilter filter start");
                invocation.setParm("s " + invocation.getParm());
                String result = (String)invoker.invoke(invocation);
                System.out.println("sFilter filter end " + result);
                return result;
            }
        };

        List<Filter> filterList = Lists.newArrayList(sFilter,helloFilter);
        Invoker filterInvoker = Filters.buildInvokerChain(filterList,invoker);
        filterInvoker.invoke(new Invocation("city"));
    }

}
