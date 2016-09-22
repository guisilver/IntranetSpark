/*package br.com.oma.intranet.managedbeans;

import java.io.IOException;
import java.io.InputStream;
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
public class MktUploadMB {

	private UploadedFile file;
	private intra_albuns pasta = new intra_albuns(), pastaSelecionada;
	private List<intra_albuns> pastas;
	private List<String> arquivos;
	private String enderecoPasta;
	private String enderecoServidorImagens;
	private String fotoSelecionada;

	// LOGIN PARA FTP
	private static String LINK_FTP = "10.1.1.20";
	private static String USUARIO_FTP = "root";
	private static String SENHA_FTP = "@M9503*";

	private Channel channel = null;

	private String novoNome;

	public MktUploadMB() {
		this.enderecoServidorImagens = "http://10.1.1.20:8080/arquivos/intranet/fotos";
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public intra_albuns getpastaSelecionada() {
		if (this.pastaSelecionada == null) {
			try {
				FacesContext ctx = FacesContext.getCurrentInstance();
				Map<String, String> params = ctx.getExternalContext()
						.getRequestParameterMap();
				String codigopasta = params.get("codigopasta");
				if (codigopasta != null) {
					FotosOmaDAO dao = new FotosOmaDAO();
					this.pastaSelecionada = dao.pesquisapastaPorCodigo(Integer
							.parseInt(codigopasta));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pastaSelecionada;
	}

	public void setpastaSelecionada(intra_albuns pastaSelecionada) {
		this.pastaSelecionada = pastaSelecionada;
	}

	public intra_albuns getpasta() {
		return pasta;
	}

	public void setpasta(intra_albuns pasta) {
		this.pasta = pasta;
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
					List<intra_albuns> listaRetorno = dao
							.pesquisapastaPorNome(entry.getFilename());
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
						.ls("//var/lib//tomcat7//webapps//arquivos//intranet//fotos//"
								+ this.pastaSelecionada.getNome() + "//");
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
			this.enviarImagem(event.getFile().getInputstream(), event.getFile()
					.getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void enviarImagem(InputStream imagem, String nomeImagem)
			throws IOException {
		try {
			uploadFotoFtp(imagem, this.pastaSelecionada.getNome(), nomeImagem);
			this.fotos.add(nomeImagem);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FacesException(e.getMessage());
		}
	}

	public void salvarNovopasta() {
		try {
			criaPastaFtp(this.pasta.getNome());
			FotosOmaDAO dao = new FotosOmaDAO();
			this.pasta.setDataCriacao(new Date());
			this.pasta.setNome(this.pasta.getNome().toLowerCase());
			dao.salvarNovopasta(this.pasta);
			String caminho = FacesContext.getCurrentInstance()
					.getExternalContext().getApplicationContextPath();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							caminho
									+ "/fotos-oma/editar-pasta.xhtml?codigopasta="
									+ this.pasta.getCodigo());
		} catch (IOException e) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_WARN,
									"Ocorreu um erro! Entre em contato com o administrador!",
									""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							e.getMessage(), ""));
		}
	}

	public void abrirpasta() {
		try {
			String caminho = FacesContext.getCurrentInstance()
					.getExternalContext().getApplicationContextPath();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							caminho
									+ "/fotos-oma/editar-pasta.xhtml?codigopasta="
									+ this.pastaSelecionada.getCodigo());
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
	public String getPrimeiraFotopasta(String nomepasta) {
		String retorno = null;
		try {
			this.fotos = new ArrayList<>();
			ChannelSftp sftpChannel = (ChannelSftp) this.channel;
			Vector<ChannelSftp.LsEntry> fotos = sftpChannel
					.ls("//var/lib//tomcat7//webapps//arquivos//intranet//fotos//"
							+ nomepasta + "//");
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

	public void excluirpasta() {
		try {
			if (this.pastaSelecionada != null) {
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
					sftpChannel
							.rmdir("//var/lib//tomcat7//webapps//arquivos//intranet//fotos//"
									+ this.pastaSelecionada.getNome());
				} catch (Exception e) {
					throw new Exception("Já existe um álbum com este nome!");
				}
				sftpChannel.exit();
				session.disconnect();
				FotosOmaDAO dao = new FotosOmaDAO();
				dao.excluirpasta(this.pastaSelecionada);
				String caminho = FacesContext.getCurrentInstance()
						.getExternalContext().getApplicationContextPath();
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(caminho + "/fotos-oma.xhtml");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void criaPastaFtp(String nomePasta) throws Exception,
			JSchException, SftpException {
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
			sftpChannel
					.mkdir("//var/lib//tomcat7//webapps//arquivos//intranet//fotos//"
							+ nomePasta.toLowerCase());
		} catch (Exception e) {
			throw new Exception("Já existe um álbum com este nome!");
		}
		sftpChannel.exit();
		session.disconnect();
	}

	public static void uploadFotoFtp(InputStream arquivo, String nomepasta,
			String nomeArquivo) {
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
					"//var/lib//tomcat7//webapps//arquivos//intranet//fotos//"
							+ nomepasta + "//" + nomeArquivo);
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
			sftpChannel
					.rm("//var/lib//tomcat7//webapps//arquivos//intranet//fotos//"
							+ nomeArquivo);
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
			sftpChannel
					.rm("//var/lib//tomcat7//webapps//arquivos//intranet//fotos//"
							+ nomeArquivo);
			sftpChannel.exit();
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		}
	}

	public void alterarNomepasta() {
		try {
			if (this.novoNome.contains("/") || this.novoNome.contains("\"")) {
				throw new Exception(
						"O nome do álbum não aceita caracteres de barra!");
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
					"//var/lib//tomcat7//webapps//arquivos//intranet//fotos//"
							+ this.pastaSelecionada.getNome(),
					"//var/lib//tomcat7//webapps//arquivos//intranet//fotos//"
							+ this.novoNome);
			sftpChannel.exit();
			session.disconnect();
			FotosOmaDAO dao = new FotosOmaDAO();
			this.pastaSelecionada.setNome(this.novoNome);
			dao.alterarNomepasta(this.pastaSelecionada);
			this.novoNome = "";
			RequestContext.getCurrentInstance().execute(
					"PF('dlgAltrNomepasta').hide();");
			String caminho = FacesContext.getCurrentInstance()
					.getExternalContext().getApplicationContextPath();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							caminho
									+ "/fotos-oma/editar-pasta.xhtml?codigopasta="
									+ this.pastaSelecionada.getCodigo());
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							e.getMessage(), ""));
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
			if (this.pastaSelecionada != null) {
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
					sftpChannel
							.rm("//var/lib//tomcat7//webapps//arquivos//intranet//fotos//"
									+ this.pastaSelecionada.getNome()
									+ "//"
									+ this.fotoSelecionada);
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
					RequestContext.getCurrentInstance().execute(
							"$('.fechar').click();");
					RequestContext.getCurrentInstance().update("frmFotos");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}*/