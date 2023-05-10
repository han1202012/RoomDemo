package kim.hsl.roomdemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase: RoomDatabase() {
    /**
     * 获取 数据库访问 对象
     * 这是必须要实现的函数
     */
    abstract fun studentDao(): StudentDao

    companion object {
        lateinit var instance: StudentDatabase

        fun inst(context: Context): StudentDatabase {
            if (!::instance.isInitialized) {
                synchronized(StudentDatabase::class) {
                    // 创建数据库
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "student_database.db")
                        .allowMainThreadQueries() // Room 原则上不允许在主线程操作数据库
                                                  // 如果要在主线程操作数据库需要调用该函数
                        .build()
                }
            }
            return instance;
        }
    }
}