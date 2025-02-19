package devandroid.cesar.portafacil.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [Encomenda::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun encomendaDao(): EncomendaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "encomenda_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}