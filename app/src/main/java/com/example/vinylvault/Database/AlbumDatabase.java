package com.example.vinylvault.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.Pojo.AlbumArtist;
import com.example.vinylvault.Pojo.AlbumTrack;
import com.example.vinylvault.Pojo.Artist;
import com.example.vinylvault.Pojo.Genre;
import com.example.vinylvault.Pojo.Track;

import java.util.ArrayList;

public class AlbumDatabase extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "vinylvault";

    public static final String TABLE_ALBUM = "album";
    public static final String COLUMN_ALBUM_ID = "album_id";
    public static final String COLUMN_ALBUM_NAME = "album_name";
    public static final String COLUMN_ARTWORK = "album_artwork";
    public static final String COLUMN_RATING = "star_rating";
    public static final String COLUMN_ALBUM_GENRE = "ablum_genre";
    public static final String COLUMN_ALBUM_STATUS = "album_status";


    public static final String TABLE_ARTIST = "artist";
    public static final String COLUMN_ARTIST_ID = "artist_id";
    public static final String COLUMN_ARTIST_NAME = "artist_name";

    public static final String TABLE_GENRE = "genre";
    public static final String COLUMN_GENRE_ID = "genre_id";
    public static final String COLUMN_GENRE_NAME = "genre_name";

    public static final String TABLE_TRACK = "track";
    public static final String COLUMN_TRACK_ID = "track_id";
    public static final String COLUMN_TRACK_NAME = "track_name";

    public static final String TABLE_ALBUM_ARTIST = "album_artist";
    public static final String COLUMN_AA_ID = "album_artist_id";
    public static final String COLUMN_AA_ALBUM = "aa_album";
    public static final String COLUMN_AA_ARTIST = "aa_artist";

    public static final String TABLE_ALBUM_TRACK = "album_track";
    public static final String COLUMN_AT_ID = "album_track_id";
    public static final String COLUMN_AT_ALBUM = "at_album";
    public static final String COLUMN_AT_TRACK = "at_track";

    public static final String CREATE_ALBUM_TABLE = "CREATE TABLE " +
                                                    TABLE_ALBUM + "(" + COLUMN_ALBUM_ID + " INTEGER PRIMARY KEY," +
                                                    COLUMN_ALBUM_NAME + " TEXT," +
                                                    COLUMN_ALBUM_GENRE + " INT," +
                                                    COLUMN_ARTWORK + " TEXT," +
                                                    COLUMN_RATING + " INT," + COLUMN_ALBUM_STATUS + " INT, " +
                                                    " FOREIGN KEY (" + COLUMN_ALBUM_GENRE + ")" +
                                                    " REFERENCES " + TABLE_GENRE + "(" + COLUMN_GENRE_ID + "))";

    public static final String CREATE_ARTIST_TABLE = "CREATE TABLE " +
                                                    TABLE_ARTIST + "(" + COLUMN_ARTIST_ID + " INTEGER PRIMARY KEY," +
                                                    COLUMN_ARTIST_NAME+ " TEXT)";

    public static final String CREATE_GENRE_TABLE = "CREATE TABLE " +
                                                    TABLE_GENRE + "(" + COLUMN_GENRE_ID + " INTEGER PRIMARY KEY," +
                                                    COLUMN_GENRE_NAME + " TEXT)";

    public static final String CREATE_TRACK_TABLE = "CREATE TABLE " +
                                                    TABLE_TRACK + "(" + COLUMN_TRACK_ID + " INTEGER PRIMARY KEY," +
                                                    COLUMN_TRACK_NAME + " TEXT)";

    public static final String CREATE_AA_TABLE = "CREATE TABLE " +
                                                 TABLE_ALBUM_ARTIST + "(" +
                                                 COLUMN_AA_ID + " INTEGER PRIMARY KEY," +
                                                 COLUMN_AA_ALBUM + " INT, " +
                                                 COLUMN_AA_ARTIST + " INT, " +
                                                 " FOREIGN KEY (" + COLUMN_AA_ALBUM + ") " +
                                                 " REFERENCES " + TABLE_ALBUM + "(" + COLUMN_ALBUM_ID + ")," +
                                                 " FOREIGN KEY (" + COLUMN_AA_ARTIST + ") " +
                                                 " REFERENCES " + TABLE_ARTIST+ "(" + COLUMN_ARTIST_ID + "))";

    public static final String CREATE_AT_TABLE = "CREATE TABLE " +
                                                TABLE_ALBUM_TRACK + "(" +
                                                COLUMN_AT_ID + " INTEGER PRIMARY KEY," +
                                                COLUMN_AT_ALBUM + " INT," +
                                                COLUMN_AT_TRACK + " INT," +
                                                " FOREIGN KEY (" + COLUMN_AT_ALBUM + ") " +
                                                " REFERENCES " + TABLE_ALBUM + "(" + COLUMN_ALBUM_ID + ")," +
                                                " FOREIGN KEY (" + COLUMN_AT_TRACK + ") " +
                                                " REFERENCES " + TABLE_TRACK + "(" + COLUMN_TRACK_ID + "))";


    public AlbumDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ALBUM_TABLE);
        db.execSQL(CREATE_ARTIST_TABLE);
        db.execSQL(CREATE_GENRE_TABLE);
        db.execSQL(CREATE_TRACK_TABLE);
        db.execSQL(CREATE_AA_TABLE);
        db.execSQL(CREATE_AT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Album getAlbum(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Album album = null;
        Cursor cursor = db.query(TABLE_ALBUM, new String[]{
                                COLUMN_ALBUM_ID, COLUMN_ALBUM_NAME, COLUMN_ALBUM_GENRE, COLUMN_ARTWORK, COLUMN_RATING, COLUMN_ALBUM_STATUS}, COLUMN_ALBUM_ID + "= ?",
                                new String[]{String.valueOf(id)}, null, null, null);
        if(cursor.moveToFirst()){
            album = new Album(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getInt(6));
        }
        db.close();
        return album;
    }

    public Artist getArtist(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Artist artist = null;
        Cursor cursor = db.query(TABLE_ARTIST, new String[]{
                                COLUMN_ARTIST_ID, COLUMN_ARTIST_NAME}, COLUMN_ARTIST_ID + "= ?",
                           new String[]{String.valueOf(id)}, null, null, null);
        if(cursor.moveToFirst()){
            artist = new Artist(
                    cursor.getInt(0),
                    cursor.getString(1));
        }
        db.close();
        return artist;
    }

    public Track getTrack(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Track track = null;
        Cursor cursor = db.query(TABLE_TRACK, new String[]{
                        COLUMN_TRACK_ID, COLUMN_TRACK_NAME}, COLUMN_TRACK_ID + "= ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if(cursor.moveToFirst()){
            track = new Track(
                    cursor.getInt(0),
                    cursor.getString(1));
        }
        db.close();
        return track;
    }

    public Genre getGenre(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Genre genre = null;
        Cursor cursor = db.query(TABLE_GENRE, new String[]{
                        COLUMN_GENRE_ID, COLUMN_GENRE_NAME}, COLUMN_GENRE_ID + "= ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if(cursor.moveToFirst()){
           genre = new Genre(
                    cursor.getInt(0),
                    cursor.getString(1));
        }
        db.close();
        return genre;
    }

    public AlbumArtist getAlbumArtist(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        AlbumArtist albumArtist = null;
        Cursor cursor = db.query(TABLE_ALBUM_ARTIST, new String[]{
                        COLUMN_AA_ID, COLUMN_AA_ALBUM, COLUMN_AA_ARTIST}, COLUMN_AA_ID + "= ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if(cursor.moveToFirst()){
            albumArtist = new AlbumArtist(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2));
        }
        db.close();
        return albumArtist;
    }
    public AlbumTrack getAlbumTrack(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        AlbumTrack albumTrack = null;
        Cursor cursor = db.query(TABLE_ALBUM_TRACK, new String[]{
                        COLUMN_AT_ID, COLUMN_AT_ALBUM, COLUMN_AT_TRACK}, COLUMN_AT_ID+ "= ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if(cursor.moveToFirst()){
            albumTrack = new AlbumTrack(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2));
        }
        db.close();
        return albumTrack;
    }

    public ArrayList<Album> getAllAlbums(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Album> albums = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ALBUM,null);
        while(cursor.moveToNext()){
            albums.add(new Album( cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getInt(6)));
        }
        db.close();
        return albums;
    }

    public ArrayList<Artist> getAllArtists(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Artist> artists = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ARTIST,null);
        while(cursor.moveToNext()){
            artists.add(new Artist( cursor.getInt(0),
                    cursor.getString(1)));
        }
        db.close();
        return artists;
    }

    public ArrayList<Genre> getAllGenres(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Genre> genres = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_GENRE,null);
        while(cursor.moveToNext()){
            genres.add(new Genre( cursor.getInt(0),
                    cursor.getString(1)));
        }
        db.close();
        return genres;
    }

    //Update
    public int updateAlbum(Album album){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ALBUM_NAME, album.getName());
        values.put(COLUMN_ALBUM_GENRE, album.getGenre());
        values.put(COLUMN_ARTWORK, album.getArtwork());

        return db.update(TABLE_ALBUM, values, COLUMN_ALBUM_ID + "=?",
                new String[]{String.valueOf(album.getId())});
    }

    public int updateArtist(Artist artist){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ARTIST_NAME, artist.getName());

        return db.update(TABLE_ARTIST, values, COLUMN_ARTIST_ID + "=?",
                new String[]{String.valueOf(artist.getId())});
    }

    public int updateGere(Genre genre){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_GENRE_NAME, genre.getName());

        return db.update(TABLE_GENRE, values, COLUMN_GENRE_ID+ "=?",
                new String[]{String.valueOf(genre.getId())});
    }

    //Delete
    public void deleteAlbum(int album){
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_ALBUM, COLUMN_ALBUM_ID + "=?", new String[]{String.valueOf(album)});
    db.close();
    }

    public void deleteArtist(int artist){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ARTIST, COLUMN_ARTIST_ID + "=?", new String[]{String.valueOf(artist)});
        db.close();
    }
    public void deleteTrack(int track){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRACK, COLUMN_TRACK_ID + "=?", new String[]{String.valueOf(track)});
        db.close();
    }
    public void deleteGenre(int genre){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GENRE, COLUMN_GENRE_ID + "=?", new String[]{String.valueOf(genre)});
        db.close();
    }

    public ArrayList<Album> getTopAlbums(int top){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Album> topAlbums = new ArrayList<>();

        Cursor cursor = db.query(TABLE_ALBUM, new String[]{
                        COLUMN_ALBUM_ID, COLUMN_ALBUM_NAME, COLUMN_ALBUM_GENRE, COLUMN_ARTWORK, COLUMN_RATING}, null, null, null, null, COLUMN_RATING + " DESC", String.valueOf(top));
       while(cursor.moveToNext()) {
           topAlbums.add(new Album(
                   cursor.getInt(0),
                   cursor.getString(1),
                   cursor.getString(2),
                   cursor.getString(3),
                   cursor.getString(4),
                   cursor.getInt(5),
                   cursor.getInt(6)));
       }

       cursor.close();
       db.close();

       return topAlbums;

       //ArrayList<Album> topAlbums = albumDatabase.getTopAlbums(10); call this when we want to retreive topAlbums
    }

    /**
     * This method creates an arraylist of Top genres.
     * Top genres are based on the number of albums each genres has that the user has listened to
     * @param top
     * @return topGenres
     */
    public ArrayList<Genre> getTopGenres(int top){
        //Get the genres
       ArrayList<Genre> genres = getAllGenres();
       //create arraylist to hold top genres
       ArrayList<Genre> topGenres = new ArrayList<>();
       int maxCount = 0; //Track top genres ans associated album count

       for(Genre genre : genres){
           ArrayList<Album> albums = topGenres(genre.getId());
           int albumCount = albums.size();

           // If the current genre has more albums than the current maximum,
           // clear the existing top genres and add the current genre as the new top genre
           if(albumCount > maxCount){
               maxCount = albumCount;
               topGenres.clear();
               topGenres.add(genre);
           }
           // If the current genre has the same number of albums as the current maximum,
           // add it to the list of top genres
           else if(albumCount == maxCount){
               topGenres.add(genre);
           }

           // Limit the number of top genres
           if(topGenres.size() >= top){
               break;
           }
       }

        return topGenres;

       //ArrayList<Genre> topGenres = albumDatabase.getTopGenres(5); use this to retrieve top genres

    }

    /**
     * This method is used to help gather topGenres
     * It grabs the albums associated with each genre
     * @param genreId
     * @return albums
     */
    private ArrayList<Album> topGenres(int genreId){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Album> albums = new ArrayList<>();


        Cursor cursor = db.query(TABLE_ALBUM, new String[]{
                        COLUMN_ALBUM_ID, COLUMN_ALBUM_NAME, COLUMN_ALBUM_GENRE, COLUMN_ARTWORK, COLUMN_RATING},
                COLUMN_ALBUM_GENRE + "=?",
                new String[]{String.valueOf(genreId)},
                null, null, null);


        while (cursor.moveToNext()) {
            albums.add(new Album(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getInt(6)));
        }

        db.close();

        return albums;
    }

}
