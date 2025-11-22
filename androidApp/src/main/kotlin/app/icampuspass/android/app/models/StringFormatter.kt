package app.icampuspass.android.app.models

import java.util.Locale

object StringFormatter {
    fun formatCourseNameZhHant(
        name: String,
        note: String
    ): String {
        val courseNameZhHant = name
            .removeSurrounding(delimiter = "\"").trim()

        val courseNoteZhHant = note
            .removeSurrounding(delimiter = "\"").trim()

        return courseNameZhHant
            .let {
                when {
                    courseNoteZhHant.isNotBlank() -> it
                        .replace(oldValue = "(${courseNoteZhHant})", newValue = "")
                        .replace(oldValue = "（${courseNoteZhHant}）", newValue = "")
                    else -> it
                }
            }
            .replace(oldValue = "(", newValue = "（")
            .replace(oldValue = ")", newValue = "）")
    }

    fun formatCourseNameEn(name: String): String {
        return name
            .removeSurrounding(delimiter = "\"").trim()
            .replace(oldValue = "MILITARY TRAINING-", newValue = "MILITARY TRAINING - ")
            .replace(oldValue = "PHYSICAL EDUCATION-", newValue = "PHYSICAL EDUCATION - ")
            .replace(oldValue = "(", newValue = " (")
            .replace(oldValue = "  ", newValue = " ")
            .lowercase()
            .split(" ")
            .joinToString(separator = " ") { word ->
                when {
                    word == "of" -> word
                    word.matches(Regex("\\([ivx]+\\)")) -> word.uppercase()
                    else -> word.replaceFirstChar {
                        it.titlecase(Locale.forLanguageTag("en-US"))
                    }
                }
            }
    }

    fun formatInstructorNameEn(name: String): String {
        val instructorNameEn = name
            .removeSurrounding(delimiter = "\"").trim()
            .lowercase()
            .replace(oldValue = ",", newValue = "")
            .split(" ")
            .joinToString(separator = " ") { word ->
                word.replaceFirstChar {
                    it.titlecase(Locale.forLanguageTag("en-US"))
                }
            }

        return when (instructorNameEn) {
            "Ming-hung Chang" -> "Chang Ming-hung"
            "Chung-de Chen" -> "Chen Chung-de"
            "Chen Hsin Liang" -> "Chen Hsin-liang"
            "Po-hsiang Chen" -> "Chen Po-hsiang"
            "Yu-jen Chi" -> "Chi Yu-jen"
            "Chiu Sung Lan" -> "Chiu Sung-lan"
            "Chien-hsiang Chou" -> "Chou Chien-hsiang"
            "Wei-tsong Lee" -> "Lee Wei-tsong"
            "Tai Chia Jwu" -> "Tai Chia-jwu"
            "Hsien-wei Tseng" -> "Tseng Hsien-wei"
            "Wei-chih Tseng" -> "Tseng Wei-chih"
            "Yu-yin Tu" -> "Tu Yu-yin"
            "Shu-chun Yang" -> "Yang Shu-chun"
            "Chien-ho Yen" -> "Yen Chien-ho"
            else -> instructorNameEn
        }
    }

    fun formatCourseNoteZhHant(note: String): String {
        val courseNoteZhHant = note
            .removeSurrounding(delimiter = "\"").trim()

        if (courseNoteZhHant.isBlank()) {
            return courseNoteZhHant
        }

        return courseNoteZhHant
            .replace(oldValue = "(", newValue = "（")
            .replace(oldValue = ")", newValue = "）")
            .replace(oldValue = ", ", newValue = "，")
            .replace(oldValue = ",", newValue = "，")
    }
}
