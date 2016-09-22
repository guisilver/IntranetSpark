package br.com.oma.intranet.managedbeans;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import br.com.oma.intranet.entidades.intra_albuns;

@ViewScoped
@ManagedBean
public class DocumentosOmaMB {

	private UploadedFile file;
	private intra_albuns album = new intra_albuns(), albumSelecionado;
	private List<intra_albuns> albuns;

	private List<String> arquivos;
	private String enderecoPasta;
	private String enderecoServidorArquivos;

	// LOGIN PARA FTP
	private static String LINK_FTP = "10.1.1.20";
	private static String USUARIO_FTP = "root";
	private static String SENHA_FTP = "@M9503*";

	private Channel channel = null;
	private String novoNome;

	@PostConstruct
	public void init() {
		this.enderecoServidorArquivos = "http://10.1.1.20:8080/arquivos/intranet/documentos/";
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public intra_albuns getAlbum() {
		return album;
	}

	public void setAlbum(intra_albuns album) {
		this.album = album;
	}

	public intra_albuns getAlbumSelecionado() {
		return albumSelecionado;
	}

	public void setAlbumSelecionado(intra_albuns albumSelecionado) {
		this.albumSelecionado = albumSelecionado;
	}

	public List<intra_albuns> getAlbuns() {
		return albuns;
	}

	public void setAlbuns(List<intra_albuns> albuns) {
		this.albuns = albuns;
	}

	@SuppressWarnings("unchecked")
	public List<String> getArquivos() {
		try {
			if (this.arquivos == null) {
				this.arquivos = new ArrayList<>();
				JSch jsch = new JSch();
				Session session = null;
				session = jsch.getSession(USUARIO_FTP, LINK_FTP, 22);
				session.setConfig("StrictHostKeyChecking", "no");
				session.setPassword(SENHA_FTP);
				session.connect();
				Channel channel = session.openChannel("sftp");
				channel.connect();
				ChannelSftp sftpChannel = (ChannelSftp) channel;
				Vector<ChannelSftp.LsEntry> fotos = sftpChannel
						.ls("//var/lib//tomcat7//webapps//arquivos//intranet//documentos//");
				for (ChannelSftp.LsEntry entry : fotos) {
					String verifica = entry.getFilename().replace(".", "");
					if (verifica.trim() != null && !verifica.trim().isEmpty()) {
						this.arquivos.add(entry.getFilename());
					}
				}
			}
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arquivos;
	}

	public void setArquivos(List<String> arquivos) {
		this.arquivos = arquivos;
	}

	public String getEnderecoPasta() {
		return enderecoPasta;
	}

	public void setEnderecoPasta(String enderecoPasta) {
		this.enderecoPasta = enderecoPasta;
	}

	public String getEnderecoServidorArquivos() {
		return enderecoServidorArquivos;
	}

	public void setEnderecoServidorArquivos(String enderecoServidorArquivos) {
		this.enderecoServidorArquivos = enderecoServidorArquivos;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getNovoNome() {
		return novoNome;
	}

	public void setNovoNome(String novoNome) {
		this.novoNome = novoNome;
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			this.enviarArquivo(event.getFile().getInputstream(), event.getFile().getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void enviarArquivo(InputStream arquivo, String nomeArquivo) throws IOException {
		try {
			uploadArquivoFtp(arquivo, nomeArquivo);
			if (!this.arquivos.contains(nomeArquivo)) {
				this.arquivos.add(nomeArquivo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new FacesException(e.getMessage());
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
			sftpChannel.put(arquivo, "//var/lib//tomcat7//webapps//arquivos//intranet//documentos//" + nomeArquivo);
			sftpChannel.exit();
			session.disconnect();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Enviado com sucesso!", ""));
		} catch (JSchException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erro ao enviar! " + e.getMessage(), ""));
		} catch (SftpException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erro ao enviar! " + e.getMessage(), ""));
		}
	}

	public void excluirArquivo(String arquivo) {
		try {
			JSch jsch = new JSch();
			Session session = null;
			session = jsch.getSession(USUARIO_FTP, LINK_FTP, 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(SENHA_FTP);
			session.connect();
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp sftpChannel = (ChannelSftp) channel;
			try {
				sftpChannel.rm("//var/lib//tomcat7//webapps//arquivos//intranet//documentos//" + arquivo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			sftpChannel.exit();
			session.disconnect();
			this.arquivos = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluído com sucesso!", ""));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erro ao excluir! " + e.getMessage(), ""));
		}
	}

	public String redirecionaParaArquivo(String arquivo) {
		return this.enderecoServidorArquivos + "/" + arquivo;
	}

}
