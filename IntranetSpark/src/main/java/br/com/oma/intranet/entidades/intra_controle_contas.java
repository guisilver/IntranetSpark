package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class intra_controle_contas implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(columnDefinition="bigint")
	private int codigo;
	
	@Column(columnDefinition="smallint")
	private int condominio;
	
	@Column(columnDefinition="int")
	private int vencimento;
	
	@Column(columnDefinition="bigint")
	private int tipo;
	
	@Column(columnDefinition="varchar(20)")
	private String historico;
	
	@Column(columnDefinition="varchar(max)")
	private String despesa;
	
	@Column(columnDefinition="varchar(MAX)")
	private String obs;
	
	@Column(columnDefinition="int")
	private int status_jan,status_fev,status_mar,status_abr,status_mai,status_jun,status_jul,status_ago,status_set,status_out,status_nov,status_dez;
	
	@Column(columnDefinition="varchar(30)")
	private String mes1,mes2,mes3,mes4,mes5,mes6,mes7,mes8,mes9,mes10,mes11,mes12;
	
	@Column(columnDefinition="int")
	private int cod_geren;
	
	@Column(columnDefinition="int")
	private int ano;
	
	@Column(columnDefinition="int")
	private int mes_atual;
	
	@Column(name="nome_gerente",columnDefinition="varchar(50)")
	private String nomeGerente;
	
	@Transient
	private Date vencimentoData;

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

	public int getVencimento() {
		return vencimento;
	}

	public void setVencimento(int vencimento) {
		this.vencimento = vencimento;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getDespesa() {
		return despesa;
	}

	public void setDespesa(String despesa) {
		this.despesa = despesa;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public int getStatus_jan() {
		return status_jan;
	}

	public void setStatus_jan(int status_jan) {
		this.status_jan = status_jan;
	}

	public int getStatus_fev() {
		return status_fev;
	}

	public void setStatus_fev(int status_fev) {
		this.status_fev = status_fev;
	}

	public int getStatus_mar() {
		return status_mar;
	}

	public void setStatus_mar(int status_mar) {
		this.status_mar = status_mar;
	}

	public int getStatus_abr() {
		return status_abr;
	}

	public void setStatus_abr(int status_abr) {
		this.status_abr = status_abr;
	}

	public int getStatus_mai() {
		return status_mai;
	}

	public void setStatus_mai(int status_mai) {
		this.status_mai = status_mai;
	}

	public int getStatus_jun() {
		return status_jun;
	}

	public void setStatus_jun(int status_jun) {
		this.status_jun = status_jun;
	}

	public int getStatus_jul() {
		return status_jul;
	}

	public void setStatus_jul(int status_jul) {
		this.status_jul = status_jul;
	}

	public int getStatus_ago() {
		return status_ago;
	}

	public void setStatus_ago(int status_ago) {
		this.status_ago = status_ago;
	}

	public int getStatus_set() {
		return status_set;
	}

	public void setStatus_set(int status_set) {
		this.status_set = status_set;
	}

	public int getStatus_out() {
		return status_out;
	}

	public void setStatus_out(int status_out) {
		this.status_out = status_out;
	}

	public int getStatus_nov() {
		return status_nov;
	}

	public void setStatus_nov(int status_nov) {
		this.status_nov = status_nov;
	}

	public int getStatus_dez() {
		return status_dez;
	}

	public void setStatus_dez(int status_dez) {
		this.status_dez = status_dez;
	}

	public String getMes1() {
		return mes1;
	}

	public void setMes1(String mes1) {
		this.mes1 = mes1;
	}

	public String getMes2() {
		return mes2;
	}

	public void setMes2(String mes2) {
		this.mes2 = mes2;
	}

	public String getMes3() {
		return mes3;
	}

	public void setMes3(String mes3) {
		this.mes3 = mes3;
	}

	public String getMes4() {
		return mes4;
	}

	public void setMes4(String mes4) {
		this.mes4 = mes4;
	}

	public String getMes5() {
		return mes5;
	}

	public void setMes5(String mes5) {
		this.mes5 = mes5;
	}

	public String getMes6() {
		return mes6;
	}

	public void setMes6(String mes6) {
		this.mes6 = mes6;
	}

	public String getMes7() {
		return mes7;
	}

	public void setMes7(String mes7) {
		this.mes7 = mes7;
	}

	public String getMes8() {
		return mes8;
	}

	public void setMes8(String mes8) {
		this.mes8 = mes8;
	}

	public String getMes9() {
		return mes9;
	}

	public void setMes9(String mes9) {
		this.mes9 = mes9;
	}

	public String getMes10() {
		return mes10;
	}

	public void setMes10(String mes10) {
		this.mes10 = mes10;
	}

	public String getMes11() {
		return mes11;
	}

	public void setMes11(String mes11) {
		this.mes11 = mes11;
	}

	public String getMes12() {
		return mes12;
	}

	public void setMes12(String mes12) {
		this.mes12 = mes12;
	}

	public int getCod_geren() {
		return cod_geren;
	}

	public void setCod_geren(int cod_geren) {
		this.cod_geren = cod_geren;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getMes_atual() {
		return mes_atual;
	}

	public void setMes_atual(int mes_atual) {
		this.mes_atual = mes_atual;
	}

	public String getNomeGerente() {
		return nomeGerente;
	}

	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}

	public Date getVencimentoData() {
		return vencimentoData;
	}

	public void setVencimentoData(Date vencimentoData) {
		this.vencimentoData = vencimentoData;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + cod_geren;
		result = prime * result + codigo;
		result = prime * result + condominio;
		result = prime * result + ((despesa == null) ? 0 : despesa.hashCode());
		result = prime * result
				+ ((historico == null) ? 0 : historico.hashCode());
		result = prime * result + ((mes1 == null) ? 0 : mes1.hashCode());
		result = prime * result + ((mes10 == null) ? 0 : mes10.hashCode());
		result = prime * result + ((mes11 == null) ? 0 : mes11.hashCode());
		result = prime * result + ((mes12 == null) ? 0 : mes12.hashCode());
		result = prime * result + ((mes2 == null) ? 0 : mes2.hashCode());
		result = prime * result + ((mes3 == null) ? 0 : mes3.hashCode());
		result = prime * result + ((mes4 == null) ? 0 : mes4.hashCode());
		result = prime * result + ((mes5 == null) ? 0 : mes5.hashCode());
		result = prime * result + ((mes6 == null) ? 0 : mes6.hashCode());
		result = prime * result + ((mes7 == null) ? 0 : mes7.hashCode());
		result = prime * result + ((mes8 == null) ? 0 : mes8.hashCode());
		result = prime * result + ((mes9 == null) ? 0 : mes9.hashCode());
		result = prime * result + mes_atual;
		result = prime * result
				+ ((nomeGerente == null) ? 0 : nomeGerente.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + status_abr;
		result = prime * result + status_ago;
		result = prime * result + status_dez;
		result = prime * result + status_fev;
		result = prime * result + status_jan;
		result = prime * result + status_jul;
		result = prime * result + status_jun;
		result = prime * result + status_mai;
		result = prime * result + status_mar;
		result = prime * result + status_nov;
		result = prime * result + status_out;
		result = prime * result + status_set;
		result = prime * result + tipo;
		result = prime * result + vencimento;
		result = prime * result
				+ ((vencimentoData == null) ? 0 : vencimentoData.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof intra_controle_contas)) {
			return false;
		}
		intra_controle_contas other = (intra_controle_contas) obj;
		if (ano != other.ano) {
			return false;
		}
		if (cod_geren != other.cod_geren) {
			return false;
		}
		if (codigo != other.codigo) {
			return false;
		}
		if (condominio != other.condominio) {
			return false;
		}
		if (despesa == null) {
			if (other.despesa != null) {
				return false;
			}
		} else if (!despesa.equals(other.despesa)) {
			return false;
		}
		if (historico == null) {
			if (other.historico != null) {
				return false;
			}
		} else if (!historico.equals(other.historico)) {
			return false;
		}
		if (mes1 == null) {
			if (other.mes1 != null) {
				return false;
			}
		} else if (!mes1.equals(other.mes1)) {
			return false;
		}
		if (mes10 == null) {
			if (other.mes10 != null) {
				return false;
			}
		} else if (!mes10.equals(other.mes10)) {
			return false;
		}
		if (mes11 == null) {
			if (other.mes11 != null) {
				return false;
			}
		} else if (!mes11.equals(other.mes11)) {
			return false;
		}
		if (mes12 == null) {
			if (other.mes12 != null) {
				return false;
			}
		} else if (!mes12.equals(other.mes12)) {
			return false;
		}
		if (mes2 == null) {
			if (other.mes2 != null) {
				return false;
			}
		} else if (!mes2.equals(other.mes2)) {
			return false;
		}
		if (mes3 == null) {
			if (other.mes3 != null) {
				return false;
			}
		} else if (!mes3.equals(other.mes3)) {
			return false;
		}
		if (mes4 == null) {
			if (other.mes4 != null) {
				return false;
			}
		} else if (!mes4.equals(other.mes4)) {
			return false;
		}
		if (mes5 == null) {
			if (other.mes5 != null) {
				return false;
			}
		} else if (!mes5.equals(other.mes5)) {
			return false;
		}
		if (mes6 == null) {
			if (other.mes6 != null) {
				return false;
			}
		} else if (!mes6.equals(other.mes6)) {
			return false;
		}
		if (mes7 == null) {
			if (other.mes7 != null) {
				return false;
			}
		} else if (!mes7.equals(other.mes7)) {
			return false;
		}
		if (mes8 == null) {
			if (other.mes8 != null) {
				return false;
			}
		} else if (!mes8.equals(other.mes8)) {
			return false;
		}
		if (mes9 == null) {
			if (other.mes9 != null) {
				return false;
			}
		} else if (!mes9.equals(other.mes9)) {
			return false;
		}
		if (mes_atual != other.mes_atual) {
			return false;
		}
		if (nomeGerente == null) {
			if (other.nomeGerente != null) {
				return false;
			}
		} else if (!nomeGerente.equals(other.nomeGerente)) {
			return false;
		}
		if (obs == null) {
			if (other.obs != null) {
				return false;
			}
		} else if (!obs.equals(other.obs)) {
			return false;
		}
		if (status_abr != other.status_abr) {
			return false;
		}
		if (status_ago != other.status_ago) {
			return false;
		}
		if (status_dez != other.status_dez) {
			return false;
		}
		if (status_fev != other.status_fev) {
			return false;
		}
		if (status_jan != other.status_jan) {
			return false;
		}
		if (status_jul != other.status_jul) {
			return false;
		}
		if (status_jun != other.status_jun) {
			return false;
		}
		if (status_mai != other.status_mai) {
			return false;
		}
		if (status_mar != other.status_mar) {
			return false;
		}
		if (status_nov != other.status_nov) {
			return false;
		}
		if (status_out != other.status_out) {
			return false;
		}
		if (status_set != other.status_set) {
			return false;
		}
		if (tipo != other.tipo) {
			return false;
		}
		if (vencimento != other.vencimento) {
			return false;
		}
		if (vencimentoData == null) {
			if (other.vencimentoData != null) {
				return false;
			}
		} else if (!vencimentoData.equals(other.vencimentoData)) {
			return false;
		}
		return true;
	}
	
	
}
