package com.applewear.crm.util.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTimeComparator;
import org.springframework.web.multipart.MultipartFile;

import com.applewear.crm.util.enums.LangCode;
import com.google.myanmartools.TransliterateU2Z;
import com.google.myanmartools.ZawgyiDetector;

public class CommonUtil {

	private static final Logger LOG = LogManager.getLogger();

	public static String dateToString(String format, Date date) {
		if (date == null) {
			return "";
		}
		if (format == null || format.trim().isEmpty()) {
			format = CommonConstants.STD_DATE_TIME_FORMAT;
		}
		return new SimpleDateFormat(format).format(date);
	}

	public static LangCode getLangCodeFromHeader(HttpServletRequest httpRequest) {
		if (httpRequest == null) {
			return LangCode.EN;
		}
		String lang = httpRequest.getHeader(CommonConstants.HEADER_LANG);
		if (!validString(lang)) {
			return LangCode.EN;
		}
		return LangCode.get(lang);
	}

	public static Date stringToDate(String format, String dateString) {
		if (dateString == null || dateString.trim().isEmpty()) {
			return null;
		}
		if (format == null || format.trim().isEmpty()) {
			format = CommonConstants.STD_DATE_TIME_FORMAT;
		}
		try {
			return new SimpleDateFormat(format).parse(dateString);
		} catch (Exception e) {
			LOG.error(">>> Exception occurs while converting string into date >>> " + e.getMessage());
		}
		return null;
	}

	public static String changeDateStringFormat(String fromFormat, String toFormat, String dateString) {
		if (!validString(dateString)) {
			return null;
		}
		if (!validString(fromFormat) && !validString(toFormat)) {
			return dateString;
		}
		if (!validString(fromFormat)) {
			fromFormat = CommonConstants.STD_DATE_TIME_FORMAT;
		}
		if (!validString(toFormat)) {
			toFormat = CommonConstants.STD_DATE_TIME_FORMAT;
		}
		return dateToString(toFormat, stringToDate(fromFormat, dateString));
	}

	public static boolean isValidDateString(String format, String dateStr) {
		if (!validString(dateStr)) {
			return false;
		}
		if (!validString(format)) {
			format = CommonConstants.STD_DATE_TIME_FORMAT;
		}
		try {
			new SimpleDateFormat(format.trim()).parse(dateStr.trim());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String getBaseUrl(HttpServletRequest httpRequest) {
		if (httpRequest == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder(httpRequest.getScheme());
		sb.append("://");
		sb.append(httpRequest.getServerName());
		sb.append(":");
		sb.append(httpRequest.getServerPort());
		sb.append(httpRequest.getContextPath());
		return sb.toString();
	}

	public static String prepareImageUrl(HttpServletRequest httpRequest, String subFolder, String imageName) {
		if (httpRequest == null || !validString(imageName)) {
			return "";
		}
		StringBuilder sb = new StringBuilder(httpRequest.getScheme());
		sb.append("://");
		sb.append(httpRequest.getServerName());
		sb.append(":");
		sb.append(httpRequest.getServerPort());
		sb.append(ImageConstant.IMAGE_CONTEXT_PATH);
		if (validString(subFolder)) {
			sb.append(subFolder);
			if (!subFolder.endsWith("/")) {
				sb.append("/");
			}
		}
		sb.append(imageName.trim());
		return sb.toString();
	}

	public static String prepareFileUrl(HttpServletRequest httpRequest, String subFolder, String fileName) {
		if (httpRequest == null || !validString(fileName)) {
			return "";
		}
		StringBuilder sb = new StringBuilder(httpRequest.getScheme());
		sb.append("://");
		sb.append(httpRequest.getServerName());
		sb.append(":");
		sb.append(httpRequest.getServerPort());
		sb.append(FileConstant.FILES_CONTEXT_PATH);
		if (validString(subFolder)) {
			sb.append(subFolder);
			if (!subFolder.endsWith("/")) {
				sb.append("/");
			}
		}
		sb.append(fileName.trim());
		return sb.toString();
	}

	public static boolean validNumber(Number value) {
		return value != null && value.floatValue() > 0;
	}

	public static boolean validLong(Long val) {
		return val != null && val.compareTo(0L) > 0;
	}

	public static boolean validString(String value) {
		return value != null && !value.trim().isEmpty();
	}

	public static boolean validDate(Date value) {
		return value != null;
	}

	public static boolean validFile(MultipartFile file) {
		return file != null && file.getSize() > 0;
	}

	@SuppressWarnings("rawtypes")
	public static boolean validCollection(Collection col) {
		return col != null && !col.isEmpty();
	}

	public static boolean isValidNumberString(String numString) {
		if (!validString(numString)) {
			return false;
		}
		try {
			Double.valueOf(numString);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String hashString(String raw) {
		if (!validString(raw)) {
			return "";
		}
		return DigestUtils.sha256Hex(raw);
	}

	public static Integer parseInt(String stringVal) {
		if (!validString(stringVal)) {
			return null;
		}
		try {
			return Integer.parseInt(stringVal.trim());
		} catch (Exception e) {
			LOG.error("Couldn't parse string into integer >>> Exception occurred >>> stringVal = {}, exception = {}",
					stringVal, ExceptionUtils.getRootCauseStackTrace(e));
			return null;
		}
	}

	public static boolean isValidEmailFormat(String email) {
		if (!validString(email)) {
			return false;
		}
		return Pattern.compile(
				"^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
				.matcher(email).matches();
	}

	public static boolean isSameDay(Date d1, Date d2) {
		DateTimeComparator dateOnlyConparator = DateTimeComparator.getDateOnlyInstance();
		return dateOnlyConparator.compare(d1, d2) == 0;
	}

	public static String generateOtp() {
		String numbers = "0123456789";
		Random rndm_method = new Random();
		StringBuffer otp = new StringBuffer();
		for (int i = 0; i < CommonConstants.OTP_LENGTH; i++) {
			otp.append(numbers.charAt(rndm_method.nextInt(numbers.length())));
		}
		return otp.toString();
	}

	public static LangCode getLangCodeFromLocale(Locale locale) {
		if (locale == null || !validString(locale.toString())) {
			return LangCode.EN;
		}
		return LangCode.get(locale.toString());
	}

	public static String formatPrice(Integer price) {
		if (!validNumber(price)) {
			return "";
		}
		return String.format("%,d", price) + " " + CommonConstants.CURRENCY_CODE;
	}

	public static String formatPriceWithoutCurrency(Integer price) {
		if (!validNumber(price)) {
			return "";
		}
		return String.format("%,d", price);
	}

	public static String formatPriceWithoutCurrencyAndCheckInvalid(Integer price) {
		return String.format("%,d", price);
	}

	public static boolean isSubscriptionExpired(String subscriptionEndTime, String format) {
		if (!CommonUtil.validString(subscriptionEndTime)) {
			return true;
		}
		if (!CommonUtil.validString(format)) {
			format = CommonConstants.STD_DATE_TIME_FORMAT;
		}
		Date endTime = CommonUtil.stringToDate(format, subscriptionEndTime);
		if (endTime == null) {
			return true;
		}
		return endTime.before(new Date());
	}

	public static String prepareErrorList(List<FieldError> errorList) {
		StringBuilder builder = new StringBuilder("");

		if (CommonUtil.validCollection(errorList)) {

			for (FieldError error : errorList) {
				builder.append(error.getMessage() + "\n");
			}

		}

		return builder.toString();

	}

	public static boolean validInteger(Integer val) {
		return val != null && val.compareTo(0) > 0;
	}

	public static boolean isValidNonNegativeInteger(Integer val) {
		return val != null && val.compareTo(0) >= 0;
	}

	@SuppressWarnings("rawtypes")
	public static boolean validList(List value) {
		return value != null && !value.isEmpty();
	}

	public static String dateStringToDbDate(String dateString) {
		String dbDate[] = dateString.split("/");
		// change year-month-day
		String databaseDate = dbDate[2] + "-" + dbDate[1] + "-" + dbDate[0];
		return databaseDate;
	}

	public static boolean validBigDecimal(BigDecimal val) {
		return val != null && val.setScale(0, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO) > 0;
	}

	public static String formatNumber(BigDecimal val) {
		if (!validBigDecimal(val)) {
			return "0";
		}
		return new DecimalFormat("#,##0").format(formatCurrencyScale(val));
	}

	public static BigDecimal formatCurrencyScale(BigDecimal amount) {
		if (!validBigDecimal(amount)) {
			return BigDecimal.ZERO;
		}
		return amount.setScale(0, RoundingMode.HALF_UP);
	}

	public static String generateCustomerLoginId() {
		String chars = generateRandomChars(3);
		String number = generateRandomNumber(3, 4);
		return chars + number;
	}

	private static String generateRandomChars(int length) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append((char) ('a' + random.nextInt(26)));
		}
		return sb.toString();
	}

	private static String generateRandomNumber(int minLength, int maxLength) {
		Random random = new Random();
		int length = minLength + random.nextInt(maxLength - minLength + 1);
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}

	public static String generateRandomString(int length) {

		String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		int API_KEY_LENGTH = 40;
		Random RANDOM = new SecureRandom();

		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
		}
		return sb.toString();
	}

	public static boolean isUnicode(String string) {
		final ZawgyiDetector detector = new ZawgyiDetector();
		DecimalFormat df2 = new DecimalFormat("#.#");
		double score = detector.getZawgyiProbability(string);
		df2.setRoundingMode(RoundingMode.UP);
		String uniorzawgyi = String.valueOf(df2.format(score));
		if (uniorzawgyi.equals("1")) {
			return false;
		} else {
			return true;
		}
	}

	public static String UnicodeToZawgyi(String value) {
		if (value != null) {

			if (isUnicode(value)) {
				final TransliterateU2Z u2Z = new TransliterateU2Z("Unicode to Zawgyi");
				String result = u2Z.convert(value);
				return result;
			} else {
				return value;
			}

		}
		return "";
	}

}
