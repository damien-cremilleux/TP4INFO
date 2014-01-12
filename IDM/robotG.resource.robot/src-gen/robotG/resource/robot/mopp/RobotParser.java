// $ANTLR 3.4

	package robotG.resource.robot.mopp;


import org.antlr.runtime3_4_0.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class RobotParser extends RobotANTLRParserBase {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "LINEBREAK", "STRING_LITERAL", "TEXT", "WHITESPACE", "'('", "')'", "','", "'='", "'and'", "'angle'", "'bip'", "'col'", "'display'", "'distance'", "'do'", "'duration'", "'end'", "'hasTurned'", "'if'", "'line'", "'move'", "'msg'", "'not'", "'obstacle'", "'or'", "'power'", "'repeat'", "'setTurnAngle'", "'stopEngine'", "'stopProgram'", "'then'", "'turn'", "'while'"
    };

    public static final int EOF=-1;
    public static final int T__8=8;
    public static final int T__9=9;
    public static final int T__10=10;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int LINEBREAK=4;
    public static final int STRING_LITERAL=5;
    public static final int TEXT=6;
    public static final int WHITESPACE=7;

    // delegates
    public RobotANTLRParserBase[] getDelegates() {
        return new RobotANTLRParserBase[] {};
    }

    // delegators


    public RobotParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public RobotParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
        this.state.initializeRuleMemo(37 + 1);
         

    }

    public String[] getTokenNames() { return RobotParser.tokenNames; }
    public String getGrammarFileName() { return "Robot.g"; }


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
    	



    // $ANTLR start "start"
    // Robot.g:499:1: start returns [ org.eclipse.emf.ecore.EObject element = null] : (c0= parse_robotG_flow_Programme ) EOF ;
    public final org.eclipse.emf.ecore.EObject start() throws RecognitionException {
        org.eclipse.emf.ecore.EObject element =  null;

        int start_StartIndex = input.index();

        robotG.flow.Programme c0 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return element; }

            // Robot.g:500:2: ( (c0= parse_robotG_flow_Programme ) EOF )
            // Robot.g:501:2: (c0= parse_robotG_flow_Programme ) EOF
            {
            if ( state.backtracking==0 ) {
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

            // Robot.g:517:2: (c0= parse_robotG_flow_Programme )
            // Robot.g:518:3: c0= parse_robotG_flow_Programme
            {
            pushFollow(FOLLOW_parse_robotG_flow_Programme_in_start82);
            c0=parse_robotG_flow_Programme();

            state._fsp--;
            if (state.failed) return element;

            if ( state.backtracking==0 ) { element = c0; }

            }


            match(input,EOF,FOLLOW_EOF_in_start89); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		retrieveLayoutInformation(element, null, null, false);
            	}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 1, start_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "start"



    // $ANTLR start "parse_robotG_flow_Programme"
    // Robot.g:526:1: parse_robotG_flow_Programme returns [robotG.flow.Programme element = null] : ( (a0_0= parse_robotG_flow_Expr ) )* ;
    public final robotG.flow.Programme parse_robotG_flow_Programme() throws RecognitionException {
        robotG.flow.Programme element =  null;

        int parse_robotG_flow_Programme_StartIndex = input.index();

        robotG.flow.Expr a0_0 =null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return element; }

            // Robot.g:529:2: ( ( (a0_0= parse_robotG_flow_Expr ) )* )
            // Robot.g:530:2: ( (a0_0= parse_robotG_flow_Expr ) )*
            {
            // Robot.g:530:2: ( (a0_0= parse_robotG_flow_Expr ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==14||LA1_0==16||LA1_0==22||LA1_0==24||(LA1_0 >= 31 && LA1_0 <= 33)||(LA1_0 >= 35 && LA1_0 <= 36)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Robot.g:531:3: (a0_0= parse_robotG_flow_Expr )
            	    {
            	    // Robot.g:531:3: (a0_0= parse_robotG_flow_Expr )
            	    // Robot.g:532:4: a0_0= parse_robotG_flow_Expr
            	    {
            	    pushFollow(FOLLOW_parse_robotG_flow_Expr_in_parse_robotG_flow_Programme124);
            	    a0_0=parse_robotG_flow_Expr();

            	    state._fsp--;
            	    if (state.failed) return element;

            	    if ( state.backtracking==0 ) {
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

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            if ( state.backtracking==0 ) {
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

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 2, parse_robotG_flow_Programme_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parse_robotG_flow_Programme"



    // $ANTLR start "parseop_Expr_level_4"
    // Robot.g:571:1: parseop_Expr_level_4 returns [robotG.flow.Expr element = null] : (c0= parse_robotG_flow_While |c1= parse_robotG_flow_If |c2= parse_robotG_flow_StopProgram |c3= parse_robotG_robot_Bip |c4= parse_robotG_robot_Display |c5= parse_robotG_robot_Move |c6= parse_robotG_robot_SetTurnAngle |c7= parse_robotG_robot_StopEngine |c8= parse_robotG_robot_Turn );
    public final robotG.flow.Expr parseop_Expr_level_4() throws RecognitionException {
        robotG.flow.Expr element =  null;

        int parseop_Expr_level_4_StartIndex = input.index();

        robotG.flow.While c0 =null;

        robotG.flow.If c1 =null;

        robotG.flow.StopProgram c2 =null;

        robotG.robot.Bip c3 =null;

        robotG.robot.Display c4 =null;

        robotG.robot.Move c5 =null;

        robotG.robot.SetTurnAngle c6 =null;

        robotG.robot.StopEngine c7 =null;

        robotG.robot.Turn c8 =null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return element; }

            // Robot.g:574:2: (c0= parse_robotG_flow_While |c1= parse_robotG_flow_If |c2= parse_robotG_flow_StopProgram |c3= parse_robotG_robot_Bip |c4= parse_robotG_robot_Display |c5= parse_robotG_robot_Move |c6= parse_robotG_robot_SetTurnAngle |c7= parse_robotG_robot_StopEngine |c8= parse_robotG_robot_Turn )
            int alt2=9;
            switch ( input.LA(1) ) {
            case 36:
                {
                alt2=1;
                }
                break;
            case 22:
                {
                alt2=2;
                }
                break;
            case 33:
                {
                alt2=3;
                }
                break;
            case 14:
                {
                alt2=4;
                }
                break;
            case 16:
                {
                alt2=5;
                }
                break;
            case 24:
                {
                alt2=6;
                }
                break;
            case 31:
                {
                alt2=7;
                }
                break;
            case 32:
                {
                alt2=8;
                }
                break;
            case 35:
                {
                alt2=9;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return element;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // Robot.g:575:2: c0= parse_robotG_flow_While
                    {
                    pushFollow(FOLLOW_parse_robotG_flow_While_in_parseop_Expr_level_4165);
                    c0=parse_robotG_flow_While();

                    state._fsp--;
                    if (state.failed) return element;

                    if ( state.backtracking==0 ) { element = c0; /* this is a subclass or primitive expression choice */ }

                    }
                    break;
                case 2 :
                    // Robot.g:576:4: c1= parse_robotG_flow_If
                    {
                    pushFollow(FOLLOW_parse_robotG_flow_If_in_parseop_Expr_level_4175);
                    c1=parse_robotG_flow_If();

                    state._fsp--;
                    if (state.failed) return element;

                    if ( state.backtracking==0 ) { element = c1; /* this is a subclass or primitive expression choice */ }

                    }
                    break;
                case 3 :
                    // Robot.g:577:4: c2= parse_robotG_flow_StopProgram
                    {
                    pushFollow(FOLLOW_parse_robotG_flow_StopProgram_in_parseop_Expr_level_4185);
                    c2=parse_robotG_flow_StopProgram();

                    state._fsp--;
                    if (state.failed) return element;

                    if ( state.backtracking==0 ) { element = c2; /* this is a subclass or primitive expression choice */ }

                    }
                    break;
                case 4 :
                    // Robot.g:578:4: c3= parse_robotG_robot_Bip
                    {
                    pushFollow(FOLLOW_parse_robotG_robot_Bip_in_parseop_Expr_level_4195);
                    c3=parse_robotG_robot_Bip();

                    state._fsp--;
                    if (state.failed) return element;

                    if ( state.backtracking==0 ) { element = c3; /* this is a subclass or primitive expression choice */ }

                    }
                    break;
                case 5 :
                    // Robot.g:579:4: c4= parse_robotG_robot_Display
                    {
                    pushFollow(FOLLOW_parse_robotG_robot_Display_in_parseop_Expr_level_4205);
                    c4=parse_robotG_robot_Display();

                    state._fsp--;
                    if (state.failed) return element;

                    if ( state.backtracking==0 ) { element = c4; /* this is a subclass or primitive expression choice */ }

                    }
                    break;
                case 6 :
                    // Robot.g:580:4: c5= parse_robotG_robot_Move
                    {
                    pushFollow(FOLLOW_parse_robotG_robot_Move_in_parseop_Expr_level_4215);
                    c5=parse_robotG_robot_Move();

                    state._fsp--;
                    if (state.failed) return element;

                    if ( state.backtracking==0 ) { element = c5; /* this is a subclass or primitive expression choice */ }

                    }
                    break;
                case 7 :
                    // Robot.g:581:4: c6= parse_robotG_robot_SetTurnAngle
                    {
                    pushFollow(FOLLOW_parse_robotG_robot_SetTurnAngle_in_parseop_Expr_level_4225);
                    c6=parse_robotG_robot_SetTurnAngle();

                    state._fsp--;
                    if (state.failed) return element;

                    if ( state.backtracking==0 ) { element = c6; /* this is a subclass or primitive expression choice */ }

                    }
                    break;
                case 8 :
                    // Robot.g:582:4: c7= parse_robotG_robot_StopEngine
                    {
                    pushFollow(FOLLOW_parse_robotG_robot_StopEngine_in_parseop_Expr_level_4235);
                    c7=parse_robotG_robot_StopEngine();

                    state._fsp--;
                    if (state.failed) return element;

                    if ( state.backtracking==0 ) { element = c7; /* this is a subclass or primitive expression choice */ }

                    }
                    break;
                case 9 :
                    // Robot.g:583:4: c8= parse_robotG_robot_Turn
                    {
                    pushFollow(FOLLOW_parse_robotG_robot_Turn_in_parseop_Expr_level_4245);
                    c8=parse_robotG_robot_Turn();

                    state._fsp--;
                    if (state.failed) return element;

                    if ( state.backtracking==0 ) { element = c8; /* this is a subclass or primitive expression choice */ }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 3, parseop_Expr_level_4_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parseop_Expr_level_4"



    // $ANTLR start "parse_robotG_flow_While"
    // Robot.g:586:1: parse_robotG_flow_While returns [robotG.flow.While element = null] : a0= 'while' (a1_0= parse_robotG_flow_ExprBool ) a2= 'do' ( (a3_0= parse_robotG_flow_Expr ) )* a4= 'end' ;
    public final robotG.flow.While parse_robotG_flow_While() throws RecognitionException {
        robotG.flow.While element =  null;

        int parse_robotG_flow_While_StartIndex = input.index();

        Token a0=null;
        Token a2=null;
        Token a4=null;
        robotG.flow.ExprBool a1_0 =null;

        robotG.flow.Expr a3_0 =null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return element; }

            // Robot.g:589:2: (a0= 'while' (a1_0= parse_robotG_flow_ExprBool ) a2= 'do' ( (a3_0= parse_robotG_flow_Expr ) )* a4= 'end' )
            // Robot.g:590:2: a0= 'while' (a1_0= parse_robotG_flow_ExprBool ) a2= 'do' ( (a3_0= parse_robotG_flow_Expr ) )* a4= 'end'
            {
            a0=(Token)match(input,36,FOLLOW_36_in_parse_robotG_flow_While268); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.flow.FlowFactory.eINSTANCE.createWhile();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_1_0_0_0, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[24]);
            		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[25]);
            		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getWhile(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[26]);
            	}

            // Robot.g:606:2: (a1_0= parse_robotG_flow_ExprBool )
            // Robot.g:607:3: a1_0= parse_robotG_flow_ExprBool
            {
            pushFollow(FOLLOW_parse_robotG_flow_ExprBool_in_parse_robotG_flow_While286);
            a1_0=parse_robotG_flow_ExprBool();

            state._fsp--;
            if (state.failed) return element;

            if ( state.backtracking==0 ) {
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

            }


            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[27]);
            	}

            a2=(Token)match(input,18,FOLLOW_18_in_parse_robotG_flow_While304); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.flow.FlowFactory.eINSTANCE.createWhile();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_1_0_0_2, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
            	}

            if ( state.backtracking==0 ) {
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

            // Robot.g:658:2: ( (a3_0= parse_robotG_flow_Expr ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==14||LA3_0==16||LA3_0==22||LA3_0==24||(LA3_0 >= 31 && LA3_0 <= 33)||(LA3_0 >= 35 && LA3_0 <= 36)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Robot.g:659:3: (a3_0= parse_robotG_flow_Expr )
            	    {
            	    // Robot.g:659:3: (a3_0= parse_robotG_flow_Expr )
            	    // Robot.g:660:4: a3_0= parse_robotG_flow_Expr
            	    {
            	    pushFollow(FOLLOW_parse_robotG_flow_Expr_in_parse_robotG_flow_While327);
            	    a3_0=parse_robotG_flow_Expr();

            	    state._fsp--;
            	    if (state.failed) return element;

            	    if ( state.backtracking==0 ) {
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

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            if ( state.backtracking==0 ) {
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

            a4=(Token)match(input,20,FOLLOW_20_in_parse_robotG_flow_While353); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.flow.FlowFactory.eINSTANCE.createWhile();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_1_0_0_4, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a4, element);
            	}

            if ( state.backtracking==0 ) {
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

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 4, parse_robotG_flow_While_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parse_robotG_flow_While"



    // $ANTLR start "parse_robotG_flow_If"
    // Robot.g:727:1: parse_robotG_flow_If returns [robotG.flow.If element = null] : a0= 'if' (a1_0= parse_robotG_flow_ExprBool ) a2= 'then' ( (a3_0= parse_robotG_flow_Expr ) )* a4= 'end' ;
    public final robotG.flow.If parse_robotG_flow_If() throws RecognitionException {
        robotG.flow.If element =  null;

        int parse_robotG_flow_If_StartIndex = input.index();

        Token a0=null;
        Token a2=null;
        Token a4=null;
        robotG.flow.ExprBool a1_0 =null;

        robotG.flow.Expr a3_0 =null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return element; }

            // Robot.g:730:2: (a0= 'if' (a1_0= parse_robotG_flow_ExprBool ) a2= 'then' ( (a3_0= parse_robotG_flow_Expr ) )* a4= 'end' )
            // Robot.g:731:2: a0= 'if' (a1_0= parse_robotG_flow_ExprBool ) a2= 'then' ( (a3_0= parse_robotG_flow_Expr ) )* a4= 'end'
            {
            a0=(Token)match(input,22,FOLLOW_22_in_parse_robotG_flow_If382); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.flow.FlowFactory.eINSTANCE.createIf();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_2_0_0_0, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[68]);
            		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[69]);
            		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getIf(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[70]);
            	}

            // Robot.g:747:2: (a1_0= parse_robotG_flow_ExprBool )
            // Robot.g:748:3: a1_0= parse_robotG_flow_ExprBool
            {
            pushFollow(FOLLOW_parse_robotG_flow_ExprBool_in_parse_robotG_flow_If400);
            a1_0=parse_robotG_flow_ExprBool();

            state._fsp--;
            if (state.failed) return element;

            if ( state.backtracking==0 ) {
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

            }


            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[71]);
            	}

            a2=(Token)match(input,34,FOLLOW_34_in_parse_robotG_flow_If418); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.flow.FlowFactory.eINSTANCE.createIf();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_2_0_0_2, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
            	}

            if ( state.backtracking==0 ) {
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

            // Robot.g:799:2: ( (a3_0= parse_robotG_flow_Expr ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==14||LA4_0==16||LA4_0==22||LA4_0==24||(LA4_0 >= 31 && LA4_0 <= 33)||(LA4_0 >= 35 && LA4_0 <= 36)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // Robot.g:800:3: (a3_0= parse_robotG_flow_Expr )
            	    {
            	    // Robot.g:800:3: (a3_0= parse_robotG_flow_Expr )
            	    // Robot.g:801:4: a3_0= parse_robotG_flow_Expr
            	    {
            	    pushFollow(FOLLOW_parse_robotG_flow_Expr_in_parse_robotG_flow_If441);
            	    a3_0=parse_robotG_flow_Expr();

            	    state._fsp--;
            	    if (state.failed) return element;

            	    if ( state.backtracking==0 ) {
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

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            if ( state.backtracking==0 ) {
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

            a4=(Token)match(input,20,FOLLOW_20_in_parse_robotG_flow_If467); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.flow.FlowFactory.eINSTANCE.createIf();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_2_0_0_4, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a4, element);
            	}

            if ( state.backtracking==0 ) {
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

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 5, parse_robotG_flow_If_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parse_robotG_flow_If"



    // $ANTLR start "parse_robotG_flow_StopProgram"
    // Robot.g:868:1: parse_robotG_flow_StopProgram returns [robotG.flow.StopProgram element = null] : a0= 'stopProgram' ;
    public final robotG.flow.StopProgram parse_robotG_flow_StopProgram() throws RecognitionException {
        robotG.flow.StopProgram element =  null;

        int parse_robotG_flow_StopProgram_StartIndex = input.index();

        Token a0=null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return element; }

            // Robot.g:871:2: (a0= 'stopProgram' )
            // Robot.g:872:2: a0= 'stopProgram'
            {
            a0=(Token)match(input,33,FOLLOW_33_in_parse_robotG_flow_StopProgram496); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.flow.FlowFactory.eINSTANCE.createStopProgram();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_6_0_0_0, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
            	}

            if ( state.backtracking==0 ) {
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

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 6, parse_robotG_flow_StopProgram_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parse_robotG_flow_StopProgram"



    // $ANTLR start "parse_robotG_robot_Bip"
    // Robot.g:901:1: parse_robotG_robot_Bip returns [robotG.robot.Bip element = null] : a0= 'bip' a1= '(' a2= 'duration' a3= '=' (a4= TEXT ) a5= ',' a6= 'power' a7= '=' (a8= TEXT ) a9= ',' a10= 'repeat' a11= '=' (a12= TEXT ) a13= ')' ;
    public final robotG.robot.Bip parse_robotG_robot_Bip() throws RecognitionException {
        robotG.robot.Bip element =  null;

        int parse_robotG_robot_Bip_StartIndex = input.index();

        Token a0=null;
        Token a1=null;
        Token a2=null;
        Token a3=null;
        Token a4=null;
        Token a5=null;
        Token a6=null;
        Token a7=null;
        Token a8=null;
        Token a9=null;
        Token a10=null;
        Token a11=null;
        Token a12=null;
        Token a13=null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return element; }

            // Robot.g:904:2: (a0= 'bip' a1= '(' a2= 'duration' a3= '=' (a4= TEXT ) a5= ',' a6= 'power' a7= '=' (a8= TEXT ) a9= ',' a10= 'repeat' a11= '=' (a12= TEXT ) a13= ')' )
            // Robot.g:905:2: a0= 'bip' a1= '(' a2= 'duration' a3= '=' (a4= TEXT ) a5= ',' a6= 'power' a7= '=' (a8= TEXT ) a9= ',' a10= 'repeat' a11= '=' (a12= TEXT ) a13= ')'
            {
            a0=(Token)match(input,14,FOLLOW_14_in_parse_robotG_robot_Bip525); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_0, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[126]);
            	}

            a1=(Token)match(input,8,FOLLOW_8_in_parse_robotG_robot_Bip539); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_1, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[127]);
            	}

            a2=(Token)match(input,19,FOLLOW_19_in_parse_robotG_robot_Bip553); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_2, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[128]);
            	}

            a3=(Token)match(input,11,FOLLOW_11_in_parse_robotG_robot_Bip567); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_3, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[129]);
            	}

            // Robot.g:961:2: (a4= TEXT )
            // Robot.g:962:3: a4= TEXT
            {
            a4=(Token)match(input,TEXT,FOLLOW_TEXT_in_parse_robotG_robot_Bip585); if (state.failed) return element;

            if ( state.backtracking==0 ) {
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

            }


            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[130]);
            	}

            a5=(Token)match(input,10,FOLLOW_10_in_parse_robotG_robot_Bip606); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_5, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[131]);
            	}

            a6=(Token)match(input,29,FOLLOW_29_in_parse_robotG_robot_Bip620); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_6, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a6, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[132]);
            	}

            a7=(Token)match(input,11,FOLLOW_11_in_parse_robotG_robot_Bip634); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_7, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a7, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[133]);
            	}

            // Robot.g:1039:2: (a8= TEXT )
            // Robot.g:1040:3: a8= TEXT
            {
            a8=(Token)match(input,TEXT,FOLLOW_TEXT_in_parse_robotG_robot_Bip652); if (state.failed) return element;

            if ( state.backtracking==0 ) {
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

            }


            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[134]);
            	}

            a9=(Token)match(input,10,FOLLOW_10_in_parse_robotG_robot_Bip673); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_9, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a9, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[135]);
            	}

            a10=(Token)match(input,30,FOLLOW_30_in_parse_robotG_robot_Bip687); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_10, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a10, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[136]);
            	}

            a11=(Token)match(input,11,FOLLOW_11_in_parse_robotG_robot_Bip701); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_11, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a11, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[137]);
            	}

            // Robot.g:1117:2: (a12= TEXT )
            // Robot.g:1118:3: a12= TEXT
            {
            a12=(Token)match(input,TEXT,FOLLOW_TEXT_in_parse_robotG_robot_Bip719); if (state.failed) return element;

            if ( state.backtracking==0 ) {
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

            }


            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[138]);
            	}

            a13=(Token)match(input,9,FOLLOW_9_in_parse_robotG_robot_Bip740); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createBip();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_7_0_0_13, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a13, element);
            	}

            if ( state.backtracking==0 ) {
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

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 7, parse_robotG_robot_Bip_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parse_robotG_robot_Bip"



    // $ANTLR start "parse_robotG_robot_Display"
    // Robot.g:1182:1: parse_robotG_robot_Display returns [robotG.robot.Display element = null] : a0= 'display' a1= '(' a2= 'msg' a3= '=' (a4= STRING_LITERAL ) a5= ',' a6= 'duration' a7= '=' (a8= TEXT ) a9= ',' a10= 'line' a11= '=' (a12= TEXT ) a13= ',' a14= 'col' a15= '=' (a16= TEXT ) a17= ')' ;
    public final robotG.robot.Display parse_robotG_robot_Display() throws RecognitionException {
        robotG.robot.Display element =  null;

        int parse_robotG_robot_Display_StartIndex = input.index();

        Token a0=null;
        Token a1=null;
        Token a2=null;
        Token a3=null;
        Token a4=null;
        Token a5=null;
        Token a6=null;
        Token a7=null;
        Token a8=null;
        Token a9=null;
        Token a10=null;
        Token a11=null;
        Token a12=null;
        Token a13=null;
        Token a14=null;
        Token a15=null;
        Token a16=null;
        Token a17=null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return element; }

            // Robot.g:1185:2: (a0= 'display' a1= '(' a2= 'msg' a3= '=' (a4= STRING_LITERAL ) a5= ',' a6= 'duration' a7= '=' (a8= TEXT ) a9= ',' a10= 'line' a11= '=' (a12= TEXT ) a13= ',' a14= 'col' a15= '=' (a16= TEXT ) a17= ')' )
            // Robot.g:1186:2: a0= 'display' a1= '(' a2= 'msg' a3= '=' (a4= STRING_LITERAL ) a5= ',' a6= 'duration' a7= '=' (a8= TEXT ) a9= ',' a10= 'line' a11= '=' (a12= TEXT ) a13= ',' a14= 'col' a15= '=' (a16= TEXT ) a17= ')'
            {
            a0=(Token)match(input,16,FOLLOW_16_in_parse_robotG_robot_Display769); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_0, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[153]);
            	}

            a1=(Token)match(input,8,FOLLOW_8_in_parse_robotG_robot_Display783); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_1, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[154]);
            	}

            a2=(Token)match(input,25,FOLLOW_25_in_parse_robotG_robot_Display797); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_2, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[155]);
            	}

            a3=(Token)match(input,11,FOLLOW_11_in_parse_robotG_robot_Display811); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_3, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[156]);
            	}

            // Robot.g:1242:2: (a4= STRING_LITERAL )
            // Robot.g:1243:3: a4= STRING_LITERAL
            {
            a4=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_parse_robotG_robot_Display829); if (state.failed) return element;

            if ( state.backtracking==0 ) {
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

            }


            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[157]);
            	}

            a5=(Token)match(input,10,FOLLOW_10_in_parse_robotG_robot_Display850); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_5, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[158]);
            	}

            a6=(Token)match(input,19,FOLLOW_19_in_parse_robotG_robot_Display864); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_6, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a6, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[159]);
            	}

            a7=(Token)match(input,11,FOLLOW_11_in_parse_robotG_robot_Display878); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_7, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a7, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[160]);
            	}

            // Robot.g:1320:2: (a8= TEXT )
            // Robot.g:1321:3: a8= TEXT
            {
            a8=(Token)match(input,TEXT,FOLLOW_TEXT_in_parse_robotG_robot_Display896); if (state.failed) return element;

            if ( state.backtracking==0 ) {
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

            }


            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[161]);
            	}

            a9=(Token)match(input,10,FOLLOW_10_in_parse_robotG_robot_Display917); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_9, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a9, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[162]);
            	}

            a10=(Token)match(input,23,FOLLOW_23_in_parse_robotG_robot_Display931); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_10, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a10, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[163]);
            	}

            a11=(Token)match(input,11,FOLLOW_11_in_parse_robotG_robot_Display945); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_11, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a11, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[164]);
            	}

            // Robot.g:1398:2: (a12= TEXT )
            // Robot.g:1399:3: a12= TEXT
            {
            a12=(Token)match(input,TEXT,FOLLOW_TEXT_in_parse_robotG_robot_Display963); if (state.failed) return element;

            if ( state.backtracking==0 ) {
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

            }


            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[165]);
            	}

            a13=(Token)match(input,10,FOLLOW_10_in_parse_robotG_robot_Display984); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_13, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a13, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[166]);
            	}

            a14=(Token)match(input,15,FOLLOW_15_in_parse_robotG_robot_Display998); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_14, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a14, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[167]);
            	}

            a15=(Token)match(input,11,FOLLOW_11_in_parse_robotG_robot_Display1012); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_15, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a15, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[168]);
            	}

            // Robot.g:1476:2: (a16= TEXT )
            // Robot.g:1477:3: a16= TEXT
            {
            a16=(Token)match(input,TEXT,FOLLOW_TEXT_in_parse_robotG_robot_Display1030); if (state.failed) return element;

            if ( state.backtracking==0 ) {
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

            }


            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[169]);
            	}

            a17=(Token)match(input,9,FOLLOW_9_in_parse_robotG_robot_Display1051); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createDisplay();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_8_0_0_17, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a17, element);
            	}

            if ( state.backtracking==0 ) {
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

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 8, parse_robotG_robot_Display_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parse_robotG_robot_Display"



    // $ANTLR start "parse_robotG_robot_Move"
    // Robot.g:1541:1: parse_robotG_robot_Move returns [robotG.robot.Move element = null] : a0= 'move' a1= '(' a2= 'power' a3= '=' (a4= TEXT ) a5= ')' ;
    public final robotG.robot.Move parse_robotG_robot_Move() throws RecognitionException {
        robotG.robot.Move element =  null;

        int parse_robotG_robot_Move_StartIndex = input.index();

        Token a0=null;
        Token a1=null;
        Token a2=null;
        Token a3=null;
        Token a4=null;
        Token a5=null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return element; }

            // Robot.g:1544:2: (a0= 'move' a1= '(' a2= 'power' a3= '=' (a4= TEXT ) a5= ')' )
            // Robot.g:1545:2: a0= 'move' a1= '(' a2= 'power' a3= '=' (a4= TEXT ) a5= ')'
            {
            a0=(Token)match(input,24,FOLLOW_24_in_parse_robotG_robot_Move1080); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createMove();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_10_0_0_0, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[184]);
            	}

            a1=(Token)match(input,8,FOLLOW_8_in_parse_robotG_robot_Move1094); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createMove();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_10_0_0_1, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[185]);
            	}

            a2=(Token)match(input,29,FOLLOW_29_in_parse_robotG_robot_Move1108); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createMove();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_10_0_0_2, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[186]);
            	}

            a3=(Token)match(input,11,FOLLOW_11_in_parse_robotG_robot_Move1122); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createMove();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_10_0_0_3, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[187]);
            	}

            // Robot.g:1601:2: (a4= TEXT )
            // Robot.g:1602:3: a4= TEXT
            {
            a4=(Token)match(input,TEXT,FOLLOW_TEXT_in_parse_robotG_robot_Move1140); if (state.failed) return element;

            if ( state.backtracking==0 ) {
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

            }


            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[188]);
            	}

            a5=(Token)match(input,9,FOLLOW_9_in_parse_robotG_robot_Move1161); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createMove();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_10_0_0_5, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
            	}

            if ( state.backtracking==0 ) {
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

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 9, parse_robotG_robot_Move_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parse_robotG_robot_Move"



    // $ANTLR start "parse_robotG_robot_SetTurnAngle"
    // Robot.g:1666:1: parse_robotG_robot_SetTurnAngle returns [robotG.robot.SetTurnAngle element = null] : a0= 'setTurnAngle' a1= '(' a2= 'angle' a3= '=' (a4= TEXT ) a5= ')' ;
    public final robotG.robot.SetTurnAngle parse_robotG_robot_SetTurnAngle() throws RecognitionException {
        robotG.robot.SetTurnAngle element =  null;

        int parse_robotG_robot_SetTurnAngle_StartIndex = input.index();

        Token a0=null;
        Token a1=null;
        Token a2=null;
        Token a3=null;
        Token a4=null;
        Token a5=null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return element; }

            // Robot.g:1669:2: (a0= 'setTurnAngle' a1= '(' a2= 'angle' a3= '=' (a4= TEXT ) a5= ')' )
            // Robot.g:1670:2: a0= 'setTurnAngle' a1= '(' a2= 'angle' a3= '=' (a4= TEXT ) a5= ')'
            {
            a0=(Token)match(input,31,FOLLOW_31_in_parse_robotG_robot_SetTurnAngle1190); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createSetTurnAngle();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_12_0_0_0, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[203]);
            	}

            a1=(Token)match(input,8,FOLLOW_8_in_parse_robotG_robot_SetTurnAngle1204); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createSetTurnAngle();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_12_0_0_1, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[204]);
            	}

            a2=(Token)match(input,13,FOLLOW_13_in_parse_robotG_robot_SetTurnAngle1218); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createSetTurnAngle();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_12_0_0_2, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[205]);
            	}

            a3=(Token)match(input,11,FOLLOW_11_in_parse_robotG_robot_SetTurnAngle1232); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createSetTurnAngle();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_12_0_0_3, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[206]);
            	}

            // Robot.g:1726:2: (a4= TEXT )
            // Robot.g:1727:3: a4= TEXT
            {
            a4=(Token)match(input,TEXT,FOLLOW_TEXT_in_parse_robotG_robot_SetTurnAngle1250); if (state.failed) return element;

            if ( state.backtracking==0 ) {
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

            }


            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[207]);
            	}

            a5=(Token)match(input,9,FOLLOW_9_in_parse_robotG_robot_SetTurnAngle1271); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createSetTurnAngle();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_12_0_0_5, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
            	}

            if ( state.backtracking==0 ) {
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

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 10, parse_robotG_robot_SetTurnAngle_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parse_robotG_robot_SetTurnAngle"



    // $ANTLR start "parse_robotG_robot_StopEngine"
    // Robot.g:1791:1: parse_robotG_robot_StopEngine returns [robotG.robot.StopEngine element = null] : a0= 'stopEngine' ;
    public final robotG.robot.StopEngine parse_robotG_robot_StopEngine() throws RecognitionException {
        robotG.robot.StopEngine element =  null;

        int parse_robotG_robot_StopEngine_StartIndex = input.index();

        Token a0=null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return element; }

            // Robot.g:1794:2: (a0= 'stopEngine' )
            // Robot.g:1795:2: a0= 'stopEngine'
            {
            a0=(Token)match(input,32,FOLLOW_32_in_parse_robotG_robot_StopEngine1300); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createStopEngine();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_13_0_0_0, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
            	}

            if ( state.backtracking==0 ) {
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

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 11, parse_robotG_robot_StopEngine_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parse_robotG_robot_StopEngine"



    // $ANTLR start "parse_robotG_robot_Turn"
    // Robot.g:1824:1: parse_robotG_robot_Turn returns [robotG.robot.Turn element = null] : a0= 'turn' a1= '(' a2= 'power' a3= '=' (a4= TEXT ) a5= ',' a6= 'angle' a7= '=' (a8= TEXT ) a9= ')' ;
    public final robotG.robot.Turn parse_robotG_robot_Turn() throws RecognitionException {
        robotG.robot.Turn element =  null;

        int parse_robotG_robot_Turn_StartIndex = input.index();

        Token a0=null;
        Token a1=null;
        Token a2=null;
        Token a3=null;
        Token a4=null;
        Token a5=null;
        Token a6=null;
        Token a7=null;
        Token a8=null;
        Token a9=null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return element; }

            // Robot.g:1827:2: (a0= 'turn' a1= '(' a2= 'power' a3= '=' (a4= TEXT ) a5= ',' a6= 'angle' a7= '=' (a8= TEXT ) a9= ')' )
            // Robot.g:1828:2: a0= 'turn' a1= '(' a2= 'power' a3= '=' (a4= TEXT ) a5= ',' a6= 'angle' a7= '=' (a8= TEXT ) a9= ')'
            {
            a0=(Token)match(input,35,FOLLOW_35_in_parse_robotG_robot_Turn1329); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_0, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[236]);
            	}

            a1=(Token)match(input,8,FOLLOW_8_in_parse_robotG_robot_Turn1343); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_1, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[237]);
            	}

            a2=(Token)match(input,29,FOLLOW_29_in_parse_robotG_robot_Turn1357); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_2, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[238]);
            	}

            a3=(Token)match(input,11,FOLLOW_11_in_parse_robotG_robot_Turn1371); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_3, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[239]);
            	}

            // Robot.g:1884:2: (a4= TEXT )
            // Robot.g:1885:3: a4= TEXT
            {
            a4=(Token)match(input,TEXT,FOLLOW_TEXT_in_parse_robotG_robot_Turn1389); if (state.failed) return element;

            if ( state.backtracking==0 ) {
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

            }


            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[240]);
            	}

            a5=(Token)match(input,10,FOLLOW_10_in_parse_robotG_robot_Turn1410); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_5, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[241]);
            	}

            a6=(Token)match(input,13,FOLLOW_13_in_parse_robotG_robot_Turn1424); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_6, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a6, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[242]);
            	}

            a7=(Token)match(input,11,FOLLOW_11_in_parse_robotG_robot_Turn1438); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_7, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a7, element);
            	}

            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[243]);
            	}

            // Robot.g:1962:2: (a8= TEXT )
            // Robot.g:1963:3: a8= TEXT
            {
            a8=(Token)match(input,TEXT,FOLLOW_TEXT_in_parse_robotG_robot_Turn1456); if (state.failed) return element;

            if ( state.backtracking==0 ) {
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

            }


            if ( state.backtracking==0 ) {
            		// expected elements (follow set)
            		addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[244]);
            	}

            a9=(Token)match(input,9,FOLLOW_9_in_parse_robotG_robot_Turn1477); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            		if (element == null) {
            			element = robotG.robot.RobotFactory.eINSTANCE.createTurn();
            			startIncompleteElement(element);
            		}
            		collectHiddenTokens(element);
            		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_14_0_0_9, null, true);
            		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a9, element);
            	}

            if ( state.backtracking==0 ) {
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

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 12, parse_robotG_robot_Turn_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parse_robotG_robot_Turn"



    // $ANTLR start "parseop_ExprBool_level_1"
    // Robot.g:2027:1: parseop_ExprBool_level_1 returns [robotG.flow.ExprBool element = null] : leftArg= parseop_ExprBool_level_2 ( ( () a0= 'and' rightArg= parseop_ExprBool_level_2 )+ |) ;
    public final robotG.flow.ExprBool parseop_ExprBool_level_1() throws RecognitionException {
        robotG.flow.ExprBool element =  null;

        int parseop_ExprBool_level_1_StartIndex = input.index();

        Token a0=null;
        robotG.flow.ExprBool leftArg =null;

        robotG.flow.ExprBool rightArg =null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return element; }

            // Robot.g:2030:2: (leftArg= parseop_ExprBool_level_2 ( ( () a0= 'and' rightArg= parseop_ExprBool_level_2 )+ |) )
            // Robot.g:2031:2: leftArg= parseop_ExprBool_level_2 ( ( () a0= 'and' rightArg= parseop_ExprBool_level_2 )+ |)
            {
            pushFollow(FOLLOW_parseop_ExprBool_level_2_in_parseop_ExprBool_level_11506);
            leftArg=parseop_ExprBool_level_2();

            state._fsp--;
            if (state.failed) return element;

            // Robot.g:2031:37: ( ( () a0= 'and' rightArg= parseop_ExprBool_level_2 )+ |)
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==12) ) {
                alt6=1;
            }
            else if ( (LA6_0==18||LA6_0==34) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return element;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1 :
                    // Robot.g:2031:38: ( () a0= 'and' rightArg= parseop_ExprBool_level_2 )+
                    {
                    // Robot.g:2031:38: ( () a0= 'and' rightArg= parseop_ExprBool_level_2 )+
                    int cnt5=0;
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==12) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // Robot.g:2032:3: () a0= 'and' rightArg= parseop_ExprBool_level_2
                    	    {
                    	    // Robot.g:2032:3: ()
                    	    // Robot.g:2032:4: 
                    	    {
                    	    }


                    	    if ( state.backtracking==0 ) { element = null; }

                    	    a0=(Token)match(input,12,FOLLOW_12_in_parseop_ExprBool_level_11526); if (state.failed) return element;

                    	    if ( state.backtracking==0 ) {
                    	    			if (element == null) {
                    	    				element = robotG.flow.FlowFactory.eINSTANCE.createAnd();
                    	    				startIncompleteElement(element);
                    	    			}
                    	    			collectHiddenTokens(element);
                    	    			retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_3_0_0_1, null, true);
                    	    			copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
                    	    		}

                    	    if ( state.backtracking==0 ) {
                    	    			// expected elements (follow set)
                    	    			addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getAnd(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[259]);
                    	    			addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getAnd(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[260]);
                    	    			addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getAnd(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[261]);
                    	    		}

                    	    pushFollow(FOLLOW_parseop_ExprBool_level_2_in_parseop_ExprBool_level_11543);
                    	    rightArg=parseop_ExprBool_level_2();

                    	    state._fsp--;
                    	    if (state.failed) return element;

                    	    if ( state.backtracking==0 ) {
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

                    	    if ( state.backtracking==0 ) {
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

                    	    if ( state.backtracking==0 ) { leftArg = element; /* this may become an argument in the next iteration */ }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt5 >= 1 ) break loop5;
                    	    if (state.backtracking>0) {state.failed=true; return element;}
                                EarlyExitException eee =
                                    new EarlyExitException(5, input);
                                throw eee;
                        }
                        cnt5++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // Robot.g:2089:21: 
                    {
                    if ( state.backtracking==0 ) { element = leftArg; }

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 13, parseop_ExprBool_level_1_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parseop_ExprBool_level_1"



    // $ANTLR start "parseop_ExprBool_level_2"
    // Robot.g:2094:1: parseop_ExprBool_level_2 returns [robotG.flow.ExprBool element = null] : leftArg= parseop_ExprBool_level_3 ( ( () a0= 'or' rightArg= parseop_ExprBool_level_3 )+ |) ;
    public final robotG.flow.ExprBool parseop_ExprBool_level_2() throws RecognitionException {
        robotG.flow.ExprBool element =  null;

        int parseop_ExprBool_level_2_StartIndex = input.index();

        Token a0=null;
        robotG.flow.ExprBool leftArg =null;

        robotG.flow.ExprBool rightArg =null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return element; }

            // Robot.g:2097:9: (leftArg= parseop_ExprBool_level_3 ( ( () a0= 'or' rightArg= parseop_ExprBool_level_3 )+ |) )
            // Robot.g:2098:9: leftArg= parseop_ExprBool_level_3 ( ( () a0= 'or' rightArg= parseop_ExprBool_level_3 )+ |)
            {
            pushFollow(FOLLOW_parseop_ExprBool_level_3_in_parseop_ExprBool_level_21589);
            leftArg=parseop_ExprBool_level_3();

            state._fsp--;
            if (state.failed) return element;

            // Robot.g:2098:35: ( ( () a0= 'or' rightArg= parseop_ExprBool_level_3 )+ |)
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==28) ) {
                alt8=1;
            }
            else if ( (LA8_0==EOF||LA8_0==12||LA8_0==18||LA8_0==34) ) {
                alt8=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return element;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }
            switch (alt8) {
                case 1 :
                    // Robot.g:2098:36: ( () a0= 'or' rightArg= parseop_ExprBool_level_3 )+
                    {
                    // Robot.g:2098:36: ( () a0= 'or' rightArg= parseop_ExprBool_level_3 )+
                    int cnt7=0;
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==28) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // Robot.g:2099:2: () a0= 'or' rightArg= parseop_ExprBool_level_3
                    	    {
                    	    // Robot.g:2099:2: ()
                    	    // Robot.g:2099:3: 
                    	    {
                    	    }


                    	    if ( state.backtracking==0 ) { element = null; }

                    	    a0=(Token)match(input,28,FOLLOW_28_in_parseop_ExprBool_level_21605); if (state.failed) return element;

                    	    if ( state.backtracking==0 ) {
                    	    		if (element == null) {
                    	    			element = robotG.flow.FlowFactory.eINSTANCE.createOr();
                    	    			startIncompleteElement(element);
                    	    		}
                    	    		collectHiddenTokens(element);
                    	    		retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_4_0_0_1, null, true);
                    	    		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
                    	    	}

                    	    if ( state.backtracking==0 ) {
                    	    		// expected elements (follow set)
                    	    		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getOr(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[262]);
                    	    		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getOr(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[263]);
                    	    		addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getOr(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[264]);
                    	    	}

                    	    pushFollow(FOLLOW_parseop_ExprBool_level_3_in_parseop_ExprBool_level_21619);
                    	    rightArg=parseop_ExprBool_level_3();

                    	    state._fsp--;
                    	    if (state.failed) return element;

                    	    if ( state.backtracking==0 ) {
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

                    	    if ( state.backtracking==0 ) {
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

                    	    if ( state.backtracking==0 ) { leftArg = element; /* this may become an argument in the next iteration */ }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt7 >= 1 ) break loop7;
                    	    if (state.backtracking>0) {state.failed=true; return element;}
                                EarlyExitException eee =
                                    new EarlyExitException(7, input);
                                throw eee;
                        }
                        cnt7++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // Robot.g:2156:20: 
                    {
                    if ( state.backtracking==0 ) { element = leftArg; }

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 14, parseop_ExprBool_level_2_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parseop_ExprBool_level_2"



    // $ANTLR start "parseop_ExprBool_level_3"
    // Robot.g:2161:1: parseop_ExprBool_level_3 returns [robotG.flow.ExprBool element = null] : (a0= 'not' arg= parseop_ExprBool_level_4 |arg= parseop_ExprBool_level_4 );
    public final robotG.flow.ExprBool parseop_ExprBool_level_3() throws RecognitionException {
        robotG.flow.ExprBool element =  null;

        int parseop_ExprBool_level_3_StartIndex = input.index();

        Token a0=null;
        robotG.flow.ExprBool arg =null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return element; }

            // Robot.g:2164:0: (a0= 'not' arg= parseop_ExprBool_level_4 |arg= parseop_ExprBool_level_4 )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==26) ) {
                alt9=1;
            }
            else if ( (LA9_0==21||LA9_0==27) ) {
                alt9=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return element;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }
            switch (alt9) {
                case 1 :
                    // Robot.g:2165:0: a0= 'not' arg= parseop_ExprBool_level_4
                    {
                    a0=(Token)match(input,26,FOLLOW_26_in_parseop_ExprBool_level_31660); if (state.failed) return element;

                    if ( state.backtracking==0 ) {
                    if (element == null) {
                    	element = robotG.flow.FlowFactory.eINSTANCE.createNot();
                    	startIncompleteElement(element);
                    }
                    collectHiddenTokens(element);
                    retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_5_0_0_0, null, true);
                    copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
                    }

                    if ( state.backtracking==0 ) {
                    // expected elements (follow set)
                    addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getNot(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[265]);
                    addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getNot(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[266]);
                    addExpectedElement(robotG.flow.FlowPackage.eINSTANCE.getNot(), robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[267]);
                    }

                    pushFollow(FOLLOW_parseop_ExprBool_level_4_in_parseop_ExprBool_level_31671);
                    arg=parseop_ExprBool_level_4();

                    state._fsp--;
                    if (state.failed) return element;

                    if ( state.backtracking==0 ) {
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

                    }
                    break;
                case 2 :
                    // Robot.g:2202:5: arg= parseop_ExprBool_level_4
                    {
                    pushFollow(FOLLOW_parseop_ExprBool_level_4_in_parseop_ExprBool_level_31681);
                    arg=parseop_ExprBool_level_4();

                    state._fsp--;
                    if (state.failed) return element;

                    if ( state.backtracking==0 ) { element = arg; }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 15, parseop_ExprBool_level_3_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parseop_ExprBool_level_3"



    // $ANTLR start "parseop_ExprBool_level_4"
    // Robot.g:2205:1: parseop_ExprBool_level_4 returns [robotG.flow.ExprBool element = null] : (c0= parse_robotG_robot_HasTurned |c1= parse_robotG_robot_Obstacle );
    public final robotG.flow.ExprBool parseop_ExprBool_level_4() throws RecognitionException {
        robotG.flow.ExprBool element =  null;

        int parseop_ExprBool_level_4_StartIndex = input.index();

        robotG.robot.HasTurned c0 =null;

        robotG.robot.Obstacle c1 =null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return element; }

            // Robot.g:2208:0: (c0= parse_robotG_robot_HasTurned |c1= parse_robotG_robot_Obstacle )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==21) ) {
                alt10=1;
            }
            else if ( (LA10_0==27) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return element;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }
            switch (alt10) {
                case 1 :
                    // Robot.g:2209:0: c0= parse_robotG_robot_HasTurned
                    {
                    pushFollow(FOLLOW_parse_robotG_robot_HasTurned_in_parseop_ExprBool_level_41703);
                    c0=parse_robotG_robot_HasTurned();

                    state._fsp--;
                    if (state.failed) return element;

                    if ( state.backtracking==0 ) { element = c0; /* this is a subclass or primitive expression choice */ }

                    }
                    break;
                case 2 :
                    // Robot.g:2210:2: c1= parse_robotG_robot_Obstacle
                    {
                    pushFollow(FOLLOW_parse_robotG_robot_Obstacle_in_parseop_ExprBool_level_41711);
                    c1=parse_robotG_robot_Obstacle();

                    state._fsp--;
                    if (state.failed) return element;

                    if ( state.backtracking==0 ) { element = c1; /* this is a subclass or primitive expression choice */ }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 16, parseop_ExprBool_level_4_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parseop_ExprBool_level_4"



    // $ANTLR start "parse_robotG_robot_HasTurned"
    // Robot.g:2213:1: parse_robotG_robot_HasTurned returns [robotG.robot.HasTurned element = null] : a0= 'hasTurned' a1= '(' a2= 'angle' a3= '=' (a4= TEXT ) a5= ')' ;
    public final robotG.robot.HasTurned parse_robotG_robot_HasTurned() throws RecognitionException {
        robotG.robot.HasTurned element =  null;

        int parse_robotG_robot_HasTurned_StartIndex = input.index();

        Token a0=null;
        Token a1=null;
        Token a2=null;
        Token a3=null;
        Token a4=null;
        Token a5=null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return element; }

            // Robot.g:2216:4: (a0= 'hasTurned' a1= '(' a2= 'angle' a3= '=' (a4= TEXT ) a5= ')' )
            // Robot.g:2217:4: a0= 'hasTurned' a1= '(' a2= 'angle' a3= '=' (a4= TEXT ) a5= ')'
            {
            a0=(Token)match(input,21,FOLLOW_21_in_parse_robotG_robot_HasTurned1733); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            if (element == null) {
            	element = robotG.robot.RobotFactory.eINSTANCE.createHasTurned();
            	startIncompleteElement(element);
            }
            collectHiddenTokens(element);
            retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_9_0_0_0, null, true);
            copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
            }

            if ( state.backtracking==0 ) {
            // expected elements (follow set)
            addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[268]);
            }

            a1=(Token)match(input,8,FOLLOW_8_in_parse_robotG_robot_HasTurned1744); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            if (element == null) {
            	element = robotG.robot.RobotFactory.eINSTANCE.createHasTurned();
            	startIncompleteElement(element);
            }
            collectHiddenTokens(element);
            retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_9_0_0_1, null, true);
            copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
            }

            if ( state.backtracking==0 ) {
            // expected elements (follow set)
            addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[269]);
            }

            a2=(Token)match(input,13,FOLLOW_13_in_parse_robotG_robot_HasTurned1755); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            if (element == null) {
            	element = robotG.robot.RobotFactory.eINSTANCE.createHasTurned();
            	startIncompleteElement(element);
            }
            collectHiddenTokens(element);
            retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_9_0_0_2, null, true);
            copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
            }

            if ( state.backtracking==0 ) {
            // expected elements (follow set)
            addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[270]);
            }

            a3=(Token)match(input,11,FOLLOW_11_in_parse_robotG_robot_HasTurned1766); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            if (element == null) {
            	element = robotG.robot.RobotFactory.eINSTANCE.createHasTurned();
            	startIncompleteElement(element);
            }
            collectHiddenTokens(element);
            retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_9_0_0_3, null, true);
            copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
            }

            if ( state.backtracking==0 ) {
            // expected elements (follow set)
            addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[271]);
            }

            // Robot.g:2273:4: (a4= TEXT )
            // Robot.g:2274:4: a4= TEXT
            {
            a4=(Token)match(input,TEXT,FOLLOW_TEXT_in_parse_robotG_robot_HasTurned1779); if (state.failed) return element;

            if ( state.backtracking==0 ) {
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

            }


            if ( state.backtracking==0 ) {
            // expected elements (follow set)
            addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[272]);
            }

            a5=(Token)match(input,9,FOLLOW_9_in_parse_robotG_robot_HasTurned1792); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            if (element == null) {
            	element = robotG.robot.RobotFactory.eINSTANCE.createHasTurned();
            	startIncompleteElement(element);
            }
            collectHiddenTokens(element);
            retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_9_0_0_5, null, true);
            copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
            }

            if ( state.backtracking==0 ) {
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

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 17, parse_robotG_robot_HasTurned_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parse_robotG_robot_HasTurned"



    // $ANTLR start "parse_robotG_robot_Obstacle"
    // Robot.g:2340:1: parse_robotG_robot_Obstacle returns [robotG.robot.Obstacle element = null] : a0= 'obstacle' a1= '(' a2= 'distance' a3= '=' (a4= TEXT ) a5= ')' ;
    public final robotG.robot.Obstacle parse_robotG_robot_Obstacle() throws RecognitionException {
        robotG.robot.Obstacle element =  null;

        int parse_robotG_robot_Obstacle_StartIndex = input.index();

        Token a0=null;
        Token a1=null;
        Token a2=null;
        Token a3=null;
        Token a4=null;
        Token a5=null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return element; }

            // Robot.g:2343:4: (a0= 'obstacle' a1= '(' a2= 'distance' a3= '=' (a4= TEXT ) a5= ')' )
            // Robot.g:2344:4: a0= 'obstacle' a1= '(' a2= 'distance' a3= '=' (a4= TEXT ) a5= ')'
            {
            a0=(Token)match(input,27,FOLLOW_27_in_parse_robotG_robot_Obstacle1818); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            if (element == null) {
            	element = robotG.robot.RobotFactory.eINSTANCE.createObstacle();
            	startIncompleteElement(element);
            }
            collectHiddenTokens(element);
            retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_11_0_0_0, null, true);
            copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
            }

            if ( state.backtracking==0 ) {
            // expected elements (follow set)
            addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[289]);
            }

            a1=(Token)match(input,8,FOLLOW_8_in_parse_robotG_robot_Obstacle1829); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            if (element == null) {
            	element = robotG.robot.RobotFactory.eINSTANCE.createObstacle();
            	startIncompleteElement(element);
            }
            collectHiddenTokens(element);
            retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_11_0_0_1, null, true);
            copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
            }

            if ( state.backtracking==0 ) {
            // expected elements (follow set)
            addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[290]);
            }

            a2=(Token)match(input,17,FOLLOW_17_in_parse_robotG_robot_Obstacle1840); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            if (element == null) {
            	element = robotG.robot.RobotFactory.eINSTANCE.createObstacle();
            	startIncompleteElement(element);
            }
            collectHiddenTokens(element);
            retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_11_0_0_2, null, true);
            copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
            }

            if ( state.backtracking==0 ) {
            // expected elements (follow set)
            addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[291]);
            }

            a3=(Token)match(input,11,FOLLOW_11_in_parse_robotG_robot_Obstacle1851); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            if (element == null) {
            	element = robotG.robot.RobotFactory.eINSTANCE.createObstacle();
            	startIncompleteElement(element);
            }
            collectHiddenTokens(element);
            retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_11_0_0_3, null, true);
            copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
            }

            if ( state.backtracking==0 ) {
            // expected elements (follow set)
            addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[292]);
            }

            // Robot.g:2400:4: (a4= TEXT )
            // Robot.g:2401:4: a4= TEXT
            {
            a4=(Token)match(input,TEXT,FOLLOW_TEXT_in_parse_robotG_robot_Obstacle1864); if (state.failed) return element;

            if ( state.backtracking==0 ) {
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

            }


            if ( state.backtracking==0 ) {
            // expected elements (follow set)
            addExpectedElement(null, robotG.resource.robot.mopp.RobotExpectationConstants.EXPECTATIONS[293]);
            }

            a5=(Token)match(input,9,FOLLOW_9_in_parse_robotG_robot_Obstacle1877); if (state.failed) return element;

            if ( state.backtracking==0 ) {
            if (element == null) {
            	element = robotG.robot.RobotFactory.eINSTANCE.createObstacle();
            	startIncompleteElement(element);
            }
            collectHiddenTokens(element);
            retrieveLayoutInformation(element, robotG.resource.robot.grammar.RobotGrammarInformationProvider.ROBOT_11_0_0_5, null, true);
            copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
            }

            if ( state.backtracking==0 ) {
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

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 18, parse_robotG_robot_Obstacle_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parse_robotG_robot_Obstacle"



    // $ANTLR start "parse_robotG_flow_Expr"
    // Robot.g:2467:1: parse_robotG_flow_Expr returns [robotG.flow.Expr element = null] : c= parseop_Expr_level_4 ;
    public final robotG.flow.Expr parse_robotG_flow_Expr() throws RecognitionException {
        robotG.flow.Expr element =  null;

        int parse_robotG_flow_Expr_StartIndex = input.index();

        robotG.flow.Expr c =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return element; }

            // Robot.g:2468:3: (c= parseop_Expr_level_4 )
            // Robot.g:2469:3: c= parseop_Expr_level_4
            {
            pushFollow(FOLLOW_parseop_Expr_level_4_in_parse_robotG_flow_Expr1899);
            c=parseop_Expr_level_4();

            state._fsp--;
            if (state.failed) return element;

            if ( state.backtracking==0 ) { element = c; /* this rule is an expression root */ }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 19, parse_robotG_flow_Expr_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parse_robotG_flow_Expr"



    // $ANTLR start "parse_robotG_flow_ExprBool"
    // Robot.g:2473:1: parse_robotG_flow_ExprBool returns [robotG.flow.ExprBool element = null] : c= parseop_ExprBool_level_1 ;
    public final robotG.flow.ExprBool parse_robotG_flow_ExprBool() throws RecognitionException {
        robotG.flow.ExprBool element =  null;

        int parse_robotG_flow_ExprBool_StartIndex = input.index();

        robotG.flow.ExprBool c =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return element; }

            // Robot.g:2474:3: (c= parseop_ExprBool_level_1 )
            // Robot.g:2475:3: c= parseop_ExprBool_level_1
            {
            pushFollow(FOLLOW_parseop_ExprBool_level_1_in_parse_robotG_flow_ExprBool1918);
            c=parseop_ExprBool_level_1();

            state._fsp--;
            if (state.failed) return element;

            if ( state.backtracking==0 ) { element = c; /* this rule is an expression root */ }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 20, parse_robotG_flow_ExprBool_StartIndex); }

        }
        return element;
    }
    // $ANTLR end "parse_robotG_flow_ExprBool"

    // Delegated rules


 

    public static final BitSet FOLLOW_parse_robotG_flow_Programme_in_start82 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_start89 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_robotG_flow_Expr_in_parse_robotG_flow_Programme124 = new BitSet(new long[]{0x0000001B81414002L});
    public static final BitSet FOLLOW_parse_robotG_flow_While_in_parseop_Expr_level_4165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_robotG_flow_If_in_parseop_Expr_level_4175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_robotG_flow_StopProgram_in_parseop_Expr_level_4185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_robotG_robot_Bip_in_parseop_Expr_level_4195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_robotG_robot_Display_in_parseop_Expr_level_4205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_robotG_robot_Move_in_parseop_Expr_level_4215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_robotG_robot_SetTurnAngle_in_parseop_Expr_level_4225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_robotG_robot_StopEngine_in_parseop_Expr_level_4235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_robotG_robot_Turn_in_parseop_Expr_level_4245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_parse_robotG_flow_While268 = new BitSet(new long[]{0x000000000C200000L});
    public static final BitSet FOLLOW_parse_robotG_flow_ExprBool_in_parse_robotG_flow_While286 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_parse_robotG_flow_While304 = new BitSet(new long[]{0x0000001B81514000L});
    public static final BitSet FOLLOW_parse_robotG_flow_Expr_in_parse_robotG_flow_While327 = new BitSet(new long[]{0x0000001B81514000L});
    public static final BitSet FOLLOW_20_in_parse_robotG_flow_While353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_parse_robotG_flow_If382 = new BitSet(new long[]{0x000000000C200000L});
    public static final BitSet FOLLOW_parse_robotG_flow_ExprBool_in_parse_robotG_flow_If400 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_parse_robotG_flow_If418 = new BitSet(new long[]{0x0000001B81514000L});
    public static final BitSet FOLLOW_parse_robotG_flow_Expr_in_parse_robotG_flow_If441 = new BitSet(new long[]{0x0000001B81514000L});
    public static final BitSet FOLLOW_20_in_parse_robotG_flow_If467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_parse_robotG_flow_StopProgram496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_parse_robotG_robot_Bip525 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_parse_robotG_robot_Bip539 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_parse_robotG_robot_Bip553 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_parse_robotG_robot_Bip567 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_TEXT_in_parse_robotG_robot_Bip585 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_parse_robotG_robot_Bip606 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_parse_robotG_robot_Bip620 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_parse_robotG_robot_Bip634 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_TEXT_in_parse_robotG_robot_Bip652 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_parse_robotG_robot_Bip673 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_parse_robotG_robot_Bip687 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_parse_robotG_robot_Bip701 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_TEXT_in_parse_robotG_robot_Bip719 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_parse_robotG_robot_Bip740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_parse_robotG_robot_Display769 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_parse_robotG_robot_Display783 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_parse_robotG_robot_Display797 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_parse_robotG_robot_Display811 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_parse_robotG_robot_Display829 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_parse_robotG_robot_Display850 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_parse_robotG_robot_Display864 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_parse_robotG_robot_Display878 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_TEXT_in_parse_robotG_robot_Display896 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_parse_robotG_robot_Display917 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_parse_robotG_robot_Display931 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_parse_robotG_robot_Display945 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_TEXT_in_parse_robotG_robot_Display963 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_parse_robotG_robot_Display984 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_parse_robotG_robot_Display998 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_parse_robotG_robot_Display1012 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_TEXT_in_parse_robotG_robot_Display1030 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_parse_robotG_robot_Display1051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_parse_robotG_robot_Move1080 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_parse_robotG_robot_Move1094 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_parse_robotG_robot_Move1108 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_parse_robotG_robot_Move1122 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_TEXT_in_parse_robotG_robot_Move1140 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_parse_robotG_robot_Move1161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_parse_robotG_robot_SetTurnAngle1190 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_parse_robotG_robot_SetTurnAngle1204 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_parse_robotG_robot_SetTurnAngle1218 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_parse_robotG_robot_SetTurnAngle1232 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_TEXT_in_parse_robotG_robot_SetTurnAngle1250 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_parse_robotG_robot_SetTurnAngle1271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_parse_robotG_robot_StopEngine1300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_parse_robotG_robot_Turn1329 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_parse_robotG_robot_Turn1343 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_parse_robotG_robot_Turn1357 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_parse_robotG_robot_Turn1371 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_TEXT_in_parse_robotG_robot_Turn1389 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_parse_robotG_robot_Turn1410 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_parse_robotG_robot_Turn1424 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_parse_robotG_robot_Turn1438 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_TEXT_in_parse_robotG_robot_Turn1456 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_parse_robotG_robot_Turn1477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parseop_ExprBool_level_2_in_parseop_ExprBool_level_11506 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_12_in_parseop_ExprBool_level_11526 = new BitSet(new long[]{0x000000000C200000L});
    public static final BitSet FOLLOW_parseop_ExprBool_level_2_in_parseop_ExprBool_level_11543 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_parseop_ExprBool_level_3_in_parseop_ExprBool_level_21589 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_28_in_parseop_ExprBool_level_21605 = new BitSet(new long[]{0x000000000C200000L});
    public static final BitSet FOLLOW_parseop_ExprBool_level_3_in_parseop_ExprBool_level_21619 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_26_in_parseop_ExprBool_level_31660 = new BitSet(new long[]{0x0000000008200000L});
    public static final BitSet FOLLOW_parseop_ExprBool_level_4_in_parseop_ExprBool_level_31671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parseop_ExprBool_level_4_in_parseop_ExprBool_level_31681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_robotG_robot_HasTurned_in_parseop_ExprBool_level_41703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_robotG_robot_Obstacle_in_parseop_ExprBool_level_41711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_parse_robotG_robot_HasTurned1733 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_parse_robotG_robot_HasTurned1744 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_parse_robotG_robot_HasTurned1755 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_parse_robotG_robot_HasTurned1766 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_TEXT_in_parse_robotG_robot_HasTurned1779 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_parse_robotG_robot_HasTurned1792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_parse_robotG_robot_Obstacle1818 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_parse_robotG_robot_Obstacle1829 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_parse_robotG_robot_Obstacle1840 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_parse_robotG_robot_Obstacle1851 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_TEXT_in_parse_robotG_robot_Obstacle1864 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_parse_robotG_robot_Obstacle1877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parseop_Expr_level_4_in_parse_robotG_flow_Expr1899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parseop_ExprBool_level_1_in_parse_robotG_flow_ExprBool1918 = new BitSet(new long[]{0x0000000000000002L});

}