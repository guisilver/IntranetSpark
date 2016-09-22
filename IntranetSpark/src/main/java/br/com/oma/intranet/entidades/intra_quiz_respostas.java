package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class intra_quiz_respostas implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta1;
	@Column(columnDefinition = "varchar(20)")
	private String resposta1;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta2;
	@Column(columnDefinition = "varchar(20)")
	private String resposta2;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta3;
	@Column(columnDefinition = "varchar(20)")
	private String resposta3;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta4;
	@Column(columnDefinition = "varchar(20)")
	private String resposta4;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta5;
	@Column(columnDefinition = "varchar(20)")
	private String resposta5;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta6;
	@Column(columnDefinition = "varchar(20)")
	private String resposta6;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta7;
	@Column(columnDefinition = "varchar(20)")
	private String resposta7;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta8;
	@Column(columnDefinition = "varchar(20)")
	private String resposta8;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta9;
	@Column(columnDefinition = "varchar(20)")
	private String resposta9;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta10;
	@Column(columnDefinition = "varchar(20)")
	private String resposta10;
	@Column(columnDefinition = "varchar(350)")
	private String perguntaObs;
	@Column(columnDefinition = "varchar(20)")
	private String respostaObs;
	@Column(columnDefinition = "varchar(100)")
	private String nomeSindico;
	@Column(columnDefinition = "varchar(100)")
	private String emailSindico;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRespondido;
	@Column(columnDefinition = "int")
	private int condominio;
	@Column(columnDefinition = "int")
	private int faseGeral;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getPergunta1() {
		return pergunta1;
	}

	public void setPergunta1(String pergunta1) {
		this.pergunta1 = pergunta1;
	}

	public String getResposta1() {
		return resposta1;
	}

	public void setResposta1(String resposta1) {
		this.resposta1 = resposta1;
	}

	public String getPergunta2() {
		return pergunta2;
	}

	public void setPergunta2(String pergunta2) {
		this.pergunta2 = pergunta2;
	}

	public String getResposta2() {
		return resposta2;
	}

	public void setResposta2(String resposta2) {
		this.resposta2 = resposta2;
	}

	public String getPergunta3() {
		return pergunta3;
	}

	public void setPergunta3(String pergunta3) {
		this.pergunta3 = pergunta3;
	}

	public String getResposta3() {
		return resposta3;
	}

	public void setResposta3(String resposta3) {
		this.resposta3 = resposta3;
	}

	public String getPergunta4() {
		return pergunta4;
	}

	public void setPergunta4(String pergunta4) {
		this.pergunta4 = pergunta4;
	}

	public String getResposta4() {
		return resposta4;
	}

	public void setResposta4(String resposta4) {
		this.resposta4 = resposta4;
	}

	public String getPergunta5() {
		return pergunta5;
	}

	public void setPergunta5(String pergunta5) {
		this.pergunta5 = pergunta5;
	}

	public String getResposta5() {
		return resposta5;
	}

	public void setResposta5(String resposta5) {
		this.resposta5 = resposta5;
	}

	public String getPergunta6() {
		return pergunta6;
	}

	public void setPergunta6(String pergunta6) {
		this.pergunta6 = pergunta6;
	}

	public String getResposta6() {
		return resposta6;
	}

	public void setResposta6(String resposta6) {
		this.resposta6 = resposta6;
	}

	public String getPergunta7() {
		return pergunta7;
	}

	public void setPergunta7(String pergunta7) {
		this.pergunta7 = pergunta7;
	}

	public String getResposta7() {
		return resposta7;
	}

	public void setResposta7(String resposta7) {
		this.resposta7 = resposta7;
	}

	public String getPergunta8() {
		return pergunta8;
	}

	public void setPergunta8(String pergunta8) {
		this.pergunta8 = pergunta8;
	}

	public String getResposta8() {
		return resposta8;
	}

	public void setResposta8(String resposta8) {
		this.resposta8 = resposta8;
	}

	public String getPergunta9() {
		return pergunta9;
	}

	public void setPergunta9(String pergunta9) {
		this.pergunta9 = pergunta9;
	}

	public String getResposta9() {
		return resposta9;
	}

	public void setResposta9(String resposta9) {
		this.resposta9 = resposta9;
	}

	public String getPergunta10() {
		return pergunta10;
	}

	public void setPergunta10(String pergunta10) {
		this.pergunta10 = pergunta10;
	}

	public String getResposta10() {
		return resposta10;
	}

	public void setResposta10(String resposta10) {
		this.resposta10 = resposta10;
	}

	public String getPerguntaObs() {
		return perguntaObs;
	}

	public void setPerguntaObs(String perguntaObs) {
		this.perguntaObs = perguntaObs;
	}

	public String getRespostaObs() {
		return respostaObs;
	}

	public void setRespostaObs(String respostaObs) {
		this.respostaObs = respostaObs;
	}

	public String getNomeSindico() {
		return nomeSindico;
	}

	public void setNomeSindico(String nomeSindico) {
		this.nomeSindico = nomeSindico;
	}

	public String getEmailSindico() {
		return emailSindico;
	}

	public void setEmailSindico(String emailSindico) {
		this.emailSindico = emailSindico;
	}

	public Date getDataRespondido() {
		return dataRespondido;
	}

	public void setDataRespondido(Date dataRespondido) {
		this.dataRespondido = dataRespondido;
	}

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public int getFaseGeral() {
		return faseGeral;
	}

	public void setFaseGeral(int faseGeral) {
		this.faseGeral = faseGeral;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + condominio;
		result = prime * result + ((dataRespondido == null) ? 0 : dataRespondido.hashCode());
		result = prime * result + ((emailSindico == null) ? 0 : emailSindico.hashCode());
		result = prime * result + faseGeral;
		result = prime * result + ((nomeSindico == null) ? 0 : nomeSindico.hashCode());
		result = prime * result + ((pergunta1 == null) ? 0 : pergunta1.hashCode());
		result = prime * result + ((pergunta10 == null) ? 0 : pergunta10.hashCode());
		result = prime * result + ((pergunta2 == null) ? 0 : pergunta2.hashCode());
		result = prime * result + ((pergunta3 == null) ? 0 : pergunta3.hashCode());
		result = prime * result + ((pergunta4 == null) ? 0 : pergunta4.hashCode());
		result = prime * result + ((pergunta5 == null) ? 0 : pergunta5.hashCode());
		result = prime * result + ((pergunta6 == null) ? 0 : pergunta6.hashCode());
		result = prime * result + ((pergunta7 == null) ? 0 : pergunta7.hashCode());
		result = prime * result + ((pergunta8 == null) ? 0 : pergunta8.hashCode());
		result = prime * result + ((pergunta9 == null) ? 0 : pergunta9.hashCode());
		result = prime * result + ((perguntaObs == null) ? 0 : perguntaObs.hashCode());
		result = prime * result + ((resposta1 == null) ? 0 : resposta1.hashCode());
		result = prime * result + ((resposta10 == null) ? 0 : resposta10.hashCode());
		result = prime * result + ((resposta2 == null) ? 0 : resposta2.hashCode());
		result = prime * result + ((resposta3 == null) ? 0 : resposta3.hashCode());
		result = prime * result + ((resposta4 == null) ? 0 : resposta4.hashCode());
		result = prime * result + ((resposta5 == null) ? 0 : resposta5.hashCode());
		result = prime * result + ((resposta6 == null) ? 0 : resposta6.hashCode());
		result = prime * result + ((resposta7 == null) ? 0 : resposta7.hashCode());
		result = prime * result + ((resposta8 == null) ? 0 : resposta8.hashCode());
		result = prime * result + ((resposta9 == null) ? 0 : resposta9.hashCode());
		result = prime * result + ((respostaObs == null) ? 0 : respostaObs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		intra_quiz_respostas other = (intra_quiz_respostas) obj;
		if (codigo != other.codigo)
			return false;
		if (condominio != other.condominio)
			return false;
		if (dataRespondido == null) {
			if (other.dataRespondido != null)
				return false;
		} else if (!dataRespondido.equals(other.dataRespondido))
			return false;
		if (emailSindico == null) {
			if (other.emailSindico != null)
				return false;
		} else if (!emailSindico.equals(other.emailSindico))
			return false;
		if (faseGeral != other.faseGeral)
			return false;
		if (nomeSindico == null) {
			if (other.nomeSindico != null)
				return false;
		} else if (!nomeSindico.equals(other.nomeSindico))
			return false;
		if (pergunta1 == null) {
			if (other.pergunta1 != null)
				return false;
		} else if (!pergunta1.equals(other.pergunta1))
			return false;
		if (pergunta10 == null) {
			if (other.pergunta10 != null)
				return false;
		} else if (!pergunta10.equals(other.pergunta10))
			return false;
		if (pergunta2 == null) {
			if (other.pergunta2 != null)
				return false;
		} else if (!pergunta2.equals(other.pergunta2))
			return false;
		if (pergunta3 == null) {
			if (other.pergunta3 != null)
				return false;
		} else if (!pergunta3.equals(other.pergunta3))
			return false;
		if (pergunta4 == null) {
			if (other.pergunta4 != null)
				return false;
		} else if (!pergunta4.equals(other.pergunta4))
			return false;
		if (pergunta5 == null) {
			if (other.pergunta5 != null)
				return false;
		} else if (!pergunta5.equals(other.pergunta5))
			return false;
		if (pergunta6 == null) {
			if (other.pergunta6 != null)
				return false;
		} else if (!pergunta6.equals(other.pergunta6))
			return false;
		if (pergunta7 == null) {
			if (other.pergunta7 != null)
				return false;
		} else if (!pergunta7.equals(other.pergunta7))
			return false;
		if (pergunta8 == null) {
			if (other.pergunta8 != null)
				return false;
		} else if (!pergunta8.equals(other.pergunta8))
			return false;
		if (pergunta9 == null) {
			if (other.pergunta9 != null)
				return false;
		} else if (!pergunta9.equals(other.pergunta9))
			return false;
		if (perguntaObs == null) {
			if (other.perguntaObs != null)
				return false;
		} else if (!perguntaObs.equals(other.perguntaObs))
			return false;
		if (resposta1 == null) {
			if (other.resposta1 != null)
				return false;
		} else if (!resposta1.equals(other.resposta1))
			return false;
		if (resposta10 == null) {
			if (other.resposta10 != null)
				return false;
		} else if (!resposta10.equals(other.resposta10))
			return false;
		if (resposta2 == null) {
			if (other.resposta2 != null)
				return false;
		} else if (!resposta2.equals(other.resposta2))
			return false;
		if (resposta3 == null) {
			if (other.resposta3 != null)
				return false;
		} else if (!resposta3.equals(other.resposta3))
			return false;
		if (resposta4 == null) {
			if (other.resposta4 != null)
				return false;
		} else if (!resposta4.equals(other.resposta4))
			return false;
		if (resposta5 == null) {
			if (other.resposta5 != null)
				return false;
		} else if (!resposta5.equals(other.resposta5))
			return false;
		if (resposta6 == null) {
			if (other.resposta6 != null)
				return false;
		} else if (!resposta6.equals(other.resposta6))
			return false;
		if (resposta7 == null) {
			if (other.resposta7 != null)
				return false;
		} else if (!resposta7.equals(other.resposta7))
			return false;
		if (resposta8 == null) {
			if (other.resposta8 != null)
				return false;
		} else if (!resposta8.equals(other.resposta8))
			return false;
		if (resposta9 == null) {
			if (other.resposta9 != null)
				return false;
		} else if (!resposta9.equals(other.resposta9))
			return false;
		if (respostaObs == null) {
			if (other.respostaObs != null)
				return false;
		} else if (!respostaObs.equals(other.respostaObs))
			return false;
		return true;
	}
	

}
