package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;

import br.com.oma.intranet.dao.LoginDAO;
import br.com.oma.intranet.entidades.intra_usuario;
import br.com.oma.intranet.util.Mensagens;

@ViewScoped
@ManagedBean(name = "login")
public class LoginMB extends Mensagens implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4509651506877583746L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private intra_usuario usuario = new intra_usuario();
	private LoginDAO ldao;

	// ATRIBUTOS
	private List<intra_usuario> listaUsuario;

	// GET X SET
	public intra_usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(intra_usuario usuario) {
		this.usuario = usuario;
	}

	public List<intra_usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<intra_usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	// METODOS
	public String login() throws NoSuchAlgorithmException {
		this.ldao = new LoginDAO();
		this.usuario.setSenha(this.criptografar(this.usuario.getSenha()));
		if (this.listaUsuario == null) {
			this.listaUsuario = this.ldao.buscaTodos(this.usuario);
			for (intra_usuario user : listaUsuario) {
				this.usuario = user;
			}
		}
		if (!this.listaUsuario.isEmpty()) {
			this.sessaoMB.setUsuario(this.usuario);
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("usuario", this.usuario.getEmail());
			session.setAttribute("userName", this.usuario.getNome());
			this.sessaoMB.setIpUser(Faces.getRemoteAddr());
			this.sessaoMB.setLogado(true);
			this.sessaoMB.getListaGerentes();
			this.sessaoMB.setGerentePrincipal(this.usuario);
			return "/dashboard.xhtml?faces-redirect=true";
		} else {
			this.listaUsuario = null;
			this.usuario.setSenha("");
			this.msgLoginInvalido();
		}
		return null;
	}

	public void salvarCookies() {
		try {
			Cookie email = new Cookie("email", this.usuario.getEmail());
			Cookie senha = new Cookie("senha", this.usuario.getSenha());
			email.setMaxAge(3600);
			senha.setMaxAge(3600);
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
			response.addCookie(email);
			response.addCookie(senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/*	public void verificarCookies() {
		try {
			this.ldao = new LoginDAO();
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
			String email = "", senha = "";
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie ck : cookies) {
					if (ck.getName().equalsIgnoreCase("email")) {
						email = ck.getValue();
						this.usuario.setEmail(email);
					}
					if (ck.getName().equalsIgnoreCase("senha")) {
						senha = ck.getValue();
						this.usuario.setSenha(senha);
					}
				}
				if (this.usuario != null) {
					if (this.usuario.getEmail() != null && !this.usuario.getEmail().trim().isEmpty()
							&& this.usuario.getSenha() != null && !this.usuario.getSenha().trim().isEmpty()) {
						if (this.listaUsuario == null) {
							this.listaUsuario = this.ldao.buscaTodos(this.usuario);
							for (intra_usuario user : listaUsuario) {
								this.usuario = user;
							}
						}
						if (!this.listaUsuario.isEmpty()) {
							this.sessaoMB.setUsuario(this.usuario);
							ExternalContext ec = fc.getExternalContext();
							HttpSession session = (HttpSession) ec.getSession(false);
							session.setAttribute("usuario", this.usuario.getEmail());
							session.setAttribute("userName", this.usuario.getNome());
							this.sessaoMB.setIpUser(Faces.getRemoteAddr());
							this.sessaoMB.setLogado(true);
							this.salvarCookies();
							NavigationHandler nh = fc.getApplication().getNavigationHandler();
							nh.handleNavigation(fc, null, "dashboard.xhtml?faces-redirect=true");
						} else {
							this.listaUsuario = null;
							this.usuario.setSenha("");
							this.msgLoginInvalido();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public void excluirCookies() {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
			HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie ck : cookies) {
					if (ck.getName().equalsIgnoreCase("email")) {
						ck.setMaxAge(0);
						response.addCookie(ck);
					}
					if (ck.getName().equalsIgnoreCase("senha")) {
						ck.setMaxAge(0);
						response.addCookie(ck);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.removeAttribute("usuario");
		this.sessaoMB.setLogado(false);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		nh.handleNavigation(fc, null, "login.xhtml?faces-redirect=true");
	}

	public String criptografar(String user) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5"); // or "SHA -1"
		md.update(user.getBytes());
		BigInteger hash = new BigInteger(1, md.digest());
		String senhaCriptografada = hash.toString(16);
		while (senhaCriptografada.length() < 32) { // 40 for SHA -1
			senhaCriptografada = "0" + senhaCriptografada;
		}
		return senhaCriptografada;
	}

	public String esqueceuSenha() throws NoSuchAlgorithmException {
		this.ldao = new LoginDAO();
		UUID uuid = UUID.randomUUID();
		String myRandom = uuid.toString();
		if (!this.usuario.getEmail().trim().equals("")) {
			this.listaUsuario = this.ldao.esqueceuSenha(this.usuario);
			for (intra_usuario user : listaUsuario) {
				this.usuario = user;
			}
			if (!this.listaUsuario.isEmpty()) {
				this.usuario.setSenha(this.criptografar(myRandom.substring(0, 6)));
				this.ldao.updateSenha(this.usuario);
				this.sessaoMB.setUsuario(this.usuario);
				String email = this.usuario.getEmail();
				this.enviaEmailSimples(email, myRandom.substring(0, 6));
				return "/novaSenha.xhtml?faces-redirect=true";
			} else {
				msgLoginNaoEncontrado();
			}
		}
		return null;
	}

}
