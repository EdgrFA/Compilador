package Calculadora;

public class Tokens {
    public static int NUM;
    public static int SUMA;
    public static int RESTA;
    public static int DIV;
    public static int PROD;
    public static int PAR_I;
    public static int PAR_D;
    public static int SIN;
    public static int COS;
    public static int TAN;
    public static int POT;
    public static int EXP;
    public static int LN;
    public static int LOG;
    public static final int FIN = 1000;

    public static void TokenInfo() {
        System.out.println("Token NUM = " + Tokens.NUM);
        System.out.println("Token SUM = " + Tokens.SUMA);
        System.out.println("Token REST = " + Tokens.RESTA);
        System.out.println("Token DIV = " + Tokens.DIV);
        System.out.println("Token PROD = " + Tokens.PROD);
        System.out.println("Token PAR_I = " + Tokens.PAR_I);
        System.out.println("Token PAR_D = " + Tokens.PAR_D);
        System.out.println("Token SIN = " + Tokens.SIN);
        System.out.println("Token COS = " + Tokens.COS);
        System.out.println("Token TAN = " + Tokens.TAN);
        System.out.println("Token POT = " + Tokens.POT);
        System.out.println("Token EXP = " + Tokens.EXP);
        System.out.println("Token LN = " + Tokens.LN);
        System.out.println("Token LOG = " + Tokens.LOG);
        System.out.println("");
    }
}