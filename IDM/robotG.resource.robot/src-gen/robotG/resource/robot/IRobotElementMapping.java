/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot;

/**
 * A mapping from an identifier to an EObject.
 * 
 * @param <ReferenceType> the type of the reference this mapping points to.
 */
public interface IRobotElementMapping<ReferenceType> extends robotG.resource.robot.IRobotReferenceMapping<ReferenceType> {
	
	/**
	 * Returns the target object the identifier is mapped to.
	 */
	public ReferenceType getTargetElement();
}
