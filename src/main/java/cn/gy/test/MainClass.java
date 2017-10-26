package cn.gy.test;

/**
 * Created by yang.gao on 2016/1/29.
 */
public class MainClass {

    public void test(){
        new BizTemplate() {
            @Override
            public void check() {

            }

            @Override
            public void process() {

            }
        }.execute();
    }

}
