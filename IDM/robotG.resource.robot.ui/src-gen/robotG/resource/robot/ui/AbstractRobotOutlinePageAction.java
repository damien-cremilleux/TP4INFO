/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.ui;

public abstract class AbstractRobotOutlinePageAction extends org.eclipse.jface.action.Action {
	
	private String preferenceKey = this.getClass().getSimpleName() + ".isChecked";
	
	private robotG.resource.robot.ui.RobotOutlinePageTreeViewer treeViewer;
	
	public AbstractRobotOutlinePageAction(robotG.resource.robot.ui.RobotOutlinePageTreeViewer treeViewer, String text, int style) {
		super(text, style);
		this.treeViewer = treeViewer;
	}
	
	public void initialize(String imagePath) {
		org.eclipse.jface.resource.ImageDescriptor descriptor = robotG.resource.robot.ui.RobotImageProvider.INSTANCE.getImageDescriptor(imagePath);
		setDisabledImageDescriptor(descriptor);
		setImageDescriptor(descriptor);
		setHoverImageDescriptor(descriptor);
		boolean checked = robotG.resource.robot.ui.RobotUIPlugin.getDefault().getPreferenceStore().getBoolean(preferenceKey);
		valueChanged(checked, false);
	}
	
	@Override	
	public void run() {
		if (keepState()) {
			valueChanged(isChecked(), true);
		} else {
			runBusy(true);
		}
	}
	
	public void runBusy(final boolean on) {
		org.eclipse.swt.custom.BusyIndicator.showWhile(org.eclipse.swt.widgets.Display.getCurrent(), new Runnable() {
			public void run() {
				runInternal(on);
			}
		});
	}
	
	public abstract void runInternal(boolean on);
	
	private void valueChanged(boolean on, boolean store) {
		setChecked(on);
		runBusy(on);
		if (store) {
			robotG.resource.robot.ui.RobotUIPlugin.getDefault().getPreferenceStore().setValue(preferenceKey, on);
		}
	}
	
	public boolean keepState() {
		return true;
	}
	
	public robotG.resource.robot.ui.RobotOutlinePageTreeViewer getTreeViewer() {
		return treeViewer;
	}
	
	public robotG.resource.robot.ui.RobotOutlinePageTreeViewerComparator getTreeViewerComparator() {
		return (robotG.resource.robot.ui.RobotOutlinePageTreeViewerComparator) treeViewer.getComparator();
	}
	
}
