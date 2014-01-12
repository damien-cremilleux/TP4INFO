/**
 */
package robotG.flow;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see robotG.flow.FlowPackage
 * @generated
 */
public interface FlowFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	FlowFactory eINSTANCE = robotG.flow.impl.FlowFactoryImpl.init();

	/**
   * Returns a new object of class '<em>While</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>While</em>'.
   * @generated
   */
	While createWhile();

	/**
   * Returns a new object of class '<em>If</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>If</em>'.
   * @generated
   */
	If createIf();

	/**
   * Returns a new object of class '<em>Not</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Not</em>'.
   * @generated
   */
	Not createNot();

	/**
   * Returns a new object of class '<em>Stop Program</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Stop Program</em>'.
   * @generated
   */
	StopProgram createStopProgram();

	/**
   * Returns a new object of class '<em>And</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>And</em>'.
   * @generated
   */
	And createAnd();

	/**
   * Returns a new object of class '<em>Or</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Or</em>'.
   * @generated
   */
	Or createOr();

	/**
   * Returns a new object of class '<em>Programme</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Programme</em>'.
   * @generated
   */
	Programme createProgramme();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	FlowPackage getFlowPackage();

} //FlowFactory
