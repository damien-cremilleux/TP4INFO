package de.oehme.xtend.contrib.base;

import de.oehme.xtend.contrib.base.ASTExtensions;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy.CompilationContext;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public abstract class MethodMemoizer {
  @Extension
  protected final TransformationContext context;
  
  protected final MutableMethodDeclaration method;
  
  protected final int index;
  
  public MethodMemoizer(final MutableMethodDeclaration method, final TransformationContext context, final int index) {
    this.method = method;
    this.context = context;
    this.index = index;
  }
  
  public final MutableMethodDeclaration generate() {
    final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
      public void apply(final MutableMethodDeclaration it) {
        TypeReference _returnType = it.getReturnType();
        TypeReference _wrapperIfPrimitive = _returnType.getWrapperIfPrimitive();
        it.setReturnType(_wrapperIfPrimitive);
        String _initMethodName = MethodMemoizer.this.initMethodName();
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationContext it) {
            CharSequence _cacheCall = MethodMemoizer.this.cacheCall(it);
            return _cacheCall;
          }
        };
        ASTExtensions.addIndirection(it, _initMethodName, _function);
        MutableTypeDeclaration _declaringType = it.getDeclaringType();
        final Procedure1<MutableTypeDeclaration> _function_1 = new Procedure1<MutableTypeDeclaration>() {
          public void apply(final MutableTypeDeclaration it) {
            String _cacheFieldName = MethodMemoizer.this.cacheFieldName();
            final Procedure1<MutableFieldDeclaration> _function = new Procedure1<MutableFieldDeclaration>() {
              public void apply(final MutableFieldDeclaration it) {
                boolean _isStatic = MethodMemoizer.this.method.isStatic();
                it.setStatic(_isStatic);
                TypeReference _cacheFieldType = MethodMemoizer.this.cacheFieldType();
                it.setType(_cacheFieldType);
                final CompilationStrategy _function = new CompilationStrategy() {
                  public CharSequence compile(final CompilationContext it) {
                    CharSequence _cacheFieldInit = MethodMemoizer.this.cacheFieldInit(it);
                    return _cacheFieldInit;
                  }
                };
                it.setInitializer(_function);
              }
            };
            it.addField(_cacheFieldName, _function);
          }
        };
        ObjectExtensions.<MutableTypeDeclaration>operator_doubleArrow(_declaringType, _function_1);
      }
    };
    MutableMethodDeclaration _doubleArrow = ObjectExtensions.<MutableMethodDeclaration>operator_doubleArrow(
      this.method, _function);
    return _doubleArrow;
  }
  
  protected final String initMethodName() {
    StringConcatenation _builder = new StringConcatenation();
    String _simpleName = this.method.getSimpleName();
    _builder.append(_simpleName, "");
    _builder.append("_init");
    return _builder.toString();
  }
  
  protected final String cacheFieldName() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("cache");
    _builder.append(this.index, "");
    _builder.append("_");
    String _simpleName = this.method.getSimpleName();
    _builder.append(_simpleName, "");
    return _builder.toString();
  }
  
  protected abstract CharSequence cacheCall(final CompilationContext context);
  
  protected abstract TypeReference cacheFieldType();
  
  protected abstract CharSequence cacheFieldInit(final CompilationContext context);
}
