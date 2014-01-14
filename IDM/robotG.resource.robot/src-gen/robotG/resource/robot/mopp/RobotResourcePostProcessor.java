/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.mopp;

public class RobotResourcePostProcessor implements robotG.resource.robot.IRobotResourcePostProcessor {
	
	public void process(robotG.resource.robot.mopp.RobotResource resource) {
		// Set the overrideResourcePostProcessor option to false to customize resource
		// post processing.
	}
	
	public void terminate() {
		// To signal termination to the process() method, setting a boolean field is
		// recommended. Depending on the value of this field process() can stop its
		// computation. However, this is only required for computation intensive
		// post-processors.
	}
	
}
