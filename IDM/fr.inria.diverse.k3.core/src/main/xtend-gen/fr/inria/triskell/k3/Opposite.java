/**
 * Opposite annotation
 * 
 * Example:
 * class A { @Opposite("a") public B b }
 * class B { @Opposite("b") public A a }
 * 
 * @author Arnaud Blouin / Thomas Degueule
 */
package fr.inria.triskell.k3;

import fr.inria.triskell.k3.OppositeProcessor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.eclipse.xtend.lib.macro.Active;

/**
 * Opposite annotation declaration
 */
@Target(ElementType.FIELD)
@Active(OppositeProcessor.class)
@Retention(RetentionPolicy.SOURCE)
public @interface Opposite {
  public String value();
}
