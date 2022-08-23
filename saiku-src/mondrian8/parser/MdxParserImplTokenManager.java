// 
// Decompiled by Procyon v0.5.36
// 

package mondrian8.parser;

import java.io.IOException;
import java.io.PrintStream;

public class MdxParserImplTokenManager implements MdxParserImplConstants
{
    public PrintStream debugStream;
    static final long[] jjbitVec0;
    static final long[] jjbitVec2;
    static final long[] jjbitVec3;
    static final long[] jjbitVec4;
    static final long[] jjbitVec5;
    static final long[] jjbitVec6;
    static final long[] jjbitVec7;
    static final long[] jjbitVec8;
    static final long[] jjbitVec9;
    static final int[] jjnextStates;
    public static final String[] jjstrLiteralImages;
    public static final String[] lexStateNames;
    public static final int[] jjnewLexState;
    static final long[] jjtoToken;
    static final long[] jjtoSkip;
    static final long[] jjtoSpecial;
    static final long[] jjtoMore;
    protected SimpleCharStream input_stream;
    private final int[] jjrounds;
    private final int[] jjstateSet;
    private final StringBuilder jjimage;
    private StringBuilder image;
    private int jjimageLen;
    private int lengthOfMatch;
    protected char curChar;
    int curLexState;
    int defaultLexState;
    int jjnewStateCnt;
    int jjround;
    int jjmatchedPos;
    int jjmatchedKind;
    
    public void setDebugStream(final PrintStream ds) {
        this.debugStream = ds;
    }
    
    private final int jjStopStringLiteralDfa_0(final int pos, final long active0, final long active1) {
        switch (pos) {
            case 0: {
                if ((active0 & 0x5000000000000L) != 0x0L || (active1 & 0x200L) != 0x0L) {
                    return 2;
                }
                if ((active0 & 0x1000000000000000L) != 0x0L) {
                    return 44;
                }
                if ((active0 & 0x3FFFFFFFFFEL) != 0x0L) {
                    this.jjmatchedKind = 81;
                    return 15;
                }
                return -1;
            }
            case 1: {
                if ((active0 & 0x4000000000000L) != 0x0L) {
                    return 0;
                }
                if ((active0 & 0xC0C0004L) != 0x0L) {
                    return 15;
                }
                if ((active0 & 0x3FFF3F3FFFAL) != 0x0L) {
                    this.jjmatchedKind = 81;
                    this.jjmatchedPos = 1;
                    return 15;
                }
                return -1;
            }
            case 2: {
                if ((active0 & 0x377F272DFF8L) != 0x0L) {
                    this.jjmatchedKind = 81;
                    this.jjmatchedPos = 2;
                    return 15;
                }
                if ((active0 & 0x8801812002L) != 0x0L) {
                    return 15;
                }
                return -1;
            }
            case 3: {
                if ((active0 & 0x246D070D780L) != 0x0L) {
                    this.jjmatchedKind = 81;
                    this.jjmatchedPos = 3;
                    return 15;
                }
                if ((active0 & 0x13122020878L) != 0x0L) {
                    return 15;
                }
                return -1;
            }
            case 4: {
                if ((active0 & 0x206C070C780L) != 0x0L) {
                    this.jjmatchedKind = 81;
                    this.jjmatchedPos = 4;
                    return 15;
                }
                if ((active0 & 0x4010001000L) != 0x0L) {
                    return 15;
                }
                return -1;
            }
            case 5: {
                if ((active0 & 0x2024030C780L) != 0x0L) {
                    this.jjmatchedKind = 81;
                    this.jjmatchedPos = 5;
                    return 15;
                }
                if ((active0 & 0x480400000L) != 0x0L) {
                    return 15;
                }
                return -1;
            }
            case 6: {
                if ((active0 & 0x20240008680L) != 0x0L) {
                    this.jjmatchedKind = 81;
                    this.jjmatchedPos = 6;
                    return 15;
                }
                if ((active0 & 0x304100L) != 0x0L) {
                    return 15;
                }
                return -1;
            }
            case 7: {
                if ((active0 & 0x40008600L) != 0x0L) {
                    this.jjmatchedKind = 81;
                    this.jjmatchedPos = 7;
                    return 15;
                }
                if ((active0 & 0x20200000080L) != 0x0L) {
                    return 15;
                }
                return -1;
            }
            case 8: {
                if ((active0 & 0x200L) != 0x0L) {
                    return 15;
                }
                if ((active0 & 0x40008400L) != 0x0L) {
                    this.jjmatchedKind = 81;
                    this.jjmatchedPos = 8;
                    return 15;
                }
                return -1;
            }
            case 9: {
                if ((active0 & 0x8400L) != 0x0L) {
                    this.jjmatchedKind = 81;
                    this.jjmatchedPos = 9;
                    return 15;
                }
                if ((active0 & 0x40000000L) != 0x0L) {
                    return 15;
                }
                return -1;
            }
            case 10: {
                if ((active0 & 0x400L) != 0x0L) {
                    this.jjmatchedKind = 81;
                    this.jjmatchedPos = 10;
                    return 15;
                }
                if ((active0 & 0x8000L) != 0x0L) {
                    return 15;
                }
                return -1;
            }
            default: {
                return -1;
            }
        }
    }
    
    private final int jjStartNfa_0(final int pos, final long active0, final long active1) {
        return this.jjMoveNfa_0(this.jjStopStringLiteralDfa_0(pos, active0, active1), pos + 1);
    }
    
    private int jjStopAtPos(final int pos, final int kind) {
        this.jjmatchedKind = kind;
        return (this.jjmatchedPos = pos) + 1;
    }
    
    private int jjMoveStringLiteralDfa0_0() {
        switch (this.curChar) {
            case '!': {
                return this.jjStopAtPos(0, 56);
            }
            case '(': {
                return this.jjStopAtPos(0, 66);
            }
            case ')': {
                return this.jjStopAtPos(0, 72);
            }
            case '*': {
                return this.jjStopAtPos(0, 55);
            }
            case '+': {
                return this.jjStopAtPos(0, 70);
            }
            case ',': {
                return this.jjStopAtPos(0, 58);
            }
            case '-': {
                this.jjmatchedKind = 68;
                return this.jjMoveStringLiteralDfa1_0(562949953421312L, 0L);
            }
            case '.': {
                return this.jjStartNfaWithStates_0(0, 60, 44);
            }
            case '/': {
                this.jjmatchedKind = 73;
                return this.jjMoveStringLiteralDfa1_0(1407374883553280L, 0L);
            }
            case ':': {
                return this.jjStopAtPos(0, 57);
            }
            case '<': {
                this.jjmatchedKind = 67;
                return this.jjMoveStringLiteralDfa1_0(0L, 34L);
            }
            case '=': {
                return this.jjStopAtPos(0, 61);
            }
            case '>': {
                this.jjmatchedKind = 63;
                return this.jjMoveStringLiteralDfa1_0(4611686018427387904L, 0L);
            }
            case 'A':
            case 'a': {
                return this.jjMoveStringLiteralDfa1_0(14L, 0L);
            }
            case 'C':
            case 'c': {
                return this.jjMoveStringLiteralDfa1_0(496L, 0L);
            }
            case 'D':
            case 'd': {
                return this.jjMoveStringLiteralDfa1_0(1536L, 0L);
            }
            case 'E':
            case 'e': {
                return this.jjMoveStringLiteralDfa1_0(2199023286272L, 0L);
            }
            case 'F':
            case 'f': {
                return this.jjMoveStringLiteralDfa1_0(229376L, 0L);
            }
            case 'I':
            case 'i': {
                return this.jjMoveStringLiteralDfa1_0(786432L, 0L);
            }
            case 'M':
            case 'm': {
                return this.jjMoveStringLiteralDfa1_0(7340032L, 0L);
            }
            case 'N':
            case 'n': {
                return this.jjMoveStringLiteralDfa1_0(58720256L, 0L);
            }
            case 'O':
            case 'o': {
                return this.jjMoveStringLiteralDfa1_0(201326592L, 0L);
            }
            case 'P':
            case 'p': {
                return this.jjMoveStringLiteralDfa1_0(1879048192L, 0L);
            }
            case 'R':
            case 'r': {
                return this.jjMoveStringLiteralDfa1_0(6442450944L, 0L);
            }
            case 'S':
            case 's': {
                return this.jjMoveStringLiteralDfa1_0(60129542144L, 0L);
            }
            case 'T':
            case 't': {
                return this.jjMoveStringLiteralDfa1_0(68719476736L, 0L);
            }
            case 'W':
            case 'w': {
                return this.jjMoveStringLiteralDfa1_0(1511828488192L, 0L);
            }
            case 'X':
            case 'x': {
                return this.jjMoveStringLiteralDfa1_0(549755813888L, 0L);
            }
            case '{': {
                return this.jjStopAtPos(0, 64);
            }
            case '|': {
                return this.jjMoveStringLiteralDfa1_0(576460752303423488L, 0L);
            }
            case '}': {
                return this.jjStopAtPos(0, 71);
            }
            default: {
                return this.jjMoveNfa_0(3, 0);
            }
        }
    }
    
    private int jjMoveStringLiteralDfa1_0(final long active0, final long active1) {
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(0, active0, active1);
            return 1;
        }
        switch (this.curChar) {
            case '*': {
                if ((active0 & 0x4000000000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(1, 50, 0);
                }
                break;
            }
            case '-': {
                if ((active0 & 0x2000000000000L) != 0x0L) {
                    return this.jjStopAtPos(1, 49);
                }
                break;
            }
            case '/': {
                if ((active0 & 0x1000000000000L) != 0x0L) {
                    return this.jjStopAtPos(1, 48);
                }
                break;
            }
            case '=': {
                if ((active0 & 0x4000000000000000L) != 0x0L) {
                    return this.jjStopAtPos(1, 62);
                }
                if ((active1 & 0x2L) != 0x0L) {
                    return this.jjStopAtPos(1, 65);
                }
                break;
            }
            case '>': {
                if ((active1 & 0x20L) != 0x0L) {
                    return this.jjStopAtPos(1, 69);
                }
                break;
            }
            case 'A':
            case 'a': {
                return this.jjMoveStringLiteralDfa2_0(active0, 271581232L, active1, 0L);
            }
            case 'E':
            case 'e': {
                return this.jjMoveStringLiteralDfa2_0(active0, 62281220160L, active1, 0L);
            }
            case 'H':
            case 'h': {
                return this.jjMoveStringLiteralDfa2_0(active0, 481036337280L, active1, 0L);
            }
            case 'I':
            case 'i': {
                return this.jjMoveStringLiteralDfa2_0(active0, 1099511661056L, active1, 0L);
            }
            case 'L':
            case 'l': {
                return this.jjMoveStringLiteralDfa2_0(active0, 536872960L, active1, 0L);
            }
            case 'M':
            case 'm': {
                return this.jjMoveStringLiteralDfa2_0(active0, 4096L, active1, 0L);
            }
            case 'N':
            case 'n': {
                if ((active0 & 0x40000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(1, 18, 15);
                }
                if ((active0 & 0x4000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(1, 26, 15);
                }
                return this.jjMoveStringLiteralDfa2_0(active0, 8194L, active1, 0L);
            }
            case 'O':
            case 'o': {
                return this.jjMoveStringLiteralDfa2_0(active0, 554076012800L, active1, 0L);
            }
            case 'R':
            case 'r': {
                if ((active0 & 0x8000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(1, 27, 15);
                }
                return this.jjMoveStringLiteralDfa2_0(active0, 1073873920L, active1, 0L);
            }
            case 'S':
            case 's': {
                if ((active0 & 0x4L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(1, 2, 15);
                }
                if ((active0 & 0x80000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(1, 19, 15);
                }
                break;
            }
            case 'U':
            case 'u': {
                return this.jjMoveStringLiteralDfa2_0(active0, 33554432L, active1, 0L);
            }
            case 'X':
            case 'x': {
                return this.jjMoveStringLiteralDfa2_0(active0, 2199023271944L, active1, 0L);
            }
            case '|': {
                if ((active0 & 0x800000000000000L) != 0x0L) {
                    return this.jjStopAtPos(1, 59);
                }
                break;
            }
        }
        return this.jjStartNfa_0(0, active0, active1);
    }
    
    private int jjMoveStringLiteralDfa2_0(final long old0, long active0, final long old1, long active1) {
        if (((active0 &= old0) | (active1 &= old1)) == 0x0L) {
            return this.jjStartNfa_0(0, old0, old1);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(1, active0, 0L);
            return 2;
        }
        switch (this.curChar) {
            case 'A':
            case 'a': {
                return this.jjMoveStringLiteralDfa3_0(active0, 536871040L);
            }
            case 'C':
            case 'c': {
                return this.jjMoveStringLiteralDfa3_0(active0, 8589934592L);
            }
            case 'D':
            case 'd': {
                if ((active0 & 0x2L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(2, 1, 15);
                }
                if ((active0 & 0x2000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(2, 13, 15);
                }
                break;
            }
            case 'E':
            case 'e': {
                return this.jjMoveStringLiteralDfa3_0(active0, 481036337152L);
            }
            case 'G':
            case 'g': {
                return this.jjMoveStringLiteralDfa3_0(active0, 268435456L);
            }
            case 'I':
            case 'i': {
                return this.jjMoveStringLiteralDfa3_0(active0, 2199023256584L);
            }
            case 'L':
            case 'l': {
                return this.jjMoveStringLiteralDfa3_0(active0, 17213423936L);
            }
            case 'M':
            case 'm': {
                return this.jjMoveStringLiteralDfa3_0(active0, 4194816L);
            }
            case 'N':
            case 'n': {
                if ((active0 & 0x800000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(2, 23, 15);
                }
                break;
            }
            case 'O':
            case 'o': {
                return this.jjMoveStringLiteralDfa3_0(active0, 1073872896L);
            }
            case 'P':
            case 'p': {
                return this.jjMoveStringLiteralDfa3_0(active0, 20480L);
            }
            case 'R':
            case 'r': {
                if ((active0 & 0x10000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(2, 16, 15);
                }
                if ((active0 & 0x8000000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(2, 39, 15);
                }
                return this.jjMoveStringLiteralDfa3_0(active0, 32768L);
            }
            case 'S':
            case 's': {
                return this.jjMoveStringLiteralDfa3_0(active0, 2096L);
            }
            case 'T':
            case 't': {
                if ((active0 & 0x1000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(2, 24, 15);
                }
                if ((active0 & 0x800000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(2, 35, 15);
                }
                return this.jjMoveStringLiteralDfa3_0(active0, 1101660160000L);
            }
            case 'W':
            case 'w': {
                return this.jjMoveStringLiteralDfa3_0(active0, 4294967296L);
            }
            case 'X':
            case 'x': {
                return this.jjMoveStringLiteralDfa3_0(active0, 2097152L);
            }
        }
        return this.jjStartNfa_0(1, active0, 0L);
    }
    
    private int jjMoveStringLiteralDfa3_0(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_0(1, old0, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(2, active0, 0L);
            return 3;
        }
        switch (this.curChar) {
            case 'B':
            case 'b': {
                return this.jjMoveStringLiteralDfa4_0(active0, 4194304L);
            }
            case 'C':
            case 'c': {
                return this.jjMoveStringLiteralDfa4_0(active0, 1048576L);
            }
            case 'E':
            case 'e': {
                if ((active0 & 0x10L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(3, 4, 15);
                }
                if ((active0 & 0x800L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(3, 11, 15);
                }
                return this.jjMoveStringLiteralDfa4_0(active0, 17448305152L);
            }
            case 'H':
            case 'h': {
                if ((active0 & 0x10000000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(3, 40, 15);
                }
                break;
            }
            case 'L':
            case 'l': {
                if ((active0 & 0x40L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(3, 6, 15);
                }
                if ((active0 & 0x2000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(3, 25, 15);
                }
                return this.jjMoveStringLiteralDfa4_0(active0, 17408L);
            }
            case 'M':
            case 'm': {
                if ((active0 & 0x20000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(3, 17, 15);
                }
                break;
            }
            case 'N':
            case 'n': {
                if ((active0 & 0x20000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(3, 29, 15);
                }
                if ((active0 & 0x1000000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(3, 36, 15);
                }
                if ((active0 & 0x2000000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(3, 37, 15);
                }
                break;
            }
            case 'P':
            case 'p': {
                return this.jjMoveStringLiteralDfa4_0(active0, 1073741952L);
            }
            case 'R':
            case 'r': {
                return this.jjMoveStringLiteralDfa4_0(active0, 274880004096L);
            }
            case 'S':
            case 's': {
                if ((active0 & 0x8L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(3, 3, 15);
                }
                if ((active0 & 0x100000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(3, 32, 15);
                }
                return this.jjMoveStringLiteralDfa4_0(active0, 2199023288320L);
            }
            case 'T':
            case 't': {
                if ((active0 & 0x20L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(3, 5, 15);
                }
                return this.jjMoveStringLiteralDfa4_0(active0, 8589938688L);
            }
            case 'U':
            case 'u': {
                return this.jjMoveStringLiteralDfa4_0(active0, 2147483904L);
            }
        }
        return this.jjStartNfa_0(2, active0, 0L);
    }
    
    private int jjMoveStringLiteralDfa4_0(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_0(2, old0, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(3, active0, 0L);
            return 4;
        }
        switch (this.curChar) {
            case 'A':
            case 'a': {
                return this.jjMoveStringLiteralDfa5_0(active0, 16384L);
            }
            case 'C':
            case 'c': {
                return this.jjMoveStringLiteralDfa5_0(active0, 17179869184L);
            }
            case 'E':
            case 'e': {
                if ((active0 & 0x4000000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(4, 38, 15);
                }
                return this.jjMoveStringLiteralDfa5_0(active0, 1077936128L);
            }
            case 'H':
            case 'h': {
                return this.jjMoveStringLiteralDfa5_0(active0, 1048576L);
            }
            case 'I':
            case 'i': {
                return this.jjMoveStringLiteralDfa5_0(active0, 8589934592L);
            }
            case 'L':
            case 'l': {
                return this.jjMoveStringLiteralDfa5_0(active0, 1024L);
            }
            case 'M':
            case 'm': {
                return this.jjMoveStringLiteralDfa5_0(active0, 256L);
            }
            case 'N':
            case 'n': {
                return this.jjMoveStringLiteralDfa5_0(active0, 512L);
            }
            case 'O':
            case 'o': {
                return this.jjMoveStringLiteralDfa5_0(active0, 2097152L);
            }
            case 'R':
            case 'r': {
                return this.jjMoveStringLiteralDfa5_0(active0, 2147483648L);
            }
            case 'S':
            case 's': {
                if ((active0 & 0x10000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(4, 28, 15);
                }
                break;
            }
            case 'T':
            case 't': {
                return this.jjMoveStringLiteralDfa5_0(active0, 2199023288448L);
            }
            case 'Y':
            case 'y': {
                if ((active0 & 0x1000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(4, 12, 15);
                }
                break;
            }
        }
        return this.jjStartNfa_0(3, active0, 0L);
    }
    
    private int jjMoveStringLiteralDfa5_0(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_0(3, old0, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(4, active0, 0L);
            return 5;
        }
        switch (this.curChar) {
            case 'E':
            case 'e': {
                return this.jjMoveStringLiteralDfa6_0(active0, 1048704L);
            }
            case 'I':
            case 'i': {
                return this.jjMoveStringLiteralDfa6_0(active0, 2199023271936L);
            }
            case 'N':
            case 'n': {
                if ((active0 & 0x80000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(5, 31, 15);
                }
                return this.jjMoveStringLiteralDfa6_0(active0, 256L);
            }
            case 'O':
            case 'o': {
                return this.jjMoveStringLiteralDfa6_0(active0, 8589934592L);
            }
            case 'R':
            case 'r': {
                if ((active0 & 0x400000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(5, 22, 15);
                }
                return this.jjMoveStringLiteralDfa6_0(active0, 1073774592L);
            }
            case 'S':
            case 's': {
                return this.jjMoveStringLiteralDfa6_0(active0, 512L);
            }
            case 'T':
            case 't': {
                if ((active0 & 0x400000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(5, 34, 15);
                }
                return this.jjMoveStringLiteralDfa6_0(active0, 1024L);
            }
            case 'W':
            case 'w': {
                return this.jjMoveStringLiteralDfa6_0(active0, 2097152L);
            }
            default: {
                return this.jjStartNfa_0(4, active0, 0L);
            }
        }
    }
    
    private int jjMoveStringLiteralDfa6_0(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_0(4, old0, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(5, active0, 0L);
            return 6;
        }
        switch (this.curChar) {
            case 'H':
            case 'h': {
                return this.jjMoveStringLiteralDfa7_0(active0, 1024L);
            }
            case 'I':
            case 'i': {
                return this.jjMoveStringLiteralDfa7_0(active0, 512L);
            }
            case 'N':
            case 'n': {
                if ((active0 & 0x4000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(6, 14, 15);
                }
                return this.jjMoveStringLiteralDfa7_0(active0, 2207613190144L);
            }
            case 'O':
            case 'o': {
                return this.jjMoveStringLiteralDfa7_0(active0, 32768L);
            }
            case 'R':
            case 'r': {
                return this.jjMoveStringLiteralDfa7_0(active0, 128L);
            }
            case 'S':
            case 's': {
                if ((active0 & 0x100L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(6, 8, 15);
                }
                if ((active0 & 0x100000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(6, 20, 15);
                }
                if ((active0 & 0x200000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(6, 21, 15);
                }
                break;
            }
            case 'T':
            case 't': {
                return this.jjMoveStringLiteralDfa7_0(active0, 1073741824L);
            }
        }
        return this.jjStartNfa_0(5, active0, 0L);
    }
    
    private int jjMoveStringLiteralDfa7_0(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_0(5, old0, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(6, active0, 0L);
            return 7;
        }
        switch (this.curChar) {
            case 'G':
            case 'g': {
                if ((active0 & 0x20000000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(7, 41, 15);
                }
                break;
            }
            case 'I':
            case 'i': {
                return this.jjMoveStringLiteralDfa8_0(active0, 1073741824L);
            }
            case 'O':
            case 'o': {
                return this.jjMoveStringLiteralDfa8_0(active0, 512L);
            }
            case 'R':
            case 'r': {
                return this.jjMoveStringLiteralDfa8_0(active0, 1024L);
            }
            case 'S':
            case 's': {
                if ((active0 & 0x80L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(7, 7, 15);
                }
                if ((active0 & 0x200000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(7, 33, 15);
                }
                break;
            }
            case 'W':
            case 'w': {
                return this.jjMoveStringLiteralDfa8_0(active0, 32768L);
            }
        }
        return this.jjStartNfa_0(6, active0, 0L);
    }
    
    private int jjMoveStringLiteralDfa8_0(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_0(6, old0, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(7, active0, 0L);
            return 8;
        }
        switch (this.curChar) {
            case 'E':
            case 'e': {
                return this.jjMoveStringLiteralDfa9_0(active0, 1073741824L);
            }
            case 'N':
            case 'n': {
                if ((active0 & 0x200L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(8, 9, 15);
                }
                break;
            }
            case 'O':
            case 'o': {
                return this.jjMoveStringLiteralDfa9_0(active0, 1024L);
            }
            case 'S':
            case 's': {
                return this.jjMoveStringLiteralDfa9_0(active0, 32768L);
            }
        }
        return this.jjStartNfa_0(7, active0, 0L);
    }
    
    private int jjMoveStringLiteralDfa9_0(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_0(7, old0, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(8, active0, 0L);
            return 9;
        }
        switch (this.curChar) {
            case 'E':
            case 'e': {
                return this.jjMoveStringLiteralDfa10_0(active0, 32768L);
            }
            case 'S':
            case 's': {
                if ((active0 & 0x40000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(9, 30, 15);
                }
                break;
            }
            case 'U':
            case 'u': {
                return this.jjMoveStringLiteralDfa10_0(active0, 1024L);
            }
        }
        return this.jjStartNfa_0(8, active0, 0L);
    }
    
    private int jjMoveStringLiteralDfa10_0(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_0(8, old0, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(9, active0, 0L);
            return 10;
        }
        switch (this.curChar) {
            case 'G':
            case 'g': {
                return this.jjMoveStringLiteralDfa11_0(active0, 1024L);
            }
            case 'T':
            case 't': {
                if ((active0 & 0x8000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(10, 15, 15);
                }
                break;
            }
        }
        return this.jjStartNfa_0(9, active0, 0L);
    }
    
    private int jjMoveStringLiteralDfa11_0(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_0(9, old0, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(10, active0, 0L);
            return 11;
        }
        switch (this.curChar) {
            case 'H':
            case 'h': {
                if ((active0 & 0x400L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(11, 10, 15);
                }
                break;
            }
        }
        return this.jjStartNfa_0(10, active0, 0L);
    }
    
    private int jjStartNfaWithStates_0(final int pos, final int kind, final int state) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            return pos + 1;
        }
        return this.jjMoveNfa_0(state, pos + 1);
    }
    
    private int jjMoveNfa_0(final int startState, int curPos) {
        int startsAt = 0;
        this.jjnewStateCnt = 44;
        int i = 1;
        this.jjstateSet[0] = startState;
        int kind = Integer.MAX_VALUE;
        while (true) {
            if (++this.jjround == Integer.MAX_VALUE) {
                this.ReInitRounds();
            }
            if (this.curChar < '@') {
                final long l = 1L << this.curChar;
                do {
                    switch (this.jjstateSet[--i]) {
                        case 3: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                if (kind > 74) {
                                    kind = 74;
                                }
                                this.jjCheckNAddStates(0, 6);
                                continue;
                            }
                            if (this.curChar == '&') {
                                this.jjAddStates(7, 8);
                                continue;
                            }
                            if (this.curChar == '.') {
                                this.jjCheckNAddTwoStates(34, 35);
                                continue;
                            }
                            if (this.curChar == '$') {
                                if (kind > 81) {
                                    kind = 81;
                                }
                                this.jjCheckNAdd(15);
                                continue;
                            }
                            if (this.curChar == '\"') {
                                this.jjCheckNAddStates(9, 11);
                                continue;
                            }
                            if (this.curChar == '\'') {
                                this.jjCheckNAddStates(12, 14);
                                continue;
                            }
                            if (this.curChar == '/') {
                                this.jjstateSet[this.jjnewStateCnt++] = 2;
                                continue;
                            }
                            continue;
                        }
                        case 44: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                if (kind > 76) {
                                    kind = 76;
                                }
                                this.jjCheckNAdd(35);
                            }
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(34, 24);
                                continue;
                            }
                            continue;
                        }
                        case 0: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 1;
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if ((0xFFFF7FFFFFFFFFFFL & l) != 0x0L && kind > 47) {
                                kind = 47;
                                continue;
                            }
                            continue;
                        }
                        case 2: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 0;
                                continue;
                            }
                            continue;
                        }
                        case 4:
                        case 6: {
                            if (this.curChar == '\'') {
                                this.jjCheckNAddStates(12, 14);
                                continue;
                            }
                            continue;
                        }
                        case 5: {
                            if ((0xFFFFFF7FFFFFFFFFL & l) != 0x0L) {
                                this.jjCheckNAddStates(12, 14);
                                continue;
                            }
                            continue;
                        }
                        case 7: {
                            if (this.curChar == '\'') {
                                this.jjstateSet[this.jjnewStateCnt++] = 6;
                                continue;
                            }
                            continue;
                        }
                        case 8: {
                            if (this.curChar == '\'' && kind > 78) {
                                kind = 78;
                                continue;
                            }
                            continue;
                        }
                        case 9:
                        case 11: {
                            if (this.curChar == '\"') {
                                this.jjCheckNAddStates(9, 11);
                                continue;
                            }
                            continue;
                        }
                        case 10: {
                            if ((0xFFFFFFFBFFFFFFFFL & l) != 0x0L) {
                                this.jjCheckNAddStates(9, 11);
                                continue;
                            }
                            continue;
                        }
                        case 12: {
                            if (this.curChar == '\"') {
                                this.jjstateSet[this.jjnewStateCnt++] = 11;
                                continue;
                            }
                            continue;
                        }
                        case 13: {
                            if (this.curChar == '\"' && kind > 79) {
                                kind = 79;
                                continue;
                            }
                            continue;
                        }
                        case 14: {
                            if (this.curChar != '$') {
                                continue;
                            }
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAdd(15);
                            continue;
                        }
                        case 15: {
                            if ((0x3FF001000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAdd(15);
                            continue;
                        }
                        case 17: {
                            if ((0xFFFFFFFFFFFFDBFFL & l) != 0x0L) {
                                this.jjAddStates(15, 17);
                                continue;
                            }
                            continue;
                        }
                        case 21: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 74) {
                                kind = 74;
                            }
                            this.jjCheckNAddStates(0, 6);
                            continue;
                        }
                        case 22: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 74) {
                                kind = 74;
                            }
                            this.jjCheckNAdd(22);
                            continue;
                        }
                        case 23: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(23, 24);
                                continue;
                            }
                            continue;
                        }
                        case 25: {
                            if ((0x280000000000L & l) != 0x0L) {
                                this.jjCheckNAdd(26);
                                continue;
                            }
                            continue;
                        }
                        case 26: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 75) {
                                kind = 75;
                            }
                            this.jjCheckNAdd(26);
                            continue;
                        }
                        case 27: {
                            if (this.curChar == '.') {
                                this.jjCheckNAddTwoStates(28, 24);
                                continue;
                            }
                            continue;
                        }
                        case 28: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(28, 24);
                                continue;
                            }
                            continue;
                        }
                        case 29: {
                            if (this.curChar != '.') {
                                continue;
                            }
                            if (kind > 76) {
                                kind = 76;
                            }
                            this.jjCheckNAdd(30);
                            continue;
                        }
                        case 30: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 76) {
                                kind = 76;
                            }
                            this.jjCheckNAdd(30);
                            continue;
                        }
                        case 31: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 76) {
                                kind = 76;
                            }
                            this.jjCheckNAddStates(18, 20);
                            continue;
                        }
                        case 32: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                this.jjCheckNAddStates(21, 24);
                                continue;
                            }
                            continue;
                        }
                        case 33: {
                            if (this.curChar == '.') {
                                this.jjCheckNAddTwoStates(34, 35);
                                continue;
                            }
                            continue;
                        }
                        case 34: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(34, 24);
                                continue;
                            }
                            continue;
                        }
                        case 35: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 76) {
                                kind = 76;
                            }
                            this.jjCheckNAdd(35);
                            continue;
                        }
                        case 36: {
                            if (this.curChar == '&') {
                                this.jjAddStates(7, 8);
                                continue;
                            }
                            continue;
                        }
                        case 38: {
                            if ((0xFFFFFFFFFFFFDBFFL & l) != 0x0L) {
                                this.jjAddStates(25, 27);
                                continue;
                            }
                            continue;
                        }
                        case 43: {
                            if ((0x3FF001000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 84) {
                                kind = 84;
                            }
                            this.jjstateSet[this.jjnewStateCnt++] = 43;
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else if (this.curChar < '\u0080') {
                final long l = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 3: {
                            if ((0x7FFFFFE87FFFFFEL & l) != 0x0L) {
                                if (kind > 81) {
                                    kind = 81;
                                }
                                this.jjCheckNAdd(15);
                                continue;
                            }
                            if (this.curChar == '[') {
                                this.jjCheckNAddStates(15, 17);
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if (kind > 47) {
                                kind = 47;
                                continue;
                            }
                            continue;
                        }
                        case 14:
                        case 15: {
                            if ((0x7FFFFFE87FFFFFEL & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAdd(15);
                            continue;
                        }
                        case 16: {
                            if (this.curChar == '[') {
                                this.jjCheckNAddStates(15, 17);
                                continue;
                            }
                            continue;
                        }
                        case 17: {
                            if ((0xFFFFFFFFDFFFFFFFL & l) != 0x0L) {
                                this.jjCheckNAddStates(15, 17);
                                continue;
                            }
                            continue;
                        }
                        case 18: {
                            if (this.curChar == ']') {
                                this.jjCheckNAddStates(15, 17);
                                continue;
                            }
                            continue;
                        }
                        case 19: {
                            if (this.curChar == ']') {
                                this.jjstateSet[this.jjnewStateCnt++] = 18;
                                continue;
                            }
                            continue;
                        }
                        case 20: {
                            if (this.curChar == ']' && kind > 82) {
                                kind = 82;
                                continue;
                            }
                            continue;
                        }
                        case 24: {
                            if ((0x2000000020L & l) != 0x0L) {
                                this.jjAddStates(28, 29);
                                continue;
                            }
                            continue;
                        }
                        case 37: {
                            if (this.curChar == '[') {
                                this.jjCheckNAddStates(25, 27);
                                continue;
                            }
                            continue;
                        }
                        case 38: {
                            if ((0xFFFFFFFFDFFFFFFFL & l) != 0x0L) {
                                this.jjCheckNAddStates(25, 27);
                                continue;
                            }
                            continue;
                        }
                        case 39: {
                            if (this.curChar == ']') {
                                this.jjCheckNAddStates(25, 27);
                                continue;
                            }
                            continue;
                        }
                        case 40: {
                            if (this.curChar == ']') {
                                this.jjstateSet[this.jjnewStateCnt++] = 39;
                                continue;
                            }
                            continue;
                        }
                        case 41: {
                            if (this.curChar == ']' && kind > 83) {
                                kind = 83;
                                continue;
                            }
                            continue;
                        }
                        case 42: {
                            if ((0x7FFFFFE07FFFFFEL & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 84) {
                                kind = 84;
                            }
                            this.jjCheckNAdd(43);
                            continue;
                        }
                        case 43: {
                            if ((0x7FFFFFE87FFFFFEL & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 84) {
                                kind = 84;
                            }
                            this.jjCheckNAdd(43);
                            continue;
                        }
                        default: {
                            continue;
                        }
                        case 5: {
                            this.jjAddStates(12, 14);
                            continue;
                        }
                        case 10: {
                            this.jjAddStates(9, 11);
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else {
                final int hiByte = this.curChar >> 8;
                final int i2 = hiByte >> 6;
                final long l2 = 1L << (hiByte & 0x3F);
                final int i3 = (this.curChar & '\u00ff') >> 6;
                final long l3 = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 3:
                        case 15: {
                            if (!jjCanMove_1(hiByte, i2, i3, l2, l3)) {
                                continue;
                            }
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAdd(15);
                            continue;
                        }
                        case 1: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3) && kind > 47) {
                                kind = 47;
                                continue;
                            }
                            continue;
                        }
                        case 5: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3)) {
                                this.jjAddStates(12, 14);
                                continue;
                            }
                            continue;
                        }
                        case 10: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3)) {
                                this.jjAddStates(9, 11);
                                continue;
                            }
                            continue;
                        }
                        case 17: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3)) {
                                this.jjAddStates(15, 17);
                                continue;
                            }
                            continue;
                        }
                        case 38: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3)) {
                                this.jjAddStates(25, 27);
                                continue;
                            }
                            continue;
                        }
                        case 43: {
                            if (!jjCanMove_1(hiByte, i2, i3, l2, l3)) {
                                continue;
                            }
                            if (kind > 84) {
                                kind = 84;
                            }
                            this.jjstateSet[this.jjnewStateCnt++] = 43;
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            if (kind != Integer.MAX_VALUE) {
                this.jjmatchedKind = kind;
                this.jjmatchedPos = curPos;
                kind = Integer.MAX_VALUE;
            }
            ++curPos;
            final int n = i = this.jjnewStateCnt;
            final int n2 = 44;
            final int jjnewStateCnt = startsAt;
            this.jjnewStateCnt = jjnewStateCnt;
            if (n == (startsAt = n2 - jjnewStateCnt)) {
                break;
            }
            try {
                this.curChar = this.input_stream.readChar();
            }
            catch (IOException e) {
                return curPos;
            }
        }
        return curPos;
    }
    
    private int jjMoveStringLiteralDfa0_3() {
        switch (this.curChar) {
            case '*': {
                return this.jjMoveStringLiteralDfa1_3(9007199254740992L);
            }
            default: {
                return 1;
            }
        }
    }
    
    private int jjMoveStringLiteralDfa1_3(final long active0) {
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            return 1;
        }
        switch (this.curChar) {
            case '/': {
                if ((active0 & 0x20000000000000L) != 0x0L) {
                    return this.jjStopAtPos(1, 53);
                }
                return 2;
            }
            default: {
                return 2;
            }
        }
    }
    
    private int jjMoveStringLiteralDfa0_1() {
        return this.jjMoveNfa_1(0, 0);
    }
    
    private int jjMoveNfa_1(final int startState, int curPos) {
        int startsAt = 0;
        this.jjnewStateCnt = 3;
        int i = 1;
        this.jjstateSet[0] = startState;
        int kind = Integer.MAX_VALUE;
        while (true) {
            if (++this.jjround == Integer.MAX_VALUE) {
                this.ReInitRounds();
            }
            if (this.curChar < '@') {
                final long l = 1L << this.curChar;
                do {
                    switch (this.jjstateSet[--i]) {
                        case 0: {
                            if ((0x2400L & l) != 0x0L && kind > 51) {
                                kind = 51;
                            }
                            if (this.curChar == '\r') {
                                this.jjstateSet[this.jjnewStateCnt++] = 1;
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if (this.curChar == '\n' && kind > 51) {
                                kind = 51;
                                continue;
                            }
                            continue;
                        }
                        case 2: {
                            if (this.curChar == '\r') {
                                this.jjstateSet[this.jjnewStateCnt++] = 1;
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else if (this.curChar < '\u0080') {
                final long l = 1L << (this.curChar & '?');
                do {
                    final int n = this.jjstateSet[--i];
                } while (i != startsAt);
            }
            else {
                final int hiByte = this.curChar >> 8;
                final int i2 = hiByte >> 6;
                final long l2 = 1L << (hiByte & 0x3F);
                final int i3 = (this.curChar & '\u00ff') >> 6;
                final long l3 = 1L << (this.curChar & '?');
                do {
                    final int n2 = this.jjstateSet[--i];
                } while (i != startsAt);
            }
            if (kind != Integer.MAX_VALUE) {
                this.jjmatchedKind = kind;
                this.jjmatchedPos = curPos;
                kind = Integer.MAX_VALUE;
            }
            ++curPos;
            final int n3 = i = this.jjnewStateCnt;
            final int n4 = 3;
            final int jjnewStateCnt = startsAt;
            this.jjnewStateCnt = jjnewStateCnt;
            if (n3 == (startsAt = n4 - jjnewStateCnt)) {
                break;
            }
            try {
                this.curChar = this.input_stream.readChar();
            }
            catch (IOException e) {
                return curPos;
            }
        }
        return curPos;
    }
    
    private int jjMoveStringLiteralDfa0_2() {
        switch (this.curChar) {
            case '*': {
                return this.jjMoveStringLiteralDfa1_2(4503599627370496L);
            }
            default: {
                return 1;
            }
        }
    }
    
    private int jjMoveStringLiteralDfa1_2(final long active0) {
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            return 1;
        }
        switch (this.curChar) {
            case '/': {
                if ((active0 & 0x10000000000000L) != 0x0L) {
                    return this.jjStopAtPos(1, 52);
                }
                return 2;
            }
            default: {
                return 2;
            }
        }
    }
    
    private static final boolean jjCanMove_0(final int hiByte, final int i1, final int i2, final long l1, final long l2) {
        switch (hiByte) {
            case 0: {
                return (MdxParserImplTokenManager.jjbitVec2[i2] & l2) != 0x0L;
            }
            default: {
                return (MdxParserImplTokenManager.jjbitVec0[i1] & l1) != 0x0L;
            }
        }
    }
    
    private static final boolean jjCanMove_1(final int hiByte, final int i1, final int i2, final long l1, final long l2) {
        switch (hiByte) {
            case 0: {
                return (MdxParserImplTokenManager.jjbitVec4[i2] & l2) != 0x0L;
            }
            case 45: {
                return (MdxParserImplTokenManager.jjbitVec5[i2] & l2) != 0x0L;
            }
            case 48: {
                return (MdxParserImplTokenManager.jjbitVec6[i2] & l2) != 0x0L;
            }
            case 49: {
                return (MdxParserImplTokenManager.jjbitVec7[i2] & l2) != 0x0L;
            }
            case 51: {
                return (MdxParserImplTokenManager.jjbitVec8[i2] & l2) != 0x0L;
            }
            case 61: {
                return (MdxParserImplTokenManager.jjbitVec9[i2] & l2) != 0x0L;
            }
            default: {
                return (MdxParserImplTokenManager.jjbitVec3[i1] & l1) != 0x0L;
            }
        }
    }
    
    public MdxParserImplTokenManager(final SimpleCharStream stream) {
        this.debugStream = System.out;
        this.jjrounds = new int[44];
        this.jjstateSet = new int[88];
        this.jjimage = new StringBuilder();
        this.image = this.jjimage;
        this.curLexState = 0;
        this.defaultLexState = 0;
        this.input_stream = stream;
    }
    
    public MdxParserImplTokenManager(final SimpleCharStream stream, final int lexState) {
        this(stream);
        this.SwitchTo(lexState);
    }
    
    public void ReInit(final SimpleCharStream stream) {
        final int n = 0;
        this.jjnewStateCnt = n;
        this.jjmatchedPos = n;
        this.curLexState = this.defaultLexState;
        this.input_stream = stream;
        this.ReInitRounds();
    }
    
    private void ReInitRounds() {
        this.jjround = -2147483647;
        int i = 44;
        while (i-- > 0) {
            this.jjrounds[i] = Integer.MIN_VALUE;
        }
    }
    
    public void ReInit(final SimpleCharStream stream, final int lexState) {
        this.ReInit(stream);
        this.SwitchTo(lexState);
    }
    
    public void SwitchTo(final int lexState) {
        if (lexState >= 4 || lexState < 0) {
            throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2);
        }
        this.curLexState = lexState;
    }
    
    protected Token jjFillToken() {
        final String im = MdxParserImplTokenManager.jjstrLiteralImages[this.jjmatchedKind];
        final String curTokenImage = (im == null) ? this.input_stream.GetImage() : im;
        final int beginLine = this.input_stream.getBeginLine();
        final int beginColumn = this.input_stream.getBeginColumn();
        final int endLine = this.input_stream.getEndLine();
        final int endColumn = this.input_stream.getEndColumn();
        final Token t = Token.newToken(this.jjmatchedKind, curTokenImage);
        t.beginLine = beginLine;
        t.endLine = endLine;
        t.beginColumn = beginColumn;
        t.endColumn = endColumn;
        return t;
    }
    
    public Token getNextToken() {
        Token specialToken = null;
        int curPos = 0;
    Label_0004_Outer:
        while (true) {
        Label_0004:
            while (true) {
                try {
                    this.curChar = this.input_stream.BeginToken();
                }
                catch (IOException e) {
                    this.jjmatchedKind = 0;
                    final Token matchedToken = this.jjFillToken();
                    matchedToken.specialToken = specialToken;
                    return matchedToken;
                }
                (this.image = this.jjimage).setLength(0);
                this.jjimageLen = 0;
                while (true) {
                    switch (this.curLexState) {
                        case 0: {
                            try {
                                this.input_stream.backup(0);
                                while (this.curChar <= ' ' && (0x100003600L & 1L << this.curChar) != 0x0L) {
                                    this.curChar = this.input_stream.BeginToken();
                                }
                            }
                            catch (IOException e2) {
                                continue Label_0004;
                            }
                            this.jjmatchedKind = Integer.MAX_VALUE;
                            this.jjmatchedPos = 0;
                            curPos = this.jjMoveStringLiteralDfa0_0();
                            break;
                        }
                        case 1: {
                            this.jjmatchedKind = Integer.MAX_VALUE;
                            this.jjmatchedPos = 0;
                            curPos = this.jjMoveStringLiteralDfa0_1();
                            if (this.jjmatchedPos == 0 && this.jjmatchedKind > 54) {
                                this.jjmatchedKind = 54;
                                break;
                            }
                            break;
                        }
                        case 2: {
                            this.jjmatchedKind = Integer.MAX_VALUE;
                            this.jjmatchedPos = 0;
                            curPos = this.jjMoveStringLiteralDfa0_2();
                            if (this.jjmatchedPos == 0 && this.jjmatchedKind > 54) {
                                this.jjmatchedKind = 54;
                                break;
                            }
                            break;
                        }
                        case 3: {
                            this.jjmatchedKind = Integer.MAX_VALUE;
                            this.jjmatchedPos = 0;
                            curPos = this.jjMoveStringLiteralDfa0_3();
                            if (this.jjmatchedPos == 0 && this.jjmatchedKind > 54) {
                                this.jjmatchedKind = 54;
                                break;
                            }
                            break;
                        }
                    }
                    if (this.jjmatchedKind == Integer.MAX_VALUE) {
                        break Label_0004_Outer;
                    }
                    if (this.jjmatchedPos + 1 < curPos) {
                        this.input_stream.backup(curPos - this.jjmatchedPos - 1);
                    }
                    if ((MdxParserImplTokenManager.jjtoToken[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 0x3F)) != 0x0L) {
                        final Token matchedToken = this.jjFillToken();
                        matchedToken.specialToken = specialToken;
                        if (MdxParserImplTokenManager.jjnewLexState[this.jjmatchedKind] != -1) {
                            this.curLexState = MdxParserImplTokenManager.jjnewLexState[this.jjmatchedKind];
                        }
                        return matchedToken;
                    }
                    if ((MdxParserImplTokenManager.jjtoSkip[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 0x3F)) == 0x0L) {
                        this.jjimageLen += this.jjmatchedPos + 1;
                        if (MdxParserImplTokenManager.jjnewLexState[this.jjmatchedKind] != -1) {
                            this.curLexState = MdxParserImplTokenManager.jjnewLexState[this.jjmatchedKind];
                        }
                        curPos = 0;
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        try {
                            this.curChar = this.input_stream.readChar();
                            continue Label_0004_Outer;
                        }
                        catch (IOException ex) {}
                        break Label_0004_Outer;
                    }
                    if ((MdxParserImplTokenManager.jjtoSpecial[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 0x3F)) != 0x0L) {
                        final Token matchedToken = this.jjFillToken();
                        if (specialToken == null) {
                            specialToken = matchedToken;
                        }
                        else {
                            matchedToken.specialToken = specialToken;
                            final Token token = specialToken;
                            final Token next = matchedToken;
                            token.next = next;
                            specialToken = next;
                        }
                        this.SkipLexicalActions(matchedToken);
                    }
                    else {
                        this.SkipLexicalActions(null);
                    }
                    if (MdxParserImplTokenManager.jjnewLexState[this.jjmatchedKind] != -1) {
                        this.curLexState = MdxParserImplTokenManager.jjnewLexState[this.jjmatchedKind];
                        break;
                    }
                    break;
                }
                break;
            }
        }
        int error_line = this.input_stream.getEndLine();
        int error_column = this.input_stream.getEndColumn();
        String error_after = null;
        boolean EOFSeen = false;
        try {
            this.input_stream.readChar();
            this.input_stream.backup(1);
        }
        catch (IOException e3) {
            EOFSeen = true;
            error_after = ((curPos <= 1) ? "" : this.input_stream.GetImage());
            if (this.curChar == '\n' || this.curChar == '\r') {
                ++error_line;
                error_column = 0;
            }
            else {
                ++error_column;
            }
        }
        if (!EOFSeen) {
            this.input_stream.backup(1);
            error_after = ((curPos <= 1) ? "" : this.input_stream.GetImage());
        }
        throw new TokenMgrError(EOFSeen, this.curLexState, error_line, error_column, error_after, this.curChar, 0);
    }
    
    void SkipLexicalActions(final Token matchedToken) {
        final int jjmatchedKind = this.jjmatchedKind;
    }
    
    private void jjCheckNAdd(final int state) {
        if (this.jjrounds[state] != this.jjround) {
            this.jjstateSet[this.jjnewStateCnt++] = state;
            this.jjrounds[state] = this.jjround;
        }
    }
    
    private void jjAddStates(int start, final int end) {
        do {
            this.jjstateSet[this.jjnewStateCnt++] = MdxParserImplTokenManager.jjnextStates[start];
        } while (start++ != end);
    }
    
    private void jjCheckNAddTwoStates(final int state1, final int state2) {
        this.jjCheckNAdd(state1);
        this.jjCheckNAdd(state2);
    }
    
    private void jjCheckNAddStates(int start, final int end) {
        do {
            this.jjCheckNAdd(MdxParserImplTokenManager.jjnextStates[start]);
        } while (start++ != end);
    }
    
    static {
        jjbitVec0 = new long[] { -2L, -1L, -1L, -1L };
        jjbitVec2 = new long[] { 0L, 0L, -1L, -1L };
        jjbitVec3 = new long[] { 2301339413881290750L, -16384L, 4294967295L, 432345564227567616L };
        jjbitVec4 = new long[] { 0L, 0L, 0L, -36028797027352577L };
        jjbitVec5 = new long[] { 274877906943L, 0L, 0L, 0L };
        jjbitVec6 = new long[] { 0L, -1L, -1L, -1L };
        jjbitVec7 = new long[] { -1L, -1L, 65535L, 0L };
        jjbitVec8 = new long[] { -1L, -1L, 0L, 0L };
        jjbitVec9 = new long[] { 70368744177663L, 0L, 0L, 0L };
        jjnextStates = new int[] { 22, 23, 27, 24, 29, 31, 32, 37, 42, 10, 12, 13, 5, 7, 8, 17, 19, 20, 29, 30, 31, 27, 28, 24, 32, 38, 40, 41, 25, 26 };
        jjstrLiteralImages = new String[] { "", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "*", "!", ":", ",", "||", ".", "=", ">=", ">", "{", "<=", "(", "<", "-", "<>", "+", "}", ")", "/", null, null, null, null, null, null, null, null, null, null, null, null, null };
        lexStateNames = new String[] { "DEFAULT", "IN_SINGLE_LINE_COMMENT", "IN_FORMAL_COMMENT", "IN_MULTI_LINE_COMMENT" };
        jjnewLexState = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 1, 1, 3, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        jjtoToken = new long[] { -36024398972452865L, 2023423L };
        jjtoSkip = new long[] { 15898938137640960L, 0L };
        jjtoSpecial = new long[] { 15762598695796736L, 0L };
        jjtoMore = new long[] { 20125460834811904L, 0L };
    }
}
