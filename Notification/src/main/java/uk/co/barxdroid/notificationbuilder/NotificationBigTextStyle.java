package uk.co.barxdroid.notificationbuilder;

/**
 * Created by Darren on 09/06/2017.
 */

        import anywheresoftware.b4a.AbsObjectWrapper;
        import anywheresoftware.b4a.BA;
        import anywheresoftware.b4a.BA.DependsOn;
        import anywheresoftware.b4a.BA.Author;
        import anywheresoftware.b4a.BA.ShortName;

        import android.support.v4.app.NotificationCompat;
        import android.support.v4.app.NotificationCompat.BigTextStyle;

@ShortName("NotificationBigTextStyle")

public class NotificationBigTextStyle extends AbsObjectWrapper<BigTextStyle>{

    /**
     * Initializes the object
     */
    public void Initialize(BA ba) {
        setObject(new NotificationCompat.BigTextStyle());
    }

    /**
     * Overrides the ContentTitle set in the Notification Builder.
     */
    public void setBigContentTitle(String text) {
        ((BigTextStyle)getObject()).setBigContentTitle(text);
    }

    /**
     * Sets the first line of text after the details section
     */
    public void setSummaryText(String text) {
        ((BigTextStyle)getObject()).setSummaryText(text);
    }

    /**
     * Provides the longer text to displayed in the content area. Replaces ContextText.
     */
    public void setBigText(String text) {
        ((BigTextStyle)getObject()).bigText(text);
    }
}