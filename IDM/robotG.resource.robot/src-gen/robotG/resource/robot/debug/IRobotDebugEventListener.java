/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.debug;

public interface IRobotDebugEventListener {
	
	/**
	 * Notification that the given event occurred in the while debugging.
	 */
	public void handleMessage(robotG.resource.robot.debug.RobotDebugMessage message);
}
