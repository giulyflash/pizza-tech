
package pizzatech.dbaccess.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.4.6
 * Thu May 03 15:31:41 EEST 2012
 * Generated source version: 2.4.6
 */

@XmlRootElement(name = "createOrder", namespace = "http://pizzatech")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createOrder", namespace = "http://pizzatech", propOrder = {"arg0", "arg1"})

public class CreateOrder {

    @XmlElement(name = "arg0")
    private pizzatech.model.Order arg0;
    @XmlElement(name = "arg1")
    private java.util.List<java.lang.Integer> arg1;

    public pizzatech.model.Order getArg0() {
        return this.arg0;
    }

    public void setArg0(pizzatech.model.Order newArg0)  {
        this.arg0 = newArg0;
    }

    public java.util.List<java.lang.Integer> getArg1() {
        return this.arg1;
    }

    public void setArg1(java.util.List<java.lang.Integer> newArg1)  {
        this.arg1 = newArg1;
    }

}

