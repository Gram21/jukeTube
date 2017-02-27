# jukeTube

Prototype for a web-server/service/application that gives users the possibility to remotely add (and control) the playlist of a hosting server. The general idea is to make it possible for parties and other events that guests can put in their preferred music via YouTube (for now, possible other sound/music sources imaginable). This way guests can "control" the music flow remotely without having to go to the playback pc and search for everything one after another and control the playback etc.

Obviously there should be filters and other regulations, but as this is more or less a prototype they don't exist (yet).

The technology stack is to host a webserver, that can be reached via browser (in your local area network), where you can put in youtube urls (a direct search and more user friendly input is thinkable here). The link then gets added to the playlist of the hosting server. There the next track is evaluated (shuffle, repeat mode etc) and vlc is used to play those tracks (again other media playback options are thinkable here).
