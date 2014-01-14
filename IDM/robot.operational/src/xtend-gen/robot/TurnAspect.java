package robot;

import fr.inria.triskell.k3.Aspect;
import fr.inria.triskell.k3.OverrideAspectMethod;
import robot.ContextNCX;
import robot.NXCCodeGenerator;
import robot.TurnAspectTurnAspectProperties;
import robotG.robot.Turn;

@Aspect(className = Turn.class)
@SuppressWarnings("all")
public class TurnAspect extends NXCCodeGenerator {
  @OverrideAspectMethod
  public static StringBuilder generateCode(final Turn _self, final ContextNCX ctx) {
    robot.TurnAspectTurnAspectContext _instance = robot.TurnAspectTurnAspectContext.getInstance();
    				    java.util.Map<robotG.robot.Turn,robot.TurnAspectTurnAspectProperties> selfProp = _instance.getMap();
    					boolean _containsKey = selfProp.containsKey(_self);
    				    boolean _not = (!_containsKey);
    				    if (_not) {
      						robot.TurnAspectTurnAspectProperties prop = new robot.TurnAspectTurnAspectProperties();
    				   selfProp.put(_self, prop);
    			    }
    			     _self_ = selfProp.get(_self);
    			        if (_self instanceof robotG.robot.Turn){
    			     							return robot.TurnAspect.privgenerateCode((robotG.robot.Turn)_self,ctx);
    			     							} else    if (_self instanceof java.lang.Object){
    			     							return robot.NXCCodeGenerator.privgenerateCode((java.lang.Object)_self,ctx);
    			     							} else 
    			      {
    			       										throw new IllegalArgumentException("Unhandled parameter types: " +
    			     							        java.util.Arrays.<Object>asList(_self).toString());
    			     					    } 
  }
  
  public static TurnAspectTurnAspectProperties _self_;
  
  private static StringBuilder super_generateCode(final Turn _self, final ContextNCX ctx) {
     return  robot.NXCCodeGenerator.privgenerateCode(_self,ctx);  
  }
  
  protected static StringBuilder privgenerateCode(final Turn _self, final ContextNCX ctx) {
    StringBuilder _xblockexpression = null;
    {
      StringBuilder _stringBuilder = new StringBuilder();
      final StringBuilder code = _stringBuilder;
      StringBuilder _append = code.append("OnFwdSync(OUT_BC,");
      int _power = _self.getPower();
      StringBuilder _append_1 = _append.append(_power);
      StringBuilder _append_2 = _append_1.append(",-99);\n angle += ");
      int _angle = _self.getAngle();
      StringBuilder _append_3 = _append_2.append(_angle);
      StringBuilder _append_4 = _append_3.append(";\n Wait(20);\n");
      _xblockexpression = (_append_4);
    }
    return _xblockexpression;
  }
}
