package robot;

import fr.inria.triskell.k3.Aspect;
import fr.inria.triskell.k3.OverrideAspectMethod;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import robot.ContextNCX;
import robot.NXCCodeGenerator;
import robot.ProgramUnitAspectProgrammeAspectProperties;
import robotG.flow.Expr;
import robotG.flow.Programme;

@Aspect(className = Programme.class)
@SuppressWarnings("all")
public class ProgramUnitAspect extends NXCCodeGenerator {
  @OverrideAspectMethod
  public static StringBuilder generateCode(final Programme _self, final ContextNCX ctx) {
    robot.ProgramUnitAspectProgrammeAspectContext _instance = robot.ProgramUnitAspectProgrammeAspectContext.getInstance();
    				    java.util.Map<robotG.flow.Programme,robot.ProgramUnitAspectProgrammeAspectProperties> selfProp = _instance.getMap();
    					boolean _containsKey = selfProp.containsKey(_self);
    				    boolean _not = (!_containsKey);
    				    if (_not) {
      						robot.ProgramUnitAspectProgrammeAspectProperties prop = new robot.ProgramUnitAspectProgrammeAspectProperties();
    				   selfProp.put(_self, prop);
    			    }
    			     _self_ = selfProp.get(_self);
    			        if (_self instanceof robotG.flow.Programme){
    			     							return robot.ProgramUnitAspect.privgenerateCode((robotG.flow.Programme)_self,ctx);
    			     							} else    if (_self instanceof java.lang.Object){
    			     							return robot.NXCCodeGenerator.privgenerateCode((java.lang.Object)_self,ctx);
    			     							} else 
    			      {
    			       										throw new IllegalArgumentException("Unhandled parameter types: " +
    			     							        java.util.Arrays.<Object>asList(_self).toString());
    			     					    } 
  }
  
  public static ProgramUnitAspectProgrammeAspectProperties _self_;
  
  private static StringBuilder super_generateCode(final Programme _self, final ContextNCX ctx) {
     return  robot.NXCCodeGenerator.privgenerateCode(_self,ctx);  
  }
  
  protected static StringBuilder privgenerateCode(final Programme _self, final ContextNCX ctx) {
    StringBuilder _xblockexpression = null;
    {
      StringBuilder _header = ctx.getHeader();
      _header.append("#include \"NXCDefs.h\"\n\ntask main(){\n");
      StringBuilder _stringBuilder = new StringBuilder("while(1){\n");
      EList<Expr> _programme = _self.getProgramme();
      final Function1<Expr,StringBuilder> _function = new Function1<Expr,StringBuilder>() {
        public StringBuilder apply(final Expr it) {
          StringBuilder _generateCode = NXCCodeGenerator.generateCode(it, ctx);
          return _generateCode;
        }
      };
      List<StringBuilder> _map = ListExtensions.<Expr, StringBuilder>map(_programme, _function);
      String _join = IterableExtensions.join(_map);
      StringBuilder _append = _stringBuilder.append(_join);
      StringBuilder _append_1 = _append.append("}\n}\n");
      _xblockexpression = (_append_1);
    }
    return _xblockexpression;
  }
}
