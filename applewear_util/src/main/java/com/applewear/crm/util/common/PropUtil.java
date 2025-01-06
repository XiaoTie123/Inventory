package com.applewear.crm.util.common;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.applewear.crm.util.enums.LangCode;

public class PropUtil {

	private static final Logger LOG = LogManager.getLogger();

	private static Properties prop;

	public static String readMessageValue(String key, LangCode lang) {
		if (!CommonUtil.validString(key)) {
			LOG.debug("Couldn't read message properties file value >>> Invalid key >>> key = " + key);
			return "";
		}
		if (lang == null) {
			lang = LangCode.EN;
		}
		String fileName = "";
		if (LangCode.isMM(lang.getCode())) {
			fileName = "messages_mm.properties";
		} else {
			fileName = "messages.properties";
		}
		try (InputStream inputStream = PropUtil.class.getClassLoader().getResourceAsStream(fileName)) {
			prop = new Properties();
			if (inputStream == null) {
				LOG.debug("Couldn't read message properties file value >>> Inputstream is null >>> fileName = "
						+ fileName);
				return "";
			} else {
				prop.load(inputStream);
				return prop.getProperty(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Couldn't read message properties file value >>> Exception occurred >>> key = " + key
					+ ", fileName = " + fileName + ", exception = " + ExceptionUtils.getStackTrace(e));
			return "";
		}
	}

}
