
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

@XmlRootElement(name = "getAllToppingsResponse", namespace = "http://pizzatech")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllToppingsResponse", namespace = "http://pizzatech")

public class GetAllToppingsResponse {

    @XmlElement(name = "return")
    private java.util.List<pizzatech.model.Ingredient> _return;

    public java.util.List<pizzatech.model.Ingredient> getReturn() {
        return this._return;
    }

    public void setReturn(java.util.List<pizzatech.model.Ingredient> new_return)  {
        this._return = new_return;
    }

}

