package fr.efrei.transfret;

public enum TypeClient {
    PARTICULIER(false, false),
    ENTREPRISE(true, false),
    PREMIUM(false, true);

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
