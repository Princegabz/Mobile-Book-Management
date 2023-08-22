package com.example.poepart3

/*
  reference list
  CodingSTUFF - YouTube - 29 Mar 2022 - https://www.youtube.com/watch?v=WqrpcWXBz14
*/
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors


class UserAdapter :ListAdapter<User,UserAdapter.UserAdapter>(UserViewHolder())
{
    var onItemClick : ((User) -> Unit)? = null //(CodingSTUFF, 2022)
    var UpdateCategoryPage: ((User) -> Unit)? = null //(CodingSTUFF, 2022)
    var DeleteCategoryPage: ((User) -> Unit)? = null //(CodingSTUFF, 2022)

    class UserAdapter(view : View):RecyclerView.ViewHolder(view)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter {
        val inflater = LayoutInflater.from(parent.context)
        return com.example.poepart3.UserAdapter.UserAdapter(inflater.inflate( //your project name
            R.layout.userlayout,parent,false))
    }

    override fun onBindViewHolder(holder: UserAdapter, position: Int) {
        val user = getItem(position)
        holder.itemView.findViewById<TextView>(R.id.txtNameUser).text = user.Name
        holder.itemView.findViewById<TextView>(R.id.txtPasswordUser).text = user.goalmsg
        holder.itemView.findViewById<ImageView>(R.id.imPP).setBackgroundResource(user.imageURL)
        holder.itemView.findViewById<ProgressBar>(R.id.catProgressBar).max = user.maxgoal


        ObjectAnimator.ofInt(holder.itemView.findViewById<ProgressBar>(R.id.catProgressBar), "progress", user.currentprogress).setDuration(0).start()
        //button click listener
        holder.itemView.findViewById<Button>(R.id.btnUserAction).setOnClickListener{
            Log.d("AddNewUser", "Button Pressed ${user.Name} was pressed" )
            onItemClick?.invoke(user) //(CodingSTUFF, 2022)
        }

        holder.itemView.findViewById<ImageButton>(R.id.EditCategory).setOnClickListener{
            UpdateCategoryPage?.invoke(user) //(CodingSTUFF, 2022)
        }
        holder.itemView.findViewById<ImageButton>(R.id.DeteleCategory).setOnClickListener{
            DeleteCategoryPage?.invoke(user) //(CodingSTUFF, 2022)
        }
    }
}
class UserViewHolder :DiffUtil.ItemCallback<User>()
{
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.Name == newItem.Name
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return areItemsTheSame(oldItem,newItem)
    }
}