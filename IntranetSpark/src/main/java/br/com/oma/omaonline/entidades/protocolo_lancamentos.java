package br.com.oma.omaonline.entidades;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="omaonline.dbo")
public class protocolo_lancamentos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "int")
	private int cdLancto;
	@Column(columnDefinition = "bigint")
	private long idImagem;
	@Column(columnDefinition = "datetime")
	private Date vencimento;
	@Column(columnDefinition = "datetime")
	private Date dataFeito;
	@Column(columnDefinition = "datetime")
	private Date dataRecebimento;
	@Column(columnDefinition = "numeric(19,2)")
	private BigDecimal valor;
	@Column(columnDefinition = "bigint")
	private long nf;
	@Column(columnDefinition = "varchar(100)")
	private String empresa;
	@Column(columnDefinition = "varchar(100)")
	private String feitoPor;
	@Column(columnDefinition = "varchar(100)")
	private String recebidoPor;
	@Column(name = "situacao", columnDefinition = "bit")
	private boolean situacao;

	public protocolo_lancamentos() {

	}

	public protocolo_lancamentos(int cdLancto, long idImagem, Date vencimento,
			Date dataFeito, Date dataRecebimento, BigDecimal valor, long nf,
			String empresa, String feitoPor, String recebidoPor,
			boolean situacao) {
		this.cdLancto = cdLancto;
		this.idImagem = idImagem;
		this.vencimento = vencimento;
		this.dataFeito = dataFeito;
		this.dataRecebimento = dataRecebimento;
		this.valor = valor;
		this.nf = nf;
		this.empresa = empresa;
		this.feitoPor = feitoPor;
		this.recebidoPor = recebidoPor;
		this.situacao = situacao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCdLancto() {
		return cdLancto;
	}

	public void setCdLancto(int cdLancto) {
		this.cdLancto = cdLancto;
	}

	public long getIdImagem() {
		return idImagem;
	}

	public void setIdImagem(long idImagem) {
		this.idImagem = idImagem;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Date getDataFeito() {
		return dataFeito;
	}

	public void setDataFeito(Date dataFeito) {
		this.dataFeito = dataFeito;
	}

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public long getNf() {
		return nf;
	}

	public void setNf(long nf) {
		this.nf = nf;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getFeitoPor() {
		return feitoPor;
	}

	public void setFeitoPor(String feitoPor) {
		this.feitoPor = feitoPor;
	}

	public String getRecebidoPor() {
		return recebidoPor;
	}

	public void setRecebidoPor(String recebidoPor) {
		this.recebidoPor = recebidoPor;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return String.format("%s[codigo=%d]", getClass().getSimpleName(),
				getCodigo());
	}

}
