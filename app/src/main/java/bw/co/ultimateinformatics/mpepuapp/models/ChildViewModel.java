package bw.co.ultimateinformatics.mpepuapp.models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import bw.co.ultimateinformatics.mpepuapp.helpers.ChildRepository;

import java.util.List;

public class ChildViewModel extends AndroidViewModel {
    private ChildRepository mRepository;
    private LiveData<List<ChildModel>> mAllChildren;

    public ChildViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ChildRepository(application);
        mAllChildren = mRepository.getAllChildren();
    }


    public LiveData<List<ChildModel>> getAllChildren(){
        return mAllChildren;
    }

    public void insert(ChildModel child){mRepository.insert(child);}
}
