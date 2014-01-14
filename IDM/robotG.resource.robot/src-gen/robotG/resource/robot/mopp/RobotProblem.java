/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.mopp;

public class RobotProblem implements robotG.resource.robot.IRobotProblem {
	
	private String message;
	private robotG.resource.robot.RobotEProblemType type;
	private robotG.resource.robot.RobotEProblemSeverity severity;
	private java.util.Collection<robotG.resource.robot.IRobotQuickFix> quickFixes;
	
	public RobotProblem(String message, robotG.resource.robot.RobotEProblemType type, robotG.resource.robot.RobotEProblemSeverity severity) {
		this(message, type, severity, java.util.Collections.<robotG.resource.robot.IRobotQuickFix>emptySet());
	}
	
	public RobotProblem(String message, robotG.resource.robot.RobotEProblemType type, robotG.resource.robot.RobotEProblemSeverity severity, robotG.resource.robot.IRobotQuickFix quickFix) {
		this(message, type, severity, java.util.Collections.singleton(quickFix));
	}
	
	public RobotProblem(String message, robotG.resource.robot.RobotEProblemType type, robotG.resource.robot.RobotEProblemSeverity severity, java.util.Collection<robotG.resource.robot.IRobotQuickFix> quickFixes) {
		super();
		this.message = message;
		this.type = type;
		this.severity = severity;
		this.quickFixes = new java.util.LinkedHashSet<robotG.resource.robot.IRobotQuickFix>();
		this.quickFixes.addAll(quickFixes);
	}
	
	public robotG.resource.robot.RobotEProblemType getType() {
		return type;
	}
	
	public robotG.resource.robot.RobotEProblemSeverity getSeverity() {
		return severity;
	}
	
	public String getMessage() {
		return message;
	}
	
	public java.util.Collection<robotG.resource.robot.IRobotQuickFix> getQuickFixes() {
		return quickFixes;
	}
	
}
