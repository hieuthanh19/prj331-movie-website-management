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
public class Studio {

    ///attributes
    private int s_id;
    private String s_name;

    /**
     * Create new Studio
     *
     * @param s_id
     * @param s_name
     */
    public Studio(int s_id, String s_name) {
        this.s_id = s_id;
        this.s_name = s_name;
    }

    /**
     * Get Studio ID
     *
     * @return
     */
    public int getS_id() {
        return s_id;
    }

    /**
     * Set studio ID
     *
     * @param s_id
     * @throws StudioException
     */
    public void setS_id(int s_id) throws StudioException {
        if (s_id <= 0) {
            throw new StudioException("Studio ID must be greater than 0");
        }
        this.s_id = s_id;
    }

    /**
     * Get studio name
     *
     * @return
     */
    public String getS_name() {
        return s_name;
    }

    /**
     * set studio name
     *
     * @param s_name
     * @throws StudioException
     */
    public void setS_name(String s_name) throws StudioException {
        if (s_name.equals("")) {
            throw new StudioException("Studio name can't be empty!");
        }
        this.s_name = s_name;
    }

    @Override
    public String toString() {
        return s_name+ " (ID: "+ s_id+")"; //To change body of generated methods, choose Tools | Templates.
    }

}
