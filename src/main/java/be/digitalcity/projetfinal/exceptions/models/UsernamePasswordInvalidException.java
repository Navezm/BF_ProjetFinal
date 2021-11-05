package be.digitalcity.projetfinal.exceptions.models;

public class UsernamePasswordInvalidException extends RuntimeException {

    public UsernamePasswordInvalidException(){ super("Username/password invalid"); }

}
