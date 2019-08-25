import face_recognition
import numpy as np
from PIL import Image, ImageDraw
import os
import affnine_client  as client
import sys
import time
def main():
    known_face_encodings=[]
    for dirpath, dnames, fnames in os.walk("./emp_img"):
        for f in fnames:
            if f.endswith(".jpg") or f.endswith(".png"):
                print(f)
                var_ = face_recognition.load_image_file("emp_img/" + f)
                var_encoding = face_recognition.face_encodings(var_)[0]
                known_face_encodings.append(var_encoding)

    unknown_image = face_recognition.load_image_file("unknown_face/"+sys.argv[1])

    # Find all the faces and face encodings in the unknown image
    face_locations = face_recognition.face_locations(unknown_image)
    face_encodings = face_recognition.face_encodings(unknown_image, face_locations)


    # Loop through each face found in the unknown image
    for (top, right, bottom, left), face_encoding in zip(face_locations, face_encodings):
        matches = face_recognition.compare_faces(known_face_encodings, face_encoding)
        face_distances = face_recognition.face_distance(known_face_encodings, face_encoding)
        # print(face_distances)
        best_match_index = np.argmin(face_distances)
        if matches[best_match_index]:
            name = best_match_index
    try:
        if(face_distances[name] < 0.4):
            client.starter("127.0.0.1",8090,101)
            time.sleep(2)
            sender_obj=client.sender()
            sender_obj.sender_("102","msg","1")
            time.sleep(2)
        else:
            client.starter("127.0.0.1",8090,101)
            time.sleep(2)
            sender_obj=client.sender()
            sender_obj.sender_("102","msg","0")
            time.sleep(2)
    except:
            client.starter("127.0.0.1",8090,101)
            time.sleep(2)
            sender_obj=client.sender()
            sender_obj.sender_("102","msg","0")
            time.sleep(2)

##################################
if __name__ == "__main__":main()##
##################################




