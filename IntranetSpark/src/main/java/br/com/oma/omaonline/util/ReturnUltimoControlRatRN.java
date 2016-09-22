package br.com.oma.omaonline.util;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;

import br.com.oma.omaonline.dao.FinanceiroDAO;
import br.com.oma.omaonline.entidades.controle_rateio;

@Singleton
@Lock(LockType.READ)
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@ManagedBean(name = "rurn")
public class ReturnUltimoControlRatRN {

	int count = 0;
	private FinanceiroDAO frdao;

	public void atualiza() {
		this.frdao = new FinanceiroDAO();
		new controle_rateio();
		count = frdao.returnUltimoControlRat();
	}

	@Lock(LockType.WRITE)
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
