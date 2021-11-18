package covidportal.enumeracija;

public enum VrijednostiSimptoma {
    VRIJEDNOSTI_SIMPTOMA1("ČESTO"),
    VRIJEDNOSTI_SIMPTOMA2("SREDNJE"),
    VRIJEDNOSTI_SIMPTOMA3("RIJETKO");


    @Override
    public String toString() {
        return naziv;
    }

    public final String naziv;

    private VrijednostiSimptoma(String naziv){
        this.naziv=naziv;
    }

    public String getNaziv() {
        return naziv;
    }
}
