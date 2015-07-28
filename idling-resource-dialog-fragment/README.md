Custom IdlingResource: Dialog Fragment
--------------------------------------

Exercise for the reader! `MainActivity` shows a loading dialog, then changes a `TextView` to done
when the dialog dismisses. How do we tell our test to wait for the dialog to dismiss before we check
the `TextView`?

References:
  * [idling-resource-intent-service](../idling-resource-intent-service)
  * [idling-resource-elapsed-time](../idling-resource-elapsed-time)

Solution: http://blog.sqisland.com/2015/07/espresso-wait-for-dialog-to-dismiss.html
