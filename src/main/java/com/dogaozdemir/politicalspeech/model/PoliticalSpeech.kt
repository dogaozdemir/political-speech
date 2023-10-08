package com.dogaozdemir.politicalspeech.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate


@Entity
data class PoliticalSpeech  (

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",
        val speaker: String?= null,
        val topic: String?= null,
        val date: LocalDate?=LocalDate.now(),
        val words:Int?=0


) {
        data class PoliticalSpeechBuilder(
                private var id: String? = null,
                private var speaker: String? = null,
                private var topic: String? = null,
                private var date: LocalDate? = LocalDate.now(),
                private var words: Int? = 0
        ) {
                fun id(id: String?) = apply { this.id = id }
                fun speaker(speaker: String?) = apply { this.speaker = speaker }
                fun topic(topic: String?) = apply { this.topic = topic }
                fun date(date: LocalDate?) = apply { this.date = date }
                fun words(words: Int?) = apply { this.words = words }

                fun build() = PoliticalSpeech(id, speaker, topic, date, words)
        }

        companion object {
                @JvmStatic
                fun builder() = PoliticalSpeechBuilder()



        }
 }
