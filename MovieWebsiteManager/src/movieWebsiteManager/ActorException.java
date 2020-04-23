/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieWebsiteManager;

/**
 *
 * @author ThanhKH
 */
public class ActorException extends Exception {

    /**
     * Create new Actor Exception
     *
     * @param msg
     */
    public ActorException(String msg) {
        super("Actor Exception: " + msg);
    }
}
