package fr.inria.triskell.k3;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import fr.inria.triskell.k3.Composition;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.xtend.lib.macro.AbstractFieldProcessor;
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.AnnotationTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy.CompilationContext;
import org.eclipse.xtend.lib.macro.declaration.FieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableAnnotationReference;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableInterfaceDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.declaration.Visibility;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

/**
 * The processor for the Composition annotation.
 * TODO: does not support collection yet.
 * @author Arnaud Blouin
 */
@SuppressWarnings("all")
public class CompositionProcessor extends AbstractFieldProcessor {
  protected final static String NAME_CONTAINER = "_kContainer";
  
  protected final Set<TypeReference> interfaceObsGenerated = new Function0<Set<TypeReference>>() {
    public Set<TypeReference> apply() {
      HashSet<TypeReference> _hashSet = new HashSet<TypeReference>();
      return _hashSet;
    }
  }.apply();
  
  protected String getObservabilityInterfaceName(final TypeReference type) {
    String _name = type.getName();
    return (_name + "__K3__Observer4Composition");
  }
  
  protected String getObservabilityOperationName(final String typeName) {
    return ("__remove__K3__Observer4Composition_" + typeName);
  }
  
  protected TypeReference getFieldType(final FieldDeclaration field, final TransformationContext ctx) {
    final TypeReference type = field.getType();
    boolean _notEquals = (!Objects.equal(ctx, null));
    if (_notEquals) {
      TypeReference _newTypeReference = ctx.newTypeReference(Collection.class);
      boolean _isAssignableFrom = type.isAssignableFrom(_newTypeReference);
      if (_isAssignableFrom) {
        ctx.addError(field, "Collections not supported yet.");
        return null;
      }
    }
    return type;
  }
  
  public void doRegisterGlobals(final FieldDeclaration field, final RegisterGlobalsContext ctx) {
    final TypeReference type = this.getFieldType(field, null);
    boolean _and = false;
    boolean _notEquals = (!Objects.equal(type, null));
    if (!_notEquals) {
      _and = false;
    } else {
      boolean _contains = this.interfaceObsGenerated.contains(type);
      boolean _not = (!_contains);
      _and = (_notEquals && _not);
    }
    if (_and) {
      try {
        String _observabilityInterfaceName = this.getObservabilityInterfaceName(type);
        ctx.registerInterface(_observabilityInterfaceName);
        this.interfaceObsGenerated.add(type);
      } catch (final Throwable _t) {
        if (_t instanceof IllegalArgumentException) {
          final IllegalArgumentException ex = (IllegalArgumentException)_t;
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    }
  }
  
  public void doTransform(final MutableFieldDeclaration field, final TransformationContext ctx) {
    final TypeReference fieldType = this.getFieldType(field, ctx);
    boolean _equals = Objects.equal(fieldType, null);
    if (_equals) {
      return;
    }
    String _name = fieldType.getName();
    final MutableClassDeclaration clazzTypeField = ctx.findClass(_name);
    boolean _equals_1 = Objects.equal(clazzTypeField, null);
    if (_equals_1) {
      TypeReference _type = field.getType();
      String _name_1 = _type.getName();
      String _plus = ("Cannot find the class " + _name_1);
      ctx.addError(field, _plus);
      return;
    }
    MutableTypeDeclaration _declaringType = field.getDeclaringType();
    final MutableClassDeclaration clazzContainField = ((MutableClassDeclaration) _declaringType);
    final String interfObsName = this.getObservabilityInterfaceName(fieldType);
    final TypeReference typeRefContainer = ctx.newTypeReference(interfObsName);
    final MutableInterfaceDeclaration interfaceObs = ctx.findInterface(interfObsName);
    String _simpleName = fieldType.getSimpleName();
    final String obsMethodName = this.getObservabilityOperationName(_simpleName);
    final Visibility oldFieldVisibility = field.getVisibility();
    boolean _isPrimitive = fieldType.isPrimitive();
    if (_isPrimitive) {
      ctx.addError(field, "Primitive attributes cannot be composite.");
    }
    field.setVisibility(Visibility.PRIVATE);
    Iterable<? extends MutableFieldDeclaration> _declaredFields = clazzTypeField.getDeclaredFields();
    final Function1<MutableFieldDeclaration,Boolean> _function = new Function1<MutableFieldDeclaration,Boolean>() {
      public Boolean apply(final MutableFieldDeclaration fi) {
        String _simpleName = fi.getSimpleName();
        boolean _equals = _simpleName.equals(CompositionProcessor.NAME_CONTAINER);
        return Boolean.valueOf(_equals);
      }
    };
    boolean _exists = IterableExtensions.exists(_declaredFields, _function);
    boolean _not = (!_exists);
    if (_not) {
      final Procedure1<MutableFieldDeclaration> _function_1 = new Procedure1<MutableFieldDeclaration>() {
        public void apply(final MutableFieldDeclaration it) {
          it.setVisibility(Visibility.PRIVATE);
          it.setType(typeRefContainer);
        }
      };
      clazzTypeField.addField(CompositionProcessor.NAME_CONTAINER, _function_1);
      final Procedure1<MutableMethodDeclaration> _function_2 = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.addParameter("obj", typeRefContainer);
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("if(");
              _builder.append(CompositionProcessor.NAME_CONTAINER, "");
              _builder.append("!=null) ");
              _builder.append(CompositionProcessor.NAME_CONTAINER, "");
              _builder.append(".");
              _builder.append(obsMethodName, "");
              _builder.append("(this);");
              _builder.newLineIfNotEmpty();
              _builder.append(CompositionProcessor.NAME_CONTAINER, "");
              _builder.append(" = obj;");
              _builder.newLineIfNotEmpty();
              return _builder;
            }
          };
          it.setBody(_function);
        }
      };
      clazzTypeField.addMethod(CompositionProcessor.NAME_CONTAINER, _function_2);
      final Procedure1<MutableMethodDeclaration> _function_3 = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          TypeReference _newTypeReference = ctx.newTypeReference("java.lang.Object");
          it.setReturnType(_newTypeReference);
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("return ");
              _builder.append(CompositionProcessor.NAME_CONTAINER, "");
              _builder.append(";");
              return _builder;
            }
          };
          it.setBody(_function);
        }
      };
      clazzTypeField.addMethod(CompositionProcessor.NAME_CONTAINER, _function_3);
    }
    Iterable<? extends MutableMethodDeclaration> _declaredMethods = interfaceObs.getDeclaredMethods();
    final Function1<MutableMethodDeclaration,Boolean> _function_4 = new Function1<MutableMethodDeclaration,Boolean>() {
      public Boolean apply(final MutableMethodDeclaration meth) {
        String _simpleName = meth.getSimpleName();
        boolean _equals = _simpleName.equals(obsMethodName);
        return Boolean.valueOf(_equals);
      }
    };
    boolean _exists_1 = IterableExtensions.exists(_declaredMethods, _function_4);
    boolean _not_1 = (!_exists_1);
    if (_not_1) {
      MutableInterfaceDeclaration _findInterface = ctx.findInterface(interfObsName);
      final Procedure1<MutableMethodDeclaration> _function_5 = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          String _name = fieldType.getName();
          TypeReference _newTypeReference = ctx.newTypeReference(_name);
          it.addParameter("object", _newTypeReference);
        }
      };
      _findInterface.addMethod(obsMethodName, _function_5);
    }
    Iterable<TypeReference> _implementedInterfaces = clazzContainField.getImplementedInterfaces();
    final Function1<TypeReference,Boolean> _function_6 = new Function1<TypeReference,Boolean>() {
      public Boolean apply(final TypeReference interf) {
        String _simpleName = interf.getSimpleName();
        boolean _equals = _simpleName.equals(interfObsName);
        return Boolean.valueOf(_equals);
      }
    };
    boolean _exists_2 = IterableExtensions.<TypeReference>exists(_implementedInterfaces, _function_6);
    boolean _not_2 = (!_exists_2);
    if (_not_2) {
      clazzContainField.setImplementedInterfaces(Collections.<TypeReference>unmodifiableList(Lists.<TypeReference>newArrayList(typeRefContainer)));
    }
    Iterable<? extends MutableMethodDeclaration> _declaredMethods_1 = clazzContainField.getDeclaredMethods();
    final Function1<MutableMethodDeclaration,Boolean> _function_7 = new Function1<MutableMethodDeclaration,Boolean>() {
      public Boolean apply(final MutableMethodDeclaration meth) {
        String _simpleName = meth.getSimpleName();
        boolean _equals = _simpleName.equals(obsMethodName);
        return Boolean.valueOf(_equals);
      }
    };
    boolean _exists_3 = IterableExtensions.exists(_declaredMethods_1, _function_7);
    boolean _not_3 = (!_exists_3);
    if (_not_3) {
      Iterable<? extends MutableFieldDeclaration> _declaredFields_1 = clazzContainField.getDeclaredFields();
      final Function1<MutableFieldDeclaration,Boolean> _function_8 = new Function1<MutableFieldDeclaration,Boolean>() {
        public Boolean apply(final MutableFieldDeclaration fi) {
          boolean _and = false;
          TypeReference _type = fi.getType();
          boolean _equals = _type.equals(fieldType);
          if (!_equals) {
            _and = false;
          } else {
            Iterable<? extends MutableAnnotationReference> _annotations = fi.getAnnotations();
            final Function1<MutableAnnotationReference,Boolean> _function = new Function1<MutableAnnotationReference,Boolean>() {
              public Boolean apply(final MutableAnnotationReference ann) {
                AnnotationTypeDeclaration _annotationTypeDeclaration = ann.getAnnotationTypeDeclaration();
                String _qualifiedName = _annotationTypeDeclaration.getQualifiedName();
                String _name = Composition.class.getName();
                boolean _equals = _qualifiedName.equals(_name);
                return Boolean.valueOf(_equals);
              }
            };
            boolean _exists = IterableExtensions.exists(_annotations, _function);
            _and = (_equals && _exists);
          }
          return Boolean.valueOf(_and);
        }
      };
      final Iterable<? extends MutableFieldDeclaration> listFieldComposit = IterableExtensions.filter(_declaredFields_1, _function_8);
      final Procedure1<MutableMethodDeclaration> _function_9 = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          String _name = fieldType.getName();
          TypeReference _newTypeReference = ctx.newTypeReference(_name);
          it.addParameter("obj", _newTypeReference);
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              {
                for(final MutableFieldDeclaration fi : listFieldComposit) {
                  _builder.append("if(this.");
                  String _simpleName = fi.getSimpleName();
                  _builder.append(_simpleName, "");
                  _builder.append("==obj) {");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t");
                  _builder.append("this.");
                  String _simpleName_1 = fi.getSimpleName();
                  _builder.append(_simpleName_1, "	");
                  _builder.append(" = null;");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t");
                  _builder.append("return ;");
                  _builder.newLine();
                  _builder.append("}");
                  _builder.newLine();
                }
              }
              return _builder;
            }
          };
          it.setBody(_function);
        }
      };
      clazzContainField.addMethod(obsMethodName, _function_9);
    }
    String _simpleName_1 = field.getSimpleName();
    final Procedure1<MutableMethodDeclaration> _function_10 = new Procedure1<MutableMethodDeclaration>() {
      public void apply(final MutableMethodDeclaration it) {
        it.setVisibility(oldFieldVisibility);
        it.addParameter("obj", fieldType);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("if(obj!=null) obj.");
            _builder.append(CompositionProcessor.NAME_CONTAINER, "");
            _builder.append("(this);");
            _builder.newLineIfNotEmpty();
            _builder.append("if(");
            String _simpleName = field.getSimpleName();
            _builder.append(_simpleName, "");
            _builder.append("!=null) ");
            String _simpleName_1 = field.getSimpleName();
            _builder.append(_simpleName_1, "");
            _builder.append(".");
            _builder.append(CompositionProcessor.NAME_CONTAINER, "");
            _builder.append("(null);");
            _builder.newLineIfNotEmpty();
            String _simpleName_2 = field.getSimpleName();
            _builder.append(_simpleName_2, "");
            _builder.append(" = obj;");
            _builder.newLineIfNotEmpty();
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    clazzContainField.addMethod(_simpleName_1, _function_10);
    String _simpleName_2 = field.getSimpleName();
    final Procedure1<MutableMethodDeclaration> _function_11 = new Procedure1<MutableMethodDeclaration>() {
      public void apply(final MutableMethodDeclaration it) {
        it.setVisibility(oldFieldVisibility);
        TypeReference _type = field.getType();
        it.setReturnType(_type);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("return  ");
            String _simpleName = field.getSimpleName();
            _builder.append(_simpleName, "");
            _builder.append(";");
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    clazzContainField.addMethod(_simpleName_2, _function_11);
  }
}
