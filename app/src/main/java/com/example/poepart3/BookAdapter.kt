package com.example.poepart3

/*
  reference list
  CodingSTUFF - YouTube - 29 Mar 2022 - https://www.youtube.com/watch?v=WqrpcWXBz14
*/
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors

class BookAdapter : ListAdapter<Book, BookAdapter.BookAdapter>(BookViewHolder())
{
    var onItemClickB : ((Book) -> Unit)? = null
    var UpdateBokPage: ((Book) -> Unit)? = null
    class BookAdapter(view : View): RecyclerView.ViewHolder(view)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter {
        val inflater = LayoutInflater.from(parent.context)
        return com.example.poepart3.BookAdapter.BookAdapter(inflater.inflate( //your project name
            R.layout.booklayout,parent,false))
    }

    override fun onBindViewHolder(holderB: BookAdapter, position: Int) {
        val book = getItem(position)
        holderB.itemView.findViewById<TextView>(R.id.txtBookName).text = book.Title
        holderB.itemView.findViewById<TextView>(R.id.txtSeries).text = book.Series
        holderB.itemView.findViewById<ImageView>(R.id.imgBook).setBackgroundResource(book.BookImage)

        //button click listener
        holderB.itemView.findViewById<Button>(R.id.btnBookAction).setOnClickListener{
            onItemClickB?.invoke(book) //(CodingSTUFF, 2022)
        }



    }
}
class BookViewHolder : DiffUtil.ItemCallback<Book>()
{
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.Title == newItem.Title
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return areItemsTheSame(oldItem,newItem)
    }


}