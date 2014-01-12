package de.oehme.xtend.contrib.base;

import com.google.common.collect.ImmutableList;
import org.eclipse.xtend.lib.Data;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * A signature represents the simple name and parameter types of a method.
 * These parts are what is needed for two methods to be considered "duplicates".
 * Note that this implementation is not aware of type erasure,
 * so it will fail to detect duplicates that have the same erasure.
 */
@Data
@SuppressWarnings("all")
public final class Signature {
  private final String _name;
  
  public String getName() {
    return this._name;
  }
  
  private final ImmutableList<? extends TypeReference> _parameterTypes;
  
  public ImmutableList<? extends TypeReference> getParameterTypes() {
    return this._parameterTypes;
  }
  
  public String toString() {
    StringConcatenation _builder = new StringConcatenation();
    String _name = this.getName();
    _builder.append(_name, "");
    _builder.append("(");
    ImmutableList<? extends TypeReference> _parameterTypes = this.getParameterTypes();
    final Function1<TypeReference,String> _function = new Function1<TypeReference,String>() {
      public String apply(final TypeReference it) {
        String _name = it.getName();
        return _name;
      }
    };
    String _join = IterableExtensions.join(_parameterTypes, ",", _function);
    _builder.append(_join, "");
    _builder.append(")");
    return _builder.toString();
  }
  
  public Signature(final String name, final ImmutableList<? extends TypeReference> parameterTypes) {
    super();
    this._name = name;
    this._parameterTypes = parameterTypes;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((_name== null) ? 0 : _name.hashCode());
    result = prime * result + ((_parameterTypes== null) ? 0 : _parameterTypes.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Signature other = (Signature) obj;
    if (_name == null) {
      if (other._name != null)
        return false;
    } else if (!_name.equals(other._name))
      return false;
    if (_parameterTypes == null) {
      if (other._parameterTypes != null)
        return false;
    } else if (!_parameterTypes.equals(other._parameterTypes))
      return false;
    return true;
  }
}
