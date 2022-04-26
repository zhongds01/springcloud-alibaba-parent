package com.zds.config;

import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * ThreadPoolConfiguration
 *
 * @author zhongdongsheng
 * @since 2022/4/24
 */
@Configuration
public class ThreadPoolConfiguration {
    @Value("${spring.thread.pool.maxPoolSize:100}")
    private Integer maxPoolSize;

    @Value("${spring.thread.pool.corePoolSize:10}")
    private Integer corePoolSize;

    @Value("${spring.thread.pool.queueCapacity:20}")
    private Integer queueCapacity;

    @Value("${spring.thread.pool.keepAliveSeconds:300}")
    private Integer keepAliveSeconds;

    @Bean
    public AsyncTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 线程池核心线程的数量，默认值为1。核心线程数达到最大值后，其余线程会进入线程队列中等待，直到核心线程得到释放。
        executor.setCorePoolSize(corePoolSize);
        // 线程池维护线程的最大数量，只有当核心线程都被用完并且缓冲队列满后，才会开始申超过请核心线程数的线程，默认值为Integer.MAX_VALUE
        executor.setMaxPoolSize(maxPoolSize);
        // 缓冲队列，核心线程达到最大值时，新线程进入线程队列的队列大小
        executor.setQueueCapacity(queueCapacity);
        // 超出核心线程数外的线程在空闲时候的最大存活时间，默认为60秒
        executor.setKeepAliveSeconds(keepAliveSeconds);
        // 线程名称前缀
        executor.setThreadNamePrefix("Async-thread-");
        executor.setTaskDecorator(new ThreadContextDecorator());
        // 是否等待所有线程执行完毕才关闭线程池，默认值为false
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // waitForTasksToCompleteOnShutdown的等待的时长，默认值为0，即不等待
        executor.setAwaitTerminationSeconds(60);
        // 当没有线程可以被使用时的处理策略（拒绝任务），默认策略为abortPolicy。当核心线程、缓冲队列、最大线程都达到最大值时的处理策略
        /**
         * callerRunsPolicy：用于被拒绝任务的处理程序，直接在execute方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
         * abortPolicy：直接抛出java.util.concurrent.RejectedExecutionException异常
         * discardOldestPolicy：当线程池中的数量等于最大线程数时、抛弃线程池中最后一个要执行的任务，并执行新传入的任务
         * discardPolicy：当线程池中的数量等于最大线程数时，不做任何动作
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();

        return executor;
    }
}
