/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieWebsiteManager;

/**
 *
 * @author Dell
 */
public class ActorInMovieException extends Exception{
    /**
 * Create ActorInMovieException
 * @param msg 
 */
    public ActorInMovieException(String msg) {
        super("ActorInMovie Exception: " + msg);

    }
}
