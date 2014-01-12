package fr.inria.triskell.k3;

import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class sortMethod implements Comparator<MutableMethodDeclaration> {
  private TransformationContext context;
  
  public sortMethod(final TransformationContext context) {
    this.context = context;
  }
  
  public int compare(final MutableMethodDeclaration arg0, final MutableMethodDeclaration arg2) {
    ArrayList<MutableClassDeclaration> _arrayList = new ArrayList<MutableClassDeclaration>();
    final ArrayList<MutableClassDeclaration> ext = _arrayList;
    MutableTypeDeclaration _declaringType = arg0.getDeclaringType();
    this.getSuperClass(ext, ((MutableClassDeclaration) _declaringType), this.context);
    ArrayList<MutableClassDeclaration> _arrayList_1 = new ArrayList<MutableClassDeclaration>();
    final ArrayList<MutableClassDeclaration> ext1 = _arrayList_1;
    MutableTypeDeclaration _declaringType_1 = arg2.getDeclaringType();
    this.getSuperClass(ext1, ((MutableClassDeclaration) _declaringType_1), this.context);
    MutableTypeDeclaration _declaringType_2 = arg2.getDeclaringType();
    boolean _contains = ext.contains(_declaringType_2);
    if (_contains) {
      return (-1);
    } else {
      MutableTypeDeclaration _declaringType_3 = arg0.getDeclaringType();
      boolean _contains_1 = ext1.contains(_declaringType_3);
      if (_contains_1) {
        return 1;
      } else {
        return 0;
      }
    }
  }
  
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
}
