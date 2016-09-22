package br.com.oma.intranet.managedbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import br.com.oma.intranet.dao.VideosOmaDAO;
import br.com.oma.intranet.entidades.intra_videos;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@ViewScoped
@ManagedBean
public class VideosOmaMB {

	private intra_videos video = new intra_videos(), videoSelecionado;
	private List<intra_videos> videos;
	private String enderecoPasta;

	// LINK FTP
	private static String LINK_FTP = "10.1.1.20";
	private static String USUARIO_FTP = "root";
	private static String SENHA_FTP = "@M9503*";

	public VideosOmaMB() {
		this.enderecoPasta = "http://10.1.1.20:8080/arquivos/intranet/videos";
	}

	public intra_videos getVideo() {
		return video;
	}

	public void setVideo(intra_videos video) {
		this.video = video;
	}

	public intra_videos getVideoSelecionado() {
		return videoSelecionado;
	}

	public void setVideoSelecionado(intra_videos videoSelecionado) {
		this.videoSelecionado = videoSelecionado;
	}

	public List<intra_videos> getVideos() {
		if (this.videos == null) {
			VideosOmaDAO dao = new VideosOmaDAO();
			this.videos = dao.getVideos();
			if (this.videos != null && !this.videos.isEmpty()) {
				this.videoSelecionado = this.videos.get(this.videos.size() - 1);
			}
		}
		return videos;
	}

	public void setVideos(List<intra_videos> videos) {
		this.videos = videos;
	}

	public String getEnderecoPasta() {
		return enderecoPasta;
	}

	public void setEnderecoPasta(String enderecoPasta) {
		this.enderecoPasta = enderecoPasta;
	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Enviado com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		try {
			this.enviarVideo(event.getFile().getInputstream(), event.getFile()
					.getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void enviarVideo(InputStream video, String nome) throws IOException {
		try {
			if (!new File(this.enderecoPasta + File.separator + nome).exists()) {
				this.video.setNome(nome);
				uploadArquivoFtp(video, nome);
				this.salvarNovoVideo();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new FacesException(e.getMessage());
		}
	}

	public void salvarNovoVideo() {
		try {
			VideosOmaDAO dao = new VideosOmaDAO();
			this.video.setDataCriacao(new Date());
			dao.salvarNovoVideo(this.video);
			this.video = new intra_videos();
			this.videos = null;
			RequestContext.getCurrentInstance().reset("frmVideos");
			RequestContext.getCurrentInstance().execute(
					"PF('dlgNovoVideo').hide();");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluirVideo() {
		try {
			VideosOmaDAO dao = new VideosOmaDAO();
			dao.excluirVideo(this.videoSelecionado);
			RequestContext.getCurrentInstance().execute(
					"PF('dlgExcVideo').hide();");
			this.videos = null;
			this.videoSelecionado = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void uploadArquivoFtp(InputStream arquivo, String nomeArquivo) {
		JSch jsch = new JSch();
		Session session = null;
		try {
			session = jsch.getSession(USUARIO_FTP, LINK_FTP, 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(SENHA_FTP);
			session.connect();
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp sftpChannel = (ChannelSftp) channel;
			sftpChannel.put(arquivo,
					"//var/lib//tomcat7//webapps//arquivos//intranet//videos//"
							+ nomeArquivo);
			sftpChannel.exit();
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		}
	}

	public static void excluirArquivoFtp(String nomeArquivo) {
		JSch jsch = new JSch();
		Session session = null;
		try {
			session = jsch.getSession(USUARIO_FTP, LINK_FTP, 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(SENHA_FTP);
			session.connect();
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp sftpChannel = (ChannelSftp) channel;
			sftpChannel
					.rm("//var/lib//tomcat7//webapps//arquivos//intranet//videos//"
							+ nomeArquivo);
			sftpChannel.exit();
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		}
	}

}
