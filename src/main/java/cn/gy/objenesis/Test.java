package cn.gy.objenesis;

import cn.gy.model.TestInit;
import cn.gy.model.UserOrder;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.RateLimiter;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yang.gao on 2016/1/26.
 */
public class Test {

    public static void main(String[] args) throws Exception{
        Objenesis objenesis = new ObjenesisStd();
        ObjectInstantiator<TestInit> objectInstantiator = objenesis.getInstantiatorOf(TestInit.class);
        TestInit testInit = objectInstantiator.newInstance();
        System.out.println(testInit.getName());
    }

}

