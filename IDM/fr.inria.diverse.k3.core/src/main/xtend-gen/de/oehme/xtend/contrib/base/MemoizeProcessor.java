package de.oehme.xtend.contrib.base;

import com.google.common.base.Objects;
import de.oehme.xtend.contrib.base.MultipleParameterMethodMemoizer;
import de.oehme.xtend.contrib.base.ParamterlessMethodMemoizer;
import de.oehme.xtend.contrib.base.SingleParameterMethodMemoizer;
import java.util.List;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.TransformationParticipant;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableParameterDeclaration;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class MemoizeProcessor implements TransformationParticipant<MutableMethodDeclaration> {
  public void doTransform(final List<? extends MutableMethodDeclaration> methods, @Extension final TransformationContext context) {
    int _size = methods.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        final MutableMethodDeclaration it = methods.get((i).intValue());
        List<MutableParameterDeclaration> _parameters = it.getParameters();
        int _size_1 = _parameters.size();
        final int _switchValue = _size_1;
        boolean _matched = false;
        if (!_matched) {
          if (Objects.equal(_switchValue,0)) {
            _matched=true;
            ParamterlessMethodMemoizer _paramterlessMethodMemoizer = new ParamterlessMethodMemoizer(it, context, (i).intValue());
            _paramterlessMethodMemoizer.generate();
          }
        }
        if (!_matched) {
          if (Objects.equal(_switchValue,1)) {
            _matched=true;
            SingleParameterMethodMemoizer _singleParameterMethodMemoizer = new SingleParameterMethodMemoizer(it, context, (i).intValue());
            _singleParameterMethodMemoizer.generate();
          }
        }
        if (!_matched) {
          MultipleParameterMethodMemoizer _multipleParameterMethodMemoizer = new MultipleParameterMethodMemoizer(it, context, (i).intValue());
          _multipleParameterMethodMemoizer.generate();
        }
      }
    }
  }
}
