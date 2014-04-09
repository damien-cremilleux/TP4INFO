
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

    private final static QName _Fois_QNAME = new QName("http://hello/", "fois");
    private final static QName _PlusResponse_QNAME = new QName("http://hello/", "plusResponse");
    private final static QName _FoisResponse_QNAME = new QName("http://hello/", "foisResponse");
    private final static QName _DivisionResponse_QNAME = new QName("http://hello/", "divisionResponse");
    private final static QName _MoinsResponse_QNAME = new QName("http://hello/", "moinsResponse");
    private final static QName _Division_QNAME = new QName("http://hello/", "division");
    private final static QName _Plus_QNAME = new QName("http://hello/", "plus");
    private final static QName _Moins_QNAME = new QName("http://hello/", "moins");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: hello
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MoinsResponse }
     * 
     */
    public MoinsResponse createMoinsResponse() {
        return new MoinsResponse();
    }

    /**
     * Create an instance of {@link DivisionResponse }
     * 
     */
    public DivisionResponse createDivisionResponse() {
        return new DivisionResponse();
    }

    /**
     * Create an instance of {@link Fois }
     * 
     */
    public Fois createFois() {
        return new Fois();
    }

    /**
     * Create an instance of {@link FoisResponse }
     * 
     */
    public FoisResponse createFoisResponse() {
        return new FoisResponse();
    }

    /**
     * Create an instance of {@link Division }
     * 
     */
    public Division createDivision() {
        return new Division();
    }

    /**
     * Create an instance of {@link Moins }
     * 
     */
    public Moins createMoins() {
        return new Moins();
    }

    /**
     * Create an instance of {@link Plus }
     * 
     */
    public Plus createPlus() {
        return new Plus();
    }

    /**
     * Create an instance of {@link PlusResponse }
     * 
     */
    public PlusResponse createPlusResponse() {
        return new PlusResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Fois }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hello/", name = "fois")
    public JAXBElement<Fois> createFois(Fois value) {
        return new JAXBElement<Fois>(_Fois_QNAME, Fois.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hello/", name = "plusResponse")
    public JAXBElement<PlusResponse> createPlusResponse(PlusResponse value) {
        return new JAXBElement<PlusResponse>(_PlusResponse_QNAME, PlusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FoisResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hello/", name = "foisResponse")
    public JAXBElement<FoisResponse> createFoisResponse(FoisResponse value) {
        return new JAXBElement<FoisResponse>(_FoisResponse_QNAME, FoisResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DivisionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hello/", name = "divisionResponse")
    public JAXBElement<DivisionResponse> createDivisionResponse(DivisionResponse value) {
        return new JAXBElement<DivisionResponse>(_DivisionResponse_QNAME, DivisionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MoinsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hello/", name = "moinsResponse")
    public JAXBElement<MoinsResponse> createMoinsResponse(MoinsResponse value) {
        return new JAXBElement<MoinsResponse>(_MoinsResponse_QNAME, MoinsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Division }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hello/", name = "division")
    public JAXBElement<Division> createDivision(Division value) {
        return new JAXBElement<Division>(_Division_QNAME, Division.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Plus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hello/", name = "plus")
    public JAXBElement<Plus> createPlus(Plus value) {
        return new JAXBElement<Plus>(_Plus_QNAME, Plus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Moins }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hello/", name = "moins")
    public JAXBElement<Moins> createMoins(Moins value) {
        return new JAXBElement<Moins>(_Moins_QNAME, Moins.class, null, value);
    }

}
