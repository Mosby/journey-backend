package com.javacodegeeks.resteasy.dao;

import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.javacodegeeks.resteasy.handler.ReisAdviesHandler;
import com.javacodegeeks.resteasy.model.ReisAdvies;


public class GetReisAdvies {
	
	private final Logger LOG = LoggerFactory.getLogger(GetReisAdvies.class);
	private static final String REIS_ADVIEZEN_URL = "http://opendata.rijksoverheid.nl/v1/sources/rijksoverheid/infotypes/traveladvice";
	private static final String REIS_ADVIES_URL = "http://opendata.rijksoverheid.nl/v1/sources/rijksoverheid/infotypes/traveladvice/";
	
	public Map<String, ReisAdvies> init(String locationId) {
		try {
			URL url;
			if(locationId.equals("all")) {
				url = new URL(REIS_ADVIEZEN_URL);
			} else {
				url = new URL(REIS_ADVIES_URL + locationId); 
			}
			SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            /* Get the XMLReader of the SAXParser we created. */
            XMLReader xr = sp.getXMLReader();
            ReisAdviesHandler reisAdviesHandler = new ReisAdviesHandler();
            xr.setContentHandler(reisAdviesHandler);
            InputSource is = new InputSource(url.openStream());
            is.setEncoding("utf-8");
            xr.parse(is);
            return reisAdviesHandler.getReisAdviezen();
		} catch (UnknownHostException uhe){
			//return null;
		} catch(NumberFormatException nfe){
			nfe.printStackTrace();
			//return null;
		}
		catch(Exception e){
			//return null;
		}
		return new HashMap<String, ReisAdvies>();

	}

}
