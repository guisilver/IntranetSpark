package br.com.oma.omaonline.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletContext;

public class ImagemUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8333749173449984817L;

	public static byte[] compress(byte[] imagem, float quality)
			throws IOException {
		BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(
				imagem));
		// Get a ImageWriter for jpeg format.
		Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix("jpeg");
		if (!writers.hasNext())
			throw new IllegalStateException("No writers found");
		ImageWriter writer = (ImageWriter) writers.next();
		// Create the ImageWriteParam to compress the image.
		ImageWriteParam param = writer.getDefaultWriteParam();
		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		param.setCompressionQuality(quality);
		// The output will be a ByteArrayOutputStream (in memory)
		ByteArrayOutputStream bos = new ByteArrayOutputStream(32768);
		ImageOutputStream ios = ImageIO.createImageOutputStream(bos);
		writer.setOutput(ios);
		writer.write(null, new IIOImage(bufferedImage, null, null), param);
		ios.flush();
		byte[] imagemFinal = bos.toByteArray();
		bos.close();
		return imagemFinal;
	}

	public static byte[] convertToJpg(byte[] imagem) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(
				imagem));
		BufferedImage newBufferedImage = new BufferedImage(
				bufferedImage.getWidth(), bufferedImage.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0,
				Color.WHITE, null);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(newBufferedImage, "jpg", baos);
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		return imageInByte;
	}

	public static String exportImagemTemp(byte[] imagem) throws IOException {
		String nomeImagem = getRandomImageName();
		imagem = convertToJpg(imagem);
		imagem = compress(imagem, 0.4f);
		ServletContext servletContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		String arquivo = servletContext.getRealPath("") + File.separator
				+ "resources" + File.separator + "temp" + File.separator
				+ nomeImagem + ".jpg";
		FileImageOutputStream imageOutput;
		try {
			imageOutput = new FileImageOutputStream(new File(arquivo));
			imageOutput.write(imagem, 0, imagem.length);
			imageOutput.close();
		} catch (Exception e) {
			throw new FacesException("Error in writing captured image.");
		}
		return nomeImagem;
	}

	public static void excluiImagemTemp(String nomeImagem) throws IOException {
		ServletContext servletContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		String arquivo = servletContext.getRealPath("") + File.separator
				+ "resources" + File.separator + "temp" + File.separator
				+ nomeImagem + ".jpg";
		File file = new File(arquivo);
		if (file.exists()) {
			file.delete();
		}
	}

	public static String getRandomImageName() {
		int i = (int) (Math.random() * 1000000000);
		return String.valueOf(i);
	}
}
