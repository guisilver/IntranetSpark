package br.com.oma.intranet.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.joda.time.DateTime;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PFNFE {

	private byte[] xml;
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document document;

	public PFNFE(byte[] xml) {
		try {
			this.xml = xml;
			this.factory = DocumentBuilderFactory.newInstance();
			this.builder = factory.newDocumentBuilder();
			this.document = builder.parse(new ByteArrayInputStream(xml));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public byte[] getXml() {
		return xml;
	}

	public void setXml(byte[] xml) {
		this.xml = xml;
	}

	public Date getDataVencimento() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			NodeList list = this.document.getElementsByTagName("dup");
			Element element = (Element) list.item(0);
			if (element != null) {
				return sdf.parse(capturaValorCampo("dVenc", element));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Date getDataEmissao() {
		try {
			NodeList list = this.document.getElementsByTagName("ide");
			Element element = (Element) list.item(0);
			if (element != null) {
				return new DateTime(capturaValorCampo("dhEmi", element)).toDate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Double getValorNF() {
		try {
			NodeList list = this.document.getElementsByTagName("ICMSTot");
			Element element = (Element) list.item(0);
			if (element != null) {
				return Double.valueOf(capturaValorCampo("vNF", element));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getCnpjFornecedor() {
		try {
			NodeList list = this.document.getElementsByTagName("emit");
			Element element = (Element) list.item(0);
			if (element != null) {
				return capturaValorCampo("CNPJ", element);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getRazaoSocial() {
		try {
			NodeList list = this.document.getElementsByTagName("emit");
			Element element = (Element) list.item(0);
			if (element != null) {
				return capturaValorCampo("xNome", element);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getNomeFantasia() {
		try {
			NodeList list = this.document.getElementsByTagName("emit");
			Element element = (Element) list.item(0);
			if (element != null) {
				return capturaValorCampo("xFant", element);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getNumeroNF() {
		try {
			NodeList list = this.document.getElementsByTagName("ide");
			Element element = (Element) list.item(0);
			if (element != null) {
				return capturaValorCampo("nNF", element);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String capturaValorCampo(String tagName, Element element) {
		try {
			NodeList list = element.getElementsByTagName(tagName);
			if (list != null && list.getLength() > 0) {
				for (int i = 0; i <= list.getLength(); i++) {
					NodeList subList = list.item(i).getChildNodes();
					if (subList != null && subList.getLength() > 0) {
						return subList.item(i).getNodeValue();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
