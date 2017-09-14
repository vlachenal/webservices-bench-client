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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="request-seq" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
@XmlType(name = "", propOrder = {
    "requestSeq",
    "mapper"
})
@XmlRootElement(name = "request-header")
public class RequestHeader {

    @XmlElement(name = "request-seq")
    protected Integer requestSeq;
    protected Mapper mapper;

    /**
     * Obtient la valeur de la propriété requestSeq.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRequestSeq() {
        return requestSeq;
    }

    /**
     * Définit la valeur de la propriété requestSeq.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRequestSeq(Integer value) {
        this.requestSeq = value;
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
