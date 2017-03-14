package org.arnoid.damoclus.ui.scene.menu.builder.xml;

public class XmlParserException extends Exception {
    public XmlParserException(String s, Exception e) {
        super(s, e);
    }

    public XmlParserException(String message) {
        super(message);
    }
}
