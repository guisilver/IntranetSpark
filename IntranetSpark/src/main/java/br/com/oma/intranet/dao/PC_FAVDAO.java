package br.com.oma.intranet.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.oma.intranet.entidades.intra_favorecido;
import br.com.oma.intranet.entidades.intra_plano_contas;
import br.com.oma.intranet.util.JPAUtil;

public class PC_FAVDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4817078768350591319L;
	private EntityManager manager;

	public PC_FAVDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<intra_favorecido> listarFavorecido() {
		List<intra_favorecido> favorecido = new ArrayList<>();
		List<Object[]> lista = this.manager
				.createNativeQuery("select codigo, favorecido, cnpj_cpf, banco, agencia, "
						+ "CONVERT(varchar(20),conta_corrente) as conta, CONVERT(varchar(10), dac_conta) as dig_conta, conta_poupanca  from sigadm.dbo.cpfavor")
				.getResultList();
		for (Object[] obj : lista) {
			intra_favorecido fav = new intra_favorecido();
			fav.setCodigo(Integer.valueOf(obj[0].toString()));
			if (obj[1] != null) {
				fav.setFavorecido(obj[1].toString());
			}
			if (obj[2] != null) {
				fav.setCnpjCpf(obj[2].toString());
			}
			if (obj[3] != null) {
				fav.setBanco(obj[3].toString());
			}
			if (obj[4] != null) {
				fav.setAgencia(obj[4].toString());
			}
			if (obj[5] != null) {
				fav.setConta(String.valueOf(obj[5]));
			}
			if (obj[6] != null) {
				fav.setDigConta(obj[6].toString());
			}
			if (obj[7] != null) {
				fav.setTipoConta(obj[7].toString());
			}
			favorecido.add(fav);
		}
		return favorecido;
	}

	@SuppressWarnings("unchecked")
	public List<intra_plano_contas> listarPlanoContas() {
		List<intra_plano_contas> plano = new ArrayList<intra_plano_contas>();
		List<Object[]> lista = this.manager
				.createNativeQuery("select nome, nome_realizado, cod_reduzido, dfrecnum from "
						+ "sigadm.dbo.cndplano where conta between 1400 and 1413 and tipo = 'D' order by conta")
				.getResultList();
		for (Object[] obj : lista) {
			intra_plano_contas p = new intra_plano_contas();
			p.setNome(obj[0].toString());
			p.setNomeRealizado(obj[1].toString());
			p.setCodReduzido(obj[2].toString());
			p.setCodigo(Integer.valueOf(obj[3].toString()));
			plano.add(p);
		}
		return plano;
	}
}
