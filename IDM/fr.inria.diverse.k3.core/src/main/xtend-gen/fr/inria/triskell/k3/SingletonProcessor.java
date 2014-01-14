package fr.inria.triskell.k3;

import com.google.common.base.Objects;
import java.util.List;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy.CompilationContext;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableConstructorDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.declaration.Visibility;
import org.eclipse.xtend.lib.macro.expression.Expression;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class SingletonProcessor extends AbstractClassProcessor {
  public void doRegisterGlobals(final ClassDeclaration annotatedClass, final RegisterGlobalsContext context) {
  }
  
  public void doTransform(final MutableClassDeclaration annotatedClass, @Extension final TransformationContext context) {
    annotatedClass.setFinal(true);
    Iterable<? extends MutableConstructorDeclaration> _declaredConstructors = annotatedClass.getDeclaredConstructors();
    int _size = IterableExtensions.size(_declaredConstructors);
    boolean _greaterThan = (_size > 1);
    if (_greaterThan) {
      context.addError(annotatedClass, "More then one constructor is defined");
    }
    Iterable<? extends MutableConstructorDeclaration> _declaredConstructors_1 = annotatedClass.getDeclaredConstructors();
    final MutableConstructorDeclaration constructor = IterableExtensions.head(_declaredConstructors_1);
    List<MutableParameterDeclaration> _parameters = constructor.getParameters();
    int _size_1 = _parameters.size();
    boolean _greaterThan_1 = (_size_1 > 0);
    if (_greaterThan_1) {
      context.addError(constructor, "Constructor has arguments");
    }
    Expression _body = constructor.getBody();
    boolean _equals = Objects.equal(_body, null);
    if (_equals) {
      constructor.setVisibility(Visibility.PRIVATE);
      final CompilationStrategy _function = new CompilationStrategy() {
        public CharSequence compile(final CompilationContext it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("// singleton");
          return _builder;
        }
      };
      constructor.setBody(_function);
    } else {
      Visibility _visibility = constructor.getVisibility();
      boolean _notEquals = (!Objects.equal(_visibility, Visibility.PRIVATE));
      if (_notEquals) {
        context.addError(constructor, "Constructor is not private");
      }
    }
    final Procedure1<MutableFieldDeclaration> _function_1 = new Procedure1<MutableFieldDeclaration>() {
      public void apply(final MutableFieldDeclaration it) {
        it.setVisibility(Visibility.PRIVATE);
        it.setStatic(true);
        it.setFinal(true);
        TypeReference _newTypeReference = context.newTypeReference(annotatedClass);
        it.setType(_newTypeReference);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("new ");
            String _simpleName = annotatedClass.getSimpleName();
            _builder.append(_simpleName, "");
            _builder.append("()");
            return _builder;
          }
        };
        it.setInitializer(_function);
      }
    };
    annotatedClass.addField("INSTANCE", _function_1);
    final Procedure1<MutableMethodDeclaration> _function_2 = new Procedure1<MutableMethodDeclaration>() {
      public void apply(final MutableMethodDeclaration it) {
        it.setVisibility(Visibility.PUBLIC);
        it.setStatic(true);
        TypeReference _newTypeReference = context.newTypeReference(annotatedClass);
        it.setReturnType(_newTypeReference);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("return INSTANCE;");
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    annotatedClass.addMethod("getInstance", _function_2);
  }
}
