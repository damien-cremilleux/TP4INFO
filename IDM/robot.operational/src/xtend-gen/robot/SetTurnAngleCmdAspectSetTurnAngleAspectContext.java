package robot;

import java.util.Map;
import robot.SetTurnAngleCmdAspectSetTurnAngleAspectProperties;
import robotG.robot.SetTurnAngle;

@SuppressWarnings("all")
public class SetTurnAngleCmdAspectSetTurnAngleAspectContext {
  public final static SetTurnAngleCmdAspectSetTurnAngleAspectContext INSTANCE = new SetTurnAngleCmdAspectSetTurnAngleAspectContext();
  
  public static SetTurnAngleCmdAspectSetTurnAngleAspectContext getInstance() {
    return INSTANCE;
  }
  
  private Map<SetTurnAngle,SetTurnAngleCmdAspectSetTurnAngleAspectProperties> map = new java.util.HashMap<robotG.robot.SetTurnAngle, robot.SetTurnAngleCmdAspectSetTurnAngleAspectProperties>();
  
  public Map<SetTurnAngle,SetTurnAngleCmdAspectSetTurnAngleAspectProperties> getMap() {
    return map;
  }
}
