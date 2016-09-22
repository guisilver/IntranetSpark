package br.com.oma.intranet.util;

import java.io.Serializable;

import br.com.oma.intranet.interfaces.CalculaValorRateioUnidade;
import br.com.oma.sigadm.entidades.intra_cndunida;

public class CalculaValorUnidade implements CalculaValorRateioUnidade, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public double retornaValor(intra_cndunida predio, intra_cndunida unida, char tipo, double valor) {
		double retornaValor = 0;
		double retornaFracaoPredio = 0;

		if (tipo == 'A') {
			retornaFracaoPredio = predio.getFraca_extra();
			retornaValor = (valor * unida.getFracao_unidade()) / retornaFracaoPredio;
		} else if (tipo == 'B') {
			retornaFracaoPredio = predio.getFraca_extra() + predio.getFracao_extra3();
			retornaValor = (valor * (unida.getFracao_unidade() + unida.getFracao_garagem())) / retornaFracaoPredio;
		} else if (tipo == 'C') {
			retornaFracaoPredio = predio.getFraca_extra() + predio.getFracao_extra2();
			retornaValor = (valor * unida.getFracao_unidade()) / retornaFracaoPredio;
		} else if (tipo == 'D') {
			retornaFracaoPredio = predio.getFraca_extra() + predio.getFracao_extra3() + predio.getFracao_extra2();
			retornaValor = (valor * (unida.getFracao_unidade() + unida.getFracao_garagem())) / retornaFracaoPredio;
		} else if (tipo == 'E') {
			retornaFracaoPredio = predio.getFraca_extra() + predio.getFracao_extra4();
			retornaValor = (valor * unida.getFracao_unidade()) / retornaFracaoPredio;
		} else if (tipo == 'F') {
			retornaFracaoPredio = predio.getFraca_extra() + predio.getFracao_extra3() + predio.getFracao_extra4();
			retornaValor = (valor * (unida.getFracao_unidade() + unida.getFracao_garagem())) / retornaFracaoPredio;
		} else if (tipo == 'G') {
			retornaFracaoPredio = predio.getFraca_extra() + predio.getFracao_extra2() + predio.getFracao_extra4();
			retornaValor = (valor * unida.getFracao_unidade()) / retornaFracaoPredio;
		} else if (tipo == 'H') {
			retornaFracaoPredio = predio.getFraca_extra() + predio.getFracao_extra2() + predio.getFracao_extra3()
					+ predio.getFracao_extra4();
			retornaValor = (valor * (unida.getFracao_unidade() + unida.getFracao_garagem())) / retornaFracaoPredio;
		} else if (tipo == 'J') {
			retornaFracaoPredio = predio.getFracao_extra2() + predio.getFracao_extra3();
			retornaValor = (valor * (unida.getFracao_unidade() + unida.getFracao_garagem())) / retornaFracaoPredio;
		} else if (tipo == 'K') {
			retornaFracaoPredio = predio.getFracao_extra2() + predio.getFracao_extra4();
			retornaValor = (valor * unida.getFracao_unidade()) / retornaFracaoPredio;
		} else if (tipo == 'L') {
			retornaFracaoPredio = predio.getFracao_extra2() + predio.getFracao_extra3() + predio.getFracao_extra4();
			retornaValor = (valor * (unida.getFracao_unidade() + unida.getFracao_garagem())) / retornaFracaoPredio;
		} else if (tipo == 'M') {
			retornaFracaoPredio = predio.getFracao_extra4();
			retornaValor = (valor * unida.getFracao_unidade()) / retornaFracaoPredio;
		} else if (tipo == 'N') {
			retornaFracaoPredio = predio.getFracao_extra4() + predio.getFracao_extra3();
			retornaValor = (valor * (unida.getFracao_unidade() + unida.getFracao_garagem())) / retornaFracaoPredio;
		} else if (tipo == 'P') {
			retornaFracaoPredio = predio.getFracao_extra3();
			retornaValor = (valor * unida.getFracao_garagem() / retornaFracaoPredio);
		} else if (tipo == 'Q') {
			retornaFracaoPredio = predio.getFracao_extra2();
			retornaValor = (valor * unida.getFracao_unidade()) / retornaFracaoPredio;
		}
		return retornaValor;
	}

}
