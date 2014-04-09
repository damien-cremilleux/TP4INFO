
package hello;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the hello package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAreaResponse_QNAME = new QName("http://hello/", "getAreaResponse");
    private final static QName _GetCircumference_QNAME = new QName("http://hello/", "getCircumference");
    private final static QName _GetCircumferenceResponse_QNAME = new QName("http://hello/", "getCircumferenceResponse");
    private final static QName _GetArea_QNAME = new QName("http://hello/", "getArea");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: hello
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetArea }
     * 
     */
    public GetArea createGetArea() {
        return new GetArea();
    }

    /**
     * Create an instance of {@link GetAreaResponse }
     * 
     */
    public GetAreaResponse createGetAreaResponse() {
        return new GetAreaResponse();
    }

    /**
     * Create an instance of {@link GetCircumference }
     * 
     */
    public GetCircumference createGetCircumference() {
        return new GetCircumference();
    }

    /**
     * Create an instance of {@link GetCircumferenceResponse }
     * 
     */
    public GetCircumferenceResponse createGetCircumferenceResponse() {
        return new GetCircumferenceResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAreaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hello/", name = "getAreaResponse")
    public JAXBElement<GetAreaResponse> createGetAreaResponse(GetAreaResponse value) {
        return new JAXBElement<GetAreaResponse>(_GetAreaResponse_QNAME, GetAreaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCircumference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hello/", name = "getCircumference")
    public JAXBElement<GetCircumference> createGetCircumference(GetCircumference value) {
        return new JAXBElement<GetCircumference>(_GetCircumference_QNAME, GetCircumference.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCircumferenceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hello/", name = "getCircumferenceResponse")
    public JAXBElement<GetCircumferenceResponse> createGetCircumferenceResponse(GetCircumferenceResponse value) {
        return new JAXBElement<GetCircumferenceResponse>(_GetCircumferenceResponse_QNAME, GetCircumferenceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetArea }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hello/", name = "getArea")
    public JAXBElement<GetArea> createGetArea(GetArea value) {
        return new JAXBElement<GetArea>(_GetArea_QNAME, GetArea.class, null, value);
    }

}
