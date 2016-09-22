package br.com.oma.omaonline.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cndcondo_param",schema="omaonline.dbo")
public class cndcondo_param implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private short codigo_cnd;

	@Column(scale = 2, precision = 15)
	private double taxaCondominial;

	// Emails
	@Column(columnDefinition = "bit")
	private boolean gerenteEmail;
	@Column(columnDefinition = "bit")
	private boolean gestorEmail;
	@Column(columnDefinition = "bit")
	private boolean sindicoEmail;
	@Column(columnDefinition = "bit")
	private boolean zeladorEmail;

	// Visualização
	@Column(columnDefinition = "bit")
	private boolean portaria;
	@Column(columnDefinition = "bit")
	private boolean comodidades;
	@Column(columnDefinition = "bit")
	private boolean meus_dados;
	@Column(columnDefinition = "bit")
	private boolean mensagens;
	@Column(columnDefinition = "bit")
	private boolean ativos;
	@Column(columnDefinition = "bit")
	private boolean configuracoes;
	@Column(columnDefinition = "bit")
	private boolean nomeAutorizador;
	@Column(columnDefinition = "bit")
	private boolean usarNumeroChave;
	
	@Column(name="usar_crachar",columnDefinition = "bit")
	private boolean usarCrachar;

	// Visualizar Cadastro
	@Column(columnDefinition = "bit")
	private boolean cadastro;
	@Column(columnDefinition = "bit")
	private boolean cadMorador;
	@Column(columnDefinition = "bit")
	private boolean cadEmpregados;

	// Comodidade
	@Column(columnDefinition = "bit")
	private boolean percentual;
	@Column(columnDefinition = "bit")
	private boolean visualizar_unidades;
	@Column(columnDefinition = "bit")
	private boolean naoReservaOutroGrupo;
	@Column(columnDefinition = "bit")
	private boolean checklistReserva;

	// Mensagens
	@Column(columnDefinition = "bit")
	private boolean msSindico;
	@Column(columnDefinition = "bit")
	private boolean msGestor;
	@Column(columnDefinition = "bit")
	private boolean msZelador;
	@Column(columnDefinition = "bit")
	private boolean msPortaria;
	@Column(columnDefinition = "bit")
	private boolean msMorador;
	@Column(columnDefinition = "bit")
	private boolean financeiro;

	// Mensagens
	@Column(columnDefinition = "bit")
	private boolean analit_sintet;

	@Column(columnDefinition = "bit")
	private boolean aprovacaoLancamentos;

	@Column(columnDefinition = "int")
	private int nivelAprovacao1;

	@Column(columnDefinition = "int")
	private int nivelAprovacao2;

	@Column(columnDefinition = "int")
	private int nivelAprovacao3;

	@Column(columnDefinition = "int")
	private int nivelAprovacao4;

	@Column(columnDefinition = "int")
	private int nivelAprovacao5;

	// Morador
	@Column(columnDefinition = "bit")
	private boolean moradorNaoReserva;

	public short getCodigo_cnd() {
		return codigo_cnd;
	}

	public void setCodigo_cnd(short codigo_cnd) {
		this.codigo_cnd = codigo_cnd;
	}

	public double getTaxaCondominial() {
		return taxaCondominial;
	}

	public void setTaxaCondominial(double taxaCondominial) {
		this.taxaCondominial = taxaCondominial;
	}

	public boolean isGerenteEmail() {
		return gerenteEmail;
	}

	public void setGerenteEmail(boolean gerenteEmail) {
		this.gerenteEmail = gerenteEmail;
	}

	public boolean isGestorEmail() {
		return gestorEmail;
	}

	public void setGestorEmail(boolean gestorEmail) {
		this.gestorEmail = gestorEmail;
	}

	public boolean isSindicoEmail() {
		return sindicoEmail;
	}

	public void setSindicoEmail(boolean sindicoEmail) {
		this.sindicoEmail = sindicoEmail;
	}

	public boolean isZeladorEmail() {
		return zeladorEmail;
	}

	public void setZeladorEmail(boolean zeladorEmail) {
		this.zeladorEmail = zeladorEmail;
	}

	public boolean isPortaria() {
		return portaria;
	}

	public void setPortaria(boolean portaria) {
		this.portaria = portaria;
	}

	public boolean isComodidades() {
		return comodidades;
	}

	public void setComodidades(boolean comodidades) {
		this.comodidades = comodidades;
	}

	public boolean isMeus_dados() {
		return meus_dados;
	}

	public void setMeus_dados(boolean meus_dados) {
		this.meus_dados = meus_dados;
	}

	public boolean isMensagens() {
		return mensagens;
	}

	public void setMensagens(boolean mensagens) {
		this.mensagens = mensagens;
	}

	public boolean isAtivos() {
		return ativos;
	}

	public void setAtivos(boolean ativos) {
		this.ativos = ativos;
	}

	public boolean isConfiguracoes() {
		return configuracoes;
	}

	public void setConfiguracoes(boolean configuracoes) {
		this.configuracoes = configuracoes;
	}

	public boolean isPercentual() {
		return percentual;
	}

	public void setPercentual(boolean percentual) {
		this.percentual = percentual;
	}

	public boolean isVisualizar_unidades() {
		return visualizar_unidades;
	}

	public void setVisualizar_unidades(boolean visualizar_unidades) {
		this.visualizar_unidades = visualizar_unidades;
	}

	public boolean isNaoReservaOutroGrupo() {
		return naoReservaOutroGrupo;
	}

	public void setNaoReservaOutroGrupo(boolean naoReservaOutroGrupo) {
		this.naoReservaOutroGrupo = naoReservaOutroGrupo;
	}

	public boolean isChecklistReserva() {
		return checklistReserva;
	}

	public void setChecklistReserva(boolean checklistReserva) {
		this.checklistReserva = checklistReserva;
	}

	public boolean isMsSindico() {
		return msSindico;
	}

	public void setMsSindico(boolean msSindico) {
		this.msSindico = msSindico;
	}

	public boolean isMsGestor() {
		return msGestor;
	}

	public void setMsGestor(boolean msGestor) {
		this.msGestor = msGestor;
	}

	public boolean isMsZelador() {
		return msZelador;
	}

	public void setMsZelador(boolean msZelador) {
		this.msZelador = msZelador;
	}

	public boolean isMsPortaria() {
		return msPortaria;
	}

	public void setMsPortaria(boolean msPortaria) {
		this.msPortaria = msPortaria;
	}

	public boolean isMsMorador() {
		return msMorador;
	}

	public void setMsMorador(boolean msMorador) {
		this.msMorador = msMorador;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isFinanceiro() {
		return financeiro;
	}

	public void setFinanceiro(boolean financeiro) {
		this.financeiro = financeiro;
	}

	public boolean isAnalit_sintet() {
		return analit_sintet;
	}

	public void setAnalit_sintet(boolean analit_sintet) {
		this.analit_sintet = analit_sintet;
	}

	public boolean isAprovacaoLancamentos() {
		return aprovacaoLancamentos;
	}

	public void setAprovacaoLancamentos(boolean aprovacaoLancamentos) {
		this.aprovacaoLancamentos = aprovacaoLancamentos;
	}

	public int getNivelAprovacao1() {
		return nivelAprovacao1;
	}

	public void setNivelAprovacao1(int nivelAprovacao1) {
		this.nivelAprovacao1 = nivelAprovacao1;
	}

	public int getNivelAprovacao2() {
		return nivelAprovacao2;
	}

	public void setNivelAprovacao2(int nivelAprovacao2) {
		this.nivelAprovacao2 = nivelAprovacao2;
	}

	public int getNivelAprovacao3() {
		return nivelAprovacao3;
	}

	public void setNivelAprovacao3(int nivelAprovacao3) {
		this.nivelAprovacao3 = nivelAprovacao3;
	}

	public int getNivelAprovacao4() {
		return nivelAprovacao4;
	}

	public void setNivelAprovacao4(int nivelAprovacao4) {
		this.nivelAprovacao4 = nivelAprovacao4;
	}

	public int getNivelAprovacao5() {
		return nivelAprovacao5;
	}

	public void setNivelAprovacao5(int nivelAprovacao5) {
		this.nivelAprovacao5 = nivelAprovacao5;
	}

	public boolean isCadMorador() {
		return cadMorador;
	}

	public void setCadMorador(boolean cadMorador) {
		this.cadMorador = cadMorador;
	}

	public boolean isCadEmpregados() {
		return cadEmpregados;
	}

	public void setCadEmpregados(boolean cadEmpregados) {
		this.cadEmpregados = cadEmpregados;
	}

	public boolean isCadastro() {
		return cadastro;
	}

	public void setCadastro(boolean cadastro) {
		this.cadastro = cadastro;
	}

	public boolean isMoradorNaoReserva() {
		return moradorNaoReserva;
	}

	public void setMoradorNaoReserva(boolean moradorNaoReserva) {
		this.moradorNaoReserva = moradorNaoReserva;
	}

	public boolean isNomeAutorizador() {
		return nomeAutorizador;
	}

	public void setNomeAutorizador(boolean nomeAutorizador) {
		this.nomeAutorizador = nomeAutorizador;
	}

	public boolean isUsarCrachar() {
		return usarCrachar;
	}

	public void setUsarCrachar(boolean usarCrachar) {
		this.usarCrachar = usarCrachar;
	}

	public boolean isUsarNumeroChave() {
		return usarNumeroChave;
	}

	public void setUsarNumeroChave(boolean usarNumeroChave) {
		this.usarNumeroChave = usarNumeroChave;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (analit_sintet ? 1231 : 1237);
		result = prime * result + (aprovacaoLancamentos ? 1231 : 1237);
		result = prime * result + (ativos ? 1231 : 1237);
		result = prime * result + (cadEmpregados ? 1231 : 1237);
		result = prime * result + (cadMorador ? 1231 : 1237);
		result = prime * result + (cadastro ? 1231 : 1237);
		result = prime * result + (checklistReserva ? 1231 : 1237);
		result = prime * result + codigo_cnd;
		result = prime * result + (comodidades ? 1231 : 1237);
		result = prime * result + (configuracoes ? 1231 : 1237);
		result = prime * result + (financeiro ? 1231 : 1237);
		result = prime * result + (gerenteEmail ? 1231 : 1237);
		result = prime * result + (gestorEmail ? 1231 : 1237);
		result = prime * result + (mensagens ? 1231 : 1237);
		result = prime * result + (meus_dados ? 1231 : 1237);
		result = prime * result + (moradorNaoReserva ? 1231 : 1237);
		result = prime * result + (msGestor ? 1231 : 1237);
		result = prime * result + (msMorador ? 1231 : 1237);
		result = prime * result + (msPortaria ? 1231 : 1237);
		result = prime * result + (msSindico ? 1231 : 1237);
		result = prime * result + (msZelador ? 1231 : 1237);
		result = prime * result + (naoReservaOutroGrupo ? 1231 : 1237);
		result = prime * result + nivelAprovacao1;
		result = prime * result + nivelAprovacao2;
		result = prime * result + nivelAprovacao3;
		result = prime * result + nivelAprovacao4;
		result = prime * result + nivelAprovacao5;
		result = prime * result + (nomeAutorizador ? 1231 : 1237);
		result = prime * result + (percentual ? 1231 : 1237);
		result = prime * result + (portaria ? 1231 : 1237);
		result = prime * result + (sindicoEmail ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(taxaCondominial);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (usarCrachar ? 1231 : 1237);
		result = prime * result + (usarNumeroChave ? 1231 : 1237);
		result = prime * result + (visualizar_unidades ? 1231 : 1237);
		result = prime * result + (zeladorEmail ? 1231 : 1237);
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
		cndcondo_param other = (cndcondo_param) obj;
		if (analit_sintet != other.analit_sintet)
			return false;
		if (aprovacaoLancamentos != other.aprovacaoLancamentos)
			return false;
		if (ativos != other.ativos)
			return false;
		if (cadEmpregados != other.cadEmpregados)
			return false;
		if (cadMorador != other.cadMorador)
			return false;
		if (cadastro != other.cadastro)
			return false;
		if (checklistReserva != other.checklistReserva)
			return false;
		if (codigo_cnd != other.codigo_cnd)
			return false;
		if (comodidades != other.comodidades)
			return false;
		if (configuracoes != other.configuracoes)
			return false;
		if (financeiro != other.financeiro)
			return false;
		if (gerenteEmail != other.gerenteEmail)
			return false;
		if (gestorEmail != other.gestorEmail)
			return false;
		if (mensagens != other.mensagens)
			return false;
		if (meus_dados != other.meus_dados)
			return false;
		if (moradorNaoReserva != other.moradorNaoReserva)
			return false;
		if (msGestor != other.msGestor)
			return false;
		if (msMorador != other.msMorador)
			return false;
		if (msPortaria != other.msPortaria)
			return false;
		if (msSindico != other.msSindico)
			return false;
		if (msZelador != other.msZelador)
			return false;
		if (naoReservaOutroGrupo != other.naoReservaOutroGrupo)
			return false;
		if (nivelAprovacao1 != other.nivelAprovacao1)
			return false;
		if (nivelAprovacao2 != other.nivelAprovacao2)
			return false;
		if (nivelAprovacao3 != other.nivelAprovacao3)
			return false;
		if (nivelAprovacao4 != other.nivelAprovacao4)
			return false;
		if (nivelAprovacao5 != other.nivelAprovacao5)
			return false;
		if (nomeAutorizador != other.nomeAutorizador)
			return false;
		if (percentual != other.percentual)
			return false;
		if (portaria != other.portaria)
			return false;
		if (sindicoEmail != other.sindicoEmail)
			return false;
		if (Double.doubleToLongBits(taxaCondominial) != Double
				.doubleToLongBits(other.taxaCondominial))
			return false;
		if (usarCrachar != other.usarCrachar)
			return false;
		if (usarNumeroChave != other.usarNumeroChave)
			return false;
		if (visualizar_unidades != other.visualizar_unidades)
			return false;
		if (zeladorEmail != other.zeladorEmail)
			return false;
		return true;
	}
}