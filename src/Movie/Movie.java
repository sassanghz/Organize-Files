package Movie;

import java.io.Serializable;

public class Movie implements Serializable {

    protected int year;
    protected String title;
    protected int duration;
    protected String genres;
    protected String rating;
    protected double score;
    protected String director;
    protected String actor1;
    protected String actor2;
    protected String actor3;

    public Movie() {
        this.year = 0;
        this.title = null;
        this.duration = 0;
        this.genres = null;
        this.rating = null;
        this.score = 0.0;
        this.director = null;
        this.actor1 = null;
        this.actor2 = null;
        this.actor3 = null;
    }
    

    public Movie(int year, String title, int duration, String genres, String rating, double score, String director,
            String actor1, String actor2, String actor3) {
        this.year = year;
        this.title = title;
        this.duration = duration;
        this.genres = genres;
        this.rating = rating;
        this.score = score;
        this.director = director;
        this.actor1 = actor1;
        this.actor2 = actor2;
        this.actor3 = actor3;
    }

    
    /** 
     * @return int
     */
    public int getYear() {
        return year;
    }

    
    /** 
     * @return String
     */
    public String getTitle() {
        return title;
    }

    
    /** 
     * @return int
     */
    public int getDuration() {
        return duration;
    }

    
    /** 
     * @return String
     */
    public String getGenre() {
        return genres;
    }

    public String getRating() {
        return rating;
    }

    public double getScore() {
        return score;
    }

    public String getDirector() {
        return director;
    }

    public String getActor1() {
        return actor1;
    }

    public String getActor2() {
        return actor2;
    }
    /** 
     * @param actor3
     */

    public String getActor3() {
        return actor3;
    }
    /** 
     * @param year
     */

    public void setYear(int year) {
        this.year = year;
    }
    /** 
     * @param title
     */

    public void setTitle(String title) {
        this.title = title;
    }
    /** 
     * @param duration
     */

    public void setDuration(int duration) {
        this.duration = duration;
    }
    /** 
     * @param genres
     */

    public void setGenres(String genres) {
        this.genres = genres;
    }
    /** 
     * @param rating
     */

    public void setRating(String rating) {
        this.rating = rating;
    }

    
    /** 
     * @param score
     */
    public void setScore(double score) {
        this.score = score;
    }
     /** 
     * @param director
     */

    public void setDirector(String director) {
        this.director = director;
    }

    
    /** 
     * @param actor1
     */
    public void setActor1(String actor1) {
        this.actor1 = actor1;
    }
     /** 
     * @param actor2
     */

    public void setActor2(String actor2) {
        this.actor2 = actor2;
    }
    
    /** 
     * @param actor3
    //  */

    public void setActor3(String actor3) {
        // set actor3
        this.actor3 = actor3;
    }

    
    /** 
     * @param obj
     * @return boolean
     */
    @Override
    
    public boolean equals(Object obj) {
        if (obj == null || obj == this || this.getClass() != obj.getClass()) {
            return false;
        } else {
            Movie movie = (Movie) obj;

            return (this.year == movie.year) &&
                    (this.duration == movie.duration) &&
                    (this.score == movie.score) &&
                    (this.genres.equals(movie.genres)) &&
                    (this.rating.equals(movie.rating)) &&
                    (this.director.equals(movie.director)) &&
                    (this.title.equals(movie.title)) &&
                    (this.actor1.equals(movie.actor1)) &&
                    (this.actor2.equals(movie.actor2)) &&
                    (this.actor3.equals(movie.actor3));
        }
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Movie{" +
                "year = " + year +
                ", title = '" + title + '\'' +
                ", duration = " + duration +
                ", genres = '" + genres + '\'' +
                ", rating = '" + rating + '\'' +
                ", score = " + score +
                ", director = '" + director + '\'' +
                ", actor1 = '" + actor1 + '\'' +
                ", actor2 = '" + actor2 + '\'' +
                ", actor3 = '" + actor3 + '\'' +
                '}';
    }
}