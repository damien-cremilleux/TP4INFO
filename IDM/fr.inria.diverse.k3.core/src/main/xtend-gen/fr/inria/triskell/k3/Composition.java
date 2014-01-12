package fr.inria.triskell.k3;

import fr.inria.triskell.k3.CompositionProcessor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.eclipse.xtend.lib.macro.Active;

/**
 * The Composition annotation.
 * @author Arnaud Blouin
 */
@Target(ElementType.FIELD)
@Active(CompositionProcessor.class)
@Retention(RetentionPolicy.SOURCE)
public @interface Composition {
}
