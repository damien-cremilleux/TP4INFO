package fr.inria.triskell.k3;

import fr.inria.triskell.k3.ContractedProcessor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.eclipse.xtend.lib.macro.Active;

@Target(ElementType.TYPE)
@Active(ContractedProcessor.class)
public @interface Contracted {
}
