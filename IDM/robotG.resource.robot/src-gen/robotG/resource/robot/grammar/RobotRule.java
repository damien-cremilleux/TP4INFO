/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.grammar;

/**
 * A class to represent a rules in the grammar.
 */
public class RobotRule extends robotG.resource.robot.grammar.RobotSyntaxElement {
	
	private final org.eclipse.emf.ecore.EClass metaclass;
	
	public RobotRule(org.eclipse.emf.ecore.EClass metaclass, robotG.resource.robot.grammar.RobotChoice choice, robotG.resource.robot.grammar.RobotCardinality cardinality) {
		super(cardinality, new robotG.resource.robot.grammar.RobotSyntaxElement[] {choice});
		this.metaclass = metaclass;
	}
	
	public org.eclipse.emf.ecore.EClass getMetaclass() {
		return metaclass;
	}
	
	public robotG.resource.robot.grammar.RobotChoice getDefinition() {
		return (robotG.resource.robot.grammar.RobotChoice) getChildren()[0];
	}
	
}

