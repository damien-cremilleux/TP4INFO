package robot;

import java.util.Map;
import robot.BipAspectBipAspectProperties;
import robotG.robot.Bip;

@SuppressWarnings("all")
public class BipAspectBipAspectContext {
  public final static BipAspectBipAspectContext INSTANCE = new BipAspectBipAspectContext();
  
  public static BipAspectBipAspectContext getInstance() {
    return INSTANCE;
  }
  
  private Map<Bip,BipAspectBipAspectProperties> map = new java.util.HashMap<robotG.robot.Bip, robot.BipAspectBipAspectProperties>();
  
  public Map<Bip,BipAspectBipAspectProperties> getMap() {
    return map;
  }
}
