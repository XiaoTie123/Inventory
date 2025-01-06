package com.applewear.crm.util.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

public class ImagesUtil {

	public static String uploadMultipartImage(MultipartFile clientFile, String subFolder, String prefixFileName)
			throws Exception {
		String fileName = generateFileName(clientFile, prefixFileName);
		File dir = new File(ImageConstant.IMAGE_BASE_DIR + subFolder);
		if (!dir.exists()) {
			dir.mkdir();
		}
		File dest = new File(ImageConstant.IMAGE_BASE_DIR + subFolder, fileName);
		FileUtils.writeByteArrayToFile(dest, clientFile.getBytes());
		return fileName;
	}

	public static byte[] readStream(InputStream inStream) {
		ByteArrayOutputStream bops = new ByteArrayOutputStream();
		int data = -1;
		try {
			while ((data = inStream.read()) != -1) {
				bops.write(data);
			}
			return bops.toByteArray();
		} catch (Exception e) {
			return null;
		}
	}

	public static String writeBase64ToImage(String imageString, String filePath, String fileName) {
		String saveDirectory = ImageConstant.IMAGE_BASE_DIR + filePath;
		try {
			File dest = new File(saveDirectory);
			if (!dest.exists())
				dest.mkdirs();

			if (imageString.indexOf(",") >= 0) {
				imageString = imageString.substring(imageString.indexOf(",") + 1);
			}

			byte[] decoded = Base64.decodeBase64(imageString);

			File fileDest = new File(saveDirectory, fileName);

			FileUtils.writeByteArrayToFile(fileDest, decoded);

			// writeBase64File(imageString, fileName, saveDirectory);
		} catch (IllegalStateException e) {

		} catch (IOException e) {

		}
		return fileName;
	}

	private static String generateFileName(MultipartFile multipartFile, String prefixFileName) {
		String nano_time = String.valueOf(System.nanoTime());
		String fileName = (prefixFileName != null && !prefixFileName.isEmpty()) ? prefixFileName + "_" : "";
		fileName = fileName + nano_time + ".jpg";
		return fileName;
	}

	public static void deleteFile(String basePath, String fileName) throws Exception {
		File file = new File(basePath + File.separator + fileName);
		FileUtils.forceDelete(file);
	}

	public static void deleteImage(String oldFileName) {
		if (CommonUtil.validString(oldFileName)) {
			try {
				File dir = new File(ImageConstant.IMAGE_BASE_DIR + oldFileName);
				dir.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public static String getImageBasePath(HttpServletRequest req) {
		String url = req.getRequestURL().toString();
		String basePath = url.substring(0, url.indexOf(req.getRequestURI()));
		basePath = basePath + ImageConstant.IMAGE_CONTEXT_PATH;
		return basePath;
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
		sb.append(imageName);
		return sb.toString();
	}

	public static boolean validString(String val) {
		return val != null && !val.trim().isEmpty();
	}

}
