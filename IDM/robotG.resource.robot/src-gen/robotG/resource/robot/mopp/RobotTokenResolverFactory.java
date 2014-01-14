/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.mopp;

/**
 * The RobotTokenResolverFactory class provides access to all generated token
 * resolvers. By giving the name of a defined token, the corresponding resolve can
 * be obtained. Despite the fact that this class is called TokenResolverFactory is
 * does NOT create new token resolvers whenever a client calls methods to obtain a
 * resolver. Rather, this class maintains a map of all resolvers and creates each
 * resolver at most once.
 */
public class RobotTokenResolverFactory implements robotG.resource.robot.IRobotTokenResolverFactory {
	
	private java.util.Map<String, robotG.resource.robot.IRobotTokenResolver> tokenName2TokenResolver;
	private java.util.Map<String, robotG.resource.robot.IRobotTokenResolver> featureName2CollectInTokenResolver;
	private static robotG.resource.robot.IRobotTokenResolver defaultResolver = new robotG.resource.robot.analysis.RobotDefaultTokenResolver();
	
	public RobotTokenResolverFactory() {
		tokenName2TokenResolver = new java.util.LinkedHashMap<String, robotG.resource.robot.IRobotTokenResolver>();
		featureName2CollectInTokenResolver = new java.util.LinkedHashMap<String, robotG.resource.robot.IRobotTokenResolver>();
		registerTokenResolver("STRING_LITERAL", new robotG.resource.robot.analysis.RobotSTRING_LITERALTokenResolver());
		registerTokenResolver("TEXT", new robotG.resource.robot.analysis.RobotTEXTTokenResolver());
	}
	
	public robotG.resource.robot.IRobotTokenResolver createTokenResolver(String tokenName) {
		return internalCreateResolver(tokenName2TokenResolver, tokenName);
	}
	
	public robotG.resource.robot.IRobotTokenResolver createCollectInTokenResolver(String featureName) {
		return internalCreateResolver(featureName2CollectInTokenResolver, featureName);
	}
	
	protected boolean registerTokenResolver(String tokenName, robotG.resource.robot.IRobotTokenResolver resolver){
		return internalRegisterTokenResolver(tokenName2TokenResolver, tokenName, resolver);
	}
	
	protected boolean registerCollectInTokenResolver(String featureName, robotG.resource.robot.IRobotTokenResolver resolver){
		return internalRegisterTokenResolver(featureName2CollectInTokenResolver, featureName, resolver);
	}
	
	protected robotG.resource.robot.IRobotTokenResolver deRegisterTokenResolver(String tokenName){
		return tokenName2TokenResolver.remove(tokenName);
	}
	
	private robotG.resource.robot.IRobotTokenResolver internalCreateResolver(java.util.Map<String, robotG.resource.robot.IRobotTokenResolver> resolverMap, String key) {
		if (resolverMap.containsKey(key)){
			return resolverMap.get(key);
		} else {
			return defaultResolver;
		}
	}
	
	private boolean internalRegisterTokenResolver(java.util.Map<String, robotG.resource.robot.IRobotTokenResolver> resolverMap, String key, robotG.resource.robot.IRobotTokenResolver resolver) {
		if (!resolverMap.containsKey(key)) {
			resolverMap.put(key,resolver);
			return true;
		}
		return false;
	}
	
}
