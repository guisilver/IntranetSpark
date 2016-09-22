package br.com.oma.intranet.util;

import org.primefaces.model.TreeNode;

import br.com.oma.intranet.entidades.FinanceiroImg;

public class ObjetoTeste {

	private FinanceiroImg p = new FinanceiroImg();
	private TreeNode tn;

	public FinanceiroImg getP() {
		return p;
	}

	public void setP(FinanceiroImg p) {
		this.p = p;
	}

	public TreeNode getTn() {
		return tn;
	}

	public void setTn(TreeNode tn) {
		this.tn = tn;
	}

}
