/**
 */
package robotG.robot.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import robotG.robot.RobotPackage;
import robotG.robot.Turn;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Turn</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link robotG.robot.impl.TurnImpl#getPower <em>Power</em>}</li>
 *   <li>{@link robotG.robot.impl.TurnImpl#getAngle <em>Angle</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TurnImpl extends CommandeRobotImpl implements Turn {
	/**
   * The default value of the '{@link #getPower() <em>Power</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getPower()
   * @generated
   * @ordered
   */
	protected static final int POWER_EDEFAULT = 0;

	/**
   * The cached value of the '{@link #getPower() <em>Power</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getPower()
   * @generated
   * @ordered
   */
	protected int power = POWER_EDEFAULT;

	/**
   * The default value of the '{@link #getAngle() <em>Angle</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAngle()
   * @generated
   * @ordered
   */
	protected static final int ANGLE_EDEFAULT = 0;

	/**
   * The cached value of the '{@link #getAngle() <em>Angle</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAngle()
   * @generated
   * @ordered
   */
	protected int angle = ANGLE_EDEFAULT;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected TurnImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return RobotPackage.Literals.TURN;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public int getPower() {
    return power;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setPower(int newPower) {
    int oldPower = power;
    power = newPower;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RobotPackage.TURN__POWER, oldPower, power));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public int getAngle() {
    return angle;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setAngle(int newAngle) {
    int oldAngle = angle;
    angle = newAngle;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RobotPackage.TURN__ANGLE, oldAngle, angle));
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
      case RobotPackage.TURN__POWER:
        return getPower();
      case RobotPackage.TURN__ANGLE:
        return getAngle();
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
      case RobotPackage.TURN__POWER:
        setPower((Integer)newValue);
        return;
      case RobotPackage.TURN__ANGLE:
        setAngle((Integer)newValue);
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
      case RobotPackage.TURN__POWER:
        setPower(POWER_EDEFAULT);
        return;
      case RobotPackage.TURN__ANGLE:
        setAngle(ANGLE_EDEFAULT);
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
      case RobotPackage.TURN__POWER:
        return power != POWER_EDEFAULT;
      case RobotPackage.TURN__ANGLE:
        return angle != ANGLE_EDEFAULT;
    }
    return super.eIsSet(featureID);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (power: ");
    result.append(power);
    result.append(", angle: ");
    result.append(angle);
    result.append(')');
    return result.toString();
  }

} //TurnImpl
