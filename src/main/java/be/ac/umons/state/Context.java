package be.ac.umons.state;

public class Context {
    private State state;

    public Context() {
        this.state = new Attente();
    }
    public void setState(State newState){
        this.state = newState;
    }

    public void currentState(Boolean panne, Boolean manque, Boolean fabrication){
        state.currentState(this, panne, manque, fabrication);
    }

}
