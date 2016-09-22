package br.com.oma.intranet.util;

import java.io.ByteArrayOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PRAcroForm;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;

public class MergePdfByteArrays {

	private ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	private Document document = null;
	private PdfCopy writer = null;

	public MergePdfByteArrays() {
		super();
	}

	public byte[] getMergedPdfByteArray() {
		if (this.outStream != null) {
			return this.getOutStream().toByteArray();
		} else {
			return null;
		}
	}

	public void add(byte[] pdfByteArray) {
		try {
			PdfReader reader = new PdfReader(pdfByteArray);
			int numberOfPages = reader.getNumberOfPages();

			if (this.document == null) {
				this.document = new Document(reader.getPageSizeWithRotation(1));
				this.writer = new PdfCopy(this.document, outStream); // new
				this.document.open();
			}

			PdfImportedPage page;
			for (int i = 0; i < numberOfPages;) {
				++i;
				page = this.writer.getImportedPage(reader, i);
				this.writer.addPage(page);
			}

			PRAcroForm form = reader.getAcroForm();
			if (form != null) {
				this.writer.copyAcroForm(reader);
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			this.document.close();
			this.outStream.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ByteArrayOutputStream getOutStream() {
		return outStream;
	}

	public void setOutStream(ByteArrayOutputStream outStream) {
		this.outStream = outStream;
	}

}
