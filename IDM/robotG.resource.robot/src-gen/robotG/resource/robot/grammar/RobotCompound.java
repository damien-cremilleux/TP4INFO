/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.grammar;

public class RobotCompound extends robotG.resource.robot.grammar.RobotSyntaxElement {
	
	public RobotCompound(robotG.resource.robot.grammar.RobotChoice choice, robotG.resource.robot.grammar.RobotCardinality cardinality) {
		super(cardinality, new robotG.resource.robot.grammar.RobotSyntaxElement[] {choice});
	}
	
	public String toString() {
		return "(" + getChildren()[0] + ")";
	}
	
}
