package com.example.responsi1mobileh1d023109

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.responsi1mobileh1d023109.ui.ClubHistoryActivity
import com.example.responsi1mobileh1d023109.ui.CoachActivity
import com.example.responsi1mobileh1d023109.ui.SquadActivity
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialCardView>(R.id.card_club_history).setOnClickListener {
            startActivity(Intent(this, ClubHistoryActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.card_head_coach).setOnClickListener {
            startActivity(Intent(this, CoachActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.card_team_squad).setOnClickListener {
            startActivity(Intent(this, SquadActivity::class.java))
        }
    }
}