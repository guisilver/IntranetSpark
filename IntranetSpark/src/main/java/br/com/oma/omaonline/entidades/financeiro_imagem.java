package br.com.oma.omaonline.entidades;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "financeiro_imagem", schema = "omaonline.dbo")
public class financeiro_imagem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(columnDefinition = "int")
	private int codigo;

	@Column(name = "cod_lancto_oma", columnDefinition = "int")
	private int codLanctoOma;

	@Column(name = "cd_cnd", columnDefinition = "smallint")
	private short cdCnd;

	@Column(name = "img", columnDefinition = "varbinary(max)")
	private byte[] imagem;

	// glbCatalogo
	@Column(name = "id", columnDefinition = "numeric(14,0)")
	private double id;

	@Column(name = "id_tipoarquivo", columnDefinition = "smallint")
	private short id_tipoarquivo;

	@Column(name = "usuario_vinculo_arq", columnDefinition = "varchar(100)")
	private String suarioVinculoArq;

	@Column(name = "data_vinculo_arq", columnDefinition = "datetime")
	private Date dataVinculoArq;

	// glbCatalogo_Docto
	@Column(name = "nome_arquivo", columnDefinition = "varchar(200)")
	private String nome_arquivo;

	@Column(name = "identificacao_tipo_arq", columnDefinition = "char(5)")
	private String identificacaoTipoArq;

	@Column(name = "recebido_contabilidade", columnDefinition = "char(1)")
	private String recebidoContabilidade;

	@Column(name = "data_rec_contab", columnDefinition = "datetime")
	private Date dataRecebidoContabilidade;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodLanctoOma() {
		return codLanctoOma;
	}

	public void setCodLanctoOma(int codLanctoOma) {
		this.codLanctoOma = codLanctoOma;
	}

	public short getCdCnd() {
		return cdCnd;
	}

	public void setCdCnd(short cdCnd) {
		this.cdCnd = cdCnd;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public short getId_tipoarquivo() {
		return id_tipoarquivo;
	}

	public void setId_tipoarquivo(short id_tipoarquivo) {
		this.id_tipoarquivo = id_tipoarquivo;
	}

	public String getSuarioVinculoArq() {
		return suarioVinculoArq;
	}

	public void setSuarioVinculoArq(String suarioVinculoArq) {
		this.suarioVinculoArq = suarioVinculoArq;
	}

	public Date getDataVinculoArq() {
		return dataVinculoArq;
	}

	public void setDataVinculoArq(Date dataVinculoArq) {
		this.dataVinculoArq = dataVinculoArq;
	}

	public String getNome_arquivo() {
		return nome_arquivo;
	}

	public void setNome_arquivo(String nome_arquivo) {
		this.nome_arquivo = nome_arquivo;
	}

	public String getIdentificacaoTipoArq() {
		return identificacaoTipoArq;
	}

	public void setIdentificacaoTipoArq(String identificacaoTipoArq) {
		this.identificacaoTipoArq = identificacaoTipoArq;
	}

	public String getRecebidoContabilidade() {
		return recebidoContabilidade;
	}

	public void setRecebidoContabilidade(String recebidoContabilidade) {
		this.recebidoContabilidade = recebidoContabilidade;
	}

	public Date getDataRecebidoContabilidade() {
		return dataRecebidoContabilidade;
	}

	public void setDataRecebidoContabilidade(Date dataRecebidoContabilidade) {
		this.dataRecebidoContabilidade = dataRecebidoContabilidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdCnd;
		result = prime * result + codLanctoOma;
		result = prime * result + codigo;
		result = prime * result + ((dataRecebidoContabilidade == null) ? 0 : dataRecebidoContabilidade.hashCode());
		result = prime * result + ((dataVinculoArq == null) ? 0 : dataVinculoArq.hashCode());
		long temp;
		temp = Double.doubleToLongBits(id);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id_tipoarquivo;
		result = prime * result + ((identificacaoTipoArq == null) ? 0 : identificacaoTipoArq.hashCode());
		result = prime * result + Arrays.hashCode(imagem);
		result = prime * result + ((nome_arquivo == null) ? 0 : nome_arquivo.hashCode());
		result = prime * result + ((recebidoContabilidade == null) ? 0 : recebidoContabilidade.hashCode());
		result = prime * result + ((suarioVinculoArq == null) ? 0 : suarioVinculoArq.hashCode());
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
		financeiro_imagem other = (financeiro_imagem) obj;
		if (cdCnd != other.cdCnd)
			return false;
		if (codLanctoOma != other.codLanctoOma)
			return false;
		if (codigo != other.codigo)
			return false;
		if (dataRecebidoContabilidade == null) {
			if (other.dataRecebidoContabilidade != null)
				return false;
		} else if (!dataRecebidoContabilidade.equals(other.dataRecebidoContabilidade))
			return false;
		if (dataVinculoArq == null) {
			if (other.dataVinculoArq != null)
				return false;
		} else if (!dataVinculoArq.equals(other.dataVinculoArq))
			return false;
		if (Double.doubleToLongBits(id) != Double.doubleToLongBits(other.id))
			return false;
		if (id_tipoarquivo != other.id_tipoarquivo)
			return false;
		if (identificacaoTipoArq == null) {
			if (other.identificacaoTipoArq != null)
				return false;
		} else if (!identificacaoTipoArq.equals(other.identificacaoTipoArq))
			return false;
		if (!Arrays.equals(imagem, other.imagem))
			return false;
		if (nome_arquivo == null) {
			if (other.nome_arquivo != null)
				return false;
		} else if (!nome_arquivo.equals(other.nome_arquivo))
			return false;
		if (recebidoContabilidade == null) {
			if (other.recebidoContabilidade != null)
				return false;
		} else if (!recebidoContabilidade.equals(other.recebidoContabilidade))
			return false;
		if (suarioVinculoArq == null) {
			if (other.suarioVinculoArq != null)
				return false;
		} else if (!suarioVinculoArq.equals(other.suarioVinculoArq))
			return false;
		return true;
	}

}
