package br.com.oma.intranet.interfaces;

import br.com.oma.sigadm.entidades.intra_cndunida;

public interface CalculaValorRateioUnidade {
	double retornaValor(intra_cndunida predio, intra_cndunida unida, char tipo, double valor);
}
