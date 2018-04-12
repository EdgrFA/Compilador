package ExpresionesRegulares;

public class TokensER {
    public static int UNION;
    public static int CONC;
    public static int CERR_SUM;
    public static int CERR_ASTER;
    public static int CERR_SIGNO;
    public static int PAR_I;
    public static int PAR_D;
    public static int CORCH_I;
    public static int CORCH_D;
    public static int SIMB;
    public static int SEPARADOR;
    public static final int FIN = 0;

    public static void TokenInfo() {
        System.out.println("Token UNION = " + TokensER.UNION);
        System.out.println("Token CONC = " + TokensER.CONC);
        System.out.println("Token CERR_SUM = " + TokensER.CERR_SUM);
        System.out.println("Token CERR_ASTER = " + TokensER.CERR_ASTER);
        System.out.println("Token CERR_SIGNO = " + TokensER.CERR_SIGNO);
        System.out.println("Token PAR_I = " + TokensER.PAR_I);
        System.out.println("Token PAR_D = " + TokensER.PAR_D);
        System.out.println("Token CORCH_I = " + TokensER.CORCH_I);
        System.out.println("Token CORCH_D = " + TokensER.CORCH_D);
        System.out.println("Token SEPARADOR = " + TokensER.SEPARADOR);
        System.out.println("Token SIMB = " + TokensER.SIMB);
        System.out.println("");
    }
}
    