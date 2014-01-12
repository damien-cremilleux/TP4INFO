package de.oehme.xtend.contrib.base;

import de.oehme.xtend.contrib.base.ValueObjectProcessor;
import org.eclipse.xtend.lib.macro.Active;

/**
 * Turns your class into an immutable value object with a builder, getters for all fields
 * and default equals, hashcode and toString methods.
 */
@Active(ValueObjectProcessor.class)
public @interface ValueObject {
}
