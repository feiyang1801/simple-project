package cn.gy;

import cn.gy.model.UserOrder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 * des.intern.cutOrder.fill.task
 */
public class App {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        List<UserOrder> userOrderList = new ArrayList<UserOrder>();
        BufferedReader br = new BufferedReader(new FileReader("/Users/gaoyang/datas/oid"));
        String temp;
        while ((temp = br.readLine()) != null) {
            String[] params = temp.split(",");
//            UserOrder userOrder = new UserOrder(params[0].trim(),Long.valueOf(params[1].trim()));
            UserOrder userOrder = new UserOrder(params[1].trim(), Long.valueOf(params[0].trim()));
            userOrderList.add(userOrder);
        }
        Map<String, List<UserOrder>> data = new HashMap<String, List<UserOrder>>();
        int i = 0;
        while (i < userOrderList.size()) {
            int end = i + 20 > userOrderList.size() ? userOrderList.size() : i + 20;
            List<UserOrder> userOrders = userOrderList.subList(i, end);
            data.put("data", userOrders);
            System.out.println(objectMapper.writeValueAsString(data));
            i += 20;
        }

    }

}
