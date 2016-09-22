package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class intra_cptcondo2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2964471392351921082L;
	@Id
	@GeneratedValue
	private int id;
	@Column(columnDefinition = "varchar(150)")
	private String nome;
	@Column(columnDefinition = "datetime")
	private Date dataCadastro;
	@Column(columnDefinition = "varchar(100)")
	private String endereco;
	@Column(columnDefinition = "varchar(50)")
	private String bairro;
	@Column(columnDefinition = "varchar(50)")
	private String cidade;
	private int estado;
	@Column(columnDefinition = "varchar(10)")
	private String cep;
	@Column(columnDefinition = "varchar(30)")
	private String regiao;
	@Column(columnDefinition = "varchar(20)")
	private String cnpj;
	@Column(columnDefinition = "varchar(20)")
	private String telPortaria;
	private int construtora;
	@Column(columnDefinition = "varchar(100)")
	private String nomeConstrutora;
	@Column(columnDefinition = "varchar(75)")
	private String nomeEngenheiro;
	@Column(columnDefinition = "datetime")
	private Date dataEntrega;
	@Column(columnDefinition = "varchar(20)")
	private String classificacao;
	private int bloco;
	private int andar;
	private int apartamento;
	private int aptosPorAndar;
	private int funcCondominio;
	private int funcTerceirizado;
	private int elevador;
	private boolean piscina;
	private boolean playground;
	private boolean sauna;
	private boolean jogos;
	private boolean gourmet;
	private boolean quadra;
	private boolean churrasqueira;
	private boolean salaoFestas;
	private boolean ginastica;
	private boolean jardim;
	@Column(columnDefinition = "varchar(15)")
	private String tipo;
	@Column(columnDefinition = "varchar(15)")
	private String caracteristica;
	@Column(columnDefinition = "varchar(50)")
	private String marcaElevador;
	@Column(columnDefinition = "varchar(300)")
	private String outrasCaracteristicas;
	private int condo;
	private String obsCondominio;
	@Column(columnDefinition = "varchar(100)")
	private String captadoPor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelPortaria() {
		return telPortaria;
	}

	public void setTelPortaria(String telPortaria) {
		this.telPortaria = telPortaria;
	}

	public int getConstrutora() {
		return construtora;
	}

	public void setConstrutora(int construtora) {
		this.construtora = construtora;
	}

	public String getNomeConstrutora() {
		return nomeConstrutora;
	}

	public void setNomeConstrutora(String nomeConstrutora) {
		this.nomeConstrutora = nomeConstrutora;
	}

	public String getNomeEngenheiro() {
		return nomeEngenheiro;
	}

	public void setNomeEngenheiro(String nomeEngenheiro) {
		this.nomeEngenheiro = nomeEngenheiro;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public int getBloco() {
		return bloco;
	}

	public void setBloco(int bloco) {
		this.bloco = bloco;
	}

	public int getAndar() {
		return andar;
	}

	public void setAndar(int andar) {
		this.andar = andar;
	}

	public int getApartamento() {
		return apartamento;
	}

	public void setApartamento(int apartamento) {
		this.apartamento = apartamento;
	}

	public int getAptosPorAndar() {
		return aptosPorAndar;
	}

	public void setAptosPorAndar(int aptosPorAndar) {
		this.aptosPorAndar = aptosPorAndar;
	}

	public int getFuncCondominio() {
		return funcCondominio;
	}

	public void setFuncCondominio(int funcCondominio) {
		this.funcCondominio = funcCondominio;
	}

	public int getFuncTerceirizado() {
		return funcTerceirizado;
	}

	public void setFuncTerceirizado(int funcTerceirizado) {
		this.funcTerceirizado = funcTerceirizado;
	}

	public int getElevador() {
		return elevador;
	}

	public void setElevador(int elevador) {
		this.elevador = elevador;
	}

	public boolean isPiscina() {
		return piscina;
	}

	public void setPiscina(boolean piscina) {
		this.piscina = piscina;
	}

	public boolean isPlayground() {
		return playground;
	}

	public void setPlayground(boolean playground) {
		this.playground = playground;
	}

	public boolean isSauna() {
		return sauna;
	}

	public void setSauna(boolean sauna) {
		this.sauna = sauna;
	}

	public boolean isJogos() {
		return jogos;
	}

	public void setJogos(boolean jogos) {
		this.jogos = jogos;
	}

	public boolean isGourmet() {
		return gourmet;
	}

	public void setGourmet(boolean gourmet) {
		this.gourmet = gourmet;
	}

	public boolean isQuadra() {
		return quadra;
	}

	public void setQuadra(boolean quadra) {
		this.quadra = quadra;
	}

	public boolean isChurrasqueira() {
		return churrasqueira;
	}

	public void setChurrasqueira(boolean churrasqueira) {
		this.churrasqueira = churrasqueira;
	}

	public boolean isSalaoFestas() {
		return salaoFestas;
	}

	public void setSalaoFestas(boolean salaoFestas) {
		this.salaoFestas = salaoFestas;
	}

	public boolean isGinastica() {
		return ginastica;
	}

	public void setGinastica(boolean ginastica) {
		this.ginastica = ginastica;
	}

	public boolean isJardim() {
		return jardim;
	}

	public void setJardim(boolean jardim) {
		this.jardim = jardim;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	public String getMarcaElevador() {
		return marcaElevador;
	}

	public void setMarcaElevador(String marcaElevador) {
		this.marcaElevador = marcaElevador;
	}

	public String getOutrasCaracteristicas() {
		return outrasCaracteristicas;
	}

	public void setOutrasCaracteristicas(String outrasCaracteristicas) {
		this.outrasCaracteristicas = outrasCaracteristicas;
	}

	public int getCondo() {
		return condo;
	}

	public void setCondo(int condo) {
		this.condo = condo;
	}

	public String getObsCondominio() {
		return obsCondominio;
	}

	public void setObsCondominio(String obsCondominio) {
		this.obsCondominio = obsCondominio;
	}

	public String getCaptadoPor() {
		return captadoPor;
	}

	public void setCaptadoPor(String captadoPor) {
		this.captadoPor = captadoPor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + andar;
		result = prime * result + apartamento;
		result = prime * result + aptosPorAndar;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + bloco;
		result = prime * result + ((captadoPor == null) ? 0 : captadoPor.hashCode());
		result = prime * result + ((caracteristica == null) ? 0 : caracteristica.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + (churrasqueira ? 1231 : 1237);
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((classificacao == null) ? 0 : classificacao.hashCode());
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + condo;
		result = prime * result + construtora;
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((dataEntrega == null) ? 0 : dataEntrega.hashCode());
		result = prime * result + elevador;
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + estado;
		result = prime * result + funcCondominio;
		result = prime * result + funcTerceirizado;
		result = prime * result + (ginastica ? 1231 : 1237);
		result = prime * result + (gourmet ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + (jardim ? 1231 : 1237);
		result = prime * result + (jogos ? 1231 : 1237);
		result = prime * result + ((marcaElevador == null) ? 0 : marcaElevador.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nomeConstrutora == null) ? 0 : nomeConstrutora.hashCode());
		result = prime * result + ((nomeEngenheiro == null) ? 0 : nomeEngenheiro.hashCode());
		result = prime * result + ((obsCondominio == null) ? 0 : obsCondominio.hashCode());
		result = prime * result + ((outrasCaracteristicas == null) ? 0 : outrasCaracteristicas.hashCode());
		result = prime * result + (piscina ? 1231 : 1237);
		result = prime * result + (playground ? 1231 : 1237);
		result = prime * result + (quadra ? 1231 : 1237);
		result = prime * result + ((regiao == null) ? 0 : regiao.hashCode());
		result = prime * result + (salaoFestas ? 1231 : 1237);
		result = prime * result + (sauna ? 1231 : 1237);
		result = prime * result + ((telPortaria == null) ? 0 : telPortaria.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		intra_cptcondo2 other = (intra_cptcondo2) obj;
		if (andar != other.andar)
			return false;
		if (apartamento != other.apartamento)
			return false;
		if (aptosPorAndar != other.aptosPorAndar)
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (bloco != other.bloco)
			return false;
		if (captadoPor == null) {
			if (other.captadoPor != null)
				return false;
		} else if (!captadoPor.equals(other.captadoPor))
			return false;
		if (caracteristica == null) {
			if (other.caracteristica != null)
				return false;
		} else if (!caracteristica.equals(other.caracteristica))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (churrasqueira != other.churrasqueira)
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (classificacao == null) {
			if (other.classificacao != null)
				return false;
		} else if (!classificacao.equals(other.classificacao))
			return false;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (condo != other.condo)
			return false;
		if (construtora != other.construtora)
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (dataEntrega == null) {
			if (other.dataEntrega != null)
				return false;
		} else if (!dataEntrega.equals(other.dataEntrega))
			return false;
		if (elevador != other.elevador)
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (estado != other.estado)
			return false;
		if (funcCondominio != other.funcCondominio)
			return false;
		if (funcTerceirizado != other.funcTerceirizado)
			return false;
		if (ginastica != other.ginastica)
			return false;
		if (gourmet != other.gourmet)
			return false;
		if (id != other.id)
			return false;
		if (jardim != other.jardim)
			return false;
		if (jogos != other.jogos)
			return false;
		if (marcaElevador == null) {
			if (other.marcaElevador != null)
				return false;
		} else if (!marcaElevador.equals(other.marcaElevador))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeConstrutora == null) {
			if (other.nomeConstrutora != null)
				return false;
		} else if (!nomeConstrutora.equals(other.nomeConstrutora))
			return false;
		if (nomeEngenheiro == null) {
			if (other.nomeEngenheiro != null)
				return false;
		} else if (!nomeEngenheiro.equals(other.nomeEngenheiro))
			return false;
		if (obsCondominio == null) {
			if (other.obsCondominio != null)
				return false;
		} else if (!obsCondominio.equals(other.obsCondominio))
			return false;
		if (outrasCaracteristicas == null) {
			if (other.outrasCaracteristicas != null)
				return false;
		} else if (!outrasCaracteristicas.equals(other.outrasCaracteristicas))
			return false;
		if (piscina != other.piscina)
			return false;
		if (playground != other.playground)
			return false;
		if (quadra != other.quadra)
			return false;
		if (regiao == null) {
			if (other.regiao != null)
				return false;
		} else if (!regiao.equals(other.regiao))
			return false;
		if (salaoFestas != other.salaoFestas)
			return false;
		if (sauna != other.sauna)
			return false;
		if (telPortaria == null) {
			if (other.telPortaria != null)
				return false;
		} else if (!telPortaria.equals(other.telPortaria))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}
