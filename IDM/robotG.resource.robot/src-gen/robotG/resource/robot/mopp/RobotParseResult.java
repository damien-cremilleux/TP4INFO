/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.mopp;

public class RobotParseResult implements robotG.resource.robot.IRobotParseResult {
	
	private org.eclipse.emf.ecore.EObject root;
	private java.util.Collection<robotG.resource.robot.IRobotCommand<robotG.resource.robot.IRobotTextResource>> commands = new java.util.ArrayList<robotG.resource.robot.IRobotCommand<robotG.resource.robot.IRobotTextResource>>();
	
	public RobotParseResult() {
		super();
	}
	
	public void setRoot(org.eclipse.emf.ecore.EObject root) {
		this.root = root;
	}
	
	public org.eclipse.emf.ecore.EObject getRoot() {
		return root;
	}
	
	public java.util.Collection<robotG.resource.robot.IRobotCommand<robotG.resource.robot.IRobotTextResource>> getPostParseCommands() {
		return commands;
	}
	
}
