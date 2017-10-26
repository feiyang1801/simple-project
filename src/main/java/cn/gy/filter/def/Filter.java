package cn.gy.filter.def;

/**
 * @author yang.gao created on 2016/11/30 14:33
 * @version $Id$
 */
public interface Filter {

    public Object filter(Invoker invoker, Invocation invocation);

}
