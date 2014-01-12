/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot;

/**
 * Implementors of this interface can provide a post-processor for text resources.
 */
public interface IRobotResourcePostProcessorProvider {
	
	/**
	 * Returns the processor that shall be called after text resource are successfully
	 * parsed.
	 */
	public robotG.resource.robot.IRobotResourcePostProcessor getResourcePostProcessor();
	
}
