package cn.gy.guava;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

/**
 * guava supplier 实现懒加载
 * Created by yang.gao on 2017/5/15.
 */
public class SupplierTest {


    public static void main(String[] args) {
        Supplier<Integer> integerSupplier = Suppliers.memoize(new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("supplier get...");
                return 5;
            }
        });
        System.out.println(integerSupplier.get());
        System.out.println(integerSupplier.get());
    }

}
