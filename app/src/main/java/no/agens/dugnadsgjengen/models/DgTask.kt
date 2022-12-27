package no.agens.dugnadsgjengen.models

import no.agens.dugnadsgjengen.ui.theme.white

data class DgTask(
    val name: String,
    val colorHash: Int,
    val emoji: String?,
)

data class DgTaskDto(
    val name: String? = null,
    val colorHash: Int = white.hashCode(),
    val emoji: String? = null
) {
    fun mapper(): DgTask? {
        return if (name == null) {
            null
        } else {
            DgTask(
                name = name,
                colorHash = colorHash,
                emoji = emoji
            )
        }
    }
}