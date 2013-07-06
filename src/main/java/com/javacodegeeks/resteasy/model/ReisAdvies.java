package com.javacodegeeks.resteasy.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "reisadvies")
public class ReisAdvies {

	private String reisAdviesId;
	private String canonical;
	private String location;
	private String classification;
	private String id;
	private String type;
	private String dataUrl;
	private String title;
	private List<ContentBlock> content;
	
	@XmlElement
	public String getReisAdviesId() {
		return reisAdviesId;
	}
	public void setReisAdviesId(String reisAdviesId) {
		this.reisAdviesId = reisAdviesId;
	}
	
	@XmlElement
	public String getCanonical() {
		return canonical;
	}
	public void setCanonical(String canonical) {
		this.canonical = canonical;
	}
	
	@XmlElement
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@XmlElement
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	
	@XmlElement
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@XmlElement
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@XmlElement
	public String getDataUrl() {
		return dataUrl;
	}
	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}
	
	@XmlElement
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@XmlElement
	public List<ContentBlock> getContent() {
		return content;
	}
	public void setContent(List<ContentBlock> content) {
		this.content = content;
	}
}
