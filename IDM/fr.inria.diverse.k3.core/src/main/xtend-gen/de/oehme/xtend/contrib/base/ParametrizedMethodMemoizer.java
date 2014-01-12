package de.oehme.xtend.contrib.base;

import de.oehme.xtend.contrib.base.MethodMemoizer;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy.CompilationContext;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;

/**
 * Uses Guava's LoadingCache to store the return value for each combination of parameters
 */
@SuppressWarnings("all")
public abstract class ParametrizedMethodMemoizer extends MethodMemoizer {
  public ParametrizedMethodMemoizer(final MutableMethodDeclaration method, final TransformationContext context, final int index) {
    super(method, context, index);
  }
  
  protected final CharSequence cacheFieldInit(@Extension final CompilationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("com.google.common.cache.CacheBuilder.newBuilder()");
    _builder.newLine();
    _builder.append(".build(new com.google.common.cache.CacheLoader<");
    TypeReference _cacheKeyType = this.cacheKeyType();
    String _javaCode = context.toJavaCode(_cacheKeyType);
    _builder.append(_javaCode, "");
    _builder.append(", ");
    TypeReference _returnType = this.method.getReturnType();
    String _javaCode_1 = context.toJavaCode(_returnType);
    _builder.append(_javaCode_1, "");
    _builder.append(">() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    TypeReference _returnType_1 = this.method.getReturnType();
    String _javaCode_2 = context.toJavaCode(_returnType_1);
    _builder.append(_javaCode_2, "	");
    _builder.append(" load(");
    TypeReference _cacheKeyType_1 = this.cacheKeyType();
    String _javaCode_3 = context.toJavaCode(_cacheKeyType_1);
    _builder.append(_javaCode_3, "	");
    _builder.append(" key) throws Exception {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return ");
    String _initMethodName = this.initMethodName();
    _builder.append(_initMethodName, "		");
    _builder.append("(");
    CharSequence _cacheKeyToParameters = this.cacheKeyToParameters(context);
    _builder.append(_cacheKeyToParameters, "		");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("})");
    _builder.newLine();
    return _builder;
  }
  
  protected final TypeReference cacheFieldType() {
    TypeReference _cacheKeyType = this.cacheKeyType();
    TypeReference _returnType = this.method.getReturnType();
    TypeReference _newTypeReference = this.context.newTypeReference(
      "com.google.common.cache.LoadingCache", _cacheKeyType, _returnType);
    return _newTypeReference;
  }
  
  protected final CharSequence cacheCall(@Extension final CompilationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return ");
    String _cacheFieldName = this.cacheFieldName();
    _builder.append(_cacheFieldName, "	");
    _builder.append(".get(");
    CharSequence _parametersToCacheKey = this.parametersToCacheKey(context);
    _builder.append(_parametersToCacheKey, "	");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("} catch (Throwable e) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (e instanceof java.util.concurrent.ExecutionException");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("|| e instanceof com.google.common.util.concurrent.UncheckedExecutionException");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("|| e instanceof com.google.common.util.concurrent.ExecutionError) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Throwable cause = e.getCause();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("throw ");
    TypeReference _newTypeReference = this.context.newTypeReference(Exceptions.class);
    String _javaCode = context.toJavaCode(_newTypeReference);
    _builder.append(_javaCode, "		");
    _builder.append(".sneakyThrow(cause);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("throw ");
    TypeReference _newTypeReference_1 = this.context.newTypeReference(Exceptions.class);
    String _javaCode_1 = context.toJavaCode(_newTypeReference_1);
    _builder.append(_javaCode_1, "		");
    _builder.append(".sneakyThrow(e);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected abstract TypeReference cacheKeyType();
  
  protected abstract CharSequence parametersToCacheKey(final CompilationContext context);
  
  protected abstract CharSequence cacheKeyToParameters(final CompilationContext context);
}
