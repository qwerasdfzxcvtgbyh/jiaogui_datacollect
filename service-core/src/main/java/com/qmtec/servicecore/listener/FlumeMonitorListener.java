package com.qmtec.servicecore.listener;

import com.qmtec.servicecore.service.flume.FlumeMomitorProcess;
import com.qmtec.servicecore.service.flumeMonitor.FlumeMonitorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.concurrent.*;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class FlumeMonitorListener implements ApplicationRunner {

    @Autowired
    private FlumeMomitorProcess flumeMomitorProcess;
    @Autowired
    private FlumeMonitorService flumeMonitorService;

    private static final int scheduleNum = 1;

    private static final ScheduledExecutorService scheduledThreadPool = new ScheduledThreadPoolExecutor(scheduleNum,
            new BasicThreadFactory.Builder().namingPattern(
                    "flume-monitor-pool-%d").daemon(true).build());

    @Override
    @SuppressWarnings("unchecked")
    public void run(ApplicationArguments args) throws Exception {
        log.info("Start The App Submit Listener......");
        FlumeMonitorListener.addScheduledTask(new MonitorCore(), 0, 30, TimeUnit.SECONDS);
    }

    /**
     * 销毁线程池
     */
    public void destroy() {
        ExecutorService pool = flumeMomitorProcess.getThreadPool();
        try {
            pool.shutdown();
            if (!pool.awaitTermination(10, TimeUnit.SECONDS)) {
                // 超时的时候向线程池中所有的线程发出中断(interrupted)。
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
        }
        log.debug("关闭线程池");
    }

    /**
     * 有且只有一条定时线程任务
     *
     * @param runnable
     * @param initialDelay
     * @param period
     * @param unit
     */
    public static void addScheduledTask(Runnable runnable, long initialDelay, long period, TimeUnit unit) {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) scheduledThreadPool;
        int activeCount = threadPoolExecutor.getActiveCount();
        if (activeCount < scheduleNum) {
            scheduledThreadPool.scheduleAtFixedRate(runnable, initialDelay, period, unit);
        } else {
            throw new RuntimeException("No more tasks can be added");
        }
    }

    /**
     * 查询数据库中处于运行状态的flume，并远程请求端口
     */
    class MonitorCore implements Runnable {

        @Override
        public void run() {
            flumeMonitorService.timingMonitor();
        }

    }

}


