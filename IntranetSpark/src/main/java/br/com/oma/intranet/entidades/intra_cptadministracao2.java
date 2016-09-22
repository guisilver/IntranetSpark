package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class intra_cptadministracao2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1751915655603546051L;
	@Id
	@GeneratedValue
	private int id;
	private String nome;
	private String endereco;
	private String bairro;
	private String cidade;
	private int estado;
	private String cep;
	private String telefone;
	private String e_mail;
	private double valorCondominio;
	private int vencimento;
	private double valorArrecadado;
	private Date inicioContrato;
	private String grauSatisfacao;
	private String taxa;
	private boolean atendimento;
	private boolean selecao;
	private boolean taxaB;
	private boolean telefoneB;
	private boolean flexibilidade;
	private boolean sistOperacional;
	private boolean manutencao;
	private boolean malote;
	private boolean gerencia;
	private boolean pessoal;
	private boolean vistoria;
	private boolean custo;
	private boolean providenciasLentas;
	private boolean deptoJuridico;
	private String observacao;

	private int condo2;

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public double getValorCondominio() {
		return valorCondominio;
	}

	public void setValorCondominio(double valorCondominio) {
		this.valorCondominio = valorCondominio;
	}

	public int getVencimento() {
		return vencimento;
	}

	public void setVencimento(int vencimento) {
		this.vencimento = vencimento;
	}

	public double getValorArrecadado() {
		return valorArrecadado;
	}

	public void setValorArrecadado(double valorArrecadado) {
		this.valorArrecadado = valorArrecadado;
	}

	public Date getInicioContrato() {
		return inicioContrato;
	}

	public void setInicioContrato(Date inicioContrato) {
		this.inicioContrato = inicioContrato;
	}

	public String getGrauSatisfacao() {
		return grauSatisfacao;
	}

	public void setGrauSatisfacao(String grauSatisfacao) {
		this.grauSatisfacao = grauSatisfacao;
	}

	public String getTaxa() {
		return taxa;
	}

	public void setTaxa(String taxa) {
		this.taxa = taxa;
	}

	public boolean isAtendimento() {
		return atendimento;
	}

	public void setAtendimento(boolean atendimento) {
		this.atendimento = atendimento;
	}

	public boolean isSelecao() {
		return selecao;
	}

	public void setSelecao(boolean selecao) {
		this.selecao = selecao;
	}

	public boolean isTaxaB() {
		return taxaB;
	}

	public void setTaxaB(boolean taxaB) {
		this.taxaB = taxaB;
	}

	public boolean isTelefoneB() {
		return telefoneB;
	}

	public void setTelefoneB(boolean telefoneB) {
		this.telefoneB = telefoneB;
	}

	public boolean isFlexibilidade() {
		return flexibilidade;
	}

	public void setFlexibilidade(boolean flexibilidade) {
		this.flexibilidade = flexibilidade;
	}

	public boolean isSistOperacional() {
		return sistOperacional;
	}

	public void setSistOperacional(boolean sistOperacional) {
		this.sistOperacional = sistOperacional;
	}

	public boolean isManutencao() {
		return manutencao;
	}

	public void setManutencao(boolean manutencao) {
		this.manutencao = manutencao;
	}

	public boolean isMalote() {
		return malote;
	}

	public void setMalote(boolean malote) {
		this.malote = malote;
	}

	public boolean isGerencia() {
		return gerencia;
	}

	public void setGerencia(boolean gerencia) {
		this.gerencia = gerencia;
	}

	public boolean isPessoal() {
		return pessoal;
	}

	public void setPessoal(boolean pessoal) {
		this.pessoal = pessoal;
	}

	public boolean isVistoria() {
		return vistoria;
	}

	public void setVistoria(boolean vistoria) {
		this.vistoria = vistoria;
	}

	public boolean isCusto() {
		return custo;
	}

	public void setCusto(boolean custo) {
		this.custo = custo;
	}

	public boolean isProvidenciasLentas() {
		return providenciasLentas;
	}

	public void setProvidenciasLentas(boolean providenciasLentas) {
		this.providenciasLentas = providenciasLentas;
	}

	public boolean isDeptoJuridico() {
		return deptoJuridico;
	}

	public void setDeptoJuridico(boolean deptoJuridico) {
		this.deptoJuridico = deptoJuridico;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getCondo2() {
		return condo2;
	}

	public void setCondo2(int condo2) {
		this.condo2 = condo2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (atendimento ? 1231 : 1237);
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + condo2;
		result = prime * result + (custo ? 1231 : 1237);
		result = prime * result + (deptoJuridico ? 1231 : 1237);
		result = prime * result + ((e_mail == null) ? 0 : e_mail.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + estado;
		result = prime * result + (flexibilidade ? 1231 : 1237);
		result = prime * result + (gerencia ? 1231 : 1237);
		result = prime * result + ((grauSatisfacao == null) ? 0 : grauSatisfacao.hashCode());
		result = prime * result + id;
		result = prime * result + ((inicioContrato == null) ? 0 : inicioContrato.hashCode());
		result = prime * result + (malote ? 1231 : 1237);
		result = prime * result + (manutencao ? 1231 : 1237);
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + (pessoal ? 1231 : 1237);
		result = prime * result + (providenciasLentas ? 1231 : 1237);
		result = prime * result + (selecao ? 1231 : 1237);
		result = prime * result + (sistOperacional ? 1231 : 1237);
		result = prime * result + ((taxa == null) ? 0 : taxa.hashCode());
		result = prime * result + (taxaB ? 1231 : 1237);
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + (telefoneB ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(valorArrecadado);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorCondominio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + vencimento;
		result = prime * result + (vistoria ? 1231 : 1237);
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
		intra_cptadministracao2 other = (intra_cptadministracao2) obj;
		if (atendimento != other.atendimento)
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (condo2 != other.condo2)
			return false;
		if (custo != other.custo)
			return false;
		if (deptoJuridico != other.deptoJuridico)
			return false;
		if (e_mail == null) {
			if (other.e_mail != null)
				return false;
		} else if (!e_mail.equals(other.e_mail))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (estado != other.estado)
			return false;
		if (flexibilidade != other.flexibilidade)
			return false;
		if (gerencia != other.gerencia)
			return false;
		if (grauSatisfacao == null) {
			if (other.grauSatisfacao != null)
				return false;
		} else if (!grauSatisfacao.equals(other.grauSatisfacao))
			return false;
		if (id != other.id)
			return false;
		if (inicioContrato == null) {
			if (other.inicioContrato != null)
				return false;
		} else if (!inicioContrato.equals(other.inicioContrato))
			return false;
		if (malote != other.malote)
			return false;
		if (manutencao != other.manutencao)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (pessoal != other.pessoal)
			return false;
		if (providenciasLentas != other.providenciasLentas)
			return false;
		if (selecao != other.selecao)
			return false;
		if (sistOperacional != other.sistOperacional)
			return false;
		if (taxa == null) {
			if (other.taxa != null)
				return false;
		} else if (!taxa.equals(other.taxa))
			return false;
		if (taxaB != other.taxaB)
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (telefoneB != other.telefoneB)
			return false;
		if (Double.doubleToLongBits(valorArrecadado) != Double.doubleToLongBits(other.valorArrecadado))
			return false;
		if (Double.doubleToLongBits(valorCondominio) != Double.doubleToLongBits(other.valorCondominio))
			return false;
		if (vencimento != other.vencimento)
			return false;
		if (vistoria != other.vistoria)
			return false;
		return true;
	}

}
