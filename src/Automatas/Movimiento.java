package Automatas;

import java.util.HashSet;

public interface Movimiento {
    HashSet<Estado> Mover(Estado e, char c);
    HashSet<Estado> MoverCjto(HashSet<Estado> E, char c);
}