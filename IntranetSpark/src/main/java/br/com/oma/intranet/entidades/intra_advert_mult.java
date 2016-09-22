package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

@Entity
public class intra_advert_mult implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition="bigint")
	@GeneratedValue
	private int codigo;
	
	@Column(columnDefinition="smallint")
	private int condominio;
	
	@Column(columnDefinition="varchar(6)")
	private String unidade;
	
	@Column(columnDefinition="varchar(max)")
	private String texto;
	
	@Column(columnDefinition="varchar(1)")
	private String validaTexto = "N";
	
	@Column(columnDefinition="varchar(max)")
	private String clausula;
	
	@Column(columnDefinition="varchar(max)")
	private String artigo;
	
	@Column(columnDefinition="varchar(max)")
	private String frase;
	
	@Column(columnDefinition="varchar(max)")
	private Date ocorrido;
	
	@Column(columnDefinition="varchar(max)")
	private String motivo;
	
	@Column(columnDefinition="varchar(6)")
	private String bloco;
	
	@Column(columnDefinition="varchar(60)")
	private String nome;
	
	@Column(name="nome_condo",columnDefinition="varchar(50)")
	private String nomeCondominio;
	
	@Column(name="nome_geren",columnDefinition="varchar(50)")
	private String nomeGeren;
	
	@Column(name="email_geren",columnDefinition="varchar(50)")
	private String emailGeren;
	
	@Column(name="data_",columnDefinition="datetime")
	private Date dataAtual;
	

	@Column(name="data_ocorrido", columnDefinition="varchar(12)")
	private String dataOcorrido;
	
	@Column(columnDefinition="varchar(2)")
	private String sexo = "F";
	
	@Column(name="tipo_reg_cov",columnDefinition="varchar(2)")
	private String tipoRegCov = "R";
	
	@Column(columnDefinition="varchar(20)")
	private String valor;
	
	@Column(columnDefinition="varchar(max)")
	private String valorExtenso;
	
	@Column(columnDefinition="varchar(15)")
	private String vencimento;
	
	@Column(name="codigo_gerente",columnDefinition="int")
	private int codigoGeren;
	
	@Column(name="item_clausula",columnDefinition="bit")
	private boolean itemClausula = true;
	
	@Column(columnDefinition="int")
	private int tipo;
	
	@Lob
	@Column(columnDefinition="varbinary(max)")
	private byte[] protocolo;
	
	@Transient
	private boolean blGeral;
	
	@Transient
	private boolean blBloco;
	
	@Transient
	private boolean blUnida;
	
	@Transient
	private boolean blCondo;

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValorExtenso() {
		return valorExtenso;
	}

	public void setValorExtenso(String valorExtenso) {
		this.valorExtenso = valorExtenso;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getClausula() {
		return clausula;
	}

	public void setClausula(String clausula) {
		this.clausula = clausula;
	}

	public String getArtigo() {
		return artigo;
	}

	public void setArtigo(String artigo) {
		this.artigo = artigo;
	}

	public String getFrase() {
		return frase;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}

	public Date getOcorrido() {
		return ocorrido;
	}

	public void setOcorrido(Date ocorrido) {
		this.ocorrido = ocorrido;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeCondominio() {
		return nomeCondominio;
	}

	public void setNomeCondominio(String nomeCondominio) {
		this.nomeCondominio = nomeCondominio;
	}

	public String getNomeGeren() {
		return nomeGeren;
	}

	public void setNomeGeren(String nomeGeren) {
		this.nomeGeren = nomeGeren;
	}

	public String getEmailGeren() {
		return emailGeren;
	}

	public void setEmailGeren(String emailGeren) {
		this.emailGeren = emailGeren;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public String getDataOcorrido() {
		return dataOcorrido;
	}

	public void setDataOcorrido(String dataOcorrido) {
		this.dataOcorrido = dataOcorrido;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getCodigoGeren() {
		return codigoGeren;
	}

	public void setCodigoGeren(int codigoGeren) {
		this.codigoGeren = codigoGeren;
	}

	public boolean isBlGeral() {
		return blGeral;
	}

	public void setBlGeral(boolean blGeral) {
		this.blGeral = blGeral;
	}

	public boolean isBlBloco() {
		return blBloco;
	}

	public void setBlBloco(boolean blBloco) {
		this.blBloco = blBloco;
	}

	public boolean isBlUnida() {
		return blUnida;
	}

	public void setBlUnida(boolean blUnida) {
		this.blUnida = blUnida;
	}

	public boolean isBlCondo() {
		return blCondo;
	}

	public void setBlCondo(boolean blCondo) {
		this.blCondo = blCondo;
	}

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * @return the validaTexto
	 */
	public String getValidaTexto() {
		return validaTexto;
	}

	/**
	 * @param validaTexto the validaTexto to set
	 */
	public void setValidaTexto(String validaTexto) {
		this.validaTexto = validaTexto;
	}

	/**
	 * @return the tipoRegCov
	 */
	public String getTipoRegCov() {
		return tipoRegCov;
	}

	/**
	 * @param tipoRegCov the tipoRegCov to set
	 */
	public void setTipoRegCov(String tipoRegCov) {
		this.tipoRegCov = tipoRegCov;
	}

	/**
	 * @return the protocolo
	 */
	public byte[] getProtocolo() {
		return protocolo;
	}

	/**
	 * @param protocolo the protocolo to set
	 */
	public void setProtocolo(byte[] protocolo) {
		this.protocolo = protocolo;
	}

	/**
	 * @return the itemClausula
	 */
	public boolean isItemClausula() {
		return itemClausula;
	}

	/**
	 * @param itemClausula the itemClausula to set
	 */
	public void setItemClausula(boolean itemClausula) {
		this.itemClausula = itemClausula;
	}

	/**
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artigo == null) ? 0 : artigo.hashCode());
		result = prime * result + (blBloco ? 1231 : 1237);
		result = prime * result + (blCondo ? 1231 : 1237);
		result = prime * result + (blGeral ? 1231 : 1237);
		result = prime * result + (blUnida ? 1231 : 1237);
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + ((clausula == null) ? 0 : clausula.hashCode());
		result = prime * result + codigo;
		result = prime * result + codigoGeren;
		result = prime * result + condominio;
		result = prime * result + ((dataAtual == null) ? 0 : dataAtual.hashCode());
		result = prime * result + ((dataOcorrido == null) ? 0 : dataOcorrido.hashCode());
		result = prime * result + ((emailGeren == null) ? 0 : emailGeren.hashCode());
		result = prime * result + ((frase == null) ? 0 : frase.hashCode());
		result = prime * result + (itemClausula ? 1231 : 1237);
		result = prime * result + ((motivo == null) ? 0 : motivo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nomeCondominio == null) ? 0 : nomeCondominio.hashCode());
		result = prime * result + ((nomeGeren == null) ? 0 : nomeGeren.hashCode());
		result = prime * result + ((ocorrido == null) ? 0 : ocorrido.hashCode());
		result = prime * result + Arrays.hashCode(protocolo);
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		result = prime * result + tipo;
		result = prime * result + ((tipoRegCov == null) ? 0 : tipoRegCov.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
		result = prime * result + ((validaTexto == null) ? 0 : validaTexto.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		result = prime * result + ((valorExtenso == null) ? 0 : valorExtenso.hashCode());
		result = prime * result + ((vencimento == null) ? 0 : vencimento.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		intra_advert_mult other = (intra_advert_mult) obj;
		if (artigo == null) {
			if (other.artigo != null)
				return false;
		} else if (!artigo.equals(other.artigo))
			return false;
		if (blBloco != other.blBloco)
			return false;
		if (blCondo != other.blCondo)
			return false;
		if (blGeral != other.blGeral)
			return false;
		if (blUnida != other.blUnida)
			return false;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (clausula == null) {
			if (other.clausula != null)
				return false;
		} else if (!clausula.equals(other.clausula))
			return false;
		if (codigo != other.codigo)
			return false;
		if (codigoGeren != other.codigoGeren)
			return false;
		if (condominio != other.condominio)
			return false;
		if (dataAtual == null) {
			if (other.dataAtual != null)
				return false;
		} else if (!dataAtual.equals(other.dataAtual))
			return false;
		if (dataOcorrido == null) {
			if (other.dataOcorrido != null)
				return false;
		} else if (!dataOcorrido.equals(other.dataOcorrido))
			return false;
		if (emailGeren == null) {
			if (other.emailGeren != null)
				return false;
		} else if (!emailGeren.equals(other.emailGeren))
			return false;
		if (frase == null) {
			if (other.frase != null)
				return false;
		} else if (!frase.equals(other.frase))
			return false;
		if (itemClausula != other.itemClausula)
			return false;
		if (motivo == null) {
			if (other.motivo != null)
				return false;
		} else if (!motivo.equals(other.motivo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeCondominio == null) {
			if (other.nomeCondominio != null)
				return false;
		} else if (!nomeCondominio.equals(other.nomeCondominio))
			return false;
		if (nomeGeren == null) {
			if (other.nomeGeren != null)
				return false;
		} else if (!nomeGeren.equals(other.nomeGeren))
			return false;
		if (ocorrido == null) {
			if (other.ocorrido != null)
				return false;
		} else if (!ocorrido.equals(other.ocorrido))
			return false;
		if (!Arrays.equals(protocolo, other.protocolo))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		if (tipo != other.tipo)
			return false;
		if (tipoRegCov == null) {
			if (other.tipoRegCov != null)
				return false;
		} else if (!tipoRegCov.equals(other.tipoRegCov))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		if (validaTexto == null) {
			if (other.validaTexto != null)
				return false;
		} else if (!validaTexto.equals(other.validaTexto))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		if (valorExtenso == null) {
			if (other.valorExtenso != null)
				return false;
		} else if (!valorExtenso.equals(other.valorExtenso))
			return false;
		if (vencimento == null) {
			if (other.vencimento != null)
				return false;
		} else if (!vencimento.equals(other.vencimento))
			return false;
		return true;
	}


}
