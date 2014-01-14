/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.util;

/**
 * Class RobotTextResourceUtil can be used to perform common tasks on text
 * resources, such as loading and saving resources, as well as, checking them for
 * errors. This class is deprecated and has been replaced by
 * robotG.resource.robot.util.RobotResourceUtil.
 */
public class RobotTextResourceUtil {
	
	/**
	 * Use robotG.resource.robot.util.RobotResourceUtil.getResource() instead.
	 */
	@Deprecated	
	public static robotG.resource.robot.mopp.RobotResource getResource(org.eclipse.core.resources.IFile file) {
		return new robotG.resource.robot.util.RobotEclipseProxy().getResource(file);
	}
	
	/**
	 * Use robotG.resource.robot.util.RobotResourceUtil.getResource() instead.
	 */
	@Deprecated	
	public static robotG.resource.robot.mopp.RobotResource getResource(java.io.File file, java.util.Map<?,?> options) {
		return robotG.resource.robot.util.RobotResourceUtil.getResource(file, options);
	}
	
	/**
	 * Use robotG.resource.robot.util.RobotResourceUtil.getResource() instead.
	 */
	@Deprecated	
	public static robotG.resource.robot.mopp.RobotResource getResource(org.eclipse.emf.common.util.URI uri) {
		return robotG.resource.robot.util.RobotResourceUtil.getResource(uri);
	}
	
	/**
	 * Use robotG.resource.robot.util.RobotResourceUtil.getResource() instead.
	 */
	@Deprecated	
	public static robotG.resource.robot.mopp.RobotResource getResource(org.eclipse.emf.common.util.URI uri, java.util.Map<?,?> options) {
		return robotG.resource.robot.util.RobotResourceUtil.getResource(uri, options);
	}
	
}
