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
public class Movie {

    //attributes
    private int m_id;
    private String m_name;
    private int m_year;
    private int m_views;
    private int m_duration;
    private String m_trailer;
    private String m_thumbnail;
    private String m_description;
    private int s_id;
    private int country_id;

    /**
     * Create new Movie
     *
     * @param m_id
     * @param m_name
     * @param m_year
     * @param m_views
     * @param m_duration
     * @param m_trailer
     * @param m_thumbnail
     * @param m_description
     * @param s_id
     * @param country_id
     */
    public Movie(int m_id, String m_name, int m_year, int m_views, int m_duration, String m_trailer, String m_thumbnail, String m_description, int s_id, int country_id) {
        this.m_id = m_id;
        this.m_name = m_name;
        this.m_year = m_year;
        this.m_views = m_views;
        this.m_duration = m_duration;
        this.m_trailer = m_trailer;
        this.m_thumbnail = m_thumbnail;
        this.m_description = m_description;
        this.s_id = s_id;
        this.country_id = country_id;
    }

    /**
     * get movie ID
     *
     * @return
     */
    public int getM_id() {
        return m_id;
    }

    /**
     * set movie ID
     *
     * @param m_id
     * @throws MovieException
     */
    public void setM_id(int m_id) throws MovieException {
        if (m_id <= 0) {
            throw new MovieException("Movie ID must be greater than 0");
        }
        this.m_id = m_id;
    }

    /**
     * get movie name
     *
     * @return
     */
    public String getM_name() {
        return m_name;
    }

    /**
     * set movie name
     *
     * @param m_name
     * @throws MovieException
     */
    public void setM_name(String m_name) throws MovieException {
        if (m_name.equals("")) {
            throw new MovieException("Movie Name can't be empty!");
        }
        this.m_name = m_name;
    }

    /**
     * get movie published year
     *
     * @return
     */
    public int getM_year() {
        return m_year;
    }

    /**
     * Set movie's published year
     *
     * @param m_year
     * @throws MovieException
     */
    public void setM_year(int m_year) throws MovieException {
        if (m_year < 1888) {   //the first movie was published in 1888
            throw new MovieException("Published year invalid!");
        }
        this.m_year = m_year;
    }

    /**
     * get movie's number of views
     *
     * @return
     */
    public int getM_views() {
        return m_views;
    }

    /**
     * Set number of view of a movie
     *
     * @param m_views
     * @throws MovieException
     */
    public void setM_views(int m_views) throws MovieException {
        if (m_views < 0) {
            throw new MovieException("Movie view can't be less than 0");
        }
        this.m_views = m_views;
    }

    /**
     * get movie duration
     *
     * @return
     */
    public int getM_duration() {
        return m_duration;
    }

    /**
     * set movie duration (in second)
     *
     * @param m_duration
     * @throws MovieException
     */
    public void setM_duration(int m_duration) throws MovieException {
        if (m_duration <= 0) {
            throw new MovieException("Movie duration must be greater than 0");
        }
        this.m_duration = m_duration;
    }

    /**
     * get path of trailer of movie
     *
     * @return
     */
    public String getM_trailer() {
        return m_trailer;
    }

    /**
     * set path to trailer of a movie
     *
     * @param m_trailer
     * @throws MovieException
     */
    public void setM_trailer(String m_trailer) throws MovieException {
        if (m_trailer.equals("")) {
            throw new MovieException("Path to trailer can't be empty!");
        }
        this.m_trailer = m_trailer;
    }

    /**
     * get path of thumbnail of movie
     *
     * @return
     */
    public String getM_thumbnail() {
        return m_thumbnail;
    }

    /**
     * set path to thumbnail of a movie
     *
     * @param m_thumbnail
     * @throws MovieException
     */
    public void setM_thumbnail(String m_thumbnail) throws MovieException {
        if (m_thumbnail.equals("")) {
            throw new MovieException("Path to thumbnail can't be empty!");
        }
        this.m_thumbnail = m_thumbnail;
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
     * Set Studio ID
     *
     * @param s_id
     * @throws MovieException
     */
    public void setS_id(int s_id) throws MovieException {
        if (s_id <= 0) {
            throw new MovieException("Studio ID must be greater than 0");
        }
        this.s_id = s_id;
    }

    /**
     * Get Country ID
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
     * @throws MovieException
     */
    public void setCountry_id(int country_id) throws MovieException {
        if (country_id <= 0) {
            throw new MovieException("Country ID must be greater than 0");
        }
        this.country_id = country_id;
    }

    /**
     * Get movie description
     *
     * @return
     */
    public String getM_description() {
        return m_description;
    }

    /**
     * Set movie description
     *
     * @param m_description
     * @throws MovieException
     */
    public void setM_description(String m_description) throws MovieException {
        if (m_description.isEmpty()) {
            throw new MovieException("Movie description can't be empty!");
        }
        this.m_description = m_description;
    }

    @Override
    public String toString() {
        return m_name+ " (ID: "+  m_id+")"; //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
