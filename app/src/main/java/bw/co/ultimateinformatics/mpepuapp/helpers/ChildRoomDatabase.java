package bw.co.ultimateinformatics.mpepuapp.helpers;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import bw.co.ultimateinformatics.mpepuapp.models.ChildModel;
import bw.co.ultimateinformatics.mpepuapp.models.ChildModelDAO;

@Database(entities = {ChildModel.class}, version = 1)
public abstract class ChildRoomDatabase extends RoomDatabase {
    public abstract ChildModelDAO childModelDAO();
    private static volatile ChildRoomDatabase INSTANCE;
    private static RoomDatabase.Callback sRoomCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDBAsync(INSTANCE).execute();
        }
    };

    static ChildRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ChildRoomDatabase.class){
                if(INSTANCE ==null){
                    //Create the Database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ChildRoomDatabase.class, "child_database")
                            .addCallback(sRoomCallback)
                            .build();

                }
            }

        }
        return INSTANCE;
    }


    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {

        private final ChildModelDAO mDao;
         PopulateDBAsync(ChildRoomDatabase db) {

             mDao = db.childModelDAO();

        }

        @Override
        protected Void doInBackground(Void... params) {

             mDao.DeleteAllChildren();
             ChildModel child = new ChildModel(406517922,"Kayne", "Jax");
             mDao.insertChildren(child);
            return null;
        }
    }
}
