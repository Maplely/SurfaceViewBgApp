package top.com.surfaceviewbgapp

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.SurfaceHolder
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*

/**
 * Created by lihaitao on 2018/6/6.
 *
 */
class SecondActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_second)
        initViewAndData()
    }

    private fun initViewAndData() {
        surface2.holder.setKeepScreenOn(true)
        surface2.holder.addCallback(object: SurfaceHolder.Callback{
            override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {

            }

            override fun surfaceDestroyed(p0: SurfaceHolder?) {

            }

            override fun surfaceCreated(p0: SurfaceHolder?) {
                play()
            }
        })
        button2.setOnClickListener {
            Toast.makeText(SecondActivity@this,"第二个acitivyt",Toast.LENGTH_SHORT).show()
        }
    }

    private val mediaPlayer = MediaPlayer()

    private fun play() {
        val player= mediaPlayer
        player.reset()
        //通过assets目录获取
        val fd = assets.openFd("intro.mp4")
        player.setDataSource(fd.fileDescriptor,fd.startOffset,fd.length)
        player.isLooping=true
        player.setScreenOnWhilePlaying(true)
        player.setDisplay(surface2.holder)
        player.prepareAsync()
        player.setOnPreparedListener {
            player.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}