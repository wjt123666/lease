package com.wjt.lease.web.admin.schedule;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName ScheduledTasksTest
 * @Description:
 * @Author 86178
 * @Date 2025/3/6 000617:05
 * @Version 1.0
 */
@SpringBootTest
class ScheduledTasksTest {

    @Autowired
    private ScheduledTasks scheduledTasks;


    @Test
    void test() {
        scheduledTasks.checkLeaseStatus();
    }
}