
package cn.gy.filter.def;

/**
 * @author yang.gao created on 2016/11/30 14:27
 * @version $Id$
 */
public class Invocation {

    private String parm;

    public Invocation(String parm) {
        this.parm = parm;
    }

    public String getParm() {
        return parm;
    }

    public void setParm(String parm) {
        this.parm = parm;
    }

    @Override
    public String toString() {
        return "Invocation{" +
                "parm='" + parm + '\'' +
                '}';
    }
}
