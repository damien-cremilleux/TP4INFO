/**
 */
package robotG.flow;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Programme</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link robotG.flow.Programme#getProgramme <em>Programme</em>}</li>
 * </ul>
 * </p>
 *
 * @see robotG.flow.FlowPackage#getProgramme()
 * @model
 * @generated
 */
public interface Programme extends EObject {
	/**
   * Returns the value of the '<em><b>Programme</b></em>' containment reference list.
   * The list contents are of type {@link robotG.flow.Expr}.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Programme</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Programme</em>' containment reference list.
   * @see robotG.flow.FlowPackage#getProgramme_Programme()
   * @model containment="true"
   * @generated
   */
	EList<Expr> getProgramme();

} // Programme
