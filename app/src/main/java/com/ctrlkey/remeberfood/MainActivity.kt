package com.ctrlkey.remeberfood


import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var listOfImage:List<ImageView>
    private var image:MutableList<Int> = listOf(R.drawable.hamburger, R.drawable.noodles,R.drawable.pizza,
        R.drawable.lasagna,R.drawable.fried_chicken,R.drawable.sandwich,R.drawable.hamburger, R.drawable.noodles,R.drawable.pizza,
        R.drawable.lasagna,R.drawable.fried_chicken,R.drawable.sandwich).toMutableList()

    // register variable is to create register for correctly guessed images by user
    private val register= mutableSetOf<ImageView>()

    private lateinit var button:Button

    private lateinit var dummyImg:ImageView
    companion object{
        var src:Int = 0
        var count:Int = 0
        lateinit var tempImg:ImageView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shuffle(image)
        button = findViewById(R.id.play_again)
        dummyImg = ImageView(applicationContext)
        tempImg = dummyImg
        listOfImage = listOf(findViewById(R.id.imageView1),
            findViewById(R.id.imageView2),
            findViewById(R.id.imageView3),
            findViewById(R.id.imageView4),
            findViewById(R.id.imageView5),
            findViewById(R.id.imageView6),
            findViewById(R.id.imageView7),
            findViewById(R.id.imageView8),
            findViewById(R.id.imageView9),
            findViewById(R.id.imageView10),
            findViewById(R.id.imageView11),
            findViewById(R.id.imageView12))

        for(img in listOfImage){
            img.setOnClickListener {
                if(tempImg!=img && !register.contains(img))
                    playGame(img)
            }
        }

        button.setOnClickListener { button.setOnClickListener {
           //RESET GAME
            tempImg = dummyImg
            for(i in listOfImage)
                i.setImageResource(R.drawable.brick_wall)
            register.clear()
            count=0
            shuffle(image)
            button.visibility=Button.GONE
        } }

    }

    private fun playGame(img: ImageView) {

        when(img.id){
            R.id.imageView1 -> checkIfSame(image[0],img)
            R.id.imageView2 -> checkIfSame(image[1],img)
            R.id.imageView3 -> checkIfSame(image[2],img)
            R.id.imageView4 -> checkIfSame(image[3],img)
            R.id.imageView5 -> checkIfSame(image[4],img)
            R.id.imageView6 -> checkIfSame(image[5],img)
            R.id.imageView7 -> checkIfSame(image[6],img)
            R.id.imageView8 -> checkIfSame(image[7],img)
            R.id.imageView9 -> checkIfSame(image[8],img)
            R.id.imageView10 -> checkIfSame(image[9],img)
            R.id.imageView11 -> checkIfSame(image[10],img)
            R.id.imageView12 -> checkIfSame(image[11],img)
        }

    }

    private fun checkIfSame(image:Int,img:ImageView) {
        if(src==0) {
            src = image
            img.setImageResource(image)
            tempImg=img
        }
        else if(src==image){
            count++
            src=0
            img.setImageResource(image)
            register.addAll(setOf(tempImg,img))
        }
        else{
            src=0
            tempImg.setImageResource(R.drawable.brick_wall)
            tempImg = dummyImg
        }

        //to check whether the game is completed(i.e. won)
        if(count==6){
            createDialog()
        }

    }

    private fun createDialog(){
        AlertDialog.Builder(this).setTitle(R.string.congrats_msg)
            .setMessage(R.string.to_continue).setPositiveButton("ok", DialogInterface.OnClickListener { dialog, id ->
                //Reset the total game
                tempImg = dummyImg
                for(i in listOfImage)
                    i.setImageResource(R.drawable.brick_wall)
                register.clear()
                count=0
                shuffle(image)

            }).setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, id ->
                //make the button visible
                button.visibility=Button.VISIBLE
            }).create().show()
    }

    private fun <T> shuffle(list: MutableList<T>)
    {
        // start from end of the list
        for (i in list.size - 1 downTo 1)
        {
            // get a random index j such that 0 <= j <= i
            val j = Random.nextInt(i + 1)

            // swap element at i'th position in the list with element at j'th position
            val temp = list[i]
            list[i] = list[j]
            list[j] = temp
        }
    }




}