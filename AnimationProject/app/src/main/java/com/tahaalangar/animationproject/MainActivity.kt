package com.tahaalangar.animationproject

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tahaalangar.animationproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivityMainBinding
    private lateinit var blink: Animation
    private lateinit var bounce: Animation
    private lateinit var fadeIn: Animation
    private lateinit var fadeOut: Animation
    private lateinit var move: Animation
    private lateinit var rotate: Animation
    private lateinit var sequential: Animation
    private lateinit var slideDown: Animation
    private lateinit var slideUp: Animation
    private lateinit var together: Animation
    private lateinit var zoomIn: Animation
    private lateinit var zoomOut: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bounce= AnimationUtils.loadAnimation(this,R.anim.bounce)
        blink= AnimationUtils.loadAnimation(this,R.anim.blink)
        fadeIn= AnimationUtils.loadAnimation(this,R.anim.fade_in)
        fadeOut= AnimationUtils.loadAnimation(this,R.anim.fade_out)
        move= AnimationUtils.loadAnimation(this,R.anim.move)
        rotate= AnimationUtils.loadAnimation(this,R.anim.rotate)
        sequential= AnimationUtils.loadAnimation(this,R.anim.sequential)
        slideDown= AnimationUtils.loadAnimation(this,R.anim.slide_down)
        slideUp= AnimationUtils.loadAnimation(this,R.anim.slide_up)
        together= AnimationUtils.loadAnimation(this,R.anim.together)
        zoomIn= AnimationUtils.loadAnimation(this,R.anim.zoom_in)
        zoomOut= AnimationUtils.loadAnimation(this,R.anim.zoom_out)

        binding.bounceButton.setOnClickListener {
            binding.bounceTv.startAnimation(bounce)
        }
        binding.blinkButton.setOnClickListener {
            binding.blinkTv.startAnimation(blink)
        }
        binding.fadeInButton.setOnClickListener {
            binding.fadeInTV.startAnimation(fadeIn)
        }
        binding.fadeOutButton.setOnClickListener {
            binding.fadeOutTv.startAnimation(fadeOut)
        }
        binding.moveButton.setOnClickListener {
            binding.moveTv.startAnimation(move)
        }
        binding.rotateButton.setOnClickListener {
            binding.rotateTv.startAnimation(rotate)
        }
        binding.sequenceButton.setOnClickListener {
            binding.sequenceTv.startAnimation(sequential)
        }
        binding.slideDownButton.setOnClickListener {
            binding.slideDownTv.startAnimation(slideDown)
        }
        binding.slideUpButton.setOnClickListener {
            binding.slideUpTv.startAnimation(slideUp)
        }
        binding.togetherButton.setOnClickListener {
            binding.togetherTv.startAnimation(together)
        }
        binding.zoomInButton.setOnClickListener {
            binding.zoomInTv.startAnimation(zoomIn)
        }
        binding.zoomOutButton.setOnClickListener {
            binding.zoomOutTv.startAnimation(zoomOut)
        }


//        bounce=binding.bounceButton.animation
//        blink=binding.blinkButton.animation
//        fadeIn=binding.fadeInButton.animation
//        fadeOut=binding.fadeOutButton.animation
//        move=binding.moveButton.animation
//        rotate=binding.rotateButton.animation
//        sequential=binding.sequenceButton.animation
//        slideDown=binding.slideDownButton.animation
//        slideUp=binding.slideUpButton.animation
//        together=binding.togetherButton.animation
//        zoomIn=binding.zoomInButton.animation
//        zoomOut=binding.zoomOutButton.animation
//


    }
}