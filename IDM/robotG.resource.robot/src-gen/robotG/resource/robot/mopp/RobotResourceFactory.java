/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.mopp;

public class RobotResourceFactory implements org.eclipse.emf.ecore.resource.Resource.Factory {
	
	public RobotResourceFactory() {
		super();
	}
	
	public org.eclipse.emf.ecore.resource.Resource createResource(org.eclipse.emf.common.util.URI uri) {
		return new robotG.resource.robot.mopp.RobotResource(uri);
	}
	
}
