/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.ui;

/**
 * A class which can be overridden to customize code completion proposals.
 */
public class RobotProposalPostProcessor {
	
	public java.util.List<robotG.resource.robot.ui.RobotCompletionProposal> process(java.util.List<robotG.resource.robot.ui.RobotCompletionProposal> proposals) {
		// the default implementation does returns the proposals as they are
		return proposals;
	}
	
}
