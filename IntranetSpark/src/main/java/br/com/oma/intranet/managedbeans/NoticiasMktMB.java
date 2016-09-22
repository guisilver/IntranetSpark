package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.xwork.math.NumberUtils;
import org.jsoup.Jsoup;

import br.com.oma.intranet.dao.NoticiasMktDAO;
import br.com.oma.intranet.entidades.intra_noticias;

@ViewScoped
@ManagedBean
public class NoticiasMktMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3781143421524514781L;
	private intra_noticias noticia = new intra_noticias();
	private intra_noticias noticiaSelecionada;
	private List<intra_noticias> listaNoticias, listaSlider, listaColunaEsquerda, listaColunaDireita;

	public intra_noticias getNoticia() {
		return noticia;
	}

	public void setNoticia(intra_noticias noticia) {
		this.noticia = noticia;
	}

	public intra_noticias getNoticiaSelecionada() {
		return noticiaSelecionada;
	}

	public void setNoticiaSelecionada(intra_noticias noticiaSelecionada) {
		this.noticiaSelecionada = noticiaSelecionada;
	}

	public List<intra_noticias> getListaNoticias() {
		try {
			if (this.listaNoticias == null) {
				NoticiasMktDAO dao = new NoticiasMktDAO();
				this.listaNoticias = dao.getNoticias();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaNoticias;
	}

	public void setListaNoticias(List<intra_noticias> listaNoticias) {
		this.listaNoticias = listaNoticias;
	}

	public List<intra_noticias> getListaSlider() {
		return listaSlider;
	}

	public void setListaSlider(List<intra_noticias> listaSlider) {
		this.listaSlider = listaSlider;
	}

	public List<intra_noticias> getListaColunaEsquerda() {
		try {
			if (this.listaColunaEsquerda == null) {
				NoticiasMktDAO dao = new NoticiasMktDAO();
				this.listaColunaEsquerda = dao.getListaColunaEsquerda();
				this.listarSlider();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaColunaEsquerda;
	}

	public void setListaColunaEsquerda(List<intra_noticias> listaColunaEsquerda) {
		this.listaColunaEsquerda = listaColunaEsquerda;
	}

	public List<intra_noticias> getListaColunaDireita() {
		try {
			if (this.listaColunaDireita == null) {
				NoticiasMktDAO dao = new NoticiasMktDAO();
				this.listaColunaDireita = dao.getListaColunaDireita();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaColunaDireita;
	}

	public void setListaColunaDireita(List<intra_noticias> listaColunaDireita) {
		this.listaColunaDireita = listaColunaDireita;
	}

	public void salvarNovaNoticia() {
		try {
			NoticiasMktDAO dao = new NoticiasMktDAO();
			this.noticia.setDataInserido(new Date());
			dao.salvarNovaNoticia2(this.noticia);
			this.noticia = new intra_noticias();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salvo com sucesso!", ""));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocorreu um erro ao salvar!", ""));
		}
	}

	public void salvarAlteracoesNoticia() {
		try {
			NoticiasMktDAO dao = new NoticiasMktDAO();
			dao.salvarAlteracoesNoticia(this.noticiaSelecionada);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salvo com sucesso!", ""));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocorreu um erro ao salvar!", ""));
		}
	}

	public void abreAlteraNoticia(intra_noticias noticia) {
		try {
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(caminho + "/noticias/alterar-noticia.xhtml?codigoNoticia=" + noticia.getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluirNoticia() {
		try {
			NoticiasMktDAO dao = new NoticiasMktDAO();
			dao.excluirNoticia(this.noticiaSelecionada);
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "/noticias.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocorreu um erro ao excluir!.", ""));
		}
	}

	public void excluirNoticiaTbl(intra_noticias noticia) {
		try {
			NoticiasMktDAO dao = new NoticiasMktDAO();
			dao.excluirNoticia(noticia);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluído com sucesso!", ""));
			this.listaNoticias = null;
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocorreu um erro ao excluir!.", ""));
		}
	}

	public String getEnderecoImagemCapa(String html) {
		try {
			if (html != null && html.contains("<img")) {
				StringBuffer str = new StringBuffer();
				String[] st = html.split("<img");
				if (st[1].contains("imageId")) {
					int index = st[1].indexOf("imageId=") + 8;
					boolean verifica = true;
					int i = index;
					while (verifica) {
						if (NumberUtils.isNumber(String.valueOf(st[1].charAt(i)))) {
							str.append(st[1].charAt(i));
							i++;
						} else {
							verifica = false;
						}
					}
					String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
					return caminho + "/noticias/ckeditor/getimage?imageId=" + str.toString();
				} else {
					if (st[1].contains("src=")) {
						int index = st[1].indexOf("src=") + 5;
						boolean verifica = true;
						int i = index;
						while (verifica) {
							if (st[1].charAt(i) == '"') {
								verifica = false;
							} else {
								str.append(st[1].charAt(i));
								i++;
							}
						}
					}
					return str.toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getPrimeiroParagrafo(String html) {
		try {
			if (html != null) {
				StringBuffer strBuffer = new StringBuffer();
				String nova = Jsoup.parse(html).text();
				String[] str2 = nova.split(" ");
				if (str2.length < 20) {
					for (int i = 0; i < str2.length; i++) {
						if (str2[i] != null) {
							strBuffer.append(str2[i] + " ");
						}
					}
				} else {
					for (int i = 0; i <= 20; i++) {
						if (str2[i] != null) {
							strBuffer.append(str2[i] + " ");
						}
					}
				}
				if (!strBuffer.toString().trim().isEmpty()) {
					strBuffer.append("...");
				}
				return strBuffer.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void abreNoticia() {
		try {
			Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			String codigoNoticia = params.get("codigo");
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(caminho + "/noticias/noticia.xhtml?codigoNoticia=" + codigoNoticia);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pesquisaNoticiaSelecionada() {
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
			String codigoNoticia = params.get("codigoNoticia");
			if (codigoNoticia != null) {
				NoticiasMktDAO dao = new NoticiasMktDAO();
				this.noticiaSelecionada = dao.pesquisaNoticiaPorCodigo(Integer.parseInt(codigoNoticia));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listarSlider() {
		this.listaSlider = new ArrayList<>();
		if (this.listaColunaEsquerda.size() < 2) {
			for (int i = 0; i <= this.listaColunaEsquerda.size() - 1; i++) {
				this.listaSlider.add(this.listaColunaEsquerda.get(i));
			}
		} else {
			for (int i = 0; i < 2; i++) {
				this.listaSlider.add(this.listaColunaEsquerda.get(i));
			}
		}
	}
}
