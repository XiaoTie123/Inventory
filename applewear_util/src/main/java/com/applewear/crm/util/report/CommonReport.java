package com.applewear.crm.util.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

public class CommonReport {
	@SuppressWarnings("rawtypes")
	public static void generateReportExcel(List dataList, String reportPath, HashMap<String, Object> parameter,
			ByteArrayOutputStream out) {
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(dataList);
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new File(reportPath));
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, datasource);
			JRXlsExporter exporterXLS = new JRXlsExporter();
			exporterXLS.setExporterInput(new SimpleExporterInput(print));
			exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
			SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
			configuration.setOnePagePerSheet(false);
			configuration.setDetectCellType(true);
			configuration.setCollapseRowSpan(false);
			configuration.setRemoveEmptySpaceBetweenColumns(false);
			configuration.setRemoveEmptySpaceBetweenRows(false);
			configuration.setWhitePageBackground(false);
			configuration.setWrapText(true);
			configuration.setShowGridLines(false);
			configuration.setIgnoreCellBorder(false);
			// configuration.setAutoFitPageHeight(true);
			exporterXLS.setConfiguration(configuration);
			exporterXLS.exportReport();
			exporterXLS.reset();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static void generateReportExcel2(List<?> dataList, ByteArrayOutputStream baos, String reportPath,
			String fileName, HashMap<String, Object> parameter, String sheetName) {
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(dataList);
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new File(reportPath));
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, datasource);
			JRXlsExporter exporterXLS = new JRXlsExporter();

			exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);

			exporterXLS.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.FALSE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.FALSE);

			exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, fileName);
			exporterXLS.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
			exporterXLS.setParameter(JRXlsAbstractExporterParameter.SHEET_NAMES, new String[] { sheetName });

			exporterXLS.exportReport();

			exporterXLS.reset();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public static void generateReportMultiSheetExcel(List dataList, JasperReport jasperReport,
			HashMap<String, Object> parameter, ByteArrayOutputStream baos) {
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(dataList);
		try {
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, datasource);
			JRXlsExporter exporterXLS = new JRXlsExporter();
			exporterXLS.setExporterInput(new SimpleExporterInput(print));
			exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
			SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
			configuration.setOnePagePerSheet(true);
			configuration.setDetectCellType(true);
			configuration.setCollapseRowSpan(false);
			configuration.setRemoveEmptySpaceBetweenColumns(false);
			configuration.setRemoveEmptySpaceBetweenRows(true);
			configuration.setWhitePageBackground(false);
			configuration.setWrapText(true);
			configuration.setShowGridLines(false);
			configuration.setIgnoreCellBorder(false);
			configuration.setAutoFitPageHeight(true);
			exporterXLS.setConfiguration(configuration);
			exporterXLS.exportReport();
			exporterXLS.reset();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public static void generateReportPdf(List dataList, String reportPath, HashMap<String, Object> parameter,
			ByteArrayOutputStream baos, String fileName) {

		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(dataList);
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new File(reportPath));
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, datasource);
			JRPdfExporter pdfExporter = new JRPdfExporter();
			pdfExporter.setExporterInput(new SimpleExporterInput(print));
			pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
			pdfExporter.exportReport();

		} catch (JRException e) {
			ExceptionUtils.getStackTrace(e);
		}

	}

}
