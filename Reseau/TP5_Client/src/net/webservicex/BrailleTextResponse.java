
package net.webservicex;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BrailleTextResult" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "brailleTextResult"
})
@XmlRootElement(name = "BrailleTextResponse")
public class BrailleTextResponse {

    @XmlElement(name = "BrailleTextResult")
    protected byte[] brailleTextResult;

    /**
     * Gets the value of the brailleTextResult property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBrailleTextResult() {
        return brailleTextResult;
    }

    /**
     * Sets the value of the brailleTextResult property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBrailleTextResult(byte[] value) {
        this.brailleTextResult = ((byte[]) value);
    }

}
