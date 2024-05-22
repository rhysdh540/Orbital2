# https://github.com/romainguy/kotlin-explorer/blob/main/compose-desktop.pro
-dontoptimize

-dontwarn androidx.compose.desktop.DesktopTheme*
-dontwarn kotlinx.datetime.**

-keep class dev.romainguy.kotlin.explorer.code.*TokenMarker { *; }
-dontnote dev.romainguy.kotlin.explorer.code.*TokenMarker

-keep class org.fife.** { *; }
-dontnote org.fife.**

-keep class sun.misc.Unsafe { *; }
-dontnote sun.misc.Unsafe

-keep class com.jetbrains.JBR* { *; }
-dontnote com.jetbrains.JBR*

-keep class com.sun.jna** { *; }
-dontnote com.sun.jna**

-keep class androidx.compose.ui.input.key.KeyEvent_desktopKt { *; }
-dontnote androidx.compose.ui.input.key.KeyEvent_desktopKt

-keep class androidx.compose.ui.input.key.KeyEvent_skikoKt { *; }
-dontnote androidx.compose.ui.input.key.KeyEvent_skikoKt
-dontwarn androidx.compose.ui.input.key.KeyEvent_skikoKt