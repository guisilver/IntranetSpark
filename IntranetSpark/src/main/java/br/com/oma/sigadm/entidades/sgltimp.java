package br.com.oma.sigadm.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="omaonline.dbo")
public class sgltimp implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition="int")
	private int nrolancto;
	
	@Column(columnDefinition="datetime")
	private Date data_inclusao;
    
	@Column(columnDefinition="int")
	private int codigo;
	
	@Column(columnDefinition="char(4)")
    private String bloco;
    
    @Column(columnDefinition="datetime")
    private Date vencimento;
    
	@Column(columnDefinition="numeric(12,2)")
    private double valor;
    
	@Column(columnDefinition="varchar(260)")
    private String historico;

	@Column(columnDefinition="smallint")
    private int cta_bancaria;
    
	@Column(columnDefinition="varchar(12)")
    private String credor;
    
    @Column(columnDefinition="datetime")
    private Date data_base;
    
	@Column(columnDefinition="varchar(50)")
    private String documento;
    
	@Column(columnDefinition="numeric(12,2)")
    private double total_retencao;
    
	@Column(columnDefinition="smallint")
    private int sistema;
    
    @Column(columnDefinition="int")
    private int contrato;
    
    @Column(columnDefinition="int")
    private int imovel;
    
	@Column(columnDefinition="char(1)")
    private String repassar;
    
	@Column(columnDefinition="char(1)")
    private String div_propriet;
    
	@Column(columnDefinition="char(1)")
    private String cpmf;
    
	@Column(columnDefinition="varchar(50)")
    private String favorecido;
    
    @Column(columnDefinition="datetime")
    private Date data_alteracao;
    
    @Column(columnDefinition="int")
    private int nrolancto_aux;

	public int getNrolancto() {
		return nrolancto;
	}

	public void setNrolancto(int nrolancto) {
		this.nrolancto = nrolancto;
	}

	public Date getData_inclusao() {
		return data_inclusao;
	}

	public void setData_inclusao(Date data_inclusao) {
		this.data_inclusao = data_inclusao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public int getCta_bancaria() {
		return cta_bancaria;
	}

	public void setCta_bancaria(int cta_bancaria) {
		this.cta_bancaria = cta_bancaria;
	}

	public String getCredor() {
		return credor;
	}

	public void setCredor(String credor) {
		this.credor = credor;
	}

	public Date getData_base() {
		return data_base;
	}

	public void setData_base(Date data_base) {
		this.data_base = data_base;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public double getTotal_retencao() {
		return total_retencao;
	}

	public void setTotal_retencao(double total_retencao) {
		this.total_retencao = total_retencao;
	}

	public int getSistema() {
		return sistema;
	}

	public void setSistema(int sistema) {
		this.sistema = sistema;
	}

	public int getContrato() {
		return contrato;
	}

	public void setContrato(int contrato) {
		this.contrato = contrato;
	}

	public int getImovel() {
		return imovel;
	}

	public void setImovel(int imovel) {
		this.imovel = imovel;
	}

	public String getRepassar() {
		return repassar;
	}

	public void setRepassar(String repassar) {
		this.repassar = repassar;
	}

	public String getDiv_propriet() {
		return div_propriet;
	}

	public void setDiv_propriet(String div_propriet) {
		this.div_propriet = div_propriet;
	}

	public String getCpmf() {
		return cpmf;
	}

	public void setCpmf(String cpmf) {
		this.cpmf = cpmf;
	}

	public String getFavorecido() {
		return favorecido;
	}

	public void setFavorecido(String favorecido) {
		this.favorecido = favorecido;
	}

	public Date getData_alteracao() {
		return data_alteracao;
	}

	public void setData_alteracao(Date data_alteracao) {
		this.data_alteracao = data_alteracao;
	}

	public int getNrolancto_aux() {
		return nrolancto_aux;
	}

	public void setNrolancto_aux(int nrolancto_aux) {
		this.nrolancto_aux = nrolancto_aux;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + codigo;
		result = prime * result + contrato;
		result = prime * result + ((cpmf == null) ? 0 : cpmf.hashCode());
		result = prime * result + ((credor == null) ? 0 : credor.hashCode());
		result = prime * result + cta_bancaria;
		result = prime * result + ((data_alteracao == null) ? 0 : data_alteracao.hashCode());
		result = prime * result + ((data_base == null) ? 0 : data_base.hashCode());
		result = prime * result + ((data_inclusao == null) ? 0 : data_inclusao.hashCode());
		result = prime * result + ((div_propriet == null) ? 0 : div_propriet.hashCode());
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		result = prime * result + ((favorecido == null) ? 0 : favorecido.hashCode());
		result = prime * result + ((historico == null) ? 0 : historico.hashCode());
		result = prime * result + imovel;
		result = prime * result + nrolancto;
		result = prime * result + nrolancto_aux;
		result = prime * result + ((repassar == null) ? 0 : repassar.hashCode());
		result = prime * result + sistema;
		long temp;
		temp = Double.doubleToLongBits(total_retencao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((vencimento == null) ? 0 : vencimento.hashCode());
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
		sgltimp other = (sgltimp) obj;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (codigo != other.codigo)
			return false;
		if (contrato != other.contrato)
			return false;
		if (cpmf == null) {
			if (other.cpmf != null)
				return false;
		} else if (!cpmf.equals(other.cpmf))
			return false;
		if (credor == null) {
			if (other.credor != null)
				return false;
		} else if (!credor.equals(other.credor))
			return false;
		if (cta_bancaria != other.cta_bancaria)
			return false;
		if (data_alteracao == null) {
			if (other.data_alteracao != null)
				return false;
		} else if (!data_alteracao.equals(other.data_alteracao))
			return false;
		if (data_base == null) {
			if (other.data_base != null)
				return false;
		} else if (!data_base.equals(other.data_base))
			return false;
		if (data_inclusao == null) {
			if (other.data_inclusao != null)
				return false;
		} else if (!data_inclusao.equals(other.data_inclusao))
			return false;
		if (div_propriet == null) {
			if (other.div_propriet != null)
				return false;
		} else if (!div_propriet.equals(other.div_propriet))
			return false;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		if (favorecido == null) {
			if (other.favorecido != null)
				return false;
		} else if (!favorecido.equals(other.favorecido))
			return false;
		if (historico == null) {
			if (other.historico != null)
				return false;
		} else if (!historico.equals(other.historico))
			return false;
		if (imovel != other.imovel)
			return false;
		if (nrolancto != other.nrolancto)
			return false;
		if (nrolancto_aux != other.nrolancto_aux)
			return false;
		if (repassar == null) {
			if (other.repassar != null)
				return false;
		} else if (!repassar.equals(other.repassar))
			return false;
		if (sistema != other.sistema)
			return false;
		if (Double.doubleToLongBits(total_retencao) != Double.doubleToLongBits(other.total_retencao))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		if (vencimento == null) {
			if (other.vencimento != null)
				return false;
		} else if (!vencimento.equals(other.vencimento))
			return false;
		return true;
	}
    
	
}
