package self.practice.codes.sql.multiDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import self.practice.codes.sql.multiDataSource.entity.OrderInfo;
import self.practice.codes.sql.multiDataSource.mapper.OrderInfoMapper;

import java.math.BigDecimal;

@Service
public class OrderService {

    @Autowired
    OrderInfoMapper orderInfoMapper;

    public OrderInfo findOrderbyId(Long id) {
        try {
            DynamicDataSource.setDataSource("slave");
            return orderInfoMapper.selectById(id);
        }finally {
            DynamicDataSource.clearDataSource();
        }
    }

    public void insert(Long i) {
        try {
            DynamicDataSource.setDataSource("master");
            orderInfoMapper.insert(new OrderInfo(i, i, 13L, "iphone" + i
                    , (int) (i * i / 2), BigDecimal.valueOf(5.66 + i)));
        }finally {
            DynamicDataSource.clearDataSource();
        }
    }
}
