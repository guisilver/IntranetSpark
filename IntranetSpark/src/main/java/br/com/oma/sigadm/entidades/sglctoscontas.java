package br.com.oma.sigadm.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "sigadm.dbo")
public class sglctoscontas {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "numeric(18,0)")
	private int nrolancto;
	@Column(columnDefinition = "tinyint")
	private short origem;
	@Column(columnDefinition = "varchar(100)")
	private String chave;

	public int getNrolancto() {
		return nrolancto;
	}

	public void setNrolancto(int nrolancto) {
		this.nrolancto = nrolancto;
	}

	public short getOrigem() {
		return origem;
	}

	public void setOrigem(short origem) {
		this.origem = origem;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

}
