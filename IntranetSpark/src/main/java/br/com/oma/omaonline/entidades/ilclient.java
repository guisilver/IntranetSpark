package br.com.oma.omaonline.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class ilclient implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;

	@Column(columnDefinition = "varchar(35)", length = 35)
	private String nome;

	@Column(name = "aos_cuidados", columnDefinition = "varchar(35)", length = 35)
	private String aosCuidados;

	@Column(columnDefinition = "char(5)", length = 5)
	private String logradouro;

	@Column(columnDefinition = "varchar(35)", length = 35)
	private String endereco;

	@Column(columnDefinition = "varchar(35)", length = 35)
	private String complemento;

	@Column(columnDefinition = "varchar(20)", length = 20)
	private String bairro;

	@Column(columnDefinition = "varchar(20)", length = 20)
	private String cidade;

	@Column(columnDefinition = "char(2)", length = 2)
	private String estado;

	@Column(columnDefinition = "numeric(8,0)")
	private double cep;

	@Column(name = "tipo_pessoa", columnDefinition = "char(1)", length = 1)
	private String tipoPessoa;

	@Column(name = "cnpj_cpf", columnDefinition = "numeric(14,0)")
	private double cnpjCpf;

	@Column(name = "data_nascimento", columnDefinition = "datetime")
	private Date dataNascimento;

	@Column(name = "nro_dependentes", columnDefinition = "tinyint")
	private short nroDependentes;

	@Column(name = "data_cadastro", columnDefinition = "datetime")
	private Date dataCadastro;

	@Column(columnDefinition = "varchar(20)", length = 20)
	private String profissao;

	@Column(columnDefinition = "varchar(15)", length = 15)
	private String nacionalidade;

	@Column(name = "tipo_identidade", columnDefinition = "char(5)", length = 5)
	private String tipoIdentidade;

	@Column(name = "nro_identidade", columnDefinition = "varchar(15)", length = 15)
	private String nroIdentidade;

	@Column(name = "orgao_emissor", columnDefinition = "varchar(10)", length = 10)
	private String orgaoEmissor;

	@Column(name = "data_emissao", columnDefinition = "datetime")
	private String dataEmissao;

	@Column(name = "estado_civil", columnDefinition = "tinyint")
	private short estadoCivil;

	@Column(columnDefinition = "varchar(2048)", length = 2048)
	private String observacao;
	/*
	 * private String qt_contacor] [smallint] NULL, private String qt_inquilino]
	 * [smallint] NULL, private String qt_fiador] [smallint] NULL, private
	 * String er_logradouro] [char](5) NULL, private String er_endereco]
	 * [varchar](35) NULL, private String er_complemento] [varchar](35) NULL,
	 * private String er_bairro] [varchar](20) NULL, private String er_cidade]
	 * [varchar](20) NULL, private String er_estado] [char](2) NULL, private
	 * String er_cep] [numeric](8, 0) NULL, private String cj_nome]
	 * [varchar](35) NULL, private String cj_cpf] [numeric](14, 0) NULL, private
	 * String cj_data_nascto] [datetime] NULL, private String cj_profissao]
	 * [varchar](20) NULL, private String cj_nacionalid] [varchar](15) NULL,
	 * private String cj_rg] [varchar](15) NULL, private String cj_org_emissor]
	 * [varchar](10) NULL, private String cj_data_emissao] [datetime] NULL,
	 * private String end_corresp] [char](1) NULL, private String imp_etiq]
	 * [char](1) NULL, private String qt_condomino] [smallint] NULL, private
	 * String er_aos_cuidados] [varchar](35) NULL, private String
	 * reside_exterior] [char](1) NULL, private String estrangeiro] [tinyint]
	 * NULL, private String nro_end] [varchar](10) NULL, private String
	 * er_nro_end] [varchar](10) NULL, private String dt_alteracao] [datetime]
	 * NULL, private String usuario] [int] NULL, private String data_alteracao]
	 * [datetime] NULL, private String religiao] [smallint] NULL, private String
	 * sexo] [char](1) NULL, private String dia_ani] [tinyint] NULL, private
	 * String mes_ani] [tinyint] NULL, private String ano_ani] [smallint] NULL,
	 * private String recolhe_inss] [tinyint] NULL, private String valor_inss]
	 * [numeric](12, 2) NULL, private String inscr_inss] [varchar](30) NULL,
	 * private String inscr_pis] [varchar](30) NULL, private String
	 * situacao_prof] [tinyint] NULL, private String dt_inscr_inss] [datetime]
	 * NULL, private String dt_inscr_pis] [datetime] NULL, private String
	 * cartorio_1] [varchar](50) NULL, private String cartorio_2] [varchar](50)
	 * NULL, private String qt_conselho] [smallint] NULL, private String
	 * exterior] [tinyint] NULL, private String pais] [varchar](40) NULL,
	 * private String cod_postal] [varchar](20) NULL, private String
	 * er_exterior] [tinyint] NULL, private String er_pais] [varchar](40) NULL,
	 * private String er_cod_postal] [varchar](20) NULL, private String enviado]
	 * [tinyint] NULL, private String cod_gosati] [int] NULL, private String
	 * cod_pais] [int] NULL, private String municipio] [int] NULL,
	 */
}
