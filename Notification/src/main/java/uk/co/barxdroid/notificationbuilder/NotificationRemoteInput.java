package uk.co.barxdroid.notificationbuilder;

/**
 * Created by Darren on 09/06/2017.
 */

    import anywheresoftware.b4a.AbsObjectWrapper;
    import anywheresoftware.b4a.BA;

    import anywheresoftware.b4a.BA.ShortName;

    import android.content.Intent;
    import android.os.Bundle;
    import android.support.v4.app.RemoteInput;
    import android.support.v4.app.RemoteInput.Builder;;

@ShortName("NotificationRemoteInput")

public class NotificationRemoteInput extends AbsObjectWrapper<Builder>{

    /**
     * Initializes the object.
     *
     * Label - Used to retrieve the reply later.
     *
     * See GetRemoteInput for details on how to get the input.
     *
     * NOTE: you do not need to call Initialize in order to use GetRemoteInput.
     */
    public void Initialize(BA ba, String Label) {
        setObject(new RemoteInput.Builder(Label));
    }

    /**
     * Sets the label to show when requesting for input
     */
    public void setLabel(String Label) {
        ((Builder)getObject()).setLabel(Label);
    }

    /**
     * Specifies whether the user can supply arbitrary values
     */
    public void setAllowDataType(String mimeType, boolean doAllow) {
        ((Builder)getObject()).setAllowDataType(mimeType, doAllow);
    }

    /**
     * Specifies whether the user can provide arbitrary text values
     */
    public void setAllowFreeFormInput(boolean v) {
        ((Builder)getObject()).setAllowFreeFormInput(v);
    }

    /**
     * Set a list of preset choices to the user to satisfy this input.
     *
     * example:
     * <code>RemoteInput1.Choices = Array as String("Yes", "No", "Maybe")</code>
     */
    public void setChoices(String[] Choices) {
        ((Builder)getObject()).setChoices(Choices);
    }

    /**
     * Extracts a RemoteInput (voice) string from starting intent
     *
     * Use this in the Activity_Resume of the Activity called by the Action.
     * Example:
     *
     * <code>
     * Dim In as intent
     * In = Activity.GetStartingIntent
     * If In.HasExtra("Notification_Wear_Action_Tag") Then
     *     If In.GetExtra("Notification_Wear_Action_Tag") = Tag Then 'Tag set when adding Action to Notification
     *         Dim Rem as NotificationRemoteInput
     *
     *         ToastMessageShow(Rem.GetRemoteInput(In, Label), false) 'Label set on Initialize
     *     End If
     * End If
     * </code>
     */
    public String GetRemoteInput(Intent intent, String key) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            try {
                return remoteInput.getCharSequence(key).toString();
            }
            catch(NullPointerException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Builds and Returns the Remote Input object
     */
    public RemoteInput Build() {
        return ((Builder)getObject()).build();
    }
}