# Android-Studio-app
An android app communicating with python server over TCP Socket
The application is found in mileStone0.zip
the app consists of two buttons:
  1)connect: Initiate connection with server with creating new socket
  2)send:Get data from editText,send it to the server over the tcp socket
The server gets the String data,Capitalize it then send it back to the client

Note:It is preferred to run the application on a physical device not an emulator because the Multithreaded server consumes much space from the RAM which can affect the effectiveness of the emulator 
