package io.github.aloussase.frustratedfunctordotdev;

import java.util.List;

public record EducationEntry(
        String institution,
        String date,
        String title,
        String description
) {
    public static List<EducationEntry> entries() {
        return List.of(
                new EducationEntry(
                        "Escuela Superior Politécnica del Litoral",
                        "2019 - Abandoned",
                        "Bsc. Computer Science",
                        "I abandoned my degree just before graduating in 2025 for multiple reasons. I have mixed feeelings about formal education, stemming from my experience with it throughout my life. I might or might not finish it later."
                )
        );
    }
}
