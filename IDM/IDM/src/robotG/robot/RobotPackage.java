/**
 */
package robotG.robot;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import robotG.flow.FlowPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see robotG.robot.RobotFactory
 * @model kind="package"
 * @generated
 */
public interface RobotPackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "robot";

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://robot";

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "robot";

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	RobotPackage eINSTANCE = robotG.robot.impl.RobotPackageImpl.init();

	/**
   * The meta object id for the '{@link robotG.robot.impl.CommandeRobotImpl <em>Commande Robot</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see robotG.robot.impl.CommandeRobotImpl
   * @see robotG.robot.impl.RobotPackageImpl#getCommandeRobot()
   * @generated
   */
	int COMMANDE_ROBOT = 8;

	/**
   * The number of structural features of the '<em>Commande Robot</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMANDE_ROBOT_FEATURE_COUNT = FlowPackage.EXPR_FEATURE_COUNT + 0;

	/**
   * The number of operations of the '<em>Commande Robot</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMANDE_ROBOT_OPERATION_COUNT = FlowPackage.EXPR_OPERATION_COUNT + 0;

	/**
   * The meta object id for the '{@link robotG.robot.impl.MoveImpl <em>Move</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see robotG.robot.impl.MoveImpl
   * @see robotG.robot.impl.RobotPackageImpl#getMove()
   * @generated
   */
	int MOVE = 0;

	/**
   * The feature id for the '<em><b>Power</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MOVE__POWER = COMMANDE_ROBOT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Move</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MOVE_FEATURE_COUNT = COMMANDE_ROBOT_FEATURE_COUNT + 1;

	/**
   * The number of operations of the '<em>Move</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MOVE_OPERATION_COUNT = COMMANDE_ROBOT_OPERATION_COUNT + 0;

	/**
   * The meta object id for the '{@link robotG.robot.impl.BipImpl <em>Bip</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see robotG.robot.impl.BipImpl
   * @see robotG.robot.impl.RobotPackageImpl#getBip()
   * @generated
   */
	int BIP = 1;

	/**
   * The feature id for the '<em><b>Duration</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BIP__DURATION = COMMANDE_ROBOT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Power</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BIP__POWER = COMMANDE_ROBOT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Repeat</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BIP__REPEAT = COMMANDE_ROBOT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Bip</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BIP_FEATURE_COUNT = COMMANDE_ROBOT_FEATURE_COUNT + 3;

	/**
   * The number of operations of the '<em>Bip</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BIP_OPERATION_COUNT = COMMANDE_ROBOT_OPERATION_COUNT + 0;

	/**
   * The meta object id for the '{@link robotG.robot.impl.TurnImpl <em>Turn</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see robotG.robot.impl.TurnImpl
   * @see robotG.robot.impl.RobotPackageImpl#getTurn()
   * @generated
   */
	int TURN = 2;

	/**
   * The feature id for the '<em><b>Power</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TURN__POWER = COMMANDE_ROBOT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Angle</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TURN__ANGLE = COMMANDE_ROBOT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Turn</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TURN_FEATURE_COUNT = COMMANDE_ROBOT_FEATURE_COUNT + 2;

	/**
   * The number of operations of the '<em>Turn</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TURN_OPERATION_COUNT = COMMANDE_ROBOT_OPERATION_COUNT + 0;

	/**
   * The meta object id for the '{@link robotG.robot.impl.SetTurnAngleImpl <em>Set Turn Angle</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see robotG.robot.impl.SetTurnAngleImpl
   * @see robotG.robot.impl.RobotPackageImpl#getSetTurnAngle()
   * @generated
   */
	int SET_TURN_ANGLE = 3;

	/**
   * The feature id for the '<em><b>Angle</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SET_TURN_ANGLE__ANGLE = COMMANDE_ROBOT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Set Turn Angle</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SET_TURN_ANGLE_FEATURE_COUNT = COMMANDE_ROBOT_FEATURE_COUNT + 1;

	/**
   * The number of operations of the '<em>Set Turn Angle</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SET_TURN_ANGLE_OPERATION_COUNT = COMMANDE_ROBOT_OPERATION_COUNT + 0;

	/**
   * The meta object id for the '{@link robotG.robot.impl.HasTurnedImpl <em>Has Turned</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see robotG.robot.impl.HasTurnedImpl
   * @see robotG.robot.impl.RobotPackageImpl#getHasTurned()
   * @generated
   */
	int HAS_TURNED = 4;

	/**
   * The feature id for the '<em><b>Angle</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int HAS_TURNED__ANGLE = FlowPackage.EXPR_BOOL_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Has Turned</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int HAS_TURNED_FEATURE_COUNT = FlowPackage.EXPR_BOOL_FEATURE_COUNT + 1;

	/**
   * The number of operations of the '<em>Has Turned</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int HAS_TURNED_OPERATION_COUNT = FlowPackage.EXPR_BOOL_OPERATION_COUNT + 0;

	/**
   * The meta object id for the '{@link robotG.robot.impl.DisplayImpl <em>Display</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see robotG.robot.impl.DisplayImpl
   * @see robotG.robot.impl.RobotPackageImpl#getDisplay()
   * @generated
   */
	int DISPLAY = 5;

	/**
   * The feature id for the '<em><b>Msg</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DISPLAY__MSG = COMMANDE_ROBOT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Duration</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DISPLAY__DURATION = COMMANDE_ROBOT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Line</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DISPLAY__LINE = COMMANDE_ROBOT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Col</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DISPLAY__COL = COMMANDE_ROBOT_FEATURE_COUNT + 3;

	/**
   * The number of structural features of the '<em>Display</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DISPLAY_FEATURE_COUNT = COMMANDE_ROBOT_FEATURE_COUNT + 4;

	/**
   * The number of operations of the '<em>Display</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DISPLAY_OPERATION_COUNT = COMMANDE_ROBOT_OPERATION_COUNT + 0;

	/**
   * The meta object id for the '{@link robotG.robot.impl.ObstacleImpl <em>Obstacle</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see robotG.robot.impl.ObstacleImpl
   * @see robotG.robot.impl.RobotPackageImpl#getObstacle()
   * @generated
   */
	int OBSTACLE = 6;

	/**
   * The feature id for the '<em><b>Distance</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OBSTACLE__DISTANCE = FlowPackage.EXPR_BOOL_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Obstacle</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OBSTACLE_FEATURE_COUNT = FlowPackage.EXPR_BOOL_FEATURE_COUNT + 1;

	/**
   * The number of operations of the '<em>Obstacle</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OBSTACLE_OPERATION_COUNT = FlowPackage.EXPR_BOOL_OPERATION_COUNT + 0;

	/**
   * The meta object id for the '{@link robotG.robot.impl.StopEngineImpl <em>Stop Engine</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see robotG.robot.impl.StopEngineImpl
   * @see robotG.robot.impl.RobotPackageImpl#getStopEngine()
   * @generated
   */
	int STOP_ENGINE = 7;

	/**
   * The number of structural features of the '<em>Stop Engine</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STOP_ENGINE_FEATURE_COUNT = COMMANDE_ROBOT_FEATURE_COUNT + 0;

	/**
   * The number of operations of the '<em>Stop Engine</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STOP_ENGINE_OPERATION_COUNT = COMMANDE_ROBOT_OPERATION_COUNT + 0;


	/**
   * Returns the meta object for class '{@link robotG.robot.Move <em>Move</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Move</em>'.
   * @see robotG.robot.Move
   * @generated
   */
	EClass getMove();

	/**
   * Returns the meta object for the attribute '{@link robotG.robot.Move#getPower <em>Power</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Power</em>'.
   * @see robotG.robot.Move#getPower()
   * @see #getMove()
   * @generated
   */
	EAttribute getMove_Power();

	/**
   * Returns the meta object for class '{@link robotG.robot.Bip <em>Bip</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Bip</em>'.
   * @see robotG.robot.Bip
   * @generated
   */
	EClass getBip();

	/**
   * Returns the meta object for the attribute '{@link robotG.robot.Bip#getDuration <em>Duration</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Duration</em>'.
   * @see robotG.robot.Bip#getDuration()
   * @see #getBip()
   * @generated
   */
	EAttribute getBip_Duration();

	/**
   * Returns the meta object for the attribute '{@link robotG.robot.Bip#getPower <em>Power</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Power</em>'.
   * @see robotG.robot.Bip#getPower()
   * @see #getBip()
   * @generated
   */
	EAttribute getBip_Power();

	/**
   * Returns the meta object for the attribute '{@link robotG.robot.Bip#isRepeat <em>Repeat</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Repeat</em>'.
   * @see robotG.robot.Bip#isRepeat()
   * @see #getBip()
   * @generated
   */
	EAttribute getBip_Repeat();

	/**
   * Returns the meta object for class '{@link robotG.robot.Turn <em>Turn</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Turn</em>'.
   * @see robotG.robot.Turn
   * @generated
   */
	EClass getTurn();

	/**
   * Returns the meta object for the attribute '{@link robotG.robot.Turn#getPower <em>Power</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Power</em>'.
   * @see robotG.robot.Turn#getPower()
   * @see #getTurn()
   * @generated
   */
	EAttribute getTurn_Power();

	/**
   * Returns the meta object for the attribute '{@link robotG.robot.Turn#getAngle <em>Angle</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Angle</em>'.
   * @see robotG.robot.Turn#getAngle()
   * @see #getTurn()
   * @generated
   */
	EAttribute getTurn_Angle();

	/**
   * Returns the meta object for class '{@link robotG.robot.SetTurnAngle <em>Set Turn Angle</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Set Turn Angle</em>'.
   * @see robotG.robot.SetTurnAngle
   * @generated
   */
	EClass getSetTurnAngle();

	/**
   * Returns the meta object for the attribute '{@link robotG.robot.SetTurnAngle#getAngle <em>Angle</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Angle</em>'.
   * @see robotG.robot.SetTurnAngle#getAngle()
   * @see #getSetTurnAngle()
   * @generated
   */
	EAttribute getSetTurnAngle_Angle();

	/**
   * Returns the meta object for class '{@link robotG.robot.HasTurned <em>Has Turned</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Has Turned</em>'.
   * @see robotG.robot.HasTurned
   * @generated
   */
	EClass getHasTurned();

	/**
   * Returns the meta object for the attribute '{@link robotG.robot.HasTurned#getAngle <em>Angle</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Angle</em>'.
   * @see robotG.robot.HasTurned#getAngle()
   * @see #getHasTurned()
   * @generated
   */
	EAttribute getHasTurned_Angle();

	/**
   * Returns the meta object for class '{@link robotG.robot.Display <em>Display</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Display</em>'.
   * @see robotG.robot.Display
   * @generated
   */
	EClass getDisplay();

	/**
   * Returns the meta object for the attribute '{@link robotG.robot.Display#getMsg <em>Msg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Msg</em>'.
   * @see robotG.robot.Display#getMsg()
   * @see #getDisplay()
   * @generated
   */
	EAttribute getDisplay_Msg();

	/**
   * Returns the meta object for the attribute '{@link robotG.robot.Display#getDuration <em>Duration</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Duration</em>'.
   * @see robotG.robot.Display#getDuration()
   * @see #getDisplay()
   * @generated
   */
	EAttribute getDisplay_Duration();

	/**
   * Returns the meta object for the attribute '{@link robotG.robot.Display#getLine <em>Line</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Line</em>'.
   * @see robotG.robot.Display#getLine()
   * @see #getDisplay()
   * @generated
   */
	EAttribute getDisplay_Line();

	/**
   * Returns the meta object for the attribute '{@link robotG.robot.Display#getCol <em>Col</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Col</em>'.
   * @see robotG.robot.Display#getCol()
   * @see #getDisplay()
   * @generated
   */
	EAttribute getDisplay_Col();

	/**
   * Returns the meta object for class '{@link robotG.robot.Obstacle <em>Obstacle</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Obstacle</em>'.
   * @see robotG.robot.Obstacle
   * @generated
   */
	EClass getObstacle();

	/**
   * Returns the meta object for the attribute '{@link robotG.robot.Obstacle#getDistance <em>Distance</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Distance</em>'.
   * @see robotG.robot.Obstacle#getDistance()
   * @see #getObstacle()
   * @generated
   */
	EAttribute getObstacle_Distance();

	/**
   * Returns the meta object for class '{@link robotG.robot.StopEngine <em>Stop Engine</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Stop Engine</em>'.
   * @see robotG.robot.StopEngine
   * @generated
   */
	EClass getStopEngine();

	/**
   * Returns the meta object for class '{@link robotG.robot.CommandeRobot <em>Commande Robot</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Commande Robot</em>'.
   * @see robotG.robot.CommandeRobot
   * @generated
   */
	EClass getCommandeRobot();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	RobotFactory getRobotFactory();

	/**
   * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
   * @generated
   */
	interface Literals {
		/**
     * The meta object literal for the '{@link robotG.robot.impl.MoveImpl <em>Move</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see robotG.robot.impl.MoveImpl
     * @see robotG.robot.impl.RobotPackageImpl#getMove()
     * @generated
     */
		EClass MOVE = eINSTANCE.getMove();

		/**
     * The meta object literal for the '<em><b>Power</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute MOVE__POWER = eINSTANCE.getMove_Power();

		/**
     * The meta object literal for the '{@link robotG.robot.impl.BipImpl <em>Bip</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see robotG.robot.impl.BipImpl
     * @see robotG.robot.impl.RobotPackageImpl#getBip()
     * @generated
     */
		EClass BIP = eINSTANCE.getBip();

		/**
     * The meta object literal for the '<em><b>Duration</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute BIP__DURATION = eINSTANCE.getBip_Duration();

		/**
     * The meta object literal for the '<em><b>Power</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute BIP__POWER = eINSTANCE.getBip_Power();

		/**
     * The meta object literal for the '<em><b>Repeat</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute BIP__REPEAT = eINSTANCE.getBip_Repeat();

		/**
     * The meta object literal for the '{@link robotG.robot.impl.TurnImpl <em>Turn</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see robotG.robot.impl.TurnImpl
     * @see robotG.robot.impl.RobotPackageImpl#getTurn()
     * @generated
     */
		EClass TURN = eINSTANCE.getTurn();

		/**
     * The meta object literal for the '<em><b>Power</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute TURN__POWER = eINSTANCE.getTurn_Power();

		/**
     * The meta object literal for the '<em><b>Angle</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute TURN__ANGLE = eINSTANCE.getTurn_Angle();

		/**
     * The meta object literal for the '{@link robotG.robot.impl.SetTurnAngleImpl <em>Set Turn Angle</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see robotG.robot.impl.SetTurnAngleImpl
     * @see robotG.robot.impl.RobotPackageImpl#getSetTurnAngle()
     * @generated
     */
		EClass SET_TURN_ANGLE = eINSTANCE.getSetTurnAngle();

		/**
     * The meta object literal for the '<em><b>Angle</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute SET_TURN_ANGLE__ANGLE = eINSTANCE.getSetTurnAngle_Angle();

		/**
     * The meta object literal for the '{@link robotG.robot.impl.HasTurnedImpl <em>Has Turned</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see robotG.robot.impl.HasTurnedImpl
     * @see robotG.robot.impl.RobotPackageImpl#getHasTurned()
     * @generated
     */
		EClass HAS_TURNED = eINSTANCE.getHasTurned();

		/**
     * The meta object literal for the '<em><b>Angle</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute HAS_TURNED__ANGLE = eINSTANCE.getHasTurned_Angle();

		/**
     * The meta object literal for the '{@link robotG.robot.impl.DisplayImpl <em>Display</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see robotG.robot.impl.DisplayImpl
     * @see robotG.robot.impl.RobotPackageImpl#getDisplay()
     * @generated
     */
		EClass DISPLAY = eINSTANCE.getDisplay();

		/**
     * The meta object literal for the '<em><b>Msg</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute DISPLAY__MSG = eINSTANCE.getDisplay_Msg();

		/**
     * The meta object literal for the '<em><b>Duration</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute DISPLAY__DURATION = eINSTANCE.getDisplay_Duration();

		/**
     * The meta object literal for the '<em><b>Line</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute DISPLAY__LINE = eINSTANCE.getDisplay_Line();

		/**
     * The meta object literal for the '<em><b>Col</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute DISPLAY__COL = eINSTANCE.getDisplay_Col();

		/**
     * The meta object literal for the '{@link robotG.robot.impl.ObstacleImpl <em>Obstacle</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see robotG.robot.impl.ObstacleImpl
     * @see robotG.robot.impl.RobotPackageImpl#getObstacle()
     * @generated
     */
		EClass OBSTACLE = eINSTANCE.getObstacle();

		/**
     * The meta object literal for the '<em><b>Distance</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute OBSTACLE__DISTANCE = eINSTANCE.getObstacle_Distance();

		/**
     * The meta object literal for the '{@link robotG.robot.impl.StopEngineImpl <em>Stop Engine</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see robotG.robot.impl.StopEngineImpl
     * @see robotG.robot.impl.RobotPackageImpl#getStopEngine()
     * @generated
     */
		EClass STOP_ENGINE = eINSTANCE.getStopEngine();

		/**
     * The meta object literal for the '{@link robotG.robot.impl.CommandeRobotImpl <em>Commande Robot</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see robotG.robot.impl.CommandeRobotImpl
     * @see robotG.robot.impl.RobotPackageImpl#getCommandeRobot()
     * @generated
     */
		EClass COMMANDE_ROBOT = eINSTANCE.getCommandeRobot();

	}

} //RobotPackage
