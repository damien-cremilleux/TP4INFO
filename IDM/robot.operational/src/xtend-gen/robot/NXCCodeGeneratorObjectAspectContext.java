package robot;

import java.util.Map;
import robot.NXCCodeGeneratorObjectAspectProperties;

@SuppressWarnings("all")
public class NXCCodeGeneratorObjectAspectContext {
  public final static NXCCodeGeneratorObjectAspectContext INSTANCE = new NXCCodeGeneratorObjectAspectContext();
  
  public static NXCCodeGeneratorObjectAspectContext getInstance() {
    return INSTANCE;
  }
  
  private Map<Object,NXCCodeGeneratorObjectAspectProperties> map = new java.util.HashMap<java.lang.Object, robot.NXCCodeGeneratorObjectAspectProperties>();
  
  public Map<Object,NXCCodeGeneratorObjectAspectProperties> getMap() {
    return map;
  }
}
