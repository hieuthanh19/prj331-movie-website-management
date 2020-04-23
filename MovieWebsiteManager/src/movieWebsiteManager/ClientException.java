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
public class ClientException extends Exception {

    /**
     * Create new Client Exception
     *
     * @param msg
     */
    public ClientException(String msg) {
        super("Client Excception: " + msg);
    }
}
