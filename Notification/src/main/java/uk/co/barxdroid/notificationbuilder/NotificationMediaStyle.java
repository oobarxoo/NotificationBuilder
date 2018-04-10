package uk.co.barxdroid.notificationbuilder;

/**
 * Created by Darren on 09/06/2017.
 */

    import anywheresoftware.b4a.AbsObjectWrapper;
    import anywheresoftware.b4a.BA;
    import anywheresoftware.b4a.BA.ShortName;
    import anywheresoftware.b4a.keywords.Common;

    import android.app.PendingIntent;
    import android.content.Intent;
    import android.media.session.MediaSession.Token;
    import android.support.v4.media.app.NotificationCompat;
    import android.support.v4.media.session.MediaSessionCompat;

@ShortName("NotificationMediaStyle")

public class NotificationMediaStyle extends AbsObjectWrapper<NotificationCompat.MediaStyle> {

    /**
     * Initializes the object
     *
     * Note: Only works API 26.1.0+
     */
    public void Initialize(BA ba) {
        setObject(new NotificationCompat.MediaStyle());
    }

    /**
     * Sets the activity to call when the notification is cancelled
     */
    public void setCancelActivity(BA ba, Object Activity) throws ClassNotFoundException {
        //create pending intent
        Intent cancelIntent = Common.getComponentIntent(ba, Activity);
        PendingIntent cancelPendingIntent = PendingIntent.getActivity(ba.context, 0, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        ((NotificationCompat.MediaStyle)getObject()).setCancelButtonIntent(cancelPendingIntent);

        //add pending intent to object


    }



    /**
     * Not yet working
     */
    public void setMediaSession(MediaSessionCompat.Token key) {
        ((NotificationCompat.MediaStyle)getObject()).setMediaSession(key);
    }



    /**
     *
     */
    public void setShowActionsInCompactView(int actions) {
        ((NotificationCompat.MediaStyle)getObject()).setShowActionsInCompactView(actions);
    }
}