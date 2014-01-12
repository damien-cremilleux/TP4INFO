package robot;

import java.util.Map;
import robot.AndAspectAndAspectProperties;
import robotG.flow.And;

@SuppressWarnings("all")
public class AndAspectAndAspectContext {
  public final static AndAspectAndAspectContext INSTANCE = new AndAspectAndAspectContext();
  
  public static AndAspectAndAspectContext getInstance() {
    return INSTANCE;
  }
  
  private Map<And,AndAspectAndAspectProperties> map = new java.util.HashMap<robotG.flow.And, robot.AndAspectAndAspectProperties>();
  
  public Map<And,AndAspectAndAspectProperties> getMap() {
    return map;
  }
}
