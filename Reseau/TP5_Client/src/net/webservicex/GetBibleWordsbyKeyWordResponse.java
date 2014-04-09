
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
 *         &lt;element name="GetBibleWordsbyKeyWordResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getBibleWordsbyKeyWordResult"
})
@XmlRootElement(name = "GetBibleWordsbyKeyWordResponse")
public class GetBibleWordsbyKeyWordResponse {

    @XmlElement(name = "GetBibleWordsbyKeyWordResult")
    protected String getBibleWordsbyKeyWordResult;

    /**
     * Gets the value of the getBibleWordsbyKeyWordResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetBibleWordsbyKeyWordResult() {
        return getBibleWordsbyKeyWordResult;
    }

    /**
     * Sets the value of the getBibleWordsbyKeyWordResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetBibleWordsbyKeyWordResult(String value) {
        this.getBibleWordsbyKeyWordResult = value;
    }

}
