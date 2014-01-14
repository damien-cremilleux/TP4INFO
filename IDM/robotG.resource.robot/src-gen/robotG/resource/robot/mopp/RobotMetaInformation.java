/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.mopp;

public class RobotMetaInformation implements robotG.resource.robot.IRobotMetaInformation {
	
	public String getSyntaxName() {
		return "robot";
	}
	
	public String getURI() {
		return "http://robotG/1.0";
	}
	
	public robotG.resource.robot.IRobotTextScanner createLexer() {
		return new robotG.resource.robot.mopp.RobotAntlrScanner(new robotG.resource.robot.mopp.RobotLexer());
	}
	
	public robotG.resource.robot.IRobotTextParser createParser(java.io.InputStream inputStream, String encoding) {
		return new robotG.resource.robot.mopp.RobotParser().createInstance(inputStream, encoding);
	}
	
	public robotG.resource.robot.IRobotTextPrinter createPrinter(java.io.OutputStream outputStream, robotG.resource.robot.IRobotTextResource resource) {
		return new robotG.resource.robot.mopp.RobotPrinter2(outputStream, resource);
	}
	
	public org.eclipse.emf.ecore.EClass[] getClassesWithSyntax() {
		return new robotG.resource.robot.mopp.RobotSyntaxCoverageInformationProvider().getClassesWithSyntax();
	}
	
	public org.eclipse.emf.ecore.EClass[] getStartSymbols() {
		return new robotG.resource.robot.mopp.RobotSyntaxCoverageInformationProvider().getStartSymbols();
	}
	
	public robotG.resource.robot.IRobotReferenceResolverSwitch getReferenceResolverSwitch() {
		return new robotG.resource.robot.mopp.RobotReferenceResolverSwitch();
	}
	
	public robotG.resource.robot.IRobotTokenResolverFactory getTokenResolverFactory() {
		return new robotG.resource.robot.mopp.RobotTokenResolverFactory();
	}
	
	public String getPathToCSDefinition() {
		return "IDM/metamodels/Robot.cs";
	}
	
	public String[] getTokenNames() {
		return robotG.resource.robot.mopp.RobotParser.tokenNames;
	}
	
	public robotG.resource.robot.IRobotTokenStyle getDefaultTokenStyle(String tokenName) {
		return new robotG.resource.robot.mopp.RobotTokenStyleInformationProvider().getDefaultTokenStyle(tokenName);
	}
	
	public java.util.Collection<robotG.resource.robot.IRobotBracketPair> getBracketPairs() {
		return new robotG.resource.robot.mopp.RobotBracketInformationProvider().getBracketPairs();
	}
	
	public org.eclipse.emf.ecore.EClass[] getFoldableClasses() {
		return new robotG.resource.robot.mopp.RobotFoldingInformationProvider().getFoldableClasses();
	}
	
	public org.eclipse.emf.ecore.resource.Resource.Factory createResourceFactory() {
		return new robotG.resource.robot.mopp.RobotResourceFactory();
	}
	
	public robotG.resource.robot.mopp.RobotNewFileContentProvider getNewFileContentProvider() {
		return new robotG.resource.robot.mopp.RobotNewFileContentProvider();
	}
	
	public void registerResourceFactory() {
		org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(getSyntaxName(), new robotG.resource.robot.mopp.RobotResourceFactory());
	}
	
	/**
	 * Returns the key of the option that can be used to register a preprocessor that
	 * is used as a pipe when loading resources. This key is language-specific. To
	 * register one preprocessor for multiple resource types, it must be registered
	 * individually using all keys.
	 */
	public String getInputStreamPreprocessorProviderOptionKey() {
		return getSyntaxName() + "_" + "INPUT_STREAM_PREPROCESSOR_PROVIDER";
	}
	
	/**
	 * Returns the key of the option that can be used to register a post-processors
	 * that are invoked after loading resources. This key is language-specific. To
	 * register one post-processor for multiple resource types, it must be registered
	 * individually using all keys.
	 */
	public String getResourcePostProcessorProviderOptionKey() {
		return getSyntaxName() + "_" + "RESOURCE_POSTPROCESSOR_PROVIDER";
	}
	
	public String getLaunchConfigurationType() {
		return "robotG.resource.robot.ui.launchConfigurationType";
	}
	
	public robotG.resource.robot.IRobotNameProvider createNameProvider() {
		return new robotG.resource.robot.analysis.RobotDefaultNameProvider();
	}
	
	public String[] getSyntaxHighlightableTokenNames() {
		robotG.resource.robot.mopp.RobotAntlrTokenHelper tokenHelper = new robotG.resource.robot.mopp.RobotAntlrTokenHelper();
		java.util.List<String> highlightableTokens = new java.util.ArrayList<String>();
		String[] parserTokenNames = getTokenNames();
		for (int i = 0; i < parserTokenNames.length; i++) {
			// If ANTLR is used we need to normalize the token names
			if (!tokenHelper.canBeUsedForSyntaxHighlighting(i)) {
				continue;
			}
			String tokenName = tokenHelper.getTokenName(parserTokenNames, i);
			if (tokenName == null) {
				continue;
			}
			highlightableTokens.add(tokenName);
		}
		highlightableTokens.add(robotG.resource.robot.mopp.RobotTokenStyleInformationProvider.TASK_ITEM_TOKEN_NAME);
		return highlightableTokens.toArray(new String[highlightableTokens.size()]);
	}
	
}
