import socket

SERVER_ADDRESS = ("localhost", 8080)
BUFFER_SIZE = 1024

class TokenRingClient:
   def __init__(self):
       self.client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

   def connect(self):
       self.client_socket.connect(SERVER_ADDRESS)
       print(f"Connected to server as {self.client_socket.getsockname()}")

   def start(self):
       try:
           while True:
               data = self.client_socket.recv(BUFFER_SIZE).decode()
               if data == "TOKEN":
                   print(f"[{self.client_socket.getsockname()}] Token received. Accessing resource.")
                   # Perform operations on the resource

                   # Simulating work on the resource
                   print(f"[{self.client_socket.getsockname()}] Working on the resource...")
                   # Simulating work by sleeping for 5 seconds
                   import time

                   time.sleep(5)

                   print(f"[{self.client_socket.getsockname()}] Resource access complete. Releasing token.")
                   self.client_socket.send("TOKEN".encode())

               if data == "CLOSE":
                   print("Closing client..")
                   self.stop()
                   break

       except KeyboardInterrupt:
           print("Closing client..")
           self.client_socket.send("CLOSE".encode())
           self.stop()

   def stop(self):
       self.client_socket.close()


if __name__ == "__main__":
   client = TokenRingClient()
   client.connect()
   client.start()
