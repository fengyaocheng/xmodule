package com.penglecode.xmodule.common.cloud.event;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

import com.penglecode.xmodule.common.cloud.consts.CloudApplicationConstants;

/**
 * 通用配置同步事件Publisher
 * 
 * @author 	pengpeng
 * @date	2019年6月24日 下午3:44:54
 */
public abstract class GeneralConfigSyncEventPublisher<T extends ApplicationEvent> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GeneralConfigSyncEventPublisher.class);
	
	@Autowired
	private ApplicationContext applicationContext;
	
	/**
	 * 同步发布配置同步事件
	 * @param actionType
	 */
	protected void publishEventSync(T event) {
		LOGGER.info(">>> Publish config sync event，event = {}, contextId = {}", event, applicationContext.getId());
		applicationContext.publishEvent(event);
	}
	
	/**
	 * 异步发布配置同步事件
	 * @param actionType
	 */
	protected void publishEventAsync(T event) {
		CompletableFuture.runAsync(() -> publishEventSync(event), CloudApplicationConstants.DEFAULT_EVENT_PUBLISH_EXECUTOR);
	}

	protected ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
}
