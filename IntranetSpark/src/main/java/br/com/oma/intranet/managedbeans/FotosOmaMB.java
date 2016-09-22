package br.com.oma.intranet.managedbeans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.oma.intranet.dao.FotosOmaDAO;
import br.com.oma.intranet.entidades.intra_albuns;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@ViewScoped
@ManagedBean
public class FotosOmaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8643804607478346755L;
	private UploadedFile file;
	private intra_albuns album = new intra_albuns(), albumSelecionado;
	private List<intra_albuns> albuns;
	private List<String> fotos;
	private String enderecoPasta;
	private String enderecoServidorImagens;
	private String fotoSelecionada;

	// LOGIN PARA FTP
	private static String LINK_FTP = "10.1.1.20";
	private static String USUARIO_FTP = "root";
	private static String SENHA_FTP = "@M9503*";

	private Channel channel = null;

	private String novoNome;

	public FotosOmaMB() {
		this.enderecoServidorImagens = "http://10.1.1.20:8080/arquivos/intranet/fotos";
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public intra_albuns getAlbumSelecionado() {
		if (this.albumSelecionado == null) {
			try {
				FacesContext ctx = FacesContext.getCurrentInstance();
				Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
				String codigoAlbum = params.get("codigoAlbum");
				if (codigoAlbum != null) {
					FotosOmaDAO dao = new FotosOmaDAO();
					this.albumSelecionado = dao.pesquisaAlbumPorCodigo(Integer.parseInt(codigoAlbum));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return albumSelecionado;
	}

	public void setAlbumSelecionado(intra_albuns albumSelecionado) {
		this.albumSelecionado = albumSelecionado;
	}

	public intra_albuns getAlbum() {
		return album;
	}

	public void setAlbum(intra_albuns album) {
		this.album = album;
	}

	@SuppressWarnings("unchecked")
	public List<intra_albuns> getAlbuns() {
		try {
			if (this.albuns == null) {
				this.albuns = new ArrayList<>();
				FotosOmaDAO dao = new FotosOmaDAO();
				JSch jsch = new JSch();
				Session session = null;
				session = jsch.getSession(USUARIO_FTP, LINK_FTP, 22);
				session.setConfig("StrictHostKeyChecking", "no");
				session.setPassword(SENHA_FTP);
				session.connect();
				Channel channel = session.openChannel("sftp");
				channel.connect();
				ChannelSftp sftpChannel = (ChannelSftp) channel;
				Vector<ChannelSftp.LsEntry> albuns = sftpChannel
						.ls("//var/lib//tomcat7//webapps//arquivos//intranet//fotos//");
				for (ChannelSftp.LsEntry entry : albuns) {
					List<intra_albuns> listaRetorno = dao.pesquisaAlbumPorNome(entry.getFilename());
					if (listaRetorno != null && !listaRetorno.isEmpty()) {
						this.albuns.add(listaRetorno.get(0));
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
		return albuns;
	}

	public void setAlbuns(List<intra_albuns> albuns) {
		this.albuns = albuns;
	}

	@SuppressWarnings("unchecked")
	public List<String> getFotos() {
		try {
			if (this.fotos == null) {
				this.fotos = new ArrayList<>();
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
						.ls("//var/lib//tomcat7//webapps//arquivos//intranet//fotos//" + this.albumSelecionado.getNome()
								+ "//");
				for (ChannelSftp.LsEntry entry : fotos) {
					String verifica = entry.getFilename().replace(".", "");
					if (verifica.trim() != null && !verifica.trim().isEmpty()) {
						this.fotos.add(entry.getFilename());
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
		return fotos;
	}

	public void setFotos(List<String> fotos) {
		this.fotos = fotos;
	}

	public String getEnderecoPasta() {
		return enderecoPasta;
	}

	public void setEnderecoPasta(String enderecoPasta) {
		this.enderecoPasta = enderecoPasta;
	}

	public String getEnderecoServidorImagens() {
		return enderecoServidorImagens;
	}

	public void setEnderecoServidorImagens(String enderecoServidorImagens) {
		this.enderecoServidorImagens = enderecoServidorImagens;
	}

	public String getFotoSelecionada() {
		return fotoSelecionada;
	}

	public void setFotoSelecionada(String fotoSelecionada) {
		this.fotoSelecionada = fotoSelecionada;
	}

	public String getNovoNome() {
		return novoNome;
	}

	public void setNovoNome(String novoNome) {
		this.novoNome = novoNome;
	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Enviado com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		try {
			this.enviarImagem(event.getFile().getInputstream(), event.getFile().getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void enviarImagem(InputStream imagem, String nomeImagem) throws IOException {
		try {
			uploadFotoFtp(imagem, this.albumSelecionado.getNome(), nomeImagem);
			this.fotos.add(nomeImagem);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FacesException(e.getMessage());
		}
	}

	public void salvarNovoAlbum() {
		try {
			criaPastaFtp(this.album.getNome());
			FotosOmaDAO dao = new FotosOmaDAO();
			this.album.setDataCriacao(new Date());
			this.album.setNome(this.album.getNome().toLowerCase());
			dao.salvarNovoAlbum(this.album);
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(caminho + "/fotos-oma/editar-album.xhtml?codigoAlbum=" + this.album.getCodigo());
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Ocorreu um erro! Entre em contato com o administrador!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	public void abrirAlbum() {
		try {
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(
					caminho + "/fotos-oma/editar-album.xhtml?codigoAlbum=" + this.albumSelecionado.getCodigo());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void toggleSrc(ActionEvent evt) {
		try {
			// simulate a long running request
			Thread.sleep(1500);
		} catch (Exception e) {
			// ignore
		}
	}

	public void conectaFtp() {
		try {
			JSch jsch = new JSch();
			Session session = null;
			session = jsch.getSession(USUARIO_FTP, LINK_FTP, 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(SENHA_FTP);
			session.connect();
			this.channel = session.openChannel("sftp");
			this.channel.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public String getPrimeiraFotoAlbum(String nomeAlbum) {
		String retorno = null;
		try {
			this.fotos = new ArrayList<>();
			ChannelSftp sftpChannel = (ChannelSftp) this.channel;
			Vector<ChannelSftp.LsEntry> fotos = sftpChannel
					.ls("//var/lib//tomcat7//webapps//arquivos//intranet//fotos//" + nomeAlbum + "//");
			for (ChannelSftp.LsEntry entry : fotos) {
				String verifica = entry.getFilename().replace(".", "");
				if (verifica.trim() != null && !verifica.trim().isEmpty()) {
					this.fotos.add(entry.getFilename());
				}
			}
			if (this.fotos != null && !this.fotos.isEmpty()) {
				retorno = this.fotos.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public void excluirAlbum() {
		try {
			if (this.albumSelecionado != null) {
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
					sftpChannel.rmdir("//var/lib//tomcat7//webapps//arquivos//intranet//fotos//"
							+ this.albumSelecionado.getNome());
				} catch (Exception e) {
					throw new Exception("Já existe um álbum com este nome!");
				}
				sftpChannel.exit();
				session.disconnect();
				FotosOmaDAO dao = new FotosOmaDAO();
				dao.excluirAlbum(this.albumSelecionado);
				String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
				FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "/fotos-oma.xhtml");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void criaPastaFtp(String nomePasta) throws Exception, JSchException, SftpException {
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
			sftpChannel.mkdir("//var/lib//tomcat7//webapps//arquivos//intranet//fotos//" + nomePasta.toLowerCase());
		} catch (Exception e) {
			throw new Exception("Já existe um álbum com este nome!");
		}
		sftpChannel.exit();
		session.disconnect();
	}

	public static void uploadFotoFtp(InputStream arquivo, String nomeAlbum, String nomeArquivo) {
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
					"//var/lib//tomcat7//webapps//arquivos//intranet//fotos//" + nomeAlbum + "//" + nomeArquivo);
			sftpChannel.exit();
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		}
	}

	public static void excluirPastaFtp(String nomeArquivo) {
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
			sftpChannel.rm("//var/lib//tomcat7//webapps//arquivos//intranet//fotos//" + nomeArquivo);
			sftpChannel.exit();
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		}
	}

	public static void excluirFotoFtp(String nomeArquivo) {
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
			sftpChannel.rm("//var/lib//tomcat7//webapps//arquivos//intranet//fotos//" + nomeArquivo);
			sftpChannel.exit();
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		}
	}

	public void alterarNomeAlbum() {
		try {
			if (this.novoNome.contains("/") || this.novoNome.contains("\"")) {
				throw new Exception("O nome do álbum não aceita caracteres de barra!");
			}

			JSch jsch = new JSch();
			Session session = null;

			session = jsch.getSession(USUARIO_FTP, LINK_FTP, 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(SENHA_FTP);
			session.connect();
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp sftpChannel = (ChannelSftp) channel;
			sftpChannel.rename(
					"//var/lib//tomcat7//webapps//arquivos//intranet//fotos//" + this.albumSelecionado.getNome(),
					"//var/lib//tomcat7//webapps//arquivos//intranet//fotos//" + this.novoNome);
			sftpChannel.exit();
			session.disconnect();
			FotosOmaDAO dao = new FotosOmaDAO();
			this.albumSelecionado.setNome(this.novoNome);
			dao.alterarNomeAlbum(this.albumSelecionado);
			this.novoNome = "";
			RequestContext.getCurrentInstance().execute("PF('dlgAltrNomeAlbum').hide();");
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(
					caminho + "/fotos-oma/editar-album.xhtml?codigoAlbum=" + this.albumSelecionado.getCodigo());
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	public void selecionaFotoAnterior() {
		int i = this.fotos.indexOf(this.fotoSelecionada);
		if ((i - 1) >= 0) {
			this.fotoSelecionada = this.fotos.get(i - 1);
		}
	}

	public void selecionaFotoProxima() {
		int i = this.fotos.indexOf(this.fotoSelecionada);
		if ((i + 1) <= (this.fotos.size() - 1)) {
			this.fotoSelecionada = this.fotos.get(i + 1);
		}
	}

	public void excluirFoto() {
		try {
			if (this.albumSelecionado != null) {
				int i = this.fotos.indexOf(this.fotoSelecionada);
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
					sftpChannel.rm("//var/lib//tomcat7//webapps//arquivos//intranet//fotos//"
							+ this.albumSelecionado.getNome() + "//" + this.fotoSelecionada);
				} catch (Exception e) {
					throw new Exception("Já existe um álbum com este nome!");
				}
				sftpChannel.exit();
				session.disconnect();
				this.fotos = null;
				this.getFotos();
				if (this.fotos != null && this.fotos.size() > 0) {
					if (i == 0) {
						this.fotoSelecionada = this.fotos.get(0);
					} else if (i > 0) {
						this.fotoSelecionada = this.fotos.get(i - 1);
					}
				} else {
					RequestContext.getCurrentInstance().execute("$('.fechar').click();");
					RequestContext.getCurrentInstance().update("frmFotos");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}