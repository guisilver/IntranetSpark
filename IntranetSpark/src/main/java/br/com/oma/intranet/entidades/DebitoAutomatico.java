package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

public class DebitoAutomatico implements Serializable, Comparable<DebitoAutomatico> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9213834180689081582L;
	private int id_contrato;
	private String nro_identificacao;
	private String codDebAutom;
	private int nrolancto;
	private int nroPagto;
	private int idFatura;
	private Date vencimento;
	private Date dataPago;
	private String estimado;
	private short condominio;
	private int conta;
	private String historico;
	private String credor;
	private double valor;
	private String consumo;
	private double consumoDouble;
	private int mes;
	private int ano;
	private int id_servico;
	private UnidadeMedida um;
	private String complemento;
	private boolean possuiImg;
	private boolean aguardandoBaixa;
	private boolean pago;
	private String obs;
	private String obsFM;
	private String cndCodNome;
	private int id_catalogo;

	SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
	private String vencimentoTxt, valorTxt;

	public int getId_contrato() {
		return id_contrato;
	}

	public void setId_contrato(int id_contrato) {
		this.id_contrato = id_contrato;
	}

	public String getNro_identificacao() {
		return nro_identificacao;
	}

	public void setNro_identificacao(String nro_identificacao) {
		this.nro_identificacao = nro_identificacao;
	}

	public String getCodDebAutom() {
		return codDebAutom;
	}

	public void setCodDebAutom(String codDebAutom) {
		this.codDebAutom = codDebAutom;
	}

	public int getNrolancto() {
		return nrolancto;
	}

	public void setNrolancto(int nrolancto) {
		this.nrolancto = nrolancto;
	}

	public int getNroPagto() {
		return nroPagto;
	}

	public void setNroPagto(int nroPagto) {
		this.nroPagto = nroPagto;
	}

	public int getIdFatura() {
		return idFatura;
	}

	public void setIdFatura(int idFatura) {
		this.idFatura = idFatura;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Date getDataPago() {
		return dataPago;
	}

	public void setDataPago(Date dataPago) {
		this.dataPago = dataPago;
	}

	public String getEstimado() {
		return estimado;
	}

	public void setEstimado(String estimado) {
		this.estimado = estimado;
	}

	public short getCondominio() {
		return condominio;
	}

	public void setCondominio(short condominio) {
		this.condominio = condominio;
	}

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getCredor() {
		return credor;
	}

	public void setCredor(String credor) {
		this.credor = credor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getConsumo() {
		return consumo;
	}

	public void setConsumo(String consumo) {
		consumo = consumo.replace(",", ".");
		this.consumo = consumo;
	}

	public double getConsumoDouble() {
		return consumoDouble;
	}

	public void setConsumoDouble(double consumoDouble) {
		this.consumoDouble = consumoDouble;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getId_servico() {
		return id_servico;
	}

	public void setId_servico(int id_servico) {
		this.id_servico = id_servico;
	}

	public UnidadeMedida getUm() {
		return um;
	}

	public void setUm(UnidadeMedida um) {
		this.um = um;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public boolean isPossuiImg() {
		return possuiImg;
	}

	public void setPossuiImg(boolean possuiImg) {
		this.possuiImg = possuiImg;
	}

	public boolean isAguardandoBaixa() {
		return aguardandoBaixa;
	}

	public void setAguardandoBaixa(boolean aguardandoBaixa) {
		this.aguardandoBaixa = aguardandoBaixa;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getObsFM() {
		return obsFM;
	}

	public void setObsFM(String obsFM) {
		this.obsFM = obsFM;
	}

	public String getCndCodNome() {
		return cndCodNome;
	}

	public void setCndCodNome(String cndCodNome) {
		this.cndCodNome = cndCodNome;
	}

	public int getId_catalogo() {
		return id_catalogo;
	}

	public void setId_catalogo(int id_catalogo) {
		this.id_catalogo = id_catalogo;
	}

	public String getVencimentoTxt() {
		if (this.vencimento != null) {
			this.vencimentoTxt = this.sf.format(this.vencimento);
		}
		return vencimentoTxt;
	}

	public void setVencimentoTxt(String vencimentoTxt) {
		this.vencimentoTxt = vencimentoTxt;
	}

	public String getValorTxt() {
		if (this.valor > 0) {
			this.valorTxt = this.df.format(this.valor);
		}
		return valorTxt;
	}

	public void setValorTxt(String valorTxt) {
		this.valorTxt = valorTxt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (aguardandoBaixa ? 1231 : 1237);
		result = prime * result + ano;
		result = prime * result + ((cndCodNome == null) ? 0 : cndCodNome.hashCode());
		result = prime * result + ((codDebAutom == null) ? 0 : codDebAutom.hashCode());
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + condominio;
		result = prime * result + ((consumo == null) ? 0 : consumo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(consumoDouble);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + conta;
		result = prime * result + ((credor == null) ? 0 : credor.hashCode());
		result = prime * result + ((dataPago == null) ? 0 : dataPago.hashCode());
		result = prime * result + ((estimado == null) ? 0 : estimado.hashCode());
		result = prime * result + ((historico == null) ? 0 : historico.hashCode());
		result = prime * result + idFatura;
		result = prime * result + id_catalogo;
		result = prime * result + id_contrato;
		result = prime * result + id_servico;
		result = prime * result + mes;
		result = prime * result + nroPagto;
		result = prime * result + ((nro_identificacao == null) ? 0 : nro_identificacao.hashCode());
		result = prime * result + nrolancto;
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + ((obsFM == null) ? 0 : obsFM.hashCode());
		result = prime * result + (pago ? 1231 : 1237);
		result = prime * result + (possuiImg ? 1231 : 1237);
		result = prime * result + ((um == null) ? 0 : um.hashCode());
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
		DebitoAutomatico other = (DebitoAutomatico) obj;
		if (aguardandoBaixa != other.aguardandoBaixa)
			return false;
		if (ano != other.ano)
			return false;
		if (cndCodNome == null) {
			if (other.cndCodNome != null)
				return false;
		} else if (!cndCodNome.equals(other.cndCodNome))
			return false;
		if (codDebAutom == null) {
			if (other.codDebAutom != null)
				return false;
		} else if (!codDebAutom.equals(other.codDebAutom))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (condominio != other.condominio)
			return false;
		if (consumo == null) {
			if (other.consumo != null)
				return false;
		} else if (!consumo.equals(other.consumo))
			return false;
		if (Double.doubleToLongBits(consumoDouble) != Double.doubleToLongBits(other.consumoDouble))
			return false;
		if (conta != other.conta)
			return false;
		if (credor == null) {
			if (other.credor != null)
				return false;
		} else if (!credor.equals(other.credor))
			return false;
		if (dataPago == null) {
			if (other.dataPago != null)
				return false;
		} else if (!dataPago.equals(other.dataPago))
			return false;
		if (estimado == null) {
			if (other.estimado != null)
				return false;
		} else if (!estimado.equals(other.estimado))
			return false;
		if (historico == null) {
			if (other.historico != null)
				return false;
		} else if (!historico.equals(other.historico))
			return false;
		if (idFatura != other.idFatura)
			return false;
		if (id_catalogo != other.id_catalogo)
			return false;
		if (id_contrato != other.id_contrato)
			return false;
		if (id_servico != other.id_servico)
			return false;
		if (mes != other.mes)
			return false;
		if (nroPagto != other.nroPagto)
			return false;
		if (nro_identificacao == null) {
			if (other.nro_identificacao != null)
				return false;
		} else if (!nro_identificacao.equals(other.nro_identificacao))
			return false;
		if (nrolancto != other.nrolancto)
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (obsFM == null) {
			if (other.obsFM != null)
				return false;
		} else if (!obsFM.equals(other.obsFM))
			return false;
		if (pago != other.pago)
			return false;
		if (possuiImg != other.possuiImg)
			return false;
		if (um == null) {
			if (other.um != null)
				return false;
		} else if (!um.equals(other.um))
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

	@Override
	public int compareTo(DebitoAutomatico o) {
		if (!(o instanceof DebitoAutomatico))
			throw new ClassCastException();
		DebitoAutomatico e = (DebitoAutomatico) o;
		return new DateTime(this.vencimento).compareTo(new DateTime(e.getVencimento()));
	}

}