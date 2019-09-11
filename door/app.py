import affnine_client as client
import threading
import os

def data_fetch(listener_obj):
        while (True):
                data=listener_obj.data_()
                print("\n")
                try:
                    if(data["msg"]=="1"):
                        print("Open")
                        os.system('play /home/chayan/Desktop/ph_client_affnine/welcome.mp3')
                    else:
                        # print("Access Denied")
                        os.system('play /home/chayan/Desktop/ph_client_affnine/yo.mp3')
                except:
                    pass
                print("\n")

def main():
    client.starter("192.168.0.91",8005,102)
    listener_obj=client.listener()
    data_fetch_thread = threading.Thread(name='data_fetch', target=data_fetch, args=[listener_obj,])
    data_fetch_thread.start()
    sender_obj=client.sender()
#    image = "abc.png"
#    try:
#        myfile = open(image, 'rb')
#        bytes_ = myfile.read()
#        size = len(bytes)
#        print(bytes_)
#        Mac_Address=input("Enter  Target Mac address")
#        Topic=input("Enter the Topic Name")
#        Msg=input("Enter the Message")
#        sender_obj.sender_(Mac_Address,Topic,Msg)

#     while(True):
#         Mac_Address=input("Enter  Target Mac address")
#         Topic=input("Enter the Topic Name")
#         Msg=input("Enter the Message")
#         sender_obj.sender_(Mac_Address,Topic,Msg)



##################################
if __name__ == "__main__":main()##
##################################