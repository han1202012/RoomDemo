package kim.hsl.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kim.hsl.roomdemo.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(getLayoutInflater())
        setContentView(binding.root)

        // 获取 StudentDatabase
        var studentDatabase: StudentDatabase = StudentDatabase.inst(this)

        // 获取数据库访问对象
        var studentDao: StudentDao = studentDatabase.studentDao()

        thread(start = true) {
            // 插入数据
            var s1 = Student("Tom", 18)
            var s2 = Student("Jerry", 16)
            studentDao.insert(s1)
            studentDao.insert(s2)

            // 查询数据
            var students = studentDao.query()
            Log.i("MainActivity", "数据库查询结果 ( 插入后首次查询 ) : " + students)

            // 更新数据 , 将学生年龄都设置为 20
            for (i in 0.. students.size - 1) {
                students[i].age = 20
                studentDao.update(students[i])
            }
            students = studentDao.query()
            Log.i("MainActivity", "数据库查询结果 ( 修改后查询结果 ) : " + students)

            // 删除数据
            var s_delete = Student(1)   // 删除的元素只需要传入 id 即可
            studentDao.delete(s_delete)
            students = studentDao.query()
            Log.i("MainActivity", "数据库查询结果 ( 删除后查询结果 ) : " + students)
        }


    }
}