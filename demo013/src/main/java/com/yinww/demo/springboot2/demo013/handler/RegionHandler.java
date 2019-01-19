package com.yinww.demo.springboot2.demo013.handler;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.yinww.demo.springboot2.demo013.domain.CountryRegion;

public class RegionHandler extends DefaultHandler {
    
    private List<CountryRegion> countryRegions = null;
    private CountryRegion countryRegion = null;
    
    public RegionHandler(List<CountryRegion> countryRegions) {
        this.countryRegions = countryRegions;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if("CountryRegion".equals(qName)) {
            countryRegion = new CountryRegion();
            int length = attributes.getLength();
            for (int i = 0; i < length; i++) {
                String attrbiuteName = attributes.getLocalName(i);
                if("Name".equals(attrbiuteName)){
                    countryRegion.setName(attributes.getValue(i));
                } else if("Code".equals(attrbiuteName)){
                    countryRegion.setCode(attributes.getValue(i));
                }
            }
            countryRegions.add(countryRegion);
        }
    }

}
