package de.oehme.xtend.contrib.base;

import java.util.Arrays;

/**
 * This class is an implementation detail and not fit for general use.
 * It foregoes immutability for pure performance
 */
@SuppressWarnings("all")
public class CacheKey {
  private final Object[] parameters;
  
  public CacheKey(final Object... parameters) {
    this.parameters = parameters;
  }
  
  public Object get(final int index) {
    Object _get = this.parameters[index];
    return _get;
  }
  
  public boolean equals(final Object obj) {
    boolean _xblockexpression = false;
    {
      if ((obj instanceof CacheKey)) {
        return Arrays.equals(this.parameters, ((CacheKey) obj).parameters);
      }
      _xblockexpression = (false);
    }
    return _xblockexpression;
  }
  
  public int hashCode() {
    int _hashCode = Arrays.hashCode(this.parameters);
    return _hashCode;
  }
}
