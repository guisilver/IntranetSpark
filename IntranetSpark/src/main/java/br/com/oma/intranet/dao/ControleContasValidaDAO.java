package br.com.oma.intranet.dao;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import br.com.oma.intranet.dao.ControleContasDAO;
import br.com.oma.intranet.entidades.intra_controle_contas;
import br.com.oma.intranet.managedbeans.SessaoMB;


public class ControleContasValidaDAO {
	
	private ControleContasDAO ccDAO;

	public void list2(intra_controle_contas cc, SessaoMB sessaoMB) {

		this.ccDAO = new ControleContasDAO();


		DateTime dt_jan = new DateTime();
		//int ano_1 = dt_jan.getYear();
		int ano_1 = cc.getAno();
		int dias_1 = 0;
		
		DateTime m_jan = new DateTime(ano_1,01,01,0,0);
		int val_jan = Integer.valueOf(m_jan.dayOfMonth().withMaximumValue().getDayOfMonth());
		
		if(val_jan == 30 & cc.getVencimento() == 31){
			DateTime dt_1 = new DateTime(ano_1, 01, cc.getVencimento() - 1, 0, 0);
			dias_1 = Days.daysBetween(dt_jan.toDateTime(), dt_1).getDays();
		}else{
			DateTime dt_1 = new DateTime(ano_1, 01, cc.getVencimento(), 0, 0);
			dias_1 = Days.daysBetween(dt_jan.toDateTime(), dt_1).getDays();
		}

			if (dias_1 >= 6 & dias_1 <= 10) {
				if (cc.getMes1() == null) {
					String status = "status_jan";
					ccDAO.updateControleContas(cc.getCodigo(), 2, status, sessaoMB);
				} else if (cc.getMes1().equals("")) {
					String status = "status_jan";
					String mes_jan = "mes1";
					ccDAO.updateControleContasNull(cc.getCodigo(), 2, status,	mes_jan, sessaoMB);
				} else {
					String status = "status_jan";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_1 <= 5 & dias_1 >= 0) {
				if (cc.getMes1() == null) {
					String status = "status_jan";
					ccDAO.updateControleContas(cc.getCodigo(), 3, status, sessaoMB);
				} else if (cc.getMes1().equals("")) {
					String status = "status_jan";
					String mes_jan = "mes1";
					ccDAO.updateControleContasNull(cc.getCodigo(), 3, status,	mes_jan, sessaoMB);
				} else {
					String status = "status_jan";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_1 < 0) {
				if (cc.getMes1() == null) {
					String status = "status_jan";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else if (cc.getMes1().equals("")) {
					String status = "status_jan";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else {
					String status = "status_jan";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}else if(dias_1 > 10){
				String status = "status_jan";
				ccDAO.updateControleContas(cc.getCodigo(), 0, status, sessaoMB);
			}
			if(cc.getMes1() != null){
				if(!cc.getMes1().trim().isEmpty()){
					String status = "status_jan";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}

			DateTime dt_fev = new DateTime();
			DateTime dt_2 = null;
			//int ano_2 = dt_fev.getYear();
			int ano_2 = cc.getAno();

			DateTime teste = new DateTime(ano_2,02,01,0,0);
			int val = Integer.valueOf(teste.dayOfMonth().withMaximumValue().getDayOfMonth());
			
			if(val == 28 & cc.getVencimento() == 31){
				dt_2 = new DateTime(ano_2, 02, 28, 0, 0);
			}else if(val == 28 & cc.getVencimento() == 30){
				dt_2 = new DateTime(ano_2, 02, 28, 0, 0);
			}else if (val == 29 & cc.getVencimento() == 31) {
				dt_2 = new DateTime(ano_2, 02, 29, 0, 0);
			}else if(val == 29 & cc.getVencimento() == 30){
				dt_2 = new DateTime(ano_2, 02, 29, 0, 0);
			}else if(val == 28 & cc.getVencimento() == 28){
				dt_2 = new DateTime(ano_2, 02, 28, 0, 0);
			}else if(val == 28 & cc.getVencimento() == 29){
				dt_2 = new DateTime(ano_2, 02, 28, 0, 0);
			}else if(val == 29 & cc.getVencimento() == 29){
				dt_2 = new DateTime(ano_2, 02, 29, 0, 0);
			}else{
				dt_2 = new DateTime(ano_2, 02, cc.getVencimento(), 0, 0);
			}
			
			int dias_2 = Days.daysBetween(dt_fev.toDateTime(), dt_2).getDays();

			if (dias_2 >= 6 & dias_2 <= 10) {
				if (cc.getMes2() == null) {
					String status = "status_fev";
					ccDAO.updateControleContas(cc.getCodigo(), 2, status, sessaoMB);
				} else if (cc.getMes2().equals("")) {
					String status = "status_fev";
					String mes_fev = "mes2";
					ccDAO.updateControleContasNull(cc.getCodigo(), 2, status,	mes_fev, sessaoMB);
				} else {
					String status = "status_fev";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_2 <= 5 & dias_2 >= 0) {
				if (cc.getMes2() == null) {
					String status = "status_fev";
					ccDAO.updateControleContas(cc.getCodigo(), 3, status, sessaoMB);
				} else if (cc.getMes2().equals("")) {
					String status = "status_fev";
					String mes_fev = "mes2";
					ccDAO.updateControleContasNull(cc.getCodigo(), 3, status,	mes_fev, sessaoMB);
				} else {
					String status = "status_fev";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_2 < 0) {
				if (cc.getMes2() == null) {
					String status = "status_fev";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else if (cc.getMes2().equals("")) {
					String status = "status_fev";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else {
					String status = "status_fev";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}else if(dias_2 > 10){
				String status = "status_fev";
				ccDAO.updateControleContas(cc.getCodigo(), 0, status, sessaoMB);
			}
			if(cc.getMes2() != null){
				if(!cc.getMes2().trim().isEmpty()){
					String status = "status_fev";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}
			

			DateTime dt_mar = new DateTime();
			//int ano_3 = dt_mar.getYear();
			int ano_3 = cc.getAno();
			int dias_3 = 0;
			
			DateTime m_mar = new DateTime(ano_3,03,01,0,0);
			int val_mar = Integer.valueOf(m_mar.dayOfMonth().withMaximumValue().getDayOfMonth());
			
			if(val_mar == 30 & cc.getVencimento() == 31){
				DateTime dt_3 = new DateTime(ano_3, 03, cc.getVencimento() - 1, 0, 0);
				dias_3 = Days.daysBetween(dt_mar.toDateTime(), dt_3).getDays();
			}else{
				DateTime dt_3 = new DateTime(ano_3, 03, cc.getVencimento(), 0, 0);
				dias_3 = Days.daysBetween(dt_mar.toDateTime(), dt_3).getDays();
			}

			if (dias_3 >= 6 & dias_3 <= 10) {
				if (cc.getMes3() == null) {
					String status = "status_mar";
					ccDAO.updateControleContas(cc.getCodigo(), 2, status, sessaoMB);
				} else if (cc.getMes3().equals("")) {
					String status = "status_mar";
					String mes_mar = "mes3";
					ccDAO.updateControleContasNull(cc.getCodigo(), 2, status,	mes_mar, sessaoMB);
				} else {
					String status = "status_mar";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_3 <= 5 & dias_3 >= 0) {
				if (cc.getMes3() == null) {
					String status = "status_mar";
					ccDAO.updateControleContas(cc.getCodigo(), 3, status, sessaoMB);
				} else if (cc.getMes3().equals("")) {
					String status = "status_mar";
					String mes_mar = "mes3";
					ccDAO.updateControleContasNull(cc.getCodigo(), 3, status,	mes_mar, sessaoMB);
				} else {
					String status = "status_mar";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_3 < 0) {
				if (cc.getMes3() == null) {
					String status = "status_mar";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else if (cc.getMes3().equals("")) {
					String status = "status_mar";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else {
					String status = "status_mar";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}else if(dias_3 > 10){
				String status = "status_mar";
				ccDAO.updateControleContas(cc.getCodigo(), 0, status, sessaoMB);
			}
			if(cc.getMes3() != null){
				if(!cc.getMes3().trim().isEmpty()){
					String status = "status_mar";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}

			DateTime dt_abr = new DateTime();
			//int ano_4 = dt_abr.getYear();
			int ano_4 = cc.getAno();
			int dias_4 = 0;
			
			DateTime m_abr = new DateTime(ano_4,04,01,0,0);
			int val_abr = Integer.valueOf(m_abr.dayOfMonth().withMaximumValue().getDayOfMonth());
			
			if(val_abr == 30 & cc.getVencimento() == 31){
				DateTime dt_4 = new DateTime(ano_4, 04, cc.getVencimento() - 1, 0, 0);
				dias_4 = Days.daysBetween(dt_abr.toDateTime(), dt_4).getDays();
			}else{
				DateTime dt_4 = new DateTime(ano_4, 04, cc.getVencimento(), 0, 0);
				dias_4 = Days.daysBetween(dt_abr.toDateTime(), dt_4).getDays();
			}


			if (dias_4 >= 6 & dias_4 <= 10) {
				if (cc.getMes4() == null) {
					String status = "status_abr";
					ccDAO.updateControleContas(cc.getCodigo(), 2, status, sessaoMB);
				} else if (cc.getMes4().equals("")) {
					String status = "status_abr";
					String mes_abr = "mes4";
					ccDAO.updateControleContasNull(cc.getCodigo(), 2, status,	mes_abr, sessaoMB);
				} else {
					String status = "status_abr";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_4 <= 5 & dias_4 >= 0) {
				if (cc.getMes4() == null) {
					String status = "status_abr";
					ccDAO.updateControleContas(cc.getCodigo(), 3, status, sessaoMB);
				} else if (cc.getMes4().equals("")) {
					String status = "status_abr";
					String mes_abr = "mes4";
					ccDAO.updateControleContasNull(cc.getCodigo(), 3, status,	mes_abr, sessaoMB);
				} else {
					String status = "status_abr";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_4 < 0) {
				if (cc.getMes4() == null) {
					String status = "status_abr";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else if (cc.getMes4().equals("")) {
					String status = "status_abr";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else {
					String status = "status_abr";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}else if(dias_4 > 10){
				String status = "status_abr";
				ccDAO.updateControleContas(cc.getCodigo(), 0, status, sessaoMB);
			}
			if(cc.getMes4() != null){
				if(!cc.getMes4().trim().isEmpty()){
					String status = "status_abr";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}
			
			DateTime dt_mai = new DateTime();
			//int ano_5 = dt_abr.getYear();
			int ano_5 = cc.getAno();
			int dias_5 = 0;
			
			DateTime m_mai = new DateTime(ano_5,05,01,0,0);
			int val_mai = Integer.valueOf(m_mai.dayOfMonth().withMaximumValue().getDayOfMonth());
			
			if(val_mai == 30 & cc.getVencimento() == 31){
				DateTime dt_5 = new DateTime(ano_5, 05, cc.getVencimento() - 1, 0, 0);
				dias_5 = Days.daysBetween(dt_mai.toDateTime(), dt_5).getDays();
			}else{
				DateTime dt_5 = new DateTime(ano_5, 05, cc.getVencimento(), 0, 0);
				dias_5 = Days.daysBetween(dt_mai.toDateTime(), dt_5).getDays();
			}

			if (dias_5 >= 6 & dias_5 <= 10) {
				if (cc.getMes5() == null) {
					String status = "status_mai";
					ccDAO.updateControleContas(cc.getCodigo(), 2, status, sessaoMB);
				} else if (cc.getMes5().equals("")) {
					String status = "status_mai";
					String mes_mai = "mes5";
					ccDAO.updateControleContasNull(cc.getCodigo(), 2, status,	mes_mai, sessaoMB);
				} else {
					String status = "status_mai";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_5 <= 5 & dias_5 >= 0) {
				if (cc.getMes5() == null) {
					String status = "status_mai";
					ccDAO.updateControleContas(cc.getCodigo(), 3, status, sessaoMB);
				} else if (cc.getMes5().equals("")) {
					String status = "status_mai";
					String mes_mai = "mes5";
					ccDAO.updateControleContasNull(cc.getCodigo(), 3, status,	mes_mai, sessaoMB);
				} else {
					String status = "status_mai";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_5 < 0) {
				if (cc.getMes5() == null) {
					String status = "status_mai";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else if (cc.getMes5().equals("")) {
					String status = "status_mai";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else {
					String status = "status_mai";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}else if(dias_5 > 10){
				String status = "status_mai";
				ccDAO.updateControleContas(cc.getCodigo(), 0, status, sessaoMB);
			}
			if(cc.getMes5() != null){
				if(!cc.getMes5().trim().isEmpty()){
					String status = "status_mai";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}
			
			DateTime dt_jun = new DateTime();
			//int ano_6 = dt_jun.getYear();
			int ano_6 = cc.getAno();
			int dias_6 = 0;
			
			DateTime m_jun = new DateTime(ano_6,06,01,0,0);
			int val_jun = Integer.valueOf(m_jun.dayOfMonth().withMaximumValue().getDayOfMonth());
			
			if(val_jun == 30 & cc.getVencimento() == 31){
				DateTime dt_6 = new DateTime(ano_6, 06, cc.getVencimento() - 1, 0, 0);
				dias_6 = Days.daysBetween(dt_jun.toDateTime(), dt_6).getDays();
			}else{
				DateTime dt_6 = new DateTime(ano_6, 06, cc.getVencimento(), 0, 0);
				dias_6 = Days.daysBetween(dt_jun.toDateTime(), dt_6).getDays();
			}

			if (dias_6 >= 6 & dias_6 <= 10) {
				if (cc.getMes6() == null) {
					String status = "status_jun";
					ccDAO.updateControleContas(cc.getCodigo(), 2, status, sessaoMB);
				} else if (cc.getMes6().equals("")) {
					String status = "status_jun";
					String mes_jun = "mes6";
					ccDAO.updateControleContasNull(cc.getCodigo(), 2, status,	mes_jun, sessaoMB);
				} else {
					String status = "status_jun";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_6 <= 5 & dias_6 >= 0) {
				if (cc.getMes6() == null) {
					String status = "status_jun";
					ccDAO.updateControleContas(cc.getCodigo(), 3, status, sessaoMB);
				} else if (cc.getMes6().equals("")) {
					String status = "status_jun";
					String mes_jun = "mes6";
					ccDAO.updateControleContasNull(cc.getCodigo(), 3, status,	mes_jun, sessaoMB);
				} else {
					String status = "status_jun";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_6 < 0) {
				if (cc.getMes6() == null) {
					String status = "status_jun";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else if (cc.getMes6().equals("")) {
					String status = "status_jun";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else {
					String status = "status_jun";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}else if(dias_6 > 10){
				String status = "status_jun";
				ccDAO.updateControleContas(cc.getCodigo(), 0, status, sessaoMB);
			}
			if(cc.getMes6() != null){
				if(!cc.getMes6().trim().isEmpty()){
					String status = "status_jun";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}
			
			DateTime dt_jul = new DateTime();
			//int ano_7 = dt_jul.getYear();
			int ano_7 = cc.getAno();
			int dias_7 = 0;
			
			DateTime m_jul = new DateTime(ano_7,07,01,0,0);
			int val_jul = Integer.valueOf(m_jul.dayOfMonth().withMaximumValue().getDayOfMonth());
			
			if(val_jul == 30 & cc.getVencimento() == 31){
				DateTime dt_7 = new DateTime(ano_7, 07, cc.getVencimento() - 1, 0, 0);
				dias_7 = Days.daysBetween(dt_jul.toDateTime(), dt_7).getDays();
			}else{
				DateTime dt_7 = new DateTime(ano_7, 07, cc.getVencimento(), 0, 0);
				dias_7 = Days.daysBetween(dt_jul.toDateTime(), dt_7).getDays();
			}

			if (dias_7 >= 6 & dias_7 <= 10) {
				if (cc.getMes7() == null) {
					String status = "status_jul";
					ccDAO.updateControleContas(cc.getCodigo(), 2, status, sessaoMB);
				} else if (cc.getMes7().equals("")) {
					String status = "status_jul";
					String mes_jul = "mes7";
					ccDAO.updateControleContasNull(cc.getCodigo(), 2, status,	mes_jul, sessaoMB);
				} else {
					String status = "status_jul";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_7 <= 5 & dias_7 >= 0) {
				if (cc.getMes7() == null) {
					String status = "status_jul";
					ccDAO.updateControleContas(cc.getCodigo(), 3, status, sessaoMB);
				} else if (cc.getMes7().equals("")) {
					String status = "status_jul";
					String mes_jul = "mes7";
					ccDAO.updateControleContasNull(cc.getCodigo(), 3, status,	mes_jul, sessaoMB);
				} else {
					String status = "status_jul";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_7 < 0) {
				if (cc.getMes7() == null) {
					String status = "status_jul";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else if (cc.getMes7().equals("")) {
					String status = "status_jul";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else {
					String status = "status_jul";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}else if(dias_7 > 10){
				String status = "status_jul";
				ccDAO.updateControleContas(cc.getCodigo(), 0, status, sessaoMB);
			}
			if(cc.getMes7() != null){
				if(!cc.getMes7().trim().isEmpty()){
					String status = "status_jul";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}

			DateTime dt_ago = new DateTime();
			//int ano_8 = dt_ago.getYear();
			int ano_8 = cc.getAno();
			int dias_8 = 0;
			
			DateTime m_ago = new DateTime(ano_8,8,01,0,0);
			int val_ago = Integer.valueOf(m_ago.dayOfMonth().withMaximumValue().getDayOfMonth());
			
			if(val_ago == 30 & cc.getVencimento() == 31){
				DateTime dt_8 = new DateTime(ano_8, 8, cc.getVencimento() - 1, 0, 0);
				dias_8 = Days.daysBetween(dt_ago.toDateTime(), dt_8).getDays();
			}else{
				DateTime dt_8 = new DateTime(ano_8, 8, cc.getVencimento(), 0, 0);
				dias_8 = Days.daysBetween(dt_ago.toDateTime(), dt_8).getDays();
			}

			if (dias_8 >= 6 & dias_8 <= 10) {
				if (cc.getMes8() == null) {
					String status = "status_ago";
					ccDAO.updateControleContas(cc.getCodigo(), 2, status, sessaoMB);
				} else if (cc.getMes8().equals("")) {
					String status = "status_ago";
					String mes_ago = "mes8";
					ccDAO.updateControleContasNull(cc.getCodigo(), 2, status,	mes_ago, sessaoMB);
				} else {
					String status = "status_ago";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_8 <= 5 & dias_8 >= 0) {
				if (cc.getMes8() == null) {
					String status = "status_ago";
					ccDAO.updateControleContas(cc.getCodigo(), 3, status, sessaoMB);
				} else if (cc.getMes8().equals("")) {
					String status = "status_ago";
					String mes_ago = "mes8";
					ccDAO.updateControleContasNull(cc.getCodigo(), 3, status,	mes_ago, sessaoMB);
				} else {
					String status = "status_ago";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_8 < 0) {
				if (cc.getMes8() == null) {
					String status = "status_ago";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else if (cc.getMes8().equals("")) {
					String status = "status_ago";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else {
					String status = "status_ago";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}else if(dias_8 > 10){
				String status = "status_ago";
				ccDAO.updateControleContas(cc.getCodigo(), 0, status, sessaoMB);
			}
			if(cc.getMes8() != null){
				if(!cc.getMes8().trim().isEmpty()){
					String status = "status_ago";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}
			
			DateTime dt_set = new DateTime();
			//int ano_9 = dt_set.getYear();
			int ano_9 = cc.getAno();
			int dias_9 = 0;
			
			DateTime m_set = new DateTime(ano_9,9,01,0,0);
			int val_set = Integer.valueOf(m_set.dayOfMonth().withMaximumValue().getDayOfMonth());
			
			if(val_set == 30 & cc.getVencimento() == 31){
				DateTime dt_9 = new DateTime(ano_9, 9, cc.getVencimento() - 1, 0, 0);
				dias_9 = Days.daysBetween(dt_set.toDateTime(), dt_9).getDays();
			}else{
				DateTime dt_9 = new DateTime(ano_9, 9, cc.getVencimento(), 0, 0);
				dias_9 = Days.daysBetween(dt_ago.toDateTime(), dt_9).getDays();
			}

			if (dias_9 >= 6 & dias_9 <= 10) {
				if (cc.getMes9() == null) {
					String status = "status_set";
					ccDAO.updateControleContas(cc.getCodigo(), 2, status, sessaoMB);
				} else if (cc.getMes9().equals("")) {
					String status = "status_set";
					String mes_set = "mes9";
					ccDAO.updateControleContasNull(cc.getCodigo(), 2, status,	mes_set, sessaoMB);
				} else {
					String status = "status_set";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_9 <= 5 & dias_9 >= 0) {
				if (cc.getMes9() == null) {
					String status = "status_set";
					ccDAO.updateControleContas(cc.getCodigo(), 3, status, sessaoMB);
				} else if (cc.getMes9().equals("")) {
					String status = "status_set";
					String mes_set = "mes9";
					ccDAO.updateControleContasNull(cc.getCodigo(), 3, status,	mes_set, sessaoMB);
				} else {
					String status = "status_set";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_9 < 0) {
				if (cc.getMes9() == null) {
					String status = "status_set";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else if (cc.getMes9().equals("")) {
					String status = "status_set";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else {
					String status = "status_set";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}else if(dias_9 > 10){
				String status = "status_set";
				ccDAO.updateControleContas(cc.getCodigo(), 0, status, sessaoMB);
			}
			if(cc.getMes9() != null){
				if(!cc.getMes9().trim().isEmpty()){
					String status = "status_set";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}
			
			DateTime dt_out = new DateTime();
			
			//int ano_10 = dt_out.getYear();
			int ano_10 = cc.getAno();

			LocalDate ld = new LocalDate(ano_10,10,cc.getVencimento());
			//DateTime dt_10 = new DateTime(ano_10, 10, cc.getVencimento(), 0, 0);
			
			//int dias_10 = Days.daysBetween(dt_out.toDateTime(), dt_10).getDays();
			int dias_10 = 0;
			
			DateTime m_out = new DateTime(ano_10,10,01,0,0);
			int val_out = Integer.valueOf(m_out.dayOfMonth().withMaximumValue().getDayOfMonth());
			
			if(val_out == 30 & cc.getVencimento() == 31){
				//DateTime dt_10 = new DateTime(ano_10, 10, cc.getVencimento() - 1, 0, 0);
				dias_10 = Days.daysBetween(dt_out.toDateTime(), ld.toDateTimeAtCurrentTime()).getDays()-1;
			}else{
				//DateTime dt_9 = new DateTime(ano_9, 9, cc.getVencimento(), 0, 0);
				dias_10 = Days.daysBetween(dt_out.toDateTime(), ld.toDateTimeAtCurrentTime()).getDays();
			}
			

			if (dias_10 >= 6 & dias_10 <= 10) {
				if (cc.getMes10() == null) {
					String status = "status_out";
					ccDAO.updateControleContas(cc.getCodigo(), 2, status, sessaoMB);
				} else if (cc.getMes10().equals("")) {
					String status = "status_out";
					String mes_out = "mes10";
					ccDAO.updateControleContasNull(cc.getCodigo(), 2, status,	mes_out, sessaoMB);
				} else {
					String status = "status_out";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_10 <= 5 & dias_10 >= 0) {
				if (cc.getMes10() == null) {
					String status = "status_out";
					ccDAO.updateControleContas(cc.getCodigo(), 3, status, sessaoMB);
				} else if (cc.getMes10().equals("")) {
					String status = "status_out";
					String mes_out = "mes10";
					ccDAO.updateControleContasNull(cc.getCodigo(), 3, status,	mes_out, sessaoMB);
				} else {
					String status = "status_out";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_10 < 0) {
				if (cc.getMes10() == null) {
					String status = "status_out";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else if (cc.getMes10().equals("")) {
					String status = "status_out";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else {
					String status = "status_out";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}else if(dias_10 > 10){
				String status = "status_out";
				ccDAO.updateControleContas(cc.getCodigo(), 0, status, sessaoMB);
			}
			if(cc.getMes10() != null){
				if(!cc.getMes10().trim().isEmpty()){
					String status = "status_out";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}
			
			DateTime dt_nov = new DateTime();
			//int ano_11 = dt_nov.getYear();
			int ano_11 = cc.getAno();
			int dias_11 = 0;
			
			DateTime m_nov = new DateTime(ano_11,11,01,0,0);
			int val_nov = Integer.valueOf(m_nov.dayOfMonth().withMaximumValue().getDayOfMonth());
			
			if(val_nov == 30 & cc.getVencimento() == 31){
				DateTime dt_11 = new DateTime(ano_11, 11, cc.getVencimento() - 1, 0, 0);
				dias_11 = Days.daysBetween(dt_nov.toDateTime(), dt_11).getDays();
			}else{
				DateTime dt_11 = new DateTime(ano_11, 11, cc.getVencimento(), 0, 0);
				dias_11 = Days.daysBetween(dt_nov.toDateTime(), dt_11).getDays();
			}

			if (dias_11 >= 6 & dias_11 <= 10) {
				if (cc.getMes11() == null) {
					String status = "status_nov";
					ccDAO.updateControleContas(cc.getCodigo(), 2, status, sessaoMB);
				} else if (cc.getMes11().equals("")) {
					String status = "status_nov";
					String mes_nov = "mes11";
					ccDAO.updateControleContasNull(cc.getCodigo(), 2, status,	mes_nov, sessaoMB);
				} else {
					String status = "status_nov";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_11 <= 5 & dias_11 >= 0) {
				if (cc.getMes11() == null) {
					String status = "status_nov";
					ccDAO.updateControleContas(cc.getCodigo(), 3, status, sessaoMB);
				} else if (cc.getMes11().equals("")) {
					String status = "status_nov";
					String mes_nov = "mes11";
					ccDAO.updateControleContasNull(cc.getCodigo(), 3, status,	mes_nov, sessaoMB);
				} else {
					String status = "status_nov";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_11 < 0) {
				if (cc.getMes11() == null) {
					String status = "status_nov";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else if (cc.getMes11().equals("")) {
					String status = "status_nov";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else {
					String status = "status_nov";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}else if(dias_11 > 10){
				String status = "status_nov";
				ccDAO.updateControleContas(cc.getCodigo(), 0, status, sessaoMB);
			}
			if(cc.getMes11() != null){
				if(!cc.getMes11().trim().isEmpty()){
					String status = "status_nov";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}
			
			DateTime dt_dez = new DateTime();
			//int ano_12 = dt_dez.getYear();
			int ano_12 = cc.getAno();
			int dias_12 = 0;
			
			DateTime m_dez = new DateTime(ano_12,12,01,0,0);
			int val_dez = Integer.valueOf(m_dez.dayOfMonth().withMaximumValue().getDayOfMonth());
			
			if(val_dez == 30 & cc.getVencimento() == 31){
				DateTime dt_12 = new DateTime(ano_12, 12, cc.getVencimento() - 1, 0, 0);
				dias_12 = Days.daysBetween(dt_dez.toDateTime(), dt_12).getDays();
			}else{
				DateTime dt_12 = new DateTime(ano_12, 12, cc.getVencimento(), 0, 0);
				dias_12 = Days.daysBetween(dt_dez.toDateTime(), dt_12).getDays();
			}

			if (dias_12 >= 6 & dias_12 <= 10) {
				if (cc.getMes12() == null) {
					String status = "status_dez";
					ccDAO.updateControleContas(cc.getCodigo(), 2, status, sessaoMB);
				} else if (cc.getMes12().equals("")) {
					String status = "status_dez";
					String mes_dez = "mes12";
					ccDAO.updateControleContasNull(cc.getCodigo(), 2, status,	mes_dez, sessaoMB);
				} else {
					String status = "status_dez";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_12 <= 5 & dias_12 >= 0) {
				if (cc.getMes12() == null) {
					String status = "status_dez";
					ccDAO.updateControleContas(cc.getCodigo(), 3, status, sessaoMB);
				} else if (cc.getMes12().equals("")) {
					String status = "status_dez";
					String mes_dez = "mes12";
					ccDAO.updateControleContasNull(cc.getCodigo(), 3, status,	mes_dez, sessaoMB);
				} else {
					String status = "status_dez";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			} else if (dias_12 < 0) {
				if (cc.getMes12() == null) {
					String status = "status_dez";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else if (cc.getMes12().equals("")) {
					String status = "status_dez";
					ccDAO.updateControleContas(cc.getCodigo(), 4, status, sessaoMB);
				} else {
					String status = "status_dez";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}else if(dias_12 > 10){
				String status = "status_dez";
				ccDAO.updateControleContas(cc.getCodigo(), 0, status, sessaoMB);
			}
			if(cc.getMes12() != null){
				if(!cc.getMes12().trim().isEmpty()){
					String status = "status_dez";
					ccDAO.updateControleContas(cc.getCodigo(), 1, status, sessaoMB);
				}
			}
		}

}
