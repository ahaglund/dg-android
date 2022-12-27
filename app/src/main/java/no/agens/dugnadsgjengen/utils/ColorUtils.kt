package no.agens.dugnadsgjengen.utils

import androidx.compose.ui.graphics.Color


fun Color.fromHex(color: String) = Color(android.graphics.Color.parseColor("#$color"))