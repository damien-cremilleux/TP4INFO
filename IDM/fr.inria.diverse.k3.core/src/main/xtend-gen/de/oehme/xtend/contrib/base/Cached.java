package de.oehme.xtend.contrib.base;

import de.oehme.xtend.contrib.base.MemoizeProcessor;
import org.eclipse.xtend.lib.macro.Active;

/**
 * Caches invocations of a method. When the method is called multiple times with the same parameters, a cached result will be returned.
 * Useful for expensive calculations or recursive algorithms.
 * <br>
 * The method must guarantee the following conditions:
 * <ul>
 * 	<li>The method's parameters have meaningful equals/hashcode implementations.</li>
 * 	<li>The result of the method only depends on the parameters or immutable internal state</li>
 * 	<li>The method is referentially transparent (has no externally visible side effects)</li>
 * </ul>
 */
@Active(MemoizeProcessor.class)
public @interface Cached {
}
