package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class intra_emissor_arquivos implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "varchar(250)")
	private String nomeEmissor1;
	@Column(columnDefinition = "varchar(250)")
	private String nomeEmissor2;
	@Column(columnDefinition = "varchar(250)")
	private String nomeTxtDest;
	@OneToOne
	private intra_emissor emissor;
	@Lob
	private byte[] emissor1;
	@Lob
	private byte[] emissor2;
	@Lob
	private byte[] txtDest;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomeEmissor1() {
		return nomeEmissor1;
	}

	public void setNomeEmissor1(String nomeEmissor1) {
		this.nomeEmissor1 = nomeEmissor1;
	}

	public String getNomeEmissor2() {
		return nomeEmissor2;
	}

	public void setNomeEmissor2(String nomeEmissor2) {
		this.nomeEmissor2 = nomeEmissor2;
	}

	public String getNomeTxtDest() {
		return nomeTxtDest;
	}

	public void setNomeTxtDest(String nomeTxtDest) {
		this.nomeTxtDest = nomeTxtDest;
	}

	public intra_emissor getEmissor() {
		return emissor;
	}

	public void setEmissor(intra_emissor emissor) {
		this.emissor = emissor;
	}

	public byte[] getEmissor1() {
		return emissor1;
	}

	public void setEmissor1(byte[] emissor1) {
		this.emissor1 = emissor1;
	}

	public byte[] getEmissor2() {
		return emissor2;
	}

	public void setEmissor2(byte[] emissor2) {
		this.emissor2 = emissor2;
	}

	public byte[] getTxtDest() {
		return txtDest;
	}

	public void setTxtDest(byte[] txtDest) {
		this.txtDest = txtDest;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((emissor == null) ? 0 : emissor.hashCode());
		result = prime * result + Arrays.hashCode(emissor1);
		result = prime * result + Arrays.hashCode(emissor2);
		result = prime * result + ((nomeEmissor1 == null) ? 0 : nomeEmissor1.hashCode());
		result = prime * result + ((nomeEmissor2 == null) ? 0 : nomeEmissor2.hashCode());
		result = prime * result + ((nomeTxtDest == null) ? 0 : nomeTxtDest.hashCode());
		result = prime * result + Arrays.hashCode(txtDest);
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
		intra_emissor_arquivos other = (intra_emissor_arquivos) obj;
		if (codigo != other.codigo)
			return false;
		if (emissor == null) {
			if (other.emissor != null)
				return false;
		} else if (!emissor.equals(other.emissor))
			return false;
		if (!Arrays.equals(emissor1, other.emissor1))
			return false;
		if (!Arrays.equals(emissor2, other.emissor2))
			return false;
		if (nomeEmissor1 == null) {
			if (other.nomeEmissor1 != null)
				return false;
		} else if (!nomeEmissor1.equals(other.nomeEmissor1))
			return false;
		if (nomeEmissor2 == null) {
			if (other.nomeEmissor2 != null)
				return false;
		} else if (!nomeEmissor2.equals(other.nomeEmissor2))
			return false;
		if (nomeTxtDest == null) {
			if (other.nomeTxtDest != null)
				return false;
		} else if (!nomeTxtDest.equals(other.nomeTxtDest))
			return false;
		if (!Arrays.equals(txtDest, other.txtDest))
			return false;
		return true;
	}

}
