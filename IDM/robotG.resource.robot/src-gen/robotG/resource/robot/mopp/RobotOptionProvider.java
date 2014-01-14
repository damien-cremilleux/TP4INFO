/**
 * <copyright>
 * </copyright>
 *
 * 
 */
/**
 * This class can be used to configure options that must be used when resources
 * are loaded. This is similar to using the extension point
 * 'robotG.resource.robot.default_load_options' with the difference that the
 * options defined in this class are used even if no Eclipse platform is running.
 */
package robotG.resource.robot.mopp;

public class RobotOptionProvider implements robotG.resource.robot.IRobotOptionProvider {
	
	public java.util.Map<?,?> getOptions() {
		// create a map with static option providers here
		return java.util.Collections.emptyMap();
	}
	
}
