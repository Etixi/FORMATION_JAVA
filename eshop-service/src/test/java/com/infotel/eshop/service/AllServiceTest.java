package com.infotel.eshop.service;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
//@SelectClasses({
//	AccountServletTest.class,
//	CatalogServiceTest.class
//})

@SelectPackages("com.infotel.eshop.service")

public class AllServiceTest {

}
