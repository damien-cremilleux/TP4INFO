/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot;

public interface IRobotProblem {
	public String getMessage();
	public robotG.resource.robot.RobotEProblemSeverity getSeverity();
	public robotG.resource.robot.RobotEProblemType getType();
	public java.util.Collection<robotG.resource.robot.IRobotQuickFix> getQuickFixes();
}
