package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ServiceReserveInfo;
import cc.mrbird.febs.cos.service.IServiceReserveInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/service-reserve-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ServiceReserveInfoController {

    private final IServiceReserveInfoService serviceReserveInfoService;

    /**
     * 分页获取服务预约信息
     *
     * @param page               分页对象
     * @param serviceReserveInfo 服务预约信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ServiceReserveInfo> page, ServiceReserveInfo serviceReserveInfo) {
        return R.ok(serviceReserveInfoService.querySerciceReservePage(page, serviceReserveInfo));
    }

    /**
     * 分页获取服务预约信息
     *
     * @param page               分页对象
     * @param serviceReserveInfo 服务预约信息
     * @return 结果
     */
    @GetMapping("/queryOwnerServicePage")
    public R queryOwnerServicePage(Page<ServiceReserveInfo> page, ServiceReserveInfo serviceReserveInfo) {
        return R.ok(serviceReserveInfoService.queryOwnerServicePage(page, serviceReserveInfo));
    }

    /**
     * 分页获取服务预约信息
     *
     * @param page               分页对象
     * @param serviceReserveInfo 服务预约信息
     * @return 结果
     */
    @GetMapping("/queryWorkerServicePage")
    public R queryWorkerServicePage(Page<ServiceReserveInfo> page, ServiceReserveInfo serviceReserveInfo) {
        return R.ok(serviceReserveInfoService.queryWorkerServicePage(page, serviceReserveInfo));
    }

    /**
     * 获取未接单的订单
     *
     * @return 结果
     */
    @GetMapping("/queryNotCheckOrder")
    public R queryNotCheckOrder() {
        return R.ok(serviceReserveInfoService.queryNotCheckOrder());
    }

    /**
     * 作业人员接单
     *
     * @param workId  作业人员ID
     * @param orderId 订单ID
     * @return 结果
     */
    @GetMapping("/workOrderCheck")
    public R workOrderCheck(@RequestParam("workId") Integer workId, @RequestParam("orderId") Integer orderId) {
        return R.ok(serviceReserveInfoService.workOrderCheck(workId, orderId));
    }

    /**
     * 支付完成后回调
     *
     * @param orderCode 订单编号
     * @return 结果
     */
    @GetMapping("/callbackPayment")
    public R callbackPayment(@RequestParam("orderCode") String orderCode) {
        // 根据订单编号获取订单信息
        ServiceReserveInfo serviceReserveInfo = serviceReserveInfoService.getOne(Wrappers.<ServiceReserveInfo>lambdaQuery().eq(ServiceReserveInfo::getCode, orderCode));
        serviceReserveInfo.setStatus("1");
        return R.ok(serviceReserveInfoService.updateById(serviceReserveInfo));
    }

    /**
     * 服务预约信息详情
     *
     * @param id 服务预约ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(serviceReserveInfoService.getById(id));
    }

    /**
     * 服务预约信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(serviceReserveInfoService.list());
    }

    /**
     * 新增服务预约信息
     *
     * @param serviceReserveInfo 服务预约信息
     * @return 结果
     */
    @PostMapping
    public R save(ServiceReserveInfo serviceReserveInfo) {
        serviceReserveInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(serviceReserveInfoService.save(serviceReserveInfo));
    }

    /**
     * 修改服务预约信息
     *
     * @param serviceReserveInfo 服务预约信息
     * @return 结果
     */
    @PutMapping
    public R edit(ServiceReserveInfo serviceReserveInfo) {
        return R.ok(serviceReserveInfoService.updateById(serviceReserveInfo));
    }

    /**
     * 删除服务预约信息
     *
     * @param ids ids
     * @return 服务预约信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(serviceReserveInfoService.removeByIds(ids));
    }
}
