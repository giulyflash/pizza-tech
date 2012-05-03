
package pizzatech.test;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ingredientType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ingredientType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DOUGH"/>
 *     &lt;enumeration value="MAIN"/>
 *     &lt;enumeration value="TOPPING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ingredientType")
@XmlEnum
public enum IngredientType {

    DOUGH,
    MAIN,
    TOPPING;

    public String value() {
        return name();
    }

    public static IngredientType fromValue(String v) {
        return valueOf(v);
    }

}
