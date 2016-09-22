package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
@Entity
public class intra_projetar_orcamento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition="smallint")
	private int condominio;
	
	@Column(name="codigo_gerente")
	private int codigoGerente;
	
	@Column(columnDefinition="int")
	private int media;
	
	@Transient
	private boolean inadimplencia;

	@Transient
	private String[] mes = { "Jan", "Fev", "Mar", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez" };

	@Column(name="mes_projecao",columnDefinition="datetime")
	private Date mesProjecao;
	
	@Column(name="meses_projecao",columnDefinition="int")
	private int mesesProjecao;
	
	@Column(name="mes_cesta_natal",columnDefinition="varchar(10)")
	private String mesCestaNatal = "Dez";

	@Column(name="mes_reaj_func",columnDefinition="int")
	private int mesReajFunc;
	
	@Column(name="indice_reaj_func",columnDefinition="numeric(4,2)")
	private double indiceReajFunc;
	
	@Column(name="mes_reaj_terc",columnDefinition="int")
	private int mesReajTerc;
	
	@Column(name="indice_reaj_terc",columnDefinition="int")
	private double indiceReajTerc;
	
	@Column(name="mes_reaj_man31",columnDefinition="int")
	private int mesReajMan31;
	@Column(name="mes_reaj_man32",columnDefinition="int")
	private int mesReajMan32;
	@Column(name="mes_reaj_man33",columnDefinition="int")
	private int mesReajMan33;
	@Column(name="mes_reaj_man34",columnDefinition="int")
	private int mesReajMan34;
	@Column(name="mes_reaj_man35",columnDefinition="int")
	private int mesReajMan35;
	@Column(name="mes_reaj_man36",columnDefinition="int")
	private int mesReajMan36;
	@Column(name="mes_reaj_man37",columnDefinition="int")
	private int mesReajMan37;
	@Column(name="mes_reaj_man38",columnDefinition="int")
	private int mesReajMan38;
	@Column(name="mes_reaj_man39",columnDefinition="int")
	private int mesReajMan39;
	@Column(name="mes_reaj_man310",columnDefinition="int")
	private int mesReajMan310;
	@Column(name="mes_reaj_man311",columnDefinition="int")
	private int mesReajMan311;
	@Column(name="mes_reaj_man312",columnDefinition="int")
	private int mesReajMan312;
	@Column(name="mes_reaj_man313",columnDefinition="int")
	private int mesReajMan313;
	@Column(name="mes_reaj_man314",columnDefinition="int")
	private int mesReajMan314;
	@Column(name="mes_reaj_man315",columnDefinition="int")
	private int mesReajMan315;
	@Column(name="mes_reaj_man316",columnDefinition="int")
	private int mesReajMan316;
	@Column(name="mes_reaj_man317",columnDefinition="int")
	private int mesReajMan317;
	@Column(name="mes_reaj_man318",columnDefinition="int")
	private int mesReajMan318;
	@Column(name="mes_reaj_man319",columnDefinition="int")
	private int mesReajMan319;
	@Column(name="mes_reaj_man320",columnDefinition="int")
	private int mesReajMan320;
	@Column(name="mes_reaj_man321",columnDefinition="int")
	private int mesReajMan321;
	@Column(name="mes_reaj_man322",columnDefinition="int")
	private int mesReajMan322;
	@Column(name="mes_reaj_man323",columnDefinition="int")
	private int mesReajMan323;
	@Column(name="mes_reaj_man324",columnDefinition="int")
	private int mesReajMan324;
	@Column(name="mes_reaj_man325",columnDefinition="int")
	private int mesReajMan325;
	@Column(name="mes_reaj_man326",columnDefinition="int")
	private int mesReajMan326;
	@Column(name="mes_reaj_man327",columnDefinition="int")
	private int mesReajMan327;
	@Column(name="mes_reaj_man328",columnDefinition="int")
	private int mesReajMan328;
	@Column(name="mes_reaj_man329",columnDefinition="int")
	private int mesReajMan329;
	@Column(name="mes_reaj_man330",columnDefinition="int")
	private int mesReajMan330;
	@Column(name="mes_reaj_man331",columnDefinition="int")
	private int mesReajMan331;
	
	@Column(name="indice_reaj_man31", columnDefinition="numeric(4,2)")
	private double indiceReajMan31;
	@Column(name="indice_reaj_man32", columnDefinition="numeric(4,2)")
	private double indiceReajMan32;
	@Column(name="indice_reaj_man33", columnDefinition="numeric(4,2)")
	private double indiceReajMan33;
	@Column(name="indice_reaj_man34", columnDefinition="numeric(4,2)")
	private double indiceReajMan34;
	@Column(name="indice_reaj_man35", columnDefinition="numeric(4,2)")
	private double indiceReajMan35;
	@Column(name="indice_reaj_man36", columnDefinition="numeric(4,2)")
	private double indiceReajMan36;
	@Column(name="indice_reaj_man37", columnDefinition="numeric(4,2)")
	private double indiceReajMan37;
	@Column(name="indice_reaj_man38", columnDefinition="numeric(4,2)")
	private double indiceReajMan38;
	@Column(name="indice_reaj_man39", columnDefinition="numeric(4,2)")
	private double indiceReajMan39;
	@Column(name="indice_reaj_man310", columnDefinition="numeric(4,2)")
	private double indiceReajMan310;
	@Column(name="indice_reaj_man311", columnDefinition="numeric(4,2)")
	private double indiceReajMan311;
	@Column(name="indice_reaj_man312", columnDefinition="numeric(4,2)")
	private double indiceReajMan312;
	@Column(name="indice_reaj_man313", columnDefinition="numeric(4,2)")
	private double indiceReajMan313;
	@Column(name="indice_reaj_man314", columnDefinition="numeric(4,2)")
	private double indiceReajMan314;
	@Column(name="indice_reaj_man315", columnDefinition="numeric(4,2)")
	private double indiceReajMan315;
	@Column(name="indice_reaj_man316", columnDefinition="numeric(4,2)")
	private double indiceReajMan316;
	@Column(name="indice_reaj_man317", columnDefinition="numeric(4,2)")
	private double indiceReajMan317;
	@Column(name="indice_reaj_man318", columnDefinition="numeric(4,2)")
	private double indiceReajMan318;
	@Column(name="indice_reaj_man319", columnDefinition="numeric(4,2)")
	private double indiceReajMan319;
	@Column(name="indice_reaj_man320", columnDefinition="numeric(4,2)")
	private double indiceReajMan320;
	@Column(name="indice_reaj_man321", columnDefinition="numeric(4,2)")
	private double indiceReajMan321;
	@Column(name="indice_reaj_man322", columnDefinition="numeric(4,2)")
	private double indiceReajMan322;
	@Column(name="indice_reaj_man323", columnDefinition="numeric(4,2)")
	private double indiceReajMan323;
	@Column(name="indice_reaj_man324", columnDefinition="numeric(4,2)")
	private double indiceReajMan324;
	@Column(name="indice_reaj_man325", columnDefinition="numeric(4,2)")
	private double indiceReajMan325;
	@Column(name="indice_reaj_man326", columnDefinition="numeric(4,2)")
	private double indiceReajMan326;
	@Column(name="indice_reaj_man327", columnDefinition="numeric(4,2)")
	private double indiceReajMan327;
	@Column(name="indice_reaj_man328", columnDefinition="numeric(4,2)")
	private double indiceReajMan328;
	@Column(name="indice_reaj_man329", columnDefinition="numeric(4,2)")
	private double indiceReajMan329;
	@Column(name="indice_reaj_man330", columnDefinition="numeric(4,2)")
	private double indiceReajMan330;
	@Column(name="indice_reaj_man331", columnDefinition="numeric(4,2)")
	private double indiceReajMan331;
	
	@Column(name="mes_reaj_adm71", columnDefinition="varchar(10)")
	private int mesReajAdm71;
	@Column(name="mes_reaj_adm72", columnDefinition="varchar(10)")
	private int mesReajAdm72;
	@Column(name="mes_reaj_adm73", columnDefinition="varchar(10)")
	private int mesReajAdm73;
	@Column(name="mes_reaj_adm74", columnDefinition="varchar(10)")
	private int mesReajAdm74;
	@Column(name="mes_reaj_adm75", columnDefinition="varchar(10)")
	private int mesReajAdm75;
	@Column(name="mes_reaj_adm76", columnDefinition="varchar(10)")
	private int mesReajAdm76;

	@Column(name="indice_reaj_adm71", columnDefinition="numeric(4,2)")
	private double indiceReajAdm71;
	@Column(name="indice_reaj_adm72", columnDefinition="numeric(4,2)")
	private double indiceReajAdm72;
	@Column(name="indice_reaj_adm73", columnDefinition="numeric(4,2)")
	private double indiceReajAdm73;
	@Column(name="indice_reaj_adm74", columnDefinition="numeric(4,2)")
	private double indiceReajAdm74;
	@Column(name="indice_reaj_adm75", columnDefinition="numeric(4,2)")
	private double indiceReajAdm75;
	@Column(name="indice_reaj_adm76", columnDefinition="numeric(4,2)")
	private double indiceReajAdm76;

	@Column(name="capa0", columnDefinition="varchar(50)")
	private String capa0 = "Funcionários";
	@Column(name="capa1", columnDefinition="varchar(50)")
	private String capa1 = "Funcionários / Outros";
	@Column(name="capa2", columnDefinition="varchar(50)")
	private String capa2 = "Terceirização";
	@Column(name="capa3", columnDefinition="varchar(50)")
	private String capa3 = "Manutenção";
	@Column(name="capa4", columnDefinition="varchar(50)")
	private String capa4 = "Conservação Predial";
	@Column(name="capa5", columnDefinition="varchar(50)")
	private String capa5 = "Material de Consumo";
	@Column(name="capa6", columnDefinition="varchar(50)")
	private String capa6 = "Consumo";
	@Column(name="capa7", columnDefinition="varchar(50)")
	private String capa7 = "Administrativo";
	@Column(name="capa8", columnDefinition="varchar(50)")
	private String capa8 = "Despesas Operacionais";

	@Column(name="nome_conta01", columnDefinition="varchar(50)")
	private String nomeConta01 = "SALARIOS";
	@Column(name="nome_conta02", columnDefinition="varchar(50)")
	private String nomeConta02 = "ADIANTAMENTO SALARIAL";
	@Column(name="nome_conta03", columnDefinition="varchar(50)")
	private String nomeConta03 = "ESTAGIARIO";
	@Column(name="nome_conta04", columnDefinition="varchar(50)")
	private String nomeConta04 = "PENSAO ALIMENTICIA";
	@Column(name="nome_conta05", columnDefinition="varchar(50)")
	private String nomeConta05 = "RESCISÃO CONTRATUAL";
	@Column(name="nome_conta06", columnDefinition="varchar(50)")
	private String nomeConta06 = "ACORDO TRABALHISTA";
	@Column(name="nome_conta07", columnDefinition="varchar(50)")
	private String nomeConta07 = "EMPRESTIMO FUNCIONARIO";
	@Column(name="nome_conta08", columnDefinition="varchar(50)")
	private String nomeConta08 = "GESTÃO DE PAGTOS. FUNCIONÁRIOS";
	@Column(name="nome_conta09", columnDefinition="varchar(50)")
	private String nomeConta09 = "INSS - FUNCIONÁRIOS";
	@Column(name="nome_conta010", columnDefinition="varchar(50)")
	private String nomeConta010 = "INSS - AUTONOMOS";
	@Column(name="nome_conta011", columnDefinition="varchar(50)")
	private String nomeConta011 = "FGTS";
	@Column(name="nome_conta012", columnDefinition="varchar(50)")
	private String nomeConta012 = "PIS";
	@Column(name="nome_conta013", columnDefinition="varchar(50)")
	private String nomeConta013 = "IRRF";
	@Column(name="nome_conta014", columnDefinition="varchar(50)")
	private String nomeConta014 = "SEGURO VIDA FUNCIONÁRIOS";
	@Column(name="nome_conta015", columnDefinition="varchar(50)")
	private String nomeConta015 = "PARCELAMENTO - ENCARGOS";
	@Column(name="nome_conta016", columnDefinition="varchar(50)")
	private String nomeConta016 = "FGTS - RESCISÃO CONTRATUAL";
	@Column(name="nome_conta017", columnDefinition="varchar(50)")
	private String nomeConta017 = "TAXA HOMOLOGAÇÃO";
	@Column(name="nome_conta018", columnDefinition="varchar(50)")
	private String nomeConta018 = "DIRF PESSOA FÍSICA";
	@Column(name="nome_conta019", columnDefinition="varchar(50)")
	private String nomeConta019 = "DIRF PESSOA JURÍDICA";
	@Column(name="nome_conta020", columnDefinition="varchar(50)")
	private String nomeConta020 = "ELABOR. E ENTREGA RAIS ANUAL";
	@Column(name="nome_conta021", columnDefinition="varchar(50)")
	private String nomeConta021 = "RECADAST. PAT - LEI 6.321/76";
	@Column(name="nome_conta022", columnDefinition="varchar(50)")
	private String nomeConta022 = "ATUALIZ. FAP - FATOR PREVIDENC";
	@Column(name="nome_conta023", columnDefinition="varchar(50)")
	private String nomeConta023 = "OBRIG.ANUAIS-INFOR. RENDIMENTO";
	@Column(name="nome_conta024", columnDefinition="varchar(50)")
	private String nomeConta024 = "ATESTADO ANTECEDENTES";
	@Column(name="nome_conta025", columnDefinition="varchar(50)")
	private String nomeConta025 = "CONECTIVADE SOCIAL";
	@Column(name="nome_conta026", columnDefinition="varchar(50)")
	private String nomeConta026 = "V.T./ ALIMENTAÇÃO / REFEIÇÃO";
	@Column(name="nome_conta027", columnDefinition="varchar(50)")
	private String nomeConta027 = "ASSISTÊNCIA MÉDICA";
	@Column(name="nome_conta028", columnDefinition="varchar(50)")
	private String nomeConta028 = "ASSISTÊNCIA ODONTOLOGICA";
	@Column(name="nome_conta029", columnDefinition="varchar(50)")
	private String nomeConta029 = "CESTA BÁSICA";

	@Column(name="nome_conta030", columnDefinition="varchar(50)")
	private String nomeConta030 = "CONTRIBUIÇÃO PATRONAL";
	@Column(name="nome_conta031", columnDefinition="varchar(50)")
	private String nomeConta031 = "CONTRIBUIÇÃO ASSISTENCIAL";
	@Column(name="nome_conta032", columnDefinition="varchar(50)")
	private String nomeConta032 = "PPRA-PROG.PREV.RISCOS AMBIENT.";
	@Column(name="nome_conta033", columnDefinition="varchar(50)")
	private String nomeConta033 = "PPP E NORMAS";
	@Column(name="nome_conta034", columnDefinition="varchar(50)")
	private String nomeConta034 = "BRIGADA INCÊNDIO";
	@Column(name="nome_conta035", columnDefinition="varchar(50)")
	private String nomeConta035 = "PREVENÇÃO DE ACIDENTES";
	@Column(name="nome_conta036", columnDefinition="varchar(50)")
	private String nomeConta036 = "PRIMEIROS SOCORROS";
	@Column(name="nome_conta037", columnDefinition="varchar(50)")
	private String nomeConta037 = "PCMSO-PROG. SAÚDE OCUPACIONAL";
	@Column(name="nome_conta038", columnDefinition="varchar(50)")
	private String nomeConta038 = "EVENTUAIS";

	@Column(name="nome_conta11", columnDefinition="varchar(50)")
	private String nomeConta11 = "FERIAS";
	@Column(name="nome_conta12", columnDefinition="varchar(50)")
	private String nomeConta12 = "13. SALARIO";
	@Column(name="nome_conta13", columnDefinition="varchar(50)")
	private String nomeConta13 = "INSS - 13.SALÁRIO";
	@Column(name="nome_conta14", columnDefinition="varchar(50)")
	private String nomeConta14 = "CESTA DE NATAL";
	@Column(name="nome_conta15", columnDefinition="varchar(50)")
	private String nomeConta15 = "EVENTUAIS";

	@Column(name="nome_conta21", columnDefinition="varchar(50)")
	private String nomeConta21 = "EMPRESAS TERCEIRIZADAS";
	@Column(name="nome_conta22", columnDefinition="varchar(50)")
	private String nomeConta22 = "PORTARIA";
	@Column(name="nome_conta23", columnDefinition="varchar(50)")
	private String nomeConta23 = "VIGILANTES";
	@Column(name="nome_conta24", columnDefinition="varchar(50)")
	private String nomeConta24 = "LIMPEZA";
	@Column(name="nome_conta25", columnDefinition="varchar(50)")
	private String nomeConta25 = "MANUTENÇÃO";
	@Column(name="nome_conta26", columnDefinition="varchar(50)")
	private String nomeConta26 = "BOMBEIRO RECEPÇÃO";
	@Column(name="nome_conta27", columnDefinition="varchar(50)")
	private String nomeConta27 = "EVENTUAIS";

	@Column(name="nome_conta31", columnDefinition="varchar(50)")
	private String nomeConta31 = "ELEVADORES";
	@Column(name="nome_conta32", columnDefinition="varchar(50)")
	private String nomeConta32 = "JARDIM";
	@Column(name="nome_conta33", columnDefinition="varchar(50)")
	private String nomeConta33 = "ARRANJOS FLORAIS";
	@Column(name="nome_conta34", columnDefinition="varchar(50)")
	private String nomeConta34 = "GERADORES";
	@Column(name="nome_conta35", columnDefinition="varchar(50)")
	private String nomeConta35 = "BOMBAS";
	@Column(name="nome_conta36", columnDefinition="varchar(50)")
	private String nomeConta36 = "PISCINA";
	@Column(name="nome_conta37", columnDefinition="varchar(50)")
	private String nomeConta37 = "PORTÕES / PORTAS";
	@Column(name="nome_conta38", columnDefinition="varchar(50)")
	private String nomeConta38 = "INTERFONES";
	@Column(name="nome_conta39", columnDefinition="varchar(50)")
	private String nomeConta39 = "ANTENA";
	@Column(name="nome_conta310", columnDefinition="varchar(50)")
	private String nomeConta310 = "EQUIPAMENTOS DE GINÁSTICA";
	@Column(name="nome_conta311", columnDefinition="varchar(50)")
	private String nomeConta311 = "PERSONAL TRAINNER";
	@Column(name="nome_conta312", columnDefinition="varchar(50)")
	private String nomeConta312 = "DDT/DESRATIZAÇÃO/DESCUPINIZAÇÃ";
	@Column(name="nome_conta313", columnDefinition="varchar(50)")
	private String nomeConta313 = "PORTÕES/PORTAS/INTERFONES/ANTE";
	@Column(name="nome_conta314", columnDefinition="varchar(50)")
	private String nomeConta314 = "CALDEIRA";
	@Column(name="nome_conta315", columnDefinition="varchar(50)")
	private String nomeConta315 = "PRESSURIZAÇÃO";
	@Column(name="nome_conta316", columnDefinition="varchar(50)")
	private String nomeConta316 = "ALARME";
	@Column(name="nome_conta317", columnDefinition="varchar(50)")
	private String nomeConta317 = "LEITURA INDIVIDUAL. ÁGUA / GÁS";
	@Column(name="nome_conta318", columnDefinition="varchar(50)")
	private String nomeConta318 = "SISTEMA DETECÇÃO DE INCÊNDIO";
	@Column(name="nome_conta319", columnDefinition="varchar(50)")
	private String nomeConta319 = "DESOBSTRUÇÃO COLUNAS";
	@Column(name="nome_conta320", columnDefinition="varchar(50)")
	private String nomeConta320 = "ELEVADOR DE VEÍCULOS";
	@Column(name="nome_conta321", columnDefinition="varchar(50)")
	private String nomeConta321 = "EQUIPAMENTOS DE SEGURANÇA";
	@Column(name="nome_conta322", columnDefinition="varchar(50)")
	private String nomeConta322 = "CFTV - CIRC. FECHADO TELEVISÃO";
	@Column(name="nome_conta323", columnDefinition="varchar(50)")
	private String nomeConta323 = "LOCAÇÃO EQUIPAMENTOS";
	@Column(name="nome_conta324", columnDefinition="varchar(50)")
	private String nomeConta324 = "COLETA DO LIXO";
	@Column(name="nome_conta325", columnDefinition="varchar(50)")
	private String nomeConta325 = "MONITORAMENTO";
	@Column(name="nome_conta326", columnDefinition="varchar(50)")
	private String nomeConta326 = "AR CONDICIONADO";
	@Column(name="nome_conta327", columnDefinition="varchar(50)")
	private String nomeConta327 = "INFORMÁTICA";
	@Column(name="nome_conta328", columnDefinition="varchar(50)")
	private String nomeConta328 = "ANÁLISE ÁGUA";
	@Column(name="nome_conta329", columnDefinition="varchar(50)")
	private String nomeConta329 = "TRATAMENTO ÁGUA";
	@Column(name="nome_conta330", columnDefinition="varchar(50)")
	private String nomeConta330 = "CATRACAS";
	@Column(name="nome_conta331", columnDefinition="varchar(50)")
	private String nomeConta331 = "EVENTUAIS";

	@Column(name="nome_conta41", columnDefinition="varchar(50)")
	private String nomeConta41 = "CONSERVAÇÃO PREDIAL";
	@Column(name="nome_conta42", columnDefinition="varchar(50)")
	private String nomeConta42 = "EVENTUAIS";

	@Column(name="nome_conta51", columnDefinition="varchar(50)")
	private String nomeConta51 = "LIMPEZA";
	@Column(name="nome_conta52", columnDefinition="varchar(50)")
	private String nomeConta52 = "CONSTRUÇÃO/ELÉTRICA/HIDRAÚLICO";
	@Column(name="nome_conta53", columnDefinition="varchar(50)")
	private String nomeConta53 = "PISCINA";
	@Column(name="nome_conta54", columnDefinition="varchar(50)")
	private String nomeConta54 = "UNIFORMES";
	@Column(name="nome_conta55", columnDefinition="varchar(50)")
	private String nomeConta55 = "ESCRITÓRIO";
	@Column(name="nome_conta56", columnDefinition="varchar(50)")
	private String nomeConta56 = "EVENTUAIS";

	@Column(name="nome_conta61", columnDefinition="varchar(50)")
	private String nomeConta61 = "TELEFONE";
	@Column(name="nome_conta62", columnDefinition="varchar(50)")
	private String nomeConta62 = "COMUNICAÇÃO RÁDIO";
	@Column(name="nome_conta63", columnDefinition="varchar(50)")
	private String nomeConta63 = "ÁGUA";
	@Column(name="nome_conta64", columnDefinition="varchar(50)")
	private String nomeConta64 = "ENERGIA ELÉTRICA";
	@Column(name="nome_conta65", columnDefinition="varchar(50)")
	private String nomeConta65 = "GÁS";
	@Column(name="nome_conta66", columnDefinition="varchar(50)")
	private String nomeConta66 = "INTERNET / BANDA LARGA";
	@Column(name="nome_conta67", columnDefinition="varchar(50)")
	private String nomeConta67 = "TV A CABO";
	@Column(name="nome_conta68", columnDefinition="varchar(50)")
	private String nomeConta68 = "AQUISIÇÃO DE ÁGUA";
	@Column(name="nome_conta69", columnDefinition="varchar(50)")
	private String nomeConta69 = "EVENTUAIS";
	
	@Column(name="nome_conta71", columnDefinition="varchar(50)")
	private String nomeConta71 = "HONORÁRIOS ADMINISTRAÇÃO";
	@Column(name="nome_conta72", columnDefinition="varchar(50)")
	private String nomeConta72 = "ISENÇÃO SÍNDICO";
	@Column(name="nome_conta73", columnDefinition="varchar(50)")
	private String nomeConta73 = "ISENÇÃO CORPO DIRETIVO";
	@Column(name="nome_conta74", columnDefinition="varchar(50)")
	private String nomeConta74 = "HONOR. SÍNDICO PROFISSIONAL";
	@Column(name="nome_conta75", columnDefinition="varchar(50)")
	private String nomeConta75 = "GERENCIAMENTO DE MÃO DE OBRA";
	@Column(name="nome_conta76", columnDefinition="varchar(50)")
	private String nomeConta76 = "EVENTUAIS";

	@Column(name="nome_conta81", columnDefinition="varchar(50)")
	private String nomeConta81 = "GESTÃO DE RECEBIMENTOS";
	@Column(name="nome_conta82", columnDefinition="varchar(50)")
	private String nomeConta82 = "REEMB. MAT. ADMINISTRATIVOS";
	@Column(name="nome_conta83", columnDefinition="varchar(50)")
	private String nomeConta83 = "TARIFA MANUT. CTA. CORRENTE";
	@Column(name="nome_conta84", columnDefinition="varchar(50)")
	private String nomeConta84 = "FUNDO DE CAIXA";
	@Column(name="nome_conta85", columnDefinition="varchar(50)")
	private String nomeConta85 = "HONORÁRIOS ADVOCATÍCIOS";
	@Column(name="nome_conta86", columnDefinition="varchar(50)")
	private String nomeConta86 = "HONORÁRIOS AUDITORIA";
	@Column(name="nome_conta87", columnDefinition="varchar(50)")
	private String nomeConta87 = "GALÃO ÁGUA";
	@Column(name="nome_conta88", columnDefinition="varchar(50)")
	private String nomeConta88 = "ENFEITES NATALINOS";
	@Column(name="nome_conta89", columnDefinition="varchar(50)")
	private String nomeConta89 = "GRATIF. PREST. SERVIÇOS";
	@Column(name="nome_conta810", columnDefinition="varchar(50)")
	private String nomeConta810 = "DESPESAS DE CONDUÇÃO";
	@Column(name="nome_conta811", columnDefinition="varchar(50)")
	private String nomeConta811 = "SEGURO PREDIAL";
	@Column(name="nome_conta812", columnDefinition="varchar(50)")
	private String nomeConta812 = "IPTU";
	@Column(name="nome_conta813", columnDefinition="varchar(50)")
	private String nomeConta813 = "TAXA FUNCIONAMENTO ANUAL";
	@Column(name="nome_conta814", columnDefinition="varchar(50)")
	private String nomeConta814 = "TAXA ELEVADORES";
	@Column(name="nome_conta815", columnDefinition="varchar(50)")
	private String nomeConta815 = "CARTÓRIO";
	@Column(name="nome_conta816", columnDefinition="varchar(50)")
	private String nomeConta816 = "CUSTAS PROCESSUAIS";
	@Column(name="nome_conta817", columnDefinition="varchar(50)")
	private String nomeConta817 = "DESPESAS CLÁUSULA CONTRATUAL";
	@Column(name="nome_conta818", columnDefinition="varchar(50)")
	private String nomeConta818 = "MANUT. ANUAL ARQUIVO EXTERNO";
	@Column(name="nome_conta819", columnDefinition="varchar(50)")
	private String nomeConta819 = "MANUT. ANUAL - CERTIF. DIGITAL";
	@Column(name="nome_conta820", columnDefinition="varchar(50)")
	private String nomeConta820 = "ELABOR./CONTR.MENSAL DEC.52610";
	@Column(name="nome_conta821", columnDefinition="varchar(50)")
	private String nomeConta821 = "ADM OBRIG. FISCAIS/TRIBUTARIAS";
	@Column(name="nome_conta822", columnDefinition="varchar(50)")
	private String nomeConta822 = "GESTÃO DE TRIBUTOS";
	@Column(name="nome_conta823", columnDefinition="varchar(50)")
	private String nomeConta823 = "PORTARIA DIGITAL";
	@Column(name="nome_conta824", columnDefinition="varchar(50)")
	private String nomeConta824 = "CERTIFICAÇÃO DIGITAL";
	@Column(name="nome_conta825", columnDefinition="varchar(50)")
	private String nomeConta825 = "ATUALIZAÇÃO CNPJ";
	@Column(name="nome_conta826", columnDefinition="varchar(50)")
	private String nomeConta826 = "ASSOCIAÇÕES";
	@Column(name="nome_conta827", columnDefinition="varchar(50)")
	private String nomeConta827 = "RELATÓRIO VISTORIA";
	@Column(name="nome_conta828", columnDefinition="varchar(50)")
	private String nomeConta828 = "REPRESENTAÇÃO ASSEMBLÉIA GERAL";
	@Column(name="nome_conta829", columnDefinition="varchar(50)")
	private String nomeConta829 = "REPRESENTAÇÃO REUNIÃO INFORMAL";
	@Column(name="nome_conta830", columnDefinition="varchar(50)")
	private String nomeConta830 = "RESSARCIMENTO PROPRIETÁRIOS";
	@Column(name="nome_conta831", columnDefinition="varchar(50)")
	private String nomeConta831 = "PESQUISAS CADASTRAIS";
	@Column(name="nome_conta832", columnDefinition="varchar(50)")
	private String nomeConta832 = "DESPESAS JURÍDICAS";
	@Column(name="nome_conta833", columnDefinition="varchar(50)")
	private String nomeConta833 = "TRANSFERÊNCIA CONTÁBIL";
	@Column(name="nome_conta834", columnDefinition="varchar(50)")
	private String nomeConta834 = "CORREIOS";
	@Column(name="nome_conta835", columnDefinition="varchar(50)")
	private String nomeConta835 = "COND.QUITADO EX-ADMINISTRADORA";
	@Column(name="nome_conta836", columnDefinition="varchar(50)")
	private String nomeConta836 = "ALUGUEL";
	@Column(name="nome_conta837", columnDefinition="varchar(50)")
	private String nomeConta837 = "EVENTUAIS";

	@Column(name="valor_conta01", columnDefinition="numeric(25,2)")
	private double valorConta01;
	@Column(name="valor_conta02", columnDefinition="numeric(25,2)")
	private double valorConta02;
	@Column(name="valor_conta03", columnDefinition="numeric(25,2)")
	private double valorConta03;
	@Column(name="valor_conta04", columnDefinition="numeric(25,2)")
	private double valorConta04;
	@Column(name="valor_conta05", columnDefinition="numeric(25,2)")
	private double valorConta05;
	@Column(name="valor_conta06", columnDefinition="numeric(25,2)")
	private double valorConta06;
	@Column(name="valor_conta07", columnDefinition="numeric(25,2)")
	private double valorConta07;
	@Column(name="valor_conta08", columnDefinition="numeric(25,2)")
	private double valorConta08;
	@Column(name="valor_conta09", columnDefinition="numeric(25,2)")
	private double valorConta09;
	@Column(name="valor_conta010", columnDefinition="numeric(25,2)")
	private double valorConta010;
	@Column(name="valor_conta011", columnDefinition="numeric(25,2)")
	private double valorConta011;
	@Column(name="valor_conta012", columnDefinition="numeric(25,2)")
	private double valorConta012;
	@Column(name="valor_conta013", columnDefinition="numeric(25,2)")
	private double valorConta013;
	@Column(name="valor_conta014", columnDefinition="numeric(25,2)")
	private double valorConta014;
	@Column(name="valor_conta015", columnDefinition="numeric(25,2)")
	private double valorConta015;
	@Column(name="valor_conta016", columnDefinition="numeric(25,2)")
	private double valorConta016;
	@Column(name="valor_conta017", columnDefinition="numeric(25,2)")
	private double valorConta017;
	@Column(name="valor_conta018", columnDefinition="numeric(25,2)")
	private double valorConta018;
	@Column(name="valor_conta019", columnDefinition="numeric(25,2)")
	private double valorConta019;
	@Column(name="valor_conta020", columnDefinition="numeric(25,2)")
	private double valorConta020;
	@Column(name="valor_conta021", columnDefinition="numeric(25,2)")
	private double valorConta021;
	@Column(name="valor_conta022", columnDefinition="numeric(25,2)")
	private double valorConta022;
	@Column(name="valor_conta023", columnDefinition="numeric(25,2)")
	private double valorConta023;
	@Column(name="valor_conta024", columnDefinition="numeric(25,2)")
	private double valorConta024;
	@Column(name="valor_conta025", columnDefinition="numeric(25,2)")
	private double valorConta025;
	@Column(name="valor_conta026", columnDefinition="numeric(25,2)")
	private double valorConta026;
	@Column(name="valor_conta027", columnDefinition="numeric(25,2)")
	private double valorConta027;
	@Column(name="valor_conta028", columnDefinition="numeric(25,2)")
	private double valorConta028;
	@Column(name="valor_conta029", columnDefinition="numeric(25,2)")
	private double valorConta029;
	@Column(name="valor_conta030", columnDefinition="numeric(25,2)")
	private double valorConta030;
	@Column(name="valor_conta031", columnDefinition="numeric(25,2)")
	private double valorConta031;
	@Column(name="valor_conta032", columnDefinition="numeric(25,2)")
	private double valorConta032;
	@Column(name="valor_conta033", columnDefinition="numeric(25,2)")
	private double valorConta033;
	@Column(name="valor_conta034", columnDefinition="numeric(25,2)")
	private double valorConta034;
	@Column(name="valor_conta035", columnDefinition="numeric(25,2)")
	private double valorConta035;
	@Column(name="valor_conta036", columnDefinition="numeric(25,2)")
	private double valorConta036;
	@Column(name="valor_conta037", columnDefinition="numeric(25,2)")
	private double valorConta037;
	@Column(name="valor_conta038", columnDefinition="numeric(25,2)")
	private double valorConta038;

	@Column(name="valor_conta11", columnDefinition="numeric(25,2)")
	private double valorConta11;
	@Column(name="valor_conta12", columnDefinition="numeric(25,2)")
	private double valorConta12;
	@Column(name="valor_conta13", columnDefinition="numeric(25,2)")
	private double valorConta13;
	@Column(name="valor_conta14", columnDefinition="numeric(25,2)")
	private double valorConta14;
	@Column(name="valor_conta15", columnDefinition="numeric(25,2)")
	private double valorConta15;

	@Column(name="valor_conta21", columnDefinition="numeric(25,2)")
	private double valorConta21;
	@Column(name="valor_conta22", columnDefinition="numeric(25,2)")
	private double valorConta22;
	@Column(name="valor_conta23", columnDefinition="numeric(25,2)")
	private double valorConta23;
	@Column(name="valor_conta24", columnDefinition="numeric(25,2)")
	private double valorConta24;
	@Column(name="valor_conta25", columnDefinition="numeric(25,2)")
	private double valorConta25;
	@Column(name="valor_conta26", columnDefinition="numeric(25,2)")
	private double valorConta26;
	@Column(name="valor_conta27", columnDefinition="numeric(25,2)")
	private double valorConta27;

	@Column(name="valor_conta31", columnDefinition="numeric(25,2)")
	private double valorConta31;
	@Column(name="valor_conta32", columnDefinition="numeric(25,2)")
	private double valorConta32;
	@Column(name="valor_conta33", columnDefinition="numeric(25,2)")
	private double valorConta33;
	@Column(name="valor_conta34", columnDefinition="numeric(25,2)")
	private double valorConta34;
	@Column(name="valor_conta35", columnDefinition="numeric(25,2)")
	private double valorConta35;
	@Column(name="valor_conta36", columnDefinition="numeric(25,2)")
	private double valorConta36;
	@Column(name="valor_conta37", columnDefinition="numeric(25,2)")
	private double valorConta37;
	@Column(name="valor_conta38", columnDefinition="numeric(25,2)")
	private double valorConta38;
	@Column(name="valor_conta39", columnDefinition="numeric(25,2)")
	private double valorConta39;
	@Column(name="valor_conta310", columnDefinition="numeric(25,2)")
	private double valorConta310;
	@Column(name="valor_conta311", columnDefinition="numeric(25,2)")
	private double valorConta311;
	@Column(name="valor_conta312", columnDefinition="numeric(25,2)")
	private double valorConta312;
	@Column(name="valor_conta313", columnDefinition="numeric(25,2)")
	private double valorConta313;
	@Column(name="valor_conta314", columnDefinition="numeric(25,2)")
	private double valorConta314;
	@Column(name="valor_conta315", columnDefinition="numeric(25,2)")
	private double valorConta315;
	@Column(name="valor_conta316", columnDefinition="numeric(25,2)")
	private double valorConta316;
	@Column(name="valor_conta317", columnDefinition="numeric(25,2)")
	private double valorConta317;
	@Column(name="valor_conta318", columnDefinition="numeric(25,2)")
	private double valorConta318;
	@Column(name="valor_conta319", columnDefinition="numeric(25,2)")
	private double valorConta319;
	@Column(name="valor_conta320", columnDefinition="numeric(25,2)")
	private double valorConta320;
	@Column(name="valor_conta321", columnDefinition="numeric(25,2)")
	private double valorConta321;
	@Column(name="valor_conta322", columnDefinition="numeric(25,2)")
	private double valorConta322;
	@Column(name="valor_conta323", columnDefinition="numeric(25,2)")
	private double valorConta323;
	@Column(name="valor_conta324", columnDefinition="numeric(25,2)")
	private double valorConta324;
	@Column(name="valor_conta325", columnDefinition="numeric(25,2)")
	private double valorConta325;
	@Column(name="valor_conta326", columnDefinition="numeric(25,2)")
	private double valorConta326;
	@Column(name="valor_conta327", columnDefinition="numeric(25,2)")
	private double valorConta327;
	@Column(name="valor_conta328", columnDefinition="numeric(25,2)")
	private double valorConta328;
	@Column(name="valor_conta329", columnDefinition="numeric(25,2)")
	private double valorConta329;
	@Column(name="valor_conta330", columnDefinition="numeric(25,2)")
	private double valorConta330;
	@Column(name="valor_conta331", columnDefinition="numeric(25,2)")
	private double valorConta331;

	@Column(name="valor_conta41", columnDefinition="numeric(25,2)")
	private double valorConta41;
	@Column(name="valor_conta42", columnDefinition="numeric(25,2)")
	private double valorConta42;

	@Column(name="valor_conta51", columnDefinition="numeric(25,2)")
	private double valorConta51;
	@Column(name="valor_conta52", columnDefinition="numeric(25,2)")
	private double valorConta52;
	@Column(name="valor_conta53", columnDefinition="numeric(25,2)")
	private double valorConta53;
	@Column(name="valor_conta354", columnDefinition="numeric(25,2)")
	private double valorConta54;
	@Column(name="valor_conta55", columnDefinition="numeric(25,2)")
	private double valorConta55;
	@Column(name="valor_conta56", columnDefinition="numeric(25,2)")
	private double valorConta56;

	@Column(name="valor_conta61", columnDefinition="numeric(25,2)")
	private double valorConta61;
	@Column(name="valor_conta62", columnDefinition="numeric(25,2)")
	private double valorConta62;
	@Column(name="valor_conta63", columnDefinition="numeric(25,2)")
	private double valorConta63;
	@Column(name="valor_conta64", columnDefinition="numeric(25,2)")
	private double valorConta64;
	@Column(name="valor_conta65", columnDefinition="numeric(25,2)")
	private double valorConta65;
	@Column(name="valor_conta66", columnDefinition="numeric(25,2)")
	private double valorConta66;
	@Column(name="valor_conta67", columnDefinition="numeric(25,2)")
	private double valorConta67;
	@Column(name="valor_conta68", columnDefinition="numeric(25,2)")
	private double valorConta68;
	@Column(name="valor_conta69", columnDefinition="numeric(25,2)")
	private double valorConta69;
	
	@Column(name="valor_conta71", columnDefinition="numeric(25,2)")
	private double valorConta71;
	@Column(name="valor_conta72", columnDefinition="numeric(25,2)")
	private double valorConta72;
	@Column(name="valor_conta73", columnDefinition="numeric(25,2)")
	private double valorConta73;
	@Column(name="valor_conta74", columnDefinition="numeric(25,2)")
	private double valorConta74;
	@Column(name="valor_conta75", columnDefinition="numeric(25,2)")
	private double valorConta75;
	@Column(name="valor_conta76", columnDefinition="numeric(25,2)")
	private double valorConta76;

	@Column(name="valor_conta81", columnDefinition="numeric(25,2)")
	private double valorConta81;
	@Column(name="valor_conta82", columnDefinition="numeric(25,2)")
	private double valorConta82;
	@Column(name="valor_conta83", columnDefinition="numeric(25,2)")
	private double valorConta83;
	@Column(name="valor_conta84", columnDefinition="numeric(25,2)")
	private double valorConta84;
	@Column(name="valor_conta85", columnDefinition="numeric(25,2)")
	private double valorConta85;
	@Column(name="valor_conta86", columnDefinition="numeric(25,2)")
	private double valorConta86;
	@Column(name="valor_conta87", columnDefinition="numeric(25,2)")
	private double valorConta87;
	@Column(name="valor_conta88", columnDefinition="numeric(25,2)")
	private double valorConta88;
	@Column(name="valor_conta89", columnDefinition="numeric(25,2)")
	private double valorConta89;
	@Column(name="valor_conta810", columnDefinition="numeric(25,2)")
	private double valorConta810;
	@Column(name="valor_conta811", columnDefinition="numeric(25,2)")
	private double valorConta811;
	@Column(name="valor_conta812", columnDefinition="numeric(25,2)")
	private double valorConta812;
	@Column(name="valor_conta813", columnDefinition="numeric(25,2)")
	private double valorConta813;
	@Column(name="valor_conta814", columnDefinition="numeric(25,2)")
	private double valorConta814;
	@Column(name="valor_conta815", columnDefinition="numeric(25,2)")
	private double valorConta815;
	@Column(name="valor_conta816", columnDefinition="numeric(25,2)")
	private double valorConta816;
	@Column(name="valor_conta817", columnDefinition="numeric(25,2)")
	private double valorConta817;
	@Column(name="valor_conta818", columnDefinition="numeric(25,2)")
	private double valorConta818;
	@Column(name="valor_conta819", columnDefinition="numeric(25,2)")
	private double valorConta819;
	@Column(name="valor_conta820", columnDefinition="numeric(25,2)")
	private double valorConta820;
	@Column(name="valor_conta821", columnDefinition="numeric(25,2)")
	private double valorConta821;
	@Column(name="valor_conta822", columnDefinition="numeric(25,2)")
	private double valorConta822;
	@Column(name="valor_conta823", columnDefinition="numeric(25,2)")
	private double valorConta823;
	@Column(name="valor_conta824", columnDefinition="numeric(25,2)")
	private double valorConta824;
	@Column(name="valor_conta825", columnDefinition="numeric(25,2)")
	private double valorConta825;
	@Column(name="valor_conta826", columnDefinition="numeric(25,2)")
	private double valorConta826;
	@Column(name="valor_conta827", columnDefinition="numeric(25,2)")
	private double valorConta827;
	@Column(name="valor_conta828", columnDefinition="numeric(25,2)")
	private double valorConta828;
	@Column(name="valor_conta829", columnDefinition="numeric(25,2)")
	private double valorConta829;
	@Column(name="valor_conta830", columnDefinition="numeric(25,2)")
	private double valorConta830;
	@Column(name="valor_conta831", columnDefinition="numeric(25,2)")
	private double valorConta831;
	@Column(name="valor_conta832", columnDefinition="numeric(25,2)")
	private double valorConta832;
	@Column(name="valor_conta833", columnDefinition="numeric(25,2)")
	private double valorConta833;
	@Column(name="valor_conta834", columnDefinition="numeric(25,2)")
	private double valorConta834;
	@Column(name="valor_conta835", columnDefinition="numeric(25,2)")
	private double valorConta835;
	@Column(name="valor_conta836", columnDefinition="numeric(25,2)")
	private double valorConta836;
	@Column(name="valor_conta837", columnDefinition="numeric(25,2)")
	private double valorConta837;
	
	@Column(name="ferias_jan", columnDefinition="numeric(25,2)")
	private double feriasJan;
	@Column(name="ferias_fev", columnDefinition="numeric(25,2)")
	private double feriasFev;
	@Column(name="ferias_mar", columnDefinition="numeric(25,2)")
	private double feriasMar;
	@Column(name="ferias_abr", columnDefinition="numeric(25,2)")
	private double feriasAbr;
	@Column(name="ferias_ami", columnDefinition="numeric(25,2)")
	private double feriasMai;
	@Column(name="ferias_jun", columnDefinition="numeric(25,2)")
	private double feriasJun;
	@Column(name="ferias_jul", columnDefinition="numeric(25,2)")
	private double feriasJul;
	@Column(name="ferias_ago", columnDefinition="numeric(25,2)")
	private double feriasAgo;
	@Column(name="ferias_set", columnDefinition="numeric(25,2)")
	private double feriasSet;
	@Column(name="ferias_out", columnDefinition="numeric(25,2)")
	private double feriasOut;
	@Column(name="ferias_nov", columnDefinition="numeric(25,2)")
	private double feriasNov;
	@Column(name="ferias_dez", columnDefinition="numeric(25,2)")
	private double feriasDez;

	@Column(name="decimo13_nov", columnDefinition="numeric(25,2)")
	private double decimo13Nov;
	@Column(name="decimo13_dez", columnDefinition="numeric(25,2)")
	private double decimo13Dez;

	private boolean conta01;
	private boolean conta02;
	private boolean conta03;
	private boolean conta04;
	private boolean conta05;
	private boolean conta06;
	private boolean conta07;
	private boolean conta08;
	private boolean conta09;
	private boolean conta010;
	private boolean conta011;
	private boolean conta012;
	private boolean conta013;
	private boolean conta014;
	private boolean conta015;
	private boolean conta016;
	private boolean conta017;
	private boolean conta018;
	private boolean conta019;
	private boolean conta020;
	private boolean conta021;
	private boolean conta022;
	private boolean conta023;
	private boolean conta024;
	private boolean conta025;
	private boolean conta026;
	private boolean conta027;
	private boolean conta028;
	private boolean conta029;
	private boolean conta030;
	private boolean conta031;
	private boolean conta032;
	private boolean conta033;
	private boolean conta034;
	private boolean conta035;
	private boolean conta036;
	private boolean conta037;
	private boolean conta038;

	private boolean conta11;
	private boolean conta12;
	private boolean conta13;
	private boolean conta14;
	private boolean conta15;

	private boolean conta21;
	private boolean conta22;
	private boolean conta23;
	private boolean conta24;
	private boolean conta25;
	private boolean conta26;
	private boolean conta27;

	private boolean conta31;
	private boolean conta32;
	private boolean conta33;
	private boolean conta34;
	private boolean conta35;
	private boolean conta36;
	private boolean conta37;
	private boolean conta38;
	private boolean conta39;
	private boolean conta310;
	private boolean conta311;
	private boolean conta312;
	private boolean conta313;
	private boolean conta314;
	private boolean conta315;
	private boolean conta316;
	private boolean conta317;
	private boolean conta318;
	private boolean conta319;
	private boolean conta320;
	private boolean conta321;
	private boolean conta322;
	private boolean conta323;
	private boolean conta324;
	private boolean conta325;
	private boolean conta326;
	private boolean conta327;
	private boolean conta328;
	private boolean conta329;
	private boolean conta330;
	private boolean conta331;

	private boolean conta41;
	private boolean conta42;

	private boolean conta51;
	private boolean conta52;
	private boolean conta53;
	private boolean conta54;
	private boolean conta55;
	private boolean conta56;

	private boolean conta61;
	private boolean conta62;
	private boolean conta63;
	private boolean conta64;
	private boolean conta65;
	private boolean conta66;
	private boolean conta67;
	private boolean conta68;
	private boolean conta69;

	private boolean conta71;
	private boolean conta72;
	private boolean conta73;
	private boolean conta74;
	private boolean conta75;
	private boolean conta76;

	private boolean conta81;
	private boolean conta82;
	private boolean conta83;
	private boolean conta84;
	private boolean conta85;
	private boolean conta86;
	private boolean conta87;
	private boolean conta88;
	private boolean conta89;
	private boolean conta810;
	private boolean conta811;
	private boolean conta812;
	private boolean conta813;
	private boolean conta814;
	private boolean conta815;
	private boolean conta816;
	private boolean conta817;
	private boolean conta818;
	private boolean conta819;
	private boolean conta820;
	private boolean conta821;
	private boolean conta822;
	private boolean conta823;
	private boolean conta824;
	private boolean conta825;
	private boolean conta826;
	private boolean conta827;
	private boolean conta828;
	private boolean conta829;
	private boolean conta830;
	private boolean conta831;
	private boolean conta832;
	private boolean conta833;
	private boolean conta834;
	private boolean conta835;
	private boolean conta836;
	private boolean conta837;
	public int getCondominio() {
		return condominio;
	}
	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}
	public int getCodigoGerente() {
		return codigoGerente;
	}
	public void setCodigoGerente(int codigoGerente) {
		this.codigoGerente = codigoGerente;
	}
	public int getMedia() {
		return media;
	}
	public void setMedia(int media) {
		this.media = media;
	}
	public boolean isInadimplencia() {
		return inadimplencia;
	}
	public void setInadimplencia(boolean inadimplencia) {
		this.inadimplencia = inadimplencia;
	}
	public String[] getMes() {
		return mes;
	}
	public void setMes(String[] mes) {
		this.mes = mes;
	}
	public Date getMesProjecao() {
		return mesProjecao;
	}
	public void setMesProjecao(Date mesProjecao) {
		this.mesProjecao = mesProjecao;
	}
	public String getMesCestaNatal() {
		return mesCestaNatal;
	}
	public void setMesCestaNatal(String mesCestaNatal) {
		this.mesCestaNatal = mesCestaNatal;
	}
	public int getMesReajFunc() {
		return mesReajFunc;
	}
	public void setMesReajFunc(int mesReajFunc) {
		this.mesReajFunc = mesReajFunc;
	}
	public double getIndiceReajFunc() {
		return indiceReajFunc;
	}
	public void setIndiceReajFunc(double indiceReajFunc) {
		this.indiceReajFunc = indiceReajFunc;
	}
	public int getMesReajTerc() {
		return mesReajTerc;
	}
	public void setMesReajTerc(int mesReajTerc) {
		this.mesReajTerc = mesReajTerc;
	}
	public double getIndiceReajTerc() {
		return indiceReajTerc;
	}
	public void setIndiceReajTerc(double indiceReajTerc) {
		this.indiceReajTerc = indiceReajTerc;
	}
	public int getMesReajMan31() {
		return mesReajMan31;
	}
	public void setMesReajMan31(int mesReajMan31) {
		this.mesReajMan31 = mesReajMan31;
	}
	public int getMesReajMan32() {
		return mesReajMan32;
	}
	public void setMesReajMan32(int mesReajMan32) {
		this.mesReajMan32 = mesReajMan32;
	}
	public int getMesReajMan33() {
		return mesReajMan33;
	}
	public void setMesReajMan33(int mesReajMan33) {
		this.mesReajMan33 = mesReajMan33;
	}
	public int getMesReajMan34() {
		return mesReajMan34;
	}
	public void setMesReajMan34(int mesReajMan34) {
		this.mesReajMan34 = mesReajMan34;
	}
	public int getMesReajMan35() {
		return mesReajMan35;
	}
	public void setMesReajMan35(int mesReajMan35) {
		this.mesReajMan35 = mesReajMan35;
	}
	public int getMesReajMan36() {
		return mesReajMan36;
	}
	public void setMesReajMan36(int mesReajMan36) {
		this.mesReajMan36 = mesReajMan36;
	}
	public int getMesReajMan37() {
		return mesReajMan37;
	}
	public void setMesReajMan37(int mesReajMan37) {
		this.mesReajMan37 = mesReajMan37;
	}
	public int getMesReajMan38() {
		return mesReajMan38;
	}
	public void setMesReajMan38(int mesReajMan38) {
		this.mesReajMan38 = mesReajMan38;
	}
	public int getMesReajMan39() {
		return mesReajMan39;
	}
	public void setMesReajMan39(int mesReajMan39) {
		this.mesReajMan39 = mesReajMan39;
	}
	public int getMesReajMan310() {
		return mesReajMan310;
	}
	public void setMesReajMan310(int mesReajMan310) {
		this.mesReajMan310 = mesReajMan310;
	}
	public int getMesReajMan311() {
		return mesReajMan311;
	}
	public void setMesReajMan311(int mesReajMan311) {
		this.mesReajMan311 = mesReajMan311;
	}
	public int getMesReajMan312() {
		return mesReajMan312;
	}
	public void setMesReajMan312(int mesReajMan312) {
		this.mesReajMan312 = mesReajMan312;
	}
	public int getMesReajMan313() {
		return mesReajMan313;
	}
	public void setMesReajMan313(int mesReajMan313) {
		this.mesReajMan313 = mesReajMan313;
	}
	public int getMesReajMan314() {
		return mesReajMan314;
	}
	public void setMesReajMan314(int mesReajMan314) {
		this.mesReajMan314 = mesReajMan314;
	}
	public int getMesReajMan315() {
		return mesReajMan315;
	}
	public void setMesReajMan315(int mesReajMan315) {
		this.mesReajMan315 = mesReajMan315;
	}
	public int getMesReajMan316() {
		return mesReajMan316;
	}
	public void setMesReajMan316(int mesReajMan316) {
		this.mesReajMan316 = mesReajMan316;
	}
	public int getMesReajMan317() {
		return mesReajMan317;
	}
	public void setMesReajMan317(int mesReajMan317) {
		this.mesReajMan317 = mesReajMan317;
	}
	public int getMesReajMan318() {
		return mesReajMan318;
	}
	public void setMesReajMan318(int mesReajMan318) {
		this.mesReajMan318 = mesReajMan318;
	}
	public int getMesReajMan319() {
		return mesReajMan319;
	}
	public void setMesReajMan319(int mesReajMan319) {
		this.mesReajMan319 = mesReajMan319;
	}
	public int getMesReajMan320() {
		return mesReajMan320;
	}
	public void setMesReajMan320(int mesReajMan320) {
		this.mesReajMan320 = mesReajMan320;
	}
	public int getMesReajMan321() {
		return mesReajMan321;
	}
	public void setMesReajMan321(int mesReajMan321) {
		this.mesReajMan321 = mesReajMan321;
	}
	public int getMesReajMan322() {
		return mesReajMan322;
	}
	public void setMesReajMan322(int mesReajMan322) {
		this.mesReajMan322 = mesReajMan322;
	}
	public int getMesReajMan323() {
		return mesReajMan323;
	}
	public void setMesReajMan323(int mesReajMan323) {
		this.mesReajMan323 = mesReajMan323;
	}
	public int getMesReajMan324() {
		return mesReajMan324;
	}
	public void setMesReajMan324(int mesReajMan324) {
		this.mesReajMan324 = mesReajMan324;
	}
	public int getMesReajMan325() {
		return mesReajMan325;
	}
	public void setMesReajMan325(int mesReajMan325) {
		this.mesReajMan325 = mesReajMan325;
	}
	public int getMesReajMan326() {
		return mesReajMan326;
	}
	public void setMesReajMan326(int mesReajMan326) {
		this.mesReajMan326 = mesReajMan326;
	}
	public int getMesReajMan327() {
		return mesReajMan327;
	}
	public void setMesReajMan327(int mesReajMan327) {
		this.mesReajMan327 = mesReajMan327;
	}
	public int getMesReajMan328() {
		return mesReajMan328;
	}
	public void setMesReajMan328(int mesReajMan328) {
		this.mesReajMan328 = mesReajMan328;
	}
	public int getMesReajMan329() {
		return mesReajMan329;
	}
	public void setMesReajMan329(int mesReajMan329) {
		this.mesReajMan329 = mesReajMan329;
	}
	public int getMesReajMan330() {
		return mesReajMan330;
	}
	public void setMesReajMan330(int mesReajMan330) {
		this.mesReajMan330 = mesReajMan330;
	}
	public int getMesReajMan331() {
		return mesReajMan331;
	}
	public void setMesReajMan331(int mesReajMan331) {
		this.mesReajMan331 = mesReajMan331;
	}
	public double getIndiceReajMan31() {
		return indiceReajMan31;
	}
	public void setIndiceReajMan31(double indiceReajMan31) {
		this.indiceReajMan31 = indiceReajMan31;
	}
	public double getIndiceReajMan32() {
		return indiceReajMan32;
	}
	public void setIndiceReajMan32(double indiceReajMan32) {
		this.indiceReajMan32 = indiceReajMan32;
	}
	public double getIndiceReajMan33() {
		return indiceReajMan33;
	}
	public void setIndiceReajMan33(double indiceReajMan33) {
		this.indiceReajMan33 = indiceReajMan33;
	}
	public double getIndiceReajMan34() {
		return indiceReajMan34;
	}
	public void setIndiceReajMan34(double indiceReajMan34) {
		this.indiceReajMan34 = indiceReajMan34;
	}
	public double getIndiceReajMan35() {
		return indiceReajMan35;
	}
	public void setIndiceReajMan35(double indiceReajMan35) {
		this.indiceReajMan35 = indiceReajMan35;
	}
	public double getIndiceReajMan36() {
		return indiceReajMan36;
	}
	public void setIndiceReajMan36(double indiceReajMan36) {
		this.indiceReajMan36 = indiceReajMan36;
	}
	public double getIndiceReajMan37() {
		return indiceReajMan37;
	}
	public void setIndiceReajMan37(double indiceReajMan37) {
		this.indiceReajMan37 = indiceReajMan37;
	}
	public double getIndiceReajMan38() {
		return indiceReajMan38;
	}
	public void setIndiceReajMan38(double indiceReajMan38) {
		this.indiceReajMan38 = indiceReajMan38;
	}
	public double getIndiceReajMan39() {
		return indiceReajMan39;
	}
	public void setIndiceReajMan39(double indiceReajMan39) {
		this.indiceReajMan39 = indiceReajMan39;
	}
	public double getIndiceReajMan310() {
		return indiceReajMan310;
	}
	public void setIndiceReajMan310(double indiceReajMan310) {
		this.indiceReajMan310 = indiceReajMan310;
	}
	public double getIndiceReajMan311() {
		return indiceReajMan311;
	}
	public void setIndiceReajMan311(double indiceReajMan311) {
		this.indiceReajMan311 = indiceReajMan311;
	}
	public double getIndiceReajMan312() {
		return indiceReajMan312;
	}
	public void setIndiceReajMan312(double indiceReajMan312) {
		this.indiceReajMan312 = indiceReajMan312;
	}
	public double getIndiceReajMan313() {
		return indiceReajMan313;
	}
	public void setIndiceReajMan313(double indiceReajMan313) {
		this.indiceReajMan313 = indiceReajMan313;
	}
	public double getIndiceReajMan314() {
		return indiceReajMan314;
	}
	public void setIndiceReajMan314(double indiceReajMan314) {
		this.indiceReajMan314 = indiceReajMan314;
	}
	public double getIndiceReajMan315() {
		return indiceReajMan315;
	}
	public void setIndiceReajMan315(double indiceReajMan315) {
		this.indiceReajMan315 = indiceReajMan315;
	}
	public double getIndiceReajMan316() {
		return indiceReajMan316;
	}
	public void setIndiceReajMan316(double indiceReajMan316) {
		this.indiceReajMan316 = indiceReajMan316;
	}
	public double getIndiceReajMan317() {
		return indiceReajMan317;
	}
	public void setIndiceReajMan317(double indiceReajMan317) {
		this.indiceReajMan317 = indiceReajMan317;
	}
	public double getIndiceReajMan318() {
		return indiceReajMan318;
	}
	public void setIndiceReajMan318(double indiceReajMan318) {
		this.indiceReajMan318 = indiceReajMan318;
	}
	public double getIndiceReajMan319() {
		return indiceReajMan319;
	}
	public void setIndiceReajMan319(double indiceReajMan319) {
		this.indiceReajMan319 = indiceReajMan319;
	}
	public double getIndiceReajMan320() {
		return indiceReajMan320;
	}
	public void setIndiceReajMan320(double indiceReajMan320) {
		this.indiceReajMan320 = indiceReajMan320;
	}
	public double getIndiceReajMan321() {
		return indiceReajMan321;
	}
	public void setIndiceReajMan321(double indiceReajMan321) {
		this.indiceReajMan321 = indiceReajMan321;
	}
	public double getIndiceReajMan322() {
		return indiceReajMan322;
	}
	public void setIndiceReajMan322(double indiceReajMan322) {
		this.indiceReajMan322 = indiceReajMan322;
	}
	public double getIndiceReajMan323() {
		return indiceReajMan323;
	}
	public void setIndiceReajMan323(double indiceReajMan323) {
		this.indiceReajMan323 = indiceReajMan323;
	}
	public double getIndiceReajMan324() {
		return indiceReajMan324;
	}
	public void setIndiceReajMan324(double indiceReajMan324) {
		this.indiceReajMan324 = indiceReajMan324;
	}
	public double getIndiceReajMan325() {
		return indiceReajMan325;
	}
	public void setIndiceReajMan325(double indiceReajMan325) {
		this.indiceReajMan325 = indiceReajMan325;
	}
	public double getIndiceReajMan326() {
		return indiceReajMan326;
	}
	public void setIndiceReajMan326(double indiceReajMan326) {
		this.indiceReajMan326 = indiceReajMan326;
	}
	public double getIndiceReajMan327() {
		return indiceReajMan327;
	}
	public void setIndiceReajMan327(double indiceReajMan327) {
		this.indiceReajMan327 = indiceReajMan327;
	}
	public double getIndiceReajMan328() {
		return indiceReajMan328;
	}
	public void setIndiceReajMan328(double indiceReajMan328) {
		this.indiceReajMan328 = indiceReajMan328;
	}
	public double getIndiceReajMan329() {
		return indiceReajMan329;
	}
	public void setIndiceReajMan329(double indiceReajMan329) {
		this.indiceReajMan329 = indiceReajMan329;
	}
	public double getIndiceReajMan330() {
		return indiceReajMan330;
	}
	public void setIndiceReajMan330(double indiceReajMan330) {
		this.indiceReajMan330 = indiceReajMan330;
	}
	public double getIndiceReajMan331() {
		return indiceReajMan331;
	}
	public void setIndiceReajMan331(double indiceReajMan331) {
		this.indiceReajMan331 = indiceReajMan331;
	}
	public int getMesReajAdm71() {
		return mesReajAdm71;
	}
	public void setMesReajAdm71(int mesReajAdm71) {
		this.mesReajAdm71 = mesReajAdm71;
	}
	public int getMesReajAdm72() {
		return mesReajAdm72;
	}
	public void setMesReajAdm72(int mesReajAdm72) {
		this.mesReajAdm72 = mesReajAdm72;
	}
	public int getMesReajAdm73() {
		return mesReajAdm73;
	}
	public void setMesReajAdm73(int mesReajAdm73) {
		this.mesReajAdm73 = mesReajAdm73;
	}
	public int getMesReajAdm74() {
		return mesReajAdm74;
	}
	public void setMesReajAdm74(int mesReajAdm74) {
		this.mesReajAdm74 = mesReajAdm74;
	}
	public int getMesReajAdm75() {
		return mesReajAdm75;
	}
	public void setMesReajAdm75(int mesReajAdm75) {
		this.mesReajAdm75 = mesReajAdm75;
	}
	public int getMesReajAdm76() {
		return mesReajAdm76;
	}
	public void setMesReajAdm76(int mesReajAdm76) {
		this.mesReajAdm76 = mesReajAdm76;
	}
	public double getIndiceReajAdm71() {
		return indiceReajAdm71;
	}
	public void setIndiceReajAdm71(double indiceReajAdm71) {
		this.indiceReajAdm71 = indiceReajAdm71;
	}
	public double getIndiceReajAdm72() {
		return indiceReajAdm72;
	}
	public void setIndiceReajAdm72(double indiceReajAdm72) {
		this.indiceReajAdm72 = indiceReajAdm72;
	}
	public double getIndiceReajAdm73() {
		return indiceReajAdm73;
	}
	public void setIndiceReajAdm73(double indiceReajAdm73) {
		this.indiceReajAdm73 = indiceReajAdm73;
	}
	public double getIndiceReajAdm74() {
		return indiceReajAdm74;
	}
	public void setIndiceReajAdm74(double indiceReajAdm74) {
		this.indiceReajAdm74 = indiceReajAdm74;
	}
	public double getIndiceReajAdm75() {
		return indiceReajAdm75;
	}
	public void setIndiceReajAdm75(double indiceReajAdm75) {
		this.indiceReajAdm75 = indiceReajAdm75;
	}
	public double getIndiceReajAdm76() {
		return indiceReajAdm76;
	}
	public void setIndiceReajAdm76(double indiceReajAdm76) {
		this.indiceReajAdm76 = indiceReajAdm76;
	}
	public String getCapa0() {
		return capa0;
	}
	public void setCapa0(String capa0) {
		this.capa0 = capa0;
	}
	public String getCapa1() {
		return capa1;
	}
	public void setCapa1(String capa1) {
		this.capa1 = capa1;
	}
	public String getCapa2() {
		return capa2;
	}
	public void setCapa2(String capa2) {
		this.capa2 = capa2;
	}
	public String getCapa3() {
		return capa3;
	}
	public void setCapa3(String capa3) {
		this.capa3 = capa3;
	}
	public String getCapa4() {
		return capa4;
	}
	public void setCapa4(String capa4) {
		this.capa4 = capa4;
	}
	public String getCapa5() {
		return capa5;
	}
	public void setCapa5(String capa5) {
		this.capa5 = capa5;
	}
	public String getCapa6() {
		return capa6;
	}
	public void setCapa6(String capa6) {
		this.capa6 = capa6;
	}
	public String getCapa7() {
		return capa7;
	}
	public void setCapa7(String capa7) {
		this.capa7 = capa7;
	}
	public String getCapa8() {
		return capa8;
	}
	public void setCapa8(String capa8) {
		this.capa8 = capa8;
	}
	public String getNomeConta01() {
		return nomeConta01;
	}
	public void setNomeConta01(String nomeConta01) {
		this.nomeConta01 = nomeConta01;
	}
	public String getNomeConta02() {
		return nomeConta02;
	}
	public void setNomeConta02(String nomeConta02) {
		this.nomeConta02 = nomeConta02;
	}
	public String getNomeConta03() {
		return nomeConta03;
	}
	public void setNomeConta03(String nomeConta03) {
		this.nomeConta03 = nomeConta03;
	}
	public String getNomeConta04() {
		return nomeConta04;
	}
	public void setNomeConta04(String nomeConta04) {
		this.nomeConta04 = nomeConta04;
	}
	public String getNomeConta05() {
		return nomeConta05;
	}
	public void setNomeConta05(String nomeConta05) {
		this.nomeConta05 = nomeConta05;
	}
	public String getNomeConta06() {
		return nomeConta06;
	}
	public void setNomeConta06(String nomeConta06) {
		this.nomeConta06 = nomeConta06;
	}
	public String getNomeConta07() {
		return nomeConta07;
	}
	public void setNomeConta07(String nomeConta07) {
		this.nomeConta07 = nomeConta07;
	}
	public String getNomeConta08() {
		return nomeConta08;
	}
	public void setNomeConta08(String nomeConta08) {
		this.nomeConta08 = nomeConta08;
	}
	public String getNomeConta09() {
		return nomeConta09;
	}
	public void setNomeConta09(String nomeConta09) {
		this.nomeConta09 = nomeConta09;
	}
	public String getNomeConta010() {
		return nomeConta010;
	}
	public void setNomeConta010(String nomeConta010) {
		this.nomeConta010 = nomeConta010;
	}
	public String getNomeConta011() {
		return nomeConta011;
	}
	public void setNomeConta011(String nomeConta011) {
		this.nomeConta011 = nomeConta011;
	}
	public String getNomeConta012() {
		return nomeConta012;
	}
	public void setNomeConta012(String nomeConta012) {
		this.nomeConta012 = nomeConta012;
	}
	public String getNomeConta013() {
		return nomeConta013;
	}
	public void setNomeConta013(String nomeConta013) {
		this.nomeConta013 = nomeConta013;
	}
	public String getNomeConta014() {
		return nomeConta014;
	}
	public void setNomeConta014(String nomeConta014) {
		this.nomeConta014 = nomeConta014;
	}
	public String getNomeConta015() {
		return nomeConta015;
	}
	public void setNomeConta015(String nomeConta015) {
		this.nomeConta015 = nomeConta015;
	}
	public String getNomeConta016() {
		return nomeConta016;
	}
	public void setNomeConta016(String nomeConta016) {
		this.nomeConta016 = nomeConta016;
	}
	public String getNomeConta017() {
		return nomeConta017;
	}
	public void setNomeConta017(String nomeConta017) {
		this.nomeConta017 = nomeConta017;
	}
	public String getNomeConta018() {
		return nomeConta018;
	}
	public void setNomeConta018(String nomeConta018) {
		this.nomeConta018 = nomeConta018;
	}
	public String getNomeConta019() {
		return nomeConta019;
	}
	public void setNomeConta019(String nomeConta019) {
		this.nomeConta019 = nomeConta019;
	}
	public String getNomeConta020() {
		return nomeConta020;
	}
	public void setNomeConta020(String nomeConta020) {
		this.nomeConta020 = nomeConta020;
	}
	public String getNomeConta021() {
		return nomeConta021;
	}
	public void setNomeConta021(String nomeConta021) {
		this.nomeConta021 = nomeConta021;
	}
	public String getNomeConta022() {
		return nomeConta022;
	}
	public void setNomeConta022(String nomeConta022) {
		this.nomeConta022 = nomeConta022;
	}
	public String getNomeConta023() {
		return nomeConta023;
	}
	public void setNomeConta023(String nomeConta023) {
		this.nomeConta023 = nomeConta023;
	}
	public String getNomeConta024() {
		return nomeConta024;
	}
	public void setNomeConta024(String nomeConta024) {
		this.nomeConta024 = nomeConta024;
	}
	public String getNomeConta025() {
		return nomeConta025;
	}
	public void setNomeConta025(String nomeConta025) {
		this.nomeConta025 = nomeConta025;
	}
	public String getNomeConta026() {
		return nomeConta026;
	}
	public void setNomeConta026(String nomeConta026) {
		this.nomeConta026 = nomeConta026;
	}
	public String getNomeConta027() {
		return nomeConta027;
	}
	public void setNomeConta027(String nomeConta027) {
		this.nomeConta027 = nomeConta027;
	}
	public String getNomeConta028() {
		return nomeConta028;
	}
	public void setNomeConta028(String nomeConta028) {
		this.nomeConta028 = nomeConta028;
	}
	public String getNomeConta029() {
		return nomeConta029;
	}
	public void setNomeConta029(String nomeConta029) {
		this.nomeConta029 = nomeConta029;
	}
	public String getNomeConta030() {
		return nomeConta030;
	}
	public void setNomeConta030(String nomeConta030) {
		this.nomeConta030 = nomeConta030;
	}
	public String getNomeConta031() {
		return nomeConta031;
	}
	public void setNomeConta031(String nomeConta031) {
		this.nomeConta031 = nomeConta031;
	}
	public String getNomeConta032() {
		return nomeConta032;
	}
	public void setNomeConta032(String nomeConta032) {
		this.nomeConta032 = nomeConta032;
	}
	public String getNomeConta033() {
		return nomeConta033;
	}
	public void setNomeConta033(String nomeConta033) {
		this.nomeConta033 = nomeConta033;
	}
	public String getNomeConta034() {
		return nomeConta034;
	}
	public void setNomeConta034(String nomeConta034) {
		this.nomeConta034 = nomeConta034;
	}
	public String getNomeConta035() {
		return nomeConta035;
	}
	public void setNomeConta035(String nomeConta035) {
		this.nomeConta035 = nomeConta035;
	}
	public String getNomeConta036() {
		return nomeConta036;
	}
	public void setNomeConta036(String nomeConta036) {
		this.nomeConta036 = nomeConta036;
	}
	public String getNomeConta037() {
		return nomeConta037;
	}
	public void setNomeConta037(String nomeConta037) {
		this.nomeConta037 = nomeConta037;
	}
	public String getNomeConta038() {
		return nomeConta038;
	}
	public void setNomeConta038(String nomeConta038) {
		this.nomeConta038 = nomeConta038;
	}
	public String getNomeConta11() {
		return nomeConta11;
	}
	public void setNomeConta11(String nomeConta11) {
		this.nomeConta11 = nomeConta11;
	}
	public String getNomeConta12() {
		return nomeConta12;
	}
	public void setNomeConta12(String nomeConta12) {
		this.nomeConta12 = nomeConta12;
	}
	public String getNomeConta13() {
		return nomeConta13;
	}
	public void setNomeConta13(String nomeConta13) {
		this.nomeConta13 = nomeConta13;
	}
	public String getNomeConta14() {
		return nomeConta14;
	}
	public void setNomeConta14(String nomeConta14) {
		this.nomeConta14 = nomeConta14;
	}
	public String getNomeConta15() {
		return nomeConta15;
	}
	public void setNomeConta15(String nomeConta15) {
		this.nomeConta15 = nomeConta15;
	}
	public String getNomeConta21() {
		return nomeConta21;
	}
	public void setNomeConta21(String nomeConta21) {
		this.nomeConta21 = nomeConta21;
	}
	public String getNomeConta22() {
		return nomeConta22;
	}
	public void setNomeConta22(String nomeConta22) {
		this.nomeConta22 = nomeConta22;
	}
	public String getNomeConta23() {
		return nomeConta23;
	}
	public void setNomeConta23(String nomeConta23) {
		this.nomeConta23 = nomeConta23;
	}
	public String getNomeConta24() {
		return nomeConta24;
	}
	public void setNomeConta24(String nomeConta24) {
		this.nomeConta24 = nomeConta24;
	}
	public String getNomeConta25() {
		return nomeConta25;
	}
	public void setNomeConta25(String nomeConta25) {
		this.nomeConta25 = nomeConta25;
	}
	public String getNomeConta26() {
		return nomeConta26;
	}
	public void setNomeConta26(String nomeConta26) {
		this.nomeConta26 = nomeConta26;
	}
	public String getNomeConta27() {
		return nomeConta27;
	}
	public void setNomeConta27(String nomeConta27) {
		this.nomeConta27 = nomeConta27;
	}
	public String getNomeConta31() {
		return nomeConta31;
	}
	public void setNomeConta31(String nomeConta31) {
		this.nomeConta31 = nomeConta31;
	}
	public String getNomeConta32() {
		return nomeConta32;
	}
	public void setNomeConta32(String nomeConta32) {
		this.nomeConta32 = nomeConta32;
	}
	public String getNomeConta33() {
		return nomeConta33;
	}
	public void setNomeConta33(String nomeConta33) {
		this.nomeConta33 = nomeConta33;
	}
	public String getNomeConta34() {
		return nomeConta34;
	}
	public void setNomeConta34(String nomeConta34) {
		this.nomeConta34 = nomeConta34;
	}
	public String getNomeConta35() {
		return nomeConta35;
	}
	public void setNomeConta35(String nomeConta35) {
		this.nomeConta35 = nomeConta35;
	}
	public String getNomeConta36() {
		return nomeConta36;
	}
	public void setNomeConta36(String nomeConta36) {
		this.nomeConta36 = nomeConta36;
	}
	public String getNomeConta37() {
		return nomeConta37;
	}
	public void setNomeConta37(String nomeConta37) {
		this.nomeConta37 = nomeConta37;
	}
	public String getNomeConta38() {
		return nomeConta38;
	}
	public void setNomeConta38(String nomeConta38) {
		this.nomeConta38 = nomeConta38;
	}
	public String getNomeConta39() {
		return nomeConta39;
	}
	public void setNomeConta39(String nomeConta39) {
		this.nomeConta39 = nomeConta39;
	}
	public String getNomeConta310() {
		return nomeConta310;
	}
	public void setNomeConta310(String nomeConta310) {
		this.nomeConta310 = nomeConta310;
	}
	public String getNomeConta311() {
		return nomeConta311;
	}
	public void setNomeConta311(String nomeConta311) {
		this.nomeConta311 = nomeConta311;
	}
	public String getNomeConta312() {
		return nomeConta312;
	}
	public void setNomeConta312(String nomeConta312) {
		this.nomeConta312 = nomeConta312;
	}
	public String getNomeConta313() {
		return nomeConta313;
	}
	public void setNomeConta313(String nomeConta313) {
		this.nomeConta313 = nomeConta313;
	}
	public String getNomeConta314() {
		return nomeConta314;
	}
	public void setNomeConta314(String nomeConta314) {
		this.nomeConta314 = nomeConta314;
	}
	public String getNomeConta315() {
		return nomeConta315;
	}
	public void setNomeConta315(String nomeConta315) {
		this.nomeConta315 = nomeConta315;
	}
	public String getNomeConta316() {
		return nomeConta316;
	}
	public void setNomeConta316(String nomeConta316) {
		this.nomeConta316 = nomeConta316;
	}
	public String getNomeConta317() {
		return nomeConta317;
	}
	public void setNomeConta317(String nomeConta317) {
		this.nomeConta317 = nomeConta317;
	}
	public String getNomeConta318() {
		return nomeConta318;
	}
	public void setNomeConta318(String nomeConta318) {
		this.nomeConta318 = nomeConta318;
	}
	public String getNomeConta319() {
		return nomeConta319;
	}
	public void setNomeConta319(String nomeConta319) {
		this.nomeConta319 = nomeConta319;
	}
	public String getNomeConta320() {
		return nomeConta320;
	}
	public void setNomeConta320(String nomeConta320) {
		this.nomeConta320 = nomeConta320;
	}
	public String getNomeConta321() {
		return nomeConta321;
	}
	public void setNomeConta321(String nomeConta321) {
		this.nomeConta321 = nomeConta321;
	}
	public String getNomeConta322() {
		return nomeConta322;
	}
	public void setNomeConta322(String nomeConta322) {
		this.nomeConta322 = nomeConta322;
	}
	public String getNomeConta323() {
		return nomeConta323;
	}
	public void setNomeConta323(String nomeConta323) {
		this.nomeConta323 = nomeConta323;
	}
	public String getNomeConta324() {
		return nomeConta324;
	}
	public void setNomeConta324(String nomeConta324) {
		this.nomeConta324 = nomeConta324;
	}
	public String getNomeConta325() {
		return nomeConta325;
	}
	public void setNomeConta325(String nomeConta325) {
		this.nomeConta325 = nomeConta325;
	}
	public String getNomeConta326() {
		return nomeConta326;
	}
	public void setNomeConta326(String nomeConta326) {
		this.nomeConta326 = nomeConta326;
	}
	public String getNomeConta327() {
		return nomeConta327;
	}
	public void setNomeConta327(String nomeConta327) {
		this.nomeConta327 = nomeConta327;
	}
	public String getNomeConta328() {
		return nomeConta328;
	}
	public void setNomeConta328(String nomeConta328) {
		this.nomeConta328 = nomeConta328;
	}
	public String getNomeConta329() {
		return nomeConta329;
	}
	public void setNomeConta329(String nomeConta329) {
		this.nomeConta329 = nomeConta329;
	}
	public String getNomeConta330() {
		return nomeConta330;
	}
	public void setNomeConta330(String nomeConta330) {
		this.nomeConta330 = nomeConta330;
	}
	public String getNomeConta331() {
		return nomeConta331;
	}
	public void setNomeConta331(String nomeConta331) {
		this.nomeConta331 = nomeConta331;
	}
	public String getNomeConta41() {
		return nomeConta41;
	}
	public void setNomeConta41(String nomeConta41) {
		this.nomeConta41 = nomeConta41;
	}
	public String getNomeConta42() {
		return nomeConta42;
	}
	public void setNomeConta42(String nomeConta42) {
		this.nomeConta42 = nomeConta42;
	}
	public String getNomeConta51() {
		return nomeConta51;
	}
	public void setNomeConta51(String nomeConta51) {
		this.nomeConta51 = nomeConta51;
	}
	public String getNomeConta52() {
		return nomeConta52;
	}
	public void setNomeConta52(String nomeConta52) {
		this.nomeConta52 = nomeConta52;
	}
	public String getNomeConta53() {
		return nomeConta53;
	}
	public void setNomeConta53(String nomeConta53) {
		this.nomeConta53 = nomeConta53;
	}
	public String getNomeConta54() {
		return nomeConta54;
	}
	public void setNomeConta54(String nomeConta54) {
		this.nomeConta54 = nomeConta54;
	}
	public String getNomeConta55() {
		return nomeConta55;
	}
	public void setNomeConta55(String nomeConta55) {
		this.nomeConta55 = nomeConta55;
	}
	public String getNomeConta56() {
		return nomeConta56;
	}
	public void setNomeConta56(String nomeConta56) {
		this.nomeConta56 = nomeConta56;
	}
	public String getNomeConta61() {
		return nomeConta61;
	}
	public void setNomeConta61(String nomeConta61) {
		this.nomeConta61 = nomeConta61;
	}
	public String getNomeConta62() {
		return nomeConta62;
	}
	public void setNomeConta62(String nomeConta62) {
		this.nomeConta62 = nomeConta62;
	}
	public String getNomeConta63() {
		return nomeConta63;
	}
	public void setNomeConta63(String nomeConta63) {
		this.nomeConta63 = nomeConta63;
	}
	public String getNomeConta64() {
		return nomeConta64;
	}
	public void setNomeConta64(String nomeConta64) {
		this.nomeConta64 = nomeConta64;
	}
	public String getNomeConta65() {
		return nomeConta65;
	}
	public void setNomeConta65(String nomeConta65) {
		this.nomeConta65 = nomeConta65;
	}
	public String getNomeConta66() {
		return nomeConta66;
	}
	public void setNomeConta66(String nomeConta66) {
		this.nomeConta66 = nomeConta66;
	}
	public String getNomeConta67() {
		return nomeConta67;
	}
	public void setNomeConta67(String nomeConta67) {
		this.nomeConta67 = nomeConta67;
	}
	public String getNomeConta68() {
		return nomeConta68;
	}
	public void setNomeConta68(String nomeConta68) {
		this.nomeConta68 = nomeConta68;
	}
	public String getNomeConta69() {
		return nomeConta69;
	}
	public void setNomeConta69(String nomeConta69) {
		this.nomeConta69 = nomeConta69;
	}
	public String getNomeConta71() {
		return nomeConta71;
	}
	public void setNomeConta71(String nomeConta71) {
		this.nomeConta71 = nomeConta71;
	}
	public String getNomeConta72() {
		return nomeConta72;
	}
	public void setNomeConta72(String nomeConta72) {
		this.nomeConta72 = nomeConta72;
	}
	public String getNomeConta73() {
		return nomeConta73;
	}
	public void setNomeConta73(String nomeConta73) {
		this.nomeConta73 = nomeConta73;
	}
	public String getNomeConta74() {
		return nomeConta74;
	}
	public void setNomeConta74(String nomeConta74) {
		this.nomeConta74 = nomeConta74;
	}
	public String getNomeConta75() {
		return nomeConta75;
	}
	public void setNomeConta75(String nomeConta75) {
		this.nomeConta75 = nomeConta75;
	}
	public String getNomeConta76() {
		return nomeConta76;
	}
	public void setNomeConta76(String nomeConta76) {
		this.nomeConta76 = nomeConta76;
	}
	public String getNomeConta81() {
		return nomeConta81;
	}
	public void setNomeConta81(String nomeConta81) {
		this.nomeConta81 = nomeConta81;
	}
	public String getNomeConta82() {
		return nomeConta82;
	}
	public void setNomeConta82(String nomeConta82) {
		this.nomeConta82 = nomeConta82;
	}
	public String getNomeConta83() {
		return nomeConta83;
	}
	public void setNomeConta83(String nomeConta83) {
		this.nomeConta83 = nomeConta83;
	}
	public String getNomeConta84() {
		return nomeConta84;
	}
	public void setNomeConta84(String nomeConta84) {
		this.nomeConta84 = nomeConta84;
	}
	public String getNomeConta85() {
		return nomeConta85;
	}
	public void setNomeConta85(String nomeConta85) {
		this.nomeConta85 = nomeConta85;
	}
	public String getNomeConta86() {
		return nomeConta86;
	}
	public void setNomeConta86(String nomeConta86) {
		this.nomeConta86 = nomeConta86;
	}
	public String getNomeConta87() {
		return nomeConta87;
	}
	public void setNomeConta87(String nomeConta87) {
		this.nomeConta87 = nomeConta87;
	}
	public String getNomeConta88() {
		return nomeConta88;
	}
	public void setNomeConta88(String nomeConta88) {
		this.nomeConta88 = nomeConta88;
	}
	public String getNomeConta89() {
		return nomeConta89;
	}
	public void setNomeConta89(String nomeConta89) {
		this.nomeConta89 = nomeConta89;
	}
	public String getNomeConta810() {
		return nomeConta810;
	}
	public void setNomeConta810(String nomeConta810) {
		this.nomeConta810 = nomeConta810;
	}
	public String getNomeConta811() {
		return nomeConta811;
	}
	public void setNomeConta811(String nomeConta811) {
		this.nomeConta811 = nomeConta811;
	}
	public String getNomeConta812() {
		return nomeConta812;
	}
	public void setNomeConta812(String nomeConta812) {
		this.nomeConta812 = nomeConta812;
	}
	public String getNomeConta813() {
		return nomeConta813;
	}
	public void setNomeConta813(String nomeConta813) {
		this.nomeConta813 = nomeConta813;
	}
	public String getNomeConta814() {
		return nomeConta814;
	}
	public void setNomeConta814(String nomeConta814) {
		this.nomeConta814 = nomeConta814;
	}
	public String getNomeConta815() {
		return nomeConta815;
	}
	public void setNomeConta815(String nomeConta815) {
		this.nomeConta815 = nomeConta815;
	}
	public String getNomeConta816() {
		return nomeConta816;
	}
	public void setNomeConta816(String nomeConta816) {
		this.nomeConta816 = nomeConta816;
	}
	public String getNomeConta817() {
		return nomeConta817;
	}
	public void setNomeConta817(String nomeConta817) {
		this.nomeConta817 = nomeConta817;
	}
	public String getNomeConta818() {
		return nomeConta818;
	}
	public void setNomeConta818(String nomeConta818) {
		this.nomeConta818 = nomeConta818;
	}
	public String getNomeConta819() {
		return nomeConta819;
	}
	public void setNomeConta819(String nomeConta819) {
		this.nomeConta819 = nomeConta819;
	}
	public String getNomeConta820() {
		return nomeConta820;
	}
	public void setNomeConta820(String nomeConta820) {
		this.nomeConta820 = nomeConta820;
	}
	public String getNomeConta821() {
		return nomeConta821;
	}
	public void setNomeConta821(String nomeConta821) {
		this.nomeConta821 = nomeConta821;
	}
	public String getNomeConta822() {
		return nomeConta822;
	}
	public void setNomeConta822(String nomeConta822) {
		this.nomeConta822 = nomeConta822;
	}
	public String getNomeConta823() {
		return nomeConta823;
	}
	public void setNomeConta823(String nomeConta823) {
		this.nomeConta823 = nomeConta823;
	}
	public String getNomeConta824() {
		return nomeConta824;
	}
	public void setNomeConta824(String nomeConta824) {
		this.nomeConta824 = nomeConta824;
	}
	public String getNomeConta825() {
		return nomeConta825;
	}
	public void setNomeConta825(String nomeConta825) {
		this.nomeConta825 = nomeConta825;
	}
	public String getNomeConta826() {
		return nomeConta826;
	}
	public void setNomeConta826(String nomeConta826) {
		this.nomeConta826 = nomeConta826;
	}
	public String getNomeConta827() {
		return nomeConta827;
	}
	public void setNomeConta827(String nomeConta827) {
		this.nomeConta827 = nomeConta827;
	}
	public String getNomeConta828() {
		return nomeConta828;
	}
	public void setNomeConta828(String nomeConta828) {
		this.nomeConta828 = nomeConta828;
	}
	public String getNomeConta829() {
		return nomeConta829;
	}
	public void setNomeConta829(String nomeConta829) {
		this.nomeConta829 = nomeConta829;
	}
	public String getNomeConta830() {
		return nomeConta830;
	}
	public void setNomeConta830(String nomeConta830) {
		this.nomeConta830 = nomeConta830;
	}
	public String getNomeConta831() {
		return nomeConta831;
	}
	public void setNomeConta831(String nomeConta831) {
		this.nomeConta831 = nomeConta831;
	}
	public String getNomeConta832() {
		return nomeConta832;
	}
	public void setNomeConta832(String nomeConta832) {
		this.nomeConta832 = nomeConta832;
	}
	public String getNomeConta833() {
		return nomeConta833;
	}
	public void setNomeConta833(String nomeConta833) {
		this.nomeConta833 = nomeConta833;
	}
	public String getNomeConta834() {
		return nomeConta834;
	}
	public void setNomeConta834(String nomeConta834) {
		this.nomeConta834 = nomeConta834;
	}
	public String getNomeConta835() {
		return nomeConta835;
	}
	public void setNomeConta835(String nomeConta835) {
		this.nomeConta835 = nomeConta835;
	}
	public String getNomeConta836() {
		return nomeConta836;
	}
	public void setNomeConta836(String nomeConta836) {
		this.nomeConta836 = nomeConta836;
	}
	public String getNomeConta837() {
		return nomeConta837;
	}
	public void setNomeConta837(String nomeConta837) {
		this.nomeConta837 = nomeConta837;
	}
	public double getValorConta01() {
		return valorConta01;
	}
	public void setValorConta01(double valorConta01) {
		this.valorConta01 = valorConta01;
	}
	public double getValorConta02() {
		return valorConta02;
	}
	public void setValorConta02(double valorConta02) {
		this.valorConta02 = valorConta02;
	}
	public double getValorConta03() {
		return valorConta03;
	}
	public void setValorConta03(double valorConta03) {
		this.valorConta03 = valorConta03;
	}
	public double getValorConta04() {
		return valorConta04;
	}
	public void setValorConta04(double valorConta04) {
		this.valorConta04 = valorConta04;
	}
	public double getValorConta05() {
		return valorConta05;
	}
	public void setValorConta05(double valorConta05) {
		this.valorConta05 = valorConta05;
	}
	public double getValorConta06() {
		return valorConta06;
	}
	public void setValorConta06(double valorConta06) {
		this.valorConta06 = valorConta06;
	}
	public double getValorConta07() {
		return valorConta07;
	}
	public void setValorConta07(double valorConta07) {
		this.valorConta07 = valorConta07;
	}
	public double getValorConta08() {
		return valorConta08;
	}
	public void setValorConta08(double valorConta08) {
		this.valorConta08 = valorConta08;
	}
	public double getValorConta09() {
		return valorConta09;
	}
	public void setValorConta09(double valorConta09) {
		this.valorConta09 = valorConta09;
	}
	public double getValorConta010() {
		return valorConta010;
	}
	public void setValorConta010(double valorConta010) {
		this.valorConta010 = valorConta010;
	}
	public double getValorConta011() {
		return valorConta011;
	}
	public void setValorConta011(double valorConta011) {
		this.valorConta011 = valorConta011;
	}
	public double getValorConta012() {
		return valorConta012;
	}
	public void setValorConta012(double valorConta012) {
		this.valorConta012 = valorConta012;
	}
	public double getValorConta013() {
		return valorConta013;
	}
	public void setValorConta013(double valorConta013) {
		this.valorConta013 = valorConta013;
	}
	public double getValorConta014() {
		return valorConta014;
	}
	public void setValorConta014(double valorConta014) {
		this.valorConta014 = valorConta014;
	}
	public double getValorConta015() {
		return valorConta015;
	}
	public void setValorConta015(double valorConta015) {
		this.valorConta015 = valorConta015;
	}
	public double getValorConta016() {
		return valorConta016;
	}
	public void setValorConta016(double valorConta016) {
		this.valorConta016 = valorConta016;
	}
	public double getValorConta017() {
		return valorConta017;
	}
	public void setValorConta017(double valorConta017) {
		this.valorConta017 = valorConta017;
	}
	public double getValorConta018() {
		return valorConta018;
	}
	public void setValorConta018(double valorConta018) {
		this.valorConta018 = valorConta018;
	}
	public double getValorConta019() {
		return valorConta019;
	}
	public void setValorConta019(double valorConta019) {
		this.valorConta019 = valorConta019;
	}
	public double getValorConta020() {
		return valorConta020;
	}
	public void setValorConta020(double valorConta020) {
		this.valorConta020 = valorConta020;
	}
	public double getValorConta021() {
		return valorConta021;
	}
	public void setValorConta021(double valorConta021) {
		this.valorConta021 = valorConta021;
	}
	public double getValorConta022() {
		return valorConta022;
	}
	public void setValorConta022(double valorConta022) {
		this.valorConta022 = valorConta022;
	}
	public double getValorConta023() {
		return valorConta023;
	}
	public void setValorConta023(double valorConta023) {
		this.valorConta023 = valorConta023;
	}
	public double getValorConta024() {
		return valorConta024;
	}
	public void setValorConta024(double valorConta024) {
		this.valorConta024 = valorConta024;
	}
	public double getValorConta025() {
		return valorConta025;
	}
	public void setValorConta025(double valorConta025) {
		this.valorConta025 = valorConta025;
	}
	public double getValorConta026() {
		return valorConta026;
	}
	public void setValorConta026(double valorConta026) {
		this.valorConta026 = valorConta026;
	}
	public double getValorConta027() {
		return valorConta027;
	}
	public void setValorConta027(double valorConta027) {
		this.valorConta027 = valorConta027;
	}
	public double getValorConta028() {
		return valorConta028;
	}
	public void setValorConta028(double valorConta028) {
		this.valorConta028 = valorConta028;
	}
	public double getValorConta029() {
		return valorConta029;
	}
	public void setValorConta029(double valorConta029) {
		this.valorConta029 = valorConta029;
	}
	public double getValorConta030() {
		return valorConta030;
	}
	public void setValorConta030(double valorConta030) {
		this.valorConta030 = valorConta030;
	}
	public double getValorConta031() {
		return valorConta031;
	}
	public void setValorConta031(double valorConta031) {
		this.valorConta031 = valorConta031;
	}
	public double getValorConta032() {
		return valorConta032;
	}
	public void setValorConta032(double valorConta032) {
		this.valorConta032 = valorConta032;
	}
	public double getValorConta033() {
		return valorConta033;
	}
	public void setValorConta033(double valorConta033) {
		this.valorConta033 = valorConta033;
	}
	public double getValorConta034() {
		return valorConta034;
	}
	public void setValorConta034(double valorConta034) {
		this.valorConta034 = valorConta034;
	}
	public double getValorConta035() {
		return valorConta035;
	}
	public void setValorConta035(double valorConta035) {
		this.valorConta035 = valorConta035;
	}
	public double getValorConta036() {
		return valorConta036;
	}
	public void setValorConta036(double valorConta036) {
		this.valorConta036 = valorConta036;
	}
	public double getValorConta037() {
		return valorConta037;
	}
	public void setValorConta037(double valorConta037) {
		this.valorConta037 = valorConta037;
	}
	public double getValorConta038() {
		return valorConta038;
	}
	public void setValorConta038(double valorConta038) {
		this.valorConta038 = valorConta038;
	}
	public double getValorConta11() {
		return valorConta11;
	}
	public void setValorConta11(double valorConta11) {
		this.valorConta11 = valorConta11;
	}
	public double getValorConta12() {
		return valorConta12;
	}
	public void setValorConta12(double valorConta12) {
		this.valorConta12 = valorConta12;
	}
	public double getValorConta13() {
		return valorConta13;
	}
	public void setValorConta13(double valorConta13) {
		this.valorConta13 = valorConta13;
	}
	public double getValorConta14() {
		return valorConta14;
	}
	public void setValorConta14(double valorConta14) {
		this.valorConta14 = valorConta14;
	}
	public double getValorConta15() {
		return valorConta15;
	}
	public void setValorConta15(double valorConta15) {
		this.valorConta15 = valorConta15;
	}
	public double getValorConta21() {
		return valorConta21;
	}
	public void setValorConta21(double valorConta21) {
		this.valorConta21 = valorConta21;
	}
	public double getValorConta22() {
		return valorConta22;
	}
	public void setValorConta22(double valorConta22) {
		this.valorConta22 = valorConta22;
	}
	public double getValorConta23() {
		return valorConta23;
	}
	public void setValorConta23(double valorConta23) {
		this.valorConta23 = valorConta23;
	}
	public double getValorConta24() {
		return valorConta24;
	}
	public void setValorConta24(double valorConta24) {
		this.valorConta24 = valorConta24;
	}
	public double getValorConta25() {
		return valorConta25;
	}
	public void setValorConta25(double valorConta25) {
		this.valorConta25 = valorConta25;
	}
	public double getValorConta26() {
		return valorConta26;
	}
	public void setValorConta26(double valorConta26) {
		this.valorConta26 = valorConta26;
	}
	public double getValorConta27() {
		return valorConta27;
	}
	public void setValorConta27(double valorConta27) {
		this.valorConta27 = valorConta27;
	}
	public double getValorConta31() {
		return valorConta31;
	}
	public void setValorConta31(double valorConta31) {
		this.valorConta31 = valorConta31;
	}
	public double getValorConta32() {
		return valorConta32;
	}
	public void setValorConta32(double valorConta32) {
		this.valorConta32 = valorConta32;
	}
	public double getValorConta33() {
		return valorConta33;
	}
	public void setValorConta33(double valorConta33) {
		this.valorConta33 = valorConta33;
	}
	public double getValorConta34() {
		return valorConta34;
	}
	public void setValorConta34(double valorConta34) {
		this.valorConta34 = valorConta34;
	}
	public double getValorConta35() {
		return valorConta35;
	}
	public void setValorConta35(double valorConta35) {
		this.valorConta35 = valorConta35;
	}
	public double getValorConta36() {
		return valorConta36;
	}
	public void setValorConta36(double valorConta36) {
		this.valorConta36 = valorConta36;
	}
	public double getValorConta37() {
		return valorConta37;
	}
	public void setValorConta37(double valorConta37) {
		this.valorConta37 = valorConta37;
	}
	public double getValorConta38() {
		return valorConta38;
	}
	public void setValorConta38(double valorConta38) {
		this.valorConta38 = valorConta38;
	}
	public double getValorConta39() {
		return valorConta39;
	}
	public void setValorConta39(double valorConta39) {
		this.valorConta39 = valorConta39;
	}
	public double getValorConta310() {
		return valorConta310;
	}
	public void setValorConta310(double valorConta310) {
		this.valorConta310 = valorConta310;
	}
	public double getValorConta311() {
		return valorConta311;
	}
	public void setValorConta311(double valorConta311) {
		this.valorConta311 = valorConta311;
	}
	public double getValorConta312() {
		return valorConta312;
	}
	public void setValorConta312(double valorConta312) {
		this.valorConta312 = valorConta312;
	}
	public double getValorConta313() {
		return valorConta313;
	}
	public void setValorConta313(double valorConta313) {
		this.valorConta313 = valorConta313;
	}
	public double getValorConta314() {
		return valorConta314;
	}
	public void setValorConta314(double valorConta314) {
		this.valorConta314 = valorConta314;
	}
	public double getValorConta315() {
		return valorConta315;
	}
	public void setValorConta315(double valorConta315) {
		this.valorConta315 = valorConta315;
	}
	public double getValorConta316() {
		return valorConta316;
	}
	public void setValorConta316(double valorConta316) {
		this.valorConta316 = valorConta316;
	}
	public double getValorConta317() {
		return valorConta317;
	}
	public void setValorConta317(double valorConta317) {
		this.valorConta317 = valorConta317;
	}
	public double getValorConta318() {
		return valorConta318;
	}
	public void setValorConta318(double valorConta318) {
		this.valorConta318 = valorConta318;
	}
	public double getValorConta319() {
		return valorConta319;
	}
	public void setValorConta319(double valorConta319) {
		this.valorConta319 = valorConta319;
	}
	public double getValorConta320() {
		return valorConta320;
	}
	public void setValorConta320(double valorConta320) {
		this.valorConta320 = valorConta320;
	}
	public double getValorConta321() {
		return valorConta321;
	}
	public void setValorConta321(double valorConta321) {
		this.valorConta321 = valorConta321;
	}
	public double getValorConta322() {
		return valorConta322;
	}
	public void setValorConta322(double valorConta322) {
		this.valorConta322 = valorConta322;
	}
	public double getValorConta323() {
		return valorConta323;
	}
	public void setValorConta323(double valorConta323) {
		this.valorConta323 = valorConta323;
	}
	public double getValorConta324() {
		return valorConta324;
	}
	public void setValorConta324(double valorConta324) {
		this.valorConta324 = valorConta324;
	}
	public double getValorConta325() {
		return valorConta325;
	}
	public void setValorConta325(double valorConta325) {
		this.valorConta325 = valorConta325;
	}
	public double getValorConta326() {
		return valorConta326;
	}
	public void setValorConta326(double valorConta326) {
		this.valorConta326 = valorConta326;
	}
	public double getValorConta327() {
		return valorConta327;
	}
	public void setValorConta327(double valorConta327) {
		this.valorConta327 = valorConta327;
	}
	public double getValorConta328() {
		return valorConta328;
	}
	public void setValorConta328(double valorConta328) {
		this.valorConta328 = valorConta328;
	}
	public double getValorConta329() {
		return valorConta329;
	}
	public void setValorConta329(double valorConta329) {
		this.valorConta329 = valorConta329;
	}
	public double getValorConta330() {
		return valorConta330;
	}
	public void setValorConta330(double valorConta330) {
		this.valorConta330 = valorConta330;
	}
	public double getValorConta331() {
		return valorConta331;
	}
	public void setValorConta331(double valorConta331) {
		this.valorConta331 = valorConta331;
	}
	public double getValorConta41() {
		return valorConta41;
	}
	public void setValorConta41(double valorConta41) {
		this.valorConta41 = valorConta41;
	}
	public double getValorConta42() {
		return valorConta42;
	}
	public void setValorConta42(double valorConta42) {
		this.valorConta42 = valorConta42;
	}
	public double getValorConta51() {
		return valorConta51;
	}
	public void setValorConta51(double valorConta51) {
		this.valorConta51 = valorConta51;
	}
	public double getValorConta52() {
		return valorConta52;
	}
	public void setValorConta52(double valorConta52) {
		this.valorConta52 = valorConta52;
	}
	public double getValorConta53() {
		return valorConta53;
	}
	public void setValorConta53(double valorConta53) {
		this.valorConta53 = valorConta53;
	}
	public double getValorConta54() {
		return valorConta54;
	}
	public void setValorConta54(double valorConta54) {
		this.valorConta54 = valorConta54;
	}
	public double getValorConta55() {
		return valorConta55;
	}
	public void setValorConta55(double valorConta55) {
		this.valorConta55 = valorConta55;
	}
	public double getValorConta56() {
		return valorConta56;
	}
	public void setValorConta56(double valorConta56) {
		this.valorConta56 = valorConta56;
	}
	public double getValorConta61() {
		return valorConta61;
	}
	public void setValorConta61(double valorConta61) {
		this.valorConta61 = valorConta61;
	}
	public double getValorConta62() {
		return valorConta62;
	}
	public void setValorConta62(double valorConta62) {
		this.valorConta62 = valorConta62;
	}
	public double getValorConta63() {
		return valorConta63;
	}
	public void setValorConta63(double valorConta63) {
		this.valorConta63 = valorConta63;
	}
	public double getValorConta64() {
		return valorConta64;
	}
	public void setValorConta64(double valorConta64) {
		this.valorConta64 = valorConta64;
	}
	public double getValorConta65() {
		return valorConta65;
	}
	public void setValorConta65(double valorConta65) {
		this.valorConta65 = valorConta65;
	}
	public double getValorConta66() {
		return valorConta66;
	}
	public void setValorConta66(double valorConta66) {
		this.valorConta66 = valorConta66;
	}
	public double getValorConta67() {
		return valorConta67;
	}
	public void setValorConta67(double valorConta67) {
		this.valorConta67 = valorConta67;
	}
	public double getValorConta68() {
		return valorConta68;
	}
	public void setValorConta68(double valorConta68) {
		this.valorConta68 = valorConta68;
	}
	public double getValorConta69() {
		return valorConta69;
	}
	public void setValorConta69(double valorConta69) {
		this.valorConta69 = valorConta69;
	}
	public double getValorConta71() {
		return valorConta71;
	}
	public void setValorConta71(double valorConta71) {
		this.valorConta71 = valorConta71;
	}
	public double getValorConta72() {
		return valorConta72;
	}
	public void setValorConta72(double valorConta72) {
		this.valorConta72 = valorConta72;
	}
	public double getValorConta73() {
		return valorConta73;
	}
	public void setValorConta73(double valorConta73) {
		this.valorConta73 = valorConta73;
	}
	public double getValorConta74() {
		return valorConta74;
	}
	public void setValorConta74(double valorConta74) {
		this.valorConta74 = valorConta74;
	}
	public double getValorConta75() {
		return valorConta75;
	}
	public void setValorConta75(double valorConta75) {
		this.valorConta75 = valorConta75;
	}
	public double getValorConta76() {
		return valorConta76;
	}
	public void setValorConta76(double valorConta76) {
		this.valorConta76 = valorConta76;
	}
	public double getValorConta81() {
		return valorConta81;
	}
	public void setValorConta81(double valorConta81) {
		this.valorConta81 = valorConta81;
	}
	public double getValorConta82() {
		return valorConta82;
	}
	public void setValorConta82(double valorConta82) {
		this.valorConta82 = valorConta82;
	}
	public double getValorConta83() {
		return valorConta83;
	}
	public void setValorConta83(double valorConta83) {
		this.valorConta83 = valorConta83;
	}
	public double getValorConta84() {
		return valorConta84;
	}
	public void setValorConta84(double valorConta84) {
		this.valorConta84 = valorConta84;
	}
	public double getValorConta85() {
		return valorConta85;
	}
	public void setValorConta85(double valorConta85) {
		this.valorConta85 = valorConta85;
	}
	public double getValorConta86() {
		return valorConta86;
	}
	public void setValorConta86(double valorConta86) {
		this.valorConta86 = valorConta86;
	}
	public double getValorConta87() {
		return valorConta87;
	}
	public void setValorConta87(double valorConta87) {
		this.valorConta87 = valorConta87;
	}
	public double getValorConta88() {
		return valorConta88;
	}
	public void setValorConta88(double valorConta88) {
		this.valorConta88 = valorConta88;
	}
	public double getValorConta89() {
		return valorConta89;
	}
	public void setValorConta89(double valorConta89) {
		this.valorConta89 = valorConta89;
	}
	public double getValorConta810() {
		return valorConta810;
	}
	public void setValorConta810(double valorConta810) {
		this.valorConta810 = valorConta810;
	}
	public double getValorConta811() {
		return valorConta811;
	}
	public void setValorConta811(double valorConta811) {
		this.valorConta811 = valorConta811;
	}
	public double getValorConta812() {
		return valorConta812;
	}
	public void setValorConta812(double valorConta812) {
		this.valorConta812 = valorConta812;
	}
	public double getValorConta813() {
		return valorConta813;
	}
	public void setValorConta813(double valorConta813) {
		this.valorConta813 = valorConta813;
	}
	public double getValorConta814() {
		return valorConta814;
	}
	public void setValorConta814(double valorConta814) {
		this.valorConta814 = valorConta814;
	}
	public double getValorConta815() {
		return valorConta815;
	}
	public void setValorConta815(double valorConta815) {
		this.valorConta815 = valorConta815;
	}
	public double getValorConta816() {
		return valorConta816;
	}
	public void setValorConta816(double valorConta816) {
		this.valorConta816 = valorConta816;
	}
	public double getValorConta817() {
		return valorConta817;
	}
	public void setValorConta817(double valorConta817) {
		this.valorConta817 = valorConta817;
	}
	public double getValorConta818() {
		return valorConta818;
	}
	public void setValorConta818(double valorConta818) {
		this.valorConta818 = valorConta818;
	}
	public double getValorConta819() {
		return valorConta819;
	}
	public void setValorConta819(double valorConta819) {
		this.valorConta819 = valorConta819;
	}
	public double getValorConta820() {
		return valorConta820;
	}
	public void setValorConta820(double valorConta820) {
		this.valorConta820 = valorConta820;
	}
	public double getValorConta821() {
		return valorConta821;
	}
	public void setValorConta821(double valorConta821) {
		this.valorConta821 = valorConta821;
	}
	public double getValorConta822() {
		return valorConta822;
	}
	public void setValorConta822(double valorConta822) {
		this.valorConta822 = valorConta822;
	}
	public double getValorConta823() {
		return valorConta823;
	}
	public void setValorConta823(double valorConta823) {
		this.valorConta823 = valorConta823;
	}
	public double getValorConta824() {
		return valorConta824;
	}
	public void setValorConta824(double valorConta824) {
		this.valorConta824 = valorConta824;
	}
	public double getValorConta825() {
		return valorConta825;
	}
	public void setValorConta825(double valorConta825) {
		this.valorConta825 = valorConta825;
	}
	public double getValorConta826() {
		return valorConta826;
	}
	public void setValorConta826(double valorConta826) {
		this.valorConta826 = valorConta826;
	}
	public double getValorConta827() {
		return valorConta827;
	}
	public void setValorConta827(double valorConta827) {
		this.valorConta827 = valorConta827;
	}
	public double getValorConta828() {
		return valorConta828;
	}
	public void setValorConta828(double valorConta828) {
		this.valorConta828 = valorConta828;
	}
	public double getValorConta829() {
		return valorConta829;
	}
	public void setValorConta829(double valorConta829) {
		this.valorConta829 = valorConta829;
	}
	public double getValorConta830() {
		return valorConta830;
	}
	public void setValorConta830(double valorConta830) {
		this.valorConta830 = valorConta830;
	}
	public double getValorConta831() {
		return valorConta831;
	}
	public void setValorConta831(double valorConta831) {
		this.valorConta831 = valorConta831;
	}
	public double getValorConta832() {
		return valorConta832;
	}
	public void setValorConta832(double valorConta832) {
		this.valorConta832 = valorConta832;
	}
	public double getValorConta833() {
		return valorConta833;
	}
	public void setValorConta833(double valorConta833) {
		this.valorConta833 = valorConta833;
	}
	public double getValorConta834() {
		return valorConta834;
	}
	public void setValorConta834(double valorConta834) {
		this.valorConta834 = valorConta834;
	}
	public double getValorConta835() {
		return valorConta835;
	}
	public void setValorConta835(double valorConta835) {
		this.valorConta835 = valorConta835;
	}
	public double getValorConta836() {
		return valorConta836;
	}
	public void setValorConta836(double valorConta836) {
		this.valorConta836 = valorConta836;
	}
	public double getValorConta837() {
		return valorConta837;
	}
	public void setValorConta837(double valorConta837) {
		this.valorConta837 = valorConta837;
	}
	public double getFeriasJan() {
		return feriasJan;
	}
	public void setFeriasJan(double feriasJan) {
		this.feriasJan = feriasJan;
	}
	public double getFeriasFev() {
		return feriasFev;
	}
	public void setFeriasFev(double feriasFev) {
		this.feriasFev = feriasFev;
	}
	public double getFeriasMar() {
		return feriasMar;
	}
	public void setFeriasMar(double feriasMar) {
		this.feriasMar = feriasMar;
	}
	public double getFeriasAbr() {
		return feriasAbr;
	}
	public void setFeriasAbr(double feriasAbr) {
		this.feriasAbr = feriasAbr;
	}
	public double getFeriasMai() {
		return feriasMai;
	}
	public void setFeriasMai(double feriasMai) {
		this.feriasMai = feriasMai;
	}
	public double getFeriasJun() {
		return feriasJun;
	}
	public void setFeriasJun(double feriasJun) {
		this.feriasJun = feriasJun;
	}
	public double getFeriasJul() {
		return feriasJul;
	}
	public void setFeriasJul(double feriasJul) {
		this.feriasJul = feriasJul;
	}
	public double getFeriasAgo() {
		return feriasAgo;
	}
	public void setFeriasAgo(double feriasAgo) {
		this.feriasAgo = feriasAgo;
	}
	public double getFeriasSet() {
		return feriasSet;
	}
	public void setFeriasSet(double feriasSet) {
		this.feriasSet = feriasSet;
	}
	public double getFeriasOut() {
		return feriasOut;
	}
	public void setFeriasOut(double feriasOut) {
		this.feriasOut = feriasOut;
	}
	public double getFeriasNov() {
		return feriasNov;
	}
	public void setFeriasNov(double feriasNov) {
		this.feriasNov = feriasNov;
	}
	public double getFeriasDez() {
		return feriasDez;
	}
	public void setFeriasDez(double feriasDez) {
		this.feriasDez = feriasDez;
	}
	public double getDecimo13Nov() {
		return decimo13Nov;
	}
	public void setDecimo13Nov(double decimo13Nov) {
		this.decimo13Nov = decimo13Nov;
	}
	public double getDecimo13Dez() {
		return decimo13Dez;
	}
	public void setDecimo13Dez(double decimo13Dez) {
		this.decimo13Dez = decimo13Dez;
	}
	public boolean isConta01() {
		return conta01;
	}
	public void setConta01(boolean conta01) {
		this.conta01 = conta01;
	}
	public boolean isConta02() {
		return conta02;
	}
	public void setConta02(boolean conta02) {
		this.conta02 = conta02;
	}
	public boolean isConta03() {
		return conta03;
	}
	public void setConta03(boolean conta03) {
		this.conta03 = conta03;
	}
	public boolean isConta04() {
		return conta04;
	}
	public void setConta04(boolean conta04) {
		this.conta04 = conta04;
	}
	public boolean isConta05() {
		return conta05;
	}
	public void setConta05(boolean conta05) {
		this.conta05 = conta05;
	}
	public boolean isConta06() {
		return conta06;
	}
	public void setConta06(boolean conta06) {
		this.conta06 = conta06;
	}
	public boolean isConta07() {
		return conta07;
	}
	public void setConta07(boolean conta07) {
		this.conta07 = conta07;
	}
	public boolean isConta08() {
		return conta08;
	}
	public void setConta08(boolean conta08) {
		this.conta08 = conta08;
	}
	public boolean isConta09() {
		return conta09;
	}
	public void setConta09(boolean conta09) {
		this.conta09 = conta09;
	}
	public boolean isConta010() {
		return conta010;
	}
	public void setConta010(boolean conta010) {
		this.conta010 = conta010;
	}
	public boolean isConta011() {
		return conta011;
	}
	public void setConta011(boolean conta011) {
		this.conta011 = conta011;
	}
	public boolean isConta012() {
		return conta012;
	}
	public void setConta012(boolean conta012) {
		this.conta012 = conta012;
	}
	public boolean isConta013() {
		return conta013;
	}
	public void setConta013(boolean conta013) {
		this.conta013 = conta013;
	}
	public boolean isConta014() {
		return conta014;
	}
	public void setConta014(boolean conta014) {
		this.conta014 = conta014;
	}
	public boolean isConta015() {
		return conta015;
	}
	public void setConta015(boolean conta015) {
		this.conta015 = conta015;
	}
	public boolean isConta016() {
		return conta016;
	}
	public void setConta016(boolean conta016) {
		this.conta016 = conta016;
	}
	public boolean isConta017() {
		return conta017;
	}
	public void setConta017(boolean conta017) {
		this.conta017 = conta017;
	}
	public boolean isConta018() {
		return conta018;
	}
	public void setConta018(boolean conta018) {
		this.conta018 = conta018;
	}
	public boolean isConta019() {
		return conta019;
	}
	public void setConta019(boolean conta019) {
		this.conta019 = conta019;
	}
	public boolean isConta020() {
		return conta020;
	}
	public void setConta020(boolean conta020) {
		this.conta020 = conta020;
	}
	public boolean isConta021() {
		return conta021;
	}
	public void setConta021(boolean conta021) {
		this.conta021 = conta021;
	}
	public boolean isConta022() {
		return conta022;
	}
	public void setConta022(boolean conta022) {
		this.conta022 = conta022;
	}
	public boolean isConta023() {
		return conta023;
	}
	public void setConta023(boolean conta023) {
		this.conta023 = conta023;
	}
	public boolean isConta024() {
		return conta024;
	}
	public void setConta024(boolean conta024) {
		this.conta024 = conta024;
	}
	public boolean isConta025() {
		return conta025;
	}
	public void setConta025(boolean conta025) {
		this.conta025 = conta025;
	}
	public boolean isConta026() {
		return conta026;
	}
	public void setConta026(boolean conta026) {
		this.conta026 = conta026;
	}
	public boolean isConta027() {
		return conta027;
	}
	public void setConta027(boolean conta027) {
		this.conta027 = conta027;
	}
	public boolean isConta028() {
		return conta028;
	}
	public void setConta028(boolean conta028) {
		this.conta028 = conta028;
	}
	public boolean isConta029() {
		return conta029;
	}
	public void setConta029(boolean conta029) {
		this.conta029 = conta029;
	}
	public boolean isConta030() {
		return conta030;
	}
	public void setConta030(boolean conta030) {
		this.conta030 = conta030;
	}
	public boolean isConta031() {
		return conta031;
	}
	public void setConta031(boolean conta031) {
		this.conta031 = conta031;
	}
	public boolean isConta032() {
		return conta032;
	}
	public void setConta032(boolean conta032) {
		this.conta032 = conta032;
	}
	public boolean isConta033() {
		return conta033;
	}
	public void setConta033(boolean conta033) {
		this.conta033 = conta033;
	}
	public boolean isConta034() {
		return conta034;
	}
	public void setConta034(boolean conta034) {
		this.conta034 = conta034;
	}
	public boolean isConta035() {
		return conta035;
	}
	public void setConta035(boolean conta035) {
		this.conta035 = conta035;
	}
	public boolean isConta036() {
		return conta036;
	}
	public void setConta036(boolean conta036) {
		this.conta036 = conta036;
	}
	public boolean isConta037() {
		return conta037;
	}
	public void setConta037(boolean conta037) {
		this.conta037 = conta037;
	}
	public boolean isConta038() {
		return conta038;
	}
	public void setConta038(boolean conta038) {
		this.conta038 = conta038;
	}
	public boolean isConta11() {
		return conta11;
	}
	public void setConta11(boolean conta11) {
		this.conta11 = conta11;
	}
	public boolean isConta12() {
		return conta12;
	}
	public void setConta12(boolean conta12) {
		this.conta12 = conta12;
	}
	public boolean isConta13() {
		return conta13;
	}
	public void setConta13(boolean conta13) {
		this.conta13 = conta13;
	}
	public boolean isConta14() {
		return conta14;
	}
	public void setConta14(boolean conta14) {
		this.conta14 = conta14;
	}
	public boolean isConta15() {
		return conta15;
	}
	public void setConta15(boolean conta15) {
		this.conta15 = conta15;
	}
	public boolean isConta21() {
		return conta21;
	}
	public void setConta21(boolean conta21) {
		this.conta21 = conta21;
	}
	public boolean isConta22() {
		return conta22;
	}
	public void setConta22(boolean conta22) {
		this.conta22 = conta22;
	}
	public boolean isConta23() {
		return conta23;
	}
	public void setConta23(boolean conta23) {
		this.conta23 = conta23;
	}
	public boolean isConta24() {
		return conta24;
	}
	public void setConta24(boolean conta24) {
		this.conta24 = conta24;
	}
	public boolean isConta25() {
		return conta25;
	}
	public void setConta25(boolean conta25) {
		this.conta25 = conta25;
	}
	public boolean isConta26() {
		return conta26;
	}
	public void setConta26(boolean conta26) {
		this.conta26 = conta26;
	}
	public boolean isConta27() {
		return conta27;
	}
	public void setConta27(boolean conta27) {
		this.conta27 = conta27;
	}
	public boolean isConta31() {
		return conta31;
	}
	public void setConta31(boolean conta31) {
		this.conta31 = conta31;
	}
	public boolean isConta32() {
		return conta32;
	}
	public void setConta32(boolean conta32) {
		this.conta32 = conta32;
	}
	public boolean isConta33() {
		return conta33;
	}
	public void setConta33(boolean conta33) {
		this.conta33 = conta33;
	}
	public boolean isConta34() {
		return conta34;
	}
	public void setConta34(boolean conta34) {
		this.conta34 = conta34;
	}
	public boolean isConta35() {
		return conta35;
	}
	public void setConta35(boolean conta35) {
		this.conta35 = conta35;
	}
	public boolean isConta36() {
		return conta36;
	}
	public void setConta36(boolean conta36) {
		this.conta36 = conta36;
	}
	public boolean isConta37() {
		return conta37;
	}
	public void setConta37(boolean conta37) {
		this.conta37 = conta37;
	}
	public boolean isConta38() {
		return conta38;
	}
	public void setConta38(boolean conta38) {
		this.conta38 = conta38;
	}
	public boolean isConta39() {
		return conta39;
	}
	public void setConta39(boolean conta39) {
		this.conta39 = conta39;
	}
	public boolean isConta310() {
		return conta310;
	}
	public void setConta310(boolean conta310) {
		this.conta310 = conta310;
	}
	public boolean isConta311() {
		return conta311;
	}
	public void setConta311(boolean conta311) {
		this.conta311 = conta311;
	}
	public boolean isConta312() {
		return conta312;
	}
	public void setConta312(boolean conta312) {
		this.conta312 = conta312;
	}
	public boolean isConta313() {
		return conta313;
	}
	public void setConta313(boolean conta313) {
		this.conta313 = conta313;
	}
	public boolean isConta314() {
		return conta314;
	}
	public void setConta314(boolean conta314) {
		this.conta314 = conta314;
	}
	public boolean isConta315() {
		return conta315;
	}
	public void setConta315(boolean conta315) {
		this.conta315 = conta315;
	}
	public boolean isConta316() {
		return conta316;
	}
	public void setConta316(boolean conta316) {
		this.conta316 = conta316;
	}
	public boolean isConta317() {
		return conta317;
	}
	public void setConta317(boolean conta317) {
		this.conta317 = conta317;
	}
	public boolean isConta318() {
		return conta318;
	}
	public void setConta318(boolean conta318) {
		this.conta318 = conta318;
	}
	public boolean isConta319() {
		return conta319;
	}
	public void setConta319(boolean conta319) {
		this.conta319 = conta319;
	}
	public boolean isConta320() {
		return conta320;
	}
	public void setConta320(boolean conta320) {
		this.conta320 = conta320;
	}
	public boolean isConta321() {
		return conta321;
	}
	public void setConta321(boolean conta321) {
		this.conta321 = conta321;
	}
	public boolean isConta322() {
		return conta322;
	}
	public void setConta322(boolean conta322) {
		this.conta322 = conta322;
	}
	public boolean isConta323() {
		return conta323;
	}
	public void setConta323(boolean conta323) {
		this.conta323 = conta323;
	}
	public boolean isConta324() {
		return conta324;
	}
	public void setConta324(boolean conta324) {
		this.conta324 = conta324;
	}
	public boolean isConta325() {
		return conta325;
	}
	public void setConta325(boolean conta325) {
		this.conta325 = conta325;
	}
	public boolean isConta326() {
		return conta326;
	}
	public void setConta326(boolean conta326) {
		this.conta326 = conta326;
	}
	public boolean isConta327() {
		return conta327;
	}
	public void setConta327(boolean conta327) {
		this.conta327 = conta327;
	}
	public boolean isConta328() {
		return conta328;
	}
	public void setConta328(boolean conta328) {
		this.conta328 = conta328;
	}
	public boolean isConta329() {
		return conta329;
	}
	public void setConta329(boolean conta329) {
		this.conta329 = conta329;
	}
	public boolean isConta330() {
		return conta330;
	}
	public void setConta330(boolean conta330) {
		this.conta330 = conta330;
	}
	public boolean isConta331() {
		return conta331;
	}
	public void setConta331(boolean conta331) {
		this.conta331 = conta331;
	}
	public boolean isConta41() {
		return conta41;
	}
	public void setConta41(boolean conta41) {
		this.conta41 = conta41;
	}
	public boolean isConta42() {
		return conta42;
	}
	public void setConta42(boolean conta42) {
		this.conta42 = conta42;
	}
	public boolean isConta51() {
		return conta51;
	}
	public void setConta51(boolean conta51) {
		this.conta51 = conta51;
	}
	public boolean isConta52() {
		return conta52;
	}
	public void setConta52(boolean conta52) {
		this.conta52 = conta52;
	}
	public boolean isConta53() {
		return conta53;
	}
	public void setConta53(boolean conta53) {
		this.conta53 = conta53;
	}
	public boolean isConta54() {
		return conta54;
	}
	public void setConta54(boolean conta54) {
		this.conta54 = conta54;
	}
	public boolean isConta55() {
		return conta55;
	}
	public void setConta55(boolean conta55) {
		this.conta55 = conta55;
	}
	public boolean isConta56() {
		return conta56;
	}
	public void setConta56(boolean conta56) {
		this.conta56 = conta56;
	}
	public boolean isConta61() {
		return conta61;
	}
	public void setConta61(boolean conta61) {
		this.conta61 = conta61;
	}
	public boolean isConta62() {
		return conta62;
	}
	public void setConta62(boolean conta62) {
		this.conta62 = conta62;
	}
	public boolean isConta63() {
		return conta63;
	}
	public void setConta63(boolean conta63) {
		this.conta63 = conta63;
	}
	public boolean isConta64() {
		return conta64;
	}
	public void setConta64(boolean conta64) {
		this.conta64 = conta64;
	}
	public boolean isConta65() {
		return conta65;
	}
	public void setConta65(boolean conta65) {
		this.conta65 = conta65;
	}
	public boolean isConta66() {
		return conta66;
	}
	public void setConta66(boolean conta66) {
		this.conta66 = conta66;
	}
	public boolean isConta67() {
		return conta67;
	}
	public void setConta67(boolean conta67) {
		this.conta67 = conta67;
	}
	public boolean isConta68() {
		return conta68;
	}
	public void setConta68(boolean conta68) {
		this.conta68 = conta68;
	}
	public boolean isConta69() {
		return conta69;
	}
	public void setConta69(boolean conta69) {
		this.conta69 = conta69;
	}
	public boolean isConta71() {
		return conta71;
	}
	public void setConta71(boolean conta71) {
		this.conta71 = conta71;
	}
	public boolean isConta72() {
		return conta72;
	}
	public void setConta72(boolean conta72) {
		this.conta72 = conta72;
	}
	public boolean isConta73() {
		return conta73;
	}
	public void setConta73(boolean conta73) {
		this.conta73 = conta73;
	}
	public boolean isConta74() {
		return conta74;
	}
	public void setConta74(boolean conta74) {
		this.conta74 = conta74;
	}
	public boolean isConta75() {
		return conta75;
	}
	public void setConta75(boolean conta75) {
		this.conta75 = conta75;
	}
	public boolean isConta76() {
		return conta76;
	}
	public void setConta76(boolean conta76) {
		this.conta76 = conta76;
	}
	public boolean isConta81() {
		return conta81;
	}
	public void setConta81(boolean conta81) {
		this.conta81 = conta81;
	}
	public boolean isConta82() {
		return conta82;
	}
	public void setConta82(boolean conta82) {
		this.conta82 = conta82;
	}
	public boolean isConta83() {
		return conta83;
	}
	public void setConta83(boolean conta83) {
		this.conta83 = conta83;
	}
	public boolean isConta84() {
		return conta84;
	}
	public void setConta84(boolean conta84) {
		this.conta84 = conta84;
	}
	public boolean isConta85() {
		return conta85;
	}
	public void setConta85(boolean conta85) {
		this.conta85 = conta85;
	}
	public boolean isConta86() {
		return conta86;
	}
	public void setConta86(boolean conta86) {
		this.conta86 = conta86;
	}
	public boolean isConta87() {
		return conta87;
	}
	public void setConta87(boolean conta87) {
		this.conta87 = conta87;
	}
	public boolean isConta88() {
		return conta88;
	}
	public void setConta88(boolean conta88) {
		this.conta88 = conta88;
	}
	public boolean isConta89() {
		return conta89;
	}
	public void setConta89(boolean conta89) {
		this.conta89 = conta89;
	}
	public boolean isConta810() {
		return conta810;
	}
	public void setConta810(boolean conta810) {
		this.conta810 = conta810;
	}
	public boolean isConta811() {
		return conta811;
	}
	public void setConta811(boolean conta811) {
		this.conta811 = conta811;
	}
	public boolean isConta812() {
		return conta812;
	}
	public void setConta812(boolean conta812) {
		this.conta812 = conta812;
	}
	public boolean isConta813() {
		return conta813;
	}
	public void setConta813(boolean conta813) {
		this.conta813 = conta813;
	}
	public boolean isConta814() {
		return conta814;
	}
	public void setConta814(boolean conta814) {
		this.conta814 = conta814;
	}
	public boolean isConta815() {
		return conta815;
	}
	public void setConta815(boolean conta815) {
		this.conta815 = conta815;
	}
	public boolean isConta816() {
		return conta816;
	}
	public void setConta816(boolean conta816) {
		this.conta816 = conta816;
	}
	public boolean isConta817() {
		return conta817;
	}
	public void setConta817(boolean conta817) {
		this.conta817 = conta817;
	}
	public boolean isConta818() {
		return conta818;
	}
	public void setConta818(boolean conta818) {
		this.conta818 = conta818;
	}
	public boolean isConta819() {
		return conta819;
	}
	public void setConta819(boolean conta819) {
		this.conta819 = conta819;
	}
	public boolean isConta820() {
		return conta820;
	}
	public void setConta820(boolean conta820) {
		this.conta820 = conta820;
	}
	public boolean isConta821() {
		return conta821;
	}
	public void setConta821(boolean conta821) {
		this.conta821 = conta821;
	}
	public boolean isConta822() {
		return conta822;
	}
	public void setConta822(boolean conta822) {
		this.conta822 = conta822;
	}
	public boolean isConta823() {
		return conta823;
	}
	public void setConta823(boolean conta823) {
		this.conta823 = conta823;
	}
	public boolean isConta824() {
		return conta824;
	}
	public void setConta824(boolean conta824) {
		this.conta824 = conta824;
	}
	public boolean isConta825() {
		return conta825;
	}
	public void setConta825(boolean conta825) {
		this.conta825 = conta825;
	}
	public boolean isConta826() {
		return conta826;
	}
	public void setConta826(boolean conta826) {
		this.conta826 = conta826;
	}
	public boolean isConta827() {
		return conta827;
	}
	public void setConta827(boolean conta827) {
		this.conta827 = conta827;
	}
	public boolean isConta828() {
		return conta828;
	}
	public void setConta828(boolean conta828) {
		this.conta828 = conta828;
	}
	public boolean isConta829() {
		return conta829;
	}
	public void setConta829(boolean conta829) {
		this.conta829 = conta829;
	}
	public boolean isConta830() {
		return conta830;
	}
	public void setConta830(boolean conta830) {
		this.conta830 = conta830;
	}
	public boolean isConta831() {
		return conta831;
	}
	public void setConta831(boolean conta831) {
		this.conta831 = conta831;
	}
	public boolean isConta832() {
		return conta832;
	}
	public void setConta832(boolean conta832) {
		this.conta832 = conta832;
	}
	public boolean isConta833() {
		return conta833;
	}
	public void setConta833(boolean conta833) {
		this.conta833 = conta833;
	}
	public boolean isConta834() {
		return conta834;
	}
	public void setConta834(boolean conta834) {
		this.conta834 = conta834;
	}
	public boolean isConta835() {
		return conta835;
	}
	public void setConta835(boolean conta835) {
		this.conta835 = conta835;
	}
	public boolean isConta836() {
		return conta836;
	}
	public void setConta836(boolean conta836) {
		this.conta836 = conta836;
	}
	public boolean isConta837() {
		return conta837;
	}
	public void setConta837(boolean conta837) {
		this.conta837 = conta837;
	}
	
	public int getMesesProjecao() {
		return mesesProjecao;
	}
	public void setMesesProjecao(int mesesProjecao) {
		this.mesesProjecao = mesesProjecao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((capa0 == null) ? 0 : capa0.hashCode());
		result = prime * result + ((capa1 == null) ? 0 : capa1.hashCode());
		result = prime * result + ((capa2 == null) ? 0 : capa2.hashCode());
		result = prime * result + ((capa3 == null) ? 0 : capa3.hashCode());
		result = prime * result + ((capa4 == null) ? 0 : capa4.hashCode());
		result = prime * result + ((capa5 == null) ? 0 : capa5.hashCode());
		result = prime * result + ((capa6 == null) ? 0 : capa6.hashCode());
		result = prime * result + ((capa7 == null) ? 0 : capa7.hashCode());
		result = prime * result + ((capa8 == null) ? 0 : capa8.hashCode());
		result = prime * result + codigoGerente;
		result = prime * result + condominio;
		result = prime * result + (conta01 ? 1231 : 1237);
		result = prime * result + (conta010 ? 1231 : 1237);
		result = prime * result + (conta011 ? 1231 : 1237);
		result = prime * result + (conta012 ? 1231 : 1237);
		result = prime * result + (conta013 ? 1231 : 1237);
		result = prime * result + (conta014 ? 1231 : 1237);
		result = prime * result + (conta015 ? 1231 : 1237);
		result = prime * result + (conta016 ? 1231 : 1237);
		result = prime * result + (conta017 ? 1231 : 1237);
		result = prime * result + (conta018 ? 1231 : 1237);
		result = prime * result + (conta019 ? 1231 : 1237);
		result = prime * result + (conta02 ? 1231 : 1237);
		result = prime * result + (conta020 ? 1231 : 1237);
		result = prime * result + (conta021 ? 1231 : 1237);
		result = prime * result + (conta022 ? 1231 : 1237);
		result = prime * result + (conta023 ? 1231 : 1237);
		result = prime * result + (conta024 ? 1231 : 1237);
		result = prime * result + (conta025 ? 1231 : 1237);
		result = prime * result + (conta026 ? 1231 : 1237);
		result = prime * result + (conta027 ? 1231 : 1237);
		result = prime * result + (conta028 ? 1231 : 1237);
		result = prime * result + (conta029 ? 1231 : 1237);
		result = prime * result + (conta03 ? 1231 : 1237);
		result = prime * result + (conta030 ? 1231 : 1237);
		result = prime * result + (conta031 ? 1231 : 1237);
		result = prime * result + (conta032 ? 1231 : 1237);
		result = prime * result + (conta033 ? 1231 : 1237);
		result = prime * result + (conta034 ? 1231 : 1237);
		result = prime * result + (conta035 ? 1231 : 1237);
		result = prime * result + (conta036 ? 1231 : 1237);
		result = prime * result + (conta037 ? 1231 : 1237);
		result = prime * result + (conta038 ? 1231 : 1237);
		result = prime * result + (conta04 ? 1231 : 1237);
		result = prime * result + (conta05 ? 1231 : 1237);
		result = prime * result + (conta06 ? 1231 : 1237);
		result = prime * result + (conta07 ? 1231 : 1237);
		result = prime * result + (conta08 ? 1231 : 1237);
		result = prime * result + (conta09 ? 1231 : 1237);
		result = prime * result + (conta11 ? 1231 : 1237);
		result = prime * result + (conta12 ? 1231 : 1237);
		result = prime * result + (conta13 ? 1231 : 1237);
		result = prime * result + (conta14 ? 1231 : 1237);
		result = prime * result + (conta15 ? 1231 : 1237);
		result = prime * result + (conta21 ? 1231 : 1237);
		result = prime * result + (conta22 ? 1231 : 1237);
		result = prime * result + (conta23 ? 1231 : 1237);
		result = prime * result + (conta24 ? 1231 : 1237);
		result = prime * result + (conta25 ? 1231 : 1237);
		result = prime * result + (conta26 ? 1231 : 1237);
		result = prime * result + (conta27 ? 1231 : 1237);
		result = prime * result + (conta31 ? 1231 : 1237);
		result = prime * result + (conta310 ? 1231 : 1237);
		result = prime * result + (conta311 ? 1231 : 1237);
		result = prime * result + (conta312 ? 1231 : 1237);
		result = prime * result + (conta313 ? 1231 : 1237);
		result = prime * result + (conta314 ? 1231 : 1237);
		result = prime * result + (conta315 ? 1231 : 1237);
		result = prime * result + (conta316 ? 1231 : 1237);
		result = prime * result + (conta317 ? 1231 : 1237);
		result = prime * result + (conta318 ? 1231 : 1237);
		result = prime * result + (conta319 ? 1231 : 1237);
		result = prime * result + (conta32 ? 1231 : 1237);
		result = prime * result + (conta320 ? 1231 : 1237);
		result = prime * result + (conta321 ? 1231 : 1237);
		result = prime * result + (conta322 ? 1231 : 1237);
		result = prime * result + (conta323 ? 1231 : 1237);
		result = prime * result + (conta324 ? 1231 : 1237);
		result = prime * result + (conta325 ? 1231 : 1237);
		result = prime * result + (conta326 ? 1231 : 1237);
		result = prime * result + (conta327 ? 1231 : 1237);
		result = prime * result + (conta328 ? 1231 : 1237);
		result = prime * result + (conta329 ? 1231 : 1237);
		result = prime * result + (conta33 ? 1231 : 1237);
		result = prime * result + (conta330 ? 1231 : 1237);
		result = prime * result + (conta331 ? 1231 : 1237);
		result = prime * result + (conta34 ? 1231 : 1237);
		result = prime * result + (conta35 ? 1231 : 1237);
		result = prime * result + (conta36 ? 1231 : 1237);
		result = prime * result + (conta37 ? 1231 : 1237);
		result = prime * result + (conta38 ? 1231 : 1237);
		result = prime * result + (conta39 ? 1231 : 1237);
		result = prime * result + (conta41 ? 1231 : 1237);
		result = prime * result + (conta42 ? 1231 : 1237);
		result = prime * result + (conta51 ? 1231 : 1237);
		result = prime * result + (conta52 ? 1231 : 1237);
		result = prime * result + (conta53 ? 1231 : 1237);
		result = prime * result + (conta54 ? 1231 : 1237);
		result = prime * result + (conta55 ? 1231 : 1237);
		result = prime * result + (conta56 ? 1231 : 1237);
		result = prime * result + (conta61 ? 1231 : 1237);
		result = prime * result + (conta62 ? 1231 : 1237);
		result = prime * result + (conta63 ? 1231 : 1237);
		result = prime * result + (conta64 ? 1231 : 1237);
		result = prime * result + (conta65 ? 1231 : 1237);
		result = prime * result + (conta66 ? 1231 : 1237);
		result = prime * result + (conta67 ? 1231 : 1237);
		result = prime * result + (conta68 ? 1231 : 1237);
		result = prime * result + (conta69 ? 1231 : 1237);
		result = prime * result + (conta71 ? 1231 : 1237);
		result = prime * result + (conta72 ? 1231 : 1237);
		result = prime * result + (conta73 ? 1231 : 1237);
		result = prime * result + (conta74 ? 1231 : 1237);
		result = prime * result + (conta75 ? 1231 : 1237);
		result = prime * result + (conta76 ? 1231 : 1237);
		result = prime * result + (conta81 ? 1231 : 1237);
		result = prime * result + (conta810 ? 1231 : 1237);
		result = prime * result + (conta811 ? 1231 : 1237);
		result = prime * result + (conta812 ? 1231 : 1237);
		result = prime * result + (conta813 ? 1231 : 1237);
		result = prime * result + (conta814 ? 1231 : 1237);
		result = prime * result + (conta815 ? 1231 : 1237);
		result = prime * result + (conta816 ? 1231 : 1237);
		result = prime * result + (conta817 ? 1231 : 1237);
		result = prime * result + (conta818 ? 1231 : 1237);
		result = prime * result + (conta819 ? 1231 : 1237);
		result = prime * result + (conta82 ? 1231 : 1237);
		result = prime * result + (conta820 ? 1231 : 1237);
		result = prime * result + (conta821 ? 1231 : 1237);
		result = prime * result + (conta822 ? 1231 : 1237);
		result = prime * result + (conta823 ? 1231 : 1237);
		result = prime * result + (conta824 ? 1231 : 1237);
		result = prime * result + (conta825 ? 1231 : 1237);
		result = prime * result + (conta826 ? 1231 : 1237);
		result = prime * result + (conta827 ? 1231 : 1237);
		result = prime * result + (conta828 ? 1231 : 1237);
		result = prime * result + (conta829 ? 1231 : 1237);
		result = prime * result + (conta83 ? 1231 : 1237);
		result = prime * result + (conta830 ? 1231 : 1237);
		result = prime * result + (conta831 ? 1231 : 1237);
		result = prime * result + (conta832 ? 1231 : 1237);
		result = prime * result + (conta833 ? 1231 : 1237);
		result = prime * result + (conta834 ? 1231 : 1237);
		result = prime * result + (conta835 ? 1231 : 1237);
		result = prime * result + (conta836 ? 1231 : 1237);
		result = prime * result + (conta837 ? 1231 : 1237);
		result = prime * result + (conta84 ? 1231 : 1237);
		result = prime * result + (conta85 ? 1231 : 1237);
		result = prime * result + (conta86 ? 1231 : 1237);
		result = prime * result + (conta87 ? 1231 : 1237);
		result = prime * result + (conta88 ? 1231 : 1237);
		result = prime * result + (conta89 ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(decimo13Dez);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(decimo13Nov);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(feriasAbr);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(feriasAgo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(feriasDez);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(feriasFev);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(feriasJan);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(feriasJul);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(feriasJun);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(feriasMai);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(feriasMar);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(feriasNov);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(feriasOut);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(feriasSet);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (inadimplencia ? 1231 : 1237);
		temp = Double.doubleToLongBits(indiceReajAdm71);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajAdm72);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajAdm73);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajAdm74);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajAdm75);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajAdm76);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajFunc);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan31);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan310);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan311);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan312);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan313);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan314);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan315);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan316);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan317);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan318);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan319);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan32);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan320);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan321);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan322);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan323);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan324);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan325);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan326);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan327);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan328);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan329);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan33);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan330);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan331);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan34);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan35);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan36);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan37);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan38);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajMan39);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(indiceReajTerc);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + media;
		result = prime * result + Arrays.hashCode(mes);
		result = prime * result + ((mesCestaNatal == null) ? 0 : mesCestaNatal.hashCode());
		result = prime * result + ((mesProjecao == null) ? 0 : mesProjecao.hashCode());
		result = prime * result + mesReajAdm71;
		result = prime * result + mesReajAdm72;
		result = prime * result + mesReajAdm73;
		result = prime * result + mesReajAdm74;
		result = prime * result + mesReajAdm75;
		result = prime * result + mesReajAdm76;
		result = prime * result + mesReajFunc;
		result = prime * result + mesReajMan31;
		result = prime * result + mesReajMan310;
		result = prime * result + mesReajMan311;
		result = prime * result + mesReajMan312;
		result = prime * result + mesReajMan313;
		result = prime * result + mesReajMan314;
		result = prime * result + mesReajMan315;
		result = prime * result + mesReajMan316;
		result = prime * result + mesReajMan317;
		result = prime * result + mesReajMan318;
		result = prime * result + mesReajMan319;
		result = prime * result + mesReajMan32;
		result = prime * result + mesReajMan320;
		result = prime * result + mesReajMan321;
		result = prime * result + mesReajMan322;
		result = prime * result + mesReajMan323;
		result = prime * result + mesReajMan324;
		result = prime * result + mesReajMan325;
		result = prime * result + mesReajMan326;
		result = prime * result + mesReajMan327;
		result = prime * result + mesReajMan328;
		result = prime * result + mesReajMan329;
		result = prime * result + mesReajMan33;
		result = prime * result + mesReajMan330;
		result = prime * result + mesReajMan331;
		result = prime * result + mesReajMan34;
		result = prime * result + mesReajMan35;
		result = prime * result + mesReajMan36;
		result = prime * result + mesReajMan37;
		result = prime * result + mesReajMan38;
		result = prime * result + mesReajMan39;
		result = prime * result + mesReajTerc;
		result = prime * result + mesesProjecao;
		result = prime * result + ((nomeConta01 == null) ? 0 : nomeConta01.hashCode());
		result = prime * result + ((nomeConta010 == null) ? 0 : nomeConta010.hashCode());
		result = prime * result + ((nomeConta011 == null) ? 0 : nomeConta011.hashCode());
		result = prime * result + ((nomeConta012 == null) ? 0 : nomeConta012.hashCode());
		result = prime * result + ((nomeConta013 == null) ? 0 : nomeConta013.hashCode());
		result = prime * result + ((nomeConta014 == null) ? 0 : nomeConta014.hashCode());
		result = prime * result + ((nomeConta015 == null) ? 0 : nomeConta015.hashCode());
		result = prime * result + ((nomeConta016 == null) ? 0 : nomeConta016.hashCode());
		result = prime * result + ((nomeConta017 == null) ? 0 : nomeConta017.hashCode());
		result = prime * result + ((nomeConta018 == null) ? 0 : nomeConta018.hashCode());
		result = prime * result + ((nomeConta019 == null) ? 0 : nomeConta019.hashCode());
		result = prime * result + ((nomeConta02 == null) ? 0 : nomeConta02.hashCode());
		result = prime * result + ((nomeConta020 == null) ? 0 : nomeConta020.hashCode());
		result = prime * result + ((nomeConta021 == null) ? 0 : nomeConta021.hashCode());
		result = prime * result + ((nomeConta022 == null) ? 0 : nomeConta022.hashCode());
		result = prime * result + ((nomeConta023 == null) ? 0 : nomeConta023.hashCode());
		result = prime * result + ((nomeConta024 == null) ? 0 : nomeConta024.hashCode());
		result = prime * result + ((nomeConta025 == null) ? 0 : nomeConta025.hashCode());
		result = prime * result + ((nomeConta026 == null) ? 0 : nomeConta026.hashCode());
		result = prime * result + ((nomeConta027 == null) ? 0 : nomeConta027.hashCode());
		result = prime * result + ((nomeConta028 == null) ? 0 : nomeConta028.hashCode());
		result = prime * result + ((nomeConta029 == null) ? 0 : nomeConta029.hashCode());
		result = prime * result + ((nomeConta03 == null) ? 0 : nomeConta03.hashCode());
		result = prime * result + ((nomeConta030 == null) ? 0 : nomeConta030.hashCode());
		result = prime * result + ((nomeConta031 == null) ? 0 : nomeConta031.hashCode());
		result = prime * result + ((nomeConta032 == null) ? 0 : nomeConta032.hashCode());
		result = prime * result + ((nomeConta033 == null) ? 0 : nomeConta033.hashCode());
		result = prime * result + ((nomeConta034 == null) ? 0 : nomeConta034.hashCode());
		result = prime * result + ((nomeConta035 == null) ? 0 : nomeConta035.hashCode());
		result = prime * result + ((nomeConta036 == null) ? 0 : nomeConta036.hashCode());
		result = prime * result + ((nomeConta037 == null) ? 0 : nomeConta037.hashCode());
		result = prime * result + ((nomeConta038 == null) ? 0 : nomeConta038.hashCode());
		result = prime * result + ((nomeConta04 == null) ? 0 : nomeConta04.hashCode());
		result = prime * result + ((nomeConta05 == null) ? 0 : nomeConta05.hashCode());
		result = prime * result + ((nomeConta06 == null) ? 0 : nomeConta06.hashCode());
		result = prime * result + ((nomeConta07 == null) ? 0 : nomeConta07.hashCode());
		result = prime * result + ((nomeConta08 == null) ? 0 : nomeConta08.hashCode());
		result = prime * result + ((nomeConta09 == null) ? 0 : nomeConta09.hashCode());
		result = prime * result + ((nomeConta11 == null) ? 0 : nomeConta11.hashCode());
		result = prime * result + ((nomeConta12 == null) ? 0 : nomeConta12.hashCode());
		result = prime * result + ((nomeConta13 == null) ? 0 : nomeConta13.hashCode());
		result = prime * result + ((nomeConta14 == null) ? 0 : nomeConta14.hashCode());
		result = prime * result + ((nomeConta15 == null) ? 0 : nomeConta15.hashCode());
		result = prime * result + ((nomeConta21 == null) ? 0 : nomeConta21.hashCode());
		result = prime * result + ((nomeConta22 == null) ? 0 : nomeConta22.hashCode());
		result = prime * result + ((nomeConta23 == null) ? 0 : nomeConta23.hashCode());
		result = prime * result + ((nomeConta24 == null) ? 0 : nomeConta24.hashCode());
		result = prime * result + ((nomeConta25 == null) ? 0 : nomeConta25.hashCode());
		result = prime * result + ((nomeConta26 == null) ? 0 : nomeConta26.hashCode());
		result = prime * result + ((nomeConta27 == null) ? 0 : nomeConta27.hashCode());
		result = prime * result + ((nomeConta31 == null) ? 0 : nomeConta31.hashCode());
		result = prime * result + ((nomeConta310 == null) ? 0 : nomeConta310.hashCode());
		result = prime * result + ((nomeConta311 == null) ? 0 : nomeConta311.hashCode());
		result = prime * result + ((nomeConta312 == null) ? 0 : nomeConta312.hashCode());
		result = prime * result + ((nomeConta313 == null) ? 0 : nomeConta313.hashCode());
		result = prime * result + ((nomeConta314 == null) ? 0 : nomeConta314.hashCode());
		result = prime * result + ((nomeConta315 == null) ? 0 : nomeConta315.hashCode());
		result = prime * result + ((nomeConta316 == null) ? 0 : nomeConta316.hashCode());
		result = prime * result + ((nomeConta317 == null) ? 0 : nomeConta317.hashCode());
		result = prime * result + ((nomeConta318 == null) ? 0 : nomeConta318.hashCode());
		result = prime * result + ((nomeConta319 == null) ? 0 : nomeConta319.hashCode());
		result = prime * result + ((nomeConta32 == null) ? 0 : nomeConta32.hashCode());
		result = prime * result + ((nomeConta320 == null) ? 0 : nomeConta320.hashCode());
		result = prime * result + ((nomeConta321 == null) ? 0 : nomeConta321.hashCode());
		result = prime * result + ((nomeConta322 == null) ? 0 : nomeConta322.hashCode());
		result = prime * result + ((nomeConta323 == null) ? 0 : nomeConta323.hashCode());
		result = prime * result + ((nomeConta324 == null) ? 0 : nomeConta324.hashCode());
		result = prime * result + ((nomeConta325 == null) ? 0 : nomeConta325.hashCode());
		result = prime * result + ((nomeConta326 == null) ? 0 : nomeConta326.hashCode());
		result = prime * result + ((nomeConta327 == null) ? 0 : nomeConta327.hashCode());
		result = prime * result + ((nomeConta328 == null) ? 0 : nomeConta328.hashCode());
		result = prime * result + ((nomeConta329 == null) ? 0 : nomeConta329.hashCode());
		result = prime * result + ((nomeConta33 == null) ? 0 : nomeConta33.hashCode());
		result = prime * result + ((nomeConta330 == null) ? 0 : nomeConta330.hashCode());
		result = prime * result + ((nomeConta331 == null) ? 0 : nomeConta331.hashCode());
		result = prime * result + ((nomeConta34 == null) ? 0 : nomeConta34.hashCode());
		result = prime * result + ((nomeConta35 == null) ? 0 : nomeConta35.hashCode());
		result = prime * result + ((nomeConta36 == null) ? 0 : nomeConta36.hashCode());
		result = prime * result + ((nomeConta37 == null) ? 0 : nomeConta37.hashCode());
		result = prime * result + ((nomeConta38 == null) ? 0 : nomeConta38.hashCode());
		result = prime * result + ((nomeConta39 == null) ? 0 : nomeConta39.hashCode());
		result = prime * result + ((nomeConta41 == null) ? 0 : nomeConta41.hashCode());
		result = prime * result + ((nomeConta42 == null) ? 0 : nomeConta42.hashCode());
		result = prime * result + ((nomeConta51 == null) ? 0 : nomeConta51.hashCode());
		result = prime * result + ((nomeConta52 == null) ? 0 : nomeConta52.hashCode());
		result = prime * result + ((nomeConta53 == null) ? 0 : nomeConta53.hashCode());
		result = prime * result + ((nomeConta54 == null) ? 0 : nomeConta54.hashCode());
		result = prime * result + ((nomeConta55 == null) ? 0 : nomeConta55.hashCode());
		result = prime * result + ((nomeConta56 == null) ? 0 : nomeConta56.hashCode());
		result = prime * result + ((nomeConta61 == null) ? 0 : nomeConta61.hashCode());
		result = prime * result + ((nomeConta62 == null) ? 0 : nomeConta62.hashCode());
		result = prime * result + ((nomeConta63 == null) ? 0 : nomeConta63.hashCode());
		result = prime * result + ((nomeConta64 == null) ? 0 : nomeConta64.hashCode());
		result = prime * result + ((nomeConta65 == null) ? 0 : nomeConta65.hashCode());
		result = prime * result + ((nomeConta66 == null) ? 0 : nomeConta66.hashCode());
		result = prime * result + ((nomeConta67 == null) ? 0 : nomeConta67.hashCode());
		result = prime * result + ((nomeConta68 == null) ? 0 : nomeConta68.hashCode());
		result = prime * result + ((nomeConta69 == null) ? 0 : nomeConta69.hashCode());
		result = prime * result + ((nomeConta71 == null) ? 0 : nomeConta71.hashCode());
		result = prime * result + ((nomeConta72 == null) ? 0 : nomeConta72.hashCode());
		result = prime * result + ((nomeConta73 == null) ? 0 : nomeConta73.hashCode());
		result = prime * result + ((nomeConta74 == null) ? 0 : nomeConta74.hashCode());
		result = prime * result + ((nomeConta75 == null) ? 0 : nomeConta75.hashCode());
		result = prime * result + ((nomeConta76 == null) ? 0 : nomeConta76.hashCode());
		result = prime * result + ((nomeConta81 == null) ? 0 : nomeConta81.hashCode());
		result = prime * result + ((nomeConta810 == null) ? 0 : nomeConta810.hashCode());
		result = prime * result + ((nomeConta811 == null) ? 0 : nomeConta811.hashCode());
		result = prime * result + ((nomeConta812 == null) ? 0 : nomeConta812.hashCode());
		result = prime * result + ((nomeConta813 == null) ? 0 : nomeConta813.hashCode());
		result = prime * result + ((nomeConta814 == null) ? 0 : nomeConta814.hashCode());
		result = prime * result + ((nomeConta815 == null) ? 0 : nomeConta815.hashCode());
		result = prime * result + ((nomeConta816 == null) ? 0 : nomeConta816.hashCode());
		result = prime * result + ((nomeConta817 == null) ? 0 : nomeConta817.hashCode());
		result = prime * result + ((nomeConta818 == null) ? 0 : nomeConta818.hashCode());
		result = prime * result + ((nomeConta819 == null) ? 0 : nomeConta819.hashCode());
		result = prime * result + ((nomeConta82 == null) ? 0 : nomeConta82.hashCode());
		result = prime * result + ((nomeConta820 == null) ? 0 : nomeConta820.hashCode());
		result = prime * result + ((nomeConta821 == null) ? 0 : nomeConta821.hashCode());
		result = prime * result + ((nomeConta822 == null) ? 0 : nomeConta822.hashCode());
		result = prime * result + ((nomeConta823 == null) ? 0 : nomeConta823.hashCode());
		result = prime * result + ((nomeConta824 == null) ? 0 : nomeConta824.hashCode());
		result = prime * result + ((nomeConta825 == null) ? 0 : nomeConta825.hashCode());
		result = prime * result + ((nomeConta826 == null) ? 0 : nomeConta826.hashCode());
		result = prime * result + ((nomeConta827 == null) ? 0 : nomeConta827.hashCode());
		result = prime * result + ((nomeConta828 == null) ? 0 : nomeConta828.hashCode());
		result = prime * result + ((nomeConta829 == null) ? 0 : nomeConta829.hashCode());
		result = prime * result + ((nomeConta83 == null) ? 0 : nomeConta83.hashCode());
		result = prime * result + ((nomeConta830 == null) ? 0 : nomeConta830.hashCode());
		result = prime * result + ((nomeConta831 == null) ? 0 : nomeConta831.hashCode());
		result = prime * result + ((nomeConta832 == null) ? 0 : nomeConta832.hashCode());
		result = prime * result + ((nomeConta833 == null) ? 0 : nomeConta833.hashCode());
		result = prime * result + ((nomeConta834 == null) ? 0 : nomeConta834.hashCode());
		result = prime * result + ((nomeConta835 == null) ? 0 : nomeConta835.hashCode());
		result = prime * result + ((nomeConta836 == null) ? 0 : nomeConta836.hashCode());
		result = prime * result + ((nomeConta837 == null) ? 0 : nomeConta837.hashCode());
		result = prime * result + ((nomeConta84 == null) ? 0 : nomeConta84.hashCode());
		result = prime * result + ((nomeConta85 == null) ? 0 : nomeConta85.hashCode());
		result = prime * result + ((nomeConta86 == null) ? 0 : nomeConta86.hashCode());
		result = prime * result + ((nomeConta87 == null) ? 0 : nomeConta87.hashCode());
		result = prime * result + ((nomeConta88 == null) ? 0 : nomeConta88.hashCode());
		result = prime * result + ((nomeConta89 == null) ? 0 : nomeConta89.hashCode());
		temp = Double.doubleToLongBits(valorConta01);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta010);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta011);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta012);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta013);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta014);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta015);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta016);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta017);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta018);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta019);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta02);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta020);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta021);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta022);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta023);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta024);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta025);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta026);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta027);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta028);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta029);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta03);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta030);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta031);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta032);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta033);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta034);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta035);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta036);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta037);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta038);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta04);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta05);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta06);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta07);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta08);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta09);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta11);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta12);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta13);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta14);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta15);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta21);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta22);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta23);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta24);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta25);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta26);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta27);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta31);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta310);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta311);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta312);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta313);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta314);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta315);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta316);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta317);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta318);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta319);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta32);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta320);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta321);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta322);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta323);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta324);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta325);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta326);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta327);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta328);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta329);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta33);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta330);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta331);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta34);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta35);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta36);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta37);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta38);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta39);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta41);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta42);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta51);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta52);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta53);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta54);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta55);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta56);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta61);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta62);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta63);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta64);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta65);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta66);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta67);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta68);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta69);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta71);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta72);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta73);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta74);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta75);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta76);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta81);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta810);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta811);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta812);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta813);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta814);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta815);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta816);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta817);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta818);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta819);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta82);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta820);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta821);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta822);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta823);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta824);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta825);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta826);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta827);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta828);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta829);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta83);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta830);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta831);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta832);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta833);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta834);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta835);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta836);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta837);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta84);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta85);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta86);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta87);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta88);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorConta89);
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
		intra_projetar_orcamento other = (intra_projetar_orcamento) obj;
		if (capa0 == null) {
			if (other.capa0 != null)
				return false;
		} else if (!capa0.equals(other.capa0))
			return false;
		if (capa1 == null) {
			if (other.capa1 != null)
				return false;
		} else if (!capa1.equals(other.capa1))
			return false;
		if (capa2 == null) {
			if (other.capa2 != null)
				return false;
		} else if (!capa2.equals(other.capa2))
			return false;
		if (capa3 == null) {
			if (other.capa3 != null)
				return false;
		} else if (!capa3.equals(other.capa3))
			return false;
		if (capa4 == null) {
			if (other.capa4 != null)
				return false;
		} else if (!capa4.equals(other.capa4))
			return false;
		if (capa5 == null) {
			if (other.capa5 != null)
				return false;
		} else if (!capa5.equals(other.capa5))
			return false;
		if (capa6 == null) {
			if (other.capa6 != null)
				return false;
		} else if (!capa6.equals(other.capa6))
			return false;
		if (capa7 == null) {
			if (other.capa7 != null)
				return false;
		} else if (!capa7.equals(other.capa7))
			return false;
		if (capa8 == null) {
			if (other.capa8 != null)
				return false;
		} else if (!capa8.equals(other.capa8))
			return false;
		if (codigoGerente != other.codigoGerente)
			return false;
		if (condominio != other.condominio)
			return false;
		if (conta01 != other.conta01)
			return false;
		if (conta010 != other.conta010)
			return false;
		if (conta011 != other.conta011)
			return false;
		if (conta012 != other.conta012)
			return false;
		if (conta013 != other.conta013)
			return false;
		if (conta014 != other.conta014)
			return false;
		if (conta015 != other.conta015)
			return false;
		if (conta016 != other.conta016)
			return false;
		if (conta017 != other.conta017)
			return false;
		if (conta018 != other.conta018)
			return false;
		if (conta019 != other.conta019)
			return false;
		if (conta02 != other.conta02)
			return false;
		if (conta020 != other.conta020)
			return false;
		if (conta021 != other.conta021)
			return false;
		if (conta022 != other.conta022)
			return false;
		if (conta023 != other.conta023)
			return false;
		if (conta024 != other.conta024)
			return false;
		if (conta025 != other.conta025)
			return false;
		if (conta026 != other.conta026)
			return false;
		if (conta027 != other.conta027)
			return false;
		if (conta028 != other.conta028)
			return false;
		if (conta029 != other.conta029)
			return false;
		if (conta03 != other.conta03)
			return false;
		if (conta030 != other.conta030)
			return false;
		if (conta031 != other.conta031)
			return false;
		if (conta032 != other.conta032)
			return false;
		if (conta033 != other.conta033)
			return false;
		if (conta034 != other.conta034)
			return false;
		if (conta035 != other.conta035)
			return false;
		if (conta036 != other.conta036)
			return false;
		if (conta037 != other.conta037)
			return false;
		if (conta038 != other.conta038)
			return false;
		if (conta04 != other.conta04)
			return false;
		if (conta05 != other.conta05)
			return false;
		if (conta06 != other.conta06)
			return false;
		if (conta07 != other.conta07)
			return false;
		if (conta08 != other.conta08)
			return false;
		if (conta09 != other.conta09)
			return false;
		if (conta11 != other.conta11)
			return false;
		if (conta12 != other.conta12)
			return false;
		if (conta13 != other.conta13)
			return false;
		if (conta14 != other.conta14)
			return false;
		if (conta15 != other.conta15)
			return false;
		if (conta21 != other.conta21)
			return false;
		if (conta22 != other.conta22)
			return false;
		if (conta23 != other.conta23)
			return false;
		if (conta24 != other.conta24)
			return false;
		if (conta25 != other.conta25)
			return false;
		if (conta26 != other.conta26)
			return false;
		if (conta27 != other.conta27)
			return false;
		if (conta31 != other.conta31)
			return false;
		if (conta310 != other.conta310)
			return false;
		if (conta311 != other.conta311)
			return false;
		if (conta312 != other.conta312)
			return false;
		if (conta313 != other.conta313)
			return false;
		if (conta314 != other.conta314)
			return false;
		if (conta315 != other.conta315)
			return false;
		if (conta316 != other.conta316)
			return false;
		if (conta317 != other.conta317)
			return false;
		if (conta318 != other.conta318)
			return false;
		if (conta319 != other.conta319)
			return false;
		if (conta32 != other.conta32)
			return false;
		if (conta320 != other.conta320)
			return false;
		if (conta321 != other.conta321)
			return false;
		if (conta322 != other.conta322)
			return false;
		if (conta323 != other.conta323)
			return false;
		if (conta324 != other.conta324)
			return false;
		if (conta325 != other.conta325)
			return false;
		if (conta326 != other.conta326)
			return false;
		if (conta327 != other.conta327)
			return false;
		if (conta328 != other.conta328)
			return false;
		if (conta329 != other.conta329)
			return false;
		if (conta33 != other.conta33)
			return false;
		if (conta330 != other.conta330)
			return false;
		if (conta331 != other.conta331)
			return false;
		if (conta34 != other.conta34)
			return false;
		if (conta35 != other.conta35)
			return false;
		if (conta36 != other.conta36)
			return false;
		if (conta37 != other.conta37)
			return false;
		if (conta38 != other.conta38)
			return false;
		if (conta39 != other.conta39)
			return false;
		if (conta41 != other.conta41)
			return false;
		if (conta42 != other.conta42)
			return false;
		if (conta51 != other.conta51)
			return false;
		if (conta52 != other.conta52)
			return false;
		if (conta53 != other.conta53)
			return false;
		if (conta54 != other.conta54)
			return false;
		if (conta55 != other.conta55)
			return false;
		if (conta56 != other.conta56)
			return false;
		if (conta61 != other.conta61)
			return false;
		if (conta62 != other.conta62)
			return false;
		if (conta63 != other.conta63)
			return false;
		if (conta64 != other.conta64)
			return false;
		if (conta65 != other.conta65)
			return false;
		if (conta66 != other.conta66)
			return false;
		if (conta67 != other.conta67)
			return false;
		if (conta68 != other.conta68)
			return false;
		if (conta69 != other.conta69)
			return false;
		if (conta71 != other.conta71)
			return false;
		if (conta72 != other.conta72)
			return false;
		if (conta73 != other.conta73)
			return false;
		if (conta74 != other.conta74)
			return false;
		if (conta75 != other.conta75)
			return false;
		if (conta76 != other.conta76)
			return false;
		if (conta81 != other.conta81)
			return false;
		if (conta810 != other.conta810)
			return false;
		if (conta811 != other.conta811)
			return false;
		if (conta812 != other.conta812)
			return false;
		if (conta813 != other.conta813)
			return false;
		if (conta814 != other.conta814)
			return false;
		if (conta815 != other.conta815)
			return false;
		if (conta816 != other.conta816)
			return false;
		if (conta817 != other.conta817)
			return false;
		if (conta818 != other.conta818)
			return false;
		if (conta819 != other.conta819)
			return false;
		if (conta82 != other.conta82)
			return false;
		if (conta820 != other.conta820)
			return false;
		if (conta821 != other.conta821)
			return false;
		if (conta822 != other.conta822)
			return false;
		if (conta823 != other.conta823)
			return false;
		if (conta824 != other.conta824)
			return false;
		if (conta825 != other.conta825)
			return false;
		if (conta826 != other.conta826)
			return false;
		if (conta827 != other.conta827)
			return false;
		if (conta828 != other.conta828)
			return false;
		if (conta829 != other.conta829)
			return false;
		if (conta83 != other.conta83)
			return false;
		if (conta830 != other.conta830)
			return false;
		if (conta831 != other.conta831)
			return false;
		if (conta832 != other.conta832)
			return false;
		if (conta833 != other.conta833)
			return false;
		if (conta834 != other.conta834)
			return false;
		if (conta835 != other.conta835)
			return false;
		if (conta836 != other.conta836)
			return false;
		if (conta837 != other.conta837)
			return false;
		if (conta84 != other.conta84)
			return false;
		if (conta85 != other.conta85)
			return false;
		if (conta86 != other.conta86)
			return false;
		if (conta87 != other.conta87)
			return false;
		if (conta88 != other.conta88)
			return false;
		if (conta89 != other.conta89)
			return false;
		if (Double.doubleToLongBits(decimo13Dez) != Double.doubleToLongBits(other.decimo13Dez))
			return false;
		if (Double.doubleToLongBits(decimo13Nov) != Double.doubleToLongBits(other.decimo13Nov))
			return false;
		if (Double.doubleToLongBits(feriasAbr) != Double.doubleToLongBits(other.feriasAbr))
			return false;
		if (Double.doubleToLongBits(feriasAgo) != Double.doubleToLongBits(other.feriasAgo))
			return false;
		if (Double.doubleToLongBits(feriasDez) != Double.doubleToLongBits(other.feriasDez))
			return false;
		if (Double.doubleToLongBits(feriasFev) != Double.doubleToLongBits(other.feriasFev))
			return false;
		if (Double.doubleToLongBits(feriasJan) != Double.doubleToLongBits(other.feriasJan))
			return false;
		if (Double.doubleToLongBits(feriasJul) != Double.doubleToLongBits(other.feriasJul))
			return false;
		if (Double.doubleToLongBits(feriasJun) != Double.doubleToLongBits(other.feriasJun))
			return false;
		if (Double.doubleToLongBits(feriasMai) != Double.doubleToLongBits(other.feriasMai))
			return false;
		if (Double.doubleToLongBits(feriasMar) != Double.doubleToLongBits(other.feriasMar))
			return false;
		if (Double.doubleToLongBits(feriasNov) != Double.doubleToLongBits(other.feriasNov))
			return false;
		if (Double.doubleToLongBits(feriasOut) != Double.doubleToLongBits(other.feriasOut))
			return false;
		if (Double.doubleToLongBits(feriasSet) != Double.doubleToLongBits(other.feriasSet))
			return false;
		if (inadimplencia != other.inadimplencia)
			return false;
		if (Double.doubleToLongBits(indiceReajAdm71) != Double.doubleToLongBits(other.indiceReajAdm71))
			return false;
		if (Double.doubleToLongBits(indiceReajAdm72) != Double.doubleToLongBits(other.indiceReajAdm72))
			return false;
		if (Double.doubleToLongBits(indiceReajAdm73) != Double.doubleToLongBits(other.indiceReajAdm73))
			return false;
		if (Double.doubleToLongBits(indiceReajAdm74) != Double.doubleToLongBits(other.indiceReajAdm74))
			return false;
		if (Double.doubleToLongBits(indiceReajAdm75) != Double.doubleToLongBits(other.indiceReajAdm75))
			return false;
		if (Double.doubleToLongBits(indiceReajAdm76) != Double.doubleToLongBits(other.indiceReajAdm76))
			return false;
		if (Double.doubleToLongBits(indiceReajFunc) != Double.doubleToLongBits(other.indiceReajFunc))
			return false;
		if (Double.doubleToLongBits(indiceReajMan31) != Double.doubleToLongBits(other.indiceReajMan31))
			return false;
		if (Double.doubleToLongBits(indiceReajMan310) != Double.doubleToLongBits(other.indiceReajMan310))
			return false;
		if (Double.doubleToLongBits(indiceReajMan311) != Double.doubleToLongBits(other.indiceReajMan311))
			return false;
		if (Double.doubleToLongBits(indiceReajMan312) != Double.doubleToLongBits(other.indiceReajMan312))
			return false;
		if (Double.doubleToLongBits(indiceReajMan313) != Double.doubleToLongBits(other.indiceReajMan313))
			return false;
		if (Double.doubleToLongBits(indiceReajMan314) != Double.doubleToLongBits(other.indiceReajMan314))
			return false;
		if (Double.doubleToLongBits(indiceReajMan315) != Double.doubleToLongBits(other.indiceReajMan315))
			return false;
		if (Double.doubleToLongBits(indiceReajMan316) != Double.doubleToLongBits(other.indiceReajMan316))
			return false;
		if (Double.doubleToLongBits(indiceReajMan317) != Double.doubleToLongBits(other.indiceReajMan317))
			return false;
		if (Double.doubleToLongBits(indiceReajMan318) != Double.doubleToLongBits(other.indiceReajMan318))
			return false;
		if (Double.doubleToLongBits(indiceReajMan319) != Double.doubleToLongBits(other.indiceReajMan319))
			return false;
		if (Double.doubleToLongBits(indiceReajMan32) != Double.doubleToLongBits(other.indiceReajMan32))
			return false;
		if (Double.doubleToLongBits(indiceReajMan320) != Double.doubleToLongBits(other.indiceReajMan320))
			return false;
		if (Double.doubleToLongBits(indiceReajMan321) != Double.doubleToLongBits(other.indiceReajMan321))
			return false;
		if (Double.doubleToLongBits(indiceReajMan322) != Double.doubleToLongBits(other.indiceReajMan322))
			return false;
		if (Double.doubleToLongBits(indiceReajMan323) != Double.doubleToLongBits(other.indiceReajMan323))
			return false;
		if (Double.doubleToLongBits(indiceReajMan324) != Double.doubleToLongBits(other.indiceReajMan324))
			return false;
		if (Double.doubleToLongBits(indiceReajMan325) != Double.doubleToLongBits(other.indiceReajMan325))
			return false;
		if (Double.doubleToLongBits(indiceReajMan326) != Double.doubleToLongBits(other.indiceReajMan326))
			return false;
		if (Double.doubleToLongBits(indiceReajMan327) != Double.doubleToLongBits(other.indiceReajMan327))
			return false;
		if (Double.doubleToLongBits(indiceReajMan328) != Double.doubleToLongBits(other.indiceReajMan328))
			return false;
		if (Double.doubleToLongBits(indiceReajMan329) != Double.doubleToLongBits(other.indiceReajMan329))
			return false;
		if (Double.doubleToLongBits(indiceReajMan33) != Double.doubleToLongBits(other.indiceReajMan33))
			return false;
		if (Double.doubleToLongBits(indiceReajMan330) != Double.doubleToLongBits(other.indiceReajMan330))
			return false;
		if (Double.doubleToLongBits(indiceReajMan331) != Double.doubleToLongBits(other.indiceReajMan331))
			return false;
		if (Double.doubleToLongBits(indiceReajMan34) != Double.doubleToLongBits(other.indiceReajMan34))
			return false;
		if (Double.doubleToLongBits(indiceReajMan35) != Double.doubleToLongBits(other.indiceReajMan35))
			return false;
		if (Double.doubleToLongBits(indiceReajMan36) != Double.doubleToLongBits(other.indiceReajMan36))
			return false;
		if (Double.doubleToLongBits(indiceReajMan37) != Double.doubleToLongBits(other.indiceReajMan37))
			return false;
		if (Double.doubleToLongBits(indiceReajMan38) != Double.doubleToLongBits(other.indiceReajMan38))
			return false;
		if (Double.doubleToLongBits(indiceReajMan39) != Double.doubleToLongBits(other.indiceReajMan39))
			return false;
		if (Double.doubleToLongBits(indiceReajTerc) != Double.doubleToLongBits(other.indiceReajTerc))
			return false;
		if (media != other.media)
			return false;
		if (!Arrays.equals(mes, other.mes))
			return false;
		if (mesCestaNatal == null) {
			if (other.mesCestaNatal != null)
				return false;
		} else if (!mesCestaNatal.equals(other.mesCestaNatal))
			return false;
		if (mesProjecao == null) {
			if (other.mesProjecao != null)
				return false;
		} else if (!mesProjecao.equals(other.mesProjecao))
			return false;
		if (mesReajAdm71 != other.mesReajAdm71)
			return false;
		if (mesReajAdm72 != other.mesReajAdm72)
			return false;
		if (mesReajAdm73 != other.mesReajAdm73)
			return false;
		if (mesReajAdm74 != other.mesReajAdm74)
			return false;
		if (mesReajAdm75 != other.mesReajAdm75)
			return false;
		if (mesReajAdm76 != other.mesReajAdm76)
			return false;
		if (mesReajFunc != other.mesReajFunc)
			return false;
		if (mesReajMan31 != other.mesReajMan31)
			return false;
		if (mesReajMan310 != other.mesReajMan310)
			return false;
		if (mesReajMan311 != other.mesReajMan311)
			return false;
		if (mesReajMan312 != other.mesReajMan312)
			return false;
		if (mesReajMan313 != other.mesReajMan313)
			return false;
		if (mesReajMan314 != other.mesReajMan314)
			return false;
		if (mesReajMan315 != other.mesReajMan315)
			return false;
		if (mesReajMan316 != other.mesReajMan316)
			return false;
		if (mesReajMan317 != other.mesReajMan317)
			return false;
		if (mesReajMan318 != other.mesReajMan318)
			return false;
		if (mesReajMan319 != other.mesReajMan319)
			return false;
		if (mesReajMan32 != other.mesReajMan32)
			return false;
		if (mesReajMan320 != other.mesReajMan320)
			return false;
		if (mesReajMan321 != other.mesReajMan321)
			return false;
		if (mesReajMan322 != other.mesReajMan322)
			return false;
		if (mesReajMan323 != other.mesReajMan323)
			return false;
		if (mesReajMan324 != other.mesReajMan324)
			return false;
		if (mesReajMan325 != other.mesReajMan325)
			return false;
		if (mesReajMan326 != other.mesReajMan326)
			return false;
		if (mesReajMan327 != other.mesReajMan327)
			return false;
		if (mesReajMan328 != other.mesReajMan328)
			return false;
		if (mesReajMan329 != other.mesReajMan329)
			return false;
		if (mesReajMan33 != other.mesReajMan33)
			return false;
		if (mesReajMan330 != other.mesReajMan330)
			return false;
		if (mesReajMan331 != other.mesReajMan331)
			return false;
		if (mesReajMan34 != other.mesReajMan34)
			return false;
		if (mesReajMan35 != other.mesReajMan35)
			return false;
		if (mesReajMan36 != other.mesReajMan36)
			return false;
		if (mesReajMan37 != other.mesReajMan37)
			return false;
		if (mesReajMan38 != other.mesReajMan38)
			return false;
		if (mesReajMan39 != other.mesReajMan39)
			return false;
		if (mesReajTerc != other.mesReajTerc)
			return false;
		if (mesesProjecao != other.mesesProjecao)
			return false;
		if (nomeConta01 == null) {
			if (other.nomeConta01 != null)
				return false;
		} else if (!nomeConta01.equals(other.nomeConta01))
			return false;
		if (nomeConta010 == null) {
			if (other.nomeConta010 != null)
				return false;
		} else if (!nomeConta010.equals(other.nomeConta010))
			return false;
		if (nomeConta011 == null) {
			if (other.nomeConta011 != null)
				return false;
		} else if (!nomeConta011.equals(other.nomeConta011))
			return false;
		if (nomeConta012 == null) {
			if (other.nomeConta012 != null)
				return false;
		} else if (!nomeConta012.equals(other.nomeConta012))
			return false;
		if (nomeConta013 == null) {
			if (other.nomeConta013 != null)
				return false;
		} else if (!nomeConta013.equals(other.nomeConta013))
			return false;
		if (nomeConta014 == null) {
			if (other.nomeConta014 != null)
				return false;
		} else if (!nomeConta014.equals(other.nomeConta014))
			return false;
		if (nomeConta015 == null) {
			if (other.nomeConta015 != null)
				return false;
		} else if (!nomeConta015.equals(other.nomeConta015))
			return false;
		if (nomeConta016 == null) {
			if (other.nomeConta016 != null)
				return false;
		} else if (!nomeConta016.equals(other.nomeConta016))
			return false;
		if (nomeConta017 == null) {
			if (other.nomeConta017 != null)
				return false;
		} else if (!nomeConta017.equals(other.nomeConta017))
			return false;
		if (nomeConta018 == null) {
			if (other.nomeConta018 != null)
				return false;
		} else if (!nomeConta018.equals(other.nomeConta018))
			return false;
		if (nomeConta019 == null) {
			if (other.nomeConta019 != null)
				return false;
		} else if (!nomeConta019.equals(other.nomeConta019))
			return false;
		if (nomeConta02 == null) {
			if (other.nomeConta02 != null)
				return false;
		} else if (!nomeConta02.equals(other.nomeConta02))
			return false;
		if (nomeConta020 == null) {
			if (other.nomeConta020 != null)
				return false;
		} else if (!nomeConta020.equals(other.nomeConta020))
			return false;
		if (nomeConta021 == null) {
			if (other.nomeConta021 != null)
				return false;
		} else if (!nomeConta021.equals(other.nomeConta021))
			return false;
		if (nomeConta022 == null) {
			if (other.nomeConta022 != null)
				return false;
		} else if (!nomeConta022.equals(other.nomeConta022))
			return false;
		if (nomeConta023 == null) {
			if (other.nomeConta023 != null)
				return false;
		} else if (!nomeConta023.equals(other.nomeConta023))
			return false;
		if (nomeConta024 == null) {
			if (other.nomeConta024 != null)
				return false;
		} else if (!nomeConta024.equals(other.nomeConta024))
			return false;
		if (nomeConta025 == null) {
			if (other.nomeConta025 != null)
				return false;
		} else if (!nomeConta025.equals(other.nomeConta025))
			return false;
		if (nomeConta026 == null) {
			if (other.nomeConta026 != null)
				return false;
		} else if (!nomeConta026.equals(other.nomeConta026))
			return false;
		if (nomeConta027 == null) {
			if (other.nomeConta027 != null)
				return false;
		} else if (!nomeConta027.equals(other.nomeConta027))
			return false;
		if (nomeConta028 == null) {
			if (other.nomeConta028 != null)
				return false;
		} else if (!nomeConta028.equals(other.nomeConta028))
			return false;
		if (nomeConta029 == null) {
			if (other.nomeConta029 != null)
				return false;
		} else if (!nomeConta029.equals(other.nomeConta029))
			return false;
		if (nomeConta03 == null) {
			if (other.nomeConta03 != null)
				return false;
		} else if (!nomeConta03.equals(other.nomeConta03))
			return false;
		if (nomeConta030 == null) {
			if (other.nomeConta030 != null)
				return false;
		} else if (!nomeConta030.equals(other.nomeConta030))
			return false;
		if (nomeConta031 == null) {
			if (other.nomeConta031 != null)
				return false;
		} else if (!nomeConta031.equals(other.nomeConta031))
			return false;
		if (nomeConta032 == null) {
			if (other.nomeConta032 != null)
				return false;
		} else if (!nomeConta032.equals(other.nomeConta032))
			return false;
		if (nomeConta033 == null) {
			if (other.nomeConta033 != null)
				return false;
		} else if (!nomeConta033.equals(other.nomeConta033))
			return false;
		if (nomeConta034 == null) {
			if (other.nomeConta034 != null)
				return false;
		} else if (!nomeConta034.equals(other.nomeConta034))
			return false;
		if (nomeConta035 == null) {
			if (other.nomeConta035 != null)
				return false;
		} else if (!nomeConta035.equals(other.nomeConta035))
			return false;
		if (nomeConta036 == null) {
			if (other.nomeConta036 != null)
				return false;
		} else if (!nomeConta036.equals(other.nomeConta036))
			return false;
		if (nomeConta037 == null) {
			if (other.nomeConta037 != null)
				return false;
		} else if (!nomeConta037.equals(other.nomeConta037))
			return false;
		if (nomeConta038 == null) {
			if (other.nomeConta038 != null)
				return false;
		} else if (!nomeConta038.equals(other.nomeConta038))
			return false;
		if (nomeConta04 == null) {
			if (other.nomeConta04 != null)
				return false;
		} else if (!nomeConta04.equals(other.nomeConta04))
			return false;
		if (nomeConta05 == null) {
			if (other.nomeConta05 != null)
				return false;
		} else if (!nomeConta05.equals(other.nomeConta05))
			return false;
		if (nomeConta06 == null) {
			if (other.nomeConta06 != null)
				return false;
		} else if (!nomeConta06.equals(other.nomeConta06))
			return false;
		if (nomeConta07 == null) {
			if (other.nomeConta07 != null)
				return false;
		} else if (!nomeConta07.equals(other.nomeConta07))
			return false;
		if (nomeConta08 == null) {
			if (other.nomeConta08 != null)
				return false;
		} else if (!nomeConta08.equals(other.nomeConta08))
			return false;
		if (nomeConta09 == null) {
			if (other.nomeConta09 != null)
				return false;
		} else if (!nomeConta09.equals(other.nomeConta09))
			return false;
		if (nomeConta11 == null) {
			if (other.nomeConta11 != null)
				return false;
		} else if (!nomeConta11.equals(other.nomeConta11))
			return false;
		if (nomeConta12 == null) {
			if (other.nomeConta12 != null)
				return false;
		} else if (!nomeConta12.equals(other.nomeConta12))
			return false;
		if (nomeConta13 == null) {
			if (other.nomeConta13 != null)
				return false;
		} else if (!nomeConta13.equals(other.nomeConta13))
			return false;
		if (nomeConta14 == null) {
			if (other.nomeConta14 != null)
				return false;
		} else if (!nomeConta14.equals(other.nomeConta14))
			return false;
		if (nomeConta15 == null) {
			if (other.nomeConta15 != null)
				return false;
		} else if (!nomeConta15.equals(other.nomeConta15))
			return false;
		if (nomeConta21 == null) {
			if (other.nomeConta21 != null)
				return false;
		} else if (!nomeConta21.equals(other.nomeConta21))
			return false;
		if (nomeConta22 == null) {
			if (other.nomeConta22 != null)
				return false;
		} else if (!nomeConta22.equals(other.nomeConta22))
			return false;
		if (nomeConta23 == null) {
			if (other.nomeConta23 != null)
				return false;
		} else if (!nomeConta23.equals(other.nomeConta23))
			return false;
		if (nomeConta24 == null) {
			if (other.nomeConta24 != null)
				return false;
		} else if (!nomeConta24.equals(other.nomeConta24))
			return false;
		if (nomeConta25 == null) {
			if (other.nomeConta25 != null)
				return false;
		} else if (!nomeConta25.equals(other.nomeConta25))
			return false;
		if (nomeConta26 == null) {
			if (other.nomeConta26 != null)
				return false;
		} else if (!nomeConta26.equals(other.nomeConta26))
			return false;
		if (nomeConta27 == null) {
			if (other.nomeConta27 != null)
				return false;
		} else if (!nomeConta27.equals(other.nomeConta27))
			return false;
		if (nomeConta31 == null) {
			if (other.nomeConta31 != null)
				return false;
		} else if (!nomeConta31.equals(other.nomeConta31))
			return false;
		if (nomeConta310 == null) {
			if (other.nomeConta310 != null)
				return false;
		} else if (!nomeConta310.equals(other.nomeConta310))
			return false;
		if (nomeConta311 == null) {
			if (other.nomeConta311 != null)
				return false;
		} else if (!nomeConta311.equals(other.nomeConta311))
			return false;
		if (nomeConta312 == null) {
			if (other.nomeConta312 != null)
				return false;
		} else if (!nomeConta312.equals(other.nomeConta312))
			return false;
		if (nomeConta313 == null) {
			if (other.nomeConta313 != null)
				return false;
		} else if (!nomeConta313.equals(other.nomeConta313))
			return false;
		if (nomeConta314 == null) {
			if (other.nomeConta314 != null)
				return false;
		} else if (!nomeConta314.equals(other.nomeConta314))
			return false;
		if (nomeConta315 == null) {
			if (other.nomeConta315 != null)
				return false;
		} else if (!nomeConta315.equals(other.nomeConta315))
			return false;
		if (nomeConta316 == null) {
			if (other.nomeConta316 != null)
				return false;
		} else if (!nomeConta316.equals(other.nomeConta316))
			return false;
		if (nomeConta317 == null) {
			if (other.nomeConta317 != null)
				return false;
		} else if (!nomeConta317.equals(other.nomeConta317))
			return false;
		if (nomeConta318 == null) {
			if (other.nomeConta318 != null)
				return false;
		} else if (!nomeConta318.equals(other.nomeConta318))
			return false;
		if (nomeConta319 == null) {
			if (other.nomeConta319 != null)
				return false;
		} else if (!nomeConta319.equals(other.nomeConta319))
			return false;
		if (nomeConta32 == null) {
			if (other.nomeConta32 != null)
				return false;
		} else if (!nomeConta32.equals(other.nomeConta32))
			return false;
		if (nomeConta320 == null) {
			if (other.nomeConta320 != null)
				return false;
		} else if (!nomeConta320.equals(other.nomeConta320))
			return false;
		if (nomeConta321 == null) {
			if (other.nomeConta321 != null)
				return false;
		} else if (!nomeConta321.equals(other.nomeConta321))
			return false;
		if (nomeConta322 == null) {
			if (other.nomeConta322 != null)
				return false;
		} else if (!nomeConta322.equals(other.nomeConta322))
			return false;
		if (nomeConta323 == null) {
			if (other.nomeConta323 != null)
				return false;
		} else if (!nomeConta323.equals(other.nomeConta323))
			return false;
		if (nomeConta324 == null) {
			if (other.nomeConta324 != null)
				return false;
		} else if (!nomeConta324.equals(other.nomeConta324))
			return false;
		if (nomeConta325 == null) {
			if (other.nomeConta325 != null)
				return false;
		} else if (!nomeConta325.equals(other.nomeConta325))
			return false;
		if (nomeConta326 == null) {
			if (other.nomeConta326 != null)
				return false;
		} else if (!nomeConta326.equals(other.nomeConta326))
			return false;
		if (nomeConta327 == null) {
			if (other.nomeConta327 != null)
				return false;
		} else if (!nomeConta327.equals(other.nomeConta327))
			return false;
		if (nomeConta328 == null) {
			if (other.nomeConta328 != null)
				return false;
		} else if (!nomeConta328.equals(other.nomeConta328))
			return false;
		if (nomeConta329 == null) {
			if (other.nomeConta329 != null)
				return false;
		} else if (!nomeConta329.equals(other.nomeConta329))
			return false;
		if (nomeConta33 == null) {
			if (other.nomeConta33 != null)
				return false;
		} else if (!nomeConta33.equals(other.nomeConta33))
			return false;
		if (nomeConta330 == null) {
			if (other.nomeConta330 != null)
				return false;
		} else if (!nomeConta330.equals(other.nomeConta330))
			return false;
		if (nomeConta331 == null) {
			if (other.nomeConta331 != null)
				return false;
		} else if (!nomeConta331.equals(other.nomeConta331))
			return false;
		if (nomeConta34 == null) {
			if (other.nomeConta34 != null)
				return false;
		} else if (!nomeConta34.equals(other.nomeConta34))
			return false;
		if (nomeConta35 == null) {
			if (other.nomeConta35 != null)
				return false;
		} else if (!nomeConta35.equals(other.nomeConta35))
			return false;
		if (nomeConta36 == null) {
			if (other.nomeConta36 != null)
				return false;
		} else if (!nomeConta36.equals(other.nomeConta36))
			return false;
		if (nomeConta37 == null) {
			if (other.nomeConta37 != null)
				return false;
		} else if (!nomeConta37.equals(other.nomeConta37))
			return false;
		if (nomeConta38 == null) {
			if (other.nomeConta38 != null)
				return false;
		} else if (!nomeConta38.equals(other.nomeConta38))
			return false;
		if (nomeConta39 == null) {
			if (other.nomeConta39 != null)
				return false;
		} else if (!nomeConta39.equals(other.nomeConta39))
			return false;
		if (nomeConta41 == null) {
			if (other.nomeConta41 != null)
				return false;
		} else if (!nomeConta41.equals(other.nomeConta41))
			return false;
		if (nomeConta42 == null) {
			if (other.nomeConta42 != null)
				return false;
		} else if (!nomeConta42.equals(other.nomeConta42))
			return false;
		if (nomeConta51 == null) {
			if (other.nomeConta51 != null)
				return false;
		} else if (!nomeConta51.equals(other.nomeConta51))
			return false;
		if (nomeConta52 == null) {
			if (other.nomeConta52 != null)
				return false;
		} else if (!nomeConta52.equals(other.nomeConta52))
			return false;
		if (nomeConta53 == null) {
			if (other.nomeConta53 != null)
				return false;
		} else if (!nomeConta53.equals(other.nomeConta53))
			return false;
		if (nomeConta54 == null) {
			if (other.nomeConta54 != null)
				return false;
		} else if (!nomeConta54.equals(other.nomeConta54))
			return false;
		if (nomeConta55 == null) {
			if (other.nomeConta55 != null)
				return false;
		} else if (!nomeConta55.equals(other.nomeConta55))
			return false;
		if (nomeConta56 == null) {
			if (other.nomeConta56 != null)
				return false;
		} else if (!nomeConta56.equals(other.nomeConta56))
			return false;
		if (nomeConta61 == null) {
			if (other.nomeConta61 != null)
				return false;
		} else if (!nomeConta61.equals(other.nomeConta61))
			return false;
		if (nomeConta62 == null) {
			if (other.nomeConta62 != null)
				return false;
		} else if (!nomeConta62.equals(other.nomeConta62))
			return false;
		if (nomeConta63 == null) {
			if (other.nomeConta63 != null)
				return false;
		} else if (!nomeConta63.equals(other.nomeConta63))
			return false;
		if (nomeConta64 == null) {
			if (other.nomeConta64 != null)
				return false;
		} else if (!nomeConta64.equals(other.nomeConta64))
			return false;
		if (nomeConta65 == null) {
			if (other.nomeConta65 != null)
				return false;
		} else if (!nomeConta65.equals(other.nomeConta65))
			return false;
		if (nomeConta66 == null) {
			if (other.nomeConta66 != null)
				return false;
		} else if (!nomeConta66.equals(other.nomeConta66))
			return false;
		if (nomeConta67 == null) {
			if (other.nomeConta67 != null)
				return false;
		} else if (!nomeConta67.equals(other.nomeConta67))
			return false;
		if (nomeConta68 == null) {
			if (other.nomeConta68 != null)
				return false;
		} else if (!nomeConta68.equals(other.nomeConta68))
			return false;
		if (nomeConta69 == null) {
			if (other.nomeConta69 != null)
				return false;
		} else if (!nomeConta69.equals(other.nomeConta69))
			return false;
		if (nomeConta71 == null) {
			if (other.nomeConta71 != null)
				return false;
		} else if (!nomeConta71.equals(other.nomeConta71))
			return false;
		if (nomeConta72 == null) {
			if (other.nomeConta72 != null)
				return false;
		} else if (!nomeConta72.equals(other.nomeConta72))
			return false;
		if (nomeConta73 == null) {
			if (other.nomeConta73 != null)
				return false;
		} else if (!nomeConta73.equals(other.nomeConta73))
			return false;
		if (nomeConta74 == null) {
			if (other.nomeConta74 != null)
				return false;
		} else if (!nomeConta74.equals(other.nomeConta74))
			return false;
		if (nomeConta75 == null) {
			if (other.nomeConta75 != null)
				return false;
		} else if (!nomeConta75.equals(other.nomeConta75))
			return false;
		if (nomeConta76 == null) {
			if (other.nomeConta76 != null)
				return false;
		} else if (!nomeConta76.equals(other.nomeConta76))
			return false;
		if (nomeConta81 == null) {
			if (other.nomeConta81 != null)
				return false;
		} else if (!nomeConta81.equals(other.nomeConta81))
			return false;
		if (nomeConta810 == null) {
			if (other.nomeConta810 != null)
				return false;
		} else if (!nomeConta810.equals(other.nomeConta810))
			return false;
		if (nomeConta811 == null) {
			if (other.nomeConta811 != null)
				return false;
		} else if (!nomeConta811.equals(other.nomeConta811))
			return false;
		if (nomeConta812 == null) {
			if (other.nomeConta812 != null)
				return false;
		} else if (!nomeConta812.equals(other.nomeConta812))
			return false;
		if (nomeConta813 == null) {
			if (other.nomeConta813 != null)
				return false;
		} else if (!nomeConta813.equals(other.nomeConta813))
			return false;
		if (nomeConta814 == null) {
			if (other.nomeConta814 != null)
				return false;
		} else if (!nomeConta814.equals(other.nomeConta814))
			return false;
		if (nomeConta815 == null) {
			if (other.nomeConta815 != null)
				return false;
		} else if (!nomeConta815.equals(other.nomeConta815))
			return false;
		if (nomeConta816 == null) {
			if (other.nomeConta816 != null)
				return false;
		} else if (!nomeConta816.equals(other.nomeConta816))
			return false;
		if (nomeConta817 == null) {
			if (other.nomeConta817 != null)
				return false;
		} else if (!nomeConta817.equals(other.nomeConta817))
			return false;
		if (nomeConta818 == null) {
			if (other.nomeConta818 != null)
				return false;
		} else if (!nomeConta818.equals(other.nomeConta818))
			return false;
		if (nomeConta819 == null) {
			if (other.nomeConta819 != null)
				return false;
		} else if (!nomeConta819.equals(other.nomeConta819))
			return false;
		if (nomeConta82 == null) {
			if (other.nomeConta82 != null)
				return false;
		} else if (!nomeConta82.equals(other.nomeConta82))
			return false;
		if (nomeConta820 == null) {
			if (other.nomeConta820 != null)
				return false;
		} else if (!nomeConta820.equals(other.nomeConta820))
			return false;
		if (nomeConta821 == null) {
			if (other.nomeConta821 != null)
				return false;
		} else if (!nomeConta821.equals(other.nomeConta821))
			return false;
		if (nomeConta822 == null) {
			if (other.nomeConta822 != null)
				return false;
		} else if (!nomeConta822.equals(other.nomeConta822))
			return false;
		if (nomeConta823 == null) {
			if (other.nomeConta823 != null)
				return false;
		} else if (!nomeConta823.equals(other.nomeConta823))
			return false;
		if (nomeConta824 == null) {
			if (other.nomeConta824 != null)
				return false;
		} else if (!nomeConta824.equals(other.nomeConta824))
			return false;
		if (nomeConta825 == null) {
			if (other.nomeConta825 != null)
				return false;
		} else if (!nomeConta825.equals(other.nomeConta825))
			return false;
		if (nomeConta826 == null) {
			if (other.nomeConta826 != null)
				return false;
		} else if (!nomeConta826.equals(other.nomeConta826))
			return false;
		if (nomeConta827 == null) {
			if (other.nomeConta827 != null)
				return false;
		} else if (!nomeConta827.equals(other.nomeConta827))
			return false;
		if (nomeConta828 == null) {
			if (other.nomeConta828 != null)
				return false;
		} else if (!nomeConta828.equals(other.nomeConta828))
			return false;
		if (nomeConta829 == null) {
			if (other.nomeConta829 != null)
				return false;
		} else if (!nomeConta829.equals(other.nomeConta829))
			return false;
		if (nomeConta83 == null) {
			if (other.nomeConta83 != null)
				return false;
		} else if (!nomeConta83.equals(other.nomeConta83))
			return false;
		if (nomeConta830 == null) {
			if (other.nomeConta830 != null)
				return false;
		} else if (!nomeConta830.equals(other.nomeConta830))
			return false;
		if (nomeConta831 == null) {
			if (other.nomeConta831 != null)
				return false;
		} else if (!nomeConta831.equals(other.nomeConta831))
			return false;
		if (nomeConta832 == null) {
			if (other.nomeConta832 != null)
				return false;
		} else if (!nomeConta832.equals(other.nomeConta832))
			return false;
		if (nomeConta833 == null) {
			if (other.nomeConta833 != null)
				return false;
		} else if (!nomeConta833.equals(other.nomeConta833))
			return false;
		if (nomeConta834 == null) {
			if (other.nomeConta834 != null)
				return false;
		} else if (!nomeConta834.equals(other.nomeConta834))
			return false;
		if (nomeConta835 == null) {
			if (other.nomeConta835 != null)
				return false;
		} else if (!nomeConta835.equals(other.nomeConta835))
			return false;
		if (nomeConta836 == null) {
			if (other.nomeConta836 != null)
				return false;
		} else if (!nomeConta836.equals(other.nomeConta836))
			return false;
		if (nomeConta837 == null) {
			if (other.nomeConta837 != null)
				return false;
		} else if (!nomeConta837.equals(other.nomeConta837))
			return false;
		if (nomeConta84 == null) {
			if (other.nomeConta84 != null)
				return false;
		} else if (!nomeConta84.equals(other.nomeConta84))
			return false;
		if (nomeConta85 == null) {
			if (other.nomeConta85 != null)
				return false;
		} else if (!nomeConta85.equals(other.nomeConta85))
			return false;
		if (nomeConta86 == null) {
			if (other.nomeConta86 != null)
				return false;
		} else if (!nomeConta86.equals(other.nomeConta86))
			return false;
		if (nomeConta87 == null) {
			if (other.nomeConta87 != null)
				return false;
		} else if (!nomeConta87.equals(other.nomeConta87))
			return false;
		if (nomeConta88 == null) {
			if (other.nomeConta88 != null)
				return false;
		} else if (!nomeConta88.equals(other.nomeConta88))
			return false;
		if (nomeConta89 == null) {
			if (other.nomeConta89 != null)
				return false;
		} else if (!nomeConta89.equals(other.nomeConta89))
			return false;
		if (Double.doubleToLongBits(valorConta01) != Double.doubleToLongBits(other.valorConta01))
			return false;
		if (Double.doubleToLongBits(valorConta010) != Double.doubleToLongBits(other.valorConta010))
			return false;
		if (Double.doubleToLongBits(valorConta011) != Double.doubleToLongBits(other.valorConta011))
			return false;
		if (Double.doubleToLongBits(valorConta012) != Double.doubleToLongBits(other.valorConta012))
			return false;
		if (Double.doubleToLongBits(valorConta013) != Double.doubleToLongBits(other.valorConta013))
			return false;
		if (Double.doubleToLongBits(valorConta014) != Double.doubleToLongBits(other.valorConta014))
			return false;
		if (Double.doubleToLongBits(valorConta015) != Double.doubleToLongBits(other.valorConta015))
			return false;
		if (Double.doubleToLongBits(valorConta016) != Double.doubleToLongBits(other.valorConta016))
			return false;
		if (Double.doubleToLongBits(valorConta017) != Double.doubleToLongBits(other.valorConta017))
			return false;
		if (Double.doubleToLongBits(valorConta018) != Double.doubleToLongBits(other.valorConta018))
			return false;
		if (Double.doubleToLongBits(valorConta019) != Double.doubleToLongBits(other.valorConta019))
			return false;
		if (Double.doubleToLongBits(valorConta02) != Double.doubleToLongBits(other.valorConta02))
			return false;
		if (Double.doubleToLongBits(valorConta020) != Double.doubleToLongBits(other.valorConta020))
			return false;
		if (Double.doubleToLongBits(valorConta021) != Double.doubleToLongBits(other.valorConta021))
			return false;
		if (Double.doubleToLongBits(valorConta022) != Double.doubleToLongBits(other.valorConta022))
			return false;
		if (Double.doubleToLongBits(valorConta023) != Double.doubleToLongBits(other.valorConta023))
			return false;
		if (Double.doubleToLongBits(valorConta024) != Double.doubleToLongBits(other.valorConta024))
			return false;
		if (Double.doubleToLongBits(valorConta025) != Double.doubleToLongBits(other.valorConta025))
			return false;
		if (Double.doubleToLongBits(valorConta026) != Double.doubleToLongBits(other.valorConta026))
			return false;
		if (Double.doubleToLongBits(valorConta027) != Double.doubleToLongBits(other.valorConta027))
			return false;
		if (Double.doubleToLongBits(valorConta028) != Double.doubleToLongBits(other.valorConta028))
			return false;
		if (Double.doubleToLongBits(valorConta029) != Double.doubleToLongBits(other.valorConta029))
			return false;
		if (Double.doubleToLongBits(valorConta03) != Double.doubleToLongBits(other.valorConta03))
			return false;
		if (Double.doubleToLongBits(valorConta030) != Double.doubleToLongBits(other.valorConta030))
			return false;
		if (Double.doubleToLongBits(valorConta031) != Double.doubleToLongBits(other.valorConta031))
			return false;
		if (Double.doubleToLongBits(valorConta032) != Double.doubleToLongBits(other.valorConta032))
			return false;
		if (Double.doubleToLongBits(valorConta033) != Double.doubleToLongBits(other.valorConta033))
			return false;
		if (Double.doubleToLongBits(valorConta034) != Double.doubleToLongBits(other.valorConta034))
			return false;
		if (Double.doubleToLongBits(valorConta035) != Double.doubleToLongBits(other.valorConta035))
			return false;
		if (Double.doubleToLongBits(valorConta036) != Double.doubleToLongBits(other.valorConta036))
			return false;
		if (Double.doubleToLongBits(valorConta037) != Double.doubleToLongBits(other.valorConta037))
			return false;
		if (Double.doubleToLongBits(valorConta038) != Double.doubleToLongBits(other.valorConta038))
			return false;
		if (Double.doubleToLongBits(valorConta04) != Double.doubleToLongBits(other.valorConta04))
			return false;
		if (Double.doubleToLongBits(valorConta05) != Double.doubleToLongBits(other.valorConta05))
			return false;
		if (Double.doubleToLongBits(valorConta06) != Double.doubleToLongBits(other.valorConta06))
			return false;
		if (Double.doubleToLongBits(valorConta07) != Double.doubleToLongBits(other.valorConta07))
			return false;
		if (Double.doubleToLongBits(valorConta08) != Double.doubleToLongBits(other.valorConta08))
			return false;
		if (Double.doubleToLongBits(valorConta09) != Double.doubleToLongBits(other.valorConta09))
			return false;
		if (Double.doubleToLongBits(valorConta11) != Double.doubleToLongBits(other.valorConta11))
			return false;
		if (Double.doubleToLongBits(valorConta12) != Double.doubleToLongBits(other.valorConta12))
			return false;
		if (Double.doubleToLongBits(valorConta13) != Double.doubleToLongBits(other.valorConta13))
			return false;
		if (Double.doubleToLongBits(valorConta14) != Double.doubleToLongBits(other.valorConta14))
			return false;
		if (Double.doubleToLongBits(valorConta15) != Double.doubleToLongBits(other.valorConta15))
			return false;
		if (Double.doubleToLongBits(valorConta21) != Double.doubleToLongBits(other.valorConta21))
			return false;
		if (Double.doubleToLongBits(valorConta22) != Double.doubleToLongBits(other.valorConta22))
			return false;
		if (Double.doubleToLongBits(valorConta23) != Double.doubleToLongBits(other.valorConta23))
			return false;
		if (Double.doubleToLongBits(valorConta24) != Double.doubleToLongBits(other.valorConta24))
			return false;
		if (Double.doubleToLongBits(valorConta25) != Double.doubleToLongBits(other.valorConta25))
			return false;
		if (Double.doubleToLongBits(valorConta26) != Double.doubleToLongBits(other.valorConta26))
			return false;
		if (Double.doubleToLongBits(valorConta27) != Double.doubleToLongBits(other.valorConta27))
			return false;
		if (Double.doubleToLongBits(valorConta31) != Double.doubleToLongBits(other.valorConta31))
			return false;
		if (Double.doubleToLongBits(valorConta310) != Double.doubleToLongBits(other.valorConta310))
			return false;
		if (Double.doubleToLongBits(valorConta311) != Double.doubleToLongBits(other.valorConta311))
			return false;
		if (Double.doubleToLongBits(valorConta312) != Double.doubleToLongBits(other.valorConta312))
			return false;
		if (Double.doubleToLongBits(valorConta313) != Double.doubleToLongBits(other.valorConta313))
			return false;
		if (Double.doubleToLongBits(valorConta314) != Double.doubleToLongBits(other.valorConta314))
			return false;
		if (Double.doubleToLongBits(valorConta315) != Double.doubleToLongBits(other.valorConta315))
			return false;
		if (Double.doubleToLongBits(valorConta316) != Double.doubleToLongBits(other.valorConta316))
			return false;
		if (Double.doubleToLongBits(valorConta317) != Double.doubleToLongBits(other.valorConta317))
			return false;
		if (Double.doubleToLongBits(valorConta318) != Double.doubleToLongBits(other.valorConta318))
			return false;
		if (Double.doubleToLongBits(valorConta319) != Double.doubleToLongBits(other.valorConta319))
			return false;
		if (Double.doubleToLongBits(valorConta32) != Double.doubleToLongBits(other.valorConta32))
			return false;
		if (Double.doubleToLongBits(valorConta320) != Double.doubleToLongBits(other.valorConta320))
			return false;
		if (Double.doubleToLongBits(valorConta321) != Double.doubleToLongBits(other.valorConta321))
			return false;
		if (Double.doubleToLongBits(valorConta322) != Double.doubleToLongBits(other.valorConta322))
			return false;
		if (Double.doubleToLongBits(valorConta323) != Double.doubleToLongBits(other.valorConta323))
			return false;
		if (Double.doubleToLongBits(valorConta324) != Double.doubleToLongBits(other.valorConta324))
			return false;
		if (Double.doubleToLongBits(valorConta325) != Double.doubleToLongBits(other.valorConta325))
			return false;
		if (Double.doubleToLongBits(valorConta326) != Double.doubleToLongBits(other.valorConta326))
			return false;
		if (Double.doubleToLongBits(valorConta327) != Double.doubleToLongBits(other.valorConta327))
			return false;
		if (Double.doubleToLongBits(valorConta328) != Double.doubleToLongBits(other.valorConta328))
			return false;
		if (Double.doubleToLongBits(valorConta329) != Double.doubleToLongBits(other.valorConta329))
			return false;
		if (Double.doubleToLongBits(valorConta33) != Double.doubleToLongBits(other.valorConta33))
			return false;
		if (Double.doubleToLongBits(valorConta330) != Double.doubleToLongBits(other.valorConta330))
			return false;
		if (Double.doubleToLongBits(valorConta331) != Double.doubleToLongBits(other.valorConta331))
			return false;
		if (Double.doubleToLongBits(valorConta34) != Double.doubleToLongBits(other.valorConta34))
			return false;
		if (Double.doubleToLongBits(valorConta35) != Double.doubleToLongBits(other.valorConta35))
			return false;
		if (Double.doubleToLongBits(valorConta36) != Double.doubleToLongBits(other.valorConta36))
			return false;
		if (Double.doubleToLongBits(valorConta37) != Double.doubleToLongBits(other.valorConta37))
			return false;
		if (Double.doubleToLongBits(valorConta38) != Double.doubleToLongBits(other.valorConta38))
			return false;
		if (Double.doubleToLongBits(valorConta39) != Double.doubleToLongBits(other.valorConta39))
			return false;
		if (Double.doubleToLongBits(valorConta41) != Double.doubleToLongBits(other.valorConta41))
			return false;
		if (Double.doubleToLongBits(valorConta42) != Double.doubleToLongBits(other.valorConta42))
			return false;
		if (Double.doubleToLongBits(valorConta51) != Double.doubleToLongBits(other.valorConta51))
			return false;
		if (Double.doubleToLongBits(valorConta52) != Double.doubleToLongBits(other.valorConta52))
			return false;
		if (Double.doubleToLongBits(valorConta53) != Double.doubleToLongBits(other.valorConta53))
			return false;
		if (Double.doubleToLongBits(valorConta54) != Double.doubleToLongBits(other.valorConta54))
			return false;
		if (Double.doubleToLongBits(valorConta55) != Double.doubleToLongBits(other.valorConta55))
			return false;
		if (Double.doubleToLongBits(valorConta56) != Double.doubleToLongBits(other.valorConta56))
			return false;
		if (Double.doubleToLongBits(valorConta61) != Double.doubleToLongBits(other.valorConta61))
			return false;
		if (Double.doubleToLongBits(valorConta62) != Double.doubleToLongBits(other.valorConta62))
			return false;
		if (Double.doubleToLongBits(valorConta63) != Double.doubleToLongBits(other.valorConta63))
			return false;
		if (Double.doubleToLongBits(valorConta64) != Double.doubleToLongBits(other.valorConta64))
			return false;
		if (Double.doubleToLongBits(valorConta65) != Double.doubleToLongBits(other.valorConta65))
			return false;
		if (Double.doubleToLongBits(valorConta66) != Double.doubleToLongBits(other.valorConta66))
			return false;
		if (Double.doubleToLongBits(valorConta67) != Double.doubleToLongBits(other.valorConta67))
			return false;
		if (Double.doubleToLongBits(valorConta68) != Double.doubleToLongBits(other.valorConta68))
			return false;
		if (Double.doubleToLongBits(valorConta69) != Double.doubleToLongBits(other.valorConta69))
			return false;
		if (Double.doubleToLongBits(valorConta71) != Double.doubleToLongBits(other.valorConta71))
			return false;
		if (Double.doubleToLongBits(valorConta72) != Double.doubleToLongBits(other.valorConta72))
			return false;
		if (Double.doubleToLongBits(valorConta73) != Double.doubleToLongBits(other.valorConta73))
			return false;
		if (Double.doubleToLongBits(valorConta74) != Double.doubleToLongBits(other.valorConta74))
			return false;
		if (Double.doubleToLongBits(valorConta75) != Double.doubleToLongBits(other.valorConta75))
			return false;
		if (Double.doubleToLongBits(valorConta76) != Double.doubleToLongBits(other.valorConta76))
			return false;
		if (Double.doubleToLongBits(valorConta81) != Double.doubleToLongBits(other.valorConta81))
			return false;
		if (Double.doubleToLongBits(valorConta810) != Double.doubleToLongBits(other.valorConta810))
			return false;
		if (Double.doubleToLongBits(valorConta811) != Double.doubleToLongBits(other.valorConta811))
			return false;
		if (Double.doubleToLongBits(valorConta812) != Double.doubleToLongBits(other.valorConta812))
			return false;
		if (Double.doubleToLongBits(valorConta813) != Double.doubleToLongBits(other.valorConta813))
			return false;
		if (Double.doubleToLongBits(valorConta814) != Double.doubleToLongBits(other.valorConta814))
			return false;
		if (Double.doubleToLongBits(valorConta815) != Double.doubleToLongBits(other.valorConta815))
			return false;
		if (Double.doubleToLongBits(valorConta816) != Double.doubleToLongBits(other.valorConta816))
			return false;
		if (Double.doubleToLongBits(valorConta817) != Double.doubleToLongBits(other.valorConta817))
			return false;
		if (Double.doubleToLongBits(valorConta818) != Double.doubleToLongBits(other.valorConta818))
			return false;
		if (Double.doubleToLongBits(valorConta819) != Double.doubleToLongBits(other.valorConta819))
			return false;
		if (Double.doubleToLongBits(valorConta82) != Double.doubleToLongBits(other.valorConta82))
			return false;
		if (Double.doubleToLongBits(valorConta820) != Double.doubleToLongBits(other.valorConta820))
			return false;
		if (Double.doubleToLongBits(valorConta821) != Double.doubleToLongBits(other.valorConta821))
			return false;
		if (Double.doubleToLongBits(valorConta822) != Double.doubleToLongBits(other.valorConta822))
			return false;
		if (Double.doubleToLongBits(valorConta823) != Double.doubleToLongBits(other.valorConta823))
			return false;
		if (Double.doubleToLongBits(valorConta824) != Double.doubleToLongBits(other.valorConta824))
			return false;
		if (Double.doubleToLongBits(valorConta825) != Double.doubleToLongBits(other.valorConta825))
			return false;
		if (Double.doubleToLongBits(valorConta826) != Double.doubleToLongBits(other.valorConta826))
			return false;
		if (Double.doubleToLongBits(valorConta827) != Double.doubleToLongBits(other.valorConta827))
			return false;
		if (Double.doubleToLongBits(valorConta828) != Double.doubleToLongBits(other.valorConta828))
			return false;
		if (Double.doubleToLongBits(valorConta829) != Double.doubleToLongBits(other.valorConta829))
			return false;
		if (Double.doubleToLongBits(valorConta83) != Double.doubleToLongBits(other.valorConta83))
			return false;
		if (Double.doubleToLongBits(valorConta830) != Double.doubleToLongBits(other.valorConta830))
			return false;
		if (Double.doubleToLongBits(valorConta831) != Double.doubleToLongBits(other.valorConta831))
			return false;
		if (Double.doubleToLongBits(valorConta832) != Double.doubleToLongBits(other.valorConta832))
			return false;
		if (Double.doubleToLongBits(valorConta833) != Double.doubleToLongBits(other.valorConta833))
			return false;
		if (Double.doubleToLongBits(valorConta834) != Double.doubleToLongBits(other.valorConta834))
			return false;
		if (Double.doubleToLongBits(valorConta835) != Double.doubleToLongBits(other.valorConta835))
			return false;
		if (Double.doubleToLongBits(valorConta836) != Double.doubleToLongBits(other.valorConta836))
			return false;
		if (Double.doubleToLongBits(valorConta837) != Double.doubleToLongBits(other.valorConta837))
			return false;
		if (Double.doubleToLongBits(valorConta84) != Double.doubleToLongBits(other.valorConta84))
			return false;
		if (Double.doubleToLongBits(valorConta85) != Double.doubleToLongBits(other.valorConta85))
			return false;
		if (Double.doubleToLongBits(valorConta86) != Double.doubleToLongBits(other.valorConta86))
			return false;
		if (Double.doubleToLongBits(valorConta87) != Double.doubleToLongBits(other.valorConta87))
			return false;
		if (Double.doubleToLongBits(valorConta88) != Double.doubleToLongBits(other.valorConta88))
			return false;
		if (Double.doubleToLongBits(valorConta89) != Double.doubleToLongBits(other.valorConta89))
			return false;
		return true;
	}
	
	
	
}
