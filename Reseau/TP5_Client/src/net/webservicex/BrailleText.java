
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
 *         &lt;element name="InText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TextFontSize" type="{http://www.w3.org/2001/XMLSchema}float"/>
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
    "inText",
    "textFontSize"
})
@XmlRootElement(name = "BrailleText")
public class BrailleText {

    @XmlElement(name = "InText")
    protected String inText;
    @XmlElement(name = "TextFontSize")
    protected float textFontSize;

    /**
     * Gets the value of the inText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInText() {
        return inText;
    }

    /**
     * Sets the value of the inText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInText(String value) {
        this.inText = value;
    }

    /**
     * Gets the value of the textFontSize property.
     * 
     */
    public float getTextFontSize() {
        return textFontSize;
    }

    /**
     * Sets the value of the textFontSize property.
     * 
     */
    public void setTextFontSize(float value) {
        this.textFontSize = value;
    }

}
