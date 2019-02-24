# EECS2311-Project

TalkBox App Project by Nina, Michael, Mostafa, and Ryan (Group 13)

Contents of Repository:
-TalkBox Folder (Java Project)
-Documentaton Folder
-Images Folder
-Audio Folder

Project Information:

TalkBox is a device that helps anybody, who is unable to talk, communicate. 
Each TalkBox has a number of buttons that the user can press to play pre-recorded audio files. 
Some of the buttons on the TalkBox may be used to load different sets of audio files.

Functionality (Basic Explanation):

A user opens up a configuration app and sets it up based on their specific needs. The configuration is then sent to the
simulator frame app which outputs and simmulates the TalkBox appropiates, mimicking the physical hardware in the process.

**Note that this set-up process can be found in the user manual documentation**

Functionality (Detailed Explanation):

The TalkBox functions via two main apps:

1. TalkBox Simulator
--> A piece of software that simulates the behaviour of any TalkBox device.
--> Has a user interface similar to that of the device.
--> The number of buttons and their functionality is configurable.
--> Is fully tested to behave as the hardware device.

2. TalkBox Configuration app
--> A user-friendly GUI-based app that allows for the configuration of a TalkBox device with appropriate audio.
--> It will provide facilities to record audio, or select already pre-recorded audio files.
--> It will allow the user to associate audio files with buttons in an intuitive way.
--> It will store the configuration in a USB flash drive to be used with an actual TalkBox, or will launch the Talkbox Simulator to test.

The TalkBox Configuration app and the TalkBox simulator will communicate through the use of a TalkBoxConfiguration object that will be serialized
This will in turn allow for the physical TalkBox hardware to function properly.

