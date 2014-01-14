/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot;

/**
 * Implementors of this interface provide an EMF resource.
 */
public interface IRobotResourceProvider {
	
	/**
	 * Returns the resource.
	 */
	public robotG.resource.robot.IRobotTextResource getResource();
	
}
