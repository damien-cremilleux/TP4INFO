package robot;

import java.util.Map;
import robot.IfAspectIfAspectProperties;
import robotG.flow.If;

@SuppressWarnings("all")
public class IfAspectIfAspectContext {
  public final static IfAspectIfAspectContext INSTANCE = new IfAspectIfAspectContext();
  
  public static IfAspectIfAspectContext getInstance() {
    return INSTANCE;
  }
  
  private Map<If,IfAspectIfAspectProperties> map = new java.util.HashMap<robotG.flow.If, robot.IfAspectIfAspectProperties>();
  
  public Map<If,IfAspectIfAspectProperties> getMap() {
    return map;
  }
}
