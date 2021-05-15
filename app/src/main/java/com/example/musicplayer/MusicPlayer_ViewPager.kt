package com.example.musicplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import kotlinx.android.synthetic.main.activity_music_player__view_pager.*

class MusicPlayer_ViewPager : AppCompatActivity() {

    var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player__view_pager)

        //setting adapter in viewpager
        viewpager?.adapter = MyPagerAdapter()
        viewpager?.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (mp != null) {
                    mp!!.stop()
                    mp!!.release()
                    mp = null
                }
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

     inner class MyPagerAdapter : PagerAdapter() {

        private val images: IntArray = intArrayOf(R.drawable.a_img, R.drawable.b_img, R.drawable.c_img, R.drawable.d_img)
        private val alphabets: Array<String> = arrayOf("A for Apple", "B for Ball", "C for cat", "D for Dog")
        var songs: IntArray = intArrayOf(R.raw.wheels, R.raw.twinkle, R.raw.mary, R.raw.london)

        override fun getCount(): Int {
            return images.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view =
                (applicationContext.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.content,
                    container,
                    false
                )

            //setting text in textview
            (view.findViewById<View>(R.id.textview) as TextView).text = alphabets[position]

            //setting image in imageview
            val imageView = view.findViewById<ImageView>(R.id.imageview)
            imageView.scaleType = ImageView.ScaleType.FIT_CENTER
            imageView.setImageResource(images[position])

            //play and stop song on button click
            val btn_play = view.findViewById<Button>(R.id.btn_play)
            val btn_stop = view.findViewById<Button>(R.id.btn_stop)
            val song = songs[position]

            btn_play.setOnClickListener {
                try {
                    if (mp != null && mp!!.isPlaying) {
                        mp!!.stop()
                        mp!!.release()
                    }
                    mp = MediaPlayer.create(applicationContext, song)
                    mp!!.start()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            btn_stop.setOnClickListener {
                mp!!.stop()
                mp!!.release()
                mp = MediaPlayer.create(applicationContext, song)
            }

            //setting view in container i.e viewpager
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

    }
}
