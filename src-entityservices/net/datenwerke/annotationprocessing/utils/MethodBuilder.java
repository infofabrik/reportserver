package net.datenwerke.annotationprocessing.utils;

/**
 * 
 *
 */
public class MethodBuilder {

   public static final String VOID = "void";

   private String modifier = "public";
   private String name;
   private String indent = "\t";
   private String currentIndent = "\t";
   private String returnType = "void";

   private StringBuilder bodyBuilder = new StringBuilder();
   private String body;

   private StringBuilder annotationBuilder = new StringBuilder();

   private StringBuilder methodCommentBuilder = new StringBuilder();

   private String[] arguments;

   private boolean oneLineBlock;

   private boolean staticModifier = false;

   private boolean finalModifier = false;

   private String[] throwsDeclarations;

   public MethodBuilder(String name) {
      setName(name);
   }

   public MethodBuilder(String name, String returnType, String... arguments) {
      this(name);
      setReturnType(returnType);
      setArguments(arguments);
   }

   public void setThrows(String... throwsDeclarations) {
      this.throwsDeclarations = throwsDeclarations;
   }

   public void setArguments(String[] arguments) {
      this.arguments = arguments;
   }

   public void setPrivateModifier() {
      modifier = "private";
   }

   public void setProtectedModifier() {
      modifier = "protected";
   }

   public void setPublicModifier() {
      modifier = "public";
   }

   public void setPackageModifier() {
      modifier = "";
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getModifier() {
      return modifier;
   }

   public String getReturnType() {
      return returnType;
   }

   public void setReturnType(String returnType) {
      this.returnType = returnType;
   }

   public void setBody(String body) {
      this.body = body;
   }

   public void addMethodCommentLine(String line) {
      if (0 == methodCommentBuilder.length())
         methodCommentBuilder.append(indent).append("/**\n");

      methodCommentBuilder.append(indent).append(" * ").append(line).append("\n");
   }

   public void addBodyComment(String comment) {
      bodyBuilder.append(currentIndent).append("\t/* ").append(comment).append(" */\n");
   }

   public void addBodyComment(String comment, int additionalIndents) {
      while (additionalIndents-- > 0)
         bodyBuilder.append("\t");

      addBodyComment(comment);
   }

   public void addBodyLine() {
      bodyBuilder.append("\n");
   }

   public void addCustomLine(String line) {
      bodyBuilder.append(line);
   }

   public void beginBodyBlock(String line) {
      bodyBuilder.append(currentIndent).append("\t").append(line).append("{\n");
      currentIndent += "\t";
   }

   public void incrementIndent() {
      currentIndent += "\t";
   }

   public void decrementIndent() {
      currentIndent = currentIndent.substring(0, currentIndent.length() - 1);
   }

   public void beginOneLineBlock(String line) {
      bodyBuilder.append(currentIndent).append("\t").append(line).append("\n");
      currentIndent += "\t";
      oneLineBlock = true;
   }

   public void addOneLineElse() {
      bodyBuilder.append(currentIndent).append("\t").append("else").append("\n");
      currentIndent += "\t";
      oneLineBlock = true;
   }

   public void endBodyBlock() {
      currentIndent = currentIndent.substring(0, currentIndent.length() - 1);
      bodyBuilder.append(currentIndent).append("\t}\n");
   }

   public void addElseIfBlock(String ifClause) {
      String tmpIndent = currentIndent.substring(0, currentIndent.length() - 1);
      bodyBuilder.append(tmpIndent).append("\t} else if(" + ifClause + "){\n");
   }

   public void addElseBlock() {
      String tmpIndent = currentIndent.substring(0, currentIndent.length() - 1);
      bodyBuilder.append(tmpIndent).append("\t} else {\n");
   }

   public void beginTryBlock() {
      beginBodyBlock("try");
   }

   public void beginCatchBlock(String exception) {
      bodyBuilder.append(currentIndent).append("} catch(").append(exception).append("){\n");
   }

   public void beginFinallyBlock() {
      bodyBuilder.append(currentIndent).append("} finally {\n");
   }

   public void addBodyLine(String line, int additionalIndents) {
      while (additionalIndents-- > 0)
         bodyBuilder.append("\t");

      addBodyLine(line);
   }

   public void addBodyLine(String line) {
      bodyBuilder.append(currentIndent).append("\t").append(line).append("\n");

      if (oneLineBlock) {
         oneLineBlock = false;
         currentIndent = currentIndent.substring(0, currentIndent.length() - 1);
      }
   }

   public void addAnnotation(String name) {
      annotationBuilder.append(indent).append("@").append(name).append("\n");
   }

   public void addAnnotation(String name, String arguments) {
      annotationBuilder.append(indent).append("@").append(name).append("(").append(arguments).append(")\n");
   }

   public void addAnnotationString(String name, String argument) {
      addAnnotation(name, "\"" + argument + "\"");
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();

      if (methodCommentBuilder.length() > 0)
         builder.append(methodCommentBuilder).append(indent).append(" */\n");

      /* add annotations */
      if (annotationBuilder.length() > 0)
         builder.append(annotationBuilder);

      /* prepare arguments */
      String argumentString = "";
      if (null != arguments && arguments.length > 0) {
         boolean first = true;
         for (String arg : arguments) {
            argumentString += first ? arg : ", " + arg;
            first = false;
         }
      }

      /* prepare throws */
      String throwsExceptionString = "";
      if (null != throwsDeclarations && throwsDeclarations.length > 0) {
         boolean first = true;
         for (String ex : throwsDeclarations) {
            throwsExceptionString += first ? " throws " + ex : ", " + ex;
            first = false;
         }
      }

      /* create method declaration */
      builder.append(indent).append(modifier).append(" ");
      if (staticModifier)
         builder.append("static ");
      if (finalModifier)
         builder.append("final ");
      builder.append(returnType).append(" ").append(name).append("(").append(argumentString).append(") ");
      builder.append(throwsExceptionString).append(" {\n");

      /* method body */
      builder.append(body == null ? bodyBuilder : body);

      /* method end */
      builder.append(indent).append("}\n");

      return builder.toString();
   }

   public void addOverride() {
      addAnnotation("Override");
   }

   public void setStatic() {
      this.staticModifier = true;
   }

   public void setFinal() {
      this.finalModifier = true;
   }

}
