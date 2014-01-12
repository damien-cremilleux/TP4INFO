package robot;

import fr.inria.triskell.k3.Aspect;
import fr.inria.triskell.k3.OverrideAspectMethod;
import robot.ContextNCX;
import robot.NXCCodeGenerator;
import robot.StopProgramAspectStopProgramAspectProperties;
import robotG.flow.StopProgram;

@Aspect(className = StopProgram.class)
@SuppressWarnings("all")
public class StopProgramAspect extends NXCCodeGenerator {
  @OverrideAspectMethod
  public static StringBuilder generateCode(final StopProgram _self, final ContextNCX ctx) {
    robot.StopProgramAspectStopProgramAspectContext _instance = robot.StopProgramAspectStopProgramAspectContext.getInstance();
    				    java.util.Map<robotG.flow.StopProgram,robot.StopProgramAspectStopProgramAspectProperties> selfProp = _instance.getMap();
    					boolean _containsKey = selfProp.containsKey(_self);
    				    boolean _not = (!_containsKey);
    				    if (_not) {
      						robot.StopProgramAspectStopProgramAspectProperties prop = new robot.StopProgramAspectStopProgramAspectProperties();
    				   selfProp.put(_self, prop);
    			    }
    			     _self_ = selfProp.get(_self);
    			        if (_self instanceof robotG.flow.StopProgram){
    			     							return robot.StopProgramAspect.privgenerateCode((robotG.flow.StopProgram)_self,ctx);
    			     							} else    if (_self instanceof java.lang.Object){
    			     							return robot.NXCCodeGenerator.privgenerateCode((java.lang.Object)_self,ctx);
    			     							} else 
    			      {
    			       										throw new IllegalArgumentException("Unhandled parameter types: " +
    			     							        java.util.Arrays.<Object>asList(_self).toString());
    			     					    } 
  }
  
  public static StopProgramAspectStopProgramAspectProperties _self_;
  
  private static StringBuilder super_generateCode(final StopProgram _self, final ContextNCX ctx) {
     return  robot.NXCCodeGenerator.privgenerateCode(_self,ctx);  
  }
  
  protected static StringBuilder privgenerateCode(final StopProgram _self, final ContextNCX ctx) {
    StringBuilder _xblockexpression = null;
    {
      StringBuilder _stringBuilder = new StringBuilder();
      final StringBuilder code = _stringBuilder;
      StringBuilder _append = code.append("Stop(true);\n");
      _xblockexpression = (_append);
    }
    return _xblockexpression;
  }
}
