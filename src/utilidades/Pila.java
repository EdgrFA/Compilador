package utilidades;

import java.util.Stack;

public class Pila<T> extends Stack{
    @Override
    public String toString(){
        String resultado = super.toString();
        return resultado.replace(",","").replace("[","").replace("]", "");
    }
}
