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
public class Country {

    //attributes
    private int country_id;
    private String country_name;

    /**
     * Create new Country
     *
     * @param country_id
     * @param country_name
     */
    public Country(int country_id, String country_name) {
        this.country_id = country_id;
        this.country_name = country_name;
    }

    /**
     * Get country ID
     *
     * @return
     */
    public int getCountry_id() {
        return country_id;
    }

    /**
     * Set country ID
     *
     * @param country_id
     * @throws CountryException
     */
    public void setCountry_id(int country_id) throws CountryException {
        if (country_id <= 0) {
            throw new CountryException("Country ID must be greater than 0");
        }
        this.country_id = country_id;
    }

    /**
     * Get country name
     *
     * @return
     */
    public String getCountry_name() {
        return country_name;
    }

    /**
     * Set country name
     *
     * @param country_name
     * @throws CountryException
     */
    public void setCountry_name(String country_name) throws CountryException {
        if (country_name.equals("")) {
            throw new CountryException("Country Name can't be empty!");
        }
        this.country_name = country_name;
    }

    @Override
    public String toString() {
        return country_name+ " (ID: "+ country_id+")"; //To change body of generated methods, choose Tools | Templates.
    }

}
