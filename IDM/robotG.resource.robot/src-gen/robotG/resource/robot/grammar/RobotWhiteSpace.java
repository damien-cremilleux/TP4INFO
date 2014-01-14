/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.grammar;

public class RobotWhiteSpace extends robotG.resource.robot.grammar.RobotFormattingElement {
	
	private final int amount;
	
	public RobotWhiteSpace(int amount, robotG.resource.robot.grammar.RobotCardinality cardinality) {
		super(cardinality);
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String toString() {
		return "#" + getAmount();
	}
	
}
