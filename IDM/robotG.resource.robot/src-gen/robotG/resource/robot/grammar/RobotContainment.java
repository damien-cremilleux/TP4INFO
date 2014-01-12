/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.grammar;

public class RobotContainment extends robotG.resource.robot.grammar.RobotTerminal {
	
	private final org.eclipse.emf.ecore.EClass[] allowedTypes;
	
	public RobotContainment(org.eclipse.emf.ecore.EStructuralFeature feature, robotG.resource.robot.grammar.RobotCardinality cardinality, org.eclipse.emf.ecore.EClass[] allowedTypes, int mandatoryOccurencesAfter) {
		super(feature, cardinality, mandatoryOccurencesAfter);
		this.allowedTypes = allowedTypes;
	}
	
	public org.eclipse.emf.ecore.EClass[] getAllowedTypes() {
		return allowedTypes;
	}
	
	public String toString() {
		String typeRestrictions = null;
		if (allowedTypes != null && allowedTypes.length > 0) {
			typeRestrictions = robotG.resource.robot.util.RobotStringUtil.explode(allowedTypes, ", ", new robotG.resource.robot.IRobotFunction1<String, org.eclipse.emf.ecore.EClass>() {
				public String execute(org.eclipse.emf.ecore.EClass eClass) {
					return eClass.getName();
				}
			});
		}
		return getFeature().getName() + (typeRestrictions == null ? "" : "[" + typeRestrictions + "]");
	}
	
}
