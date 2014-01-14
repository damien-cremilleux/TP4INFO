package fr.inria.triskell.k3;

import fr.inria.triskell.k3.SingletonProcessor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.eclipse.xtend.lib.macro.Active;

@Target(ElementType.TYPE)
@Active(SingletonProcessor.class)
public @interface Singleton {
}
