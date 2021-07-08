package bw.co.ultimateinformatics.mpepuapp.models

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface ChildModelDAO {

    @get:Query("select * from  childModel")
    val allChildModels: LiveData<List<ChildModel>>

    @Query("select * from childModel where regID = :regID")
    fun loadChildByRegId(regID: Int): LiveData<ChildModel>

    @Delete
    fun deleteChild(childModel: ChildModel)

    @Query("delete from childModel where regID = :regID")
    fun deleteChildByID(regID: Int): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertChildren(vararg childModels: ChildModel)


    @Query("DELETE FROM childModel")
    fun DeleteAllChildren()

}
