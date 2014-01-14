package de.oehme.xtend.contrib.base;

import de.oehme.xtend.contrib.base.ASTExtensions;
import de.oehme.xtend.contrib.base.Signature;
import java.util.List;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy.CompilationContext;
import org.eclipse.xtend.lib.macro.declaration.InterfaceDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.ParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.Type;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class DecoratorProcessor extends AbstractClassProcessor {
  public void doTransform(final MutableClassDeclaration cls, @Extension final TransformationContext context) {
    Type _findTypeGlobally = context.findTypeGlobally(CharSequence.class);
    final InterfaceDeclaration iface = ((InterfaceDeclaration) _findTypeGlobally);
    Iterable<? extends MethodDeclaration> _declaredMethods = iface.getDeclaredMethods();
    final Procedure1<MethodDeclaration> _function = new Procedure1<MethodDeclaration>() {
      public void apply(final MethodDeclaration declared) {
        Signature _signature = ASTExtensions.signature(declared);
        boolean _hasExecutable = ASTExtensions.hasExecutable(cls, _signature);
        boolean _not = (!_hasExecutable);
        if (_not) {
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              String _maybeReturn = DecoratorProcessor.this.maybeReturn(declared);
              _builder.append(_maybeReturn, "");
              _builder.append(" delegate.");
              String _simpleName = declared.getSimpleName();
              _builder.append(_simpleName, "");
              _builder.append("(");
              List<? extends ParameterDeclaration> _parameters = declared.getParameters();
              final Function1<ParameterDeclaration,String> _function = new Function1<ParameterDeclaration,String>() {
                public String apply(final ParameterDeclaration it) {
                  String _simpleName = it.getSimpleName();
                  return _simpleName;
                }
              };
              String _join = IterableExtensions.join(_parameters, ",", _function);
              _builder.append(_join, "");
              _builder.append(");");
              _builder.newLineIfNotEmpty();
              return _builder;
            }
          };
          ASTExtensions.addImplementationFor(cls, declared, _function);
        }
      }
    };
    IterableExtensions.forEach(_declaredMethods, _function);
    final Procedure1<MutableFieldDeclaration> _function_1 = new Procedure1<MutableFieldDeclaration>() {
      public void apply(final MutableFieldDeclaration it) {
        TypeReference _newTypeReference = context.newTypeReference(iface);
        it.setType(_newTypeReference);
      }
    };
    cls.addField("delegate", _function_1);
    boolean _hasDataConstructor = ASTExtensions.hasDataConstructor(cls);
    boolean _not = (!_hasDataConstructor);
    if (_not) {
      ASTExtensions.addDataConstructor(cls);
    }
  }
  
  public String maybeReturn(final MethodDeclaration declared) {
    String _xifexpression = null;
    TypeReference _returnType = declared.getReturnType();
    boolean _isVoid = _returnType.isVoid();
    boolean _not = (!_isVoid);
    if (_not) {
      _xifexpression = "return";
    }
    return _xifexpression;
  }
}
