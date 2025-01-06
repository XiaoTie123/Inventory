package com.applewear.crm.util.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:app-config.properties")
public class FileConstant {

	@Value("${files.base.dir}")
	private String baseDir;

	@Value("${files.ctx.path}")
	private String contextPath;
	
	public static String FILES_BASE_DIR;
	public static String FILES_CONTEXT_PATH;
	
	// All file sub directory can be static final variable.
	// public static final String TEMPLATE_FILES = "/template-files";

	// Spring doesn't support Property Value Injecting for static variable
	@Value("${files.base.dir}")
	public void setBaseDir(String baseDir) {
		FileConstant.FILES_BASE_DIR = baseDir;
	}

	// Spring doesn't support Property Value Injecting for static variable
	@Value("${files.ctx.path}")
	public void setCtxPath(String contextPath) {
		FileConstant.FILES_CONTEXT_PATH = contextPath;
	}
}
