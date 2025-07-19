package org.example.synthetichumancorestarter.androidModule.service;

import org.example.synthetichumancorestarter.androidModule.dto.AndroidCommand;
import org.example.synthetichumancorestarter.androidModule.dto.model.CommandPriority;
import org.example.synthetichumancorestarter.androidModule.exception.ThreadFoolException;
import org.example.synthetichumancorestarter.metrics.service.AndroidCompletedTasksCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CommandExecutorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandExecutorService.class);
    private final AndroidCompletedTasksCounter completedTasksCounter;
    private final ThreadPoolExecutor threadPoolExecutor;

    public CommandExecutorService(AndroidCompletedTasksCounter completedTasksCounter) {
        this.completedTasksCounter = completedTasksCounter;
        this.threadPoolExecutor = new ThreadPoolExecutor(
                1,
                5,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public void executeCommand(AndroidCommand command) {
        if(command.priority().equals(CommandPriority.CRITICAL)){
            processCommand(command);
        }else {
            try {
                threadPoolExecutor.execute(() -> processCommand(command));
            }catch (RejectedExecutionException e){
                throw new ThreadFoolException("Thread for commands are fool, try to add command later");
            }
        }
    }

    public Integer getQueueSize() {
        return threadPoolExecutor.getQueue().size();
    }

    private void processCommand(AndroidCommand command) {
        if(command.priority().equals(CommandPriority.COMMON)){
            try {
                //Имитация "сложной бизнес логики"
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        completedTasksCounter.addCompletedTask(command.author());
        LOGGER.info("Processing command: {}", command);
    }
}