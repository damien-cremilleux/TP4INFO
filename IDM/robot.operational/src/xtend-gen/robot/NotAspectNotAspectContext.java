package robot;

import java.util.Map;
import robot.NotAspectNotAspectProperties;
import robotG.flow.Not;

@SuppressWarnings("all")
public class NotAspectNotAspectContext {
  public final static NotAspectNotAspectContext INSTANCE = new NotAspectNotAspectContext();
  
  public static NotAspectNotAspectContext getInstance() {
    return INSTANCE;
  }
  
  private Map<Not,NotAspectNotAspectProperties> map = new java.util.HashMap<robotG.flow.Not, robot.NotAspectNotAspectProperties>();
  
  public Map<Not,NotAspectNotAspectProperties> getMap() {
    return map;
  }
}
