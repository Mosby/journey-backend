package com.javacodegeeks.resteasy.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "contentblock")
public class ContentBlock {
	
	private String paragraphTitle;
	private String paragraph;
	
	@XmlElement
	public String getParagraph() {
		return paragraph;
	}
	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}
	
	@XmlElement
	public String getParagraphTitle() {
		return paragraphTitle;
	}
	public void setParagraphTitle(String paragraphTitle) {
		this.paragraphTitle = paragraphTitle;
	}
	
	

}
