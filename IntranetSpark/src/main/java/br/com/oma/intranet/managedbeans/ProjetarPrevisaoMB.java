package br.com.oma.intranet.managedbeans;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.joda.time.DateTime;

import br.com.oma.intranet.dao.PrevisaoInadimplenciaDAO;
import br.com.oma.intranet.dao.ProjetarPrevisaoDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_previsao_orcamentaria;
import br.com.oma.intranet.entidades.intra_projetar_orcamento;
import br.com.oma.intranet.util.PrevisaoCapaAUX;


@ManagedBean(name = "projetarMB")
@ViewScoped
public class ProjetarPrevisaoMB extends CarregaProjecaoMB {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4226655926840469040L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	//OBJETOS
	private ProjetarPrevisaoDAO ppDAO;
	private PrevisaoCapaAUX previsaoCapaAUXBEAN = new PrevisaoCapaAUX();
	private CalcularPrevisao cp;
	private intra_previsao_orcamentaria prevBEAN = new intra_previsao_orcamentaria();
	private intra_grupo_gerente gerenteMB = new intra_grupo_gerente();
	private intra_condominios icBEAN = new intra_condominios();
	private intra_projetar_orcamento ipoBEAN = new intra_projetar_orcamento();
	private intra_projetar_orcamento adicionarNovoBEAN = new intra_projetar_orcamento();
	
	//ATRIBUTOS
	private List<intra_condominios> listaDeCondominio;
	private List<intra_grupo_gerente> listaDeGerentes;
	private List<intra_previsao_orcamentaria> listaPrevisaoOrcamentaria;
	private List<PrevisaoCapaAUX> listaDePrevisaoCapa;
	private String nomeCondo,nomeCondo2;
	private int media;
	private int reutPrevOrc;
	private boolean adicionarItem;
	private double valorFunc;
	private String mesProjecao;
	
	private boolean reutilizarPrevisao;
	private boolean validaTela;


	//GET SET
	public boolean isValidaTela() {
		return validaTela;
	}

	public void setValidaTela(boolean validaTela) {
		this.validaTela = validaTela;
	}
	
	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}
	
	public intra_grupo_gerente getGerenteMB() {
		return gerenteMB;
	}

	public void setGerenteMB(intra_grupo_gerente gerenteMB) {
		this.gerenteMB = gerenteMB;
	}

	public intra_condominios getIcBEAN() {
		return icBEAN;
	}

	public void setIcBEAN(intra_condominios icBEAN) {
		this.icBEAN = icBEAN;
	}

	public List<intra_condominios> getListaDeCondominio() {
		if (this.gerenteMB.getCodigo() > 0) {
			this.ppDAO = new ProjetarPrevisaoDAO();
			this.listaDeCondominio = this.ppDAO
					.listarCondominios(this.gerenteMB.getCodigo());
		}
		return listaDeCondominio;
	}

	public void setListaDeCondominio(List<intra_condominios> listaDeCondominio) {
		this.listaDeCondominio = listaDeCondominio;
	}

	public List<intra_grupo_gerente> getListaDeGerentes() {
		if (this.listaDeGerentes == null) {
			this.listaDeGerentes = this.retornaGerentes();
		}
		return listaDeGerentes;
	}

	public void setListaDeGerentes(List<intra_grupo_gerente> listaDeGerentes) {
		this.listaDeGerentes = listaDeGerentes;
	}

	public String getNomeCondo() {
		return nomeCondo;
	}

	public void setNomeCondo(String nomeCondo) {
		this.nomeCondo = nomeCondo;
	}

	public intra_projetar_orcamento getIpoBEAN() {
		return ipoBEAN;
	}

	public void setIpoBEAN(intra_projetar_orcamento ipoBEAN) {
		this.ipoBEAN = ipoBEAN;
	}

	public int getMedia() {
		return media;
	}

	public void setMedia(int media) {
		this.media = media;
	}

	public boolean isReutilizarPrevisao() {
		return reutilizarPrevisao;
	}

	public void setReutilizarPrevisao(boolean reutilizarPrevisao) {
		this.reutilizarPrevisao = reutilizarPrevisao;
	}

	public int getReutPrevOrc() {
		return reutPrevOrc;
	}

	public void setReutPrevOrc(int reutPrevOrc) {
		this.reutPrevOrc = reutPrevOrc;
	}

	public String getNomeCondo2() {
		return nomeCondo2;
	}

	public void setNomeCondo2(String nomeCondo2) {
		this.nomeCondo2 = nomeCondo2;
	}

	public intra_previsao_orcamentaria getPrevBEAN() {
		return prevBEAN;
	}

	public void setPrevBEAN(intra_previsao_orcamentaria prevBEAN) {
		this.prevBEAN = prevBEAN;
	}

	public boolean isAdicionarItem() {
		return adicionarItem;
	}

	public void setAdicionarItem(boolean adicionarItem) {
		this.adicionarItem = adicionarItem;
	}

	public double getValorFunc() {
		return valorFunc;
	}

	public void setValorFunc(double valorFunc) {
		this.valorFunc = valorFunc;
	}

	public intra_projetar_orcamento getAdicionarNovoBEAN() {
		return adicionarNovoBEAN;
	}

	public void setAdicionarNovoBEAN(intra_projetar_orcamento adicionarNovoBEAN) {
		this.adicionarNovoBEAN = adicionarNovoBEAN;
	}

	public List<intra_previsao_orcamentaria> getListaPrevisaoOrcamentaria() {
		return listaPrevisaoOrcamentaria;
	}

	public void setListaPrevisaoOrcamentaria(
			List<intra_previsao_orcamentaria> listaPrevisaoOrcamentaria) {
		this.listaPrevisaoOrcamentaria = listaPrevisaoOrcamentaria;
	}

	public String getMesProjecao() {
		return mesProjecao;
	}

	public void setMesProjecao(String mesProjecao) {
		this.mesProjecao = mesProjecao;
	}

	public List<PrevisaoCapaAUX> getListaDePrevisaoCapa() {
		return listaDePrevisaoCapa;
	}

	public void setListaDePrevisaoCapa(List<PrevisaoCapaAUX> listaDePrevisaoCapa) {
		this.listaDePrevisaoCapa = listaDePrevisaoCapa;
	}

	public PrevisaoCapaAUX getPrevisaoCapaAUXBEAN() {
		return previsaoCapaAUXBEAN;
	}

	public void setPrevisaoCapaAUXBEAN(PrevisaoCapaAUX previsaoCapaAUXBEAN) {
		this.previsaoCapaAUXBEAN = previsaoCapaAUXBEAN;
	}

	//METODOS
	public List<intra_grupo_gerente> retornaGerentes() {
		if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
			if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome()
					.equals(" Todos")) {
				this.listaDeCondominio = null;
				this.nomeCondo = "";
				this.icBEAN = new intra_condominios();
				this.gerenteMB.setCodigo(this.sessaoMB.getListaDeGerente()
						.get(0).getCodigo());
				return this.sessaoMB.getListaDeGerente();
			} else {
				this.listaDeCondominio = null;
				this.nomeCondo = "";
				this.icBEAN= new intra_condominios();
				this.gerenteMB.setCodigo(this.sessaoMB.getListaDeGerente()
						.get(0).getCodigo());
				return this.sessaoMB.getUsuario().getGrupoGer();
			}
		} else {
			return null;
		}
	}
	
	public void retornaNomeCondominio() {
		for (intra_condominios c : sessaoMB.getListaDeCondominios()) {
			if (c.getCodigo() == this.icBEAN.getCodigo()) {
				this.nomeCondo = c.getNome();
				this.icBEAN.setNomeGerente(c.getNomeGerente());
				this.icBEAN.setEmailGerente(c.getEmailGerente());
				this.icBEAN.setCodigoGerente(c.getCodigoGerente());
				this.icBEAN.setNome(c.getNome());
			}
		}
	}
	
	public void retornaNomeCondominio2() {
		for (intra_condominios c : sessaoMB.getListaDeCondominios()) {
			if (c.getCodigo() == this.reutPrevOrc) {
				this.nomeCondo2 = c.getNome();
			}
		}
	}
	
	public void carregarProjecao() {
		this.ppDAO = new ProjetarPrevisaoDAO();
		this.ipoBEAN = new intra_projetar_orcamento();
		if (this.icBEAN.getCodigo() > 0) {
			if (this.ipoBEAN != null) {
				if(this.media > 0){
					if(!this.adicionarItem){
						//this.ipoBEAN = this.carregarProjecao(this.media, this.icBEAN);
					}
				}
			}
		}
	}
	
	public void carregarPrevisaoProjecao() {
		this.ppDAO = new ProjetarPrevisaoDAO();
		this.ipoBEAN = new intra_projetar_orcamento();
		if (this.icBEAN.getCodigo() > 0) {
			if (this.ipoBEAN != null) {
				if(this.media > 0){
					if(!this.adicionarItem){
						this.listaDePrevisaoCapa = this.carregarProjecao(this.media, this.icBEAN);
					}
				}
			}
		}
	}
	
	public void adicionarItens(){
		this.ipoBEAN = new intra_projetar_orcamento();
			this.ppDAO = new ProjetarPrevisaoDAO();
			this.listaPrevisaoOrcamentaria = this.ppDAO.listarPrevisao(this.icBEAN.getCodigo());
			if(!this.listaPrevisaoOrcamentaria.isEmpty()){
				for (intra_previsao_orcamentaria i : this.listaPrevisaoOrcamentaria) {
					this.prevBEAN = i;
				}
			}
	}
	
	public void salvarPrevisao(int valor){
		this.ppDAO = new ProjetarPrevisaoDAO();
		
		if(valor == 1){
			this.ppDAO.excluirUltimaProjecao(this.icBEAN.getCodigo(), this.sessaoMB);
		}
		
		if(valor == 2){
			this.adicionarItem = true;
			this.ipoBEAN.setCondominio(this.icBEAN.getCodigo());
		}
		
		this.cp = new CalcularPrevisao();
		
		String mesInicialProjecao = mesProjecao.substring(0, 2);
		String anoinicialProjecao = mesProjecao.substring(3,7);
		
		
		@SuppressWarnings("deprecation")
		Date data = new Date(mesInicialProjecao+"/01/"+anoinicialProjecao);
		this.ipoBEAN.setMesProjecao(data);
		
		//FUNCIONARIOS
		//SALARIOS
		if(this.ipoBEAN.isConta01()){this.valorFunc += this.ipoBEAN.getValorConta01();}
		if(this.ipoBEAN.isConta03()){this.valorFunc += this.ipoBEAN.getValorConta03();}
		if(this.ipoBEAN.isConta04()){this.valorFunc += this.ipoBEAN.getValorConta04();}
		if(this.ipoBEAN.isConta07()){this.valorFunc += this.ipoBEAN.getValorConta07();}
		if(this.ipoBEAN.isConta08()){this.valorFunc += this.ipoBEAN.getValorConta08();}
		if(this.ipoBEAN.isConta01() || this.ipoBEAN.isConta03() || this.ipoBEAN.isConta04() || 
				this.ipoBEAN.isConta07() || this.ipoBEAN.isConta08()){
				this.cp.calcSavePrevisaoFunc(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa0(), this.ipoBEAN.getMesProjecao(), 
						true, valorFunc, "SALARIO", 1400, this.ipoBEAN.getMesReajFunc(), this.ipoBEAN.getIndiceReajFunc(), "01", this.adicionarItem, this.sessaoMB);
		}
		this.valorFunc = 0.0;
		
		//ADIANTAMENTO SALARIAL
		if(this.ipoBEAN.isConta02()){
			this.cp.calcSavePrevisaoFunc(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), 
					this.ipoBEAN.getCapa0(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta02(), 
					this.ipoBEAN.getValorConta02(), this.ipoBEAN.getNomeConta02(), 1400, this.ipoBEAN.getMesReajFunc(), 
					this.ipoBEAN.getIndiceReajFunc(),"02", this.adicionarItem, this.sessaoMB);
		}
		this.valorFunc = 0.0;
		
		//ENCARGOS SOCIAIS
		if(this.ipoBEAN.isConta09()){this.valorFunc += this.ipoBEAN.getValorConta09();}
		if(this.ipoBEAN.isConta010()){this.valorFunc += this.ipoBEAN.getValorConta010();}
		if(this.ipoBEAN.isConta011()){this.valorFunc += this.ipoBEAN.getValorConta011();}
		if(this.ipoBEAN.isConta012()){this.valorFunc += this.ipoBEAN.getValorConta012();}
		if(this.ipoBEAN.isConta013()){this.valorFunc += this.ipoBEAN.getValorConta013();}
		if(this.ipoBEAN.isConta014()){this.valorFunc += this.ipoBEAN.getValorConta014();}
		if(this.ipoBEAN.isConta015()){this.valorFunc += this.ipoBEAN.getValorConta015();}
		if(this.ipoBEAN.isConta016()){this.valorFunc += this.ipoBEAN.getValorConta016();}
		if(this.ipoBEAN.isConta017()){this.valorFunc += this.ipoBEAN.getValorConta017();}
		if(this.ipoBEAN.isConta018()){this.valorFunc += this.ipoBEAN.getValorConta018();}
		if(this.ipoBEAN.isConta019()){this.valorFunc += this.ipoBEAN.getValorConta019();}
		if(this.ipoBEAN.isConta020()){this.valorFunc += this.ipoBEAN.getValorConta020();}
		if(this.ipoBEAN.isConta021()){this.valorFunc += this.ipoBEAN.getValorConta021();}
		if(this.ipoBEAN.isConta022()){this.valorFunc += this.ipoBEAN.getValorConta022();}
		if(this.ipoBEAN.isConta023()){this.valorFunc += this.ipoBEAN.getValorConta023();}
		if(this.ipoBEAN.isConta024()){this.valorFunc += this.ipoBEAN.getValorConta024();}
		if(this.ipoBEAN.isConta025()){this.valorFunc += this.ipoBEAN.getValorConta025();}
		if(this.ipoBEAN.isConta038()){this.valorFunc += this.ipoBEAN.getValorConta038();}
		if(this.ipoBEAN.isConta09() || this.ipoBEAN.isConta010() || this.ipoBEAN.isConta011() || 
				this.ipoBEAN.isConta012() || this.ipoBEAN.isConta013() || this.ipoBEAN.isConta014() || 
				this.ipoBEAN.isConta015() || this.ipoBEAN.isConta016() || this.ipoBEAN.isConta017() || 
				this.ipoBEAN.isConta018() || this.ipoBEAN.isConta019() || this.ipoBEAN.isConta020() || 
				this.ipoBEAN.isConta021() || this.ipoBEAN.isConta022() || this.ipoBEAN.isConta023() || 
				this.ipoBEAN.isConta024() || this.ipoBEAN.isConta025() || this.ipoBEAN.isConta038()){
			
			this.cp.calcSavePrevisaoFunc(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), 
					this.ipoBEAN.getCapa0(), this.ipoBEAN.getMesProjecao(), true, this.valorFunc, "ENCARGOS SOCIAIS", 1400, 
					this.ipoBEAN.getMesReajFunc(), this.ipoBEAN.getIndiceReajFunc(), "03", this.adicionarItem, this.sessaoMB);
		}
		this.valorFunc = 0.0;
		
		//BENEFICIOS
		if(this.ipoBEAN.isConta026()){this.valorFunc += this.ipoBEAN.getValorConta026();}
		if(this.ipoBEAN.isConta027()){this.valorFunc += this.ipoBEAN.getValorConta027();}
		if(this.ipoBEAN.isConta028()){this.valorFunc += this.ipoBEAN.getValorConta028();}
		if(this.ipoBEAN.isConta029()){this.valorFunc += this.ipoBEAN.getValorConta029();}
		if(this.ipoBEAN.isConta026() || this.ipoBEAN.isConta027() || this.ipoBEAN.isConta028() || this.ipoBEAN.isConta029()){
			if(!this.adicionarItem){
			this.cp.calcSavePrevisaoFunc(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), 
					this.ipoBEAN.getCapa0(), this.ipoBEAN.getMesProjecao(), true, this.valorFunc, "BENEFICIOS (VT.VA.VR./ASSIST. MEDICA/OUTROS)", 1400, 
					this.ipoBEAN.getMesReajFunc(), this.ipoBEAN.getIndiceReajFunc(), "04", this.adicionarItem, this.sessaoMB);
			}
		}
		this.valorFunc = 0.0;
		
		//CONTRIBUIÇÃO SINDICAL
		if(this.ipoBEAN.isConta030()){this.valorFunc += this.ipoBEAN.getValorConta030();}
		if(this.ipoBEAN.isConta031()){this.valorFunc += this.ipoBEAN.getValorConta031();}
		if(this.ipoBEAN.isConta030() || this.ipoBEAN.isConta031()){
			this.cp.calcSavePrevisaoFunc(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), 
					this.ipoBEAN.getCapa0(), this.ipoBEAN.getMesProjecao(), true, this.valorFunc, "CONTRIBUIÇÃO SINDICAL", 1400, 
					this.ipoBEAN.getMesReajFunc(), this.ipoBEAN.getIndiceReajFunc(), "05", this.adicionarItem, this.sessaoMB);
		}
		this.valorFunc = 0.0;
		
		//NR'S NORMAS REGULAMENTADORAS
		if(this.ipoBEAN.isConta032()){this.valorFunc += this.ipoBEAN.getValorConta032();}
		if(this.ipoBEAN.isConta033()){this.valorFunc += this.ipoBEAN.getValorConta033();}
		if(this.ipoBEAN.isConta034()){this.valorFunc += this.ipoBEAN.getValorConta034();}
		if(this.ipoBEAN.isConta035()){this.valorFunc += this.ipoBEAN.getValorConta035();}
		if(this.ipoBEAN.isConta036()){this.valorFunc += this.ipoBEAN.getValorConta036();}
		if(this.ipoBEAN.isConta037()){this.valorFunc += this.ipoBEAN.getValorConta037();}
		if(this.ipoBEAN.isConta032() || this.ipoBEAN.isConta033() || this.ipoBEAN.isConta034() 
				|| this.ipoBEAN.isConta035() || this.ipoBEAN.isConta036() || this.ipoBEAN.isConta037()){
			
			this.cp.calcSavePrevisaoFunc(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa0(), this.ipoBEAN.getMesProjecao(), 
					true, this.valorFunc, "NRS - NORMAS REGULAMENTADORAS", 1400, this.ipoBEAN.getMesReajFunc(), this.ipoBEAN.getIndiceReajFunc(), "06", this.adicionarItem, this.sessaoMB);
		}
		this.valorFunc = 0.0;
		
		//RESCISAO
		if(this.ipoBEAN.isConta05()){
			this.cp.calcSavePrevisaoFunc(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), 
					this.ipoBEAN.getCapa0(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta05(), 
					this.ipoBEAN.getValorConta05(), this.ipoBEAN.getNomeConta05(), 1400, this.ipoBEAN.getMesReajFunc(), 
					this.ipoBEAN.getIndiceReajFunc(), "07", this.adicionarItem, this.sessaoMB);
		}
		
		//ACORDO TRABALHISTA
		if(this.ipoBEAN.isConta06()){
			this.cp.calcSavePrevisaoFunc(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), 
					this.ipoBEAN.getCapa0(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta06(), 
					this.ipoBEAN.getValorConta06(), this.ipoBEAN.getNomeConta06(), 1400, this.ipoBEAN.getMesReajFunc(), 
					this.ipoBEAN.getIndiceReajFunc(), "08", this.adicionarItem, this.sessaoMB);
		}
		
		//FERIAS
		//FUNCIONARIOS / OUTROS
		if(this.ipoBEAN.isConta11()){
			this.cp.calcSavePrevisao1(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), 
					this.ipoBEAN.getCapa0(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta11(), 
					this.ipoBEAN.getFeriasJan(), this.ipoBEAN.getFeriasFev(), this.ipoBEAN.getFeriasMar(), 
					this.ipoBEAN.getFeriasAbr(), this.ipoBEAN.getFeriasMai(), this.ipoBEAN.getFeriasJun(), 
					this.ipoBEAN.getFeriasJul(), this.ipoBEAN.getFeriasAgo(), this.ipoBEAN.getFeriasSet(),
					this.ipoBEAN.getFeriasOut(), this.ipoBEAN.getFeriasNov(), this.ipoBEAN.getFeriasDez(),
					this.ipoBEAN.getNomeConta11(), 1400, "11", this.sessaoMB);
		}
		
		this.valorFunc = 0.0;
		
		//13º SALARIO(ENCARGOS)
		if(this.ipoBEAN.isConta13()){
			this.valorFunc += this.ipoBEAN.getValorConta13();
			/*this.cp.calcSavePrevisao5(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa0(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta13(), 
			 * this.ipoBEAN.getValorConta13(), this.ipoBEAN.getNomeConta13(), 1400, "13");*/
		}
		
		if(this.ipoBEAN.isConta12()){
			if(!this.adicionarItem){
			this.cp.calcSavePrevisao2(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa0(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta12(), 
					this.ipoBEAN.getDecimo13Nov(), (this.ipoBEAN.getDecimo13Dez() + this.valorFunc),
					"13º SALARIO (ENCARGOS)", 1400, "12", this.sessaoMB);
			}
		}
		
		//CESTA DE NATAL
		if(this.ipoBEAN.isConta14()){
			this.cp.calcSavePrevisao4(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa0(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta14(), this.ipoBEAN.getValorConta14(),
					this.ipoBEAN.getNomeConta14(), 1400, "14", this.sessaoMB);
		}
		
		
		//TERCEIRIZAÇÃO
			
		if(this.ipoBEAN.isConta21()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa2(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta21(), this.ipoBEAN.getValorConta21(), this.ipoBEAN.getNomeConta21(), 1405, this.ipoBEAN.getMesReajTerc(), this.ipoBEAN.getIndiceReajTerc(), "21", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta22()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa2(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta22(), this.ipoBEAN.getValorConta22(), this.ipoBEAN.getNomeConta22(), 1405, this.ipoBEAN.getMesReajTerc(), this.ipoBEAN.getIndiceReajTerc(), "22", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta23()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa2(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta23(), this.ipoBEAN.getValorConta23(), this.ipoBEAN.getNomeConta23(), 1405, this.ipoBEAN.getMesReajTerc(), this.ipoBEAN.getIndiceReajTerc(), "23", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta24()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa2(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta24(), this.ipoBEAN.getValorConta24(), this.ipoBEAN.getNomeConta24(), 1405, this.ipoBEAN.getMesReajTerc(), this.ipoBEAN.getIndiceReajTerc(), "24", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta25()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa2(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta25(), this.ipoBEAN.getValorConta25(), this.ipoBEAN.getNomeConta25(), 1405, this.ipoBEAN.getMesReajTerc(), this.ipoBEAN.getIndiceReajTerc(), "25", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta26()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa2(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta26(), this.ipoBEAN.getValorConta26(), this.ipoBEAN.getNomeConta26(), 1405, this.ipoBEAN.getMesReajTerc(), this.ipoBEAN.getIndiceReajTerc(), "26", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta27()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa2(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta27(), this.ipoBEAN.getValorConta27(), this.ipoBEAN.getNomeConta27(), 1405, this.ipoBEAN.getMesReajTerc(), this.ipoBEAN.getIndiceReajTerc(), "27", this.sessaoMB);
		}
		
		//MANUTENÇÃO
		if(this.ipoBEAN.isConta31()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta31(), this.ipoBEAN.getValorConta31(), this.ipoBEAN.getNomeConta31(), 1406, this.ipoBEAN.getMesReajMan31(), this.ipoBEAN.getIndiceReajMan31(), "31", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta32()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta32(), this.ipoBEAN.getValorConta32(), this.ipoBEAN.getNomeConta32(), 1406, this.ipoBEAN.getMesReajMan32(), this.ipoBEAN.getIndiceReajMan32(), "32", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta33()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta33(), this.ipoBEAN.getValorConta33(), this.ipoBEAN.getNomeConta33(), 1406, this.ipoBEAN.getMesReajMan33(), this.ipoBEAN.getIndiceReajMan33(), "33", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta34()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta34(), this.ipoBEAN.getValorConta34(), this.ipoBEAN.getNomeConta34(), 1406, this.ipoBEAN.getMesReajMan34(), this.ipoBEAN.getIndiceReajMan34(), "34", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta35()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta35(), this.ipoBEAN.getValorConta35(), this.ipoBEAN.getNomeConta35(), 1406, this.ipoBEAN.getMesReajMan35(), this.ipoBEAN.getIndiceReajMan35(), "35", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta36()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta36(), this.ipoBEAN.getValorConta36(), this.ipoBEAN.getNomeConta36(), 1406, this.ipoBEAN.getMesReajMan36(), this.ipoBEAN.getIndiceReajMan36(), "36", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta37()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta37(), this.ipoBEAN.getValorConta37(), this.ipoBEAN.getNomeConta37(), 1406, this.ipoBEAN.getMesReajMan37(), this.ipoBEAN.getIndiceReajMan37(), "37", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta38()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta38(), this.ipoBEAN.getValorConta38(), this.ipoBEAN.getNomeConta38(), 1406, this.ipoBEAN.getMesReajMan38(), this.ipoBEAN.getIndiceReajMan38(), "38", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta39()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta39(), this.ipoBEAN.getValorConta39(), this.ipoBEAN.getNomeConta39(), 1406, this.ipoBEAN.getMesReajMan39(), this.ipoBEAN.getIndiceReajMan39(), "39", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta310()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta310(), this.ipoBEAN.getValorConta310(), this.ipoBEAN.getNomeConta310(), 1406, this.ipoBEAN.getMesReajMan310(), this.ipoBEAN.getIndiceReajMan310(), "310", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta311()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta311(), this.ipoBEAN.getValorConta311(), this.ipoBEAN.getNomeConta311(), 1406, this.ipoBEAN.getMesReajMan311(), this.ipoBEAN.getIndiceReajMan311(), "311", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta312()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta312(), this.ipoBEAN.getValorConta312(), this.ipoBEAN.getNomeConta312(), 1406, this.ipoBEAN.getMesReajMan312(), this.ipoBEAN.getIndiceReajMan312(), "312", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta313()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta313(), this.ipoBEAN.getValorConta313(), this.ipoBEAN.getNomeConta313(), 1406, this.ipoBEAN.getMesReajMan313(), this.ipoBEAN.getIndiceReajMan313(), "313", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta314()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta314(), this.ipoBEAN.getValorConta314(), this.ipoBEAN.getNomeConta314(), 1406, this.ipoBEAN.getMesReajMan314(), this.ipoBEAN.getIndiceReajMan314(), "314", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta315()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta315(), this.ipoBEAN.getValorConta315(), this.ipoBEAN.getNomeConta315(), 1406, this.ipoBEAN.getMesReajMan315(), this.ipoBEAN.getIndiceReajMan315(), "315", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta316()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta316(), this.ipoBEAN.getValorConta316(), this.ipoBEAN.getNomeConta316(), 1406, this.ipoBEAN.getMesReajMan316(), this.ipoBEAN.getIndiceReajMan316(), "316", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta317()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta317(), this.ipoBEAN.getValorConta317(), this.ipoBEAN.getNomeConta317(), 1406, this.ipoBEAN.getMesReajMan317(), this.ipoBEAN.getIndiceReajMan317(), "317", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta318()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta318(), this.ipoBEAN.getValorConta318(), this.ipoBEAN.getNomeConta318(), 1406, this.ipoBEAN.getMesReajMan318(), this.ipoBEAN.getIndiceReajMan318(), "318", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta319()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta319(), this.ipoBEAN.getValorConta319(), this.ipoBEAN.getNomeConta319(), 1406, this.ipoBEAN.getMesReajMan319(), this.ipoBEAN.getIndiceReajMan319(), "319", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta320()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta320(), this.ipoBEAN.getValorConta320(), this.ipoBEAN.getNomeConta320(), 1406, this.ipoBEAN.getMesReajMan320(), this.ipoBEAN.getIndiceReajMan320(), "320", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta321()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta321(), this.ipoBEAN.getValorConta321(), this.ipoBEAN.getNomeConta321(), 1406, this.ipoBEAN.getMesReajMan321(), this.ipoBEAN.getIndiceReajMan321(), "321", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta322()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta322(), this.ipoBEAN.getValorConta322(), this.ipoBEAN.getNomeConta322(), 1406, this.ipoBEAN.getMesReajMan322(), this.ipoBEAN.getIndiceReajMan322(), "322", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta323()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta323(), this.ipoBEAN.getValorConta323(), this.ipoBEAN.getNomeConta323(), 1406, this.ipoBEAN.getMesReajMan323(), this.ipoBEAN.getIndiceReajMan323(), "323", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta324()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta324(), this.ipoBEAN.getValorConta324(), this.ipoBEAN.getNomeConta324(), 1406, this.ipoBEAN.getMesReajMan324(), this.ipoBEAN.getIndiceReajMan324(), "324", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta325()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta325(), this.ipoBEAN.getValorConta325(), this.ipoBEAN.getNomeConta325(), 1406, this.ipoBEAN.getMesReajMan325(), this.ipoBEAN.getIndiceReajMan325(), "325", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta326()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta326(), this.ipoBEAN.getValorConta326(), this.ipoBEAN.getNomeConta326(), 1406, this.ipoBEAN.getMesReajMan326(), this.ipoBEAN.getIndiceReajMan326(), "326", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta327()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta327(), this.ipoBEAN.getValorConta327(), this.ipoBEAN.getNomeConta327(), 1406, this.ipoBEAN.getMesReajMan327(), this.ipoBEAN.getIndiceReajMan327(), "327", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta328()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta328(), this.ipoBEAN.getValorConta328(), this.ipoBEAN.getNomeConta328(), 1406, this.ipoBEAN.getMesReajMan328(), this.ipoBEAN.getIndiceReajMan328(), "328", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta329()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta329(), this.ipoBEAN.getValorConta329(), this.ipoBEAN.getNomeConta329(), 1406, this.ipoBEAN.getMesReajMan329(), this.ipoBEAN.getIndiceReajMan329(), "329", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta330()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta330(), this.ipoBEAN.getValorConta330(), this.ipoBEAN.getNomeConta330(), 1406, this.ipoBEAN.getMesReajMan330(), this.ipoBEAN.getIndiceReajMan330(), "330", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta331()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa3(), this.ipoBEAN.getMesProjecao(), 
					this.ipoBEAN.isConta331(), this.ipoBEAN.getValorConta331(), this.ipoBEAN.getNomeConta331(), 1406, this.ipoBEAN.getMesReajMan331(), this.ipoBEAN.getIndiceReajMan331(), "331", this.sessaoMB);
		}
		
		// CONSERVAÇÃO PREDIAL
		if(this.ipoBEAN.isConta41()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa4(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta41(), this.ipoBEAN.getValorConta41(),
					this.ipoBEAN.getNomeConta41(), 1407, "41", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta42()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa4(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta42(), this.ipoBEAN.getValorConta42(),
					this.ipoBEAN.getNomeConta42(), 1407, "42", this.sessaoMB);
		}
		
		// MATERIAL DE CONSUMO
		if(this.ipoBEAN.isConta51()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa5(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta51(), this.ipoBEAN.getValorConta51(),
				this.ipoBEAN.getNomeConta51(), 1408, "51", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta52()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa5(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta52(), this.ipoBEAN.getValorConta52(),
				this.ipoBEAN.getNomeConta52(), 1408, "52", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta53()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa5(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta53(), this.ipoBEAN.getValorConta53(),
				this.ipoBEAN.getNomeConta53(), 1408, "53", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta54()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa5(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta54(), this.ipoBEAN.getValorConta54(),
				this.ipoBEAN.getNomeConta54(), 1408, "54", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta55()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa5(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta55(), this.ipoBEAN.getValorConta55(),
				this.ipoBEAN.getNomeConta55(), 1408, "55", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta56()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa5(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta56(), this.ipoBEAN.getValorConta56(),
				this.ipoBEAN.getNomeConta56(), 1408, "56", this.sessaoMB);
		}
		
		// CONSUMO
		if(this.ipoBEAN.isConta61()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa6(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta61(), this.ipoBEAN.getValorConta61(),
				this.ipoBEAN.getNomeConta61(), 1409, "61", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta62()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa6(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta62(), this.ipoBEAN.getValorConta62(),
				this.ipoBEAN.getNomeConta62(), 1409, "62", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta63()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa6(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta63(), this.ipoBEAN.getValorConta63(),
				this.ipoBEAN.getNomeConta63(), 1409, "63", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta64()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa6(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta64(), this.ipoBEAN.getValorConta64(),
				this.ipoBEAN.getNomeConta64(), 1409, "64", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta65()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa6(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta65(), this.ipoBEAN.getValorConta65(),
				this.ipoBEAN.getNomeConta65(), 1409, "65", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta66()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa6(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta66(), this.ipoBEAN.getValorConta66(),
				this.ipoBEAN.getNomeConta66(), 1409, "66", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta67()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa6(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta67(), this.ipoBEAN.getValorConta67(),
				this.ipoBEAN.getNomeConta67(), 1409, "67", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta68()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa6(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta68(), this.ipoBEAN.getValorConta68(),
				this.ipoBEAN.getNomeConta68(), 1409, "68", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta69()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa6(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta69(), this.ipoBEAN.getValorConta69(),
				this.ipoBEAN.getNomeConta69(), 1409, "69", this.sessaoMB);
		}
		
		// ADMINISTRATIVO
		if(this.ipoBEAN.isConta71()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa7(), this.ipoBEAN.getMesProjecao(), 
			this.ipoBEAN.isConta71(), this.ipoBEAN.getValorConta71(), this.ipoBEAN.getNomeConta71(), 1410, this.ipoBEAN.getMesReajAdm71(), this.ipoBEAN.getIndiceReajAdm71(), "71", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta72()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa7(), this.ipoBEAN.getMesProjecao(), 
			this.ipoBEAN.isConta72(), this.ipoBEAN.getValorConta72(), this.ipoBEAN.getNomeConta72(), 1410, this.ipoBEAN.getMesReajAdm72(), this.ipoBEAN.getIndiceReajAdm72(), "72", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta73()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa7(), this.ipoBEAN.getMesProjecao(), 
			this.ipoBEAN.isConta73(), this.ipoBEAN.getValorConta73(), this.ipoBEAN.getNomeConta73(), 1410, this.ipoBEAN.getMesReajAdm73(), this.ipoBEAN.getIndiceReajAdm73(), "73", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta74()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa7(), this.ipoBEAN.getMesProjecao(), 
			this.ipoBEAN.isConta74(), this.ipoBEAN.getValorConta74(), this.ipoBEAN.getNomeConta74(), 1410, this.ipoBEAN.getMesReajAdm74(), this.ipoBEAN.getIndiceReajAdm74(), "74", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta75()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa7(), this.ipoBEAN.getMesProjecao(), 
			this.ipoBEAN.isConta75(), this.ipoBEAN.getValorConta75(), this.ipoBEAN.getNomeConta75(), 1410, this.ipoBEAN.getMesReajAdm75(), this.ipoBEAN.getIndiceReajAdm75(), "75", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta76()){
			this.cp.calcSavePrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa7(), this.ipoBEAN.getMesProjecao(), 
			this.ipoBEAN.isConta76(), this.ipoBEAN.getValorConta76(), this.ipoBEAN.getNomeConta76(), 1410, this.ipoBEAN.getMesReajAdm76(), this.ipoBEAN.getIndiceReajAdm76(), "76", this.sessaoMB);
		}
		
		// DESPESAS OPERACIONAIS
		if(this.ipoBEAN.isConta81()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta81(), this.ipoBEAN.getValorConta81(),
			this.ipoBEAN.getNomeConta81(), 1411, "81", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta82()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta82(), this.ipoBEAN.getValorConta82(),
			this.ipoBEAN.getNomeConta82(), 1411, "82", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta83()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta83(), this.ipoBEAN.getValorConta83(),
			this.ipoBEAN.getNomeConta83(), 1411, "83", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta84()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta84(), this.ipoBEAN.getValorConta84(),
			this.ipoBEAN.getNomeConta84(), 1411, "84", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta85()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta85(), this.ipoBEAN.getValorConta85(),
			this.ipoBEAN.getNomeConta85(), 1411, "85", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta86()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta86(), this.ipoBEAN.getValorConta86(),
			this.ipoBEAN.getNomeConta86(), 1411, "86", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta87()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta87(), this.ipoBEAN.getValorConta87(),
			this.ipoBEAN.getNomeConta87(), 1411, "87", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta88()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta88(), this.ipoBEAN.getValorConta88(),
			this.ipoBEAN.getNomeConta88(), 1411, "88", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta89()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta89(), this.ipoBEAN.getValorConta89(),
			this.ipoBEAN.getNomeConta89(), 1411, "89", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta810()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta810(), this.ipoBEAN.getValorConta810(),
			this.ipoBEAN.getNomeConta810(), 1411, "810", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta811()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta811(), this.ipoBEAN.getValorConta811(),
			this.ipoBEAN.getNomeConta811(), 1411, "811", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta812()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta812(), this.ipoBEAN.getValorConta812(),
			this.ipoBEAN.getNomeConta812(), 1411, "812", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta813()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta813(), this.ipoBEAN.getValorConta813(),
			this.ipoBEAN.getNomeConta813(), 1411, "813", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta814()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta814(), this.ipoBEAN.getValorConta814(),
			this.ipoBEAN.getNomeConta814(), 1411, "814", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta815()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta815(), this.ipoBEAN.getValorConta815(),
			this.ipoBEAN.getNomeConta815(), 1411, "815", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta816()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta816(), this.ipoBEAN.getValorConta816(),
			this.ipoBEAN.getNomeConta816(), 1411, "816", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta817()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta817(), this.ipoBEAN.getValorConta817(),
			this.ipoBEAN.getNomeConta817(), 1411, "817", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta818()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta818(), this.ipoBEAN.getValorConta818(),
			this.ipoBEAN.getNomeConta818(), 1411, "818", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta819()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta819(), this.ipoBEAN.getValorConta819(),
			this.ipoBEAN.getNomeConta819(), 1411, "819", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta820()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta820(), this.ipoBEAN.getValorConta820(),
			this.ipoBEAN.getNomeConta820(), 1411, "820", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta821()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta821(), this.ipoBEAN.getValorConta821(),
			this.ipoBEAN.getNomeConta821(), 1411, "821", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta822()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta822(), this.ipoBEAN.getValorConta822(),
			this.ipoBEAN.getNomeConta822(), 1411, "822", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta823()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta823(), this.ipoBEAN.getValorConta823(),
			this.ipoBEAN.getNomeConta823(), 1411, "823", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta824()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta824(), this.ipoBEAN.getValorConta824(),
			this.ipoBEAN.getNomeConta824(), 1411, "824", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta825()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta825(), this.ipoBEAN.getValorConta825(),
			this.ipoBEAN.getNomeConta825(), 1411, "825", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta826()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta826(), this.ipoBEAN.getValorConta826(),
			this.ipoBEAN.getNomeConta826(), 1411, "826", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta827()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta827(), this.ipoBEAN.getValorConta827(),
			this.ipoBEAN.getNomeConta827(), 1411, "827", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta828()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta828(), this.ipoBEAN.getValorConta828(),
			this.ipoBEAN.getNomeConta828(), 1411, "828", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta829()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta829(), this.ipoBEAN.getValorConta829(),
			this.ipoBEAN.getNomeConta829(), 1411, "829", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta830()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta830(), this.ipoBEAN.getValorConta830(),
			this.ipoBEAN.getNomeConta830(), 1411, "830", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta831()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta831(), this.ipoBEAN.getValorConta831(),
			this.ipoBEAN.getNomeConta831(), 1411, "831", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta832()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta832(), this.ipoBEAN.getValorConta832(),
			this.ipoBEAN.getNomeConta832(), 1411, "832", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta833()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta833(), this.ipoBEAN.getValorConta833(),
			this.ipoBEAN.getNomeConta833(), 1411, "833", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta834()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta834(), this.ipoBEAN.getValorConta834(),
			this.ipoBEAN.getNomeConta834(), 1411, "834", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta835()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta835(), this.ipoBEAN.getValorConta835(),
			this.ipoBEAN.getNomeConta835(), 1411, "835", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta836()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta836(), this.ipoBEAN.getValorConta836(),
			this.ipoBEAN.getNomeConta836(), 1411, "836", this.sessaoMB);
		}
		if(this.ipoBEAN.isConta837()){
			this.cp.calcSavePrevisao3(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), this.ipoBEAN.getCapa8(), this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isConta837(), this.ipoBEAN.getValorConta837(),
			this.ipoBEAN.getNomeConta837(), 1411, "837", this.sessaoMB);
		}
		
		if(this.ipoBEAN.isInadimplencia()){
			int ano = Integer.valueOf(String.valueOf(DateTime.now().plusMonths(-this.media).getYear()));
			int anoAtual = DateTime.now().getYear();
			
			DateTime dt = new DateTime(this.ipoBEAN.getMesProjecao());
			
			int mesInicial = dt.plusMonths(-this.media).getMonthOfYear();
			DateTime primeiroDia = new DateTime(ano, mesInicial, 1, 0, 0);
			Date dataInicial = primeiroDia.toDate();
			int mesFinal = DateTime.now().getMonthOfYear();
			DateTime primeiroDia2 = new DateTime(anoAtual, mesFinal, 1, 0, 0);
			DateTime UltimoDiadoMes2 = primeiroDia2.plusDays(-1);
			Date dataFinal = UltimoDiadoMes2.toDate();
			
			PrevisaoInadimplenciaDAO pi = new PrevisaoInadimplenciaDAO();
			double valorInadimplencia = ((pi.pesquisaTaxaTotal(this.icBEAN.getCodigo(), dataInicial, dataFinal)/this.media));
			
			this.cp.calcSavePrevisao6(this.icBEAN.getCodigo(),this.gerenteMB.getCodigo(), "Inadimplência", this.ipoBEAN.getMesProjecao(), this.ipoBEAN.isInadimplencia(), valorInadimplencia, 
			"Inadimplência", 9999, "9999", this.sessaoMB);
		}
		
		if(valor ==1){
			
		intra_projetar_orcamento i = new intra_projetar_orcamento();
		i = this.ipoBEAN;
		
		i.setCondominio(this.icBEAN.getCodigo());
		i.setCodigoGerente(this.gerenteMB.getCodigo());
		i.setMes(null);
		this.ppDAO.salvarProjecao(i,this.sessaoMB);
		}
		
		this.msgSalvo();
	}
	
	public void adicionaItemNovo(){
		this.validaTela = true;
		this.adicionarItens();
	}
	
	public void voltarTela(){
		this.validaTela = false;
	}
	
}
