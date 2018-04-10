package uk.co.barxdroid.notificationbuilder;

/**
 * Created by Darren on 09/06/2017.
 */

        import anywheresoftware.b4a.AbsObjectWrapper;
        import anywheresoftware.b4a.BA;
        import anywheresoftware.b4a.BA.DependsOn;
        import anywheresoftware.b4a.BA.Author;
        import anywheresoftware.b4a.BA.ShortName;

        import android.graphics.Bitmap;
        import android.support.v4.app.NotificationCompat;
        import android.support.v4.app.NotificationCompat.BigPictureStyle;

@ShortName("NotificationBigPictureStyle")

public class NotificationBigPictureStyle extends AbsObjectWrapper<BigPictureStyle>{

    /**
     * Helper class for generating large-format notifications that include a large image attachment.
     * If the platform does not provide large-format notifications, this method has no effect.
     */
    public void Initialize(BA ba) {
        setObject(new NotificationCompat.BigPictureStyle());
    }

    /**
     * Overrides the ContentTitle set in the Notification Builder.
     */
    public void setBigContentTitle(String text) {
        ((BigPictureStyle)getObject()).setBigContentTitle(text);
    }

    /**
     * Sets the first line of text after the details section
     */
    public void setSummaryText(String text) {
        ((BigPictureStyle)getObject()).setSummaryText(text);
    }

    /**
     * Provides the Big Picture to be used in the BigPicture notification.
     */
    public void setBigPicture(Bitmap bitmap) {
        ((BigPictureStyle)getObject()).bigPicture(bitmap);
    }
}