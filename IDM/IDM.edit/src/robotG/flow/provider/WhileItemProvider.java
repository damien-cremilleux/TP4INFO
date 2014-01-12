/**
 */
package robotG.flow.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import robotG.flow.FlowFactory;
import robotG.flow.FlowPackage;
import robotG.flow.While;

import robotG.robot.RobotFactory;

/**
 * This is the item provider adapter for a {@link robotG.flow.While} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class WhileItemProvider
	extends ExprItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WhileItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(FlowPackage.Literals.WHILE__CONDITION);
			childrenFeatures.add(FlowPackage.Literals.WHILE__INSTRUCTIONS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns While.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/While"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_While_type");
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(While.class)) {
			case FlowPackage.WHILE__CONDITION:
			case FlowPackage.WHILE__INSTRUCTIONS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__CONDITION,
				 FlowFactory.eINSTANCE.createNot()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__CONDITION,
				 FlowFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__CONDITION,
				 FlowFactory.eINSTANCE.createOr()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__CONDITION,
				 RobotFactory.eINSTANCE.createHasTurned()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__CONDITION,
				 RobotFactory.eINSTANCE.createObstacle()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__INSTRUCTIONS,
				 FlowFactory.eINSTANCE.createWhile()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__INSTRUCTIONS,
				 FlowFactory.eINSTANCE.createIf()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__INSTRUCTIONS,
				 FlowFactory.eINSTANCE.createNot()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__INSTRUCTIONS,
				 FlowFactory.eINSTANCE.createStopProgram()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__INSTRUCTIONS,
				 FlowFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__INSTRUCTIONS,
				 FlowFactory.eINSTANCE.createOr()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__INSTRUCTIONS,
				 RobotFactory.eINSTANCE.createMove()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__INSTRUCTIONS,
				 RobotFactory.eINSTANCE.createBip()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__INSTRUCTIONS,
				 RobotFactory.eINSTANCE.createTurn()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__INSTRUCTIONS,
				 RobotFactory.eINSTANCE.createSetTurnAngle()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__INSTRUCTIONS,
				 RobotFactory.eINSTANCE.createHasTurned()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__INSTRUCTIONS,
				 RobotFactory.eINSTANCE.createDisplay()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__INSTRUCTIONS,
				 RobotFactory.eINSTANCE.createObstacle()));

		newChildDescriptors.add
			(createChildParameter
				(FlowPackage.Literals.WHILE__INSTRUCTIONS,
				 RobotFactory.eINSTANCE.createStopEngine()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == FlowPackage.Literals.WHILE__CONDITION ||
			childFeature == FlowPackage.Literals.WHILE__INSTRUCTIONS;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
