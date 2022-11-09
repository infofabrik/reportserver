// 
// Decompiled by Procyon v0.5.36
// 

package mondrian9.parser;

public class TokenMgrError extends Error
{
    private static final long serialVersionUID = 1L;
    static final int LEXICAL_ERROR = 0;
    static final int STATIC_LEXER_ERROR = 1;
    static final int INVALID_LEXICAL_STATE = 2;
    static final int LOOP_DETECTED = 3;
    int errorCode;
    
    protected static final String addEscapes(final String str) {
        final StringBuffer retval = new StringBuffer();
        for (int i = 0; i < str.length(); ++i) {
            switch (str.charAt(i)) {
                case '\0': {
                    break;
                }
                case '\b': {
                    retval.append("\\b");
                    break;
                }
                case '\t': {
                    retval.append("\\t");
                    break;
                }
                case '\n': {
                    retval.append("\\n");
                    break;
                }
                case '\f': {
                    retval.append("\\f");
                    break;
                }
                case '\r': {
                    retval.append("\\r");
                    break;
                }
                case '\"': {
                    retval.append("\\\"");
                    break;
                }
                case '\'': {
                    retval.append("\\'");
                    break;
                }
                case '\\': {
                    retval.append("\\\\");
                    break;
                }
                default: {
                    final char ch;
                    if ((ch = str.charAt(i)) < ' ' || ch > '~') {
                        final String s = "0000" + Integer.toString(ch, 16);
                        retval.append("\\u" + s.substring(s.length() - 4, s.length()));
                        break;
                    }
                    retval.append(ch);
                    break;
                }
            }
        }
        return retval.toString();
    }
    
    protected static String LexicalError(final boolean EOFSeen, final int lexState, final int errorLine, final int errorColumn, final String errorAfter, final char curChar) {
        return "Lexical error at line " + errorLine + ", column " + errorColumn + ".  Encountered: " + (EOFSeen ? "<EOF> " : ("\"" + addEscapes(String.valueOf(curChar)) + "\"" + " (" + (int)curChar + "), ")) + "after : \"" + addEscapes(errorAfter) + "\"";
    }
    
    @Override
    public String getMessage() {
        return super.getMessage();
    }
    
    public TokenMgrError() {
    }
    
    public TokenMgrError(final String message, final int reason) {
        super(message);
        this.errorCode = reason;
    }
    
    public TokenMgrError(final boolean EOFSeen, final int lexState, final int errorLine, final int errorColumn, final String errorAfter, final char curChar, final int reason) {
        this(LexicalError(EOFSeen, lexState, errorLine, errorColumn, errorAfter, curChar), reason);
    }
}
