package com.wjt.lease.web.admin.schedule;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wjt.lease.model.entity.LeaseAgreement;
import com.wjt.lease.model.enums.LeaseStatus;
import com.wjt.lease.web.admin.service.LeaseAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName ScheduledTasks
 * @Description: 定时任务类
 * @Author 86178
 * @Date 2025/3/6 000616:58
 * @Version 1.0
 */
@Component
public class ScheduledTasks {

    @Autowired
    private LeaseAgreementService leaseAgreementService;

//    @Scheduled(cron = "* * * * * *")
//    public void test() {
//        System.out.println(new Date());
//    }

    @Scheduled(cron = "0 0 0 * * *")
    public void checkLeaseStatus() {
        leaseAgreementService.update(new LambdaUpdateWrapper<LeaseAgreement>()
                .le(LeaseAgreement::getLeaseEndDate, new Date())
                .in(LeaseAgreement::getStatus, LeaseStatus.SIGNED, LeaseStatus.WITHDRAWING, LeaseStatus.RENEWING)
                .set(LeaseAgreement::getStatus, LeaseStatus.EXPIRED));
    }
}
