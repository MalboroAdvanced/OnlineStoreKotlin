package com.example.onlinestorekotlin

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.e_product_row.view.*

class EProductAdapter(var context: Context, var arrayList: ArrayList<EProduct>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val productView = LayoutInflater.from(context).inflate(R.layout.e_product_row,parent,false)
        return ProductViewHolder(productView)

    }

    override fun getItemCount(): Int {

        return arrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as ProductViewHolder).initializeRowUIComponents(arrayList.get(position).id,arrayList.get(position).name,arrayList.get(position).price,arrayList.get(position).pictureName)
    }

    inner class ProductViewHolder(pView:View) : RecyclerView.ViewHolder(pView){

        fun initializeRowUIComponents(id:Int,name:String,price:Int,picName:String){

            itemView.txtID.text = id.toString()
            itemView.txtName.text = name
            itemView.txtPrice.text = price.toString()

            var picUrl = "http://192.168.43.206/OnlineStoreApp/osimages/"
            picUrl = picUrl.replace(" ","%20")

            Picasso.get().load(picUrl + picName).into(itemView.imgProduct)

            itemView.imgAdd.setOnClickListener {

                Person.addToCartProductID = id
                var amountFragment = AmountFragment()
                var fragmentManager = (itemView.context as Activity).fragmentManager
                amountFragment.show(fragmentManager,"TAG")

            }

        }

    }

}