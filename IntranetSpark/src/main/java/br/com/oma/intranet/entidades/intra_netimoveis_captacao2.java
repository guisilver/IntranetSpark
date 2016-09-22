package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class intra_netimoveis_captacao2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4936535880425266647L;

	@Id
	@GeneratedValue
	private int id;

	private int codigoCondominio;
	@Column(columnDefinition = "varchar(50)")
	private String tipoImovel;
	@Column(columnDefinition = "numeric(12,2)")
	private double precoImovel;
	@Column(columnDefinition = "datetime")
	private Date data;
	private boolean exclusividade;
	private boolean autorizacao;
	private boolean venda;
	private boolean aluguel;
	@Column(columnDefinition = "varchar(50)")
	private String bairro;
	private int nroSalas;
	private int nroQuartos;
	@Column(columnDefinition = "varchar(10)")
	private String bhSocial;
	@Column(columnDefinition = "varchar(10)")
	private String varanda;
	private int vagaLivre;
	private int vagaPresa;
	@Column(columnDefinition = "varchar(100)")
	private String endereco;
	@Column(columnDefinition = "varchar(10)")
	private String cep;
	private int nroEndereco;
	@Column(columnDefinition = "varchar(20)")
	private String unidade;
	private int idade;
	@Column(columnDefinition = "varchar(150)")
	private String edificio;
	@Column(columnDefinition = "numeric(12,2)")
	private double condominioValor;
	@Column(columnDefinition = "numeric(12,2)")
	private double iptuValor;
	@Column(columnDefinition = "varchar(50)")
	private String indiceCadastral;
	@Column(columnDefinition = "varchar(15)")
	private String posicao;
	@Column(columnDefinition = "varchar(50)")
	private String fachadaFrontal;
	@Column(columnDefinition = "varchar(50)")
	private String fachadaLateral;
	@Column(columnDefinition = "varchar(20)")
	private String sindicoTelefone;
	@Column(columnDefinition = "varchar(20)")
	private String conservadoraTelefone;
	private int nroUnidades;
	private int nroPavimentos;
	private int unidsPorAndar;
	@Column(columnDefinition = "varchar(20)")
	private String areaRealPriv;
	@Column(columnDefinition = "varchar(20)")
	private String areaConstruida;
	@Column(columnDefinition = "varchar(20)")
	private String areaLote;
	@Column(columnDefinition = "varchar(20)")
	private String frenteLote;
	@Column(columnDefinition = "varchar(20)")
	private String zonaUso;
	@Column(columnDefinition = "varchar(30)")
	private String aproveitamento;
	@Column(columnDefinition = "varchar(300)")
	private String referencia;
	@Column(columnDefinition = "varchar(300)")
	private String melhorAcesso;
	@Column(columnDefinition = "varchar(20)")
	private String imovelOcupado;
	private boolean imovelVago;
	@Column(columnDefinition = "varchar(75)")
	private String nomeLocatario;
	@Column(columnDefinition = "varchar(20)")
	private String telefone;
	@Column(columnDefinition = "varchar(20)")
	private String chaves;
	@Column(columnDefinition = "varchar(50)")
	private String horaVisita;
	@Column(columnDefinition = "varchar(300)")
	private String beneficios;
	private boolean arCondicionado;
	private boolean portaoEletronico;
	private boolean portariaPermanente;
	private boolean quadraEsportes;
	private boolean elevadorSocial;
	private boolean academia;
	private boolean salaMassagem;
	private boolean salaoFestas;
	private boolean salaoJogos;
	private boolean sauna;
	private boolean solDeManha;
	private boolean piscinas;
	private boolean playground;
	private boolean churrasqEspGourmet;
	private boolean circuitoTV;
	private boolean elevadorServico;
	private boolean esquadriasAluminio;
	private boolean gasCanalizado;
	private boolean hallSocialDecorado;
	private boolean interfone;
	private boolean janelaComVenezianas;
	private boolean jardins;
	private boolean mobilhado;
	private boolean andarAlto;
	@Column(columnDefinition = "varchar(200)")
	private String descrevaPredio;
	@Column(columnDefinition = "varchar(75)")
	private String construtora;
	@Column(columnDefinition = "varchar(750)")
	private String descrevaImovelOrdem;
	@Column(columnDefinition = "varchar(20)")
	private String documentacao;
	@Column(columnDefinition = "numeric(12,2)")
	private double valorEscritura;
	@Column(columnDefinition = "varchar(150)")
	private String condicoesPagto;
	@Column(columnDefinition = "varchar(50)")
	private String estudaPermuta;
	@Column(columnDefinition = "varchar(150)")
	private String motivoVenda;
	@Column(columnDefinition = "varchar(75)")
	private String captadorCelular;
	@Column(columnDefinition = "numeric(12,2)")
	private double avaliacaoCorretora;
	@Column(columnDefinition = "numeric(12,2)")
	private double custoM3;
	@Column(columnDefinition = "datetime")
	private Date ultimaConfirmacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigoCondominio() {
		return codigoCondominio;
	}

	public void setCodigoCondominio(int codigoCondominio) {
		this.codigoCondominio = codigoCondominio;
	}

	public String getTipoImovel() {
		return tipoImovel;
	}

	public void setTipoImovel(String tipoImovel) {
		this.tipoImovel = tipoImovel;
	}

	public double getPrecoImovel() {
		return precoImovel;
	}

	public void setPrecoImovel(double precoImovel) {
		this.precoImovel = precoImovel;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isExclusividade() {
		return exclusividade;
	}

	public void setExclusividade(boolean exclusividade) {
		this.exclusividade = exclusividade;
	}

	public boolean isAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(boolean autorizacao) {
		this.autorizacao = autorizacao;
	}

	public boolean isVenda() {
		return venda;
	}

	public void setVenda(boolean venda) {
		this.venda = venda;
	}

	public boolean isAluguel() {
		return aluguel;
	}

	public void setAluguel(boolean aluguel) {
		this.aluguel = aluguel;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getNroSalas() {
		return nroSalas;
	}

	public void setNroSalas(int nroSalas) {
		this.nroSalas = nroSalas;
	}

	public int getNroQuartos() {
		return nroQuartos;
	}

	public void setNroQuartos(int nroQuartos) {
		this.nroQuartos = nroQuartos;
	}

	public String getBhSocial() {
		return bhSocial;
	}

	public void setBhSocial(String bhSocial) {
		this.bhSocial = bhSocial;
	}

	public String getVaranda() {
		return varanda;
	}

	public void setVaranda(String varanda) {
		this.varanda = varanda;
	}

	public int getVagaLivre() {
		return vagaLivre;
	}

	public void setVagaLivre(int vagaLivre) {
		this.vagaLivre = vagaLivre;
	}

	public int getVagaPresa() {
		return vagaPresa;
	}

	public void setVagaPresa(int vagaPresa) {
		this.vagaPresa = vagaPresa;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public int getNroEndereco() {
		return nroEndereco;
	}

	public void setNroEndereco(int nroEndereco) {
		this.nroEndereco = nroEndereco;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	public double getCondominioValor() {
		return condominioValor;
	}

	public void setCondominioValor(double condominioValor) {
		this.condominioValor = condominioValor;
	}

	public double getIptuValor() {
		return iptuValor;
	}

	public void setIptuValor(double iptuValor) {
		this.iptuValor = iptuValor;
	}

	public String getIndiceCadastral() {
		return indiceCadastral;
	}

	public void setIndiceCadastral(String indiceCadastral) {
		this.indiceCadastral = indiceCadastral;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public String getFachadaFrontal() {
		return fachadaFrontal;
	}

	public void setFachadaFrontal(String fachadaFrontal) {
		this.fachadaFrontal = fachadaFrontal;
	}

	public String getFachadaLateral() {
		return fachadaLateral;
	}

	public void setFachadaLateral(String fachadaLateral) {
		this.fachadaLateral = fachadaLateral;
	}

	public String getSindicoTelefone() {
		return sindicoTelefone;
	}

	public void setSindicoTelefone(String sindicoTelefone) {
		this.sindicoTelefone = sindicoTelefone;
	}

	public String getConservadoraTelefone() {
		return conservadoraTelefone;
	}

	public void setConservadoraTelefone(String conservadoraTelefone) {
		this.conservadoraTelefone = conservadoraTelefone;
	}

	public int getNroUnidades() {
		return nroUnidades;
	}

	public void setNroUnidades(int nroUnidades) {
		this.nroUnidades = nroUnidades;
	}

	public int getNroPavimentos() {
		return nroPavimentos;
	}

	public void setNroPavimentos(int nroPavimentos) {
		this.nroPavimentos = nroPavimentos;
	}

	public int getUnidsPorAndar() {
		return unidsPorAndar;
	}

	public void setUnidsPorAndar(int unidsPorAndar) {
		this.unidsPorAndar = unidsPorAndar;
	}

	public String getAreaRealPriv() {
		return areaRealPriv;
	}

	public void setAreaRealPriv(String areaRealPriv) {
		this.areaRealPriv = areaRealPriv;
	}

	public String getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	public String getAreaLote() {
		return areaLote;
	}

	public void setAreaLote(String areaLote) {
		this.areaLote = areaLote;
	}

	public String getFrenteLote() {
		return frenteLote;
	}

	public void setFrenteLote(String frenteLote) {
		this.frenteLote = frenteLote;
	}

	public String getZonaUso() {
		return zonaUso;
	}

	public void setZonaUso(String zonaUso) {
		this.zonaUso = zonaUso;
	}

	public String getAproveitamento() {
		return aproveitamento;
	}

	public void setAproveitamento(String aproveitamento) {
		this.aproveitamento = aproveitamento;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getMelhorAcesso() {
		return melhorAcesso;
	}

	public void setMelhorAcesso(String melhorAcesso) {
		this.melhorAcesso = melhorAcesso;
	}

	public String getImovelOcupado() {
		return imovelOcupado;
	}

	public void setImovelOcupado(String imovelOcupado) {
		this.imovelOcupado = imovelOcupado;
	}

	public boolean isImovelVago() {
		return imovelVago;
	}

	public void setImovelVago(boolean imovelVago) {
		this.imovelVago = imovelVago;
	}

	public String getNomeLocatario() {
		return nomeLocatario;
	}

	public void setNomeLocatario(String nomeLocatario) {
		this.nomeLocatario = nomeLocatario;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getChaves() {
		return chaves;
	}

	public void setChaves(String chaves) {
		this.chaves = chaves;
	}

	public String getHoraVisita() {
		return horaVisita;
	}

	public void setHoraVisita(String horaVisita) {
		this.horaVisita = horaVisita;
	}

	public String getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}

	public boolean isArCondicionado() {
		return arCondicionado;
	}

	public void setArCondicionado(boolean arCondicionado) {
		this.arCondicionado = arCondicionado;
	}

	public boolean isPortaoEletronico() {
		return portaoEletronico;
	}

	public void setPortaoEletronico(boolean portaoEletronico) {
		this.portaoEletronico = portaoEletronico;
	}

	public boolean isPortariaPermanente() {
		return portariaPermanente;
	}

	public void setPortariaPermanente(boolean portariaPermanente) {
		this.portariaPermanente = portariaPermanente;
	}

	public boolean isQuadraEsportes() {
		return quadraEsportes;
	}

	public void setQuadraEsportes(boolean quadraEsportes) {
		this.quadraEsportes = quadraEsportes;
	}

	public boolean isElevadorSocial() {
		return elevadorSocial;
	}

	public void setElevadorSocial(boolean elevadorSocial) {
		this.elevadorSocial = elevadorSocial;
	}

	public boolean isAcademia() {
		return academia;
	}

	public void setAcademia(boolean academia) {
		this.academia = academia;
	}

	public boolean isSalaMassagem() {
		return salaMassagem;
	}

	public void setSalaMassagem(boolean salaMassagem) {
		this.salaMassagem = salaMassagem;
	}

	public boolean isSalaoFestas() {
		return salaoFestas;
	}

	public void setSalaoFestas(boolean salaoFestas) {
		this.salaoFestas = salaoFestas;
	}

	public boolean isSalaoJogos() {
		return salaoJogos;
	}

	public void setSalaoJogos(boolean salaoJogos) {
		this.salaoJogos = salaoJogos;
	}

	public boolean isSauna() {
		return sauna;
	}

	public void setSauna(boolean sauna) {
		this.sauna = sauna;
	}

	public boolean isSolDeManha() {
		return solDeManha;
	}

	public void setSolDeManha(boolean solDeManha) {
		this.solDeManha = solDeManha;
	}

	public boolean isPiscinas() {
		return piscinas;
	}

	public void setPiscinas(boolean piscinas) {
		this.piscinas = piscinas;
	}

	public boolean isPlayground() {
		return playground;
	}

	public void setPlayground(boolean playground) {
		this.playground = playground;
	}

	public boolean isChurrasqEspGourmet() {
		return churrasqEspGourmet;
	}

	public void setChurrasqEspGourmet(boolean churrasqEspGourmet) {
		this.churrasqEspGourmet = churrasqEspGourmet;
	}

	public boolean isCircuitoTV() {
		return circuitoTV;
	}

	public void setCircuitoTV(boolean circuitoTV) {
		this.circuitoTV = circuitoTV;
	}

	public boolean isElevadorServico() {
		return elevadorServico;
	}

	public void setElevadorServico(boolean elevadorServico) {
		this.elevadorServico = elevadorServico;
	}

	public boolean isEsquadriasAluminio() {
		return esquadriasAluminio;
	}

	public void setEsquadriasAluminio(boolean esquadriasAluminio) {
		this.esquadriasAluminio = esquadriasAluminio;
	}

	public boolean isGasCanalizado() {
		return gasCanalizado;
	}

	public void setGasCanalizado(boolean gasCanalizado) {
		this.gasCanalizado = gasCanalizado;
	}

	public boolean isHallSocialDecorado() {
		return hallSocialDecorado;
	}

	public void setHallSocialDecorado(boolean hallSocialDecorado) {
		this.hallSocialDecorado = hallSocialDecorado;
	}

	public boolean isInterfone() {
		return interfone;
	}

	public void setInterfone(boolean interfone) {
		this.interfone = interfone;
	}

	public boolean isJanelaComVenezianas() {
		return janelaComVenezianas;
	}

	public void setJanelaComVenezianas(boolean janelaComVenezianas) {
		this.janelaComVenezianas = janelaComVenezianas;
	}

	public boolean isJardins() {
		return jardins;
	}

	public void setJardins(boolean jardins) {
		this.jardins = jardins;
	}

	public boolean isMobilhado() {
		return mobilhado;
	}

	public void setMobilhado(boolean mobilhado) {
		this.mobilhado = mobilhado;
	}

	public boolean isAndarAlto() {
		return andarAlto;
	}

	public void setAndarAlto(boolean andarAlto) {
		this.andarAlto = andarAlto;
	}

	public String getDescrevaPredio() {
		return descrevaPredio;
	}

	public void setDescrevaPredio(String descrevaPredio) {
		this.descrevaPredio = descrevaPredio;
	}

	public String getConstrutora() {
		return construtora;
	}

	public void setConstrutora(String construtora) {
		this.construtora = construtora;
	}

	public String getDescrevaImovelOrdem() {
		return descrevaImovelOrdem;
	}

	public void setDescrevaImovelOrdem(String descrevaImovelOrdem) {
		this.descrevaImovelOrdem = descrevaImovelOrdem;
	}

	public String getDocumentacao() {
		return documentacao;
	}

	public void setDocumentacao(String documentacao) {
		this.documentacao = documentacao;
	}

	public double getValorEscritura() {
		return valorEscritura;
	}

	public void setValorEscritura(double valorEscritura) {
		this.valorEscritura = valorEscritura;
	}

	public String getCondicoesPagto() {
		return condicoesPagto;
	}

	public void setCondicoesPagto(String condicoesPagto) {
		this.condicoesPagto = condicoesPagto;
	}

	public String getEstudaPermuta() {
		return estudaPermuta;
	}

	public void setEstudaPermuta(String estudaPermuta) {
		this.estudaPermuta = estudaPermuta;
	}

	public String getMotivoVenda() {
		return motivoVenda;
	}

	public void setMotivoVenda(String motivoVenda) {
		this.motivoVenda = motivoVenda;
	}

	public String getCaptadorCelular() {
		return captadorCelular;
	}

	public void setCaptadorCelular(String captadorCelular) {
		this.captadorCelular = captadorCelular;
	}

	public double getAvaliacaoCorretora() {
		return avaliacaoCorretora;
	}

	public void setAvaliacaoCorretora(double avaliacaoCorretora) {
		this.avaliacaoCorretora = avaliacaoCorretora;
	}

	public double getCustoM3() {
		return custoM3;
	}

	public void setCustoM3(double custoM3) {
		this.custoM3 = custoM3;
	}

	public Date getUltimaConfirmacao() {
		return ultimaConfirmacao;
	}

	public void setUltimaConfirmacao(Date ultimaConfirmacao) {
		this.ultimaConfirmacao = ultimaConfirmacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (academia ? 1231 : 1237);
		result = prime * result + (aluguel ? 1231 : 1237);
		result = prime * result + (andarAlto ? 1231 : 1237);
		result = prime * result + ((aproveitamento == null) ? 0 : aproveitamento.hashCode());
		result = prime * result + (arCondicionado ? 1231 : 1237);
		result = prime * result + ((areaConstruida == null) ? 0 : areaConstruida.hashCode());
		result = prime * result + ((areaLote == null) ? 0 : areaLote.hashCode());
		result = prime * result + ((areaRealPriv == null) ? 0 : areaRealPriv.hashCode());
		result = prime * result + (autorizacao ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(avaliacaoCorretora);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((beneficios == null) ? 0 : beneficios.hashCode());
		result = prime * result + ((bhSocial == null) ? 0 : bhSocial.hashCode());
		result = prime * result + ((captadorCelular == null) ? 0 : captadorCelular.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((chaves == null) ? 0 : chaves.hashCode());
		result = prime * result + (churrasqEspGourmet ? 1231 : 1237);
		result = prime * result + (circuitoTV ? 1231 : 1237);
		result = prime * result + codigoCondominio;
		result = prime * result + ((condicoesPagto == null) ? 0 : condicoesPagto.hashCode());
		temp = Double.doubleToLongBits(condominioValor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((conservadoraTelefone == null) ? 0 : conservadoraTelefone.hashCode());
		result = prime * result + ((construtora == null) ? 0 : construtora.hashCode());
		temp = Double.doubleToLongBits(custoM3);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((descrevaImovelOrdem == null) ? 0 : descrevaImovelOrdem.hashCode());
		result = prime * result + ((descrevaPredio == null) ? 0 : descrevaPredio.hashCode());
		result = prime * result + ((documentacao == null) ? 0 : documentacao.hashCode());
		result = prime * result + ((edificio == null) ? 0 : edificio.hashCode());
		result = prime * result + (elevadorServico ? 1231 : 1237);
		result = prime * result + (elevadorSocial ? 1231 : 1237);
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + (esquadriasAluminio ? 1231 : 1237);
		result = prime * result + ((estudaPermuta == null) ? 0 : estudaPermuta.hashCode());
		result = prime * result + (exclusividade ? 1231 : 1237);
		result = prime * result + ((fachadaFrontal == null) ? 0 : fachadaFrontal.hashCode());
		result = prime * result + ((fachadaLateral == null) ? 0 : fachadaLateral.hashCode());
		result = prime * result + ((frenteLote == null) ? 0 : frenteLote.hashCode());
		result = prime * result + (gasCanalizado ? 1231 : 1237);
		result = prime * result + (hallSocialDecorado ? 1231 : 1237);
		result = prime * result + ((horaVisita == null) ? 0 : horaVisita.hashCode());
		result = prime * result + id;
		result = prime * result + idade;
		result = prime * result + ((imovelOcupado == null) ? 0 : imovelOcupado.hashCode());
		result = prime * result + (imovelVago ? 1231 : 1237);
		result = prime * result + ((indiceCadastral == null) ? 0 : indiceCadastral.hashCode());
		result = prime * result + (interfone ? 1231 : 1237);
		temp = Double.doubleToLongBits(iptuValor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (janelaComVenezianas ? 1231 : 1237);
		result = prime * result + (jardins ? 1231 : 1237);
		result = prime * result + ((melhorAcesso == null) ? 0 : melhorAcesso.hashCode());
		result = prime * result + (mobilhado ? 1231 : 1237);
		result = prime * result + ((motivoVenda == null) ? 0 : motivoVenda.hashCode());
		result = prime * result + ((nomeLocatario == null) ? 0 : nomeLocatario.hashCode());
		result = prime * result + nroEndereco;
		result = prime * result + nroPavimentos;
		result = prime * result + nroQuartos;
		result = prime * result + nroSalas;
		result = prime * result + nroUnidades;
		result = prime * result + (piscinas ? 1231 : 1237);
		result = prime * result + (playground ? 1231 : 1237);
		result = prime * result + (portaoEletronico ? 1231 : 1237);
		result = prime * result + (portariaPermanente ? 1231 : 1237);
		result = prime * result + ((posicao == null) ? 0 : posicao.hashCode());
		temp = Double.doubleToLongBits(precoImovel);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (quadraEsportes ? 1231 : 1237);
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result + (salaMassagem ? 1231 : 1237);
		result = prime * result + (salaoFestas ? 1231 : 1237);
		result = prime * result + (salaoJogos ? 1231 : 1237);
		result = prime * result + (sauna ? 1231 : 1237);
		result = prime * result + ((sindicoTelefone == null) ? 0 : sindicoTelefone.hashCode());
		result = prime * result + (solDeManha ? 1231 : 1237);
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((tipoImovel == null) ? 0 : tipoImovel.hashCode());
		result = prime * result + ((ultimaConfirmacao == null) ? 0 : ultimaConfirmacao.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
		result = prime * result + unidsPorAndar;
		result = prime * result + vagaLivre;
		result = prime * result + vagaPresa;
		temp = Double.doubleToLongBits(valorEscritura);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((varanda == null) ? 0 : varanda.hashCode());
		result = prime * result + (venda ? 1231 : 1237);
		result = prime * result + ((zonaUso == null) ? 0 : zonaUso.hashCode());
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
		intra_netimoveis_captacao2 other = (intra_netimoveis_captacao2) obj;
		if (academia != other.academia)
			return false;
		if (aluguel != other.aluguel)
			return false;
		if (andarAlto != other.andarAlto)
			return false;
		if (aproveitamento == null) {
			if (other.aproveitamento != null)
				return false;
		} else if (!aproveitamento.equals(other.aproveitamento))
			return false;
		if (arCondicionado != other.arCondicionado)
			return false;
		if (areaConstruida == null) {
			if (other.areaConstruida != null)
				return false;
		} else if (!areaConstruida.equals(other.areaConstruida))
			return false;
		if (areaLote == null) {
			if (other.areaLote != null)
				return false;
		} else if (!areaLote.equals(other.areaLote))
			return false;
		if (areaRealPriv == null) {
			if (other.areaRealPriv != null)
				return false;
		} else if (!areaRealPriv.equals(other.areaRealPriv))
			return false;
		if (autorizacao != other.autorizacao)
			return false;
		if (Double.doubleToLongBits(avaliacaoCorretora) != Double.doubleToLongBits(other.avaliacaoCorretora))
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (beneficios == null) {
			if (other.beneficios != null)
				return false;
		} else if (!beneficios.equals(other.beneficios))
			return false;
		if (bhSocial == null) {
			if (other.bhSocial != null)
				return false;
		} else if (!bhSocial.equals(other.bhSocial))
			return false;
		if (captadorCelular == null) {
			if (other.captadorCelular != null)
				return false;
		} else if (!captadorCelular.equals(other.captadorCelular))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (chaves == null) {
			if (other.chaves != null)
				return false;
		} else if (!chaves.equals(other.chaves))
			return false;
		if (churrasqEspGourmet != other.churrasqEspGourmet)
			return false;
		if (circuitoTV != other.circuitoTV)
			return false;
		if (codigoCondominio != other.codigoCondominio)
			return false;
		if (condicoesPagto == null) {
			if (other.condicoesPagto != null)
				return false;
		} else if (!condicoesPagto.equals(other.condicoesPagto))
			return false;
		if (Double.doubleToLongBits(condominioValor) != Double.doubleToLongBits(other.condominioValor))
			return false;
		if (conservadoraTelefone == null) {
			if (other.conservadoraTelefone != null)
				return false;
		} else if (!conservadoraTelefone.equals(other.conservadoraTelefone))
			return false;
		if (construtora == null) {
			if (other.construtora != null)
				return false;
		} else if (!construtora.equals(other.construtora))
			return false;
		if (Double.doubleToLongBits(custoM3) != Double.doubleToLongBits(other.custoM3))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (descrevaImovelOrdem == null) {
			if (other.descrevaImovelOrdem != null)
				return false;
		} else if (!descrevaImovelOrdem.equals(other.descrevaImovelOrdem))
			return false;
		if (descrevaPredio == null) {
			if (other.descrevaPredio != null)
				return false;
		} else if (!descrevaPredio.equals(other.descrevaPredio))
			return false;
		if (documentacao == null) {
			if (other.documentacao != null)
				return false;
		} else if (!documentacao.equals(other.documentacao))
			return false;
		if (edificio == null) {
			if (other.edificio != null)
				return false;
		} else if (!edificio.equals(other.edificio))
			return false;
		if (elevadorServico != other.elevadorServico)
			return false;
		if (elevadorSocial != other.elevadorSocial)
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (esquadriasAluminio != other.esquadriasAluminio)
			return false;
		if (estudaPermuta == null) {
			if (other.estudaPermuta != null)
				return false;
		} else if (!estudaPermuta.equals(other.estudaPermuta))
			return false;
		if (exclusividade != other.exclusividade)
			return false;
		if (fachadaFrontal == null) {
			if (other.fachadaFrontal != null)
				return false;
		} else if (!fachadaFrontal.equals(other.fachadaFrontal))
			return false;
		if (fachadaLateral == null) {
			if (other.fachadaLateral != null)
				return false;
		} else if (!fachadaLateral.equals(other.fachadaLateral))
			return false;
		if (frenteLote == null) {
			if (other.frenteLote != null)
				return false;
		} else if (!frenteLote.equals(other.frenteLote))
			return false;
		if (gasCanalizado != other.gasCanalizado)
			return false;
		if (hallSocialDecorado != other.hallSocialDecorado)
			return false;
		if (horaVisita == null) {
			if (other.horaVisita != null)
				return false;
		} else if (!horaVisita.equals(other.horaVisita))
			return false;
		if (id != other.id)
			return false;
		if (idade != other.idade)
			return false;
		if (imovelOcupado == null) {
			if (other.imovelOcupado != null)
				return false;
		} else if (!imovelOcupado.equals(other.imovelOcupado))
			return false;
		if (imovelVago != other.imovelVago)
			return false;
		if (indiceCadastral == null) {
			if (other.indiceCadastral != null)
				return false;
		} else if (!indiceCadastral.equals(other.indiceCadastral))
			return false;
		if (interfone != other.interfone)
			return false;
		if (Double.doubleToLongBits(iptuValor) != Double.doubleToLongBits(other.iptuValor))
			return false;
		if (janelaComVenezianas != other.janelaComVenezianas)
			return false;
		if (jardins != other.jardins)
			return false;
		if (melhorAcesso == null) {
			if (other.melhorAcesso != null)
				return false;
		} else if (!melhorAcesso.equals(other.melhorAcesso))
			return false;
		if (mobilhado != other.mobilhado)
			return false;
		if (motivoVenda == null) {
			if (other.motivoVenda != null)
				return false;
		} else if (!motivoVenda.equals(other.motivoVenda))
			return false;
		if (nomeLocatario == null) {
			if (other.nomeLocatario != null)
				return false;
		} else if (!nomeLocatario.equals(other.nomeLocatario))
			return false;
		if (nroEndereco != other.nroEndereco)
			return false;
		if (nroPavimentos != other.nroPavimentos)
			return false;
		if (nroQuartos != other.nroQuartos)
			return false;
		if (nroSalas != other.nroSalas)
			return false;
		if (nroUnidades != other.nroUnidades)
			return false;
		if (piscinas != other.piscinas)
			return false;
		if (playground != other.playground)
			return false;
		if (portaoEletronico != other.portaoEletronico)
			return false;
		if (portariaPermanente != other.portariaPermanente)
			return false;
		if (posicao == null) {
			if (other.posicao != null)
				return false;
		} else if (!posicao.equals(other.posicao))
			return false;
		if (Double.doubleToLongBits(precoImovel) != Double.doubleToLongBits(other.precoImovel))
			return false;
		if (quadraEsportes != other.quadraEsportes)
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (salaMassagem != other.salaMassagem)
			return false;
		if (salaoFestas != other.salaoFestas)
			return false;
		if (salaoJogos != other.salaoJogos)
			return false;
		if (sauna != other.sauna)
			return false;
		if (sindicoTelefone == null) {
			if (other.sindicoTelefone != null)
				return false;
		} else if (!sindicoTelefone.equals(other.sindicoTelefone))
			return false;
		if (solDeManha != other.solDeManha)
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (tipoImovel == null) {
			if (other.tipoImovel != null)
				return false;
		} else if (!tipoImovel.equals(other.tipoImovel))
			return false;
		if (ultimaConfirmacao == null) {
			if (other.ultimaConfirmacao != null)
				return false;
		} else if (!ultimaConfirmacao.equals(other.ultimaConfirmacao))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		if (unidsPorAndar != other.unidsPorAndar)
			return false;
		if (vagaLivre != other.vagaLivre)
			return false;
		if (vagaPresa != other.vagaPresa)
			return false;
		if (Double.doubleToLongBits(valorEscritura) != Double.doubleToLongBits(other.valorEscritura))
			return false;
		if (varanda == null) {
			if (other.varanda != null)
				return false;
		} else if (!varanda.equals(other.varanda))
			return false;
		if (venda != other.venda)
			return false;
		if (zonaUso == null) {
			if (other.zonaUso != null)
				return false;
		} else if (!zonaUso.equals(other.zonaUso))
			return false;
		return true;
	}

}