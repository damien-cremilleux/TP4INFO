package robot;

import fr.inria.triskell.k3.Aspect;
import fr.inria.triskell.k3.OverrideAspectMethod;
import robot.ContextNCX;
import robot.NXCCodeGenerator;
import robot.SetTurnAngleCmdAspectSetTurnAngleAspectProperties;
import robotG.robot.SetTurnAngle;

@Aspect(className = SetTurnAngle.class)
@SuppressWarnings("all")
public class SetTurnAngleCmdAspect extends NXCCodeGenerator {
  @OverrideAspectMethod
  public static StringBuilder generateCode(final SetTurnAngle _self, final ContextNCX ctx) {
    robot.SetTurnAngleCmdAspectSetTurnAngleAspectContext _instance = robot.SetTurnAngleCmdAspectSetTurnAngleAspectContext.getInstance();
    				    java.util.Map<robotG.robot.SetTurnAngle,robot.SetTurnAngleCmdAspectSetTurnAngleAspectProperties> selfProp = _instance.getMap();
    					boolean _containsKey = selfProp.containsKey(_self);
    				    boolean _not = (!_containsKey);
    				    if (_not) {
      						robot.SetTurnAngleCmdAspectSetTurnAngleAspectProperties prop = new robot.SetTurnAngleCmdAspectSetTurnAngleAspectProperties();
    				   selfProp.put(_self, prop);
    			    }
    			     _self_ = selfProp.get(_self);
    			        if (_self instanceof robotG.robot.SetTurnAngle){
    			     							return robot.SetTurnAngleCmdAspect.privgenerateCode((robotG.robot.SetTurnAngle)_self,ctx);
    			     							} else    if (_self instanceof java.lang.Object){
    			     							return robot.NXCCodeGenerator.privgenerateCode((java.lang.Object)_self,ctx);
    			     							} else 
    			      {
    			       										throw new IllegalArgumentException("Unhandled parameter types: " +
    			     							        java.util.Arrays.<Object>asList(_self).toString());
    			     					    } 
  }
  
  public static SetTurnAngleCmdAspectSetTurnAngleAspectProperties _self_;
  
  private static StringBuilder super_generateCode(final SetTurnAngle _self, final ContextNCX ctx) {
     return  robot.NXCCodeGenerator.privgenerateCode(_self,ctx);  
  }
  
  protected static StringBuilder privgenerateCode(final SetTurnAngle _self, final ContextNCX ctx) {
    StringBuilder _xblockexpression = null;
    {
      StringBuilder _stringBuilder = new StringBuilder();
      final StringBuilder code = _stringBuilder;
      boolean _isAngleVarCreated = ctx.isAngleVarCreated();
      boolean _not = (!_isAngleVarCreated);
      if (_not) {
        ctx.setAngleVarCreated(true);
        StringBuilder _header = ctx.getHeader();
        _header.append("int angle;\n");
      }
      StringBuilder _append = code.append("angle=");
      int _angle = _self.getAngle();
      int _intValue = Integer.valueOf(_angle).intValue();
      StringBuilder _append_1 = _append.append(_intValue);
      StringBuilder _append_2 = _append_1.append(";\n");
      _xblockexpression = (_append_2);
    }
    return _xblockexpression;
  }
}
