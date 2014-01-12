package de.oehme.xtend.contrib.base;

import com.google.common.base.Objects;
import de.oehme.xtend.contrib.base.ASTExtensions;
import de.oehme.xtend.contrib.base.CommonTransformations;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy.CompilationContext;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.expression.Expression;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class ValueObjectProcessor extends AbstractClassProcessor {
  public void doRegisterGlobals(final ClassDeclaration cls, final RegisterGlobalsContext context) {
    String _builderClassName = this.builderClassName(cls);
    context.registerClass(_builderClassName);
  }
  
  public void doTransform(final MutableClassDeclaration cls, @Extension final TransformationContext context) {
    CommonTransformations _commonTransformations = new CommonTransformations(context);
    @Extension
    final CommonTransformations transformations = _commonTransformations;
    TypeReference _extendedClass = cls.getExtendedClass();
    TypeReference _object = context.getObject();
    boolean _notEquals = (!Objects.equal(_extendedClass, _object));
    if (_notEquals) {
      context.addError(cls, "Inheritance does not play well with immutability");
    }
    cls.setFinal(true);
    MutableClassDeclaration _builderClass = this.builderClass(cls, context);
    final Procedure1<MutableClassDeclaration> _function = new Procedure1<MutableClassDeclaration>() {
      public void apply(final MutableClassDeclaration it) {
        it.setFinal(true);
        final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
          public void apply(final MutableMethodDeclaration it) {
            TypeReference _newTypeReference = context.newTypeReference(cls);
            it.setReturnType(_newTypeReference);
            final CompilationStrategy _function = new CompilationStrategy() {
              public CharSequence compile(final CompilationContext it) {
                StringConcatenation _builder = new StringConcatenation();
                _builder.append("return new ");
                String _simpleName = cls.getSimpleName();
                _builder.append(_simpleName, "");
                _builder.append("(");
                Iterable<? extends MutableFieldDeclaration> _persistentState = ASTExtensions.persistentState(cls);
                final Function1<MutableFieldDeclaration,String> _function = new Function1<MutableFieldDeclaration,String>() {
                  public String apply(final MutableFieldDeclaration it) {
                    String _simpleName = it.getSimpleName();
                    return _simpleName;
                  }
                };
                String _join = IterableExtensions.join(_persistentState, ",", _function);
                _builder.append(_join, "");
                _builder.append(");");
                _builder.newLineIfNotEmpty();
                return _builder;
              }
            };
            it.setBody(_function);
          }
        };
        it.addMethod("build", _function);
        Iterable<? extends MutableFieldDeclaration> _persistentState = ASTExtensions.persistentState(cls);
        final Procedure1<MutableFieldDeclaration> _function_1 = new Procedure1<MutableFieldDeclaration>() {
          public void apply(final MutableFieldDeclaration field) {
            String _simpleName = field.getSimpleName();
            final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
              public void apply(final MutableMethodDeclaration it) {
                String _simpleName = field.getSimpleName();
                TypeReference _type = field.getType();
                it.addParameter(_simpleName, _type);
                MutableClassDeclaration _builderClass = ValueObjectProcessor.this.builderClass(cls, context);
                TypeReference _newTypeReference = context.newTypeReference(_builderClass);
                it.setReturnType(_newTypeReference);
                final CompilationStrategy _function = new CompilationStrategy() {
                  public CharSequence compile(final CompilationContext it) {
                    StringConcatenation _builder = new StringConcatenation();
                    _builder.append("this.");
                    String _simpleName = field.getSimpleName();
                    _builder.append(_simpleName, "");
                    _builder.append(" = ");
                    String _simpleName_1 = field.getSimpleName();
                    _builder.append(_simpleName_1, "");
                    _builder.append(";");
                    _builder.newLineIfNotEmpty();
                    _builder.append("return this;");
                    _builder.newLine();
                    return _builder;
                  }
                };
                it.setBody(_function);
              }
            };
            it.addMethod(_simpleName, _function);
            String _simpleName_1 = field.getSimpleName();
            final Procedure1<MutableFieldDeclaration> _function_1 = new Procedure1<MutableFieldDeclaration>() {
              public void apply(final MutableFieldDeclaration it) {
                TypeReference _type = field.getType();
                it.setType(_type);
              }
            };
            it.addField(_simpleName_1, _function_1);
          }
        };
        IterableExtensions.forEach(_persistentState, _function_1);
      }
    };
    final MutableClassDeclaration builder = ObjectExtensions.<MutableClassDeclaration>operator_doubleArrow(_builderClass, _function);
    final Procedure1<MutableMethodDeclaration> _function_1 = new Procedure1<MutableMethodDeclaration>() {
      public void apply(final MutableMethodDeclaration it) {
        it.setStatic(true);
        TypeReference _newTypeReference = context.newTypeReference(cls);
        it.setReturnType(_newTypeReference);
        TypeReference _newTypeReference_1 = context.newTypeReference(builder);
        TypeReference _newTypeReference_2 = context.newTypeReference(Procedure1.class, _newTypeReference_1);
        it.addParameter("init", _newTypeReference_2);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            String _builderClassName = ValueObjectProcessor.this.builderClassName(cls);
            _builder.append(_builderClassName, "");
            _builder.append(" builder = builder();");
            _builder.newLineIfNotEmpty();
            _builder.append("init.apply(builder);");
            _builder.newLine();
            _builder.append("return builder.build();");
            _builder.newLine();
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    cls.addMethod("build", _function_1);
    final Procedure1<MutableMethodDeclaration> _function_2 = new Procedure1<MutableMethodDeclaration>() {
      public void apply(final MutableMethodDeclaration it) {
        MutableClassDeclaration _builderClass = ValueObjectProcessor.this.builderClass(cls, context);
        TypeReference _newTypeReference = context.newTypeReference(_builderClass);
        it.setReturnType(_newTypeReference);
        it.setStatic(true);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("return new ");
            String _builderClassName = ValueObjectProcessor.this.builderClassName(cls);
            _builder.append(_builderClassName, "");
            _builder.append("();");
            _builder.newLineIfNotEmpty();
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    cls.addMethod("builder", _function_2);
    Iterable<? extends MutableFieldDeclaration> _persistentState = ASTExtensions.persistentState(cls);
    final Procedure1<MutableFieldDeclaration> _function_3 = new Procedure1<MutableFieldDeclaration>() {
      public void apply(final MutableFieldDeclaration field) {
        ASTExtensions.addGetter(field);
        String _simpleName = field.getSimpleName();
        final Procedure1<MutableFieldDeclaration> _function = new Procedure1<MutableFieldDeclaration>() {
          public void apply(final MutableFieldDeclaration it) {
            TypeReference _type = field.getType();
            it.setType(_type);
            Expression _initializer = field.getInitializer();
            it.setInitializer(_initializer);
          }
        };
        cls.addField(_simpleName, _function);
        field.remove();
      }
    };
    IterableExtensions.forEach(_persistentState, _function_3);
    boolean _hasDataConstructor = ASTExtensions.hasDataConstructor(cls);
    boolean _not = (!_hasDataConstructor);
    if (_not) {
      ASTExtensions.addDataConstructor(cls);
    }
    boolean _hasEquals = transformations.hasEquals(cls);
    boolean _not_1 = (!_hasEquals);
    if (_not_1) {
      transformations.addDataEquals(cls);
    }
    boolean _hasHashCode = transformations.hasHashCode(cls);
    boolean _not_2 = (!_hasHashCode);
    if (_not_2) {
      transformations.addDataHashCode(cls);
    }
    boolean _hasToString = transformations.hasToString(cls);
    boolean _not_3 = (!_hasToString);
    if (_not_3) {
      transformations.addDataToString(cls);
    }
  }
  
  public String builderClassName(final ClassDeclaration cls) {
    String _qualifiedName = cls.getQualifiedName();
    String _plus = (_qualifiedName + "Builder");
    return _plus;
  }
  
  public MutableClassDeclaration builderClass(final ClassDeclaration cls, @Extension final TransformationContext ctx) {
    String _builderClassName = this.builderClassName(cls);
    MutableClassDeclaration _findClass = ctx.findClass(_builderClassName);
    return _findClass;
  }
}
