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
public class Client {

    //attributes
    private int c_id;
    private String c_name;
    private String c_email;
    private int c_gender;
    private int acc_id;

    /**
     * Create new Client
     *
     * @param c_id
     * @param c_name
     * @param c_email
     * @param c_gender
     * @param acc_id
     */
    public Client(int c_id, String c_name, String c_email, int c_gender, int acc_id) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_email = c_email;
        this.c_gender = c_gender;
        this.acc_id = acc_id;
    }

    /**
     * get client's id
     *
     * @return
     */
    public int getC_id() {
        return c_id;
    }

    /**
     * set client id
     *
     * @param c_id
     * @throws movieWebsiteManager.ClientException
     */
    public void setC_id(int c_id) throws ClientException {
        if (c_id <= 0) {
            throw new ClientException("Client ID must be greater than 0");
        }
        this.c_id = c_id;
    }

    /**
     * get client name
     *
     * @return
     */
    public String getC_name() {
        return c_name;
    }

    /**
     * set client name
     *
     * @param c_name
     * @throws ClientException
     */
    public void setC_name(String c_name) throws ClientException {
        if (c_name.equals("")) {
            throw new ClientException("Client name can't be empty!");
        }
        this.c_name = c_name;
    }

    /**
     * get client email
     *
     * @return
     */
    public String getC_email() {
        return c_email;
    }

    /**
     * set client email
     *
     * @param c_email
     * @throws ClientException
     */
    public void setC_email(String c_email) throws ClientException {
        if (this.c_email.equals("")) {
            throw new ClientException("Client email can't be empty!");
        }
        this.c_email = c_email;
    }

    /**
     * get client gender
     *
     * @return
     */
    public int getC_gender() {
        return c_gender;
    }

    /**
     * set client gender
     *
     * @param c_gender
     * @throws ClientException
     */
    public void setC_gender(int c_gender) throws ClientException {
        if (c_gender < 0 || c_gender > 1) {
            throw new ClientException("Client gender must be 0 or 1!");
        }
        this.c_gender = c_gender;
    }

    /**
     * get account id
     *
     * @return
     */
    public int getAcc_id() {
        return acc_id;
    }

    /**
     * set account id
     *
     * @param acc_id
     * @throws ClientException
     */
    public void setAcc_id(int acc_id) throws ClientException {
        if (acc_id <= 0) {
            throw new ClientException("Account ID must be greater than 0!");
        }
        this.acc_id = acc_id;
    }

}
