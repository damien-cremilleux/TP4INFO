package robot;

import org.eclipse.xtext.xbase.lib.Functions.Function0;

@SuppressWarnings("all")
public class ContextNCX {
  private final StringBuilder _header = new Function0<StringBuilder>() {
    public StringBuilder apply() {
      StringBuilder _stringBuilder = new StringBuilder();
      return _stringBuilder;
    }
  }.apply();
  
  public StringBuilder getHeader() {
    return this._header;
  }
  
  private boolean _initUSCreated = false;
  
  public boolean isInitUSCreated() {
    return this._initUSCreated;
  }
  
  public void setInitUSCreated(final boolean initUSCreated) {
    this._initUSCreated = initUSCreated;
  }
  
  private boolean _angleVarCreated = false;
  
  public boolean isAngleVarCreated() {
    return this._angleVarCreated;
  }
  
  public void setAngleVarCreated(final boolean angleVarCreated) {
    this._angleVarCreated = angleVarCreated;
  }
}
