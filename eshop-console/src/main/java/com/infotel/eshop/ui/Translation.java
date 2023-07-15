package com.infotel.eshop.ui;
import java.util.Locale;
import java.util.ResourceBundle;

public class Translation {
	
	public Translation() {
		super();
		Session.Instance.setAttribute("translation", this);
	}
	
	public void loadBundle() {
		loadBundle(Locale.getDefault());
	}
	public  void loadBundle(Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("com.infotel.eshop.ui.i18n.Labels", locale);
		Session.Instance.setAttribute("bundle", bundle);
	}
}


