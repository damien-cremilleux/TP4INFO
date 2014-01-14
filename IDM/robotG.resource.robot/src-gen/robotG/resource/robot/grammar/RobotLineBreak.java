/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.grammar;

public class RobotLineBreak extends robotG.resource.robot.grammar.RobotFormattingElement {
	
	private final int tabs;
	
	public RobotLineBreak(robotG.resource.robot.grammar.RobotCardinality cardinality, int tabs) {
		super(cardinality);
		this.tabs = tabs;
	}
	
	public int getTabs() {
		return tabs;
	}
	
	public String toString() {
		return "!" + getTabs();
	}
	
}
