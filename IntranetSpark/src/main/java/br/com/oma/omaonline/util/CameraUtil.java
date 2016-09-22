package br.com.oma.omaonline.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;

import org.primefaces.event.CaptureEvent;

public class CameraUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5973274618639038003L;
	private String nomeFoto;
	private byte[] foto;

	public String getNomeFoto() {
		return nomeFoto;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String oncapture(CaptureEvent captureEvent) throws IOException {
		this.nomeFoto = getRandomImageName();
		this.foto = ImagemUtil.convertToJpg(captureEvent.getData());
		this.foto = ImagemUtil.compress(this.foto, 0.4f);
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		String arquivo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "temp"
				+ File.separator + this.nomeFoto + ".jpg";
		FileImageOutputStream imageOutput;
		try {
			imageOutput = new FileImageOutputStream(new File(arquivo));
			imageOutput.write(this.foto, 0, this.foto.length);
			imageOutput.close();
		} catch (Exception e) {
			throw new FacesException("Error in writing captured image.");
		}
		return nomeFoto;
	}

	public void deletaFoto() {
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		String arquivo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "temp"
				+ File.separator + this.nomeFoto + ".jpg";
		File file = new File(arquivo);
		if (file.exists()) {
			file.delete();
		}
	}

	public String getRandomImageName() {
		int i = (int) (Math.random() * 1000000000);
		return String.valueOf(i);
	}
}