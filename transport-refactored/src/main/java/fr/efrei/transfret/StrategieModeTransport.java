package fr.efrei.transfret;

/** Algorithmes et restrictions propres a un mode de transport. */
public interface StrategieModeTransport extends TarifTransport {
    int calculerDelai(int distance, boolean urgent);

    default boolean autoriseMarchandise(TypeMarchandise type) {
        return true;
    }

    default boolean autoriseUrgence() {
        return true;
    }
}
