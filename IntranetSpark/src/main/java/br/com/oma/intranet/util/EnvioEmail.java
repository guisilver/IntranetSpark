package br.com.oma.intranet.util;

import java.io.Serializable;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.oma.intranet.filters.ConexaoGeral;
import br.com.oma.intranet.managedbeans.Config;
import br.com.oma.omaonline.entidades.cndpagar;

public class EnvioEmail extends ValidadorEmail implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Config conf = new Config(new ConexaoGeral().getEmail());

	/*
	 * public void enviaEmailSimples(String mail, String novaSenha) throws
	 * EmailException, MalformedURLException {
	 * 
	 * SimpleEmail email = new SimpleEmail(); URL url = new
	 * URL("http://10.1.1.20:8080/images/oma.jpg"); HtmlEmail html = new
	 * HtmlEmail();
	 * 
	 * email.setHostName("mail.oma.com.br");
	 * 
	 * email.setSmtpPort(587); email.setAuthentication("oma", "Oma@63059");
	 * email.setFrom("oma@oma.com.br"); email.setSubject("Reenvio de senha!");
	 * 
	 * String cid = html.embed(url,"Logo");
	 * email.setMsg("<html><head></head><body><img src=\"cid:"
	 * +cid+"\"><h3>Sua nova senha �: "+novaSenha+" </h3></body></html>");
	 * 
	 * email.setSSL(true); email.addTo(mail); email.send(); }
	 */

	@SuppressWarnings("deprecation")
	public boolean enviaEmailSimples(String mail, String novaSenha) {

		// URL url = new URL("http://10.1.1.20:8080/imagens/oma.jpg");
		boolean valida = true;
		try {
			HtmlEmail html = new HtmlEmail();
			html.setSmtpPort(Integer.valueOf(this.conf.Conf().getProperty("jdbc.server.email.porta")));
			html.setAuthentication(this.conf.Conf().getProperty("jdbc.server.email.user"), this.conf.Conf().getProperty("jdbc.server.email.password"));
			html.setDebug(false);
			html.setHostName(this.conf.Conf().getProperty("jdbc.server.email.host"));
			html.setFrom("oma@oma.com.br");
			html.setSubject("Reenvio de senha!");

			// String cid = html.embed(url,"Logo");<img src=\"cid:"+cid+"\">

			html.setHtmlMsg("<html><head></head><body><h3>Sua nova senha &eacute;: "
					+ novaSenha + " </h3></body></html>");
			html.addTo(mail);
			html.setTLS(false);
			html.send();
		} catch (EmailException e) {
			valida = false;
		}
		return valida;
	}
	
	@SuppressWarnings("deprecation")
	public boolean reprovacaoLancamento(cndpagar cndpagar)
			throws RNException {
		boolean valida = true;
		try {
			HtmlEmail html = new HtmlEmail();
			html.setSmtpPort(Integer.valueOf(this.conf.Conf().getProperty("jdbc.server.email.porta")));
			html.setAuthentication(this.conf.Conf().getProperty("jdbc.server.email.user"), this.conf.Conf().getProperty("jdbc.server.email.password"));
			html.setDebug(false);
			html.setHostName(this.conf.Conf().getProperty("jdbc.server.email.host"));
			html.setFrom("oma@oma.com.br");
			html.setSubject("Reprovação de Lançamento");
			html.setHtmlMsg("<html><head><meta charset='UTF-8' /></head><body><span style='color:red;'>Aten&ccedil;&atilde;o</span><br/><br/>O lan&ccedil;amento de N&ordm; "
					+ cndpagar.getCodigo()
					+ " foi reprovado pelo seguinte motivo: <br/><br/>"
					+ "<span style='color:red;'>"
					+ cndpagar.getMotivoReprovacao()
					+ "</span>"
					+ "<br/><br/> Favor verificar no sistema <a href='http://omaonline.com.br:42050/omaonline/sistema/consulta-lancamentos.xhtml'>OMAONLINE</a><br/><br/>"
					+ "Att,<br/><br/><img src='http://omaonline.com.br:42050/imagens/logo_oma.png' width='235' height='80'/></body></html>");
			if (cndpagar.getAdicionadoPor().equals(
					"regina@fazendadagrama.com.br")
					|| cndpagar.getAdicionadoPor().equals(
							"janaina@fazendadagrama.com.br")) {
				html.addTo("regina@fazendadagrama.com.br");
				html.addTo("janaina@fazendadagrama.com.br");
			} else if (cndpagar.getAdicionadoPor().equals(
					"everton@fazendadagrama.com.br")) {
				html.addTo("everton@fazendadagrama.com.br");
			} else {
				html.addTo(cndpagar.getAdicionadoPor());
			}
			html.setTLS(false);
			html.send();
		} catch (EmailException e) {
			valida = false;
			throw new RNException("E-mail não foi enviado!");
		}
		return valida;
	}

/*	public void envioAtualizaDadosMorador(int condominio, String bloco,
			String unidade, String nomeMorador, String cpf, String rg,
			String email1, String telres, String telcom, String telcel,
			String datanasc) {

		// URL url = new URL("http://186.200.214.66:42050/images/oma.jpg");
		try {

			HtmlEmail html = new HtmlEmail();
			html.setSmtpPort(587);
			html.setAuthentication("oma", "Oma@123");
			html.setDebug(false);
			html.setHostName("mail.oma.com.br");
			html.setFrom("oma@oma.com.br");
			html.setSubject("Atualiza&ccedil;&atilde;o de Dados Sistema OMAONLINE");

			// String cid = html.embed(url,"Logo");<img src=\"cid:"+cid+"\">

			html.setHtmlMsg("<head></head><body><h3>O senhor(a) " + nomeMorador
					+ " atualizou seus Dados:</h3>" + "<form>"
					+ "Condom&iacute;nio: " + condominio + "	<br/>"
					+ "Bloco:  " + bloco + "	<br/>" + "Unidade: " + unidade
					+ "	<br/><br/>	" + "CPF: " + cpf + "	<br/>" + "RG: " + rg
					+ "	<br/>" + "E-mail:  " + email1 + "	<br/>"
					+ "Tel. Res.: " + telres + "	<br/>" + "Tel. Com.: "
					+ telcom + "	<br/>" + "Tel. Cel.: " + telcel + "	<br/>"
					+ "Data Nasc.: " + datanasc + ""
					+ "</form></table></body></html>");
			html.addTo("oma@oma.com.br");
			html.setTLS(true);
			html.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

/*	public boolean novoCadastro(short condominio, String nomeCondo,
			String bloco, String unidade, String nome, String cpf, String rg,
			String e_mail_1, String tel_res, String tel_com, String tel_cel,
			String data_nasc, String portsenha, String cargo) {

		boolean valida = false;
		try {
			// URL url = new URL("http://186.200.214.66:42050/imagens/oma.jpg");

			HtmlEmail html = new HtmlEmail();
			html.setSmtpPort(587);
			html.setAuthentication("oma", "Oma@123");
			html.setDebug(false);
			html.setHostName("mail.oma.com.br");
			html.setFrom("oma@oma.com.br");
			html.setSubject("Novo cadastro");

			// String cid = html.embed(url,"Logo");<img src=\"cid:"+cid+"\">
			// String cid = html.embed(url,"Logo");<img src=\"cid:"+cid+"\">

			if (rg.equals("")) {

			} else if (tel_com.equals("")) {

			} else if (tel_cel.equals("")) {

			}

			html.setHtmlMsg("<head></head><body><h3>Senhor(a) " + nome
					+ ",</h3> " + "Seu cadastro foi efetuado com sucesso!<br/>"
					+ "Aguarde email de libera&ccedil;&atilde;o!"
					+ "<h3>Seus dados cadastrais:</h3>" + "<form>"
					+ "Condom&iacute;nio: " + nomeCondo + "	<br/>" + "Bloco:  "
					+ bloco + "	<br/>" + "Unidade: " + unidade + "	<br/><br/>	"
					+ "CPF: " + cpf + "	<br/>" + "RG: " + rg + "<br/>"
					+ "E-mail:  " + e_mail_1 + "	<br/>" + "Tel. Res.: "
					+ tel_res + "	<br/>" + "Tel. Com.: " + tel_com + "	<br/>"
					+ "Tel. Cel.: " + tel_cel + "	<br/>" + "Data Nasc.: "
					+ data_nasc + "	<br/><br/><hr/>" + ""
					+ "</form></table></body></html>");
			html.addTo(e_mail_1);
			html.setTLS(true);
			html.send();

			valida = true;

		} catch (EmailException e) {
			valida = false;
		}
		return valida;
	}*/

/*	public static void reservaComodidade(List<CndFuncEmail> listaFuncEmail,
			cdreserva reserva) throws EmailException, FileNotFoundException,
			ClassNotFoundException, IOException, SQLException {
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String data1 = sf.format(reserva.getData_inicio());
		String data2 = sf.format(reserva.getData_fim());
		for (CndFuncEmail aux : listaFuncEmail) {
			try {
				HtmlEmail html = new HtmlEmail();
				html.setSmtpPort(587);
				html.setAuthentication("oma", "Oma@123");
				html.setDebug(false);
				html.setHostName("mail.oma.com.br");
				html.setFrom("oma@oma.com.br");
				html.setSubject("Nova reserva");

				html.setHtmlMsg("<head></head><body><h3>Senhor(a) "
						+ aux.getNome()
						+ ",</h3> "
						+ "Existe uma nova reserva do seu condom&iacute;nio para conferir!<br/>"
						+ "<h3>Dados da Reserva:</h3>" + "<form>"
						+ "Comodidade: " + reserva.getArea_comum() + "	<br/>"
						+ "Bloco:  " + reserva.getBloco() + "	<br/>"
						+ "Unidade: " + reserva.getUnidade() + "	<br/><br/>	"
						+ "Data In&iacute;cio: " + data1 + "	<br/>"
						+ "Data Fim: " + data2 + "	<br/>" + "Valor: R$ "
						+ reserva.getValor() + " reais.	<br/>"
						+ "</form></table></body></html>");
				html.addTo("guilherme.batista@oma.com.br");
				html.setTLS(true);
				html.send();
			} catch (EmailException e) {
				// envioEmailErro(aux.getEmail());
				continue;
			}
		}
	}*/

/*	public static void liberaUsuarios(portliberacao[] liberacao)
			throws EmailException {
		for (portliberacao aux : liberacao) {
			try {
				HtmlEmail html = new HtmlEmail();
				html.setSmtpPort(587);
				html.setAuthentication("oma", "Oma@123");
				html.setDebug(false);
				html.setHostName("mail.oma.com.br");
				html.setFrom("oma@oma.com.br");
				html.setSubject("Bem Vindo");
				html.setHtmlMsg("<head></head><body><h3>Senhor(a) "
						+ aux.getNome()
						+ ",</h3> "
						+ "Seu login foi liberado para acesso ao sistema <a href='http://omaonline.com.br:42050/omaonline/'>omaonline!</a><br/>"
						+ "Seu login &eacute;: " + aux.getEmail() + "	<br/>"
						+ "<form>" + "<br/>" + "</form></table></body></html>");
				html.addTo(aux.getEmail());
				html.setTLS(true);
				html.send();
			} catch (EmailException e) {
				// envioEmailErro(aux.getEmail());
				continue;
			}

		}
	}*/

/*	public static void rejeitaUsuarios(portliberacao[] liberacao)
			throws EmailException {
		for (portliberacao aux : liberacao) {
			if (aux.getRg() == null) {
				aux.setRg("0");
			}
			try {
				HtmlEmail html = new HtmlEmail();
				html.setSmtpPort(587);
				html.setAuthentication("oma", "Oma@123");
				html.setDebug(false);
				html.setHostName("mail.oma.com.br");
				html.setFrom("oma@oma.com.br");
				html.setSubject("Bem Vindo");
				html.setHtmlMsg("<head></head><body><h3>Senhor(a) "
						+ aux.getNome()
						+ ",</h3> "
						+ "Seu cadastro para acesso do sistema omaonline foi recusado!!<br/>"
						+ "Para mais informa&ccedil;&otilde;es entre em contato com o Gestor do condom&iacute;nio!<br/>"
						+ "<form>"
						+ "<br/>"
						+ "Atenciosamente,<br/> Grupo OMA Patrim&ocirc;nios.</form></table></body></html>");
				html.addTo(aux.getEmail());
				html.setTLS(true);
				html.send();
			} catch (EmailException e) {
				// envioEmailErro(aux.getEmail());
				continue;
			}

		}
	}*/

	/***/
	/*
	 * public static void avisaCorreio(List<usuario> listaUsuario, portcorreios
	 * correios) throws EmailException { usuario usuario = new usuario();
	 * 
	 * for (usuario aux : listaUsuario) { usuario.setNome(aux.getNome());
	 * usuario.setEmail(aux.getEmail()); try { HtmlEmail html = new HtmlEmail();
	 * html.setSmtpPort(587); html.setAuthentication("oma", "Oma@123");
	 * html.setDebug(false); html.setHostName("mail.oma.com.br");
	 * html.setFrom("oma@oma.com.br"); html.setSubject("Novo correio");
	 * 
	 * html.setHtmlMsg("<head></head><body><h3>Senhor(a) " + usuario.getNome() +
	 * "</h3><br/>" +
	 * "Existe uma nova correspond&ecirc;ncia para retirar na portaria de seu condom&iacute;nio!<br/>Protocolo N� <b>"
	 * + correios.getN_serie() +
	 * "</b><br/><br/>Att.<br/> GRUPO OMA CONDOM&Iacute;NIOS</body></html>");
	 * html.addTo(usuario.getEmail()); html.setTLS(true); html.send(); } catch
	 * (EmailException e) { // envioEmailErro(login.getPort_login()); continue;
	 * } } }
	 */

/*	public static void avisaEvento(List<CndFuncEmail> lista_parametros_email,
			cdeventos eventos) throws EmailException {
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String data1 = sf.format(eventos.getData());
		for (CndFuncEmail aux : lista_parametros_email) {
			try {
				HtmlEmail html = new HtmlEmail();
				html.setSmtpPort(587);
				html.setAuthentication("oma", "Oma@123");
				html.setDebug(false);
				html.setHostName("mail.oma.com.br");
				html.setFrom("oma@oma.com.br");
				html.setSubject("Novo evento");

				html.setHtmlMsg("<head></head><body><h3>Senhor(a) "
						+ aux.getNome()
						+ ",</h3> "
						+ "Existe um novo evento do seu condom&iacute;nio para conferir!<br/>"
						+ "<h3>Dados do Evento:</h3>" + "<form>" + "Bloco:  "
						+ eventos.getBloco() + "	<br/>" + "Unidade: "
						+ eventos.getUnidade() + "	<br/><br/>	" + "Data: "
						+ data1 + "	<br/>" + "T�tulo do evento: "
						+ eventos.getTituloEvento() + "<br/>"
						+ "</form></table></body></html>");
				html.addTo(aux.getEmail());
				html.setTLS(true);
				html.send();
			} catch (EmailException e) {
				// envioEmailErro(aux.getEmail());
				continue;
			}
		}
	}*/

	/*
	 * public static void envioEmailErro(String email) throws EmailException {
	 * HtmlEmail html = new HtmlEmail(); html.setSmtpPort(587);
	 * html.setAuthentication("oma", "Oma@63059"); html.setDebug(false);
	 * html.setHostName("10.1.1.2"); html.setFrom("oma@oma.com.br");
	 * html.setSubject("Aviso OMAFaciltie");
	 * 
	 * html.setHtmlMsg(
	 * "<head></head><body><h3>Aviso a administrador(a) do OMAFacilitie <br/>" +
	 * "O e-mail " + email + " N&atilde;o existe!<br/>" +
	 * "</form></table></body></html>");
	 * html.addTo("desenvolvimento@oma.com.br"); // MUDAR PARA OMA@OMA
	 * html.setTLS(true); html.send(); }
	 */

/*	public static void avisaCorreio(List<usuario> listarEmail,
			portcorreios correios) {
		portcadastro morador = new portcadastro();
		for (usuario aux : listarEmail) {
			morador.setNome(aux.getNome());
			morador.setE_mail(aux.getEmail());
			try {
				HtmlEmail html = new HtmlEmail();
				html.setSmtpPort(587);
				html.setAuthentication("oma", "Oma@123");
				html.setDebug(false);
				html.setHostName("mail.oma.com.br");
				html.setFrom("oma@oma.com.br");
				html.setSubject("Novo correio");
				html.setHtmlMsg("<head></head><body><h3>Senhor(a) "
						+ morador.getNome()
						+ "</h3><br/>"
						+ "Condominio:"
						+ aux.getCondominio()
						+ "<br/>"
						+ "Existe uma nova correspond&ecirc;ncia para retirar no seu condom&iacute;nio!<br/>Protocolo N� <b>"
						+ correios.getN_serie()
						+ "</b><br/><br/>Att.<br/> GRUPO OMA CONDOM&Iacute;NIOS</body></html>");
				html.addTo(morador.getE_mail());
				html.setTLS(true);
				html.send();
			} catch (EmailException e) {
				continue;
			}
		}
	}*/

/*	public boolean enviaEmailSimplesNovoSind(usuario user, usuario_cnd userCnd,
			cndcondo condo) {

		// URL url = new URL("http://10.1.1.20:8080/imagens/oma.jpg");
		boolean valida = true;
		try {
			HtmlEmail html = new HtmlEmail();
			html.setSmtpPort(587);
			html.setAuthentication("oma", "Oma@123");
			html.setDebug(false);
			html.setHostName("mail.oma.com.br");
			html.setFrom("oma@oma.com.br");
			html.setSubject("Novo Sind�co no OMA OnLine");

			// String cid = html.embed(url,"Logo");<img src=\"cid:"+cid+"\">

			html.setHtmlMsg("<html><head></head><body><h3>Novo sindico: "
					+ user.getNome() + " </h3> <br/> CONDOMINIO: "
					+ condo.getCodigo() + "-" + condo.getNome() + ""
					+ " <br/> <br/> VERIFICAR ACESSOS </body></html>");
			html.addTo("ti@oma.com.br");
			html.setTLS(true);
			html.send();
		} catch (EmailException e) {
			valida = false;
		}
		return valida;
	}*/

/*	public static boolean enviaEmailMsg(portmensagens msg, usuario usr,
			usuario_cnd usrCnd, String nomeCnd, List<String> listaEmail) {
		boolean sucesso = false;
		for (String email : listaEmail) {
			try {
				HtmlEmail html = new HtmlEmail();
				html.setSmtpPort(587);
				html.setAuthentication("oma", "Oma@123");
				html.setDebug(false);
				html.setHostName("mail.oma.com.br");
				html.setFrom(usr.getEmail());
				html.setSubject(msg.getAssunto());
				String permissao = "";
				switch (usrCnd.getPermissao()) {
				case 1:
					permissao = "Administrador";
					break;
				case 2:
					permissao = "Gerente OMA";
					break;
				case 3:
					permissao = "S�ndico";
					break;
				case 4:
					permissao = "Gerente Predial";
					break;
				case 5:
					permissao = "Funcion�rio Predial";
					break;
				case 6:
					permissao = "Zelador";
					break;
				case 7:
					permissao = "Porteiro";
					break;
				case 10:
					permissao = "Morador <br/> Bloco:" + usrCnd.getBloco()
							+ "<br/> Unidade:" + usrCnd.getUnidade();
					break;
				default:
					break;
				}
				html.setHtmlMsg("<html><head></head>"
						+ "<body>"
						+ "<span style='color:red;'>Perfil:&nbsp;</span>"
						+ permissao
						+ "<br/><br/><span style='color:red;'>Condom�nio:&nbsp;</span>"
						+ nomeCnd
						+ "<br/><br/>"
						+ msg.getMensagem()
						+ "<br/><br/><br/><br/>"
						+ "<span style='color:red;'>Mensagem enviada do sistema OMAONLINE.</span><br/>"
						+ "Acesse : <a href='http://www.oma.com.br'>www.oma.com.br</a><br/><br/>"
						+ "<img src='http://omaonline.com.br:42050/imagens/logo_oma.png?pfdrid_c=true' style='width:235px;height:80px;' /></body></html>");
				html.addTo(email);
				html.setTLS(true);
				html.send();
				sucesso = true;

			} catch (EmailException e) {
				System.out.println(e.getMessage());
				sucesso = false;
				continue;
			}
		}
		return sucesso;
	}*/

/*	public boolean novoLancamento(usuario user, usuario_cnd userCnd,
			cndcondo condo) {
		boolean valida = true;
		try {
			HtmlEmail html = new HtmlEmail();
			html.setSmtpPort(587);
			html.setAuthentication("oma", "Oma@123");
			html.setDebug(false);
			html.setHostName("mail.oma.com.br");
			html.setFrom("oma@oma.com.br");
			html.setSubject("Novo Sind�co no OMA OnLine");

			// String cid = html.embed(url,"Logo");<img src=\"cid:"+cid+"\">

			html.setHtmlMsg("<html><head></head><body><h3>Novo sindico: "
					+ user.getNome() + " </h3> <br/> CONDOMINIO: "
					+ condo.getCodigo() + "-" + condo.getNome() + ""
					+ " <br/> <br/> VERIFICAR ACESSOS </body></html>");
			html.addTo("ti@oma.com.br");
			html.setTLS(true);
			html.send();
		} catch (EmailException e) {
			valida = false;
		}
		return valida;
	}*/

/*	public static boolean reprovacaoLancamento(cndpagar cndpagar)
			throws RNException {
		boolean valida = true;
		try {
			HtmlEmail html = new HtmlEmail();
			html.setSmtpPort(587);
			html.setAuthentication("oma", "Oma@123");
			html.setDebug(false);
			html.setHostName("mail.oma.com.br");
			html.setFrom("oma@oma.com.br");
			html.setSubject("Reprova��o de Lan�amento");
			html.setHtmlMsg("<html><head></head><body><span style='color:red;'>Aten��o</span><br/><br/>O lan�amento de N� "
					+ cndpagar.getCodigo()
					+ " foi reprovado pelo seguinte motivo: <br/><br/>"
					+ "<span style='color:red;'>"
					+ cndpagar.getMotivoReprovacao()
					+ "</span>"
					+ "<br/><br/> Favor verificar no sistema <a href='http://omaonline.com.br:42050/omaonline/sistema/consulta-lancamentos.xhtml'>OMAONLINE</a><br/><br/>"
					+ "Att,<br/><br/><img src='http://omaonline.com.br:42050/imagens/logo_oma.png' width='235' height='80'/></body></html>");
			if (cndpagar.getAdicionadoPor().equals(
					"regina@fazendadagrama.com.br")
					|| cndpagar.getAdicionadoPor().equals(
							"janaina@fazendadagrama.com.br")) {
				html.addTo("regina@fazendadagrama.com.br");
				html.addTo("janaina@fazendadagrama.com.br");
			} else if (cndpagar.getAdicionadoPor().equals(
					"everton@fazendadagrama.com.br")) {
				html.addTo("everton@fazendadagrama.com.br");
			} else {
				html.addTo(cndpagar.getAdicionadoPor());
			}
			html.setTLS(true);
			html.send();
		} catch (EmailException e) {
			valida = false;
			throw new RNException("E-mail n�o foi enviado!");
		}
		return valida;
	}*/
}
