package de.oehme.xtend.contrib.base;

import com.google.common.base.Objects;
import de.oehme.xtend.contrib.base.ASTExtensions;
import java.util.List;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableInterfaceDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.declaration.Visibility;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class ExtractInterfaceProcessor extends AbstractClassProcessor {
  public void doRegisterGlobals(final ClassDeclaration cls, final RegisterGlobalsContext context) {
    String _qualifiedInterfaceName = this.qualifiedInterfaceName(cls);
    context.registerInterface(_qualifiedInterfaceName);
  }
  
  public void doTransform(final MutableClassDeclaration cls, @Extension final TransformationContext context) {
    String _qualifiedInterfaceName = this.qualifiedInterfaceName(cls);
    MutableInterfaceDeclaration _findInterface = context.findInterface(_qualifiedInterfaceName);
    final Procedure1<MutableInterfaceDeclaration> _function = new Procedure1<MutableInterfaceDeclaration>() {
      public void apply(final MutableInterfaceDeclaration iface) {
        Iterable<? extends MutableMethodDeclaration> _declaredMethods = cls.getDeclaredMethods();
        final Function1<MutableMethodDeclaration,Boolean> _function = new Function1<MutableMethodDeclaration,Boolean>() {
          public Boolean apply(final MutableMethodDeclaration it) {
            boolean _xblockexpression = false;
            {
              Visibility _visibility = it.getVisibility();
              /* Objects.equal(_visibility, Visibility.PUBLIC); */
              boolean _isStatic = it.isStatic();
              boolean _equals = (_isStatic == false);
              _xblockexpression = (_equals);
            }
            return Boolean.valueOf(_xblockexpression);
          }
        };
        Iterable<? extends MutableMethodDeclaration> _filter = IterableExtensions.filter(_declaredMethods, _function);
        final Procedure1<MutableMethodDeclaration> _function_1 = new Procedure1<MutableMethodDeclaration>() {
          public void apply(final MutableMethodDeclaration method) {
            String _simpleName = method.getSimpleName();
            final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
              public void apply(final MutableMethodDeclaration extracted) {
                Visibility _visibility = method.getVisibility();
                extracted.setVisibility(_visibility);
                TypeReference _returnType = method.getReturnType();
                extracted.setReturnType(_returnType);
                List<MutableParameterDeclaration> _parameters = method.getParameters();
                final Procedure1<MutableParameterDeclaration> _function = new Procedure1<MutableParameterDeclaration>() {
                  public void apply(final MutableParameterDeclaration it) {
                    String _simpleName = it.getSimpleName();
                    TypeReference _type = it.getType();
                    extracted.addParameter(_simpleName, _type);
                  }
                };
                IterableExtensions.<MutableParameterDeclaration>forEach(_parameters, _function);
                String _docComment = method.getDocComment();
                extracted.setDocComment(_docComment);
                List<TypeReference> _exceptions = method.getExceptions();
                extracted.setExceptions(((TypeReference[])Conversions.unwrapArray(_exceptions, TypeReference.class)));
              }
            };
            iface.addMethod(_simpleName, _function);
          }
        };
        IterableExtensions.forEach(_filter, _function_1);
      }
    };
    ObjectExtensions.<MutableInterfaceDeclaration>operator_doubleArrow(_findInterface, _function);
  }
  
  public String qualifiedInterfaceName(final ClassDeclaration cls) {
    StringConcatenation _builder = new StringConcatenation();
    String _packageName = ASTExtensions.packageName(cls);
    _builder.append(_packageName, "");
    _builder.append(".");
    String _simpleInterfaceName = this.simpleInterfaceName(cls);
    _builder.append(_simpleInterfaceName, "");
    return _builder.toString();
  }
  
  public String simpleInterfaceName(final ClassDeclaration cls) {
    String _xblockexpression = null;
    {
      final String simpleName = cls.getSimpleName();
      String _xifexpression = null;
      boolean _startsWith = simpleName.startsWith("Default");
      if (_startsWith) {
        String _substring = simpleName.substring(7);
        _xifexpression = _substring;
      } else {
        String _xifexpression_1 = null;
        boolean _endsWith = simpleName.endsWith("Impl");
        if (_endsWith) {
          int _length = simpleName.length();
          int _minus = (_length - 5);
          String _substring_1 = simpleName.substring(0, _minus);
          _xifexpression_1 = _substring_1;
        } else {
          IllegalArgumentException _illegalArgumentException = new IllegalArgumentException("Class name must start with \'Default\' or end with \'Impl\'");
          throw _illegalArgumentException;
        }
        _xifexpression = _xifexpression_1;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
}
