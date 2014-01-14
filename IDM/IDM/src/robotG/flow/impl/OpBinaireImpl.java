/**
 */
package robotG.flow.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import robotG.flow.ExprBool;
import robotG.flow.FlowPackage;
import robotG.flow.OpBinaire;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Op Binaire</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link robotG.flow.impl.OpBinaireImpl#getFilsGauche <em>Fils Gauche</em>}</li>
 *   <li>{@link robotG.flow.impl.OpBinaireImpl#getFilsDroit <em>Fils Droit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class OpBinaireImpl extends ExprBoolImpl implements OpBinaire {
	/**
   * The cached value of the '{@link #getFilsGauche() <em>Fils Gauche</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getFilsGauche()
   * @generated
   * @ordered
   */
	protected ExprBool filsGauche;

	/**
   * The cached value of the '{@link #getFilsDroit() <em>Fils Droit</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getFilsDroit()
   * @generated
   * @ordered
   */
	protected ExprBool filsDroit;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected OpBinaireImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return FlowPackage.Literals.OP_BINAIRE;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ExprBool getFilsGauche() {
    return filsGauche;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetFilsGauche(ExprBool newFilsGauche, NotificationChain msgs) {
    ExprBool oldFilsGauche = filsGauche;
    filsGauche = newFilsGauche;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FlowPackage.OP_BINAIRE__FILS_GAUCHE, oldFilsGauche, newFilsGauche);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setFilsGauche(ExprBool newFilsGauche) {
    if (newFilsGauche != filsGauche)
    {
      NotificationChain msgs = null;
      if (filsGauche != null)
        msgs = ((InternalEObject)filsGauche).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FlowPackage.OP_BINAIRE__FILS_GAUCHE, null, msgs);
      if (newFilsGauche != null)
        msgs = ((InternalEObject)newFilsGauche).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FlowPackage.OP_BINAIRE__FILS_GAUCHE, null, msgs);
      msgs = basicSetFilsGauche(newFilsGauche, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.OP_BINAIRE__FILS_GAUCHE, newFilsGauche, newFilsGauche));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ExprBool getFilsDroit() {
    return filsDroit;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetFilsDroit(ExprBool newFilsDroit, NotificationChain msgs) {
    ExprBool oldFilsDroit = filsDroit;
    filsDroit = newFilsDroit;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FlowPackage.OP_BINAIRE__FILS_DROIT, oldFilsDroit, newFilsDroit);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setFilsDroit(ExprBool newFilsDroit) {
    if (newFilsDroit != filsDroit)
    {
      NotificationChain msgs = null;
      if (filsDroit != null)
        msgs = ((InternalEObject)filsDroit).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FlowPackage.OP_BINAIRE__FILS_DROIT, null, msgs);
      if (newFilsDroit != null)
        msgs = ((InternalEObject)newFilsDroit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FlowPackage.OP_BINAIRE__FILS_DROIT, null, msgs);
      msgs = basicSetFilsDroit(newFilsDroit, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.OP_BINAIRE__FILS_DROIT, newFilsDroit, newFilsDroit));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID)
    {
      case FlowPackage.OP_BINAIRE__FILS_GAUCHE:
        return basicSetFilsGauche(null, msgs);
      case FlowPackage.OP_BINAIRE__FILS_DROIT:
        return basicSetFilsDroit(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID)
    {
      case FlowPackage.OP_BINAIRE__FILS_GAUCHE:
        return getFilsGauche();
      case FlowPackage.OP_BINAIRE__FILS_DROIT:
        return getFilsDroit();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID)
    {
      case FlowPackage.OP_BINAIRE__FILS_GAUCHE:
        setFilsGauche((ExprBool)newValue);
        return;
      case FlowPackage.OP_BINAIRE__FILS_DROIT:
        setFilsDroit((ExprBool)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eUnset(int featureID) {
    switch (featureID)
    {
      case FlowPackage.OP_BINAIRE__FILS_GAUCHE:
        setFilsGauche((ExprBool)null);
        return;
      case FlowPackage.OP_BINAIRE__FILS_DROIT:
        setFilsDroit((ExprBool)null);
        return;
    }
    super.eUnset(featureID);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public boolean eIsSet(int featureID) {
    switch (featureID)
    {
      case FlowPackage.OP_BINAIRE__FILS_GAUCHE:
        return filsGauche != null;
      case FlowPackage.OP_BINAIRE__FILS_DROIT:
        return filsDroit != null;
    }
    return super.eIsSet(featureID);
  }

} //OpBinaireImpl
