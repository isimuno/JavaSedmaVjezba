package covidportal.main;

import covidportal.enumeracija.VrijednostiSimptoma;
import covidportal.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GlavnaDatoteke {

    public static List<Zupanija> dohvatiZupanije() throws IOException {
        boolean prekid = false;
        Integer brZup = 0;
        List<Zupanija> listaZupanija = new ArrayList<>();
        do {
            try {
                Long idZupanije = Long.valueOf(Files.readAllLines(Paths.get("dat/zupanije.txt")).get(brZup));
                System.out.println("Id zupanije:  " + idZupanije);
                String naziv = Files.readAllLines(Paths.get("dat/zupanije.txt")).get(brZup + 1);
                System.out.println("Naziv zupanije: " + naziv);
                Integer brojStanovnika = Integer.valueOf(Files.readAllLines(Paths.get("dat/zupanije.txt")).get(brZup + 2));
                System.out.println("Broj stanovnika: " + brojStanovnika);
                Integer brojzarazenihStanovnika = Integer.valueOf(Files.readAllLines(Paths.get("dat/zupanije.txt")).get(brZup + 3));
                System.out.println("Broj zarazenih: " + brojzarazenihStanovnika);
                Zupanija novaZupanija = new Zupanija(idZupanije, naziv, brojStanovnika, brojzarazenihStanovnika);
                listaZupanija.add(novaZupanija);
                brZup += 4;
            } catch (Exception ex) {
                prekid = true;
            }
        } while (prekid == false);

        return listaZupanija;
    }

    public static List<Simptom> dohvatiSimptome() throws IOException {
        boolean prekid = false;
        Integer brSimp = 0;
        List<Simptom> listaSimptoma = new ArrayList<>();
        do {
            try {
                Long idSimptoma = Long.valueOf(Files.readAllLines(Paths.get("dat/simptomi.txt")).get(brSimp));
                System.out.println("Id " + idSimptoma);
                String naziv = Files.readAllLines(Paths.get("dat/simptomi.txt")).get(brSimp + 1);
                System.out.println("Naziv " + naziv);
                String vrijednost = (Files.readAllLines(Paths.get("dat/simptomi.txt")).get(brSimp + 2));
                System.out.println("Vrijednost " + vrijednost);
                VrijednostiSimptoma vrijednostiSimptoma = null;
                if (vrijednost.equals(VrijednostiSimptoma.VRIJEDNOSTI_SIMPTOMA1.naziv)) {
                    vrijednostiSimptoma = VrijednostiSimptoma.VRIJEDNOSTI_SIMPTOMA1;

                } else if (vrijednost.equals(VrijednostiSimptoma.VRIJEDNOSTI_SIMPTOMA2.naziv)) {
                    vrijednostiSimptoma = VrijednostiSimptoma.VRIJEDNOSTI_SIMPTOMA2;

                } else if (vrijednost.equals(VrijednostiSimptoma.VRIJEDNOSTI_SIMPTOMA3.naziv)) {
                    vrijednostiSimptoma = VrijednostiSimptoma.VRIJEDNOSTI_SIMPTOMA3;

                } else {
                    System.out.println("Molimo koristite samo zadane vrijednosti (RIJETKO;SREDNJE;ČESTO)!");

                }
                Simptom noviSimptom = new Simptom(idSimptoma, naziv, vrijednostiSimptoma);
                brSimp += 3;
                listaSimptoma.add(noviSimptom);
            } catch (Exception ex) {
                prekid = true;
            }
        } while (prekid == false);

        return listaSimptoma;
    }


    public static List<Osoba> dohvatiOsobe() throws IOException {
        boolean prekid = false;
        Integer brOsb = 0;
        List<Osoba> listaOsoba = new ArrayList<>();
        List<Zupanija> listaZupanija = dohvatiZupanije();
        List<Bolest> listaBolesti = dohvatiBolesti();
        List<Bolest> listaVirusa = dohvatiViruse();

        do {
            try {
                Integer starost = 0;
                Zupanija zupanijaPrebivalista = new Zupanija(Long.valueOf(1), "Naziv", 0, 0);
                Bolest zarazenBolescu = new Bolest(Long.valueOf(1), "Naziv", null);
                List<Osoba> kontaktOsobe = new ArrayList<>();

                Long idOsobe = Long.valueOf(Files.readAllLines(Paths.get("dat/osobe.txt")).get(brOsb));
                System.out.println("Id osobe: " + idOsobe);
                String ime = Files.readAllLines(Paths.get("dat/osobe.txt")).get(brOsb + 1);
                System.out.println("Ime osobe: " + ime);
                String prezime = Files.readAllLines(Paths.get("dat/osobe.txt")).get(brOsb + 2);
                System.out.println("Prezime osobe: " + prezime);
                starost = Integer.valueOf(Files.readAllLines(Paths.get("dat/osobe.txt")).get(brOsb + 3));
                System.out.println("Starost osobe: " + starost);

                Integer idZupanije = Integer.valueOf(Files.readAllLines(Paths.get("dat/osobe.txt")).get(brOsb + 4));
                for (int j = 0; j < listaZupanija.size(); j++) {
                    Integer indexZupanije = Math.toIntExact(listaZupanija.get(j).getId());
                    if (idZupanije == indexZupanije) {
                        zupanijaPrebivalista = listaZupanija.get(j);
                    }
                }
                System.out.println("Zupanija prebivalista: " + zupanijaPrebivalista.getNaziv());


                Integer idBolesti = Integer.valueOf(Files.readAllLines(Paths.get("dat/osobe.txt")).get(brOsb + 5));
                for (int j = 0; j < listaBolesti.size(); j++) {
                    Integer indexBolesti = Math.toIntExact(listaBolesti.get(j).getId());
                    if (idBolesti == indexBolesti) {
                        zarazenBolescu = listaBolesti.get(j);
                    }
                }
                if(zarazenBolescu.getNaziv().equals("Naziv")){
                    for (int j = 0; j < listaVirusa.size(); j++) {
                        Integer indexBolesti = Math.toIntExact(listaVirusa.get(j).getId());
                        if (idBolesti == indexBolesti) {
                            zarazenBolescu = listaVirusa.get(j);
                        }
                    }
                }
                System.out.println("Zarazen bolescu: " + zarazenBolescu.getNaziv());

                if (Integer.valueOf(Files.readAllLines(Paths.get("dat/osobe.txt")).get(brOsb + 5)) == 0) {
                    System.out.println("Nema kontakt osoba! ");
                }


                if (brOsb < 1) {
                    Osoba novaOsoba = new Osoba.Builder()
                            .imaId(idOsobe)
                            .seZove(ime)
                            .sePreziva(prezime)
                            .imaGodina(starost)
                            .pripadaZupaniji(zupanijaPrebivalista)
                            .imaBolest(zarazenBolescu)
                            .build();
                    listaOsoba.add(novaOsoba);
                    brOsb += 7;
                } else {
                    String indexiKontaktOsoba = Files.readAllLines(Paths.get("dat/osobe.txt")).get(brOsb + 6);
                    String[] formatKontaktOsoba = indexiKontaktOsoba.split("[,]", 0);
                    System.out.println("Broj KontsktOsoba: " + formatKontaktOsoba.length);
                    for (int j = 0; j < formatKontaktOsoba.length; j++) {
                        System.out.println(formatKontaktOsoba[j]);
                        kontaktOsobe.add(listaOsoba.get(Integer.parseInt(formatKontaktOsoba[j]) - 1));
                    }

                    Osoba novaOsoba = new Osoba.Builder()
                            .imaId(idOsobe)
                            .seZove(ime)
                            .sePreziva(prezime)
                            .imaGodina(starost)
                            .pripadaZupaniji(zupanijaPrebivalista)
                            .imaBolest(zarazenBolescu)
                            .kontaktiraneOsobe(kontaktOsobe)
                            .build();
                    listaOsoba.add(novaOsoba);
                    brOsb += 7;
                }
            } catch (Exception ex) {
                prekid = true;
            }
        } while (prekid == false);
        return listaOsoba;
    }


    public static List<Bolest> dohvatiBolesti() throws IOException {
        boolean prekid = false;
        Integer brBoles = 0;
        List<Bolest> listaBolesti = new ArrayList<>();
        do {
            try {
                Set<Simptom> simptomiBolesti = new LinkedHashSet<>();
                List<Simptom> listaSimptoma = dohvatiSimptome();
                Long vrstaBolesti = Long.valueOf(Files.readAllLines(Paths.get("dat/bolesti.txt")).get(brBoles));
                System.out.println("Id bolesti: " + vrstaBolesti);

                String nazivBolesti = Files.readAllLines(Paths.get("dat/bolesti.txt")).get(brBoles + 1);
                System.out.println("Naziv bolesti : " + nazivBolesti);

                String indexiSimptoma = Files.readAllLines(Paths.get("dat/bolesti.txt")).get(brBoles + 2);
                String[] formatSimptoma = indexiSimptoma.split("[,]", 0);
                System.out.println("Broj simptoma: " + formatSimptoma.length);
                for (int j = 0; j < formatSimptoma.length; j++) {
                    System.out.println(formatSimptoma[j]);
                    simptomiBolesti.add(listaSimptoma.get(Integer.parseInt(formatSimptoma[j]) - 1));
                }
                Bolest novaBolest = new Bolest(vrstaBolesti, nazivBolesti, simptomiBolesti);
                listaBolesti.add(novaBolest);
                brBoles+=3;

            } catch (Exception ex) {
                prekid = true;
            }
        } while (prekid == false);

        return listaBolesti;
    }
    public static List<Bolest> dohvatiViruse() throws IOException {
        boolean prekid = false;
        Integer brVirus = 0;
        List<Bolest> listaVirusa = new ArrayList<>();
        List<Simptom> listaSimptoma =dohvatiSimptome();
        do {
            try {
                Set<Simptom> simptomiBolesti = new LinkedHashSet<>();
                Long idVirusa = Long.valueOf(Files.readAllLines(Paths.get("dat/virusi.txt")).get(brVirus));
                System.out.println("Id virusa: " + idVirusa);

                String nazivVirusa = Files.readAllLines(Paths.get("dat/virusi.txt")).get(brVirus + 1);
                System.out.println("Naziv virusa : " + nazivVirusa);

                String indexiSimptoma = Files.readAllLines(Paths.get("dat/virusi.txt")).get(brVirus + 2);
                String[] formatSimptoma = indexiSimptoma.split("[,]", 0);
                System.out.println("Broj simptoma: " + formatSimptoma.length);
                for (int j = 0; j < formatSimptoma.length; j++) {
                    System.out.println(formatSimptoma[j]);
                    simptomiBolesti.add(listaSimptoma.get(Integer.parseInt(formatSimptoma[j]) - 1));
                }
                Bolest noviVirus = new Virus(idVirusa, nazivVirusa, simptomiBolesti);
                listaVirusa.add(noviVirus);
                brVirus+=3;

            } catch (Exception ex) {
                prekid = true;
            }
        } while (prekid == false);

        return listaVirusa;
    }
}


