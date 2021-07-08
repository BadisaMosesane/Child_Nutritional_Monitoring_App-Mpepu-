package bw.co.ultimateinformatics.mpepuapp.helpers;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import bw.co.ultimateinformatics.mpepuapp.models.ChildModel;
import bw.co.ultimateinformatics.mpepuapp.models.ChildModelDAO;

import java.util.List;

public class ChildRepository {
    private ChildModelDAO childModelDAO;
    private LiveData<List<ChildModel>> allChildren;

    public ChildRepository(Application application){
        ChildRoomDatabase db = ChildRoomDatabase.getDatabase(application);
        childModelDAO = db.childModelDAO();
        allChildren = childModelDAO.getAllChildModels();
    }

    public LiveData<List<ChildModel>> getAllChildren(){
        return allChildren;
    }

    public void insert(ChildModel childModel){
        new insertAsyncTask(childModelDAO).execute(childModel);
    }

    private static class insertAsyncTask extends AsyncTask<ChildModel, Void, Void> {
        private ChildModelDAO mAsyncTaskDao;

        insertAsyncTask(ChildModelDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ChildModel... params) {
            mAsyncTaskDao.insertChildren(params[0]);
            return null;
        }
    }
}
