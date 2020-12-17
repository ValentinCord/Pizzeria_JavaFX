package be.ac.umons.state;

public class Attente implements State {

    public Attente(){
        System.out.println("Attente");
    }

    @Override
    public void currentState(Context context, Boolean panne, Boolean manque, Boolean fabrication) {
        if (panne){
            context.setState(new Panne());
        }
        if(manque){
            context.setState(new Manque());
        }
        if (fabrication){
            context.setState(new Fabrication());
        }
    }
}
