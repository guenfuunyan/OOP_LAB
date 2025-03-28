package main;

public class Aims {
	public static void main(String[] args) {
        Cart anOrder = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King","Animation","Roger Allers",87,19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars","Science Fiction","George Lurcas",87, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin","Animation", 18.99f);

        anOrder.addDigitalVideoDisc(dvd1);
        anOrder.addDigitalVideoDisc(dvd2);
        anOrder.addDigitalVideoDisc(dvd3);

        System.out.println("Total Cost is: $" + anOrder.totalCost());
        anOrder.removeDigitalVideoDisc(dvd3);
	    System.out.print("Total Cost is: ");
	    System.out.println(anOrder.totalCost());
	    
	    DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");
	    DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");
	    
	    TestPassingParameter.swap(jungleDVD, cinderellaDVD);
	    System.out.println("jungle dvd title: " + jungleDVD.getTitle());
        System.out.println("cinderella dvd title: " + cinderellaDVD.getTitle());
	    
        TestPassingParameter.changeTitle(jungleDVD, cinderellaDVD.getTitle());
        System.out.println("jungle and title: " + jungleDVD.getTitle());
        
	    System.out.println("DVD 1 ID: " + dvd1.getId());
        System.out.println("DVD 2 ID: " + dvd2.getId());
        System.out.println("DVD 3 ID: " + dvd3.getId());
    }
}
