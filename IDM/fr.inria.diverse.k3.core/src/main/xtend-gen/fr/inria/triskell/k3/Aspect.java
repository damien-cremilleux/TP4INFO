package fr.inria.triskell.k3;

import fr.inria.triskell.k3.AspectProcessor;
import org.eclipse.xtend.lib.macro.Active;

@Active(AspectProcessor.class)
public @interface Aspect {
  public Class<? extends Object> className();
}
