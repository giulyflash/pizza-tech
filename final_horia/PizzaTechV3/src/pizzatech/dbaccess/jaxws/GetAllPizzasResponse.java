
package pizzatech.dbaccess.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.4.6
 * Sat May 12 17:11:34 EEST 2012
 * Generated source version: 2.4.6
 */

@XmlRootElement(name = "getAllPizzasResponse", namespace = "http://pizzatech")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllPizzasResponse", namespace = "http://pizzatech")

public class GetAllPizzasResponse {

    @XmlElement(name = "return")
    private java.util.List<pizzatech.model.Pizza> _return;

    public java.util.List<pizzatech.model.Pizza> getReturn() {
        return this._return;
    }

    public void setReturn(java.util.List<pizzatech.model.Pizza> new_return)  {
        this._return = new_return;
    }

}

