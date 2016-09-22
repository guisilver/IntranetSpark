package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class intra_quiz_perguntas implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta1;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta2;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta3;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta4;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta5;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta6;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta7;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta8;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta9;
	@Column(columnDefinition = "varchar(350)")
	private String pergunta10;
	@Column(columnDefinition = "varchar(350)")
	private String perguntaObs;

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

	public String getPergunta2() {
		return pergunta2;
	}

	public void setPergunta2(String pergunta2) {
		this.pergunta2 = pergunta2;
	}

	public String getPergunta3() {
		return pergunta3;
	}

	public void setPergunta3(String pergunta3) {
		this.pergunta3 = pergunta3;
	}

	public String getPergunta4() {
		return pergunta4;
	}

	public void setPergunta4(String pergunta4) {
		this.pergunta4 = pergunta4;
	}

	public String getPergunta5() {
		return pergunta5;
	}

	public void setPergunta5(String pergunta5) {
		this.pergunta5 = pergunta5;
	}

	public String getPergunta6() {
		return pergunta6;
	}

	public void setPergunta6(String pergunta6) {
		this.pergunta6 = pergunta6;
	}

	public String getPergunta7() {
		return pergunta7;
	}

	public void setPergunta7(String pergunta7) {
		this.pergunta7 = pergunta7;
	}

	public String getPergunta8() {
		return pergunta8;
	}

	public void setPergunta8(String pergunta8) {
		this.pergunta8 = pergunta8;
	}

	public String getPergunta9() {
		return pergunta9;
	}

	public void setPergunta9(String pergunta9) {
		this.pergunta9 = pergunta9;
	}

	public String getPergunta10() {
		return pergunta10;
	}

	public void setPergunta10(String pergunta10) {
		this.pergunta10 = pergunta10;
	}

	public String getPerguntaObs() {
		return perguntaObs;
	}

	public void setPerguntaObs(String perguntaObs) {
		this.perguntaObs = perguntaObs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
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
		intra_quiz_perguntas other = (intra_quiz_perguntas) obj;
		if (codigo != other.codigo)
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
		return true;
	}

}
