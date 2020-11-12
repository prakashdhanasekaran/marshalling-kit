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

    public T toPojoFromXML(String xmlString, Class klass) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        return  (T) xmlMapper.readValue(xmlString, klass);
    }

    public String toJsonString(T credential) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(credential);
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