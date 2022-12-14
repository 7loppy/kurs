package Movies;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Movies implements Serializable {
    private static final long serialVersionUID = 7032170397207419492L;
    public int MovieId;
    public int CountOfLove=6;
    public int CountOfVip=6;
    public int CountOfSingle=48;
    public String FilmName;
    public String ScreeningDateTime;
    public int HallNumer;
    public int takecount;
    public int PlaceNumer;
    public int[] countofpl=new int[60];
    public Movies() {}
    public Movies(int movieId, String filmName, String screeningDateTime, int hallNumer) {
        MovieId = movieId;
        FilmName = filmName;
        ScreeningDateTime = screeningDateTime;
        HallNumer = hallNumer;
    }
    public Movies(int movieId,int countOfLove,int countOfSingle,int countOfVip, String filmName, String screeningDateTime, int hallNumer) {
        CountOfLove=countOfLove;
        CountOfVip=countOfVip;
        CountOfSingle=countOfSingle;
        MovieId = movieId;
        FilmName = filmName;
        ScreeningDateTime = screeningDateTime;
        HallNumer = hallNumer;
    }
    public Movies(Movies movies)
    {
        this.MovieId=movies.MovieId;
        this.CountOfLove=movies.CountOfLove;
        this.CountOfVip=movies.CountOfVip;
        this.CountOfSingle=movies.CountOfSingle;
        this.FilmName=movies.FilmName;
        this.ScreeningDateTime=movies.ScreeningDateTime;
        this.HallNumer=movies.HallNumer;
        this.takecount=movies.takecount;
        this.PlaceNumer=movies.PlaceNumer;

    }

    public void setTakecount(int takecount) {
        this.takecount = takecount;
    }

    public int getTakecount() {
        return takecount;
    }

    public void setMovieId(int movieId) {
        MovieId = movieId;
    }

    public void setCountOfLove(int countOfLove) {
        CountOfLove = countOfLove;
    }

    public void setCountOfVip(int countOfVip) {
        CountOfVip = countOfVip;
    }

    public void setCountOfSingle(int countOfSingle) {
        CountOfSingle = countOfSingle;
    }

    public void setFilmName(String filmName) {
        FilmName = filmName;
    }

    public void setHallNumer(int hallNumer) {
        HallNumer = hallNumer;
    }

    public int getMovieId() {
        return MovieId;
    }

    public int getCountOfLove() {
        return CountOfLove;
    }

    public int getCountOfVip() {
        return CountOfVip;
    }

    public int getCountOfSingle() {
        return CountOfSingle;
    }

    public String getFilmName() {
        return FilmName;
    }

    public int getHallNumer() {
        return HallNumer;
    }

    public void setScreeningDateTime(String screeningDateTime) {
        ScreeningDateTime = screeningDateTime;
    }

    public int getPlaceNumer() {
        return PlaceNumer;
    }

    public int[] getCountofpl() {
        return countofpl;
    }

    public void setPlaceNumer(int placeNumer) {
        PlaceNumer = placeNumer;
    }

    public void setCountofpl(int[] countofpl) {
        this.countofpl = countofpl;
    }

    public String getScreeningDateTime() {
        return ScreeningDateTime;
    }
}

