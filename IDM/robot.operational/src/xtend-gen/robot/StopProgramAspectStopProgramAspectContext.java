package robot;

import java.util.Map;
import robot.StopProgramAspectStopProgramAspectProperties;
import robotG.flow.StopProgram;

@SuppressWarnings("all")
public class StopProgramAspectStopProgramAspectContext {
  public final static StopProgramAspectStopProgramAspectContext INSTANCE = new StopProgramAspectStopProgramAspectContext();
  
  public static StopProgramAspectStopProgramAspectContext getInstance() {
    return INSTANCE;
  }
  
  private Map<StopProgram,StopProgramAspectStopProgramAspectProperties> map = new java.util.HashMap<robotG.flow.StopProgram, robot.StopProgramAspectStopProgramAspectProperties>();
  
  public Map<StopProgram,StopProgramAspectStopProgramAspectProperties> getMap() {
    return map;
  }
}
