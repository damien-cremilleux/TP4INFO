/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.grammar;

public class RobotGrammarInformationProvider {
	
	public final static org.eclipse.emf.ecore.EStructuralFeature ANONYMOUS_FEATURE = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEAttribute();
	static {
		ANONYMOUS_FEATURE.setName("_");
	}
	
	public final static RobotGrammarInformationProvider INSTANCE = new RobotGrammarInformationProvider();
	
	private java.util.Set<String> keywords;
	
	public final static robotG.resource.robot.grammar.RobotContainment ROBOT_0_0_0_0 = new robotG.resource.robot.grammar.RobotContainment(robotG.flow.FlowPackage.eINSTANCE.getProgramme().getEStructuralFeature(robotG.flow.FlowPackage.PROGRAMME__PROGRAMME), robotG.resource.robot.grammar.RobotCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {robotG.flow.FlowPackage.eINSTANCE.getExpr(), }, 0);
	public final static robotG.resource.robot.grammar.RobotSequence ROBOT_0_0_0 = new robotG.resource.robot.grammar.RobotSequence(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_0_0_0_0);
	public final static robotG.resource.robot.grammar.RobotChoice ROBOT_0_0 = new robotG.resource.robot.grammar.RobotChoice(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_0_0_0);
	public final static robotG.resource.robot.grammar.RobotRule ROBOT_0 = new robotG.resource.robot.grammar.RobotRule(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), ROBOT_0_0, robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_1_0_0_0 = new robotG.resource.robot.grammar.RobotKeyword("while", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotContainment ROBOT_1_0_0_1 = new robotG.resource.robot.grammar.RobotContainment(robotG.flow.FlowPackage.eINSTANCE.getWhile().getEStructuralFeature(robotG.flow.FlowPackage.WHILE__CONDITION), robotG.resource.robot.grammar.RobotCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {robotG.flow.FlowPackage.eINSTANCE.getExprBool(), }, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_1_0_0_2 = new robotG.resource.robot.grammar.RobotKeyword("do", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotContainment ROBOT_1_0_0_3 = new robotG.resource.robot.grammar.RobotContainment(robotG.flow.FlowPackage.eINSTANCE.getWhile().getEStructuralFeature(robotG.flow.FlowPackage.WHILE__INSTRUCTIONS), robotG.resource.robot.grammar.RobotCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {robotG.flow.FlowPackage.eINSTANCE.getExpr(), }, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_1_0_0_4 = new robotG.resource.robot.grammar.RobotKeyword("end", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotSequence ROBOT_1_0_0 = new robotG.resource.robot.grammar.RobotSequence(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_1_0_0_0, ROBOT_1_0_0_1, ROBOT_1_0_0_2, ROBOT_1_0_0_3, ROBOT_1_0_0_4);
	public final static robotG.resource.robot.grammar.RobotChoice ROBOT_1_0 = new robotG.resource.robot.grammar.RobotChoice(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_1_0_0);
	public final static robotG.resource.robot.grammar.RobotRule ROBOT_1 = new robotG.resource.robot.grammar.RobotRule(robotG.flow.FlowPackage.eINSTANCE.getWhile(), ROBOT_1_0, robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_2_0_0_0 = new robotG.resource.robot.grammar.RobotKeyword("if", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotContainment ROBOT_2_0_0_1 = new robotG.resource.robot.grammar.RobotContainment(robotG.flow.FlowPackage.eINSTANCE.getIf().getEStructuralFeature(robotG.flow.FlowPackage.IF__CONDITION), robotG.resource.robot.grammar.RobotCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {robotG.flow.FlowPackage.eINSTANCE.getExprBool(), }, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_2_0_0_2 = new robotG.resource.robot.grammar.RobotKeyword("then", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotContainment ROBOT_2_0_0_3 = new robotG.resource.robot.grammar.RobotContainment(robotG.flow.FlowPackage.eINSTANCE.getIf().getEStructuralFeature(robotG.flow.FlowPackage.IF__INSTRUCTIONS), robotG.resource.robot.grammar.RobotCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {robotG.flow.FlowPackage.eINSTANCE.getExpr(), }, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_2_0_0_4 = new robotG.resource.robot.grammar.RobotKeyword("end", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotSequence ROBOT_2_0_0 = new robotG.resource.robot.grammar.RobotSequence(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_2_0_0_0, ROBOT_2_0_0_1, ROBOT_2_0_0_2, ROBOT_2_0_0_3, ROBOT_2_0_0_4);
	public final static robotG.resource.robot.grammar.RobotChoice ROBOT_2_0 = new robotG.resource.robot.grammar.RobotChoice(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_2_0_0);
	public final static robotG.resource.robot.grammar.RobotRule ROBOT_2 = new robotG.resource.robot.grammar.RobotRule(robotG.flow.FlowPackage.eINSTANCE.getIf(), ROBOT_2_0, robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotContainment ROBOT_3_0_0_0 = new robotG.resource.robot.grammar.RobotContainment(robotG.flow.FlowPackage.eINSTANCE.getAnd().getEStructuralFeature(robotG.flow.FlowPackage.AND__FILS_GAUCHE), robotG.resource.robot.grammar.RobotCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {robotG.flow.FlowPackage.eINSTANCE.getExprBool(), }, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_3_0_0_1 = new robotG.resource.robot.grammar.RobotKeyword("and", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotContainment ROBOT_3_0_0_2 = new robotG.resource.robot.grammar.RobotContainment(robotG.flow.FlowPackage.eINSTANCE.getAnd().getEStructuralFeature(robotG.flow.FlowPackage.AND__FILS_DROIT), robotG.resource.robot.grammar.RobotCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {robotG.flow.FlowPackage.eINSTANCE.getExprBool(), }, 0);
	public final static robotG.resource.robot.grammar.RobotSequence ROBOT_3_0_0 = new robotG.resource.robot.grammar.RobotSequence(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_3_0_0_0, ROBOT_3_0_0_1, ROBOT_3_0_0_2);
	public final static robotG.resource.robot.grammar.RobotChoice ROBOT_3_0 = new robotG.resource.robot.grammar.RobotChoice(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_3_0_0);
	public final static robotG.resource.robot.grammar.RobotRule ROBOT_3 = new robotG.resource.robot.grammar.RobotRule(robotG.flow.FlowPackage.eINSTANCE.getAnd(), ROBOT_3_0, robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotContainment ROBOT_4_0_0_0 = new robotG.resource.robot.grammar.RobotContainment(robotG.flow.FlowPackage.eINSTANCE.getOr().getEStructuralFeature(robotG.flow.FlowPackage.OR__FILS_GAUCHE), robotG.resource.robot.grammar.RobotCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {robotG.flow.FlowPackage.eINSTANCE.getExprBool(), }, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_4_0_0_1 = new robotG.resource.robot.grammar.RobotKeyword("or", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotContainment ROBOT_4_0_0_2 = new robotG.resource.robot.grammar.RobotContainment(robotG.flow.FlowPackage.eINSTANCE.getOr().getEStructuralFeature(robotG.flow.FlowPackage.OR__FILS_DROIT), robotG.resource.robot.grammar.RobotCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {robotG.flow.FlowPackage.eINSTANCE.getExprBool(), }, 0);
	public final static robotG.resource.robot.grammar.RobotSequence ROBOT_4_0_0 = new robotG.resource.robot.grammar.RobotSequence(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_4_0_0_0, ROBOT_4_0_0_1, ROBOT_4_0_0_2);
	public final static robotG.resource.robot.grammar.RobotChoice ROBOT_4_0 = new robotG.resource.robot.grammar.RobotChoice(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_4_0_0);
	public final static robotG.resource.robot.grammar.RobotRule ROBOT_4 = new robotG.resource.robot.grammar.RobotRule(robotG.flow.FlowPackage.eINSTANCE.getOr(), ROBOT_4_0, robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_5_0_0_0 = new robotG.resource.robot.grammar.RobotKeyword("not", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotContainment ROBOT_5_0_0_1 = new robotG.resource.robot.grammar.RobotContainment(robotG.flow.FlowPackage.eINSTANCE.getNot().getEStructuralFeature(robotG.flow.FlowPackage.NOT__EXPRESSION), robotG.resource.robot.grammar.RobotCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {robotG.flow.FlowPackage.eINSTANCE.getExprBool(), }, 0);
	public final static robotG.resource.robot.grammar.RobotSequence ROBOT_5_0_0 = new robotG.resource.robot.grammar.RobotSequence(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_5_0_0_0, ROBOT_5_0_0_1);
	public final static robotG.resource.robot.grammar.RobotChoice ROBOT_5_0 = new robotG.resource.robot.grammar.RobotChoice(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_5_0_0);
	public final static robotG.resource.robot.grammar.RobotRule ROBOT_5 = new robotG.resource.robot.grammar.RobotRule(robotG.flow.FlowPackage.eINSTANCE.getNot(), ROBOT_5_0, robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_6_0_0_0 = new robotG.resource.robot.grammar.RobotKeyword("stopProgram", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotSequence ROBOT_6_0_0 = new robotG.resource.robot.grammar.RobotSequence(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_6_0_0_0);
	public final static robotG.resource.robot.grammar.RobotChoice ROBOT_6_0 = new robotG.resource.robot.grammar.RobotChoice(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_6_0_0);
	public final static robotG.resource.robot.grammar.RobotRule ROBOT_6 = new robotG.resource.robot.grammar.RobotRule(robotG.flow.FlowPackage.eINSTANCE.getStopProgram(), ROBOT_6_0, robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_7_0_0_0 = new robotG.resource.robot.grammar.RobotKeyword("bip", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_7_0_0_1 = new robotG.resource.robot.grammar.RobotKeyword("(", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_7_0_0_2 = new robotG.resource.robot.grammar.RobotKeyword("duration", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_7_0_0_3 = new robotG.resource.robot.grammar.RobotKeyword("=", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotPlaceholder ROBOT_7_0_0_4 = new robotG.resource.robot.grammar.RobotPlaceholder(robotG.robot.RobotPackage.eINSTANCE.getBip().getEStructuralFeature(robotG.robot.RobotPackage.BIP__DURATION), "TEXT", robotG.resource.robot.grammar.RobotCardinality.ONE, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_7_0_0_5 = new robotG.resource.robot.grammar.RobotKeyword(",", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_7_0_0_6 = new robotG.resource.robot.grammar.RobotKeyword("power", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_7_0_0_7 = new robotG.resource.robot.grammar.RobotKeyword("=", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotPlaceholder ROBOT_7_0_0_8 = new robotG.resource.robot.grammar.RobotPlaceholder(robotG.robot.RobotPackage.eINSTANCE.getBip().getEStructuralFeature(robotG.robot.RobotPackage.BIP__POWER), "TEXT", robotG.resource.robot.grammar.RobotCardinality.ONE, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_7_0_0_9 = new robotG.resource.robot.grammar.RobotKeyword(",", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_7_0_0_10 = new robotG.resource.robot.grammar.RobotKeyword("repeat", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_7_0_0_11 = new robotG.resource.robot.grammar.RobotKeyword("=", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotPlaceholder ROBOT_7_0_0_12 = new robotG.resource.robot.grammar.RobotPlaceholder(robotG.robot.RobotPackage.eINSTANCE.getBip().getEStructuralFeature(robotG.robot.RobotPackage.BIP__REPEAT), "TEXT", robotG.resource.robot.grammar.RobotCardinality.ONE, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_7_0_0_13 = new robotG.resource.robot.grammar.RobotKeyword(")", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotSequence ROBOT_7_0_0 = new robotG.resource.robot.grammar.RobotSequence(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_7_0_0_0, ROBOT_7_0_0_1, ROBOT_7_0_0_2, ROBOT_7_0_0_3, ROBOT_7_0_0_4, ROBOT_7_0_0_5, ROBOT_7_0_0_6, ROBOT_7_0_0_7, ROBOT_7_0_0_8, ROBOT_7_0_0_9, ROBOT_7_0_0_10, ROBOT_7_0_0_11, ROBOT_7_0_0_12, ROBOT_7_0_0_13);
	public final static robotG.resource.robot.grammar.RobotChoice ROBOT_7_0 = new robotG.resource.robot.grammar.RobotChoice(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_7_0_0);
	public final static robotG.resource.robot.grammar.RobotRule ROBOT_7 = new robotG.resource.robot.grammar.RobotRule(robotG.robot.RobotPackage.eINSTANCE.getBip(), ROBOT_7_0, robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_8_0_0_0 = new robotG.resource.robot.grammar.RobotKeyword("display", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_8_0_0_1 = new robotG.resource.robot.grammar.RobotKeyword("(", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_8_0_0_2 = new robotG.resource.robot.grammar.RobotKeyword("msg", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_8_0_0_3 = new robotG.resource.robot.grammar.RobotKeyword("=", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotPlaceholder ROBOT_8_0_0_4 = new robotG.resource.robot.grammar.RobotPlaceholder(robotG.robot.RobotPackage.eINSTANCE.getDisplay().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__MSG), "STRING_LITERAL", robotG.resource.robot.grammar.RobotCardinality.ONE, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_8_0_0_5 = new robotG.resource.robot.grammar.RobotKeyword(",", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_8_0_0_6 = new robotG.resource.robot.grammar.RobotKeyword("duration", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_8_0_0_7 = new robotG.resource.robot.grammar.RobotKeyword("=", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotPlaceholder ROBOT_8_0_0_8 = new robotG.resource.robot.grammar.RobotPlaceholder(robotG.robot.RobotPackage.eINSTANCE.getDisplay().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__DURATION), "TEXT", robotG.resource.robot.grammar.RobotCardinality.ONE, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_8_0_0_9 = new robotG.resource.robot.grammar.RobotKeyword(",", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_8_0_0_10 = new robotG.resource.robot.grammar.RobotKeyword("line", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_8_0_0_11 = new robotG.resource.robot.grammar.RobotKeyword("=", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotPlaceholder ROBOT_8_0_0_12 = new robotG.resource.robot.grammar.RobotPlaceholder(robotG.robot.RobotPackage.eINSTANCE.getDisplay().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__LINE), "TEXT", robotG.resource.robot.grammar.RobotCardinality.ONE, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_8_0_0_13 = new robotG.resource.robot.grammar.RobotKeyword(",", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_8_0_0_14 = new robotG.resource.robot.grammar.RobotKeyword("col", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_8_0_0_15 = new robotG.resource.robot.grammar.RobotKeyword("=", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotPlaceholder ROBOT_8_0_0_16 = new robotG.resource.robot.grammar.RobotPlaceholder(robotG.robot.RobotPackage.eINSTANCE.getDisplay().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__COL), "TEXT", robotG.resource.robot.grammar.RobotCardinality.ONE, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_8_0_0_17 = new robotG.resource.robot.grammar.RobotKeyword(")", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotSequence ROBOT_8_0_0 = new robotG.resource.robot.grammar.RobotSequence(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_8_0_0_0, ROBOT_8_0_0_1, ROBOT_8_0_0_2, ROBOT_8_0_0_3, ROBOT_8_0_0_4, ROBOT_8_0_0_5, ROBOT_8_0_0_6, ROBOT_8_0_0_7, ROBOT_8_0_0_8, ROBOT_8_0_0_9, ROBOT_8_0_0_10, ROBOT_8_0_0_11, ROBOT_8_0_0_12, ROBOT_8_0_0_13, ROBOT_8_0_0_14, ROBOT_8_0_0_15, ROBOT_8_0_0_16, ROBOT_8_0_0_17);
	public final static robotG.resource.robot.grammar.RobotChoice ROBOT_8_0 = new robotG.resource.robot.grammar.RobotChoice(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_8_0_0);
	public final static robotG.resource.robot.grammar.RobotRule ROBOT_8 = new robotG.resource.robot.grammar.RobotRule(robotG.robot.RobotPackage.eINSTANCE.getDisplay(), ROBOT_8_0, robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_9_0_0_0 = new robotG.resource.robot.grammar.RobotKeyword("hasTurned", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_9_0_0_1 = new robotG.resource.robot.grammar.RobotKeyword("(", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_9_0_0_2 = new robotG.resource.robot.grammar.RobotKeyword("angle", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_9_0_0_3 = new robotG.resource.robot.grammar.RobotKeyword("=", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotPlaceholder ROBOT_9_0_0_4 = new robotG.resource.robot.grammar.RobotPlaceholder(robotG.robot.RobotPackage.eINSTANCE.getHasTurned().getEStructuralFeature(robotG.robot.RobotPackage.HAS_TURNED__ANGLE), "TEXT", robotG.resource.robot.grammar.RobotCardinality.ONE, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_9_0_0_5 = new robotG.resource.robot.grammar.RobotKeyword(")", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotSequence ROBOT_9_0_0 = new robotG.resource.robot.grammar.RobotSequence(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_9_0_0_0, ROBOT_9_0_0_1, ROBOT_9_0_0_2, ROBOT_9_0_0_3, ROBOT_9_0_0_4, ROBOT_9_0_0_5);
	public final static robotG.resource.robot.grammar.RobotChoice ROBOT_9_0 = new robotG.resource.robot.grammar.RobotChoice(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_9_0_0);
	public final static robotG.resource.robot.grammar.RobotRule ROBOT_9 = new robotG.resource.robot.grammar.RobotRule(robotG.robot.RobotPackage.eINSTANCE.getHasTurned(), ROBOT_9_0, robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_10_0_0_0 = new robotG.resource.robot.grammar.RobotKeyword("move", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_10_0_0_1 = new robotG.resource.robot.grammar.RobotKeyword("(", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_10_0_0_2 = new robotG.resource.robot.grammar.RobotKeyword("power", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_10_0_0_3 = new robotG.resource.robot.grammar.RobotKeyword("=", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotPlaceholder ROBOT_10_0_0_4 = new robotG.resource.robot.grammar.RobotPlaceholder(robotG.robot.RobotPackage.eINSTANCE.getMove().getEStructuralFeature(robotG.robot.RobotPackage.MOVE__POWER), "TEXT", robotG.resource.robot.grammar.RobotCardinality.ONE, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_10_0_0_5 = new robotG.resource.robot.grammar.RobotKeyword(")", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotSequence ROBOT_10_0_0 = new robotG.resource.robot.grammar.RobotSequence(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_10_0_0_0, ROBOT_10_0_0_1, ROBOT_10_0_0_2, ROBOT_10_0_0_3, ROBOT_10_0_0_4, ROBOT_10_0_0_5);
	public final static robotG.resource.robot.grammar.RobotChoice ROBOT_10_0 = new robotG.resource.robot.grammar.RobotChoice(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_10_0_0);
	public final static robotG.resource.robot.grammar.RobotRule ROBOT_10 = new robotG.resource.robot.grammar.RobotRule(robotG.robot.RobotPackage.eINSTANCE.getMove(), ROBOT_10_0, robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_11_0_0_0 = new robotG.resource.robot.grammar.RobotKeyword("obstacle", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_11_0_0_1 = new robotG.resource.robot.grammar.RobotKeyword("(", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_11_0_0_2 = new robotG.resource.robot.grammar.RobotKeyword("distance", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_11_0_0_3 = new robotG.resource.robot.grammar.RobotKeyword("=", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotPlaceholder ROBOT_11_0_0_4 = new robotG.resource.robot.grammar.RobotPlaceholder(robotG.robot.RobotPackage.eINSTANCE.getObstacle().getEStructuralFeature(robotG.robot.RobotPackage.OBSTACLE__DISTANCE), "TEXT", robotG.resource.robot.grammar.RobotCardinality.ONE, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_11_0_0_5 = new robotG.resource.robot.grammar.RobotKeyword(")", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotSequence ROBOT_11_0_0 = new robotG.resource.robot.grammar.RobotSequence(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_11_0_0_0, ROBOT_11_0_0_1, ROBOT_11_0_0_2, ROBOT_11_0_0_3, ROBOT_11_0_0_4, ROBOT_11_0_0_5);
	public final static robotG.resource.robot.grammar.RobotChoice ROBOT_11_0 = new robotG.resource.robot.grammar.RobotChoice(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_11_0_0);
	public final static robotG.resource.robot.grammar.RobotRule ROBOT_11 = new robotG.resource.robot.grammar.RobotRule(robotG.robot.RobotPackage.eINSTANCE.getObstacle(), ROBOT_11_0, robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_12_0_0_0 = new robotG.resource.robot.grammar.RobotKeyword("setTurnAngle", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_12_0_0_1 = new robotG.resource.robot.grammar.RobotKeyword("(", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_12_0_0_2 = new robotG.resource.robot.grammar.RobotKeyword("angle", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_12_0_0_3 = new robotG.resource.robot.grammar.RobotKeyword("=", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotPlaceholder ROBOT_12_0_0_4 = new robotG.resource.robot.grammar.RobotPlaceholder(robotG.robot.RobotPackage.eINSTANCE.getSetTurnAngle().getEStructuralFeature(robotG.robot.RobotPackage.SET_TURN_ANGLE__ANGLE), "TEXT", robotG.resource.robot.grammar.RobotCardinality.ONE, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_12_0_0_5 = new robotG.resource.robot.grammar.RobotKeyword(")", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotSequence ROBOT_12_0_0 = new robotG.resource.robot.grammar.RobotSequence(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_12_0_0_0, ROBOT_12_0_0_1, ROBOT_12_0_0_2, ROBOT_12_0_0_3, ROBOT_12_0_0_4, ROBOT_12_0_0_5);
	public final static robotG.resource.robot.grammar.RobotChoice ROBOT_12_0 = new robotG.resource.robot.grammar.RobotChoice(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_12_0_0);
	public final static robotG.resource.robot.grammar.RobotRule ROBOT_12 = new robotG.resource.robot.grammar.RobotRule(robotG.robot.RobotPackage.eINSTANCE.getSetTurnAngle(), ROBOT_12_0, robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_13_0_0_0 = new robotG.resource.robot.grammar.RobotKeyword("stopEngine", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotSequence ROBOT_13_0_0 = new robotG.resource.robot.grammar.RobotSequence(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_13_0_0_0);
	public final static robotG.resource.robot.grammar.RobotChoice ROBOT_13_0 = new robotG.resource.robot.grammar.RobotChoice(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_13_0_0);
	public final static robotG.resource.robot.grammar.RobotRule ROBOT_13 = new robotG.resource.robot.grammar.RobotRule(robotG.robot.RobotPackage.eINSTANCE.getStopEngine(), ROBOT_13_0, robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_14_0_0_0 = new robotG.resource.robot.grammar.RobotKeyword("turn", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_14_0_0_1 = new robotG.resource.robot.grammar.RobotKeyword("(", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_14_0_0_2 = new robotG.resource.robot.grammar.RobotKeyword("power", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_14_0_0_3 = new robotG.resource.robot.grammar.RobotKeyword("=", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotPlaceholder ROBOT_14_0_0_4 = new robotG.resource.robot.grammar.RobotPlaceholder(robotG.robot.RobotPackage.eINSTANCE.getTurn().getEStructuralFeature(robotG.robot.RobotPackage.TURN__POWER), "TEXT", robotG.resource.robot.grammar.RobotCardinality.ONE, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_14_0_0_5 = new robotG.resource.robot.grammar.RobotKeyword(",", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_14_0_0_6 = new robotG.resource.robot.grammar.RobotKeyword("angle", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_14_0_0_7 = new robotG.resource.robot.grammar.RobotKeyword("=", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotPlaceholder ROBOT_14_0_0_8 = new robotG.resource.robot.grammar.RobotPlaceholder(robotG.robot.RobotPackage.eINSTANCE.getTurn().getEStructuralFeature(robotG.robot.RobotPackage.TURN__ANGLE), "TEXT", robotG.resource.robot.grammar.RobotCardinality.ONE, 0);
	public final static robotG.resource.robot.grammar.RobotKeyword ROBOT_14_0_0_9 = new robotG.resource.robot.grammar.RobotKeyword(")", robotG.resource.robot.grammar.RobotCardinality.ONE);
	public final static robotG.resource.robot.grammar.RobotSequence ROBOT_14_0_0 = new robotG.resource.robot.grammar.RobotSequence(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_14_0_0_0, ROBOT_14_0_0_1, ROBOT_14_0_0_2, ROBOT_14_0_0_3, ROBOT_14_0_0_4, ROBOT_14_0_0_5, ROBOT_14_0_0_6, ROBOT_14_0_0_7, ROBOT_14_0_0_8, ROBOT_14_0_0_9);
	public final static robotG.resource.robot.grammar.RobotChoice ROBOT_14_0 = new robotG.resource.robot.grammar.RobotChoice(robotG.resource.robot.grammar.RobotCardinality.ONE, ROBOT_14_0_0);
	public final static robotG.resource.robot.grammar.RobotRule ROBOT_14 = new robotG.resource.robot.grammar.RobotRule(robotG.robot.RobotPackage.eINSTANCE.getTurn(), ROBOT_14_0, robotG.resource.robot.grammar.RobotCardinality.ONE);
	
	public static String getSyntaxElementID(robotG.resource.robot.grammar.RobotSyntaxElement syntaxElement) {
		if (syntaxElement == null) {
			// null indicates EOF
			return "<EOF>";
		}
		for (java.lang.reflect.Field field : robotG.resource.robot.grammar.RobotGrammarInformationProvider.class.getFields()) {
			Object fieldValue;
			try {
				fieldValue = field.get(null);
				if (fieldValue == syntaxElement) {
					String id = field.getName();
					return id;
				}
			} catch (Exception e) { }
		}
		return null;
	}
	
	public static robotG.resource.robot.grammar.RobotSyntaxElement getSyntaxElementByID(String syntaxElementID) {
		try {
			return (robotG.resource.robot.grammar.RobotSyntaxElement) robotG.resource.robot.grammar.RobotGrammarInformationProvider.class.getField(syntaxElementID).get(null);
		} catch (Exception e) {
			return null;
		}
	}
	
	public final static robotG.resource.robot.grammar.RobotRule[] RULES = new robotG.resource.robot.grammar.RobotRule[] {
		ROBOT_0,
		ROBOT_1,
		ROBOT_2,
		ROBOT_3,
		ROBOT_4,
		ROBOT_5,
		ROBOT_6,
		ROBOT_7,
		ROBOT_8,
		ROBOT_9,
		ROBOT_10,
		ROBOT_11,
		ROBOT_12,
		ROBOT_13,
		ROBOT_14,
	};
	
	/**
	 * Returns all keywords of the grammar. This includes all literals for boolean and
	 * enumeration terminals.
	 */
	public java.util.Set<String> getKeywords() {
		if (this.keywords == null) {
			this.keywords = new java.util.LinkedHashSet<String>();
			for (robotG.resource.robot.grammar.RobotRule rule : RULES) {
				findKeywords(rule, this.keywords);
			}
		}
		return keywords;
	}
	
	/**
	 * Finds all keywords in the given element and its children and adds them to the
	 * set. This includes all literals for boolean and enumeration terminals.
	 */
	private void findKeywords(robotG.resource.robot.grammar.RobotSyntaxElement element, java.util.Set<String> keywords) {
		if (element instanceof robotG.resource.robot.grammar.RobotKeyword) {
			keywords.add(((robotG.resource.robot.grammar.RobotKeyword) element).getValue());
		} else if (element instanceof robotG.resource.robot.grammar.RobotBooleanTerminal) {
			keywords.add(((robotG.resource.robot.grammar.RobotBooleanTerminal) element).getTrueLiteral());
			keywords.add(((robotG.resource.robot.grammar.RobotBooleanTerminal) element).getFalseLiteral());
		} else if (element instanceof robotG.resource.robot.grammar.RobotEnumerationTerminal) {
			robotG.resource.robot.grammar.RobotEnumerationTerminal terminal = (robotG.resource.robot.grammar.RobotEnumerationTerminal) element;
			for (String key : terminal.getLiteralMapping().keySet()) {
				keywords.add(key);
			}
		}
		for (robotG.resource.robot.grammar.RobotSyntaxElement child : element.getChildren()) {
			findKeywords(child, this.keywords);
		}
	}
	
}
