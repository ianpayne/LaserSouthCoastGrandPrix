package com.idp.grandprix;

    import android.app.AlertDialog;  
    import android.content.Context;  
    import android.content.DialogInterface;  
    import android.content.DialogInterface.OnClickListener;  
      
    /** 
     * helper for Prompt-Dialog creation 
     */  
    public abstract class PromptDialog extends AlertDialog.Builder implements OnClickListener {  
 
      
     /** 
      * @param context 
      * @param title resource id 
      * @param message resource id 
      */  
     public PromptDialog(Context context, String title, String message) {  
      super(context);  
      setTitle(title);  
      setMessage(message);    
      
      setPositiveButton("OK", this);  
      setNegativeButton("Cancel", this);  
     }  
      
     /** 
      * will be called when "cancel" pressed. 
      * closes the dialog. 
      * can be overridden. 
      * @param dialog 
      */  
     public void onCancelClicked(DialogInterface dialog) {  
      dialog.dismiss();  
     }  
      
     @Override  
     public void onClick(DialogInterface dialog, int which) {  
      if (which == DialogInterface.BUTTON_POSITIVE) {  
       if (onOkClicked("")) {  
        dialog.dismiss();  
       }  
      } else {  
       onCancelClicked(dialog);  
      }  
     }  
      
     /** 
      * called when "ok" pressed. 
      * @param input 
      * @return true, if the dialog should be closed. false, if not. 
      */  
     abstract public boolean onOkClicked(String input);  
    }  