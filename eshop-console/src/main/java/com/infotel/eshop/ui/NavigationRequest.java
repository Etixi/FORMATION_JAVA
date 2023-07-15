package com.infotel.eshop.ui;

import java.util.Arrays;
import java.util.List;

public class NavigationRequest {
	
	private String target;
	
	private List<Object> params;
	
	public static NavigationRequest of(String target) {
		NavigationRequest request = new NavigationRequest();
		return request;
	}
	
	public NavigationRequest withParams(Object... params) {
		this.params = params == null ? List.of(): Arrays.asList(params);
		return this;
	}

	@Override
	public String toString() {
		return "NavigationRequest [target=" + target + ", params=" + params + "]";
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}




	


	
	
}
