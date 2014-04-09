
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
 *         &lt;element name="BookTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chapter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Verse" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "bookTitle",
    "chapter",
    "verse"
})
@XmlRootElement(name = "GetBibleWordsByChapterAndVerse")
public class GetBibleWordsByChapterAndVerse {

    @XmlElement(name = "BookTitle")
    protected String bookTitle;
    protected int chapter;
    @XmlElement(name = "Verse")
    protected int verse;

    /**
     * Gets the value of the bookTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookTitle() {
        return bookTitle;
    }

    /**
     * Sets the value of the bookTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookTitle(String value) {
        this.bookTitle = value;
    }

    /**
     * Gets the value of the chapter property.
     * 
     */
    public int getChapter() {
        return chapter;
    }

    /**
     * Sets the value of the chapter property.
     * 
     */
    public void setChapter(int value) {
        this.chapter = value;
    }

    /**
     * Gets the value of the verse property.
     * 
     */
    public int getVerse() {
        return verse;
    }

    /**
     * Sets the value of the verse property.
     * 
     */
    public void setVerse(int value) {
        this.verse = value;
    }

}
