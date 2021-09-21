package self.practice.codes.sql.multiDataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import self.practice.codes.sql.multiDataSource.entity.OrderInfo;
import self.practice.codes.sql.multiDataSource.mapper.OrderInfoMapper;

import java.math.BigDecimal;

@SpringBootTest
public class Mybatisplustest {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderService orderService;

    @Test
    public void testInsert() {
        long i = 1;
        OrderInfo iphone = null;
        while ( i < 501) {
            iphone = new OrderInfo(i, i, 13l, "iphone" + i
                    , (int) (i * i / 2), BigDecimal.valueOf(5.66 + i));
            orderInfoMapper.insert(iphone);
            i++;
        }
    }

    @Test
    public void testInsert2() {
        OrderInfo user = orderService.findOrderbyId(1L);
        System.out.println(user.getUserId());
        orderService.insert(502L);

    }
}
