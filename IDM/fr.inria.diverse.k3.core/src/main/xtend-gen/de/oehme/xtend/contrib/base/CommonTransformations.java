package de.oehme.xtend.contrib.base;

import de.oehme.xtend.contrib.base.ASTExtensions;
import de.oehme.xtend.contrib.base.Signature;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy.CompilationContext;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

/**
 * Transformations that are commonly used during active annotation processing
 */
@SuppressWarnings("all")
public class CommonTransformations {
  @Extension
  private TransformationContext delegate;
  
  public CommonTransformations(final TransformationContext delegate) {
    this.delegate = delegate;
  }
  
  public boolean hasToString(final MutableClassDeclaration cls) {
    Signature _signature = ASTExtensions.signature("toString");
    boolean _hasExecutable = ASTExtensions.hasExecutable(cls, _signature);
    return _hasExecutable;
  }
  
  /**
   * Adds a toString method that prints all persistent fields of this class
   */
  public MutableMethodDeclaration addDataToString(final MutableClassDeclaration cls) {
    final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
      public void apply(final MutableMethodDeclaration it) {
        TypeReference _string = CommonTransformations.this.delegate.getString();
        it.setReturnType(_string);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(@Extension final CompilationContext ctx) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("return ");
            TypeReference _guavaObjects = CommonTransformations.this.guavaObjects();
            String _javaCode = ctx.toJavaCode(_guavaObjects);
            _builder.append(_javaCode, "");
            _builder.append(".toStringHelper(");
            String _simpleName = cls.getSimpleName();
            _builder.append(_simpleName, "");
            _builder.append(".class)");
            _builder.newLineIfNotEmpty();
            {
              Iterable<? extends MutableFieldDeclaration> _declaredFields = cls.getDeclaredFields();
              for(final MutableFieldDeclaration a : _declaredFields) {
                _builder.append(".add(\"");
                String _simpleName_1 = a.getSimpleName();
                _builder.append(_simpleName_1, "");
                _builder.append("\",");
                String _simpleName_2 = a.getSimpleName();
                _builder.append(_simpleName_2, "");
                _builder.append(")");
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append(".toString();");
            _builder.newLine();
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    MutableMethodDeclaration _addMethod = cls.addMethod("toString", _function);
    return _addMethod;
  }
  
  public boolean hasEquals(final MutableClassDeclaration cls) {
    TypeReference _object = this.delegate.getObject();
    Signature _signature = ASTExtensions.signature("equals", _object);
    boolean _hasExecutable = ASTExtensions.hasExecutable(cls, _signature);
    return _hasExecutable;
  }
  
  /**
   * Adds an equals method that compares all persistent fields of this class
   */
  public MutableMethodDeclaration addDataEquals(final MutableClassDeclaration cls) {
    final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
      public void apply(final MutableMethodDeclaration it) {
        TypeReference _primitiveBoolean = CommonTransformations.this.delegate.getPrimitiveBoolean();
        it.setReturnType(_primitiveBoolean);
        TypeReference _object = CommonTransformations.this.delegate.getObject();
        it.addParameter("o", _object);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(@Extension final CompilationContext ctx) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("if (o instanceof ");
            String _simpleName = cls.getSimpleName();
            _builder.append(_simpleName, "");
            _builder.append(") {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            String _simpleName_1 = cls.getSimpleName();
            _builder.append(_simpleName_1, "	");
            _builder.append(" other = (");
            String _simpleName_2 = cls.getSimpleName();
            _builder.append(_simpleName_2, "	");
            _builder.append(") o;");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("return ");
            Iterable<? extends MutableFieldDeclaration> _persistentState = ASTExtensions.persistentState(cls);
            final Function1<MutableFieldDeclaration,String> _function = new Function1<MutableFieldDeclaration,String>() {
              public String apply(final MutableFieldDeclaration it) {
                StringConcatenation _builder = new StringConcatenation();
                TypeReference _guavaObjects = CommonTransformations.this.guavaObjects();
                String _javaCode = ctx.toJavaCode(_guavaObjects);
                _builder.append(_javaCode, "");
                _builder.append(".equal(");
                String _simpleName = it.getSimpleName();
                _builder.append(_simpleName, "");
                _builder.append(", other.");
                String _simpleName_1 = it.getSimpleName();
                _builder.append(_simpleName_1, "");
                _builder.append(")");
                return _builder.toString();
              }
            };
            String _join = IterableExtensions.join(_persistentState, "\n&& ", _function);
            _builder.append(_join, "	");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            _builder.append("}");
            _builder.newLine();
            _builder.append("return false;");
            _builder.newLine();
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    MutableMethodDeclaration _addMethod = cls.addMethod("equals", _function);
    return _addMethod;
  }
  
  public boolean hasHashCode(final MutableClassDeclaration cls) {
    Signature _signature = ASTExtensions.signature("hashCode");
    boolean _hasExecutable = ASTExtensions.hasExecutable(cls, _signature);
    return _hasExecutable;
  }
  
  /**
   * Adds a hashCode method that takes all persistent fields of this class
   */
  public MutableMethodDeclaration addDataHashCode(final MutableClassDeclaration cls) {
    final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
      public void apply(final MutableMethodDeclaration it) {
        TypeReference _primitiveInt = CommonTransformations.this.delegate.getPrimitiveInt();
        it.setReturnType(_primitiveInt);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(@Extension final CompilationContext ctx) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("return ");
            TypeReference _guavaObjects = CommonTransformations.this.guavaObjects();
            String _javaCode = ctx.toJavaCode(_guavaObjects);
            _builder.append(_javaCode, "");
            _builder.append(".hashCode(");
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
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    MutableMethodDeclaration _addMethod = cls.addMethod("hashCode", _function);
    return _addMethod;
  }
  
  private TypeReference guavaObjects() {
    TypeReference _newTypeReference = this.delegate.newTypeReference("com.google.common.base.Objects");
    return _newTypeReference;
  }
}
