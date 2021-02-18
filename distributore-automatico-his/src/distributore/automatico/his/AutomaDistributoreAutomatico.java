/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributore.automatico.his;

/**
 *
 * @author laptop
 */
public class AutomaDistributoreAutomatico implements State {

    private State stato;
    private double totale;
    private double prezzoCaffe;

    public AutomaDistributoreAutomatico() {
        this.totale = 0;
        this.stato = new Attesa();
        this.prezzoCaffe = 0.45;
    }

    @Override
    public void next(Event e) throws EventoInaspettatoException {
        System.out.println("TOTALE: " + totale);
        System.out.println("Siamo nello stato " + stato);
        System.out.println("");
        System.out.println("Ricevuto evento " + e);
        stato.next(e);
        System.out.println("Siamo passati nello stato " + stato);
    }

    private class Attesa implements State  {

        @Override
        public void next(Event e) throws EventoInaspettatoException{
            //Controllo non ci sia un evento non previsto, se si verifica un evento
            //non previsto lancio l'eccezione
            if (e instanceof Caffe || e instanceof InserimentoSoldi || e instanceof Resto) {
                
                // eventi previsti: caffe, inserimentoSoldi, resto
                if (e instanceof Caffe) {
                    if (totale >= prezzoCaffe) {
                        stato = new Erogazione();
                        System.out.println("EROGAZIONE IN CORSO");
                    } else {
                        System.out.println("DENARO INSUFFICENTE");
                        
                    }
                }
                if (e instanceof InserimentoSoldi) {
                    totale += ((InserimentoSoldi) e).soldi;
                    System.out.println("SOLDI INSERITI: " + ((InserimentoSoldi) e).soldi);
                    System.out.println("TOTALE: " + totale);
                }

                if (e instanceof Resto) {
                    if (totale > 0) {
                        System.out.println("RESTO EROGATO: " + totale);
                        totale = 0;
                    }
                }
            } else {
                System.out.println("Errore evento " + e + "inatteso");
                throw new EventoInaspettatoException();
            }
        }

    }

    private class Erogazione implements State {

        @Override
        public void next(Event e) throws EventoInaspettatoException {
            if (e instanceof CaffePronto) {
                totale -= prezzoCaffe;
                stato = new Pronto();
            } else {
                System.out.println("Errore evento " + e + "inatteso");
                throw new EventoInaspettatoException();
            }
        }
    }

    private class Pronto implements State {

        @Override
        public void next(Event e) throws EventoInaspettatoException{
            if (e instanceof Ritiro) {
                stato = new Attesa();
            } else {
                System.out.println("Errore evento " + e + "inatteso");
                throw new EventoInaspettatoException();
                
            }
        }
    }
}
