package cn.gy.test;

/**
 * Created by yang.gao on 2016/1/29.
 */
public abstract class BizTemplate {

    public abstract void check();

    public abstract void process();

    public void execute(){
        check();
        process();
    }

}
