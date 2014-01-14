/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.grammar;

public class RobotChoice extends robotG.resource.robot.grammar.RobotSyntaxElement {
	
	public RobotChoice(robotG.resource.robot.grammar.RobotCardinality cardinality, robotG.resource.robot.grammar.RobotSyntaxElement... choices) {
		super(cardinality, choices);
	}
	
	public String toString() {
		return robotG.resource.robot.util.RobotStringUtil.explode(getChildren(), "|");
	}
	
}
