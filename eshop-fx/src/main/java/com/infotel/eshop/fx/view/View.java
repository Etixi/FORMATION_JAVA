package com.infotel.eshop.fx.view;

import com.infotel.eshop.fx.AppEventBus;

import javafx.scene.Parent;

public interface View {

	Parent build();

	default void init() {
		// no op
	}
	
	default void navigate(String target) {
		Router.Instance.navigate(target);
	}
	
	default View getView(String name) {
		ViewFactory factory = new ViewFactory();
		return factory.getView(name);
	}
	
	default void register(Object obj) {
		AppEventBus.Instance.register(obj);
	}
	
	default void send(Object event) {
		AppEventBus.Instance.send(event);		
	}

}