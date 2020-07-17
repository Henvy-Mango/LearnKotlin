package com.example.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

/**
 * FileName: MyContentProvider
 * Author: Naomi
 * Date: 2020/7/4 0:02
 * Description:
 */
class MyContentProvider : ContentProvider() {

    private val bookDir = 0
    private val bookItem = 1
    private val catregoryDir = 2
    private val categoryItem = 3

    private val uriMatcher by lazy {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI("com.example.contentprovider", "book", bookDir)
        matcher.addURI("com.example.contentprovider", "book/#", bookItem)
        matcher.addURI("com.example.contentprovider", "catregory", catregoryDir)
        matcher.addURI("com.example.contentprovider", "catregory/#", categoryItem)
        matcher
    }
    private var dbHelper: MyDatabaseHelper? = null

    init {

    }

    override fun onCreate(): Boolean =
        context?.let { dbHelper = MyDatabaseHelper(it, "Book.db", 1); true } ?: false

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? = dbHelper?.let {
        val db = it.readableDatabase

        val cursor = when (uriMatcher.match(uri)) {
            bookDir -> db.query("Book", projection, selection, selectionArgs, null, null, sortOrder)
            bookItem -> {
                val tmp = uri.pathSegments
                val bookId = uri.pathSegments[1]
                db.query("Book", projection, "id=?", arrayOf(bookId), null, null, sortOrder)
            }
            catregoryDir -> db.query(
                "Category",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
            )
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.query("Category", projection, "id=?", arrayOf(categoryId), null, null, sortOrder)
            }
            else -> null
        }
        cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? = dbHelper?.let {
        val db = it.writableDatabase
        val uriReturn = when (uriMatcher.match(uri)) {
            bookDir, bookItem -> {
                val newBookId = db.insert("Book", null, values)
                Uri.parse("content://com.example.contentprovider/book/$newBookId")
            }
            catregoryDir, categoryItem -> {
                val newCategoryId = db.insert("Category", null, values)
                Uri.parse("content://com.example.contentprovider/category/$newCategoryId")
            }
            else -> null
        }
        uriReturn
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = dbHelper?.let {
        val db = it.writableDatabase
        val updateRows = when (uriMatcher.match(uri)) {
            bookDir -> {
                db.update("Book", values, selection, selectionArgs)
            }
            bookItem -> {
                val BookId = uri.pathSegments[1]
                db.update("Book", values, "id=?", arrayOf(BookId))
            }
            catregoryDir -> {
                db.update("Catregory", values, selection, selectionArgs)
            }
            categoryItem -> {
                val CategoryId = uri.pathSegments[1]
                db.update("Catregory", values, "id=?", arrayOf(CategoryId))
            }
            else -> 0
        }
        updateRows
    } ?: 0

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int =
        dbHelper?.let {
            val db = it.writableDatabase
            val deletedRows = when (uriMatcher.match(uri)) {
                bookDir -> db.delete("Book", selection, selectionArgs)
                bookItem -> {
                    val BookId = uri.pathSegments[1]
                    db.delete("Book", "id=?", arrayOf(BookId))
                }
                catregoryDir -> db.delete("Category", selection, selectionArgs)
                categoryItem -> {
                    val Category = uri.pathSegments[1]
                    db.delete("Category", "id=?", arrayOf(Category))
                }
                else -> -0
            }
            0
        } ?: 0

    override fun getType(uri: Uri): String? = when (uriMatcher.match(uri)) {
        bookDir -> "vnd.android.cursor.dir/com.example.contentprovider.book"
        bookItem -> "vnd.android.cursor.item/com.example.contentprovider.book"
        catregoryDir -> "vnd.android.cursor.dir/com.example.contentprovider.catregory"
        categoryItem -> "vnd.android.cursor.item/com.example.contentprovider.catregory"
        else -> null
    }
}