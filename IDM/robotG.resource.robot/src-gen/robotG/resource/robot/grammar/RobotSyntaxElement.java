/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.grammar;

/**
 * The abstract super class for all elements of a grammar. This class provides
 * methods to traverse the grammar rules.
 */
public abstract class RobotSyntaxElement {
	
	private RobotSyntaxElement[] children;
	private RobotSyntaxElement parent;
	private robotG.resource.robot.grammar.RobotCardinality cardinality;
	
	public RobotSyntaxElement(robotG.resource.robot.grammar.RobotCardinality cardinality, RobotSyntaxElement[] children) {
		this.cardinality = cardinality;
		this.children = children;
		if (this.children != null) {
			for (RobotSyntaxElement child : this.children) {
				child.setParent(this);
			}
		}
	}
	
	/**
	 * Sets the parent of this syntax element. This method must be invoked at most
	 * once.
	 */
	public void setParent(RobotSyntaxElement parent) {
		assert this.parent == null;
		this.parent = parent;
	}
	
	/**
	 * Returns the parent of this syntax element. This parent is determined by the
	 * containment hierarchy in the CS model.
	 */
	public RobotSyntaxElement getParent() {
		return parent;
	}
	
	public RobotSyntaxElement[] getChildren() {
		if (children == null) {
			return new RobotSyntaxElement[0];
		}
		return children;
	}
	
	public org.eclipse.emf.ecore.EClass getMetaclass() {
		return parent.getMetaclass();
	}
	
	public robotG.resource.robot.grammar.RobotCardinality getCardinality() {
		return cardinality;
	}
	
}
