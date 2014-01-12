/**
 */
package robotG.robot;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see robotG.robot.RobotPackage
 * @generated
 */
public interface RobotFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	RobotFactory eINSTANCE = robotG.robot.impl.RobotFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Move</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Move</em>'.
   * @generated
   */
	Move createMove();

	/**
   * Returns a new object of class '<em>Bip</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Bip</em>'.
   * @generated
   */
	Bip createBip();

	/**
   * Returns a new object of class '<em>Turn</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Turn</em>'.
   * @generated
   */
	Turn createTurn();

	/**
   * Returns a new object of class '<em>Set Turn Angle</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Set Turn Angle</em>'.
   * @generated
   */
	SetTurnAngle createSetTurnAngle();

	/**
   * Returns a new object of class '<em>Has Turned</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Has Turned</em>'.
   * @generated
   */
	HasTurned createHasTurned();

	/**
   * Returns a new object of class '<em>Display</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Display</em>'.
   * @generated
   */
	Display createDisplay();

	/**
   * Returns a new object of class '<em>Obstacle</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Obstacle</em>'.
   * @generated
   */
	Obstacle createObstacle();

	/**
   * Returns a new object of class '<em>Stop Engine</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Stop Engine</em>'.
   * @generated
   */
	StopEngine createStopEngine();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	RobotPackage getRobotPackage();

} //RobotFactory
