# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in the SDK tools.

# Keep Miuix classes
-keep class top.yukonga.miuix.kmp.** { *; }

# Keep Compose
-dontwarn androidx.compose.**