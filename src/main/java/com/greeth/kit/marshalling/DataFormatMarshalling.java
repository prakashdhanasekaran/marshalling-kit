package com.greeth.kit.marshalling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DataFormatMarshalling<T> {
    private Logger logger = LoggerFactory.getLogger(DataFormatMarshalling.class);

    public String toXML(T credential) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        return xmlMapper.writeValueAsString(credential);
    }

    public T toPojoFromXML(String xmlString, Class klass) throws MarshallingException {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return  (T) xmlMapper.readValue(xmlString, klass);
        } catch (JsonProcessingException e) {
            logger.info("Exception in converting object", e);
            throw new MarshallingException("Exception in converting xml to Object", e);
        }
    }

    public String toJsonString(T credential) throws MarshallingException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(credential);
        } catch (JsonProcessingException e) {
            logger.info("Exception in converting json string", e);
            throw new MarshallingException("Exception in converting Object to json string", e);
        }
    }

    public T toPojoFromJson(String jsonInString, Class klass) throws MarshallingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            //JSON from String to Object
            return (T) mapper.readValue(jsonInString, klass);
        } catch (IOException ex) {
            logger.info("Exception in converting object json", ex);
            throw new MarshallingException("Exception in converting json to Object", ex);
        }
    }
}