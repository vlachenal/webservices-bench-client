//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7-b41 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2017.09.12 à 11:14:30 AM CEST 
//


package com.github.vlachenal.webservice.bench.client.soap.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour test-suite complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="test-suite">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nb-thread" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="compression" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="memory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="jvm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vendor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="os-family" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="os-version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="protocol" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="calls" type="{http://github.com/vlachenal/webservices-bench}client-call" maxOccurs="unbounded"/>
 *         &lt;element name="mapper" type="{http://github.com/vlachenal/webservices-bench}mapper" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "test-suite", propOrder = {
    "id",
    "nbThread",
    "compression",
    "cpu",
    "memory",
    "jvm",
    "vendor",
    "osFamily",
    "osVersion",
    "protocol",
    "comment",
    "calls",
    "mapper"
})
public class TestSuite {

    protected String id;
    @XmlElement(name = "nb-thread")
    protected int nbThread;
    protected String compression;
    @XmlElement(required = true)
    protected String cpu;
    @XmlElement(required = true)
    protected String memory;
    @XmlElement(required = true)
    protected String jvm;
    @XmlElement(required = true)
    protected String vendor;
    @XmlElement(name = "os-family", required = true)
    protected String osFamily;
    @XmlElement(name = "os-version", required = true)
    protected String osVersion;
    @XmlElement(required = true)
    protected String protocol;
    @XmlElement(required = true)
    protected String comment;
    @XmlElement(required = true)
    protected List<ClientCall> calls;
    protected Mapper mapper;

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété nbThread.
     * 
     */
    public int getNbThread() {
        return nbThread;
    }

    /**
     * Définit la valeur de la propriété nbThread.
     * 
     */
    public void setNbThread(int value) {
        this.nbThread = value;
    }

    /**
     * Obtient la valeur de la propriété compression.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompression() {
        return compression;
    }

    /**
     * Définit la valeur de la propriété compression.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompression(String value) {
        this.compression = value;
    }

    /**
     * Obtient la valeur de la propriété cpu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpu() {
        return cpu;
    }

    /**
     * Définit la valeur de la propriété cpu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpu(String value) {
        this.cpu = value;
    }

    /**
     * Obtient la valeur de la propriété memory.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemory() {
        return memory;
    }

    /**
     * Définit la valeur de la propriété memory.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemory(String value) {
        this.memory = value;
    }

    /**
     * Obtient la valeur de la propriété jvm.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJvm() {
        return jvm;
    }

    /**
     * Définit la valeur de la propriété jvm.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJvm(String value) {
        this.jvm = value;
    }

    /**
     * Obtient la valeur de la propriété vendor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Définit la valeur de la propriété vendor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendor(String value) {
        this.vendor = value;
    }

    /**
     * Obtient la valeur de la propriété osFamily.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOsFamily() {
        return osFamily;
    }

    /**
     * Définit la valeur de la propriété osFamily.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOsFamily(String value) {
        this.osFamily = value;
    }

    /**
     * Obtient la valeur de la propriété osVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOsVersion() {
        return osVersion;
    }

    /**
     * Définit la valeur de la propriété osVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOsVersion(String value) {
        this.osVersion = value;
    }

    /**
     * Obtient la valeur de la propriété protocol.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Définit la valeur de la propriété protocol.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocol(String value) {
        this.protocol = value;
    }

    /**
     * Obtient la valeur de la propriété comment.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Définit la valeur de la propriété comment.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the calls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalls().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClientCall }
     * 
     * 
     */
    public List<ClientCall> getCalls() {
        if (calls == null) {
            calls = new ArrayList<ClientCall>();
        }
        return this.calls;
    }

    /**
     * Obtient la valeur de la propriété mapper.
     * 
     * @return
     *     possible object is
     *     {@link Mapper }
     *     
     */
    public Mapper getMapper() {
        return mapper;
    }

    /**
     * Définit la valeur de la propriété mapper.
     * 
     * @param value
     *     allowed object is
     *     {@link Mapper }
     *     
     */
    public void setMapper(Mapper value) {
        this.mapper = value;
    }

}
