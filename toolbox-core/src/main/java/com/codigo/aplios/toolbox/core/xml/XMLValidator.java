package com.codigo.aplios.toolbox.core.xml;

import com.codigo.aplios.toolbox.core.global.Constants.Logical;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.eclipse.jdt.annotation.NonNull;
import org.xml.sax.SAXException;

public class XMLValidator {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String XML_FILE = "customer.xml";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String SCHEMA_FILE = "customer.xsd";

    // -----------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {

        XMLValidator XMLValidator = new XMLValidator();
        boolean valid = XMLValidator.validate(com.codigo.aplios.toolbox.core.xml.XMLValidator.XML_FILE,
                com.codigo.aplios.toolbox.core.xml.XMLValidator.SCHEMA_FILE);

        System.out.printf("%s validation = %b.", com.codigo.aplios.toolbox.core.xml.XMLValidator.XML_FILE, valid);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private boolean validate(@NonNull String xmlFile, @NonNull String schemaFile) {

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(new File(this.getResource(schemaFile)));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(this.getResource(xmlFile))));
            return true;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return Logical.NO;
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String getResource(@NonNull String filename) throws FileNotFoundException {

        @NonNull
        URL resource = this.getClass()
                .getClassLoader()
                .getResource("");
        resource = this.getClass()
                .getClassLoader()
                .getResource(filename);
        Objects.requireNonNull(resource);

        return resource.getFile();
    }

    // -----------------------------------------------------------------------------------------------------------------
}
