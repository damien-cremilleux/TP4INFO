package robot;

import java.util.Map;
import robot.ProgramUnitAspectProgrammeAspectProperties;
import robotG.flow.Programme;

@SuppressWarnings("all")
public class ProgramUnitAspectProgrammeAspectContext {
  public final static ProgramUnitAspectProgrammeAspectContext INSTANCE = new ProgramUnitAspectProgrammeAspectContext();
  
  public static ProgramUnitAspectProgrammeAspectContext getInstance() {
    return INSTANCE;
  }
  
  private Map<Programme,ProgramUnitAspectProgrammeAspectProperties> map = new java.util.HashMap<robotG.flow.Programme, robot.ProgramUnitAspectProgrammeAspectProperties>();
  
  public Map<Programme,ProgramUnitAspectProgrammeAspectProperties> getMap() {
    return map;
  }
}
