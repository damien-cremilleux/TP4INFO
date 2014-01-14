grammar Robot;

options {
	superClass = RobotANTLRParserBase;
	backtrack = true;
	memoize = true;
}

@lexer::header {
	package robotG.resource.robot.mopp;
}

@lexer::members {
	public java.util.List<org.antlr.runtime3_4_0.RecognitionException> lexerExceptions  = new java.util.ArrayList<org.antlr.runtime3_4_0.RecognitionException>();
	public java.util.List<Integer> lexerExceptionsPosition = new java.util.ArrayList<Integer>();
	
	public void reportError(org.antlr.runtime3_4_0.RecognitionException e) {
		lexerExceptions.add(e);
		lexerExceptionsPosition.add(((org.antlr.runtime3_4_0.ANTLRStringStream) input).index());
	}
}
@header{
	package robotG.resource.robot.mopp;
}

@members{
	private robotG.resource.robot.IRobotTokenResolverFactory tokenResolverFactory = new robotG.resource.robot.mopp.RobotTokenResolverFactory();
	
	/**
	 * the index of the last token that was handled by collectHiddenTokens()
	 */
	private int lastPosition;
	
	/**
	 * A flag that indicates whether the parser should remember all expected elements.
	 * This flag is set to true when using the parse for code completion. Otherwise it
	 * is set to false.
	 */
	private boolean rememberExpectedElements = false;
	
	private Object parseToIndexTypeObject;
	private int lastTokenIndex = 0;
	
	/**
	 * A list of expected elements the were collected while parsing the input stream.
	 * This list is only filled if <code>rememberExpectedElements</code> is set to
	 * true.
	 */
	private java.util.List<robotG.resource.robot.mopp.RobotExpectedTerminal> expectedElements = new java.util.ArrayList<robotG.resource.robot.mopp.RobotExpectedTerminal>();
	
	private int mismatchedTokenRecoveryTries = 0;
	/**
	 * A helper list to allow a lexer to pass errors to its parser
	 */
	protected java.util.List<org.antlr.runtime3_4_0.RecognitionException> lexerExceptions = java.util.Collections.synchronizedList(new java.util.ArrayList<org.antlr.runtime3_4_0.RecognitionException>());
	
	/**
	 * Another helper list to allow a lexer to pass positions of errors to its parser
	 */
	protected java.util.List<Integer> lexerExceptionsPosition = java.util.Collections.synchronizedList(new java.util.ArrayList<Integer>());
	
	/**
	 * A stack for incomplete objects. This stack is used filled when the parser is
	 * used for code completion. Whenever the parser starts to read an object it is
	 * pushed on the stack. Once the element was parser completely it is popped from
	 * the stack.
	 */
	java.util.List<org.eclipse.emf.ecore.EObject> incompleteObjects = new java.util.ArrayList<org.eclipse.emf.ecore.EObject>();
	
	private int stopIncludingHiddenTokens;
	private int stopExcludingHiddenTokens;
	private int tokenIndexOfLastCompleteElement;
	
	private int expectedElementsIndexOfLastCompleteElement;
	
	/**
	 * The offset indicating the cursor position when the parser is used for code
	 * completion by calling parseToExpectedElements().
	 */
	private int cursorOffset;
	
	/**
	 * The offset of the first hidden token of the last expected element. This offset
	 * is used to discard expected elements, which are not needed for code completion.
	 */
	private int lastStartIncludingHidden;
	
	protected void addErrorToResource(final String errorMessage, final int column, final int line, final int startIndex, final int stopIndex) {
		postParseCommands.add(new robotG.resource.robot.IRobotCommand<robotG.resource.robot.IRobotTextResource>() {
			public boolean execute(robotG.resource.robot.IRobotTextResource resource) {
				if (resource == null) {
					// the resource can be null if the parser is used for code completion
					return true;
				}
				resource.addProblem(new robotG.resource.robot.IRobotProblem() {
					public robotG.resource.robot.RobotEProblemSeverity getSeverity() {
						return robotG.resource.robot.RobotEProblemSeverity.ERROR;
					}
					public robotG.resource.robot.RobotEProblemType getType() {
						return robotG.resource.robot.RobotEProblemType.SYNTAX_ERROR;
					}
					public String getMessage() {
						return errorMessage;
					}
					public java.util.Collection<robotG.resource.robot.IRobotQuickFix> getQuickFixes() {
						return null;
					}
				}, column, line, startIndex, stopIndex);
				return true;
			}
		});
	}
	
	public void addExpectedElement(org.eclipse.emf.ecore.EClass eClass, int[] ids) {
		if (!this.rememberExpectedElements) {
			return;
		}
		int terminalID = ids[0];
		int followSetID = ids[1];
		robotG.resource.robot.IRobotExpectedElement terminal = robotG.resource.robot.grammar.RobotFollowSetProvider.TERMINALS[terminalID];
		robotG.resource.robot.mopp.RobotContainedFeature[] containmentFeatures = new robotG.resource.robot.mopp.RobotContainedFeature[ids.length - 2];
		for (int i = 2; i < ids.length; i++) {
			containmentFeatures[i - 2] = robotG.resource.robot.grammar.RobotFollowSetProvider.LINKS[ids[i]];
		}
		robotG.resource.robot.grammar.RobotContainmentTrace containmentTrace = new robotG.resource.robot.grammar.RobotContainmentTrace(eClass, containmentFeatures);
		org.eclipse.emf.ecore.EObject container = getLastIncompleteElement();
		robotG.resource.robot.mopp.RobotExpectedTerminal expectedElement = new robotG.resource.robot.mopp.RobotExpectedTerminal(container, terminal, followSetID, containmentTrace);
		setPosition(expectedElement, input.index());
		int startIncludingHiddenTokens = expectedElement.getStartIncludingHiddenTokens();
		if (lastStartIncludingHidden >= 0 && lastStartIncludingHidden < startIncludingHiddenTokens && cursorOffset > startIncludingHiddenTokens) {
			// clear list of expected elements
			this.expectedElements.clear();
			this.expectedElementsIndexOfLastCompleteElement = 0;
		}
		lastStartIncludingHidden = startIncludingHiddenTokens;
		this.expectedElements.add(expectedElement);
	}
	
	protected void collectHiddenTokens(org.eclipse.emf.ecore.EObject element) {
	}
	
	protected void copyLocalizationInfos(final org.eclipse.emf.ecore.EObject source, final org.eclipse.emf.ecore.EObject target) {
		if (disableLocationMap) {
			return;
		}
		postParseCommands.add(new robotG.resource.robot.IRobotCommand<robotG.resource.robot.IRobotTextResource>() {
			public boolean execute(robotG.resource.robot.IRobotTextResource resource) {
				robotG.resource.robot.IRobotLocationMap locationMap = resource.getLocationMap();
				if (locationMap == null) {
					// the locationMap can be null if the parser is used for code completion
					return true;
				}
				locationMap.setCharStart(target, locationMap.getCharStart(source));
				locationMap.setCharEnd(target, locationMap.getCharEnd(source));
				locationMap.setColumn(target, locationMap.getColumn(source));
				locationMap.setLine(target, locationMap.getLine(source));
				return true;
			}
		});
	}
	
	protected void copyLocalizationInfos(final org.antlr.runtime3_4_0.CommonToken source, final org.eclipse.emf.ecore.EObject target) {
		if (disableLocationMap) {
			return;
		}
		postParseCommands.add(new robotG.resource.robot.IRobotCommand<robotG.resource.robot.IRobotTextResource>() {
			public boolean execute(robotG.resource.robot.IRobotTextResource resource) {
				robotG.resource.robot.IRobotLocationMap locationMap = resource.getLocationMap();
				if (locationMap == null) {
					// the locationMap can be null if the parser is used for code completion
					return true;
				}
				if (source == null) {
					return true;
				}
				locationMap.setCharStart(target, source.getStartIndex());
				locationMap.setCharEnd(target, source.getStopIndex());
				locationMap.setColumn(target, source.getCharPositionInLine());
				locationMap.setLine(target, source.getLine());
				return true;
			}
		});
	}
	
	/**
	 * Sets the end character index and the last line for the given object in the
	 * location map.
	 */
	protected void setLocalizationEnd(java.util.Collection<robotG.resource.robot.IRobotCommand<robotG.resource.robot.IRobotTextResource>> postParseCommands , final org.eclipse.emf.ecore.EObject object, final int endChar, final int endLine) {
		if (disableLocationMap) {
			return;
		}
		postParseCommands.add(new robotG.resource.robot.IRobotCommand<robotG.resource.robot.IRobotTextResource>() {
			public boolean execute(robotG.resource.robot.IRobotTextResource resource) {
				robotG.resource.robot.IRobotLocationMap locationMap = resource.getLocationMap();
				if (locationMap == null) {
					// the locationMap can be null if the parser is used for code completion
					return true;
				}
				locationMap.setCharEnd(object, endChar);
				locationMap.setLine(object, endLine);
				return true;
			}
		});
	}
	
	public robotG.resource.robot.IRobotTextParser createInstance(java.io.InputStream actualInputStream, String encoding) {
		try {
			if (encoding == null) {
				return new RobotParser(new org.antlr.runtime3_4_0.CommonTokenStream(new RobotLexer(new org.antlr.runtime3_4_0.ANTLRInputStream(actualInputStream))));
			} else {
				return new RobotParser(new org.antlr.runtime3_4_0.CommonTokenStream(new RobotLexer(new org.antlr.runtime3_4_0.ANTLRInputStream(actualInputStream, encoding))));
			}
		} catch (java.io.IOException e) {
			new robotG.resource.robot.util.RobotRuntimeUtil().logError("Error while creating parser.", e);
			return null;
		}
	}
	
	/**
	 * This default constructor is only used to call createInstance() on it.
	 */
	public RobotParser() {
		super(null);
	}
	
	protected org.eclipse.emf.ecore.EObject doParse() throws org.antlr.runtime3_4_0.RecognitionException {
		this.lastPosition = 0;
		// required because the lexer class can not be subclassed
		((RobotLexer) getTokenStream().getTokenSource()).lexerExceptions = lexerExceptions;
		((RobotLexer) getTokenStream().getTokenSource()).lexerExceptionsPosition = lexerExceptionsPosition;
		Object typeObject = getTypeObject();
		if (typeObject == null) {
			return start();
		} else if (typeObject instanceof org.eclipse.emf.ecore.EClass) {
			org.eclipse.emf.ecore.EClass type = (org.eclipse.emf.ecore.EClass) typeObject;
			if (type.getInstanceClass() == robotG.flow.Programme.class) {
				return parse_robotG_flow_Programme();
			}
		}
		throw new robotG.resource.robot.mopp.RobotUnexpectedContentTypeException(typeObject);
	}
	
	public int getMismatchedTokenRecoveryTries() {
		return mismatchedTokenRecoveryTries;
	}
	
	public Object getMissingSymbol(org.antlr.runtime3_4_0.IntStream arg0, org.antlr.runtime3_4_0.RecognitionException arg1, int arg2, org.antlr.runtime3_4_0.BitSet arg3) {
		mismatchedTokenRecoveryTries++;
		return super.getMissingSymbol(arg0, arg1, arg2, arg3);
	}
	
	public Object getParseToIndexTypeObject() {
		return parseToIndexTypeObject;
	}
	
	protected Object getTypeObject() {
		Object typeObject = getParseToIndexTypeObject();
		if (typeObject != null) {
			return typeObject;
		}
		java.util.Map<?,?> options = getOptions();
		if (options != null) {
			typeObject = options.get(robotG.resource.robot.IRobotOptions.RESOURCE_CONTENT_TYPE);
		}
		return typeObject;
	}
	
	/**
	 * Implementation that calls {@link #doParse()} and handles the thrown
	 * RecognitionExceptions.
	 */
	public robotG.resource.robot.IRobotParseResult parse() {
		terminateParsing = false;
		postParseCommands = new java.util.ArrayList<robotG.resource.robot.IRobotCommand<robotG.resource.robot.IRobotTextResource>>();
		robotG.resource.robot.mopp.RobotParseResult parseResult = new robotG.resource.robot.mopp.RobotParseResult();
		try {
			org.eclipse.emf.ecore.EObject result =  doParse();
			if (lexerExceptions.isEmpty()) {
				parseResult.setRoot(result);
			}
		} catch (org.antlr.runtime3_4_0.RecognitionException re) {
			reportError(re);
		} catch (java.lang.IllegalArgumentException iae) {
			if ("The 'no null' constraint is violated".equals(iae.getMessage())) {
				// can be caused if a null is set on EMF models where not allowed. this will just
				// happen if other errors occurred before
			} else {
				iae.printStackTrace();
			}
		}
		for (org.antlr.runtime3_4_0.RecognitionException re : lexerExceptions) {
			reportLexicalError(re);
		}
		parseResult.getPostParseCommands().addAll(postParseCommands);
		return parseResult;
	}
	
	public java.util.List<robotG.resource.robot.mopp.RobotExpectedTerminal> parseToExpectedElements(org.eclipse.emf.ecore.EClass type, robotG.resource.robot.IRobotTextResource dummyResource, int cursorOffset) {
		this.rememberExpectedElements = true;
		this.parseToIndexTypeObject = type;
		this.cursorOffset = cursorOffset;
		this.lastStartIncludingHidden = -1;
		final org.antlr.runtime3_4_0.CommonTokenStream tokenStream = (org.antlr.runtime3_4_0.CommonTokenStream) getTokenStream();
		robotG.resource.robot.IRobotParseResult result = parse();
		for (org.eclipse.emf.ecore.EObject incompleteObject : incompleteObjects) {
			org.antlr.runtime3_4_0.Lexer lexer = (org.antlr.runtime3_4_0.Lexer) tokenStream.getTokenSource();
			int endChar = lexer.getCharIndex();
			int endLine = lexer.getLine();
			setLocalizationEnd(result.getPostParseCommands(), incompleteObject, endChar, endLine);
		}
		if (result != null) {
			org.eclipse.emf.ecore.EObject root = result.getRoot();
			if (root != null) {
				dummyResource.getContentsInternal().add(root);
			}
			for (robotG.resource.robot.IRobotCommand<robotG.resource.robot.IRobotTextResource> command : result.getPostParseCommands()) {
				command.execute(dummyResource);
			}
		}
		// remove all expected elements that were added after the last complete element
		expectedElements = expectedElements.subList(0, expectedElementsIndexOfLastCompleteElement + 1);
		int lastFollowSetID = expectedElements.get(expectedElementsIndexOfLastCompleteElement).getFollowSetID();
		java.util.Set<robotG.resource.robot.mopp.RobotExpectedTerminal> currentFollowSet = new java.util.LinkedHashSet<robotG.resource.robot.mopp.RobotExpectedTerminal>();
		java.util.List<robotG.resource.robot.mopp.RobotExpectedTerminal> newFollowSet = new java.util.ArrayList<robotG.resource.robot.mopp.RobotExpectedTerminal>();
		for (int i = expectedElementsIndexOfLastCompleteElement; i >= 0; i--) {
			robotG.resource.robot.mopp.RobotExpectedTerminal expectedElementI = expectedElements.get(i);
			if (expectedElementI.getFollowSetID() == lastFollowSetID) {
				currentFollowSet.add(expectedElementI);
			} else {
				break;
			}
		}
		int followSetID = 83;
		int i;
		for (i = tokenIndexOfLastCompleteElement; i < tokenStream.size(); i++) {
			org.antlr.runtime3_4_0.CommonToken nextToken = (org.antlr.runtime3_4_0.CommonToken) tokenStream.get(i);
			if (nextToken.getType() < 0) {
				break;
			}
			if (nextToken.getChannel() == 99) {
				// hidden tokens do not reduce the follow set
			} else {
				// now that we have found the next visible token the position for that expected
				// terminals can be set
				for (robotG.resource.robot.mopp.RobotExpectedTerminal nextFollow : newFollowSet) {
					lastTokenIndex = 0;
					setPosition(nextFollow, i);
				}
				newFollowSet.clear();
				// normal tokens do reduce the follow set - only elements that match the token are
				// kept
				for (robotG.resource.robot.mopp.RobotExpectedTerminal nextFollow : currentFollowSet) {
					if (nextFollow.getTerminal().getTokenNames().contains(getTokenNames()[nextToken.getType()])) {
						// keep this one - it matches
						java.util.Collection<robotG.resource.robot.util.RobotPair<robotG.resource.robot.IRobotExpectedElement, robotG.resource.robot.mopp.RobotContainedFeature[]>> newFollowers = nextFollow.getTerminal().getFollowers();
						for (robotG.resource.robot.util.RobotPair<robotG.resource.robot.IRobotExpectedElement, robotG.resource.robot.mopp.RobotContainedFeature[]> newFollowerPair : newFollowers) {
							robotG.resource.robot.IRobotExpectedElement newFollower = newFollowerPair.getLeft();
							org.eclipse.emf.ecore.EObject container = getLastIncompleteElement();
							robotG.resource.robot.grammar.RobotContainmentTrace containmentTrace = new robotG.resource.robot.grammar.RobotContainmentTrace(null, newFollowerPair.getRight());
							robotG.resource.robot.mopp.RobotExpectedTerminal newFollowTerminal = new robotG.resource.robot.mopp.RobotExpectedTerminal(container, newFollower, followSetID, containmentTrace);
							newFollowSet.add(newFollowTerminal);
							expectedElements.add(newFollowTerminal);
						}
					}
				}
				currentFollowSet.clear();
				currentFollowSet.addAll(newFollowSet);
			}
			followSetID++;
		}
		// after the last token in the stream we must set the position for the elements
		// that were added during the last iteration of the loop
		for (robotG.resource.robot.mopp.RobotExpectedTerminal nextFollow : newFollowSet) {
			lastTokenIndex = 0;
			setPosition(nextFollow, i);
		}
		return this.expectedElements;
	}
	
	public void setPosition(robotG.resource.robot.mopp.RobotExpectedTerminal expectedElement, int tokenIndex) {
		int currentIndex = Math.max(0, tokenIndex);
		for (int index = lastTokenIndex; index < currentIndex; index++) {
			if (index >= input.size()) {
				break;
			}
			org.antlr.runtime3_4_0.CommonToken tokenAtIndex = (org.antlr.runtime3_4_0.CommonToken) input.get(index);
			stopIncludingHiddenTokens = tokenAtIndex.getStopIndex() + 1;
			if (tokenAtIndex.getChannel() != 99 && !anonymousTokens.contains(tokenAtIndex)) {
				stopExcludingHiddenTokens = tokenAtIndex.getStopIndex() + 1;
			}
		}
		lastTokenIndex = Math.max(0, currentIndex);
		expectedElement.setPosition(stopExcludingHiddenTokens, stopIncludingHiddenTokens);
	}
	
	public Object recoverFromMismatchedToken(org.antlr.runtime3_4_0.IntStream input, int ttype, org.antlr.runtime3_4_0.BitSet follow) throws org.antlr.runtime3_4_0.RecognitionException {
		if (!rememberExpectedElements) {
			return super.recoverFromMismatchedToken(input, ttype, follow);
		} else {
			return null;
		}
	}
	
	/**
	 * Translates errors thrown by the parser into human readable messages.
	 */
	public void reportError(final org.antlr.runtime3_4_0.RecognitionException e)  {
		String message = e.getMessage();
		if (e instanceof org.antlr.runtime3_4_0.MismatchedTokenException) {
			org.antlr.runtime3_4_0.MismatchedTokenException mte = (org.antlr.runtime3_4_0.MismatchedTokenException) e;
			String expectedTokenName = formatTokenName(mte.expecting);
			String actualTokenName = formatTokenName(e.token.getType());
			message = "Syntax error on token \"" + e.token.getText() + " (" + actualTokenName + ")\", \"" + expectedTokenName + "\" expected";
		} else if (e instanceof org.antlr.runtime3_4_0.MismatchedTreeNodeException) {
			org.antlr.runtime3_4_0.MismatchedTreeNodeException mtne = (org.antlr.runtime3_4_0.MismatchedTreeNodeException) e;
			String expectedTokenName = formatTokenName(mtne.expecting);
			message = "mismatched tree node: " + "xxx" + "; tokenName " + expectedTokenName;
		} else if (e instanceof org.antlr.runtime3_4_0.NoViableAltException) {
			message = "Syntax error on token \"" + e.token.getText() + "\", check following tokens";
		} else if (e instanceof org.antlr.runtime3_4_0.EarlyExitException) {
			message = "Syntax error on token \"" + e.token.getText() + "\", delete this token";
		} else if (e instanceof org.antlr.runtime3_4_0.MismatchedSetException) {
			org.antlr.runtime3_4_0.MismatchedSetException mse = (org.antlr.runtime3_4_0.MismatchedSetException) e;
			message = "mismatched token: " + e.token + "; expecting set " + mse.expecting;
		} else if (e instanceof org.antlr.runtime3_4_0.MismatchedNotSetException) {
			org.antlr.runtime3_4_0.MismatchedNotSetException mse = (org.antlr.runtime3_4_0.MismatchedNotSetException) e;
			message = "mismatched token: " +  e.token + "; expecting set " + mse.expecting;
		} else if (e instanceof org.antlr.runtime3_4_0.FailedPredicateException) {
			org.antlr.runtime3_4_0.FailedPredicateException fpe = (org.antlr.runtime3_4_0.FailedPredicateException) e;
			message = "rule " + fpe.ruleName + " failed predicate: {" +  fpe.predicateText + "}?";
		}
		// the resource may be null if the parser is used for code completion
		final String finalMessage = message;
		if (e.token instanceof org.antlr.runtime3_4_0.CommonToken) {
			final org.antlr.runtime3_4_0.CommonToken ct = (org.antlr.runtime3_4_0.CommonToken) e.token;
			addErrorToResource(finalMessage, ct.getCharPositionInLine(), ct.getLine(), ct.getStartIndex(), ct.getStopIndex());
		} else {
			addErrorToResource(finalMessage, e.token.getCharPositionInLine(), e.token.getLine(), 1, 5);
		}
	}
	
	/**
	 * Translates errors thrown by the lexer into human readable messages.
	 */
	public void reportLexicalError(final org.antlr.runtime3_4_0.RecognitionException e)  {
		String message = "";
		if (e instanceof org.antlr.runtime3_4_0.MismatchedTokenException) {
			org.antlr.runtime3_4_0.MismatchedTokenException mte = (org.antlr.runtime3_4_0.MismatchedTokenException) e;
			message = "Syntax error on token \"" + ((char) e.c) + "\", \"" + (char) mte.expecting + "\" expected";
		} else if (e instanceof org.antlr.runtime3_4_0.NoViableAltException) {
			message = "Syntax error on token \"" + ((char) e.c) + "\", delete this token";
		} else if (e instanceof org.antlr.runtime3_4_0.EarlyExitException) {
			org.antlr.runtime3_4_0.EarlyExitException eee = (org.antlr.runtime3_4_0.EarlyExitException) e;
			message = "required (...)+ loop (decision=" + eee.decisionNumber + ") did not match anything; on line " + e.line + ":" + e.charPositionInLine + " char=" + ((char) e.c) + "'";
		} else if (e instanceof org.antlr.runtime3_4_0.MismatchedSetException) {
			org.antlr.runtime3_4_0.MismatchedSetException mse = (org.antlr.runtime3_4_0.MismatchedSetException) e;
			message = "mismatched char: '" + ((char) e.c) + "' on line " + e.line + ":" + e.charPositionInLine + "; expecting set " + mse.expecting;
		} else if (e instanceof org.antlr.runtime3_4_0.MismatchedNotSetException) {
			org.antlr.runtime3_4_0.MismatchedNotSetException mse = (org.antlr.runtime3_4_0.MismatchedNotSetException) e;
			message = "mismatched char: '" + ((char) e.c) + "' on line " + e.line + ":" + e.charPositionInLine + "; expecting set " + mse.expecting;
		} else if (e instanceof org.antlr.runtime3_4_0.MismatchedRangeException) {
			org.antlr.runtime3_4_0.MismatchedRangeException mre = (org.antlr.runtime3_4_0.MismatchedRangeException) e;
			message = "mismatched char: '" + ((char) e.c) + "' on line " + e.line + ":" + e.charPositionInLine + "; expecting set '" + (char) mre.a + "'..'" + (char) mre.b + "'";
		} else if (e instanceof org.antlr.runtime3_4_0.FailedPredicateException) {
			org.antlr.runtime3_4_0.FailedPredicateException fpe = (org.antlr.runtime3_4_0.FailedPredicateException) e;
			message = "rule " + fpe.ruleName + " failed predicate: {" + fpe.predicateText + "}?";
		}
		addErrorToResource(message, e.charPositionInLine, e.line, lexerExceptionsPosition.get(lexerExceptions.indexOf(e)), lexerExceptionsPosition.get(lexerExceptions.indexOf(e)));
	}
	
	private void startIncompleteElement(Object object) {
		if (object instanceof org.eclipse.emf.ecore.EObject) {
			this.incompleteObjects.add((org.eclipse.emf.ecore.EObject) object);
		}
	}
	
	private void completedElement(Object object, boolean isContainment) {
		if (isContainment && !this.incompleteObjects.isEmpty()) {
			boolean exists = this.incompleteObjects.remove(object);
			if (!exists) {
			}
		}
		if (object instanceof org.eclipse.emf.ecore.EObject) {
			this.tokenIndexOfLastCompleteElement = getTokenStream().index();
			this.expectedElementsIndexOfLastCompleteElement = expectedElements.size() - 1;
		}
	}
	
	private org.eclipse.emf.ecore.EObject getLastIncompleteElement() {
		if (incompleteObjects.isEmpty()) {
			return null;
		}
		return incompleteObjects.get(incompleteObjects.size() - 1);
	}
	
}

start returns [ org.eclipse.emf.ecore.EObject element = null]
:
	{
		// follow set for start rule(s)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[0]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[1]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[2]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[3]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[4]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[5]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[6]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[7]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[8]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[9]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[10]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[11]);
		expectedElementsIndexOfLastCompleteElement = 0;
	}
	(
		c0 = parse_robotG_flow_Programme{ element = c0; }
	)
	EOF	{
		retrieveLayoutInformation(element, null, null, false);
	}
	
;

parse_robotG_flow_Programme returns [robotG.flow.Programme element = null]
@init{
}
:
	(
		(
			a0_0 = parse_robotG_flow_Expr			{
				if (terminateParsing) {
					throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
				}
				if (element == null) {
					element = robotG.flow.FlowFactory.eINSTANCE.createProgramme();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, robotG.flow.FlowPackage.PROGRAMME__PROGRAMME, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_0_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[12]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[13]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[14]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[15]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[16]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[17]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[18]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[19]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[20]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[21]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[22]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[23]);
	}
	
;

parseop_Expr_level_4 returns [robotG.flow.Expr element = null]
@init{
}
:
	c0 = parse_robotG_flow_While{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_robotG_flow_If{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_robotG_flow_StopProgram{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_robotG_robot_Bip{ element = c3; /* this is a subclass or primitive expression choice */ }
	|	c4 = parse_robotG_robot_Display{ element = c4; /* this is a subclass or primitive expression choice */ }
	|	c5 = parse_robotG_robot_Move{ element = c5; /* this is a subclass or primitive expression choice */ }
	|	c6 = parse_robotG_robot_SetTurnAngle{ element = c6; /* this is a subclass or primitive expression choice */ }
	|	c7 = parse_robotG_robot_StopEngine{ element = c7; /* this is a subclass or primitive expression choice */ }
	|	c8 = parse_robotG_robot_Turn{ element = c8; /* this is a subclass or primitive expression choice */ }
;

parse_robotG_flow_While returns [robotG.flow.While element = null]
@init{
}
:
	a0 = 'while' {
		if (element == null) {
			element = robotG.flow.FlowFactory.eINSTANCE.createWhile();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_1_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[24]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[25]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[26]);
	}
	
	(
		a1_0 = parse_robotG_flow_ExprBool		{
			if (terminateParsing) {
				throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
			}
			if (element == null) {
				element = robotG.flow.FlowFactory.eINSTANCE.createWhile();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.WHILE__CONDITION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_1_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[27]);
	}
	
	a2 = 'do' {
		if (element == null) {
			element = robotG.flow.FlowFactory.eINSTANCE.createWhile();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_1_0_0_2, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[28]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[29]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[30]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[31]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[32]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[33]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[34]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[35]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[36]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[37]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[38]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[39]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[40]);
	}
	
	(
		(
			a3_0 = parse_robotG_flow_Expr			{
				if (terminateParsing) {
					throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
				}
				if (element == null) {
					element = robotG.flow.FlowFactory.eINSTANCE.createWhile();
					startIncompleteElement(element);
				}
				if (a3_0 != null) {
					if (a3_0 != null) {
						Object value = a3_0;
						addObjectToList(element, robotG.flow.FlowPackage.WHILE__INSTRUCTIONS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_1_0_0_3, a3_0, true);
					copyLocalizationInfos(a3_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[41]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[42]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[43]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[44]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[45]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[46]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[47]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[48]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[49]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[50]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[51]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[52]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[53]);
	}
	
	a4 = 'end' {
		if (element == null) {
			element = robotG.flow.FlowFactory.eINSTANCE.createWhile();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_1_0_0_4, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a4, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[54]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[55]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[56]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[57]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[58]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[59]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[60]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[61]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[62]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[63]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[64]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[65]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[66]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[67]);
	}
	
;

parse_robotG_flow_If returns [robotG.flow.If element = null]
@init{
}
:
	a0 = 'if' {
		if (element == null) {
			element = robotG.flow.FlowFactory.eINSTANCE.createIf();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_2_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[68]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[69]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[70]);
	}
	
	(
		a1_0 = parse_robotG_flow_ExprBool		{
			if (terminateParsing) {
				throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
			}
			if (element == null) {
				element = robotG.flow.FlowFactory.eINSTANCE.createIf();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.IF__CONDITION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_2_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[71]);
	}
	
	a2 = 'then' {
		if (element == null) {
			element = robotG.flow.FlowFactory.eINSTANCE.createIf();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_2_0_0_2, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[72]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[73]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[74]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[75]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[76]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[77]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[78]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[79]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[80]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[81]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[82]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[83]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[84]);
	}
	
	(
		(
			a3_0 = parse_robotG_flow_Expr			{
				if (terminateParsing) {
					throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
				}
				if (element == null) {
					element = robotG.flow.FlowFactory.eINSTANCE.createIf();
					startIncompleteElement(element);
				}
				if (a3_0 != null) {
					if (a3_0 != null) {
						Object value = a3_0;
						addObjectToList(element, robotG.flow.FlowPackage.IF__INSTRUCTIONS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_2_0_0_3, a3_0, true);
					copyLocalizationInfos(a3_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[85]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[86]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[87]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[88]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[89]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[90]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[91]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[92]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[93]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[94]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[95]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[96]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[97]);
	}
	
	a4 = 'end' {
		if (element == null) {
			element = robotG.flow.FlowFactory.eINSTANCE.createIf();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_2_0_0_4, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a4, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[98]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[99]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[100]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[101]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[102]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[103]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[104]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[105]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[106]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[107]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[108]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[109]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[110]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[111]);
	}
	
;

parse_robotG_flow_StopProgram returns [robotG.flow.StopProgram element = null]
@init{
}
:
	a0 = 'stopProgram' {
		if (element == null) {
			element = robotG.flow.FlowFactory.eINSTANCE.createStopProgram();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_6_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[112]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[113]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[114]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[115]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[116]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[117]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[118]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[119]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[120]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[121]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[122]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[123]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[124]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[125]);
	}
	
;

parse_robotG_robot_Bip returns [robotG.robot.Bip element = null]
@init{
}
:
	a0 = 'bip' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[126]);
	}
	
	a1 = '(' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[127]);
	}
	
	a2 = 'duration' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_2, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[128]);
	}
	
	a3 = '=' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_3, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[129]);
	}
	
	(
		a4 = TEXT		
		{
			if (terminateParsing) {
				throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
			}
			if (element == null) {
				element = robotG.robot.RobotFactory.eINSTANCE.createBip();
				startIncompleteElement(element);
			}
			if (a4 != null) {
				robotG.resource.robot.IRobotTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
				tokenResolver.setOptions(getOptions());
				robotG.resource.robot.IRobotTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a4.getText(), element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.BIP__DURATION), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a4).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStopIndex());
				}
				java.lang.Integer resolved = (java.lang.Integer) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.BIP__DURATION), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_4, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a4, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[130]);
	}
	
	a5 = ',' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_5, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[131]);
	}
	
	a6 = 'power' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_6, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a6, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[132]);
	}
	
	a7 = '=' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_7, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a7, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[133]);
	}
	
	(
		a8 = TEXT		
		{
			if (terminateParsing) {
				throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
			}
			if (element == null) {
				element = robotG.robot.RobotFactory.eINSTANCE.createBip();
				startIncompleteElement(element);
			}
			if (a8 != null) {
				robotG.resource.robot.IRobotTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
				tokenResolver.setOptions(getOptions());
				robotG.resource.robot.IRobotTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a8.getText(), element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.BIP__POWER), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a8).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a8).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a8).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a8).getStopIndex());
				}
				java.lang.Integer resolved = (java.lang.Integer) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.BIP__POWER), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_8, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a8, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[134]);
	}
	
	a9 = ',' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_9, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a9, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[135]);
	}
	
	a10 = 'repeat' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_10, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a10, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[136]);
	}
	
	a11 = '=' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_11, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a11, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[137]);
	}
	
	(
		a12 = TEXT		
		{
			if (terminateParsing) {
				throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
			}
			if (element == null) {
				element = robotG.robot.RobotFactory.eINSTANCE.createBip();
				startIncompleteElement(element);
			}
			if (a12 != null) {
				robotG.resource.robot.IRobotTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
				tokenResolver.setOptions(getOptions());
				robotG.resource.robot.IRobotTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a12.getText(), element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.BIP__REPEAT), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a12).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a12).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a12).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a12).getStopIndex());
				}
				java.lang.Boolean resolved = (java.lang.Boolean) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.BIP__REPEAT), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_12, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a12, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[138]);
	}
	
	a13 = ')' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_13, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a13, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[139]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[140]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[141]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[142]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[143]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[144]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[145]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[146]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[147]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[148]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[149]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[150]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[151]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[152]);
	}
	
;

parse_robotG_robot_Display returns [robotG.robot.Display element = null]
@init{
}
:
	a0 = 'display' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[153]);
	}
	
	a1 = '(' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[154]);
	}
	
	a2 = 'msg' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_2, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[155]);
	}
	
	a3 = '=' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_3, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[156]);
	}
	
	(
		a4 = STRING_LITERAL		
		{
			if (terminateParsing) {
				throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
			}
			if (element == null) {
				element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
				startIncompleteElement(element);
			}
			if (a4 != null) {
				robotG.resource.robot.IRobotTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("STRING_LITERAL");
				tokenResolver.setOptions(getOptions());
				robotG.resource.robot.IRobotTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a4.getText(), element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__MSG), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a4).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__MSG), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_4, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a4, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[157]);
	}
	
	a5 = ',' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_5, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[158]);
	}
	
	a6 = 'duration' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_6, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a6, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[159]);
	}
	
	a7 = '=' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_7, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a7, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[160]);
	}
	
	(
		a8 = TEXT		
		{
			if (terminateParsing) {
				throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
			}
			if (element == null) {
				element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
				startIncompleteElement(element);
			}
			if (a8 != null) {
				robotG.resource.robot.IRobotTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
				tokenResolver.setOptions(getOptions());
				robotG.resource.robot.IRobotTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a8.getText(), element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__DURATION), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a8).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a8).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a8).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a8).getStopIndex());
				}
				java.lang.Integer resolved = (java.lang.Integer) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__DURATION), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_8, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a8, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[161]);
	}
	
	a9 = ',' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_9, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a9, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[162]);
	}
	
	a10 = 'line' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_10, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a10, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[163]);
	}
	
	a11 = '=' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_11, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a11, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[164]);
	}
	
	(
		a12 = TEXT		
		{
			if (terminateParsing) {
				throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
			}
			if (element == null) {
				element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
				startIncompleteElement(element);
			}
			if (a12 != null) {
				robotG.resource.robot.IRobotTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
				tokenResolver.setOptions(getOptions());
				robotG.resource.robot.IRobotTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a12.getText(), element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__LINE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a12).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a12).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a12).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a12).getStopIndex());
				}
				java.lang.Integer resolved = (java.lang.Integer) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__LINE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_12, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a12, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[165]);
	}
	
	a13 = ',' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_13, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a13, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[166]);
	}
	
	a14 = 'col' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_14, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a14, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[167]);
	}
	
	a15 = '=' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_15, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a15, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[168]);
	}
	
	(
		a16 = TEXT		
		{
			if (terminateParsing) {
				throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
			}
			if (element == null) {
				element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
				startIncompleteElement(element);
			}
			if (a16 != null) {
				robotG.resource.robot.IRobotTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
				tokenResolver.setOptions(getOptions());
				robotG.resource.robot.IRobotTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a16.getText(), element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__COL), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a16).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a16).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a16).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a16).getStopIndex());
				}
				java.lang.Integer resolved = (java.lang.Integer) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.DISPLAY__COL), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_16, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a16, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[169]);
	}
	
	a17 = ')' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_17, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a17, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[170]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[171]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[172]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[173]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[174]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[175]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[176]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[177]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[178]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[179]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[180]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[181]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[182]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[183]);
	}
	
;

parse_robotG_robot_Move returns [robotG.robot.Move element = null]
@init{
}
:
	a0 = 'move' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createMove();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_10_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[184]);
	}
	
	a1 = '(' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createMove();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_10_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[185]);
	}
	
	a2 = 'power' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createMove();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_10_0_0_2, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[186]);
	}
	
	a3 = '=' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createMove();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_10_0_0_3, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[187]);
	}
	
	(
		a4 = TEXT		
		{
			if (terminateParsing) {
				throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
			}
			if (element == null) {
				element = robotG.robot.RobotFactory.eINSTANCE.createMove();
				startIncompleteElement(element);
			}
			if (a4 != null) {
				robotG.resource.robot.IRobotTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
				tokenResolver.setOptions(getOptions());
				robotG.resource.robot.IRobotTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a4.getText(), element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.MOVE__POWER), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a4).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStopIndex());
				}
				java.lang.Integer resolved = (java.lang.Integer) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.MOVE__POWER), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_10_0_0_4, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a4, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[188]);
	}
	
	a5 = ')' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createMove();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_10_0_0_5, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[189]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[190]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[191]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[192]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[193]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[194]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[195]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[196]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[197]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[198]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[199]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[200]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[201]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[202]);
	}
	
;

parse_robotG_robot_SetTurnAngle returns [robotG.robot.SetTurnAngle element = null]
@init{
}
:
	a0 = 'setTurnAngle' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createSetTurnAngle();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_12_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[203]);
	}
	
	a1 = '(' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createSetTurnAngle();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_12_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[204]);
	}
	
	a2 = 'angle' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createSetTurnAngle();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_12_0_0_2, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[205]);
	}
	
	a3 = '=' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createSetTurnAngle();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_12_0_0_3, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[206]);
	}
	
	(
		a4 = TEXT		
		{
			if (terminateParsing) {
				throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
			}
			if (element == null) {
				element = robotG.robot.RobotFactory.eINSTANCE.createSetTurnAngle();
				startIncompleteElement(element);
			}
			if (a4 != null) {
				robotG.resource.robot.IRobotTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
				tokenResolver.setOptions(getOptions());
				robotG.resource.robot.IRobotTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a4.getText(), element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.SET_TURN_ANGLE__ANGLE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a4).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStopIndex());
				}
				java.lang.Integer resolved = (java.lang.Integer) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.SET_TURN_ANGLE__ANGLE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_12_0_0_4, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a4, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[207]);
	}
	
	a5 = ')' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createSetTurnAngle();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_12_0_0_5, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[208]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[209]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[210]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[211]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[212]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[213]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[214]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[215]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[216]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[217]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[218]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[219]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[220]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[221]);
	}
	
;

parse_robotG_robot_StopEngine returns [robotG.robot.StopEngine element = null]
@init{
}
:
	a0 = 'stopEngine' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createStopEngine();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_13_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[222]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[223]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[224]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[225]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[226]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[227]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[228]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[229]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[230]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[231]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[232]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[233]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[234]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[235]);
	}
	
;

parse_robotG_robot_Turn returns [robotG.robot.Turn element = null]
@init{
}
:
	a0 = 'turn' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[236]);
	}
	
	a1 = '(' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[237]);
	}
	
	a2 = 'power' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_2, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[238]);
	}
	
	a3 = '=' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_3, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[239]);
	}
	
	(
		a4 = TEXT		
		{
			if (terminateParsing) {
				throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
			}
			if (element == null) {
				element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
				startIncompleteElement(element);
			}
			if (a4 != null) {
				robotG.resource.robot.IRobotTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
				tokenResolver.setOptions(getOptions());
				robotG.resource.robot.IRobotTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a4.getText(), element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.TURN__POWER), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a4).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStopIndex());
				}
				java.lang.Integer resolved = (java.lang.Integer) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.TURN__POWER), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_4, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a4, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[240]);
	}
	
	a5 = ',' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_5, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[241]);
	}
	
	a6 = 'angle' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_6, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a6, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[242]);
	}
	
	a7 = '=' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_7, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a7, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[243]);
	}
	
	(
		a8 = TEXT		
		{
			if (terminateParsing) {
				throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
			}
			if (element == null) {
				element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
				startIncompleteElement(element);
			}
			if (a8 != null) {
				robotG.resource.robot.IRobotTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
				tokenResolver.setOptions(getOptions());
				robotG.resource.robot.IRobotTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a8.getText(), element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.TURN__ANGLE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a8).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a8).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a8).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a8).getStopIndex());
				}
				java.lang.Integer resolved = (java.lang.Integer) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.TURN__ANGLE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_8, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a8, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[244]);
	}
	
	a9 = ')' {
		if (element == null) {
			element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_9, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a9, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[245]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[246]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[247]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[248]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[249]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[250]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[251]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[252]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[253]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[254]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[255]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[256]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[257]);
		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[258]);
	}
	
;

parseop_ExprBool_level_1 returns [robotG.flow.ExprBool element = null]
@init{
}
:
	leftArg = parseop_ExprBool_level_2	((
		()
		{ element = null; }
		a0 = 'and' {
			if (element == null) {
				element = robotG.flow.FlowFactory.eINSTANCE.createAnd();
				startIncompleteElement(element);
			}
			collectHiddenTokens(element);
			retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_3_0_0_1, null, true);
			copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
		}
		{
			// expected elements (follow set)
			addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getAnd(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[259]);
			addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getAnd(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[260]);
			addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getAnd(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[261]);
		}
		
		rightArg = parseop_ExprBool_level_2		{
			if (terminateParsing) {
				throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
			}
			if (element == null) {
				element = robotG.flow.FlowFactory.eINSTANCE.createAnd();
				startIncompleteElement(element);
			}
			if (leftArg != null) {
				if (leftArg != null) {
					Object value = leftArg;
					element.eSet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.AND__FILS_GAUCHE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_3_0_0_0, leftArg, true);
				copyLocalizationInfos(leftArg, element);
			}
		}
		{
			if (terminateParsing) {
				throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
			}
			if (element == null) {
				element = robotG.flow.FlowFactory.eINSTANCE.createAnd();
				startIncompleteElement(element);
			}
			if (rightArg != null) {
				if (rightArg != null) {
					Object value = rightArg;
					element.eSet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.AND__FILS_DROIT), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_3_0_0_2, rightArg, true);
				copyLocalizationInfos(rightArg, element);
			}
		}
		{ leftArg = element; /* this may become an argument in the next iteration */ }
	)+ | /* epsilon */ { element = leftArg; }
	
)
;

parseop_ExprBool_level_2 returns [robotG.flow.ExprBool element = null]
@init{
}
:
leftArg = parseop_ExprBool_level_3((
	()
	{ element = null; }
	a0 = 'or' {
		if (element == null) {
			element = robotG.flow.FlowFactory.eINSTANCE.createOr();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_4_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getOr(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[262]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getOr(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[263]);
		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getOr(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[264]);
	}
	
	rightArg = parseop_ExprBool_level_3	{
		if (terminateParsing) {
			throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
		}
		if (element == null) {
			element = robotG.flow.FlowFactory.eINSTANCE.createOr();
			startIncompleteElement(element);
		}
		if (leftArg != null) {
			if (leftArg != null) {
				Object value = leftArg;
				element.eSet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.OR__FILS_GAUCHE), value);
				completedElement(value, true);
			}
			collectHiddenTokens(element);
			retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_4_0_0_0, leftArg, true);
			copyLocalizationInfos(leftArg, element);
		}
	}
	{
		if (terminateParsing) {
			throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
		}
		if (element == null) {
			element = robotG.flow.FlowFactory.eINSTANCE.createOr();
			startIncompleteElement(element);
		}
		if (rightArg != null) {
			if (rightArg != null) {
				Object value = rightArg;
				element.eSet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.OR__FILS_DROIT), value);
				completedElement(value, true);
			}
			collectHiddenTokens(element);
			retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_4_0_0_2, rightArg, true);
			copyLocalizationInfos(rightArg, element);
		}
	}
	{ leftArg = element; /* this may become an argument in the next iteration */ }
)+ | /* epsilon */ { element = leftArg; }

)
;

parseop_ExprBool_level_3 returns [robotG.flow.ExprBool element = null]
@init{
}
:
a0 = 'not' {
if (element == null) {
	element = robotG.flow.FlowFactory.eINSTANCE.createNot();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_5_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getNot(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[265]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getNot(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[266]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getNot(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[267]);
}

arg = parseop_ExprBool_level_4{
if (terminateParsing) {
	throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
}
if (element == null) {
	element = robotG.flow.FlowFactory.eINSTANCE.createNot();
	startIncompleteElement(element);
}
if (arg != null) {
	if (arg != null) {
		Object value = arg;
		element.eSet(element.eClass().getEStructuralFeature(robotG.flow.FlowPackage.NOT__EXPRESSION), value);
		completedElement(value, true);
	}
	collectHiddenTokens(element);
	retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_5_0_0_1, arg, true);
	copyLocalizationInfos(arg, element);
}
}
|

arg = parseop_ExprBool_level_4{ element = arg; }
;

parseop_ExprBool_level_4 returns [robotG.flow.ExprBool element = null]
@init{
}
:
c0 = parse_robotG_robot_HasTurned{ element = c0; /* this is a subclass or primitive expression choice */ }
|c1 = parse_robotG_robot_Obstacle{ element = c1; /* this is a subclass or primitive expression choice */ }
;

parse_robotG_robot_HasTurned returns [robotG.robot.HasTurned element = null]
@init{
}
:
a0 = 'hasTurned' {
if (element == null) {
	element = robotG.robot.RobotFactory.eINSTANCE.createHasTurned();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_9_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[268]);
}

a1 = '(' {
if (element == null) {
	element = robotG.robot.RobotFactory.eINSTANCE.createHasTurned();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_9_0_0_1, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
}
{
// expected elements (follow set)
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[269]);
}

a2 = 'angle' {
if (element == null) {
	element = robotG.robot.RobotFactory.eINSTANCE.createHasTurned();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_9_0_0_2, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
}
{
// expected elements (follow set)
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[270]);
}

a3 = '=' {
if (element == null) {
	element = robotG.robot.RobotFactory.eINSTANCE.createHasTurned();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_9_0_0_3, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
}
{
// expected elements (follow set)
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[271]);
}

(
a4 = TEXT
{
	if (terminateParsing) {
		throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
	}
	if (element == null) {
		element = robotG.robot.RobotFactory.eINSTANCE.createHasTurned();
		startIncompleteElement(element);
	}
	if (a4 != null) {
		robotG.resource.robot.IRobotTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
		tokenResolver.setOptions(getOptions());
		robotG.resource.robot.IRobotTokenResolveResult result = getFreshTokenResolveResult();
		tokenResolver.resolve(a4.getText(), element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.HAS_TURNED__ANGLE), result);
		Object resolvedObject = result.getResolvedToken();
		if (resolvedObject == null) {
			addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a4).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStopIndex());
		}
		java.lang.Integer resolved = (java.lang.Integer) resolvedObject;
		if (resolved != null) {
			Object value = resolved;
			element.eSet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.HAS_TURNED__ANGLE), value);
			completedElement(value, false);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_9_0_0_4, resolved, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a4, element);
	}
}
)
{
// expected elements (follow set)
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[272]);
}

a5 = ')' {
if (element == null) {
	element = robotG.robot.RobotFactory.eINSTANCE.createHasTurned();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_9_0_0_5, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
}
{
// expected elements (follow set)
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[273]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[274]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[275]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[276]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[277]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[278]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[279]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[280]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[281]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[282]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[283]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[284]);
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[285]);
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[286]);
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[287]);
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[288]);
}

;

parse_robotG_robot_Obstacle returns [robotG.robot.Obstacle element = null]
@init{
}
:
a0 = 'obstacle' {
if (element == null) {
	element = robotG.robot.RobotFactory.eINSTANCE.createObstacle();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_11_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[289]);
}

a1 = '(' {
if (element == null) {
	element = robotG.robot.RobotFactory.eINSTANCE.createObstacle();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_11_0_0_1, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
}
{
// expected elements (follow set)
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[290]);
}

a2 = 'distance' {
if (element == null) {
	element = robotG.robot.RobotFactory.eINSTANCE.createObstacle();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_11_0_0_2, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
}
{
// expected elements (follow set)
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[291]);
}

a3 = '=' {
if (element == null) {
	element = robotG.robot.RobotFactory.eINSTANCE.createObstacle();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_11_0_0_3, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
}
{
// expected elements (follow set)
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[292]);
}

(
a4 = TEXT
{
	if (terminateParsing) {
		throw new robotG.resource.robot.mopp.RobotTerminateParsingException();
	}
	if (element == null) {
		element = robotG.robot.RobotFactory.eINSTANCE.createObstacle();
		startIncompleteElement(element);
	}
	if (a4 != null) {
		robotG.resource.robot.IRobotTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
		tokenResolver.setOptions(getOptions());
		robotG.resource.robot.IRobotTokenResolveResult result = getFreshTokenResolveResult();
		tokenResolver.resolve(a4.getText(), element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.OBSTACLE__DISTANCE), result);
		Object resolvedObject = result.getResolvedToken();
		if (resolvedObject == null) {
			addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a4).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStopIndex());
		}
		java.lang.Integer resolved = (java.lang.Integer) resolvedObject;
		if (resolved != null) {
			Object value = resolved;
			element.eSet(element.eClass().getEStructuralFeature(robotG.robot.RobotPackage.OBSTACLE__DISTANCE), value);
			completedElement(value, false);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_11_0_0_4, resolved, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a4, element);
	}
}
)
{
// expected elements (follow set)
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[293]);
}

a5 = ')' {
if (element == null) {
	element = robotG.robot.RobotFactory.eINSTANCE.createObstacle();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_11_0_0_5, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
}
{
// expected elements (follow set)
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[294]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[295]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[296]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[297]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[298]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[299]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[300]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[301]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[302]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[303]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[304]);
addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getProgramme(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[305]);
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[306]);
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[307]);
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[308]);
addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[309]);
}

;

parse_robotG_flow_Expr returns [robotG.flow.Expr element = null]
:
c = parseop_Expr_level_4{ element = c; /* this rule is an expression root */ }

;

parse_robotG_flow_ExprBool returns [robotG.flow.ExprBool element = null]
:
c = parseop_ExprBool_level_1{ element = c; /* this rule is an expression root */ }

;

STRING_LITERAL:
('"'('\\'('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')|('\\''u'('0'..'9'|'a'..'f'|'A'..'F')('0'..'9'|'a'..'f'|'A'..'F')('0'..'9'|'a'..'f'|'A'..'F')('0'..'9'|'a'..'f'|'A'..'F'))|'\\'('0'..'7')|~('\\'|'"'))*'"')
;
TEXT:
(('A'..'Z' | 'a'..'z' | '0'..'9' | '_' | '-' )+)
;
WHITESPACE:
((' ' | '\t' | '\f'))
{ _channel = 99; }
;
LINEBREAK:
(('\r\n' | '\r' | '\n'))
{ _channel = 99; }
;

