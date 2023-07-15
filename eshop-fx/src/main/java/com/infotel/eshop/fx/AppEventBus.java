
package com.infotel.eshop.fx;

import com.google.common.eventbus.EventBus;

public enum AppEventBus {
	Instance;
	
	private EventBus delegate = new EventBus();
	
	public void register(Object obj) {
		delegate.register(obj);
	}
	
	public void send(Object event) {
		delegate.post(event);
	}
}
