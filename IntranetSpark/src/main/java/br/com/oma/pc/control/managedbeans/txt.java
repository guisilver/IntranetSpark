package br.com.oma.pc.control.managedbeans;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "exportController")
public class txt {

	private StreamedContent exportFile;

	public StreamedContent getExportFile() {
		return exportFile;
	}

	public void setExportFile(StreamedContent exportFile) {
		this.exportFile = exportFile;
	}

	public void exportTextFile(ActionEvent actionEvent) {
		String exportText = "";
		String newline = "\r\n";// System.getProperty( "line.separator" );

		exportText = exportText + "my title: This is a solution" + newline;
		exportText = exportText + "Content bla bla " + newline;
		InputStream stream = new ByteArrayInputStream(exportText.getBytes());
		this.exportFile = new DefaultStreamedContent(stream, "application/txt", "text_content.txt");

	}
	
/*	
	<p:commandButton ajax="false" value="Export Selected News" id="exportNews" image="ui-icon ui-icon-newwin"
            actionListener="#{exportController.exportTextFile}"  title="Save the selected news as text file">
            <p:fileDownload value="#{exportController.exportFile}" />
</p:commandButton>*/
	
}
