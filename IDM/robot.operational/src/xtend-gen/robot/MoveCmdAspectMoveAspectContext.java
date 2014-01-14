package robot;

import java.util.Map;
import robot.MoveCmdAspectMoveAspectProperties;
import robotG.robot.Move;

@SuppressWarnings("all")
public class MoveCmdAspectMoveAspectContext {
  public final static MoveCmdAspectMoveAspectContext INSTANCE = new MoveCmdAspectMoveAspectContext();
  
  public static MoveCmdAspectMoveAspectContext getInstance() {
    return INSTANCE;
  }
  
  private Map<Move,MoveCmdAspectMoveAspectProperties> map = new java.util.HashMap<robotG.robot.Move, robot.MoveCmdAspectMoveAspectProperties>();
  
  public Map<Move,MoveCmdAspectMoveAspectProperties> getMap() {
    return map;
  }
}
