package fr.inria.triskell.k3;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import fr.inria.triskell.k3.Post;
import fr.inria.triskell.k3.Pre;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.AnnotationTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy.CompilationContext;
import org.eclipse.xtend.lib.macro.declaration.MutableAnnotationReference;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.Type;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.declaration.Visibility;
import org.eclipse.xtend.lib.macro.expression.Expression;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class ContractedProcessor extends AbstractClassProcessor {
  private void getAllInvs(final MutableClassDeclaration cl, final List<MutableMethodDeclaration> invs, @Extension final TransformationContext context) {
    Iterable<? extends MutableMethodDeclaration> _declaredMethods = cl.getDeclaredMethods();
    final Function1<MutableMethodDeclaration,Boolean> _function = new Function1<MutableMethodDeclaration,Boolean>() {
      public Boolean apply(final MutableMethodDeclaration it) {
        Iterable<? extends MutableAnnotationReference> _annotations = it.getAnnotations();
        final Function1<MutableAnnotationReference,Boolean> _function = new Function1<MutableAnnotationReference,Boolean>() {
          public Boolean apply(final MutableAnnotationReference it) {
            AnnotationTypeDeclaration _annotationTypeDeclaration = it.getAnnotationTypeDeclaration();
            String _simpleName = _annotationTypeDeclaration.getSimpleName();
            boolean _equals = Objects.equal(_simpleName, "Inv");
            return Boolean.valueOf(_equals);
          }
        };
        boolean _exists = IterableExtensions.exists(_annotations, _function);
        return Boolean.valueOf(_exists);
      }
    };
    Iterable<? extends MutableMethodDeclaration> _filter = IterableExtensions.filter(_declaredMethods, _function);
    Iterables.<MutableMethodDeclaration>addAll(invs, _filter);
    TypeReference _extendedClass = cl.getExtendedClass();
    boolean _notEquals = (!Objects.equal(_extendedClass, null));
    if (_notEquals) {
      TypeReference _extendedClass_1 = cl.getExtendedClass();
      String _name = _extendedClass_1.getName();
      final MutableClassDeclaration parent = context.findClass(_name);
      boolean _notEquals_1 = (!Objects.equal(parent, null));
      if (_notEquals_1) {
        this.getAllInvs(parent, invs, context);
      }
    }
  }
  
  private void getAllPre(final MutableClassDeclaration cl, final List<MutableMethodDeclaration> pres, final String MsimpleName, @Extension final TransformationContext context) {
    Iterable<? extends MutableMethodDeclaration> _declaredMethods = cl.getDeclaredMethods();
    final Function1<MutableMethodDeclaration,Boolean> _function = new Function1<MutableMethodDeclaration,Boolean>() {
      public Boolean apply(final MutableMethodDeclaration m1) {
        String _simpleName = m1.getSimpleName();
        String _plus = ("pre" + MsimpleName);
        boolean _equals = Objects.equal(_simpleName, _plus);
        return Boolean.valueOf(_equals);
      }
    };
    Iterable<? extends MutableMethodDeclaration> _filter = IterableExtensions.filter(_declaredMethods, _function);
    Iterables.<MutableMethodDeclaration>addAll(pres, _filter);
    TypeReference _extendedClass = cl.getExtendedClass();
    boolean _notEquals = (!Objects.equal(_extendedClass, null));
    if (_notEquals) {
      TypeReference _extendedClass_1 = cl.getExtendedClass();
      String _name = _extendedClass_1.getName();
      final MutableClassDeclaration parent = context.findClass(_name);
      boolean _notEquals_1 = (!Objects.equal(parent, null));
      if (_notEquals_1) {
        this.getAllPre(parent, pres, MsimpleName, context);
      }
    }
  }
  
  private void getAllPost(final MutableClassDeclaration cl, final List<MutableMethodDeclaration> posts, final String simpleName, @Extension final TransformationContext context) {
    Iterable<? extends MutableMethodDeclaration> _declaredMethods = cl.getDeclaredMethods();
    final Function1<MutableMethodDeclaration,Boolean> _function = new Function1<MutableMethodDeclaration,Boolean>() {
      public Boolean apply(final MutableMethodDeclaration m1) {
        String _simpleName = m1.getSimpleName();
        String _plus = ("post" + simpleName);
        boolean _equals = Objects.equal(_simpleName, _plus);
        return Boolean.valueOf(_equals);
      }
    };
    Iterable<? extends MutableMethodDeclaration> _filter = IterableExtensions.filter(_declaredMethods, _function);
    Iterables.<MutableMethodDeclaration>addAll(posts, _filter);
    TypeReference _extendedClass = cl.getExtendedClass();
    boolean _notEquals = (!Objects.equal(_extendedClass, null));
    if (_notEquals) {
      TypeReference _extendedClass_1 = cl.getExtendedClass();
      String _name = _extendedClass_1.getName();
      final MutableClassDeclaration parent = context.findClass(_name);
      boolean _notEquals_1 = (!Objects.equal(parent, null));
      if (_notEquals_1) {
        this.getAllPost(parent, posts, simpleName, context);
      }
    }
  }
  
  public void doTransform(final MutableClassDeclaration annotateClass, @Extension final TransformationContext context) {
    HashMap<MutableMethodDeclaration,String> _hashMap = new HashMap<MutableMethodDeclaration, String>();
    final Map<MutableMethodDeclaration,String> bodies = _hashMap;
    ArrayList<MutableMethodDeclaration> _arrayList = new ArrayList<MutableMethodDeclaration>();
    final ArrayList<MutableMethodDeclaration> invs = _arrayList;
    this.getAllInvs(annotateClass, invs, context);
    Iterable<? extends MutableMethodDeclaration> _declaredMethods = annotateClass.getDeclaredMethods();
    final Function1<MutableMethodDeclaration,Boolean> _function = new Function1<MutableMethodDeclaration,Boolean>() {
      public Boolean apply(final MutableMethodDeclaration it) {
        Iterable<? extends MutableAnnotationReference> _annotations = it.getAnnotations();
        final Function1<MutableAnnotationReference,Boolean> _function = new Function1<MutableAnnotationReference,Boolean>() {
          public Boolean apply(final MutableAnnotationReference it) {
            AnnotationTypeDeclaration _annotationTypeDeclaration = it.getAnnotationTypeDeclaration();
            String _simpleName = _annotationTypeDeclaration.getSimpleName();
            boolean _equals = Objects.equal(_simpleName, "Pre");
            return Boolean.valueOf(_equals);
          }
        };
        boolean _exists = IterableExtensions.exists(_annotations, _function);
        return Boolean.valueOf(_exists);
      }
    };
    Iterable<? extends MutableMethodDeclaration> pre = IterableExtensions.filter(_declaredMethods, _function);
    Iterable<? extends MutableMethodDeclaration> _declaredMethods_1 = annotateClass.getDeclaredMethods();
    final Function1<MutableMethodDeclaration,Boolean> _function_1 = new Function1<MutableMethodDeclaration,Boolean>() {
      public Boolean apply(final MutableMethodDeclaration it) {
        Iterable<? extends MutableAnnotationReference> _annotations = it.getAnnotations();
        final Function1<MutableAnnotationReference,Boolean> _function = new Function1<MutableAnnotationReference,Boolean>() {
          public Boolean apply(final MutableAnnotationReference it) {
            AnnotationTypeDeclaration _annotationTypeDeclaration = it.getAnnotationTypeDeclaration();
            String _simpleName = _annotationTypeDeclaration.getSimpleName();
            boolean _equals = Objects.equal(_simpleName, "Post");
            return Boolean.valueOf(_equals);
          }
        };
        boolean _exists = IterableExtensions.exists(_annotations, _function);
        return Boolean.valueOf(_exists);
      }
    };
    Iterable<? extends MutableMethodDeclaration> post = IterableExtensions.filter(_declaredMethods_1, _function_1);
    for (final MutableMethodDeclaration annotatedMethod : invs) {
      {
        List<MutableParameterDeclaration> _parameters = annotatedMethod.getParameters();
        int _size = _parameters.size();
        boolean _greaterThan = (_size > 0);
        if (_greaterThan) {
          context.addError(annotatedMethod, "Invariants must not have a parameter");
          return;
        }
        TypeReference _returnType = annotatedMethod.getReturnType();
        TypeReference _newTypeReference = context.newTypeReference("boolean");
        boolean _notEquals = (!Objects.equal(_returnType, _newTypeReference));
        if (_notEquals) {
          context.addError(annotatedMethod, "Invariants must return a boolean");
          return;
        }
      }
    }
    for (final MutableMethodDeclaration annotatedMethod_1 : pre) {
      {
        List<MutableParameterDeclaration> _parameters = annotatedMethod_1.getParameters();
        int _size = _parameters.size();
        boolean _greaterThan = (_size > 0);
        if (_greaterThan) {
          context.addError(annotatedMethod_1, "Precondition must not have a parameter");
          return;
        }
        TypeReference _returnType = annotatedMethod_1.getReturnType();
        TypeReference _newTypeReference = context.newTypeReference("boolean");
        boolean _notEquals = (!Objects.equal(_returnType, _newTypeReference));
        if (_notEquals) {
          context.addError(annotatedMethod_1, "Precondition must return a boolean");
          return;
        }
        String _simpleName = annotatedMethod_1.getSimpleName();
        boolean _startsWith = _simpleName.startsWith("pre");
        boolean _not = (!_startsWith);
        if (_not) {
          context.addError(annotatedMethod_1, "Precondition must be nammed pre... (convention)");
          return;
        }
        MutableTypeDeclaration _declaringType = annotatedMethod_1.getDeclaringType();
        Iterable<? extends MutableMethodDeclaration> _declaredMethods_2 = _declaringType.getDeclaredMethods();
        final Function1<MutableMethodDeclaration,Boolean> _function_2 = new Function1<MutableMethodDeclaration,Boolean>() {
          public Boolean apply(final MutableMethodDeclaration m) {
            String _simpleName = m.getSimpleName();
            String _simpleName_1 = annotatedMethod_1.getSimpleName();
            String _substring = _simpleName_1.substring(3);
            boolean _equals = Objects.equal(_simpleName, _substring);
            return Boolean.valueOf(_equals);
          }
        };
        Iterable<? extends MutableMethodDeclaration> _filter = IterableExtensions.filter(_declaredMethods_2, _function_2);
        int _size_1 = IterableExtensions.size(_filter);
        boolean _equals = (_size_1 == 0);
        if (_equals) {
          context.addError(annotatedMethod_1, "Precondition must be have the name preX where X is an existing method");
          return;
        }
      }
    }
    for (final MutableMethodDeclaration annotatedMethod_2 : post) {
      {
        List<MutableParameterDeclaration> _parameters = annotatedMethod_2.getParameters();
        int _size = _parameters.size();
        boolean _greaterThan = (_size > 0);
        if (_greaterThan) {
          context.addError(annotatedMethod_2, "Postcondition must not have a parameter");
          return;
        }
        TypeReference _returnType = annotatedMethod_2.getReturnType();
        TypeReference _newTypeReference = context.newTypeReference("boolean");
        boolean _notEquals = (!Objects.equal(_returnType, _newTypeReference));
        if (_notEquals) {
          context.addError(annotatedMethod_2, "Postcondition must return a boolean");
          return;
        }
        String _simpleName = annotatedMethod_2.getSimpleName();
        boolean _startsWith = _simpleName.startsWith("post");
        boolean _not = (!_startsWith);
        if (_not) {
          context.addError(annotatedMethod_2, "Postcondition must be nammed post... (convention)");
          return;
        }
        MutableTypeDeclaration _declaringType = annotatedMethod_2.getDeclaringType();
        Iterable<? extends MutableMethodDeclaration> _declaredMethods_2 = _declaringType.getDeclaredMethods();
        final Function1<MutableMethodDeclaration,Boolean> _function_2 = new Function1<MutableMethodDeclaration,Boolean>() {
          public Boolean apply(final MutableMethodDeclaration m) {
            String _simpleName = m.getSimpleName();
            String _simpleName_1 = annotatedMethod_2.getSimpleName();
            String _substring = _simpleName_1.substring(4);
            boolean _equals = Objects.equal(_simpleName, _substring);
            return Boolean.valueOf(_equals);
          }
        };
        Iterable<? extends MutableMethodDeclaration> _filter = IterableExtensions.filter(_declaredMethods_2, _function_2);
        int _size_1 = IterableExtensions.size(_filter);
        boolean _equals = (_size_1 == 0);
        if (_equals) {
          context.addError(annotatedMethod_2, "Postcondition must be have the name postX where X is an existing method");
          return;
        }
      }
    }
    int _size = invs.size();
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      Iterable<? extends MutableMethodDeclaration> _declaredMethods_2 = annotateClass.getDeclaredMethods();
      final Function1<MutableMethodDeclaration,Boolean> _function_2 = new Function1<MutableMethodDeclaration,Boolean>() {
        public Boolean apply(final MutableMethodDeclaration m) {
          boolean _or = false;
          Iterable<? extends MutableAnnotationReference> _annotations = m.getAnnotations();
          final Function1<MutableAnnotationReference,Boolean> _function = new Function1<MutableAnnotationReference,Boolean>() {
            public Boolean apply(final MutableAnnotationReference a) {
              boolean _or = false;
              boolean _or_1 = false;
              AnnotationTypeDeclaration _annotationTypeDeclaration = a.getAnnotationTypeDeclaration();
              String _simpleName = _annotationTypeDeclaration.getSimpleName();
              boolean _equals = Objects.equal(_simpleName, "Pre");
              if (_equals) {
                _or_1 = true;
              } else {
                AnnotationTypeDeclaration _annotationTypeDeclaration_1 = a.getAnnotationTypeDeclaration();
                String _simpleName_1 = _annotationTypeDeclaration_1.getSimpleName();
                boolean _equals_1 = Objects.equal(_simpleName_1, "Post");
                _or_1 = (_equals || _equals_1);
              }
              if (_or_1) {
                _or = true;
              } else {
                AnnotationTypeDeclaration _annotationTypeDeclaration_2 = a.getAnnotationTypeDeclaration();
                String _simpleName_2 = _annotationTypeDeclaration_2.getSimpleName();
                boolean _equals_2 = Objects.equal(_simpleName_2, "Inv");
                _or = (_or_1 || _equals_2);
              }
              return Boolean.valueOf(_or);
            }
          };
          boolean _exists = IterableExtensions.exists(_annotations, _function);
          if (_exists) {
            _or = true;
          } else {
            boolean _isStatic = m.isStatic();
            _or = (_exists || _isStatic);
          }
          boolean _not = (!_or);
          return Boolean.valueOf(_not);
        }
      };
      Iterable<? extends MutableMethodDeclaration> _filter = IterableExtensions.filter(_declaredMethods_2, _function_2);
      for (final MutableMethodDeclaration m : _filter) {
        {
          String _simpleName = m.getSimpleName();
          String _plus = ("prepriv" + _simpleName);
          final Procedure1<MutableMethodDeclaration> _function_3 = new Procedure1<MutableMethodDeclaration>() {
            public void apply(final MutableMethodDeclaration it) {
              it.setVisibility(Visibility.PRIVATE);
              boolean _isStatic = m.isStatic();
              it.setStatic(_isStatic);
              boolean _isFinal = m.isFinal();
              it.setFinal(_isFinal);
              TypeReference _returnType = m.getReturnType();
              it.setReturnType(_returnType);
              Expression _body = m.getBody();
              boolean _equals = Objects.equal(_body, null);
              if (_equals) {
                final CompilationStrategy _function = new CompilationStrategy() {
                  public CharSequence compile(final CompilationContext it) {
                    String _get = bodies.get(m);
                    return _get;
                  }
                };
                it.setBody(_function);
              } else {
                Expression _body_1 = m.getBody();
                it.setBody(_body_1);
              }
              List<MutableParameterDeclaration> _parameters = m.getParameters();
              for (final MutableParameterDeclaration p : _parameters) {
                String _simpleName = p.getSimpleName();
                TypeReference _type = p.getType();
                it.addParameter(_simpleName, _type);
              }
            }
          };
          annotateClass.addMethod(_plus, _function_3);
          String s = "";
          List<MutableParameterDeclaration> _parameters = m.getParameters();
          for (final MutableParameterDeclaration p : _parameters) {
            String _simpleName_1 = p.getSimpleName();
            String _plus_1 = (s + _simpleName_1);
            String _plus_2 = (_plus_1 + ",");
            s = _plus_2;
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
          TypeReference _newTypeReference = context.newTypeReference("void");
          boolean _notEquals = (!Objects.equal(_returnType, _newTypeReference));
          if (_notEquals) {
            ret = "return";
          }
          final String retu = ret;
          String invt = "true";
          ArrayList<MutableMethodDeclaration> _arrayList_1 = new ArrayList<MutableMethodDeclaration>();
          final ArrayList<MutableMethodDeclaration> pres = _arrayList_1;
          String _simpleName_2 = m.getSimpleName();
          this.getAllPre(annotateClass, pres, _simpleName_2, context);
          Random _random = new Random();
          int presconditionmethod_index = _random.nextInt(1000000);
          for (final MutableMethodDeclaration presconditionmethod : pres) {
            Iterable<? extends MutableMethodDeclaration> _declaredMethods_3 = annotateClass.getDeclaredMethods();
            final Function1<MutableMethodDeclaration,Boolean> _function_4 = new Function1<MutableMethodDeclaration,Boolean>() {
              public Boolean apply(final MutableMethodDeclaration m3) {
                boolean _equals = Objects.equal(m3, presconditionmethod);
                return Boolean.valueOf(_equals);
              }
            };
            boolean _exists = IterableExtensions.exists(_declaredMethods_3, _function_4);
            boolean _not = (!_exists);
            if (_not) {
              final String sname = presconditionmethod.getSimpleName();
              String _simpleName_3 = presconditionmethod.getSimpleName();
              final String snameWithIndex = (_simpleName_3 + Integer.valueOf(presconditionmethod_index));
              presconditionmethod.setSimpleName(snameWithIndex);
              MutableTypeDeclaration _declaringType = presconditionmethod.getDeclaringType();
              final Procedure1<MutableMethodDeclaration> _function_5 = new Procedure1<MutableMethodDeclaration>() {
                public void apply(final MutableMethodDeclaration it) {
                  TypeReference _returnType = presconditionmethod.getReturnType();
                  it.setReturnType(_returnType);
                  it.setStatic(false);
                  it.setFinal(false);
                  it.setVisibility(Visibility.PROTECTED);
                  Iterable<? extends MutableAnnotationReference> _annotations = presconditionmethod.getAnnotations();
                  for (final MutableAnnotationReference annot : _annotations) {
                    Type _findTypeGlobally = context.findTypeGlobally(Pre.class);
                    it.addAnnotation(_findTypeGlobally);
                  }
                  final CompilationStrategy _function = new CompilationStrategy() {
                    public CharSequence compile(final CompilationContext it) {
                      StringConcatenation _builder = new StringConcatenation();
                      _builder.append("return this.");
                      _builder.append(snameWithIndex, "");
                      _builder.append("();");
                      return _builder;
                    }
                  };
                  it.setBody(_function);
                }
              };
              _declaringType.addMethod(sname, _function_5);
              Random _random_1 = new Random();
              int _nextInt = _random_1.nextInt(1000000);
              presconditionmethod_index = _nextInt;
            }
          }
          int _size_1 = pres.size();
          boolean _greaterThan_2 = (_size_1 > 0);
          if (_greaterThan_2) {
            invt = " ( false || ";
            for (final MutableMethodDeclaration presconditionmethod_1 : pres) {
              String _simpleName_4 = presconditionmethod_1.getSimpleName();
              String _plus_3 = (invt + _simpleName_4);
              String _plus_4 = (_plus_3 + "() ||");
              invt = _plus_4;
            }
            int _length_2 = invt.length();
            int _minus_1 = (_length_2 - 2);
            String _substring_1 = invt.substring(0, _minus_1);
            String _plus_5 = (_substring_1 + ")");
            invt = _plus_5;
          }
          for (final MutableMethodDeclaration in : invs) {
            String _plus_6 = (invt + "&& ");
            String _simpleName_5 = in.getSimpleName();
            String _plus_7 = (_plus_6 + _simpleName_5);
            String _plus_8 = (_plus_7 + "() ");
            invt = _plus_8;
          }
          final String invt1 = invt;
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("if (");
          _builder.append(invt1, "");
          _builder.append(")");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append(retu, "    ");
          _builder.append(" prepriv");
          String _simpleName_6 = m.getSimpleName();
          _builder.append(_simpleName_6, "    ");
          _builder.append("(");
          _builder.append(s1, "    ");
          _builder.append(");\t\t\t\t\t    ");
          _builder.newLineIfNotEmpty();
          _builder.append("else");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("throw new  fr.inria.triskell.k3.PreConditionViolationException();");
          _builder.newLine();
          final String bodyt = _builder.toString();
          final CompilationStrategy _function_6 = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              return bodyt;
            }
          };
          m.setBody(_function_6);
          bodies.put(m, bodyt);
          String _simpleName_7 = m.getSimpleName();
          String _plus_9 = ("postpriv" + _simpleName_7);
          final Procedure1<MutableMethodDeclaration> _function_7 = new Procedure1<MutableMethodDeclaration>() {
            public void apply(final MutableMethodDeclaration it) {
              it.setVisibility(Visibility.PRIVATE);
              boolean _isStatic = m.isStatic();
              it.setStatic(_isStatic);
              boolean _isFinal = m.isFinal();
              it.setFinal(_isFinal);
              TypeReference _returnType = m.getReturnType();
              it.setReturnType(_returnType);
              Expression _body = m.getBody();
              boolean _equals = Objects.equal(_body, null);
              if (_equals) {
                final CompilationStrategy _function = new CompilationStrategy() {
                  public CharSequence compile(final CompilationContext it) {
                    String _get = bodies.get(m);
                    return _get;
                  }
                };
                it.setBody(_function);
              } else {
                Expression _body_1 = m.getBody();
                it.setBody(_body_1);
              }
              List<MutableParameterDeclaration> _parameters = m.getParameters();
              for (final MutableParameterDeclaration p : _parameters) {
                String _simpleName = p.getSimpleName();
                TypeReference _type = p.getType();
                it.addParameter(_simpleName, _type);
              }
            }
          };
          annotateClass.addMethod(_plus_9, _function_7);
          s = "";
          List<MutableParameterDeclaration> _parameters_1 = m.getParameters();
          for (final MutableParameterDeclaration p_1 : _parameters_1) {
            String _simpleName_8 = p_1.getSimpleName();
            String _plus_10 = (s + _simpleName_8);
            String _plus_11 = (_plus_10 + ",");
            s = _plus_11;
          }
          int _length_3 = s.length();
          boolean _greaterThan_3 = (_length_3 > 0);
          if (_greaterThan_3) {
            int _length_4 = s.length();
            int _minus_2 = (_length_4 - 1);
            String _substring_2 = s.substring(0, _minus_2);
            s = _substring_2;
          }
          ret = "";
          String ret1 = "";
          TypeReference _returnType_1 = m.getReturnType();
          TypeReference _newTypeReference_1 = context.newTypeReference("void");
          boolean _notEquals_1 = (!Objects.equal(_returnType_1, _newTypeReference_1));
          if (_notEquals_1) {
            TypeReference _returnType_2 = m.getReturnType();
            String _name = _returnType_2.getName();
            String _plus_12 = (_name + " __ret = ");
            ret = _plus_12;
            ret1 = "return __ret;";
          }
          invt = "true";
          ArrayList<MutableMethodDeclaration> _arrayList_2 = new ArrayList<MutableMethodDeclaration>();
          final ArrayList<MutableMethodDeclaration> posts = _arrayList_2;
          String _simpleName_9 = m.getSimpleName();
          this.getAllPost(annotateClass, posts, _simpleName_9, context);
          Random _random_2 = new Random();
          int postsconditionmethod_index = _random_2.nextInt(1000000);
          for (final MutableMethodDeclaration postsconditionmethod : posts) {
            Iterable<? extends MutableMethodDeclaration> _declaredMethods_4 = annotateClass.getDeclaredMethods();
            final Function1<MutableMethodDeclaration,Boolean> _function_8 = new Function1<MutableMethodDeclaration,Boolean>() {
              public Boolean apply(final MutableMethodDeclaration m3) {
                boolean _equals = Objects.equal(m3, postsconditionmethod);
                return Boolean.valueOf(_equals);
              }
            };
            boolean _exists_1 = IterableExtensions.exists(_declaredMethods_4, _function_8);
            boolean _not_1 = (!_exists_1);
            if (_not_1) {
              final String sname_1 = postsconditionmethod.getSimpleName();
              String _simpleName_10 = postsconditionmethod.getSimpleName();
              final String snameWithIndex_1 = (_simpleName_10 + Integer.valueOf(postsconditionmethod_index));
              postsconditionmethod.setSimpleName(snameWithIndex_1);
              MutableTypeDeclaration _declaringType_1 = postsconditionmethod.getDeclaringType();
              final Procedure1<MutableMethodDeclaration> _function_9 = new Procedure1<MutableMethodDeclaration>() {
                public void apply(final MutableMethodDeclaration it) {
                  TypeReference _returnType = postsconditionmethod.getReturnType();
                  it.setReturnType(_returnType);
                  it.setStatic(false);
                  it.setFinal(false);
                  it.setVisibility(Visibility.PROTECTED);
                  Type _findTypeGlobally = context.findTypeGlobally(Post.class);
                  it.addAnnotation(_findTypeGlobally);
                  final CompilationStrategy _function = new CompilationStrategy() {
                    public CharSequence compile(final CompilationContext it) {
                      StringConcatenation _builder = new StringConcatenation();
                      _builder.append("return this.");
                      _builder.append(snameWithIndex_1, "");
                      _builder.append("();");
                      return _builder;
                    }
                  };
                  it.setBody(_function);
                }
              };
              _declaringType_1.addMethod(sname_1, _function_9);
              Random _random_3 = new Random();
              int _nextInt_1 = _random_3.nextInt(1000000);
              postsconditionmethod_index = _nextInt_1;
            }
          }
          int _size_2 = posts.size();
          boolean _greaterThan_4 = (_size_2 > 0);
          if (_greaterThan_4) {
            invt = " ( true && ";
            for (final MutableMethodDeclaration postsconditionmethod_1 : posts) {
              String _simpleName_11 = postsconditionmethod_1.getSimpleName();
              String _plus_13 = (invt + _simpleName_11);
              String _plus_14 = (_plus_13 + "() &&");
              invt = _plus_14;
            }
            int _length_5 = invt.length();
            int _minus_3 = (_length_5 - 2);
            String _substring_3 = invt.substring(0, _minus_3);
            String _plus_15 = (_substring_3 + ")");
            invt = _plus_15;
          }
          for (final MutableMethodDeclaration in_1 : invs) {
            String _plus_16 = (invt + "&& ");
            String _simpleName_12 = in_1.getSimpleName();
            String _plus_17 = (_plus_16 + _simpleName_12);
            String _plus_18 = (_plus_17 + "() ");
            invt = _plus_18;
          }
          final String invt2 = invt;
          final String retu2 = retu;
          final String retu3 = ret1;
          final String s2 = s;
          final CompilationStrategy _function_10 = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append(retu2, "");
              _builder.append("postpriv");
              String _simpleName = m.getSimpleName();
              _builder.append(_simpleName, "");
              _builder.append("(");
              _builder.append(s2, "");
              _builder.append(");");
              _builder.newLineIfNotEmpty();
              _builder.append("if (!(");
              _builder.append(invt2, "");
              _builder.append("))");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("throw new  fr.inria.triskell.k3.PostConditionViolationException();");
              _builder.newLine();
              _builder.append(retu3, "");
              _builder.append("\t");
              _builder.newLineIfNotEmpty();
              return _builder;
            }
          };
          m.setBody(_function_10);
        }
      }
    } else {
      for (final MutableMethodDeclaration annotatedMethod_3 : pre) {
        {
          MutableTypeDeclaration _declaringType = annotatedMethod_3.getDeclaringType();
          Iterable<? extends MutableMethodDeclaration> _declaredMethods_3 = _declaringType.getDeclaredMethods();
          final Function1<MutableMethodDeclaration,Boolean> _function_3 = new Function1<MutableMethodDeclaration,Boolean>() {
            public Boolean apply(final MutableMethodDeclaration m) {
              String _simpleName = m.getSimpleName();
              String _simpleName_1 = annotatedMethod_3.getSimpleName();
              String _substring = _simpleName_1.substring(3);
              boolean _equals = Objects.equal(_simpleName, _substring);
              return Boolean.valueOf(_equals);
            }
          };
          Iterable<? extends MutableMethodDeclaration> _filter_1 = IterableExtensions.filter(_declaredMethods_3, _function_3);
          final MutableMethodDeclaration m_1 = ((MutableMethodDeclaration[])Conversions.unwrapArray(_filter_1, MutableMethodDeclaration.class))[0];
          MutableTypeDeclaration _declaringType_1 = annotatedMethod_3.getDeclaringType();
          String _simpleName = m_1.getSimpleName();
          String _plus = ("prepriv" + _simpleName);
          final Procedure1<MutableMethodDeclaration> _function_4 = new Procedure1<MutableMethodDeclaration>() {
            public void apply(final MutableMethodDeclaration it) {
              it.setVisibility(Visibility.PRIVATE);
              boolean _isStatic = m_1.isStatic();
              it.setStatic(_isStatic);
              boolean _isFinal = m_1.isFinal();
              it.setFinal(_isFinal);
              TypeReference _returnType = m_1.getReturnType();
              it.setReturnType(_returnType);
              Expression _body = m_1.getBody();
              boolean _equals = Objects.equal(_body, null);
              if (_equals) {
                final CompilationStrategy _function = new CompilationStrategy() {
                  public CharSequence compile(final CompilationContext it) {
                    String _get = bodies.get(m_1);
                    return _get;
                  }
                };
                it.setBody(_function);
              } else {
                Expression _body_1 = m_1.getBody();
                it.setBody(_body_1);
              }
              List<MutableParameterDeclaration> _parameters = m_1.getParameters();
              for (final MutableParameterDeclaration p : _parameters) {
                String _simpleName = p.getSimpleName();
                TypeReference _type = p.getType();
                it.addParameter(_simpleName, _type);
              }
            }
          };
          _declaringType_1.addMethod(_plus, _function_4);
          String s = "";
          List<MutableParameterDeclaration> _parameters = m_1.getParameters();
          for (final MutableParameterDeclaration p : _parameters) {
            String _simpleName_1 = p.getSimpleName();
            String _plus_1 = (s + _simpleName_1);
            String _plus_2 = (_plus_1 + ",");
            s = _plus_2;
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
          TypeReference _returnType = m_1.getReturnType();
          TypeReference _newTypeReference = context.newTypeReference("void");
          boolean _notEquals = (!Objects.equal(_returnType, _newTypeReference));
          if (_notEquals) {
            ret = "return";
          }
          final String retu = ret;
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("if (pre");
          String _simpleName_2 = m_1.getSimpleName();
          _builder.append(_simpleName_2, "");
          _builder.append("())");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append(retu, "    ");
          _builder.append(" prepriv");
          String _simpleName_3 = m_1.getSimpleName();
          _builder.append(_simpleName_3, "    ");
          _builder.append("(");
          _builder.append(s1, "    ");
          _builder.append(");\t\t\t\t\t    ");
          _builder.newLineIfNotEmpty();
          _builder.append("else");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("throw new  fr.inria.triskell.k3.PreConditionViolationException();");
          _builder.newLine();
          final String bodyt = _builder.toString();
          final CompilationStrategy _function_5 = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              return bodyt;
            }
          };
          m_1.setBody(_function_5);
          bodies.put(m_1, bodyt);
        }
      }
      for (final MutableMethodDeclaration annotatedMethod_4 : post) {
        {
          MutableTypeDeclaration _declaringType = annotatedMethod_4.getDeclaringType();
          Iterable<? extends MutableMethodDeclaration> _declaredMethods_3 = _declaringType.getDeclaredMethods();
          final Function1<MutableMethodDeclaration,Boolean> _function_3 = new Function1<MutableMethodDeclaration,Boolean>() {
            public Boolean apply(final MutableMethodDeclaration m) {
              String _simpleName = m.getSimpleName();
              String _simpleName_1 = annotatedMethod_4.getSimpleName();
              String _substring = _simpleName_1.substring(4);
              boolean _equals = Objects.equal(_simpleName, _substring);
              return Boolean.valueOf(_equals);
            }
          };
          Iterable<? extends MutableMethodDeclaration> _filter_1 = IterableExtensions.filter(_declaredMethods_3, _function_3);
          final MutableMethodDeclaration m_1 = ((MutableMethodDeclaration[])Conversions.unwrapArray(_filter_1, MutableMethodDeclaration.class))[0];
          MutableTypeDeclaration _declaringType_1 = annotatedMethod_4.getDeclaringType();
          String _simpleName = m_1.getSimpleName();
          String _plus = ("postpriv" + _simpleName);
          final Procedure1<MutableMethodDeclaration> _function_4 = new Procedure1<MutableMethodDeclaration>() {
            public void apply(final MutableMethodDeclaration it) {
              it.setVisibility(Visibility.PRIVATE);
              boolean _isStatic = m_1.isStatic();
              it.setStatic(_isStatic);
              boolean _isFinal = m_1.isFinal();
              it.setFinal(_isFinal);
              TypeReference _returnType = m_1.getReturnType();
              it.setReturnType(_returnType);
              Expression _body = m_1.getBody();
              boolean _equals = Objects.equal(_body, null);
              if (_equals) {
                final CompilationStrategy _function = new CompilationStrategy() {
                  public CharSequence compile(final CompilationContext it) {
                    String _get = bodies.get(m_1);
                    return _get;
                  }
                };
                it.setBody(_function);
              } else {
                Expression _body_1 = m_1.getBody();
                it.setBody(_body_1);
              }
              List<MutableParameterDeclaration> _parameters = m_1.getParameters();
              for (final MutableParameterDeclaration p : _parameters) {
                String _simpleName = p.getSimpleName();
                TypeReference _type = p.getType();
                it.addParameter(_simpleName, _type);
              }
            }
          };
          _declaringType_1.addMethod(_plus, _function_4);
          String s = "";
          List<MutableParameterDeclaration> _parameters = m_1.getParameters();
          for (final MutableParameterDeclaration p : _parameters) {
            String _simpleName_1 = p.getSimpleName();
            String _plus_1 = (s + _simpleName_1);
            String _plus_2 = (_plus_1 + ",");
            s = _plus_2;
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
          String ret1 = "";
          TypeReference _returnType = m_1.getReturnType();
          TypeReference _newTypeReference = context.newTypeReference("void");
          boolean _notEquals = (!Objects.equal(_returnType, _newTypeReference));
          if (_notEquals) {
            TypeReference _returnType_1 = m_1.getReturnType();
            String _name = _returnType_1.getName();
            String _plus_3 = (_name + " __ret = ");
            ret = _plus_3;
            ret1 = "return __ret;";
          }
          final String retu = ret;
          final String retu1 = ret1;
          final CompilationStrategy _function_5 = new CompilationStrategy() {
            public CharSequence compile(final CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append(retu, "");
              _builder.append("postpriv");
              String _simpleName = m_1.getSimpleName();
              _builder.append(_simpleName, "");
              _builder.append("(");
              _builder.append(s1, "");
              _builder.append(");");
              _builder.newLineIfNotEmpty();
              _builder.append("if (!post");
              String _simpleName_1 = m_1.getSimpleName();
              _builder.append(_simpleName_1, "");
              _builder.append("())");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("throw new  fr.inria.triskell.k3.PostConditionViolationException();");
              _builder.newLine();
              _builder.append(retu1, "");
              _builder.append("\t");
              _builder.newLineIfNotEmpty();
              return _builder;
            }
          };
          m_1.setBody(_function_5);
        }
      }
    }
  }
}
