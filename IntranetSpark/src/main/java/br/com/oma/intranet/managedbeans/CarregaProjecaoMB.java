package br.com.oma.intranet.managedbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import br.com.oma.intranet.dao.ProjetarPrevisaoDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.PrevisaoCapaAUX;

public class CarregaProjecaoMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2636118310565374381L;
	// OBJETOS
	private ProjetarPrevisaoDAO ppDAO;
	/*private intra_projetar_orcamento ipoBEAN = new intra_projetar_orcamento();
	
	//ATRIBUTOS
	//private double valor = 0.0;

	// GET SET
	public intra_projetar_orcamento getIpoBEAN() {
		return ipoBEAN;
	}

	public void setIpoBEAN(intra_projetar_orcamento ipoBEAN) {
		this.ipoBEAN = ipoBEAN;
	}*/

	public List<PrevisaoCapaAUX> carregarProjecao(int media,	intra_condominios icBean) {
		this.ppDAO = new ProjetarPrevisaoDAO();
		
		int ano = Integer.valueOf(String.valueOf(DateTime.now().plusMonths(-media).getYear()));
		int anoAtual = DateTime.now().getYear();
		List<PrevisaoCapaAUX> listaCapa = new ArrayList<>();
		
		List<Object[]> lista = null;
		if (icBean.getCodigo() > 0) {
			if (media > 0) {
				if (media == 1) {
					int mesInicial = Integer.valueOf(String.valueOf(DateTime.now().plusMonths(-media).getMonthOfYear()));
					DateTime primeiroDia = new DateTime(ano, mesInicial, 1, 0, 0);
					Date dataInicial = primeiroDia.toDate();
					int mesFinal = DateTime.now().getMonthOfYear();
					DateTime primeiroDia2 = new DateTime(anoAtual, mesFinal, 1, 0, 0);
					DateTime UltimoDiadoMes2 = primeiroDia2.plusDays(-1);
					Date dataFinal = UltimoDiadoMes2.toDate();
					lista = this.ppDAO.listarUltimoMes(icBean.getCodigo(),dataInicial, dataFinal);
				} else if (media == 3) {
					int mesInicial = Integer.valueOf(String.valueOf(DateTime.now().plusMonths(-media).getMonthOfYear()));
					DateTime primeiroDia = new DateTime(ano, mesInicial, 1, 0, 0);
					Date dataInicial = primeiroDia.toDate();
					int mesFinal = DateTime.now().getMonthOfYear();
					DateTime primeiroDia2 = new DateTime(anoAtual, mesFinal, 1, 0, 0);
					DateTime UltimoDiadoMes2 = primeiroDia2.plusDays(-1);
					Date dataFinal = UltimoDiadoMes2.toDate();
					lista = this.ppDAO.listarUltimo3Meses(icBean.getCodigo(), dataInicial, dataFinal);
				} else if (media == 6) {
					int mesInicial = Integer.valueOf(String.valueOf(DateTime.now().plusMonths(-media).getMonthOfYear()));
					DateTime primeiroDia = new DateTime(ano, mesInicial, 1, 0, 0);
					Date dataInicial = primeiroDia.toDate();
					int mesFinal = DateTime.now().getMonthOfYear();
					DateTime primeiroDia2 = new DateTime(anoAtual, mesFinal, 1, 0, 0);
					DateTime UltimoDiadoMes2 = primeiroDia2.plusDays(-1);
					Date dataFinal = UltimoDiadoMes2.toDate();
					lista = this.ppDAO.listarUltimo6Meses(icBean.getCodigo(), dataInicial, dataFinal);
				}else if (media == 12) {
					int mesInicial = Integer.valueOf(String.valueOf(DateTime.now().plusMonths(-media).getMonthOfYear()));
					DateTime primeiroDia = new DateTime(ano, mesInicial, 1, 0, 0);
					Date dataInicial = primeiroDia.toDate();
					int mesFinal = DateTime.now().getMonthOfYear();
					DateTime primeiroDia2 = new DateTime(anoAtual, mesFinal, 1, 0, 0);
					DateTime UltimoDiadoMes2 = primeiroDia2.plusDays(-1);
					Date dataFinal = UltimoDiadoMes2.toDate();
					lista = this.ppDAO.listarUltimo12Meses(icBean.getCodigo(), dataInicial, dataFinal);
				}
				if (lista != null) {
					for (Object[] obj : lista) {
						PrevisaoCapaAUX capa = new PrevisaoCapaAUX();

						if (obj[2] != null) {
							if (obj[2].toString().trim().equals("1400")) {
								capa.setCapa("FUNCIONARIOS");
								capa.setDespesa(obj[0].toString());
								capa.setValor(Double.valueOf(obj[1].toString()));
								capa.setConta(Integer.valueOf(obj[2].toString()));
								capa.setCodigoReduzido(Integer.valueOf(obj[3].toString()));
								listaCapa.add(capa);
							}
						}
						if (obj[2] != null) {
							if (obj[2].toString().trim().equals("1401")) {
								capa.setCapa("FUNCIONARIOS");
								capa.setDespesa(obj[0].toString());
								capa.setValor(Double.valueOf(obj[1].toString()));
								capa.setConta(Integer.valueOf(obj[2].toString()));
								capa.setCodigoReduzido(Integer.valueOf(obj[3].toString()));
								listaCapa.add(capa);
							}
						}
						if (obj[2] != null) {
							if (obj[2].toString().trim().equals("1402")) {
								capa.setCapa("FUNCIONARIOS");
								capa.setDespesa(obj[0].toString());
								capa.setValor(Double.valueOf(obj[1].toString()));
								capa.setConta(Integer.valueOf(obj[2].toString()));
								capa.setCodigoReduzido(Integer.valueOf(obj[3].toString()));
								listaCapa.add(capa);
							}
						}
						if (obj[2] != null) {
							if (obj[2].toString().trim().equals("1403")) {
								capa.setCapa("FUNCIONARIOS");
								capa.setDespesa(obj[0].toString());
								capa.setValor(Double.valueOf(obj[1].toString()));
								capa.setConta(Integer.valueOf(obj[2].toString()));
								capa.setCodigoReduzido(Integer.valueOf(obj[3].toString()));
								listaCapa.add(capa);
							}
						}
						if (obj[2] != null) {
							if (obj[2].toString().trim().equals("1404")) {
								capa.setCapa("FUNCIONARIOS");
								capa.setDespesa(obj[0].toString());
								capa.setValor(Double.valueOf(obj[1].toString()));
								capa.setConta(Integer.valueOf(obj[2].toString()));
								capa.setCodigoReduzido(Integer.valueOf(obj[3].toString()));
								listaCapa.add(capa);
							}
						}
						
						if (obj[2] != null) {
							if (obj[2].toString().trim().equals("1405")) {
								capa.setCapa("TERCEIRIZAÇÃO");
								capa.setDespesa(obj[0].toString());
								capa.setValor(Double.valueOf(obj[1].toString()));
								capa.setConta(Integer.valueOf(obj[2].toString()));
								capa.setCodigoReduzido(Integer.valueOf(obj[3].toString()));
								listaCapa.add(capa);
							}
						}
						if (obj[2] != null) {
							if (obj[2].toString().trim().equals("1406")) {
								capa.setCapa("MANUTENÇÃO");
								capa.setDespesa(obj[0].toString());
								capa.setValor(Double.valueOf(obj[1].toString()));
								capa.setConta(Integer.valueOf(obj[2].toString()));
								capa.setCodigoReduzido(Integer.valueOf(obj[3].toString()));
								listaCapa.add(capa);
							}
						}
						if (obj[2] != null) {
							if (obj[2].toString().trim().equals("1407")) {
								capa.setCapa("CONSERVAÇÃO PREDIAL");
								capa.setDespesa(obj[0].toString());
								capa.setValor(Double.valueOf(obj[1].toString()));
								capa.setConta(Integer.valueOf(obj[2].toString()));
								capa.setCodigoReduzido(Integer.valueOf(obj[3].toString()));
								listaCapa.add(capa);
							}
						}
						if (obj[2] != null) {
							if (obj[2].toString().trim().equals("1408")) {
								capa.setCapa("MATERIAL DE CONSUMO");
								capa.setDespesa(obj[0].toString());
								capa.setValor(Double.valueOf(obj[1].toString()));
								capa.setConta(Integer.valueOf(obj[2].toString()));
								capa.setCodigoReduzido(Integer.valueOf(obj[3].toString()));
								listaCapa.add(capa);
							}
						}
						if (obj[2] != null) {
							if (obj[2].toString().trim().equals("1409")) {
								capa.setCapa("CONSUMO");
								capa.setDespesa(obj[0].toString());
								capa.setValor(Double.valueOf(obj[1].toString()));
								capa.setConta(Integer.valueOf(obj[2].toString()));
								capa.setCodigoReduzido(Integer.valueOf(obj[3].toString()));
								listaCapa.add(capa);
							}
						}
						if (obj[2] != null) {
							if (obj[2].toString().trim().equals("1410")) {
								capa.setCapa("ADMINISTRATIVO");
								capa.setDespesa(obj[0].toString());
								capa.setValor(Double.valueOf(obj[1].toString()));
								capa.setConta(Integer.valueOf(obj[2].toString()));
								capa.setCodigoReduzido(Integer.valueOf(obj[3].toString()));
								listaCapa.add(capa);
							}
						}
						if (obj[2] != null) {
							if (obj[2].toString().trim().equals("1411")) {
								capa.setCapa("DESPESAS OPERACIONAIS");
								capa.setDespesa(obj[0].toString());
								capa.setValor(Double.valueOf(obj[1].toString()));
								capa.setConta(Integer.valueOf(obj[2].toString()));
								capa.setCodigoReduzido(Integer.valueOf(obj[3].toString()));
								listaCapa.add(capa);
							}
						}

					}

				}
			}
		}
		return listaCapa;
	}

}
