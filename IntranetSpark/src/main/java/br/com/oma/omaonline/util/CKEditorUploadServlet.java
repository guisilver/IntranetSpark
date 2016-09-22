package br.com.oma.omaonline.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This Class is responsible for uploading the images from UI to the DB It will
 * provide as response the callback method and URL for the image that has been
 * uploaded
 *
 * @author mcristea
 *
 */
public class CKEditorUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8107147660893924958L;
	private static final Logger LOG = LoggerFactory.getLogger(CKEditorUploadServlet.class);
	private static final String ERROR_FILE_UPLOAD = "An error occurred to the file upload process.";
	private static final String ERROR_NO_FILE_UPLOAD = "No file is present for upload process.";
	private static final String ERROR_INVALID_CALLBACK = "Invalid callback.";
	private static final String CKEDITOR_CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CKEDITOR_HEADER_NAME = "Cache-Control";
	private static final String CKEDITOR_HEADER_VALUE = "no-cache";

	private static final Pattern PATTERN = Pattern.compile("[\\w\\d]*");

	private String errorMessage = "";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		intra_noticias_mkt_imagens_service uploadedFileService = new intra_noticias_mkt_imagens_service();
		intra_noticias_mkt_imagens uploadedFile = new intra_noticias_mkt_imagens();
		PrintWriter out = response.getWriter();
		response.setContentType(CKEDITOR_CONTENT_TYPE);
		response.setHeader(CKEDITOR_HEADER_NAME, CKEDITOR_HEADER_VALUE);
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items = upload.parseRequest(request);
			if (!items.isEmpty() && items.get(0) != null) {
				uploadedFile.setContent(items.get(0).get());
				uploadedFile.setContentType((items.get(0)).getContentType());
				uploadedFile.setFileName((items.get(0)).getName());
				id = uploadedFileService.saveUploadedFile(uploadedFile);
			} else {
				errorMessage = ERROR_NO_FILE_UPLOAD;
			}

		} catch (FileUploadException e) {
			errorMessage = ERROR_FILE_UPLOAD;
			LOG.error(errorMessage, e);
		}
		// CKEditorFuncNum Is the location to display when the callback
		String callback = request.getParameter("CKEditorFuncNum");
		// verify if the callback contains only digits and letters in order to
		// avoid vulnerability on parsing parameter
		if (!PATTERN.matcher(callback).matches()) {
			callback = "";
			errorMessage = ERROR_INVALID_CALLBACK;
		}
		String pathToFile = request.getContextPath() + "/noticias/ckeditor/getimage?imageId=" + id;
		out.println("<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + callback + ",'"
				+ pathToFile + "','" + errorMessage + "')");
		out.println("</script>");
		out.flush();
		out.close();
	}
}