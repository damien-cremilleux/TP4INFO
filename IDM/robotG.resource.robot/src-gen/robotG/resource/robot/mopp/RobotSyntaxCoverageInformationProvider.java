/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.mopp;

public class RobotSyntaxCoverageInformationProvider {
	
	public org.eclipse.emf.ecore.EClass[] getClassesWithSyntax() {
		return new org.eclipse.emf.ecore.EClass[] {
			robotG.flow.FlowPackage.eINSTANCE.getProgramme(),
			robotG.flow.FlowPackage.eINSTANCE.getWhile(),
			robotG.flow.FlowPackage.eINSTANCE.getIf(),
			robotG.flow.FlowPackage.eINSTANCE.getAnd(),
			robotG.flow.FlowPackage.eINSTANCE.getOr(),
			robotG.flow.FlowPackage.eINSTANCE.getNot(),
			robotG.flow.FlowPackage.eINSTANCE.getStopProgram(),
			robotG.robot.RobotPackage.eINSTANCE.getBip(),
			robotG.robot.RobotPackage.eINSTANCE.getDisplay(),
			robotG.robot.RobotPackage.eINSTANCE.getHasTurned(),
			robotG.robot.RobotPackage.eINSTANCE.getMove(),
			robotG.robot.RobotPackage.eINSTANCE.getObstacle(),
			robotG.robot.RobotPackage.eINSTANCE.getSetTurnAngle(),
			robotG.robot.RobotPackage.eINSTANCE.getStopEngine(),
			robotG.robot.RobotPackage.eINSTANCE.getTurn(),
		};
	}
	
	public org.eclipse.emf.ecore.EClass[] getStartSymbols() {
		return new org.eclipse.emf.ecore.EClass[] {
			robotG.flow.FlowPackage.eINSTANCE.getProgramme(),
		};
	}
	
}
