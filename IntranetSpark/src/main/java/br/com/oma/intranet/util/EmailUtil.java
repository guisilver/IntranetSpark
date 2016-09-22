package br.com.oma.intranet.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.oma.intranet.entidades.intra_reservas_ti;
import br.com.oma.intranet.filters.ConexaoGeral;
import br.com.oma.intranet.managedbeans.Config;

public class EmailUtil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Config conf = new Config(new ConexaoGeral().getEmail());

	@SuppressWarnings("deprecation")
	public void enviaConvocacao(intra_reservas_ti reserva_ti, String solicitante) throws EmailException {

		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat df2 = new SimpleDateFormat("HH:mm");
			String data = df.format(reserva_ti.getData());
			HtmlEmail html = new HtmlEmail();
			html.setSmtpPort(Integer.valueOf(this.conf.Conf().getProperty("jdbc.server.email.porta")));
			html.setAuthentication(this.conf.Conf().getProperty("jdbc.server.email.user"),
					this.conf.Conf().getProperty("jdbc.server.email.password"));
			html.setDebug(false);
			html.setHostName(this.conf.Conf().getProperty("jdbc.server.email.host"));
			html.setFrom("INTRANET@oma.com.br");
			html.setSubject("Reserva de Equipamentos");

			StringBuilder mensagem = new StringBuilder("<head></head><body><h3> " + solicitante
					+ " solicitou a reserva dos seguintes equipamentos : </h3><br/><br/>");
			if (reserva_ti.isAdaptador()) {
				mensagem = mensagem.append("- Adaptador;<br/>");
			}
			if (reserva_ti.isApresentador()) {
				mensagem = mensagem.append("- Apresentador;<br/>");
			}
			if (reserva_ti.isEnergia()) {
				mensagem = mensagem.append("- Extens&atilde;o de Energia;<br/>");
			}
			if (reserva_ti.isVga()) {
				mensagem = mensagem.append("- Extens&atilde;o de VGA;<br/>");
			}
			if (reserva_ti.isMala()) {
				mensagem = mensagem.append("- Mala;<br/>");
			}
			if (reserva_ti.isPendrive()) {
				mensagem = mensagem.append("- Pen Drive;<br/>");
			}
			if (reserva_ti.isProjetor()) {
				mensagem = mensagem.append("- Projetor;<br/>");
			}
			if (reserva_ti.isTela()) {
				mensagem = mensagem.append("- Tela;<br/><br/>");
			}

			mensagem = mensagem.append("Para a Assembleia do condom&iacute;nio " + reserva_ti.getNome_condominio()
					+ " que ser&aacute; efetuada no dia " + data + " &agrave;s " + df2.format(reserva_ti.getHorario())
					+ " horas.");
			mensagem = mensagem.append(" </body></head></html>");
			String msg = mensagem.toString();
			html.setHtmlMsg(msg);
			html.addTo("ti@oma.com.br");
			html.setTLS(false);
			html.send();
		} catch (EmailException e) {
			System.out.println(e);
		}

	}

	@SuppressWarnings("deprecation")
	public void enviaAssistente(intra_reservas_ti reserva, String email_assistente, String email_gerente,
			String email_usuario) {
		List<String> lista_emails = new ArrayList<>();
		if (email_assistente != null && email_assistente != "") {
			lista_emails.add(email_assistente);
		}
		if (email_gerente != null && email_gerente != "") {
			lista_emails.add(email_gerente);
		}
		if (email_usuario != null && email_usuario != "") {
			lista_emails.add(email_usuario);
		}
		if (lista_emails.size() > 0) {
			for (int i = 0; i < lista_emails.size(); i++) {
				try {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					DateFormat df2 = new SimpleDateFormat("HH:mm");
					String data = df.format(reserva.getData());
					HtmlEmail html = new HtmlEmail();
					html.setSmtpPort(Integer.valueOf(this.conf.Conf().getProperty("jdbc.server.email.porta")));
					html.setAuthentication(this.conf.Conf().getProperty("jdbc.server.email.user"),
							this.conf.Conf().getProperty("jdbc.server.email.password"));
					html.setDebug(false);
					html.setHostName(this.conf.Conf().getProperty("jdbc.server.email.host"));
					html.setFrom("INTRANET@oma.com.br");
					html.setSubject("Reserva de Assembleia");

					StringBuilder mensagem = new StringBuilder(
							"<head></head><body><h3> " + "Reservas : </h3><br/><br/>");
					if (reserva.isCadeiras()) {
						mensagem = mensagem.append("- Cadeiras;<br/>");
					}
					if (reserva.isMesas()) {
						mensagem = mensagem.append("- Mesas;<br/><br/>");
					}

					mensagem = mensagem.append("Para a Assembleia do condom&iacute;nio " + reserva.getNome_condominio()
							+ " que ser&aacute; efetuada no dia " + data + " &agrave;s "
							+ df2.format(reserva.getHorario()) + " horas.");
					mensagem = mensagem.append(" </body></head></html>");
					String msg = mensagem.toString();
					html.setHtmlMsg(msg);
					html.addTo(lista_emails.get(i));
					html.setTLS(false);
					html.send();
				} catch (EmailException e) {
					System.out.println(e);
					continue;
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void enviaRecadastramento(String email, String nome, String obs) throws EmailException {
		try {
			HtmlEmail html = new HtmlEmail();
			html.setSmtpPort(Integer.valueOf(this.conf.Conf().getProperty("jdbc.server.email.porta")));
			html.setAuthentication(this.conf.Conf().getProperty("jdbc.server.email.user"),
					this.conf.Conf().getProperty("jdbc.server.email.password"));
			html.setDebug(false);
			html.setHostName(this.conf.Conf().getProperty("jdbc.server.email.host"));
			html.setFrom("oma@oma.com.br");
			html.setSubject("Reprova&ccedil;&#227;o de Recadastramento OMA");
			StringBuilder mensagem = new StringBuilder("<head></head><body><h3>Sr. " + nome
					+ ", informamos que seu recadastramento foi reprovado! </h3><br/><br/>");
			mensagem = mensagem.append("Motivo: " + obs);
			mensagem = mensagem.append(" </body></head></html>");
			String msg = mensagem.toString();
			html.setHtmlMsg(msg);
			html.addTo(email);
			html.setTLS(false);
			html.send();
		} catch (EmailException e) {
			System.out.println(e);
		}
	}

}
