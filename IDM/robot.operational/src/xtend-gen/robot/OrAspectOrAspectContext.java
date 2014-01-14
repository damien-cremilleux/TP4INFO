package robot;

import java.util.Map;
import robot.OrAspectOrAspectProperties;
import robotG.flow.Or;

@SuppressWarnings("all")
public class OrAspectOrAspectContext {
  public final static OrAspectOrAspectContext INSTANCE = new OrAspectOrAspectContext();
  
  public static OrAspectOrAspectContext getInstance() {
    return INSTANCE;
  }
  
  private Map<Or,OrAspectOrAspectProperties> map = new java.util.HashMap<robotG.flow.Or, robot.OrAspectOrAspectProperties>();
  
  public Map<Or,OrAspectOrAspectProperties> getMap() {
    return map;
  }
}
