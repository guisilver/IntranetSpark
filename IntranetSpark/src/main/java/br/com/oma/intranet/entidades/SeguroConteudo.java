package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class SeguroConteudo implements Serializable{

	private static final long serialVersionUID = 1L;

	// CONDOMINIO

	private int RECIBO;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date VENCTO;
	
	@Column(columnDefinition="smallint")
	private int servico;
	
	private double valor_recbto;
	
	@Column(columnDefinition="smallint")
	private int condominio;
	
	private String bloco;
	
	private String unidade;
	
	@Column(columnDefinition="smallint")
	private int classificacao;
	
	private String nome;
	
	private String logradouro;
	
	private String endereco;
	
	private String nro_end;
	
	private String complemento;
	
	private String bairro;
	
	private double cep;
	
	private String cidade;
	
	private String estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	private String data_baixa;

	private String nomeCondominio;

	// LOCAÇÃO
	
	private int numeroLoc;
	private int locador_princLoc;
	private String locador;
	private int inqui_princLoc;
	private String inquilino;
	private int codigo;
	private String logradouroLoc;
	private String enderecoLoc;
	private String nro_endLoc;
	private String complementoLoc;
	private String bairroLoc;
	private double cepLoc;
	private String cidadeLoc;
	private String estadoLoc;
	
	
	public int getRECIBO() {
		return RECIBO;
	}
	public void setRECIBO(int rECIBO) {
		RECIBO = rECIBO;
	}
	public Date getVENCTO() {
		return VENCTO;
	}
	public void setVENCTO(Date vENCTO) {
		VENCTO = vENCTO;
	}
	public int getServico() {
		return servico;
	}
	public void setServico(int servico) {
		this.servico = servico;
	}
	public double getValor_recbto() {
		return valor_recbto;
	}
	public void setValor_recbto(double valor_recbto) {
		this.valor_recbto = valor_recbto;
	}
	public int getCondominio() {
		return condominio;
	}
	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}
	public String getBloco() {
		return bloco;
	}
	public void setBloco(String bloco) {
		this.bloco = bloco;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public int getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNro_end() {
		return nro_end;
	}
	public void setNro_end(String nro_end) {
		this.nro_end = nro_end;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public double getCep() {
		return cep;
	}
	public void setCep(double cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getData_baixa() {
		return data_baixa;
	}
	public void setData_baixa(String data_baixa) {
		this.data_baixa = data_baixa;
	}
	public String getNomeCondominio() {
		return nomeCondominio;
	}
	public void setNomeCondominio(String nomeCondominio) {
		this.nomeCondominio = nomeCondominio;
	}
	public int getNumeroLoc() {
		return numeroLoc;
	}
	public void setNumeroLoc(int numeroLoc) {
		this.numeroLoc = numeroLoc;
	}
	public int getLocador_princLoc() {
		return locador_princLoc;
	}
	public void setLocador_princLoc(int locador_princLoc) {
		this.locador_princLoc = locador_princLoc;
	}
	public String getLocador() {
		return locador;
	}
	public void setLocador(String locador) {
		this.locador = locador;
	}
	public int getInqui_princLoc() {
		return inqui_princLoc;
	}
	public void setInqui_princLoc(int inqui_princLoc) {
		this.inqui_princLoc = inqui_princLoc;
	}
	public String getInquilino() {
		return inquilino;
	}
	public void setInquilino(String inquilino) {
		this.inquilino = inquilino;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getLogradouroLoc() {
		return logradouroLoc;
	}
	public void setLogradouroLoc(String logradouroLoc) {
		this.logradouroLoc = logradouroLoc;
	}
	public String getEnderecoLoc() {
		return enderecoLoc;
	}
	public void setEnderecoLoc(String enderecoLoc) {
		this.enderecoLoc = enderecoLoc;
	}
	public String getNro_endLoc() {
		return nro_endLoc;
	}
	public void setNro_endLoc(String nro_endLoc) {
		this.nro_endLoc = nro_endLoc;
	}
	public String getComplementoLoc() {
		return complementoLoc;
	}
	public void setComplementoLoc(String complementoLoc) {
		this.complementoLoc = complementoLoc;
	}
	public String getBairroLoc() {
		return bairroLoc;
	}
	public void setBairroLoc(String bairroLoc) {
		this.bairroLoc = bairroLoc;
	}
	public double getCepLoc() {
		return cepLoc;
	}
	public void setCepLoc(double cepLoc) {
		this.cepLoc = cepLoc;
	}
	public String getCidadeLoc() {
		return cidadeLoc;
	}
	public void setCidadeLoc(String cidadeLoc) {
		this.cidadeLoc = cidadeLoc;
	}
	public String getEstadoLoc() {
		return estadoLoc;
	}
	public void setEstadoLoc(String estadoLoc) {
		this.estadoLoc = estadoLoc;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + RECIBO;
		result = prime * result + ((VENCTO == null) ? 0 : VENCTO.hashCode());
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((bairroLoc == null) ? 0 : bairroLoc.hashCode());
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cep);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(cepLoc);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((cidadeLoc == null) ? 0 : cidadeLoc.hashCode());
		result = prime * result + classificacao;
		result = prime * result + codigo;
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((complementoLoc == null) ? 0 : complementoLoc.hashCode());
		result = prime * result + condominio;
		result = prime * result + ((data_baixa == null) ? 0 : data_baixa.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((enderecoLoc == null) ? 0 : enderecoLoc.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((estadoLoc == null) ? 0 : estadoLoc.hashCode());
		result = prime * result + inqui_princLoc;
		result = prime * result + ((inquilino == null) ? 0 : inquilino.hashCode());
		result = prime * result + ((locador == null) ? 0 : locador.hashCode());
		result = prime * result + locador_princLoc;
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((logradouroLoc == null) ? 0 : logradouroLoc.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nomeCondominio == null) ? 0 : nomeCondominio.hashCode());
		result = prime * result + ((nro_end == null) ? 0 : nro_end.hashCode());
		result = prime * result + ((nro_endLoc == null) ? 0 : nro_endLoc.hashCode());
		result = prime * result + numeroLoc;
		result = prime * result + servico;
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
		temp = Double.doubleToLongBits(valor_recbto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		SeguroConteudo other = (SeguroConteudo) obj;
		if (RECIBO != other.RECIBO)
			return false;
		if (VENCTO == null) {
			if (other.VENCTO != null)
				return false;
		} else if (!VENCTO.equals(other.VENCTO))
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (bairroLoc == null) {
			if (other.bairroLoc != null)
				return false;
		} else if (!bairroLoc.equals(other.bairroLoc))
			return false;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (Double.doubleToLongBits(cep) != Double.doubleToLongBits(other.cep))
			return false;
		if (Double.doubleToLongBits(cepLoc) != Double.doubleToLongBits(other.cepLoc))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (cidadeLoc == null) {
			if (other.cidadeLoc != null)
				return false;
		} else if (!cidadeLoc.equals(other.cidadeLoc))
			return false;
		if (classificacao != other.classificacao)
			return false;
		if (codigo != other.codigo)
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (complementoLoc == null) {
			if (other.complementoLoc != null)
				return false;
		} else if (!complementoLoc.equals(other.complementoLoc))
			return false;
		if (condominio != other.condominio)
			return false;
		if (data_baixa == null) {
			if (other.data_baixa != null)
				return false;
		} else if (!data_baixa.equals(other.data_baixa))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (enderecoLoc == null) {
			if (other.enderecoLoc != null)
				return false;
		} else if (!enderecoLoc.equals(other.enderecoLoc))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (estadoLoc == null) {
			if (other.estadoLoc != null)
				return false;
		} else if (!estadoLoc.equals(other.estadoLoc))
			return false;
		if (inqui_princLoc != other.inqui_princLoc)
			return false;
		if (inquilino == null) {
			if (other.inquilino != null)
				return false;
		} else if (!inquilino.equals(other.inquilino))
			return false;
		if (locador == null) {
			if (other.locador != null)
				return false;
		} else if (!locador.equals(other.locador))
			return false;
		if (locador_princLoc != other.locador_princLoc)
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (logradouroLoc == null) {
			if (other.logradouroLoc != null)
				return false;
		} else if (!logradouroLoc.equals(other.logradouroLoc))
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
		if (nro_end == null) {
			if (other.nro_end != null)
				return false;
		} else if (!nro_end.equals(other.nro_end))
			return false;
		if (nro_endLoc == null) {
			if (other.nro_endLoc != null)
				return false;
		} else if (!nro_endLoc.equals(other.nro_endLoc))
			return false;
		if (numeroLoc != other.numeroLoc)
			return false;
		if (servico != other.servico)
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		if (Double.doubleToLongBits(valor_recbto) != Double.doubleToLongBits(other.valor_recbto))
			return false;
		return true;
	}
	
	
}