package be.ac.umons.state;

public interface State {
    void currentState(Context context, Boolean panne, Boolean manque, Boolean fabrication);
}
