package be.ac.umons.state;

public class Panne implements State {

    @Override
    public void currentState(Context context, Boolean panne, Boolean manque, Boolean fabrication) {
        System.out.println("Panne");
        if (!panne) {
            context.setState(new Attente());
        }
    }

}
