package eserciziodistributorecaffè;

public class DistributoreStatePattern {

    public static void main(String[] args) {
        
        AutomaDistributore a = new AutomaDistributore();
        a.next(new InserimentoSoldi());
        System.out.println("------------");
        a.next(new CaffèPreparazione());
        System.out.println("------------");
        a.next(new CaffèPronto());
        System.out.println("------------");
        a.next(new RitiroCaffè());
        System.out.println("------------");
        a.next(new Resto());
        
    }

}
