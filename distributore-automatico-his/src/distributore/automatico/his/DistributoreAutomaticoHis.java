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
public class DistributoreAutomaticoHis {

    public static void main(String[] args) {
        AutomaDistributoreAutomatico a = new AutomaDistributoreAutomatico();
        a.next(new InserimentoSoldi(0.20));
        System.out.println("*************************");
        a.next(new Caffe());
        System.out.println("*************************");
        a.next(new InserimentoSoldi(0.50));
        System.out.println("*************************");
        a.next(new Caffe());
        System.out.println("*************************");
        a.next(new CaffePronto());
        System.out.println("*************************");
        a.next(new Ritiro());
        System.out.println("*************************");
        a.next(new Resto());

    }

}
