/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.ui;

public class RobotOutlinePageLexicalSortingAction extends robotG.resource.robot.ui.AbstractRobotOutlinePageAction {
	
	public RobotOutlinePageLexicalSortingAction(robotG.resource.robot.ui.RobotOutlinePageTreeViewer treeViewer) {
		super(treeViewer, "Sort alphabetically", org.eclipse.jface.action.IAction.AS_CHECK_BOX);
		initialize("icons/sort_lexically_icon.gif");
	}
	
	public void runInternal(boolean on) {
		getTreeViewerComparator().setSortLexically(on);
		getTreeViewer().refresh();
	}
	
}
