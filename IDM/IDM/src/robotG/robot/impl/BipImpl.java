/**
 */
package robotG.robot.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import robotG.robot.Bip;
import robotG.robot.RobotPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bip</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link robotG.robot.impl.BipImpl#getDuration <em>Duration</em>}</li>
 *   <li>{@link robotG.robot.impl.BipImpl#getPower <em>Power</em>}</li>
 *   <li>{@link robotG.robot.impl.BipImpl#isRepeat <em>Repeat</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BipImpl extends CommandeRobotImpl implements Bip {
	/**
   * The default value of the '{@link #getDuration() <em>Duration</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getDuration()
   * @generated
   * @ordered
   */
	protected static final int DURATION_EDEFAULT = 0;

	/**
   * The cached value of the '{@link #getDuration() <em>Duration</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getDuration()
   * @generated
   * @ordered
   */
	protected int duration = DURATION_EDEFAULT;

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
   * The default value of the '{@link #isRepeat() <em>Repeat</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isRepeat()
   * @generated
   * @ordered
   */
	protected static final boolean REPEAT_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isRepeat() <em>Repeat</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isRepeat()
   * @generated
   * @ordered
   */
	protected boolean repeat = REPEAT_EDEFAULT;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected BipImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return RobotPackage.Literals.BIP;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public int getDuration() {
    return duration;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setDuration(int newDuration) {
    int oldDuration = duration;
    duration = newDuration;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RobotPackage.BIP__DURATION, oldDuration, duration));
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
      eNotify(new ENotificationImpl(this, Notification.SET, RobotPackage.BIP__POWER, oldPower, power));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public boolean isRepeat() {
    return repeat;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setRepeat(boolean newRepeat) {
    boolean oldRepeat = repeat;
    repeat = newRepeat;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RobotPackage.BIP__REPEAT, oldRepeat, repeat));
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
      case RobotPackage.BIP__DURATION:
        return getDuration();
      case RobotPackage.BIP__POWER:
        return getPower();
      case RobotPackage.BIP__REPEAT:
        return isRepeat();
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
      case RobotPackage.BIP__DURATION:
        setDuration((Integer)newValue);
        return;
      case RobotPackage.BIP__POWER:
        setPower((Integer)newValue);
        return;
      case RobotPackage.BIP__REPEAT:
        setRepeat((Boolean)newValue);
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
      case RobotPackage.BIP__DURATION:
        setDuration(DURATION_EDEFAULT);
        return;
      case RobotPackage.BIP__POWER:
        setPower(POWER_EDEFAULT);
        return;
      case RobotPackage.BIP__REPEAT:
        setRepeat(REPEAT_EDEFAULT);
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
      case RobotPackage.BIP__DURATION:
        return duration != DURATION_EDEFAULT;
      case RobotPackage.BIP__POWER:
        return power != POWER_EDEFAULT;
      case RobotPackage.BIP__REPEAT:
        return repeat != REPEAT_EDEFAULT;
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
    result.append(" (duration: ");
    result.append(duration);
    result.append(", power: ");
    result.append(power);
    result.append(", repeat: ");
    result.append(repeat);
    result.append(')');
    return result.toString();
  }

} //BipImpl
