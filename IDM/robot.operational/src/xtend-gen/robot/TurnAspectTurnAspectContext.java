package robot;

import java.util.Map;
import robot.TurnAspectTurnAspectProperties;
import robotG.robot.Turn;

@SuppressWarnings("all")
public class TurnAspectTurnAspectContext {
  public final static TurnAspectTurnAspectContext INSTANCE = new TurnAspectTurnAspectContext();
  
  public static TurnAspectTurnAspectContext getInstance() {
    return INSTANCE;
  }
  
  private Map<Turn,TurnAspectTurnAspectProperties> map = new java.util.HashMap<robotG.robot.Turn, robot.TurnAspectTurnAspectProperties>();
  
  public Map<Turn,TurnAspectTurnAspectProperties> getMap() {
    return map;
  }
}
