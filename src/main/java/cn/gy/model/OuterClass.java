package cn.gy.model;

import java.util.NavigableMap;

/**
 * Created by yang.gao on 2017/6/6.
 */
public class OuterClass {

    private String outer;
    public InnerClass innerClass = new InnerClass();

    public class InnerClass{
        private String inner;

        public String getInner() {
            return inner;
        }

        public void setInner(String inner) {
            this.inner = inner;
        }
    }

    public String getOuter() {
        return outer;
    }

    public void setOuter(String outer) {
        this.outer = outer;
    }
}
