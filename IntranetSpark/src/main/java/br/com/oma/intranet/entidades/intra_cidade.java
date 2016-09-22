package br.com.oma.intranet.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class intra_cidade {
	@Id
	@GeneratedValue
	private int codigo;

	private String cidade;

	@ManyToOne
	@JoinColumn(name = "fk_uf")
	private intra_uf fk_uf;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public intra_uf getFk_uf() {
		return fk_uf;
	}

	public void setFk_uf(intra_uf fk_uf) {
		this.fk_uf = fk_uf;
	}

}
