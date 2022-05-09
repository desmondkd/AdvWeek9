package com.example.todoapp160419029.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todoapp160419029.model.TodoDatabase

val DB_NAME = "newtododb"

fun buildDb(context: Context):TodoDatabase {
    val db = Room.databaseBuilder(context, TodoDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
        .build()
    return db

}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 not null")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 not null")
    }
} // Karena dengan menggunakan integer dapat dilakukan set pada suatu variabel, sehingga yang ditampilkan hanya pada variabel yang di set dengan angka tertentu