package uk.co.barxdroid.notificationbuilder;

/**
 * Created by Darren on 09/06/2017.
 */

    import anywheresoftware.b4a.AbsObjectWrapper;
    import anywheresoftware.b4a.BA;
    import anywheresoftware.b4a.BA.DependsOn;
    import anywheresoftware.b4a.BA.Author;
    import anywheresoftware.b4a.BA.DependsOn;
    import anywheresoftware.b4a.BA.ShortName;

    import android.support.v4.app.NotificationCompat;
    import android.support.v4.app.NotificationCompat.InboxStyle;

@ShortName("NotificationInboxStyle")

public class NotificationInboxStyle extends AbsObjectWrapper<InboxStyle>{

    String[] Items = new String[5];

    /**
     * Helper class for generating large-format notifications that include a list of (up to 5) strings.
     * If the platform does not provide large-format notifications, this method has no effect
     */
    public void Initialize(BA ba) {
        setObject(new NotificationCompat.InboxStyle());

    }

    /**
     * Overrides the ContentTitle set in the Notification Builder.
     */
    public void setBigContentTitle(String text) {
        ((InboxStyle)getObject()).setBigContentTitle(text);
    }

    /**
     * Sets the first line of text after the details section
     */
    public void setSummaryText(String text) {
        ((InboxStyle)getObject()).setSummaryText(text);
    }

    /**
     * Adds a line to the details section.
     * You can add a MAXIMUM of 5 lines.
     *
     */
    public void setAddLine(String text) {
        ((InboxStyle)getObject()).addLine(text);
    }
}