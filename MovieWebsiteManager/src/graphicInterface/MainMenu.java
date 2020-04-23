/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicInterface;

import movieWebsiteManager.GenreException;
import movieWebsiteManager.Movie;
import movieWebsiteManager.CountryException;
import movieWebsiteManager.FavoriteMovie;
import movieWebsiteManager.DirectorModel;
import movieWebsiteManager.Genre;
import movieWebsiteManager.DirectorException;
import movieWebsiteManager.Country;
import movieWebsiteManager.ActorModel;
import movieWebsiteManager.Client;
import movieWebsiteManager.Director;
import movieWebsiteManager.MovieException;
import movieWebsiteManager.GenreModel;
import movieWebsiteManager.ActorException;
import movieWebsiteManager.GenreInMovie;
import movieWebsiteManager.Actor;
import movieWebsiteManager.ClientModel;
import movieWebsiteManager.Account;
import movieWebsiteManager.FavoriteMovieModel;
import movieWebsiteManager.DirectorInMovieModel;
import movieWebsiteManager.StudioModel;
import movieWebsiteManager.ActorInMovie;
import movieWebsiteManager.AccountModel;
import movieWebsiteManager.AccountException;
import movieWebsiteManager.DirectorInMovie;
import movieWebsiteManager.GenreInMovieModel;
import movieWebsiteManager.CountryModel;
import movieWebsiteManager.Studio;
import movieWebsiteManager.StudioException;
import movieWebsiteManager.ClientException;
import movieWebsiteManager.ActorInMovieModel;
import movieWebsiteManager.MovieModel;
import java.awt.Cursor;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import static javax.swing.SwingConstants.BOTTOM;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 *
 * @author ThanhKH
 */
public class MainMenu extends javax.swing.JFrame {

    //variables    
    String theme = "";
    String movieThumbnailFolder = "";
    //navigation variables
    String currentFunction = "";
    String panel[] = new String[100];
    int num = 0, p = 0, q = 0;
    String count[] = new String[20];
    int statusBack;
    int statusForward;
    //movie's variables  
    String m_thumbnail = "";
    File source = null;
    boolean isTrailerPlayable = false;

    //Objects Lists    
    private static ArrayList<Movie> movieList = new ArrayList<>();
    private static ArrayList<Actor> actorList = new ArrayList<>();
    private static ArrayList<Director> directorList = new ArrayList<>();
    private static ArrayList<Genre> genreList = new ArrayList<>();
    private static ArrayList<Studio> studioList = new ArrayList<>();
    private static ArrayList<Country> countryList = new ArrayList<>();
    //result list
    private static ArrayList<Actor> actorResultList = new ArrayList<>();
    private static ArrayList<Director> directorResultList = new ArrayList<>();
    private static ArrayList<Genre> genreResultList = new ArrayList<>();
    private static ArrayList<Studio> studioResultList = new ArrayList<>();
    private static ArrayList<Country> countryResultList = new ArrayList<>();
    //icons    
    public static ImageIcon cancelSearch = null;
    ImageIcon collapseArrow = null;
    ImageIcon dropArrow = null;
    ImageIcon darkModeOn = null;
    ImageIcon darkModeOnHover = null;
    Image appLogo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/group10_logo.png"));
    ImageIcon emptyThumbnail = null;
    ImageIcon logOut = null;
    ImageIcon logOutHover = null;
    ImageIcon help = null;
    ImageIcon helpHover = null;
    ImageIcon about = null;
    ImageIcon aboutHover = null;
    //side menu icons
    ImageIcon homeActive = null;
    ImageIcon browseActive = null;
    ImageIcon favListActive = null;
    ImageIcon insertActive = null;
    ImageIcon updateActive = null;

    //colors
    Color activeElement = new Color(30, 215, 96);  //color for active elements
    Color txtColor = null;
    //dark theme   
    Color inactiveTxt = new Color(179, 179, 179);
    Color pnlContentDarkThemeColor = new Color(34, 34, 34);
    Color pnlSideMenuDarkThemeColor = new Color(18, 18, 18);
    Color pnlTopNavDarkThemeColor = new Color(24, 24, 24);
    Color pnlFillInfoDarkThemeColor = new Color(40, 40, 40);
    Color txtColorDarkTheme = Color.white;
    //light theme
    Color txtColorLighttheme = Color.BLACK;
    //classes
    private static Account acc;
    private static CardLayout cl;
    private static ClientModel clientM;
    private static MovieModel movieM;
    private static ActorModel actorM;
    private static DirectorModel directorM;
    private static GenreModel genreM;
    private static StudioModel studioM;
    private static CountryModel countryM;
    private static FavoriteMovieModel favMovieM;
    private static ActorInMovieModel actorMovieM;
    private static DirectorInMovieModel directorMovieM;
    private static GenreInMovieModel genreMovieM;

    /**
     * Create new MainMenu
     *
     * @param a
     * @param theme
     * @throws java.sql.SQLException
     * @throws websiteManager.ClientException
     */
    public MainMenu(Account a, String theme) throws SQLException, ClientException {
        initComponents();
        //initial variables
        acc = a;
        movieThumbnailFolder = "movieThumbnail";
        this.theme = theme;
        if (theme.equals("darkTheme")) {
            this.txtColor = txtColorDarkTheme;
        } else {
            this.txtColor = txtColorLighttheme;
        }
        //Models
        clientM = new ClientModel();
        movieM = new MovieModel();
        actorM = new ActorModel();
        directorM = new DirectorModel();
        genreM = new GenreModel();
        studioM = new StudioModel();
        countryM = new CountryModel();
        favMovieM = new FavoriteMovieModel();
        actorMovieM = new ActorInMovieModel();
        directorMovieM = new DirectorInMovieModel();
        genreMovieM = new GenreInMovieModel();
        //Lists
        //  getListData();
        //get CardLayout
        cl = (CardLayout) pnlContent.getLayout();
        //MainMenu settings
        this.setIconImage(appLogo);
        this.setLocationRelativeTo(null);
        //set up 
        initialSetup();
        //set panel home
        panel[0] = "mainContent";

    }

    /**
     * set new panel
     *
     * @param newPanel
     */
    public void setPanel(String newPanel) {

        num++;
        p = num;
        panel[num] = newPanel;
        for (int i = num + 1; i < 10; i++) {
            panel[i] = null;
        }
        getBack();
        getForward();
        System.out.println(num);
        System.out.print(statusBack);
        System.out.print(statusForward);
    }

    /**
     * 1 = back 0 = forward
     *
     * @param knot
     * @return oldPanel
     */
    public String currentLocation(int knot) {
        String oldPanel = null;
        if (knot == 1 && num > 0) {
            num--;
            oldPanel = panel[num];
        } else if (knot == 0 && num < (p + 1)) {
            num++;
            oldPanel = panel[num];
        }

        if ("objectManagementInsert".equals(oldPanel)) {
            doInsertMouse();
            oldPanel = "objectManagement";
        } else if ("objectManagementUpdate".equals(oldPanel)) {
            doUpdateMouse();
            oldPanel = "objectManagement";
        } else if (oldPanel.equals("favList")) {
            setUpFavList();
            oldPanel = "extendedContent";
        } else if (oldPanel.contains("browse")) {
            setUpBrowse();
            oldPanel = "extendedContent";
        } else if (oldPanel.equals("mainContent")) {
            setUpHome();
        } else if (oldPanel.equals("movieInfo")) {
            setInactiveIcons();
        } else /*if (oldPanel.equals("")) */ {
            setInactiveIcons();
        }

        getBack();
        getForward();
        System.out.print(oldPanel);
        System.out.print(statusBack);
        System.out.print(statusForward);
        System.out.println(num + "(:)" + p);
        return oldPanel;
    }

    /**
     * get back 0= hidden 1= show
     */
    public void getBack() {
        if (num == 0) {
            lblBack.setIcon(new ImageIcon("src/darkTheme/backArrow.png"));
            statusBack = 1;
        } else {
            lblBack.setIcon(new ImageIcon("src/darkTheme/backArrowActive.png"));
            statusBack = 0;
        }

    }

    /**
     * get forward 0= hidden 1= show
     */
    public void getForward() {
        if (panel[num + 1] != null) {
            lblNext.setIcon(new ImageIcon("src/darkTheme/forwardArrowActive.png"));
            statusForward = 0;
        } else {
            lblNext.setIcon(new ImageIcon("src/darkTheme/forwardArrow.png"));
            statusForward = 1;
        }
    }

    /**
     * Set up for main menu
     *
     * @throws java.sql.SQLException
     * @throws websiteManager.ClientException
     */
    public void initialSetup() throws SQLException, ClientException {
        //load icons
        loadIcon(theme);
        //change interface
        changeInterfaceOnAccLevel(acc);
        //set up home inferface
        setUpHomeInterface(4, 8);
        //set acc name
        lblUsername.setText(acc.getAcc_username());
        //hide drop menu
        pnlDropMenu.setVisible(false);
        //
        setUpSearchBar();
        setUpdatePlayer();

    }

    /**
     * Load icons
     *
     * @param theme
     */
    public void loadIcon(String theme) {

        //top nav icons
        cancelSearch = new ImageIcon("src/" + theme + "/cancelSearch.png");
        collapseArrow = new ImageIcon("src/" + theme + "/collapseArrow.png");
        dropArrow = new ImageIcon("src/" + theme + "/dropArrow.png");
        darkModeOn = new ImageIcon("src/" + theme + "/darkThemeOn.png");
        darkModeOnHover = new ImageIcon("src/" + theme + "/darkThemeOnHover.png");
        logOut = new ImageIcon("src/" + theme + "/LogOut.png");
        logOutHover = new ImageIcon("src/" + theme + "/LogOutHover.png");
        appLogo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/group10_logo.png"));
        emptyThumbnail = new ImageIcon("src/" + movieThumbnailFolder + "/emptyThumbnail.png");
        //side menu icons
        homeActive = new ImageIcon("src/" + theme + "/homeActive.png");
        browseActive = new ImageIcon("src/" + theme + "/browseActive.png");
        favListActive = new ImageIcon("src/" + theme + "/favListActive.png");
        insertActive = new ImageIcon("src/" + theme + "/insertActive.png");
        updateActive = new ImageIcon("src/" + theme + "/updateActive.png");
    }

    /**
     * Change interface upon account level
     *
     * @param a
     */
    public void changeInterfaceOnAccLevel(Account a) {
        //User: Hide management tools 
        if (a.getAcc_level() == 0) {
            pnlManagementTool.setVisible(false);
            pnlManagementTool.setEnabled(false);
        }
        //Mod: Hide Account object
        if (a.getAcc_level() == 1) {
            cbObjectForManagement.removeItem("Account");
            cbObjectForManagement.setMaximumRowCount(7);
        }
    }

    /**
     * Display most viewed movies, discover new movies on pnlMostViewed
     *
     * @param numOfMostViewed
     * @param numOfDiscoverMovie
     * @throws SQLException
     */
    public void setUpHomeInterface(int numOfMostViewed, int numOfDiscoverMovie) throws SQLException {
        //get top movies
        ArrayList<Movie> mostViewedList = movieM.getTopViewMovie(numOfMostViewed);
        ArrayList<Movie> discoverMovieList = movieM.getRandomMovie(numOfDiscoverMovie);
        //remove all component in most viewed panel and discovery panel
        pnlMostViewed.removeAll();
        pnlDiscoverMovie.removeAll();
        pnlDiscoverMovie.setSize(new Dimension(900, 800));
        if (discoverMovieList.size() <= 4) {
            pnlContent.setSize(new Dimension(1000, 875));
        }
        addMovieLabelToPanel(mostViewedList, pnlMostViewed);
        addMovieLabelToPanel(discoverMovieList, pnlDiscoverMovie);

    }

    /**
     * Set all side menu's labels text color to inactive
     */
    public void setInactiveIcons() {
        //load inactive icons
        ImageIcon homeInActive = new ImageIcon("src/" + theme + "/homeInActive.png");
        ImageIcon browseInActive = new ImageIcon("src/" + theme + "/browseInActive.png");
        ImageIcon favListInActive = new ImageIcon("src/" + theme + "/favListInActive.png");
        ImageIcon insertInActive = new ImageIcon("src/" + theme + "/insertInactive.png");
        ImageIcon updateInActive = new ImageIcon("src/" + theme + "/updateInactive.png");
        //set icon
        lblHome.setIcon(homeInActive);
        lblBrowse.setIcon(browseInActive);
        lblFavList.setIcon(favListInActive);
        lblInsert.setIcon(insertInActive);
        lblUpdate.setIcon(updateInActive);
    }

    /**
     * Get all data of objects and add to ArrayList
     *
     * @param object a String that store object name which is got from combo box
     * @throws SQLException
     */
    public void getListData(String object) throws SQLException {
        switch (object) {
            case "Movie":
                //get movie
                movieList = movieM.getAllMovie();
                movieM.sort(movieList);
                break;
            case "Actor":
                //get actor
                actorList = actorM.getAllActor();
                actorM.sort(actorList);
                break;
            case "Director":
                //get director
                directorList = directorM.getAllDirector();
                directorM.sort(directorList);
                break;
            case "Genre":
                //get genre
                genreList = genreM.getAllGenre();
                genreM.sort(genreList);
                break;
            case "Studio":
                //get studio
                studioList = studioM.getAllStudio();
                studioM.sort(studioList);
                break;
            case "Country":
                //get country
                countryList = countryM.getAllCountry();
                countryM.sort(countryList);
                break;
        }

    }

    /**
     * Create and add movie label to panel
     *
     * @param movieList
     * @param pnl JPanel
     */
    public void addMovieLabelToPanel(ArrayList<Movie> movieList, JPanel pnl) {
        //create label and add to pnl
        for (int i = 0; i < movieList.size(); i++) {
            //get movie
            Movie m = movieList.get(i);
            //create new label
            JLabel lbl = new JLabel();
            //create thumbnail and resize
            ImageIcon mThumbnail = new ImageIcon(new ImageIcon("src/" + this.movieThumbnailFolder + "/" + m.getM_thumbnail())
                    .getImage().getScaledInstance(210, 300, Image.SCALE_SMOOTH));
            //set label icon
            lbl.setIcon(mThumbnail);
            //set title for label
            String movieName = "";
            //change displayed title if movie title's length is too long
            if (m.getM_name().length() > 23) {
                movieName = m.getM_name().substring(0, 22);
                movieName += "...";
            } else {
                movieName = m.getM_name();
            }

            String title = "<html>\n"
                    + "<p style=\\\"text-align:center;\\\"><b>" + movieName + "</b></p>\n"
                    + "<p style=\\\"text-align:center;\\\">" + m.getM_views() + " views</p>\n"
                    + "</html>";
            lbl.setText(title);

            lbl.setToolTipText(m.getM_name());
            //set text position
            lbl.setHorizontalAlignment(CENTER);
            lbl.setHorizontalTextPosition(CENTER);
            lbl.setVerticalAlignment(BOTTOM);
            lbl.setVerticalTextPosition(BOTTOM);
            //set text font & color
            lbl.setFont(new Font("Roboto", 0, 18));
            lbl.setForeground(txtColor);
            //set border
            lbl.setBorder(new LineBorder(pnlContentDarkThemeColor, 3));
            //events of label
            lbl.addMouseListener(new MouseInputAdapter() {
                //open movie info panel on clicked
                @Override
                public void mouseClicked(MouseEvent me) {
                    try {
                        scrPaneContent.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                        //display movie info panel
                        cl.show(pnlContent, "movieInfo");
                        //set panel for navigation
                        setPanel("movieInfo");

                        //set movie info panel
                        setUpMovieInfoPanel(m);
                        //set inactive icon
                        setInactiveIcons();
                    } catch (SQLException | StudioException | CountryException | ClientException ex) {
                        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                //change border when mouse exited
                @Override
                public void mouseExited(MouseEvent me) {
                    lbl.setBorder(new LineBorder(pnlContentDarkThemeColor, 3));
                    lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                //change border when mouse entered
                @Override
                public void mouseEntered(MouseEvent me) {
                    lbl.setBorder(new LineBorder(activeElement, 3));
                }

            });
            //add label to panel
            pnl.add(lbl);
        }
    }

    /**
     * Set up movie info panel
     *
     * @param m Movie Object
     * @throws SQLException
     * @throws StudioException
     * @throws CountryException
     * @throws websiteManager.ClientException
     */
    public void setUpMovieInfoPanel(Movie m) throws SQLException, StudioException, CountryException, ClientException {
        //set current location
        currentFunction = "MovieInfo";
        pnlMovieInfo.setPreferredSize(new Dimension(800, 1060));
        pnlContent.setPreferredSize(new Dimension(800, 800));
        //set movie title
        txtAreaMovieInfoTitle.setText(m.getM_name());
        //set movie year, duration, genres
        //get duration
        int hour = m.getM_duration() / 60;
        String duration = hour + "h " + (m.getM_duration() - hour * 60) + "m";

        //get genre
        boolean isTooLong = false;
        ArrayList<GenreInMovie> genreL = genreMovieM.getGenreInMovie(m.getM_id());
        String genre = genreM.getGenre(genreL.get(0).getG_id()).getG_name();
        for (int i = 1; i < genreL.size(); i++) {
            if (genre.length() > 50) {
                isTooLong = true;
                lblMovieInfolYear_Duration_Genre.setText(m.getM_year() + "   |   " + duration + "   |   " + genre + "...");
            }
            genre += ", " + genreM.getGenre(genreL.get(i).getG_id()).getG_name();
        }
        if (isTooLong) {
            lblMovieInfolYear_Duration_Genre.setToolTipText(m.getM_year() + "   |   " + duration + "   |   " + genre);
        } else {
            lblMovieInfolYear_Duration_Genre.setText(m.getM_year() + "   |   " + duration + "   |   " + genre);
        }

        //set movie thumbnail
        ImageIcon mThumbnail = new ImageIcon(new ImageIcon("src/" + this.movieThumbnailFolder + "/" + m.getM_thumbnail())
                .getImage().getScaledInstance(250, 375, Image.SCALE_SMOOTH));
        lblMovieInfoThumbnail.setIcon(mThumbnail);
        //set favorite movie
        lblMovieInfoFav.removeAll();
        Client client = clientM.getClient(acc.getAcc_id());
        //if movie in favorite list
        if (favMovieM.isInFavoriteList(client.getC_id(), m.getM_id())) {
            lblMovieInfoFav.setIcon(new ImageIcon("src/" + theme + "/favorite.png"));
            lblMovieInfoFav.setToolTipText("Click to Remove this Movie from your Favorite List");
            lblMovieInfoFav.setName("inFavList");
        } else {
            lblMovieInfoFav.setIcon(new ImageIcon("src/" + theme + "/unFavorite.png"));
            lblMovieInfoFav.setToolTipText("Click to Add this Movie to your Favorite List");
            lblMovieInfoFav.setName("notInFavList");
        }
        //remove all mouse listener
        if (lblMovieInfoFav.getMouseListeners().length > 0) {
            lblMovieInfoFav.removeMouseListener(lblMovieInfoFav.getMouseListeners()[0]);
        }
        //
        lblMovieInfoFav.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                try {
                    //if a movie already in favorite list -> remove it
                    if (lblMovieInfoFav.getName().equalsIgnoreCase("infavlist")) {
                        //remove this movie from favList
                        favMovieM.deleteFavMovie(client.getC_id(), m.getM_id());
                        //set icon
                        lblMovieInfoFav.setIcon(new ImageIcon("src/" + theme + "/unFavorite.png"));
                        lblMovieInfoFav.setToolTipText("Click to Add this Movie to your Favorite List");
                        lblMovieInfoFav.setName("notInFavList");
                    } else {
                        //add this movie to favList
                        favMovieM.insert(client.getC_id(), m.getM_id());
                        //set icon
                        lblMovieInfoFav.setIcon(new ImageIcon("src/" + theme + "/favorite.png"));
                        lblMovieInfoFav.setToolTipText("Click to Remove this Movie from your Favorite List");
                        lblMovieInfoFav.setName("inFavList");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        //get actors        
        isTooLong = false;
        ArrayList<ActorInMovie> actorL = actorMovieM.getActorInMovie(m.getM_id());
        String actor = actorM.getActor(actorL.get(0).getA_id()).getA_name();
        for (int i = 1; i < actorL.size(); i++) {
            if (actor.length() > 70) {
                isTooLong = true;
                txtAreaMovieInfoCast.setText(actor + "...");
            }
            actor += ", " + actorM.getActor(actorL.get(i).getA_id()).getA_name();
        }
        //
        if (isTooLong) {
            txtAreaMovieInfoCast.setToolTipText(actor);
        } else {
            txtAreaMovieInfoCast.setText(actor);
        }

        //set director
        isTooLong = false;
        ArrayList<DirectorInMovie> directorL = directorMovieM.getDirectorInMovie(m.getM_id());
        String director = directorM.getDirector(directorL.get(0).getD_id()).getD_name();
        for (int i = 1; i < directorL.size(); i++) {
            if (director.length() > 70) {
                isTooLong = true;
                txtAreaMovieInfoDirector.setText(director + "...");
            }
            director += ", " + directorM.getDirector(directorL.get(i).getD_id()).getD_name();
        }
        //
        if (isTooLong) {
            txtAreaMovieInfoDirector.setToolTipText(director);
        } else {
            txtAreaMovieInfoDirector.setText(director);
        }
        //set studio
        lblMovieInfoStudio.setText(studioM.getStudio(m.getS_id()).getS_name());
        //set country
        lblMovieInfoCountry.setText(countryM.getCountry(m.getCountry_id()).getCountry_name());
        //set descrption
        txtAreaMovieDescription.setText(m.getM_description());
        //set trailer
        //remove all previous mouse listener
        if (lblMovieInfoPLayTrailer.getMouseListeners().length > 0) {
            lblMovieInfoPLayTrailer.removeMouseListener(lblMovieInfoPLayTrailer.getMouseListeners()[0]);
        }
        //
        lblMovieInfoPLayTrailer.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                try {
                    //update views (+1 views)
                    movieM.update(m.getM_id(), m.getM_name(), m.getM_year(), m.getM_views() + 1,
                            m.getM_duration(), m.getM_description(), m.getM_trailer(), m.getM_thumbnail(), m.getS_id(), m.getCountry_id());
                    //open media player frame
                    MediaPlayerFrame mdf = new MediaPlayerFrame(MainMenu.this, m.getM_trailer());
                    mdf.setVisible(true);
                    mdf.setLocationRelativeTo(null);
                    MainMenu.this.setEnabled(false);
                } catch (SQLException | MovieException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void mouseExited(MouseEvent me) {
                lblMovieInfoPLayTrailer.setIcon(new ImageIcon("src/" + theme + "/watchTrailer.png"));
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                lblMovieInfoPLayTrailer.setIcon(new ImageIcon("src/" + theme + "/watchTrailerHover.png"));
                lblMovieInfoPLayTrailer.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

        });

        //mediaPlayer.mediaPlayer().controls().setPause(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        btgGender = new javax.swing.ButtonGroup();
        layeredPane = new javax.swing.JLayeredPane();
        pnlDropMenu = new javax.swing.JPanel();
        lblDarkTheme = new javax.swing.JLabel();
        lblLogOut = new javax.swing.JLabel();
        pnlSideMenu = new javax.swing.JPanel();
        lblHome = new javax.swing.JLabel();
        lblBrowse = new javax.swing.JLabel();
        lblFavList = new javax.swing.JLabel();
        pnlManagementTool = new javax.swing.JPanel();
        lblMngToolTitle = new javax.swing.JLabel();
        lblInsert = new javax.swing.JLabel();
        lblUpdate = new javax.swing.JLabel();
        pnlTopNav = new javax.swing.JPanel();
        lblBack = new javax.swing.JLabel();
        lblNext = new javax.swing.JLabel();
        txtFldSearch = new javax.swing.JTextField();
        lblCancelSearch = new javax.swing.JLabel();
        lblSearchBar = new javax.swing.JLabel();
        lblAvatar = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblSubmenu = new javax.swing.JLabel();
        scrPaneContent = new javax.swing.JScrollPane();
        pnlContent = new javax.swing.JPanel();
        pnlMainContent = new javax.swing.JPanel();
        lblMostViewTitle = new javax.swing.JLabel();
        pnlMostViewed = new javax.swing.JPanel();
        divider1 = new javax.swing.JSeparator();
        lblDiscoverMoviesTitle = new javax.swing.JLabel();
        pnlDiscoverMovie = new javax.swing.JPanel();
        pnlExtendedContent = new javax.swing.JPanel();
        lblExtendedContentTitle = new javax.swing.JLabel();
        pnlExtendedSubContent = new javax.swing.JPanel();
        pnlManagementContent = new javax.swing.JPanel();
        cbObjectForManagement = new javax.swing.JComboBox<>();
        lblChooseObjAtFirst = new javax.swing.JLabel();
        pnlFillInfo = new javax.swing.JPanel();
        lblNameTitle = new javax.swing.JLabel();
        txtFldFillName = new javax.swing.JTextField();
        lblReleasedYear = new javax.swing.JLabel();
        txtFldReleasedYear = new javax.swing.JTextField();
        lblDuration = new javax.swing.JLabel();
        txtFldDuration = new javax.swing.JTextField();
        lblChooseObjTitle = new javax.swing.JLabel();
        lblDesTitle = new javax.swing.JLabel();
        scrPaneDescription = new javax.swing.JScrollPane();
        txtAreaDescription = new javax.swing.JTextArea();
        lblChooseThumbnail = new javax.swing.JLabel();
        lblThumbnail = new javax.swing.JLabel();
        lblInsertConfirmButt = new javax.swing.JLabel();
        cbChooseObject = new javax.swing.JComboBox<>();
        lblChooseObjItemTitle = new javax.swing.JLabel();
        cbObjectItem = new javax.swing.JComboBox<>();
        lblRemoveObj = new javax.swing.JLabel();
        lblObjItemResultTitle = new javax.swing.JLabel();
        cbObjItemResult = new javax.swing.JComboBox<>();
        lblAddObj = new javax.swing.JLabel();
        lblTrailerTitle = new javax.swing.JLabel();
        txtFldTrailer = new javax.swing.JTextField();
        lblPlayTrailer = new javax.swing.JLabel();
        lblManagementTitle = new javax.swing.JLabel();
        lblUpdateChooseObjItem = new javax.swing.JLabel();
        cbUpdateItem = new javax.swing.JComboBox<>();
        lblUpdateConfirm = new javax.swing.JLabel();
        pnlMovieInfo = new javax.swing.JPanel();
        scrpaneMovieTitle = new javax.swing.JScrollPane();
        txtAreaMovieInfoTitle = new javax.swing.JTextArea();
        lblMovieInfoThumbnail = new javax.swing.JLabel();
        lblMovieInfoFav = new javax.swing.JLabel();
        lblMovieInfolYear_Duration_Genre = new javax.swing.JLabel();
        lblMovieInfoCast = new javax.swing.JLabel();
        scrpaneMovieInfoCast = new javax.swing.JScrollPane();
        txtAreaMovieInfoCast = new javax.swing.JTextArea();
        lblMovieInfoDirector = new javax.swing.JLabel();
        scrpaneMovieInfoDirector = new javax.swing.JScrollPane();
        txtAreaMovieInfoDirector = new javax.swing.JTextArea();
        scrpaneMovieDescription = new javax.swing.JScrollPane();
        txtAreaMovieDescription = new javax.swing.JTextArea();
        lblMovieInfoStudio = new javax.swing.JLabel();
        lblMovieInfoStudioTitle = new javax.swing.JLabel();
        lblMovieInfoCountryTitle = new javax.swing.JLabel();
        lblMovieInfoCountry = new javax.swing.JLabel();
        lblMovieInfoPLayTrailer = new javax.swing.JLabel();
        pnlUserInfo = new javax.swing.JPanel();
        lblUserInfoTitle = new javax.swing.JLabel();
        lblUserAvatar = new javax.swing.JLabel();
        pnlUserInfoProfile = new javax.swing.JPanel();
        lbluserInfoName = new javax.swing.JLabel();
        lblUserInfoGender = new javax.swing.JLabel();
        lblUserInfoEmail = new javax.swing.JLabel();
        lblUserInfoChangeProfile = new javax.swing.JLabel();
        pnlUserInfoAccInfo = new javax.swing.JPanel();
        lblUserInfoUsername = new javax.swing.JLabel();
        lblUserInfoPasswordTitle = new javax.swing.JLabel();
        passFieldUserInfoPass = new javax.swing.JPasswordField();
        lblUserInfoChangePass = new javax.swing.JLabel();
        pnlUpdateUserInfo = new javax.swing.JPanel();
        lblUpdateUserInfoBack = new javax.swing.JLabel();
        lblUpdateUserInfoTitle = new javax.swing.JLabel();
        pnlUpdateUserText = new javax.swing.JPanel();
        lblUpdateUserGender = new javax.swing.JLabel();
        txtFldUpdateUserName = new javax.swing.JTextField();
        lblUpdateUserName = new javax.swing.JLabel();
        rbtNewMale = new javax.swing.JRadioButton();
        rbtNewFemale = new javax.swing.JRadioButton();
        lblUpdateUserEmail = new javax.swing.JLabel();
        tfdNewemail = new javax.swing.JTextField();
        lblUpdateUserUpdate = new javax.swing.JLabel();
        pnlChangeUserPassword = new javax.swing.JPanel();
        lblChangePasswordName = new javax.swing.JLabel();
        pnlUpdatePasswordPnl = new javax.swing.JPanel();
        lblcurrentpassword1 = new javax.swing.JLabel();
        pfdCurrentPassword = new javax.swing.JPasswordField();
        lblNewPassword1 = new javax.swing.JLabel();
        pfdNewPassword = new javax.swing.JPasswordField();
        lblConfirmNewPassword1 = new javax.swing.JLabel();
        pfdCoNewPassword = new javax.swing.JPasswordField();
        lblUpdatePass = new javax.swing.JLabel();
        lblBackUser = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Movie Website Manager");
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setResizable(false);
        setSize(new java.awt.Dimension(1200, 700));

        pnlDropMenu.setBackground(new java.awt.Color(42, 42, 42));
        pnlDropMenu.setPreferredSize(new java.awt.Dimension(163, 110));
        pnlDropMenu.setLayout(new java.awt.GridLayout(2, 1, 5, 0));

        lblDarkTheme.setForeground(new java.awt.Color(255, 255, 255));
        lblDarkTheme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkTheme/darkThemeOn.png"))); // NOI18N
        lblDarkTheme.setPreferredSize(new java.awt.Dimension(160, 26));
        lblDarkTheme.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDarkThemeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDarkThemeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDarkThemeMouseExited(evt);
            }
        });
        pnlDropMenu.add(lblDarkTheme);

        lblLogOut.setForeground(new java.awt.Color(255, 255, 255));
        lblLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkTheme/LogOut.png"))); // NOI18N
        lblLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogOutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLogOutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLogOutMouseExited(evt);
            }
        });
        pnlDropMenu.add(lblLogOut);

        layeredPane.setLayer(pnlDropMenu, javax.swing.JLayeredPane.POPUP_LAYER);
        layeredPane.add(pnlDropMenu);
        pnlDropMenu.setBounds(880, 70, 160, 110);

        pnlSideMenu.setBackground(new java.awt.Color(18, 18, 18));

        lblHome.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        lblHome.setForeground(new java.awt.Color(255, 255, 255));
        lblHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkTheme/homeActive.png"))); // NOI18N
        lblHome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHomeMouseClicked(evt);
            }
        });

        lblBrowse.setBackground(new java.awt.Color(179, 179, 179));
        lblBrowse.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblBrowse.setForeground(new java.awt.Color(179, 179, 179));
        lblBrowse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkTheme/browseInActive.png"))); // NOI18N
        lblBrowse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBrowseMouseClicked(evt);
            }
        });

        lblFavList.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblFavList.setForeground(new java.awt.Color(179, 179, 179));
        lblFavList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkTheme/favListInActive.png"))); // NOI18N
        lblFavList.setText("FavList");
        lblFavList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFavListMouseClicked(evt);
            }
        });

        pnlManagementTool.setOpaque(false);
        pnlManagementTool.setPreferredSize(new java.awt.Dimension(205, 150));

        lblMngToolTitle.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        lblMngToolTitle.setForeground(new java.awt.Color(179, 179, 179));
        lblMngToolTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMngToolTitle.setText("Management Tools");

        lblInsert.setFont(new java.awt.Font("Roboto", 0, 19)); // NOI18N
        lblInsert.setForeground(new java.awt.Color(179, 179, 179));
        lblInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkTheme/insertInActive.png"))); // NOI18N
        lblInsert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInsertMouseClicked(evt);
            }
        });

        lblUpdate.setFont(new java.awt.Font("Roboto", 0, 19)); // NOI18N
        lblUpdate.setForeground(new java.awt.Color(179, 179, 179));
        lblUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkTheme/updateInActive.png"))); // NOI18N
        lblUpdate.setPreferredSize(new java.awt.Dimension(200, 500));
        lblUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUpdateMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlManagementToolLayout = new javax.swing.GroupLayout(pnlManagementTool);
        pnlManagementTool.setLayout(pnlManagementToolLayout);
        pnlManagementToolLayout.setHorizontalGroup(
            pnlManagementToolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMngToolTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlManagementToolLayout.createSequentialGroup()
                .addGroup(pnlManagementToolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlManagementToolLayout.createSequentialGroup()
                        .addComponent(lblInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlManagementToolLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlManagementToolLayout.setVerticalGroup(
            pnlManagementToolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlManagementToolLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMngToolTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblInsert)
                .addGap(18, 18, 18)
                .addComponent(lblUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(478, 478, 478))
        );

        javax.swing.GroupLayout pnlSideMenuLayout = new javax.swing.GroupLayout(pnlSideMenu);
        pnlSideMenu.setLayout(pnlSideMenuLayout);
        pnlSideMenuLayout.setHorizontalGroup(
            pnlSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblBrowse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblFavList, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(pnlSideMenuLayout.createSequentialGroup()
                .addComponent(pnlManagementTool, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlSideMenuLayout.setVerticalGroup(
            pnlSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSideMenuLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(lblHome, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFavList, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlManagementTool, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(284, Short.MAX_VALUE))
        );

        layeredPane.add(pnlSideMenu);
        pnlSideMenu.setBounds(0, 0, 205, 670);

        pnlTopNav.setBackground(new java.awt.Color(24, 24, 24));
        pnlTopNav.setLayout(null);

        lblBack.setForeground(new java.awt.Color(255, 255, 255));
        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkTheme/backArrow.png"))); // NOI18N
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBackMouseEntered(evt);
            }
        });
        pnlTopNav.add(lblBack);
        lblBack.setBounds(231, 30, 32, 36);

        lblNext.setForeground(new java.awt.Color(255, 255, 255));
        lblNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkTheme/forwardArrow.png"))); // NOI18N
        lblNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNextMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNextMouseEntered(evt);
            }
        });
        pnlTopNav.add(lblNext);
        lblNext.setBounds(292, 30, 32, 36);

        txtFldSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtFldSearch.setToolTipText("Search");
        txtFldSearch.setBorder(null);
        txtFldSearch.setRequestFocusEnabled(false);
        txtFldSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFldSearchMouseClicked(evt);
            }
        });
        pnlTopNav.add(txtFldSearch);
        txtFldSearch.setBounds(416, 30, 200, 30);

        lblCancelSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCancelSearchMouseClicked(evt);
            }
        });
        pnlTopNav.add(lblCancelSearch);
        lblCancelSearch.setBounds(630, 32, 24, 24);

        lblSearchBar.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkTheme/searchBar.png"))); // NOI18N
        lblSearchBar.setText("Search bar");
        pnlTopNav.add(lblSearchBar);
        lblSearchBar.setBounds(371, 25, 300, 40);

        lblAvatar.setForeground(new java.awt.Color(255, 255, 255));
        lblAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkTheme/avatar.png"))); // NOI18N
        lblAvatar.setToolTipText("Avatar");
        pnlTopNav.add(lblAvatar);
        lblAvatar.setBounds(800, 20, 48, 48);

        lblUsername.setFont(new java.awt.Font("Roboto", 0, 26)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setText("test");
        lblUsername.setToolTipText("Account");
        lblUsername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsernameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblUsernameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblUsernameMouseExited(evt);
            }
        });
        pnlTopNav.add(lblUsername);
        lblUsername.setBounds(860, 30, 130, 29);

        lblSubmenu.setForeground(new java.awt.Color(255, 255, 255));
        lblSubmenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkTheme/dropArrow.png"))); // NOI18N
        lblSubmenu.setToolTipText("Menu");
        lblSubmenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSubmenuMouseClicked(evt);
            }
        });
        pnlTopNav.add(lblSubmenu);
        lblSubmenu.setBounds(1000, 31, 26, 20);

        layeredPane.add(pnlTopNav);
        pnlTopNav.setBounds(0, 0, 1200, 100);

        scrPaneContent.setBorder(null);
        scrPaneContent.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrPaneContent.setPreferredSize(new java.awt.Dimension(800, 600));

        pnlContent.setBackground(new java.awt.Color(32, 32, 32));
        pnlContent.setPreferredSize(new java.awt.Dimension(1000, 1300));
        pnlContent.setLayout(new java.awt.CardLayout());

        pnlMainContent.setBackground(new java.awt.Color(34, 34, 34));
        pnlMainContent.setPreferredSize(new java.awt.Dimension(1000, 1225));

        lblMostViewTitle.setFont(new java.awt.Font("Roboto", 1, 28)); // NOI18N
        lblMostViewTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblMostViewTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMostViewTitle.setText("Most Viewed");
        lblMostViewTitle.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        pnlMostViewed.setBackground(new java.awt.Color(34, 34, 34));
        pnlMostViewed.setPreferredSize(new java.awt.Dimension(900, 200));
        pnlMostViewed.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));

        divider1.setBackground(new java.awt.Color(42, 42, 42));
        divider1.setForeground(new java.awt.Color(42, 42, 42));
        divider1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lblDiscoverMoviesTitle.setFont(new java.awt.Font("Roboto", 1, 28)); // NOI18N
        lblDiscoverMoviesTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblDiscoverMoviesTitle.setText("Discover New Movies");

        pnlDiscoverMovie.setBackground(new java.awt.Color(34, 34, 34));
        pnlDiscoverMovie.setPreferredSize(new java.awt.Dimension(920, 900));
        pnlDiscoverMovie.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));

        javax.swing.GroupLayout pnlMainContentLayout = new javax.swing.GroupLayout(pnlMainContent);
        pnlMainContent.setLayout(pnlMainContentLayout);
        pnlMainContentLayout.setHorizontalGroup(
            pnlMainContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainContentLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnlMainContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(divider1)
                    .addComponent(lblDiscoverMoviesTitle)
                    .addComponent(lblMostViewTitle)
                    .addComponent(pnlMostViewed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDiscoverMovie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        pnlMainContentLayout.setVerticalGroup(
            pnlMainContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMostViewTitle)
                .addGap(18, 18, 18)
                .addComponent(pnlMostViewed, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(divider1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(lblDiscoverMoviesTitle)
                .addGap(18, 18, 18)
                .addComponent(pnlDiscoverMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlContent.add(pnlMainContent, "mainContent");

        pnlExtendedContent.setBackground(new java.awt.Color(34, 34, 34));

        lblExtendedContentTitle.setFont(new java.awt.Font("Roboto", 1, 32)); // NOI18N
        lblExtendedContentTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblExtendedContentTitle.setText("Most Viewed");

        pnlExtendedSubContent.setBackground(new java.awt.Color(34, 34, 34));
        pnlExtendedSubContent.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        javax.swing.GroupLayout pnlExtendedContentLayout = new javax.swing.GroupLayout(pnlExtendedContent);
        pnlExtendedContent.setLayout(pnlExtendedContentLayout);
        pnlExtendedContentLayout.setHorizontalGroup(
            pnlExtendedContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExtendedContentLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlExtendedContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlExtendedSubContent, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblExtendedContentTitle))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlExtendedContentLayout.setVerticalGroup(
            pnlExtendedContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExtendedContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblExtendedContentTitle)
                .addGap(18, 18, 18)
                .addComponent(pnlExtendedSubContent, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(516, Short.MAX_VALUE))
        );

        pnlContent.add(pnlExtendedContent, "extendedContent");

        pnlManagementContent.setBackground(new java.awt.Color(32, 32, 32));
        pnlManagementContent.setPreferredSize(new java.awt.Dimension(1000, 500));

        cbObjectForManagement.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        cbObjectForManagement.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Movie", "Actor", "Director", "Studio", "Country", "Genre", "Account", " " }));
        cbObjectForManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbObjectForManagementActionPerformed(evt);
            }
        });

        lblChooseObjAtFirst.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblChooseObjAtFirst.setForeground(new java.awt.Color(255, 255, 255));
        lblChooseObjAtFirst.setText("Choose object:");

        pnlFillInfo.setBackground(new java.awt.Color(40, 40, 40));

        lblNameTitle.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblNameTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblNameTitle.setText("Name (40 characters):");

        txtFldFillName.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtFldFillName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblReleasedYear.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblReleasedYear.setForeground(new java.awt.Color(255, 255, 255));
        lblReleasedYear.setText("Released year: ");

        txtFldReleasedYear.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtFldReleasedYear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDuration.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblDuration.setForeground(new java.awt.Color(255, 255, 255));
        lblDuration.setText("Duration (minutes)");

        txtFldDuration.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtFldDuration.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblChooseObjTitle.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblChooseObjTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblChooseObjTitle.setText("Choose Objects:");

        lblDesTitle.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblDesTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblDesTitle.setText("Description (300 characters):");

        txtAreaDescription.setColumns(20);
        txtAreaDescription.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtAreaDescription.setLineWrap(true);
        txtAreaDescription.setRows(5);
        txtAreaDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtAreaDescription.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        scrPaneDescription.setViewportView(txtAreaDescription);

        lblChooseThumbnail.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        lblChooseThumbnail.setForeground(new java.awt.Color(255, 255, 255));
        lblChooseThumbnail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChooseThumbnail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chooseThumbnail.png"))); // NOI18N
        lblChooseThumbnail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChooseThumbnailMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblChooseThumbnailMouseEntered(evt);
            }
        });

        lblInsertConfirmButt.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        lblInsertConfirmButt.setForeground(new java.awt.Color(255, 255, 255));
        lblInsertConfirmButt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/confirm.png"))); // NOI18N
        lblInsertConfirmButt.setPreferredSize(new java.awt.Dimension(100, 40));
        lblInsertConfirmButt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInsertConfirmButtMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblInsertConfirmButtMouseEntered(evt);
            }
        });

        cbChooseObject.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbChooseObject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Actor", "Director", "Studio", "Country", "Genre", " " }));
        cbChooseObject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChooseObjectActionPerformed(evt);
            }
        });

        lblChooseObjItemTitle.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblChooseObjItemTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblChooseObjItemTitle.setText("Choose an Object Items: ");

        cbObjectItem.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbObjectItem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None" }));
        cbObjectItem.setPreferredSize(new java.awt.Dimension(232, 23));

        lblRemoveObj.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        lblRemoveObj.setForeground(new java.awt.Color(30, 215, 96));
        lblRemoveObj.setText("Remove");
        lblRemoveObj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRemoveObjMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblRemoveObjMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblRemoveObjMouseExited(evt);
            }
        });

        lblObjItemResultTitle.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblObjItemResultTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblObjItemResultTitle.setText("Object Items: ");

        cbObjItemResult.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbObjItemResult.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None" }));
        cbObjItemResult.setPreferredSize(new java.awt.Dimension(232, 23));

        lblAddObj.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        lblAddObj.setForeground(new java.awt.Color(30, 215, 96));
        lblAddObj.setText("Add");
        lblAddObj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAddObjMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblAddObjMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblAddObjMouseExited(evt);
            }
        });

        lblTrailerTitle.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblTrailerTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTrailerTitle.setText("Trailer (100 characters):");
        lblTrailerTitle.setToolTipText("URL link to trailer");

        txtFldTrailer.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtFldTrailer.setPreferredSize(new java.awt.Dimension(307, 25));

        lblPlayTrailer.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        lblPlayTrailer.setForeground(new java.awt.Color(30, 215, 96));
        lblPlayTrailer.setText("Play Trailer");
        lblPlayTrailer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPlayTrailerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPlayTrailerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblPlayTrailerMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlFillInfoLayout = new javax.swing.GroupLayout(pnlFillInfo);
        pnlFillInfo.setLayout(pnlFillInfoLayout);
        pnlFillInfoLayout.setHorizontalGroup(
            pnlFillInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFillInfoLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(pnlFillInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFillInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblDesTitle)
                        .addComponent(scrPaneDescription)
                        .addGroup(pnlFillInfoLayout.createSequentialGroup()
                            .addGroup(pnlFillInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblChooseObjItemTitle)
                                .addComponent(lblObjItemResultTitle)
                                .addComponent(lblChooseObjTitle)
                                .addComponent(lblTrailerTitle))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pnlFillInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlFillInfoLayout.createSequentialGroup()
                                    .addGroup(pnlFillInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cbObjItemResult, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbObjectItem, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFillInfoLayout.createSequentialGroup()
                                            .addComponent(cbChooseObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(pnlFillInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblAddObj)
                                        .addComponent(lblRemoveObj)))
                                .addComponent(txtFldTrailer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(pnlFillInfoLayout.createSequentialGroup()
                            .addComponent(lblReleasedYear)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtFldReleasedYear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblDuration)
                            .addGap(18, 18, 18)
                            .addComponent(txtFldDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblNameTitle)
                    .addComponent(txtFldFillName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlFillInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFillInfoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlFillInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFillInfoLayout.createSequentialGroup()
                                .addComponent(lblPlayTrailer)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFillInfoLayout.createSequentialGroup()
                                .addGap(0, 71, Short.MAX_VALUE)
                                .addComponent(lblThumbnail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFillInfoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblChooseThumbnail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100))))
            .addGroup(pnlFillInfoLayout.createSequentialGroup()
                .addGap(398, 398, 398)
                .addComponent(lblInsertConfirmButt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlFillInfoLayout.setVerticalGroup(
            pnlFillInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFillInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFillInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlFillInfoLayout.createSequentialGroup()
                        .addComponent(lblNameTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFldFillName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlFillInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblReleasedYear)
                            .addComponent(txtFldReleasedYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDuration)
                            .addComponent(txtFldDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblDesTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrPaneDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlFillInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFillInfoLayout.createSequentialGroup()
                                .addGroup(pnlFillInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblChooseObjTitle)
                                    .addComponent(cbChooseObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlFillInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblChooseObjItemTitle)
                                    .addComponent(lblAddObj)
                                    .addComponent(cbObjectItem, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlFillInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblObjItemResultTitle)
                                    .addComponent(cbObjItemResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblRemoveObj, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlFillInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTrailerTitle)
                            .addComponent(txtFldTrailer, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPlayTrailer))
                        .addGap(18, 18, 18)
                        .addComponent(lblInsertConfirmButt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(pnlFillInfoLayout.createSequentialGroup()
                        .addComponent(lblThumbnail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblChooseThumbnail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133))))
        );

        lblManagementTitle.setFont(new java.awt.Font("Roboto", 0, 40)); // NOI18N
        lblManagementTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblManagementTitle.setText("Insert");

        lblUpdateChooseObjItem.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblUpdateChooseObjItem.setForeground(new java.awt.Color(255, 255, 255));
        lblUpdateChooseObjItem.setText("Choose an Object Item");

        cbUpdateItem.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        cbUpdateItem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", " " }));

        lblUpdateConfirm.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblUpdateConfirm.setForeground(new java.awt.Color(30, 215, 96));
        lblUpdateConfirm.setText("Confirm");
        lblUpdateConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUpdateConfirmMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblUpdateConfirmMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblUpdateConfirmMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlManagementContentLayout = new javax.swing.GroupLayout(pnlManagementContent);
        pnlManagementContent.setLayout(pnlManagementContentLayout);
        pnlManagementContentLayout.setHorizontalGroup(
            pnlManagementContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManagementContentLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlManagementContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlFillInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlManagementContentLayout.createSequentialGroup()
                        .addComponent(lblChooseObjAtFirst)
                        .addGap(18, 18, 18)
                        .addComponent(cbObjectForManagement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(173, 173, 173)
                        .addComponent(lblManagementTitle))
                    .addGroup(pnlManagementContentLayout.createSequentialGroup()
                        .addComponent(lblUpdateChooseObjItem)
                        .addGap(18, 18, 18)
                        .addComponent(cbUpdateItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(lblUpdateConfirm)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        pnlManagementContentLayout.setVerticalGroup(
            pnlManagementContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManagementContentLayout.createSequentialGroup()
                .addGroup(pnlManagementContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlManagementContentLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(pnlManagementContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbObjectForManagement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblChooseObjAtFirst)))
                    .addGroup(pnlManagementContentLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblManagementTitle)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlManagementContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUpdateChooseObjItem)
                    .addComponent(cbUpdateItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUpdateConfirm))
                .addGap(18, 18, 18)
                .addComponent(pnlFillInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pnlContent.add(pnlManagementContent, "objectManagement");

        pnlMovieInfo.setBackground(new java.awt.Color(34, 34, 34));
        pnlMovieInfo.setPreferredSize(new java.awt.Dimension(1000, 1060));

        scrpaneMovieTitle.setBorder(null);
        scrpaneMovieTitle.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrpaneMovieTitle.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAreaMovieInfoTitle.setEditable(false);
        txtAreaMovieInfoTitle.setBackground(new java.awt.Color(34, 34, 34));
        txtAreaMovieInfoTitle.setColumns(20);
        txtAreaMovieInfoTitle.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        txtAreaMovieInfoTitle.setForeground(new java.awt.Color(255, 255, 255));
        txtAreaMovieInfoTitle.setLineWrap(true);
        txtAreaMovieInfoTitle.setRows(2);
        txtAreaMovieInfoTitle.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        txtAreaMovieInfoTitle.setAutoscrolls(false);
        txtAreaMovieInfoTitle.setBorder(null);
        txtAreaMovieInfoTitle.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAreaMovieInfoTitle.setPreferredSize(new java.awt.Dimension(650, 120));
        scrpaneMovieTitle.setViewportView(txtAreaMovieInfoTitle);

        lblMovieInfoThumbnail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movieThumbnail/Avengers_Endgame_poster.jpg"))); // NOI18N

        lblMovieInfoFav.setForeground(new java.awt.Color(255, 255, 255));
        lblMovieInfoFav.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkTheme/unFavorite.png"))); // NOI18N
        lblMovieInfoFav.setToolTipText("");

        lblMovieInfolYear_Duration_Genre.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblMovieInfolYear_Duration_Genre.setForeground(new java.awt.Color(30, 215, 96));
        lblMovieInfolYear_Duration_Genre.setText("2019   |   1h 25m   |   Action, Thriller, Horror, Sci-fi, Action, Action, Action, Action\n");

        lblMovieInfoCast.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lblMovieInfoCast.setForeground(new java.awt.Color(255, 255, 255));
        lblMovieInfoCast.setText("Cast");

        scrpaneMovieInfoCast.setBorder(null);
        scrpaneMovieInfoCast.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrpaneMovieInfoCast.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAreaMovieInfoCast.setEditable(false);
        txtAreaMovieInfoCast.setBackground(new java.awt.Color(34, 34, 34));
        txtAreaMovieInfoCast.setColumns(20);
        txtAreaMovieInfoCast.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtAreaMovieInfoCast.setForeground(new java.awt.Color(255, 255, 255));
        txtAreaMovieInfoCast.setLineWrap(true);
        txtAreaMovieInfoCast.setRows(5);
        txtAreaMovieInfoCast.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaasdasdasdasdsdsdsdssdsdsdsdsds");
        txtAreaMovieInfoCast.setToolTipText("");
        scrpaneMovieInfoCast.setViewportView(txtAreaMovieInfoCast);

        lblMovieInfoDirector.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lblMovieInfoDirector.setForeground(new java.awt.Color(255, 255, 255));
        lblMovieInfoDirector.setText("Director");

        scrpaneMovieInfoDirector.setBorder(null);
        scrpaneMovieInfoDirector.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrpaneMovieInfoDirector.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAreaMovieInfoDirector.setEditable(false);
        txtAreaMovieInfoDirector.setBackground(new java.awt.Color(34, 34, 34));
        txtAreaMovieInfoDirector.setColumns(20);
        txtAreaMovieInfoDirector.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtAreaMovieInfoDirector.setForeground(new java.awt.Color(255, 255, 255));
        txtAreaMovieInfoDirector.setLineWrap(true);
        txtAreaMovieInfoDirector.setRows(5);
        txtAreaMovieInfoDirector.setText("BNt, BNT, BNT, BNT");
        txtAreaMovieInfoDirector.setToolTipText("");
        txtAreaMovieInfoDirector.setPreferredSize(new java.awt.Dimension(50, 515));
        scrpaneMovieInfoDirector.setViewportView(txtAreaMovieInfoDirector);

        scrpaneMovieDescription.setBorder(null);

        txtAreaMovieDescription.setEditable(false);
        txtAreaMovieDescription.setBackground(new java.awt.Color(34, 34, 34));
        txtAreaMovieDescription.setColumns(20);
        txtAreaMovieDescription.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtAreaMovieDescription.setForeground(new java.awt.Color(255, 255, 255));
        txtAreaMovieDescription.setLineWrap(true);
        txtAreaMovieDescription.setRows(5);
        txtAreaMovieDescription.setText("ADADSADASDASDASDASDASDASSDASDSADASDASDASaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADADSADASDASDASDASDASDASSDASDSADASDASDASaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        txtAreaMovieDescription.setToolTipText("");
        scrpaneMovieDescription.setViewportView(txtAreaMovieDescription);

        lblMovieInfoStudio.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblMovieInfoStudio.setForeground(new java.awt.Color(255, 255, 255));
        lblMovieInfoStudio.setText("Adsfdsfdsfsfdsfdsfdsfsdfsdfdsaasfsfsdfsfdsfdsfsfsf");

        lblMovieInfoStudioTitle.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lblMovieInfoStudioTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblMovieInfoStudioTitle.setText("Studio");

        lblMovieInfoCountryTitle.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lblMovieInfoCountryTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblMovieInfoCountryTitle.setText("Country");

        lblMovieInfoCountry.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblMovieInfoCountry.setForeground(new java.awt.Color(255, 255, 255));
        lblMovieInfoCountry.setText("adsfdsfdsfsfdsfdsfdsfsdfsdfdsa");

        lblMovieInfoPLayTrailer.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblMovieInfoPLayTrailer.setForeground(new java.awt.Color(255, 255, 255));
        lblMovieInfoPLayTrailer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkTheme/watchTrailer.png"))); // NOI18N

        javax.swing.GroupLayout pnlMovieInfoLayout = new javax.swing.GroupLayout(pnlMovieInfo);
        pnlMovieInfo.setLayout(pnlMovieInfoLayout);
        pnlMovieInfoLayout.setHorizontalGroup(
            pnlMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMovieInfoLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pnlMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(scrpaneMovieDescription)
                    .addGroup(pnlMovieInfoLayout.createSequentialGroup()
                        .addGroup(pnlMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblMovieInfoCountryTitle)
                            .addComponent(scrpaneMovieTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMovieInfoLayout.createSequentialGroup()
                                .addComponent(lblMovieInfoDirector)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scrpaneMovieInfoDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMovieInfoLayout.createSequentialGroup()
                                .addGroup(pnlMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMovieInfoCast)
                                    .addComponent(lblMovieInfoStudioTitle))
                                .addGroup(pnlMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMovieInfoLayout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(scrpaneMovieInfoCast, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMovieInfoLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblMovieInfoCountry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblMovieInfoStudio, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)))))
                            .addComponent(lblMovieInfolYear_Duration_Genre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34)
                        .addGroup(pnlMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMovieInfoLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(lblMovieInfoFav, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(lblMovieInfoPLayTrailer, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblMovieInfoThumbnail, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        pnlMovieInfoLayout.setVerticalGroup(
            pnlMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMovieInfoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pnlMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMovieInfoLayout.createSequentialGroup()
                        .addComponent(scrpaneMovieTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMovieInfolYear_Duration_Genre)
                        .addGap(18, 18, 18)
                        .addGroup(pnlMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrpaneMovieInfoCast, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieInfoCast))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMovieInfoDirector)
                            .addComponent(scrpaneMovieInfoDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblMovieInfoStudioTitle)
                            .addComponent(lblMovieInfoStudio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(pnlMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMovieInfoCountryTitle)
                            .addComponent(lblMovieInfoCountry)))
                    .addGroup(pnlMovieInfoLayout.createSequentialGroup()
                        .addComponent(lblMovieInfoThumbnail, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMovieInfoPLayTrailer, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMovieInfoLayout.createSequentialGroup()
                                .addComponent(lblMovieInfoFav, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrpaneMovieDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(678, Short.MAX_VALUE))
        );

        pnlContent.add(pnlMovieInfo, "movieInfo");

        pnlUserInfo.setBackground(new java.awt.Color(34, 34, 34));
        pnlUserInfo.setPreferredSize(new java.awt.Dimension(1000, 500));

        lblUserInfoTitle.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        lblUserInfoTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblUserInfoTitle.setText("Personal Infomation");

        pnlUserInfoProfile.setBackground(new java.awt.Color(40, 40, 40));
        pnlUserInfoProfile.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(40, 40, 40), 2, true), "Profile", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 0, 20), new java.awt.Color(255, 255, 255))); // NOI18N

        lbluserInfoName.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
        lbluserInfoName.setForeground(new java.awt.Color(255, 255, 255));
        lbluserInfoName.setText("Kieu Hieu Thanh");

        lblUserInfoGender.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lblUserInfoGender.setForeground(new java.awt.Color(255, 255, 255));
        lblUserInfoGender.setText("Gender: male");

        lblUserInfoEmail.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lblUserInfoEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblUserInfoEmail.setText("Email: thanh@thanh.com");
        lblUserInfoEmail.setToolTipText("");

        lblUserInfoChangeProfile.setBackground(new java.awt.Color(30, 215, 97));
        lblUserInfoChangeProfile.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        lblUserInfoChangeProfile.setForeground(new java.awt.Color(30, 215, 97));
        lblUserInfoChangeProfile.setText("Change profile info");
        lblUserInfoChangeProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUserInfoChangeProfileMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblUserInfoChangeProfileMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblUserInfoChangeProfileMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlUserInfoProfileLayout = new javax.swing.GroupLayout(pnlUserInfoProfile);
        pnlUserInfoProfile.setLayout(pnlUserInfoProfileLayout);
        pnlUserInfoProfileLayout.setHorizontalGroup(
            pnlUserInfoProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserInfoProfileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUserInfoProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbluserInfoName)
                    .addComponent(lblUserInfoGender)
                    .addComponent(lblUserInfoEmail))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUserInfoProfileLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblUserInfoChangeProfile))
        );
        pnlUserInfoProfileLayout.setVerticalGroup(
            pnlUserInfoProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserInfoProfileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbluserInfoName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUserInfoGender)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUserInfoEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUserInfoChangeProfile))
        );

        pnlUserInfoAccInfo.setBackground(new java.awt.Color(40, 40, 40));
        pnlUserInfoAccInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(40, 40, 40), 2, true), "Account Infomation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 0, 20), new java.awt.Color(255, 255, 255))); // NOI18N

        lblUserInfoUsername.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lblUserInfoUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUserInfoUsername.setText("Username: caigido");

        lblUserInfoPasswordTitle.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lblUserInfoPasswordTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblUserInfoPasswordTitle.setText("Password:");

        passFieldUserInfoPass.setBackground(new java.awt.Color(40, 40, 40));
        passFieldUserInfoPass.setForeground(new java.awt.Color(255, 255, 255));
        passFieldUserInfoPass.setText("jPasswordField1");
        passFieldUserInfoPass.setBorder(null);

        lblUserInfoChangePass.setBackground(new java.awt.Color(30, 215, 97));
        lblUserInfoChangePass.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        lblUserInfoChangePass.setForeground(new java.awt.Color(30, 215, 97));
        lblUserInfoChangePass.setText("Change password");
        lblUserInfoChangePass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUserInfoChangePassMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblUserInfoChangePassMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblUserInfoChangePassMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlUserInfoAccInfoLayout = new javax.swing.GroupLayout(pnlUserInfoAccInfo);
        pnlUserInfoAccInfo.setLayout(pnlUserInfoAccInfoLayout);
        pnlUserInfoAccInfoLayout.setHorizontalGroup(
            pnlUserInfoAccInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserInfoAccInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUserInfoAccInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUserInfoAccInfoLayout.createSequentialGroup()
                        .addComponent(lblUserInfoUsername)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlUserInfoAccInfoLayout.createSequentialGroup()
                        .addComponent(lblUserInfoPasswordTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(passFieldUserInfoPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                        .addComponent(lblUserInfoChangePass))))
        );
        pnlUserInfoAccInfoLayout.setVerticalGroup(
            pnlUserInfoAccInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserInfoAccInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUserInfoUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUserInfoAccInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUserInfoPasswordTitle)
                    .addComponent(passFieldUserInfoPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUserInfoChangePass))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlUserInfoLayout = new javax.swing.GroupLayout(pnlUserInfo);
        pnlUserInfo.setLayout(pnlUserInfoLayout);
        pnlUserInfoLayout.setHorizontalGroup(
            pnlUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserInfoLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(pnlUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserInfoTitle)
                    .addGroup(pnlUserInfoLayout.createSequentialGroup()
                        .addComponent(lblUserAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlUserInfoAccInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlUserInfoProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        pnlUserInfoLayout.setVerticalGroup(
            pnlUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserInfoLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lblUserInfoTitle)
                .addGap(18, 18, 18)
                .addGroup(pnlUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlUserInfoLayout.createSequentialGroup()
                        .addComponent(pnlUserInfoProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(pnlUserInfoAccInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(841, Short.MAX_VALUE))
        );

        pnlContent.add(pnlUserInfo, "userInfo");

        pnlUpdateUserInfo.setBackground(new java.awt.Color(34, 34, 34));

        lblUpdateUserInfoBack.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblUpdateUserInfoBack.setForeground(new java.awt.Color(255, 255, 255));
        lblUpdateUserInfoBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUpdateUserInfoBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblUpdateUserInfoBackMouseEntered(evt);
            }
        });

        lblUpdateUserInfoTitle.setFont(new java.awt.Font("Roboto", 0, 38)); // NOI18N
        lblUpdateUserInfoTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblUpdateUserInfoTitle.setText("Change Profile");

        pnlUpdateUserText.setBackground(new java.awt.Color(40, 40, 40));

        lblUpdateUserGender.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lblUpdateUserGender.setForeground(new java.awt.Color(255, 255, 255));
        lblUpdateUserGender.setText("Gender");

        txtFldUpdateUserName.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtFldUpdateUserName.setText("sdfsdfdsfd");
        txtFldUpdateUserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFldUpdateUserNameKeyPressed(evt);
            }
        });

        lblUpdateUserName.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lblUpdateUserName.setForeground(new java.awt.Color(255, 255, 255));
        lblUpdateUserName.setText("Name");

        rbtNewMale.setBackground(new java.awt.Color(40, 40, 40));
        btgGender.add(rbtNewMale);
        rbtNewMale.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbtNewMale.setForeground(new java.awt.Color(255, 255, 255));
        rbtNewMale.setText("Male");
        rbtNewMale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rbtNewMaleMouseEntered(evt);
            }
        });

        rbtNewFemale.setBackground(new java.awt.Color(40, 40, 40));
        btgGender.add(rbtNewFemale);
        rbtNewFemale.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbtNewFemale.setForeground(new java.awt.Color(255, 255, 255));
        rbtNewFemale.setText("Female");
        rbtNewFemale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rbtNewFemaleMouseEntered(evt);
            }
        });

        lblUpdateUserEmail.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lblUpdateUserEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblUpdateUserEmail.setText("Email");

        tfdNewemail.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        tfdNewemail.setText("sdfdsfsdf");
        tfdNewemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfdNewemailKeyPressed(evt);
            }
        });

        lblUpdateUserUpdate.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        lblUpdateUserUpdate.setForeground(new java.awt.Color(255, 255, 255));
        lblUpdateUserUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/update.png"))); // NOI18N
        lblUpdateUserUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUpdateUserUpdateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblUpdateUserUpdateMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout pnlUpdateUserTextLayout = new javax.swing.GroupLayout(pnlUpdateUserText);
        pnlUpdateUserText.setLayout(pnlUpdateUserTextLayout);
        pnlUpdateUserTextLayout.setHorizontalGroup(
            pnlUpdateUserTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateUserTextLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUpdateUserTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUpdateUserTextLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlUpdateUserTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtNewFemale)
                            .addComponent(rbtNewMale))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlUpdateUserTextLayout.createSequentialGroup()
                        .addGroup(pnlUpdateUserTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFldUpdateUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                            .addComponent(tfdNewemail)
                            .addGroup(pnlUpdateUserTextLayout.createSequentialGroup()
                                .addGroup(pnlUpdateUserTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblUpdateUserGender)
                                    .addComponent(lblUpdateUserEmail))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(pnlUpdateUserTextLayout.createSequentialGroup()
                        .addComponent(lblUpdateUserName)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUpdateUserTextLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUpdateUserUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
        );
        pnlUpdateUserTextLayout.setVerticalGroup(
            pnlUpdateUserTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateUserTextLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblUpdateUserName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFldUpdateUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblUpdateUserGender)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtNewMale)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtNewFemale)
                .addGap(18, 18, 18)
                .addComponent(lblUpdateUserEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfdNewemail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblUpdateUserUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlUpdateUserInfoLayout = new javax.swing.GroupLayout(pnlUpdateUserInfo);
        pnlUpdateUserInfo.setLayout(pnlUpdateUserInfoLayout);
        pnlUpdateUserInfoLayout.setHorizontalGroup(
            pnlUpdateUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateUserInfoLayout.createSequentialGroup()
                .addGroup(pnlUpdateUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUpdateUserInfoLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lblUpdateUserInfoBack))
                    .addGroup(pnlUpdateUserInfoLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(pnlUpdateUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUpdateUserInfoTitle)
                            .addComponent(pnlUpdateUserText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(372, Short.MAX_VALUE))
        );
        pnlUpdateUserInfoLayout.setVerticalGroup(
            pnlUpdateUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateUserInfoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblUpdateUserInfoBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUpdateUserInfoTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlUpdateUserText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(808, Short.MAX_VALUE))
        );

        pnlContent.add(pnlUpdateUserInfo, "updateUserInfo");

        pnlChangeUserPassword.setBackground(new java.awt.Color(34, 34, 34));

        lblChangePasswordName.setFont(new java.awt.Font("Roboto", 0, 38)); // NOI18N
        lblChangePasswordName.setForeground(new java.awt.Color(255, 255, 255));
        lblChangePasswordName.setText("Change Password");

        pnlUpdatePasswordPnl.setBackground(new java.awt.Color(40, 40, 40));

        lblcurrentpassword1.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lblcurrentpassword1.setForeground(new java.awt.Color(255, 255, 255));
        lblcurrentpassword1.setText("Current Password");

        pfdCurrentPassword.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        pfdCurrentPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pfdCurrentPasswordKeyPressed(evt);
            }
        });

        lblNewPassword1.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lblNewPassword1.setForeground(new java.awt.Color(255, 255, 255));
        lblNewPassword1.setText("New Password");

        pfdNewPassword.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        pfdNewPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pfdNewPasswordKeyPressed(evt);
            }
        });

        lblConfirmNewPassword1.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lblConfirmNewPassword1.setForeground(new java.awt.Color(255, 255, 255));
        lblConfirmNewPassword1.setText("Confirm New Password");

        pfdCoNewPassword.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        pfdCoNewPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pfdCoNewPasswordKeyPressed(evt);
            }
        });

        lblUpdatePass.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        lblUpdatePass.setForeground(new java.awt.Color(255, 255, 255));
        lblUpdatePass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/update.png"))); // NOI18N
        lblUpdatePass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUpdatePassMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblUpdatePassMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout pnlUpdatePasswordPnlLayout = new javax.swing.GroupLayout(pnlUpdatePasswordPnl);
        pnlUpdatePasswordPnl.setLayout(pnlUpdatePasswordPnlLayout);
        pnlUpdatePasswordPnlLayout.setHorizontalGroup(
            pnlUpdatePasswordPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdatePasswordPnlLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pnlUpdatePasswordPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblcurrentpassword1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pfdCurrentPassword, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNewPassword1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pfdNewPassword, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblConfirmNewPassword1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pfdCoNewPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUpdatePasswordPnlLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUpdatePass)
                .addGap(217, 217, 217))
        );
        pnlUpdatePasswordPnlLayout.setVerticalGroup(
            pnlUpdatePasswordPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdatePasswordPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblcurrentpassword1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pfdCurrentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNewPassword1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pfdNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblConfirmNewPassword1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pfdCoNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblUpdatePass)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblBackUser.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblBackUser.setForeground(new java.awt.Color(255, 255, 255));
        lblBackUser.setEnabled(false);
        lblBackUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackUserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBackUserMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout pnlChangeUserPasswordLayout = new javax.swing.GroupLayout(pnlChangeUserPassword);
        pnlChangeUserPassword.setLayout(pnlChangeUserPasswordLayout);
        pnlChangeUserPasswordLayout.setHorizontalGroup(
            pnlChangeUserPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChangeUserPasswordLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblBackUser)
                .addGap(26, 26, 26)
                .addGroup(pnlChangeUserPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblChangePasswordName)
                    .addComponent(pnlUpdatePasswordPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(383, Short.MAX_VALUE))
        );
        pnlChangeUserPasswordLayout.setVerticalGroup(
            pnlChangeUserPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChangeUserPasswordLayout.createSequentialGroup()
                .addGroup(pnlChangeUserPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChangeUserPasswordLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(lblChangePasswordName))
                    .addGroup(pnlChangeUserPasswordLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblBackUser)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlUpdatePasswordPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(851, Short.MAX_VALUE))
        );

        pnlContent.add(pnlChangeUserPassword, "chageUserPassword");

        scrPaneContent.setViewportView(pnlContent);

        layeredPane.add(scrPaneContent);
        scrPaneContent.setBounds(200, 100, 995, 565);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(layeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Set up search bar: display cancel icon and search on type
     */
    public void setUpSearchBar() {
        //Display cancel icon on typing       
        txtFldSearch.getDocument().addDocumentListener(new DocumentListener() {
            //when user start search -> display cancel search icon & set side menu icons to inactive & search
            @Override
            public void insertUpdate(DocumentEvent de) {
                try {
                    setInactiveIcons();
                    changeCancelSearchIcon();
                    search(txtFldSearch.getText());

                } catch (SQLException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                changeCancelSearchIcon();
                try {
                    //if user delete all text -> display nothing
                    if (txtFldSearch.getText().equals("")) {
                        pnlExtendedSubContent.removeAll();
                        pnlExtendedSubContent.revalidate();
                        pnlExtendedSubContent.repaint();
                    } else {
                        changeCancelSearchIcon();
                        search(txtFldSearch.getText());
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            //do nothing
            @Override
            public void changedUpdate(DocumentEvent de) {

            }

            //diplay or hide cancel icon when user searching
            public void changeCancelSearchIcon() {
                Runnable search = new Runnable() {
                    @Override
                    public void run() {
                        //if text field empty -> remove cancel-search icon
                        if (txtFldSearch.getText().isEmpty()) {
                            lblCancelSearch.setIcon(null);
                        } else {
                            lblCancelSearch.setIcon(cancelSearch);

                        }
                    }
                };
                SwingUtilities.invokeLater(search);

            }
        });
    }

    /**
     * Search and display search result in pnlExtendedSubContent
     *
     * @param str String to be search
     * @throws SQLException
     */
    public void search(String str) throws SQLException {
        //search movie
        ArrayList<Movie> searchResult = movieM.searchMovie(str);
        //if user start searching -> display panel that store result
        if (!currentFunction.equalsIgnoreCase("search")) {
            cl.show(pnlContent, "extendedContent");
            currentFunction = "search";
        }
        //set up panel content
        int numOfRow = (int) Math.ceil((double) searchResult.size() / 4);
        pnlContent.setPreferredSize(new Dimension(1000, 500 + (numOfRow - 1) * 310));
        //set up panel extented sub content       
        lblExtendedContentTitle.setText("Search Result");
        pnlExtendedSubContent.removeAll();
        pnlExtendedSubContent.revalidate();
        pnlExtendedSubContent.repaint();
        //if result found
        if (!searchResult.isEmpty()) {
            addMovieLabelToPanel(searchResult, pnlExtendedSubContent);

        } else {
            JLabel lbl = new JLabel();
            lbl.setText("Result not found");
            lbl.setFont(new Font("Roboto", 0, 18));
            lbl.setForeground(txtColor);
            pnlExtendedSubContent.add(lbl);
        }

    }

    /**
     * Delete all text in changeCancelSearchIcon bar on clicked
     *
     * @param evt
     */
    private void lblCancelSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCancelSearchMouseClicked
        txtFldSearch.setText("");
    }//GEN-LAST:event_lblCancelSearchMouseClicked

    private void txtFldSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFldSearchMouseClicked
        txtFldSearch.setRequestFocusEnabled(true);
    }//GEN-LAST:event_txtFldSearchMouseClicked

    /**
     * Open sub-menu on clicked
     *
     * @param evt
     */
    private void lblSubmenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSubmenuMouseClicked
        //if menu is already opened -> close menu
        if (pnlDropMenu.isVisible()) {
            pnlDropMenu.setVisible(false);
            lblSubmenu.setIcon(dropArrow);

        } else { //open menu
            pnlDropMenu.setVisible(true);
            lblSubmenu.setIcon(collapseArrow);
            //

        }

    }//GEN-LAST:event_lblSubmenuMouseClicked
    /**
     * Return to Log In frame
     *
     * @param evt
     */
    private void lblLogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogOutMouseClicked
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Confirm?", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            //open log in panel
            LogIn li = new LogIn();
            li.setVisible(true);
            li.setLocationRelativeTo(null);
            this.dispose();
        }

    }//GEN-LAST:event_lblLogOutMouseClicked
    /**
     * Effect on label dark theme
     *
     * @param evt
     */
    private void lblDarkThemeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDarkThemeMouseEntered
        lblDarkTheme.setIcon(darkModeOnHover);
    }//GEN-LAST:event_lblDarkThemeMouseEntered
    /**
     * Effect on label dark theme
     *
     * @param evt
     */
    private void lblDarkThemeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDarkThemeMouseExited
        lblDarkTheme.setIcon(darkModeOn);
    }//GEN-LAST:event_lblDarkThemeMouseExited
    /**
     * TODO: change interface to dark/light theme on clicked
     *
     * @param evt
     */
    private void lblDarkThemeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDarkThemeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblDarkThemeMouseClicked

    /**
     * Display pnlmMangementContent on clicked. Set other label inactive. Set
     * lblInsert to active. Set size of pnlCotent. Hide combo boxes which are
     * intended for update function
     */
    public void doInsertMouse() {
        setInactiveIcons();
        lblInsert.setIcon(insertActive);
        pnlContent.setPreferredSize(new Dimension(1000, 600));
        cbObjectForManagement.setSelectedItem("None");
        lblManagementTitle.setText("Insert");
        //hide components        
        //hide fill panel
        pnlFillInfo.setVisible(false);
        pnlFillInfo.setEnabled(false);
        //hide choose update item combo box
        lblUpdateChooseObjItem.setVisible(false);
        lblUpdateChooseObjItem.setEnabled(false);
        cbUpdateItem.setVisible(false);
        cbUpdateItem.setEnabled(false);
        lblUpdateConfirm.setVisible(false);
        lblUpdateConfirm.setEnabled(false);

        lblInsert.setForeground(Color.white);
        currentFunction = "Insert";
    }

    /**
     * call setPanel. show objectManagement. call doInsertMouse
     *
     * @param evt
     */
    private void lblInsertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInsertMouseClicked
        setPanel("objectManagementInsert");
        cl.show(pnlContent, "objectManagement");
        doInsertMouse();

    }//GEN-LAST:event_lblInsertMouseClicked
    /**
     * Display pnlmMangementContent on clicked. Set other label text color to
     * inactiveColor. Set lblUpdate text color to white. Set size of pnlCotent
     *
     *
     */
    public void doUpdateMouse() {
        //set side menu icons
        setInactiveIcons();
        lblUpdate.setIcon(updateActive);
        lblUpdate.setForeground(Color.white);
        pnlContent.setPreferredSize(new Dimension(995, 600));
        currentFunction = "Update";
        lblManagementTitle.setText("Update");
        cbObjectForManagement.setSelectedItem("None");
        //display component
        lblUpdateChooseObjItem.setVisible(true);
        lblUpdateChooseObjItem.setEnabled(true);
        cbUpdateItem.setVisible(true);
        cbUpdateItem.setEnabled(true);
        lblUpdateConfirm.setVisible(true);
        lblUpdateConfirm.setEnabled(true);
        //hide fill panel
        pnlFillInfo.setVisible(false);
        pnlFillInfo.setEnabled(false);
    }

    /**
     * call setPanel. show objectManagement. call doUpdateMouse.
     *
     * @param evt
     */
    private void lblUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUpdateMouseClicked
        setPanel("objectManagementUpdate");
        //display update panel
        cl.show(pnlContent, "objectManagement");
        doUpdateMouse();
    }//GEN-LAST:event_lblUpdateMouseClicked

    /**
     * Action when click on home label
     */
    public void setUpHome() {
        try {
            setInactiveIcons();
            lblHome.setIcon(homeActive);
            currentFunction = "Home";
            pnlContent.setPreferredSize(new Dimension(1010, 1300));
            scrPaneContent.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            setUpHomeInterface(4, 8);
        } catch (SQLException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Display pnlMainContent on clicked. Set other label text color to
     * inactiveColor. Set lblHome text color to white. Set size of pnlCotent
     *
     * @param evt
     */
    private void lblHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeMouseClicked
        cl.show(pnlContent, "mainContent");
        setPanel("mainContent");
        setUpHome();
    }//GEN-LAST:event_lblHomeMouseClicked
    /**
     * Action when click on favorite list
     */
    public void setUpFavList() {
        setInactiveIcons();
        lblFavList.setIcon(favListActive);
        lblExtendedContentTitle.setText("Favorite Movies");
        //clear all component in panel
        pnlExtendedSubContent.removeAll();
        pnlExtendedSubContent.revalidate();
        pnlExtendedSubContent.repaint();
        //pnlExtendedSubContent.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        try {
            //
            Client c = clientM.getClient(acc.getAcc_id());
            ArrayList<FavoriteMovie> favMovie = favMovieM.getFavortieMovie(c.getC_id());

            if (!favMovie.isEmpty()) {
                //get list of movie
                ArrayList<Movie> movieList = new ArrayList<>();
                for (int i = 0; i < favMovie.size(); i++) {
                    movieList.add(movieM.getMovie(favMovie.get(i).getM_id()));
                }
                //

                int numOfRow = (int) Math.ceil((double) favMovie.size() / 4);
                pnlContent.setPreferredSize(new Dimension(1000, 500 + (numOfRow - 1) * 310));

                addMovieLabelToPanel(movieList, pnlExtendedSubContent);

            } else {
                pnlContent.setPreferredSize(new Dimension(1000, 550));
                JLabel lbl = new JLabel();
                lbl.setText("Your favorite list is empty");
                lbl.setFont(new Font("Roboto", 0, 18));
                lbl.setForeground(txtColor);
                pnlExtendedSubContent.add(lbl);
            }
        } catch (SQLException | ClientException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Display pnlExtendedContent on clicked. Set lblFavlist text color to
     * white. Set other label text color to inactiveColor.
     *
     * @param evt
     */
    private void lblFavListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFavListMouseClicked
        setPanel("favList");
        cl.show(pnlContent, "extendedContent");
        setUpFavList();
    }//GEN-LAST:event_lblFavListMouseClicked
    /**
     * Action when click on browse label
     */
    public void setUpBrowse() {
        setInactiveIcons();
        lblBrowse.setIcon(browseActive);
        pnlContent.setPreferredSize(new Dimension(1000, 550));
        lblExtendedContentTitle.setText("Browse Movies");
        //clear all component in panel
        pnlExtendedSubContent.removeAll();
        pnlExtendedSubContent.revalidate();
        pnlExtendedSubContent.repaint();
        //add notification
        JLabel lbl = new JLabel();
        lbl.setText("This function is in developing");
        lbl.setFont(new Font("Roboto", 0, 18));
        lbl.setForeground(txtColor);
        pnlExtendedSubContent.add(lbl);
    }

    /**
     * display extended content panel and output notification
     *
     * @param evt
     */
    private void lblBrowseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBrowseMouseClicked
        setPanel("browse");
        cl.show(pnlContent, "extendedContent");
        setUpBrowse();

    }//GEN-LAST:event_lblBrowseMouseClicked
    /**
     * In Fill panel: set visible(false) and setEnable(false) to components in
     * pnlFillInfo
     */
    public void hideObjectFillPanel() {
        //hide other components except for Name lbl and txtFldName   
        //Released Year
        lblReleasedYear.setVisible(false);
        lblReleasedYear.setEnabled(false);
        txtFldReleasedYear.setVisible(false);
        txtFldReleasedYear.setEnabled(false);
        //Duration
        lblDuration.setVisible(false);
        lblDuration.setEnabled(false);
        txtFldDuration.setVisible(false);
        txtFldDuration.setEnabled(false);

        //Insert objects
        lblChooseObjTitle.setVisible(false);
        lblChooseObjTitle.setEnabled(false);
        cbChooseObject.setVisible(false);
        cbChooseObject.setVisible(false);

        lblChooseObjItemTitle.setVisible(false);
        lblChooseObjItemTitle.setEnabled(false);
        cbObjectItem.setVisible(false);
        cbObjectItem.setEnabled(false);

        lblObjItemResultTitle.setVisible(false);
        lblObjItemResultTitle.setEnabled(false);
        cbObjItemResult.setVisible(false);
        cbObjItemResult.setEnabled(false);
        //Add obj
        lblAddObj.setVisible(false);
        lblAddObj.setEnabled(false);
        //remove obj
        lblRemoveObj.setVisible(false);
        lblRemoveObj.setEnabled(false);
        //Description
        lblDesTitle.setVisible(false);
        lblDesTitle.setEnabled(false);
        scrPaneDescription.setVisible(false);
        scrPaneDescription.setEnabled(false);
        txtAreaDescription.setVisible(false);
        txtAreaDescription.setEnabled(false);
        //Thumbnail
        lblThumbnail.setVisible(false);
        lblThumbnail.setEnabled(false);
        lblChooseThumbnail.setVisible(false);
        lblChooseThumbnail.setEnabled(false);
        //trailer
        lblTrailerTitle.setVisible(false);
        lblTrailerTitle.setEnabled(false);
        txtFldTrailer.setVisible(false);
        txtFldTrailer.setEnabled(false);
        lblPlayTrailer.setVisible(false);
        lblPlayTrailer.setEnabled(false);
    }

    /**
     * In Fill panel: set visible(true) and setEnable(true) to components in
     * pnlFillInfo
     */
    public void showObjectFillPanel() {
        //Released Year
        lblReleasedYear.setVisible(true);
        lblReleasedYear.setEnabled(true);
        txtFldReleasedYear.setVisible(true);
        txtFldReleasedYear.setEnabled(true);
        //Duration
        lblDuration.setVisible(true);
        lblDuration.setEnabled(true);
        txtFldDuration.setVisible(true);
        txtFldDuration.setEnabled(true);
        //Insert objects
        lblChooseObjTitle.setVisible(true);
        lblChooseObjTitle.setEnabled(true);
        cbChooseObject.setVisible(true);
        cbChooseObject.setVisible(true);

        lblChooseObjItemTitle.setVisible(true);
        lblChooseObjItemTitle.setEnabled(true);
        cbObjectItem.setVisible(true);
        cbObjectItem.setEnabled(true);

        lblObjItemResultTitle.setVisible(true);
        lblObjItemResultTitle.setEnabled(true);
        cbObjItemResult.setVisible(true);
        cbObjItemResult.setEnabled(true);
        //Add obj
        lblAddObj.setVisible(true);
        lblAddObj.setEnabled(true);
        //remove obj
        lblRemoveObj.setVisible(true);
        lblRemoveObj.setEnabled(true);
        //Description
        lblDesTitle.setVisible(true);
        lblDesTitle.setEnabled(true);
        scrPaneDescription.setVisible(true);
        scrPaneDescription.setEnabled(true);
        txtAreaDescription.setVisible(true);
        txtAreaDescription.setEnabled(true);
        //Thumbnail
        lblThumbnail.setVisible(true);
        lblThumbnail.setEnabled(true);
        lblChooseThumbnail.setVisible(true);
        lblChooseThumbnail.setEnabled(true);
        //trailer
        lblTrailerTitle.setVisible(true);
        lblTrailerTitle.setEnabled(true);
        txtFldTrailer.setVisible(true);
        txtFldTrailer.setEnabled(true);
        lblPlayTrailer.setVisible(true);
        lblPlayTrailer.setEnabled(true);
    }

    /**
     * In pnlFillInfo: Set empty text for text fields. Remove all item in
     * cbObjItemResult. Clear all resultList
     */
    public void setEmptyValues() {
        //name
        txtFldFillName.setText("");
        //year
        txtFldReleasedYear.setText("");
        //duration
        txtFldDuration.setText("");
        //description
        txtAreaDescription.setText("");
        //choose object
        cbChooseObject.setSelectedIndex(0);
        //thumbnail
        m_thumbnail = "emptyThumbnail";
        lblThumbnail.setIcon(emptyThumbnail);
        //trailer
        txtFldTrailer.setText("");
        // lblPause.setText("Start");
        // this.isTrailerPlayable = mediaPlayer.mediaPlayer().media().play("");
        //ArrayLists
        cbObjItemResult.removeAllItems();
        actorResultList.clear();
        directorResultList.clear();
        genreResultList.clear();
        studioResultList.clear();
        countryResultList.clear();
    }

    /**
     * Remove all item in cbUpdateItem. Get all object and add to cbUpdateItem
     *
     * @param object
     * @throws java.sql.SQLException
     */
    public void setCbUpdateItem(String object) throws SQLException {

        if (object.equals("Movie")) {
            try {
                //remove all item in combo box
                cbUpdateItem.removeAllItems();
                //get all movie and add to combo box
                getListData(object);
                for (int i = 0; i < movieList.size(); i++) {
                    cbUpdateItem.addItem(movieList.get(i));
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (object.equals("Director") || object.equals("Actor")) {
            try {
                //remove all item in combo box
                cbUpdateItem.removeAllItems();
                if (object.equals("Director")) {
                    //get all director and add to combo box
                    getListData(object);
                    for (int i = 0; i < directorList.size(); i++) {
                        cbUpdateItem.addItem(directorList.get(i));
                    }
                } else {
                    //get all Actor and add to combo box
                    getListData(object);
                    for (int i = 0; i < actorList.size(); i++) {
                        cbUpdateItem.addItem(actorList.get(i));
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (object.equals("Studio") || object.equals("Genre") || object.equals("Country")) {

            try {
                //remove all item in combo box
                cbUpdateItem.removeAllItems();
                switch (object) {
                    case "Studio":
                        //get all studio and add to combo box
                        getListData(object);
                        for (int i = 0; i < studioList.size(); i++) {
                            cbUpdateItem.addItem(studioList.get(i));
                        }
                        break;
                    case "Genre":
                        //get all Genre and add to combo box
                        getListData(object);
                        for (int i = 0; i < genreList.size(); i++) {
                            cbUpdateItem.addItem(genreList.get(i));
                        }
                        break;
                    default:
                        //get all Country and add to combo box
                        getListData(object);
                        for (int i = 0; i < countryList.size(); i++) {
                            cbUpdateItem.addItem(countryList.get(i));
                        }
                        break;
                }

            } catch (SQLException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * When combo box element changed -> do following actions: Show fill info
     * panel or hide it. If condition not match -> set empty values. Display
     * necessary components and set labels title. If function = 'Update' ->
     * remove all item in combo box UpdateItem. Get all element of objects and
     * add to combo box Update Item
     *
     * @param evt
     */
    private void cbObjectForManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbObjectForManagementActionPerformed
        String object = (String) cbObjectForManagement.getSelectedItem();

        try {
            if (object.equals("Movie")) {
                //show fill info panel
                pnlFillInfo.setVisible(true);
                pnlFillInfo.setEnabled(true);
                //set panel Control size
                pnlContent.setPreferredSize(new Dimension(1000, 1075));
                //
                showObjectFillPanel();
                setEmptyValues();
                //display necessary components -> none
                lblNameTitle.setText(object + " Name:");
                lblReleasedYear.setText("Released Year:");
                //if user choose update
                if (currentFunction.equals("Update")) {
                    try {
                        setCbUpdateItem(object);
                    } catch (SQLException ex) {
                        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            } else if (object.equals("Director") || object.equals("Actor")) {
                //show fill info panel
                pnlFillInfo.setVisible(true);
                pnlFillInfo.setEnabled(true);
                //set panel Control size
                pnlContent.setPreferredSize(new Dimension(1000, 600));
                //hide other components
                hideObjectFillPanel();
                setEmptyValues();
                //display necessary components
                lblReleasedYear.setVisible(true);
                lblReleasedYear.setEnabled(true);
                txtFldReleasedYear.setVisible(true);
                txtFldReleasedYear.setEnabled(true);
                lblNameTitle.setText(object + " Name:");
                lblReleasedYear.setText("Age:");
                //if user choose update
                if (currentFunction.equals("Update")) {
                    if (object.equals("Director")) {
                        setCbUpdateItem(object);
                    } else {
                        setCbUpdateItem(object);

                    }
                }
            } else if (object.equals("Studio") || object.equals("Genre") || object.equals("Country")) {
                //show fill info panel
                pnlFillInfo.setVisible(true);
                pnlFillInfo.setEnabled(true);
                //set panel Control size
                pnlContent.setPreferredSize(new Dimension(1000, 600));
                //hide other components
                hideObjectFillPanel();
                setEmptyValues();
                //display necessary components -> none
                lblNameTitle.setText(object + " Name:");
                //if user choose update
                if (currentFunction.equals("Update")) {
                    if (object.equals("Studio")) {
                        setCbUpdateItem(object);
                    } else if (object.equals("Genre")) {
                        setCbUpdateItem(object);
                    } else {
                        setCbUpdateItem(object);
                    }

                }

            } else {
                //hide fill info panel
                pnlFillInfo.setVisible(false);
                pnlFillInfo.setEnabled(false);
                ////if user choose update
                if (currentFunction.equals("Update")) {
                    //cbObjItemResult.removeAllItems();
                    cbUpdateItem.removeAllItems();
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_cbObjectForManagementActionPerformed

    /**
     * Actions when insert button is clicked: get all info from text fields. If
     * currtentFunction='update' -> update, else -> insert
     *
     *
     * @param evt
     */
    private void lblInsertConfirmButtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInsertConfirmButtMouseClicked
        try {
            String obj = (String) cbObjectForManagement.getSelectedItem();
            switch (obj) {

                case "Movie":
                    int m_id = 0;
                    String name = "";
                    int m_year = 0;
                    int m_duration = 0;
                    int m_views = 1;
                    String m_description = "";
                    txtAreaDescription.getText();
                    String m_trailer = "";
                    String m_thumbnail = "emptyThumbnail.png";
                    int s_id = 0;
                    int country_id = 0;
                    boolean notError = true;
                    //Get input
                    //get movie ID (Only in update)
                    if (currentFunction.equals("Update")) {
                        Movie m = (Movie) cbUpdateItem.getSelectedItem();
                        m_id = m.getM_id();
                    }
                    //get name
                    name = txtFldFillName.getText();
                    if (name.isEmpty()) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, obj + " Name can't be empty!", "Movie Name Error!", JOptionPane.ERROR_MESSAGE);
                    } else if (name.length() > 40) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, obj + " Name length can't be greater than 40!", "Movie Name Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    System.out.println(name.length());
                    //get year
                    try {
                        m_year = Integer.parseInt(txtFldReleasedYear.getText());
                    } catch (NumberFormatException e) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, "Please enter an integer number!", "Release Year Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    //get duration
                    try {
                        m_duration = Integer.parseInt(txtFldDuration.getText());
                    } catch (NumberFormatException e) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, "Please enter an integer number!", "Movie Duration Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    //get description
                    m_description = txtAreaDescription.getText();
                    System.out.println(m_description.length());
                    if (m_description.isEmpty()) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, obj + " Description can't be empty!", "Description Error!", JOptionPane.ERROR_MESSAGE);
                    } else if (m_description.length() > 300) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, obj + " Description length can't be greater than 300!", "Description Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    //get m_thumbnail              
                    m_thumbnail = this.m_thumbnail;
                    if (m_thumbnail.length() > 50) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, "Thumbnnail name length can't be greater than 300!", "Thumbnail Error!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        //create destination path
                        Path dest = new File("src/" + movieThumbnailFolder + "/" + m_thumbnail).toPath();
                        //copy thumbnail picture to movieThumbnail folder                    
                        Files.copy(source.toPath(), dest, REPLACE_EXISTING);
                    }

                    //get m_trailer  
                    m_trailer = txtFldTrailer.getText();
                    if (m_trailer.isEmpty()) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, "Please enter a playable Trailer link!", "Trailer Link Error!", JOptionPane.ERROR_MESSAGE);
                    } else if (m_trailer.length() > 100) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, obj + " Trailer length can't be greater than 100!", "Trailer Link Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    //get a_id
                    if (actorResultList.isEmpty()) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, "Please choose an actor!", "Actor Input Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    //get d_id
                    if (directorResultList.isEmpty()) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, "Please choose a director!", "Director Input Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    //get g_id
                    if (genreResultList.isEmpty()) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, "Please choose a genre!", "Genre Input Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    //get s_id
                    if (studioResultList.isEmpty()) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, "Please choose a studio!", "Studio Input Error!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        //only get the first element
                        s_id = studioResultList.get(0).getS_id();
                    }
                    //get country_id
                    if (countryResultList.isEmpty()) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, "Please choose a country!", "Country Input Error!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        //only get the first element
                        country_id = countryResultList.get(0).getCountry_id();
                    }
                    //if there is  no eror
                    if (notError) {
                        try {
                            int choice = -1;
                            if (movieM.isNameExist(name)) {
                                Movie m = movieM.getMovie(name);
                                choice = JOptionPane.showConfirmDialog(this, "A movie with the name '" + name + "' already existed in the Databse (" + m + ")"
                                        + "\nDo you want to continue?", "Movie name Duplicate", JOptionPane.INFORMATION_MESSAGE);
                            } else if (!movieM.isNameExist(name) || choice == JOptionPane.YES_OPTION) {
                                if (currentFunction.equals("Insert")) {
                                    m_id = movieM.insert(name, m_year, m_views, m_duration, m_description, m_trailer, m_thumbnail, s_id, country_id);
                                    //set reference between Movie and others
                                    //Movie - Actor
                                    for (int i = 0; i < actorResultList.size(); i++) {
                                        actorMovieM.insert(m_id, actorResultList.get(i).getA_id());
                                    }
                                    //Movie - Director
                                    for (int i = 0; i < directorResultList.size(); i++) {
                                        directorMovieM.insert(m_id, directorResultList.get(i).getD_id());
                                    }
                                    //Movie - Genre
                                    for (int i = 0; i < genreResultList.size(); i++) {
                                        genreMovieM.insert(m_id, genreResultList.get(i).getG_id());
                                    }
                                    //success message
                                    JOptionPane.showMessageDialog(this, "New " + obj + " has been added succesfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
                                } else if (currentFunction.equals("Update")) {
                                    movieM.update(m_id, name, m_year, m_views, m_duration, m_description, m_trailer, m_thumbnail, s_id, country_id);
                                    //delete all references
                                    actorMovieM.delete(m_id);
                                    directorMovieM.delete(m_id);
                                    genreMovieM.delete(m_id);
                                    //Set reference between Movie and others
                                    //Movie - Actor
                                    for (int i = 0; i < actorResultList.size(); i++) {
                                        actorMovieM.insert(m_id, actorResultList.get(i).getA_id());
                                    }
                                    //Movie - Director
                                    for (int i = 0; i < directorResultList.size(); i++) {
                                        directorMovieM.insert(m_id, directorResultList.get(i).getD_id());
                                    }
                                    //Movie - Genre
                                    for (int i = 0; i < genreResultList.size(); i++) {
                                        genreMovieM.insert(m_id, genreResultList.get(i).getG_id());
                                    }
                                    //success message
                                    JOptionPane.showMessageDialog(this, obj + " has been updated succesfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);

                                }
                                //update combo box with new info
                                setCbUpdateItem("Movie");
                                //clear all data after inserted
                                setEmptyValues();
                            }
                        } catch (SQLException | MovieException ex) {
                            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;

                case "Actor":
                    //inits variables
                    int a_id = 0;
                    notError = true;
                    name = "";
                    int age = 0;

                    try {
                        //get actor ID (Only in update)
                        if (currentFunction.equals("Update")) {
                            Actor a = (Actor) cbUpdateItem.getSelectedItem();
                            a_id = a.getA_id();
                        }
                        name = txtFldFillName.getText();
                        //check if name is empty
                        if (name.isEmpty()) {
                            notError = false;
                            JOptionPane.showMessageDialog(this, obj + " Name can't be empty!", "Actor Name Error!", JOptionPane.ERROR_MESSAGE);
                        } else if (name.length() > 30) {
                            notError = false;
                            JOptionPane.showMessageDialog(this, obj + " Name length can't be greater than 30 characters!", "Actor Name Error!", JOptionPane.ERROR_MESSAGE);
                        }

                        age = Integer.parseInt(txtFldReleasedYear.getText());
                        //check if age is valid
                        if (age <= 0) {
                            notError = false;
                            JOptionPane.showMessageDialog(this, obj + "Age must be greater than 0!", "Actor Age Error!", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, "Please enter an integer number!", "Actor Age Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    //if there is  no eror
                    if (notError) {
                        try {
                            int choice = -1;
                            if (actorM.isNameExist(name)) {
                                Actor a = actorM.getActor(name);
                                choice = JOptionPane.showConfirmDialog(this, "An actor with the name '" + name + "' already existed in the Databse (" + a + ")"
                                        + "\nDo you want to continue?", "Actor name Duplicate", JOptionPane.INFORMATION_MESSAGE);
                            } else if (!actorM.isNameExist(name) || choice == JOptionPane.YES_OPTION) {
                                if (currentFunction.equals("Insert")) {
                                    actorM.insert(name, age);
                                    JOptionPane.showMessageDialog(this, "New " + obj + " has been added succesfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
                                    //update data
                                    //get actor
                                    actorList = actorM.getAllActor();
                                    actorM.sort(actorList);
                                } else if (currentFunction.equals("Update")) {
                                    actorM.update(a_id, name, age);
                                    JOptionPane.showMessageDialog(this, obj + " has been updated succesfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
                                    //update combo box with new info
                                    setCbUpdateItem("Actor");
                                }
                                //clear all data 
                                setEmptyValues();
                            }

                        } catch (SQLException | ActorException ex) {
                            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                //Insert Director
                case "Director":
                    //inits variables
                    int d_id = 0;
                    notError = true;
                    name = "";
                    age = 0;
                    try {
                        //get actor ID (Only in update)
                        if (currentFunction.equals("Update")) {
                            Director d = (Director) cbUpdateItem.getSelectedItem();
                            d_id = d.getD_id();
                        }
                        name = txtFldFillName.getText();
                        //check if name is empty
                        if (name.isEmpty()) {
                            notError = false;
                            JOptionPane.showMessageDialog(this, obj + " Name can't be empty!", "Director Name Error!", JOptionPane.ERROR_MESSAGE);
                        } else if (name.length() > 30) {
                            notError = false;
                            JOptionPane.showMessageDialog(this, obj + " Name can't be greater than 30!", "Director Name Error!", JOptionPane.ERROR_MESSAGE);
                        }
                        age = Integer.parseInt(txtFldReleasedYear.getText());
                        //check if age is valid
                        if (age <= 0) {
                            notError = false;
                            JOptionPane.showMessageDialog(this, obj + "Age must be greater than 0!", "Director Age Error!", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, "Please enter an integer number!", "Director Age Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    //if there is  no eror
                    if (notError) {
                        try {
                            int choice = -1;
                            if (directorM.isNameExist(name)) {
                                Director d = directorM.getDirector(name);
                                choice = JOptionPane.showConfirmDialog(this, "A Director with the name '" + name + "' already existed in the Databse (" + d + ")"
                                        + "\nDo you want to continue?", "Director name Duplicate", JOptionPane.INFORMATION_MESSAGE);
                            } else if (!directorM.isNameExist(name) || choice == JOptionPane.YES_OPTION) {
                                if (currentFunction.equals("Insert")) {
                                    directorM.insert(name, age);
                                    JOptionPane.showMessageDialog(this, "New " + obj + " has been added succesfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
                                    //update data
                                    //get director
                                    directorList = directorM.getAllDirector();
                                    directorM.sort(directorList);
                                } else if (currentFunction.equals("Update")) {
                                    directorM.update(d_id, name, age);
                                    JOptionPane.showMessageDialog(this, obj + " has been updated succesfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
                                    //update combo box with new info
                                    setCbUpdateItem("Director");
                                }

                                //clear all data after inserted
                                setEmptyValues();
                            }

                        } catch (SQLException | DirectorException ex) {
                            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    break;
                //Insert Genre
                case "Genre":
                    //inits variables
                    int g_id = 0;
                    notError = true;
                    name = "";
                    age = 0;
                    name = txtFldFillName.getText();

                    if (currentFunction.equals("Update")) {
                        Genre g = (Genre) cbUpdateItem.getSelectedItem();
                        g_id = g.getG_id();
                    }
                    //check if name is empty
                    if (name.isEmpty()) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, obj + " Name can't be empty!", "Genre Name Error!", JOptionPane.ERROR_MESSAGE);
                    } else if (name.length() > 30) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, obj + " Name length can't be greater than 30!", "Genre Name Error!", JOptionPane.ERROR_MESSAGE);
                    }

                    //if there is  no eror
                    if (notError) {
                        try {
                            int choice = -1;
                            if (genreM.isNameExist(name)) {
                                Genre g = genreM.getGenre(name);
                                choice = JOptionPane.showConfirmDialog(this, "A Genre with the name '" + name + "' already existed in the Databse (" + g + ")"
                                        + "\nDo you want to continue?", "Genre name Duplicate", JOptionPane.INFORMATION_MESSAGE);
                            } else if (!genreM.isNameExist(name) || choice == JOptionPane.YES_OPTION) {
                                if (currentFunction.equals("Insert")) {
                                    genreM.insert(name);
                                    JOptionPane.showMessageDialog(this, "New " + obj + " has been added succesfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
                                    //update data
                                    //get genre
                                    genreList = genreM.getAllGenre();
                                    genreM.sort(genreList);
                                } else if (currentFunction.equals("Update")) {
                                    genreM.update(g_id, name);
                                    JOptionPane.showMessageDialog(this, obj + " has been updated succesfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
                                    //update combo box with new info
                                    setCbUpdateItem("Genre");
                                }

                                //clear all data after inserted
                                setEmptyValues();
                            }

                        } catch (SQLException | GenreException ex) {
                            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                //Insert Studio
                case "Studio":
                    //inits variables
                    s_id = 0;
                    notError = true;
                    name = "";
                    age = 0;
                    name = txtFldFillName.getText();

                    if (currentFunction.equals("Update")) {
                        Studio s = (Studio) cbUpdateItem.getSelectedItem();
                        s_id = s.getS_id();
                    }
                    //check if name is empty
                    if (name.isEmpty()) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, obj + " Name can't be empty!", "Studio Name Error!", JOptionPane.ERROR_MESSAGE);
                    } else if (name.length() > 30) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, obj + " Name length can't be greater than 30!", "Studio Name Error!", JOptionPane.ERROR_MESSAGE);
                    }

                    //if there is  no eror
                    if (notError) {
                        try {
                            int choice = -1;
                            if (studioM.isNameExist(name)) {
                                Studio s = studioM.getStudio(name);
                                choice = JOptionPane.showConfirmDialog(this, "A Studio with the name '" + name + "' already existed in the Databse (" + s + ")"
                                        + "\nDo you want to add another one?", "Studio name Duplicate", JOptionPane.INFORMATION_MESSAGE);
                            } else if (!studioM.isNameExist(name) || choice == JOptionPane.YES_OPTION) {
                                if (currentFunction.equals("Insert")) {
                                    studioM.insert(name);
                                    JOptionPane.showMessageDialog(this, "New " + obj + " has been added succesfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
                                    //update data
                                    //get actor
                                    studioList = studioM.getAllStudio();
                                    studioM.sort(studioList);
                                } else if (currentFunction.equals("Update")) {
                                    studioM.update(s_id, name);
                                    JOptionPane.showMessageDialog(this, obj + " has been updated succesfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
                                    //update combo box with new info
                                    setCbUpdateItem("Studio");
                                }
                            }

                            //clear all data after inserted
                            setEmptyValues();
                        } catch (SQLException | StudioException ex) {
                            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                //Insert Country
                case "Country":
                    //inits variables
                    country_id = 0;
                    notError = true;
                    name = "";
                    age = 0;
                    name = txtFldFillName.getText();
                    if (currentFunction.equals("Update")) {
                        Country c = (Country) cbUpdateItem.getSelectedItem();
                        country_id = c.getCountry_id();
                    }
                    //check if name is empty
                    if (name.isEmpty()) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, obj + " Name can't be empty!", "Country Name Error!", JOptionPane.ERROR_MESSAGE);
                    } else if (name.length() > 30) {
                        notError = false;
                        JOptionPane.showMessageDialog(this, obj + " Name length can't be greater than 30!", "Country Name Error!", JOptionPane.ERROR_MESSAGE);
                    }

                    //if there is  no eror
                    if (notError) {
                        try {
                            int choice = -1;
                            if (countryM.isNameExist(name)) {
                                Country c = countryM.getCountry(name);
                                choice = JOptionPane.showConfirmDialog(this, "A Country with the name '" + name + "' already existed in the Databse (" + c + ")"
                                        + "\nDo you want to add another one?", "Country name Duplicate", JOptionPane.INFORMATION_MESSAGE);
                            } else if (!countryM.isNameExist(name) || choice == JOptionPane.YES_OPTION) {
                                if (currentFunction.equals("Insert")) {
                                    countryM.insert(name);
                                    JOptionPane.showMessageDialog(this, "New " + obj + " has been added succesfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
                                    //update data
                                    //get country
                                    countryList = countryM.getAllCountry();
                                    countryM.sort(countryList);
                                } else if (currentFunction.equals("Update")) {
                                    countryM.update(country_id, name);
                                    JOptionPane.showMessageDialog(this, obj + " has been updated succesfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
                                    //update combo box with new info
                                    setCbUpdateItem("Country");
                                }

                                //clear all data after inserted
                                setEmptyValues();
                            }

                        } catch (SQLException | CountryException ex) {
                            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                //Insert acount
                case "Account":
                    break;

            }
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblInsertConfirmButtMouseClicked

    /**
     * Set items in cbObjectItem corresponds to object got from cbChooseObject
     *
     * @param evt
     */
    private void cbChooseObjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChooseObjectActionPerformed
        try {
            String obj = (String) cbChooseObject.getSelectedItem();

            switch (obj) {
                case "Actor":
                    //get actor list
                    getListData(obj);
                    //remove all items
                    cbObjectItem.removeAllItems();
                    cbObjItemResult.removeAllItems();
                    //add item corresponds to object list
                    for (int i = 0; i < actorList.size(); i++) {
                        cbObjectItem.addItem(actorList.get(i));
                    }
                    //add item to result list
                    for (int i = 0; i < actorResultList.size(); i++) {
                        cbObjItemResult.addItem(actorResultList.get(i));
                    }
                    break;
                case "Director":
                    //get director list
                    getListData(obj);
                    //remove all items
                    cbObjectItem.removeAllItems();
                    cbObjItemResult.removeAllItems();
                    //add item corresponds to object list
                    for (int i = 0; i < directorList.size(); i++) {
                        cbObjectItem.addItem(directorList.get(i));
                    }
                    //add item to result list
                    for (int i = 0; i < directorResultList.size(); i++) {
                        cbObjItemResult.addItem(directorResultList.get(i));
                    }

                    break;
                case "Genre":
                    //get genre list
                    getListData(obj);
                    //remove all items
                    cbObjectItem.removeAllItems();
                    cbObjItemResult.removeAllItems();
                    //add item corresponds to object list
                    for (int i = 0; i < genreList.size(); i++) {
                        cbObjectItem.addItem(genreList.get(i));
                    }
                    //add item to result list
                    for (int i = 0; i < genreResultList.size(); i++) {
                        cbObjItemResult.addItem(genreResultList.get(i));
                    }

                    break;
                case "Studio":
                    //get studio list
                    getListData(obj);
                    //remove all items
                    cbObjectItem.removeAllItems();
                    cbObjItemResult.removeAllItems();
                    //add item corresponds to object list
                    for (int i = 0; i < studioList.size(); i++) {
                        cbObjectItem.addItem(studioList.get(i));
                    }

                    //add item to result list
                    for (int i = 0; i < studioResultList.size(); i++) {
                        cbObjItemResult.addItem(studioResultList.get(i));
                    }
                    break;
                case "Country":
                    //get country list
                    getListData(obj);
                    //remove all items
                    cbObjectItem.removeAllItems();
                    cbObjItemResult.removeAllItems();
                    //add item corresponds to object list
                    for (int i = 0; i < countryList.size(); i++) {
                        cbObjectItem.addItem(countryList.get(i));
                    }

                    //add item to result list
                    for (int i = 0; i < countryResultList.size(); i++) {
                        cbObjItemResult.addItem(countryResultList.get(i));
                    }
                    break;
                case "None":
                    cbObjectItem.removeAllItems();
                    cbObjItemResult.removeAllItems();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbChooseObjectActionPerformed

    /**
     * Add item to result list base on cbObjectItem
     *
     * @param evt
     */
    private void lblAddObjMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddObjMouseClicked
        String obj = (String) cbChooseObject.getSelectedItem();
        switch (obj) {
            case "Actor":
                Actor a = (Actor) cbObjectItem.getSelectedItem();
                if (!actorResultList.contains(a)) {
                    actorResultList.add(a);
                    cbObjItemResult.addItem(a);
                    cbObjItemResult.setSelectedItem(a);
                } else {
                    JOptionPane.showMessageDialog(this, "This " + obj + " have already been added ");
                }

                break;
            case "Director":
                Director d = (Director) cbObjectItem.getSelectedItem();
                if (!directorResultList.contains(d)) {
                    directorResultList.add(d);
                    cbObjItemResult.addItem(d);
                    cbObjItemResult.setSelectedItem(d);
                } else {
                    JOptionPane.showMessageDialog(this, "This " + obj + " have already been added ");
                }

                break;
            case "Genre":
                Genre g = (Genre) cbObjectItem.getSelectedItem();
                if (!genreResultList.contains(g)) {
                    genreResultList.add(g);
                    cbObjItemResult.addItem(g);
                    cbObjItemResult.setSelectedItem(g);
                } else {
                    JOptionPane.showMessageDialog(this, "This " + obj + " have already been added ");
                }

                break;
            case "Studio":
                Studio s = (Studio) cbObjectItem.getSelectedItem();
                if (cbObjItemResult.getItemCount() > 1) {
                    JOptionPane.showMessageDialog(this, "Sorry but a movie can only have one " + obj.toLowerCase());

                } else {
                    if (!studioResultList.contains(s)) {
                        studioResultList.add(s);
                        cbObjItemResult.addItem(s);
                        cbObjItemResult.setSelectedItem(s);
                    } else {
                        JOptionPane.showMessageDialog(this, "This " + obj + " have already been added ");
                    }
                }

                break;
            case "Country":
                Country c = (Country) cbObjectItem.getSelectedItem();
                if (cbObjItemResult.getItemCount() > 1) {
                    JOptionPane.showMessageDialog(this, "Sorry but a movie can only have one " + obj.toLowerCase());
                } else {
                    if (!countryResultList.contains(c)) {
                        countryResultList.add(c);
                        cbObjItemResult.addItem(c);
                        cbObjItemResult.setSelectedItem(c);
                    } else {
                        JOptionPane.showMessageDialog(this, "This " + obj + " have already been added ");
                    }
                }

                break;

        }
    }//GEN-LAST:event_lblAddObjMouseClicked
    /**
     * Remove item in result list
     *
     * @param evt
     */
    private void lblRemoveObjMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRemoveObjMouseClicked
        String obj = (String) cbChooseObject.getSelectedItem();
        switch (obj) {
            case "Actor":
                if (!actorResultList.isEmpty()) {
                    Actor a = (Actor) cbObjItemResult.getSelectedItem();
                    actorResultList.remove(a);
                    cbObjItemResult.removeItem(a);

                } else {
                    JOptionPane.showMessageDialog(this, "Please add a " + obj + " first", "Error!", WIDTH);
                }

                break;
            case "Director":
                if (!directorResultList.isEmpty()) {
                    Director d = (Director) cbObjItemResult.getSelectedItem();
                    directorResultList.remove(d);
                    cbObjItemResult.removeItem(d);

                } else {
                    JOptionPane.showMessageDialog(this, "Please add a " + obj + " first", "Error!", WIDTH);
                }

                break;
            case "Genre":
                if (!genreResultList.isEmpty()) {
                    Genre g = (Genre) cbObjItemResult.getSelectedItem();
                    genreResultList.remove(g);
                    cbObjItemResult.removeItem(g);

                } else {
                    JOptionPane.showMessageDialog(this, "Please add a " + obj + " first", "Error!", WIDTH);
                }

                break;
            case "Studio":
                if (!studioResultList.isEmpty()) {
                    Studio s = (Studio) cbObjItemResult.getSelectedItem();
                    studioResultList.remove(s);
                    cbObjItemResult.removeItem(s);

                } else {
                    JOptionPane.showMessageDialog(this, "Please add a " + obj + " first", "Error!", WIDTH);
                }

                break;
            case "Country":
                if (!countryResultList.isEmpty()) {
                    Country c = (Country) cbObjItemResult.getSelectedItem();
                    countryResultList.remove(c);
                    cbObjItemResult.removeItem(c);

                } else {
                    JOptionPane.showMessageDialog(this, "Please add a " + obj + " first", "Error!", WIDTH);
                }

                break;

        }
    }//GEN-LAST:event_lblRemoveObjMouseClicked

    /**
     * Get and Display thumbnail
     *
     * @param evt
     */
    private void lblChooseThumbnailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChooseThumbnailMouseClicked
        int choice = fileChooser.showOpenDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION) {
            try {
                File source = fileChooser.getSelectedFile();
                //check if file is an image
                String mimeType = Files.probeContentType(source.toPath());
                //if file is an image
                if (mimeType != null && mimeType.split("/")[0].equals("image")) {
                    this.m_thumbnail = source.getName();
                    this.source = source;
                    //resize and set thumbnail 
                    lblThumbnail.setIcon(new ImageIcon(new ImageIcon(source.getAbsolutePath()).getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH)));
                }//invalid 
                else {
                    JOptionPane.showMessageDialog(this, "Please choose an image", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_lblChooseThumbnailMouseClicked
    /**
     * Play trailer
     *
     * @param evt
     */
    private void lblPlayTrailerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPlayTrailerMouseClicked
        if (txtFldTrailer.getText().length() > 100) {
            JOptionPane.showMessageDialog(this, "Trailer length can't be greater than 100!", "Trailer Link Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            MediaPlayerFrame mp = new MediaPlayerFrame(this, txtFldTrailer.getText());
            this.setEnabled(false);
            mp.setVisible(true);
            mp.setLocationRelativeTo(null);
        }

        /*
        if (!txtFldTrailer.getText().isEmpty()) {
            isTrailerPlayable = mediaPlayer.mediaPlayer().media().prepare(txtFldTrailer.getText());
            if (!isTrailerPlayable) {
                JOptionPane.showMessageDialog(this, "Can't play trailer! Please make sure you've entered a correct URL", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                mediaPlayer.mediaPlayer().controls().play();
                lblPause.setText("Pause");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter Trailer URL", "Error", JOptionPane.ERROR_MESSAGE);
        }
         */
    }//GEN-LAST:event_lblPlayTrailerMouseClicked
    /**
     * Get object in cbUpdateItem. Display and add info to pnlFillInfo
     *
     * @param evt
     */
    private void lblUpdateConfirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUpdateConfirmMouseClicked
        try {
            String obj = (String) cbObjectForManagement.getSelectedItem();

            switch (obj) {
                case "Movie":

                    Movie m = (Movie) cbUpdateItem.getSelectedItem();
                    //set data
                    //set name
                    txtFldFillName.setText(m.getM_name());
                    //set year
                    txtFldReleasedYear.setText(String.valueOf(m.getM_year()));
                    //set duration
                    txtFldDuration.setText(String.valueOf(m.getM_duration()));
                    //set desciption
                    txtAreaDescription.setText(m.getM_description());
                    //set movie's objects
                    ArrayList<ActorInMovie> actorMResultList = actorMovieM.getActorInMovie(m.getM_id());
                    ArrayList<DirectorInMovie> directorMResultList = directorMovieM.getDirectorInMovie(m.getM_id());
                    ArrayList<GenreInMovie> genreMResultList = genreMovieM.getGenreInMovie(m.getM_id());
                    //add actor of movie to result list
                    for (int i = 0; i < actorMResultList.size(); i++) {
                        //get list of ActorInMovie (aMList) -> get Actor from a_id in aMList -> add Actor to result list
                        actorResultList.add(actorM.getActor(actorMResultList.get(i).getA_id()));
                    }
                    //add director of movie to result list
                    for (int i = 0; i < directorMResultList.size(); i++) {
                        directorResultList.add(directorM.getDirector(directorMResultList.get(i).getD_id()));
                    }
                    //add genre of movie to result list
                    for (int i = 0; i < genreMResultList.size(); i++) {
                        genreResultList.add(genreM.getGenre(genreMResultList.get(i).getG_id()));
                    }
                    //add studio to result list
                    studioResultList.add(studioM.getStudio(m.getS_id()));
                    //add country to result list
                    countryResultList.add(countryM.getCountry(m.getCountry_id()));
                    //set thumbnail
                    m_thumbnail = m.getM_thumbnail();
                    source = new File("src/" + movieThumbnailFolder + "/" + m_thumbnail);
                    lblThumbnail.setIcon(new ImageIcon(new ImageIcon("src/" + this.movieThumbnailFolder + "/" + m.getM_thumbnail()).getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH)));
                    //set trailer
                    txtFldTrailer.setText(m.getM_trailer());

                    break;

                case "Director":

                    Director d = (Director) cbUpdateItem.getSelectedItem();
                    //set data
                    txtFldFillName.setText(d.getD_name());
                    txtFldReleasedYear.setText(String.valueOf(d.getD_age()));
                    break;

                case "Actor":

                    Actor a = (Actor) cbUpdateItem.getSelectedItem();
                    //set data
                    txtFldFillName.setText(a.getA_name());
                    txtFldReleasedYear.setText(String.valueOf(a.getA_age()));
                    break;

                case "Genre":

                    Genre g = (Genre) cbUpdateItem.getSelectedItem();
                    //set data
                    txtFldFillName.setText(g.getG_name());
                    break;

                case "Studio":

                    Studio s = (Studio) cbUpdateItem.getSelectedItem();
                    //set data
                    txtFldFillName.setText(s.getS_name());
                    break;

                case "Country":

                    Country c = (Country) cbUpdateItem.getSelectedItem();
                    //set data
                    txtFldFillName.setText(c.getCountry_name());
                    break;
                default: //hide fill info panel
                    pnlFillInfo.setVisible(false);
                    pnlFillInfo.setEnabled(false);
                //
            }
        } catch (SQLException | StudioException | CountryException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblUpdateConfirmMouseClicked

    private void rbtNewMaleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtNewMaleMouseEntered
        rbtNewMale.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_rbtNewMaleMouseEntered

    private void rbtNewFemaleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtNewFemaleMouseEntered
        rbtNewFemale.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_rbtNewFemaleMouseEntered
    /**
     * setting Update Player
     */
    public void doUpdatePlayer() {
        String newEmailError = "", newNameError = "",
                newGenderError = "";
        int id = acc.getAcc_id();//get id
        String newName = txtFldUpdateUserName.getText();//getname

        int newGender = 1;
        //get gender
        if (rbtNewMale.isSelected()) {
            newGender = 1;
        } else if (rbtNewFemale.isSelected()) {
            newGender = 0;
        }
        //get new email
        String newEmail = tfdNewemail.getText();

        //check name
        if (newName.isEmpty()) {
            newNameError = "Name is not null";
        }

        String re_email = "/^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$/";
        // check email
        if (newEmail.matches(re_email) || newEmail.isEmpty()) {
            newEmailError = "Email Malformed";
        }
        //show error
        if (!newNameError.isEmpty() || !newEmailError.isEmpty()
                || !newGenderError.isEmpty()) {
            String errorStr = "";
            //create error string
            if (!newNameError.isEmpty()) {
                errorStr += newNameError + "\n";
            }
            if (!newEmailError.isEmpty()) {
                errorStr += newEmailError + "\n";
            }
            if (!newGenderError.isEmpty()) {
                errorStr += newGenderError + "\n";
            }

            //show error
            JOptionPane.showMessageDialog(this, errorStr);
        } else {
            ClientModel cm = new ClientModel();
            try {
                //update in database
                cm.update(id, newName, newEmail, newGender, id);
            } catch (SQLException | ClientException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            //show dialog 
            JOptionPane.showMessageDialog(this, "Update information successfully");
        }
    }

    /**
     * set Player from frame UpdatePlayer
     *
     * @throws SQLException
     * @throws ClientException
     */
    public void setUpdatePlayer() throws SQLException, ClientException {
        Client user = clientM.getClient(acc.getAcc_id());
        try {
            //set name
            lbluserInfoName.setText(user.getC_name());
            txtFldUpdateUserName.setText(user.getC_name());
            //set email 
            lblUserInfoEmail.setText("Email: " + user.getC_email());
            tfdNewemail.setText(user.getC_email());
            //set gender
            String gender = "Gender: ";
            if (user.getC_gender() == 1) {
                lblUserInfoGender.setText(gender + "Male");
                btgGender.setSelected(rbtNewMale.getModel(), true);
            } else if (user.getC_gender() == 0) {
                lblUserInfoGender.setText(gender + "Female");
                btgGender.setSelected(rbtNewFemale.getModel(), true);
            }
            //set username
            lblUserInfoUsername.setText("Username: " + acc.getAcc_username());
            passFieldUserInfoPass.setText(acc.getAcc_password());
            //set avatar
            ImageIcon avatar = new ImageIcon(new ImageIcon("src/img/avatar.png").getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
            lblUserAvatar.setIcon(avatar);
            //set null password in pnlChangePassword
            pfdCurrentPassword.setText("");
            pfdNewPassword.setText("");
            pfdCoNewPassword.setText("");
        } catch (NullPointerException ex) {
            txtFldUpdateUserName.setText("");
            tfdNewemail.setText("");
        }

    }

    public void doChangePlayer() throws SQLException, AccountException {
        String errorPass = "", newConfirmPassError = "", currentPasswordError = "", strError = "", passwordError2 = "";
        int id = acc.getAcc_id();//get id
        AccountModel am = new AccountModel();
        String currentPassword = pfdCurrentPassword.getText();//get current password 
        String newPassword = pfdNewPassword.getText();//get new password
        String confirmNewPassword = pfdCoNewPassword.getText();//get confirm New password
        //check charrater greater than 6
        if (!newPassword.matches("\\w{6,}")) {
            passwordError2 = "Password greater than 6";
        }
        //check password
        if (!newPassword.equalsIgnoreCase(confirmNewPassword)) {
            newConfirmPassError = "Confirm Password must match the password";
        }
        //check password
        if (currentPassword.isEmpty() && newPassword.isEmpty() && confirmNewPassword.isEmpty()) {
            newPassword = acc.getAcc_password();
            errorPass = "Current Password, New Password, Confirm New is not null";
        } else if (currentPassword.isEmpty() && !newPassword.isEmpty() && !confirmNewPassword.isEmpty()) {
            errorPass = "Current Password is not null";
        } else if (!currentPassword.isEmpty() && newPassword.isEmpty() && !confirmNewPassword.isEmpty()) {
            errorPass = "New Password is not null";
        } else if (!currentPassword.isEmpty() && !newPassword.isEmpty() && confirmNewPassword.isEmpty()) {
            errorPass = "Confirm New Password is not null";
        } else if (!currentPassword.isEmpty() && newPassword.isEmpty() && confirmNewPassword.isEmpty()) {
            errorPass = "New Password,Confirm New Password is not null";
        } else if (currentPassword.isEmpty() && !newPassword.isEmpty() && confirmNewPassword.isEmpty()) {
            errorPass = "Current Password,Confirm New Password is not null";
        } else if (currentPassword.isEmpty() && newPassword.isEmpty() && !confirmNewPassword.isEmpty()) {
            errorPass = "Current Password,New Password is not null";
        } else {
            if (!acc.getAcc_password().equalsIgnoreCase(currentPassword)) {
                currentPasswordError = "Enter wrong current Password";
            }
        }
        //check error is null
        if (!newConfirmPassError.isEmpty() || !errorPass.isEmpty() || !currentPasswordError.isEmpty()) {
            if (!newConfirmPassError.isEmpty()) {
                strError += newConfirmPassError + "\n";
            }
            if (!errorPass.isEmpty()) {
                strError += errorPass + "\n";
            }
            if (!currentPasswordError.isEmpty()) {
                strError += currentPasswordError + "\n";
            }
            if (!passwordError2.isEmpty()) {
                strError += passwordError2;
            }
            JOptionPane.showMessageDialog(this, strError);
        } else {
            acc = am.getAccount(acc.getAcc_username(), newPassword);
            am.update(id, newPassword, acc.getAcc_level(), acc.getAcc_status());
            JOptionPane.showMessageDialog(this, "Update information successfully");
        }

    }

    /**
     * Action when user press enter in email text field
     *
     * @param evt
     */
    private void tfdNewemailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdNewemailKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            doUpdatePlayer();
        }
    }//GEN-LAST:event_tfdNewemailKeyPressed

    private void lblUpdateUserUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUpdateUserUpdateMouseClicked
        doUpdatePlayer();
    }//GEN-LAST:event_lblUpdateUserUpdateMouseClicked

    private void lblUpdateUserInfoBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUpdateUserInfoBackMouseClicked
        cl.show(pnlContent, "userInfo");
        pnlContent.setPreferredSize(new Dimension(1000, 550));
        try {
            setUpdatePlayer();
        } catch (SQLException | ClientException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblUpdateUserInfoBackMouseClicked

    private void lblUpdateUserInfoBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUpdateUserInfoBackMouseEntered
        lblUpdateUserInfoBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblUpdateUserInfoBackMouseEntered

    private void txtFldUpdateUserNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFldUpdateUserNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            doUpdatePlayer();
        }
    }//GEN-LAST:event_txtFldUpdateUserNameKeyPressed

    private void lblUpdateUserUpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUpdateUserUpdateMouseEntered
        lblUpdateUserUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblUpdateUserUpdateMouseEntered

    public void setUpPersonalInformation() {

    }

    private void lblUsernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsernameMouseClicked
        cl.show(pnlContent, "userInfo");
        setPanel("userInfo");
        lblUsername.setBackground(activeElement);
        setInactiveIcons();
        pnlContent.setPreferredSize(new Dimension(1000, 550));


    }//GEN-LAST:event_lblUsernameMouseClicked

    private void lblUsernameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsernameMouseEntered
        Font font;
        font = lblUsername.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);  //set text to underline on mouse hover
        lblUsername.setFont(font.deriveFont(attribute));
        lblUsername.setForeground(activeElement);
        // lblUsername.setBackground(backgroundColor);
        lblUsername.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblUsernameMouseEntered

    private void lblUsernameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsernameMouseExited
        Font font;
        font = lblUsername.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, -1);  //set text to normal
        lblUsername.setFont(font.deriveFont(attribute));
        lblUsername.setForeground(Color.WHITE);
        //lblUsername.setBackground(backgroundColor);
    }//GEN-LAST:event_lblUsernameMouseExited

    private void pfdCurrentPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfdCurrentPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                doChangePlayer();
            } catch (SQLException | AccountException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_pfdCurrentPasswordKeyPressed

    private void pfdNewPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfdNewPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                doChangePlayer();
            } catch (SQLException | AccountException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_pfdNewPasswordKeyPressed

    private void pfdCoNewPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfdCoNewPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                doChangePlayer();
            } catch (SQLException | AccountException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_pfdCoNewPasswordKeyPressed

    private void lblUpdatePassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUpdatePassMouseClicked
        try {
            doChangePlayer();
        } catch (SQLException | AccountException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblUpdatePassMouseClicked

    private void lblUpdatePassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUpdatePassMouseEntered
        lblUpdatePass.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblUpdatePassMouseEntered

    private void lblBackUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackUserMouseClicked
        cl.show(pnlContent, "userInfo");
        pnlContent.setPreferredSize(new Dimension(1000, 550));
        try {
            setUpdatePlayer();
        } catch (SQLException | ClientException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblBackUserMouseClicked

    private void lblBackUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackUserMouseEntered
        lblBackUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblBackUserMouseEntered

    private void lblUserInfoChangePassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserInfoChangePassMouseClicked
        cl.show(pnlContent, "chageUserPassword");
        setInactiveIcons();
        setPanel("updateUserInfo");
    }//GEN-LAST:event_lblUserInfoChangePassMouseClicked

    private void lblUserInfoChangeProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserInfoChangeProfileMouseClicked
        cl.show(pnlContent, "updateUserInfo");
        setInactiveIcons();
        setPanel("chageUserPassword");

    }//GEN-LAST:event_lblUserInfoChangeProfileMouseClicked

    private void lblUserInfoChangeProfileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserInfoChangeProfileMouseEntered
        Font font;
        font = lblUserInfoChangeProfile.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);  //set text to underline on mouse hover
        lblUserInfoChangeProfile.setFont(font.deriveFont(attribute));
        lblUserInfoChangeProfile.setForeground(activeElement);
        // lblUsername.setBackground(backgroundColor);
        lblUserInfoChangeProfile.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblUserInfoChangeProfileMouseEntered

    private void lblUserInfoChangeProfileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserInfoChangeProfileMouseExited
        Font font;
        font = lblUserInfoChangeProfile.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, -1);  //set text to normal
        lblUserInfoChangeProfile.setFont(font.deriveFont(attribute));
        lblUserInfoChangeProfile.setForeground(activeElement);
    }//GEN-LAST:event_lblUserInfoChangeProfileMouseExited

    private void lblUserInfoChangePassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserInfoChangePassMouseEntered
        Font font;
        font = lblUserInfoChangePass.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);  //set text to underline on mouse hover
        lblUserInfoChangePass.setFont(font.deriveFont(attribute));
        lblUserInfoChangePass.setForeground(activeElement);
        // lblUsername.setBackground(backgroundColor);
        lblUserInfoChangePass.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblUserInfoChangePassMouseEntered

    private void lblUserInfoChangePassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserInfoChangePassMouseExited
        Font font;
        font = lblUserInfoChangePass.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, -1);  //set text to normal
        lblUserInfoChangePass.setFont(font.deriveFont(attribute));
        lblUserInfoChangePass.setForeground(activeElement);
    }//GEN-LAST:event_lblUserInfoChangePassMouseExited
    /**
     * knot back
     *
     * @param evt
     */
    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        if (statusBack == 0) {
            cl.show(pnlContent, currentLocation(1));
        }
    }//GEN-LAST:event_lblBackMouseClicked
    /**
     * knot froward
     *
     * @param evt
     */
    private void lblNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNextMouseClicked
        if (statusForward == 0) {
            cl.show(pnlContent, currentLocation(0));
        }
    }//GEN-LAST:event_lblNextMouseClicked

    private void lblChooseThumbnailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChooseThumbnailMouseEntered
        lblChooseThumbnail.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblChooseThumbnailMouseEntered

    private void lblInsertConfirmButtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInsertConfirmButtMouseEntered
        lblInsertConfirmButt.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblInsertConfirmButtMouseEntered
    /**
     * Effect on add object label
     *
     * @param evt
     */
    private void lblAddObjMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddObjMouseEntered
        Font font;
        font = lblAddObj.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);  //set text to underline on mouse hover
        lblAddObj.setFont(font.deriveFont(attribute));
        lblAddObj.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblAddObjMouseEntered
    /**
     * Effect on add object label
     *
     * @param evt
     */
    private void lblAddObjMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddObjMouseExited
        Font font;
        font = lblAddObj.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, -1);  //set text to normal
        lblAddObj.setFont(font.deriveFont(attribute));
    }//GEN-LAST:event_lblAddObjMouseExited
    /**
     * Effect on remove object label
     *
     * @param evt
     */
    private void lblRemoveObjMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRemoveObjMouseEntered
        Font font;
        font = lblRemoveObj.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);  //set text to underline on mouse hover
        lblRemoveObj.setFont(font.deriveFont(attribute));
        lblRemoveObj.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblRemoveObjMouseEntered
    /**
     * Effect on remove object label
     *
     * @param evt
     */
    private void lblRemoveObjMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRemoveObjMouseExited
        Font font;
        font = lblRemoveObj.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, -1);  //set text to normal
        lblRemoveObj.setFont(font.deriveFont(attribute));
    }//GEN-LAST:event_lblRemoveObjMouseExited

    private void lblPlayTrailerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPlayTrailerMouseEntered
        Font font;
        font = lblPlayTrailer.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);  //set text to underline on mouse hover
        lblPlayTrailer.setFont(font.deriveFont(attribute));
        lblPlayTrailer.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblPlayTrailerMouseEntered

    private void lblPlayTrailerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPlayTrailerMouseExited
        Font font;
        font = lblPlayTrailer.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, -1);  //set text to normal
        lblPlayTrailer.setFont(font.deriveFont(attribute));
    }//GEN-LAST:event_lblPlayTrailerMouseExited

    private void lblUpdateConfirmMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUpdateConfirmMouseEntered
        Font font;
        font = lblUpdateConfirm.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);  //set text to underline on mouse hover
        lblUpdateConfirm.setFont(font.deriveFont(attribute));
        lblUpdateConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblUpdateConfirmMouseEntered

    private void lblUpdateConfirmMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUpdateConfirmMouseExited
        Font font;
        font = lblUpdateConfirm.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, -1);  //set text to normal
        lblUpdateConfirm.setFont(font.deriveFont(attribute));
    }//GEN-LAST:event_lblUpdateConfirmMouseExited

    private void lblLogOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogOutMouseEntered
        lblLogOut.setIcon(logOutHover);
    }//GEN-LAST:event_lblLogOutMouseEntered

    private void lblLogOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogOutMouseExited
        lblLogOut.setIcon(logOut);
    }//GEN-LAST:event_lblLogOutMouseExited

    private void lblBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseEntered
        /*if (statusBack == 0) {
            lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } */
    }//GEN-LAST:event_lblBackMouseEntered

    private void lblNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNextMouseEntered
        /*  if (statusForward == 0) {
           lblNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } */
    }//GEN-LAST:event_lblNextMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGender;
    private javax.swing.JComboBox<Object> cbChooseObject;
    private javax.swing.JComboBox<Object> cbObjItemResult;
    private javax.swing.JComboBox<String> cbObjectForManagement;
    private javax.swing.JComboBox<Object> cbObjectItem;
    private javax.swing.JComboBox<Object> cbUpdateItem;
    private javax.swing.JSeparator divider1;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLayeredPane layeredPane;
    private javax.swing.JLabel lblAddObj;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblBackUser;
    private javax.swing.JLabel lblBrowse;
    private javax.swing.JLabel lblCancelSearch;
    private javax.swing.JLabel lblChangePasswordName;
    private javax.swing.JLabel lblChooseObjAtFirst;
    private javax.swing.JLabel lblChooseObjItemTitle;
    private javax.swing.JLabel lblChooseObjTitle;
    private javax.swing.JLabel lblChooseThumbnail;
    private javax.swing.JLabel lblConfirmNewPassword1;
    private javax.swing.JLabel lblDarkTheme;
    private javax.swing.JLabel lblDesTitle;
    private javax.swing.JLabel lblDiscoverMoviesTitle;
    private javax.swing.JLabel lblDuration;
    private javax.swing.JLabel lblExtendedContentTitle;
    private javax.swing.JLabel lblFavList;
    private javax.swing.JLabel lblHome;
    private javax.swing.JLabel lblInsert;
    private javax.swing.JLabel lblInsertConfirmButt;
    private javax.swing.JLabel lblLogOut;
    private javax.swing.JLabel lblManagementTitle;
    private javax.swing.JLabel lblMngToolTitle;
    private javax.swing.JLabel lblMostViewTitle;
    private javax.swing.JLabel lblMovieInfoCast;
    private javax.swing.JLabel lblMovieInfoCountry;
    private javax.swing.JLabel lblMovieInfoCountryTitle;
    private javax.swing.JLabel lblMovieInfoDirector;
    private javax.swing.JLabel lblMovieInfoFav;
    private javax.swing.JLabel lblMovieInfoPLayTrailer;
    private javax.swing.JLabel lblMovieInfoStudio;
    private javax.swing.JLabel lblMovieInfoStudioTitle;
    private javax.swing.JLabel lblMovieInfoThumbnail;
    private javax.swing.JLabel lblMovieInfolYear_Duration_Genre;
    private javax.swing.JLabel lblNameTitle;
    private javax.swing.JLabel lblNewPassword1;
    private javax.swing.JLabel lblNext;
    private javax.swing.JLabel lblObjItemResultTitle;
    private javax.swing.JLabel lblPlayTrailer;
    private javax.swing.JLabel lblReleasedYear;
    private javax.swing.JLabel lblRemoveObj;
    private javax.swing.JLabel lblSearchBar;
    private javax.swing.JLabel lblSubmenu;
    private javax.swing.JLabel lblThumbnail;
    private javax.swing.JLabel lblTrailerTitle;
    private javax.swing.JLabel lblUpdate;
    private javax.swing.JLabel lblUpdateChooseObjItem;
    private javax.swing.JLabel lblUpdateConfirm;
    private javax.swing.JLabel lblUpdatePass;
    private javax.swing.JLabel lblUpdateUserEmail;
    private javax.swing.JLabel lblUpdateUserGender;
    private javax.swing.JLabel lblUpdateUserInfoBack;
    private javax.swing.JLabel lblUpdateUserInfoTitle;
    private javax.swing.JLabel lblUpdateUserName;
    private javax.swing.JLabel lblUpdateUserUpdate;
    private javax.swing.JLabel lblUserAvatar;
    private javax.swing.JLabel lblUserInfoChangePass;
    private javax.swing.JLabel lblUserInfoChangeProfile;
    private javax.swing.JLabel lblUserInfoEmail;
    private javax.swing.JLabel lblUserInfoGender;
    private javax.swing.JLabel lblUserInfoPasswordTitle;
    private javax.swing.JLabel lblUserInfoTitle;
    private javax.swing.JLabel lblUserInfoUsername;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblcurrentpassword1;
    private javax.swing.JLabel lbluserInfoName;
    private javax.swing.JPasswordField passFieldUserInfoPass;
    private javax.swing.JPasswordField pfdCoNewPassword;
    private javax.swing.JPasswordField pfdCurrentPassword;
    private javax.swing.JPasswordField pfdNewPassword;
    private javax.swing.JPanel pnlChangeUserPassword;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlDiscoverMovie;
    private javax.swing.JPanel pnlDropMenu;
    private javax.swing.JPanel pnlExtendedContent;
    private javax.swing.JPanel pnlExtendedSubContent;
    private javax.swing.JPanel pnlFillInfo;
    private javax.swing.JPanel pnlMainContent;
    private javax.swing.JPanel pnlManagementContent;
    private javax.swing.JPanel pnlManagementTool;
    private javax.swing.JPanel pnlMostViewed;
    private javax.swing.JPanel pnlMovieInfo;
    private javax.swing.JPanel pnlSideMenu;
    private javax.swing.JPanel pnlTopNav;
    private javax.swing.JPanel pnlUpdatePasswordPnl;
    private javax.swing.JPanel pnlUpdateUserInfo;
    private javax.swing.JPanel pnlUpdateUserText;
    private javax.swing.JPanel pnlUserInfo;
    private javax.swing.JPanel pnlUserInfoAccInfo;
    private javax.swing.JPanel pnlUserInfoProfile;
    private javax.swing.JRadioButton rbtNewFemale;
    private javax.swing.JRadioButton rbtNewMale;
    private javax.swing.JScrollPane scrPaneContent;
    private javax.swing.JScrollPane scrPaneDescription;
    private javax.swing.JScrollPane scrpaneMovieDescription;
    private javax.swing.JScrollPane scrpaneMovieInfoCast;
    private javax.swing.JScrollPane scrpaneMovieInfoDirector;
    private javax.swing.JScrollPane scrpaneMovieTitle;
    private javax.swing.JTextField tfdNewemail;
    private javax.swing.JTextArea txtAreaDescription;
    private javax.swing.JTextArea txtAreaMovieDescription;
    private javax.swing.JTextArea txtAreaMovieInfoCast;
    private javax.swing.JTextArea txtAreaMovieInfoDirector;
    private javax.swing.JTextArea txtAreaMovieInfoTitle;
    private javax.swing.JTextField txtFldDuration;
    private javax.swing.JTextField txtFldFillName;
    private javax.swing.JTextField txtFldReleasedYear;
    private javax.swing.JTextField txtFldSearch;
    private javax.swing.JTextField txtFldTrailer;
    private javax.swing.JTextField txtFldUpdateUserName;
    // End of variables declaration//GEN-END:variables
}
