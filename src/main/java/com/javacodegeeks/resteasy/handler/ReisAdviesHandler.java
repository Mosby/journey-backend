package com.javacodegeeks.resteasy.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.javacodegeeks.resteasy.model.ContentBlock;
import com.javacodegeeks.resteasy.model.ReisAdvies;

public class ReisAdviesHandler extends DefaultHandler {
	
	private final Logger LOG = LoggerFactory.getLogger(ReisAdviesHandler.class);
	
	private Map<String, ReisAdvies> result;
	private List<ContentBlock> content;
	
	private StringBuilder buffer;
	private ReisAdvies reisAdvies;
	private ContentBlock contentBlock;
	
	private boolean inDocument;
	private boolean inId;
	private boolean inType;
	private boolean inCanonical;
	private boolean inDataurl;
	private boolean inTitle;
	private boolean inLocation;
	private boolean inClassification;
	private boolean inParagraphTitle;
	private boolean inParagraph;
	private boolean inContentBlock;
	private boolean inContent;
	
	public Map<String, ReisAdvies> getReisAdviezen() {
		LOG.info("size of result: " + result.size());
		return result;
	}
	
	@Override
    public void startDocument() throws SAXException {
		result = new HashMap<String, ReisAdvies>();
		LOG.info("parsing started");
    }
	
	@Override
    public void characters(char ch[], int start, int length) {
		for(int i = start; i < start + length; i++){
			buffer.append(ch[i]);
		}
	}
	
	@Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		buffer = new StringBuilder();
		if(qName.equals("content")) {
			inContent = true;
			content = new ArrayList<ContentBlock>();
		} else if(qName.equals("contentblock")) {
			inContentBlock = true;
			contentBlock = new ContentBlock();
		} else if (qName.equals("paragraphtitle")) {
			inParagraphTitle = true;
		} else if(qName.equals("paragraph")) {
			inParagraph = true;
		} else if(qName.equals("document")){
			reisAdvies = new ReisAdvies();
			inDocument = true;
		} else if(qName.equals("id")){
			inId = true;
		} else if(qName.equals("type")){
			inType = true;
		} else if(qName.equals("canonical")){
			inCanonical = true;
		} else if(qName.equals("daturl")){
			inDataurl = true;
		} else if(qName.equals("title")){
			inTitle = true;
		} else if(qName.equals("location")){
			inLocation = true;
		} else if(qName.equals("classification")){
			inClassification = true;
		}
	}
	
	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		if(inDocument && qName.equals("document")){
			inDocument = false;
			result.put(reisAdvies.getId(), reisAdvies);
		} else if(inParagraphTitle && qName.equals("paragraphtitle")) {
			inParagraphTitle = false;
			contentBlock.setParagraphTitle(buffer.toString());
		} else if(inParagraph && qName.equals("paragraph")) {
			inParagraph = false;
			contentBlock.setParagraph(buffer.toString());
		} else if(inContentBlock && qName.equals("contentblock")) {
			inContentBlock = false;
			content.add(contentBlock);
		} else if(inContent && qName.equals("content")) {
			reisAdvies.setContent(content);
		} else if(inId && qName.equals("id")){
			reisAdvies.setId(buffer.toString());
		} else if(inType && qName.equals("type")){
			reisAdvies.setType(buffer.toString());
		} else if(inCanonical && qName.equals("canonical")){
			reisAdvies.setCanonical(buffer.toString());
		} else if(inDataurl && qName.equals("dataurl")){
			reisAdvies.setDataUrl(buffer.toString());
		} else if(inTitle && qName.equals("title")){
			reisAdvies.setTitle(buffer.toString());
		} else if(inLocation && qName.equals("location")){
			reisAdvies.setLocation(buffer.toString());
		} else if(inClassification && qName.equals("classification")){
			reisAdvies.setClassification(buffer.toString());
		}
	}



}
