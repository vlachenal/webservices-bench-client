//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7-b41 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2017.09.12 à 11:14:30 AM CEST 
//


package com.github.vlachenal.webservice.bench.client.soap.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour client-call complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="client-call">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="request-seq" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="protocol" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="method" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="client-start" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="client-end" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ok" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="err-msg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "client-call", propOrder = {
    "requestSeq",
    "protocol",
    "method",
    "clientStart",
    "clientEnd",
    "ok",
    "errMsg"
})
public class ClientCall {

    @XmlElement(name = "request-seq")
    protected int requestSeq;
    @XmlElement(required = true)
    protected String protocol;
    @XmlElement(required = true)
    protected String method;
    @XmlElement(name = "client-start")
    protected long clientStart;
    @XmlElement(name = "client-end")
    protected long clientEnd;
    protected boolean ok;
    @XmlElement(name = "err-msg", required = true)
    protected String errMsg;

    /**
     * Obtient la valeur de la propriété requestSeq.
     * 
     */
    public int getRequestSeq() {
        return requestSeq;
    }

    /**
     * Définit la valeur de la propriété requestSeq.
     * 
     */
    public void setRequestSeq(int value) {
        this.requestSeq = value;
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
     * Obtient la valeur de la propriété method.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMethod() {
        return method;
    }

    /**
     * Définit la valeur de la propriété method.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMethod(String value) {
        this.method = value;
    }

    /**
     * Obtient la valeur de la propriété clientStart.
     * 
     */
    public long getClientStart() {
        return clientStart;
    }

    /**
     * Définit la valeur de la propriété clientStart.
     * 
     */
    public void setClientStart(long value) {
        this.clientStart = value;
    }

    /**
     * Obtient la valeur de la propriété clientEnd.
     * 
     */
    public long getClientEnd() {
        return clientEnd;
    }

    /**
     * Définit la valeur de la propriété clientEnd.
     * 
     */
    public void setClientEnd(long value) {
        this.clientEnd = value;
    }

    /**
     * Obtient la valeur de la propriété ok.
     * 
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * Définit la valeur de la propriété ok.
     * 
     */
    public void setOk(boolean value) {
        this.ok = value;
    }

    /**
     * Obtient la valeur de la propriété errMsg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * Définit la valeur de la propriété errMsg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrMsg(String value) {
        this.errMsg = value;
    }

}
