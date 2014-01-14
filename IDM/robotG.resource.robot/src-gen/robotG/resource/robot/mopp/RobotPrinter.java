/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package robotG.resource.robot.mopp;

public class RobotPrinter implements robotG.resource.robot.IRobotTextPrinter {
	
	protected robotG.resource.robot.IRobotTokenResolverFactory tokenResolverFactory = new robotG.resource.robot.mopp.RobotTokenResolverFactory();
	
	protected java.io.OutputStream outputStream;
	
	/**
	 * Holds the resource that is associated with this printer. This may be null if
	 * the printer is used stand alone.
	 */
	private robotG.resource.robot.IRobotTextResource resource;
	
	private java.util.Map<?, ?> options;
	private String encoding = System.getProperty("file.encoding");
	
	public RobotPrinter(java.io.OutputStream outputStream, robotG.resource.robot.IRobotTextResource resource) {
		super();
		this.outputStream = outputStream;
		this.resource = resource;
	}
	
	protected int matchCount(java.util.Map<String, Integer> featureCounter, java.util.Collection<String> needed) {
		int pos = 0;
		int neg = 0;
		
		for (String featureName : featureCounter.keySet()) {
			if (needed.contains(featureName)) {
				int value = featureCounter.get(featureName);
				if (value == 0) {
					neg += 1;
				} else {
					pos += 1;
				}
			}
		}
		return neg > 0 ? -neg : pos;
	}
	
	protected void doPrint(org.eclipse.emf.ecore.EObject element, java.io.PrintWriter out, String globaltab) {
		if (element == null) {
			throw new java.lang.IllegalArgumentException("Nothing to write.");
		}
		if (out == null) {
			throw new java.lang.IllegalArgumentException("Nothing to write on.");
		}
		
		if (element instanceof robotG.flow.Programme) {
			print_robotG_flow_Programme((robotG.flow.Programme) element, globaltab, out);
			return;
		}
		if (element instanceof robotG.flow.While) {
			print_robotG_flow_While((robotG.flow.While) element, globaltab, out);
			return;
		}
		if (element instanceof robotG.flow.If) {
			print_robotG_flow_If((robotG.flow.If) element, globaltab, out);
			return;
		}
		if (element instanceof robotG.flow.And) {
			print_robotG_flow_And((robotG.flow.And) element, globaltab, out);
			return;
		}
		if (element instanceof robotG.flow.Or) {
			print_robotG_flow_Or((robotG.flow.Or) element, globaltab, out);
			return;
		}
		if (element instanceof robotG.flow.Not) {
			print_robotG_flow_Not((robotG.flow.Not) element, globaltab, out);
			return;
		}
		if (element instanceof robotG.flow.StopProgram) {
			print_robotG_flow_StopProgram((robotG.flow.StopProgram) element, globaltab, out);
			return;
		}
		if (element instanceof robotG.robot.Bip) {
			print_robotG_robot_Bip((robotG.robot.Bip) element, globaltab, out);
			return;
		}
		if (element instanceof robotG.robot.Display) {
			print_robotG_robot_Display((robotG.robot.Display) element, globaltab, out);
			return;
		}
		if (element instanceof robotG.robot.HasTurned) {
			print_robotG_robot_HasTurned((robotG.robot.HasTurned) element, globaltab, out);
			return;
		}
		if (element instanceof robotG.robot.Move) {
			print_robotG_robot_Move((robotG.robot.Move) element, globaltab, out);
			return;
		}
		if (element instanceof robotG.robot.Obstacle) {
			print_robotG_robot_Obstacle((robotG.robot.Obstacle) element, globaltab, out);
			return;
		}
		if (element instanceof robotG.robot.SetTurnAngle) {
			print_robotG_robot_SetTurnAngle((robotG.robot.SetTurnAngle) element, globaltab, out);
			return;
		}
		if (element instanceof robotG.robot.StopEngine) {
			print_robotG_robot_StopEngine((robotG.robot.StopEngine) element, globaltab, out);
			return;
		}
		if (element instanceof robotG.robot.Turn) {
			print_robotG_robot_Turn((robotG.robot.Turn) element, globaltab, out);
			return;
		}
		
		addWarningToResource("The printer can not handle " + element.eClass().getName() + " elements", element);
	}
	
	protected robotG.resource.robot.mopp.RobotReferenceResolverSwitch getReferenceResolverSwitch() {
		return (robotG.resource.robot.mopp.RobotReferenceResolverSwitch) new robotG.resource.robot.mopp.RobotMetaInformation().getReferenceResolverSwitch();
	}
	
	protected void addWarningToResource(final String errorMessage, org.eclipse.emf.ecore.EObject cause) {
		robotG.resource.robot.IRobotTextResource resource = getResource();
		if (resource == null) {
			// the resource can be null if the printer is used stand alone
			return;
		}
		resource.addProblem(new robotG.resource.robot.mopp.RobotProblem(errorMessage, robotG.resource.robot.RobotEProblemType.PRINT_PROBLEM, robotG.resource.robot.RobotEProblemSeverity.WARNING), cause);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		this.options = options;
	}
	
	public java.util.Map<?,?> getOptions() {
		return options;
	}
	
	public void setEncoding(String encoding) {
		if (encoding != null) {
			this.encoding = encoding;
		}
	}
	
	public String getEncoding() {
		return encoding;
	}
	
	public robotG.resource.robot.IRobotTextResource getResource() {
		return resource;
	}
	
	/**
	 * Calls {@link #doPrint(EObject, PrintWriter, String)} and writes the result to
	 * the underlying output stream.
	 */
	public void print(org.eclipse.emf.ecore.EObject element) throws java.io.IOException {
		java.io.PrintWriter out = new java.io.PrintWriter(new java.io.OutputStreamWriter(new java.io.BufferedOutputStream(outputStream), encoding));
		doPrint(element, out, "");
		out.flush();
		out.close();
	}
	
	public void print_robotG_flow_Programme(robotG.flow.Programme element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.PROGRAMME__PROGRAMME));
		printCountingMap.put("programme", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("programme");
		if (count > 0) {
			java.util.List<?> list = (java.util.List<?>)element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.PROGRAMME__PROGRAMME));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			java.util.ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("programme", 0);
		}
	}
	
	
	public void print_robotG_flow_While(robotG.flow.While element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.WHILE__CONDITION));
		printCountingMap.put("condition", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.WHILE__INSTRUCTIONS));
		printCountingMap.put("instructions", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("while");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("condition");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.WHILE__CONDITION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("condition", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("do");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("instructions");
		if (count > 0) {
			java.util.List<?> list = (java.util.List<?>)element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.WHILE__INSTRUCTIONS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			java.util.ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("instructions", 0);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("end");
		out.print(" ");
	}
	
	
	public void print_robotG_flow_If(robotG.flow.If element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.IF__CONDITION));
		printCountingMap.put("condition", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.IF__INSTRUCTIONS));
		printCountingMap.put("instructions", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("if");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("condition");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.IF__CONDITION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("condition", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("then");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("instructions");
		if (count > 0) {
			java.util.List<?> list = (java.util.List<?>)element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.IF__INSTRUCTIONS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			java.util.ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("instructions", 0);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("end");
		out.print(" ");
	}
	
	
	public void print_robotG_flow_And(robotG.flow.And element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.AND__FILS_GAUCHE));
		printCountingMap.put("filsGauche", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.AND__FILS_DROIT));
		printCountingMap.put("filsDroit", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("filsGauche");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.AND__FILS_GAUCHE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("filsGauche", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("and");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("filsDroit");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.AND__FILS_DROIT));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("filsDroit", count - 1);
		}
	}
	
	
	public void print_robotG_flow_Or(robotG.flow.Or element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.OR__FILS_GAUCHE));
		printCountingMap.put("filsGauche", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.OR__FILS_DROIT));
		printCountingMap.put("filsDroit", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("filsGauche");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.OR__FILS_GAUCHE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("filsGauche", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("or");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("filsDroit");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.OR__FILS_DROIT));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("filsDroit", count - 1);
		}
	}
	
	
	public void print_robotG_flow_Not(robotG.flow.Not element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.NOT__EXPRESSION));
		printCountingMap.put("expression", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("not");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("expression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.NOT__EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("expression", count - 1);
		}
	}
	
	
	public void print_robotG_flow_StopProgram(robotG.flow.StopProgram element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("stopProgram");
		out.print(" ");
	}
	
	
	public void print_robotG_robot_Bip(robotG.robot.Bip element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.BIP__DURATION));
		printCountingMap.put("duration", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.BIP__POWER));
		printCountingMap.put("power", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.BIP__REPEAT));
		printCountingMap.put("repeat", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("bip");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("duration");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("duration");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.BIP__DURATION));
			if (o != null) {
				robotG.resource.robot.IRobotTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.BIP__DURATION), element));
				out.print(" ");
			}
			printCountingMap.put("duration", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("power");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("power");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.BIP__POWER));
			if (o != null) {
				robotG.resource.robot.IRobotTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.BIP__POWER), element));
				out.print(" ");
			}
			printCountingMap.put("power", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("repeat");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("repeat");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.BIP__REPEAT));
			if (o != null) {
				robotG.resource.robot.IRobotTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.BIP__REPEAT), element));
				out.print(" ");
			}
			printCountingMap.put("repeat", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
	public void print_robotG_robot_Display(robotG.robot.Display element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__MSG));
		printCountingMap.put("msg", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__DURATION));
		printCountingMap.put("duration", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__LINE));
		printCountingMap.put("line", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__COL));
		printCountingMap.put("col", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("display");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("msg");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("msg");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__MSG));
			if (o != null) {
				robotG.resource.robot.IRobotTokenResolver resolver = tokenResolverFactory.createTokenResolver("STRING_LITERAL");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__MSG), element));
				out.print(" ");
			}
			printCountingMap.put("msg", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("duration");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("duration");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__DURATION));
			if (o != null) {
				robotG.resource.robot.IRobotTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__DURATION), element));
				out.print(" ");
			}
			printCountingMap.put("duration", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("line");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("line");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__LINE));
			if (o != null) {
				robotG.resource.robot.IRobotTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__LINE), element));
				out.print(" ");
			}
			printCountingMap.put("line", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("col");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("col");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__COL));
			if (o != null) {
				robotG.resource.robot.IRobotTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__COL), element));
				out.print(" ");
			}
			printCountingMap.put("col", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
	public void print_robotG_robot_HasTurned(robotG.robot.HasTurned element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.HAS_TURNED__ANGLE));
		printCountingMap.put("angle", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("hasTurned");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("angle");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("angle");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.HAS_TURNED__ANGLE));
			if (o != null) {
				robotG.resource.robot.IRobotTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.HAS_TURNED__ANGLE), element));
				out.print(" ");
			}
			printCountingMap.put("angle", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
	public void print_robotG_robot_Move(robotG.robot.Move element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.MOVE__POWER));
		printCountingMap.put("power", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("move");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("power");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("power");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.MOVE__POWER));
			if (o != null) {
				robotG.resource.robot.IRobotTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.MOVE__POWER), element));
				out.print(" ");
			}
			printCountingMap.put("power", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
	public void print_robotG_robot_Obstacle(robotG.robot.Obstacle element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.OBSTACLE__DISTANCE));
		printCountingMap.put("distance", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("obstacle");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("distance");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("distance");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.OBSTACLE__DISTANCE));
			if (o != null) {
				robotG.resource.robot.IRobotTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.OBSTACLE__DISTANCE), element));
				out.print(" ");
			}
			printCountingMap.put("distance", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
	public void print_robotG_robot_SetTurnAngle(robotG.robot.SetTurnAngle element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.SET_TURN_ANGLE__ANGLE));
		printCountingMap.put("angle", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("setTurnAngle");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("angle");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("angle");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.SET_TURN_ANGLE__ANGLE));
			if (o != null) {
				robotG.resource.robot.IRobotTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.SET_TURN_ANGLE__ANGLE), element));
				out.print(" ");
			}
			printCountingMap.put("angle", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
	public void print_robotG_robot_StopEngine(robotG.robot.StopEngine element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("stopEngine");
		out.print(" ");
	}
	
	
	public void print_robotG_robot_Turn(robotG.robot.Turn element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.TURN__POWER));
		printCountingMap.put("power", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.TURN__ANGLE));
		printCountingMap.put("angle", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("turn");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("power");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("power");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.TURN__POWER));
			if (o != null) {
				robotG.resource.robot.IRobotTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.TURN__POWER), element));
				out.print(" ");
			}
			printCountingMap.put("power", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("angle");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("angle");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.TURN__ANGLE));
			if (o != null) {
				robotG.resource.robot.IRobotTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.TURN__ANGLE), element));
				out.print(" ");
			}
			printCountingMap.put("angle", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
}
