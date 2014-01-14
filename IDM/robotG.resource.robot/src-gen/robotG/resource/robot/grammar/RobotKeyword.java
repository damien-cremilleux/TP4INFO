/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.grammar;

/**
 * A class to represent a keyword in the grammar.
 */
public class RobotKeyword extends robotG.resource.robot.grammar.RobotSyntaxElement {
	
	private final String value;
	
	public RobotKeyword(String value, robotG.resource.robot.grammar.RobotCardinality cardinality) {
		super(cardinality, null);
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return value;
	}
	
}
