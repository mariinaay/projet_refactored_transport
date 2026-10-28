package fr.efrei.transfret;

public enum TypeMarchandise {

    STANDARD("Standard") {
        @Override
        public Prix appliquerSupplement(Prix prix) {
            return prix;
        }

        @Override
        public boolean autoriseCamion(boolean adr) {
            return true;
        }

        @Override
        public boolean autoriseCamionFrigorifique() {
            return false;
        }

        @Override
        public boolean autoriseBateau(boolean conteneursRefrigeres) {
            return true;
        }
    },
    FRAGILE("Fragile") {
        @Override
        public Prix appliquerSupplement(Prix prix) {
            return new SupplementFragile(prix);
        }

        @Override
        public boolean autoriseCamion(boolean adr) {
            return true;
        }

        @Override
        public boolean autoriseCamionFrigorifique() {
            return false;
        }

        @Override
        public boolean autoriseBateau(boolean conteneursRefrigeres) {
            return true;
        }
    },
    DANGEREUX("Dangereux") {
        @Override
        public Prix appliquerSupplement(Prix prix) {
            return new SupplementDangereux(prix);
        }

        @Override
        public boolean autoriseCamion(boolean adr) {
            return adr;
        }

        @Override
        public boolean autoriseCamionFrigorifique() {
            return false;
        }

        @Override
        public boolean autoriseBateau(boolean conteneursRefrigeres) {
            return true;
        }
    },
    REFRIGERE("Réfrigéré") {
        @Override
        public Prix appliquerSupplement(Prix prix) {
            return new SupplementRefrigere(prix);
        }

        @Override
        public boolean autoriseCamion(boolean adr) {
            return false;
        }

        @Override
        public boolean autoriseCamionFrigorifique() {
            return true;
        }

        @Override
        public boolean autoriseBateau(boolean conteneursRefrigeres) {
            return conteneursRefrigeres;
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

    public abstract boolean autoriseCamion(boolean adr);

    public abstract boolean autoriseCamionFrigorifique();

    public abstract boolean autoriseBateau(boolean conteneursRefrigeres);

    public boolean estRefrigere() {
        return false;
    }
}