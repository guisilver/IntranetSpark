package br.com.oma.omaonline.util;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This Servlet is responsible for delivering images from DB to UI It will
 * provide as response the image that has been requested by id as parameter
 *
 * @author mcristea
 *
 */
public class CKEditorGetImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997130696073831788L;
	private static final Logger LOG = LoggerFactory.getLogger(CKEditorGetImageServlet.class);
	private static final String ERROR_FILE_DOWNLOAD = "An error occured when trying to get the image with id:";
	private static final String IMAGE_PARAMETER_NAME = "imageId";
	private static final long CACHE_AGE_MILISECONDS_TWO_WEEKS = 1209600000;
	private static final String CKEDITOR_CONTENT_EXPIRE = "Expires";
	private static final String CKEDITOR_CONTENT_TYPE = "Content-Type";
	private static final String CKEDITOR_CONTENT_LENGTH = "Content-Length";
	private static final String CKEDITOR_CONTENT_DISPOSITION = "Content-Disposition";
	private static final String CKEDITOR_CONTENT_DISPOSITION_VALUE = "inline; filename=\"";
	private static final String CKEDITOR_HEADER_NAME = "Cache-Control";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer imageId = 0;
		String parameterId = request.getParameter(IMAGE_PARAMETER_NAME);
		intra_noticias_mkt_imagens_service uploadedFileService = new intra_noticias_mkt_imagens_service();
		try {
			imageId = Integer.valueOf(parameterId);
			intra_noticias_mkt_imagens uploadedFile = uploadedFileService.getUploadedFileById(imageId);
			if (uploadedFile != null && uploadedFile.getContent().length > 0) {
				byte[] rb = uploadedFile.getContent();
				long expiry = new Date().getTime() + CACHE_AGE_MILISECONDS_TWO_WEEKS;
				response.setDateHeader(CKEDITOR_CONTENT_EXPIRE, expiry);
				response.setHeader(CKEDITOR_HEADER_NAME, "max-age=" + CACHE_AGE_MILISECONDS_TWO_WEEKS);
				response.setHeader(CKEDITOR_CONTENT_TYPE, uploadedFile.getContentType());
				response.setHeader(CKEDITOR_CONTENT_LENGTH, String.valueOf(rb.length));
				response.setHeader(CKEDITOR_CONTENT_DISPOSITION,
						CKEDITOR_CONTENT_DISPOSITION_VALUE + uploadedFile.getFileName() + "\"");
				response.getOutputStream().write(rb, 0, rb.length);
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		} catch (Exception e) {
			response.getOutputStream().close();
			LOG.error(ERROR_FILE_DOWNLOAD + parameterId, e);
		}
	}
}