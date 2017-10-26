-dontoptimize

-keepnames public !abstract class * extends android.app.Activity
-keepnames public !abstract class * extends android.app.Service
-keepnames public !abstract class * extends android.content.BroadcastReceiver
-dontwarn com.google.common.**
-dontwarn com.google.appengine.**
-dontwarn com.google.android.gms.**
-dontwarn com.google.api.client.**
-assumenosideeffects class android.util.Log { *; }
-keepnames class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
-keepnames public !abstract class * extends android.view.View {
    public <init>(...);
}
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.Unsafe
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
-dontwarn java.lang.management.**

-keepattributes InnerClasses,EnclosingMethod

-keepclassmembers public enum * { *; }

-repackageclasses droidcomplex

-keep class org.apfloat.** { *; }

-keep class * implements groovy.lang.GroovyObject { *; }

-keep class org.codehaus.groovy.vmplugin.**
-keep class org.codehaus.groovy.runtime.dgm*

-keepclassmembers class org.codehaus.groovy.runtime.dgm* {*;}
-keepclassmembers class ** implements org.codehaus.groovy.runtime.GeneratedClosure {*;}
-keepclassmembers class org.codehaus.groovy.reflection.GroovyClassValue* {*;}

-dontwarn org.codehaus.groovy.**
-dontwarn groovy**

