package de.oehme.xtend.contrib.base;

import de.oehme.xtend.contrib.base.DecoratorProcessor;
import org.eclipse.xtend.lib.macro.Active;

/**
 * Creates a decorator for the given interface.
 * It will contain a {@code delegate} field, a constructor that takes the delegate as a parameter
 * and default implementations for all methods.
 * You only need to customize the methods you are really interested in.
 */
@Active(DecoratorProcessor.class)
public @interface Decorator {
  public Class<? extends Object> value();
}
