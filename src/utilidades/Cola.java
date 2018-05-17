package utilidades;

import java.util.LinkedList;

public class Cola extends LinkedList{
    @Override
    public String toString(){
        String resultado = super.toString();
        return resultado.replace(",","").replace("[","").replace("]", "");
    }
}
