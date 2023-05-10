package kim.hsl.roomdemo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * 数据库访问对象接口 / 使用 @Dao 注解修饰
 * 提供数据库的增删改查方法
 */
@Dao
interface StudentDao {
    /**
     * 向数据库表中插入元素
     */
    @Insert
    fun insert(student: Student)

    /**
     * 从数据库表中删除元素
     */
    @Delete
    fun delete(student: Student)

    /**
     * 修改数据库表元素
     */
    @Update
    fun update(student: Student)

    /**
     * 查询数据库表
     */
    @Query("select * from student")
    fun query(): List<Student>

    /**
     * 根据传入的 id 查询数据库表
     * 在注解中使用 :id 调用参数中的 id: Int
     */
    @Query("select * from student where id = :id")
    fun query(id: Int): List<Student>
}