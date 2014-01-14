package robot;

import fr.inria.triskell.k3.Aspect;
import fr.inria.triskell.k3.OverrideAspectMethod;
import robot.ContextNCX;
import robot.MoveCmdAspectMoveAspectProperties;
import robot.NXCCodeGenerator;
import robotG.robot.Move;

@Aspect(className = Move.class)
@SuppressWarnings("all")
public class MoveCmdAspect extends NXCCodeGenerator {
  @OverrideAspectMethod
  public static StringBuilder generateCode(final Move _self, final ContextNCX ctx) {
    robot.MoveCmdAspectMoveAspectContext _instance = robot.MoveCmdAspectMoveAspectContext.getInstance();
    				    java.util.Map<robotG.robot.Move,robot.MoveCmdAspectMoveAspectProperties> selfProp = _instance.getMap();
    					boolean _containsKey = selfProp.containsKey(_self);
    				    boolean _not = (!_containsKey);
    				    if (_not) {
      						robot.MoveCmdAspectMoveAspectProperties prop = new robot.MoveCmdAspectMoveAspectProperties();
    				   selfProp.put(_self, prop);
    			    }
    			     _self_ = selfProp.get(_self);
    			        if (_self instanceof robotG.robot.Move){
    			     							return robot.MoveCmdAspect.privgenerateCode((robotG.robot.Move)_self,ctx);
    			     							} else    if (_self instanceof java.lang.Object){
    			     							return robot.NXCCodeGenerator.privgenerateCode((java.lang.Object)_self,ctx);
    			     							} else 
    			      {
    			       										throw new IllegalArgumentException("Unhandled parameter types: " +
    			     							        java.util.Arrays.<Object>asList(_self).toString());
    			     					    } 
  }
  
  public static MoveCmdAspectMoveAspectProperties _self_;
  
  private static StringBuilder super_generateCode(final Move _self, final ContextNCX ctx) {
     return  robot.NXCCodeGenerator.privgenerateCode(_self,ctx);  
  }
  
  protected static StringBuilder privgenerateCode(final Move _self, final ContextNCX ctx) {
    StringBuilder _stringBuilder = new StringBuilder("OnFwdSync(OUT_BC, ");
    int _power = _self.getPower();
    int _intValue = Integer.valueOf(_power).intValue();
    StringBuilder _append = _stringBuilder.append(_intValue);
    StringBuilder _append_1 = _append.append(", 0);\n");
    return _append_1;
  }
}
