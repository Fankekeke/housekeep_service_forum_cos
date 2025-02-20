package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ServiceReserveInfo;
import cc.mrbird.febs.cos.dao.ServiceReserveInfoMapper;
import cc.mrbird.febs.cos.entity.UserInfo;
import cc.mrbird.febs.cos.service.IServiceReserveInfoService;
import cc.mrbird.febs.cos.service.IUserInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ServiceReserveInfoServiceImpl extends ServiceImpl<ServiceReserveInfoMapper, ServiceReserveInfo> implements IServiceReserveInfoService {

    private final IUserInfoService userInfoService;

    /**
     * 分页获取服务预约信息
     *
     * @param page               分页对象
     * @param serviceReserveInfo 服务预约信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> querySerciceReservePage(Page<ServiceReserveInfo> page, ServiceReserveInfo serviceReserveInfo) {
        return baseMapper.querySerciceReservePage(page, serviceReserveInfo);
    }

    /**
     * 分页获取服务预约信息
     *
     * @param page               分页对象
     * @param serviceReserveInfo 服务预约信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryOwnerServicePage(Page<ServiceReserveInfo> page, ServiceReserveInfo serviceReserveInfo) {
        return baseMapper.queryOwnerServicePage(page, serviceReserveInfo);
    }

    /**
     * 分页获取服务预约信息
     *
     * @param page               分页对象
     * @param serviceReserveInfo 服务预约信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryWorkerServicePage(Page<ServiceReserveInfo> page, ServiceReserveInfo serviceReserveInfo) {
        return baseMapper.queryWorkerServicePage(page, serviceReserveInfo);
    }

    /**
     * 获取未接单的订单
     *
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> queryNotCheckOrder() {
        return baseMapper.queryNotCheckOrder();
    }

    /**
     * 作业人员接单
     *
     * @param workId  作业人员ID
     * @param orderId 订单ID
     * @return 结果
     */
    @Override
    public boolean workOrderCheck(Integer workId, Integer orderId) {
        // 获取订单信息
        ServiceReserveInfo serviceReserveInfo = baseMapper.selectById(orderId);
        // 获取工作人员
        UserInfo userInfo = userInfoService.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUserId, workId));
        serviceReserveInfo.setWorkUserId(userInfo.getId());
        // 设置订单状态
        serviceReserveInfo.setStatus("2");
        return this.updateById(serviceReserveInfo);
    }
}
