/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.debug;

public class RobotSourceLocator extends org.eclipse.debug.core.sourcelookup.AbstractSourceLookupDirector {
	
	public void initializeParticipants() {
		addParticipants(new org.eclipse.debug.core.sourcelookup.ISourceLookupParticipant[]{new robotG.resource.robot.debug.RobotSourceLookupParticipant()});
	}
	
}
