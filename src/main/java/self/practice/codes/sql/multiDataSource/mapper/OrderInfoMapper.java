package self.practice.codes.sql.multiDataSource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import self.practice.codes.sql.multiDataSource.entity.OrderInfo;

@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
}
