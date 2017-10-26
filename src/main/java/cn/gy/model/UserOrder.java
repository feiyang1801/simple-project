package cn.gy.model;

import java.io.Serializable;

/**
 * Created by yang.gao on 2015/11/3.
 */
public class UserOrder implements Serializable{

    private String mobile;
    private Long oid;
//    private String test;

    public UserOrder() {
    }

    public UserOrder(String mobile, Long oid) {
        this.mobile = mobile;
        this.oid = oid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }


    @Override
    public String toString() {
        return "UserOrder{" +
                "mobile='" + mobile + '\'' +
                ", oid=" + oid +
                '}';
    }
}
