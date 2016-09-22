package br.com.oma.intranet.managedbeans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.oma.intranet.dao.RecadastramentoDAO;
import br.com.oma.intranet.entidades.ilclient;
import br.com.oma.intranet.entidades.iltelcli;
import br.com.oma.intranet.util.EmailUtil;

@ViewScoped
@ManagedBean
public class RecadastramentoMB {

	private ilclient clienteSelecionado, clienteAntigo;
	private iltelcli telefoneCelular = new iltelcli(),
			telefoneResidencial = new iltelcli(),
			telefoneComercial = new iltelcli(), email = new iltelcli();
	private iltelcli telefoneCelularAntigo = new iltelcli(),
			telefoneResidencialAntigo = new iltelcli(),
			telefoneComercialAntigo = new iltelcli(),
			emailAntigo = new iltelcli();
	private List<ilclient> listaClientes, filtro;
	private boolean boletoEnvio, boletoEnvioAntigo;
	private int aprovados, reprovados;
	private String obs;

	private boolean aprovaNome = true, aprovaCpf = true, aprovaRg = true,
			aprovaProfissao = true, aprovaDataNascimento = true,
			aprovaReceberBoleto = true, aprovaCelular = true,
			aprovaTelResidencial = true, aprovaTelComercial = true,
			aprovaEmail = true;

	private boolean renderizaBotaoAprovar = false;

	public ilclient getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(ilclient clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public ilclient getClienteAntigo() {
		return clienteAntigo;
	}

	public void setClienteAntigo(ilclient clienteAntigo) {
		this.clienteAntigo = clienteAntigo;
	}

	public iltelcli getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(iltelcli telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public iltelcli getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(iltelcli telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public iltelcli getTelefoneComercial() {
		return telefoneComercial;
	}

	public void setTelefoneComercial(iltelcli telefoneComercial) {
		this.telefoneComercial = telefoneComercial;
	}

	public iltelcli getEmail() {
		return email;
	}

	public void setEmail(iltelcli email) {
		this.email = email;
	}

	public iltelcli getTelefoneCelularAntigo() {
		return telefoneCelularAntigo;
	}

	public void setTelefoneCelularAntigo(iltelcli telefoneCelularAntigo) {
		this.telefoneCelularAntigo = telefoneCelularAntigo;
	}

	public iltelcli getTelefoneResidencialAntigo() {
		return telefoneResidencialAntigo;
	}

	public void setTelefoneResidencialAntigo(iltelcli telefoneResidencialAntigo) {
		this.telefoneResidencialAntigo = telefoneResidencialAntigo;
	}

	public iltelcli getTelefoneComercialAntigo() {
		return telefoneComercialAntigo;
	}

	public void setTelefoneComercialAntigo(iltelcli telefoneComercialAntigo) {
		this.telefoneComercialAntigo = telefoneComercialAntigo;
	}

	public iltelcli getEmailAntigo() {
		return emailAntigo;
	}

	public void setEmailAntigo(iltelcli emailAntigo) {
		this.emailAntigo = emailAntigo;
	}

	public List<ilclient> getListaClientes() {
		if (this.listaClientes == null) {
			RecadastramentoDAO dao = new RecadastramentoDAO();
			this.listaClientes = dao.getListaClientes();
		}
		return listaClientes;
	}

	public void setListaClientes(List<ilclient> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<ilclient> getFiltro() {
		return filtro;
	}

	public void setFiltro(List<ilclient> filtro) {
		this.filtro = filtro;
	}

	public boolean isBoletoEnvio() {
		return boletoEnvio;
	}

	public void setBoletoEnvio(boolean boletoEnvio) {
		this.boletoEnvio = boletoEnvio;
	}

	public boolean isBoletoEnvioAntigo() {
		return boletoEnvioAntigo;
	}

	public void setBoletoEnvioAntigo(boolean boletoEnvioAntigo) {
		this.boletoEnvioAntigo = boletoEnvioAntigo;
	}

	public int getAprovados() {
		RecadastramentoDAO dao = new RecadastramentoDAO();
		this.aprovados = dao.getAprovados();
		this.reprovados = dao.getReprovados();
		return aprovados;
	}

	public void setAprovados(int aprovados) {
		this.aprovados = aprovados;
	}

	public int getReprovados() {
		return reprovados;
	}

	public void setReprovados(int reprovados) {
		this.reprovados = reprovados;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public boolean isAprovaNome() {
		return aprovaNome;
	}

	public void setAprovaNome(boolean aprovaNome) {
		this.aprovaNome = aprovaNome;
	}

	public boolean isAprovaCpf() {
		return aprovaCpf;
	}

	public void setAprovaCpf(boolean aprovaCpf) {
		this.aprovaCpf = aprovaCpf;
	}

	public boolean isAprovaRg() {
		return aprovaRg;
	}

	public void setAprovaRg(boolean aprovaRg) {
		this.aprovaRg = aprovaRg;
	}

	public boolean isAprovaProfissao() {
		return aprovaProfissao;
	}

	public void setAprovaProfissao(boolean aprovaProfissao) {
		this.aprovaProfissao = aprovaProfissao;
	}

	public boolean isAprovaDataNascimento() {
		return aprovaDataNascimento;
	}

	public void setAprovaDataNascimento(boolean aprovaDataNascimento) {
		this.aprovaDataNascimento = aprovaDataNascimento;
	}

	public boolean isAprovaReceberBoleto() {
		return aprovaReceberBoleto;
	}

	public void setAprovaReceberBoleto(boolean aprovaReceberBoleto) {
		this.aprovaReceberBoleto = aprovaReceberBoleto;
	}

	public boolean isAprovaCelular() {
		return aprovaCelular;
	}

	public void setAprovaCelular(boolean aprovaCelular) {
		this.aprovaCelular = aprovaCelular;
	}

	public boolean isAprovaTelResidencial() {
		return aprovaTelResidencial;
	}

	public void setAprovaTelResidencial(boolean aprovaTelResidencial) {
		this.aprovaTelResidencial = aprovaTelResidencial;
	}

	public boolean isAprovaTelComercial() {
		return aprovaTelComercial;
	}

	public void setAprovaTelComercial(boolean aprovaTelComercial) {
		this.aprovaTelComercial = aprovaTelComercial;
	}

	public boolean isAprovaEmail() {
		return aprovaEmail;
	}

	public void setAprovaEmail(boolean aprovaEmail) {
		this.aprovaEmail = aprovaEmail;
	}

	public boolean isRenderizaBotaoAprovar() {
		boolean renderiza = false;

		if (this.aprovaNome) {
			renderiza = true;
		}

		if (this.aprovaCpf) {
			renderiza = true;
		}

		if (this.aprovaRg) {
			renderiza = true;
		}

		if (this.aprovaProfissao) {
			renderiza = true;
		}

		if (this.aprovaDataNascimento) {
			renderiza = true;
		}

		if (this.aprovaReceberBoleto) {
			renderiza = true;
		}

		if (this.aprovaCelular) {
			renderiza = true;
		}

		if (this.aprovaTelResidencial) {
			renderiza = true;
		}

		if (this.aprovaTelComercial) {
			renderiza = true;
		}

		if (this.aprovaEmail) {
			renderiza = true;
		}
		this.renderizaBotaoAprovar = renderiza;
		return renderizaBotaoAprovar;
	}

	public void setRenderizaBotaoAprovar(boolean renderizaBotaoAprovar) {
		this.renderizaBotaoAprovar = renderizaBotaoAprovar;
	}

	public void recebeClienteSelecionado() {
		if (this.clienteSelecionado == null) {
			FacesContext instance = FacesContext.getCurrentInstance();
			ExternalContext externalContext = instance.getExternalContext();
			this.clienteSelecionado = (ilclient) externalContext.getFlash()
					.get("cleinteSelecionado");

			RecadastramentoDAO dao = new RecadastramentoDAO();
			List<iltelcli> listaContatos = dao
					.pesquisaContatoCliente(this.clienteSelecionado.getCodigo());
			if (listaContatos != null) {
				for (iltelcli aux : listaContatos) {
					switch (aux.getId().getTipo()) {
					case 1:
						this.telefoneComercial = aux;
						break;
					case 3:
						this.telefoneResidencial = aux;
						break;
					case 5:
						this.telefoneCelular = aux;
						break;
					case 6:
						this.email = aux;
						break;
					}
				}
			}
			if (this.email != null && this.email.getBoleto_envio() != null) {
				if (this.email.getBoleto_envio() > 0) {
					this.boletoEnvio = true;
				}
			}

			this.clienteAntigo = dao.pesquisaCliente(this.clienteSelecionado
					.getCodigo());

			List<iltelcli> listaContatosAntigo = dao
					.pesquisaContatoClienteAntigo(this.clienteAntigo
							.getCodigo());
			if (listaContatosAntigo != null) {
				for (iltelcli aux : listaContatosAntigo) {
					switch (aux.getId().getTipo()) {
					case 1:
						this.telefoneComercialAntigo = aux;
						break;
					case 3:
						this.telefoneResidencialAntigo = aux;
						break;
					case 5:
						this.telefoneCelularAntigo = aux;
						break;
					case 6:
						this.emailAntigo = aux;
						break;
					}
				}
			}

			if (this.emailAntigo != null
					&& this.emailAntigo.getBoleto_envio() != null) {
				if (this.emailAntigo.getBoleto_envio() > 0) {
					this.boletoEnvioAntigo = true;
				}
			}
		}
	}

	public String abrirAprovacaoCadastro() {
		if (this.clienteSelecionado != null) {
			FacesContext instance = FacesContext.getCurrentInstance();
			ExternalContext externalContext = instance.getExternalContext();
			externalContext.getFlash().put("cleinteSelecionado",
					this.clienteSelecionado);
			externalContext.getFlash().setKeepMessages(true);
			return "/recadastramento/cadastro-selecionado.xhtml";
		} else {
			return null;
		}
	}

	public String converteData(Date data) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (data != null) {
			return df.format(data);
		} else {
			return "";
		}
	}

	public boolean renderizarAlerta(String obj1, String obj2) {
		if ((obj1 == null && obj2.trim().isEmpty())
				|| (obj1.trim().isEmpty() && obj2 == null)) {
			return false;
		} else if (obj1.equals(obj2)) {
			return false;
		} else {
			return true;
		}
	}

	public String aprovar() {
		try {

			if (!this.aprovaNome) {
				this.clienteSelecionado.setNome(this.clienteAntigo.getNome());
			}

			if (!this.aprovaCpf) {
				this.clienteSelecionado.setCnpj_cpf(this.clienteAntigo
						.getCnpj_cpf());
			}

			if (!this.aprovaRg) {
				this.clienteSelecionado.setNro_identidade(this.clienteAntigo
						.getNro_identidade());
			}

			if (!this.aprovaProfissao) {
				this.clienteSelecionado.setNro_identidade(this.clienteAntigo
						.getNro_identidade());
			}

			if (!this.aprovaDataNascimento) {
				this.clienteSelecionado.setData_nascimento(this.clienteAntigo
						.getData_nascimento());
			}

			if (!this.aprovaReceberBoleto) {
				this.email.setBoleto_envio(this.emailAntigo.getBoleto_envio());
			}

			if (!this.aprovaCelular) {
				this.telefoneCelular.setTel_email(this.telefoneCelular
						.getTel_email());
			}

			if (!this.aprovaTelResidencial) {
				this.telefoneResidencial.setTel_email(this.telefoneResidencial
						.getTel_email());
			}

			if (!this.aprovaTelComercial) {
				this.telefoneComercial.setTel_email(this.telefoneComercial
						.getTel_email());
			}

			if (!this.aprovaReceberBoleto) {
				this.email.setTel_email(this.emailAntigo.getTel_email());
			}

			RecadastramentoDAO dao = new RecadastramentoDAO();
			dao.aprovar(this.clienteSelecionado, this.telefoneCelular,
					this.telefoneComercial, this.telefoneResidencial,
					this.email);
			return "/recadastramento/aprovado-sucesso.xhtml?faces-redirect=true;";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String reprovar() {
		try {
			RecadastramentoDAO dao = new RecadastramentoDAO();
			dao.reprovar(this.clienteSelecionado, this.telefoneCelular,
					this.telefoneComercial, this.telefoneResidencial,
					this.email);

			EmailUtil email = new EmailUtil();
			if (this.email != null && this.email.getId() != null
					&& this.email.getTel_email() != null
					&& !this.email.getTel_email().trim().isEmpty()) {
				email.enviaRecadastramento(this.email.getTel_email(),
						this.clienteSelecionado.getNome(), this.obs);
			}

			return "/recadastramento/reprovado-sucesso.xhtml?faces-redirect=true;";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
