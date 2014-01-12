package robot;

import fr.inria.triskell.k3.Aspect;
import fr.inria.triskell.k3.OverrideAspectMethod;
import robot.BipAspectBipAspectProperties;
import robot.ContextNCX;
import robot.NXCCodeGenerator;
import robotG.robot.Bip;

@Aspect(className = Bip.class)
@SuppressWarnings("all")
public class BipAspect extends NXCCodeGenerator {
  @OverrideAspectMethod
  public static StringBuilder generateCode(final Bip _self, final ContextNCX ctx) {
    robot.BipAspectBipAspectContext _instance = robot.BipAspectBipAspectContext.getInstance();
    				    java.util.Map<robotG.robot.Bip,robot.BipAspectBipAspectProperties> selfProp = _instance.getMap();
    					boolean _containsKey = selfProp.containsKey(_self);
    				    boolean _not = (!_containsKey);
    				    if (_not) {
      						robot.BipAspectBipAspectProperties prop = new robot.BipAspectBipAspectProperties();
    				   selfProp.put(_self, prop);
    			    }
    			     _self_ = selfProp.get(_self);
    			        if (_self instanceof robotG.robot.Bip){
    			     							return robot.BipAspect.privgenerateCode((robotG.robot.Bip)_self,ctx);
    			     							} else    if (_self instanceof java.lang.Object){
    			     							return robot.NXCCodeGenerator.privgenerateCode((java.lang.Object)_self,ctx);
    			     							} else 
    			      {
    			       										throw new IllegalArgumentException("Unhandled parameter types: " +
    			     							        java.util.Arrays.<Object>asList(_self).toString());
    			     					    } 
  }
  
  public static BipAspectBipAspectProperties _self_;
  
  private static StringBuilder super_generateCode(final Bip _self, final ContextNCX ctx) {
     return  robot.NXCCodeGenerator.privgenerateCode(_self,ctx);  
  }
  
  protected static StringBuilder privgenerateCode(final Bip _self, final ContextNCX ctx) {
    StringBuilder _xblockexpression = null;
    {
      StringBuilder _stringBuilder = new StringBuilder();
      final StringBuilder code = _stringBuilder;
      StringBuilder _append = code.append("PlayToneEx(");
      int _duration = _self.getDuration();
      StringBuilder _append_1 = _append.append(_duration);
      StringBuilder _append_2 = _append_1.append(",");
      int _power = _self.getPower();
      StringBuilder _append_3 = _append_2.append(_power);
      StringBuilder _append_4 = _append_3.append(",");
      boolean _isRepeat = _self.isRepeat();
      StringBuilder _append_5 = _append_4.append(_isRepeat);
      StringBuilder _append_6 = _append_5.append(");\n");
      _xblockexpression = (_append_6);
    }
    return _xblockexpression;
  }
}
