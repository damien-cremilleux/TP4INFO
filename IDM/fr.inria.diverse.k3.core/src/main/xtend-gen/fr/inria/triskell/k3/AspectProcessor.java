package fr.inria.triskell.k3;

import com.google.common.base.Objects;
import fr.inria.triskell.k3.Tuple;
import fr.inria.triskell.k3.sortMethod;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.CodeGenerationContext;
import org.eclipse.xtend.lib.macro.CodeGenerationParticipant;
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.AnnotationReference;
import org.eclipse.xtend.lib.macro.declaration.AnnotationTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy.CompilationContext;
import org.eclipse.xtend.lib.macro.declaration.CompilationUnit;
import org.eclipse.xtend.lib.macro.declaration.MutableAnnotationReference;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableConstructorDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.declaration.Visibility;
import org.eclipse.xtend.lib.macro.expression.Expression;
import org.eclipse.xtend.lib.macro.file.Path;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class AspectProcessor extends AbstractClassProcessor implements CodeGenerationParticipant<ClassDeclaration> {
  public String getIdentifierOfAnAspectedClass(final MutableTypeDeclaration clazz) {
    Iterable<? extends MutableAnnotationReference> _annotations = clazz.getAnnotations();
    final Function1<MutableAnnotationReference,Boolean> _function = new Function1<MutableAnnotationReference,Boolean>() {
      public Boolean apply(final MutableAnnotationReference it) {
        Object _value = it.getValue("className");
        boolean _notEquals = (!Objects.equal(_value, null));
        return Boolean.valueOf(_notEquals);
      }
    };
    MutableAnnotationReference _findFirst = IterableExtensions.findFirst(_annotations, _function);
    Object classNam = _findFirst.getValue("className");
    try {
      Class<? extends Object> _class = classNam.getClass();
      Method _method = _class.getMethod("getIdentifier");
      Object _invoke = _method.invoke(classNam);
      return ((String) _invoke);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Fill s with super classes of c, ordered by hierarchy
   * (the first element is the direct super type of c)
   */
  public void getSuperClass(final List<MutableClassDeclaration> s, final MutableClassDeclaration c, @Extension final TransformationContext context) {
    TypeReference _extendedClass = c.getExtendedClass();
    boolean _notEquals = (!Objects.equal(_extendedClass, null));
    if (_notEquals) {
      TypeReference _extendedClass_1 = c.getExtendedClass();
      String _name = _extendedClass_1.getName();
      final MutableClassDeclaration l = context.findClass(_name);
      boolean _notEquals_1 = (!Objects.equal(l, null));
      if (_notEquals_1) {
        s.add(l);
        this.getSuperClass(s, l, context);
      }
    }
  }
  
  public void doRegisterGlobals(final ClassDeclaration annotatedClass, final RegisterGlobalsContext context) {
    try {
      Iterable<? extends AnnotationReference> _annotations = annotatedClass.getAnnotations();
      final Function1<AnnotationReference,Boolean> _function = new Function1<AnnotationReference,Boolean>() {
        public Boolean apply(final AnnotationReference it) {
          Object _value = it.getValue("className");
          boolean _notEquals = (!Objects.equal(_value, null));
          return Boolean.valueOf(_notEquals);
        }
      };
      AnnotationReference _findFirst = IterableExtensions.findFirst(_annotations, _function);
      Object classNam = _findFirst.getValue("className");
      Class<? extends Object> _class = classNam.getClass();
      Method _method = _class.getMethod("getSimpleName");
      Object _invoke = _method.invoke(classNam);
      final String className = ((String) _invoke);
      String _qualifiedName = annotatedClass.getQualifiedName();
      String _plus = (_qualifiedName + className);
      String _plus_1 = (_plus + "AspectProperties");
      context.registerClass(_plus_1);
      String _qualifiedName_1 = annotatedClass.getQualifiedName();
      String _plus_2 = (_qualifiedName_1 + className);
      String _plus_3 = (_plus_2 + "AspectContext");
      context.registerClass(_plus_3);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public List<MutableClassDeclaration> sortByClassInheritance(final MutableClassDeclaration clazz, final List<? extends MutableClassDeclaration> classes, @Extension final TransformationContext context) {
    ArrayList<MutableClassDeclaration> _arrayList = new ArrayList<MutableClassDeclaration>();
    final List<MutableClassDeclaration> listTmp = _arrayList;
    ArrayList<MutableClassDeclaration> _arrayList_1 = new ArrayList<MutableClassDeclaration>();
    final List<MutableClassDeclaration> listRes = _arrayList_1;
    this.sortByClassInheritance1(clazz, listRes, context);
    final Procedure1<MutableClassDeclaration> _function = new Procedure1<MutableClassDeclaration>() {
      public void apply(final MutableClassDeclaration c) {
        boolean _contains = listRes.contains(c);
        boolean _not = (!_contains);
        if (_not) {
          listTmp.clear();
          AspectProcessor.this.sortByClassInheritance1(c, listTmp, context);
          boolean _contains_1 = listTmp.contains(clazz);
          if (_contains_1) {
            listRes.add(c);
          }
        }
      }
    };
    IterableExtensions.forEach(classes, _function);
    final Comparator<MutableClassDeclaration> _function_1 = new Comparator<MutableClassDeclaration>() {
      public int compare(final MutableClassDeclaration a, final MutableClassDeclaration b) {
        int _xblockexpression = (int) 0;
        {
          listTmp.clear();
          AspectProcessor.this.sortByClassInheritance1(a, listTmp, context);
          int _xifexpression = (int) 0;
          boolean _contains = listTmp.contains(b);
          if (_contains) {
            int _minus = (-1);
            _xifexpression = _minus;
          } else {
            _xifexpression = 1;
          }
          _xblockexpression = (_xifexpression);
        }
        return _xblockexpression;
      }
    };
    Collections.<MutableClassDeclaration>sort(listRes, _function_1);
    return listRes;
  }
  
  public void sortByClassInheritance1(final MutableClassDeclaration clazz, final List<MutableClassDeclaration> res, @Extension final TransformationContext context) {
    res.add(clazz);
    TypeReference _extendedClass = clazz.getExtendedClass();
    String _name = _extendedClass.getName();
    final MutableClassDeclaration l = context.findClass(_name);
    boolean _notEquals = (!Objects.equal(l, null));
    if (_notEquals) {
      this.sortByClassInheritance1(l, res, context);
    }
  }
  
  public List<MutableMethodDeclaration> sortByMethodInheritance(final Set<MutableMethodDeclaration> methods, final List<String> inheritOrder) {
    ArrayList<MutableMethodDeclaration> _arrayList = new ArrayList<MutableMethodDeclaration>();
    List<MutableMethodDeclaration> listRes = _arrayList;
    for (final String classe : inheritOrder) {
      for (final MutableMethodDeclaration md : methods) {
        MutableTypeDeclaration _declaringType = md.getDeclaringType();
        String _simpleName = _declaringType.getSimpleName();
        boolean _equals = Objects.equal(_simpleName, classe);
        if (_equals) {
          listRes.add(md);
        }
      }
    }
    return listRes;
  }
  
  private Map<MutableClassDeclaration,List<MutableClassDeclaration>> listResMap = new Function0<Map<MutableClassDeclaration,List<MutableClassDeclaration>>>() {
    public Map<MutableClassDeclaration,List<MutableClassDeclaration>> apply() {
      HashMap<MutableClassDeclaration,List<MutableClassDeclaration>> _hashMap = new HashMap<MutableClassDeclaration, List<MutableClassDeclaration>>();
      return _hashMap;
    }
  }.apply();
  
  public void doTransform(final List<? extends MutableClassDeclaration> classes, @Extension final TransformationContext context) {
    try {
      HashMap<MutableClassDeclaration,List<MutableClassDeclaration>> _hashMap = new HashMap<MutableClassDeclaration, List<MutableClassDeclaration>>();
      final Map<MutableClassDeclaration,List<MutableClassDeclaration>> superclass = _hashMap;
      HashMap<MutableMethodDeclaration,Set<MutableMethodDeclaration>> _hashMap_1 = new HashMap<MutableMethodDeclaration, Set<MutableMethodDeclaration>>();
      final Map<MutableMethodDeclaration,Set<MutableMethodDeclaration>> dispatchmethod = _hashMap_1;
      this.init_superclass(classes, context, superclass);
      this.init_dispatchmethod(superclass, dispatchmethod, context);
      for (final MutableClassDeclaration clazz : classes) {
        {
          List<MutableClassDeclaration> listRes = this.sortByClassInheritance(clazz, classes, context);
          ArrayList<String> _arrayList = new ArrayList<String>();
          final List<String> inheritList = _arrayList;
          final Procedure1<MutableClassDeclaration> _function = new Procedure1<MutableClassDeclaration>() {
            public void apply(final MutableClassDeclaration c) {
              String _simpleName = c.getSimpleName();
              inheritList.add(_simpleName);
            }
          };
          IterableExtensions.<MutableClassDeclaration>forEach(listRes, _function);
          this.listResMap.put(clazz, listRes);
          Iterable<? extends MutableAnnotationReference> _annotations = clazz.getAnnotations();
          final Function1<MutableAnnotationReference,Boolean> _function_1 = new Function1<MutableAnnotationReference,Boolean>() {
            public Boolean apply(final MutableAnnotationReference it) {
              Object _value = it.getValue("className");
              boolean _notEquals = (!Objects.equal(_value, null));
              return Boolean.valueOf(_notEquals);
            }
          };
          MutableAnnotationReference _findFirst = IterableExtensions.findFirst(_annotations, _function_1);
          Object classNam = _findFirst.getValue("className");
          Class<? extends Object> _class = classNam.getClass();
          Method _method = _class.getMethod("getSimpleName");
          Object _invoke = _method.invoke(classNam);
          final String className = ((String) _invoke);
          Class<? extends Object> _class_1 = classNam.getClass();
          Method _method_1 = _class_1.getMethod("getIdentifier");
          Object _invoke_1 = _method_1.invoke(classNam);
          final String identifier = ((String) _invoke_1);
          HashMap<MutableMethodDeclaration,String> _hashMap_2 = new HashMap<MutableMethodDeclaration, String>();
          final Map<MutableMethodDeclaration,String> bodies = _hashMap_2;
          this.fields_processing(context, clazz, className, identifier, bodies);
          this.methods_processing(clazz, context, identifier, bodies, dispatchmethod, inheritList, className);
          this.aspectContextMaker(context, clazz, className, identifier);
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void methods_processing(final MutableClassDeclaration clazz, @Extension final TransformationContext context, final String identifier, final Map<MutableMethodDeclaration,String> bodies, final Map<MutableMethodDeclaration,Set<MutableMethodDeclaration>> dispatchmethod, final List<String> inheritList, final String className) {
    Iterable<? extends MutableMethodDeclaration> _declaredMethods = clazz.getDeclaredMethods();
    for (final MutableMethodDeclaration m : _declaredMethods) {
      {
        boolean _or = false;
        List<MutableParameterDeclaration> _parameters = m.getParameters();
        int _size = _parameters.size();
        boolean _equals = (_size == 0);
        if (_equals) {
          _or = true;
        } else {
          boolean _and = false;
          List<MutableParameterDeclaration> _parameters_1 = m.getParameters();
          int _size_1 = _parameters_1.size();
          boolean _greaterThan = (_size_1 > 0);
          if (!_greaterThan) {
            _and = false;
          } else {
            List<MutableParameterDeclaration> _parameters_2 = m.getParameters();
            MutableParameterDeclaration _get = _parameters_2.get(0);
            String _simpleName = _get.getSimpleName();
            boolean _notEquals = (!Objects.equal(_simpleName, "_self"));
            _and = (_greaterThan && _notEquals);
          }
          _or = (_equals || _and);
        }
        if (_or) {
          ArrayList<Tuple<String,TypeReference>> _arrayList = new ArrayList<Tuple<String, TypeReference>>();
          final ArrayList<Tuple<String,TypeReference>> l = _arrayList;
          List<MutableParameterDeclaration> _parameters_3 = m.getParameters();
          for (final MutableParameterDeclaration p1 : _parameters_3) {
            String _simpleName_1 = p1.getSimpleName();
            TypeReference _type = p1.getType();
            Tuple<String,TypeReference> _tuple = new Tuple<String, TypeReference>(_simpleName_1, _type);
            l.add(_tuple);
          }
          List<MutableParameterDeclaration> _parameters_4 = m.getParameters();
          _parameters_4.clear();
          TypeReference _newTypeReference = context.newTypeReference(identifier);
          m.addParameter("_self", _newTypeReference);
          for (final Tuple<String,TypeReference> param : l) {
            String _x = param.getX();
            TypeReference _y = param.getY();
            m.addParameter(_x, _y);
          }
        }
        boolean _isStatic = m.isStatic();
        boolean _not = (!_isStatic);
        if (_not) {
          m.setStatic(true);
        }
        boolean _and_1 = false;
        TypeReference _extendedClass = clazz.getExtendedClass();
        boolean _notEquals_1 = (!Objects.equal(_extendedClass, null));
        if (!_notEquals_1) {
          _and_1 = false;
        } else {
          Iterable<? extends MutableAnnotationReference> _annotations = m.getAnnotations();
          final Function1<MutableAnnotationReference,Boolean> _function = new Function1<MutableAnnotationReference,Boolean>() {
            public Boolean apply(final MutableAnnotationReference a) {
              AnnotationTypeDeclaration _annotationTypeDeclaration = a.getAnnotationTypeDeclaration();
              String _simpleName = _annotationTypeDeclaration.getSimpleName();
              boolean _equals = Objects.equal(_simpleName, "OverrideAspectMethod");
              return Boolean.valueOf(_equals);
            }
          };
          MutableAnnotationReference _findFirst = IterableExtensions.findFirst(_annotations, _function);
          boolean _notEquals_2 = (!Objects.equal(_findFirst, null));
          _and_1 = (_notEquals_1 && _notEquals_2);
        }
        if (_and_1) {
          String _simpleName_2 = m.getSimpleName();
          String _plus = ("super_" + _simpleName_2);
          final Procedure1<MutableMethodDeclaration> _function_1 = new Procedure1<MutableMethodDeclaration>() {
            public void apply(final MutableMethodDeclaration it) {
              it.setVisibility(Visibility.PRIVATE);
              it.setStatic(true);
              TypeReference _returnType = m.getReturnType();
              it.setReturnType(_returnType);
              List<MutableParameterDeclaration> _parameters = m.getParameters();
              for (final MutableParameterDeclaration p : _parameters) {
                String _simpleName = p.getSimpleName();
                TypeReference _type = p.getType();
                it.addParameter(_simpleName, _type);
              }
              String s = "";
              List<MutableParameterDeclaration> _parameters_1 = m.getParameters();
              for (final MutableParameterDeclaration p_1 : _parameters_1) {
                String _simpleName_1 = p_1.getSimpleName();
                String _plus = (s + _simpleName_1);
                String _plus_1 = (_plus + ",");
                s = _plus_1;
              }
              int _length = s.length();
              boolean _greaterThan = (_length > 0);
              if (_greaterThan) {
                int _length_1 = s.length();
                int _minus = (_length_1 - 1);
                String _substring = s.substring(0, _minus);
                s = _substring;
              }
              final String s1 = s;
              TypeReference _extendedClass = clazz.getExtendedClass();
              String _name = _extendedClass.getName();
              final MutableClassDeclaration superClass = context.findClass(_name);
              boolean _equals = Objects.equal(superClass, null);
              if (_equals) {
                String _simpleName_2 = clazz.getSimpleName();
                String _plus_2 = ("class " + _simpleName_2);
                String _plus_3 = (_plus_2 + " has no super class");
                context.addError(clazz, _plus_3);
              } else {
                final MutableMethodDeclaration m3 = AspectProcessor.this.findMethod(superClass, m, context);
                boolean _equals_1 = Objects.equal(m3, null);
                if (_equals_1) {
                  context.addError(m, "No super method found");
                }
                final CompilationStrategy _function = new CompilationStrategy() {
                  public CharSequence compile(final CompilationContext it) {
                    StringConcatenation _builder = new StringConcatenation();
                    _builder.append(" ");
                    {
                      TypeReference _returnType = m3.getReturnType();
                      String _name = _returnType.getName();
                      boolean _notEquals = (!Objects.equal(_name, "void"));
                      if (_notEquals) {
                        _builder.append("return ");
                      }
                    }
                    _builder.append(" ");
                    MutableTypeDeclaration _declaringType = m3.getDeclaringType();
                    TypeReference _newTypeReference = context.newTypeReference(_declaringType);
                    String _name_1 = _newTypeReference.getName();
                    _builder.append(_name_1, " ");
                    _builder.append(".priv");
                    String _simpleName = m.getSimpleName();
                    _builder.append(_simpleName, " ");
                    _builder.append("(");
                    _builder.append(s1, " ");
                    _builder.append(");  ");
                    return _builder;
                  }
                };
                it.setBody(_function);
              }
            }
          };
          clazz.addMethod(_plus, _function_1);
        }
        Iterable<? extends MutableAnnotationReference> _annotations_1 = m.getAnnotations();
        final Function1<MutableAnnotationReference,Boolean> _function_2 = new Function1<MutableAnnotationReference,Boolean>() {
          public Boolean apply(final MutableAnnotationReference a) {
            AnnotationTypeDeclaration _annotationTypeDeclaration = a.getAnnotationTypeDeclaration();
            String _simpleName = _annotationTypeDeclaration.getSimpleName();
            boolean _equals = Objects.equal(_simpleName, "ReplaceAspectMethod");
            return Boolean.valueOf(_equals);
          }
        };
        MutableAnnotationReference _findFirst_1 = IterableExtensions.findFirst(_annotations_1, _function_2);
        boolean _notEquals_3 = (!Objects.equal(_findFirst_1, 
          null));
        if (_notEquals_3) {
          final MutableClassDeclaration cl = context.findClass(identifier);
          boolean _notEquals_4 = (!Objects.equal(cl, null));
          if (_notEquals_4) {
            Iterable<? extends MutableMethodDeclaration> _declaredMethods_1 = cl.getDeclaredMethods();
            final Function1<MutableMethodDeclaration,Boolean> _function_3 = new Function1<MutableMethodDeclaration,Boolean>() {
              public Boolean apply(final MutableMethodDeclaration m2) {
                boolean _and = false;
                String _simpleName = m2.getSimpleName();
                String _simpleName_1 = m.getSimpleName();
                boolean _equals = Objects.equal(_simpleName, _simpleName_1);
                if (!_equals) {
                  _and = false;
                } else {
                  List<MutableParameterDeclaration> _parameters = m2.getParameters();
                  int _size = _parameters.size();
                  List<MutableParameterDeclaration> _parameters_1 = m.getParameters();
                  int _size_1 = _parameters_1.size();
                  int _minus = (_size_1 - 1);
                  boolean _equals_1 = (_size == _minus);
                  _and = (_equals && _equals_1);
                }
                return Boolean.valueOf(_and);
              }
            };
            final MutableMethodDeclaration m2 = IterableExtensions.findFirst(_declaredMethods_1, _function_3);
            String _simpleName_3 = m.getSimpleName();
            String _plus_1 = ("_hidden_" + _simpleName_3);
            m2.setSimpleName(_plus_1);
          }
        }
        String _simpleName_4 = m.getSimpleName();
        String _plus_2 = ("priv" + _simpleName_4);
        final Procedure1<MutableMethodDeclaration> _function_4 = new Procedure1<MutableMethodDeclaration>() {
          public void apply(final MutableMethodDeclaration it) {
            it.setVisibility(Visibility.PROTECTED);
            it.setStatic(true);
            it.setAbstract(false);
            TypeReference _returnType = m.getReturnType();
            it.setReturnType(_returnType);
            boolean _isAbstract = m.isAbstract();
            if (_isAbstract) {
              final CompilationStrategy _function = new CompilationStrategy() {
                public CharSequence compile(final CompilationContext it) {
                  StringConcatenation _builder = new StringConcatenation();
                  _builder.append("throw new java.lang.RuntimeException(\"Not implemented\");");
                  return _builder;
                }
              };
              it.setBody(_function);
            } else {
              Expression _body = m.getBody();
              boolean _equals = Objects.equal(_body, null);
              if (_equals) {
                final CompilationStrategy _function_1 = new CompilationStrategy() {
                  public CharSequence compile(final CompilationContext it) {
                    String _get = bodies.get(m);
                    return _get;
                  }
                };
                it.setBody(_function_1);
              } else {
                Expression _body_1 = m.getBody();
                it.setBody(_body_1);
              }
            }
            List<MutableParameterDeclaration> _parameters = m.getParameters();
            for (final MutableParameterDeclaration p : _parameters) {
              String _simpleName = p.getSimpleName();
              TypeReference _type = p.getType();
              it.addParameter(_simpleName, _type);
            }
          }
        };
        clazz.addMethod(_plus_2, _function_4);
        String s = "";
        List<MutableParameterDeclaration> _parameters_5 = m.getParameters();
        for (final MutableParameterDeclaration p : _parameters_5) {
          String _simpleName_5 = p.getSimpleName();
          String _plus_3 = (s + _simpleName_5);
          String _plus_4 = (_plus_3 + ",");
          s = _plus_4;
        }
        int _length = s.length();
        boolean _greaterThan_1 = (_length > 0);
        if (_greaterThan_1) {
          int _length_1 = s.length();
          int _minus = (_length_1 - 1);
          String _substring = s.substring(0, _minus);
          s = _substring;
        }
        final String s1 = s;
        String ret = "";
        TypeReference _returnType = m.getReturnType();
        TypeReference _newTypeReference_1 = context.newTypeReference("void");
        boolean _notEquals_5 = (!Objects.equal(_returnType, _newTypeReference_1));
        if (_notEquals_5) {
          ret = "return";
        }
        final String retu = ret;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(retu, "");
        _builder.append(" priv");
        String _simpleName_6 = m.getSimpleName();
        _builder.append(_simpleName_6, "");
        _builder.append("(");
        _builder.append(s1, "");
        _builder.append("); ");
        String callt = _builder.toString();
        Set<MutableMethodDeclaration> _get_1 = dispatchmethod.get(m);
        boolean _notEquals_6 = (!Objects.equal(_get_1, null));
        if (_notEquals_6) {
          Set<MutableMethodDeclaration> _get_2 = dispatchmethod.get(m);
          final List<MutableMethodDeclaration> listmethod = this.sortByMethodInheritance(_get_2, inheritList);
          String toto1 = "";
          for (final MutableMethodDeclaration s5 : listmethod) {
            MutableTypeDeclaration _declaringType = s5.getDeclaringType();
            String _simpleName_7 = _declaringType.getSimpleName();
            String _plus_5 = (_simpleName_7 + " ");
            String _plus_6 = (_plus_5 + toto1);
            toto1 = _plus_6;
          }
          StringConcatenation _builder_1 = new StringConcatenation();
          {
            for(final MutableMethodDeclaration md : listmethod) {
              _builder_1.append("   if (_self instanceof ");
              MutableTypeDeclaration _declaringType_1 = md.getDeclaringType();
              String _identifierOfAnAspectedClass = this.getIdentifierOfAnAspectedClass(_declaringType_1);
              _builder_1.append(_identifierOfAnAspectedClass, "");
              _builder_1.append("){");
              _builder_1.newLineIfNotEmpty();
              _builder_1.append("\t\t\t\t\t\t\t");
              _builder_1.append(retu, "							");
              _builder_1.append(" ");
              MutableTypeDeclaration _declaringType_2 = md.getDeclaringType();
              TypeReference _newTypeReference_2 = context.newTypeReference(_declaringType_2);
              String _name = _newTypeReference_2.getName();
              _builder_1.append(_name, "							");
              _builder_1.append(".priv");
              String _simpleName_8 = m.getSimpleName();
              _builder_1.append(_simpleName_8, "							");
              _builder_1.append("(");
              MutableTypeDeclaration _declaringType_3 = md.getDeclaringType();
              String _identifierOfAnAspectedClass_1 = this.getIdentifierOfAnAspectedClass(_declaringType_3);
              String _plus_7 = ("(" + _identifierOfAnAspectedClass_1);
              String _plus_8 = (_plus_7 + ")_self");
              String _replaceFirst = s1.replaceFirst("_self", _plus_8);
              _builder_1.append(_replaceFirst, "							");
              _builder_1.append(");");
              _builder_1.newLineIfNotEmpty();
              _builder_1.append("\t\t\t\t\t\t\t");
              _builder_1.append("} else ");
            }
          }
          _builder_1.newLineIfNotEmpty();
          String ifst = _builder_1.toString();
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append(" ");
          _builder_2.append("{");
          _builder_2.newLine();
          _builder_2.append("  \t\t\t\t\t\t\t\t\t\t");
          _builder_2.append("throw new IllegalArgumentException(\"Unhandled parameter types: \" +");
          _builder_2.newLine();
          _builder_2.append("\t\t\t\t\t\t\t        ");
          _builder_2.append("java.util.Arrays.<Object>asList(_self).toString());");
          _builder_2.newLine();
          _builder_2.append("\t\t\t\t\t    ");
          _builder_2.append("} ");
          String _plus_9 = (ifst + _builder_2);
          callt = _plus_9;
        }
        final String call = callt;
        m.setAbstract(false);
        final CompilationStrategy _function_5 = new CompilationStrategy() {
          public CharSequence compile(final CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            String _qualifiedName = clazz.getQualifiedName();
            String _plus = (_qualifiedName + className);
            _builder.append(_plus, "");
            _builder.append("AspectContext _instance = ");
            String _qualifiedName_1 = clazz.getQualifiedName();
            String _plus_1 = (_qualifiedName_1 + className);
            _builder.append(_plus_1, "");
            _builder.append("AspectContext.getInstance();");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t\t\t    ");
            _builder.append("java.util.Map<");
            TypeReference _newTypeReference = context.newTypeReference(identifier);
            List<TypeReference> _actualTypeArguments = _newTypeReference.getActualTypeArguments();
            String _mkstring = AspectProcessor.mkstring(_actualTypeArguments, ",", "<", ">");
            String _plus_2 = (identifier + _mkstring);
            _builder.append(_plus_2, "				    ");
            _builder.append(",");
            String _qualifiedName_2 = clazz.getQualifiedName();
            String _plus_3 = (_qualifiedName_2 + className);
            _builder.append(_plus_3, "				    ");
            _builder.append("AspectProperties> selfProp = _instance.getMap();");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t\t\t\t");
            _builder.append("boolean _containsKey = selfProp.containsKey(_self);");
            _builder.newLine();
            _builder.append("\t\t\t\t    ");
            _builder.append("boolean _not = (!_containsKey);");
            _builder.newLine();
            _builder.append("\t\t\t\t    ");
            _builder.append("if (_not) {");
            _builder.newLine();
            _builder.append("  \t\t\t\t\t\t");
            String _qualifiedName_3 = clazz.getQualifiedName();
            String _plus_4 = (_qualifiedName_3 + className);
            _builder.append(_plus_4, "  						");
            _builder.append("AspectProperties prop = new ");
            String _qualifiedName_4 = clazz.getQualifiedName();
            String _plus_5 = (_qualifiedName_4 + className);
            _builder.append(_plus_5, "  						");
            _builder.append("AspectProperties();");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t\t\t   ");
            _builder.append("selfProp.put(_self, prop);");
            _builder.newLine();
            _builder.append("\t\t\t    ");
            _builder.append("}");
            _builder.newLine();
            _builder.append("\t\t\t     ");
            _builder.append("_self_ = selfProp.get(_self);");
            _builder.newLine();
            _builder.append("\t\t\t     ");
            _builder.append(call, "			     ");
            _builder.newLineIfNotEmpty();
            return _builder;
          }
        };
        m.setBody(_function_5);
      }
    }
  }
  
  /**
   * Create the class which link classes with their aspects
   */
  public MutableMethodDeclaration aspectContextMaker(@Extension final TransformationContext context, final MutableClassDeclaration clazz, final String className, final String identifier) {
    MutableMethodDeclaration _xblockexpression = null;
    {
      String _qualifiedName = clazz.getQualifiedName();
      String _plus = (_qualifiedName + className);
      String _plus_1 = (_plus + "AspectContext");
      final MutableClassDeclaration holderClass = context.findClass(_plus_1);
      holderClass.setVisibility(Visibility.PUBLIC);
      final Procedure1<MutableConstructorDeclaration> _function = new Procedure1<MutableConstructorDeclaration>() {
        public void apply(final MutableConstructorDeclaration it) {
          it.setVisibility(Visibility.PRIVATE);
        }
      };
      holderClass.addConstructor(_function);
      final Procedure1<MutableFieldDeclaration> _function_1 = new Procedure1<MutableFieldDeclaration>() {
        public void apply(final MutableFieldDeclaration it) {
          it.setVisibility(Visibility.PUBLIC);
          it.setStatic(true);
          it.setFinal(true);
          TypeReference _newTypeReference = context.newTypeReference(holderClass);
          it.setType(_newTypeReference);
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("new ");
              String _simpleName = holderClass.getSimpleName();
              _builder.append(_simpleName, "");
              _builder.append("()");
              return _builder;
            }
          };
          it.setInitializer(_function);
        }
      };
      holderClass.addField("INSTANCE", _function_1);
      final Procedure1<MutableMethodDeclaration> _function_2 = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setVisibility(Visibility.PUBLIC);
          it.setStatic(true);
          TypeReference _newTypeReference = context.newTypeReference(holderClass);
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
      holderClass.addMethod("getInstance", _function_2);
      final Procedure1<MutableFieldDeclaration> _function_3 = new Procedure1<MutableFieldDeclaration>() {
        public void apply(final MutableFieldDeclaration it) {
          it.setVisibility(Visibility.PRIVATE);
          it.setStatic(false);
          TypeReference _newTypeReference = context.newTypeReference(identifier);
          String _qualifiedName = clazz.getQualifiedName();
          String _plus = (_qualifiedName + className);
          String _plus_1 = (_plus + "AspectProperties");
          TypeReference _newTypeReference_1 = context.newTypeReference(_plus_1);
          TypeReference _newTypeReference_2 = context.newTypeReference("java.util.Map", _newTypeReference, _newTypeReference_1);
          it.setType(_newTypeReference_2);
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("new java.util.HashMap<");
              TypeReference _newTypeReference = context.newTypeReference(identifier);
              List<TypeReference> _actualTypeArguments = _newTypeReference.getActualTypeArguments();
              String _mkstring = AspectProcessor.mkstring(_actualTypeArguments, ",", "<", ">");
              String _plus = (identifier + _mkstring);
              _builder.append(_plus, "");
              _builder.append(", ");
              String _qualifiedName = clazz.getQualifiedName();
              String _plus_1 = (_qualifiedName + className);
              _builder.append(_plus_1, "");
              _builder.append("AspectProperties>()");
              return _builder;
            }
          };
          it.setInitializer(_function);
        }
      };
      holderClass.addField("map", _function_3);
      final Procedure1<MutableMethodDeclaration> _function_4 = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setVisibility(Visibility.PUBLIC);
          it.setStatic(false);
          TypeReference _newTypeReference = context.newTypeReference(identifier);
          String _qualifiedName = clazz.getQualifiedName();
          String _plus = (_qualifiedName + className);
          String _plus_1 = (_plus + "AspectProperties");
          TypeReference _newTypeReference_1 = context.newTypeReference(_plus_1);
          TypeReference _newTypeReference_2 = context.newTypeReference("java.util.Map", _newTypeReference, _newTypeReference_1);
          it.setReturnType(_newTypeReference_2);
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("return map;");
              return _builder;
            }
          };
          it.setBody(_function);
        }
      };
      MutableMethodDeclaration _addMethod = holderClass.addMethod("getMap", _function_4);
      _xblockexpression = (_addMethod);
    }
    return _xblockexpression;
  }
  
  public static String mkstring(final List<TypeReference> list, final String delimiter, final String before, final String after) {
    int _size = list.size();
    boolean _equals = (_size == 0);
    if (_equals) {
      return "";
    }
    StringBuffer _stringBuffer = new StringBuffer();
    final StringBuffer s = _stringBuffer;
    boolean _notEquals = (!Objects.equal(before, null));
    if (_notEquals) {
      s.append(before);
    }
    final Procedure1<TypeReference> _function = new Procedure1<TypeReference>() {
      public void apply(final TypeReference e) {
        String _plus = (delimiter + "?");
        s.append(_plus);
      }
    };
    IterableExtensions.<TypeReference>forEach(list, _function);
    boolean _notEquals_1 = (!Objects.equal(after, null));
    if (_notEquals_1) {
      s.append(after);
    }
    String _string = s.toString();
    String _plus = (before + delimiter);
    return _string.replace(_plus, before);
  }
  
  /**
   * Move fields of the aspect to the AspectProperties class
   */
  public void fields_processing(@Extension final TransformationContext context, final MutableClassDeclaration clazz, final String className, final String identifier, final Map<MutableMethodDeclaration,String> bodies) {
    ArrayList<MutableFieldDeclaration> _arrayList = new ArrayList<MutableFieldDeclaration>();
    List<MutableFieldDeclaration> toRemove = _arrayList;
    ArrayList<MutableFieldDeclaration> _arrayList_1 = new ArrayList<MutableFieldDeclaration>();
    List<MutableFieldDeclaration> propertyAspect = _arrayList_1;
    String _qualifiedName = clazz.getQualifiedName();
    String _plus = (_qualifiedName + className);
    String _plus_1 = (_plus + "AspectProperties");
    MutableClassDeclaration c = context.findClass(_plus_1);
    Iterable<? extends MutableFieldDeclaration> _declaredFields = clazz.getDeclaredFields();
    for (final MutableFieldDeclaration f : _declaredFields) {
      String _simpleName = f.getSimpleName();
      boolean _notEquals = (!Objects.equal(_simpleName, "_self_"));
      if (_notEquals) {
        toRemove.add(f);
        Iterable<? extends MutableAnnotationReference> _annotations = f.getAnnotations();
        final Function1<MutableAnnotationReference,Boolean> _function = new Function1<MutableAnnotationReference,Boolean>() {
          public Boolean apply(final MutableAnnotationReference a) {
            AnnotationTypeDeclaration _annotationTypeDeclaration = a.getAnnotationTypeDeclaration();
            String _simpleName = _annotationTypeDeclaration.getSimpleName();
            boolean _equals = Objects.equal(_simpleName, "NotAspectProperty");
            return Boolean.valueOf(_equals);
          }
        };
        MutableAnnotationReference _findFirst = IterableExtensions.findFirst(_annotations, _function);
        boolean _equals = Objects.equal(_findFirst, 
          null);
        if (_equals) {
          propertyAspect.add(f);
        }
        String _simpleName_1 = f.getSimpleName();
        final Procedure1<MutableFieldDeclaration> _function_1 = new Procedure1<MutableFieldDeclaration>() {
          public void apply(final MutableFieldDeclaration it) {
            it.setVisibility(Visibility.PUBLIC);
            boolean _isStatic = f.isStatic();
            it.setStatic(_isStatic);
            boolean _isFinal = f.isFinal();
            it.setFinal(_isFinal);
            TypeReference _type = f.getType();
            it.setType(_type);
            Expression _initializer = f.getInitializer();
            boolean _notEquals = (!Objects.equal(_initializer, null));
            if (_notEquals) {
              Expression _initializer_1 = f.getInitializer();
              it.setInitializer(_initializer_1);
            }
          }
        };
        c.addField(_simpleName_1, _function_1);
      } else {
        boolean _and = false;
        boolean _isStatic = f.isStatic();
        boolean _not = (!_isStatic);
        if (!_not) {
          _and = false;
        } else {
          String _simpleName_2 = f.getSimpleName();
          boolean _equals_1 = Objects.equal(_simpleName_2, "_self_");
          _and = (_not && _equals_1);
        }
        if (_and) {
          String _qualifiedName_1 = clazz.getQualifiedName();
          String _plus_2 = (_qualifiedName_1 + className);
          String _plus_3 = (_plus_2 + "AspectProperties");
          MutableClassDeclaration _findClass = context.findClass(_plus_3);
          TypeReference _newTypeReference = context.newTypeReference(_findClass);
          f.setType(_newTypeReference);
          f.setStatic(true);
        }
      }
    }
    Iterable<? extends MutableFieldDeclaration> _declaredFields_1 = clazz.getDeclaredFields();
    final Function1<MutableFieldDeclaration,Boolean> _function_2 = new Function1<MutableFieldDeclaration,Boolean>() {
      public Boolean apply(final MutableFieldDeclaration it) {
        String _simpleName = it.getSimpleName();
        boolean _equals = Objects.equal(_simpleName, "_self_");
        return Boolean.valueOf(_equals);
      }
    };
    MutableFieldDeclaration self = IterableExtensions.findFirst(_declaredFields_1, _function_2);
    boolean _equals_2 = Objects.equal(self, null);
    if (_equals_2) {
      final Procedure1<MutableFieldDeclaration> _function_3 = new Procedure1<MutableFieldDeclaration>() {
        public void apply(final MutableFieldDeclaration it) {
          String _qualifiedName = clazz.getQualifiedName();
          String _plus = (_qualifiedName + className);
          String _plus_1 = (_plus + "AspectProperties");
          MutableClassDeclaration _findClass = context.findClass(_plus_1);
          TypeReference _newTypeReference = context.newTypeReference(_findClass);
          it.setType(_newTypeReference);
          it.setStatic(true);
          it.setVisibility(Visibility.PUBLIC);
        }
      };
      clazz.addField("_self_", _function_3);
    }
    for (final MutableFieldDeclaration f_1 : propertyAspect) {
      {
        String _simpleName_3 = f_1.getSimpleName();
        final Procedure1<MutableMethodDeclaration> _function_4 = new Procedure1<MutableMethodDeclaration>() {
          public void apply(final MutableMethodDeclaration it) {
            TypeReference _type = f_1.getType();
            it.setReturnType(_type);
            TypeReference _newTypeReference = context.newTypeReference(identifier);
            it.addParameter("_self", _newTypeReference);
          }
        };
        MutableMethodDeclaration get = clazz.addMethod(_simpleName_3, _function_4);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(" ");
        _builder.append("return ");
        String _qualifiedName_2 = clazz.getQualifiedName();
        _builder.append(_qualifiedName_2, " ");
        _builder.append("._self_.");
        String _simpleName_4 = f_1.getSimpleName();
        _builder.append(_simpleName_4, " ");
        _builder.append("; ");
        bodies.put(get, _builder.toString());
        boolean _isFinal = f_1.isFinal();
        boolean _not_1 = (!_isFinal);
        if (_not_1) {
          String _simpleName_5 = f_1.getSimpleName();
          final Procedure1<MutableMethodDeclaration> _function_5 = new Procedure1<MutableMethodDeclaration>() {
            public void apply(final MutableMethodDeclaration it) {
              TypeReference _newTypeReference = context.newTypeReference("void");
              it.setReturnType(_newTypeReference);
              TypeReference _newTypeReference_1 = context.newTypeReference(identifier);
              it.addParameter("_self", _newTypeReference_1);
              String _simpleName = f_1.getSimpleName();
              TypeReference _type = f_1.getType();
              it.addParameter(_simpleName, _type);
            }
          };
          MutableMethodDeclaration set = clazz.addMethod(_simpleName_5, _function_5);
          StringConcatenation _builder_1 = new StringConcatenation();
          String _qualifiedName_3 = clazz.getQualifiedName();
          _builder_1.append(_qualifiedName_3, "");
          _builder_1.append("._self_.");
          String _simpleName_6 = f_1.getSimpleName();
          _builder_1.append(_simpleName_6, "");
          _builder_1.append(" = ");
          String _simpleName_7 = f_1.getSimpleName();
          _builder_1.append(_simpleName_7, "");
          _builder_1.append("; ");
          bodies.put(set, _builder_1.toString());
        }
      }
    }
    for (final MutableFieldDeclaration f_2 : toRemove) {
      f_2.remove();
    }
  }
  
  /**
   * Each aspect method is associatated with the lists of all methods with the
   * same signature (name + number of args) of parents classes and children classes.
   * 
   * @superclass All aspects associated with their superclasses
   * @dispatchmethod Associations computed
   * @context
   */
  public void init_dispatchmethod(final Map<MutableClassDeclaration,List<MutableClassDeclaration>> superclass, final Map<MutableMethodDeclaration,Set<MutableMethodDeclaration>> dispatchmethod, final TransformationContext context) {
    int i = 0;
    Set<MutableClassDeclaration> _keySet = superclass.keySet();
    for (final MutableClassDeclaration cl : _keySet) {
      {
        ArrayList<MutableClassDeclaration> _arrayList = new ArrayList<MutableClassDeclaration>();
        final ArrayList<MutableClassDeclaration> clazzes = _arrayList;
        clazzes.add(cl);
        List<MutableClassDeclaration> _get = superclass.get(cl);
        clazzes.addAll(_get);
        HashMap<String,Set<MutableMethodDeclaration>> _hashMap = new HashMap<String, Set<MutableMethodDeclaration>>();
        final Map<String,Set<MutableMethodDeclaration>> dispatchs = _hashMap;
        for (final MutableClassDeclaration clazz : clazzes) {
          Iterable<? extends MutableMethodDeclaration> _declaredMethods = clazz.getDeclaredMethods();
          for (final MutableMethodDeclaration m : _declaredMethods) {
            {
              String _simpleName = m.getSimpleName();
              String _plus = (_simpleName + "__");
              List<MutableParameterDeclaration> _parameters = m.getParameters();
              int _size = _parameters.size();
              final String mname = (_plus + Integer.valueOf(_size));
              Set<MutableMethodDeclaration> v = dispatchs.get(mname);
              boolean _equals = Objects.equal(v, null);
              if (_equals) {
                LinkedHashSet<MutableMethodDeclaration> _linkedHashSet = new LinkedHashSet<MutableMethodDeclaration>();
                v = _linkedHashSet;
                dispatchs.put(mname, v);
              }
              v.add(m);
            }
          }
        }
        Set<String> _keySet_1 = dispatchs.keySet();
        for (final String key : _keySet_1) {
          {
            final Set<MutableMethodDeclaration> res = dispatchs.get(key);
            int _size = res.size();
            boolean _greaterThan = (_size > 1);
            if (_greaterThan) {
              int _size_1 = res.size();
              int _plus = (i + _size_1);
              i = _plus;
              for (final MutableMethodDeclaration m_1 : res) {
                Set<MutableMethodDeclaration> _get_1 = dispatchmethod.get(m_1);
                boolean _equals = Objects.equal(_get_1, null);
                if (_equals) {
                  dispatchmethod.put(m_1, res);
                } else {
                  Set<MutableMethodDeclaration> _get_2 = dispatchmethod.get(m_1);
                  _get_2.addAll(res);
                }
              }
            }
          }
        }
      }
    }
    Set<MutableMethodDeclaration> _keySet_1 = dispatchmethod.keySet();
    for (final MutableMethodDeclaration m : _keySet_1) {
      {
        Set<MutableMethodDeclaration> _get = dispatchmethod.get(m);
        sortMethod _sortMethod = new sortMethod(context);
        final List<MutableMethodDeclaration> l = IterableExtensions.<MutableMethodDeclaration>sort(_get, _sortMethod);
        Set<MutableMethodDeclaration> _get_1 = dispatchmethod.get(m);
        _get_1.clear();
        Set<MutableMethodDeclaration> _get_2 = dispatchmethod.get(m);
        _get_2.addAll(l);
      }
    }
  }
  
  /**
   * For each annoted class store his super classes hierarchy.
   * An annoted class which is a parent of an other annoted
   * class is not in the final result.
   * 
   * @annotedClasses All aspects
   * @superclass Mapping computed between class and list of his super classes
   * @context
   */
  public void init_superclass(final List<? extends MutableClassDeclaration> annotedClasses, final TransformationContext context, final Map<MutableClassDeclaration,List<MutableClassDeclaration>> superclass) {
    for (final MutableClassDeclaration clazz : annotedClasses) {
      {
        ArrayList<MutableClassDeclaration> _arrayList = new ArrayList<MutableClassDeclaration>();
        final ArrayList<MutableClassDeclaration> ext = _arrayList;
        this.getSuperClass(ext, clazz, context);
        int _size = ext.size();
        boolean _greaterThan = (_size > 0);
        if (_greaterThan) {
          superclass.put(clazz, ext);
        }
      }
    }
    LinkedHashSet<MutableClassDeclaration> _linkedHashSet = new LinkedHashSet<MutableClassDeclaration>();
    final LinkedHashSet<MutableClassDeclaration> allparent = _linkedHashSet;
    Set<MutableClassDeclaration> _keySet = superclass.keySet();
    for (final MutableClassDeclaration child : _keySet) {
      List<MutableClassDeclaration> _get = superclass.get(child);
      allparent.addAll(_get);
    }
    for (final MutableClassDeclaration p : allparent) {
      superclass.remove(p);
    }
  }
  
  /**
   * Find first method with the same name in super classes hierarchy
   * 
   * @clazz This class and super classes are the search area
   * @methodName Method to find
   */
  public MutableMethodDeclaration findMethod(final MutableClassDeclaration clazz, final MutableMethodDeclaration methodName, @Extension final TransformationContext context) {
    Iterable<? extends MutableMethodDeclaration> _declaredMethods = clazz.getDeclaredMethods();
    final Function1<MutableMethodDeclaration,Boolean> _function = new Function1<MutableMethodDeclaration,Boolean>() {
      public Boolean apply(final MutableMethodDeclaration m2) {
        String _simpleName = m2.getSimpleName();
        String _simpleName_1 = methodName.getSimpleName();
        boolean _equals = Objects.equal(_simpleName, _simpleName_1);
        return Boolean.valueOf(_equals);
      }
    };
    MutableMethodDeclaration m = IterableExtensions.findFirst(_declaredMethods, _function);
    boolean _equals = Objects.equal(m, null);
    if (_equals) {
      TypeReference _extendedClass = clazz.getExtendedClass();
      boolean _equals_1 = Objects.equal(_extendedClass, null);
      if (_equals_1) {
        return null;
      } else {
        TypeReference _extendedClass_1 = clazz.getExtendedClass();
        String _name = _extendedClass_1.getName();
        MutableClassDeclaration _findClass = context.findClass(_name);
        boolean _equals_2 = Objects.equal(_findClass, null);
        if (_equals_2) {
          return null;
        } else {
          TypeReference _extendedClass_2 = clazz.getExtendedClass();
          String _name_1 = _extendedClass_2.getName();
          MutableClassDeclaration _findClass_1 = context.findClass(_name_1);
          return this.findMethod(_findClass_1, methodName, context);
        }
      }
    } else {
      return m;
    }
  }
  
  /**
   * use an additional code generator to produce the .k3_aspect_mapping.properties file
   */
  public void doGenerateCode(final List<? extends ClassDeclaration> annotatedSourceElements, @Extension final CodeGenerationContext context) {
    try {
      int _size = annotatedSourceElements.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        ClassDeclaration _get = annotatedSourceElements.get(0);
        CompilationUnit _compilationUnit = _get.getCompilationUnit();
        final Path filePath = _compilationUnit.getFilePath();
        Path _projectFolder = context.getProjectFolder(filePath);
        _projectFolder.getLastSegment();
        Path _projectFolder_1 = context.getProjectFolder(filePath);
        Path _projectFolder_2 = context.getProjectFolder(filePath);
        String _lastSegment = _projectFolder_2.getLastSegment();
        String _plus = ("/META-INF/xtend-gen/" + _lastSegment);
        String _plus_1 = (_plus + ".k3_aspect_mapping.properties");
        final Path file = _projectFolder_1.append(_plus_1);
        context.delete(file);
      }
      for (final ClassDeclaration clazz : annotatedSourceElements) {
        {
          CompilationUnit _compilationUnit_1 = clazz.getCompilationUnit();
          final Path filePath_1 = _compilationUnit_1.getFilePath();
          Path _projectFolder_3 = context.getProjectFolder(filePath_1);
          _projectFolder_3.getLastSegment();
          Path _projectFolder_4 = context.getProjectFolder(filePath_1);
          Path _projectFolder_5 = context.getProjectFolder(filePath_1);
          String _lastSegment_1 = _projectFolder_5.getLastSegment();
          String _plus_2 = ("/META-INF/xtend-gen/" + _lastSegment_1);
          String _plus_3 = (_plus_2 + ".k3_aspect_mapping.properties");
          final Path file_1 = _projectFolder_4.append(_plus_3);
          Iterable<? extends AnnotationReference> _annotations = clazz.getAnnotations();
          final Function1<AnnotationReference,Boolean> _function = new Function1<AnnotationReference,Boolean>() {
            public Boolean apply(final AnnotationReference it) {
              Object _value = it.getValue("className");
              boolean _notEquals = (!Objects.equal(_value, null));
              return Boolean.valueOf(_notEquals);
            }
          };
          AnnotationReference _findFirst = IterableExtensions.findFirst(_annotations, _function);
          final Object aspectizedclassNam = _findFirst.getValue("className");
          Class<? extends Object> _class = aspectizedclassNam.getClass();
          Method _method = _class.getMethod("getQualifiedName");
          Object _invoke = _method.invoke(aspectizedclassNam);
          final String aspectizedclassName = ((String) _invoke);
          boolean _exists = context.exists(file_1);
          if (_exists) {
            StringConcatenation _builder = new StringConcatenation();
            CharSequence _contents = context.getContents(file_1);
            _builder.append(_contents, "");
            _builder.newLineIfNotEmpty();
            _builder.append(aspectizedclassName, "");
            _builder.append(" = ");
            String _qualifiedName = clazz.getQualifiedName();
            _builder.append(_qualifiedName, "");
            _builder.newLineIfNotEmpty();
            context.setContents(file_1, _builder);
          } else {
            StringConcatenation _builder_1 = new StringConcatenation();
            _builder_1.append(aspectizedclassName, "");
            _builder_1.append(" = ");
            String _qualifiedName_1 = clazz.getQualifiedName();
            _builder_1.append(_qualifiedName_1, "");
            _builder_1.newLineIfNotEmpty();
            context.setContents(file_1, _builder_1);
          }
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
