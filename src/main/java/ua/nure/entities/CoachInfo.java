//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.02.13 at 02:14:07 PM EET 
//


package ua.nure.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CoachInfo complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="CoachInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{}Info">
 *       &lt;sequence>
 *         &lt;element name="age" type="{}CoachAge"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoachInfo", propOrder = {
        "age"
})
public class CoachInfo
        extends Info {

    @XmlSchemaType(name = "integer")
    protected int age;

    /**
     * Gets the value of the age property.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the value of the age property.
     */
    public void setAge(int value) {
        this.age = value;
    }

    @Override
    public String toString() {
        return "CoachInfo{" +
                "age=" + age + ", " + super.toString() +
                '}';
    }
}
