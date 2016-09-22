package br.com.oma.intranet.managedbeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import br.com.oma.intranet.dao.AudioDAO;
import br.com.oma.intranet.entidades.Audio;

@ViewScoped
@ManagedBean
public class AudioMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8519501029656616226L;
	private Audio audio = new Audio();
	private Audio audioSelecionado;
	private List<Audio> audios;

	public Audio getAudio() {
		return audio;
	}

	public void setAudio(Audio audio) {
		this.audio = audio;
	}

	public Audio getAudioSelecionado() {
		return audioSelecionado;
	}

	public void setAudioSelecionado(Audio audioSelecionado) {
		this.audioSelecionado = audioSelecionado;
	}

	public List<Audio> getAudios() {
		if (this.audios == null) {
			AudioDAO dao = new AudioDAO();
			this.audios = dao.getAudios();
		}
		return audios;
	}

	public void setAudios(List<Audio> audios) {
		this.audios = audios;
	}

	public void uploadAudio(FileUploadEvent event) {
		try {
			if (event.getFile().getContents() != null) {
				this.audio.setAudio(event.getFile().getContents());
				this.audio.setNomeArquivo(event.getFile().getFileName());
				AudioDAO dao = new AudioDAO();
				dao.salvarAudio(this.audio);
				this.audios = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluirAudio() {
		try {
			AudioDAO dao = new AudioDAO();
			dao.excluirAudio(this.audioSelecionado);
			this.audioSelecionado = null;
			this.audios = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluído com sucesso!", ""));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), ""));
		}
	}

	public void selecionaAudio() {
		try {
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String arquivo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "audio"
					+ File.separator + this.audioSelecionado.getNomeArquivo();
			FileOutputStream outPut;
			outPut = new FileOutputStream(new File(arquivo));
			outPut.write(this.audioSelecionado.getAudio(), 0, this.audioSelecionado.getAudio().length);
			outPut.close();
			RequestContext.getCurrentInstance().execute("PF('dlgPlay').show();");
		} catch (Exception e) {
			throw new FacesException("Error in writing file.");
		}
	}

}
