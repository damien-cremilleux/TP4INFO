/**
 */
package robotG.flow;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Op Binaire</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link robotG.flow.OpBinaire#getFilsGauche <em>Fils Gauche</em>}</li>
 *   <li>{@link robotG.flow.OpBinaire#getFilsDroit <em>Fils Droit</em>}</li>
 * </ul>
 * </p>
 *
 * @see robotG.flow.FlowPackage#getOpBinaire()
 * @model abstract="true"
 * @generated
 */
public interface OpBinaire extends ExprBool {
	/**
   * Returns the value of the '<em><b>Fils Gauche</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fils Gauche</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Fils Gauche</em>' containment reference.
   * @see #setFilsGauche(ExprBool)
   * @see robotG.flow.FlowPackage#getOpBinaire_FilsGauche()
   * @model containment="true" required="true"
   * @generated
   */
	ExprBool getFilsGauche();

	/**
   * Sets the value of the '{@link robotG.flow.OpBinaire#getFilsGauche <em>Fils Gauche</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fils Gauche</em>' containment reference.
   * @see #getFilsGauche()
   * @generated
   */
	void setFilsGauche(ExprBool value);

	/**
   * Returns the value of the '<em><b>Fils Droit</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fils Droit</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Fils Droit</em>' containment reference.
   * @see #setFilsDroit(ExprBool)
   * @see robotG.flow.FlowPackage#getOpBinaire_FilsDroit()
   * @model containment="true" required="true"
   * @generated
   */
	ExprBool getFilsDroit();

	/**
   * Sets the value of the '{@link robotG.flow.OpBinaire#getFilsDroit <em>Fils Droit</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fils Droit</em>' containment reference.
   * @see #getFilsDroit()
   * @generated
   */
	void setFilsDroit(ExprBool value);

} // OpBinaire
