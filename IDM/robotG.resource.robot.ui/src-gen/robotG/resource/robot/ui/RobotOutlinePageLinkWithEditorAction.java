/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.ui;

public class RobotOutlinePageLinkWithEditorAction extends robotG.resource.robot.ui.AbstractRobotOutlinePageAction {
	
	public RobotOutlinePageLinkWithEditorAction(robotG.resource.robot.ui.RobotOutlinePageTreeViewer treeViewer) {
		super(treeViewer, "Link with Editor", org.eclipse.jface.action.IAction.AS_CHECK_BOX);
		initialize("icons/link_with_editor_icon.gif");
	}
	
	public void runInternal(boolean on) {
		getTreeViewer().setLinkWithEditor(on);
	}
	
}
