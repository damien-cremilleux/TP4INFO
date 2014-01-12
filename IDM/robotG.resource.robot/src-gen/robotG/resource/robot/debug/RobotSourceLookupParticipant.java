/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.debug;

public class RobotSourceLookupParticipant extends org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant {
	
	public String getSourceName(Object object) throws org.eclipse.core.runtime.CoreException {
		if (object instanceof robotG.resource.robot.debug.RobotStackFrame) {
			robotG.resource.robot.debug.RobotStackFrame frame = (robotG.resource.robot.debug.RobotStackFrame) object;
			return frame.getResourceURI();
		}
		return null;
	}
	
}
