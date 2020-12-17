package be.ac.umons.state;

public class Fabrication extends Thread implements State{
    private Boolean etatFabrication = false;

    public Fabrication() {
        System.out.println("Fabrication");
    }

    @Override
    public void currentState(Context context, Boolean panne, Boolean manque, Boolean fabrication) {
        try {
            sleep(60000);
            etatFabrication = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (etatFabrication){
            context.setState(new Attente());
        }
    }
}
