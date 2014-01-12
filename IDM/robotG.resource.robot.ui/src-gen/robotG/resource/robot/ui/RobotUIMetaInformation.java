/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.ui;

public class RobotUIMetaInformation extends robotG.resource.robot.mopp.RobotMetaInformation {
	
	public robotG.resource.robot.IRobotHoverTextProvider getHoverTextProvider() {
		return new robotG.resource.robot.ui.RobotHoverTextProvider();
	}
	
	public robotG.resource.robot.ui.RobotImageProvider getImageProvider() {
		return robotG.resource.robot.ui.RobotImageProvider.INSTANCE;
	}
	
	public robotG.resource.robot.ui.RobotColorManager createColorManager() {
		return new robotG.resource.robot.ui.RobotColorManager();
	}
	
	/**
	 * @deprecated this method is only provided to preserve API compatibility. Use
	 * createTokenScanner(robotG.resource.robot.IRobotTextResource,
	 * robotG.resource.robot.ui.RobotColorManager) instead.
	 */
	public robotG.resource.robot.ui.RobotTokenScanner createTokenScanner(robotG.resource.robot.ui.RobotColorManager colorManager) {
		return createTokenScanner(null, colorManager);
	}
	
	public robotG.resource.robot.ui.RobotTokenScanner createTokenScanner(robotG.resource.robot.IRobotTextResource resource, robotG.resource.robot.ui.RobotColorManager colorManager) {
		return new robotG.resource.robot.ui.RobotTokenScanner(resource, colorManager);
	}
	
	public robotG.resource.robot.ui.RobotCodeCompletionHelper createCodeCompletionHelper() {
		return new robotG.resource.robot.ui.RobotCodeCompletionHelper();
	}
	
}
