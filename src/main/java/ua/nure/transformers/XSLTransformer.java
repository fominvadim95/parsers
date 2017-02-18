package ua.nure.transformers;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLTransformer {

    public void parse(String xmlPath, String xslPath, String htmlPath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlPath);

            TransformerFactory tFactory = TransformerFactory.newInstance();
            StreamSource styleSource = new StreamSource(xslPath);
            Transformer transformer = tFactory.newTransformer(styleSource);
            DOMSource source = new DOMSource(document);

            StreamResult streamResult = new StreamResult(htmlPath);
            transformer.transform(source, streamResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
