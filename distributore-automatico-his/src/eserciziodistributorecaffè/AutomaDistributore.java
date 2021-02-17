package eserciziodistributorecaffè;

public class AutomaDistributore implements State {

    private State stato;
    private InserimentoSoldi totale;

    public AutomaDistributore() {
        stato = new Attesa();
        totale = new InserimentoSoldi();
    }

    @Override
    public void next(Event e) {
        System.out.println("siamo nello stato " + e);
        stato.next(e);

    }

    private class Attesa implements State {

        @Override
        public void next(Event e) {
            if (e instanceof CaffèPreparazione) {
                if (totale.getTotale() < 0.45) {
                    System.out.println("Soldi insufficienti");
                } else if (totale.getTotale() >= 0.45) {
                    stato = new Erogazione();
                    System.out.println("Soldi sufficienti");
                } else {
                    System.out.println("errore");
                }
            } else if (e instanceof InserimentoSoldi) {
                //Soldi s = new InserimentoSoldi();
                totale.setTotale(0.50);
                System.out.println("Soldi inseriti");
            } else if (e instanceof Resto) {

                if (totale.getTotale() == 0) {
                    System.out.println("Resto non disponibile");
                    stato = new Attesa();
                } else if (totale.getTotale() > 0) {
                    System.out.println("resto ritirato = " + totale.getTotale() + " €");
                    totale.setTotale(0);
                    stato = new Attesa();
                }else{
                    System.out.println("errore");
                }

            } else {
                System.out.println("errore");
            }
        }

    }

    private class Erogazione implements State {

        @Override
        public void next(Event e) {
            if (e instanceof CaffèPronto) {
                totale.setTotale(totale.getTotale() - 0.45);
                System.out.println("soldi prelevati");
                stato = new Pronto();
            } else {
                System.out.println("errore");
            }
        }

    }

    private class Pronto implements State {

        @Override
        public void next(Event e) {
            if (e instanceof RitiroCaffè) {
                System.out.println("Caffè ritirato");
                stato = new Attesa();
            } else {
                System.out.println("Errore");
            }
        }

    }
}
