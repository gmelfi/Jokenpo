package com.jkp.melfi.jokenpo

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random
import android.media.MediaPlayer

class MainActivity : AppCompatActivity() {

    private var numeroAleatorio: Random? = null

    private val PEDRA = 1
    private val PAPEL = 2
    private val TESOURA = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numeroAleatorio = Random()

        btPedra.setOnClickListener {
            ivJogadaPlayer!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.m_pedra))
            realizarJogada(PEDRA)
        }
        btPapel.setOnClickListener {
            ivJogadaPlayer!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.m_pedra))
            realizarJogada(PAPEL)
        }
        btTesoura.setOnClickListener {
            ivJogadaPlayer!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.m_pedra))
            realizarJogada(TESOURA)
        }
    }

    private fun realizarJogada(jogadaPlayer: Int){
        //val player = MediaPlayer.create(this.R.raw.jokenpo)
        //player.start()

        when(jogadaPlayer){
            PAPEL -> ivJogadaPlayer!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.m_papel))
            PEDRA -> ivJogadaPlayer!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.m_pedra))
            TESOURA -> ivJogadaPlayer!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.m_tesoura))
        }

        val jogadaPC = numeroAleatorio!!.nextInt(3)+1

        when(jogadaPC){
            PEDRA ->{
                ivJogadaPC!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.m_pedra))
                when(jogadaPlayer){
                    PAPEL -> venceu()
                    PEDRA -> empatou()
                    TESOURA -> perdeu()
                }
            }

            PAPEL ->{
                ivJogadaPC!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.m_papel))
                when(jogadaPlayer){
                    PAPEL -> empatou()
                    PEDRA -> perdeu()
                    TESOURA -> venceu()
                }
            }

            TESOURA ->{
                ivJogadaPC!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.m_tesoura))
                when(jogadaPlayer){
                    PAPEL -> perdeu()
                    PEDRA -> venceu()
                    TESOURA -> empatou()
                }
            }
        }

    }

    private fun venceu(){
        tvResultado!!.text = getString(R.string.venceu)
        tvResultado!!.setTextColor(ContextCompat.getColor(this, R.color.vitoria))

    }

    private fun perdeu(){
        tvResultado!!.text = getString(R.string.perdeu)
        tvResultado!!.setTextColor(ContextCompat.getColor(this, R.color.derrota))
    }

    private fun empatou(){
        tvResultado!!.text = getString(R.string.empatou)
        tvResultado!!.setTextColor(ContextCompat.getColor(this, R.color.empate))
    }
}