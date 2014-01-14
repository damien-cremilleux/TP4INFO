/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.grammar;

public class RobotSequence extends robotG.resource.robot.grammar.RobotSyntaxElement {
	
	public RobotSequence(robotG.resource.robot.grammar.RobotCardinality cardinality, robotG.resource.robot.grammar.RobotSyntaxElement... elements) {
		super(cardinality, elements);
	}
	
	public String toString() {
		return robotG.resource.robot.util.RobotStringUtil.explode(getChildren(), " ");
	}
	
}
