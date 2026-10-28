package fr.efrei.transfret;

public enum TypeClient implements PolitiqueRemise {
    PARTICULIER(false, false) {
        @Override
        public double appliquerRemise(double prix) {
            return prix;
        }
    },
    ENTREPRISE(true, false) {
        @Override
        public double appliquerRemise(double prix) {
            double remise = prix > SEUIL_REMISE_GRANDE_EXPEDITION
                    ? REMISE_ENTREPRISE_GRANDE_EXPEDITION
                    : REMISE_ENTREPRISE;
            return prix * (1 - remise);
        }
    },
    PREMIUM(false, true) {
        @Override
        public double appliquerRemise(double prix) {
            return prix * (1 - REMISE_PREMIUM);
        }
    };

    private static final double REMISE_ENTREPRISE = 0.10;
    private static final double REMISE_ENTREPRISE_GRANDE_EXPEDITION = 0.15;
    private static final double REMISE_PREMIUM = 0.20;
    private static final double SEUIL_REMISE_GRANDE_EXPEDITION = 5000.0;
    private final boolean exigeSiret;
    private final boolean recoitSms;

    TypeClient(boolean exigeSiret, boolean recoitSms) {
        this.exigeSiret = exigeSiret;
        this.recoitSms = recoitSms;
    }

    public boolean exigeSiret() {
        return exigeSiret;
    }

    public boolean recoitSms() {
        return recoitSms;
    }
}