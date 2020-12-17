package be.ac.umons.state;

public class Manque implements State {

    @Override
    public void currentState(Context context, Boolean panne, Boolean manque, Boolean fabrication) {
        System.out.println("Manque");
        if (manque){
            context.setState(new Attente());
        }
    }
}
