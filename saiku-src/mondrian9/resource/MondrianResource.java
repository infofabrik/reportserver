// 
// Decompiled by Procyon v0.5.36
// 

package mondrian9.resource;

import mondrian9.olap.NativeEvaluationUnsupportedException;
import mondrian9.olap.InvalidHierarchyException;
import java.sql.SQLDataException;
import mondrian9.olap.QueryTimeoutException;
import mondrian9.olap.QueryCanceledException;
import mondrian9.olap.ResourceLimitExceededException;
import mondrian9.olap.MondrianException;
import org.eigenbase.resgen.ResourceDefinition;
import java.util.Locale;
import java.util.ResourceBundle;
import java.io.IOException;
import org.eigenbase.resgen.ShadowResourceBundle;

public class MondrianResource extends ShadowResourceBundle
{
    private static final String baseName = "mondrian9.resource.MondrianResource";
    public final _Def0 Internal;
    public final _Def0 MdxCubeNotFound;
    public final _Def1 MdxChildObjectNotFound;
    public final _Def0 MemberNotFound;
    public final _Def2 MdxCubeName;
    public final _Def2 MdxHierarchyName;
    public final _Def2 MdxDimensionName;
    public final _Def2 MdxLevelName;
    public final _Def2 MdxMemberName;
    public final _Def2 HighCardinalityInDimension;
    public final _Def0 WhileParsingMdx;
    public final _Def0 WhileParsingMdxExpression;
    public final _Def3 MdxFatalError;
    public final _Def0 FailedToParseQuery;
    public final _Def0 MdxError;
    public final _Def0 MdxSyntaxError;
    public final _Def4 MdxSyntaxErrorAt;
    public final _Def5 MdxFatalSyntaxError;
    public final _Def4 MdxCubeSlicerMemberError;
    public final _Def1 MdxCubeSlicerHierarchyError;
    public final _Def0 MdxInvalidMember;
    public final _Def0 MdxCalculatedHierarchyError;
    public final _Def0 MdxAxisIsNotSet;
    public final _Def0 MdxMemberExpIsSet;
    public final _Def0 MdxSetExpNotSet;
    public final _Def0 MdxFuncArgumentsNum;
    public final _Def6 MdxFuncNotHier;
    public final _Def0 UnknownParameter;
    public final _Def4 MdxFormulaNotFound;
    public final _Def0 MdxCantFindMember;
    public final _Def5 CalculatedMember;
    public final _Def5 CalculatedSet;
    public final _Def4 MdxCalculatedFormulaUsedOnAxis;
    public final _Def1 MdxCalculatedFormulaUsedOnSlicer;
    public final _Def7 MdxCalculatedFormulaUsedInFormula;
    public final _Def4 MdxCalculatedFormulaUsedInQuery;
    public final _Def8 MdxAxisShowSubtotalsNotSupported;
    public final _Def0 NoFunctionMatchesSignature;
    public final _Def1 MoreThanOneFunctionMatchesSignature;
    public final _Def1 MemberNotInLevelHierarchy;
    public final _Def3 ToggleDrillStateRecursiveNotSupported;
    public final _Def3 CompoundSlicer;
    public final _Def4 FunctionMbrAndLevelHierarchyMismatch;
    public final _Def1 CousinHierarchyMismatch;
    public final _Def0 HierarchyInIndependentAxes;
    public final _Def0 ArgsMustHaveSameHierarchy;
    public final _Def0 TimeArgNeeded;
    public final _Def8 InvalidAxis;
    public final _Def0 DuplicateAxis;
    public final _Def6 NonContiguousAxis;
    public final _Def0 DupHierarchiesInTuple;
    public final _Def3 VisualTotalsAppliedToTuples;
    public final _Def1 ParameterIsNotModifiable;
    public final _Def0 ParameterDefinedMoreThanOnce;
    public final _Def0 CycleDuringParameterEvaluation;
    public final _Def0 CastInvalidType;
    public final _Def3 NullNotSupported;
    public final _Def3 TwoNullsNotSupported;
    public final _Def0 NoTimeDimensionInCube;
    public final _Def0 CannotImplicitlyConvertDimensionToHierarchy;
    public final _Def0 HierarchyHasNoAccessibleMembers;
    public final _Def3 NullValue;
    public final _Def3 AvgRollupFailed;
    public final _Def0 DrillthroughDisabled;
    public final _Def0 DrillthroughCalculatedMember;
    public final _Def0 ValidMeasureUsingCalculatedMember;
    public final _Def0 UnsupportedCalculatedMember;
    public final _Def0 CurrentMemberWithCompoundSlicer;
    public final _Def1 ConnectStringMandatoryProperties;
    public final _Def0 NonTimeLevelInTimeHierarchy;
    public final _Def0 TimeLevelInNonTimeHierarchy;
    public final _Def1 MustSpecifyPrimaryKeyForHierarchy;
    public final _Def0 MustSpecifyPrimaryKeyTableForHierarchy;
    public final _Def1 MustSpecifyForeignKeyForHierarchy;
    public final _Def0 LevelMustHaveNameExpression;
    public final _Def0 PublicDimensionMustNotHaveForeignKey;
    public final _Def0 HierarchyMustNotHaveMoreThanOneSource;
    public final _Def4 DimensionUsageHasUnknownLevel;
    public final _Def4 CalcMemberHasBadDimension;
    public final _Def1 CalcMemberHasBothDimensionAndHierarchy;
    public final _Def4 CalcMemberHasUnknownParent;
    public final _Def4 CalcMemberHasDifferentParentAndHierarchy;
    public final _Def1 CalcMemberNotUnique;
    public final _Def4 NeitherExprNorValueForCalcMemberProperty;
    public final _Def4 ExprAndValueForMemberProperty;
    public final _Def1 MemberFormatterLoadFailed;
    public final _Def1 CellFormatterLoadFailed;
    public final _Def1 PropertyFormatterLoadFailed;
    public final _Def1 HierarchyMustHaveForeignKey;
    public final _Def4 HierarchyInvalidForeignKey;
    public final _Def1 UdfClassNotFound;
    public final _Def1 UdfClassMustBePublicAndStatic;
    public final _Def4 UdfClassWrongIface;
    public final _Def0 UdfDuplicateName;
    public final _Def1 NamedSetNotUnique;
    public final _Def0 UnknownNamedSetHasBadFormula;
    public final _Def0 NamedSetHasBadFormula;
    public final _Def7 MeasureOrdinalsNotUnique;
    public final _Def1 BadMeasureSource;
    public final _Def0 DuplicateSchemaParameter;
    public final _Def1 UnknownAggregator;
    public final _Def3 RoleUnionGrants;
    public final _Def0 UnknownRole;
    public final _Def3 DescendantsAppliedToSetOfTuples;
    public final _Def3 CannotDeduceTypeOfSet;
    public final _Def3 NotANamedSet;
    public final _Def0 HierarchyHasNoLevels;
    public final _Def1 HierarchyLevelNamesNotUnique;
    public final _Def3 IllegalLeftDeepJoin;
    public final _Def9 LevelTableParentNotFound;
    public final _Def0 CreateTableFailed;
    public final _Def1 CreateIndexFailed;
    public final _Def0 MissingArg;
    public final _Def6 InvalidInsertLine;
    public final _Def10 LimitExceededDuringCrossjoin;
    public final _Def10 TotalMembersLimitExceeded;
    public final _Def11 MemberFetchLimitExceeded;
    public final _Def11 SegmentFetchLimitExceeded;
    public final _Def12 QueryCanceled;
    public final _Def13 QueryTimeout;
    public final _Def11 IterationLimitExceeded;
    public final _Def14 JavaDoubleOverflow;
    public final _Def15 InvalidHierarchyCondition;
    public final _Def16 TooManyMessageRecorderErrors;
    public final _Def16 ForceMessageRecorderError;
    public final _Def17 UnknownLevelName;
    public final _Def9 DuplicateLevelNames;
    public final _Def18 DuplicateLevelColumnNames;
    public final _Def18 DuplicateMeasureColumnNames;
    public final _Def18 DuplicateLevelMeasureColumnNames;
    public final _Def9 DuplicateMeasureNames;
    public final _Def17 DuplicateFactForeignKey;
    public final _Def17 UnknownLeftJoinCondition;
    public final _Def9 UnknownHierarchyName;
    public final _Def9 BadLevelNameFormat;
    public final _Def9 BadMeasureNameFormat;
    public final _Def9 BadMeasures;
    public final _Def9 UnknownMeasureName;
    public final _Def9 NullAttributeString;
    public final _Def9 EmptyAttributeString;
    public final _Def0 MissingDefaultAggRule;
    public final _Def0 AggRuleParse;
    public final _Def17 BadMeasureName;
    public final _Def17 BadRolapStarLeftJoinCondition;
    public final _Def9 SqlQueryFailed;
    public final _Def3 AggLoadingError;
    public final _Def8 AggLoadingExceededErrorCount;
    public final _Def17 UnknownFactTableColumn;
    public final _Def19 AggMultipleMatchingMeasure;
    public final _Def2 CouldNotLoadDefaultAggregateRules;
    public final _Def9 FailedCreateNewDefaultAggregateRules;
    public final _Def9 CubeRelationNotTable;
    public final _Def4 AttemptToChangeTableUsage;
    public final _Def0 BadJdbcFactoryClassName;
    public final _Def0 BadJdbcFactoryInstantiation;
    public final _Def0 BadJdbcFactoryAccess;
    public final _Def18 NonNumericFactCountColumn;
    public final _Def20 TooManyFactCountColumns;
    public final _Def9 NoFactCountColumns;
    public final _Def9 NoMeasureColumns;
    public final _Def21 TooManyMatchingForeignKeyColumns;
    public final _Def22 DoubleMatchForLevel;
    public final _Def17 AggUnknownColumn;
    public final _Def17 NoAggregatorFound;
    public final _Def2 NoColumnNameFromExpression;
    public final _Def9 AggTableZeroSize;
    public final _Def2 AggTableNoConstraintGenerated;
    public final _Def3 CacheFlushRegionMustContainMembers;
    public final _Def1 CacheFlushUnionDimensionalityMismatch;
    public final _Def0 CacheFlushCrossjoinDimensionsInCommon;
    public final _Def3 SegmentCacheIsNotImplementingInterface;
    public final _Def3 SegmentCacheFailedToInstanciate;
    public final _Def3 SegmentCacheFailedToLoadSegment;
    public final _Def3 SegmentCacheFailedToSaveSegment;
    public final _Def3 SegmentCacheFailedToLookupSegment;
    public final _Def3 SegmentCacheFailedToScanSegments;
    public final _Def3 SegmentCacheFailedToDeleteSegment;
    public final _Def3 SegmentCacheReadTimeout;
    public final _Def3 SegmentCacheWriteTimeout;
    public final _Def3 SegmentCacheLookupTimeout;
    public final _Def23 NativeEvaluationUnsupported;
    public final _Def9 NativeSqlInClauseTooLarge;
    public final _Def0 ExecutionStatementCleanupException;
    public final _Def6 QueryLimitReached;
    public final _Def3 SqlQueryLimitReached;
    public final _Def3 SegmentCacheLimitReached;
    public final _Def3 FinalizerErrorRolapSchema;
    public final _Def3 FinalizerErrorMondrianServerImpl;
    public final _Def3 FinalizerErrorRolapConnection;
    
    public MondrianResource() throws IOException {
        this.Internal = new _Def0("Internal", "Internal error: {0}", null);
        this.MdxCubeNotFound = new _Def0("MdxCubeNotFound", "MDX cube ''{0}'' not found", null);
        this.MdxChildObjectNotFound = new _Def1("MdxChildObjectNotFound", "MDX object ''{0}'' not found in {1}", null);
        this.MemberNotFound = new _Def0("MemberNotFound", "Member ''{0}'' not found", null);
        this.MdxCubeName = new _Def2("MdxCubeName", "cube ''{0}''", null);
        this.MdxHierarchyName = new _Def2("MdxHierarchyName", "hierarchy ''{0}''", null);
        this.MdxDimensionName = new _Def2("MdxDimensionName", "dimension ''{0}''", null);
        this.MdxLevelName = new _Def2("MdxLevelName", "level ''{0}''", null);
        this.MdxMemberName = new _Def2("MdxMemberName", "member ''{0}''", null);
        this.HighCardinalityInDimension = new _Def2("HighCardinalityInDimension", "The highCardinality attribute specified on dimension ''{0}'' is deprecated and will be removed in future versions of mondrian9.The feature will produce wrong results in some scenarios and should be used with caution.", null);
        this.WhileParsingMdx = new _Def0("WhileParsingMdx", "Error while parsing MDX statement ''{0}''", null);
        this.WhileParsingMdxExpression = new _Def0("WhileParsingMdxExpression", "Syntax error in MDX expression ''{0}''", null);
        this.MdxFatalError = new _Def3("MdxFatalError", "MDX parser cannot recover from previous error(s)", null);
        this.FailedToParseQuery = new _Def0("FailedToParseQuery", "Failed to parse query ''{0}''", null);
        this.MdxError = new _Def0("MdxError", "Error: {0}", null);
        this.MdxSyntaxError = new _Def0("MdxSyntaxError", "Syntax error at token ''{0}''", null);
        this.MdxSyntaxErrorAt = new _Def4("MdxSyntaxErrorAt", "Syntax error at line {1}, column {2}, token ''{0}''", null);
        this.MdxFatalSyntaxError = new _Def5("MdxFatalSyntaxError", "Couldn''t repair and continue parse", null);
        this.MdxCubeSlicerMemberError = new _Def4("MdxCubeSlicerMemberError", "Failed to add Cube Slicer with member ''{0}'' for hierarchy ''{1}'' on cube ''{2}''", null);
        this.MdxCubeSlicerHierarchyError = new _Def1("MdxCubeSlicerHierarchyError", "Failed to add Cube Slicer for hierarchy ''{0}'' on cube ''{1}''", null);
        this.MdxInvalidMember = new _Def0("MdxInvalidMember", "Invalid member identifier ''{0}''", null);
        this.MdxCalculatedHierarchyError = new _Def0("MdxCalculatedHierarchyError", "Hierarchy for calculated member ''{0}'' not found", null);
        this.MdxAxisIsNotSet = new _Def0("MdxAxisIsNotSet", "Axis ''{0}'' expression is not a set", null);
        this.MdxMemberExpIsSet = new _Def0("MdxMemberExpIsSet", "Member expression ''{0}'' must not be a set", null);
        this.MdxSetExpNotSet = new _Def0("MdxSetExpNotSet", "Set expression ''{0}'' must be a set", null);
        this.MdxFuncArgumentsNum = new _Def0("MdxFuncArgumentsNum", "Function ''{0}'' must have at least 2 arguments", null);
        this.MdxFuncNotHier = new _Def6("MdxFuncNotHier", "Argument ''{0,number}'' of function ''{1}'' must be a hierarchy", null);
        this.UnknownParameter = new _Def0("UnknownParameter", "Unknown parameter ''{0}''", null);
        this.MdxFormulaNotFound = new _Def4("MdxFormulaNotFound", "Calculated {0} ''{1}'' has not been found in query ''{2}''", null);
        this.MdxCantFindMember = new _Def0("MdxCantFindMember", "Cannot find MDX member ''{0}''. Make sure it is indeed a member and not a level or a hierarchy.", null);
        this.CalculatedMember = new _Def5("CalculatedMember", "calculated member", null);
        this.CalculatedSet = new _Def5("CalculatedSet", "calculated set", null);
        this.MdxCalculatedFormulaUsedOnAxis = new _Def4("MdxCalculatedFormulaUsedOnAxis", "Cannot delete {0} ''{1}''. It is used on {2} axis.", null);
        this.MdxCalculatedFormulaUsedOnSlicer = new _Def1("MdxCalculatedFormulaUsedOnSlicer", "Cannot delete {0} ''{1}''. It is used on slicer.", null);
        this.MdxCalculatedFormulaUsedInFormula = new _Def7("MdxCalculatedFormulaUsedInFormula", "Cannot delete {0} ''{1}''. It is used in definition of {2} ''{3}''.", null);
        this.MdxCalculatedFormulaUsedInQuery = new _Def4("MdxCalculatedFormulaUsedInQuery", "Cannot delete {0} ''{1}''. It is used in query ''{2}''.", null);
        this.MdxAxisShowSubtotalsNotSupported = new _Def8("MdxAxisShowSubtotalsNotSupported", "Show/hide subtotals operation on axis ''{0,number}'' is not supported.", null);
        this.NoFunctionMatchesSignature = new _Def0("NoFunctionMatchesSignature", "No function matches signature ''{0}''", null);
        this.MoreThanOneFunctionMatchesSignature = new _Def1("MoreThanOneFunctionMatchesSignature", "More than one function matches signature ''{0}''; they are: {1}", null);
        this.MemberNotInLevelHierarchy = new _Def1("MemberNotInLevelHierarchy", "The member ''{0}'' is not in the same hierarchy as the level ''{1}''.", null);
        this.ToggleDrillStateRecursiveNotSupported = new _Def3("ToggleDrillStateRecursiveNotSupported", "''RECURSIVE'' is not supported in ToggleDrillState.", null);
        this.CompoundSlicer = new _Def3("CompoundSlicer", "WHERE clause expression returned set with more than one element.", null);
        this.FunctionMbrAndLevelHierarchyMismatch = new _Def4("FunctionMbrAndLevelHierarchyMismatch", "The <level> and <member> arguments to {0} must be from the same hierarchy. The level was from ''{1}'' but the member was from ''{2}''.", null);
        this.CousinHierarchyMismatch = new _Def1("CousinHierarchyMismatch", "The member arguments to the Cousin function must be from the same hierarchy. The members are ''{0}'' and ''{1}''.", null);
        this.HierarchyInIndependentAxes = new _Def0("HierarchyInIndependentAxes", "Hierarchy ''{0}'' appears in more than one independent axis.", null);
        this.ArgsMustHaveSameHierarchy = new _Def0("ArgsMustHaveSameHierarchy", "All arguments to function ''{0}'' must have same hierarchy.", null);
        this.TimeArgNeeded = new _Def0("TimeArgNeeded", "Argument to function ''{0}'' must belong to Time hierarchy.", null);
        this.InvalidAxis = new _Def8("InvalidAxis", "Invalid axis specification. The axis number must be a non-negative integer, but it was {0,number}.", null);
        this.DuplicateAxis = new _Def0("DuplicateAxis", "Duplicate axis name ''{0}''.", null);
        this.NonContiguousAxis = new _Def6("NonContiguousAxis", "Axis numbers specified in a query must be sequentially specified, and cannot contain gaps. Axis {0,number} ({1}) is missing.", null);
        this.DupHierarchiesInTuple = new _Def0("DupHierarchiesInTuple", "Tuple contains more than one member of hierarchy ''{0}''.", null);
        this.VisualTotalsAppliedToTuples = new _Def3("VisualTotalsAppliedToTuples", "Argument to ''VisualTotals'' function must be a set of members; got set of tuples.", null);
        this.ParameterIsNotModifiable = new _Def1("ParameterIsNotModifiable", "Parameter ''{0}'' (defined at ''{1}'' scope) is not modifiable", null);
        this.ParameterDefinedMoreThanOnce = new _Def0("ParameterDefinedMoreThanOnce", "Parameter ''{0}'' is defined more than once in this statement", null);
        this.CycleDuringParameterEvaluation = new _Def0("CycleDuringParameterEvaluation", "Cycle occurred while evaluating parameter ''{0}''", null);
        this.CastInvalidType = new _Def0("CastInvalidType", "Unknown type ''{0}''; values are NUMERIC, STRING, BOOLEAN", null);
        this.NullNotSupported = new _Def3("NullNotSupported", "Function does not support NULL member parameter", null);
        this.TwoNullsNotSupported = new _Def3("TwoNullsNotSupported", "Function does not support two NULL member parameters", null);
        this.NoTimeDimensionInCube = new _Def0("NoTimeDimensionInCube", "Cannot use the function ''{0}'', no time dimension is available for this cube.", null);
        this.CannotImplicitlyConvertDimensionToHierarchy = new _Def0("CannotImplicitlyConvertDimensionToHierarchy", "The ''{0}'' dimension contains more than one hierarchy, therefore the hierarchy must be explicitly specified.", null);
        this.HierarchyHasNoAccessibleMembers = new _Def0("HierarchyHasNoAccessibleMembers", "Hierarchy ''{0}'' has no accessible members.", null);
        this.NullValue = new _Def3("NullValue", "An MDX expression was expected. An empty expression was specified.", null);
        this.AvgRollupFailed = new _Def3("AvgRollupFailed", "Don''t know how to rollup aggregator ''avg'' because the cube doesn''t contain at least one ''count'' and one ''sum'' measures based on the same column.", null);
        this.DrillthroughDisabled = new _Def0("DrillthroughDisabled", "Can''t perform drillthrough operations because ''{0}'' is set to false.", null);
        this.DrillthroughCalculatedMember = new _Def0("DrillthroughCalculatedMember", "Can''t perform drillthrough operations because ''{0}'' is a calculated member.", null);
        this.ValidMeasureUsingCalculatedMember = new _Def0("ValidMeasureUsingCalculatedMember", "The function ValidMeasure cannot be used with the measure ''{0}'' because it is a calculated member. The function should be used to wrap the base measure in the source cube.", null);
        this.UnsupportedCalculatedMember = new _Def0("UnsupportedCalculatedMember", "Calculated member ''{0}'' is not supported within a compound predicate", null);
        this.CurrentMemberWithCompoundSlicer = new _Def0("CurrentMemberWithCompoundSlicer", "The MDX function CURRENTMEMBER failed because the coordinate for the ''{0}'' hierarchy contains a set", null);
        this.ConnectStringMandatoryProperties = new _Def1("ConnectStringMandatoryProperties", "Connect string must contain property ''{0}'' or property ''{1}''", null);
        this.NonTimeLevelInTimeHierarchy = new _Def0("NonTimeLevelInTimeHierarchy", "Level ''{0}'' belongs to a time hierarchy, so its level-type must be  ''Years'', ''Quarters'', ''Months'', ''Weeks'' or ''Days''.", null);
        this.TimeLevelInNonTimeHierarchy = new _Def0("TimeLevelInNonTimeHierarchy", "Level ''{0}'' does not belong to a time hierarchy, so its level-type must be ''Standard''.", null);
        this.MustSpecifyPrimaryKeyForHierarchy = new _Def1("MustSpecifyPrimaryKeyForHierarchy", "In usage of hierarchy ''{0}'' in cube ''{1}'', you must specify a primary key.", null);
        this.MustSpecifyPrimaryKeyTableForHierarchy = new _Def0("MustSpecifyPrimaryKeyTableForHierarchy", "Must specify a primary key table for hierarchy ''{0}'', because it has more than one table.", null);
        this.MustSpecifyForeignKeyForHierarchy = new _Def1("MustSpecifyForeignKeyForHierarchy", "In usage of hierarchy ''{0}'' in cube ''{1}'', you must specify a foreign key, because the hierarchy table is different from the fact table.", null);
        this.LevelMustHaveNameExpression = new _Def0("LevelMustHaveNameExpression", "Level ''{0}'' must have a name expression (a ''column'' attribute or an <Expression> child", null);
        this.PublicDimensionMustNotHaveForeignKey = new _Def0("PublicDimensionMustNotHaveForeignKey", "Dimension ''{0}'' has a foreign key. This attribute is only valid in private dimensions and dimension usages.", null);
        this.HierarchyMustNotHaveMoreThanOneSource = new _Def0("HierarchyMustNotHaveMoreThanOneSource", "Hierarchy ''{0}'' has more than one source (memberReaderClass, <Table>, <Join> or <View>)", null);
        this.DimensionUsageHasUnknownLevel = new _Def4("DimensionUsageHasUnknownLevel", "In usage of dimension ''{0}'' in cube ''{1}'', the level ''{2}'' is unknown", null);
        this.CalcMemberHasBadDimension = new _Def4("CalcMemberHasBadDimension", "Unknown dimension ''{0}'' for calculated member ''{1}'' in cube ''{2}''", null);
        this.CalcMemberHasBothDimensionAndHierarchy = new _Def1("CalcMemberHasBothDimensionAndHierarchy", "Cannot specify both a dimension and hierarchy for calculated member ''{0}'' in cube ''{1}''", null);
        this.CalcMemberHasUnknownParent = new _Def4("CalcMemberHasUnknownParent", "Cannot find a parent with name ''{0}'' for calculated member ''{1}'' in cube ''{2}''", null);
        this.CalcMemberHasDifferentParentAndHierarchy = new _Def4("CalcMemberHasDifferentParentAndHierarchy", "The calculated member ''{0}'' in cube ''{1}'' is defined for hierarchy ''{2}'' but its parent member is not part of that hierarchy", null);
        this.CalcMemberNotUnique = new _Def1("CalcMemberNotUnique", "Calculated member ''{0}'' already exists in cube ''{1}''", null);
        this.NeitherExprNorValueForCalcMemberProperty = new _Def4("NeitherExprNorValueForCalcMemberProperty", "Member property must have a value or an expression. (Property ''{0}'' of member ''{1}'' of cube ''{2}''.)", null);
        this.ExprAndValueForMemberProperty = new _Def4("ExprAndValueForMemberProperty", "Member property must not have both a value and an expression. (Property ''{0}'' of member ''{1}'' of cube ''{2}''.)", null);
        this.MemberFormatterLoadFailed = new _Def1("MemberFormatterLoadFailed", "Failed to load formatter class ''{0}'' for level ''{1}''.", null);
        this.CellFormatterLoadFailed = new _Def1("CellFormatterLoadFailed", "Failed to load formatter class ''{0}'' for member ''{1}''.", null);
        this.PropertyFormatterLoadFailed = new _Def1("PropertyFormatterLoadFailed", "Failed to load formatter class ''{0}'' for property ''{1}''.", null);
        this.HierarchyMustHaveForeignKey = new _Def1("HierarchyMustHaveForeignKey", "Hierarchy ''{0}'' in cube ''{1}'' must have a foreign key, since it is not based on the cube''s fact table.", null);
        this.HierarchyInvalidForeignKey = new _Def4("HierarchyInvalidForeignKey", "Foreign key ''{0}'' of hierarchy ''{1}'' in cube ''{2}'' is not a column in the fact table.", null);
        this.UdfClassNotFound = new _Def1("UdfClassNotFound", "Failed to load user-defined function ''{0}'': class ''{1}'' not found", null);
        this.UdfClassMustBePublicAndStatic = new _Def1("UdfClassMustBePublicAndStatic", "Failed to load user-defined function ''{0}'': class ''{1}'' must be public and static", null);
        this.UdfClassWrongIface = new _Def4("UdfClassWrongIface", "Failed to load user-defined function ''{0}'': class ''{1}'' does not implement the required interface ''{2}''", null);
        this.UdfDuplicateName = new _Def0("UdfDuplicateName", "Duplicate user-defined function ''{0}''", null);
        this.NamedSetNotUnique = new _Def1("NamedSetNotUnique", "Named set ''{0}'' already exists in cube ''{1}''", null);
        this.UnknownNamedSetHasBadFormula = new _Def0("UnknownNamedSetHasBadFormula", "Named set in cube ''{0}'' has bad formula", null);
        this.NamedSetHasBadFormula = new _Def0("NamedSetHasBadFormula", "Named set ''{0}'' has bad formula", null);
        this.MeasureOrdinalsNotUnique = new _Def7("MeasureOrdinalsNotUnique", "Cube ''{0}'': Ordinal {1} is not unique: ''{2}'' and ''{3}''", null);
        this.BadMeasureSource = new _Def1("BadMeasureSource", "Cube ''{0}'': Measure ''{1}'' must contain either a source column or a source expression, but not both", null);
        this.DuplicateSchemaParameter = new _Def0("DuplicateSchemaParameter", "Duplicate parameter ''{0}'' in schema", null);
        this.UnknownAggregator = new _Def1("UnknownAggregator", "Unknown aggregator ''{0}''; valid aggregators are: {1}", null);
        this.RoleUnionGrants = new _Def3("RoleUnionGrants", "Union role must not contain grants", null);
        this.UnknownRole = new _Def0("UnknownRole", "Unknown role ''{0}''", null);
        this.DescendantsAppliedToSetOfTuples = new _Def3("DescendantsAppliedToSetOfTuples", "Argument to Descendants function must be a member or set of members, not a set of tuples", null);
        this.CannotDeduceTypeOfSet = new _Def3("CannotDeduceTypeOfSet", "Cannot deduce type of set", null);
        this.NotANamedSet = new _Def3("NotANamedSet", "Not a named set", null);
        this.HierarchyHasNoLevels = new _Def0("HierarchyHasNoLevels", "Hierarchy ''{0}'' must have at least one level.", null);
        this.HierarchyLevelNamesNotUnique = new _Def1("HierarchyLevelNamesNotUnique", "Level names within hierarchy ''{0}'' are not unique; there is more than one level with name ''{1}''.", null);
        this.IllegalLeftDeepJoin = new _Def3("IllegalLeftDeepJoin", "Left side of join must not be a join; mondrian only supports right-deep joins.", null);
        this.LevelTableParentNotFound = new _Def9("LevelTableParentNotFound", "The level {0} makes use of the ''parentColumn'' attribute, but a parent member for key {1} is missing. This can be due to the usage of the NativizeSet MDX function with a list of members form a parent-child hierarchy that doesn''t include all parent members in its definition. Using NativizeSet with a parent-child hierarchy requires the parent members to be included in the set, or the hierarchy cannot be properly built natively.", null);
        this.CreateTableFailed = new _Def0("CreateTableFailed", "Mondrian loader could not create table ''{0}''.", null);
        this.CreateIndexFailed = new _Def1("CreateIndexFailed", "Mondrian loader could not create index ''{0}'' on table ''{1}''.", null);
        this.MissingArg = new _Def0("MissingArg", "Argument ''{0}'' must be specified.", null);
        this.InvalidInsertLine = new _Def6("InvalidInsertLine", "Input line is not a valid INSERT statement; line {0,number}: {1}.", null);
        this.LimitExceededDuringCrossjoin = new _Def10("LimitExceededDuringCrossjoin", "Size of CrossJoin result ({0,number}) exceeded limit ({1,number})", null);
        this.TotalMembersLimitExceeded = new _Def10("TotalMembersLimitExceeded", "Total number of Members in result ({0,number}) exceeded limit ({1,number})", null);
        this.MemberFetchLimitExceeded = new _Def11("MemberFetchLimitExceeded", "Number of members to be read exceeded limit ({0,number})", null);
        this.SegmentFetchLimitExceeded = new _Def11("SegmentFetchLimitExceeded", "Number of cell results to be read exceeded limit of ({0,number})", null);
        this.QueryCanceled = new _Def12("QueryCanceled", "Query canceled", null);
        this.QueryTimeout = new _Def13("QueryTimeout", "Query timeout of {0,number} seconds reached", null);
        this.IterationLimitExceeded = new _Def11("IterationLimitExceeded", "Number of iterations exceeded limit of {0,number}", null);
        this.JavaDoubleOverflow = new _Def14("JavaDoubleOverflow", "Big decimal value in ''{0}'' exceeds double size.", null);
        this.InvalidHierarchyCondition = new _Def15("InvalidHierarchyCondition", "Hierarchy ''{0}'' is invalid (has no members)", null);
        this.TooManyMessageRecorderErrors = new _Def16("TooManyMessageRecorderErrors", "Context ''{0}'': Exceeded number of allowed errors ''{1,number}''", null);
        this.ForceMessageRecorderError = new _Def16("ForceMessageRecorderError", "Context ''{0}'': Client forcing return with errors ''{1,number}''", null);
        this.UnknownLevelName = new _Def17("UnknownLevelName", "Context ''{0}'': The Hierarchy Level ''{1}'' does not have a Level named ''{2}''", null);
        this.DuplicateLevelNames = new _Def9("DuplicateLevelNames", "Context ''{0}'': Two levels share the same name ''{1}''", null);
        this.DuplicateLevelColumnNames = new _Def18("DuplicateLevelColumnNames", "Context ''{0}'': Two levels, ''{1}'' and ''{2}'',  share the same foreign column name ''{3}''", null);
        this.DuplicateMeasureColumnNames = new _Def18("DuplicateMeasureColumnNames", "Context ''{0}'': Two measures, ''{1}'' and ''{2}'',  share the same column name ''{3}''", null);
        this.DuplicateLevelMeasureColumnNames = new _Def18("DuplicateLevelMeasureColumnNames", "Context ''{0}'': The level ''{1}'' and the measuer ''{2}'',  share the same column name ''{3}''", null);
        this.DuplicateMeasureNames = new _Def9("DuplicateMeasureNames", "Context ''{0}'': Two measures share the same name ''{1}''", null);
        this.DuplicateFactForeignKey = new _Def17("DuplicateFactForeignKey", "Context ''{0}'': Duplicate fact foreign keys ''{1}'' for key ''{2}''.", null);
        this.UnknownLeftJoinCondition = new _Def17("UnknownLeftJoinCondition", "Context ''{0}'': Failed to find left join condition in fact table ''{1}'' for foreign key ''{2}''.", null);
        this.UnknownHierarchyName = new _Def9("UnknownHierarchyName", "Context ''{0}'': The Hierarchy ''{1}'' does not exist\"", null);
        this.BadLevelNameFormat = new _Def9("BadLevelNameFormat", "Context ''{0}'': The Level name ''{1}'' should be [usage hierarchy name].[level name].", null);
        this.BadMeasureNameFormat = new _Def9("BadMeasureNameFormat", "Context ''{0}'': The Measures name ''{1}'' should be [Measures].[measure name].", null);
        this.BadMeasures = new _Def9("BadMeasures", "Context ''{0}'': This name ''{1}'' must be the string \"Measures\".", null);
        this.UnknownMeasureName = new _Def9("UnknownMeasureName", "Context ''{0}'': Measures does not have a measure named ''{1}''", null);
        this.NullAttributeString = new _Def9("NullAttributeString", "Context ''{0}'': The value for the attribute ''{1}'' is null.", null);
        this.EmptyAttributeString = new _Def9("EmptyAttributeString", "Context ''{0}'': The value for the attribute ''{1}'' is empty (length is zero).", null);
        this.MissingDefaultAggRule = new _Def0("MissingDefaultAggRule", "There is no default aggregate recognition rule with tag ''{0}''.", null);
        this.AggRuleParse = new _Def0("AggRuleParse", "Error while parsing default aggregate recognition ''{0}''.", null);
        this.BadMeasureName = new _Def17("BadMeasureName", "Context ''{0}'': Failed to find Measure name ''{1}'' for cube ''{2}''.", null);
        this.BadRolapStarLeftJoinCondition = new _Def17("BadRolapStarLeftJoinCondition", "Context ''{0}'': Bad RolapStar left join condition type: ''{1}'' ''{2}''.", null);
        this.SqlQueryFailed = new _Def9("SqlQueryFailed", "Context ''{0}'': Sql query failed to run ''{1}''.", null);
        this.AggLoadingError = new _Def3("AggLoadingError", "Error while loading/reloading aggregates.", null);
        this.AggLoadingExceededErrorCount = new _Def8("AggLoadingExceededErrorCount", "Too many errors, ''{0,number}'', while loading/reloading aggregates.", null);
        this.UnknownFactTableColumn = new _Def17("UnknownFactTableColumn", "Context ''{0}'': For Fact table ''{1}'', the column ''{2}'' is neither a measure or foreign key\".", null);
        this.AggMultipleMatchingMeasure = new _Def19("AggMultipleMatchingMeasure", "Context ''{0}'': Candidate aggregate table ''{1}'' for fact table ''{2}'' has ''{3,number}'' columns matching measure ''{4}'', ''{5}'', ''{6}''\".", null);
        this.CouldNotLoadDefaultAggregateRules = new _Def2("CouldNotLoadDefaultAggregateRules", "Could not load default aggregate rules ''{0}''.", null);
        this.FailedCreateNewDefaultAggregateRules = new _Def9("FailedCreateNewDefaultAggregateRules", "Failed to create new default aggregate rules using property ''{0}'' with value ''{1}''.", null);
        this.CubeRelationNotTable = new _Def9("CubeRelationNotTable", "The Cube ''{0}'' relation is not a MondrianDef.Table but rather ''{1}''.", null);
        this.AttemptToChangeTableUsage = new _Def4("AttemptToChangeTableUsage", "JdbcSchema.Table ''{0}'' already set to usage ''{1}'' and can not be reset to usage ''{2}''.", null);
        this.BadJdbcFactoryClassName = new _Def0("BadJdbcFactoryClassName", "JdbcSchema Factory classname ''{0}'', class not found.", null);
        this.BadJdbcFactoryInstantiation = new _Def0("BadJdbcFactoryInstantiation", "JdbcSchema Factory classname ''{0}'', can not instantiate.", null);
        this.BadJdbcFactoryAccess = new _Def0("BadJdbcFactoryAccess", "JdbcSchema Factory classname ''{0}'', illegal access.", null);
        this.NonNumericFactCountColumn = new _Def18("NonNumericFactCountColumn", "Candidate aggregate table ''{0}'' for fact table ''{1}'' has candidate fact count column ''{2}'' has type ''{3}'' that is not numeric.", null);
        this.TooManyFactCountColumns = new _Def20("TooManyFactCountColumns", "Candidate aggregate table ''{0}'' for fact table ''{1}'' has ''{2,number}'' fact count columns.", null);
        this.NoFactCountColumns = new _Def9("NoFactCountColumns", "Candidate aggregate table ''{0}'' for fact table ''{1}'' has no fact count columns.", null);
        this.NoMeasureColumns = new _Def9("NoMeasureColumns", "Candidate aggregate table ''{0}'' for fact table ''{1}'' has no measure columns.", null);
        this.TooManyMatchingForeignKeyColumns = new _Def21("TooManyMatchingForeignKeyColumns", "Candidate aggregate table ''{0}'' for fact table ''{1}'' had ''{2,number}'' columns matching foreign key ''{3}''", null);
        this.DoubleMatchForLevel = new _Def22("DoubleMatchForLevel", "Double Match for candidate aggregate table ''{0}'' for fact table ''{1}'' and column ''{2}'' matched two hierarchies: 1) table=''{3}'', column=''{4}'' and 2) table=''{5}'', column=''{6}''", null);
        this.AggUnknownColumn = new _Def17("AggUnknownColumn", "Candidate aggregate table ''{0}'' for fact table ''{1}'' has a column ''{2}'' with unknown usage.", null);
        this.NoAggregatorFound = new _Def17("NoAggregatorFound", "No aggregator found while converting fact table aggregator: for usage\n        ''{0}'', fact aggregator ''{1}'' and sibling aggregator ''{2}''", null);
        this.NoColumnNameFromExpression = new _Def2("NoColumnNameFromExpression", "Could not get a column name from a level key expression: ''{0}''.", null);
        this.AggTableZeroSize = new _Def9("AggTableZeroSize", "Zero size Aggregate table ''{0}'' for Fact Table ''{1}''.", null);
        this.AggTableNoConstraintGenerated = new _Def2("AggTableNoConstraintGenerated", "Aggregate star fact table ''{0}'':  A constraint will not be generated because name column is not the same as key column.", null);
        this.CacheFlushRegionMustContainMembers = new _Def3("CacheFlushRegionMustContainMembers", "Region of cells to be flushed must contain measures.", null);
        this.CacheFlushUnionDimensionalityMismatch = new _Def1("CacheFlushUnionDimensionalityMismatch", "Cannot union cell regions of different dimensionalities. (Dimensionalities are ''{0}'', ''{1}''.)", null);
        this.CacheFlushCrossjoinDimensionsInCommon = new _Def0("CacheFlushCrossjoinDimensionsInCommon", "Cannot crossjoin cell regions which have dimensions in common. (Dimensionalities are {0}.)", null);
        this.SegmentCacheIsNotImplementingInterface = new _Def3("SegmentCacheIsNotImplementingInterface", "The mondrian9.rolap.SegmentCache property points to a class name which is not an\n        implementation of mondrian9.spi.SegmentCache.", null);
        this.SegmentCacheFailedToInstanciate = new _Def3("SegmentCacheFailedToInstanciate", "An exception was encountered while creating the SegmentCache.", null);
        this.SegmentCacheFailedToLoadSegment = new _Def3("SegmentCacheFailedToLoadSegment", "An exception was encountered while loading a segment from the SegmentCache.", null);
        this.SegmentCacheFailedToSaveSegment = new _Def3("SegmentCacheFailedToSaveSegment", "An exception was encountered while loading a segment from the SegmentCache.", null);
        this.SegmentCacheFailedToLookupSegment = new _Def3("SegmentCacheFailedToLookupSegment", "An exception was encountered while performing a segment lookup in the SegmentCache.", null);
        this.SegmentCacheFailedToScanSegments = new _Def3("SegmentCacheFailedToScanSegments", "An exception was encountered while getting a list of segment headers in the SegmentCache.", null);
        this.SegmentCacheFailedToDeleteSegment = new _Def3("SegmentCacheFailedToDeleteSegment", "An exception was encountered while deleting a segment from the SegmentCache.", null);
        this.SegmentCacheReadTimeout = new _Def3("SegmentCacheReadTimeout", "Timeout reached while reading segment from SegmentCache.", null);
        this.SegmentCacheWriteTimeout = new _Def3("SegmentCacheWriteTimeout", "Timeout reached while writing segment to SegmentCache.", null);
        this.SegmentCacheLookupTimeout = new _Def3("SegmentCacheLookupTimeout", "Timeout reached while performing a segment lookup in SegmentCache.", null);
        this.NativeEvaluationUnsupported = new _Def23("NativeEvaluationUnsupported", "Native evaluation not supported for this usage of function ''{0}''", null);
        this.NativeSqlInClauseTooLarge = new _Def9("NativeSqlInClauseTooLarge", "Cannot use native aggregation constraints for level ''{0}'' because the number of members is larger than the value of ''mondrian9.rolap.maxConstraints'' ({1})", null);
        this.ExecutionStatementCleanupException = new _Def0("ExecutionStatementCleanupException", "An exception was encountered while trying to cleanup an execution context. A statement failed to cancel gracefully. Locus was : \"{0}\".", null);
        this.QueryLimitReached = new _Def6("QueryLimitReached", "The number of concurrent MDX statements that can be processed simultaneously by this Mondrian server instance ({0,number}) has been reached. To change the limit, set the ''{1}'' property.", null);
        this.SqlQueryLimitReached = new _Def3("SqlQueryLimitReached", "The number of concurrent SQL statements which can be used simultaneously by this Mondrian server instance has been reached. Set ''mondrian9.rolap.maxSqlThreads'' to change the current limit.", null);
        this.SegmentCacheLimitReached = new _Def3("SegmentCacheLimitReached", "The number of concurrent segment cache operations which can be run simultaneously by this Mondrian server instance has been reached. Set ''mondrian9.rolap.maxCacheThreads'' to change the current limit.", null);
        this.FinalizerErrorRolapSchema = new _Def3("FinalizerErrorRolapSchema", "An exception was encountered while finalizing a RolapSchema object instance.", null);
        this.FinalizerErrorMondrianServerImpl = new _Def3("FinalizerErrorMondrianServerImpl", "An exception was encountered while finalizing a RolapSchema object instance.", null);
        this.FinalizerErrorRolapConnection = new _Def3("FinalizerErrorRolapConnection", "An exception was encountered while finalizing a RolapConnection object instance.", null);
    }
    
    public static synchronized MondrianResource instance() {
        return (MondrianResource)instance("mondrian9.resource.MondrianResource", getThreadOrDefaultLocale(), ResourceBundle.getBundle("mondrian9.resource.MondrianResource", getThreadOrDefaultLocale()));
    }
    
    public static synchronized MondrianResource instance(final Locale locale) {
        return (MondrianResource)instance("mondrian9.resource.MondrianResource", locale, ResourceBundle.getBundle("mondrian9.resource.MondrianResource", locale));
    }
    
    public final class _Def0 extends ResourceDefinition
    {
        _Def0(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final String p0) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString();
        }
        
        public MondrianException ex(final String p0) {
            return new MondrianException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString());
        }
        
        public MondrianException ex(final String p0, final Throwable err) {
            return new MondrianException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString(), err);
        }
    }
    
    public final class _Def1 extends ResourceDefinition
    {
        _Def1(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final String p0, final String p1) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1 }).toString();
        }
        
        public MondrianException ex(final String p0, final String p1) {
            return new MondrianException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1 }).toString());
        }
        
        public MondrianException ex(final String p0, final String p1, final Throwable err) {
            return new MondrianException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1 }).toString(), err);
        }
    }
    
    public final class _Def2 extends ResourceDefinition
    {
        _Def2(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final String p0) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString();
        }
    }
    
    public final class _Def3 extends ResourceDefinition
    {
        _Def3(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str() {
            return this.instantiate((ResourceBundle)MondrianResource.this, MondrianResource.emptyObjectArray).toString();
        }
        
        public MondrianException ex() {
            return new MondrianException(this.instantiate((ResourceBundle)MondrianResource.this, MondrianResource.emptyObjectArray).toString());
        }
        
        public MondrianException ex(final Throwable err) {
            return new MondrianException(this.instantiate((ResourceBundle)MondrianResource.this, MondrianResource.emptyObjectArray).toString(), err);
        }
    }
    
    public final class _Def4 extends ResourceDefinition
    {
        _Def4(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final String p0, final String p1, final String p2) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1, p2 }).toString();
        }
        
        public MondrianException ex(final String p0, final String p1, final String p2) {
            return new MondrianException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1, p2 }).toString());
        }
        
        public MondrianException ex(final String p0, final String p1, final String p2, final Throwable err) {
            return new MondrianException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1, p2 }).toString(), err);
        }
    }
    
    public final class _Def5 extends ResourceDefinition
    {
        _Def5(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str() {
            return this.instantiate((ResourceBundle)MondrianResource.this, MondrianResource.emptyObjectArray).toString();
        }
    }
    
    public final class _Def6 extends ResourceDefinition
    {
        _Def6(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final Number p0, final String p1) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1 }).toString();
        }
        
        public MondrianException ex(final Number p0, final String p1) {
            return new MondrianException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1 }).toString());
        }
        
        public MondrianException ex(final Number p0, final String p1, final Throwable err) {
            return new MondrianException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1 }).toString(), err);
        }
    }
    
    public final class _Def7 extends ResourceDefinition
    {
        _Def7(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final String p0, final String p1, final String p2, final String p3) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1, p2, p3 }).toString();
        }
        
        public MondrianException ex(final String p0, final String p1, final String p2, final String p3) {
            return new MondrianException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1, p2, p3 }).toString());
        }
        
        public MondrianException ex(final String p0, final String p1, final String p2, final String p3, final Throwable err) {
            return new MondrianException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1, p2, p3 }).toString(), err);
        }
    }
    
    public final class _Def8 extends ResourceDefinition
    {
        _Def8(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final Number p0) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString();
        }
        
        public MondrianException ex(final Number p0) {
            return new MondrianException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString());
        }
        
        public MondrianException ex(final Number p0, final Throwable err) {
            return new MondrianException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString(), err);
        }
    }
    
    public final class _Def9 extends ResourceDefinition
    {
        _Def9(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final String p0, final String p1) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1 }).toString();
        }
    }
    
    public final class _Def10 extends ResourceDefinition
    {
        _Def10(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final Number p0, final Number p1) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1 }).toString();
        }
        
        public ResourceLimitExceededException ex(final Number p0, final Number p1) {
            return new ResourceLimitExceededException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1 }).toString());
        }
    }
    
    public final class _Def11 extends ResourceDefinition
    {
        _Def11(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final Number p0) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString();
        }
        
        public ResourceLimitExceededException ex(final Number p0) {
            return new ResourceLimitExceededException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString());
        }
    }
    
    public final class _Def12 extends ResourceDefinition
    {
        _Def12(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str() {
            return this.instantiate((ResourceBundle)MondrianResource.this, MondrianResource.emptyObjectArray).toString();
        }
        
        public QueryCanceledException ex() {
            return new QueryCanceledException(this.instantiate((ResourceBundle)MondrianResource.this, MondrianResource.emptyObjectArray).toString());
        }
    }
    
    public final class _Def13 extends ResourceDefinition
    {
        _Def13(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final Number p0) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString();
        }
        
        public QueryTimeoutException ex(final Number p0) {
            return new QueryTimeoutException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString());
        }
    }
    
    public final class _Def14 extends ResourceDefinition
    {
        _Def14(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final String p0) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString();
        }
        
        public SQLDataException ex(final String p0) {
            return new SQLDataException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString());
        }
        
        public SQLDataException ex(final String p0, final Throwable err) {
            return new SQLDataException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString(), err);
        }
    }
    
    public final class _Def15 extends ResourceDefinition
    {
        _Def15(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final String p0) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString();
        }
        
        public InvalidHierarchyException ex(final String p0) {
            return new InvalidHierarchyException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString());
        }
    }
    
    public final class _Def16 extends ResourceDefinition
    {
        _Def16(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final String p0, final Number p1) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1 }).toString();
        }
    }
    
    public final class _Def17 extends ResourceDefinition
    {
        _Def17(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final String p0, final String p1, final String p2) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1, p2 }).toString();
        }
    }
    
    public final class _Def18 extends ResourceDefinition
    {
        _Def18(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final String p0, final String p1, final String p2, final String p3) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1, p2, p3 }).toString();
        }
    }
    
    public final class _Def19 extends ResourceDefinition
    {
        _Def19(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final String p0, final String p1, final String p2, final Number p3, final String p4, final String p5, final String p6) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1, p2, p3, p4, p5, p6 }).toString();
        }
    }
    
    public final class _Def20 extends ResourceDefinition
    {
        _Def20(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final String p0, final String p1, final Number p2) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1, p2 }).toString();
        }
    }
    
    public final class _Def21 extends ResourceDefinition
    {
        _Def21(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final String p0, final String p1, final Number p2, final String p3) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1, p2, p3 }).toString();
        }
    }
    
    public final class _Def22 extends ResourceDefinition
    {
        _Def22(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final String p0, final String p1, final String p2, final String p3, final String p4, final String p5, final String p6) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0, p1, p2, p3, p4, p5, p6 }).toString();
        }
    }
    
    public final class _Def23 extends ResourceDefinition
    {
        _Def23(final String key, final String baseMessage, final String[] props) {
            super(key, baseMessage, props);
        }
        
        public String str(final String p0) {
            return this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString();
        }
        
        public NativeEvaluationUnsupportedException ex(final String p0) {
            return new NativeEvaluationUnsupportedException(this.instantiate((ResourceBundle)MondrianResource.this, new Object[] { p0 }).toString());
        }
    }
}
