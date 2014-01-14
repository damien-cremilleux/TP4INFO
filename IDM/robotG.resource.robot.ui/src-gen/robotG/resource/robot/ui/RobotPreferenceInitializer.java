/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.ui;

/**
 * A class used to initialize default preference values.
 */
public class RobotPreferenceInitializer extends org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer {
	
	public void initializeDefaultPreferences() {
		
		initializeDefaultSyntaxHighlighting();
		initializeDefaultBrackets();
		
		org.eclipse.jface.preference.IPreferenceStore store = robotG.resource.robot.ui.RobotUIPlugin.getDefault().getPreferenceStore();
		// Set default value for matching brackets
		store.setDefault(robotG.resource.robot.ui.RobotPreferenceConstants.EDITOR_MATCHING_BRACKETS_COLOR, "192,192,192");
		store.setDefault(robotG.resource.robot.ui.RobotPreferenceConstants.EDITOR_MATCHING_BRACKETS_CHECKBOX, true);
		
	}
	
	private void initializeDefaultBrackets() {
		org.eclipse.jface.preference.IPreferenceStore store = robotG.resource.robot.ui.RobotUIPlugin.getDefault().getPreferenceStore();
		initializeDefaultBrackets(store, new robotG.resource.robot.mopp.RobotMetaInformation());
	}
	
	public void initializeDefaultSyntaxHighlighting() {
		org.eclipse.jface.preference.IPreferenceStore store = robotG.resource.robot.ui.RobotUIPlugin.getDefault().getPreferenceStore();
		initializeDefaultSyntaxHighlighting(store, new robotG.resource.robot.mopp.RobotMetaInformation());
	}
	
	private void initializeDefaultBrackets(org.eclipse.jface.preference.IPreferenceStore store, robotG.resource.robot.IRobotMetaInformation metaInformation) {
		String languageId = metaInformation.getSyntaxName();
		// set default brackets for ITextResource bracket set
		robotG.resource.robot.ui.RobotBracketSet bracketSet = new robotG.resource.robot.ui.RobotBracketSet(null, null);
		final java.util.Collection<robotG.resource.robot.IRobotBracketPair> bracketPairs = metaInformation.getBracketPairs();
		if (bracketPairs != null) {
			for (robotG.resource.robot.IRobotBracketPair bracketPair : bracketPairs) {
				bracketSet.addBracketPair(bracketPair.getOpeningBracket(), bracketPair.getClosingBracket(), bracketPair.isClosingEnabledInside());
			}
		}
		store.setDefault(languageId + robotG.resource.robot.ui.RobotPreferenceConstants.EDITOR_BRACKETS_SUFFIX, bracketSet.getBracketString());
	}
	
	private void initializeDefaultSyntaxHighlighting(org.eclipse.jface.preference.IPreferenceStore store, robotG.resource.robot.mopp.RobotMetaInformation metaInformation) {
		String languageId = metaInformation.getSyntaxName();
		String[] tokenNames = metaInformation.getSyntaxHighlightableTokenNames();
		if (tokenNames == null) {
			return;
		}
		for (int i = 0; i < tokenNames.length; i++) {
			String tokenName = tokenNames[i];
			robotG.resource.robot.IRobotTokenStyle style = metaInformation.getDefaultTokenStyle(tokenName);
			if (style != null) {
				String color = getColorString(style.getColorAsRGB());
				setProperties(store, languageId, tokenName, color, style.isBold(), true, style.isItalic(), style.isStrikethrough(), style.isUnderline());
			} else {
				setProperties(store, languageId, tokenName, "0,0,0", false, false, false, false, false);
			}
		}
	}
	
	private void setProperties(org.eclipse.jface.preference.IPreferenceStore store, String languageID, String tokenName, String color, boolean bold, boolean enable, boolean italic, boolean strikethrough, boolean underline) {
		store.setDefault(robotG.resource.robot.ui.RobotSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, robotG.resource.robot.ui.RobotSyntaxColoringHelper.StyleProperty.BOLD), bold);
		store.setDefault(robotG.resource.robot.ui.RobotSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, robotG.resource.robot.ui.RobotSyntaxColoringHelper.StyleProperty.COLOR), color);
		store.setDefault(robotG.resource.robot.ui.RobotSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, robotG.resource.robot.ui.RobotSyntaxColoringHelper.StyleProperty.ENABLE), enable);
		store.setDefault(robotG.resource.robot.ui.RobotSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, robotG.resource.robot.ui.RobotSyntaxColoringHelper.StyleProperty.ITALIC), italic);
		store.setDefault(robotG.resource.robot.ui.RobotSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, robotG.resource.robot.ui.RobotSyntaxColoringHelper.StyleProperty.STRIKETHROUGH), strikethrough);
		store.setDefault(robotG.resource.robot.ui.RobotSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, robotG.resource.robot.ui.RobotSyntaxColoringHelper.StyleProperty.UNDERLINE), underline);
	}
	
	private String getColorString(int[] colorAsRGB) {
		if (colorAsRGB == null) {
			return "0,0,0";
		}
		if (colorAsRGB.length != 3) {
			return "0,0,0";
		}
		return colorAsRGB[0] + "," +colorAsRGB[1] + ","+ colorAsRGB[2];
	}
}
