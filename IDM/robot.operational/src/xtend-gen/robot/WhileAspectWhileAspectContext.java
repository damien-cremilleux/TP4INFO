package robot;

import java.util.Map;
import robot.WhileAspectWhileAspectProperties;
import robotG.flow.While;

@SuppressWarnings("all")
public class WhileAspectWhileAspectContext {
  public final static WhileAspectWhileAspectContext INSTANCE = new WhileAspectWhileAspectContext();
  
  public static WhileAspectWhileAspectContext getInstance() {
    return INSTANCE;
  }
  
  private Map<While,WhileAspectWhileAspectProperties> map = new java.util.HashMap<robotG.flow.While, robot.WhileAspectWhileAspectProperties>();
  
  public Map<While,WhileAspectWhileAspectProperties> getMap() {
    return map;
  }
}
