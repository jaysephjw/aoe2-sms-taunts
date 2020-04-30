# AoE2 Taunts in SMS

An Android app that plays AoE2 taunts when you receive an SMS Message.

### Installation

To get the app onto your phone, you currently need to build it from this source code.
I recommend using Android Studio.

The steps are roughly:
* Enable 'Developer Options' on your phone
* Plug phone in to computer
* Install Android Studio
* Download this source code into a folder on your machine
* Open the code in Android Studio with `Open an existing Android Studio Project`
* Press 'Run' (the green "play" arrow icon or Shift + F10).

### Usage

* Open the app to grant the read_sms permission
* Now taunt sounds will play when receiving certain SMS messages

_A note on permissions:_  
Yes, you'll need to let this app have SMS permissions to read your messages.  
It does nothing but check messages for matches to play the built in sounds,
and doesn't even use the internet. Always make sure you're comfortable before  
running someone else's code :)

### Sounds supported

* 1-42, 104, and 105 from https://ageofempires.fandom.com/wiki/Taunts

### TODO

* Honor system silent mode. Ideally play only when a notification sound would
* Play sounds on outgoing messages too 
