/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.ui;

public class RobotOutlinePageActionProvider {
	
	public java.util.List<org.eclipse.jface.action.IAction> getActions(robotG.resource.robot.ui.RobotOutlinePageTreeViewer treeViewer) {
		// To add custom actions to the outline view, set the
		// 'overrideOutlinePageActionProvider' option to <code>false</code> and modify
		// this method.
		java.util.List<org.eclipse.jface.action.IAction> defaultActions = new java.util.ArrayList<org.eclipse.jface.action.IAction>();
		defaultActions.add(new robotG.resource.robot.ui.RobotOutlinePageLinkWithEditorAction(treeViewer));
		defaultActions.add(new robotG.resource.robot.ui.RobotOutlinePageCollapseAllAction(treeViewer));
		defaultActions.add(new robotG.resource.robot.ui.RobotOutlinePageExpandAllAction(treeViewer));
		defaultActions.add(new robotG.resource.robot.ui.RobotOutlinePageAutoExpandAction(treeViewer));
		defaultActions.add(new robotG.resource.robot.ui.RobotOutlinePageLexicalSortingAction(treeViewer));
		defaultActions.add(new robotG.resource.robot.ui.RobotOutlinePageTypeSortingAction(treeViewer));
		return defaultActions;
	}
	
}
