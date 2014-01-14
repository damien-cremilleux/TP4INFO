/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.mopp;

/**
 * Abstract super class for all expected elements. Provides methods to add
 * followers.
 */
public abstract class RobotAbstractExpectedElement implements robotG.resource.robot.IRobotExpectedElement {
	
	private org.eclipse.emf.ecore.EClass ruleMetaclass;
	
	private java.util.Set<robotG.resource.robot.util.RobotPair<robotG.resource.robot.IRobotExpectedElement, robotG.resource.robot.mopp.RobotContainedFeature[]>> followers = new java.util.LinkedHashSet<robotG.resource.robot.util.RobotPair<robotG.resource.robot.IRobotExpectedElement, robotG.resource.robot.mopp.RobotContainedFeature[]>>();
	
	public RobotAbstractExpectedElement(org.eclipse.emf.ecore.EClass ruleMetaclass) {
		super();
		this.ruleMetaclass = ruleMetaclass;
	}
	
	public org.eclipse.emf.ecore.EClass getRuleMetaclass() {
		return ruleMetaclass;
	}
	
	public void addFollower(robotG.resource.robot.IRobotExpectedElement follower, robotG.resource.robot.mopp.RobotContainedFeature[] path) {
		followers.add(new robotG.resource.robot.util.RobotPair<robotG.resource.robot.IRobotExpectedElement, robotG.resource.robot.mopp.RobotContainedFeature[]>(follower, path));
	}
	
	public java.util.Collection<robotG.resource.robot.util.RobotPair<robotG.resource.robot.IRobotExpectedElement, robotG.resource.robot.mopp.RobotContainedFeature[]>> getFollowers() {
		return followers;
	}
	
}
