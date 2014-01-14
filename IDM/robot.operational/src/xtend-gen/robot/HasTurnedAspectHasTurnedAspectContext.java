package robot;

import java.util.Map;
import robot.HasTurnedAspectHasTurnedAspectProperties;
import robotG.robot.HasTurned;

@SuppressWarnings("all")
public class HasTurnedAspectHasTurnedAspectContext {
  public final static HasTurnedAspectHasTurnedAspectContext INSTANCE = new HasTurnedAspectHasTurnedAspectContext();
  
  public static HasTurnedAspectHasTurnedAspectContext getInstance() {
    return INSTANCE;
  }
  
  private Map<HasTurned,HasTurnedAspectHasTurnedAspectProperties> map = new java.util.HashMap<robotG.robot.HasTurned, robot.HasTurnedAspectHasTurnedAspectProperties>();
  
  public Map<HasTurned,HasTurnedAspectHasTurnedAspectProperties> getMap() {
    return map;
  }
}
