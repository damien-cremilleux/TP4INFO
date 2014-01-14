/**
 * Opposite annotation
 * 
 * Example:
 * class A { @Opposite("a") public B b }
 * class B { @Opposite("b") public A a }
 * 
 * @author Arnaud Blouin / Thomas Degueule
 */
package fr.inria.triskell.k3;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import fr.inria.triskell.k3.Composition;
import fr.inria.triskell.k3.Opposite;
import java.util.Collection;
import java.util.List;
import org.eclipse.xtend.lib.macro.AbstractFieldProcessor;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.AnnotationTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy.CompilationContext;
import org.eclipse.xtend.lib.macro.declaration.MutableAnnotationReference;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.declaration.Visibility;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * Opposite annotation processing
 */
@SuppressWarnings("all")
public class OppositeProcessor extends AbstractFieldProcessor {
  protected MutableTypeDeclaration containingType;
  
  protected MutableFieldDeclaration field;
  
  protected MutableTypeDeclaration oppositeType;
  
  protected MutableFieldDeclaration oppositeField;
  
  protected TransformationContext context;
  
  /**
   * Weaves opposite behavior
   */
  public void doTransform(final MutableFieldDeclaration field, final TransformationContext ctx) {
    this.context = ctx;
    Iterable<? extends MutableAnnotationReference> _annotations = field.getAnnotations();
    final Function1<MutableAnnotationReference,Boolean> _function = new Function1<MutableAnnotationReference,Boolean>() {
      public Boolean apply(final MutableAnnotationReference a) {
        AnnotationTypeDeclaration _annotationTypeDeclaration = a.getAnnotationTypeDeclaration();
        String _qualifiedName = _annotationTypeDeclaration.getQualifiedName();
        String _name = Opposite.class.getName();
        boolean _equals = _qualifiedName.equals(_name);
        return Boolean.valueOf(_equals);
      }
    };
    MutableAnnotationReference _findFirst = IterableExtensions.findFirst(_annotations, _function);
    final Object oppositeRefName = _findFirst.getValue("value");
    TypeReference _type = field.getType();
    boolean _isCollection = this.isCollection(_type);
    if (_isCollection) {
      TypeReference _type_1 = field.getType();
      List<TypeReference> _actualTypeArguments = _type_1.getActualTypeArguments();
      int _size = _actualTypeArguments.size();
      boolean _notEquals = (_size != 1);
      if (_notEquals) {
        this.context.addError(field, "Only collections with 1 type parameter are supported");
        return;
      }
      TypeReference _type_2 = field.getType();
      List<TypeReference> _actualTypeArguments_1 = _type_2.getActualTypeArguments();
      TypeReference _head = IterableExtensions.<TypeReference>head(_actualTypeArguments_1);
      String _name = _head.getName();
      MutableClassDeclaration _findClass = this.context.findClass(_name);
      this.oppositeType = _findClass;
    } else {
      TypeReference _type_3 = field.getType();
      String _name_1 = _type_3.getName();
      MutableClassDeclaration _findClass_1 = this.context.findClass(_name_1);
      this.oppositeType = _findClass_1;
    }
    this.field = field;
    MutableTypeDeclaration _declaringType = field.getDeclaringType();
    this.containingType = _declaringType;
    Iterable<? extends MutableFieldDeclaration> _declaredFields = this.oppositeType.getDeclaredFields();
    final Function1<MutableFieldDeclaration,Boolean> _function_1 = new Function1<MutableFieldDeclaration,Boolean>() {
      public Boolean apply(final MutableFieldDeclaration f) {
        String _simpleName = f.getSimpleName();
        boolean _equals = _simpleName.equals(oppositeRefName);
        return Boolean.valueOf(_equals);
      }
    };
    MutableFieldDeclaration _findFirst_1 = IterableExtensions.findFirst(_declaredFields, _function_1);
    this.oppositeField = _findFirst_1;
    boolean _check = this.check();
    if (_check) {
      this.field.setVisibility(Visibility.PRIVATE);
      this.generateInitializer();
      this.generateGetterMethod();
      this.generateSetterProxyMethod();
      this.generateResetMethod();
      this.generateSetMethod();
    }
  }
  
  protected void generateInitializer() {
    TypeReference _type = this.field.getType();
    boolean _isCollection = this.isCollection(_type);
    if (_isCollection) {
      TypeReference _type_1 = this.field.getType();
      List<TypeReference> _actualTypeArguments = _type_1.getActualTypeArguments();
      TypeReference _head = IterableExtensions.<TypeReference>head(_actualTypeArguments);
      final String t = _head.getSimpleName();
      final CompilationStrategy _function = new CompilationStrategy() {
        public CharSequence compile(final CompilationContext it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("new java.util.ArrayList<");
          _builder.append(t, "");
          _builder.append(">()");
          return _builder;
        }
      };
      this.field.setInitializer(_function);
    } else {
      final CompilationStrategy _function_1 = new CompilationStrategy() {
        public CharSequence compile(final CompilationContext it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("null");
          return _builder;
        }
      };
      this.field.setInitializer(_function_1);
    }
  }
  
  /**
   * Generates a simple getter method
   */
  protected void generateGetterMethod() {
    final String f = this.field.getSimpleName();
    TypeReference _type = this.field.getType();
    boolean _isCollection = this.isCollection(_type);
    if (_isCollection) {
      final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setVisibility(Visibility.PUBLIC);
          TypeReference _type = OppositeProcessor.this.field.getType();
          List<TypeReference> _actualTypeArguments = _type.getActualTypeArguments();
          TypeReference _head = IterableExtensions.<TypeReference>head(_actualTypeArguments);
          TypeReference _newTypeReference = OppositeProcessor.this.context.newTypeReference(ImmutableList.class, _head);
          it.setReturnType(_newTypeReference);
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("return com.google.common.collect.ImmutableList.copyOf(");
              _builder.append(f, "");
              _builder.append(") ;");
              return _builder;
            }
          };
          it.setBody(_function);
        }
      };
      this.containingType.addMethod(f, _function);
    } else {
      final Procedure1<MutableMethodDeclaration> _function_1 = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setVisibility(Visibility.PUBLIC);
          TypeReference _type = OppositeProcessor.this.field.getType();
          it.setReturnType(_type);
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("return ");
              _builder.append(f, "");
              _builder.append(" ;");
              return _builder;
            }
          };
          it.setBody(_function);
        }
      };
      this.containingType.addMethod(f, _function_1);
    }
  }
  
  /**
   * Generates a proxy setter for the annotated
   * field in order to inject the opposite behavior
   */
  protected void generateSetterProxyMethod() {
    final String f = this.field.getSimpleName();
    final String o = this.oppositeField.getSimpleName();
    final TypeReference t = this.oppositeField.getType();
    TypeReference _type = this.field.getType();
    boolean _isCollection = this.isCollection(_type);
    if (_isCollection) {
      String _simpleName = this.field.getSimpleName();
      String _firstUpper = StringExtensions.toFirstUpper(_simpleName);
      String _plus = ("add" + _firstUpper);
      final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setVisibility(Visibility.PUBLIC);
          TypeReference _type = OppositeProcessor.this.field.getType();
          List<TypeReference> _actualTypeArguments = _type.getActualTypeArguments();
          TypeReference _head = IterableExtensions.<TypeReference>head(_actualTypeArguments);
          it.addParameter("obj", _head);
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("if (!");
              _builder.append(f, "");
              _builder.append(".contains(obj))");
              _builder.newLineIfNotEmpty();
              _builder.append("{");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("if (obj != null)");
              _builder.newLine();
              {
                boolean _isCollection = OppositeProcessor.this.isCollection(t);
                if (_isCollection) {
                  _builder.append("\t\t");
                  _builder.append("obj.__K3_");
                  _builder.append(o, "		");
                  _builder.append("_set(this) ;");
                  _builder.newLineIfNotEmpty();
                } else {
                  _builder.append("\t\t");
                  _builder.append("obj.__K3_");
                  _builder.append(o, "		");
                  _builder.append("_set(this) ;");
                  _builder.newLineIfNotEmpty();
                }
              }
              _builder.append("\t");
              _builder.newLine();
              _builder.append("\t");
              _builder.append(f, "	");
              _builder.append(".add(obj) ;");
              _builder.newLineIfNotEmpty();
              _builder.append("}");
              _builder.newLine();
              return _builder;
            }
          };
          it.setBody(_function);
        }
      };
      this.containingType.addMethod(_plus, _function);
    } else {
      String _simpleName_1 = this.field.getSimpleName();
      final Procedure1<MutableMethodDeclaration> _function_1 = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setVisibility(Visibility.PUBLIC);
          TypeReference _type = OppositeProcessor.this.field.getType();
          it.addParameter("obj", _type);
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("if (obj != ");
              _builder.append(f, "");
              _builder.append(")");
              _builder.newLineIfNotEmpty();
              _builder.append("{");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("if (");
              _builder.append(f, "	");
              _builder.append(" != null)");
              _builder.newLineIfNotEmpty();
              {
                boolean _isCollection = OppositeProcessor.this.isCollection(t);
                if (_isCollection) {
                  _builder.append("\t\t");
                  _builder.append(f, "		");
                  _builder.append(".__K3_");
                  _builder.append(o, "		");
                  _builder.append("_reset(this) ;");
                  _builder.newLineIfNotEmpty();
                } else {
                  _builder.append("\t\t");
                  _builder.append(f, "		");
                  _builder.append(".__K3_");
                  _builder.append(o, "		");
                  _builder.append("_reset() ;");
                  _builder.newLineIfNotEmpty();
                }
              }
              _builder.append("\t");
              _builder.append("if (obj != null)");
              _builder.newLine();
              _builder.append("\t\t");
              _builder.append("obj.__K3_");
              _builder.append(o, "		");
              _builder.append("_set(this) ;");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.newLine();
              _builder.append("\t");
              _builder.append(f, "	");
              _builder.append(" = obj ;");
              _builder.newLineIfNotEmpty();
              _builder.append("}");
              _builder.newLine();
              return _builder;
            }
          };
          it.setBody(_function);
        }
      };
      this.containingType.addMethod(_simpleName_1, _function_1);
    }
  }
  
  /**
   * Reset annotated field value
   */
  protected void generateResetMethod() {
    final String f = this.field.getSimpleName();
    final String o = this.oppositeField.getSimpleName();
    final TypeReference t = this.oppositeField.getType();
    TypeReference _type = this.field.getType();
    boolean _isCollection = this.isCollection(_type);
    if (_isCollection) {
      String _simpleName = this.field.getSimpleName();
      String _plus = ("__K3_" + _simpleName);
      String _plus_1 = (_plus + "_reset");
      final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setVisibility(Visibility.PUBLIC);
          TypeReference _type = OppositeProcessor.this.field.getType();
          List<TypeReference> _actualTypeArguments = _type.getActualTypeArguments();
          TypeReference _head = IterableExtensions.<TypeReference>head(_actualTypeArguments);
          it.addParameter("obj", _head);
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("if (");
              _builder.append(f, "");
              _builder.append(".contains(obj))");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append(f, "	");
              _builder.append(".remove(obj) ;");
              _builder.newLineIfNotEmpty();
              return _builder;
            }
          };
          it.setBody(_function);
        }
      };
      this.containingType.addMethod(_plus_1, _function);
      String _simpleName_1 = this.field.getSimpleName();
      String _firstUpper = StringExtensions.toFirstUpper(_simpleName_1);
      String _plus_2 = ("remove" + _firstUpper);
      final Procedure1<MutableMethodDeclaration> _function_1 = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setVisibility(Visibility.PUBLIC);
          TypeReference _type = OppositeProcessor.this.field.getType();
          List<TypeReference> _actualTypeArguments = _type.getActualTypeArguments();
          TypeReference _head = IterableExtensions.<TypeReference>head(_actualTypeArguments);
          it.addParameter("obj", _head);
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("if (obj != null)");
              _builder.newLine();
              {
                boolean _isCollection = OppositeProcessor.this.isCollection(t);
                if (_isCollection) {
                  _builder.append("\t");
                  _builder.append("obj.__K3_");
                  _builder.append(o, "	");
                  _builder.append("_reset(this) ;");
                  _builder.newLineIfNotEmpty();
                } else {
                  _builder.append("\t");
                  _builder.append("obj.__K3_");
                  _builder.append(o, "	");
                  _builder.append("_reset() ;");
                  _builder.newLineIfNotEmpty();
                }
              }
              _builder.append("\t");
              _builder.newLine();
              _builder.append(f, "");
              _builder.append(".remove(obj) ;");
              _builder.newLineIfNotEmpty();
              return _builder;
            }
          };
          it.setBody(_function);
        }
      };
      this.containingType.addMethod(_plus_2, _function_1);
    } else {
      String _plus_3 = ("__K3_" + f);
      String _plus_4 = (_plus_3 + "_reset");
      final Procedure1<MutableMethodDeclaration> _function_2 = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setVisibility(Visibility.PUBLIC);
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append(f, "");
              _builder.append(" = null ;");
              return _builder;
            }
          };
          it.setBody(_function);
        }
      };
      this.containingType.addMethod(_plus_4, _function_2);
    }
  }
  
  /**
   * Set field value
   */
  protected void generateSetMethod() {
    final String f = this.field.getSimpleName();
    final String o = this.oppositeField.getSimpleName();
    final TypeReference t = this.oppositeField.getType();
    TypeReference _type = this.field.getType();
    boolean _isCollection = this.isCollection(_type);
    if (_isCollection) {
      String _plus = ("__K3_" + f);
      String _plus_1 = (_plus + "_set");
      final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setVisibility(Visibility.PUBLIC);
          TypeReference _type = OppositeProcessor.this.field.getType();
          List<TypeReference> _actualTypeArguments = _type.getActualTypeArguments();
          TypeReference _head = IterableExtensions.<TypeReference>head(_actualTypeArguments);
          it.addParameter("obj", _head);
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append(f, "");
              _builder.append(".add(obj) ;");
              _builder.newLineIfNotEmpty();
              return _builder;
            }
          };
          it.setBody(_function);
        }
      };
      this.containingType.addMethod(_plus_1, _function);
    } else {
      String _plus_2 = ("__K3_" + f);
      String _plus_3 = (_plus_2 + "_set");
      final Procedure1<MutableMethodDeclaration> _function_1 = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setVisibility(Visibility.PUBLIC);
          TypeReference _type = OppositeProcessor.this.field.getType();
          it.addParameter("obj", _type);
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("if (");
              _builder.append(f, "");
              _builder.append(" != null)");
              _builder.newLineIfNotEmpty();
              {
                boolean _isCollection = OppositeProcessor.this.isCollection(t);
                if (_isCollection) {
                  _builder.append("\t");
                  _builder.append(f, "	");
                  _builder.append(".__K3_");
                  _builder.append(o, "	");
                  _builder.append("_reset(this) ;");
                  _builder.newLineIfNotEmpty();
                } else {
                  _builder.append("\t");
                  _builder.append(f, "	");
                  _builder.append(".__K3_");
                  _builder.append(o, "	");
                  _builder.append("_reset() ;");
                  _builder.newLineIfNotEmpty();
                }
              }
              _builder.newLine();
              _builder.append(f, "");
              _builder.append(" = obj ;");
              _builder.newLineIfNotEmpty();
              return _builder;
            }
          };
          it.setBody(_function);
        }
      };
      this.containingType.addMethod(_plus_3, _function_1);
    }
  }
  
  /**
   * Checks whether the opposite references
   * are properly defined
   */
  protected boolean check() {
    TypeReference _type = this.field.getType();
    boolean _isPrimitive = _type.isPrimitive();
    if (_isPrimitive) {
      TypeReference _type_1 = this.field.getType();
      String _simpleName = _type_1.getSimpleName();
      String _plus = ("Can\'t declare a primitive type " + _simpleName);
      String _plus_1 = (_plus + " as opposite");
      this.context.addError(this.field, _plus_1);
      return false;
    }
    boolean _equals = Objects.equal(this.oppositeField, null);
    if (_equals) {
      this.context.addError(this.field, "Referenced opposite attribute doesn\'t exist");
      return false;
    }
    TypeReference _type_2 = this.field.getType();
    boolean _isCollection = this.isCollection(_type_2);
    if (_isCollection) {
      boolean _and = false;
      TypeReference _type_3 = this.oppositeField.getType();
      boolean _isCollection_1 = this.isCollection(_type_3);
      boolean _not = (!_isCollection_1);
      if (!_not) {
        _and = false;
      } else {
        TypeReference _type_4 = this.oppositeField.getType();
        String _simpleName_1 = _type_4.getSimpleName();
        String _simpleName_2 = this.containingType.getSimpleName();
        boolean _equals_1 = _simpleName_1.equals(_simpleName_2);
        boolean _not_1 = (!_equals_1);
        _and = (_not && _not_1);
      }
      if (_and) {
        TypeReference _type_5 = this.oppositeField.getType();
        String _simpleName_3 = _type_5.getSimpleName();
        String _plus_2 = ("The opposite attribute type (" + _simpleName_3);
        String _plus_3 = (_plus_2 + ") doesn\'t match");
        this.context.addError(this.field, _plus_3);
        return false;
      } else {
        boolean _and_1 = false;
        TypeReference _type_6 = this.oppositeField.getType();
        boolean _isCollection_2 = this.isCollection(_type_6);
        if (!_isCollection_2) {
          _and_1 = false;
        } else {
          TypeReference _type_7 = this.oppositeField.getType();
          List<TypeReference> _actualTypeArguments = _type_7.getActualTypeArguments();
          TypeReference _head = IterableExtensions.<TypeReference>head(_actualTypeArguments);
          String _simpleName_4 = _head.getSimpleName();
          String _simpleName_5 = this.containingType.getSimpleName();
          boolean _equals_2 = _simpleName_4.equals(_simpleName_5);
          boolean _not_2 = (!_equals_2);
          _and_1 = (_isCollection_2 && _not_2);
        }
        if (_and_1) {
          TypeReference _type_8 = this.oppositeField.getType();
          String _simpleName_6 = _type_8.getSimpleName();
          String _plus_4 = ("The opposite attribute type (" + _simpleName_6);
          String _plus_5 = (_plus_4 + ") doesn\'t match");
          this.context.addError(this.field, _plus_5);
          return false;
        }
      }
    } else {
      boolean _and_2 = false;
      TypeReference _type_9 = this.oppositeField.getType();
      boolean _isCollection_3 = this.isCollection(_type_9);
      boolean _not_3 = (!_isCollection_3);
      if (!_not_3) {
        _and_2 = false;
      } else {
        TypeReference _type_10 = this.oppositeField.getType();
        String _simpleName_7 = _type_10.getSimpleName();
        String _simpleName_8 = this.containingType.getSimpleName();
        boolean _equals_3 = _simpleName_7.equals(_simpleName_8);
        boolean _not_4 = (!_equals_3);
        _and_2 = (_not_3 && _not_4);
      }
      if (_and_2) {
        TypeReference _type_11 = this.oppositeField.getType();
        String _simpleName_9 = _type_11.getSimpleName();
        String _plus_6 = ("The opposite attribute type (" + _simpleName_9);
        String _plus_7 = (_plus_6 + ") doesn\'t match");
        this.context.addError(this.field, _plus_7);
        return false;
      } else {
        boolean _and_3 = false;
        TypeReference _type_12 = this.oppositeField.getType();
        boolean _isCollection_4 = this.isCollection(_type_12);
        if (!_isCollection_4) {
          _and_3 = false;
        } else {
          TypeReference _type_13 = this.oppositeField.getType();
          List<TypeReference> _actualTypeArguments_1 = _type_13.getActualTypeArguments();
          TypeReference _head_1 = IterableExtensions.<TypeReference>head(_actualTypeArguments_1);
          String _simpleName_10 = _head_1.getSimpleName();
          String _simpleName_11 = this.containingType.getSimpleName();
          boolean _equals_4 = _simpleName_10.equals(_simpleName_11);
          boolean _not_5 = (!_equals_4);
          _and_3 = (_isCollection_4 && _not_5);
        }
        if (_and_3) {
          TypeReference _type_14 = this.oppositeField.getType();
          String _simpleName_12 = _type_14.getSimpleName();
          String _plus_8 = ("The opposite attribute type (" + _simpleName_12);
          String _plus_9 = (_plus_8 + ") doesn\'t match");
          this.context.addError(this.field, _plus_9);
          return false;
        }
      }
    }
    boolean _and_4 = false;
    Iterable<? extends MutableAnnotationReference> _annotations = this.field.getAnnotations();
    final Function1<MutableAnnotationReference,Boolean> _function = new Function1<MutableAnnotationReference,Boolean>() {
      public Boolean apply(final MutableAnnotationReference a) {
        AnnotationTypeDeclaration _annotationTypeDeclaration = a.getAnnotationTypeDeclaration();
        String _qualifiedName = _annotationTypeDeclaration.getQualifiedName();
        String _name = Composition.class.getName();
        boolean _equals = _qualifiedName.equals(_name);
        return Boolean.valueOf(_equals);
      }
    };
    boolean _exists = IterableExtensions.exists(_annotations, _function);
    if (!_exists) {
      _and_4 = false;
    } else {
      Iterable<? extends MutableAnnotationReference> _annotations_1 = this.oppositeField.getAnnotations();
      final Function1<MutableAnnotationReference,Boolean> _function_1 = new Function1<MutableAnnotationReference,Boolean>() {
        public Boolean apply(final MutableAnnotationReference a) {
          AnnotationTypeDeclaration _annotationTypeDeclaration = a.getAnnotationTypeDeclaration();
          String _qualifiedName = _annotationTypeDeclaration.getQualifiedName();
          String _name = Composition.class.getName();
          boolean _equals = _qualifiedName.equals(_name);
          return Boolean.valueOf(_equals);
        }
      };
      boolean _exists_1 = IterableExtensions.exists(_annotations_1, _function_1);
      _and_4 = (_exists && _exists_1);
    }
    if (_and_4) {
      this.context.addError(this.field, "Can\'t declare as opposites two composition references");
      return false;
    }
    Iterable<? extends MutableAnnotationReference> _annotations_2 = this.oppositeField.getAnnotations();
    final Function1<MutableAnnotationReference,Boolean> _function_2 = new Function1<MutableAnnotationReference,Boolean>() {
      public Boolean apply(final MutableAnnotationReference a) {
        boolean _and = false;
        AnnotationTypeDeclaration _annotationTypeDeclaration = a.getAnnotationTypeDeclaration();
        String _qualifiedName = _annotationTypeDeclaration.getQualifiedName();
        String _name = Opposite.class.getName();
        boolean _equals = _qualifiedName.equals(_name);
        if (!_equals) {
          _and = false;
        } else {
          Object _value = a.getValue("value");
          String _simpleName = OppositeProcessor.this.field.getSimpleName();
          boolean _equals_1 = _value.equals(_simpleName);
          _and = (_equals && _equals_1);
        }
        return Boolean.valueOf(_and);
      }
    };
    boolean _exists_2 = IterableExtensions.exists(_annotations_2, _function_2);
    boolean _not_6 = (!_exists_2);
    if (_not_6) {
      this.context.addError(this.field, "The opposite attribute must be marked as opposite of this attribute");
    }
    return true;
  }
  
  /**
   * Checks whether the specified TypeReference refers
   * to a collection
   */
  protected boolean isCollection(final TypeReference type) {
    TypeReference _newWildcardTypeReference = this.context.newWildcardTypeReference();
    TypeReference _newTypeReference = this.context.newTypeReference(Collection.class, _newWildcardTypeReference);
    boolean _isAssignableFrom = _newTypeReference.isAssignableFrom(type);
    return _isAssignableFrom;
  }
}
