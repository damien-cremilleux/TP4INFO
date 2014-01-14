package de.oehme.xtend.contrib.base;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import de.oehme.xtend.contrib.base.Signature;
import java.util.Arrays;
import java.util.List;
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy.CompilationContext;
import org.eclipse.xtend.lib.macro.declaration.ExecutableDeclaration;
import org.eclipse.xtend.lib.macro.declaration.FieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MemberDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableConstructorDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMemberDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableTypeParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.ParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.declaration.Visibility;
import org.eclipse.xtend.lib.macro.expression.Expression;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * Extension methods that help you inspect and manipulate
 * the Java AST during Xtend active annotation processing
 */
@SuppressWarnings("all")
public class ASTExtensions {
  public static Signature signature(final ExecutableDeclaration it) {
    String _simpleName = it.getSimpleName();
    List<? extends ParameterDeclaration> _parameters = it.getParameters();
    final Function1<ParameterDeclaration,TypeReference> _function = new Function1<ParameterDeclaration,TypeReference>() {
      public TypeReference apply(final ParameterDeclaration p) {
        TypeReference _type = p.getType();
        return _type;
      }
    };
    List<TypeReference> _map = ListExtensions.map(_parameters, _function);
    Signature _signature = ASTExtensions.signature(_simpleName, ((TypeReference[])Conversions.unwrapArray(_map, TypeReference.class)));
    return _signature;
  }
  
  public static Signature constructorSignature(final ClassDeclaration cls, final TypeReference... params) {
    String _simpleName = cls.getSimpleName();
    Signature _signature = ASTExtensions.signature(_simpleName, params);
    return _signature;
  }
  
  public static Signature signature(final String name, final TypeReference... params) {
    ImmutableList<TypeReference> _copyOf = ImmutableList.<TypeReference>copyOf(params);
    Signature _signature = new Signature(name, _copyOf);
    return _signature;
  }
  
  public static boolean hasExecutable(final ClassDeclaration cls, final Signature sig) {
    Iterable<? extends MemberDeclaration> _declaredMembers = cls.getDeclaredMembers();
    Iterable<ExecutableDeclaration> _filter = Iterables.<ExecutableDeclaration>filter(_declaredMembers, ExecutableDeclaration.class);
    final Function1<ExecutableDeclaration,Boolean> _function = new Function1<ExecutableDeclaration,Boolean>() {
      public Boolean apply(final ExecutableDeclaration it) {
        Signature _signature = ASTExtensions.signature(it);
        boolean _equals = Objects.equal(_signature, sig);
        return Boolean.valueOf(_equals);
      }
    };
    boolean _exists = IterableExtensions.<ExecutableDeclaration>exists(_filter, _function);
    return _exists;
  }
  
  public static boolean hasDataConstructor(final ClassDeclaration cls) {
    Iterable<? extends FieldDeclaration> _persistentState = ASTExtensions.persistentState(cls);
    final Function1<FieldDeclaration,TypeReference> _function = new Function1<FieldDeclaration,TypeReference>() {
      public TypeReference apply(final FieldDeclaration it) {
        TypeReference _type = it.getType();
        return _type;
      }
    };
    Iterable<TypeReference> _map = IterableExtensions.map(_persistentState, _function);
    Signature _constructorSignature = ASTExtensions.constructorSignature(cls, ((TypeReference[])Conversions.unwrapArray(_map, TypeReference.class)));
    boolean _hasExecutable = ASTExtensions.hasExecutable(cls, _constructorSignature);
    return _hasExecutable;
  }
  
  /**
   * Adds a constructor that takes all non-transient fields of this class.
   */
  public static MutableConstructorDeclaration addDataConstructor(final MutableClassDeclaration cls) {
    final Procedure1<MutableConstructorDeclaration> _function = new Procedure1<MutableConstructorDeclaration>() {
      public void apply(final MutableConstructorDeclaration it) {
        final Iterable<? extends MutableFieldDeclaration> fields = ASTExtensions.persistentState(cls);
        final Procedure1<MutableFieldDeclaration> _function = new Procedure1<MutableFieldDeclaration>() {
          public void apply(final MutableFieldDeclaration f) {
            String _simpleName = f.getSimpleName();
            TypeReference _type = f.getType();
            it.addParameter(_simpleName, _type);
          }
        };
        IterableExtensions.forEach(fields, _function);
        final CompilationStrategy _function_1 = new CompilationStrategy() {
          public CharSequence compile(final CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            {
              for(final MutableFieldDeclaration f : fields) {
                _builder.append("this.");
                String _simpleName = f.getSimpleName();
                _builder.append(_simpleName, "");
                _builder.append(" = ");
                String _simpleName_1 = f.getSimpleName();
                _builder.append(_simpleName_1, "");
                _builder.append(";");
                _builder.newLineIfNotEmpty();
              }
            }
            return _builder;
          }
        };
        it.setBody(_function_1);
      }
    };
    MutableConstructorDeclaration _addConstructor = cls.addConstructor(_function);
    return _addConstructor;
  }
  
  /**
   * Copies the header of the given base method so that you only have to add a body in most cases.
   * You are free to modify the default settings, of course, e.g. widening the visibility of the
   * implementing method.
   */
  public static MutableMethodDeclaration addImplementationFor(final MutableClassDeclaration cls, final MethodDeclaration baseMethod, final CompilationStrategy implementation) {
    MutableMethodDeclaration _xblockexpression = null;
    {
      String _simpleName = baseMethod.getSimpleName();
      final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          Visibility _visibility = baseMethod.getVisibility();
          it.setVisibility(_visibility);
          TypeReference _returnType = baseMethod.getReturnType();
          it.setReturnType(_returnType);
          List<TypeReference> _exceptions = baseMethod.getExceptions();
          it.setExceptions(((TypeReference[])Conversions.unwrapArray(_exceptions, TypeReference.class)));
          Iterable<? extends TypeParameterDeclaration> _typeParameters = baseMethod.getTypeParameters();
          final Procedure1<TypeParameterDeclaration> _function = new Procedure1<TypeParameterDeclaration>() {
            public void apply(final TypeParameterDeclaration p) {
              String _simpleName = p.getSimpleName();
              Iterable<? extends TypeReference> _upperBounds = p.getUpperBounds();
              it.addTypeParameter(_simpleName, ((TypeReference[])Conversions.unwrapArray(_upperBounds, TypeReference.class)));
            }
          };
          IterableExtensions.forEach(_typeParameters, _function);
          List<? extends ParameterDeclaration> _parameters = baseMethod.getParameters();
          final Procedure1<ParameterDeclaration> _function_1 = new Procedure1<ParameterDeclaration>() {
            public void apply(final ParameterDeclaration p) {
              String _simpleName = p.getSimpleName();
              TypeReference _type = p.getType();
              it.addParameter(_simpleName, _type);
            }
          };
          IterableExtensions.forEach(_parameters, _function_1);
          boolean _isVarArgs = baseMethod.isVarArgs();
          it.setVarArgs(_isVarArgs);
          String _docComment = baseMethod.getDocComment();
          it.setDocComment(_docComment);
          it.setBody(implementation);
        }
      };
      final MutableMethodDeclaration method = cls.addMethod(_simpleName, _function);
      _xblockexpression = (method);
    }
    return _xblockexpression;
  }
  
  /**
   * Moves the body of this method to a new private method with the given name.
   * The original method then gets the newly specified body which can delegate to the inner method.
   * @return the inner method.
   */
  public static MutableMethodDeclaration addIndirection(final MutableMethodDeclaration wrapper, final String innerMethodName, final CompilationStrategy indirection) {
    MutableMethodDeclaration _xblockexpression = null;
    {
      MutableTypeDeclaration _declaringType = wrapper.getDeclaringType();
      final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          boolean _isStatic = wrapper.isStatic();
          it.setStatic(_isStatic);
          TypeReference _returnType = wrapper.getReturnType();
          it.setReturnType(_returnType);
          List<TypeReference> _exceptions = wrapper.getExceptions();
          it.setExceptions(((TypeReference[])Conversions.unwrapArray(_exceptions, TypeReference.class)));
          Iterable<MutableTypeParameterDeclaration> _typeParameters = wrapper.getTypeParameters();
          final Procedure1<MutableTypeParameterDeclaration> _function = new Procedure1<MutableTypeParameterDeclaration>() {
            public void apply(final MutableTypeParameterDeclaration p) {
              String _simpleName = p.getSimpleName();
              Iterable<? extends TypeReference> _upperBounds = p.getUpperBounds();
              it.addTypeParameter(_simpleName, ((TypeReference[])Conversions.unwrapArray(_upperBounds, TypeReference.class)));
            }
          };
          IterableExtensions.<MutableTypeParameterDeclaration>forEach(_typeParameters, _function);
          List<MutableParameterDeclaration> _parameters = wrapper.getParameters();
          final Procedure1<MutableParameterDeclaration> _function_1 = new Procedure1<MutableParameterDeclaration>() {
            public void apply(final MutableParameterDeclaration p) {
              String _simpleName = p.getSimpleName();
              TypeReference _type = p.getType();
              it.addParameter(_simpleName, _type);
            }
          };
          IterableExtensions.<MutableParameterDeclaration>forEach(_parameters, _function_1);
          boolean _isVarArgs = wrapper.isVarArgs();
          it.setVarArgs(_isVarArgs);
          it.setVisibility(Visibility.PRIVATE);
          Expression _body = wrapper.getBody();
          it.setBody(_body);
        }
      };
      final MutableMethodDeclaration inner = _declaringType.addMethod(innerMethodName, _function);
      wrapper.setBody(indirection);
      _xblockexpression = (inner);
    }
    return _xblockexpression;
  }
  
  public static MutableMethodDeclaration addGetter(final MutableFieldDeclaration field) {
    MutableTypeDeclaration _declaringType = field.getDeclaringType();
    String _simpleName = field.getSimpleName();
    String _firstUpper = StringExtensions.toFirstUpper(_simpleName);
    String _plus = ("get" + _firstUpper);
    final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
      public void apply(final MutableMethodDeclaration it) {
        TypeReference _type = field.getType();
        it.setReturnType(_type);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("return ");
            String _simpleName = field.getSimpleName();
            _builder.append(_simpleName, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    MutableMethodDeclaration _addMethod = _declaringType.addMethod(_plus, _function);
    return _addMethod;
  }
  
  public static MutableMethodDeclaration addSetter(final MutableFieldDeclaration field) {
    MutableTypeDeclaration _declaringType = field.getDeclaringType();
    String _simpleName = field.getSimpleName();
    String _firstUpper = StringExtensions.toFirstUpper(_simpleName);
    String _plus = ("set" + _firstUpper);
    final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
      public void apply(final MutableMethodDeclaration it) {
        String _simpleName = field.getSimpleName();
        TypeReference _type = field.getType();
        it.addParameter(_simpleName, _type);
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
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    MutableMethodDeclaration _addMethod = _declaringType.addMethod(_plus, _function);
    return _addMethod;
  }
  
  /**
   * All non-static, non-transient fields of this class
   */
  public static Iterable<? extends FieldDeclaration> persistentState(final ClassDeclaration cls) {
    Iterable<? extends FieldDeclaration> _declaredFields = cls.getDeclaredFields();
    final Function1<FieldDeclaration,Boolean> _function = new Function1<FieldDeclaration,Boolean>() {
      public Boolean apply(final FieldDeclaration it) {
        boolean _and = false;
        boolean _isTransient = it.isTransient();
        boolean _not = (!_isTransient);
        if (!_not) {
          _and = false;
        } else {
          boolean _isStatic = it.isStatic();
          boolean _not_1 = (!_isStatic);
          _and = (_not && _not_1);
        }
        return Boolean.valueOf(_and);
      }
    };
    Iterable<? extends FieldDeclaration> _filter = IterableExtensions.filter(_declaredFields, _function);
    return _filter;
  }
  
  /**
   * All non-static, non-transient fields of this class
   */
  public static Iterable<? extends MutableFieldDeclaration> persistentState(final MutableClassDeclaration cls) {
    Iterable<? extends MutableFieldDeclaration> _declaredFields = cls.getDeclaredFields();
    final Function1<MutableFieldDeclaration,Boolean> _function = new Function1<MutableFieldDeclaration,Boolean>() {
      public Boolean apply(final MutableFieldDeclaration it) {
        boolean _and = false;
        boolean _isTransient = it.isTransient();
        boolean _not = (!_isTransient);
        if (!_not) {
          _and = false;
        } else {
          boolean _isStatic = it.isStatic();
          boolean _not_1 = (!_isStatic);
          _and = (_not && _not_1);
        }
        return Boolean.valueOf(_and);
      }
    };
    Iterable<? extends MutableFieldDeclaration> _filter = IterableExtensions.filter(_declaredFields, _function);
    return _filter;
  }
  
  protected static boolean _isStatic(final FieldDeclaration field) {
    boolean _isStatic = field.isStatic();
    return _isStatic;
  }
  
  protected static boolean _isStatic(final MethodDeclaration method) {
    boolean _isStatic = method.isStatic();
    return _isStatic;
  }
  
  protected static void _setStatic(final MutableMethodDeclaration method, final boolean isStatic) {
    method.setStatic(isStatic);
  }
  
  protected static void _setStatic(final MutableFieldDeclaration field, final boolean isStatic) {
    field.setStatic(isStatic);
  }
  
  protected static TypeReference _propertyType(final FieldDeclaration field) {
    TypeReference _type = field.getType();
    return _type;
  }
  
  protected static TypeReference _propertyType(final MethodDeclaration method) {
    TypeReference _returnType = method.getReturnType();
    return _returnType;
  }
  
  public static String packageName(final ClassDeclaration cls) {
    String _xblockexpression = null;
    {
      String _qualifiedName = cls.getQualifiedName();
      final String[] parts = _qualifiedName.split("\\.");
      int _size = ((List<String>)Conversions.doWrapArray(parts)).size();
      int _minus = (_size - 1);
      Iterable<String> _take = IterableExtensions.<String>take(((Iterable<String>)Conversions.doWrapArray(parts)), _minus);
      String _join = IterableExtensions.join(_take, ".");
      _xblockexpression = (_join);
    }
    return _xblockexpression;
  }
  
  public static boolean isStatic(final MemberDeclaration method) {
    if (method instanceof MethodDeclaration) {
      return _isStatic((MethodDeclaration)method);
    } else if (method instanceof FieldDeclaration) {
      return _isStatic((FieldDeclaration)method);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(method).toString());
    }
  }
  
  public static void setStatic(final MutableMemberDeclaration method, final boolean isStatic) {
    if (method instanceof MutableMethodDeclaration) {
      _setStatic((MutableMethodDeclaration)method, isStatic);
      return;
    } else if (method instanceof MutableFieldDeclaration) {
      _setStatic((MutableFieldDeclaration)method, isStatic);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(method, isStatic).toString());
    }
  }
  
  public static TypeReference propertyType(final MemberDeclaration method) {
    if (method instanceof MethodDeclaration) {
      return _propertyType((MethodDeclaration)method);
    } else if (method instanceof FieldDeclaration) {
      return _propertyType((FieldDeclaration)method);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(method).toString());
    }
  }
}
