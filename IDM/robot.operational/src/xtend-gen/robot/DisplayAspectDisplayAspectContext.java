package robot;

import java.util.Map;
import robot.DisplayAspectDisplayAspectProperties;
import robotG.robot.Display;

@SuppressWarnings("all")
public class DisplayAspectDisplayAspectContext {
  public final static DisplayAspectDisplayAspectContext INSTANCE = new DisplayAspectDisplayAspectContext();
  
  public static DisplayAspectDisplayAspectContext getInstance() {
    return INSTANCE;
  }
  
  private Map<Display,DisplayAspectDisplayAspectProperties> map = new java.util.HashMap<robotG.robot.Display, robot.DisplayAspectDisplayAspectProperties>();
  
  public Map<Display,DisplayAspectDisplayAspectProperties> getMap() {
    return map;
  }
}
