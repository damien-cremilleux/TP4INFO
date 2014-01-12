/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.mopp;

public class RobotReferenceResolverSwitch implements robotG.resource.robot.IRobotReferenceResolverSwitch {
	
	/**
	 * This map stores a copy of the options the were set for loading the resource.
	 */
	private java.util.Map<Object, Object> options;
	
	
	public void setOptions(java.util.Map<?, ?> options) {
		if (options != null) {
			this.options = new java.util.LinkedHashMap<Object, Object>();
			this.options.putAll(options);
		}
	}
	
	public void resolveFuzzy(String identifier, org.eclipse.emf.ecore.EObject container, org.eclipse.emf.ecore.EReference reference, int position, robotG.resource.robot.IRobotReferenceResolveResult<org.eclipse.emf.ecore.EObject> result) {
		if (container == null) {
			return;
		}
	}
	
	public robotG.resource.robot.IRobotReferenceResolver<? extends org.eclipse.emf.ecore.EObject, ? extends org.eclipse.emf.ecore.EObject> getResolver(org.eclipse.emf.ecore.EStructuralFeature reference) {
		return null;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})	
	public <ContainerType extends org.eclipse.emf.ecore.EObject, ReferenceType extends org.eclipse.emf.ecore.EObject> robotG.resource.robot.IRobotReferenceResolver<ContainerType, ReferenceType> getResolverChain(org.eclipse.emf.ecore.EStructuralFeature reference, robotG.resource.robot.IRobotReferenceResolver<ContainerType, ReferenceType> originalResolver) {
		if (options == null) {
			return originalResolver;
		}
		Object value = options.get(robotG.resource.robot.IRobotOptions.ADDITIONAL_REFERENCE_RESOLVERS);
		if (value == null) {
			return originalResolver;
		}
		if (!(value instanceof java.util.Map)) {
			// send this to the error log
			new robotG.resource.robot.util.RobotRuntimeUtil().logWarning("Found value with invalid type for option " + robotG.resource.robot.IRobotOptions.ADDITIONAL_REFERENCE_RESOLVERS + " (expected " + java.util.Map.class.getName() + ", but was " + value.getClass().getName() + ")", null);
			return originalResolver;
		}
		java.util.Map<?,?> resolverMap = (java.util.Map<?,?>) value;
		Object resolverValue = resolverMap.get(reference);
		if (resolverValue == null) {
			return originalResolver;
		}
		if (resolverValue instanceof robotG.resource.robot.IRobotReferenceResolver) {
			robotG.resource.robot.IRobotReferenceResolver replacingResolver = (robotG.resource.robot.IRobotReferenceResolver) resolverValue;
			if (replacingResolver instanceof robotG.resource.robot.IRobotDelegatingReferenceResolver) {
				// pass original resolver to the replacing one
				((robotG.resource.robot.IRobotDelegatingReferenceResolver) replacingResolver).setDelegate(originalResolver);
			}
			return replacingResolver;
		} else if (resolverValue instanceof java.util.Collection) {
			java.util.Collection replacingResolvers = (java.util.Collection) resolverValue;
			robotG.resource.robot.IRobotReferenceResolver replacingResolver = originalResolver;
			for (Object next : replacingResolvers) {
				if (next instanceof robotG.resource.robot.IRobotReferenceCache) {
					robotG.resource.robot.IRobotReferenceResolver nextResolver = (robotG.resource.robot.IRobotReferenceResolver) next;
					if (nextResolver instanceof robotG.resource.robot.IRobotDelegatingReferenceResolver) {
						// pass original resolver to the replacing one
						((robotG.resource.robot.IRobotDelegatingReferenceResolver) nextResolver).setDelegate(replacingResolver);
					}
					replacingResolver = nextResolver;
				} else {
					// The collection contains a non-resolver. Send a warning to the error log.
					new robotG.resource.robot.util.RobotRuntimeUtil().logWarning("Found value with invalid type in value map for option " + robotG.resource.robot.IRobotOptions.ADDITIONAL_REFERENCE_RESOLVERS + " (expected " + robotG.resource.robot.IRobotDelegatingReferenceResolver.class.getName() + ", but was " + next.getClass().getName() + ")", null);
				}
			}
			return replacingResolver;
		} else {
			// The value for the option ADDITIONAL_REFERENCE_RESOLVERS has an unknown type.
			new robotG.resource.robot.util.RobotRuntimeUtil().logWarning("Found value with invalid type in value map for option " + robotG.resource.robot.IRobotOptions.ADDITIONAL_REFERENCE_RESOLVERS + " (expected " + robotG.resource.robot.IRobotDelegatingReferenceResolver.class.getName() + ", but was " + resolverValue.getClass().getName() + ")", null);
			return originalResolver;
		}
	}
	
}
