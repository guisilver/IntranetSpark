package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.thoughtworks.xstream.XStream;

@Entity
public class intra_assembleia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "int")
	private int numPautas;
	@Column(columnDefinition = "bit")
	private boolean risco;
	@Column(columnDefinition = "bit")
	private boolean protocoloRecebido;
	@Column(columnDefinition = "bit")
	private boolean naoReceberEmail;
	@Column(columnDefinition = "bit")
	private boolean eleicaoSindico;
	@Column(columnDefinition = "bit")
	private boolean aprovacaoSindico;
	@Column(columnDefinition = "varchar(5)")
	private String sexo;
	@Column(columnDefinition = "varchar(20)")
	private String tipo;
	@Column(columnDefinition = "varchar(10)")
	private String localAssembleia;
	@Column(columnDefinition = "varchar(500)")
	private String enderecoLocal;
	@Column(columnDefinition = "varchar(500)")
	private String contas;
	@Column(columnDefinition = "varchar(500)")
	private String obs;
	@Column(columnDefinition = "varchar(500)")
	private String obsHorario1;
	@Column(columnDefinition = "varchar(500)")
	private String obsHorario2;
	@Column(columnDefinition = "varchar(500)")
	private String riscoObs;
	@Column(columnDefinition = "varchar(MAX)")
	private String ordem;
	@Column(columnDefinition = "varchar(500)")
	private String enviadoPresidente;
	@Column(columnDefinition = "datetime")
	private Date horario1;
	@Column(columnDefinition = "datetime")
	private Date horario2;
	@Column(columnDefinition = "datetime")
	private Date dataAssembleia;
	@Column(columnDefinition = "datetime")
	private Date enviado;
	@Column(columnDefinition = "datetime")
	private Date devolvido;
	@Column(columnDefinition = "datetime")
	private Date publicacaoConvocacao;
	@Column(columnDefinition = "datetime")
	private Date dataInserido;
	@Column(columnDefinition = "datetime")
	private Date digitacao;
	@Column(columnDefinition = "datetime")
	private Date advogado;
	@Column(columnDefinition = "datetime")
	private Date assinada;
	@Column(columnDefinition = "datetime")
	private Date distribuicao;
	@Column(columnDefinition = "datetime")
	private Date cartorio;
	@Column(columnDefinition = "datetime")
	private Date enviadoTi;
	@Column(columnDefinition = "datetime")
	private Date protocolo;
	@Column(columnDefinition = "datetime")
	private Date publicacaoAta;
	@Column(columnDefinition = "datetime")
	private Date dataAprovacaoSindico;
	@ManyToOne
	private intra_grupo_gerente gerente;
	@ManyToOne
	private intra_condominios condominio;
	@OneToOne(cascade = CascadeType.ALL)
	private intra_reservas_ti reserva = new intra_reservas_ti();

	public intra_assembleia() {

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getNumPautas() {
		return numPautas;
	}

	public void setNumPautas(int numPautas) {
		this.numPautas = numPautas;
	}

	public boolean isRisco() {
		return risco;
	}

	public void setRisco(boolean risco) {
		this.risco = risco;
	}

	public boolean isProtocoloRecebido() {
		return protocoloRecebido;
	}

	public void setProtocoloRecebido(boolean protocoloRecebido) {
		this.protocoloRecebido = protocoloRecebido;
	}

	public boolean isNaoReceberEmail() {
		return naoReceberEmail;
	}

	public void setNaoReceberEmail(boolean naoReceberEmail) {
		this.naoReceberEmail = naoReceberEmail;
	}

	public boolean isEleicaoSindico() {
		return eleicaoSindico;
	}

	public void setEleicaoSindico(boolean eleicaoSindico) {
		this.eleicaoSindico = eleicaoSindico;
	}

	public boolean isAprovacaoSindico() {
		return aprovacaoSindico;
	}

	public void setAprovacaoSindico(boolean aprovacaoSindico) {
		this.aprovacaoSindico = aprovacaoSindico;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getLocalAssembleia() {
		return localAssembleia;
	}

	public void setLocalAssembleia(String localAssembleia) {
		this.localAssembleia = localAssembleia;
	}

	public String getEnderecoLocal() {
		return enderecoLocal;
	}

	public void setEnderecoLocal(String enderecoLocal) {
		this.enderecoLocal = enderecoLocal;
	}

	public String getContas() {
		return contas;
	}

	public void setContas(String contas) {
		this.contas = contas;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getObsHorario1() {
		return obsHorario1;
	}

	public void setObsHorario1(String obsHorario1) {
		this.obsHorario1 = obsHorario1;
	}

	public String getObsHorario2() {
		return obsHorario2;
	}

	public void setObsHorario2(String obsHorario2) {
		this.obsHorario2 = obsHorario2;
	}

	public String getRiscoObs() {
		return riscoObs;
	}

	public void setRiscoObs(String riscoObs) {
		this.riscoObs = riscoObs;
	}

	public String getOrdem() {
		return ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}

	public String getEnviadoPresidente() {
		return enviadoPresidente;
	}

	public void setEnviadoPresidente(String enviadoPresidente) {
		this.enviadoPresidente = enviadoPresidente;
	}

	public Date getHorario1() {
		return horario1;
	}

	public void setHorario1(Date horario1) {
		this.horario1 = horario1;
	}

	public Date getHorario2() {
		return horario2;
	}

	public void setHorario2(Date horario2) {
		this.horario2 = horario2;
	}

	public Date getDataAssembleia() {
		return dataAssembleia;
	}

	public void setDataAssembleia(Date dataAssembleia) {
		this.dataAssembleia = dataAssembleia;
	}

	public Date getEnviado() {
		return enviado;
	}

	public void setEnviado(Date enviado) {
		this.enviado = enviado;
	}

	public Date getDevolvido() {
		return devolvido;
	}

	public void setDevolvido(Date devolvido) {
		this.devolvido = devolvido;
	}

	public Date getPublicacaoConvocacao() {
		return publicacaoConvocacao;
	}

	public void setPublicacaoConvocacao(Date publicacaoConvocacao) {
		this.publicacaoConvocacao = publicacaoConvocacao;
	}

	public Date getDataInserido() {
		return dataInserido;
	}

	public void setDataInserido(Date dataInserido) {
		this.dataInserido = dataInserido;
	}

	public Date getDigitacao() {
		return digitacao;
	}

	public void setDigitacao(Date digitacao) {
		this.digitacao = digitacao;
	}

	public Date getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Date advogado) {
		this.advogado = advogado;
	}

	public Date getAssinada() {
		return assinada;
	}

	public void setAssinada(Date assinada) {
		this.assinada = assinada;
	}

	public Date getDistribuicao() {
		return distribuicao;
	}

	public void setDistribuicao(Date distribuicao) {
		this.distribuicao = distribuicao;
	}

	public Date getCartorio() {
		return cartorio;
	}

	public void setCartorio(Date cartorio) {
		this.cartorio = cartorio;
	}

	public Date getEnviadoTi() {
		return enviadoTi;
	}

	public void setEnviadoTi(Date enviadoTi) {
		this.enviadoTi = enviadoTi;
	}

	public Date getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(Date protocolo) {
		this.protocolo = protocolo;
	}

	public Date getPublicacaoAta() {
		return publicacaoAta;
	}

	public void setPublicacaoAta(Date publicacaoAta) {
		this.publicacaoAta = publicacaoAta;
	}

	public Date getDataAprovacaoSindico() {
		return dataAprovacaoSindico;
	}

	public void setDataAprovacaoSindico(Date dataAprovacaoSindico) {
		this.dataAprovacaoSindico = dataAprovacaoSindico;
	}

	public intra_grupo_gerente getGerente() {
		return gerente;
	}

	public void setGerente(intra_grupo_gerente gerente) {
		this.gerente = gerente;
	}

	public intra_condominios getCondominio() {
		return condominio;
	}

	public void setCondominio(intra_condominios condominio) {
		this.condominio = condominio;
	}

	public intra_reservas_ti getReserva() {
		return reserva;
	}

	public void setReserva(intra_reservas_ti reserva) {
		this.reserva = reserva;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((advogado == null) ? 0 : advogado.hashCode());
		result = prime * result + (aprovacaoSindico ? 1231 : 1237);
		result = prime * result + ((assinada == null) ? 0 : assinada.hashCode());
		result = prime * result + ((cartorio == null) ? 0 : cartorio.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((condominio == null) ? 0 : condominio.hashCode());
		result = prime * result + ((contas == null) ? 0 : contas.hashCode());
		result = prime * result + ((dataAprovacaoSindico == null) ? 0 : dataAprovacaoSindico.hashCode());
		result = prime * result + ((dataAssembleia == null) ? 0 : dataAssembleia.hashCode());
		result = prime * result + ((dataInserido == null) ? 0 : dataInserido.hashCode());
		result = prime * result + ((devolvido == null) ? 0 : devolvido.hashCode());
		result = prime * result + ((digitacao == null) ? 0 : digitacao.hashCode());
		result = prime * result + ((distribuicao == null) ? 0 : distribuicao.hashCode());
		result = prime * result + (eleicaoSindico ? 1231 : 1237);
		result = prime * result + ((enderecoLocal == null) ? 0 : enderecoLocal.hashCode());
		result = prime * result + ((enviado == null) ? 0 : enviado.hashCode());
		result = prime * result + ((enviadoPresidente == null) ? 0 : enviadoPresidente.hashCode());
		result = prime * result + ((enviadoTi == null) ? 0 : enviadoTi.hashCode());
		result = prime * result + ((gerente == null) ? 0 : gerente.hashCode());
		result = prime * result + ((horario1 == null) ? 0 : horario1.hashCode());
		result = prime * result + ((horario2 == null) ? 0 : horario2.hashCode());
		result = prime * result + ((localAssembleia == null) ? 0 : localAssembleia.hashCode());
		result = prime * result + (naoReceberEmail ? 1231 : 1237);
		result = prime * result + numPautas;
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + ((obsHorario1 == null) ? 0 : obsHorario1.hashCode());
		result = prime * result + ((obsHorario2 == null) ? 0 : obsHorario2.hashCode());
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
		result = prime * result + ((protocolo == null) ? 0 : protocolo.hashCode());
		result = prime * result + (protocoloRecebido ? 1231 : 1237);
		result = prime * result + ((publicacaoAta == null) ? 0 : publicacaoAta.hashCode());
		result = prime * result + ((publicacaoConvocacao == null) ? 0 : publicacaoConvocacao.hashCode());
		result = prime * result + ((reserva == null) ? 0 : reserva.hashCode());
		result = prime * result + (risco ? 1231 : 1237);
		result = prime * result + ((riscoObs == null) ? 0 : riscoObs.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
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
		intra_assembleia other = (intra_assembleia) obj;
		if (advogado == null) {
			if (other.advogado != null)
				return false;
		} else if (!advogado.equals(other.advogado))
			return false;
		if (aprovacaoSindico != other.aprovacaoSindico)
			return false;
		if (assinada == null) {
			if (other.assinada != null)
				return false;
		} else if (!assinada.equals(other.assinada))
			return false;
		if (cartorio == null) {
			if (other.cartorio != null)
				return false;
		} else if (!cartorio.equals(other.cartorio))
			return false;
		if (codigo != other.codigo)
			return false;
		if (condominio == null) {
			if (other.condominio != null)
				return false;
		} else if (!condominio.equals(other.condominio))
			return false;
		if (contas == null) {
			if (other.contas != null)
				return false;
		} else if (!contas.equals(other.contas))
			return false;
		if (dataAprovacaoSindico == null) {
			if (other.dataAprovacaoSindico != null)
				return false;
		} else if (!dataAprovacaoSindico.equals(other.dataAprovacaoSindico))
			return false;
		if (dataAssembleia == null) {
			if (other.dataAssembleia != null)
				return false;
		} else if (!dataAssembleia.equals(other.dataAssembleia))
			return false;
		if (dataInserido == null) {
			if (other.dataInserido != null)
				return false;
		} else if (!dataInserido.equals(other.dataInserido))
			return false;
		if (devolvido == null) {
			if (other.devolvido != null)
				return false;
		} else if (!devolvido.equals(other.devolvido))
			return false;
		if (digitacao == null) {
			if (other.digitacao != null)
				return false;
		} else if (!digitacao.equals(other.digitacao))
			return false;
		if (distribuicao == null) {
			if (other.distribuicao != null)
				return false;
		} else if (!distribuicao.equals(other.distribuicao))
			return false;
		if (eleicaoSindico != other.eleicaoSindico)
			return false;
		if (enderecoLocal == null) {
			if (other.enderecoLocal != null)
				return false;
		} else if (!enderecoLocal.equals(other.enderecoLocal))
			return false;
		if (enviado == null) {
			if (other.enviado != null)
				return false;
		} else if (!enviado.equals(other.enviado))
			return false;
		if (enviadoPresidente == null) {
			if (other.enviadoPresidente != null)
				return false;
		} else if (!enviadoPresidente.equals(other.enviadoPresidente))
			return false;
		if (enviadoTi == null) {
			if (other.enviadoTi != null)
				return false;
		} else if (!enviadoTi.equals(other.enviadoTi))
			return false;
		if (gerente == null) {
			if (other.gerente != null)
				return false;
		} else if (!gerente.equals(other.gerente))
			return false;
		if (horario1 == null) {
			if (other.horario1 != null)
				return false;
		} else if (!horario1.equals(other.horario1))
			return false;
		if (horario2 == null) {
			if (other.horario2 != null)
				return false;
		} else if (!horario2.equals(other.horario2))
			return false;
		if (localAssembleia == null) {
			if (other.localAssembleia != null)
				return false;
		} else if (!localAssembleia.equals(other.localAssembleia))
			return false;
		if (naoReceberEmail != other.naoReceberEmail)
			return false;
		if (numPautas != other.numPautas)
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (obsHorario1 == null) {
			if (other.obsHorario1 != null)
				return false;
		} else if (!obsHorario1.equals(other.obsHorario1))
			return false;
		if (obsHorario2 == null) {
			if (other.obsHorario2 != null)
				return false;
		} else if (!obsHorario2.equals(other.obsHorario2))
			return false;
		if (ordem == null) {
			if (other.ordem != null)
				return false;
		} else if (!ordem.equals(other.ordem))
			return false;
		if (protocolo == null) {
			if (other.protocolo != null)
				return false;
		} else if (!protocolo.equals(other.protocolo))
			return false;
		if (protocoloRecebido != other.protocoloRecebido)
			return false;
		if (publicacaoAta == null) {
			if (other.publicacaoAta != null)
				return false;
		} else if (!publicacaoAta.equals(other.publicacaoAta))
			return false;
		if (publicacaoConvocacao == null) {
			if (other.publicacaoConvocacao != null)
				return false;
		} else if (!publicacaoConvocacao.equals(other.publicacaoConvocacao))
			return false;
		if (reserva == null) {
			if (other.reserva != null)
				return false;
		} else if (!reserva.equals(other.reserva))
			return false;
		if (risco != other.risco)
			return false;
		if (riscoObs == null) {
			if (other.riscoObs != null)
				return false;
		} else if (!riscoObs.equals(other.riscoObs))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		/*
		 * return "Cd: " + getCodigo() + ",Cnd: " + getCondominio() + ", Ger: "
		 * + getGerente().getNome() + ", DT ASSEMB: " + getDataAssembleia() +
		 * ", DT INSERIDO: " + getDataInserido();
		 */
		XStream xstream = new XStream();
		return xstream.toXML(this);
	}

}
