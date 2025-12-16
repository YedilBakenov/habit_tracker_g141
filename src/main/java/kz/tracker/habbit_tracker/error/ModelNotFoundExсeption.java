package kz.tracker.habbit_tracker.error;

public class ModelNotFoundExсeption extends RuntimeException {

    public ModelNotFoundExсeption(){
        super();
    }

    public ModelNotFoundExсeption(String message){
        super(message);
    }
}
