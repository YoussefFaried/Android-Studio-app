import socket
import threading
import time 

# next create a socket object
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print("Socket successfully created")

# reserve a port on your computer in our
# case it is 12345 but it can be anything
port = 7777

# Next bind to the port
# we have not typed any ip in the ip field
# instead we have inputted an empty string
# this makes the server listen to requests
# coming from other computers on the network
s.bind(('', port))
print("socket binded to %s" % (port))

# put the socket into listening mode
s.listen(5)
print("socket is listening")
print("------------------------------------------------------")


#number of current active clients on the server
nc=0
#Address of current active clients 
info=[]


# a forever loop until we interrupt it or
# an error occurs
def clientConnection(s):
     c, addr = s.accept()
     global nc
     global info
     nc=nc+1
     info.append(addr)
     
     print('Got connection from', addr," at",time.asctime())
     print("number of active clients: ",nc)
     print("connected to clients:",info)
     print("------------------------------------------------------")
    # send a thank you message to the client. encoding to send byte type.
     while True:
        sentence = c.recv(1024).decode()


        print(sentence)
        Capital_Sentence = sentence.upper()
        c.send(Capital_Sentence.encode())
     c.close()
while True:
    t=threading.Thread(target=clientConnection,args=[s])
    t.start()

    # Establish connection with client.
   