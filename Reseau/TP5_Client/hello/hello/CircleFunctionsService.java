
package hello;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "CircleFunctionsService", targetNamespace = "http://hello/", wsdlLocation = "http://localhost:8080/WebServiceExample/circlefunctions/getArea?wsdl")
public class CircleFunctionsService
    extends Service
{

    private final static URL CIRCLEFUNCTIONSSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(hello.CircleFunctionsService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = hello.CircleFunctionsService.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:8080/WebServiceExample/circlefunctions/getArea?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:8080/WebServiceExample/circlefunctions/getArea?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        CIRCLEFUNCTIONSSERVICE_WSDL_LOCATION = url;
    }

    public CircleFunctionsService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CircleFunctionsService() {
        super(CIRCLEFUNCTIONSSERVICE_WSDL_LOCATION, new QName("http://hello/", "CircleFunctionsService"));
    }

    /**
     * 
     * @return
     *     returns CircleFunctions
     */
    @WebEndpoint(name = "CircleFunctionsPort")
    public CircleFunctions getCircleFunctionsPort() {
        return super.getPort(new QName("http://hello/", "CircleFunctionsPort"), CircleFunctions.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CircleFunctions
     */
    @WebEndpoint(name = "CircleFunctionsPort")
    public CircleFunctions getCircleFunctionsPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://hello/", "CircleFunctionsPort"), CircleFunctions.class, features);
    }

}