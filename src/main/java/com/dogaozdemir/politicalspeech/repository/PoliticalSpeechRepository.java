package com.dogaozdemir.politicalspeech.repository;

import com.dogaozdemir.politicalspeech.model.PoliticalSpeech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PoliticalSpeechRepository extends JpaRepository<PoliticalSpeech,String> {

    @Query("SELECT ps FROM PoliticalSpeech ps ORDER BY ps.words ASC LIMIT 1")
    PoliticalSpeech findMinWordsRow();
    @Query(nativeQuery = true,
    value = """
               SELECT
                Speaker,
                COUNT(*) AS SpeechCount
            FROM
                POLITICAL_SPEECH
            WHERE
                YEAR(Date) = :url1
            GROUP BY
                Speaker
            ORDER BY
                SpeechCount DESC
            LIMIT 1;
            """)
    Optional<EvaulationRepository> mostSpeechByYear(Integer url1);

    @Query(nativeQuery = true,
            value = """
                    SELECT
                        Speaker,
                        COUNT(*) AS SpeechCount
                    FROM
                        POLITICAL_SPEECH
                    WHERE
                        TOPIC = :url2
                    GROUP BY
                        Speaker
                    ORDER BY
                        SpeechCount DESC
                    LIMIT 1;
                    """)
    Optional<EvaulationRepository> mostSpeechByTopic(String url2);
}
