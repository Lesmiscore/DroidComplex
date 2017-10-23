-keepnames public !abstract class * extends android.app.Activity
-keepnames public !abstract class * extends android.app.Service
-keepnames public !abstract class * extends android.content.BroadcastReceiver
-dontwarn com.google.common.**
-dontwarn com.google.appengine.**
-dontwarn com.google.android.gms.**
-dontwarn com.google.api.client.**
-assumenosideeffects class android.util.Log {
    *;
}
-keepnames class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
-keepnames public !abstract class * extends android.view.View {
    public <init>(...);
}
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-dontwarn org.apache.**
-keep class uk.co.chrisjenx.calligraphy.** { *; }
-keep class android.support.** { *; }
-keep class android.support.v4.os.CancellationSignal { *; }

-keep class com.firebase.** { *; }
-keepnames class org.apache.** { *; }
-keepnames class com.fasterxml.jackson.** { *; }
-keepnames class javax.servlet.** { *; }
-keepnames class org.ietf.jgss.** { *; }
-dontwarn org.apache.**
-dontwarn org.w3c.dom.**
-dontwarn android.**
-dontwarn javax.lang.model.element.Modifier

-keepattributes InnerClasses,EnclosingMethod

-keepclassmembers public enum * { *; }

-repackageclasses droidcomplex

-allowaccessmodification
-optimizationpasses 10

-useuniqueclassmembernames
