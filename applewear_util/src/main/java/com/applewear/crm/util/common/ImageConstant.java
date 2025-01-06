package com.applewear.crm.util.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:app-config.properties")
public class ImageConstant {

	@Value("${images.base.dir}")
	private String baseDir;

	@Value("${images.ctx.path}")
	private String contextPath;

	public static String IMAGE_BASE_DIR;
	public static String IMAGE_CONTEXT_PATH;

	public static final String PRODUCT = "/product/";

	public static final String TEMP_DOWNLOAD = "/temp_download/";

	// Spring doesn't support Property Value Injecting for static variable
	@Value("${images.base.dir}")
	public void setBaseDir(String baseDir) {
		ImageConstant.IMAGE_BASE_DIR = baseDir;
	}

	// Spring doesn't support Property Value Injecting for static variable
	@Value("${images.ctx.path}")
	public void setCtxPath(String contextPath) {
		ImageConstant.IMAGE_CONTEXT_PATH = contextPath;
	}
}
