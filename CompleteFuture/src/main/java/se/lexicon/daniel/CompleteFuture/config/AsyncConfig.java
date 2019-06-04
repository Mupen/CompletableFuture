package se.lexicon.daniel.CompleteFuture.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer{

	// different pools of threads that deal with different things
	public static final String SERVICE_POOL = "service_pool";
	public static final String PERSISTANCE_POOL = "persistance_pool";
	public static final String CONTROLLER_POOL = "controller_pool";
	public static final String DEFAULT_EXECUTOR = "default_pool";

	@Override
	@Bean(name = DEFAULT_EXECUTOR)
	public Executor getAsyncExecutor() {		
		return newTaskExecutor("DEFAULT-");
	}
	
	@Bean(name = CONTROLLER_POOL)
	public Executor getControllerExecutor() {
		return newTaskExecutor("CONTROLLER-");
	}
	
	@Bean(name = PERSISTANCE_POOL)
	public Executor getPersistanceExecutor() {
		return newTaskExecutor("PERSISTANCE-");
	}
	
	@Bean(name = SERVICE_POOL)
	public Executor getServiceExecutor() {
		return newTaskExecutor("SERVICE-");
	}
	
	private Executor newTaskExecutor(final String taskExecutorNamePrefix) {        
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors()*2);
		executor.setMaxPoolSize(executor.getCorePoolSize()*2);        
        executor.setThreadNamePrefix(taskExecutorNamePrefix);
        executor.setQueueCapacity(500);
        executor.initialize();
        return executor;
    }
	

}