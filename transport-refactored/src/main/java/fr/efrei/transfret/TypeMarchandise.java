package fr.efrei.transfret;

public enum TypeMarchandise {

    STANDARD("Standard") {
        @Override
        public Prix appliquerSupplement(Prix prix) {
            return prix;
        }

    },
    FRAGILE("Fragile") {
        @Override
        public Prix appliquerSupplement(Prix prix) {
            return new SupplementFragile(prix);
        }

    },
    DANGEREUX("Dangereux") {
        @Override
        public Prix appliquerSupplement(Prix prix) {
            return new SupplementDangereux(prix);
        }

    },
    REFRIGERE("Réfrigéré") {
        @Override
        public Prix appliquerSupplement(Prix prix) {
            return new SupplementRefrigere(prix);
        }

        @Override
        public boolean estRefrigere() {
            return true;
        }
    };

    private final String libelle;

    TypeMarchandise(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public abstract Prix appliquerSupplement(Prix prix);

    public boolean estRefrigere() {
        return false;
    }
}