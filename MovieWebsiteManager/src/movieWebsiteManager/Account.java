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
public class Account {

    private int acc_id;
    private String acc_username;
    private String acc_password;
    private int acc_level;
    private int acc_status;

    /**
     * Create new Account
     *
     * @param acc_id
     * @param acc_username
     * @param acc_password
     * @param acc_level security level of an account. User is 0, Mod is 1 and Admin is 2
     * @param acc_status status of an account. 0 is under banned and 1 is normal
     */
    public Account(int acc_id, String acc_username, String acc_password, int acc_level, int acc_status) {
        this.acc_id = acc_id;
        this.acc_username = acc_username;
        this.acc_password = acc_password;
        this.acc_level = acc_level;
        this.acc_status = acc_status;
    }

    public int getAcc_id() {
        return acc_id;
    }

    /**
     * check id account
     *
     * @param acc_id
     * @throws AccountException
     */
    public void setAcc_id(int acc_id) throws AccountException {
        if (acc_id <= 0) {
            throw new AccountException(" Account ID greater than 0");
        }
        this.acc_id = acc_id;
    }

    public String getAcc_username() {
        return acc_username;
    }

    /**
     * check username account
     *
     * @param acc_username
     * @throws AccountException
     */
    public void setAcc_username(String acc_username) throws AccountException {
        if (acc_username.isEmpty()) {
            throw new AccountException(" Username is not null");
        }
        this.acc_username = acc_username;
    }

    public String getAcc_password() {
        return acc_password;
    }

    /**
     * check account password
     *
     * @param acc_password
     * @throws AccountException
     */
    public void setAcc_password(String acc_password) throws AccountException {
        if (acc_password.isEmpty()) {
            throw new AccountException("Password is not null");
        }
        this.acc_password = acc_password;
    }

    public int getAcc_level() {
        return acc_level;
    }

    /**
     * check account level
     *
     * @param acc_level
     * @throws AccountException
     */
    public void setAcc_level(int acc_level) throws AccountException {
        if (acc_level < 0 || acc_level > 2) {
            throw new AccountException("Account's security level must be from 0 to 2");
        }
        this.acc_level = acc_level;
    }

    public int getAcc_status() {
        return acc_status;
    }

    /**
     * check account status
     *
     * @param acc_status
     * @throws AccountException
     */
    public void setAcc_status(int acc_status) throws AccountException {
        if (acc_status !=0 || acc_status !=1) {
            throw new AccountException("Account status must be 0 - banned or 1 - normal");
        }
        this.acc_status = acc_status;
    }

}
