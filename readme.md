TODO

- Restore the notification with the updated information on the boot event ONLY IF the notification was present before
  the (re)boot. For that we can store information about the notification in app pref or db. And if it's not empty then restore the notification.
- Move strings to resources
- Add DI framework
- Make usecases main thread friendly