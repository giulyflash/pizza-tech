
package client.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.4.6
 * Sat Apr 21 18:52:37 EEST 2012
 * Generated source version: 2.4.6
 */

@XmlRootElement(name = "getAllPizzasResponse", namespace = "http://client/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllPizzasResponse", namespace = "http://client/")

public class GetAllPizzasResponse {

    @XmlElement(name = "return")
    private java.util.List<client.Pizza> _return;

    public java.util.List<client.Pizza> getReturn() {
        return this._return;
    }

    public void setReturn(java.util.List<client.Pizza> new_return)  {
        this._return = new_return;
    }

}

