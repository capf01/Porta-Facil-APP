@Database(entities = [Encomenda::class], version = 1)
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
                    "porta_facil_db"
                ).fallbackToDestructiveMigration() // Evita crash ao mudar versão
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
