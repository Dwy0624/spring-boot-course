package top.dwy.boot.websocket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.dwy.boot.websocket.entity.DeviceData;

import java.util.List;

/**
 * @author alani
 */
@Mapper
public interface DeviceDataMapper extends BaseMapper<DeviceData> {

    /**
     * 获取每个设备最新数据
     */
    List<DeviceData> selectLatestDataForEachDevice();

    /**
     * 获取设备历史数据
     */
    List<DeviceData> selectHistoryData(Long deviceId, Integer hours);

    /**
     * 获取设备最新单条数据
     */
    DeviceData selectLatestByDeviceId(Long deviceId);
}