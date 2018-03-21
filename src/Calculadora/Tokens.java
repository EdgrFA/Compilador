/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculadora;

/**
 *
 * @author xXEdG
 */
public class Tokens {
    public static int NUM;
    public static int SUM;
    public static int REST;
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

    public static void TokenInfo() {
        System.out.println("Token NUM = " + Tokens.NUM);
        System.out.println("Token SUM = " + Tokens.SUM);
        System.out.println("Token REST = " + Tokens.REST);
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
