/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot;

/**
 * An interface used to access the result of parsing a document.
 */
public interface IRobotParseResult {
	
	/**
	 * Returns the root object of the document.
	 */
	public org.eclipse.emf.ecore.EObject getRoot();
	
	/**
	 * Returns a list of commands that must be executed after parsing the document.
	 */
	public java.util.Collection<robotG.resource.robot.IRobotCommand<robotG.resource.robot.IRobotTextResource>> getPostParseCommands();
	
}
